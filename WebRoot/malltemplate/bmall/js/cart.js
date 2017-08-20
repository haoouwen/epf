//小计
var sale_money=0;
var is_limit=0;
//添加商品到cookie
function addCookie(cartpos) { 
         //读取当前购物车的数量
	     countCartNumber();
	     //判断当前购物车的数量是否超过控制的个数
	     var s_cart_number=0;
	     s_cart_number=parseInt($("#cart_id_num").html())+1;
	     if(s_cart_number>15){
	     	alert("购物车已满,确认前往清理购物车");
     	    window.location.href='/cart.html';
	     	return false;
	     }
	    //商品id
		var goodsid=$("#cart_goods_id").val();
		//商品名称
		//var goodsname=decodeURIComponent($("#cart_goods_name").val());
		//价格
		var goodsprice=$("#cart_goods_sale_price").val();
		//是否虚拟商品
		//var is_virtual = $("#cart_is_virtual").val();
		//图片
		//var goodsimg=decodeURIComponent($("#cart_goods_img_path").val()); 
		//店铺名称
		//var shopname=decodeURIComponent($("#cart_shop_name").val());
		//商品属性名称
		//var spec_name=decodeURIComponent($("#cart_sepc_name").val());
		//商品属性ID
		var spec_id=decodeURIComponent($("#cart_sepc_id").val());
		//店铺qq 
		//var shopqq=$("#cart_shopqq").val();
		//数量
		var buy_nums=$("#buy_nums").val();
		//卖家cust_id
		//var cust_id=$("#cart_shop_cust_id").val();
		//卖家radom_no
		//var radom_no=$("#cart_radom_no").val();
		//店铺会员名称
		//var user_name=decodeURIComponent($("#cust_name").val());
		//var  integral =$("#cart_give_inter").val();
		//设置购买数量默认为1
		if(buy_nums==null||buy_nums==0||buy_nums==""){
			buy_nums=1;
		}
	    var cart_cookieNum;
	    var cookie_id;
	    var result;
	    if(spec_id==null||spec_id==""){
	    	spec_id='0';
	    }
	    cookie_id=''+goodsid+spec_id;
	    if($.cookie("ccn")!=null  ){
	        cart_cookieNum=parseInt($.cookie("ccn"))+1;
	   		var goodsList=JSON.parse($.cookie("twgl"));
	    	var j_cookie_id;
	    	var k=0;
	    	for(var i=0;i<cart_cookieNum-1;i++){
	    		var goodsFlag=goodsList[i];
	    		var j_cookie_gis=goodsFlag.i.split('|');
		    	j_cookie_id=j_cookie_gis[3];
		    	if(cookie_id==j_cookie_id){
	    			buy_nums=parseInt(buy_nums)+parseInt(j_cookie_gis[1]);
			        j_cookie_gis[1]=buy_nums;
			        var alltwbc="";
			        for(var k=0;k<j_cookie_gis.length;k++){
			           alltwbc=alltwbc+j_cookie_gis[k]+"|";
			        }
			        if(alltwbc!=null&&alltwbc!=""){
			         alltwbc=alltwbc.substring(0,alltwbc.length-1);
			        }
			        goodsFlag.i=alltwbc;
			        goodsList[i]=goodsFlag;
			        var json = JSON.stringify(goodsList);
			        $.cookie("twgl", json, { expires: 7, path: '/' });
				 	k=1;
				 	break;
	    		}
	    	}
	    	if(k==0){
    			var goods =new Object();
				goods.i = spec_id+"|"+buy_nums+"|"+goodsid+"|"+cookie_id;
				//goods.goods_name = goodsname;
				//goods.list_img = goodsimg;
				     ////goods.gp = goodsprice;
				//goods.integral = integral;
				     ////goods.bn = buy_nums;
				//goods.shop_name = shopname;
				//goods.shop_cust_id = cust_id;
				//goods.shop_qq = shopqq;
				//goods.spec_name = spec_name;
				    ////goods.si = spec_id;
				//goods.radom_no = radom_no;
				//goods.is_virtual = is_virtual;
				    ////goods.ci = cookie_id;
				goodsList[cart_cookieNum-1]=goods;
				var json = JSON.stringify(goodsList);
				$.cookie("twgl", json, { expires: 7, path: '/' });
				$.cookie("ccn",cart_cookieNum, { expires: 7, path: '/' });
				//cookiesNumber(cookie_id,cart_cookieNum);
	    	}
 		}else{
			var goodsList = new Array();
			var goods =new Object();
			goods.i = spec_id+"|"+buy_nums+"|"+goodsid+"|"+cookie_id;
			//goods.goods_name = goodsname;
			//goods.list_img = goodsimg;
			    ////goods.gp = goodsprice;
			//goods.integral = integral;
			   ////goods.bn = buy_nums;
			//goods.shop_name = shopname;
			//goods.shop_cust_id = cust_id;
			//goods.shop_qq = shopqq;
			//goods.spec_name = spec_name;
			   ////goods.si = spec_id;
			//goods.radom_no = radom_no;
			//goods.is_virtual = is_virtual;
			   ////goods.ci = cookie_id;
			goodsList[0]=goods;
			var json = JSON.stringify(goodsList);
			$.cookie("twgl",json, { expires: 7, path: '/' });
			$.cookie("ccn",1, { expires: 7, path: '/' });
	 		//获取刚刚加入的购物车的商品
	 		// var goodsFlagc=goodsList[0];
	    	// var j_cookie_id=goodsFlagc.cookie_id;
	    	// cookiesNumber(j_cookie_id,1);
   		}
   		//头部购物车显示信息
   		showTopCart();
   		//加入购物成功提示
   		showCart(cartpos,'cartDiv');
   		
 }
 
 function addCookie_old(cartpos) { 
         //读取当前购物车的数量
	     countCartNumber();
	     //判断当前购物车的数量是否超过控制的个数
	     var s_cart_number=0;
	     s_cart_number=parseInt($("#cart_id_num").html())+1;
	     if(s_cart_number>20){
	     	alert("购物车已满,确认前往清理购物车");
     	    window.location.href='/cart.html';
	     	return false;
	     }
	    //商品id
		var goodsid=$("#cart_goods_id").val();
		//商品名称
		var goodsname=decodeURIComponent($("#cart_goods_name").val());
		//价格
		var goodsprice=$("#cart_goods_sale_price").val();
		//是否虚拟商品
		var is_virtual = $("#cart_is_virtual").val();
		//图片
		var goodsimg=decodeURIComponent($("#cart_goods_img_path").val()); 
		//店铺名称
		var shopname=decodeURIComponent($("#cart_shop_name").val());
		//商品属性名称
		var spec_name=decodeURIComponent($("#cart_sepc_name").val());
		//商品属性ID
		var spec_id=decodeURIComponent($("#cart_sepc_id").val());
		//店铺qq 
		var shopqq=$("#cart_shopqq").val();
		//数量
		var buy_nums=$("#buy_nums").val();
		//卖家cust_id
		var cust_id=$("#cart_shop_cust_id").val();
		//卖家radom_no
		var radom_no=$("#cart_radom_no").val();
		//店铺会员名称
		var user_name=decodeURIComponent($("#cust_name").val());
		var  integral =$("#cart_give_inter").val();
		//设置购买数量默认为1
		if(buy_nums==null||buy_nums==0){
			buy_nums=1;
		}
	    var cart_cookieNum;
	    var cookie_id;
	    var result;
	    if(spec_id==null){
	    	spec_id='0';
	    }
	    cookie_id=''+goodsid+spec_id;
	    if($.cookie("cart_cookieNum")!=null  ){
	        cart_cookieNum=parseInt($.cookie("cart_cookieNum"))+1;
	   		var goodsList=JSON.parse($.cookie("goodsList"));
	    	var j_cookie_id;
	    	var k=0;
	    	for(var i=0;i<cart_cookieNum-1;i++){
	    		var goodsFlag=goodsList[i];
		    	j_cookie_id=goodsFlag.cookie_id;
		    	if(cookie_id==j_cookie_id){
	    			buy_nums=parseInt(buy_nums)+parseInt(goodsFlag.buy_nums);
			        goodsFlag.buy_nums=buy_nums;
			        goodsList[i]=goodsFlag;
			        var json = JSON.stringify(goodsList);
			        $.cookie("goodsList", json, { expires: 7, path: '/' });
				 	k=1;
				 	break;
	    		}
	    	}
	    	if(k==0){
    			var goods =new Object();
				goods.goods_id = goodsid;
				goods.goods_name = goodsname;
				goods.list_img = goodsimg;
				goods.sale_price = goodsprice;
				goods.integral = integral;
				goods.buy_nums = buy_nums;
				goods.shop_name = shopname;
				goods.shop_cust_id = cust_id;
				goods.shop_qq = shopqq;
				goods.spec_name = spec_name;
				goods.spec_id = spec_id;
				goods.radom_no = radom_no;
				goods.is_virtual = is_virtual;
				goods.cookie_id = cookie_id;
				goodsList[cart_cookieNum-1]=goods;
				var json = JSON.stringify(goodsList);
				$.cookie("goodsList", json, { expires: 7, path: '/' });
				$.cookie("cart_cookieNum",cart_cookieNum, { expires: 7, path: '/' });
				//cookiesNumber(cookie_id,cart_cookieNum);
	    	}
 		}else{
			var goodsList = new Array();
			var goods =new Object();
			goods.goods_id = goodsid;
			goods.goods_name = goodsname;
			goods.list_img = goodsimg;
			goods.sale_price = goodsprice;
			goods.integral = integral;
			goods.buy_nums = buy_nums;
			goods.shop_name = shopname;
			goods.shop_cust_id = cust_id;
			goods.shop_qq = shopqq;
			goods.spec_name = spec_name;
			goods.spec_id = spec_id;
			goods.radom_no = radom_no;
			goods.is_virtual = is_virtual;
			goods.cookie_id = cookie_id;
			goodsList[0]=goods;
			var json = JSON.stringify(goodsList);
			$.cookie("goodsList",json, { expires: 7, path: '/' });
			$.cookie("cart_cookieNum",1, { expires: 7, path: '/' });
	 		//获取刚刚加入的购物车的商品
	 		// var goodsFlagc=goodsList[0];
	    	// var j_cookie_id=goodsFlagc.cookie_id;
	    	// cookiesNumber(j_cookie_id,1);
   		}
   		//头部购物车显示信息
   		showTopCart();
   		//加入购物成功提示
   		showCart(cartpos,'cartDiv');
   		
 }
 
 
 
 function cookiesNumber(cookie_id){
 //data返回0：没有存在该购物车商品1，1:存在该购物车商品
  var count_ck=0;
  $.ajax({  	 
       type: "post",    	     
       url: "/mall/goods!ifExistsCartGoods.action?j_cookie_id="+cookie_id,
       datatype:"json",
       success: function(data){
       	   count_ck=data;
       }
   });  
   return count_ck;
 }
