<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://webapi.amap.com/maps?v=1.3&key=9b2c5c13a6501227c97613b559324a12"></script>    
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
<style type="text/css">
   #container{
 
      width:1250pxx;
      height:500px;
   }
    .userImage{ 
    width:40px; 
    height:40px; 
    border-radius:200px; 
}
</style>
<script type="text/javascript">
var schoolCenter = [113.3714900000, 23.1316400000];
var map
var user1;var user2;var user3;
var user1pos;var user2pos;var user3pos;
function change(){
	if($('#school').val() == 0){
		schoolCenter = [113.3714900000, 23.1316400000];	
		user1pos =[113.3714900000, 23.1316400000];
		user2pos = [113.3715900000, 23.132600000];
		user3pos = [113.3724900000, 23.1326400000];
	}else if($('#school').val() == 1){
		schoolCenter = [113.3480000000,23.1509400000];
		user1pos =[113.3490000000,23.1509500000];
		user2pos = [113.3480000000,23.1509400000];
		user3pos = [113.3494000000,23.1507400000];
	}else if($('#school').val() == 2){
		schoolCenter = [113.2995800000,23.0974700000];
		user1pos =[113.2985800000,23.0974700000];
		user2pos = [113.2976800000,23.0975700000];
		user3pos = [113.2968800000,23.0975700000];
	}
	map.destroy();
	map = new AMap.Map('container', {
  	    resizeEnable: true,
  	    zoom:17,
  	    center:schoolCenter
  	});
	AMapUI.loadUI(['overlay/SimpleMarker'], function(SimpleMarker) {
   		user1 = new SimpleMarker({
            iconStyle: '<div style="width:20px;height:60px;"><img src="${pageContext.request.contextPath}/initImage/userhead/boy1.jpg"  class="userImage"</div>',
            offset: new AMap.Pixel(-10, -60),
            map: map,
           showPositionPoint: true,
           position: user1pos
        }); 
   	 	user2 =new SimpleMarker({
            iconStyle: '<div style="width:20px;height:60px;"><img src="${pageContext.request.contextPath}/initImage/userhead/boy2.jpg"  class="userImage"</div>',
            offset: new AMap.Pixel(-10, -60),
            map: map,
           showPositionPoint: true,
           position:user2pos
        });
   	 	user3 = new SimpleMarker({
            iconStyle: '<div style="width:20px;height:60px;"><img src="${pageContext.request.contextPath}/initImage/userhead/boy3.jpg"  class="userImage"</div>',
            offset: new AMap.Pixel(-10, -60),
            map: map,
           showPositionPoint: true,
           position: user3pos
        }); 
    });
    AMapUI.loadUI(['overlay/SimpleInfoWindow'], function(SimpleInfoWindow) {
        var infoWindow = new SimpleInfoWindow({
            infoTitle: '<strong>张三(18813299970)</strong>',
            infoBody: '<p class="my-desc"> <br/>我乐于助人啊</p>',
            offset: new AMap.Pixel(0, -31)
        }); 
		function openInfoWin() {
            infoWindow.open(map, user1.getPosition());
        }
        AMap.event.addListener(user1, 'click', function() {
            openInfoWin();
        });
        var infoWindow1 = new SimpleInfoWindow({
            infoTitle: '<strong>李四(18813299971)</strong>',
            infoBody: '<p class="my-desc"> <br/>我也乐于助人啊</p>',
            offset: new AMap.Pixel(0, -31)
        }); 
		function openInfoWin1() {
            infoWindow1.open(map, user2.getPosition());
        }
        AMap.event.addListener(user2, 'click', function() {
            openInfoWin1();
        });
        var infoWindow2 = new SimpleInfoWindow({
            infoTitle: '<strong>王五(18813299972)</strong>',
            infoBody: '<p class="my-desc"> <br/>我乐乐乐乐于助人啊</p>',
            offset: new AMap.Pixel(0, -31)
        }); 
		function openInfoWin2() {
            infoWindow2.open(map, user3.getPosition());
        }
        AMap.event.addListener(user3, 'click', function() {
            openInfoWin2();
        });

     
    });
	
}

