package org.penpi.app.dto;

import org.penpi.subsys.entity.User;

public class UserEx extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userHeadPictStr;

	public String getUserHeadPictStr() {
		return userHeadPictStr;
	}

	public void setUserHeadPictStr(String userHeadPictStr) {
		this.userHeadPictStr = userHeadPictStr;
	}

}