//统计当前购物车的商品数量
function countCartNumber(){
  	 $.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!getCarNumCount.action",
          datatype:"json",
          success: function(data){
                var cart_num=0;
				if($.cookie("ccn")!=null){
					cart_num=parseInt($.cookie("ccn"));
				}
          		if(cart_num!=null){
          			cart_num=parseInt(data)+cart_num;
          		}else{
          		    $.cookie("ccn", null, { expires: 7, path: '/' });
          		}
          		if(cart_num ==null || cart_num=='' || cart_num<0){
					$("#cart_id_num").html("0");
					$(".numi").html("0");
				}else{
					$("#cart_id_num").html(cart_num);
					$(".numi").html(cart_num);
				}
          }
      });  
}
 //头部显示购物车数量
function showTopCart(){
    countCartNumber();  
}
 
//删除购物车某一个商品
function removerCookies(trade_id,goodsid){
	if(confirm("确定删除该商品？")) {
	    if(trade_id!= null && trade_id!=""){
		    // 删除单个商品
		    mallClearItem(trade_id,goodsid);
		    //移除tr
			$("#item"+goodsid).remove();
		}
	}
	isEmptyCart();
}
//批量删除购物车
function removerAllCookies(){
	var trade_id=""
	var goodsid="";
	  var ids="";
	  var id="";
	if(confirm("确定删除该商品？")) {
	var i=0;
			$("input:checkbox[class='goods']").each(function(){
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
function insertColl(title,goods_id,price){
   var link_url = document.location.href;
  if (link_url.indexOf('/mall') > -1) {
                var posi = link_url.indexOf('/mall');
                var loc = link_url.substring(0, posi+1);
                link_url=loc+"mall-goodsdetail-"+goods_id+".html";
       }
   $.ajax({  	 
          type: "post",    	     
          url: "/collect!ajxinsert.action",
          data:{title:title,link_url:link_url,goods_id:goods_id,price:price},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("您还没登陆，请先登陆!","系统提示");
             	window.location.href="/login.html?loc=/cart.html";
             }else if(data=='2'){
             	jAlert("已经收藏过此商品!","系统提示");
             }else if(data=='0'){
                jAlert("收藏成功!","系统提示");
             }else if(data=='3')
                jAlert("不能收藏自己的商品!");
          }
      });  
  }

 //清除购物项
 function mallClearItem(trade_id,goodsid){
	  var result;
 	  //删除服务器上的购物车商品
	  $.ajax({
	        type: "post",
	        url: "/cartgoods!delCartGoods.action",
	        data:{'trade_id':trade_id},
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	        	result = data;
	        }
	   });
	   if(result == "1"){
	   	   window.location.reload();
	   }else{
	   		jNotify("删除失败！")
	   }
 }
 //判断购物车是否为空
function isEmptyCart(){
setTimeout('getCatNum()',100);
}
//统计购物车数量，获取数据库和cookies的个数
function getCatNum(){
  	 $.ajax({  	 
          type: "post",    	     
          url: "/mall/goods!getCarNumCount.action",
          datatype:"json",
          success: function(data){
                var all_car_num=0;
                //获取cookies的数量
				if($.cookie("ccn")!=null){
				   all_car_num =parseInt( $.cookie("ccn"));
				}
          		if(all_car_num!=null){
          			all_car_num=parseInt(data)+all_car_num;
          		}else{
          			$.cookie("ccn", null, { expires: 7, path: '/' });
          		}
          		if(all_car_num>0){
					//选中所有商品
					cartCheckAll("cart");
					cartCheckAll("baoCart");
					$("#cartDiv input:checkbox").click(function(){
						cartCheck(this,$(this).attr("id"));
				    });
				    $("#baoCartDiv input:checkbox").click(function(){
						baoCartCheck(this,$(this).attr("id"));
				    });
					//计算选中商品价格
					setTimeout('CheckedMoney()',100);
					//计算选中商品价格
					setTimeout('CheckedBaoMoney()',100);
				}else{
					$("#noEmptyCart").hide();
					$(".emptyCart").show();
				}
          }
      });  

      
      
      getHistory() ;

}
//统计选中商品总金额
function CheckedMoney(){
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
	  $("input:checkbox[class='goods']").each(function(){
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
	 		integral=eval($("#integral"+trade_id).val());
	 		all_integral+=integral;
			count++;
		}
	});
		if(total_tax > 0) {
		 allcount = allcount + total_tax; 
		}
		$("#allnum").html(allnum);
		$("#count").html(count);
	//	$("#all_integral").html(all_integral);
	    $("#total_goodsprice").html(total_price.toFixed(2)+" ");
	    $("#total_tax").html(total_tax.toFixed(2)+" ");
		$("#total").html("  "+allcount.toFixed(2)+" ");
		if(total_tax > 0) {
			$("#gup").removeClass("gup2");
		}else {
		    $("#gup").addClass("gup2");
		}
}
 //点击加号增加商品数量
