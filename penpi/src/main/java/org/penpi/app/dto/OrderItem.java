package org.penpi.app.dto;

import java.util.Date;

import org.penpi.subsys.entity.Order;
import org.springframework.beans.BeanUtils;

public class OrderItem {

	private Integer orderId;
	private String startPlace;
	private String endPlace;
	private Double orderFee;
	private Double distance;
	private Date createDate;

	private String userSexCode;

	public OrderItem() {
	}
	
	public OrderItem(Order order) {
		BeanUtils.copyProperties(order, this);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public Double getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserSexCode() {
		return userSexCode;
	}

	public void setUserSexCode(String userSexCode) {
		this.userSexCode = userSexCode;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
