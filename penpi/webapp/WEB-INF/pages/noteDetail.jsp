<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 .userImage{      
        width:40px; 
        height:40px; 
        border-radius:200px; 
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function deleteNoteComment(noteCommentId){
	/* alert(noteCommentId); */
	$('#noteCommentId').val(noteCommentId);
}
function deleteNoteComment1(){
	$('#deleteReason').val($('#delReason').val());
	var form = document.getElementById("deleteNoteComment");
	form.submit();
}
</script>
</head>
<body>
<div class="panel-body"
		style="padding-bottom: 0px; width: 1200px; height: 50px; padding-left: 50px; ">
		<div class="panel panel-default">
			<div class="panel-heading">话题管理 &nbsp; &nbsp;>> &nbsp; &nbsp;
				话题详情&nbsp; &nbsp;>> &nbsp; &nbsp;帖子详情</div>
		</div>

				<c:forEach items="${topicDetail.notes}" var="notes">
				<c:if test="${notes.noteId ==noteId }">
				<div style="border:1px black solid;height:300px;width:65%;margin-left:200px;margin-top:20px;">
  				<span><img src="${notes.userHeadPictStr}" class="userImage" ></span>
  				<span>${notes.sendNoteUserNm}</span>
  				 
  				<span style="float:right;padding-right:20px;padding-top:10px;"><fmt:formatDate value="${notes.createDate}" pattern="yyyy-MM-dd  HH:mm:ss"/></span><br><br>
  				<span>${notes.noteCont}</span><br>
  				<c:forEach items="${notes.noteFiles}" var="noteFiles">  
	  				<img src="${noteFiles.fileInfStr}" width="150px" height="150px"  >
	  			</c:forEach> 	<br>
  			 
  				<span style="float:left;margin-top:40px;"><img src="${pageContext.request.contextPath}/images/comment.png" width="25px" height="25px" >评论数:${notes.commentAmount}</span>
  				<span style="float:left;padding-left:20px;margin-top:40px;"><img src="${pageContext.request.contextPath}/images/heat.png" width="25px" height="25px" >热度:${notes.noteHeat}</span>
 
  				</div>
					 
				</c:if>
				</c:forEach>
			
		 
			<div style="border:1px black solid;width:65%;height:60px;margin-left:200px;"></div>
			<c:forEach items="${noteDetail.noteCommentExs}" var="noteCommentExs">
			<div style="border:1px black solid;height:150px;width:65%;margin-left:200px;">
				<img src="${noteCommentExs.userHeadPictStr}" class="userImage">${noteCommentExs.userNm }
				<span style="float:right;padding-right:20px;"><fmt:formatDate value="${noteCommentExs.createDate}" pattern="yyyy-MM-dd  HH:mm:ss"/></span><br>
				<span style="padding-left:40px;">${ noteCommentExs.cont}</span><br>
				
				<c:forEach items="${noteCommentExs.commentReplies}" var="commentReplies">
				<div style="margin-left:40px;background:	#FCFCFC;">
					<c:if test="${!empty commentReplies.atUserNm}">
					<span style="color:blue">${commentReplies.commentUserNm}</span>回复<span style="color:blue">@${commentReplies.atUserNm }</span>:${commentReplies.cont }<br>
					</c:if>
					<c:if test="${empty commentReplies.atUserNm}">
					<span style="color:blue">${commentReplies.commentUserNm}</span>:${commentReplies.cont }<br>
					</c:if>
				</div>
				</c:forEach>
				<span style="float:right;cursor:hand"><img src="${pageContext.request.contextPath}/images/delete.png" width="25px" height="25px" data-toggle="modal" data-target="#myModal"  onclick="deleteNoteComment(${noteCommentExs.noteCommentId })"></span>
			</div>
			</c:forEach>
  				
	</div>
	   <form id="deleteNoteComment" action="${pageContext.request.contextPath}/admin/note/deleteNoteComment.do">
    	<input type="hidden" id="noteCommentId" name="noteCommentId"/>
    	<input type="hidden" id="deleteReason" name="deleteReason"/>
    </form>
    	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除评论</h4>
            </div>
            <div class="modal-body">		
				<input type="text" placeholder="删除评论原因" class="form-control" id="delReason" name="delReason">
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteNoteComment1()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 
</body>
</html>