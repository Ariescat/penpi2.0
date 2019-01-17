package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "PRD_TOPIC_IDENTITY")
@Document(indexName = Global.INDEX_NAME, type = "topicIdentity")
public class TopicIdentity implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String TOPIC_IDENTITY_ID = "topicIdentityId";
	public static final String TOPIC_ID = "topicId";
	public static final String USER_ID = "userId";
	public static final String TOPIC_IDENTITY_CODE = "topicIdentityCode";//话题身份代码
	public static final String IDENTITY_LEVEL = "identityLevel";//身份等级
	public static final String TOPIC_PERMIS_CODE = "topicPermisCode";//话题权限代码
	public static final String IF_FREEZE = "ifFreeze";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_IDENTITY_ID")
	private java.lang.Integer topicIdentityId;

	@Column(name = "USER_ID")
	private java.lang.Integer userId;

	@Column(name = "TOPIC_ID")
	private java.lang.Integer topicId;

	@Column(name = "TOPIC_IDENTITY_CODE")
	private java.lang.String topicIdentityCode;

	@Column(name = "IDENTITY_LEVEL")
	private java.lang.Integer identityLevel;

	@Column(name = "TOPIC_PERMIS_CODE")
	private java.lang.String topicPermisCode;
	
	@Column(name = "IF_FREEZE")
	private java.lang.String ifFreeze;

	public TopicIdentity() {
	}

	public java.lang.Integer getTopicIdentityId() {
		return topicIdentityId;
	}

	public void setTopicIdentityId(java.lang.Integer topicIdentityId) {
		this.topicIdentityId = topicIdentityId;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(java.lang.Integer topicId) {
		this.topicId = topicId;
	}

	public java.lang.String getTopicIdentityCode() {
		return topicIdentityCode;
	}

	public void setTopicIdentityCode(java.lang.String topicIdentityCode) {
		this.topicIdentityCode = topicIdentityCode;
	}

	public java.lang.Integer getIdentityLevel() {
		return identityLevel;
	}

	public void setIdentityLevel(java.lang.Integer identityLevel) {
		this.identityLevel = identityLevel;
	}

	public java.lang.String getTopicPermisCode() {
		return topicPermisCode;
	}

	public void setTopicPermisCode(java.lang.String topicPermisCode) {
		this.topicPermisCode = topicPermisCode;
	}

	public java.lang.String getIfFreeze() {
		return ifFreeze;
	}

	public void setIfFreeze(java.lang.String ifFreeze) {
		this.ifFreeze = ifFreeze;
	}

}
