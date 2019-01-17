<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ include file="/base.jsp"%>
        <%@ taglib prefix="el" uri="/WEB-INF/tlds/el-common.tld"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
var excepOrder;
var thistr;
function ExcepDetail(obj,Exceporder){
	excepOrder = Exceporder;
	 thistr = $(obj).parent().parent(); 
	$.ajax({  
		   async:false,
	       type:'post', 
	       url:'${pageContext.request.contextPath}/admin/order/findAnOrder.do',  
	       scriptCharset:'utf-8',
	       data:{"orderId":excepOrder.orderId},
	       success:function(data){//返回json结果  
	    	   
	    	   var jsonObj = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object   
	  			 
	    	   $('#ModelSendUserNm').val(jsonObj.sendUserNm);    
	    	   $('#ModelSendUserMobile').val(jsonObj.sendUserMobile); 
	    	   $('#ModelTakeUserNm').val(jsonObj.takeUserNm); 
	    	   $('#ModelTakeUserMobile').val(jsonObj.takeUserMobile); 
	    	   $('#ModelStartPlace').val(jsonObj.startPlace); 
	    	   $('#ModelEndPlace').val(jsonObj.endPlace); 
	       }
	     
	   	
	   });
	 $('#ModelExceptionReason').val(excepOrder.exceptionReason);
}
function repair(){
	$.ajax({  
		   async:false,
	       type:'post', 
	       url:'${pageContext.request.contextPath}/admin/orderexcep/repairOrder.do',  
	       scriptCharset:'utf-8',
	       data:{"orderId":excepOrder.orderId},
	       success:function(data){//返回json结果  
	    	   var jsonObj = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object 
	    	   var s = jsonObj.finishFixTime;
	  		   var date = new Date(s).Format("yyyy-MM-dd hh:mm:ss");
	    	   alert("修复成功");
	    	   thistr.find("td").eq(3).text("已修复");
	    	   thistr.find("td").eq(4).text(date);
	       }
	   });
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="panel-body" style="padding-bottom:0px;width:1200px;height:50px;padding-left:50px;">
	<div class="panel panel-default"  >
		<div class="panel-heading" >订单管理 &nbsp; &nbsp;>> &nbsp; &nbsp; 异常订单</div>
	</div>

    <div style="padding-top:100px;">
	<table id="orderExcepRecordsinfo" style="font-size:13px;" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	    	<th  style="text-align:center;width:20%;"> 异常订单ID</th>
	        <th  style="text-align:center;width:20%;"> 异常原因</th>
	        <th  style="text-align:center;width:20%;">异常时间</th>
	        <th  style="text-align:center;width:20%;">状态</th>
	        <th  style="text-align:center;width:20%;"> 修复时间</th>
	 
	         
 
	    </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${pagebean.data}" var="orderExcepRecords">
	   
	    	<tr style="cursor:hand" data-toggle="modal" data-target="#detailModel"  onclick='ExcepDetail(this,${el:toJsonString(orderExcepRecords)})' >
	        <td  style="text-align:center;">${orderExcepRecords.orderId }</td> 
	        <td  style="text-align:center;">${orderExcepRecords.exceptionReason }</td>
	        <td style="text-align:center;"><fmt:formatDate value="${orderExcepRecords.exceptionTime }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <c:if test="${orderExcepRecords.ifFixException=='N' }"> <td style="text-align:center;">未修复</td></c:if>
	        <c:if test="${orderExcepRecords.ifFixException=='Y' }"> <td style="text-align:center;">已修复</td></c:if>
	        
	        <td style="text-align:center;"><fmt:formatDate value="${orderExcepRecords.finishFixTime }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	     
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
 			<span><a href="${pageContext.request.contextPath}/admin/orderexcep/findallordersExcepRecord.do?pageNum=1">首页  </a></span>
			</c:if>
			
			<c:if test="${pagebean.pageNum == 1}">
        		<span class="disabled">[上一页]</span>
        	</c:if>
        
        	<c:if test="${pagebean.pageNum > 1}">	
            	 <span><a href="${pageContext.request.contextPath}/admin/orderexcep/findallordersExcepRecord.do?pageNum=${pagebean.pageNum-1}">[上一页]</a></span>
        	</c:if>
        	
        	<%--动态显示条 --%>
        	<c:forEach begin="${pagebean.start }" end="${pagebean.end }" var="num">
			     <c:choose>
				   <c:when test="${pagebean.pageNum != num}">   
				   		<span><a href="${pageContext.request.contextPath}/admin/orderexcep/findallordersExcepRecord.do?pageNum=${num}" >${num}</a></span>			   
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
           		<a href="${pageContext.request.contextPath}/admin/orderexcep/findallordersExcepRecord.do?pageNum=${pagebean.pageNum + 1 }">[下一页]</a>	
            </c:if>
            
            <c:if test="${pagebean.pageNum == pagebean.totalPage || pagebean.totalPage==0}">
        		<span class="disabled">[尾页]</span>
        	</c:if>
        	<c:if test="${pagebean.pageNum < pagebean.totalPage}">
        		<a href="${pageContext.request.contextPath}/admin/orderexcep/findallordersExcepRecord.do?pageNum=${pagebean.totalPage }" >[尾页]</a>
        	</c:if>
            
    	</p></div> 
  </div>

 <!-- 模态框（Modal） -->
<div class="modal fade" id="detailModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">订单详情</h4>
            </div>
            <div class="modal-body">
        
		   		   <form class="form-inline" role="form">
				      	<div class="form-group">
				        	<label class="form-label">发单人</label>
				        	<input type="text" class="form-control" id="ModelSendUserNm" name="ModelSendUserNm">
				      	</div>
				      	
				      	<div class="form-group">
				        	<label class="form-label">发单人手机</label>
				        	<input type="text" class="form-control" id="ModelSendUserMobile" name="ModelSendUserMobile">
				      	</div>
				      	
				      	<div class="form-group"style="padding-top:20px;">
				        	<label class="form-label">收单人</label>
				        	<input type="text" class="form-control" id="ModelTakeUserNm" name="ModelTakeUserNm">
				      	</div>
				      	
				      	<div class="form-group"style="padding-top:20px;" >
				        	<label class="form-label">收单人手机</label>
				        	<input type="text" class="form-control" id="ModelTakeUserMobile" name="ModelTakeUserMobile">
				      	</div>
				      			   		   
				      	<div class="form-group" style="padding-top:20px;">
				        	<label class="form-label">开始地点</label>
				        	<input type="text" class="form-control" style="width:470px;" id="ModelStartPlace" name="ModelStartPlace">
				      	</div>
				      	<br>

				      	<div class="form-group" style="padding-top:20px;">
				        	<label class="form-label">结束地点</label>
				        	<input type="text" class="form-control" style="width:470px;" id="ModelEndPlace" name="ModelEndPlace">
				      	</div>
				      	
				      	<div class="form-group" style="padding-top:20px;">
				        	<label class="form-label">异常原因</label>
				        	 <textarea class="form-control"id="ModelExceptionReason" name="ModelExceptionReason" rows="3"></textarea>
				        	 
 
				      	</div>
				      	 		
				      
				        	 
				        	 
					</form>
               	 
            </div> 
            <div class="modal-footer"  >
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="repair()">修复</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>





</body>
</html>