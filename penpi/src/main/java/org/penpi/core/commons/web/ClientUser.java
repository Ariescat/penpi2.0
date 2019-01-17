package org.penpi.core.commons.web;

import org.penpi.core.commons.helper.SpringContextHelper;
import org.penpi.subsys.Global;
import org.penpi.subsys.entity.User;
import org.penpi.subsys.service.FileInfService;
import org.springframework.beans.BeanUtils;

/**
 * 前台用户.
 *
 */
public class ClientUser {

	private Integer userId;
	private String loginId;
	
	private Integer userHeadPictId;
	private String userHeadPictStr;
	private String userSexCode;
	private String userNm;
	private String userRemark;
	
	public ClientUser(){}
	
	public static ClientUser create(User user){
		ClientUser clientUser = new ClientUser();
		BeanUtils.copyProperties(user, clientUser);
		clientUser.userHeadPictStr = SpringContextHelper.getBean(FileInfService.class).getSmallPictUrl(
				clientUser.userHeadPictId, Global.DEFAULT_SMALL_PICT_SIZE, Global.DEFAULT_SMALL_PICT_SIZE);
		return clientUser;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Integer getUserHeadPictId() {
		return userHeadPictId;
	}

	public void setUserHeadPictId(Integer userHeadPictId) {
		this.userHeadPictId = userHeadPictId;
	}

	public String getUserHeadPictStr() {
		return userHeadPictStr;
	}

	public void setUserHeadPictStr(String userHeadPictStr) {
		this.userHeadPictStr = userHeadPictStr;
	}

	public String getUserSexCode() {
		return userSexCode;
	}

	public void setUserSexCode(String userSexCode) {
		this.userSexCode = userSexCode;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

}
