package org.penpi.app.dto;

import java.util.ArrayList;
import java.util.List;

public class NoteDetail {

	private List<NoteCommentEx> noteCommentExs;

	public NoteDetail() {
		super();
		this.noteCommentExs = new ArrayList<>();
	}

	public List<NoteCommentEx> getNoteCommentExs() {
		return noteCommentExs;
	}

	public void setNoteCommentExs(List<NoteCommentEx> noteCommentExs) {
		this.noteCommentExs = noteCommentExs;
	}
}
