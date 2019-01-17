package org.penpi.app.dto;

import java.util.ArrayList;
import java.util.List;

import org.penpi.subsys.entity.Note;

public class NoteEx extends Note {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<NoteFileEx> noteFiles;
	private int commentAmount;
	private String userHeadPictStr;

	public NoteEx() {
		super();
		this.noteFiles = new ArrayList<NoteEx.NoteFileEx>();
	}

	public class NoteFileEx {
		Integer fileInfId;
		String fileInfStr;

		public NoteFileEx(Integer fileInfId, String fileInfStr) {
			super();
			this.fileInfId = fileInfId;
			this.fileInfStr = fileInfStr;
		}

		public Integer getFileInfId() {
			return fileInfId;
		}

		public void setFileInfId(Integer fileInfId) {
			this.fileInfId = fileInfId;
		}

		public String getFileInfStr() {
			return fileInfStr;
		}

		public void setFileInfStr(String fileInfStr) {
			this.fileInfStr = fileInfStr;
		}
	}

	public List<NoteFileEx> getNoteFiles() {
		return noteFiles;
	}

	public void setNoteFiles(List<NoteFileEx> noteFiles) {
		this.noteFiles = noteFiles;
	}

	public String getUserHeadPictStr() {
		return userHeadPictStr;
	}

	public void setUserHeadPictStr(String userHeadPictStr) {
		this.userHeadPictStr = userHeadPictStr;
	}

	public int getCommentAmount() {
		return commentAmount;
	}

	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
	}

}
