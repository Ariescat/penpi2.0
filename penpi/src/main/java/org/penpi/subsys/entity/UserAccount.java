package org.penpi.subsys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "PRD_USER_ACCOUNT")
@Document(indexName = Global.INDEX_NAME, type = "userAccount")
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MEMBER_ACCOUNT_ID = "memberAccountId";
	public static final String USER_ID = "userId";
	public static final String ACCOUNT_TYPE_CODE = "accountTypeCode";
	public static final String ACCOUNT_VALUE = "accountValue";
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ACCOUNT_ID")
	private Integer userAccountId;

	@Column(name = "USER_ID")
	private Integer user_id;

	@Column(name = "ACCOUNT_TYPE_CODE")
	private String accountTypeCode;

	@Column(name = "ACCOUNT_VALUE")
	private Double accountValue;

	public UserAccount() {
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public Double getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(Double accountValue) {
		this.accountValue = accountValue;
	}
}
