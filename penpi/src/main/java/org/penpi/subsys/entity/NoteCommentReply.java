package org.penpi.subsys.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "PRD_NOTE_COMMENT_REPLY")
@Document(indexName = Global.INDEX_NAME, type = "noteCommentReply")
public class NoteCommentReply implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String NOTE_COMMENT_REPLY_ID = "noteCommentReplyId";
	public static final String NOTE_COMMENT_ID = "noteCommentId";
	public static final String COMMENT_USER_ID = "commentUserId";
	public static final String AT_USER_ID = "atUserId";
	public static final String FLOOR_NUM = "floorNum";
	public static final String CONT = "cont";
	public static final String CREATE_DATE = "createDate";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_COMMENT_REPLY_ID")
	private Integer noteCommentReplyId;

	@Column(name = "NOTE_COMMENT_ID")
	private Integer noteCommentId;

	@Column(name = "COMMENT_USER_ID")
	private Integer commentUserId;

	@Column(name = "AT_USER_ID")
	private Integer atUserId;

	@Column(name = "FLOOR_NUM")
	private Integer floorNum;

	@Column(name = "CONT")
	private String cont;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_DATE")
	private Date createDate;

	public NoteCommentReply() {
	}

	public Integer getNoteCommentReplyId() {
		return noteCommentReplyId;
	}

	public Integer getNoteCommentId() {
		return noteCommentId;
	}

	public Integer getCommentUserId() {
		return commentUserId;
	}

	public Integer getAtUserId() {
		return atUserId;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public String getCont() {
		return cont;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setNoteCommentReplyId(Integer noteCommentReplyId) {
		this.noteCommentReplyId = noteCommentReplyId;
	}

	public void setNoteCommentId(Integer noteCommentId) {
		this.noteCommentId = noteCommentId;
	}

	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}

	public void setAtUserId(Integer atUserId) {
		this.atUserId = atUserId;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
