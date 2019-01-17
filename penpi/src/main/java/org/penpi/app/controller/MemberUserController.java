package org.penpi.app.controller;

import org.penpi.app.dto.UserEx;
import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.service.UserSearchService;
import org.penpi.subsys.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerMapping.APP_USER)
public class MemberUserController {
	
	@RequestMapping(value = "getUserProfile")
	public UserEx getUserProfile(Integer userId) {
		return SpringContextHelper.getBean(UserSearchService.class).getUserProfile(userId);
	}
	
	@RequestMapping(value = "updateProfile", method = RequestMethod.POST)
	public User updateProfile(@RequestBody User user) {
		return SpringContextHelper.getBean(UserService.class).save(user);
	}
	
	@RequestMapping(value = "updateUserHeadPict", method = RequestMethod.POST)
	public UserEx updateUserHeadPict(@RequestBody User user) {
		return SpringContextHelper.getBean(UserService.class).updateUserHeadPict(user);
	}
}
