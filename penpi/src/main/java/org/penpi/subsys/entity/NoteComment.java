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
@Table(name = "PRD_NOTE_COMMENT")
@Document(indexName = Global.INDEX_NAME, type = "noteComment")
public class NoteComment implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String NOTE_COMMENT_ID = "noteCommentId";
	public static final String NOTE_ID = "noteId";
	public static final String COMMENT_USER_ID = "commentUserId";
	public static final String FLOOR_NUM = "floorNum";
	public static final String CONT = "cont";
	public static final String CREATE_DATE = "createDate";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_COMMENT_ID")
	private java.lang.Integer noteCommentId;

	@Column(name = "NOTE_ID")
	private java.lang.Integer noteId;

	@Column(name = "COMMENT_USER_ID")
	private java.lang.Integer commentUserId;

	@Column(name = "FLOOR_NUM")
	private java.lang.Integer floorNum;

	@Column(name = "CONT")
	private java.lang.String cont;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_DATE")
	private java.util.Date createDate;

	public NoteComment() {
	}

	public java.lang.Integer getNoteCommentId() {
		return noteCommentId;
	}

	public void setNoteCommentId(java.lang.Integer noteCommentId) {
		this.noteCommentId = noteCommentId;
	}

	public java.lang.Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(java.lang.Integer noteId) {
		this.noteId = noteId;
	}

	public java.lang.Integer getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(java.lang.Integer commentUserId) {
		this.commentUserId = commentUserId;
	}

	public java.lang.Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(java.lang.Integer floorNum) {
		this.floorNum = floorNum;
	}

	public java.lang.String getCont() {
		return cont;
	}

	public void setCont(java.lang.String cont) {
		this.cont = cont;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

}
