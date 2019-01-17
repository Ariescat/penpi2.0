package org.penpi.core.commons.exception;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.WebContextHolder;
import org.penpi.subsys.ResGlobal;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringMVC异常处理框架.
 */
public class BusinessExceptionResolver implements HandlerExceptionResolver {

	public static final String ERRORPAGE = "errorPage";

	private Logger log = Logger.getLogger(this.getClass());

	private Map<String, String> uriExceptionStatusMap;

	public Map<String, String> getUriExceptionStatusMap() {
		return uriExceptionStatusMap;
	}

	public void setUriExceptionStatusMap(Map<String, String> uriExceptionStatusMap) {
		this.uriExceptionStatusMap = uriExceptionStatusMap;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ErrorObjectResp error = null;
		if (ex instanceof BusinessException) {
			BusinessException busException = (BusinessException) ex;
			String errorText = SpringContextHelper.getBean(AbstractMessageSource.class)
					.getMessage(busException.getCode(), busException.getArgs(), request.getLocale());
			error = new ErrorObjectResp(busException.getCode(), errorText, busException.getViewData());
			if (busException.isUseSimpleLog()) {
				log.error(errorText);
			} else {
				log.error(errorText, ex);
			}
		} else {
			error = new ErrorObjectResp(ResGlobal.ERRORS_EXCEPTION,
					ex.getMessage() == null ? ex.toString() : ex.getMessage(), null);
			log.error(ex.getMessage(), ex);
		}
		response.setStatus(checkStatus(request));
		return new ModelAndView().addObject(error);
	}

	private int checkStatus(HttpServletRequest request) {
		String requestUri = request.getRequestURI().substring(WebContextHolder.getContextPath().length());
		for (Entry<String, String> entry : uriExceptionStatusMap.entrySet()) {
			if (requestUri.startsWith(entry.getKey())) {
				return new Integer(entry.getValue());
			}
		}
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

}
