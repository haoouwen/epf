/*1点击图标轮播,2左右轮播,3自由轮播*/
function focusm(focusId,style){
	var $focusmId = $("#"+focusId);
	var $focusmul = $("#"+focusId+" "+"ul");
	var $focusmli = $("#"+focusId+" "+"ul"+" "+"li");
	var sWidth =$focusmul.width(); //获取ul宽度
	var len = $focusmli.length;
	var index = 0;
	var picTimer;
	//添加数字按钮和按钮后的半透明条
	var btn = "<div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	if(style==1){
		btn +="</div>"
		$focusmId.append(btn);
		var $focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
		//小按钮添加滑入事件
		$focusmspan.mouseenter(function() {
			index = $focusmspan.index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
		//计算出外围ul元素的宽度
	}
	if(style==2){
	    btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
		$focusmId.append(btn);
		var $focusmbtnBg = $("#"+focusId+" ."+"btnBg");
		var $focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
		var $focusmpreNext = $("#"+focusId+" ."+"preNext");
		var $focusmpre = $("#"+focusId+" ."+"pre");
		var $focusmnext = $("#"+focusId+" ."+"next");
		$focusmbtnBg.css("opacity", 0);
		$focusmspan.css("opacity", 0.4).mouseenter(function () {
			index = $focusmspan.index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
		$focusmpreNext.css("opacity", "0.5");
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
	if(style==3){
		btn +="</div>"
		$focusmId.append(btn);
		var $focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
		//小按钮添加滑入事件
		$focusmspan.mouseenter(function() {
			index = $focusmspan.index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$focusmId.hover(function() {clearInterval(picTimer);},
		function()  { picTimer = setInterval(function(){
			showPics(index);
			index++;
			if(index == len){index = 0;}
			},
			3000); //自动播放时间
		}).trigger("mouseleave");
	}
	if(style==4){
	    btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
		$focusmId.append(btn);
		var $focusmbtnBg = $("#"+focusId+" ."+"btnBg");
		var $focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
		var $focusmpreNext = $("#"+focusId+" ."+"preNext");
		var $focusmpre = $("#"+focusId+" ."+"pre");
		var $focusmnext = $("#"+focusId+" ."+"next");
		$focusmbtnBg.css("opacity", 0);
		$focusmspan.css("opacity", 0.4).mouseenter(function () {
			index = $focusmspan.index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
		$focusmpreNext.css("opacity", "0.5");
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
		$focusmId.hover(function() {clearInterval(picTimer);},
		function()  { picTimer = setInterval(function(){
			showPics(index);
			index++;
			if(index == len){index = 0;}
			},
			3000); //自动播放时间
		}).trigger("mouseleave");
	}
	$focusmul.css("width",sWidth * (len));
	//显示图片函数
	function showPics(index) {
		var nowLeft = -index*sWidth; 
		$focusmul.stop(true,false).animate({"left":nowLeft},300); 
		$focusmspan.stop(true,false).css("background","url(/malltemplate/bmall/images/focunsel.gif) no-repeat").eq(index).stop(true,false).css("background","url(/malltemplate/bmall/images/focusel.gif) no-repeat");
	}
}