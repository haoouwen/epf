$(document).ready(function() {
	//焦点图
	if($("div").hasClass("slider")){
		
		$(".slider").mobileSlider({width:620, during:5000,scale:2});
	}
	
	//压马路
	if($("div").hasClass("ymlDiv")){
		$(".ymlDiv a:first").css({"width":"66.6%"});
	}
	
	//引导页
	if($("div").hasClass("guidePage")){
		//判断页面支付支持本地存储机制
	   //if(window.localStorage){
	   //   alert(1);
	    //   var storage = window.localStorage;  
	    //   var indexTipLocal = storage["indexTipLocal"];//用户名
	    //   alert(indexTipLocal);
	    //   if(getStorageTime("indexTip_time",1000*60*60*24*30)|| indexTipLocal==undefined || indexTipLocal==null || indexTipLocal==""){//判断自动登陆时间是否过期
	     //      alert(2);
	     //       $(".guidePage").show();
         //   }else{
         //      alert(3);
          //      $(".guidePage").hide();
          //  }
	  // }else{
	  // 	$(".guidePage").show();
	  // }
		//$(".guideIn").click(function(){
		//	$(".guidePage").hide();
			//重新设置本地存储
		//	if(window.localStorage){
		//		var storage = window.localStorage;  
		//		storage.setItem("indexTipLocal","1");
		//        setStorageTime("indexTip_time","30");
	    //   }
		//})
	}
	ajaxGetGoodInfoDate();
	if($("div").hasClass("ifSerDiv")){
		
		$(".ifSerDiv .topCls").toggle(function(){
			$(".sinav").show();	
		},function(){
			$(".sinav").hide();	
		})
	}
});

function ajaxGetGoodInfoDate(){
$.ajax({  	 
     type: "post",    	     
     url: "/webapp/goods!ajaxDJSDate.action?tab_number=4637384735",
     datatype:"json",
     success: function(data){
        appindextime(data);
     }
 });   
}

//设置存储时间 
function setStorageTime(key,value){

	  var curtime = new Date().getTime();//获取当前时间
	  localStorage.setItem(key,JSON.stringify({val:value,time:curtime}));//转换成json字符串序列
}

//获取存储时间
function getStorageTime(key,exp){//exp是设置的过期时间

	  var val = localStorage.getItem(key);//获取存储的元素
	  var dataobj = JSON.parse(val);//解析出json对象
	  if(new Date().getTime() - dataobj.time > exp){//如果当前时间-减去存储的元素在创建时候设置的时间 > 过期时间
	     return false;
	  }else{
	     return true;
	  }
}