function AddGoodsNums(id,trade_id,total_stock,goods_id,limit_num,cookies_id){
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
	ChangePrice(id,trade_id,buy_num);
	//计算总价
	CheckedMoney();
 }
function addNums(id,trade_id,total_stock,goods_id,limit_num,cookies_id){
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
	ChangePrice(id,trade_id,buy_num);
	//计算总价
	CheckedMoney();
 }
 //点击小箭头减少商品数量
 function SubGoodsNum(id,trade_id,goods_id,cookies_id)
 { 
	var buy_num = $("#gnumber_"+id).val();
	if(buy_num >= 1 && (eval(buy_num)-1) >= 1)
	{
	  buy_num = buy_num - 1;
	    //头部购物车显示信息
   		showTopCart();   	
	  //小计
	  ChangePrice(id,trade_id,buy_num);
	}
	$("#gnumber_"+id).val(buy_num);
	//给隐藏域赋值
	$("#gnumber"+goods_id+"_"+cookies_id.replace(':','_')).val(buy_num);
	//计算总价
	CheckedMoney();
 } 
//当商品购物车数量改变的时候，修改小计价格
function ChangePrice(id,trade_id,buy_num)
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
	        jNotify("只能输入数字,请正确输入!"); 
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
	CheckedMoney();
	
}
//选中全部
function cartCheckAll(id){
	$("input:checkbox[id^="+id+"]").each(function(){
			this.checked=true;
	});
}
//继续购买
function goBuy(){
	 window.location.href="/" 
}

