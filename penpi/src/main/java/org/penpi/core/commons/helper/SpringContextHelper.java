package org.penpi.core.commons.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHelper implements ApplicationContextAware  {

	private Logger log = Logger.getLogger(this.getClass());
    private static ApplicationContext applicationContext;
    
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext != null) {
            log.error("ApplicationContextHolder already holded 'applicationContext'.");
        }
        this.applicationContext = applicationContext;
        log.info("holded applicationContext, displayName:" + applicationContext.getDisplayName());
	}
	
	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException( "'applicationContext' property is null,ApplicationContextHolder not yet init.");
		}
		return applicationContext;
	}    
	
	private static Map<Class<?>,Object> beans = new HashMap<Class<?>, Object>();
	public static <T> T getBean(Class<T> requiredType) {
		if (beans.get(requiredType) != null) {
			return (T) beans.get(requiredType);
		} else {
			Object instance = getApplicationContext().getBean(requiredType);
			beans.put(requiredType, instance);
			return (T) instance;
		}
	}


    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    public static boolean containsBean(String beanName) {
    	return getApplicationContext().containsBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requiredType){
    	return getApplicationContext().getBeansOfType(requiredType);
    }
    
    public static void cleanHolder() {
        applicationContext = null;
    }

}
