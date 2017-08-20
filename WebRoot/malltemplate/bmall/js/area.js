// 创建地区弹出层一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.areapopup= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup.defaults, options);
	      //初始化方法
	      this.each(function(){    
	          //获取窗体的宽度与高度
 	   		  var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
 	   		  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = $("#areabotton").offset().top +19;
			  var _posiLeft = $("#areabotton").offset().left;
	          //新建一个DIV遮盖层
   	   		  $("<div id='cover' class='cover'></div>").appendTo("body");	
   	   		  $("#cover").height(_documentHeight);
   	   		  $("#cover").width("100%");
   	   		  $("#cover").css({"opacity": "0","position":"absolute","left":"100px","top":"0px","z-index":888});
   	   		  $("#cover").fadeIn("slow");
			  $(this).css({"left": (_posiLeft-200) + "px","top":_posiTop + "px","display":"block","position":"absolute","z-index":9999});//设置position
		  }); 
	  };  
// 闭包结束  
})(jQuery); 
//设置地区默认DIV
function setAreaDiv(a_div){	
	if(a_div!=null&&a_div!=""){
		area_div_id=a_div;		
	}	
}
//加载第一级地区
function shiparea(){
  	$("#showship").areapopup({});
 	 var careaid = $("#careaid").val();
 	 //第一次加载是采用IP定位 cateid为空
 	 if(careaid==''){
		  var shipareaid = $("#shipareaid").val();
		  $("#"+shipareaid).addClass("lired");
		  areafoc(shipareaid);
		  var areaid = $("#areaid").val();
		  $("#"+areaid).addClass("lired");
 	 }else{
	     $("#"+careaid).addClass("lired");
         areafoc(careaid);
         var sareaid = $("#sareaid").val();
		 $("#"+sareaid).addClass("lired");
  	 }
}


//加载第二级地区
function areafoc(areaid){
    $.ajax({
       type: "post",
       url: "/mall/goods!getshiparea.action?shiparea=" + areaid,
       datatype:"json",
       async:false,
       success: function(data){ 
	    if(data!=''){
	          $(".twoarea").html(data);
		   }
	    }
    });
    //遍历careadiv 中的li 清除li的样式
    $('#careadiv li').each(function(i){
          $(this).removeClass("lired");
    });
      //对选中的地区添加 高亮样式
      $("#"+areaid).addClass("lired");
}
	
//选中地区 赋值后关闭层
function shipfoc(areaid,up_areaid,areaNmae){
   $("#careaid").val(up_areaid);
   $("#sareaid").val(areaid);
   $("#areabotton").html(areaNmae);
   //计算运费
   getShipPrice();
   closearea();
}

//关闭层
function closearea(){
  $(".cover").remove();
  $("#showship").css("display","none");
}




//通过AJAX方式加载全国省份
function loadProvinceList(area_div_id){
	var dataUrl="/area!childList.action?pId=1111111111";
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	if(data.length>0){
             	var areaStr="<ul class='selareaul' id='selareaulid'>";
             	for(var i=0;i<data.length;i++){
             		areaStr+="<li id='"+data[i].area_id+"'><a href='javascript:;' onclick='document.forms[0].area_attr_s.value="+data[i].area_id+";document.forms[0].submit();'>"+data[i].area_name+"</a></li>";
             	}
             	areaStr+="</ul>";
             	$("#"+area_div_id).append(areaStr);               	   		
        	}
        }	 
	}); 
}
//根据父级ID加载地区
function getCityList(pid){
	var dataUrl="/area!childList.action?pId="+pid;
	var resultData="";
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	if(data.length>0){
             	var areaStr="<ul>";
             	for(var i=0;i<data.length;i++){
             		areaStr+="<li><a href='javascript:;' onclick='document.forms[0].area_attr_s.value="+data[i].area_id+";document.forms[0].submit();'>"+data[i].area_name+"</a></li>";
             	}
             	areaStr+="</ul>";
             	resultData=areaStr;   	   		
        	}
        }	 
	}); 
	return resultData;
}


