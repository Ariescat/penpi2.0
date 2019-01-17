package org.penpi.subsys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.core.utils.UtilDateTimeClient;
import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PRD_USER")
@Document(indexName = Global.INDEX_NAME, type = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String USER_ID = "userId";
	public static final String LOGIN_ID = "loginId";
	public static final String USER_HEAD_PICT_ID = "userHeadPictId";
	public static final String USER_NM = "userNm";
	public static final String USER_PSW = "userPsw";
	public static final String USER_SEX_CODE = "userSexCode";
	public static final String USER_MOBILE = "userMobile";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_REMARK = "userRemark";
	public static final String IF_ADMIN = "ifAdmin";
	public static final String CREATE_DATE = "createDate";
	public static final String USER_STAT_CODE = "userStatCode";
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@Column(name = "USER_HEAD_PICT_ID")
	private Integer userHeadPictId;
	
 	@Column(name = "USER_NM")
	private String userNm;

	@Column(name = "USER_PSW")
	private String userPsw;

	@Column(name = "USER_SEX_CODE")
	private String userSexCode;

	@Column(name = "USER_MOBILE")
	private String userMobile;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "USER_REMARK")
	private String userRemark;
	
	@Column(name = "IF_ADMIN")
	private String ifAdmin;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "USER_STAT_CODE")
	private String userStatCode;

	public User() {
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

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserPsw() {
		return userPsw;
	}

	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}

	public String getUserSexCode() {
		return userSexCode;
	}

	public void setUserSexCode(String userSexCode) {
		this.userSexCode = userSexCode;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getIfAdmin() {
		return ifAdmin;
	}

	public void setIfAdmin(String ifAdmin) {
		this.ifAdmin = ifAdmin;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateStr() {
		return this.getCreateDate() == null ? null
				: UtilDateTimeClient.convertDateTimeToMmString(this.getCreateDate());
	}

	public void setCreateDateStr(String value) {
		if (value != null) {
			this.setCreateDate(UtilDateTimeClient.convertStringToDateTime(value));
		}
	}
	
	public String getUserStatCode() {
		return userStatCode;
	}

	public void setUserStatCode(String userStatCode) {
		this.userStatCode = userStatCode;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

}
