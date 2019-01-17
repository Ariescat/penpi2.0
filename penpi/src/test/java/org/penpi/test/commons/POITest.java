package org.penpi.test.commons;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.penpi.core.commons.utils.FillExcelWriter;
import org.penpi.core.commons.utils.ListExcelWriter;
import org.penpi.core.utils.UtilDateTime;

public class POITest {
	
    @Test
    public void testFillExcelWriter() throws IOException {
    	Order order = new Order();

    	OrderItem item1 = new OrderItem();
    	item1.setIntegral(30);
    	item1.setNum(2);
    	item1.setProductNm("商品B");
    	item1.setSpecNm("红色");
    	item1.setStockNo("AFDSE001");
    	item1.setUnitPrice(3143234234123.15);
    	order.getOrderItems().add(item1);

    	OrderItem item2 = new OrderItem();
    	item2.setIntegral(50);
    	item2.setNum(3);
    	item2.setProductNm("商品W");
    	item2.setSpecNm("");
    	item2.setStockNo("FDWEE345");
    	item2.setUnitPrice(32.15);
    	order.getOrderItems().add(item2);

    	FillExcelWriter fill = new FillExcelWriter("webapp/exceltemplate/fillExcelExample.xls");
    	String outFileName = "webapp/temp/" + UUID.randomUUID() + ".xls";
    	fill.fillToFile(order, outFileName);
    	System.out.println(outFileName);
    }

    @Test
    public void testListExcelWriter() throws IOException {
        ListExcelWriter writer = new ListExcelWriter("webapp/exceltemplate/listExcelExample.xls");

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("loginId", "loginId" + i);
            data.put("name", "name" + i);
            data.put("email", "email" + i);
            data.put("mobi", "mobi" + i);
            data.put("tel", "tel" + i);
            data.put("addr", "addr" + i);
            data.put("money", i % 21);
            dataList.add(data);
        }
    	String outFileName = "webapp/temp/" + UUID.randomUUID() + ".xls";
        writer.fillToFile(dataList, outFileName);
    	System.out.println(outFileName);
    }

    public static class OrderItem{
    	private String productNm;
    	private String stockNo;
    	private String specNm;
    	private Double unitPrice;
    	private Integer num;
    	private Integer integral;
    	private String totalPrice;
		public String getProductNm() {
			return productNm;
		}
		public void setProductNm(String productNm) {
			this.productNm = productNm;
		}
		public String getStockNo() {
			return stockNo;
		}
		public void setStockNo(String stockNo) {
			this.stockNo = stockNo;
		}
		public String getSpecNm() {
			return specNm;
		}
		public void setSpecNm(String specNm) {
			this.specNm = specNm;
		}
		public Double getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(Double unitPrice) {
			this.unitPrice = unitPrice;
		}
		public Integer getNum() {
			return num;
		}
		public void setNum(Integer num) {
			this.num = num;
		}
		public Integer getIntegral() {
			return integral;
		}
		public void setIntegral(Integer integral) {
			this.integral = integral;
		}
		public String getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}
    }
    
    public static class Order{
		private String orderNum = "DFANE00201103030032";
		private Date createDate = new Date();
		private String userName = "用户x";
		private String loginId = "usera";
		private String userEmail = "admin@iloosen.com";
		private String userMobile = "13800138000";
		private String userTel = "020-342432342";
		private List<OrderItem> orderItems = new ArrayList<OrderItem>();
		public String getOrderNum() {
			return orderNum;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public String getUserName() {
			return userName;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public String getUserMobile() {
			return userMobile;
		}
		public String getUserTel() {
			return userTel;
		}
		public String getCreateDateStr(){
			return UtilDateTime.convertDateToString(createDate);
		}
		public String getLoginId() {
			return loginId;
		}
		public List<OrderItem> getOrderItems() {
			return orderItems;
		}
		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}
	}

    
}
