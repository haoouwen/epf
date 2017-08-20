/*
 *弹出新的窗口层
 *bruinHu
*/
(function($){  
	  var thisCont = 0;
	  var dragFalg = false;
      var startPostX, startPosY;
	  
	  $.fn.popup= function(options) {   

	      var opts = $.extend({pOpacity:"0.5",
		  					   pcBackground:"#FFFFFF",//内容背景
		  					   ptBackground:"#f3f3f3",//标题背景
							   pWidth:"700",
							   pHeight:"550",
							   pTitle:"标题"}, options);
	     
		  
	      this.each(function(){
			  
			  var tpHeight=parseInt(opts.pHeight)+30;
			  thisCont = $(this);
			  
	      	  $("<div class='tPopupDiv'></div>").appendTo("body");
			  
			  //包括标题在内的Div弹出层
	      	  $(".tPopupDiv").css({"width":opts.pWidth+"px",
			                  "height":tpHeight+"px",
							  "background":opts.pcBackground,
							  "border":"5px solid #c2c2c2",
							  "border-radius":"2px"
							  });
			  //加标题和关闭按钮			   
	      	  $(".tPopupDiv").append("<div class='pTitle'>"+opts.pTitle+"<img class='ptImg'  src='/malltemplate/bmall/images/close.png'/></div>");
	      	  $(".pTitle").css({"height":"32px",
			                    "line-height":"32px",
			                    "font-size":"14px",
								"font-family":"微软雅黑",
								"padding":"0 10px",
								"background":opts.ptBackground,
								"position":"relative"});
	      	  $(".ptImg").css({"position":"absolute",
			                   "top":"5px",
			                   "right":"5px",
							   "cursor":"pointer"});
			  
	      	  //内容的DIV
	      	  thisCont.css({"width":opts.pWidth+"px","height":opts.pHeight+"px","background":opts.pcBackground});
			  
	          //设置位置
    	      var  scrHeight = $(document).scrollTop(),
    	   	       docHeight =  $(document).height(),
				   winWidth = $(window).width(),
			       winHeight = $(window).height(),
				   popWeight = $(this).width(),
			       popHeight = $(this).height(),
			       posTop = (winHeight - popHeight)/2,
			       posLeft = (winWidth - popWeight)/2;
				   
			  $(".tPopupDiv").css({"position":"fixed",
				                   "left":posLeft + "px",
			                       "top":posTop + "px",
								   "display":"block",
								   "z-index":100});
			  thisCont.show();
		  	  $(".tPopupDiv").append(thisCont);	
				
	          //满的背景层(一般透明黑色的背景)
   	   		  $("<div class='popLayer'></div>").appendTo("body");
   	   		  $(".popLayer").css({"width":"100%",
			                      "height":docHeight+'px',
				                  "opacity":opts.pOpacity,
			                      "background": "black",
								  "position":"fixed",
								  "left":"0px",
								  "top":"0px",
								  "z-index":99,
								  "overflow-x":"hidden"});
   	   		  $(".popLayer").fadeIn("slow");
			  //隐藏滚动条
			  $("html").css({"overflow":"hidden"});
			  
			  //关闭			   
			   $(".ptImg").click(function(){
					$(".popLayer").remove();
     	  	  		$(".tPopupDiv").remove();
					thisCont.appendTo("body");
					thisCont.hide();
					$("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
     	  	  }); 
			  //拖动
			  $(".tPopupDiv").mousedown(function(e){
					   dragFalg = true;
					   sPosX = e.clientX - this.offsetLeft;
					   sPosY = e.clientY - this.offsetTop;
				});
			  document.onmousemove = function(e) {
					if (dragFalg) {
					var e = e || window.event;
					var lPosX = e.clientX - sPosX;
					var lPosY = e.clientY - sPosY ;
					$(".tPopupDiv").css({"left":lPosX + "px", "top":lPosY + "px"});
					return false;
				 }
			   };
			   $(".tPopupDiv").mouseup(function(e) {
					dragFalg = false;
					e.cancelBubble = true;
			  }); 
			  	  
		  }); 
	  }
	  //保存
	  $.fn.saveBut = function(){
		  
		   $(".saveBut").click(function(){
					$(".popLayer").remove();
     	  	  		$(".tPopupDiv").remove();
					thisCont.appendTo("body");
					thisCont.hide();
					$("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
     	  	 }); 
	  }
})(jQuery); 