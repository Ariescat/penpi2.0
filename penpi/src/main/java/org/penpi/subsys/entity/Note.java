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
@Table(name = "PRD_NOTE")
@Document(indexName = Global.INDEX_NAME, type = "note")
public class Note implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String NOTE_ID = "noteId";
	public static final String TOPIC_ID = "topicId";
	public static final String TOPIC_NM = "topicNm";
	public static final String SEND_NOTE_USER_ID = "sendNoteUserId";
	public static final String SEND_NOTE_USER_NM = "sendNoteUserNm";
	public static final String NOTE_CONT = "noteCont";
	public static final String NOTE_HEAT = "noteHeat";
	public static final String NOTE_STAT_CODE = "noteStatCode";
	public static final String CREATE_DATE = "createDate";
	public static final String LAST_UPD_TIME = "lastUpdTime";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ID")
	private java.lang.Integer noteId;

	@Column(name = "TOPIC_ID")
	private java.lang.Integer topicId;

	@Column(name = "TOPIC_NM")
	private java.lang.String topicNm;

	@Column(name = "SEND_NOTE_USER_ID")
	private java.lang.Integer sendNoteUserId;

	@Column(name = "SEND_NOTE_USER_NM")
	private java.lang.String sendNoteUserNm;
	
	@Column(name = "NOTE_CONT")
	private java.lang.String noteCont;

	@Column(name = "NOTE_HEAT")
	private java.lang.Integer noteHeat;
	
	@Column(name = "NOTE_STAT_CODE")
	private java.lang.String noteStatCode;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_DATE")
	private java.util.Date createDate;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "LAST_UPD_TIME")
	private java.util.Date lastUpdTime;

	public Note() {
	}

	public java.lang.Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(java.lang.Integer noteId) {
		this.noteId = noteId;
	}

	public java.lang.Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(java.lang.Integer topicId) {
		this.topicId = topicId;
	}

	public java.lang.String getTopicNm() {
		return topicNm;
	}

	public void setTopicNm(java.lang.String topicNm) {
		this.topicNm = topicNm;
	}

	public java.lang.Integer getSendNoteUserId() {
		return sendNoteUserId;
	}

	public void setSendNoteUserId(java.lang.Integer sendNoteUserId) {
		this.sendNoteUserId = sendNoteUserId;
	}

	public java.lang.String getNoteCont() {
		return noteCont;
	}

	public void setNoteCont(java.lang.String noteCont) {
		this.noteCont = noteCont;
	}

	public java.lang.String getNoteStatCode() {
		return noteStatCode;
	}

	public void setNoteStatCode(java.lang.String noteStatCode) {
		this.noteStatCode = noteStatCode;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(java.util.Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public java.lang.String getSendNoteUserNm() {
		return sendNoteUserNm;
	}

	public void setSendNoteUserNm(java.lang.String sendNoteUserNm) {
		this.sendNoteUserNm = sendNoteUserNm;
	}

	public java.lang.Integer getNoteHeat() {
		return noteHeat;
	}

	public void setNoteHeat(java.lang.Integer noteHeat) {
		this.noteHeat = noteHeat;
	}

}
