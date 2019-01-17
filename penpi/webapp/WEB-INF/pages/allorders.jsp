<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/base.jsp"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function submitformSelect(){
	//获取form表单对象
    	var sortType = document.getElementById("sort");
    	var orderType = document.getElementById("type");
    	//alert(orderType.value);
    	var formSelect = document.getElementById("formSelect");
    	 if(sortType.value==0){
    		 $('#sortType').val("0");
    	 }else{
    		 $('#sortType').val("1");
    	 }
    	// alert(orderType.value);
    	 if(orderType.value==-1){
 			 $('#orderType').val("-1");
 		}
    	 else if(orderType.value==0){
 			 $('#orderType').val("0");
 		}else if(orderType.value==1){
 			 $('#orderType').val("1");
 		}else if(orderType.value==2){
 			 $('#orderType').val("2");
 		}else if(orderType.value==3){
 			 $('#orderType').val("3");
 		}else if(orderType.value==4){
 			 $('#orderType').val("4");
 		}else{
 			 $('#orderType').val("5");
 		}
 		
    	 formSelect.submit();//form表单提交 */
	 
}
$(function(){  
	$("#buttonSelect").click(function(){ 
		/* alert("da"); */
		var form = document.getElementById("formSelect");
		
		
		form.submit();
		// alert($('#sendUserMobile').val());
		
	});
})
$(function () { $("[data-toggle='tooltip']").tooltip(); });

var pOrderType;
var pOrderId;
var thistr;
function orderOp(obj,orderId,orderType){
/* 	alert(orderType); 
	alert(orderId); */
	pOrderType = orderType;
	pOrderId = orderId;
    thistr = $(obj).parent().parent(); //获取td的父节点，就是当前行的tr啦~
	
    $('#orderId').text(orderId);
	 
		 
	/*  $.ajax({  
	       type:'post', 
	       url:'/admin/order/setExcepOrder.do',  
	       scriptCharset:'utf-8',
	
	       data:{},  
	       success:function(data){//返回json结果  
	       	refreshTable(data); 	        
	       }
	     
	   	
	   }); */	
	
	 
	
	  
	/* alert($('#sb').val()); */
}
function setExcep(){
	/* alert(pOrderId);
	alert(pOrderType);
	alert($('#excepReason').val()); */
	 
	$.ajax({  
	       type:'post', 
	       url:'${pageContext.request.contextPath}/admin/order/setExcepOrder.do',  
	       scriptCharset:'utf-8',
	       data:{"orderId":pOrderId,"orderType":pOrderType,"excepReason":$('#excepReason').val()},  
	       success:function(data){//返回json结果  
	    	   alert("订单已变为异常");
	    	   thistr.find("td").eq(10).css({color:"red"}) 
	    	   thistr.find("td").eq(10).text("异常");
	    	   thistr.find("td").eq(11).text("");
	       }
	     
	   	
	   });
}
 
