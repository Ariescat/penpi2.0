package org.penpi.subsys.service;

 

import java.util.List;

import org.penpi.core.commons.base.BaseSearchService;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.utils.PageBean;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.OrderExceptionRecord;
import org.penpi.subsys.search.OrderExceptionRecordSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderExceptionRecordSearchService extends BaseSearchService<OrderExceptionRecord, Integer,OrderExceptionRecordSearchRepository> {

	
	@Transactional(readOnly = true)
	public PageBean<OrderExceptionRecord> findOrdersExcepRecord(Integer pageNum) {
 
		PageRequest pageable=new PageRequest(pageNum-1, Global.size, new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC,OrderExceptionRecord.ORDER_EXCEPTION_RECORD_ID)));
		 
		Page<OrderExceptionRecord> pageOrderExceptionRecord = SpringContextHelper.getBean(OrderExceptionRecordSearchService.class).getRepository().findAll(pageable);
	    List<OrderExceptionRecord> orders = pageOrderExceptionRecord.getContent();//获取结果集  
	 
	    PageBean<OrderExceptionRecord> orderExceptionRecordPageBean = new PageBean<OrderExceptionRecord>(pageNum, Global.size, pageOrderExceptionRecord.getTotalPages());  
	    orderExceptionRecordPageBean.setData(orders);
 
		return orderExceptionRecordPageBean;
	}
	
	

}
