<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/base.jsp"%>
 <%@ taglib prefix="el" uri="/WEB-INF/tlds/el-common.tld"%>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
	
  
<script type="text/javascript">
	var pageNum = 1;
	var pageSize;
	var totalPage;
	var start;
	var end;

	var userNm="";
	var sexType="-1";
	var identifyType="-1";
	var stateType="-1";
	var userMobile="";
	
	var thistr;
	var iUser;
//处理查找所有用户和分页
function SelectAllUsersAndPage(num){

	pageNum = num;	 
     $.ajax({  
         type:'post',  
         url:'${pageContext.request.contextPath}/admin/user/findusersbypage.do',  
         scriptCharset:'utf-8',
         data:{pageNum,pageNum},  
         success:function(data){//返回json结果  
        	 refreshTable(data); 	 
         },  
         complete:function(){ //生成分页条
             getSelectAllUsersPageBar();
         }
     });  
}
//点击查询按钮
function btnSelect(num){
	userNm = $("#userNm").val();
	sexType = $("#sexType").val();
	identifyType = $("#identifyType").val();
	stateType = $("#stateType").val();
	userMobile = $("#userMobile").val();
	SelectUserByConditionsAndPage(num);
}
//处理根据特定的条件查找用户和分页
function SelectUserByConditionsAndPage(num){
    
	pageNum = num;
	$.ajax({  
        type:'post', 
        url:'${pageContext.request.contextPath}/admin/user/findusersbyconditions.do',  
        scriptCharset:'utf-8',

        data:{"userNm":userNm,"userSexCode":sexType,"ifAdmin":identifyType,"userStatCode":stateType,"userMobile":userMobile,"pageNum":num},  
        success:function(data){//返回json结果  
        	refreshTable(data); 	        
        },  
        complete:function(){ //生成分页条
        	getSelectUsersByConditionsPageBar();    	
        }
    	
    });
}
function userDetail(user)
{
	iUser = user;
	
	$('#ModeluserId').val(user.userId);
	$('#ModeluserNm').val(user.userNm);   //给模态框赋值登录号
	$('#ModeluserLoginId').val(user.loginId);   //给模态框赋值登录号
	$('#ModeluserPsw').val(user.userPsw);		//给模态框赋值密码
	user.userSexCode == "1"?$('#ModeluserSex').val("男"):$('#ModeluserSex').val("女"); //给模态框赋值性别
	
	var createdate = new Date(user.createDate).Format("yyyy-MM-dd hh:mm:ss");
	$('#ModeluserCreateTime').val(createdate);		//给模态框赋值创建日期

	user.ifAdmin=="Y"?$('#ModeluserIfAdmin').selectpicker('val', 'Y'):$('#ModeluserIfAdmin').selectpicker('val', 'N')//给模态框赋值身份
	 
  	if(user.userStatCode=="0")
  		$('#ModeluserStat').selectpicker('val', '0');
  	else if(user.userStatCode=="1")
  		$('#ModeluserStat').selectpicker('val', '1');
  	else
  		$('#ModeluserStat').selectpicker('val', '2');
  
}

