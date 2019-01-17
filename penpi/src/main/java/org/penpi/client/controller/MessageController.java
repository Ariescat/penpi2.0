package org.penpi.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.NoticeMsg;
import org.penpi.subsys.service.NoticeMsgSearchService;
import org.penpi.subsys.service.NoticeMsgService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("adminMsgController")
@RequestMapping(ControllerMapping.ADMIN_MSG)   /* /admin/msg */
public class MessageController {

	@RequestMapping(ControllerMapping.ADMIN_MSG_LIST)
	public ModelAndView messagegList(HttpSession session) {

		Iterable<NoticeMsg> noticeMsgInterable =  SpringContextHelper.getBean(NoticeMsgSearchService.class).getRepository().findAll();
		List<NoticeMsg> noticeMsgList = new ArrayList();
		for (NoticeMsg item : noticeMsgInterable) {
			noticeMsgList.add(item);
		}
		session.setAttribute("noticeMsgList", noticeMsgList);
		return new ModelAndView("forward:/WEB-INF/pages/messageList.jsp");		
	}
	
	@RequestMapping(ControllerMapping.ADMIN_MSG_PUBLISH)
	public ModelAndView publishMsg(String msg,HttpSession session) {
		 SpringContextHelper.getBean(NoticeMsgService.class).msgPublish(msg);
		Iterable<NoticeMsg> noticeMsgInterable =  SpringContextHelper.getBean(NoticeMsgSearchService.class).getRepository().findAll();
		List<NoticeMsg> noticeMsgList = new ArrayList();
		for (NoticeMsg item : noticeMsgInterable) {
			noticeMsgList.add(item);
		}
		session.setAttribute("noticeMsgList", noticeMsgList);
		System.out.println(msg);
		return new ModelAndView("forward:/WEB-INF/pages/messageList.jsp");		
	}
	@RequestMapping(ControllerMapping.ADMIN_FIND_BY_MSG_TYPE)
	public ModelAndView findByMsgType(String messageType,HttpSession session) {
		 
		Iterable<NoticeMsg> noticeMsgInterable;
		noticeMsgInterable = SpringContextHelper.getBean(NoticeMsgSearchService.class).findByMsgType(messageType);
		List<NoticeMsg> noticeMsgList = new ArrayList();
		for (NoticeMsg item : noticeMsgInterable) {
			noticeMsgList.add(item);
		}
		session.setAttribute("noticeMsgList", noticeMsgList);
		session.setAttribute("msgType", messageType);
		return new ModelAndView("forward:/WEB-INF/pages/messageList.jsp");		
	}
}
