//日期格式转换
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o){
    if (new RegExp("(" + k + ")").test(fmt)) {
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
}
    }
    return fmt;
}
//刷新页面表格
function refreshTable(data){
	//alert(data);
	 var jsonObj = eval("("+data+")"); // data的值是json字符串，这行把字符串转成object   
	 var tableResultStr =  "";
	 pageSize = jsonObj.pageSize;
	 totalPage = jsonObj.totalPage;
	 start = jsonObj.start;
	 end = jsonObj.end;
	 
	 $("#userInformation tr:not(:first)").html("");

	 $.each(jsonObj.data,function(index, dataItem){
	 	 var s = jsonObj.data[index].createDate;
		 var date = new Date(s).Format("yyyy-MM-dd hh:mm:ss");
			 //alert(data);
			 //alert(JSON.stringify(jsonObj.data[index]));
		 tableResultStr += "<tr style=\"cursor:hand\"><td style=\"text-align:center;\"><input type=\"checkbox\" name=\"check_box\" value=\""+jsonObj.data[index].userId+"\"/>"+jsonObj.data[index].userId+"</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" + jsonObj.data[index].userNm +"</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" + jsonObj.data[index].userPsw +"</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" + jsonObj.data[index].userMobile +"</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" +date+ "</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" +(jsonObj.data[index].userSexCode==1?'男':'女') +"</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" + (jsonObj.data[index].ifAdmin=='Y'?"管理员":"普通用户") +"</td>";
		 tableResultStr += "<td style=\"text-align:center;\" data-toggle=\"modal\" data-target=\"#detailModel\" onClick=\'userDetail("+JSON.stringify(jsonObj.data[index])+")\';>" + (jsonObj.data[index].userStatCode=='0'?"正常":(jsonObj.data[index].userStatCode=='1'?"已冻结":"已删除")) +"</td></tr>";
	    }); 	
	 //alert(tableResultStr);
	 $("#userInformation").append(tableResultStr);  

	 
	 
  
}
//分页导航栏公共方法
function basePage(pageFunName){
	
	 $("#page").html("");
	 
	 var pageResultStr = "<span><a href=\"javascript:void(0)\" onclick=\""+pageFunName+"(1)\">首页  </a></span>";
	 if(pageNum == 1)
	 	pageResultStr += "<span class=\"disabled\">[上一页]</span>";
	 if(pageNum > 1)
		pageResultStr += "<span><a href=\"javascript:void(0)\" onclick=\""+pageFunName+"(" +(pageNum-1)+ ")\">[上一页]</a></span>";
	
	  for(var i=start;i<=end;i++){
			 if(pageNum != i){
			 	pageResultStr += "<span><a href=\"javascript:void(0)\" onclick=\""+pageFunName+"(" +i+ ")\">" +i+ "</a></span>"; 
				}
			else{
				pageResultStr += "<span class=\"current\">" +i+ "</a></span>"; 
				}   		  	  	 
		}  
	  if(pageNum == totalPage)  	  
		  pageResultStr += "<span class=\"disabled\">[下一页]</span>";
	  if(pageNum < totalPage)
		  pageResultStr += "<a href=\"javascript:void(0)\" onclick=\""+pageFunName+"(" +(pageNum+1)+")\">[下一页]</a>";

	  pageResultStr +=  "<a href=\"javascript:void(0)\" onclick=\""+pageFunName+"(" +totalPage+ ")\">[尾页]</a>";
		
	  $("#page").append(pageResultStr); 
}

$(function(){  
    //实现全选与反选  
    $("#allAndNotAll").click(function() {   
    	 $("input[name='check_box']").prop("checked",this.checked);
    });  

    	   
})




//刷新查找所有用户的分页导航条
function getSelectAllUsersPageBar(){
	basePage("SelectAllUsersAndPage");	 
}

//刷新根据条件查找的用户的分页导航条
function getSelectUsersByConditionsPageBar(){
	 basePage("SelectUserByConditionsAndPage");	 
}

//刷新查找对所有用户操作的分页导航条
function getSelectAllOpUserLogPageBar(){
	basePage("SelectAllUserLogAndPage");
}


//刷新查找某个管理员对所有用户操作的分页导航条
function getSelectOpUserLogByOperPageBar(){
	basePage("SelectOpUserLogByOper");
}