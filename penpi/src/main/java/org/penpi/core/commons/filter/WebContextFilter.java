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

import org.apache.log4j.Logger;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.core.commons.web.WebSessionContext;

public class WebContextFilter implements Filter {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public void init(FilterConfig filterConfig) throws ServletException {
		WebContextHolder.setContextPath(filterConfig.getServletContext().getContextPath());
		WebContextHolder.setWarPath(filterConfig.getServletContext().getRealPath("/"));
		log.debug("项目地址为：" + filterConfig.getServletContext().getRealPath("/"));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        WebContextHolder.setSessionContextStore(new WebSessionContext((HttpServletRequest)request, (HttpServletResponse)response));
        chain.doFilter(request, response);
    }
	
    public void destroy() {
        
    }
}