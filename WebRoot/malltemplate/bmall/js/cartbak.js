//小计
var sale_money=0;
//添加商品到cookie
function addCookie(cartpos) { 
	    //商品id
		var goodsid=$("#cart_goods_id").val();
		//商品名称
		var goodsname=$("#cart_goods_name").val();
		//价格
		var goodsprice=$("#cart_goods_sale_price").val();
		//是否虚拟商品
		var is_virtual = $("#cart_is_virtual").val();
		//图片
		var goodsimg=$("#cart_goods_img_path").val(); 
		//店铺名称
		var shopname=$("#cart_shop_name").val();
		//商品属性名称
		var spec_name=$("#cart_sepc_name").val();
		//商品属性ID
		var spec_id=$("#cart_sepc_id").val();
		//店铺qq
		var shopqq=$("#cart_shopqq").val();
		//数量
		var buy_nums=$("#buy_nums").val();
		//卖家cust_id
		var cust_id=$("#cart_shop_cust_id").val();
		//店铺会员名称
		//var user_name=$("#user_name").val();
		//设置购买数量默认为1
		if(buy_nums==null||buy_nums==0){
			buy_nums=1;
		}
		//cookie个数
	    var cart_cookieNum;
	    //cookie索引
	    var cart_cookieIndex=1;
	    //判断cookie的个数
	    if($.cookie("ccn")!=null && $.cookie("ccn")!=""){
	    	var fag=0;
	    	//获取cookie里的个数并加一
	    	cart_cookieNum=parseInt($.cookie("ccn"))+1;
	    	//获取cookie里原有的goods_id
	    	var goods_id;
	    	for(var i=1;i<cart_cookieNum;i++){
		    	goods_id=$.cookie("goods_id"+i);
		    	//判断id是否相等
		    	if(goods_id==goodsid){
	    			fag+=1;
	    		}
	    	}
	    	//fag等于0就是没有重复的数据
	    	if(fag==0){
		    	$.cookie("cust_id" + cart_cookieNum, cust_id, { expires: 7, path: '/' });  
		    	$.cookie("memb_name" + cart_cookieNum, memb_name, { expires: 7, path: '/' });  
		   		$.cookie("goods_id" + cart_cookieNum, goodsid, { expires: 7, path: '/' });  
		       	$.cookie("goods_name" + cart_cookieNum, goodsname, { expires: 7, path: '/' });  
		       	$.cookie("is_virtual" + cart_cookieNum, is_virtual, { expires: 7, path: '/' });  
		       	$.cookie("list_img" + cart_cookieNum, goodsimg, { expires: 7, path: '/' });  
		       	$.cookie("sale_price" + cart_cookieNum, goodsprice, { expires: 7, path: '/' });  
		       	$.cookie("buy_nums" + cart_cookieNum, buy_nums, { expires: 7, path: '/' });
		       	$.cookie("shop_name" + cart_cookieNum, shopname, { expires: 7, path: '/' });  
		       	$.cookie("shop_qq" + cart_cookieNum, shopqq, { expires: 7, path: '/' });  
		       	$.cookie("spec_name" + cart_cookieNum, spec_name, { expires: 7, path: '/' });
		       	$.cookie("spec_id" + cart_cookieNum, spec_id, { expires: 7, path: '/' });
		        //cookie索引方便用于后期的删除购物车
		 		$.cookie("goods_id_Index" + goodsid, cart_cookieNum, { expires: 7, path: '/' }); 
		        //cookie递增的数值
		 		$.cookie("cart_cookieNum", cart_cookieNum, { expires: 7, path: '/' });
		 		//购物车的个数
		 		$.cookie("cart_Num", cart_cookieNum, { expires: 7, path: '/' });
	 		}
	    }else{
	    	cart_cookieNum=1;
	    	$.cookie("cust_id" + cart_cookieNum, cust_id, { expires: 7, path: '/' });  
	    	$.cookie("memb_name" + cart_cookieNum, memb_name, { expires: 7, path: '/' });  
	   		$.cookie("goods_id" + cart_cookieNum, goodsid, { expires: 7, path: '/' });  
	       	$.cookie("goods_name" + cart_cookieNum, goodsname, { expires: 7, path: '/' });  
	       	$.cookie("is_virtual" + cart_cookieNum, is_virtual, { expires: 7, path: '/' });  
	       	$.cookie("list_img" + cart_cookieNum, goodsimg, { expires: 7, path: '/' });  
	       	$.cookie("sale_price" + cart_cookieNum, goodsprice, { expires: 7, path: '/' });  
	       	$.cookie("buy_nums" + cart_cookieNum, buy_nums, { expires: 7, path: '/' });
	       	$.cookie("shop_name" + cart_cookieNum, shopname, { expires: 7, path: '/' });  
	       	$.cookie("shop_qq" + cart_cookieNum, shopqq, { expires: 7, path: '/' });  
	       	$.cookie("spec_name" + cart_cookieNum, spec_name, { expires: 7, path: '/' }); 
	       	$.cookie("spec_id" + cart_cookieNum, spec_id, { expires: 7, path: '/' });
	        //cookie索引
	 		$.cookie("goods_id_Index" + goodsid, cart_cookieNum, { expires: 7, path: '/' }); 
	        //cookie购物车个数更改
	 		$.cookie("ccn", cart_cookieNum, { expires: 7, path: '/' });
	 		//购物车商品的个数
		 	$.cookie("cart_Num", cart_cookieNum, { expires: 7, path: '/' });
	    }
	    showCartGoods();
	 	showCart(cartpos,'cartDiv');
	    //document.location.href="cart.action?user_name="+user_name;
 }
