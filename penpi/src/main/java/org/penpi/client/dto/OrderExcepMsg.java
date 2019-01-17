package org.penpi.client.dto;

public class OrderExcepMsg {

	private String sendUserNm;
	private String sendUserMobile;
	private String takeUserNm;
	private String takeUserMobile;
	private String startPlace;
	private String endPlace;
	 
	
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
	public String getTakeUserNm() {
		return takeUserNm;
	}
	public void setTakeUserNm(String takeUserNm) {
		this.takeUserNm = takeUserNm;
	}
	public String getTakeUserMobile() {
		return takeUserMobile;
	}
	public void setTakeUserMobile(String takeUserMobile) {
		this.takeUserMobile = takeUserMobile;
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
 
	
}
