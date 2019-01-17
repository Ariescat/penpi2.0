package org.penpi.app.controller;

import java.util.List;

import org.penpi.app.dto.OrderItem;
import org.penpi.app.dto.OrderParams;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.Order;
import org.penpi.subsys.service.OrderSearchService;
import org.penpi.subsys.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerMapping.APP_ORDER)
public class MemberOrderController {

	/**
	 * 无参（无刷选条件）
	 */
	@RequestMapping(value = ControllerMapping.GET_ORDERS)
	public List<OrderItem> getOrders() {
		return SpringContextHelper.getBean(OrderSearchService.class).getOrders();
	}
	
	/**
	 * 根据参数获取订单
	 */
	@RequestMapping(value = ControllerMapping.GET_ORDERS_BY_PARAMS, method = RequestMethod.POST)
	public List<OrderItem> getOrdersByParams(@RequestBody OrderParams params) {
		return SpringContextHelper.getBean(OrderSearchService.class).getOrdersByParams(params);
	}

	@RequestMapping(value = ControllerMapping.SEND_ORDER, method = RequestMethod.POST)
	public OrderItem sendOrder(@RequestBody Order order) {
		return SpringContextHelper.getBean(OrderService.class).sendOrder(order);
	}
	
	@RequestMapping(value = ControllerMapping.GRAB_ORDER)
	public Order grabOrder(Integer userId, Integer orderId, Double endPlaceLng, Double endPlaceLat) {
		return SpringContextHelper.getBean(OrderService.class).grabOrder(userId, orderId, endPlaceLng, endPlaceLat);
	}
	
	//2018.5.24
	@RequestMapping(value = ControllerMapping.QUERY_ORDERBY_ID)
	public List<Order> queryOrdersById(Integer userId) {
		return SpringContextHelper.getBean(OrderSearchService.class).findByKey(Order.SEND_USER_ID, userId);
	}
}
