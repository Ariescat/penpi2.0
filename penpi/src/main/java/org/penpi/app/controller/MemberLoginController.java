package org.penpi.app.controller;

import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.core.commons.web.ClientUser;
import org.penpi.subsys.ControllerMapping;
import org.penpi.subsys.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(ControllerMapping.APP_LOGIN)
public class MemberLoginController {

	@RequestMapping(value = ControllerMapping.MEMBER_LOGIN)
	public ClientUser login(String loginId, String validateCode) {
		return SpringContextHelper.getBean(UserService.class).memberLogin(loginId, validateCode);
	}

	@RequestMapping(value = ControllerMapping.GET_VALIDATE_CODE)
	public String getValidateCode(String loginId) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(SpringContextHelper.getBean(UserService.class).getValidateCode(loginId));
	}
	
	@RequestMapping(value = ControllerMapping.RE_MEMBER_LOGIN)
	public ClientUser relogin(String loginId) {
		// TODO 有安全漏洞
		return SpringContextHelper.getBean(UserService.class).relogin(loginId);
	}
}
