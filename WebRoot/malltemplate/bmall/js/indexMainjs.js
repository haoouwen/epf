$(document).ready(function(){
	/*首页全屏轮播*/
	$("#banner_pic_1").show();
	$("#mainbody").removeClass();
    $("#mainbody").addClass("index_bg01");
    //获取第一张图的背景颜色
    var bg_color = $("#banner_pic_1").attr("i");
    $("#mainbody").css("background",bg_color);
	$('#slides').slides({
		preload: false,
		preloadImage: '/images/loading.gif',
		effect:'fade',
		slideSpeed:400,
		fadeSpeed:100,
		play:4000,
		pause:100,
		hoverPause: true
	});
	/*分类定位hong注释掉*/
	$("#proclaId").floatdiv("float-top");
	/*返回首页*/
	$("#returnTopId").floatdiv("float-right-bottom");
	/*分类显示和隐藏*/
	$('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('p').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		var showType = $("#fcbutId").attr("class");
		if(showType == "fcdown"){
			$(this).find('p').hide();
		}
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	//分类默认不展开
	//$(".lclacont > p").hide();
	$("#fcbutId").toggle(
		function (){
			$(".lclacont > p").hide("fast");
			$("#fcbutId").removeClass("fcup");
			$("#fcbutId").addClass("fcdown");
		},
		function (){
			$(".lclacont > p").show("fast");
			$("#fcbutId").removeClass("fcdown");
			$("#fcbutId").addClass("fcup");
			
		}
	);
	var hotLength=$("#example li").length;
	var moveLength=0;
	if(hotLength>4){
	   moveLength=4;
	}
	/*热门商品移动*/
	$('#example').bxCarousel({
		display_num:4, 
		move:moveLength,
		margin:0
	});
	/*品牌展示*/
	brand('brandId1');
	brand('brandId2');
	brand('brandId3');
	brand('brandId4');
	brand('brandId5');
	brand('brandId6');
	brand('brandId7');
});

/*品牌展示*/
function brand(brandId){
	var $brandsId = $("#"+brandId);
	var $brandul = $("#"+brandId+" "+"ul");
	var $brandli = $("#"+brandId+" "+"ul"+" "+"li");
	var sWidth =$brandul.width(); //获取ul宽度
	var lilen = $brandli.length; //获取焦点图个数
	var len =parseInt((lilen+4)/5);//求个数取整
	var index = 0;
	var picTimer;
	//添加数字按钮和按钮后的半透明条
	var btn = "<div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	btn = btn+"</div>";
	$brandsId.append(btn);
	var $brandspan = $("#"+brandId+" ."+"btn"+" "+"span");
	//小按钮添加滑入事件
	$brandspan.mouseenter(function() {
		index = $brandspan.index(this);
		showPics(index);
	}).eq(0).trigger("mouseenter");;
	//计算出外围ul元素的宽度
	$brandul.css("width",sWidth * (len));
	//显示图片函数
	function showPics(index) {
		var nowLeft = -index*sWidth; //计算ul元素的left值
		$brandul.stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		$brandspan.stop(true,false).css("background","url(malltemplate/bmall/images/mingdot_09.gif) no-repeat").eq(index).stop(true,false).css("background","url(malltemplate/bmall/images/andot_09.gif) no-repeat"); //为当前的按钮切换到选中的效果
	}
}