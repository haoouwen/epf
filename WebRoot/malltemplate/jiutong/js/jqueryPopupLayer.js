/*
 *弹出新的窗口层
 *bruinHu
*/
;(function($){  
   
	  $.fn.popup= function(options) {   

	      var opts = $.extend({pOpacity:"0.3",
		  					   ptBackground:"#CAD9E6",//标题div的背景颜色
							   pcBackground:"#FFFFFF",//内容的背景颜色
							   pWidth:"700",
							   pHeight:"550",
							   pTitle:"标题",
							   oprid:"opid"}, options);
		  
	      this.each(function(){
			     
			  var tpHeight=parseInt(opts.pHeight)+30;
			    	  
	      	  $("<div class='tPopupDiv'></div>").appendTo("body");
			  
			  //包括标题在内的Div弹出层
	      	  $(".tPopupDiv").css({"width":opts.pWidth+"px",
			                  "height":tpHeight+"px",
							  "background":opts.ptBackground,
							  "border":"1px solid #a3a3a3",
							  "padding":"5px"});
			  //加标题和关闭按钮			   
	      	  $(".tPopupDiv").append("<div class='pTitle'>"+opts.pTitle+"<img class='ptImg'  src='/include/bmember/images/close.png'/></div>");
	      	  $(".pTitle").css({"height":"28px",
			                    "line-height":"18px",
			                    "font-size":"14px",
								"font-weight":"bold",
								"position":"relative"});
	      	  $(".ptImg").css({"position":"absolute",
			                   "top":"-2px",
			                   "right":"0px",
							   "cursor":"pointer"});
			  
	      	  //内容的DIV
	      	  $(this).css({"width":opts.pWidth+"px","height":opts.pHeight+"px","background":opts.pcBackground});
			  
	          //设置位置
    	      var  scrHeight = $(document).scrollTop(),
    	   	       docHeight =  $(document).height(),
				   winWidth = $(window).width(),
			       winHeight = $(window).height(),
				   popWeight = $(this).width(),
			       popHeight = $(this).height(),
			       posTop = Math.ceil((winHeight - popHeight)/2) + Math.ceil(scrHeight),
				   //posTop = Math.ceil((winHeight - popHeight)/2),
			       posLeft = (winWidth - popWeight)/2;
				      
			  $(".tPopupDiv").css({"position":"absolute",
				                   "left":posLeft + "px",
			                       "top":posTop + "px",
								   "display":"block",
								   "z-index":100});
			  $(this).css({"display":"block"});
		  	  $(".tPopupDiv").append($(this));	
				
	          //满的背景层(一般透明黑色的背景)
   	   		  $("<div class='popLayer'></div>").appendTo("body");
   	   		  $(".popLayer").css({"width":"100%",
			                      "height":docHeight+'px',
				                  "opacity":opts.pOpacity,
			                      "background": "black",
								  "position":"absolute",
								  "left":"0px",
								  "top":"0px",
								  "z-index":99,
								  "overflow-x":"hidden"});
   	   		  $(".popLayer").fadeIn("slow");
			  //隐藏滚动条
			  $("html").css({"overflow":"hidden"});
			  
			  //图片按钮关闭			   
			   $(".ptImg").click(function(){
					$(".popLayer").remove();
     	  	  		$(".tPopupDiv").remove();
					$("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
					window.location.reload();//刷新页面
     	  	  });
			  //取消按钮关闭			   
			   $("#"+opts.oprid).click(function(){
					$(".popLayer").remove();
     	  	  		$(".tPopupDiv").remove();
					$("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
					window.location.reload();//刷新页面
     	  	  });  
		  }); 
	  }
	     
})(jQuery); 
/*
 *弹出新的窗口层
 *bruinHu
*/
;(function($){  
   
	  $.fn.pwpopup= function(options) {   

	      var opts = $.extend({pOpacity:"0.3",
		  					   ptBackground:"#CAD9E6",//标题div的背景颜色
							   pcBackground:"#FFFFFF",//内容的背景颜色
							   pWidth:"300",
							   pHeight:"150",
							   pTitle:"设置支付密码",
							   oprid:"opid"}, options);
		  
	      this.each(function(){
			  var tpHeight=parseInt(opts.pHeight)+30;
	      	  $("<div class='tPopupDiv'></div>").appendTo("body");
			  
			  //包括标题在内的Div弹出层
	      	  $(".tPopupDiv").css({"width":opts.pWidth+"px",
			                  "height":tpHeight+"px",
							  "background":opts.ptBackground,
							  "border":"1px solid #a3a3a3",
							  "padding":"10px"});
			  //加标题和关闭按钮			   
	      	  $(".tPopupDiv").append("<div class='pTitle'>"+opts.pTitle+"<img class='ptImg'  src='/include/bmember/images/close.png'/></div>");
	      	  $(".pTitle").css({"height":"28px",
			                    "line-height":"18px",
			                    "font-size":"14px",
								"font-weight":"bold",
								"position":"relative"});
	      	  $(".ptImg").css({"position":"absolute",
			                   "top":"-2px",
			                   "right":"0px",
							   "cursor":"pointer"});
			  
	      	  //内容的DIV
	      	  $(this).css({"width":opts.pWidth+"px","height":opts.pHeight+"px","background":opts.pcBackground});
			  
	          //设置位置
    	      var  scrHeight = $(document).scrollTop(),
    	   	       docHeight =  $(document).height(),
				   winWidth = $(window).width(),
			       winHeight = $(window).height(),
				   popWeight = $(this).width(),
			       popHeight = $(this).height(),
			       posTop = Math.ceil((winHeight - popHeight)/2) + Math.ceil(scrHeight),
				   //posTop = Math.ceil((winHeight - popHeight)/2),
			       posLeft = (winWidth - popWeight)/2;
				      
			  $(".tPopupDiv").css({"position":"absolute",
				                   "left":posLeft + "px",
			                       "top":posTop + "px",
								   "display":"block",
								   "z-index":100});
			  $(this).css({"display":"block"});
		  	  $(".tPopupDiv").append($(this));	
				
	          //满的背景层(一般透明黑色的背景)
   	   		  $("<div class='popLayer'></div>").appendTo("body");
   	   		  $(".popLayer").css({"width":"100%",
			                      "height":docHeight+'px',
				                  "opacity":opts.pOpacity,
			                      "background": "black",
								  "position":"absolute",
								  "left":"0px",
								  "top":"0px",
								  "z-index":99,
								  "overflow-x":"hidden"});
   	   		  $(".popLayer").fadeIn("slow");
			  //隐藏滚动条
			  $("html").css({"overflow":"hidden"});
			  
			  //图片按钮关闭			   
			   $(".ptImg").click(function(){
					$(".popLayer").remove();
     	  	  		$(".tPopupDiv").remove();
					$("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
					window.location.reload();//刷新页面
     	  	  });
			  //取消按钮关闭			   
			   $("#"+opts.oprid).click(function(){
					$(".popLayer").remove();
     	  	  		$(".tPopupDiv").remove();
					$("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
					window.location.reload();//刷新页面
     	  	  });  
		  }); 
	  }
	     
})(jQuery); 

