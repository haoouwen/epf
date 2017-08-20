$(function(){			
   $(".jqzoom").jqueryzoom({
		xzoom:400,
		yzoom:400,
		offset:10,
		position:"right",
		preload:1,
		lens:1
	});
	$("#spec-list").jdMarquee({
		deriction:"left",
		width:320,
		height:56,
		step:2,
		speed:4,
		delay:10,
		control:true,
		_front:"#spec-right",
		_back:"#spec-left"
	});
	$("#spec-list img").bind("mouseover",function(){
		var src=$(this).attr("src");
		$("#spec-n1 img").eq(0).attr({
			src:src.replace("\/n5\/","\/n1\/"),
			jqimg:src.replace("\/n5\/","\/n0\/")
		});
		$(this).css({
			"border":"2px solid #ff6600",
			"padding":"1px"
		});
	}).bind("mouseout",function(){
		$(this).css({
			"border":"1px solid #ccc",
			"padding":"2px"
		});
	});
	/*套餐详情的放大镜*/	
	$(".jqzoomtc").jqueryzoom({
		xzoom:550,
		yzoom:350,
		offset:10,
		position:"right",
		preload:1,
		lens:1
	});
	$("#spec-listtc").jdMarquee({
		deriction:"left",
		width:220,
		height:56,
		step:2,
		speed:4,
		delay:10,
		control:true,
		_front:"#spec-righttc",
		_back:"#spec-lefttc"
	});
	$("#spec-listtc img").bind("mouseover",function(){
		var src=$(this).attr("src");
		$("#spec-n1tc img").eq(0).attr({
			src:src.replace("\/n5\/","\/n1\/"),
			jqimg:src.replace("\/n5\/","\/n0\/")
		});
		$(this).css({
			"border":"2px solid #ff6600",
			"padding":"1px"
		});
	}).bind("mouseout",function(){
		$(this).css({
			"border":"1px solid #ccc",
			"padding":"2px"
		});
	});				
})