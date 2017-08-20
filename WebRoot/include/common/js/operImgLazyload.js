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
		placeholder : "/include/common/images/appload.jpg",
		effect : "fadeIn"
	});
}
//处理不延迟加载--需要处理的加载写入这个方法里面
function nolazyload(){
	//处理统计
	$("#tongji_id img").each(function(){
		noorglazyload(this);
	});
	//处理客服
	$("#rbfdiv img").each(function(){
		noorglazyload(this);
	});
	//处理注册页面地区选择 
	$("#areabut img").each(function(){
		noorglazyload(this);
	});
	//通用延迟不加载
	$("#com_nolazy_id img").each(function(){
		noorglazyload(this);
	});
	//商品详细页处理不延迟加载
	$("#com_goodsdetai_nolazy1 img").each(function(){
		noorglazyload(this);
	});
	//商品详细页处理不延迟加载
	$("#com_goodsdetai_nolazy2 img").each(function(){
		noorglazyload(this);
	});
	//商品详细页处理不延迟加载
	$("#com_goodsdetai_nolazy3 img").each(function(){
		noorglazyload(this);
	});
	//商品详细页处理不延迟加载
	$("#com_goodsdetai_nolazy4 img").each(function(){
		noorglazyload(this);
	});
	//商品详细页处理不延迟加载
	$("#com_goodsdetai_nolazy5 img").each(function(){
		noorglazyload(this);
	});
	//商品详细页处理不延迟加载
	$("#com_goodsdetai_nolazy6 img").each(function(){
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



