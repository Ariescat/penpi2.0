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
@Table(name = "PRD_TOPIC_CATEGORY")
@Document(indexName = Global.INDEX_NAME, type = "topicCategory")
public class TopicCategory implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String TOPIC_CATEGORY_ID = "topicCategoryId";
	public static final String TOPIC_CATEGORY_NM = "topicCategoryNm";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_CATEGORY_ID")
	private java.lang.Integer topicCategoryId;

	@Column(name = "TOPIC_CATEGORY_NM")
	private java.lang.String topicCategoryNm;

	public TopicCategory() {
	}

	public java.lang.Integer getTopicCategoryId() {
		return topicCategoryId;
	}

	public void setTopicCategoryId(java.lang.Integer topicCategoryId) {
		this.topicCategoryId = topicCategoryId;
	}

	public java.lang.String getTopicCategoryNm() {
		return topicCategoryNm;
	}

	public void setTopicCategoryNm(java.lang.String topicCategoryNm) {
		this.topicCategoryNm = topicCategoryNm;
	}

}
