 $(document).ready(function(){
    //处理图片懒加载的 给图片自动加上属性original=src
    yeslazyload();
}); 
//延迟加载处理方式
function yesorglazlazyload(obj){
	$(obj).each(function(){
		$(this).attr("original",$(this).attr("src"));
		//处理懒加载的方式
		$(this).lazyload({
			placeholder : "/malltemplate/bmall/images/loading.gif",
			effect : "fadeIn"
		});
	});	
}
//处理延迟加载--需要处理的加载写入这个方法里面
function yeslazyload(){
	//////////////////处理底部结束////////////////
	//商品列表页延迟加载
	$(".sProList img").each(function(){
		yesorglazlazyload(this);
	});
	//商品详细大图图文介绍页面页延迟加载
	//$(".proFocus img").each(function(){
	//	yesorglazlazyload(this);
	//});
	//商品详细图文介绍页面页延迟加载
	$(".proCont img").each(function(){
		yesorglazlazyload(this);
	});
	//H5首页绍页面页延迟加载
	//$(".slider img").each(function(){
	//	yesorglazlazyload(this);
	//});
	//H5首页绍页面页延迟加载
	//$(".exclusive img").each(function(){
	//	yesorglazlazyload(this);
	//});
	//H5首页绍页面页延迟加载
	$(".advDiv img").each(function(){
		yesorglazlazyload(this);
	});
	//H5首页绍页面页延迟加载
	$(".ymlDiv img").each(function(){
		yesorglazlazyload(this);
	});
	//H5首页绍页面页延迟加载
	$(".hotProuct img").each(function(){
		yesorglazlazyload(this);
	});
}



