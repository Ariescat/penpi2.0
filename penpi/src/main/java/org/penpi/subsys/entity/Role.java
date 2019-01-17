package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "SYS_ROLE")
@Document(indexName = Global.INDEX_NAME, type = "role")
public class Role implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String ROLE_ID = "roleId";
	public static final String ROLE_NM = "roleNm";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private java.lang.Integer roleId;

	@Column(name = "ROLE_NM")
	private java.lang.String roleNm;

	public Role() {
	}

	public java.lang.Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(java.lang.String roleNm) {
		this.roleNm = roleNm;
	}

}
