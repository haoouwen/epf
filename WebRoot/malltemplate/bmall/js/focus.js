/*
 *多类型焦点图轮播
 *bruinHu 2014/6/16
*/
function aFocus(focusId,type,nClass,selClass,isFull){
	
	var SMALL_ICON = 1,
	    LEFT_RIGHT = 2,
		FREE=3,
		MORE_FREE=4;
		
	var index = 0,picTimer,btn,$focusmspan,sWidth;
	
	var $focusmId = $("#"+focusId),
		$focusmul = $("#"+focusId+" "+"ul"),
		$focusmli = $("#"+focusId+" "+"ul"+" "+"li"),
		len = $focusmli.length;
		
	//获取ul宽度 判断是否是全宽轮播	
    if(isFull){
		sWidth = window.screen.width;
		$("#"+focusId+" "+"ul").css("width",sWidth+"px");
		$("#"+focusId+" "+"li").css("width",sWidth+"px");
	}else{
		sWidth = $focusmul.width();

	}	
	//点击小图标轮播
	if(type==SMALL_ICON){
		
		btn = "<div class='btn'>";
		for(var i=0; i < len; i++) {
			btn += "<span></span>";
		}
		btn +="</div>"
		$focusmId.append(btn);
		
		$focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
		
		$focusmspan.mouseenter(function() {
			index = $focusmspan.index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
	}
	//点击左右图标轮播
	if(type==LEFT_RIGHT){
		
	    btn = "<div class='pre'></div><div class='next'></div>";
		$focusmId.append(btn);
		
		var $focusmpre = $("#"+focusId+" ."+"pre"),
			$focusmnext = $("#"+focusId+" ."+"next");
			
		$focusmpre.click(function () {
			index -= 1;
			if (index == -1) { index = len - 1; }
			showPics(index);
		});
		$focusmnext.click(function () {
			index += 1;
			if (index == len) { index = 0; }
			showPics(index);
		});
	}
	//自由轮播
	if(type==FREE){
		
		btn = "<div class='btn'>";
		for(var i=0; i < len; i++) {
			btn += "<span>"+(i+1)+"</span>";
		}
		btn +="</div>"
		$focusmId.append(btn);
		
		$focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
		
		$focusmspan.mouseenter(function() {
			index = $focusmspan.index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$focusmId.hover(function() {
			clearInterval(picTimer);
		},function(){ 
	     	picTimer = setInterval(function(){showPics(index);
						index++;
						if(index == len){index = 0;}},4000); //自动播放时间
		}).trigger("mouseleave");
	}
	//自由轮播+左右点击轮播
	if(type==MORE_FREE){
		
		btn = "<div class='pre'></div><div class='next'></div>";
		$focusmId.append(btn);
			
		$focusmspan = $("#"+focusId+" ."+"btn");
		
		var $focusmpre = $("#"+focusId+" ."+"pre"),
			$focusmnext = $("#"+focusId+" ."+"next");
			
		$focusmpre.click(function () {
			index -= 1;
			if (index == -1) { index = len - 1; }
			showPics(index);
		});
		$focusmnext.click(function () {
			index += 1;
			if (index == len) { index = 0; }
			showPics(index);
		});
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$focusmId.hover(function() {
			clearInterval(picTimer);
		},function(){ 
	     	picTimer = setInterval(function(){showPics(index);
						index++;
						if(index == len){index = 0;}},4000); //自动播放时间
		}).trigger("mouseleave");
		
	}
	
	//显示图片函数
	function showPics(index) {
		$focusmul.css("width",sWidth * (len));
		var nowLeft = -index*sWidth;
		$focusmul.stop(true,false).animate({"left":nowLeft},300); 
		$focusmspan.removeClass(selClass);
		$focusmspan.stop(true,false).addClass(nClass);
		$focusmspan.eq(index).stop(true,false).addClass(selClass);
	}
	
}