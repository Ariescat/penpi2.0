package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "SYS_ROLE_MENU_REL")
@Document(indexName = Global.INDEX_NAME, type = "roleMenuRel")
public class RoleMenuRel implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String ROLE_MENU_REL_ID = "roleMenuRelId";
	public static final String ROLE_ID = "roleId";
	public static final String MENU_NODE_ID = "menuNodeId";
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_MENU_REL_ID")
	private java.lang.Integer roleMenuRelId;

	@Column(name = "ROLE_ID")
	private java.lang.Integer roleId;

	@Column(name = "MENU_NODE_ID")
	private java.lang.Integer menuNodeId;

	public RoleMenuRel() {
	}

	public java.lang.Integer getRoleMenuRelId() {
		return roleMenuRelId;
	}

	public void setRoleMenuRelId(java.lang.Integer roleMenuRelId) {
		this.roleMenuRelId = roleMenuRelId;
	}

	public java.lang.Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

	public java.lang.Integer getMenuNodeId() {
		return menuNodeId;
	}

	public void setMenuNodeId(java.lang.Integer menuNodeId) {
		this.menuNodeId = menuNodeId;
	}

}
