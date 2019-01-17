<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
      <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function submit(){
	var form = document.getElementById("submit");
	
	
	form.submit();
}
function msgType(){
	var msgType = document.getElementById("msgType");
	//alert(msgType.value);
	var form = document.getElementById("formMsgType");
	$('#messageType').val(msgType.value);
 
	form.submit();
	 
}
</script>
</head>
<body>
<form id="submit" action="${pageContext.request.contextPath}/admin/msg/publishMsg.do">
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">消息发布</h4>
            </div>
            <div class="modal-body">		
				<textarea class="form-control" rows="5"  placeholder="填写消息内容" id="msg" name="msg"></textarea>
				 
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submit()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 
</form>

	<div class="panel-body"
		style="padding-bottom: 0px; width: 1200px; height: 50px; padding-left: 50px; ">
		<div class="panel panel-default">
			<div class="panel-heading">消息管理 &nbsp; &nbsp;>> &nbsp; &nbsp;
				消息列表</div>
		</div>
		
    	<div class="form-group" float:left;"  >
       	<label class="form-label">消息类型</label> &nbsp; &nbsp; 
       	<select  class="selectpicker" id="msgType" name="msgType" data-width="auto" onchange="msgType()">
			<option value="-1" ${msgType == "-1" ? "selected" : ""}  >不限</option>
			 <option value="0"  ${msgType == "0" ? "selected" : ""}>系统消息</option>
			  <option value="1"  ${msgType == "1" ? "selected" : ""}>活动消息</option>
		</select>
		</div>
		<form action="${pageContext.request.contextPath}/admin/msg/findByMsgType.do" id="formMsgType">
		<input type="hidden" value="-1" id="messageType" name="messageType"/>
		</form>

  		<div id="toolbar" class="btn-group" style="padding-top:50px;">
              <button id="btnPublish" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>发布通知
        </div>
        <div style="float:right;padding-top:50px;padding-left:20px;">
      			<button type="button"   class="btn btn-primary" id="buttonSelect">查询</button>     	              
      		</div>
			<div style="float:right;padding-top:50px;">
      			<input type="text" placeholder="消息内容"  value="" id="msgCont" name="msgCont"  class="form-control" >
     		</div>	
	 
	 	<div style="padding-top:20px;">
	 	
		<table id="msginfo" style="font-size:13px;" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	    	<th  style="text-align:center;width:10%;"> 通知消息ID</th>
	        <th  style="text-align:center;width:25%;"> 接收人</th>
	        <th  style="text-align:center;width:40%;">消息内容</th>
	        <th  style="text-align:center;width:15%;">消息时间</th>
	        <th  style="text-align:center;width:10%;">消息类型</th>
	    </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${noticeMsgList}" var="noticeMsg">
	   
	    <tr style="cursor:hand"  > 
	        <td  style="text-align:center;">${noticeMsg.noticeMsgId }</td> 
	        <td  style="text-align:center;">${noticeMsg.receiverUserId }</td>
	        <td style="text-align:center;">${noticeMsg.msgCont }</td>
	        <td style="text-align:center;"><fmt:formatDate value="${noticeMsg.msgTime }" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <td style="text-align:center;">${noticeMsg.sysNoticeTypeCode==0?"系统消息":"活动消息"}</td>
 
	         
	   		 
	    </tr>   
	   
     	</c:forEach>
	    </tbody>
	</table>
	</div>
   
    <div class="black2" id="page"><p>
 			 
      	<span class="disabled">[首页]</span>
   		<span class="disabled">[上一页]</span>
   
		<span class="current">1</span>
		<span class="disabled">[下一页]</span>   
		<span class="disabled">[尾页]</span>
    </p></div> 
  
  			
  			 
			
	</div>
</body>
</html>