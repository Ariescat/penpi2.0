package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "SYS_USER_ROLE_REL")
@Document(indexName = Global.INDEX_NAME, type = "userRoleRel")
public class UserRoleRel implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String USER_ROLE_REL_ID = "userRoleRelId";
	public static final String USER_ID = "userId";
	public static final String ROLE_ID = "roleId";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ROLE_REL_ID")
	private java.lang.Integer userRoleRelId;

	@Column(name = "USER_ID")
	private java.lang.Integer userId;

	@Column(name = "ROLE_ID")
	private java.lang.Integer roleId;

	public UserRoleRel() {
	}

	public java.lang.Integer getUserRoleRelId() {
		return userRoleRelId;
	}

	public void setUserRoleRelId(java.lang.Integer userRoleRelId) {
		this.userRoleRelId = userRoleRelId;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

}
