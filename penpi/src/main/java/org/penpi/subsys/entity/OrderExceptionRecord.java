package org.penpi.subsys.entity;

import java.io.Serializable;

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
@Table(name = "PRD_ORDER_EXCEPTION_RECORD")
@Document(indexName = Global.INDEX_NAME, type = "orderExceptionRecord")
public class OrderExceptionRecord implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String ORDER_EXCEPTION_RECORD_ID = "orderExceptionRecordId";
	public static final String ORDER_ID = "orderId";
	public static final String EXCEPTION_TIME = "exceptionTime";
	public static final String EXCEPTION_REASON = "exceptionReason";
	public static final String IF_FIX_EXCEPTION = "ifFixException";
	public static final String FINISH_FIX_TIME = "finishFixTime";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_EXCEPTION_RECORD_ID")
	private java.lang.Integer orderExceptionRecordId;

	@Column(name = "ORDER_ID")
	private java.lang.Integer orderId;

	@Column(name = "EXCEPTION_TIME")
	private java.util.Date exceptionTime;

	@Column(name = "EXCEPTION_REASON")
	private java.lang.String exceptionReason;

	@Column(name = "IF_FIX_EXCEPTION")
	private java.lang.String ifFixException;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "FINISH_FIX_TIME")
	private java.util.Date finishFixTime;

	public OrderExceptionRecord() {
	}

	public java.lang.Integer getOrderExceptionRecordId() {
		return orderExceptionRecordId;
	}

	public void setOrderExceptionRecordId(java.lang.Integer orderExceptionRecordId) {
		this.orderExceptionRecordId = orderExceptionRecordId;
	}

	public java.lang.Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}

	public java.util.Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(java.util.Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public java.lang.String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(java.lang.String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	public java.lang.String getIfFixException() {
		return ifFixException;
	}

	public void setIfFixException(java.lang.String ifFixException) {
		this.ifFixException = ifFixException;
	}

	public java.util.Date getFinishFixTime() {
		return finishFixTime;
	}

	public void setFinishFixTime(java.util.Date finishFixTime) {
		this.finishFixTime = finishFixTime;
	}

}
