package org.penpi.app.dto;

import java.util.ArrayList;
import java.util.List;

import org.penpi.subsys.entity.NoteComment;

public class NoteCommentEx extends NoteComment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userNm;
	private String userHeadPictStr;
	
	private List<NoteCommentReplyEx> commentReplies;
	
	public NoteCommentEx() {
		super();
		this.commentReplies = new ArrayList<>();
	}

	public List<NoteCommentReplyEx> getCommentReplies() {
		return commentReplies;
	}

	public void setCommentReplies(List<NoteCommentReplyEx> commentReplies) {
		this.commentReplies = commentReplies;
	}

	public String getUserNm() {
		return userNm;
	}

	public String getUserHeadPictStr() {
		return userHeadPictStr;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public void setUserHeadPictStr(String userHeadPictStr) {
		this.userHeadPictStr = userHeadPictStr;
	}

}
