package org.penpi.core.commons.web;

import org.penpi.core.commons.adapter.ISessionContext;

public class WebContextHolder {
	
	private static String contextPath;

	private static String warPath;

	/**
	 * 结尾不带/或\的地址 
	 */
	public static String getWarPath() {
		return warPath;
	}

	/**
	 * @param warPath war目录本地地址
	 */
	public static void setWarPath(String warPath) {
		WebContextHolder.warPath = jettyToTomcatPathConvert(warPath);
	}
	
	private static ThreadLocal<ISessionContext> sessionContextStore = new ThreadLocal<ISessionContext>();

	public static ISessionContext getSessionContextStore() {
		return sessionContextStore.get();
	}

	public static void setSessionContextStore(ISessionContext sessionContext) {
		sessionContextStore.set(sessionContext);
	}

	public static String getContextPath(){
		return contextPath;
	}

	/**
	 * @param contextPath contextPath 类似/admin,/myapp之类，如果以/结尾（服务配置错），则去掉尾部的/
	 */
	public static void setContextPath(String contextPath) {
		WebContextHolder.contextPath = jettyToTomcatPathConvert(contextPath);
	}
	
	private static String jettyToTomcatPathConvert(String path){ //把结尾的/或\去掉
		return (path.endsWith("/") || path.endsWith("\\")) ? path.substring(0, path.length() - 1) : path;
	}
}