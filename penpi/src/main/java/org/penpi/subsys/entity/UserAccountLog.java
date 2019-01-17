package org.penpi.subsys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PRD_USER_ACCOUNT_LOG")
@Document(indexName = Global.INDEX_NAME, type = "userAccountLog")
public class UserAccountLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MEMBER_ACCOUNT_LOG_ID = "memberAccountLogId";
	public static final String USER_ID = "userId";
	public static final String USER_HEAD_PICT_PICT_ID = "userHeadPictPictId";
	public static final String MEMBER_ACCOUNT_ID = "memberAccountId";
	public static final String PREV_VALUE = "prevValue";
	public static final String AFTER_VALUE = "afterValue";
	public static final String CHANGE_VALUE = "changeValue";
	public static final String SYS_TIME = "sysTime";
	public static final String REASON = "reason";
	public static final String OPERATION_MAN_ID = "operationManId";
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ACCOUNT_LOG_ID")
	private Integer userAccountLogId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_HEAD_PICT_ID")
	private Integer userHeadPictId;

	@Column(name = "MEMBER_ACCOUNT_ID")
	private Integer memberAccountId;

	@Column(name = "PREV_VALUE")
	private Double prevValue;

	@Column(name = "AFTER_VALUE")
	private Double afterValue;

	@Column(name = "CHANGE_VALUE")
	private Double changeValue;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "SYS_TIME")
	private Date sysTime;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "OPERATION_MAN_ID")
	private Integer operationManId;

	public UserAccountLog() {
	}

	public Integer getUserAccountLogId() {
		return userAccountLogId;
	}

	public void setUserAccountLogId(Integer userAccountLogId) {
		this.userAccountLogId = userAccountLogId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserHeadPictId() {
		return userHeadPictId;
	}

	public void setUserHeadPictId(Integer userHeadPictId) {
		this.userHeadPictId = userHeadPictId;
	}

	public Integer getMemberAccountId() {
		return memberAccountId;
	}

	public void setMemberAccountId(Integer memberAccountId) {
		this.memberAccountId = memberAccountId;
	}

	public Double getPrevValue() {
		return prevValue;
	}

	public void setPrevValue(Double prevValue) {
		this.prevValue = prevValue;
	}

	public Double getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(Double afterValue) {
		this.afterValue = afterValue;
	}

	public Double getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(Double changeValue) {
		this.changeValue = changeValue;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getOperationManId() {
		return operationManId;
	}

	public void setOperationManId(Integer operationManId) {
		this.operationManId = operationManId;
	}

}
