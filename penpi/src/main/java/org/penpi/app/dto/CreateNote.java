package org.penpi.app.dto;

import java.util.List;

import org.penpi.subsys.entity.Note;
import org.penpi.subsys.entity.NoteFile;

public class CreateNote extends Note {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NoteFile> noteFiles;

	public List<NoteFile> getNoteFiles() {
		return noteFiles;
	}

	public void setNoteFiles(List<NoteFile> noteFiles) {
		this.noteFiles = noteFiles;
	}
}
