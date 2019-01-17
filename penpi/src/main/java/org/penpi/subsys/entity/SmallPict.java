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
@Table(name = "SYS_SMALL_PICT")
@Document(indexName = Global.INDEX_NAME, type = "smallPict")
public class SmallPict implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String SMALL_PICT_ID = "smallPictId";
	public static final String FILE_INF_ID = "fileInfId";
	public static final String SMALL_PICT_SETUP_ID = "smallPictSetupId";
	public static final String SMALL_PICT_WIDTH = "smallPictWidth";
	public static final String SMALL_PICT_HEIGHT = "smallPictHeight";
	public static final String FILE_SIZE_KB = "fileSizeKb";
	public static final String FILE_TIME = "fileTime";
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SMALL_PICT_ID")	
	private java.lang.Integer smallPictId;

	@Column(name = "FILE_INF_ID")
	private java.lang.Integer fileInfId;

	@Column(name = "SMALL_PICT_SETUP_ID")
	private java.lang.Integer smallPictSetupId;

	@Column(name = "SMALL_PICT_WIDTH")
	private java.lang.Integer smallPictWidth;

	@Column(name = "SMALL_PICT_HEIGHT")
	private java.lang.Integer smallPictHeight;

	@Column(name = "FILE_SIZE_KB")
	private java.lang.Integer fileSizeKb;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "FILE_TIME")
	private java.util.Date fileTime;


	public SmallPict(){
	}


	public java.lang.Integer getSmallPictId() {
		return smallPictId;
	}


	public void setSmallPictId(java.lang.Integer smallPictId) {
		this.smallPictId = smallPictId;
	}


	public java.lang.Integer getFileInfId() {
		return fileInfId;
	}


	public void setFileInfId(java.lang.Integer fileInfId) {
		this.fileInfId = fileInfId;
	}


	public java.lang.Integer getSmallPictSetupId() {
		return smallPictSetupId;
	}


	public void setSmallPictSetupId(java.lang.Integer smallPictSetupId) {
		this.smallPictSetupId = smallPictSetupId;
	}


	public java.lang.Integer getSmallPictWidth() {
		return smallPictWidth;
	}


	public void setSmallPictWidth(java.lang.Integer smallPictWidth) {
		this.smallPictWidth = smallPictWidth;
	}


	public java.lang.Integer getSmallPictHeight() {
		return smallPictHeight;
	}


	public void setSmallPictHeight(java.lang.Integer smallPictHeight) {
		this.smallPictHeight = smallPictHeight;
	}


	public java.lang.Integer getFileSizeKb() {
		return fileSizeKb;
	}


	public void setFileSizeKb(java.lang.Integer fileSizeKb) {
		this.fileSizeKb = fileSizeKb;
	}


	public java.util.Date getFileTime() {
		return fileTime;
	}


	public void setFileTime(java.util.Date fileTime) {
		this.fileTime = fileTime;
	}

	
}

