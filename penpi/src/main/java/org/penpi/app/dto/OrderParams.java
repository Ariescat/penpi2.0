package org.penpi.app.dto;

import java.util.Date;

public class OrderParams {
	private Double locationLng;
	private Double locationLat;

	private String orderType; // 排序类型

	private String userSexCode;// 过滤性别
	private Date fromTime; // 过滤时间
	private Date toTime;
	private Integer distance;// 过滤范围

	public Double getLocationLng() {
		return locationLng;
	}

	public void setLocationLng(Double locationLng) {
		this.locationLng = locationLng;
	}

	public Double getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getUserSexCode() {
		return userSexCode;
	}

	public void setUserSexCode(String userSexCode) {
		this.userSexCode = userSexCode;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

}
