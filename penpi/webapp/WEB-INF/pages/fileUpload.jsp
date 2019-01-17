<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/base.jsp"%>
    ﻿﻿
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
 .report-file {
            display: block;
            position: relative;
            width: 120px;
            height: 28px;
            overflow: hidden;
            border: 1px solid #428bca;
            background: none repeat scroll 0 0 #428bca;
            color: #fff;
            cursor: pointer;
            text-align: center;
            float: left;
            margin-right:5px;
    }
    .report-file span {
            cursor: pointer;
            display: block;
            line-height: 28px;
    }
    .file-prew {
            position: absolute;
            top: 0;
            left:0;
            width: 120px;
            height: 30px;
            font-size: 100px; 
            opacity: 0; 
            filter: alpha(opacity=0);
            cursor: pointer;
    }
    .container{
    width: 200px;
    height: 20px;
    background-color: gray;
    float:left;
}
#progress{
    height: 20px;
    background-color: orange;
    display: inline-block;
}
  
</style>
<script type="text/javascript">
function download1(fileNm){
	 
	window.location.href = "${pageContext.request.contextPath}/download/apk/"+fileNm;
 
}
var totalSize = 0;  

//绑定所有type=file的元素的onchange事件的处理函数  
$(':file').change(function() {  
    var file = this.files[0]; //假设file标签没打开multiple属性，那么只取第一个文件就行了  
    name = file.name;  
    size = file.size;  
    type = file.type;  
    url = window.URL.createObjectURL(file); //获取本地文件的url，如果是图片文件，可用于预览图片  
      
    totalSize += size;  
    $("#info").html("文件名：" + name + "<br>文件类型：" + type + "<br>文件大小：" + size + "<br>url: " + url);  
      
});  

function upload() {  
    //创建FormData对象，初始化为form表单中的数据。需要添加其他数据可使用formData.append("property", "value");  
    var formData = new FormData($('form')[0]);  
      
    //ajax异步上传  
    $.ajax({  
        url: "${pageContext.request.contextPath }/admin/upload/fileUpload.do",  
        type: "POST",  
        data: formData,  
        xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数  
          
            myXhr = $.ajaxSettings.xhr();  
            if(myXhr.upload){ //检查upload属性是否存在  
                //绑定progress事件的回调函数  
                myXhr.upload.addEventListener('progress',progressHandlingFunction, false);   
            }  
            return myXhr; //xhr对象返回给jQuery使用  
        },  
        success: function(result){  
        	var jsonObj = eval("("+result+")");
         
        	var html="";
        	html += "<tr style=\"cursor:hand\"> <td  style=\"text-align:center;\">" +jsonObj.date+"</td>  <td style=\"text-align:center;\"><a href=\"#\" onclick='download1(\""+jsonObj.fileName+"\")' class=\"btn btn-sm btn-success\">" + jsonObj.fileName +"</a></td></tr>";
        	$("#filelist").find('tr').eq(0).before(html);
        	 
         //   $("#result").html(result);  
        },  
        contentType: false, //必须false才会自动加上正确的Content-Type  
        processData: false  //必须false才会避开jQuery对 formdata 的默认处理  
    });  
}         

//上传进度回调函数：  
function progressHandlingFunction(e) {  
    if (e.lengthComputable) {  
        $('#progress').attr({value : e.loaded, max : e.total}); //更新数据到进度条  
        var percent = e.loaded/e.total*100;  
        $('#progress').html(e.loaded + "/" + e.total+" bytes. " + percent.toFixed(2) + "%");  
        $('#progress').css('width', percent.toFixed(2) + "%");
    }  
}  
</script>
</head>
<body>

   	<div class="panel-body"
		style="padding-bottom: 0px; width: 1200px; height: 50px; padding-left: 50px;">
		<div class="panel panel-default">
			<div class="panel-heading">文件管理 &nbsp; &nbsp;>> &nbsp; &nbsp;
				文件列表</div>
		</div>
 

  <div style="padding-top:20px;">
  <form action="${pageContext.request.contextPath }/admin/upload/fileUpload.do" method="post" enctype="multipart/form-data">
 
 <div class="report-file">
      <span>选择文件</span><input tabindex="3" size="3" name="uploadFile" id="uploadFile" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value">
 </div>
 <input type="text" id="textName" style="height: 28px;width:250px;border:1px solid #f1f1f1" /></form> <button onclick="upload()">上传</button>

  </div>
  <br>
  
  <div class='container'>
             <span id="progress"></span>
         </div> 
  
    <div id="info"></div>
    <div id="result"></div>
  
  
 <div style="float:right;padding-top:20px;padding-left:20px;">
      			<button type="button"   class="btn btn-primary" id="buttonSelect">查询</button>     	              
      		</div>
 <div style="float:right;padding-top:20px;">
      			<input type="text" placeholder="文件名关键字" class="form-control" >
     		</div>	
 
 
   <div style="padding-top:80px;">   
 	<table style="font-size:13px;" class="table table-hover table-bordered" >
	    <thead>
	    <tr>
	    	 <th  style="text-align:center;"> 上传时间</th>
	        <th  style="text-align:center;"> 文件列表</th>
	        
	    </tr>
	    </thead>
	    <tbody id="filelist">
	     
 
			 <c:forEach items="${mapFileInfo}" var="fileInfo">	
			 
			    <tr style="cursor:hand">
			      <td  style="text-align:center;"> ${fileInfo.key }</td>  
			        <td  style="text-align:center;"><a href="#" onclick='download1("${fileInfo.value.fileName}")' class="btn btn-sm btn-success"  >${fileInfo.value.fileName} </a></td>  
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