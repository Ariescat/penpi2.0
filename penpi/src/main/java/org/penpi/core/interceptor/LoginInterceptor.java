package org.penpi.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.penpi.subsys.Global;
import org.penpi.subsys.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		 
        String reqUrl=request.getRequestURI().replace(request.getContextPath(), "");  
        //System.out.println(reqUrl);
        if(reqUrl.contains("login"))
            return true;  
    	//System.out.println("-------登录拦截器---------");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Global.SESSION_LOGIN_USER); 
		if(user == null) {
			session.setAttribute(Global.LOGIN_MESSAGE, "请先登录");
			response.sendRedirect("/login.jsp");
			//request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
			return false;
		}
		return true;
	}

}
