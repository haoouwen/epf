 $(document).ready(function(){
    //处理图片懒加载的 给图片自动加上属性original=src
    yeslazyload();
    //处理不延迟加载的图片
	nolazyload();
}); 
//延迟加载处理方式
function yeslazyload(){
	$("img").each(function(){
		$(this).attr("original",$(this).attr("src"));
	});	
	//处理懒加载的方式
	$("img").lazyload({
		placeholder : "/malltemplate/bmall/images/loading.gif",
		effect : "fadeIn"
	});
}
//处理不延迟加载--需要处理的加载写入这个方法里面
function nolazyload(){
    
    //////////////////处理头部///////////////////
	$(".navBack img").each(function(){
		noorglazyload(this);
	});
	$(".bw960 img").each(function(){
		noorglazyload(this);
	});
	$(".topBack img").each(function(){
		noorglazyload(this);
	});
	$(".logoSearDiv img").each(function(){
		noorglazyload(this);
	});
	$(".hotBanner img").each(function(){
		noorglazyload(this);
	});
	$("#indexTipID img").each(function(){
		noorglazyload(this);
	});
	$(".fpyj img").each(function(){
		noorglazyload(this);
	});
	//////////////////处理头部结束///////////////////
	//处理统计
	$(".advPic img").each(function(){
		noorglazyload(this);
	});
	//处理统计
	$(".sBanner img").each(function(){
		noorglazyload(this);
	});
	//////////////////处理客服购物车////////////
	$(".leftSide img").each(function(){
		noorglazyload(this);
	});
	$(".popupDiv img").each(function(){
		noorglazyload(this);
	});
	$(".rightCart img").each(function(){
		noorglazyload(this);
	});
	//////////////////处理客服购物车结束////////////
	
	//////////////////处理底部///////////////////
	$(".botBack img").each(function(){
		noorglazyload(this);
	});
	//////////////////处理底部结束////////////////
	//通用延迟不加载
	$("#com_nolazy_id img").each(function(){
		noorglazyload(this);
	});
	
}
//不延迟加载原始方法无需修改
function noorglazyload(obj){
   //恢复原来图片的属性
	$(obj).attr("src",$(obj).attr("original"));
	//移除懒加载的标识
	$(obj).removeAttr("original");
}



