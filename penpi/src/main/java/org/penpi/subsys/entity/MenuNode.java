package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "SYS_MENU_NODE")
@Document(indexName = Global.INDEX_NAME, type = "menuNode")
public class MenuNode implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String MENU_NODE_ID = "menuNodeId";
	public static final String UPPER_MENU_NODE_ID = "upperMenuNodeId";
	public static final String MENU_NODE_TYPE_CODE = "menuNodeTypeCode";
	public static final String ICON = "icon";
	public static final String MENU_NODE_NM = "menuNodeNm";
	public static final String MENU_NODE_URL = "menuNodeUrl";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_NODE_ID")
	private java.lang.Integer menuNodeId;

	@Column(name = "UPPER_MENU_NODE_ID")
	private java.lang.Integer upperMenuNodeId;

	@Column(name = "MENU_NODE_TYPE_CODE")
	private java.lang.String menuNodeTypeCode;

	@Column(name = "ICON")
	private java.lang.String icon;

	@Column(name = "MENU_NODE_NM")
	private java.lang.String menuNodeNm;

	@Column(name = "MENU_NODE_URL")
	private java.lang.Integer menuNodeUrl;

	public MenuNode() {
	}

	public java.lang.Integer getMenuNodeId() {
		return menuNodeId;
	}

	public void setMenuNodeId(java.lang.Integer menuNodeId) {
		this.menuNodeId = menuNodeId;
	}

	public java.lang.Integer getUpperMenuNodeId() {
		return upperMenuNodeId;
	}

	public void setUpperMenuNodeId(java.lang.Integer upperMenuNodeId) {
		this.upperMenuNodeId = upperMenuNodeId;
	}

	public java.lang.String getMenuNodeTypeCode() {
		return menuNodeTypeCode;
	}

	public void setMenuNodeTypeCode(java.lang.String menuNodeTypeCode) {
		this.menuNodeTypeCode = menuNodeTypeCode;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.String getMenuNodeNm() {
		return menuNodeNm;
	}

	public void setMenuNodeNm(java.lang.String menuNodeNm) {
		this.menuNodeNm = menuNodeNm;
	}

	public java.lang.Integer getMenuNodeUrl() {
		return menuNodeUrl;
	}

	public void setMenuNodeUrl(java.lang.Integer menuNodeUrl) {
		this.menuNodeUrl = menuNodeUrl;
	}

}
