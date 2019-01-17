package org.penpi.client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
@RestController("adminLoginController")
@RequestMapping(ControllerMapping.AMIN_LOGIN)
public class LoginController {

	@Autowired
	UserSearchService userSearchService;

	@RequestMapping(ControllerMapping.ADMIN_HANDLE_LOGIN)
	public ModelAndView handleLogin(User user,HttpSession session) {
		
		//System.out.println( user.getLoginId());
		session.setAttribute("LoginId", user.getLoginId());
		List<User> userlist = userSearchService.findByLoginIdAndUserPsw(user.getLoginId(), user.getUserPsw());
		/*System.out.println(userlist.size());
		System.out.println(userlist.get(0).getLoginId());*/
		//System.out.println(userlist.get(0).getIfAdmin());
		if(userlist.size() == 0) {
			session.setAttribute(Global.LOGIN_MESSAGE, "用户名或密码不正确");
			return new ModelAndView("redirect:/login.jsp");
		}
		//System.out.println(userlist.get(0).getIfAdmin());
		if(userlist.get(0).getIfAdmin().equals("N")) {
			session.setAttribute(Global.LOGIN_MESSAGE, "你不是管理员，不能登录");
			return new ModelAndView("redirect:/login.jsp");
		}
				
		 session.setAttribute(Global.SESSION_LOGIN_USER, user);	 
		 return new ModelAndView("redirect:"+ControllerMapping.ADMIN_UI+"/"+ControllerMapping.ADMIN_MAIN);
		 
	}
	
	
	
	 
}
