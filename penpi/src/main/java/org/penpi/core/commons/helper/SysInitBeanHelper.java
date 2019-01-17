package org.penpi.core.commons.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.penpi.core.commons.adapter.IHasSearchBuilder;
import org.penpi.core.commons.utils.DbUnitUtils;
import org.penpi.subsys.Global;
import org.penpi.subsys.service.FileInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 系统初始化
 * 
 * @author drugbean
 *
 */
@Component
public class SysInitBeanHelper implements ApplicationListener<ContextRefreshedEvent> {

	private Logger log = Logger.getLogger(this.getClass());
	
	private String productName;
	private String initMode;
	private List<String> defaultDataPath;
	
	private DataSource dataSource;
	private SmallPictImageQueueHelper smallPictImageQueueHelper;
	
	public static boolean inited = false;

	@Value("${app.productName: productName}")
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Value("${app.initMode: none}")
	public void setInitMode(String initMode){
		this.initMode = initMode;
	}
	
    @Value("${app.init.defaultDataPath}")
    public void setDefaultDataPath(String paths) {
        this.defaultDataPath = new ArrayList<String>();
        if(StringUtils.isNotBlank(paths)){
            for (String path : paths.split(",")){
                if(StringUtils.isNotBlank(path)){
                    this.defaultDataPath.add(path);
                }
            }
        }
    }
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Autowired
	public void setSmallPictImageQueueHelper(SmallPictImageQueueHelper smallPictImageQueueHelper) {
		this.smallPictImageQueueHelper = smallPictImageQueueHelper;
	}
	
    public static boolean isInited() {
		return inited;
	}
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) { // 避免出现二次调用的问题
			Date contextInitEndTime = new Date();
			log.info(productName + " 开始启动...");
			loadInitData();
			initFileInf();
			buildIndex();
			inited = true;
			long milliseconds = new Date().getTime() - contextInitEndTime.getTime();
	    	log.info(productName + " 启动完毕 耗时:"+ milliseconds / 1000 + "." + milliseconds % 1000 + " s");
		}
	}
	
	private void loadInitData() {
		if ("create".equals(initMode)) {
			log.info("整站数据重新创建模式，自动装入初始化数据...");
            try {
            	log.info("载入项目初始数据...");
                DbUnitUtils.appendDbUnitData(dataSource, defaultDataPath.toArray(new String[defaultDataPath.size()]));
            	log.info("自动装入初始化数据结束...");
            } catch (Exception e) { //NOSONAR
                log.error("load init data failed.", e);
            }
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void buildIndex() {
		log.info("开始重建索引[异步]...");
		log.info("es document search url: http://localhost:9200/" + Global.INDEX_NAME + "/_search?q=*&pretty ");
		Map<String, IHasSearchBuilder> searchBuilderMap = SpringContextHelper.getBeansOfType(IHasSearchBuilder.class);
		List<IHasSearchBuilder> searchBuilderList = new ArrayList<IHasSearchBuilder>(searchBuilderMap.values());
		searchBuilderList.sort(new Comparator<IHasSearchBuilder>() {
			public int compare(IHasSearchBuilder lhs, IHasSearchBuilder rhs) {
				return lhs.getPriority().compareTo(rhs.getPriority()); // 当只设置少数几个Async线程干活时，会按照顺序来建立
			}
		});
//		searchBuilderList.sort((lhs,rhs) -> lhs.getPriority().compareTo(rhs.getPriority())); //jetty8跟lambda表达式冲突
		for (IHasSearchBuilder searchBuilder : searchBuilderList) {
			searchBuilder.buildAllIndex(); // 异步建立索引
		}
	}
	
	private void initFileInf() {
    	log.info("处理预置图片资源...");
    	smallPictImageQueueHelper.init(); //初始化小图处理线程
    	SpringContextHelper.getBean(FileInfService.class).processInitFile();
	}

}