//判断关税是否大于50，并提示
function checkTax() {
	//商品总关税
	var total_tax = 0;
	//商品总件数
	var goods_num = 0;
	//商品种类
	var goods_count = 0;
	//商品总计
	var total = 0;
    $("input:checkbox[id^=cart_1_]").each(function(){
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
	//if(total_tax > 50 && goods_num > 1) {
   //      $("#zhi_tax").hide();
	//     $("#zhi_count").show();
	//	 $(".tax").show();
	//	 $("#gup").find("i").show();
	//	 $("#count").html(goods_count);
	//	 $("#allnum").html(goods_num);
	//	 $("#total_tax").html(total_tax.toFixed(2));
	//	 $("#total_goodsprice").html(total.toFixed(2));
	//	 $("#total").html((eval(total_tax) + eval(total)).toFixed(2));
	//}else {
	    cartSubmit(); 
	//}
}

//购物车结算
function cartSubmit(){
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
	var sel_cust_id="1";
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
		 $("input:checkbox[id^=cart_"+sel_cust_id+"_]").each(function(){
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

//验证只能输入 >0(非负整数)
function checkNums(obj)
{
    var num_value=$(obj).val();
    var re =/^([1-9]\d*)$/;
	if (!re.test(obj.value)){
	     if(isNaN(obj.value)){
	        obj.value="1";
	        obj.focus();
	        jNotify("该文本框只能输入正整数,请正确输入!"); 
	        return false;
	     }
	     obj.value="1";
     	 obj.focus();
         jNotify("该文本框只能输入正整数,请正确输入!"); 
         return false;
	}
}

//获取最近浏览过的商品  
function getHistory() {  
        //最新访问的商品ID  
	    var history_goods_id; 
	    //商品名称
	    var history_goods_name; 
	    //商品图片
	    var history_list_img; 
	    //商品价格
	    var history_sale_price; 
	    var history_market_price; 
	    //cokie索引值
	    var history_goods_cookieIndex;
	    //cookie个数
	    var history_goods_cookieNum;
	    var liStr="";	
	    var historyIdsindex="";
        if ($.cookie("hgcn") != null) //cookie 不存在history_goods_cookieNum  
        {  
            //获取cookie个数
	    	history_goods_cookieNum = parseInt($.cookie("hgcn"));
			var historygoodsList=JSON.parse($.cookie("hglt"));
			var historyIds="";
			for(var i = 0; i< history_goods_cookieNum; i++){
	    	      var goodsFlag=historygoodsList[i];
		    	   historyIds +=goodsFlag.hi+",";
		    	   historyIdsindex+=goodsFlag.hn+",";
	    	}
			if(historyIds!=null&&historyIds!=""){
			   //商品ID串
			   historyIds=historyIds.substring(0,historyIds.length-1);
			   //cookies索引串
			   historyIdsindex=historyIdsindex.substring(0,historyIdsindex.length-1);
			}
			var historyIdsindexs=historyIdsindex.split(',');//cookies索引串数组
			  //获取商品信息
			  $.ajax({  	 
		          type: "post",    	     
		          url: "/mall/goods!getGoodsInfoByIdStr.action?gidstr="+historyIds,
		          datatype:"json",
		          success: function(data){
		           data=eval("("+data+")");
		           for(var i = 0; i < data.length; i++){
						  history_goods_id =data[i].goods_id;
						  history_goods_name = data[i].goods_name;
						  if(history_goods_name!=null&&history_goods_name!=""){
						    //截取商品名称标题
						    if(history_goods_name.length>12){
						      history_goods_name=history_goods_name.substring(0,11);
						    }
						  }
						  history_list_img = data[i].list_img;
						  history_sale_price =data[i].min_sale_price;
						  history_goods_cookieIndex =historyIdsindexs[i];
						  history_market_price = data[i].max_market_price;
						  if(history_market_price==null){
								history_market_price=	'';
							}else{
								history_market_price=	'￥<b>'+history_market_price+'</b>';
							}
						  if(history_goods_id != null && history_goods_id != ""){
						  	liStr+="<li id='item"+history_goods_cookieIndex+"'><div>"
						  	+"<a href='/mall-goodsdetail-"+history_goods_id+".html'>"
						  	+"<img src='"+history_list_img+"' /></a></div>"
						  	+"<p><span class='lpspan'>￥"+history_sale_price+"</span>"
						  	+"<span class='rsspan'></span><br class='clear'></p>"
						  	+"<p><a href='/mall-goodsdetail-"+history_goods_id+".html'>"+history_goods_name+"</a></p></li>";
						  }
					}
					//输出li节点
        			$("#historyCarRecord").append(liStr);
		          }
	           });  
      	}
}



















