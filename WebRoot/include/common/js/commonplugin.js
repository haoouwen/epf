// 创建tab页切换一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.mallTab = function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.mallTab.defaults, options);
	      //初始化方法
	      var tab_li=$(this).find("."+opts.indexId).find("li");
	      var tab_div=$(this).find("."+opts.indexDiv);
	      this.each(function(){    
	      	   	tab_li.each(function(i){
	      	   		$(this).click(function(){
	      	   		     var index=$(this).index();//获取当前点击的li索引 	
	      	   		     tab_li.removeClass(opts.selected);//去除所有样式
		      	   		 $(this).addClass(opts.selected);//为当前选择框增加样式
		      	   		 var displayIndexStr=opts.displayIndex;//获取设置全显DIV的值		      	   		 
	      	   			 if(displayIndexStr!=null&&displayIndexStr.indexOf(index)>-1){
	      	   			  	 tab_div.css("display","block");
	      	   			 }else{
		      	   			 tab_div.css("display","none");//隐藏所有的tabdiv  	      	   			   			 
		      	   			 tab_div.eq(index).css("display","block")//显示对应的tabdiv
		      	   		}
	      	   		});	      	   
	      	   });
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.mallTab.defaults = {  
	  	 //选择的样式名称
	  	 selected:"selected",
	  	 //显示所有DIV的索引, 如果有多个值以,隔开
	  	 displayIndex:"",
	  	 indexId:"tabbar",
	  	 indexDiv:"tabdiv"
	  };	
// 闭包结束  
})(jQuery);   


// 创建tab页切换一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.mallTabp = function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.mallTabp.defaults, options);
	      //初始化方法
	      var tab_li=$(this).find("."+opts.indexId).find("li");
	      var tab_div=$(this).find("."+opts.indexDiv);
	      this.each(function(){    
	      	   	tab_li.each(function(i){
	      	   		$(this).click(function(){
	      	   		     var index=$(this).index();//获取当前点击的li索引 	
	      	   		     tab_li.removeClass(opts.selected);//去除所有样式
		      	   		 $(this).addClass(opts.selected);//为当前选择框增加样式
		      	   			 tab_div.css("display","none");//隐藏所有的tabdiv  	      	   			   			 
		      	   			 tab_div.eq(index).css("display","block")//显示对应的tabdiv
	      	   		});	      	   
	      	   });
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.mallTabp.defaults = {  
	  	 //选择的样式名称
	  	 selected:"pselected",
	  	 //显示所有DIV的索引, 如果有多个值以,隔开
	  	 displayIndex:"",
	  	 indexId:"ptabbar",
	  	 indexDiv:"ptabdiv"
	  };	
// 闭包结束  
})(jQuery);   






// 创建table隔行变色一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.mallTable = function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.mallTable.defaults, options); 
	      //定义全局的背景颜色变化  
	      var tr_backcolor="";	 
	      //初始化方法
	      this.each(function(){  
	      	   //定义表格的变量  
	      	   var $table=$(this);
	      	   //初始化表格颜色
	      	   $table.find("tr:gt(0)").each(function(i){
 		      	   if(i%2==0){
 		      	   		$(this).css("background",opts.even_color);
 		      	   }else{
 		      	   		$(this).css("background",opts.odd_color);
 		      	   }
		       });
		       //鼠标移动隔行变色
	      	   $(this).find("tr:gt(0)").each(function(){		 		      	 		      	 
					  $(this).hover(
						  function () {
							  tr_backcolor=$(this).css("background-Color"); //得到行本身颜色							  
						      $(this).css("background",opts.float_color);
						  },
						  function () {
						      $(this).css("background",tr_backcolor);
					      }
					  );
		 	   });
		 	   //选中checkbox变色
		 	   $(this).find("input:checkbox").click(function(){	
		 	   		  var check_tr=$(this).parent("td").parent("tr");	 	   				
		 		      if(this.checked){
		 		      	   check_tr.css("background",opts.check_color);
		 		      }else{
		 		      	   var tr_index=check_tr.index();
		 		      	   if(tr_index%2==0){
		 		      	   		check_tr.css("background",opts.odd_color);
		 		      	   }else{
		 		      	   		check_tr.css("background",opts.even_color);
		 		      	   }
		 		      }
		 		      tr_backcolor=check_tr.css("background-Color");
		 	   });
		 	   //当选中第一个checkbox全选变色
		 	   $(this).find("input:checkbox").eq(0).click(function(){	
		 		      if(this.checked){
		 		      	   $table.find("tr:gt(0)").css("background",opts.check_color);
		 		      }else{
		 		      	   $table.find("tr:gt(0)").each(function(i){
			 		      	   if(i%2==0){
			 		      	   		$(this).css("background",opts.even_color);
			 		      	   }else{
			 		      	   		$(this).css("background",opts.odd_color);
			 		      	   }
		 		      	   });
		 		      }
		 	   });
		 	   //查找当前表格下是否存在已选中的checkbox框
	      	   $(this).find("input:checkbox").each(function(){
	      	   		if(this.checked){
	      	   			$(this).parent("td").parent("tr").css("background",opts.check_color);
	      	   		}
	      	   });
		  }); 
	  };
	  // 插件的defaults默认配置  
	  $.fn.mallTable.defaults = {  
	  	  even_color:"#FFFFFF",//偶数行颜色
	  	  odd_color:"#F2F2F2", //奇数行颜色 	   
	  	  float_color:"#F6DDA1", //鼠标移动tr上颜色
	  	  check_color:"#FFFFCC"  //checkbox选中的颜色 
	  };
