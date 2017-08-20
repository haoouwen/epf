//小计
var sale_money=0;
var is_limit=0;
//批量删除购物车
function removerBaoAllCookies(){
	var trade_id=""
	var goodsid="";
	  var ids="";
	  var id="";
	if(confirm("确定删除该商品？")) {
	var i=0;
			$("input:checkbox[class='baoGoods']").each(function(){
				if(this.checked==true){
					i++;
		 			ids =$(this).val();
		 		   id=ids.split(","); 
			 		 goodsid=id[1];
			 		trade_id=id[2];
					   // 删除单个商品
					    mallClearItem(trade_id,goodsid);
					    //移除tr
						$("#item"+goodsid).remove();
				}
			});
			if(i==0){
				jAlert("至少选一条!","系统提示");
			}
		 
	}
	isEmptyCart();
}

//统计选中商品总金额
function CheckedBaoMoney(){
	  var allcount=0;
	   var zallcount=0;
	  var count=0;
	  var all_integral=0;
	 var integral=0;
	  var id;
	  var ids;
	  var trade_id;
	  var allnum=0;
	  var total_price= 0;
	  var total_tax=0;
	  $("input:checkbox[class='baoGoods']").each(function(){
		if(this.checked==true){
			var goods_price = eval($("#gprice_"+this.id.replace(new RegExp(/(:)/g),'_')).html());
			total_price = total_price + goods_price;
			ids =$(this).val();
 		    id=ids.split(","); 
	 		trade_id=id[2];
	 		zallcount=eval($("#gnumber_"+this.id.replace(new RegExp(/(:)/g),'_')).val());
	 		var tax_rate=eval($("#tax_rate"+trade_id).html());
	 		total_tax = total_tax + tax_rate;
	 		allcount = allcount + goods_price;
	 		allnum=allnum+zallcount;
	 		integral=eval($("#baointegral"+trade_id).val());
	 		all_integral+=integral;
			count++;
		}
	});
		if(total_tax > 0) {
		 allcount = allcount + total_tax; 
		}
		$("#baoAllnum").html(allnum);
		$("#baoCount").html(count);
	//	$("#all_integral").html(all_integral);
	    $("#baototal_goodsprice").html(total_price.toFixed(2)+" ");
	    $("#baototal_tax").html(total_tax.toFixed(2)+" ");
		$("#baototal").html("  "+allcount.toFixed(2)+" ");
		if(total_tax > 0) {
			$("#baogup").removeClass("gup2");
		}else {
		    $("#baogup").addClass("gup2");
		}
}
 //点击加号增加商品数量
function AddBaoGoodsNums(id,trade_id,total_stock,goods_id,limit_num,cookies_id){
    var buy_num = parseInt($("#gnumber_"+id).val())+1;
      if(total_stock==null||total_stock==''){
      	 jNotify("库存异常!"); 
      	 buy_num=0;
      }
	if(buy_num > total_stock){
        jNotify("购买数量超过库存量!"); 
        buy_num=total_stock;
	}else if (buy_num>limit_num){
		jNotify("超过该商品限购量!"); 
        buy_num=limit_num;
        is_limit="1";
	}else{
		//头部购物车显示信息
   		showTopCart();  	
	}
	$("#gnumber_"+id).val(buy_num);
	//给隐藏域赋值
	$("#gnumber"+goods_id+"_"+cookies_id.replace(':','_')).val(buy_num);
	//小计
	ChangeBaoPrice(id,trade_id,buy_num);
	//计算总价
	CheckedBaoMoney();
 }
