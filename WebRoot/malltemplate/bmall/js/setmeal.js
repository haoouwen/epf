$(document).ready(function(){
	var isBuySelf=$("#isBuySelf").val();
	if(isBuySelf=='0'){
	 jAlert("卖家不能购买自己的商品!","系统提示");
	}
	/*返回首页*/
	$("#returnTopId").floatdiv("float-right-bottom");
	/*下拉菜单*/
	$(".deprocont ul li").menu();
	/*热门排行*/
	$('#grankidf').dropList();
	$('#grankids').dropList();
	/*搭配选择*/
	$('.setcart').hide();
	$('.setmealyh > ul > li').hover(
		function(){
			$(this).addClass('addcss');
			$(this).find(".setcart").show();
		},
		function(){
			$(this).removeClass('addcss');
			$(this).find(".setcart").hide();
		}
	)
	//分类
   $('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	$("#classMainId").hide();
	$("#h2classId").hover(
		function(){
			$("#classMainId").show();
			$(this).removeClass("h2down");
			$(this).addClass("h2up");
			$("#classMainId").hover(
			  function(){
				  $("#classMainId").show();
				  $("#h2classId").removeClass("h2down");
			      $("#h2classId").addClass("h2up");
			  },
			  function(){
				  $("#classMainId").hide();
				  $("#h2classId").removeClass("h2up");
				  $("#h2classId").addClass("h2down");
			  }
			);
		},
		function(){
			$("#classMainId").hide();
			$(this).removeClass("h2up");
			$(this).addClass("h2down");
		}
	);
	
	
	
	//鼠标滑过规格触发事件
    $(".goodsli > p > a").hover(function(){
		var specstr = $(this).attr("id");
		var spec_id = $(this).parent().find("span").attr("id");
		//遍历选中规格
		var specstr_str = "";
    	$(this).parent().parent().find("a").each(function(){
    		//判断选中规格是否与鼠标滑过规格同组
    		if($(this).parent().find("span").attr("id") != spec_id){
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
 		var goods_id = $(this).parent().parent().find("#goods_id_str").val();
    	var stock = getStock(specstr_str,goods_id);
    	if(eval(stock) <= 0){//若该规格库存不足，禁止点击
    		$(this).removeClass();
			$(this).addClass("disable");
    	}
    },function () {
		$(this).removeClass("disable");
  });
	
	
	
	
	//选择商品规格(标签)
	    $(".setsize > a").click(function(){
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
	    		$(this).siblings().removeClass();
	    		$(this).addClass("checked");
	    	}
	    	//遍历选中规格
	    	$(this).parent().parent().find("a").each(function(){
	    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
	    			var id = $(this).attr("id");
	    			if(specstr_str == ""){
	    				specstr_str = id;
	    			}else{
	    				specstr_str = specstr_str+","+id;
	    			}
	    		}
	    	});
	    	$("#specstr_str").val(specstr_str);
	    	var goods_id = $(this).parent().parent().find("#goods_id_str").val();
		    getGoodsAttr(specstr_str,goods_id);
	    });
	    //选择商品规格(图片)
	    $(".setcolor > a").click(function(){
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
	    	}else{
	    		$(this).siblings().children("i").removeClass();
	    		$(this).children("i").addClass("checked");
	    	}
	    	//遍历选中规格
	    	$(this).parent().parent().find("a").each(function(){
	    		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
	    			var id = $(this).attr("id");
	    			if(specstr_str == ""){
	    				specstr_str = id;
	    			}else{
	    				specstr_str = specstr_str+","+id;
	    			}
	    		}
	    	});
	    	$("#specstr_str").val(specstr_str);
	    	var goods_id = $(this).parent().parent().find("#goods_id_str").val();
		    getGoodsAttr(specstr_str,goods_id);
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
			for(var i=0;i<data.length;i++){
				stock=eval(data[i].stock)+eval(stock);
				if(sale_price==''&&market_price==''){
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
			$("#sale_price_"+goods_id).val(sale_price);
			$("#price_"+goods_id).html(sale_price);
			$("#stock_"+goods_id).html(stock);
        }
    });
}

//立即购买
function buyNow(){  
    //获取购买数量
    var sum = $("#buyNum").val();
	var flag = 0; 
	var flagout = 0;
	var goods_num = 0;
	//遍历套餐底下所有商品
	$(".goodsli").each(function(){
		//商品ID
		var goods_id = $(this).find("#goods_id_str").val();
		//商品个数+1
		goods_num++;
		//统计出选中规格值个数
		var checkedNums = 0;
		//商品属性名称
		var spec_name_str = "";
		//商品属性ID
		var spec_id_str = "";
		var current_spec_id="";
		$(this).children("p").children("a").each(function(){
	  		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
	  			//判断是标签属性还是图片属性
  				var current_spec_name = $(this).parent().find("span").html()+$(this).attr("title");
  				current_spec_id = $(this).attr("id");
				if(spec_name_str == "" && spec_id_str == ""){
  					spec_name_str = current_spec_name;
  					spec_id_str = current_spec_id;
  				}else{
  					spec_name_str = spec_name_str +"<br>"+current_spec_name;
  					spec_id_str = spec_id_str+":"+current_spec_id;
  				}
	  			$("#spec_name_"+goods_id).val(spec_name_str);
	  			$("#spec_id_"+goods_id).val(spec_id_str);
	  			checkedNums++;
	  		}
	  	});
	  	
	  	var dataUrl="/mall/goods!getgoodsstack.action?goods_id="+goods_id+"&current_spec_id="+current_spec_id;
		$.ajax({
	        type: "post",
	        url: dataUrl,
	        datatype:"json",
	        async:false,
	        success: function(data){ 
		       if(eval(data)<eval(sum)){
		          flagout=1;
		       }
	        }
	    });
	  	//自定义规格个数
		var count = $(this).find("#spec_nums").val();
		if(checkedNums != count){
			$(this).addClass("redborder");
			flag++;
		}else{
			$(this).removeClass("redborder");
		}
	});
	
	if(flagout==1){
	   alert("商品库存不足！");
	   return;
	}
	//flag大于0说明有为选择规格的商品
	if(flag == 0){
		//商品个数
		$("#goods_length_str").val(goods_num);
		//购买数量
		var buyNum = $("#buyNum").val();
		//计算总价
		var combo_price = $("#combo_price").val();//单个套餐价格
		var total_combo_price = eval(combo_price)*eval(buyNum);
		$("#combo_price").val(total_combo_price);
		//拼接商品购买数量
		var order_num_str = "0";
		for(var i = 0; i < goods_num; i++){
			if(i == 0){
				order_num_str = buyNum;
			}else{
				order_num_str = order_num_str +","+ buyNum;
			}
		}
		$("#order_num_str").val(order_num_str);
		if(eval(buyNum) > 0 ){
			$("#buyCombo").submit();
		}else{
			jNotify("请输入正确的购买数!"); 
		}
	}
}

//根据规格值获取商品属性
function getStock(specstr,goods_id){
	var stock = 0;
	var dataUrl="/mall/goods!getGoodsAttr.action?specstr="+specstr+"&goods_id="+goods_id;
	$.ajax({
        type: "post",
        url: dataUrl,
        datatype:"json",
        async:false,
        success: function(data){ 
	        data=eval("("+data+")");
			for(var i=0; i<data.length; i++){
				stock=eval(data[i].stock)+eval(stock);
			}
        }
    });
    return stock;
}






function re_show(val,num,btnName,divName,btncss1,btncss2){
		for(var i=1;i<=num;i++)
		{
			if(val==i)
			{
				document.getElementById(divName+i).style.display = 'block';
				document.getElementById(btnName+i).className = btncss1;
			}
			else{
				document.getElementById(divName+i).style.display = 'none';
				document.getElementById(btnName+i).className = btncss2;
			}
		}
}