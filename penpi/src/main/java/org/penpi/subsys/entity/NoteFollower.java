package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "PRD_NOTE_FOLLOWER")
@Document(indexName = Global.INDEX_NAME, type = "noteFollower")
public class NoteFollower implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String NOTE_FOLLOWER_ID = "noteFollowerId";
	public static final String NOTE_ID = "noteId";
	public static final String FOLLOW_USER_ID = "followUserId";
	public static final String IF_FREEZE = "ifFreeze";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_FOLLOWER_ID")
	private java.lang.Integer noteFollowerId;

	@Column(name = "NOTE_ID")
	private java.lang.Integer noteId;

	@Column(name = "FOLLOW_USER_ID")
	private java.lang.Integer followUserId;

	@Column(name = "IF_FREEZE")
	private java.lang.Boolean ifFreeze;

	public NoteFollower() {
	}

	public java.lang.Integer getNoteFollowerId() {
		return noteFollowerId;
	}

	public java.lang.Integer getNoteId() {
		return noteId;
	}

	public java.lang.Integer getFollowUserId() {
		return followUserId;
	}

	public java.lang.Boolean getIfFreeze() {
		return ifFreeze;
	}

	public void setNoteFollowerId(java.lang.Integer noteFollowerId) {
		this.noteFollowerId = noteFollowerId;
	}

	public void setNoteId(java.lang.Integer noteId) {
		this.noteId = noteId;
	}

	public void setFollowUserId(java.lang.Integer followUserId) {
		this.followUserId = followUserId;
	}

	public void setIfFreeze(java.lang.Boolean ifFreeze) {
		this.ifFreeze = ifFreeze;
	}

}