function addBaoNums(id,trade_id,total_stock,goods_id,limit_num,cookies_id){
    var buy_num = parseInt($("#gnumber_"+id).val());
    if(total_stock==null||total_stock==''){
      	 jNotify("库存异常!"); 
      	 buy_num=0;
      }
	if(buy_num > total_stock){
        jNotify("购买数量超过库存量!"); 
        buy_num=total_stock;
	}else if (buy_num>limit_num){
		jNotify("超过该商品限购量!"); 
        buy_num=limit_num;
        is_limit++;
	}else{
		//头部购物车显示信息
   		showTopCart();  	
	}
	$("#gnumber_"+id).val(buy_num);
	//给隐藏域赋值
	$("#gnumber"+goods_id+"_"+cookies_id.replace(':','_')).val(buy_num);
	//小计
	ChangeBaoPrice(id,trade_id,buy_num);
	//计算总价
	CheckedBaoMoney();
 }
 //点击小箭头减少商品数量
 function SubBaoGoodsNum(id,trade_id,goods_id,cookies_id)
 { 
	var buy_num = $("#gnumber_"+id).val();
	if(buy_num >= 1 && (eval(buy_num)-1) >= 1)
	{
	  buy_num = buy_num - 1;
	    //头部购物车显示信息
   		showTopCart();   	
	  //小计
	  ChangeBaoPrice(id,trade_id,buy_num);
	}
	$("#gnumber_"+id).val(buy_num);
	//给隐藏域赋值
	$("#gnumber"+goods_id+"_"+cookies_id.replace(':','_')).val(buy_num);
	//计算总价
	CheckedBaoMoney();
 } 
//当商品购物车数量改变的时候，修改小计价格
function ChangeBaoPrice(id,trade_id,buy_num)
{  
	var sale_id = $("#sale_id"+trade_id).val();
	var taxrate = eval($("#taxrate"+trade_id).html());
	var allcount=0;
	var alltax = 0;
	//单价
	var goods_price = $("#price_"+id).val();
	allcount = buy_num * goods_price;
	sale_money = allcount;
 	//更新服务器上的购物车商品
	$.ajax({
	      type: "post",
	      url: "/cartgoods!updateCartGoods.action",
	      data:{'trade_id':trade_id,'buy_num':buy_num,'sale_id':sale_id,'sale_price':sale_money},
	      datatype:"json",
	      async:false,
	      success: function(data){ 
	      	sale_money = eval(data);
	      }
	 });
		//计算关税
	if(taxrate != null && taxrate != 0) {
	   alltax = sale_money*taxrate/100;
	}
	//总价
    $("#gprice_"+id).html(sale_money.toFixed(2));
    $("#tax_rate"+trade_id).html(alltax.toFixed(2));
    
}

 //购物车选中商品
function baoCartCheck(obj,id){
	var val = obj.value;
	if(obj.checked){
		$("input:checkbox[id^="+id+"]").each(function(){
			this.checked=true;
			//判断店铺是否全选
			var unShopoChenkedNum = 0;
			$("input:checkbox[id^=baoCart_"+val+"_]").each(function(){
				if(this.checked==false){
					unShopoChenkedNum++;
				}
			});
			if(unShopoChenkedNum != 0){
				$("#baoCart_"+val).attr("checked",false);
			}else{
				$("#baoCart_"+val).attr("checked",true);
			}
		});
	}else{
		$("input:checkbox[id^="+id+"]").each(function(){
			this.checked=false;
			//店铺去除选中
			$("#baoCart_"+val).attr("checked",false);
		});
	}
	//判断是否全选
	var unCheckedNum = 0;//未选中个数
	$("input:checkbox[id^=baoCart_]").each(function(){
		if(this.checked==false){
			unCheckedNum++;
		}
	});
	if(unCheckedNum != 0){
		$("#baoCart").attr("checked",false);
	}else{
		$("#baoCart").attr("checked",true);
	}
	//根据选中个数判断是否可点击结算
	var checkedNum = 0;//选中个数
	$("input:checkbox[id^=baoCart_]").each(function(){
		if(this.checked==true){
			checkedNum++;
		}
	});
	if(checkedNum != 0){
		$("#cart_count").removeClass("cart_count_none");
		$("#cart_count").addClass("cart_count");
		$('#cart_count').attr('disabled',false);
	}else{
		$("#cart_count").removeClass("cart_count");
		$("#cart_count").addClass("cart_count_none");
		$('#cart_count').attr('disabled',true);
	}
	//根据选中商品计算价格
	CheckedBaoMoney();
	
}

