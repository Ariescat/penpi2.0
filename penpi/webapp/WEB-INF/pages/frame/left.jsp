<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/css/main_left.css" rel="stylesheet" type="text/css">
 
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">

$(function(){	
	//导航切换
	$("ul li ul li").click(function(){
		 
		 $("ul li ul li").each(function(){               
			 $(this).css("background","#FFF");
             });
		 $(this).css("background","#EFEFEF");
 	});

})	

</script>
</head>	
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
 <body>
 
    <div class="container-fluid"  >
        <div class="row">
            <div style="border-right:1px solid gray;height:1000px;">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <li class="active">
                        <a href="#">
                            <i class="glyphicon glyphicon-th-large"></i>
                            首页         
                        </a>
                    </li>
                    <li>
                   		<a href="#usermanage" class="nav-header collapsed" data-toggle="collapse">
                          <i class=" glyphicon glyphicon-user"></i> 
                            <span style="font-size:1.5em;">用户管理</span>
                               <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                        <ul id="usermanage" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="${pageContext.request.contextPath}/admin/user/findalluser.do?pageNum=1" id="li_1.1" target="rightFrame"><i class="glyphicon glyphicon-user"></i>&nbsp;<span style="font-size:1.4em;">用户列表</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/oplog/operateUserLog.do?pageNum=1" id="li_1.2" target="rightFrame"><i class="glyphicon glyphicon-eye-open"></i>&nbsp;<span style="font-size:1.4em;">日志查看</span></a></li>
                             <li><a href="${pageContext.request.contextPath}/admin/ui/toPrintUser.do" id="li_1.3" target="rightFrame"><i class="glyphicon glyphicon-download"></i>&nbsp;<span style="font-size:1.4em;">打印用户表格</span></a></li>
                        	<li><a href="${pageContext.request.contextPath}/admin/ui/toUserStatistic.do" id="li_1.4" target="rightFrame"><i class="glyphicon glyphicon-zoom-in"></i>&nbsp;<span style="font-size:1.4em;">数据统计</span></a></li>
                        </ul>
                       
                    </li>
                    
                    <li>
                    <a href="#ordermanage" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-folder-open"></i>
                            <span style="font-size:1.5em;">订单管理</span>
                               <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                        <ul id="ordermanage" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="${pageContext.request.contextPath}/admin/order/findallorders.do?pageNum=1&sortType=0&orderType=-1&sendUserMobile=" id="li_2.1" target="rightFrame"><i class="glyphicon glyphicon-tasks"></i>&nbsp;<span style="font-size:1.4em;">订单列表</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/orderexcep/findallordersExcepRecord.do?pageNum=1" id="li_2.2" target="rightFrame"><i class="	glyphicon glyphicon-flash"></i>&nbsp;<span style="font-size:1.4em;">异常订单</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/ui/toPrintOrders.do" id="li_2.3" target="rightFrame"><i class="glyphicon glyphicon-download"></i>&nbsp;<span style="font-size:1.4em;">订单打印</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/ui/toOrderStatistic.do" id="li_2.4" target="rightFrame"><i class="glyphicon glyphicon-zoom-in"></i>&nbsp;<span style="font-size:1.4em;">订单数据统计</span></a></li>
                       		
                        </ul>
                       
                    </li>
                    
                    <li>
                    <a href="#topicmanage" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-pencil"></i>
                            <span style="font-size:1.5em;">话题管理</span>
                               <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                        <ul id="topicmanage" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="${pageContext.request.contextPath}/admin/topic/topicMain.do?pageNum=1&topicCategoryId=50001" id="li_3.1" target="rightFrame"><i class="glyphicon glyphicon-pencil"></i>&nbsp;<span style="font-size:1.4em;">话题列表</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/ui/toTopicStatistic.do" id="li_3.2" target="rightFrame"><i class="glyphicon glyphicon-zoom-in"></i>&nbsp;<span style="font-size:1.4em;">数据统计</span></a></li>
                        </ul>
                       
                    </li>
                    
                     <li>
                    <a href="#msgcmanage" class="nav-header collapsed" data-toggle="collapse">
                            <i class="	glyphicon glyphicon-tag"></i>
                            <span style="font-size:1.5em;">消息管理</span>
                               <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                        <ul id="msgcmanage" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="${pageContext.request.contextPath}/admin/msg/messageList.do" id="li_4.1" target="rightFrame"><i class="	glyphicon glyphicon-comment"></i>&nbsp;<span style="font-size:1.4em;">消息列表</span></a></li>
                            
                        </ul>
                       
                    </li>
                    
                       <li>
                    <a href="#filemanage" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-file"></i>
                            <span style="font-size:1.5em;">文件管理</span>
                               <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                        </a>
                        <ul id="filemanage" class="nav nav-list collapse secondmenu" style="height: 0px;">
                            <li><a href="${pageContext.request.contextPath}/admin/ui/toFileUpload.do" id="li_5.1" target="rightFrame"><i class="	glyphicon glyphicon-envelope"></i>&nbsp;<span style="font-size:1.4em;">文件列表</span></a></li>
                            
                        </ul>
                       
                    </li>
     
     				
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-cog"></i>
                            	 <span style="font-size:1.5em;">关于系统</span>		
                        </a>
                    </li>

                </ul>
            </div>        
        </div>
    </div>
</body>
</html>