//获取cookie的商品      
function getCookie() {  
		var cartstr="";
        //商品ID  
	    var goods_id; 
	    //会员名称
	    var cust_name;
	    //商品名称
	    var goods_name; 
	    //是否虚拟商品
	    var is_virtual;
	    //商品图片
	    var list_img; 
	    //商品价格
	    var sale_price;
	    //商品属性
	    var spec_name; 
	    //商品属性ID
	    var spec_id; 
	    //cokie索引值
	    var cart_cookieIndex;
	    //cookie个数
	    var cart_cookieNum;
	    //cust_id
	    var cust_id;
	    //总价格
	    var totalPrice=0;
	    var onePrice=0;
	    //判断cookie是否存在
        if ($.cookie("ccn") != null){  
            //获取cookie个数
	    	cart_cookieNum = eval(parseInt($.cookie("ccn")));
	    	//根据cart_cookieNum循环
	    	for(var i = 1; i <= cart_cookieNum; i++){
	    	  //从cookie取值
			  cust_id=$.cookie("cust_id"+i);
			  cust_name=$.cookie("memb_name"+i);
			  goods_id = $.cookie("goods_id" + i);
			  goods_name = $.cookie("goods_name" + i);
			  is_virtual = $.cookie("is_virtual" + i);
			  list_img = $.cookie("list_img" + i);
			  sale_price =parseInt($.cookie("sale_price" + i));
			  spec_name = $.cookie("spec_name" + i);
			  spec_id = $.cookie("spec_id" + i);
			  buy_nums = parseInt($.cookie("buy_nums"+i));
			  shop_name = $.cookie("shop_name"+i);
			  shop_qq = $.cookie("shop_qq"+i);
			  cart_cookieIndex = $.cookie("cart_cookieIndex" + i);
			  //根据数量计算小计
			  sale_money=sale_price*buy_nums;
			  if(cust_id != null && cust_id != ""){
			  		//
			  		cartstr="";
			  		
			  		cartstr+="<tr id='item"+goods_id+"' class='goodsid"+goods_id+"'>"
				  		+"<td><input type='checkbox' class='goods' id='cart_"+cust_id+"_"+goods_id+"' value='"+cust_id+"'/>"
				  		+"<input class='selcust_goods_id' type='hidden' value='"+goods_id+"'/></td>"
						+"<td width='8%'><img width='55px' height='55px' src='"+list_img+"' /></a>"
						+"<input id='goods_img"+goods_id+"' type='hidden' value='"+list_img+"'/></td>"
						+"<td class='name' style='padding-left:10px;'><a href='/mall-goodsdetail-"+goods_id+".html' target='_blank'>"+goods_name+"</a>"
						+"<input id='goods_name"+goods_id+"' type='hidden' value='"+goods_name+"'/></td>"
						+"<td>"+spec_name+"</span>"
						+"<input id='spec_id"+goods_id+"' type='hidden' value='"+spec_id+"'/><input id='spec_name"+goods_id+"' type='hidden' value='"+spec_name+"'/></td>"
						+"<td> <span style='color:#A10000'><strong>"+sale_price+"</strong></span>元"
						+"<input id='price"+goods_id+"' class='selgoods_sale_price' type='hidden' value='"+sale_price+"'/></td>"
						+"<td>"+sale_price+"分"
						+"<input id='price"+goods_id+"' class='selgoods_sale_price' type='hidden' value='"+sale_price+"'/></td>"
						+"<td><a onclick='SubGoodsNum("+goods_id+","+i+");' style='cursor:pointer;'><img src='/shoptemplate/default/images/subbut.gif'></a>"
						+"<input id='gnumber"+goods_id+"' type='text' style='width:30px;' readonly='true' value='"+buy_nums+"'/><a onclick='AddGoodsNum("+goods_id+","+i+");' style='cursor:pointer;'><img src='/shoptemplate/default/images/addbut.gif'></a></td>"
						+"<td> <span style='color:#A10000' id='g_give_inter_"+goods_id+"' >-</span></td>"
						+" <td> ￥<strong id='gprice"+goods_id+"' style='color:#A10000'>"+sale_money+"</strong> 元 </td>"
						+" <td><a class='del track' href='javascript:void(0);' onclick='removerCookies("+goods_id+","+i+")' > 删除</a></td>"
						+"</tr>";
						
				  	//取出店铺是否有值
				  	if($("#cart"+cust_id).length > 0){
				  		//当购物车页面存在该商家时能直接添加商品到商家的模块
						$("#carttable"+cust_id).append(cartstr);
				  	}else{
				  		var cartHtml="<div id='cart"+cust_id+"' class='cartcont'>"
				  		+"<table id='carttable"+cust_id+"' width='100%' class='carttab' cellspacing='0' cellpadding='0'>"
				  		+"<tr><th width='3%'><input type='checkbox' id='cart_"+cust_id+"' value='"+cust_id+"'>"
				  		+"<input class='selcust' type='hidden' value='"+cust_id+"'/><input id='shopname"+cust_id+"' type='hidden' value='"+shop_name+"'/><input id='shopqq"+cust_id+"' type='hidden' value='"+shop_qq+"'/></th>"
				  		+"<th width='15%' class='shopName' colspan='2'><a href='/shopsIndex.action?user_name="+cust_name+"'>店铺："+shop_name+"</a><a target='_blank' href='http://wpa.qq.com/msgrd?v=3&uin="+shop_qq+"&site=qq&menu=ye' target='_blank'><img src='http://wpa.qq.com/pa?p=1:"+shop_qq+":16'></a></th>"
				  		+"<th width='10%'>属性</th><th width='10%'>单价(元)</th><th width='10%'>赠送积分</th><th width='10%'>数量</th><th width='10%'>优惠方式</th><th width='10%'>小计(元)</th><th width='7%'>操作</th></tr>"
				  		+cartstr
						+"</table>"
						+"</div>"; 
						$("#cartDiv").append(cartHtml);
				  	}
			  	}
        	}  
        	//总价格
        	AcountMoney();
      	}
}
//删除购物车某一个商品
function removerCookies(goods_id,buy_nums){
	var cart_countNum=0;
	if(confirm("确定删除该商品？")) {
		var cart_index = parseInt($.cookie("goods_id_Index"+goods_id));
	    if(cart_index!= null && cart_index!=""){
		    // 删除单个商品
		    mallClearItem(cart_index,goods_id);
		    //移除tr
			$("#item"+goods_id).remove();
		}
	}
	AcountMoney();
}
 //清除购物项
 function mallClearItem(index_num,goods_id){
      $.cookie("cust_id" +index_num, null, { expires: 7, path: '/' }); 
	  $.cookie("goods_id" +index_num, null, { expires: 7, path: '/' });  
      $.cookie("goods_name"+index_num, null, { expires: 7, path: '/' });  
      $.cookie("is_virtual"+index_num, null, { expires: 7, path: '/' });  
      $.cookie("list_img"+index_num, null, { expires: 7, path: '/' });  
      $.cookie("sale_price"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("shop_name"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("spec_name"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("spec_id"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("shop_qq"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("buy_nums"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("smode_id"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("ship"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("ship_price"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("ship_name"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("goods_cookieIndex"+index_num, null, { expires: 7, path: '/' }); 
      $.cookie("goods_id_Index"+goods_id, null, { expires: 7, path: '/' }); 
      //获取cookie个数
   	  var cart_cookieNum = parseInt($.cookie("ccn"));
   	  //获取购物车数量
   	  var cart_Num = parseInt($.cookie("cart_Num"));
   	  cart_cookieNum--;
   	  cart_Num--;
   	  if(cart_cookieNum <= 0 || cart_Num <= 0){
   	  		$.cookie("cart_cookieNum", null, { expires: 7, path: '/' });
   	  		$.cookie("cart_Num", null, { expires: 7, path: '/' });
    	}else{
   		    $.cookie("ccn", cart_cookieNum, { expires: 7, path: '/' });
   		    $.cookie("cart_Num", cart_cookieNum, { expires: 7, path: '/' });
   	  }
 }
 //清空所有购物车
 function EmptyCart(goods_id){
 		
 	  //获取购物车cookie个数
   	  var cart_cookieNum = parseInt($.cookie("ccn"));
 	  for(var i = 1; i <= cart_cookieNum; i++){
 	  	  $.cookie("cust_id" +i, null, { expires: 7, path: '/' }); 
		  $.cookie("goods_id" +i, null, { expires: 7, path: '/' });  
	      $.cookie("goods_name"+i, null, { expires: 7, path: '/' });  
	      $.cookie("is_virtual"+i, null, { expires: 7, path: '/' });  
	      $.cookie("list_img"+i, null, { expires: 7, path: '/' });  
	      $.cookie("sale_price"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("shop_name"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("spec_name"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("spec_id"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("shop_qq"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("buy_nums"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("ship"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("ship_price"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("ship_name"+i, null, { expires: 7, path: '/' }); 
	      $.cookie("goods_cookieIndex"+i, null, { expires: 7, path: '/' }); 
 	  }
 	  var ids = goods_id.split(",");
 	  for(var i = 0; i < ids.length; i++){
		  $.cookie("goods_id_Index"+ids[i], null, { expires: 7, path: '/' }); 
	  }
      
	  $.cookie("ccn", null, { expires: 7, path: '/' });
	  $.cookie("cart_Num", null, { expires: 7, path: '/' });
 }
//统计商品总金额
function AcountMoney()
{
	  var allcount=0;
	  if($.cookie("ccn")!=null&&$.cookie("ccn")!="")
	  {
	      var cart_cookieNum = parseInt($.cookie("ccn"));
		  for(var i = 1; i<= cart_cookieNum; i++)
		  {
		  	  var goods_id=$.cookie("goods_id" + i);
			  var goods_price=$.cookie("sale_price" + i);
			  var goods_num=$.cookie("buy_nums"+i);
			  if(goods_id!=null&&goods_id!="")
			  {
			    allcount=allcount+parseInt(goods_price*goods_num);
			  }
		  }
	   	 $("#total").html("  "+allcount.toFixed(2)+" ");
	  }
}
//统计选中商品总金额
function CheckedMoney(){
	  var allcount=0;
	  $("input:checkbox[class='goods']").each(function(){
		if(this.checked==true){
			var pos = this.id.lastIndexOf("_");
			var goods_id = this.id.substring(pos+1,this.id.length);
			var goods_price = eval($("#gprice"+goods_id).html());
			allcount = allcount + goods_price;
			$("#total").html("  "+allcount.toFixed(2)+" ");
		}else{
			$("#total").html("  "+allcount.toFixed(2)+" ");
		}
	});
}
//点击加号增加商品数量
function AddGoodsNum(goods_id,buy_nums){
   if($.cookie("buy_nums"+buy_nums)!=null&&$.cookie("buy_nums"+buy_nums)!="")
   {
	   var goodnum= parseInt($.cookie("buy_nums"+buy_nums));
	   goodnum=goodnum+1;
	   $.cookie("buy_nums" + buy_nums, goodnum, { expires: 7, path: '/' });
	}
	$("#gnumber"+goods_id).val("");
	$("#gnumber"+goods_id).val(goodnum);
	//小计
	ChangePrice(goods_id,buy_nums);
	//计算总价
	CheckedMoney();
 }
 //点击小箭头减少商品数量
 function SubGoodsNum(goods_id,buy_nums)
 {
 	//cookie里商品的数量
 	var goodnum=$.cookie("buy_nums"+buy_nums);
	if(goodnum>=1&&(goodnum-1)>=1)
	{
	 goodnum=goodnum-1;
	 $.cookie("buy_nums" + buy_nums, goodnum, { expires: 7, path: '/' });
	}
	$("#gnumber"+goods_id).val("");
	$("#gnumber"+goods_id).val(goodnum);
	//小计
	ChangePrice(goods_id,buy_nums);
	//计算总价
	CheckedMoney();
 } 
//当商品购物车数量改变的时候，修改小计价格
function ChangePrice(goods_id,buy_nums)
{
  	var allcount=0;
  	//数量
	var goods_Num=$.cookie("buy_nums" + buy_nums);
	//价格
	var goods_price=$.cookie("sale_price" + buy_nums);
	allcount=goods_Num*goods_price;
	sale_money=allcount;
	//总价
    $("#gprice"+goods_id).html(sale_money.toFixed(2));
    
}
 
//点击加入购物车弹出提示购物车信息
function showCartGoods(){
    //商品总数量
    var gnumber=0;
    //商品总价
    var allcount=0;
    var cart_cookieNum = parseInt($.cookie("cart_cookieNum"));
    for(var i = 1; i<= cart_cookieNum; i++){
	  	  var goods_id=$.cookie("goods_id" + i);
		  var goods_price=$.cookie("sale_price" + i);
		   var buynum = $.cookie("buy_nums" + i);
		  if(goods_id!=null&&goods_id!="") {
		     allcount=allcount+parseInt(goods_price)*buynum;
		     gnumber+=1;
		  }
    }
    $("#gcountnum").html(gnumber);
    $("#gcountmomey").html(allcount.toFixed(2));
}

//列表筛选弹出层操作-开始
function divfixed2(r,name){ 
   var sug=document.getElementById(name);
   sug.style.left=(getPosition(r).x - 150) +"px"; 
   sug.style.top= (getPosition(r).y -100 ) +r.offsetHeight+"px"; 
   sug.style.position="absolute"; 
   sug.style.display="block"; 
}
//显示购物车
function showCart(obj,val){
	divfixed2(obj,val);
}
//关闭购物车
function closeCart(val){
	$("#"+val).hide();
}
 //验证只能输入正整数 >=0
function checkNum(obj)
{
    var num_value=$(obj).val();
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(obj.value))
	  {
	     if(isNaN(obj.value)){
	        obj.value="";
	        obj.focus();
	        alert("只能输入数字,请正确输入!"); 
	        return false;
	     }
	  }
}
 //购物车选中商品
function cartCheck(obj,id){
	var val = obj.value;
	if(obj.checked){
		$("input:checkbox[id^="+id+"]").each(function(){
			this.checked=true;
			//判断店铺是否全选
			var unShopoChenkedNum = 0;
			$("input:checkbox[id^=cart_"+val+"_]").each(function(){
				if(this.checked==false){
					unShopoChenkedNum++;
				}
			});
			if(unShopoChenkedNum != 0){
				$("#cart_"+val).attr("checked",false);
			}else{
				$("#cart_"+val).attr("checked",true);
			}
		});
	}else{
		$("input:checkbox[id^="+id+"]").each(function(){
			this.checked=false;
			//店铺去除选中
			$("#cart_"+val).attr("checked",false);
		});
	}
	//判断是否全选
	var unCheckedNum = 0;//未选中个数
	$("input:checkbox[id^=cart_]").each(function(){
		if(this.checked==false){
			unCheckedNum++;
		}
	});
	if(unCheckedNum != 0){
		$("#cart").attr("checked",false);
	}else{
		$("#cart").attr("checked",true);
	}
	//根据选中个数判断是否可点击结算
	var checkedNum = 0;//选中个数
	$("input:checkbox[id^=cart_]").each(function(){
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
	var allcount = 0;
	$("input:checkbox[class='goods']").each(function(){
		if(this.checked==true){
			var pos = this.id.lastIndexOf("_");
			var goods_id = this.id.substring(pos+1,this.id.length);
			var goods_price = eval($("#gprice"+goods_id).html());
			allcount = allcount + goods_price;
			$("#total").html("  "+allcount.toFixed(2)+" ");
		}else{
			$("#total").html("  "+allcount.toFixed(2)+" ");
		}
	});
	
}
//选中全部
function cartCheckAll(id){
	$("input:checkbox[id^="+id+"]").each(function(){
			this.checked=true;
	});
}
//继续购买
function gobuy(){
	 window.location.href="/" 
}
//购物车结算
function cartsubmit(){
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
	var smode_id_str="";
	var ship_str="";
	var ship_price_str="";
	var ship_name_str="";
	$(".selcust").each(function(){
		 var sel_cust_id = $(this).val();
		 var sel_shop_qq = $("#shopqq"+sel_cust_id).val();
		 var sel_shop_name = $("#shopname"+sel_cust_id).val();
		 //获取选中商品的运输方式ID
		 var sel_smode_id = $("#smode_id"+sel_cust_id).val();
		 //获取选中商品的运输方式
		 var sel_ship = $("#ship"+sel_cust_id).val();
		 //配送方式名称
		 var sel_ship_name = $("#ship_name"+sel_cust_id).val();
		 //运费
		 var sel_ship_price = $("#ship_price"+sel_cust_id).val();
		 var count=0;
		 $("input:checkbox[id^=cart_"+sel_cust_id+"_]").each(function(){
		 		if(this.checked){
		 			//获取选中商品的goods_id
			 		var sel_goods_id = $(this).parent("td").find(".selcust_goods_id").val();
			 		//获取选中商品的购买个数
			 		var sel_goods_num = $("#gnumber"+sel_goods_id).val();
			 		//获取选中商品的规格值
			 		var sel_goods_specid = $("#spec_id"+sel_goods_id).val();
			 		//获取选中商品的规格名称
			 		var sel_goods_specname = $("#spec_name"+sel_goods_id).val();
			 		//获取选中商品的单价
			 		var sel_goods_price = $("#price"+sel_goods_id).val();
			 		//获取选中商品的图片
			 		var sel_goods_img = $("#goods_img"+sel_goods_id).val();
			 		//获取选中商品的名称
			 		var sel_goods_name = $("#goods_name"+sel_goods_id).val();
			 		
			 		goods_id_str += sel_goods_id +",";
			 		goods_num_str += sel_goods_num +",";
			 		goods_spec_id_str += sel_goods_specid +",";
			 		goods_spec_name_str += sel_goods_specname +",";
			 		goods_price_str += sel_goods_price +",";
			 		goods_img_str += sel_goods_img +",";
			 		goods_name_str += sel_goods_name +",";
			 		count++;
		 		}
		 });
		 if(count>0){
		 	 cust_id_str += sel_cust_id+",";
		 	 shop_name_str += sel_shop_name+",";
		 	 shop_qq_str += sel_shop_qq+",";
			 goods_length_str += count+",";
		 }
	})
	$("#cust_id_str").val(cust_id_str.substring(0,cust_id_str.length-1));
	$("#goods_id_str").val(goods_id_str.substring(0,goods_id_str.length-1));
	$("#goods_length_str").val(goods_length_str.substring(0,goods_length_str.length-1));
	$("#goods_name_str").val(goods_name_str.substring(0,goods_name_str.length-1));
	$("#goods_img_str").val(goods_img_str.substring(0,goods_img_str.length-1));
	$("#spec_id_str").val(goods_spec_id_str.substring(0,goods_spec_id_str.length-1));
	$("#spec_name_str").val(goods_spec_name_str.substring(0,goods_spec_name_str.length-1));
	$("#sale_price_str").val(goods_price_str.substring(0,goods_price_str.length-1));
	$("#give_inter_str").val(goods_price_str.substring(0,goods_price_str.length-1));
	$("#shop_name_str").val(shop_name_str.substring(0,shop_name_str.length-1));
	$("#order_num_str").val(goods_num_str.substring(0,goods_num_str.length-1));
	$("#shop_qq_str").val(shop_qq_str.substring(0,shop_qq_str.length-1));
	$("#smode_id_str").val(smode_id_str.substring(0,smode_id_str.length-1));
	$("#ship_str").val(ship_str.substring(0,ship_str.length-1));
	$("#ship_price_str").val(ship_price_str.substring(0,ship_price_str.length-1));
	$("#ship_name_str").val(ship_name_str.substring(0,ship_name_str.length-1));
	 //跳回登陆前位置
	$("#refloc").val("/cart.html");
    document.forms[0].action="/mall/order!goOrder.action";
    document.forms[0].submit();
}




