//判断关税是否大于50，并提示
function checkBaoTax() {
	//商品总关税
	var total_tax = 0;
	//商品总件数
	var goods_num = 0;
	//商品种类
	var goods_count = 0;
	//商品总计
	var total = 0;
    $("input:checkbox[id^=baoCart_0_]").each(function(){
		 		if(this.checked){
		 		   //获取id串
		 		   var ids=$(this).val();
		 		   //分割id串
		 		   var id=ids.split(","); 
			 	  //获取选中商品的goods_id
			 		var sel_goods_id=id[1];
			 		//获取购物车的trade_id
			 		var trade_id=id[2];
			 		//获取cookie_id
			 		var cookie_id=id[3];
			 		//获取选中商品的单价
			 		var sel_goods_price = $("#price"+trade_id).val();
			 		//获取选中商品的购买个数
			 		var sel_goods_num = $("#gnumber"+sel_goods_id+"_"+cookie_id.replace(':','_')).val();
			 		var sel_tax = $("#tax_rate"+trade_id).html();
			 		 total_tax = eval(total_tax) + eval(sel_tax); 
			 		 total = eval(total) + eval(sel_goods_price) * eval(sel_goods_num);
			 		 goods_num = goods_num  + eval(sel_goods_num);
			 		 goods_count ++;
				 	}        
       
    });
    //判断商品关税是否大于50且商品数量大于1
	//if(total_tax > 0 && goods_num > 1) {
    //     $("#bao_tax").hide();
   //      $("#bao_count").show();
	//	 $(".baotax").show();
	//	 $("#baogup").find("i").show();
	//	 $("#baoCount").html(goods_count);
	//	 $("#baoAllnum").html(goods_num);
	//	 $("#baototal_tax").html(total_tax.toFixed(2));
	//	 $("#baototal_goodsprice").html(total.toFixed(2));
	//	 $("#baototal").html((eval(total_tax) + eval(total)).toFixed(2));
	//}else {
	    baoCartSubmit(); 
	//}
}

