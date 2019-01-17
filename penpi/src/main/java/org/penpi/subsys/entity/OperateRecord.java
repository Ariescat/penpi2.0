package org.penpi.subsys.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "PRD_OPERATE_RECORD")
@Document(indexName = Global.INDEX_NAME, type = "operateRecord")
public class OperateRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OP_ID")
	private Integer opId;
	
	@Column(name = "OP_CONTENT")
	private String opContent;
	

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "OP_DATE")
	private Date opDate;
	
	@Column(name = "OP_TYPE")
	private String opType;
	
	@Column(name = "OP_USER_LOGINID")
	private String opUserLoginId;
	
	public OperateRecord() {
		
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
	}

	public String getOpContent() {
		return opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpUserLoginId() {
		return opUserLoginId;
	}

	public void setOpUserLoginId(String opUserLoginId) {
		this.opUserLoginId = opUserLoginId;
	}
	
	

}
