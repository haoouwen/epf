
/******************************以下是触屏版会员中心公共js操作类**************************************************/


//删除单个操作
function webappdelOneInfo(actionName,field_name,field_value){
	art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+'确定要操作?'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        document.getElementById('commonForm').action=actionName;
			document.getElementById('commonText').name = field_name;
			document.getElementById('commonText').value = field_value;
			document.getElementById('commonForm').submit();	
	    },
	    cancel: function () {
			return true;
	    }
   });
}	

//列表批量删除操作
function webappdelInfo(actionName,field_name){
	// 值拼串
	var filedVal = '';	
	var allModelIds = document.getElementsByName(field_name);
	for(var i=0;i<allModelIds.length;i++){
		if(allModelIds[i].value!=""){
			filedVal += allModelIds[i].value+',';
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	jConfirm('确定删除？', '系统提示',function(r){ 
 		if(r){ 
			document.getElementById('commonForm').action=actionName;
	    	document.getElementById('commonText').name = "chb_id";
	    	document.getElementById('commonText').value = filedVal;
	    	document.getElementById('commonForm').submit();	  
		 }
    }); 
}
//去掉字符串中，最后一个字符
function deleteLastChar(str,ch){
 	if(str.indexOf(ch)>-1){
 		var lastPos = str.lastIndexOf(ch);
 		str=str.substring(0,lastPos);
 	}
 	return str;
 }
function goCart(){
   //判断页面支付支持本地存储机制
   if(window.localStorage){
       var storage = window.localStorage;
	   var  josnlist= storage.getItem("twgl");
	   var custnum = $("#custnum").val();
	   var registertype = $("#registertype").val();
	   var loc = "/webapplogin.html?loc=/webapp/goods!mallcart.action";
	   if(custnum !="" &&　custnum!=null && registertype!="" && registertype!=null){
	       var mobile_url = "&custnum="+custnum+"&registertype="+registertype;
	       loc += loc + mobile_url;
	   }
	 	$.ajax({  	 
	       type: "post",    	     
	       url: "/webapp/goods!dealCartApp.action?josnlist="+josnlist,
	       datatype:"json",
	       success: function(data){
	      	if(data=='0'){
	     	  	    window.location.href=loc;
		     		return false;
		     }else if(data=='1'){
		    		 storage.removeItem("ccn");
	     			 storage.removeItem("twgl");
	       	         window.location.href='/webapp/goods!mallcart.action';
		     }else{
		     	alert("购物车异常！");
		     }
	       }
	   });  
   }else{
      window.location.href='/webapp/goods!mallcart.action';
   }
  
 }
 function goCartupdate(){
   //判断页面支付支持本地存储机制
   if(window.localStorage){
       var storage = window.localStorage;
	   var  josnlist= storage.getItem("twgl");
	   var  josnlistccn= storage.getItem("ccn");
	   if(josnlist!=null&&josnlist!=""&&josnlistccn!=null&&josnlistccn!=""){
		   $.ajax({  	 
		       type: "post",    	     
		       url: "/webapp/goods!dealCartApp.action?josnlist="+josnlist,
		       datatype:"json",
		       success: function(data){
	    		 storage.removeItem("ccn");
     			 storage.removeItem("twgl");
       	         window.location.href='/webapp/goods!mallcart.action';
		       }
		   });  
	   
	   }
	 	
   }
  
 }
 
 
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
//触屏版分页控件处理脚本
function pages_util_app(num_pages){
	$("#pages_s").val(num_pages);
	$("#indexForm").submit();
}