package org.penpi.subsys.service;

import java.util.Date;

import org.penpi.app.dto.OrderItem;
import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.exception.BusinessException;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.ClientUser;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.Global;
import org.penpi.subsys.ResGlobal;
import org.penpi.subsys.code.OrderStatCodeEnum;
import org.penpi.subsys.entity.Order;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService extends BaseService<Order, Integer, OrderRepository> {

	@Transactional
	public OrderItem sendOrder(Order order) {
		order.setCreateDate(new Date());
		order.setOrderStatCode(OrderStatCodeEnum.HAS_SEND.toCode());
		User user = SpringContextHelper.getBean(UserSearchService.class).getById(order.getSendUserId());
		order.setSendUserNm(user.getUserNm());
		order.setSendUserMobile(user.getUserMobile());
		SpringContextHelper.getBean(OrderService.class).save(order);
		
		OrderItem orderItem = new OrderItem(order);
		orderItem.setUserSexCode(user.getUserSexCode());
		return orderItem;
	}

	/**
	 * 抢单
	 * 1、需要判断param的userId和seesion的userId是否相等
	 * 2、防止抢同一订单
	 */
	@Transactional
	public Order grabOrder(Integer userId, Integer orderId, Double endPlaceLng, Double endPlaceLat) {
		
		ClientUser clientUser = WebContextHolder.getSessionContextStore().getServerValue(Global.SESSION_LOGIN_MEMBER);
		if (clientUser == null || !userId.equals(clientUser.getUserId())) {
			throw new BusinessException(ResGlobal.ERRORS_USER_DEFINED, ResGlobal.LOGIG_ILLEGAL);
		}
		
		Order order = SpringContextHelper.getBean(OrderSearchService.class).getById(orderId);
		if (order.getOrderStatCode().equals(OrderStatCodeEnum.HAS_SEND.toCode())) {
			order.setTakeOrderDate(new Date());
			order.setTakeUserId(userId);
			if (endPlaceLng == 0 && endPlaceLat == 0) {
				order.setEndPlaceLat(Global.DEFAULT_LAT);
				order.setEndPlaceLng(Global.DEFAULT_LNG);
			} else {
				order.setEndPlaceLat(endPlaceLat);
				order.setEndPlaceLng(endPlaceLng);
			}
			order.setOrderStatCode(OrderStatCodeEnum.HAS_GRAB.toCode());
			SpringContextHelper.getBean(OrderService.class).save(order);
			return order;
		}
		throw new BusinessException(ResGlobal.ERRORS_USER_DEFINED, new String[] {"订单已被抢啦"});
	}
	
	@Transactional
	public Order setExcepOrder(String orderId) {
		Order order = null;
		order = SpringContextHelper.getBean(this.getClass()).getById(Integer.valueOf(orderId));
		order.setOrderStatCode("5");
		SpringContextHelper.getBean(this.getClass()).save(order);
		return order;
	}

}