</script>
</head>
<body>
 
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">订单id:<label id="orderId"></label></h4>
            </div>
            <div class="modal-body">		
				<input type="text" placeholder="订单异常原因" class="form-control" id="excepReason" name="excepReason">
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="setExcep()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 
 
 
 
 
<div class="panel-body" style="padding-bottom:0px;width:1200px;height:50px;padding-left:50px;">
	<div class="panel panel-default"  >
		<div class="panel-heading" >订单管理 &nbsp; &nbsp;>> &nbsp; &nbsp; 订单列表</div>
	</div>
			<form id="formSelect" action="${pageContext.request.contextPath}/admin/order/findallorders.do">
			<input type="hidden" name="pageNum" value=1></input>
			<input type="hidden" id="sortType" name="sortType" value="${sortType}"></input>
			<input type="hidden" id="orderType" name="orderType" value="${orderType}"></input>
			
	    	<div class="form-group" style="padding-top:20px;float:left;"  >
		        	<label class="form-label">排序方式</label> &nbsp; &nbsp; 
		        	<select  class="selectpicker" id="sort" name="sort" data-width="auto" onchange="submitformSelect()">
						 <option value="0"  ${sortType == "0" ? "selected" : ""}>按时间</option>
						  <option value="1"  ${sortType == "1" ? "selected" : ""}>按金额</option>
					</select>
			</div>	
				<div class="form-group" style="padding-top:20px;float:left;padding-left:30px;">
		        	<label class="form-label">订单状态</label> &nbsp; &nbsp; 
		        	<select  class="selectpicker" id="type" name="type" data-width="auto" onchange="submitformSelect()">
		        		  <option value="-1" ${orderType == "-1" ? "selected" : ""}>不限</option>
						  <option value="0"  ${orderType == "0" ? "selected" : ""}>发单</option>
						  <option value="1"  ${orderType == "1" ? "selected" : ""}>已抢</option>
						  <option value="2"  ${orderType == "2" ? "selected" : ""}>交付</option>
						  <option value="3"  ${orderType == "3" ? "selected" : ""}>支付</option>
						  <option value="4"  ${orderType == "4" ? "selected" : ""}>完成</option>
						  <option value="5"  ${orderType == "5" ? "selected" : ""}>异常</option>
					</select>
			</div>
			<div style="float:right;padding-top:20px;padding-left:20px;">
      			<button type="button"   class="btn btn-primary" id="buttonSelect">查询</button>     	              
      		</div>
			<div style="float:right;padding-top:20px;">
      			<input type="text" placeholder="发单人手机号"  value="${sendUserMobile}" id="sendUserMobile" name="sendUserMobile" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" class="form-control" >
     		</div>	
     		
			</form>
			
 
    
    <div style="padding-top:100px;">
	<table id="ordersinfo" style="font-size:13px;" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	    	<th  style="text-align:center;width:5%;"> 订单ID</th>
	        <th  style="text-align:center;width:13%;"> 开始地点</th>
	        <th  style="text-align:center;width:13%;">结束地点</th>
	        <th  style="text-align:center;width:10%;">创建日期</th>
	        <th  style="text-align:center;width:10%;"> 取单时间</th>
	        <th  style="text-align:center;width:8%;"> 发单人ID</th>
	        <th  style="text-align:center;width:8%;">发单人名称</th>
	        <th  style="text-align:center;width:7%;">发单人手机</th> 
	         <th  style="text-align:center;width:8%;">收单人ID</th> 
	        <th  style="text-align:center;width:7%;">订单费用</th>
	        <th  style="text-align:center;width:7%;">订单状态</th> 
	        <th  style="text-align:center;width:11%;">设为异常</th> 
 
	    </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${pagebean.data}" var="orders">
	   
	    <tr style="cursor:hand"  title="订单备注:${orders.orderRemark }"  >
	        <td  style="text-align:center;">${orders.orderId }</td> 
	        <td  style="text-align:center;">${orders.startPlace }</td>
	        <td style="text-align:center;">${orders.endPlace }</td>
	        <td style="text-align:center;"><fmt:formatDate value="${orders.createDate }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <td style="text-align:center;"><fmt:formatDate value="${orders.takeOrderDate }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <td style="text-align:center;">${orders.sendUserId }</td>	
	        <td style="text-align:center;">${orders.sendUserNm }</td>	
         	<td style="text-align:center;">${orders.sendUserMobile }</td>
            <td style="text-align:center;">${orders.takeUserId }</td>
            <td style="text-align:center;">${orders.orderFee }</td>
			<c:if test="${orders.orderStatCode==0 }"> <td style="text-align:center;">发单</td></c:if>        
			<c:if test="${orders.orderStatCode==1 }"> <td style="text-align:center;">已抢</td></c:if>   
			<c:if test="${orders.orderStatCode==2 }"> <td style="text-align:center;">交付</td></c:if>   
			<c:if test="${orders.orderStatCode==3 }"> <td style="text-align:center;">支付</td></c:if>   
			<c:if test="${orders.orderStatCode==4 }"> <td style="text-align:center;">完成</td></c:if>  
			<c:if test="${orders.orderStatCode==5 }"> <td style="text-align:center;color:red">异常</td></c:if>    
			<form>
			<input id="sb" type="hidden" name="pageNum" value="${pagebean.pageNum}"></input>
			<td style="text-align:center;"><c:if test="${orders.orderStatCode!=5 }"><img src="${pageContext.request.contextPath}/images/exception.png"  width="35px" height="30px" data-toggle="modal" data-target="#myModal" onclick="orderOp(this,${orders.orderId },${orders.orderStatCode })"/></c:if></td>
	   		</form>
	    </tr>   
	   
     	</c:forEach>
	    </tbody>
	</table>
	</div>
	
 <div class="black2" id="page"><p>
 			<c:if test="${pagebean.pageNum == 1}">
        		<span class="disabled">[首页]</span>
        	</c:if>
        	
        	<c:if test="${pagebean.pageNum > 1}">
 			<span><a href="${pageContext.request.contextPath}/admin/order/findallorders.do?pageNum=1&sortType=${sortType}&orderType=${orderType}&sendUserMobile=${sendUserMobile}">首页  </a></span>
			</c:if>
			
			<c:if test="${pagebean.pageNum == 1}">
        		<span class="disabled">[上一页]</span>
        	</c:if>
        
        	<c:if test="${pagebean.pageNum > 1}">	
            	 <span><a href="${pageContext.request.contextPath}/admin/order/findallorders.do?pageNum=${pagebean.pageNum-1}&sortType=${sortType}&orderType=${orderType}&sendUserMobile=${sendUserMobile}">[上一页]</a></span>
        	</c:if>
        	
        	<%--动态显示条 --%>
        	<c:forEach begin="${pagebean.start }" end="${pagebean.end }" var="num">
			     <c:choose>
				   <c:when test="${pagebean.pageNum != num}">   
				   		<span><a href="${pageContext.request.contextPath}/admin/order/findallorders.do?pageNum=${num}&sortType=${sortType}&orderType=${orderType}&sendUserMobile=${sendUserMobile}" >${num}</a></span>			   
				   </c:when>	   
				   <c:otherwise>  
					<span class="current">${num}</span>
				   </c:otherwise>
				</c:choose>
        	</c:forEach>
        	
             <c:if test="${pagebean.pageNum >= pagebean.totalPage}">
           		<span class="disabled">[下一页]</span>   
            </c:if>
        	
        	<c:if test="${pagebean.pageNum < pagebean.totalPage}">
           		<a href="${pageContext.request.contextPath}/admin/order/findallorders.do?pageNum=${pagebean.pageNum + 1 }&sortType=${sortType}&orderType=${orderType}&sendUserMobile=${sendUserMobile}">[下一页]</a>	
            </c:if>
            
            <c:if test="${pagebean.pageNum == pagebean.totalPage || pagebean.totalPage==0}">
        		<span class="disabled">[尾页]</span>
        	</c:if>
        	<c:if test="${pagebean.pageNum < pagebean.totalPage}">
        		<a href="${pageContext.request.contextPath}/admin/order/findallorders.do?pageNum=${pagebean.totalPage }&sortType=${sortType}&orderType=${orderType}&sendUserMobile=${sendUserMobile}" >[尾页]</a>
        	</c:if>
            
    	</p></div> 
  </div>
 
</body>
</html>