<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript">

function submitformSelect(){
	var type = document.getElementById("type");
if(type.value == 0){
	$.ajax({  
	       type:'post', 
	       url:'${pageContext.request.contextPath}/admin/topic/topicTypeStatistic.do',  
	       scriptCharset:'utf-8',
	       success:function(data){//返回json结果 
	    	   
		    	  var jsonObj = eval("("+data+")");
		       		 
		       		$('#container').highcharts({
		       		    chart: {
		       		        plotBackgroundColor: null,
		       		        plotBorderWidth: null,
		       		        plotShadow: false
		       		    },
		       		    title: {text: '话题类别比例'},//这里是标题
		       		    tooltip: {
		       		        pointFormat: '{series.name}: <b>{point.percentage}%</b>',  
		       		    },
		       		    credits: {    
		       		        enabled:false    //去掉水印     
		       		    },
		       		    plotOptions: {        //饼外面拉出的黑线和文字
		       		        pie: {
		       		            allowPointSelect: true,
		       		            cursor: 'pointer',
		       		            dataLabels: {
		       		              enabled: true,        //设为false，不显示:饼外面拉出的黑线和文字
		       		              color: '#000000',
		       		              connectorColor: '#000000',
		       		              format: '<b>{point.name}</b>: {point.percentage} %'
		       		            }
		       		        }
		       		    },
		       		    series: [{
		       		            type: 'pie',
		       		           
		       		            colors: ['#1ccb6a','#f7ef1e','#7cb5ec','#434348','#f7a35c','#8085e9'],//指定颜色
		       		            data: [
		       		            	['关注',Number(jsonObj.follow)],
		       		                ['热门',Number(jsonObj.hot)],
		       		             	['校园',Number(jsonObj.school)],
		       		         		['动漫',Number(jsonObj.comic)],
		       		       			['科技',Number(jsonObj.technology)],
		       		    			['游戏',Number(jsonObj.game)],
		       		 				['互动',Number(jsonObj.interact)],
		       						['情感',Number(jsonObj.emotion)],
		       						['娱乐',Number(jsonObj.entertainment)],
		       		            ]
		       		        }]
		       		    }); 
	       }	
	   });
}
}
</script>
</head>
<body>   
 <div class="panel-body" style="padding-bottom:0px;width:1200px;height:50px;padding-left:50px;">
	<div class="panel panel-default"  >
		<div class="panel-heading" >话题管理 &nbsp; &nbsp;>> &nbsp; &nbsp; 数据统计</div>
	</div>
	 
			<select  class="selectpicker" id="type" name="type" data-width="auto" onchange="submitformSelect()">
		<option value="-1" >請選擇</option>
		<option value="0" >话题类别比例</option>
		  <option value="1" ></option>
		  <option value="2" ></option>
	</select>
	
	<div id="container" style="width: 600px;height:400px;"></div>
 
    
 
	
  
  </div>
</body>
</html>