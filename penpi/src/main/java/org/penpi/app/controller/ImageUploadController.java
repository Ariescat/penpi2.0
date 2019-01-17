package org.penpi.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.penpi.core.commons.file.FileInfDto;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.service.FileInfService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ControllerMapping.APP_IMAGE)
public class ImageUploadController {

	@RequestMapping(value = ControllerMapping.IMAGE_UPLOAD, method = RequestMethod.POST)
	public FileInfDto upload(@RequestParam("file") MultipartFile file) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		String fileName = file.getOriginalFilename(); // 此处还可以升级成文件头确定图片格式
		return SpringContextHelper.getBean(FileInfService.class).saveTempFile(fileName, file.getInputStream());
	}

}
