package org.penpi.subsys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.core.utils.UtilDateTimeClient;
import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PRD_ORDER")
@Document(indexName = Global.INDEX_NAME, type = "order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ORDER_ID = "orderId";
	public static final String SEND_USER_ID = "sendUserId";
	public static final String SEND_USER_NM = "sendUserNm";
	public static final String SEND_USER_MOBILE = "sendUserMobile";
	public static final String TAKE_USER_ID = "takeUserId";
	public static final String START_PLACE = "startPlace";
	public static final String START_PLACE_LNG = "startPlaceLng";
	public static final String START_PLACE_LAT = "startPlaceLat";
	public static final String END_PLACE = "endPlace";
	public static final String END_PLACE_LNG = "endPlaceLng";
	public static final String END_PLACE_LAT = "endPlaceLat";
	public static final String CREATE_DATE = "createDate";
	public static final String TAKE_ORDER_DATE = "takeOrderDate";
	public static final String ORDER_FEE = "orderFee";
	public static final String ORDER_STAT_CODE = "orderStatCode";
	public static final String ORDER_REMARK = "orderRemark";
	
	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "SEND_USER_ID")
	private Integer sendUserId;

	@Column(name = "SEND_USER_NM")
	private String sendUserNm;

	@Column(name = "SEND_USER_MOBILE")
	private String sendUserMobile;

	@Column(name = "TAKE_USER_ID")
	private Integer takeUserId;

	@Column(name = "START_PLACE")
	private String startPlace;

	@Column(name = "START_PLACE_LNG")
	private Double startPlaceLng;

	@Column(name = "START_PLACE_LAT")
	private Double startPlaceLat;

	@Column(name = "END_PLACE")
	private String endPlace;

	@Column(name = "END_PLACE_LNG")
	private Double endPlaceLng;

	@Column(name = "END_PLACE_LAT")
	private Double endPlaceLat;

	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Column(name = "TAKE_ORDER_DATE")
	private Date takeOrderDate;

	@Column(name = "ORDER_FEE")
	private Double orderFee;

	@Column(name = "ORDER_STAT_CODE")
	private String orderStatCode;

	@Column(name = "ORDER_REMARK")
	private String orderRemark;

	public Order() {
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendUserNm() {
		return sendUserNm;
	}

	public void setSendUserNm(String sendUserNm) {
		this.sendUserNm = sendUserNm;
	}

	public String getSendUserMobile() {
		return sendUserMobile;
	}

	public void setSendUserMobile(String sendUserMobile) {
		this.sendUserMobile = sendUserMobile;
	}

	public Integer getTakeUserId() {
		return takeUserId;
	}

	public void setTakeUserId(Integer takeUserId) {
		this.takeUserId = takeUserId;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public Double getStartPlaceLng() {
		return startPlaceLng;
	}

	public void setStartPlaceLng(Double startPlaceLng) {
		this.startPlaceLng = startPlaceLng;
	}

	public Double getStartPlaceLat() {
		return startPlaceLat;
	}

	public void setStartPlaceLat(Double startPlaceLat) {
		this.startPlaceLat = startPlaceLat;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public Double getEndPlaceLng() {
		return endPlaceLng;
	}

	public void setEndPlaceLng(Double endPlaceLng) {
		this.endPlaceLng = endPlaceLng;
	}

	public Double getEndPlaceLat() {
		return endPlaceLat;
	}

	public void setEndPlaceLat(Double endPlaceLat) {
		this.endPlaceLat = endPlaceLat;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getCreateDateStr() {
		return this.getCreateDate() == null ? null
				: UtilDateTimeClient.convertDateTimeToMmString(this.getCreateDate());
	}

	public void setCreateDateStr(String value) {
		if (value != null) {
			this.setCreateDate(UtilDateTimeClient.convertStringToDateTime(value));
		}
	}

	public Double getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}

	public String getOrderStatCode() {
		return orderStatCode;
	}

	public void setOrderStatCode(String orderStatCode) {
		this.orderStatCode = orderStatCode;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Date getTakeOrderDate() {
		return takeOrderDate;
	}

	public void setTakeOrderDate(Date takeOrderDate) {
		this.takeOrderDate = takeOrderDate;
	}
	
	public String getTakeOrderDateStr() {
		return this.getTakeOrderDate() == null ? null
				: UtilDateTimeClient.convertDateTimeToMmString(this.getTakeOrderDate());
	}

	public void setTakeOrderDateStr(String value) {
		if (value != null) {
			this.setTakeOrderDate(UtilDateTimeClient.convertStringToDateTime(value));
		}
	}

}