// 闭包结束  
})(jQuery);   


// 创建弹出层绝对居中一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.popup= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup.defaults, options);
	      //私有方法
	      var pm =popupMethod;
	      var dragFalg = false;
          var startPostX, startPosY;
	      //初始化方法
	      this.each(function(){    
	      	  //获取需要弹出层控件的ID
	      	  var popup_id = $(this).attr("id");
	      	  //创建一个父DIV框
	      	  if($("#"+opts.parert_id).length<=0){
	      	     $("<div id='"+opts.parert_id+"' class='"+opts.parert_id+"'></div>").appendTo("body");
	      	     $("#"+opts.parert_id).append("<div id='"+opts.parert_title+"'><table width='100%' style='margin:-6px 0px 6px 0px;'><tr><td align='left'><b id='p_p_b'>"+opts.pop_title+"</b></td><td align='right'><img id='"+opts.cover_img+"'  src='/include/common/images/closecover.png'/></td></tr></table></div>");
		         $("#"+opts.parert_title).css({"height":"25px"});
		         $("#p_c_img").css({"float":"right","margin":"0px -3px auto","cursor":"pointer"});
		         $("#p_p_b").css({"padding-left":"8px","margin-top":"-30px"});
	      	  }
	      	  var p_p_height=parseInt(opts.p_height)+20;//父框的高度
		      $("#"+opts.parert_id).css({"width":opts.p_width+"px","height":p_p_height+"px","background":opts.p_bg_color,"border":"1px solid #3D596F","padding":"10px"});
		      //初始化高度与宽度
		      $(this).css({"width":opts.p_width+"px","height":opts.p_height+"px","background":opts.bg_color});
		      //当存在这个父DIV时
	      	  if($("#"+opts.parert_id).length>0){
	      	     $("#"+opts.parert_id).show();
	      	  }
	          //获取窗体的宽度与高度
    	      var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	   	  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2 + eval(_scrollHeight+opts.move_top);
			  var _posiLeft = (_windowWidth - _popupWeight)/2+opts.move_left;
	          //新建一个DIV遮盖层
	          if($("#"+opts.parent_cover).length<=0){
	         	 $("<div id='"+opts.parent_cover+"' class='cover'></div>").appendTo("body");	
	          }else{
	          	 $("#"+opts.parent_cover).show();
	          }
   	   		  $("#"+opts.parent_cover).height(_documentHeight);
   	   		  $("#"+opts.parent_cover).width("100%");
   	   		  $("#"+opts.parent_cover).css({"opacity":opts.opacity, background: "black","position":"fixed","left":"0px","top":"0px","z-index":opts.cover_index-1});
   	   		  $("#"+opts.parent_cover).fadeIn("slow");
   	   		  //设置position
			  $("#"+opts.parert_id).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"fixed","z-index":opts.cover_index});
			  $(this).css({"display":"block"});
		  	  $("#"+opts.parert_id).append($(this));	
		  	  //渲染方法
		  	  pm._close_cover(popup_id,opts);
		  	
		  //拖动
	  	   $("#"+opts.parert_id).mousedown(function(e) {
           dragFalg = true;
           sPosX = e.clientX - this.offsetLeft;
           sPosY = e.clientY - this.offsetTop;
	      });
	       document.onmousemove = function(e) {
				if (dragFalg) {
				var e = e || window.event;
				var lPosX = e.clientX - sPosX;
				var lPosY = e.clientY - sPosY ;
				$("#"+opts.parert_id).css({"left":lPosX + "px", "top":lPosY + "px"});
				return false;
			 }
		   };
		   $("#"+opts.parert_id).mouseup(function(e) {
				dragFalg = false;
				 $("#"+opts.parert_id)[0].releaseCapture();
				e.cancelBubble = true;
		    }) 	  
		  	  
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.popup.defaults = {  
	     opacity:"0.3",
	 	 p_bg_color:"#CAD9E6",
	 	 bg_color:"#FFFFFF",
	 	 p_width:"700",
	 	 p_height:"500",
	 	 pop_title:"标题",
	 	 otherMethod:"",
	 	 parert_id:"divPanel",
	 	 parert_title:"divTitle",
	 	 parent_cover:"p_cover",
	 	 cover_index:"8888",
	 	 cover_img:"p_c_img",
	 	 move_left:0,
	 	 move_top:0
	  };	
	  
	 //私有方法 
     var popupMethod={
     	  _close_cover:function(popup_id,opts){
     	  	  $("#"+opts.cover_img).click(function(){
     	  	        //获取需要隐藏的规格项
     	  	  		$("#"+opts.parert_id).hide();
     	  	  		$("#"+opts.parent_cover).hide();
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
  		$("#"+opts.parert_id).remove();
  		$("#"+opts.parent_cover).remove();
  		$("<div id='"+popup_id+"' class='"+popup_id+"'></div>").appendTo("body");	
  		$("#"+popup_id).html(sizeHtml);
		$("#"+popup_id).css({"display":"none"});
		if(opts.otherMethod!=""){
			eval(opts.otherMethod);
		}
	 }  
	 
// 闭包结束  
})(jQuery); 


 // 创建弹出层一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.lighttip= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.lighttip.defaults, options);
	      //初始化方法
	      this.each(function(){   	       	 
	          $(this).hover(function() {	
	          	    var alt=$(this).attr("alt");          
	          		var X = $(this).offset().top-5;
					var Y = $(this).offset().left+25;					
	        	 	$("<div id='showtip' class='showtip'></div>").appendTo("body");
	        	 	$("#showtip").html(alt);
	        	 	var _tipHeight=$("#showtip").height(),
	        	 	_tipWidth=$("#showtip").width();	        	 	
	        	 	$("#showtip").css({"left": Y + "px","top":X + "px","position":"absolute"});	        	 	
					
			   }, function() {
					$("#showtip").remove();
			  });
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.lighttip.defaults = {  
	 
	  };	
// 闭包结束  
})(jQuery); 


//滚动插件到指定位置
(function ($) {
    $.scrollToID = $.fn.scrollToID = function(id, gap){
        gap = !isNaN(Number(gap))? gap : 50;
        var X = $(id).offset().left + $(this).scrollLeft() - gap;
        var Y = $(id).offset().top + $(this).scrollTop() - gap;
		$("html,body").animate({scrollTop:Y},1000);
    };
})(jQuery);


 // 创建弹出层一个闭包 用于前台支付的
(function($) {  
	    // 插件的定义  
	  $.fn.tipshow= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.tipshow.defaults, options);
	      //私有方法
	      var pm =popupMethod;
	     var dragFalg = false;
          var startPostX, startPosY;
	      //初始化方法
	      this.each(function(){    
	      	  //获取需要弹出层控件的ID
	      	  var popup_id = $(this).attr("id");
	      	  //创建一个父DIV框
	      	  if($("#"+opts.parert_id).length<=0){
	      	     $("<div  id='"+opts.parert_id+"' class='"+opts.parert_id+"'></div>").appendTo("body");
	      	     $("#"+opts.parert_id).append("<div id='"+opts.parert_title+"'><table width='100%' style='margin:-6px 0px 6px 0px;'><tr><td align='left'><b id='p_p_b'>"+opts.pop_title+"</b></td><td align='right'></td></tr></table></div>");
		         $("#"+opts.parert_title).css({"height":"25px"});
		         $("#p_c_img").css({"float":"right","margin":"0px -3px auto","cursor":"pointer"});
		         $("#p_p_b").css({"padding-left":"8px","margin-top":"-30px"});
	      	  }
	      	  var p_p_height=parseInt(opts.p_height)+17;//父框的高度
		      $("#"+opts.parert_id).css({"width":opts.p_width+"px","height":p_p_height+"px","background":opts.p_bg_color,"border":"1px solid #3D596F","padding":"10px"});
		      //初始化高度与宽度
		      $(this).css({"width":opts.p_width+"px","height":opts.p_height+"px","background":opts.bg_color});
		      //当存在这个父DIV时
	      	  if($("#"+opts.parert_id).length>0){
	      	     $("#"+opts.parert_id).show();
	      	  }
	          //获取窗体的宽度与高度
    	      var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	   	  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2 + eval(_scrollHeight+opts.move_top);
			  var _posiLeft = (_windowWidth - _popupWeight)/2+opts.move_left;
	          //新建一个DIV遮盖层
	          if($("#"+opts.parent_cover).length<=0){
	         	 $("<div id='"+opts.parent_cover+"' class='cover'></div>").appendTo("body");	
	          }else{
	          	 $("#"+opts.parent_cover).show();
	          }
   	   		  $("#"+opts.parent_cover).height(_documentHeight);
   	   		  $("#"+opts.parent_cover).width("100%");
   	   		  $("#"+opts.parent_cover).css({"opacity":opts.opacity, background: "black","position":"fixed","left":"0px","top":"0px","z-index":opts.cover_index-1});
   	   		  $("#"+opts.parent_cover).fadeIn("slow");
   	   		  //设置position
			  $("#"+opts.parert_id).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"fixed","z-index":opts.cover_index});
			  $(this).css({"display":"block"});
		  	  $("#"+opts.parert_id).append($(this));	
		  	  //渲染方法
		  	  pm._close_cover(popup_id,opts);
		  	  	
			  //拖动
		  	   $("#"+opts.parert_id).mousedown(function(e) {
	           dragFalg = true;
	           sPosX = e.clientX - this.offsetLeft;
	           sPosY = e.clientY - this.offsetTop;
		      });
		       document.onmousemove = function(e) {
					if (dragFalg) {
					var e = e || window.event;
					var lPosX = e.clientX - sPosX;
					var lPosY = e.clientY - sPosY ;
					$("#"+opts.parert_id).css({"left":lPosX + "px", "top":lPosY + "px"});
					return false;
				 }
			   };
			   $("#"+opts.parert_id).mouseup(function(e) {
					dragFalg = false;
					 $("#"+opts.parert_id)[0].releaseCapture();
					e.cancelBubble = true;
			    }) 	  
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.tipshow.defaults = {  
	     opacity:"0.3",
	 	 p_bg_color:"#CAD9E6",
	 	 bg_color:"#FFFFFF",
	 	 p_width:"400",
	 	 p_height:"200",
	 	 pop_title:"标题",
	 	 otherMethod:"",
	 	 parert_id:"divPanel",
	 	 parert_title:"divTitle",
	 	 parent_cover:"p_cover",
	 	 cover_index:"88880",
	 	 cover_img:"p_c_img",
	 	 move_left:0,
	 	 move_top:0
	  };	
	   //私有方法 
     var popupMethod={
     	  _close_cover:function(popup_id,opts){
     	  	  $("#"+opts.cover_img).click(function(){
     	  	        //获取需要隐藏的规格项
     	  	  		$("#"+opts.parert_id).hide();
     	  	  		$("#"+opts.parent_cover).hide();
	 		 		$("#"+popup_id).css({"display":"none"});
     	  	  }); 
     	  	  if(opts.otherMethod!=""){
				    eval(opts.otherMethod);
			  }
          }
     };
// 闭包结束  
})(jQuery); 


