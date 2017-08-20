//初始化加载
$(document).ready(function(){
	//鼠标滑过规格触发事件
	var specstr_str_all = $("#specstr_str_all").val();
    $(".specstr > p > a").hover(function(){
		var specstr = $(this).attr("id");
		var spec_id = $(this).parent().parent().find("span").attr("id");
		//该规格值存在于商品属性中
		if (specstr_str_all.indexOf(specstr) > -1) {
			//遍历选中规格
			var specstr_str = "";
	    	$(".specstr > p > a").each(function(){
	    		//判断选中规格是否与鼠标滑过规格同组
	    		if($(this).parent().parent().find("span").attr("id") != spec_id){
		    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
		    			var id = $(this).attr("id");
		    			if(specstr_str == ""){
		    				specstr_str = id;
		    			}else{
		    				specstr_str = specstr_str+","+id;
		    			}
		    		}
		    	}
	    	});
	    	//拼凑规格串
	    	if(specstr_str == ""){
   				specstr_str = specstr;
   			}else{
   				specstr_str = specstr_str+","+specstr;
   			}
   			//根据规格获取库存
	    	var stock = getStock(specstr_str);
	    	if(eval(stock) <= 0){//若该规格库存不足，禁止点击
	    		$(this).removeClass();
				$(this).addClass("disable");
	    	}
		}else{
			$(this).removeClass();
			$(this).addClass("disable");
		}
    },function () {
		$(this).removeClass("disable");
  });
	//选择商品规格(标签)
    $(".specstr > .selsize > a").click(function(){
    	var css = $(this).attr("class");
    	var id = $(this).attr("id");
    	//禁止点击
    	if(css == "disable"){
    		return;
    	}
    	//规格串
	    var specstr_str = "";
    	if(css == "checked"){
    		$(this).removeClass();
    	}else{
    		//该规格值存在于商品属性中
    		if (specstr_str_all.indexOf(id) > -1) {
	    		$(this).siblings().removeClass();
	    		$(this).addClass("checked");
			}else{
				//$(this).removeClass();
			}
    	}
    	//遍历选中规格
    	$(".specstr > p > a").each(function(){
    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
    			var id = $(this).attr("id");
    			if(specstr_str == ""){
    				specstr_str = id;
    			}else{
    				specstr_str = specstr_str+","+id;
    			}
    		}
    	});
    	$("#spec_id_str").val(specstr_str);
	    getGoodsAttr(specstr_str);
	    checkBuyNum(buy_nums);
    });
    //选择商品规格(图片)
    $(".specstr > .selcolor > a").click(function(){
    	var css = $(this).children("i").attr("class");
    	var id = $(this).children("i").attr("id");
		//禁止点击
    	if($(this).attr("class") == "disable"){
    		return;
    	}
    	//规格串
	    var specstr_str = "";
    	if(css == "checked"){
    		$(this).children("i").removeClass();
    		//重置默认相册
			replacePhoto($("#img_str").val());
    	}else{
    		//该规格值存在于商品属性中
    		if (specstr_str_all.indexOf(id) > -1) {
				$(this).siblings().children("i").removeClass();
	    		$(this).children("i").addClass("checked");
	    		//替换放大镜相册
	    		var self_img_str = $(this).children("i").attr("img");
	    		if(self_img_str != undefined && self_img_str != ""){
	    			replacePhoto(self_img_str);
	    		}
	    		
			}else{
				//$(this).children("i").removeClass();
			}
    	}
    	//遍历选中规格
    	$(".specstr > p > a").each(function(){
    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
    			var id = $(this).attr("id");
    			if(specstr_str == ""){
    				specstr_str = id;
    			}else{
    				specstr_str = specstr_str+","+id;
    			}
    		}
    	});
    	$("#spec_id_str").val(specstr_str);
	    getGoodsAttr(specstr_str);
	    checkBuyNum(buy_nums);
    });
});

//根据规格值获取商品属性
function getGoodsAttr(specstr,goods_id){
	var dataUrl="/mall/goods!getGoodsAttr.action?specstr="+specstr+"&goods_id="+goods_id;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
	        data=eval("("+data+")");
	        var sale_price='';
	        var market_price='';
	        var stock=0;
			for(var i=0; i<data.length; i++){
				stock=eval(data[i].stock)+eval(stock);
				if(sale_price=='' && market_price==''){
					sale_price=data[i].sale_price;
					market_price=data[i].market_price;
				}else{
					if(data.length > 1 && i == data.length-1){
						if(sale_price != data[i].sale_price){
							sale_price=sale_price+'-'+data[i].sale_price;
						}
						if(market_price != data[i].market_price){
							market_price=market_price+'-'+data[i].market_price;
						}
					}
				}
			}
			$("#market_price_str").html(market_price);
			$("#sale_price").html(sale_price);
			$("#cart_goods_sale_price").val(sale_price);
			$("#sale_price_str").val(sale_price);
			$("#total_stock").html(stock);
			$("#temp_stock").val(stock);
        }
    });
}


