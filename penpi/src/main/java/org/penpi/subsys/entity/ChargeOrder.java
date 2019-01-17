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
@Table(name = "PRD_CHARGE_ORDER")
@Document(indexName = Global.INDEX_NAME, type = "chargeOrder")
public class ChargeOrder implements Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	public static final String CHARGE_ORDER_ID = "chargeOrderId";
	public static final String CHARGE_ORDER_NUM = "chargeOrderNum";
	public static final String USER_ID = "userId";
	public static final String MEMBER_ACCOUNT_ID = "memberAccountId";
	public static final String MEMBER_ACCOUNT_LOG_ID = "memberAccountLogId";
	public static final String PAYMENT_TYPE_CODE = "paymentTypeCode";
	public static final String PAYMENT_TRANSACTIONAL_NUMBER = "paymentTransactionalNumber";
	public static final String CHARGE_AMMOUNT = "chargeAmmount";
	public static final String CHARGE_ORDER_STAT_CODE = "chargeOrderStatCode";
	public static final String CREATE_TIME = "createTime";
	public static final String CHARGE_FINISH_TIME = "chargeFinishTime";
	public static final String REFUND_FINISH_TIME = "refundFinishTime";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHARGE_ORDER_ID")
	private Integer chargeOrderId;

	@Column(name = "CHARGE_ORDER_NUM")
	private String chargeOrderNum;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "MEMBER_ACCOUNT_ID")
	private Integer memberAccountId;

	@Column(name = "MEMBER_ACCOUNT_LOG_ID")
	private Integer memberAccountLogId;

	@Column(name = "PAYMENT_TYPE_CODE")
	private String paymentTypeCode;

	@Column(name = "PAYMENT_TRANSACTIONAL_NUMBER")
	private String paymentTransactionalNumber;

	@Column(name = "CHARGE_AMMOUNT")
	private Double chargeAmmount;

	@Column(name = "CHARGE_ORDER_STAT_CODE")
	private String chargeOrderStatCode;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CHARGE_FINISH_TIME")
	private Date chargeFinishTime;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "REFUND_FINISH_TIME")
	private Date refundFinishTime;

	public ChargeOrder() {
	}

	public Integer getChargeOrderId() {
		return chargeOrderId;
	}

	public void setChargeOrderId(Integer chargeOrderId) {
		this.chargeOrderId = chargeOrderId;
	}

	public String getChargeOrderNum() {
		return chargeOrderNum;
	}

	public void setChargeOrderNum(String chargeOrderNum) {
		this.chargeOrderNum = chargeOrderNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMemberAccountId() {
		return memberAccountId;
	}

	public void setMemberAccountId(Integer memberAccountId) {
		this.memberAccountId = memberAccountId;
	}

	public Integer getMemberAccountLogId() {
		return memberAccountLogId;
	}

	public void setMemberAccountLogId(Integer memberAccountLogId) {
		this.memberAccountLogId = memberAccountLogId;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getPaymentTransactionalNumber() {
		return paymentTransactionalNumber;
	}

	public void setPaymentTransactionalNumber(String paymentTransactionalNumber) {
		this.paymentTransactionalNumber = paymentTransactionalNumber;
	}

	public Double getChargeAmmount() {
		return chargeAmmount;
	}

	public void setChargeAmmount(Double chargeAmmount) {
		this.chargeAmmount = chargeAmmount;
	}

	public String getChargeOrderStatCode() {
		return chargeOrderStatCode;
	}

	public void setChargeOrderStatCode(String chargeOrderStatCode) {
		this.chargeOrderStatCode = chargeOrderStatCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getChargeFinishTime() {
		return chargeFinishTime;
	}

	public void setChargeFinishTime(Date chargeFinishTime) {
		this.chargeFinishTime = chargeFinishTime;
	}

	public Date getRefundFinishTime() {
		return refundFinishTime;
	}

	public void setRefundFinishTime(Date refundFinishTime) {
		this.refundFinishTime = refundFinishTime;
	}

}
