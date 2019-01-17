<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function toExcel(type){
	
		$.ajax({  
		       type:'post', 
		       url:'${pageContext.request.contextPath}/admin/user/printUser.do',  
		       scriptCharset:'utf-8',
		       data:{"type":type},  
		       success:function(data){//返回json结果  
		    	   
		    	   window.location.href="${pageContext.request.contextPath}/temp/"+data;     
		       }	     		   	
		   });
	
		
}
 
</script>
</head>
<body>

 
 <div class="panel-body" style="padding-bottom:0px;width:1200px;height:50px;padding-left:50px;">
	<div class="panel panel-default"  >
		<div class="panel-heading" >用户管理 &nbsp; &nbsp;>> &nbsp; &nbsp; 打印用户表格</div>
	</div>

 
    <div style="padding-top:100px;">   
 	<table id="userPrint" style="font-size:13px;" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	    	<th  style="text-align:center;">编号</th>
	        <th  style="text-align:center;"> 导出链接</th>
	        
	    </tr>
	    </thead>
	    <tbody>
	    
	   
	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">1</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(0)" title="导出所有管理员">导出所有用户</a></td> 
	    </tr>   
	    	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">2</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(1)" title="导出所有普通用户">导出所有管理员</a></td> 
	    </tr>
	    	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">3</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(3)" title="导出所有女性用户">导出所有男性用户</a></td> 
	    </tr>
	    	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">4</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(4)" title="导出所有正常用户">导出所有女性用户</a></td> 
	    </tr>
	    	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">5</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(5)" title="导出所有冻结用户">导出所有正常用户</a></td> 
	    </tr>
	    	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">6</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(6)" title="导出所有已删除用户">导出所有冻结用户</a></td> 
	    </tr>
	    	    <tr style="cursor:hand"   >
	        <td  style="text-align:center;">7</td> 
	        <td  style="text-align:center;"><a href="#" class="btn btn-sm btn-success" onclick="toExcel(7)" title="导出所有用户">导出所有已删除用户</a></td> 
	    </tr>
	    	 
	    </tbody>
	</table> 	 	
	</div> 
 </div>
 

 
 
 
 
</body> 
</html>