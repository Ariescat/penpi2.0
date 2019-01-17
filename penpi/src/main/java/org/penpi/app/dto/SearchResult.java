package org.penpi.app.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
	
	private String type;
	private List<UserEx> userExlist;
	private List<NoteEx> noteExlist;
	private List<TopicEx> topicExlist;

	public SearchResult() {
		super();
		userExlist = new ArrayList<>();
		noteExlist = new ArrayList<>();
		topicExlist = new ArrayList<>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<UserEx> getUserExlist() {
		return userExlist;
	}

	public void setUserExlist(List<UserEx> userExlist) {
		this.userExlist = userExlist;
	}

	public List<NoteEx> getNoteExlist() {
		return noteExlist;
	}

	public void setNoteExlist(List<NoteEx> noteExlist) {
		this.noteExlist = noteExlist;
	}

	public List<TopicEx> getTopicExlist() {
		return topicExlist;
	}

	public void setTopicExlist(List<TopicEx> topicExlist) {
		this.topicExlist = topicExlist;
	}
}
