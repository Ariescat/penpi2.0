package org.penpi.client.controller;

import javax.servlet.http.HttpSession;

import org.penpi.core.utils.PageBean;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.OperateRecord;
import org.penpi.subsys.service.OperateRecordSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("adminOpLogController")
@RequestMapping(ControllerMapping.ADMIN_OPLOG) /* /admin/oplog */
public class OpLogController {
	
	@Autowired
	OperateRecordSearchService operateRecordSearchService;
	
	@RequestMapping(ControllerMapping.ADMIN_OPERATE_USER_LOG)
	public ModelAndView findAllUserOpLog(Integer pageNum,HttpSession session) {	
		 
		PageBean<OperateRecord> pagebean = operateRecordSearchService.findUserOpLogByPage(pageNum); 
		session.setAttribute("pagebean", pagebean);
		 
		return new ModelAndView("forward:/WEB-INF/pages/operateUserLog.jsp");		
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_OPERATE_USER_LOG_BY_PAGE,produces="text/html;charset=UTF-8;")
	public String findUsersByPage(String pageNum) throws JsonProcessingException {	
	 
		PageBean<OperateRecord> operateRecordPageBean = operateRecordSearchService.findUserOpLogByPage(Integer.parseInt(pageNum)); 
	
		ObjectMapper mapper = new ObjectMapper();  
		String operateRecordPageBeanJson = mapper.writeValueAsString(operateRecordPageBean); 
		System.out.println(operateRecordPageBeanJson);
		return operateRecordPageBeanJson;	
	}
	
	@RequestMapping(value=ControllerMapping.ADMIN_OPERATE_USER_LOG_SELECT_BY_OPER,produces="text/html;charset=UTF-8;")
	public String findAllUserOpLogByOper(String userLogOper,String pageNum) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); 
		PageBean<OperateRecord> operateRecordPageBean = operateRecordSearchService.findOpUserLogByOper(userLogOper, Integer.parseInt(pageNum));
		String operateRecordPageBeanJson = mapper.writeValueAsString(operateRecordPageBean); 
		return operateRecordPageBeanJson;
		
	}

}
