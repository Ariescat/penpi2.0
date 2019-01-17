package org.penpi.core.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.penpi.core.commons.web.ClientUser;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.Global;

/**
 * 用于通知前端重登录
 */
public class MemberReLoginFilter implements Filter  {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//如果是" app/login/* "的方法都放行不过滤
		if (checkisLoginUrl(httpRequest)) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		ClientUser clientUser = (ClientUser) httpRequest.getSession().getAttribute(Global.SESSION_LOGIN_MEMBER);
		if (clientUser == null) {
			httpResponse.setStatus(403);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}
	
	private boolean checkisLoginUrl(HttpServletRequest httpRequest) {
		String requestUri = httpRequest.getRequestURI().substring(WebContextHolder.getContextPath().length());
		return requestUri.startsWith("/" + ControllerMapping.APP_LOGIN) ? true : false;
	}

}
