// 创建弹出层绝对居中一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.popup= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup.defaults, options);
	      //私有方法
	      var pm =popupMethod;
	      //初始化方法
	      this.each(function(){    
	      	  //获取需要弹出层控件的ID
	      	  var popup_id = $(this).attr("id");
	      	  //创建一个父DIV框
	      	  $("<div id='p_p_div'></div>").appendTo("body");	
	      	  var p_p_height=parseInt(opts.p_height)+20;//父框的高度
	      	  $("#p_p_div").css({"width":opts.p_width+"px","height":p_p_height+"px","background":opts.p_bg_color,"border":"1px solid #a3a3a3","padding":"10px"});
	      	  $("#p_p_div").append("<div id='p_c_title'><b id='p_p_b'>"+opts.pop_title+"</b><img id='p_c_img'  src='images/closecover.png'/></div>");
	      	  $("#p_c_title").css({"height":"20px"});
	      	  $("#p_c_img").css({"margin":"-3px -5px auto 270px","cursor":"pointer"});
	      	  $("#p_p_b").css({"padding-left":"8px","margin-top":"-30px"});
	      	  //初始化高度与宽度
	      	  $(this).css({"width":opts.p_width+"px","height":opts.p_height+"px","background":opts.bg_color});
	          //获取窗体的宽度与高度
    	      var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	   	  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2 + _scrollHeight;
			  var _posiLeft = (_windowWidth - _popupWeight)/2;
	          //新建一个DIV遮盖层
   	   		  $("<div id='p_cover' class='cover'></div>").appendTo("body");	
   	   		  $("#p_cover").height(_documentHeight);
   	   		  $("#p_cover").width("100%");
   	   		  $("#p_cover").css({"opacity":opts.opacity, background: "black","position":"absolute","left":"0px","top":"0px","z-index":8887});
   	   		  $("#p_cover").fadeIn("slow");
   	   		  //设置position
			  $("#p_p_div").css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"absolute","z-index":8888});
			  $(this).css({"display":"block"});
		  	  $("#p_p_div").append($(this));	
		  	  //渲染方法
		  	  pm._close_cover(popup_id,opts);
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.popup.defaults = {
		  opacity:"0.3",
		  p_bg_color:"#CAD9E6",
		  bg_color:"#FFFFFF",
		  p_width:"700",
		  p_height:"550",
		  pop_title:"标题",
		  otherMethod:""
	  };	
	  
	 //私有方法 
     var popupMethod={
     	  _close_cover:function(popup_id,opts){
     	  	  $("#p_c_img,#p_cover").click(function(){
     	  	        //获取需要隐藏的规格项
     	  	        var sizeHtml =$("#"+popup_id).html();
     	  	  		$("#p_p_div").remove();
     	  	  		$("#p_cover").remove();
     	  	  		$("<div id='"+popup_id+"' class='"+popup_id+"'></div>").appendTo("body");	
     	  	  		$("#"+popup_id).html(sizeHtml);
	 		 		$("#"+popup_id).css({"display":"none"});
     	  	  }); 
     	  	  if(opts.otherMethod!=""){
				    eval(opts.otherMethod);
			  }
          }
     };
     
     // 关闭层  
	 $.fn.ccover= function(options) {
	 	//获取设置插件的选项	 
	    var opts = $.extend({}, $.fn.popup.defaults, options);
	    var popup_id = $(this).attr("id");
        //获取需要隐藏的规格项
        var sizeHtml =$("#"+popup_id).html();
  		$("#p_p_div").remove();
  		$("#p_cover").remove();
  		$("<div id='"+popup_id+"' class='"+popup_id+"'></div>").appendTo("body");	
  		$("#"+popup_id).html(sizeHtml);
		$("#"+popup_id).css({"display":"none"});
		if(opts.otherMethod!=""){
			eval(opts.otherMethod);
		}
	 }  
		  
// 闭包结束  
})(jQuery); 

$(document).ready(function(){
	$("#lcfsfoid > p > a").click(function(){
    $("#lcfsfoid").hide();
	$("#lcfsthid").show();
	$("#lcfsfid").hide();
	$("#lcfssid").show();
	});
	$("#lcfssid > p > a").click(function(){
    $("#lcfssid").hide();
	$("#lcfsfid").show();
	$("#lcfsthid").hide();
	$("#lcfsfoid").show();
	});
	$("#tjbutfid").click(function(){
		$("#converconfid").popup({
				opacity:"0.3",
				p_bg_color:"#e6e6e6",
				bg_color:"#f5f5f5",
				p_width:"390",
				p_height:"220",
				pop_title:"短信获取验证码"
			});}
	  );
	$("#tjbutsfid").click(function(){
		$("#convercontsid").popup({
				opacity:"0.3",
				p_bg_color:"#e6e6e6",
				bg_color:"#f5f5f5",
				p_width:"390",
				p_height:"220",
				pop_title:"短信获取验证码"
			});}
	  );
	 $("#tjbutssid").click(function(){
		$("#convercontsid").ccover();
		$("#convercontssid").popup({
				opacity:"0.3",
				p_bg_color:"#e6e6e6",
				bg_color:"#f5f5f5",
				p_width:"390",
				p_height:"220",
				pop_title:"短信获取验证码"
			});
		});
});