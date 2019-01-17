package org.penpi.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itest")
public class TestController {
	   
	@Autowired
	UserService userService;
	
	@RequestMapping("/save")
	public String save() {
		
		System.out.println("save");
		
		User user = new User();
		user.setUserNm("李志鹏456");
		user.setUserPsw("admin");
		SpringContextHelper.getBean(UserService.class).save(user);
		
//		SpringContextHelper.getBean(UserSearchService.class).save(user);
		
		return "index";
	}
	
	@RequestMapping("/findall")
	public Map<String, Object> getUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		map.put("data", userService.listAll());
		return map;
	}
	
	@RequestMapping("/currentThread")
	public String testCurrentThread() throws Exception {
		
		System.out.println("currentThread: ");
		
		Thread current = Thread.currentThread();  
        System.out.println(current.getPriority());  
        System.out.println(current.getName());  
        System.out.println(current.activeCount());  
        System.out.println(current.getId());  
        System.out.println(current.getThreadGroup());  
        System.out.println(current.getStackTrace());  
        System.out.println(current.hashCode());  
        System.out.println(current.toString()); 
        System.out.println();
        
        return "currentThread";
	}
	
	@RequestMapping("/clear")
	public void clear() {
		WebContextHolder.getSessionContextStore().removeServerValue(Global.SESSION_LOGIN_MEMBER);
		System.out.println("clear");
	}
}
