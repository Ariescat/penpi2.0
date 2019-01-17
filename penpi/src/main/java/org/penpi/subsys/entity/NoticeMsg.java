package org.penpi.subsys.entity;

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
@Table(name = "PRD_NOTICE_MSG")
@Document(indexName = Global.INDEX_NAME, type = "noticeMsg")
public class NoticeMsg implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String NOTICE_MSG_ID = "noticeMsgId";
	public static final String RECEIVER_USER_ID = "receiverUserId";
	public static final String MSG_HEAD_PICT_ID = "msgHeadPictId";
	public static final String MSG_CONT = "msgCont";
	public static final String MSG_OBJECT = "msgObject";
	public static final String MSG_TIME = "msgTime";
	public static final String IF_HAS_READ = "ifHasRead";
	public static final String SYS_NOTICE_TYPE_CODE = "sysNoticeTypeCode";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTICE_MSG_ID")
	private java.lang.Integer noticeMsgId;

	@Column(name = "RECEIVER_USER_ID")
	private java.lang.Integer receiverUserId;

	@Column(name = "MSG_HEAD_PICT_ID")
	private java.lang.Integer msgHeadPictId;

	@Column(name = "MSG_CONT")
	private java.lang.String msgCont;

	@Column(name = "MSG_OBJECT")
	private byte[] msgObject;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "MSG_TIME")
	private java.util.Date msgTime;

	@Column(name = "IF_HAS_READ")
	private java.lang.String ifHasRead;

	@Column(name = "SYS_NOTICE_TYPE_CODE")
	private java.lang.String sysNoticeTypeCode;

	public NoticeMsg() {
	}

	public java.lang.Integer getNoticeMsgId() {
		return noticeMsgId;
	}

	public void setNoticeMsgId(java.lang.Integer noticeMsgId) {
		this.noticeMsgId = noticeMsgId;
	}

	public java.lang.Integer getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(java.lang.Integer receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public java.lang.Integer getMsgHeadPictId() {
		return msgHeadPictId;
	}

	public void setMsgHeadPictId(java.lang.Integer msgHeadPictId) {
		this.msgHeadPictId = msgHeadPictId;
	}

	public java.lang.String getMsgCont() {
		return msgCont;
	}

	public void setMsgCont(java.lang.String msgCont) {
		this.msgCont = msgCont;
	}

	public byte[] getMsgObject() {
		return msgObject;
	}

	public void setMsgObject(byte[] msgObject) {
		this.msgObject = msgObject;
	}

	public java.util.Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(java.util.Date msgTime) {
		this.msgTime = msgTime;
	}

	public java.lang.String getIfHasRead() {
		return ifHasRead;
	}

	public void setIfHasRead(java.lang.String ifHasRead) {
		this.ifHasRead = ifHasRead;
	}

	public java.lang.String getSysNoticeTypeCode() {
		return sysNoticeTypeCode;
	}

	public void setSysNoticeTypeCode(java.lang.String sysNoticeTypeCode) {
		this.sysNoticeTypeCode = sysNoticeTypeCode;
	}

}