function updateUser(){
 
 	if($('#ModeluserIfAdmin').val()==identifyType && $('#ModeluserStat').val()==stateType)
		return ; 
	else{ 
		 var operater = $('#operater').val() ;
		 
 
 
		  iUser.ifAdmin = $('#ModeluserIfAdmin').val();
		  iUser.userStatCode =  $('#ModeluserStat').val();
		$.ajax({  
	        type:'post', 
	        url:'${pageContext.request.contextPath}/admin/user/updateuser.do?operater='+operater, 
	       contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型  
	        scriptCharset:'utf-8',
	       	data: JSON.stringify(iUser),
			success:function(){//返回json结果  
				SelectUserByConditionsAndPage(pageNum);
				alert("更改用户成功");
	        }         	
	    });
 	}  
}
$(function(){  
$("#btnDel").click(function(){ 
	
 
	   if(confirm('确定要删除所选吗?')){ 
	       var checks = $("input[name='check_box']:checked");
	     if(checks.length == 0){ alert('未选中任何项！');return false;}
	     var operater = $('#operater').val() ;
	       //将获取的值存入数组   
	       var checkData = new Array();
	       checks.each(function(){ 
	       checkData.push($(this).val()); 
	      });   
	     // alert(checkData);
	     }
	   $.ajax({  
	        type:'post', 
	        url:'${pageContext.request.contextPath}/admin/user/updateUserStatCode.do', 
	        scriptCharset:'utf-8',
	       	data:{"userStatCode":"2","userIds": checkData.toString(),"operater":operater},
			success:function(){//返回json结果  
				alert("用户id"+checkData+"删除成功");
				SelectUserByConditionsAndPage(pageNum);
				$('#allAndNotAll').prop('checked',false)
	        }         	
	    }); 
	   }); 
$("#btnLock").click(function(){ 
	
	   if(confirm('确定要冻结所选用户吗?')){ 
	       var checks = $("input[name='check_box']:checked");
	     if(checks.length == 0){ alert('未选中任何项！');return false;}
	       //将获取的值存入数组   
	       var operater = $('#operater').val() ;
	       var checkData = new Array();
	       checks.each(function(){ 
	         checkData.push($(this).val()); 
	      });   
	     // alert(checkData);
	     }
	   $.ajax({  
	        type:'post', 
	        url:'${pageContext.request.contextPath}/admin/user/updateUserStatCode.do', 
	        scriptCharset:'utf-8',
	       	data:{"userStatCode":"1","userIds": checkData.toString(),"operater":operater},
			success:function(){//返回json结果  
				alert("用户id"+checkData+"冻结成功");
				SelectUserByConditionsAndPage(pageNum);
				$('#allAndNotAll').prop('checked',false)
	        }         	
	    }); 
	   }); 	   
$("#btnRepeat").click(function(){ 
	   if(confirm('确定要恢复所选用户吗?')){ 
	       var checks = $("input[name='check_box']:checked");
	     if(checks.length == 0){ alert('未选中任何项！');return false;}
	     var operater = $('#operater').val() ;
	       //将获取的值存入数组   
	       var checkData = new Array();
	       checks.each(function(){ 
	        checkData.push($(this).val()); 
	      });   
	     // alert(checkData);
	     }
	   $.ajax({  
	        type:'post', 
	        url:'${pageContext.request.contextPath}/admin/user/updateUserStatCode.do', 
	        scriptCharset:'utf-8',
	       	data:{"userStatCode":"0","userIds": checkData.toString(),"operater":operater},
			success:function(){//返回json结果  
				alert("用户id"+checkData+"恢复正常");
				SelectUserByConditionsAndPage(pageNum); 
				$('#allAndNotAll').prop('checked',false)
	        }         	
	    }); 
	   }); 	   
	     
})
// td.innerHTML = "<a href='#'>超链接</a>";
	//thistr.find("td").eq(1).setAttribute('onClick','www.baidu.com');
	//alert("ca");
	//thistr.find("td").eq(1).innerHTML = "<a href='www.baidu.com'></a>";
	//alert(thistr.length); */
	//thistr.find("td").eq(1).html("<input type='checkbox'/>");
	//alert("第一列:"+thistr.find("td").eq(0).html()+"\n第二列:"+thistr.find("td").eq(1).html()+"\n第三列:"+thistr.find("td").eq(2).html());
	 
	
	//thistr.find("td").eq(7).text($('#ModeluserStat').val()=='0'?"正常":($('#ModeluserStat').val()=='1'?"已冻结":"已删除"));
	 
	


</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	 
</head>
 
<body>
 <input type="hidden" value="${login_user.loginId}" id="operater"/>
 
 <!-- 模态框（Modal） -->