</script>
</head>
<body>
 	<div class="panel-body"
		style="padding-bottom: 0px; width: 1200px; height: 50px; padding-left: 50px; ">
		<div class="panel panel-default">
			<div class="panel-heading">首页
				 </div>
		</div>

	 	<div class="form-group" style="padding-top:20px;">
		        	<label class="form-label">学校</label> &nbsp; &nbsp; 
		        	<select  class="selectpicker" id="school" name="school" data-width="auto" onchange="change()">
						  <option value="0" >广东技术师范学院</option>
						  <option value="1" }>华南理工大学</option>
						  <option value="2"  >中山大学</option>
					</select>
			</div>
			
 
  			<div id="container"></div>
  		 
  	<script type="text/javascript">
  	 map = new AMap.Map('container', {
  	    resizeEnable: true,
  	    zoom:17,
  	    center: schoolCenter
  	});
     AMapUI.loadUI(['overlay/SimpleMarker'], function(SimpleMarker) {
    	 user1 = new SimpleMarker({
             iconStyle: '<div style="width:20px;height:60px;"><img src="${pageContext.request.contextPath}/initImage/userhead/boy1.jpg"  class="userImage"</div>',
             offset: new AMap.Pixel(-10, -60),
             map: map,
            showPositionPoint: true,
            position: [113.3714900000, 23.1316400000]
         }); 
    	 user2 = new SimpleMarker({
             iconStyle: '<div style="width:20px;height:60px;"><img src="${pageContext.request.contextPath}/initImage/userhead/boy2.jpg"  class="userImage"</div>',
             offset: new AMap.Pixel(-10, -60),
             map: map,
            showPositionPoint: true,
            position: [113.3715900000, 23.132600000]
         });
    	 user3 = new SimpleMarker({
             iconStyle: '<div style="width:20px;height:60px;"><img src="${pageContext.request.contextPath}/initImage/userhead/boy3.jpg"  class="userImage"</div>',
             offset: new AMap.Pixel(-10, -60),
             map: map,
            showPositionPoint: true,
            position: [113.3724900000, 23.1326400000]
         }); 
     });
     AMapUI.loadUI(['overlay/SimpleInfoWindow'], function(SimpleInfoWindow) {
         var infoWindow = new SimpleInfoWindow({
             infoTitle: '<strong>张三(18813299970)</strong>',
             infoBody: '<p class="my-desc"> <br/>我乐于助人啊</p>',
             offset: new AMap.Pixel(0, -31)
         }); 
		function openInfoWin() {
             infoWindow.open(map, user1.getPosition());
         }
         AMap.event.addListener(user1, 'click', function() {
             openInfoWin();
         });
         var infoWindow1 = new SimpleInfoWindow({
             infoTitle: '<strong>李四(18813299971)</strong>',
             infoBody: '<p class="my-desc"> <br/>我也乐于助人啊</p>',
             offset: new AMap.Pixel(0, -31)
         }); 
		function openInfoWin1() {
             infoWindow1.open(map, user2.getPosition());
         }
         AMap.event.addListener(user2, 'click', function() {
             openInfoWin1();
         });
         var infoWindow2 = new SimpleInfoWindow({
             infoTitle: '<strong>王五(18813299972)</strong>',
             infoBody: '<p class="my-desc"> <br/>我乐乐乐乐于助人啊</p>',
             offset: new AMap.Pixel(0, -31)
         }); 
		function openInfoWin2() {
             infoWindow2.open(map, user3.getPosition());
         }
         AMap.event.addListener(user3, 'click', function() {
             openInfoWin2();
         });

      
     });
  	</script>	
		
	</div>
 
 
</body>
</html>