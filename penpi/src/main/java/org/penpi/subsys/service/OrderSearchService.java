package org.penpi.subsys.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.penpi.app.dto.OrderItem;
import org.penpi.app.dto.OrderParams;
import org.penpi.client.dto.OrderExcepMsg;
import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.utils.ListExcelWriter;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.core.utils.PageBean;
import org.penpi.core.utils.UtilDistance;
import org.penpi.subsys.Global;
import org.penpi.subsys.code.OrderStatCodeEnum;
import org.penpi.subsys.entity.Order;
import org.penpi.subsys.entity.OrderExceptionRecord;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.search.OrderSearchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderSearchService extends BaseSearchService<Order, Integer, OrderSearchRepository> {

	@Transactional(readOnly = true)
	public List<OrderItem> getOrders() {
		List<Order> orders = SpringContextHelper.getBean(OrderSearchService.class).findByKey(Order.ORDER_STAT_CODE, OrderStatCodeEnum.HAS_SEND.toCode(),
				new org.springframework.data.domain.Sort.Order(Direction.DESC, Order.CREATE_DATE));
		List<OrderItem> list = new ArrayList<OrderItem>();
		for (Order order : orders) {
			User sendUser = SpringContextHelper.getBean(UserSearchService.class).getById(order.getSendUserId());
			OrderItem orderShow = new OrderItem();
			BeanUtils.copyProperties(order, orderShow);
			orderShow.setUserSexCode(sendUser.getUserSexCode());
			list.add(orderShow);
		}
		return list;
	}
	
	/**
	 * 根据参数获取订单
	 */
	@Transactional(readOnly = true)
	public List<OrderItem> getOrdersByParams(OrderParams params) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.termQuery(Order.ORDER_STAT_CODE, OrderStatCodeEnum.HAS_SEND.toCode()));
		if (params.getFromTime() != null && params.getToTime() != null) {
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Order.CREATE_DATE)
					.from(params.getFromTime())
					.to(params.getToTime())
					.includeLower(true) // 包含上界
					.includeUpper(true)); // 包含下届
		}
		
		if (params.getLocationLat() == null && params.getLocationLng() == null) {
			params.setLocationLng(Global.DEFAULT_LNG);
			params.setLocationLat(Global.DEFAULT_LAT);
		}
		
		Page<Order> page = this.getRepository().search(boolQueryBuilder, new PageRequest(0, 10000,
				new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC, Order.CREATE_DATE))));
		List<OrderItem> list = new ArrayList<OrderItem>();
		
		for (Order order : page.getContent()) {
			User sendUser = SpringContextHelper.getBean(UserSearchService.class).getById(order.getSendUserId());
			if (StringUtils.isEmpty(params.getUserSexCode()) || sendUser.getUserSexCode().equals(params.getUserSexCode())) {
				OrderItem orderShow = new OrderItem();
				BeanUtils.copyProperties(order, orderShow);
				orderShow.setUserSexCode(sendUser.getUserSexCode());
				double distance = UtilDistance.getDistance(params.getLocationLng(), params.getLocationLat(),
						order.getStartPlaceLng(), order.getStartPlaceLat());
				orderShow.setDistance(distance);
				if (params.getDistance() == null || distance < params.getDistance()) {
					list.add(orderShow);
				}
			}
		}
		
		// 排序如果为距离
		if (StringUtils.isNotBlank(params.getOrderType()) && params.getOrderType().equals("distance")) {
			Collections.sort(list, new Comparator<OrderItem>() {
				@Override
				public int compare(OrderItem o1, OrderItem o2) {
					return o1.getDistance().compareTo(o2.getDistance());
				}
			});
		}
		
		return list;
	}
	
	@Transactional(readOnly = true)
	public PageBean<Order> findOrdersByPage(Integer pageNum,String sortType,String orderType,String sendUserMobile) {
			PageRequest pageable = null;
			 
			if(sortType.equals("0")) {  //默认按时间排序
				pageable=new PageRequest(pageNum-1, Global.size, new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC,Order.ORDER_ID)));
			}else if(sortType.equals("1")){
				pageable=new PageRequest(pageNum-1, Global.size, new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC,Order.ORDER_FEE)));
			}
			 Page<Order> pageOrder = null;
			 if(sendUserMobile==null||sendUserMobile.equals("")) {
				 if(orderType.equals("-1")) {
			    	 pageOrder = SpringContextHelper.getBean(OrderSearchService.class).getRepository().findAll(pageable);
			     }else{
			    	 pageOrder = SpringContextHelper.getBean(OrderSearchService.class).getRepository().findByOrderStatCode(orderType,pageable);          
			     }
			 }else {
				 pageOrder = SpringContextHelper.getBean(OrderSearchService.class).getRepository().findByOrderStatCodeAndSendUserMobile(orderType,sendUserMobile,pageable);   
			 }
		     
		     List<Order> orders = pageOrder.getContent();//获取结果集  
		 
		     PageBean<Order> orderPageBean = new PageBean<Order>(pageNum, Global.size, pageOrder.getTotalPages());  
		     orderPageBean.setData(orders);
		    
		    return orderPageBean; 
	}

	@Transactional(readOnly = true)
	public OrderExcepMsg findAnOrder(Integer orderId) {
		OrderExcepMsg orderExcepMsg = new OrderExcepMsg();
		Order order = SpringContextHelper.getBean(OrderSearchService.class).getById(orderId);
		orderExcepMsg.setEndPlace(order.getEndPlace());
		orderExcepMsg.setSendUserMobile(order.getSendUserMobile());
		orderExcepMsg.setSendUserNm(order.getSendUserNm());
		orderExcepMsg.setStartPlace(order.getStartPlace());
		
		if(order.getTakeUserId() != null) {
			User takeUser = SpringContextHelper.getBean(UserSearchService.class).getById(order.getTakeUserId());
			orderExcepMsg.setTakeUserNm(takeUser.getUserNm());
			orderExcepMsg.setTakeUserMobile(takeUser.getUserMobile());
		}
	
		return orderExcepMsg;
	 
	}
	
	@Transactional(readOnly = true)
	public String allOrderExcel(String type) throws IOException {
		Iterable<Order> orders = null;
		ListExcelWriter writer = new ListExcelWriter(WebContextHolder.getWarPath()+File.separator+"exceltemplate"+File.separator+"allOrderExcelExample.xls");
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		UUID name = UUID.randomUUID();
		String outFileName = WebContextHolder.getWarPath()+File.separator+"temp" + File.separator+name + ".xls";
		
		if(type.equals("0"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findAll();
		else if(type.equals("1"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findByOrderStatCode("0");
		else if(type.equals("2"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findByOrderStatCode("1");
		else if(type.equals("3"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findByOrderStatCode("2");
		else if(type.equals("4"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findByOrderStatCode("3");
		else if(type.equals("5"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findByOrderStatCode("4");
		else if(type.equals("6"))
			orders = SpringContextHelper.getBean(this.getClass()).getRepository().findByOrderStatCode("5");
		else if(type.equals("7")) {
			Iterable<OrderExceptionRecord> orderExceptionRecords = null;
			orderExceptionRecords = SpringContextHelper.getBean(OrderExceptionRecordSearchService.class).getRepository().findByIfFixException("Y");
			for(OrderExceptionRecord orderExceptionRecord:orderExceptionRecords) {
				Map<String, Object> data = new HashMap<String, Object>();
				Order order = SpringContextHelper.getBean(this.getClass()).getRepository().findOne(orderExceptionRecord.getOrderId());		 
				data.put("sendUserMobile",order.getSendUserMobile());
				data.put("startPlace", order.getStartPlace());
				data.put("endPlace", order.getEndPlace());
				data.put("creatTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateDate()));
				if(order.getTakeOrderDate()!=null)data.put("takeOrderDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getTakeOrderDate()));
				data.put("orderFee", order.getOrderFee());
				if(order.getOrderStatCode().equals("0"))
					data.put("orderStat","发单");
				else if(order.getOrderStatCode().equals("1"))
					data.put("orderStat","已抢");
				else if(order.getOrderStatCode().equals("2"))
					data.put("orderStat","交付");
				else if(order.getOrderStatCode().equals("3"))
					data.put("orderStat","支付");
				else if(order.getOrderStatCode().equals("4"))
					data.put("orderStat","完成");	
				if(orderExceptionRecord.getExceptionTime()!=null)data.put("exceptionTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderExceptionRecord.getExceptionTime()));
				data.put("exceptionReason", orderExceptionRecord.getExceptionReason());
				data.put("ifFixException", orderExceptionRecord.getIfFixException().equals("Y")?"已修复":"未修复");
				if(orderExceptionRecord.getFinishFixTime()!=null)data.put("finishFixTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderExceptionRecord.getFinishFixTime()));				
				data.put("orderRemark",order.getOrderRemark());		
				dataList.add(data);
			}
			 writer.fillToFile(dataList, outFileName);
	 
			 return name + ".xls";
		
		}
		if(orders != null) {
			for(Order order:orders) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("sendUserMobile",order.getSendUserMobile());
				User takeUser = SpringContextHelper.getBean(UserSearchService.class).getById(order.getTakeUserId());
				if(takeUser!=null)data.put("takeUserMobile",takeUser.getUserMobile());
				data.put("startPlace", order.getStartPlace());
				data.put("endPlace", order.getEndPlace());
				data.put("creatTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateDate()));
				if(order.getTakeOrderDate()!=null)data.put("takeOrderDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getTakeOrderDate()));
				data.put("orderFee", order.getOrderFee());
				if(order.getOrderStatCode().equals("0"))
					data.put("orderStat","发单");
				else if(order.getOrderStatCode().equals("1"))
					data.put("orderStat","已抢");
				else if(order.getOrderStatCode().equals("2"))
					data.put("orderStat","交付");
				else if(order.getOrderStatCode().equals("3"))
					data.put("orderStat","支付");
				else if(order.getOrderStatCode().equals("4"))
					data.put("orderStat","完成");
				else {
					data.put("orderStat","异常");
					OrderExceptionRecord orderExceptionRecord = SpringContextHelper.getBean(OrderExceptionRecordSearchService.class).getRepository().findByOrderIdAndIfFixException(order.getOrderId(),"N");	
					if(orderExceptionRecord != null) {
						if(orderExceptionRecord.getExceptionTime()!=null)data.put("exceptionTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderExceptionRecord.getExceptionTime()));
						data.put("exceptionReason", orderExceptionRecord.getExceptionReason());
						data.put("ifFixException", orderExceptionRecord.getIfFixException().equals("Y")?"已修复":"未修复");
						if(orderExceptionRecord.getFinishFixTime()!=null)data.put("finishFixTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderExceptionRecord.getFinishFixTime()));				
					}
					
				}
				data.put("orderRemark",order.getOrderRemark());	
				dataList.add(data);
			}
			}
		 writer.fillToFile(dataList, outFileName);
 
		 return name + ".xls";
	}

}