//购物车结算
function baoCartSubmit(){
    var cust_id_str="";
	var shop_name_str="";
	var shop_qq_str="";
	var goods_length_str="";
	var goods_img_str="";
	var goods_id_str="";
	var goods_num_str="";
	var goods_spec_id_str="";
	var goods_spec_name_str="";
	var goods_price_str="";
	var goods_name_str="";
	var goods_cat_str="";
	var use_integral_str="";
	var smode_id_str="";
	var ship_str="";
	var ship_price_str="";
	var ship_name_str="";
	var trade_id_str="";
	var cookie_id_str="";
	var radom_no_str="";
	var sel_cust_id="0";
	if(is_limit>1){
		 jNotify("购物车中存在超过限购数量的商品，建议检查购买数量大商品。!"); 
		 return false;
	}
		 var sel_shop_qq = $("#shopqq"+sel_cust_id).val();
		 var sel_shop_name = $("#shopname"+sel_cust_id).val();
		 var sel_radom_no=$("#radomno"+sel_cust_id).val();
		 //获取选中商品的运输方式ID
		 var sel_smode_id = $("#smode_id"+sel_cust_id).val();
		 //获取选中商品的运输方式
		 var sel_ship = $("#ship"+sel_cust_id).val();
		 //配送方式名称
		 var sel_ship_name = $("#ship_name"+sel_cust_id).val();
		 //运费
		 var sel_ship_price = $("#ship_price"+sel_cust_id).val();
		 var count=0;
		 $("input:checkbox[id^=baoCart_"+sel_cust_id+"_]").each(function(){
		 		if(this.checked){
		 		   //获取id串
		 		   var ids=$(this).val();
		 		   //分割id串
		 		   var id=ids.split(","); 
		 			//获取选中商品的goods_id
			 		var sel_goods_id=id[1];
			 		//获取购物车的trade_id
			 		var trade_id=id[2];
			 		//获取cookie_id
			 		var cookie_id=id[3];
			 		//获取选中商品的购买个数
			 		var sel_goods_num = $("#gnumber"+sel_goods_id+"_"+cookie_id.replace(':','_')).val();
			 		//获取选中商品的规格值
			 		var sel_goods_specid = $("#spec_id"+trade_id).val();
			 		//获取选中商品的规格名称
			 		var sel_goods_specname = $("#spec_name"+trade_id).val();
			 		//获取选中商品的单价
			 		var sel_goods_price = $("#price"+trade_id).val();
			 		//获取选中商品的图片
			 		var sel_goods_img = $("#goods_img"+sel_goods_id).val();
			 		//获取选中商品的名称
			 		var sel_goods_name = $("#goods_name"+sel_goods_id).val();
			 		//获取选中商品的分类
			 		var sel_goods_cat = $("#goods_cat"+sel_goods_id).val();
			 		//获取选中商品的是否使用积分
			 		var sel_use_integral = $("#use_integral"+sel_goods_id).val();
			 		goods_id_str += sel_goods_id +",";
			 		goods_num_str += sel_goods_num +",";
			 		goods_spec_id_str += sel_goods_specid +",";
			 		goods_spec_name_str += sel_goods_specname +",";
			 		goods_price_str += sel_goods_price +",";
			 		goods_img_str += sel_goods_img +",";
			 		goods_name_str += sel_goods_name +",";
			 		use_integral_str += sel_use_integral +",";
			 		goods_cat_str += sel_goods_cat +"#";
			 		trade_id_str += trade_id +",";
			 		cookie_id_str += cookie_id +",";
			 		count++;
				 		}
				 });
				  if(count>0){
				  		cust_id_str += sel_cust_id+",";
				  		shop_name_str += sel_shop_name+",";
			 	  		 shop_qq_str += sel_shop_qq+",";
			 	  		 radom_no_str += sel_radom_no+",";
					   goods_length_str += count+",";
				 }
		
	$("#cust_id_str").val(cust_id_str.substring(0,cust_id_str.length-1));
	$("#goods_id_str").val(goods_id_str.substring(0,goods_id_str.length-1));
	$("#goods_length_str").val(goods_length_str.substring(0,goods_length_str.length-1));
	$("#goods_name_str").val(goods_name_str.substring(0,goods_name_str.length-1));
	$("#goods_cat_str").val(goods_cat_str.substring(0,goods_cat_str.length-1));
	$("#goods_img_str").val(goods_img_str.substring(0,goods_img_str.length-1));
	$("#spec_id_str").val(goods_spec_id_str.substring(0,goods_spec_id_str.length-1));
	$("#spec_name_str").val(goods_spec_name_str.substring(0,goods_spec_name_str.length-1));
	$("#sale_price_str").val(goods_price_str.substring(0,goods_price_str.length-1));
	$("#give_inter_str").val(goods_price_str.substring(0,goods_price_str.length-1));
	$("#shop_name_str").val(shop_name_str.substring(0,shop_name_str.length-1));
	$("#order_num_str").val(goods_num_str.substring(0,goods_num_str.length-1));
	$("#shop_qq_str").val(shop_qq_str.substring(0,shop_qq_str.length-1));
	$("#radom_no_str").val(radom_no_str.substring(0,radom_no_str.length-1));
	$("#smode_id_str").val(smode_id_str.substring(0,smode_id_str.length-1));
	$("#ship_str").val(ship_str.substring(0,ship_str.length-1));
	$("#ship_price_str").val(ship_price_str.substring(0,ship_price_str.length-1));
	$("#ship_name_str").val(ship_name_str.substring(0,ship_name_str.length-1));
	$("#trade_id_str").val(trade_id_str);
	$("#cookie_id_str").val(cookie_id_str.substring(0,cookie_id_str.length-1));
	$("#use_integral_str").val(use_integral_str.substring(0,use_integral_str.length-1));
	$("#refloc").val("/cart.html");
    document.forms[0].action="/mall/order!goOrder.action";
    document.forms[0].submit();
}