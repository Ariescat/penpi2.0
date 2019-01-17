package org.penpi.client.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.penpi.client.dto.OrderExcepMsg;
import org.penpi.core.utils.PageBean;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.Order;
import org.penpi.subsys.service.OrderExceptionRecordService;
import org.penpi.subsys.service.OrderSearchService;
import org.penpi.subsys.service.OrderService;
import org.penpi.subsys.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("adminOrderController")
@RequestMapping(ControllerMapping.ADMIN_ORDER)   /* /admin/order */
public class OrderController {
	
	@Autowired
	OrderSearchService orderSearchService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderExceptionRecordService orderExceptionRecordService;
	@Autowired
	UserSearchService userSearchService;
	
	@RequestMapping(ControllerMapping.ADMIN_FIND_ALL_ORDERS)
	public ModelAndView findAllOrders(Integer pageNum,String sortType,String orderType,String sendUserMobile,HttpSession session){
		PageBean<Order> pagebean = orderSearchService.findOrdersByPage(pageNum,sortType,orderType,sendUserMobile); 
		session.setAttribute("pagebean", pagebean);
		session.setAttribute("sortType", sortType);
		session.setAttribute("orderType", orderType);
		session.setAttribute("sendUserMobile", sendUserMobile);
		/*System.out.println(pagebean.getData().size());*/
		return new ModelAndView("forward:/WEB-INF/pages/allorders.jsp");		
	}
	
	@RequestMapping(ControllerMapping.ADMIN_SET_EXCEPTION_ORDER)
	public String setExcepOrder(String orderId,String orderType,String excepReason) {
		Order order = orderService.setExcepOrder(orderId);
		if(order != null) {
			orderExceptionRecordService.setExcepRecord(orderId,orderType,excepReason);
		}
		
		return null;
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_FIND_AN_ORDER,produces="text/html;charset=UTF-8;")
	public String findAnOrder(String orderId) throws JsonProcessingException {
		OrderExcepMsg orderExcepMsg = orderSearchService.findAnOrder(Integer.valueOf(orderId));
		ObjectMapper mapper = new ObjectMapper();  
		String orderJson = mapper.writeValueAsString(orderExcepMsg); 
		return orderJson;
		
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_PRINT_ORDER,produces="text/html;charset=UTF-8;")
	public String printOrder(String type) throws IOException {
	
		 return orderSearchService.allOrderExcel(type);
		
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_ORDER_STAT_STATISTIC,produces="text/html;charset=UTF-8;")
	public String statStatistic() throws IOException {
		int hasSendStat = orderSearchService.getRepository().findByOrderStatCode("0").size();
		int hasTake = orderSearchService.getRepository().findByOrderStatCode("1").size();
		int takePpay = orderSearchService.getRepository().findByOrderStatCode("2").size();
		int pay = orderSearchService.getRepository().findByOrderStatCode("3").size();
		int finish = orderSearchService.getRepository().findByOrderStatCode("4").size();
		int exception = orderSearchService.getRepository().findByOrderStatCode("5").size();
		 return "{\"hasSendStat\":\"" + hasSendStat + "\",\"hasTake\":\"" + hasTake+ "\",\"takePpay\":\""+takePpay+"\",\"pay\":\""+pay+"\",\"finish\":\""+finish+"\",\"exception\":\""+exception+"\"}";
		 
	
	}
	
}