<div class="modal fade" id="detailModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">个人信息</h4>
            </div>
            <div class="modal-body">
        
		   		   <form class="form-inline" role="form">
		   		   
          				
				      	<div class="form-group">
				        	<label class="form-label">用户名</label>
				        	<input type="text" class="form-control" id="ModeluserNm" name="ModeluserNm">
				      	</div>
				      	
				      	<div class="form-group" style="padding-left:50px;">
				        	<label class="form-label">个人头像</label>
				        	<img src="${pageContext.request.contextPath}/images/login/login-img.png"  width="100px" height="150px"/>
				      	</div>
				      			   		   
				      	<div class="form-group">
				        	<label class="form-label">登录号</label>
				        	<input type="text" class="form-control" id="ModeluserLoginId" name="ModeluserLoginId">
				      	</div>
				      	

				      	<div class="form-group" style="padding-left:50px;">
				        	<label class="form-label">密码</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        	<input type="text" class="form-control" id="ModeluserPsw" name="ModeluserPsw">
				      	</div>
				      	
				      	<div class="form-group" style="padding-top:20px;">
				        	<label class="form-label">性别</label>&nbsp;&nbsp;&nbsp;
				        	<input type="text" class="form-control" id="ModeluserSex" name="ModeluserSex">
				      	</div>
				      	
				      	<div class="form-group" style="padding-left:50px;padding-top:20px;">
				        	<label class="form-label">创建日期</label>
				        	<input type="text" class="form-control" id="ModeluserCreateTime" name="ModeluserCreateTime">
				      	</div>
				      	
				      	<div class="form-group" style="padding-top:20px;">
				        	<label class="form-label">身份</label>&nbsp;&nbsp;&nbsp;  	 
				        	<select  class="selectpicker" id="ModeluserIfAdmin" name="ModeluserIfAdmin" data-width="auto">
								 <option value="Y">管理员</option>
								 <option value="N" >普通用户</option>
							</select>
				      	</div>	
				      	<div class="form-group" style="padding-left:150px;padding-top:20px;">
				        	<label class="form-label">用户状态</label>&nbsp;
				        	<select class="form-control" id="ModeluserStat"  data-width="auto">
								<option value="0">正常</option>
								<option value="1">已冻结</option>
								<option value="2">已删除</option>
						 	 </select>
				      	</div>			
				      	<input type="hidden" name="ModeluserId" id="ModeluserId" >
				        	 
				        	 
					</form>
               	 
            </div><div style="padding-top:60px;"></div>
            <div class="modal-footer"  >
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateUser()">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
 
	<div class="panel-body" style="padding-bottom:0px;width:1200px;height:50px;padding-left:50px;">
        <div class="panel panel-default"  >
            <div class="panel-heading" >用户管理 &nbsp; &nbsp;>> &nbsp; &nbsp; 用户列表</div>
            <div class="panel-body" style="width:1250px;height:120px;">
                <form id="formSearch" class="form-horizontal"> 
                 	<label class="control-label col-sm-1" >用户名</label>
                        <div  style="width:140px;height:50px;float:left;" >
                            <input type="text" class="form-control" id="userNm">
                        </div>
                      <label class="control-label col-sm-1" >手机号码</label>
                        <div  style="width:140px;height:50px;float:left;" >
                            <input type="text" class="form-control" id="userMobile">
                        </div>
                        
                 	 <label class="control-label col-sm-1"  >性别</label>
                       	<div  style="width:100px;height:50px;float:left;">
                             <select class="selectpicker" id="sexType"  data-width="auto">
 								<option value="-1" selected="selected">不限</option>
								<option value="1">男</option>
								<option value="0">女</option>
						 	 </select>
                       	</div>
                   <label class="control-label col-sm-1" >身份</label>
                    	<div  style="width:100px;height:50px;float:left">
                        	<select class="selectpicker" id="identifyType" data-width="auto">
 								 <option value="-1" selected="selected">不限</option>
								 <option value="Y">管理员</option>
								 <option value="N">普通用户</option>
							</select>
                   		</div> 	
                   		
                    <label class="control-label col-md-1">用户状态</label>
                       	<div style="width:100px;height:50px;float:left">
                             <select class="selectpicker" id="stateType"  data-width="auto">
 								<option value="-1" selected="selected">不限</option>
								<option value="0">正常</option>
								<option value="1">已冻结</option>
								<option value="2">已删除</option>
						 	 </select>
                       	</div>               	 
                </form>
                 
                 <div style="width:100px;margin-left:1020px;margin-top:60px;">
						     <button type="button" class="btn btn-primary" id="buttonSelect" onclick="btnSelect(1)">查询</button>     	              
              			</div>            
                       	 
            </div>
        </div>       
        <div id="toolbar" class="btn-group" >
              <button id="btnDel" type="button" class="btn  btn-danger" >
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                
                <button id="btnLock"type="button" class="btn btn-warning" style="margin-left:10px;"> 
                   <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>冻结
                   
                 <button id="btnRepeat" type="button" class="btn btn-success btn-md"  style="margin-left:10px;">
                <span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>恢复

        </div>
        
        
      <table id="userInformation" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	        <th  style="text-align:center;width:15%"><input type="checkbox" id="allAndNotAll"/>用户id</th>
	        <th  style="text-align:center;width:15%;">用户名</th>
	        <th  style="text-align:center;width:15%;">密码</th>
	        <th  style="text-align:center;width:15%;">手机号码</th>
	        <th  style="text-align:center;width:15%;">创建日期</th>
	        <th  style="text-align:center;width:5%;">性别</th>
	        <th  style="text-align:center;width:10%;">身份</th>
	        <th  style="text-align:center;width:10%;">用户状态</th>
	        
	     
	    </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${pagebean.data}" var="user">
	   
	    <tr style="cursor:hand"  >
	        <td style="text-align:center;"><input type="checkbox" name="check_box" value="${user.userId}">${user.userId}</input>
			</td>
			<td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'>${user.userNm}</td>
			<td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'>${user.userPsw}</td>
			<td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'>${user.userMobile}</td>
			<td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
	        <td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'>${user.userSexCode == 1?'男':'女' }</td>
	        <td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'>${user.ifAdmin=='Y'?"管理员":"普通用户"}</td>
	        <td style="text-align:center;" data-toggle="modal" data-target="#detailModel" onClick='userDetail(${el:toJsonString(user)})'>${user.userStatCode=='0'?"正常":(user.userStatCode=='1'?"已冻结":"已删除")}</td>	         	         
	    </tr>   
	   
     	</c:forEach>
	    </tbody>
	</table>
	
	<div class="black2" id="page"><p>
 			<span><a href="javascript:void(0)" onclick="SelectAllUsersAndPage(1)">首页  </a></span>
			<c:if test="${pagebean.pageNum == 1}">
        		<span class="disabled">[上一页]</span>
        	</c:if>
        
        	<c:if test="${pagebean.pageNum > 1}">	
            	 <span><a href="javascript:void(0)" onclick="SelectAllUsersAndPage(${pagebean.pageNum}-1)">[上一页]</a></span>
        	</c:if>
        	
        	<%--动态显示条 --%>
        	<c:forEach begin="${pagebean.start }" end="${pagebean.end }" var="num">
			     <c:choose>
				   <c:when test="${pagebean.pageNum != num}">   
				   		<span><a href="javascript:void(0)" onclick="SelectAllUsersAndPage(${num})">${num}</a></span>			   
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
           		<a href="javascript:void(0)" onclick="SelectAllUsersAndPage(${pagebean.pageNum + 1 })">[下一页]</a>	
            </c:if>
            
            <a href="javascript:void(0)" onclick="SelectAllUsersAndPage(${pagebean.totalPage })">[尾页]</a>
    	</p></div> 

	</div>
	 
   
</body>

</html>