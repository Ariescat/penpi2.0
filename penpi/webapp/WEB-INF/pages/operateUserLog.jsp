<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/base.jsp"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css" media="all" />
 <script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
   <script language="JavaScript" src="${pageContext.request.contextPath}/js/base-utils.js"></script>
     <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>  
  <script src="https://cdn.bootcss.com/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var pageNum = 1;
function SelectAllUserLogAndPage(num){
	pageNum = num;
	$.ajax({  
        type:'post',  
        url:'${pageContext.request.contextPath}/admin/oplog/findOpUserLogByPage.do',  
        scriptCharset:'utf-8',
        data:{pageNum,pageNum},  
        success:function(data){//返回json结果  
        	 var jsonObj = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object   
        	 var tableResultStr =  "";
        	 pageSize = jsonObj.pageSize;
        	 totalPage = jsonObj.totalPage;
        	 start = jsonObj.start;
        	 end = jsonObj.end;
        	 
        	 $("#opUserRecordInformation tr:not(:first)").html("");

        	 $.each(jsonObj.data,function(index, dataItem){
        	 	 var s = jsonObj.data[index].opDate;
        		 var date = new Date(s).Format("yyyy-MM-dd hh:mm:ss");
        			 //alert(data);
        			 //alert(JSON.stringify(jsonObj.data[index]));
        			 
        		 tableResultStr += "<tr style=\"cursor:hand\"><td style=\"text-align:center;\">"+jsonObj.data[index].opContent+"</td>";
        		 tableResultStr += "<td style=\"text-align:center;\">" + date +"</td>";
        		 tableResultStr += "<td style=\"text-align:center;\">" + jsonObj.data[index].opUserLoginId +"</td>";
        		 
        	    }); 	
        	 //alert(tableResultStr);
        	 $("#opUserRecordInformation").append(tableResultStr);   	 
        },  
        complete:function(){ //生成分页条
        	getSelectAllOpUserLogPageBar();
        }
    });  
}
function btnSelect(num){
	 
	SelectOpUserLogByOper(num);
}
 
 function SelectOpUserLogByOper(num){
	 pageNum = num;
	 //alert(pageNum);
	 var userLogOper = $('#userLogOper').val() ;
	$.ajax({  
        type:'post', 
        url:'${pageContext.request.contextPath}/admin/oplog/findOpUserLogByOper.do',  
        scriptCharset:'utf-8',
       
        data:{"userLogOper":userLogOper,"pageNum":pageNum},  
        success:function(data){//返回json结果  
        	//refreshTable(data); 	    
        	//alert(data);
        	 var jsonObj = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object   
        	 var tableResultStr =  "";
        	 pageSize = jsonObj.pageSize;
        	 totalPage = jsonObj.totalPage;
        	 start = jsonObj.start;
        	 end = jsonObj.end;
        	 $("#opUserRecordInformation tr:not(:first)").html("");
        	 $.each(jsonObj.data,function(index, dataItem){
        	 	 var s = jsonObj.data[index].opDate;
        		 var date = new Date(s).Format("yyyy-MM-dd hh:mm:ss");
        			 //alert(data);
        			 //alert(JSON.stringify(jsonObj.data[index]));
        			 
        		 tableResultStr += "<tr style=\"cursor:hand\"><td style=\"text-align:center;\">"+jsonObj.data[index].opContent+"</td>";
        		 tableResultStr += "<td style=\"text-align:center;\">" + date +"</td>";
        		 tableResultStr += "<td style=\"text-align:center;\">" + jsonObj.data[index].opUserLoginId +"</td>";
        		 
        	    }); 	
        	 //alert(tableResultStr);
        	 $("#opUserRecordInformation").append(tableResultStr); 
        },  
        complete:function(){ //生成分页条
        	getSelectOpUserLogByOperPageBar();    	
        }
    	
    });
 }
</script>

<title>Insert title here</title>
</head>
<body>

<div class="panel-body" style="padding-bottom:0px;width:1200px;height:50px;padding-left:50px;">
	<div class="panel panel-default"  >
		<div class="panel-heading" >用户管理 &nbsp; &nbsp;>> &nbsp; &nbsp; 日志查看</div>
	</div>
	
	<form id="formSearch" class="form-horizontal"  > 
	<div style="float:right;padding-top:50px;">  
	 
	 <div style="float:right;">
      <button type="button"   class="btn btn-primary" id="buttonSelect" onclick="btnSelect(1)">查询</button>     	              
      </div>
      <div style="float:right;padding-right:40px;">
      	<input type="text" placeholder="操作人" class="form-control" id="userLogOper" name="userLogOper">
      </div>
    </div>
    </form>
    
    <div style="padding-top:120px;">
	<table id="opUserRecordInformation" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	        <th  style="text-align:center;width:50%"> 操作内容</th>
	        <th  style="text-align:center;width:25%;">操作时间</th>
	        <th  style="text-align:center;width:25%;">操作人</th>    
	    </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${pagebean.data}" var="orLog">
	   
	    <tr style="cursor:hand" >
	        <td style="text-align:center;">${orLog.opContent }</td>
	        <td style="text-align:center;"><fmt:formatDate value="${orLog.opDate }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <td style="text-align:center;">${orLog.opUserLoginId }</td>	       
	    </tr>   
	   
     	</c:forEach>
	    </tbody>
	</table>
	</div>
	
 <div class="black2" id="page"><p>
 			<span><a href="javascript:void(0)" onclick="SelectAllUserLogAndPage(1)">首页  </a></span>
			<c:if test="${pagebean.pageNum == 1}">
        		<span class="disabled">[上一页]</span>
        	</c:if>
        
        	<c:if test="${pagebean.pageNum > 1}">	
            	 <span><a href="javascript:void(0)" onclick="SelectAllUserLogAndPage(${pagebean.pageNum}-1)">[上一页]</a></span>
        	</c:if>
        	
        	<%--动态显示条 --%>
        	<c:forEach begin="${pagebean.start }" end="${pagebean.end }" var="num">
			     <c:choose>
				   <c:when test="${pagebean.pageNum != num}">   
				   		<span><a href="javascript:void(0)" onclick="SelectAllUserLogAndPage(${num})">${num}</a></span>			   
				   </c:when>	   
				   <c:otherwise>  
					<span class="current">${num}</span>
				   </c:otherwise>
				</c:choose>
        	</c:forEach>
        	
             <c:if test="${pagebean.pageNum == pagebean.totalPage}">
           		<span class="disabled">[下一页]</span>   
            </c:if>
        	
        	<c:if test="${pagebean.pageNum < pagebean.totalPage}">
           		<a href="javascript:void(0)" onclick="SelectAllUserLogAndPage(${pagebean.pageNum + 1 })">[下一页]</a>	
            </c:if>
            
            <a href="javascript:void(0)" onclick="SelectAllUserLogAndPage(${pagebean.totalPage })">[尾页]</a>
    	</p></div> 
  </div>
</body>
</html>