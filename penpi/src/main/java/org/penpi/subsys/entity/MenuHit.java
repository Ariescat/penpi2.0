package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "SYS_MENU_HIT")
@Document(indexName = Global.INDEX_NAME, type = "menuHit")
public class MenuHit implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String ALIAS_MENU_HIT_ID = "menuHitId";
	public static final String ALIAS_MENU_NODE_ID = "menuNodeId";
	public static final String ALIAS_USER_ID = "userId";
	public static final String ALIAS_LAST_HIT_TIME = "lastHitTime";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALIAS_MENU_HIT_ID")
	private java.lang.Integer menuHitId;

	@Column(name = "ALIAS_MENU_NODE_ID")
	private java.lang.Integer menuNodeId;

	@Column(name = "ALIAS_USER_ID")
	private java.lang.Integer userId;

	@Column(name = "ALIAS_LAST_HIT_TIME")
	private java.lang.Integer lastHitTime;

	public MenuHit() {
	}

	public java.lang.Integer getMenuHitId() {
		return menuHitId;
	}

	public void setMenuHitId(java.lang.Integer menuHitId) {
		this.menuHitId = menuHitId;
	}

	public java.lang.Integer getMenuNodeId() {
		return menuNodeId;
	}

	public void setMenuNodeId(java.lang.Integer menuNodeId) {
		this.menuNodeId = menuNodeId;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getLastHitTime() {
		return lastHitTime;
	}

	public void setLastHitTime(java.lang.Integer lastHitTime) {
		this.lastHitTime = lastHitTime;
	}

}
