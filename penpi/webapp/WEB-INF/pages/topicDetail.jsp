<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 .followImage{ 
      
        width:40px; 
        height:40px; 
        border-radius:200px; 
    }
</style>
<script type="text/javascript">
function deleteNote(noteId){
	
	$('#noteId').val(noteId);

}
function deleteNote1(){
	$('#deleteReason').val($('#delReason').val());
	var form = document.getElementById("deleteNote");
	form.submit();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<title>Insert title here</title>
</head>
<body>
	<div class="panel-body"
		style="padding-bottom: 0px; width: 1200px; height: 50px; padding-left: 50px; ">
		<div class="panel panel-default">
			<div class="panel-heading">话题管理 &nbsp; &nbsp;>> &nbsp; &nbsp;
				话题详情</div>
		</div>

			<div style="border:1px black solid;margin-left:200px;height:180px;width:65%;">
				<div style="margin-left:30px;height:100px;width:60%;margin-top:50px;float:left;">
					<span style="font-size:25px;">${topicDetail.topicNm }</span><br>
					<span style="font-size:15px;color:gray;">${topicDetail.followerAmount}位${topicDetail.followerNm }</span><br><br>
					<span style="font-size:17px;">${topicDetail.topicDescr}</span>
					
				</div>
				
				<div style="border:1px black solid;height:150px;width:150px;float:left;margin-left:50px;margin-top:20px;"><img src="${topicDetail.topicHeadPictStr}"  ></div>
			</div>
			
			<div style="border:1px black solid;margin-left:200px;height:80px;width:65%;line-height:80px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关注者  
				<c:forEach items="${topicDetail.followers}" var="followers"> 
	  				 <img src="${followers.userHeadPictStr}" class="followImage" >
	  			</c:forEach>
  			</div>
  			
  			<div style="border:1px black solid;height:180px;line-height:150px;width:65%;margin-left:200px;">
  			<form role="form">
  				<div class="form-group" style="padding-left:40px;padding-top:20px;width:95%;">
    				<textarea placeholder="编写规则" class="form-control" rows="4"></textarea>
    				<button style="float:right;margin-top:20px;" type="button"   class="btn btn-primary" id="buttonRule">提交</button>    
 				 </div>
			</form> 
  			</div>
  			
  			<form action="${pageContext.request.contextPath}/admin/note/findNoteByKeyWord.do">
  			<div style="height:50px;line-height:50px;width:65%;margin-left:200px;margin-top:20px;">
  				<div style="float:right;">
	      			<button type="submit" class="btn btn-primary" id="buttonSelect">查询</button>     	              
	      		</div>
	      		<div style="float:right;">
	      			<input type="text" placeholder="帖子关键字"  value="${noteKeyWord}" id="noteKeyWord" name="noteKeyWord"  class="form-control" >
	     		</div>
  			</div>
  			</form>
  			
  			<c:forEach items="${topicDetail.notes}" var="notes">
  			<div style="border:1px black solid;height:180px;width:65%;margin-left:200px;margin-top:20px;">
  				<span><img src="${notes.userHeadPictStr}" class="followImage" ></span>
  				<span>${notes.sendNoteUserNm}</span>
  				 
  				<span style="float:right;padding-right:20px;padding-top:10px;"><fmt:formatDate value="${notes.createDate}" pattern="yyyy-MM-dd  HH:mm:ss"/></span><br><br>
  				<a href="${pageContext.request.contextPath}/admin/note/noteDetail.do?noteId=${notes.noteId}"><span>${notes.noteCont}</span></a>
  				<c:forEach items="${notes.noteFiles}" var="noteFiles">  
	  				<img src="${noteFiles.fileInfStr}" width="50px" height="50px"  >
	  			</c:forEach> 	<br>
  				<span style="float:right;margin-top:40px;cursor:hand"><img src="${pageContext.request.contextPath}/images/delete.png" width="25px" height="25px" data-toggle="modal" data-target="#myModal"  onclick="deleteNote(${notes.noteId })"></span>
  				<span style="float:left;margin-top:40px;"><img src="${pageContext.request.contextPath}/images/comment.png" width="25px" height="25px" >评论数:${notes.commentAmount}</span>
  				<span style="float:left;padding-left:20px;margin-top:40px;"><img src="${pageContext.request.contextPath}/images/heat.png" width="25px" height="25px" >热度:${notes.noteHeat}</span>
 
  			</div>
  			</c:forEach>
  			 <div class="black2" id="page"><p>
 			 
      	<span class="disabled">[首页]</span>
   		<span class="disabled">[上一页]</span>
   
		<span class="current">1</span>
		<span class="disabled">[下一页]</span>   
		<span class="disabled">[尾页]</span>
    </p></div>
    <form id="deleteNote" action="${pageContext.request.contextPath}/admin/note/deleteNote.do">
    	<input type="hidden" id="noteId" name="noteId"/>
    	<input type="hidden" id="deleteReason" name="deleteReason"/>
    </form>
		
	</div>
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除帖子</h4>
            </div>
            <div class="modal-body">		
				<input type="text" placeholder="删除帖子原因" class="form-control" id="delReason" name="delReason">
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteNote1()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 
</body>
 
</html>