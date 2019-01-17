package org.penpi.client.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.penpi.client.dto.FileInfo;
import org.penpi.subsys.ControllerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("adminUIController")
@RequestMapping(ControllerMapping.ADMIN_UI)  /*"/admin/ui"*/
public class UIController {

	@RequestMapping("main_top")
	public ModelAndView main_top() {
		return new ModelAndView("forward:/WEB-INF/pages/frame/top.jsp");
	}
	
	@RequestMapping("main_left")
	public ModelAndView main_left() {
		return new ModelAndView("forward:/WEB-INF/pages/frame/left.jsp");
	}
	
	@RequestMapping("main_right")
	public ModelAndView main_right() {
		return new ModelAndView("forward:/WEB-INF/pages/frame/right.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_MAIN)
	public ModelAndView tomain() {
		return new ModelAndView("forward:/WEB-INF/pages/main.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_OPERATE_USER_LOG)
	public ModelAndView toOperateUserLog() {
		return new ModelAndView("forward:/WEB-INF/pages/operateUserLog.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_TO_PRINT_USER)
	public ModelAndView toPrintUser() {
		return new ModelAndView("forward:/WEB-INF/pages/userPrint.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_TO_PRINT_ORDERS)
	public ModelAndView toPrintOrders() {
		return new ModelAndView("forward:/WEB-INF/pages/orderPrint.jsp");
	}
	 
	@RequestMapping(ControllerMapping.ADMIN_TO_USER_STATISTIC)
	public ModelAndView toUserStatistic() {
		return new ModelAndView("forward:/WEB-INF/pages/userStatistic.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_TO_ORDER_STATISTIC)
	public ModelAndView toOrderStatistic() {
		return new ModelAndView("forward:/WEB-INF/pages/orderStatistic.jsp");
	}
	
	@RequestMapping(ControllerMapping.ADMIN_TO_MAIN_TOPIC)
	public ModelAndView toMainTopic() {
		return new ModelAndView("forward:/WEB-INF/pages/alltopic.jsp");
	}
	@RequestMapping(ControllerMapping.ADMIN_TO_TOPIC_STATISTIC)
	public ModelAndView toTopicSTATISTIC() {
		return new ModelAndView("forward:/WEB-INF/pages/topicStatistic.jsp");
	}
	@RequestMapping(ControllerMapping.ADMIN_TO_UPLOAD_FILE)
	public ModelAndView toUploadFile(HttpSession session) {
		String realPath = session.getServletContext().getRealPath("/download/apk");
		File filedir = new File(realPath);
		File flist[] = filedir.listFiles();  
		Map<String,FileInfo> mapFileInfo = new HashMap<String, FileInfo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (File f : flist) {       
		/*   System.out.println("file==>" + f.getName());  
		   System.out.println("file==>" + f.getAbsolutePath()); */ 
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(f.getName());
			fileInfo.setFilePath(f.getAbsolutePath());
	 
			Calendar cal = Calendar.getInstance();   
			long time = f.lastModified();   
			cal.setTimeInMillis(time); 
			Date d = new Date(time); 
		   mapFileInfo.put(sdf.format(d),fileInfo);
			
		}   
		session.setAttribute("mapFileInfo", mapFileInfo);
		
	 
		return new ModelAndView("forward:/WEB-INF/pages/fileUpload.jsp");
	}
}
