package org.penpi.app.dto;

import org.penpi.subsys.entity.Topic;

public class TopicEx extends Topic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String topicHeadPictStr;

	public String getTopicHeadPictStr() {
		return topicHeadPictStr;
	}

	public void setTopicHeadPictStr(String topicHeadPictStr) {
		this.topicHeadPictStr = topicHeadPictStr;
	}
}
