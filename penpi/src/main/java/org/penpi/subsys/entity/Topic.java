package org.penpi.subsys.entity;

import java.io.Serializable;

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
@Table(name = "PRD_TOPIC")
@Document(indexName = Global.INDEX_NAME, type = "topic")
public class Topic implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String TOPIC_ID = "topicId";
	public static final String TOPIC_CATEGORY_ID = "topicCategoryId";
	public static final String TOPIC_HEAD_PICT_ID = "topicHeadPictId";
	public static final String TOPIC_NM = "topicNm";
	public static final String FOLLOWER_NM = "followerNm";
	public static final String TOPIC_DESCR = "topicDescr";
	public static final String CREATE_DATE = "createDate";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_ID")
	private java.lang.Integer topicId;

	@Column(name = "TOPIC_CATEGORY_ID")
	private java.lang.Integer topicCategoryId;
	
	@Column(name = "TOPIC_HEAD_PICT_ID")
	private java.lang.Integer topicHeadPictId;

	@Column(name = "TOPIC_NM")
	private java.lang.String topicNm;

	@Column(name = "FOLLOWER_NM")
	private java.lang.String followerNm;
	
	@Column(name = "TOPIC_DESCR")
	private java.lang.String topicDescr;
	
	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_DATE")
	private java.util.Date createDate;

	public Topic() {
	}

	public java.lang.Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(java.lang.Integer topicId) {
		this.topicId = topicId;
	}

	public java.lang.Integer getTopicCategoryId() {
		return topicCategoryId;
	}

	public void setTopicCategoryId(java.lang.Integer topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}

	public java.lang.String getTopicNm() {
		return topicNm;
	}

	public void setTopicNm(java.lang.String topicNm) {
		this.topicNm = topicNm;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.lang.String getFollowerNm() {
		return followerNm;
	}

	public void setFollowerNm(java.lang.String followerNm) {
		this.followerNm = followerNm;
	}

	public java.lang.String getTopicDescr() {
		return topicDescr;
	}

	public void setTopicDescr(java.lang.String topicDescr) {
		this.topicDescr = topicDescr;
	}

	public java.lang.Integer getTopicHeadPictId() {
		return topicHeadPictId;
	}

	public void setTopicHeadPictId(java.lang.Integer topicHeadPictId) {
		this.topicHeadPictId = topicHeadPictId;
	}

}