// 创建弹出层绝对居中一个闭包  
(function($) {  
	  // 插件的定义  
	  $.fn.popup_search= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup_search.defaults, options);
	      //私有方法
	      var pm =popupMethod;
	      var dragFalg = false;
          var startPostX, startPosY;
	      //初始化方法
	      this.each(function(){    
	      	  //获取需要弹出层控件的ID
	      	  var popup_id = $(this).attr("id");
	      	  //创建一个父DIV框
	      	  if($("#"+opts.parert_id).length<=0){
	      	 	  $("<div id='"+opts.parert_id+"' class='"+opts.parert_id+"'></div>").appendTo("body");	
	      	 	  var p_p_height=parseInt(opts.p_height)-10;//父框的高度
		      	  var p_p_width=parseInt(opts.p_width)+10;
		      	  $("#"+opts.parert_id).css({"width":p_p_width+"px","background":opts.p_bg_color,"border":"1px solid #3D596F","padding":"10px"});
		      	  $("#"+opts.parert_id).append("<div id='"+opts.parert_title+"'><table width='100%' style='margin:-6px 0px 6px 0px;'><tr><td align='left'><b id='p_p_b'>"+opts.pop_title+"</b></td><td align='right' class='img_td'><img id='"+opts.cover_img+"'  src='/include/admin/images/closecover.png'/></td></tr></table></div>");
		      	  $("#"+opts.parert_title).css({"height":"25px"});
		      	  $("#p_c_img").css({"float":"right","margin":"-1px -2px auto","cursor":"pointer"});
		      	  $("#p_p_b").css({"padding-left":"8px","margin-top":"-30px"});
		      	  //初始化高度与宽度
		      	  $(this).css({"width":opts.p_width+"px","height":opts.p_height+"px","background":opts.bg_color});
	      	  }else{
	      	  	 $("#"+opts.parert_id).show();
	      	  }
	          //获取窗体的宽度与高度
    	      var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	   	  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2-65 ;
			  var _posiLeft = (_windowWidth - _popupWeight)/2+opts.move_left;
	          //新建一个DIV遮盖层
	          if($("#"+opts.parent_cover).length<=0){
	         	 $("<div id='"+opts.parent_cover+"' class='cover'></div>").appendTo("body");	
	          }else{
	          	 $("#"+opts.parent_cover).show();
	          }
   	   		  $("#"+opts.parent_cover).height(_windowHeight);
   	   		  $("#"+opts.parent_cover).width("100%");
   	   		  $("#"+opts.parent_cover).css({"opacity":opts.opacity, background: "black","position":"fixed","left":"0px","top":"0px","z-index":opts.cover_index-1});
   	   		  $("#"+opts.parent_cover).fadeIn("slow");
   	   		  //设置position
			  $("#"+opts.parert_id).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"fixed","z-index":opts.cover_index});
			  $(this).css({"display":"block"});
		  	  $("#"+opts.parert_id).append($(this));	
		  	  //渲染方法
		  	  pm._close_cover(popup_id,opts);
		  	  
		  	  
		     //拖动
		  	   $("#"+opts.parert_id).mousedown(function(e) {
	           dragFalg = true;
	           sPosX = e.clientX - this.offsetLeft;
	           sPosY = e.clientY - this.offsetTop;
		      });
		       document.onmousemove = function(e) {
					if (dragFalg) {
					var e = e || window.event;
					var lPosX = e.clientX - sPosX;
					var lPosY = e.clientY - sPosY ;
					$("#"+opts.parert_id).css({"left":lPosX + "px", "top":lPosY + "px"});
					return false;
				 }
			   };
			   $("#"+opts.parert_id).mouseup(function(e) {
					dragFalg = false;
					 $("#"+opts.parert_id)[0].releaseCapture();
					e.cancelBubble = true;
			    }) 	  
		    
		    
		    
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.popup_search.defaults = {  
	     opacity:"0.3",
	 	 p_bg_color:"#CAD9E6",
	 	 bg_color:"#FFFFFF",
	 	 p_width:"400",
	 	 p_height:"300",
	 	 pop_title:"标题",
	 	 otherMethod:"",
	 	 parert_id:"divPanel",
	 	 parert_title:"divTitle",
	 	 parent_cover:"p_cover",
	 	 cover_index:"8888",
	 	 cover_img:"p_c_img",
	 	 move_left:0,
	 	 move_top:0
	  };	
	  
	 //私有方法 
     var popupMethod={
     	  _close_cover:function(popup_id,opts){
     	  	  $("#"+opts.cover_img).click(function(){
		        //获取需要隐藏的规格项
		        var sizeHtml =$("#"+popup_id).html();
     	  	        //获取需要隐藏的规格项
     	  	  		$("#"+opts.parert_id).remove();
  					$("#"+opts.parent_cover).remove();
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
	    var opts = $.extend({}, $.fn.popup_search.defaults, options);
	    var popup_id = $(this).attr("id");
        //获取需要隐藏的规格项
        var sizeHtml =$("#"+popup_id).html();
  		$("#"+opts.parert_id).remove();
  		$("#"+opts.parent_cover).remove();
  		$("<div id='"+popup_id+"' class='"+popup_id+"'></div>").appendTo("body");	
  		$("#"+popup_id).html(sizeHtml);
		$("#"+popup_id).css({"display":"none"});
		if(opts.otherMethod!=""){
			eval(opts.otherMethod);
		}
	 }  
	 
// 闭包结束  
})(jQuery); 
// 创建弹出层绝对居中一个闭包  用于打印票据
(function($) {  
	  // 插件的定义  
	  $.fn.popup_invoice= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup_search.defaults, options);
	      //私有方法
	       
	      var pm =popupMethod;
	     var dragFalg = false;
          var startPostX, startPosY;
	      //初始化方法
	      this.each(function(){    
	      	  //获取需要弹出层控件的ID
	      	  var popup_id = $(this).attr("id");
	      	  //创建一个父DIV框
	      	  if($("#"+opts.parert_id).length<=0){
	      	 	  $("<div id='"+opts.parert_id+"' class='"+opts.parert_id+"'></div>").appendTo("body");	
	      	 	  var p_p_height=parseInt(opts.p_height)-10;//父框的高度
		      	  var p_p_width=parseInt(opts.p_width)+10;
		      	  $("#"+opts.parert_id).css({"width":p_p_width+"px","background":opts.p_bg_color,"border":"1px solid #3D596F","padding":"10px"});
		      	  $("#"+opts.parert_id).append("<div id='"+opts.parert_title+"'><table width='100%' style='margin:-6px 0px 6px 0px;'><tr><td align='left'><b id='p_p_b'>"+opts.pop_title+"</b></td><td align='right'><img id='"+opts.cover_img+"'  src='/include/admin/images/closecover.png'/></td></tr></table></div>");
		      	  $("#"+opts.parert_title).css({"height":"25px"});
		      	  $("#p_c_img").css({"float":"right","margin":"-1px -2px auto","cursor":"pointer"});
		      	  $("#p_p_b").css({"padding-left":"8px","margin-top":"-30px"});
		      	  //初始化高度与宽度
		      	  $(this).css({"width":opts.p_width+"px","height":opts.p_height+"px","background":opts.bg_color});
	      	  }else{
	      	  	 $("#"+opts.parert_id).show();
	      	  }
	          //获取窗体的宽度与高度
    	      var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	   	  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2-65 ;
			  var _posiLeft = (_windowWidth - _popupWeight)/2+opts.move_left;
	          //新建一个DIV遮盖层
	          if($("#"+opts.parent_cover).length<=0){
	         	 $("<div id='"+opts.parent_cover+"' class='cover'></div>").appendTo("body");	
	          }else{
	          	 $("#"+opts.parent_cover).show();
	          }
   	   		  $("#"+opts.parent_cover).height(_windowHeight);
   	   		  $("#"+opts.parent_cover).width("100%");
   	   		  $("#"+opts.parent_cover).css({"opacity":opts.opacity, background: "black","position":"fixed","left":"0px","top":"0px","z-index":opts.cover_index-1});
   	   		  $("#"+opts.parent_cover).fadeIn("slow");
   	   		  //设置position
			  $("#"+opts.parert_id).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"fixed","z-index":opts.cover_index});
			  $(this).css({"display":"block"});
		  	  $("#"+opts.parert_id).append($(this));	
		  	  //渲染方法
		  	  pm._close_cover(popup_id,opts);
		  	  
		  	   //拖动
		  	   $("#"+opts.parert_id).mousedown(function(e) {
	           dragFalg = true;
	           sPosX = e.clientX - this.offsetLeft;
	           sPosY = e.clientY - this.offsetTop;
		      });
		       document.onmousemove = function(e) {
					if (dragFalg) {
					var e = e || window.event;
					var lPosX = e.clientX - sPosX;
					var lPosY = e.clientY - sPosY ;
					$("#"+opts.parert_id).css({"left":lPosX + "px", "top":lPosY + "px"});
					return false;
				 }
			   };
			   $("#"+opts.parert_id).mouseup(function(e) {
					dragFalg = false;
					 $("#"+opts.parert_id)[0].releaseCapture();
					e.cancelBubble = true;
			    }) 	  
		  	  
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.popup_search.defaults = {  
	     opacity:"0.3",
	 	 p_bg_color:"#CAD9E6",
	 	 bg_color:"#FFFFFF",
	 	 p_width:"400",
	 	 p_height:"300",
	 	 pop_title:"标题",
	 	 otherMethod:"",
	 	 parert_id:"divPanel1",
	 	 parert_title:"divTitle1",
	 	 parent_cover:"p_cover1",
	 	 cover_index:"8888",
	 	 cover_img:"p_c_img",
	 	 move_left:0,
	 	 move_top:0
	  };	
	  
	 //私有方法 
     var popupMethod={
     	  _close_cover:function(popup_id,opts){
     	  	  $("#"+opts.cover_img).click(function(){
		        //获取需要隐藏的规格项
		        var sizeHtml =$("#"+popup_id).html();
     	  	        //获取需要隐藏的规格项
     	  	  		$("#"+opts.parert_id).remove();
  					$("#"+opts.parent_cover).remove();
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
	 $.fn.invoice_ccover= function(options) {
	 	
	 	//获取设置插件的选项	 
	    var opts = $.extend({}, $.fn.popup_search.defaults, options);
	    var popup_id = $(this).attr("id");
        //获取需要隐藏的规格项
        var sizeHtml =$("#"+popup_id).html();
  		$("#"+opts.parert_id).remove();
  		$("#"+opts.parent_cover).remove();
  		$("<div id='"+popup_id+"' class='"+popup_id+"'></div>").appendTo("body");	
  		$("#"+popup_id).html(sizeHtml);
		$("#"+popup_id).css({"display":"none"});
		if(opts.otherMethod!=""){
			eval(opts.otherMethod);
		}
	 }  
	 
// 闭包结束  
})(jQuery);

// 创建弹出层绝对居中一个闭包  用于修改运费
(function($) {  
	  // 插件的定义  
	  $.fn.popup_ship= function(options) {   
	  	  //获取设置插件的选项	 
	      var opts = $.extend({}, $.fn.popup_search.defaults, options);
	      //私有方法
	      var pm =popupMethod;
	      var dragFalg = false;
          var startPostX, startPosY;
	      //初始化方法
	      this.each(function(){    
	      	  //获取需要弹出层控件的ID
	      	  var popup_id = $(this).attr("id");
	      	  //创建一个父DIV框
	      	  if($("#"+opts.parert_id).length<=0){
	      	 	  $("<div id='"+opts.parert_id+"' class='"+opts.parert_id+"'></div>").appendTo("body");	
	      	 	  var p_p_height=parseInt(opts.p_height)-10;//父框的高度
		      	  var p_p_width=parseInt(opts.p_width)+10;
		      	  $("#"+opts.parert_id).css({"width":p_p_width+"px","background":opts.p_bg_color,"border":"1px solid #3D596F","padding":"10px"});
		      	  $("#"+opts.parert_id).append("<div id='"+opts.parert_title+"'><table width='100%' style='margin:-6px 0px 6px 0px;'><tr><td align='left'><b id='p_p_b'>"+opts.pop_title+"</b></td><td align='right'><img id='"+opts.cover_img+"'  src='/include/admin/images/closecover.png'/></td></tr></table></div>");
		      	  $("#"+opts.parert_title).css({"height":"25px"});
		      	  $("#p_c_img").css({"float":"right","margin":"-1px -2px auto","cursor":"pointer"});
		      	  $("#p_p_b").css({"padding-left":"8px","margin-top":"-30px"});
		      	  //初始化高度与宽度
		      	  $(this).css({"width":opts.p_width+"px","height":opts.p_height+"px","background":opts.bg_color});
	      	  }else{
	      	  	 $("#"+opts.parert_id).show();
	      	  }
	          //获取窗体的宽度与高度
    	      var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
    	   	  _documentHeight =  $(document).height(),//获取当前文档的高度
			  _windowHeight = $(window).height(),//获取当前窗口高度
			  _windowWidth = $(window).width(),//获取当前窗口宽度
			  _popupHeight = $(this).height(),//获取弹出层高度
			  _popupWeight = $(this).width();//获取弹出层宽度
			  var _posiTop = (_windowHeight - _popupHeight)/2-65 ;
			  var _posiLeft = (_windowWidth - _popupWeight)/2+opts.move_left;
	          //新建一个DIV遮盖层
	          if($("#"+opts.parent_cover).length<=0){
	         	 $("<div id='"+opts.parent_cover+"' class='cover'></div>").appendTo("body");	
	          }else{
	          	 $("#"+opts.parent_cover).show();
	          }
   	   		  $("#"+opts.parent_cover).height(_windowHeight);
   	   		  $("#"+opts.parent_cover).width("100%");
   	   		  $("#"+opts.parent_cover).css({"opacity":opts.opacity, background: "black","position":"fixed","left":"0px","top":"0px","z-index":opts.cover_index-1});
   	   		  $("#"+opts.parent_cover).fadeIn("slow");
   	   		  //设置position
			  $("#"+opts.parert_id).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block","position":"fixed","z-index":opts.cover_index});
			  $(this).css({"display":"block"});
		  	  $("#"+opts.parert_id).append($(this));	
		  	  //渲染方法
		  	  pm._close_cover(popup_id,opts);
		  	  
		  	   //拖动
		  	   $("#"+opts.parert_id).mousedown(function(e) {
	           dragFalg = true;
	           sPosX = e.clientX - this.offsetLeft;
	           sPosY = e.clientY - this.offsetTop;
		      });
		       document.onmousemove = function(e) {
					if (dragFalg) {
					var e = e || window.event;
					var lPosX = e.clientX - sPosX;
					var lPosY = e.clientY - sPosY ;
					$("#"+opts.parert_id).css({"left":lPosX + "px", "top":lPosY + "px"});
					return false;
				 }
			   };
			   $("#"+opts.parert_id).mouseup(function(e) {
					dragFalg = false;
					 $("#"+opts.parert_id)[0].releaseCapture();
					e.cancelBubble = true;
			    }) 	  
		  	  
		  }); 
	  };  
	  // 插件的defaults默认配置  
	  $.fn.popup_search.defaults = {  
	     opacity:"0.3",
	 	 p_bg_color:"#CAD9E6",
	 	 bg_color:"#FFFFFF",
	 	 p_width:"500",
	 	 p_height:"300",
	 	 pop_title:"标题",
	 	 otherMethod:"",
	 	 parert_id:"divPanel2",
	 	 parert_title:"divTitle2",
	 	 parent_cover:"p_cover2",
	 	 cover_index:"8888",
	 	 cover_img:"p_c_img",
	 	 move_left:0,
	 	 move_top:0
	  };	
	  
	 //私有方法 
     var popupMethod={
     	  _close_cover:function(popup_id,opts){
     	  	  $("#"+opts.cover_img).click(function(){
		        //获取需要隐藏的规格项
		        var sizeHtml =$("#"+popup_id).html();
     	  	        //获取需要隐藏的规格项
     	  	  		$("#"+opts.parert_id).remove();
  					$("#"+opts.parent_cover).remove();
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
	 $.fn.ship_ccover= function(options) {
	 	
	 	//获取设置插件的选项	 
	    var opts = $.extend({}, $.fn.popup_search.defaults, options);
	    var popup_id = $(this).attr("id");
        //获取需要隐藏的规格项
        var sizeHtml =$("#"+popup_id).html();
  		$("#"+opts.parert_id).remove();
  		$("#"+opts.parent_cover).remove();
  		$("<div id='"+popup_id+"' class='"+popup_id+"'></div>").appendTo("body");	
  		$("#"+popup_id).html(sizeHtml);
		$("#"+popup_id).css({"display":"none"});
		if(opts.otherMethod!=""){
			eval(opts.otherMethod);
		}
	 }  
	 
// 闭包结束  
})(jQuery);

