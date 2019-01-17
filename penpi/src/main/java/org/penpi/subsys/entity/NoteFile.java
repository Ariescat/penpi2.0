package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "PRD_NOTE_FILE")
@Document(indexName = Global.INDEX_NAME, type = "noteFile")
public class NoteFile implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String NOTE_FILE_ID = "noteFileId";
	public static final String NOTE_ID = "noteId";
	public static final String FILE_INF_ID = "fileInfId";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_FILE_ID")
	private java.lang.Integer noteFileId;

	@Column(name = "NOTE_ID")
	private java.lang.Integer noteId;

	@Column(name = "FILE_INF_ID")
	private java.lang.Integer fileInfId;

	public NoteFile() {
	}

	public java.lang.Integer getNoteFileId() {
		return noteFileId;
	}

	public void setNoteFileId(java.lang.Integer noteFileId) {
		this.noteFileId = noteFileId;
	}

	public java.lang.Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(java.lang.Integer noteId) {
		this.noteId = noteId;
	}

	public java.lang.Integer getFileInfId() {
		return fileInfId;
	}

	public void setFileInfId(java.lang.Integer fileInfId) {
		this.fileInfId = fileInfId;
	}

}
