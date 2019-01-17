package org.penpi.app.dto;

import org.penpi.subsys.entity.NoteCommentReply;

public class NoteCommentReplyEx extends NoteCommentReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String commentUserNm;
	private String commentUserHeadPictStr;
	
	private String atUserNm;
	
	public String getCommentUserNm() {
		return commentUserNm;
	}

	public String getAtUserNm() {
		return atUserNm;
	}

	public void setCommentUserNm(String commentUserNm) {
		this.commentUserNm = commentUserNm;
	}

	public void setAtUserNm(String atUserNm) {
		this.atUserNm = atUserNm;
	}

	public String getCommentUserHeadPictStr() {
		return commentUserHeadPictStr;
	}

	public void setCommentUserHeadPictStr(String commentUserHeadPictStr) {
		this.commentUserHeadPictStr = commentUserHeadPictStr;
	}

}
