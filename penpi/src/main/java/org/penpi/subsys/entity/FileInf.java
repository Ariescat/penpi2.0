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
@Table(name = "SYS_FILE_INF")
@Document(indexName = Global.INDEX_NAME, type = "fileInf")
public class FileInf implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String FILE_INF_ID = "fileInfId";
	public static final String FILE_PATH = "filePath";
	public static final String BUSINESS_CLASS_NM = "businessClassNm";
	public static final String BUSINESS_FIELD_NM = "businessFieldNm";
	public static final String BUSINESS_OBJECT_ID = "businessObjectId";
	public static final String IF_PICT = "ifPict";
	public static final String FILE_NM = "fileNm";
	public static final String FILE_TYPE_NM = "fileTypeNm";
	public static final String FILE_SIZE_KB = "fileSizeKb";
	public static final String FILE_TIME = "fileTime";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_INF_ID")
	private java.lang.Integer fileInfId;

	@Column(name = "FILE_PATH")
	private java.lang.String filePath;

	@Column(name = "BUSINESS_CLASS_NM")
	private java.lang.String businessClassNm;
	
	@Column(name = "BUSINESS_FIELD_NM")
	private java.lang.String businessFieldNm;

	@Column(name = "BUSINESS_OBJECT_ID")
	private java.lang.Integer businessObjectId;

	@Column(name = "IF_PICT")
	private java.lang.String ifPict;

	@Column(name = "FILE_NM")
	private java.lang.String fileNm;

	@Column(name = "FILE_TYPE_NM")
	private java.lang.String fileTypeNm;

	@Column(name = "FILE_SIZE_KB")
	private java.lang.Integer fileSizeKb;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "FILE_TIME")
	private java.util.Date fileTime;

	public FileInf() {
	}

	public java.lang.Integer getFileInfId() {
		return fileInfId;
	}

	public void setFileInfId(java.lang.Integer fileInfId) {
		this.fileInfId = fileInfId;
	}

	public java.lang.String getFilePath() {
		return filePath;
	}

	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	public java.lang.String getBusinessClassNm() {
		return businessClassNm;
	}

	public void setBusinessClassNm(java.lang.String businessClassNm) {
		this.businessClassNm = businessClassNm;
	}

	public java.lang.Integer getBusinessObjectId() {
		return businessObjectId;
	}

	public void setBusinessObjectId(java.lang.Integer businessObjectId) {
		this.businessObjectId = businessObjectId;
	}

	public java.lang.String getIfPict() {
		return ifPict;
	}

	public void setIfPict(java.lang.String ifPict) {
		this.ifPict = ifPict;
	}

	public java.lang.String getFileNm() {
		return fileNm;
	}

	public void setFileNm(java.lang.String fileNm) {
		this.fileNm = fileNm;
	}

	public java.lang.String getFileTypeNm() {
		return fileTypeNm;
	}

	public void setFileTypeNm(java.lang.String fileTypeNm) {
		this.fileTypeNm = fileTypeNm;
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

	public java.lang.String getBusinessFieldNm() {
		return businessFieldNm;
	}

	public void setBusinessFieldNm(java.lang.String businessFieldNm) {
		this.businessFieldNm = businessFieldNm;
	}
}
