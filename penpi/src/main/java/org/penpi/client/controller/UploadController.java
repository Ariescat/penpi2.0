package org.penpi.client.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;
import org.penpi.subsys.ControllerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController("adminUploadController")
@RequestMapping(ControllerMapping.ADMIN_UPLOAD)   /* /admin/upload */
public class UploadController {
	
	@RequestMapping(value=ControllerMapping.ADMIN_FILE_UPLOAD,produces="text/html;charset=UTF-8;")
	public String fileUpload(MultipartFile uploadFile,HttpSession session) throws IllegalStateException, IOException {
		String fileName = uploadFile.getOriginalFilename();
		 
		String realPath = session.getServletContext().getRealPath("/download/apk");
		File file = new File(realPath,fileName);
		uploadFile.transferTo(file);
		Date d = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);  
 
		 return "{\"fileName\":\"" + fileName + "\",\"date\":\"" + dateNowStr+ "\"}";
		 
	}
	
	 

}
