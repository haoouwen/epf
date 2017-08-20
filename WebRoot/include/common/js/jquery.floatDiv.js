/*任意位置浮动固定层,可以让指定的层浮动到网页上的任何位置，当滚动条滚动时它会保持在当前位置不变，不会产生闪动*/
/*当自定义right位置时无效，这里加上一个判断
有值时就不设置，无值时要加18px已修正层位置在ie6下的问题
*/
/*调用：
1 无参数调用：默认浮动在右下角
$("#id").floatdiv();

2 内置固定位置浮动
//右下角
$("#id").floatdiv("rightbottom");
//左下角
$("#id").floatdiv("leftbottom");
//右下角
$("#id").floatdiv("rightbottom");
//左上角
$("#id").floatdiv("lefttop");
//右上角
$("#id").floatdiv("righttop");
//居中
$("#id").floatdiv("middle");
//自定义浮动顶部
$("#id").floatdiv("float-top");

3 自定义位置浮动
$("#id").floatdiv({left:"10px",top:"10px"});
以上参数，设置浮动层在left 10个像素,top 10个像素的位置

*/
jQuery.fn.floatdiv=function(location){
	//ie6要隐藏纵向滚动条
	var isIE6=false;
	if($.browser.msie && $.browser.version=="6.0"){
		//$("html").css("overflow-x","auto").css("overflow-y","hidden");
		isIE6=true;
	};
	//$("body").css({margin:"0px",padding:"0 10px 0 10px",
		//border:"0px",
		//height:"100%",
		//overflow:"auto"
	//});
	return this.each(function(){
		var $fdiv = $(this);
		var loc;//层的绝对定位位置
		if(location==undefined || location.constructor == String){
			switch(location){
				case("rightbottom")://右下角
					loc={right:"0px",bottom:"0px"};
					break;
				case("leftbottom")://左下角
					loc={left:"0px",bottom:"0px"};
					break;	
				case("lefttop")://左上角
					loc={left:"0px",top:"0px"};
					break;
				case("righttop")://右上角
					loc={right:"0px",top:"0px"};
					break;
				case("middle")://居中
					var windowWidth,windowHeight;//窗口的高和宽
					//取得窗口的高和宽
					if (self.innerHeight) {
						windowWidth=self.innerWidth;
						windowHeight=self.innerHeight;
					}else if (document.documentElement&&document.documentElement.clientHeight) {
						windowWidth=document.documentElement.clientWidth;
						windowHeight=document.documentElement.clientHeight;
					} else if (document.body) {
						windowWidth=document.body.clientWidth;
						windowHeight=document.body.clientHeight;
					}
					l=windowWidth/2-$(this).width()/2;
					t=windowHeight/2-$(this).height()/2;
					loc={left:l+"px",top:t+"px"};
					break;
				case("float-top")://浮动向上 
					var f_left = $fdiv.offset().left;
				    var f_top = $fdiv.offset().top;
					loc={left:f_left+"px",top:f_top+"px"};
					if(isIE6){
						window.onscroll=function (){  
							var s_top = document.documentElement.scrollTop;
							var sub_top = f_top - s_top;
							if(sub_top>0) {
								loc={left:f_left+"px",top:sub_top-40+"px"};
							}else{
								loc={left:f_left+"px",top:s_top-70+"px"};
							}
							//$("#show").html(sub_top);
							$fdiv.css("z-index","888").css(loc).css("position","absolute");	
						}; 
					}else{
						$(window).bind("scroll", function(){ 
							var s_top = $(document).scrollTop();
							var sub_top = f_top - s_top;
							if(sub_top<=0) sub_top=0;
							loc={left:f_left+"px",top:sub_top+"px"};
							$fdiv.css("z-index","888").css(loc).css("position","fixed");
						}); 
					}
					var s_top = $(document).scrollTop();
					var sub_top = f_top - s_top;
					if(sub_top<=0) sub_top=0;
					loc={left:f_left+"px",top:sub_top+"px"};
					$fdiv.css("z-index","888").css(loc).css("position","fixed");
					break;
				case("float-right-bottom")://浮动靠右向下 
					var f_right =20;
				    var f_bottom = 50;
					loc={right:f_right+"px",bottom:f_bottom+"px"};
					if(isIE6){
						window.onscroll=function (){  
							var s_top = document.documentElement.scrollTop;
							if(s_top>0){
								var windowHeight = $(window).height(); //浏览器时下窗口可视区域高度
								var f_fixed_bottom =s_top+windowHeight - f_bottom;
								loc={right:f_right+"px",top:f_fixed_bottom+"px"};
								$fdiv.css("z-index","888").css(loc).css("position","absolute").css("display","block");	
							}else{
								$fdiv.css("display","none");
							}
						}; 
					}else{
						$(window).bind("scroll", function(){ 
							var s_top = $(document).scrollTop()
							if(s_top>0){
								loc={right:f_right+"px",bottom:f_bottom+"px"};
								$fdiv.css("z-index","888").css(loc).css("position","fixed").css("display","block");
							}else{
								$fdiv.css("display","none");
							}
						}); 
					}
					break;
				case("center-bottom")://居中向下 
					var f_left = $fdiv.offset().left;
				    var f_top = $fdiv.offset().top;
					var self_f_height = $fdiv.height();
					var windowHeight = $(window).height(); //浏览器时下窗口可视区域高度
					var s_top = $(document).scrollTop();
					if(f_top>(windowHeight)){
						loc={left:f_left+"px",bottom:0+"px"};
					}else{
						loc={left:f_left+"px",top:(f_top-36)+"px"};
					}
					if(isIE6){
						window.onscroll=function (){  
							var os_s_top = $(document).scrollTop();
							loc={left:f_left+"px",top:f_top+os_s_top+"px"};
							$fdiv.css("z-index","888").css(loc).css("position","absolute");
						}; 
					}else{
						$(window).bind("scroll", function(){ 
							s_top = $(document).scrollTop();
							if(f_top>(windowHeight)){
								loc={left:f_left+"px",bottom:0+"px"};
							}else{
								loc={left:f_left+"px",top:f_top+"px"};
							}
						}); 
					}
					break;
					
				default://默认为右下角
					loc={right:"0px",bottom:"0px"};
					break;
			}
		}else{
			loc=location;
		}
		//判断是否为浮动向下类型
		if(location=='float-right-bottom'){
			$(this).css("display","none");
		}else{
			$(this).css("z-index","888").css(loc).css("position","fixed");
		}
		if(isIE6){
			if(loc.right!=undefined){
				//当自定义right位置时无效，这里加上一个判断
				//有值时就不设置，无值时要加18px已修正层位置
				if($(this).css("right")==null || $(this).css("right")==""){
					$(this).css("right","18px");
				}
			}
			$(this).css("position","absolute");
		}
	});
};