<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function submitform(){
	var topicCategory = document.getElementById("topicCategory");
	var formTopicCategory = document.getElementById("formTopicCategory");
	$('#topicCategoryId').val(topicCategory.value);
	formTopicCategory.submit(); 
	 
}

function topicDetail(topicId){
	alert(topicId);
	  
}
var iTopicId;
var thistr;
var itopicCategoryId;
function topicOp(obj,topicId,topicCategoryId){
	itopicCategoryId = topicCategoryId
	iTopicId = topicId;
	thistr = $(obj).parent().parent();
}
function modifyTopic(){
	 
	if(itopicCategoryId != $('#ModelTopicCategory').val()){
	 $.ajax({  
	       type:'post', 
	       url:'${pageContext.request.contextPath}/admin/topic/modifyTopic.do',  
	       scriptCharset:'utf-8',
	       data:{"topicId":iTopicId,"topicCategory":$('#ModelTopicCategory').val()},  
	       success:function(data){//返回json结果  
	    	   alert("修改话题类别成功");
	    	   thistr.remove();
	    	  /*  thistr.find("td").eq(10).css({color:"red"}) 
	    	   thistr.find("td").eq(10).text("异常");
	    	   thistr.find("td").eq(11).text(""); */
	       }
	     
	   	
	   }); 
	}
}
function find(){
	var findForm = document.getElementById("findForm");
	 
	findForm.submit(); 
	 
}
</script>
</head>
<body>
	<div class="panel-body"
		style="padding-bottom: 0px; width: 1200px; height: 50px; padding-left: 50px;">
		<div class="panel panel-default">
			<div class="panel-heading">话题管理 &nbsp; &nbsp;>> &nbsp; &nbsp;
				话题列表</div>
		</div>

		<form id="formTopicCategory" action="${pageContext.request.contextPath}/admin/topic/topicMain.do">
			<input type="hidden" name="pageNum" value=1></input>
			<input type="hidden" id="topicCategoryId" name="topicCategoryId" value=${topicCategory.topicCategoryId }></input>
			<div class="form-group" style="padding-top: 20px; float: left;">
				<label class="form-label">话题种类</label> &nbsp; &nbsp;
				 <select
					class="selectpicker" id="topicCategory" name="topicCategory"
					data-width="auto" onchange="submitform()">
					
					<c:forEach items="${topicCategoryList}" var="topicCategory">
					<option value=${topicCategory.topicCategoryId }
						${topicCategorySelected == topicCategory.topicCategoryId ? "selected" : ""}>${topicCategory.topicCategoryNm }</option>
				 </c:forEach>
				</select>
			</div>
		
		</form>
			<form id="findForm" action="${pageContext.request.contextPath}/admin/topic/findTopicByKeyWord.do">
			 
				<div style="float:right;padding-top:20px;padding-left:20px;">
				<input type="hidden" id="FindtopicCategoryId" name="FindtopicCategoryId" value=${topicCategorySelected }></input>
	      			<button type="button"   class="btn btn-primary" id="buttonSelect" onclick='find()'>查询</button>     	              
	      		</div>
	      		<div style="float:right;padding-top:20px;">
	      			<input type="text" placeholder="话题名称"  value="${keyWord}" id="keyWord" name="keyWord"  class="form-control" >
	     		</div>
     		</form>	
		
		<div style="padding-top:120px;">
		<table id="topicinfo" style="font-size:13px;" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	    	<th  style="text-align:center;width:10%;"> 话题ID</th>
	        <th  style="text-align:center;width:25%;"> 话题名称</th>
	        <th  style="text-align:center;width:10%;">关注者称号</th>
	        <th  style="text-align:center;width:10%;">创建日期</th>
	        <th  style="text-align:center;width:10%;">话题头像</th>
	         <th  style="text-align:center;width:5%;">更改话题</th>
	        
	     
 
	    </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${topicExList.data}" var="topicEx">
	   
	    <tr style="cursor:hand"  > 
	        <td  style="text-align:center;"onclick="location.href='${pageContext.request.contextPath}/admin/topic/topicDetail.do?topicId=${topicEx.topicId }';">${topicEx.topicId }</td> 
	        <td  style="text-align:center;"onclick="location.href='${pageContext.request.contextPath}/admin/topic/topicDetail.do?topicId=${topicEx.topicId }';">${topicEx.topicNm }</td>
	        <td style="text-align:center;"onclick="location.href='${pageContext.request.contextPath}/admin/topic/topicDetail.do?topicId=${topicEx.topicId }';">${topicEx.followerNm }</td>
	        <td style="text-align:center;"onclick="location.href='${pageContext.request.contextPath}/admin/topic/topicDetail.do?topicId=${topicEx.topicId }';"><fmt:formatDate value="${topicEx.createDate }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <td style="text-align:center;"onclick="location.href='${pageContext.request.contextPath}/admin/topic/topicDetail.do?topicId=${topicEx.topicId }';"><img src="${topicEx.topicHeadPictStr}" width="50px" height="50px"></td>
	         <td style="text-align:center;"><img src="${pageContext.request.contextPath}/images/modify.png" width="20px" height="20px" data-toggle="modal" data-target="#myModal" onclick="topicOp(this,${topicEx.topicId },${topicEx.topicCategoryId })"></td>
	         
	   		 
	    </tr>   
	   
     	</c:forEach>
	    </tbody>
	</table>
	</div>

 <div class="black2" id="page"><p>
 			<c:if test="${topicExList.pageNum == 1}">
        		<span class="disabled">[首页]</span>
        	</c:if>
        	 
        	<c:if test="${topicExList.pageNum > 1}">
 			<span><a href="${pageContext.request.contextPath}/admin/topic/topicMain.do?pageNum=1&topicCategoryId=${topicCategorySelected}">首页  </a></span>
			</c:if>
			
			<c:if test="${topicExList.pageNum == 1}">
        		<span class="disabled">[上一页]</span>
        	</c:if>
        
        	<c:if test="${topicExList.pageNum > 1}">	
            	 <span><a href="${pageContext.request.contextPath}/admin/topic/topicMain.do?pageNum=${topicExList.pageNum-1}&topicCategoryId=${topicCategorySelected}">[上一页]</a></span>
        	</c:if>		
        	
        	<%--动态显示条 --%>
        	<c:forEach begin="${topicExList.start }" end="${topicExList.end }" var="num">
			     <c:choose>
				   <c:when test="${topicExList.pageNum != num}">   
				   		<span><a href="${pageContext.request.contextPath}/admin/topic/topicMain.do?pageNum=${num}&topicCategoryId=${topicCategorySelected}" >${num}</a></span>			   
				   </c:when>	   
				   <c:otherwise>  
					<span class="current">${num}</span>
				   </c:otherwise>
				</c:choose>
        	</c:forEach>
        	
             <c:if test="${topicExList.pageNum >= topicExList.totalPage}">
           		<span class="disabled">[下一页]</span>   
            </c:if>
        	
        	<c:if test="${topicExList.pageNum < topicExList.totalPage}">
           		<a href="${pageContext.request.contextPath}/admin/topic/topicMain.do?pageNum=${topicExList.pageNum + 1 }&topicCategoryId=${topicCategorySelected}">[下一页]</a>	
            </c:if>
            
            <c:if test="${topicExList.pageNum == topicExList.totalPage || topicExList.totalPage==0}">
        		<span class="disabled">[尾页]</span>
        	</c:if>
        	<c:if test="${topicExList.pageNum < topicExList.totalPage}">
        		<a href="${pageContext.request.contextPath}/admin/topic/topicMain.do?pageNum=${topicExList.totalPage }&topicCategoryId=${topicCategorySelected}" >[尾页]</a>
        	</c:if>
            
    	</p></div> 




	</div>




<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改话题类别<label id="orderId"></label></h4>
            </div>
            <div class="modal-body">		
				  <select
					class="selectpicker" id="ModelTopicCategory" name="ModelTopicCategory"
					data-width="auto">
					
					<c:forEach items="${topicCategoryList}" var="topicCategory">
					<option value=${topicCategory.topicCategoryId }
						${topicCategorySelected == topicCategory.topicCategoryId ? "selected" : ""}>${topicCategory.topicCategoryNm }</option>
				 </c:forEach>
				</select>
				
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="modifyTopic()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 

   


</body>
</html>