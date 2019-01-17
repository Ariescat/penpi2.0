package org.penpi.subsys.service;

import java.util.Date;

import org.penpi.core.commons.base.BaseService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.code.BoolCodeEnum;
import org.penpi.subsys.code.OrderStatCodeEnum;
import org.penpi.subsys.entity.Order;
import org.penpi.subsys.entity.OrderExceptionRecord;
import org.penpi.subsys.repository.OrderExceptionRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderExceptionRecordService extends BaseService<OrderExceptionRecord, Integer, OrderExceptionRecordRepository>{

	public void setExcepRecord(String orderId, String orderType, String excepReason) {
	 
		OrderExceptionRecord OrderExceptionRecordRecord = new OrderExceptionRecord();
		OrderExceptionRecordRecord.setExceptionReason(excepReason);
		OrderExceptionRecordRecord.setExceptionTime(new Date());
		OrderExceptionRecordRecord.setIfFixException("N");
		OrderExceptionRecordRecord.setOrderId(Integer.valueOf(orderId));
		SpringContextHelper.getBean(this.getClass()).save(OrderExceptionRecordRecord);
	}
	
	@Transactional
	public OrderExceptionRecord repairExcepRecord(Integer orderId){
		OrderExceptionRecord OrderExceptionRecordRecord = SpringContextHelper.getBean(OrderExceptionRecordSearchService.class).getRepository().findByOrderIdAndIfFixException(orderId,"N");
		OrderExceptionRecordRecord.setIfFixException(BoolCodeEnum.YES.toCode());
		OrderExceptionRecordRecord.setFinishFixTime(new Date());
		SpringContextHelper.getBean(this.getClass()).save(OrderExceptionRecordRecord);
		
		Order order = SpringContextHelper.getBean(OrderSearchService.class).getById(orderId);
		order.setOrderStatCode(OrderStatCodeEnum.HAS_SEND.toCode());
		SpringContextHelper.getBean(OrderService.class).save(order);
		return OrderExceptionRecordRecord;
		
	}

}
