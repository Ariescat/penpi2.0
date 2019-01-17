package org.penpi.client.controller;

import javax.servlet.http.HttpSession;

import org.penpi.core.utils.PageBean;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.OrderExceptionRecord;
import org.penpi.subsys.service.OrderExceptionRecordSearchService;
import org.penpi.subsys.service.OrderExceptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("adminOrderExcepRecordController")
@RequestMapping(ControllerMapping.ADMIN_ORDER_Excep_Record)   /* /admin/orderexcep */
public class OrderExceptionController {

	@Autowired
	OrderExceptionRecordSearchService orderExceptionRecordSearchService;
	@Autowired
	OrderExceptionRecordService orderExceptionRecordService;
	
	@RequestMapping(ControllerMapping.ADMIN_FIND_ALL_ORDERS_EXCEP_RECORD)
	public ModelAndView findAllOrders(Integer pageNum,HttpSession session){
		PageBean<OrderExceptionRecord> pagebean = orderExceptionRecordSearchService.findOrdersExcepRecord(pageNum); 
		session.setAttribute("pagebean", pagebean);
		return new ModelAndView("forward:/WEB-INF/pages/allOrdersExcepRecord.jsp");		
	}
	
	
	@RequestMapping(value=ControllerMapping.ADMIN_REPAIR_EXCEP_RECORD,produces="text/html;charset=UTF-8;")
	public String repairOrder(String orderId) throws JsonProcessingException{	
		OrderExceptionRecord orderExceptionRecord = orderExceptionRecordService.repairExcepRecord(Integer.valueOf(orderId));
		ObjectMapper mapper = new ObjectMapper();  
		String orderExcepJson = mapper.writeValueAsString(orderExceptionRecord); 
		return orderExcepJson;	 
	}
	
	
}
