//初始化加载
$(document).ready(function(){
	initAddr();
	//计算运费
	getOrderShipPrice();
	initShip();
	//绑定配送方式select事件
	$('.ship_fee').change(function(){
		initShip();
	}) 
	
  /*增加收货地址*/
  $(".addressCont .addrsp").click(function(){
        $("#addr_id").val("");
        $("#consignee").val("");
        $("#address").val("");
        $("#cell_phone").val("");
        $("#identitycard").val("");
        $("#areaDiv").html("");
        loadArea("1111111111","");
	  $("#addressId").popup({pWidth:"900",pHeight:"380",pTitle:"收货地址"});
  })
	//清楚优惠券，红包余额参数
	 $("input[name='coupon']").each(function(){
         $(this).attr("checked",false);
	 });
 	 $("#coupon_money").val("");
     $("#coupon_goods_id").val("");
     $("#coupon_id").val("");
     $("#comsumer_id").val("");
     $("#coupon_cust_id").val("");
     $("#red_money").val("");
     $("#redsumer_id").val("");
     $("#red_id").val("");
     $("#couponMoney").html("0");
     $("#usepayprice").html("0");
     $("#sub_total_negative").val(0);
     $("input[name='paytype_id']").each(function(){
         $(this).attr("checked",false);
	 });     
});
//初始化收货地址
function initAddr(){
	var srtTable="";
    var check="";
	srtTable+="<table width='100%' cellpadding='0' cellspacing='0' class='addrTab' id='addrTab'>";
	var dataUrl="/mall/order!initAddr.action";
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
          data=eval("("+data+")");
        	for(var i=0;i<data.length;i++){
	        	if(data[i].sel_address=='0'){
	        		check="checked='checked' class='useAdd useAddclass'";
	        		 $("#addr_id").val(data[i].addr_id);
        		 	 $("#end_area_attr").val(data[i].address_attr);
	        	}else{
	        	check="	class='useAdd' ";
	        	}
        		 srtTable+=" <tr id='"+data[i].addr_id+"'>";
        		 
		          srtTable+="<td><input value='"+data[i].addr_id+"' type='radio' "+check+"name='adrRadio' onclick='chooseAddr("+data[i].addr_id+")'/></td>"+"<td><b>商品寄至</b>"+data[i].area_attr;
		       	 srtTable+="  <span>  "+data[i].address+"</span><span>"+data[i].consignee+"(收)</span><span> "+data[i].cell_phone+"</span><span><i class='editor' onclick='auAddr("+data[i].addr_id+");'>编辑</i><i onclick='delAddr("+data[i].addr_id+");'>删除</i></span>";
		           srtTable+="  </td>";      
		         srtTable+="  </tr>";
        	}
        srtTable+="</table>";
        $("#addressDiv").html(srtTable);
        }
  	});	
} 
function initShip(){
	var total_ship_price = 0;
	$('.ship_fee').each(function(){
		var ship_price =eval ($("#ship_fee").html());//selected的值
		total_ship_price = total_ship_price + eval(ship_price);
		//var smode_id = $(this).children('option:selected').attr("id");//selected的值
		//更新店铺价格
		var shop_price = $(this).parent().parent().parent().parent().find(".goods_amount_str").val();//店铺小计不含运费
		var over_all=(eval(shop_price)+eval(ship_price) - eval($("#order_money").val()));
	     over_all	=Math.round(over_all * 100) / 100;
		$(this).parent().parent().parent().find(".orderprice").html(' 应付总额：'+over_all+'元');
	$("#now_order_allfund").val(over_all);
		
		//加上运费后的价格
		var goods_price = $("#goods_price").val();//商品总价
		var total_amount = eval(goods_price) + eval(total_ship_price);//订单总价
		if($("#integral_state").val()=="1") {
		  cfg_sc_exchscale=$("#cfg_sc_exchscale").val();
		  over_all = over_all * cfg_sc_exchscale
		}
		$("#total_amount").val(over_all);//总价
		$("#coupon_total").val(over_all);
		$("#red_total").val(over_all);
		$("#all_total").val(over_all);
		$("#all_totals").val(over_all);
		//运费
		$(this).parent().parent().prev().find("#ship_price").val(ship_price);
	    $(".cartprice").html(over_all);
	    $("#overprice").html(over_all);

		//配送方式ID
		//$(this).parent().parent().parent().find("#smode_id_str").val(smode_id);
		
	}) 
}


//AJAX新增收货地址
function addSellerAddr(){
	var consignee = $("#consignee").val();
	var address = $("#address").val();
	var post_code = $("#post_code").val();
	var phone = $("#phone").val();
	var cell_phone = $("#cell_phone").val();
	var addr_id=$("#addr_id").val();
	var identitycard=$("#identitycard").val();
	var area_attr = "";
	var toback=false;
	//验证收货人是否为空
	if(consignee == ""){
		jNotify("收货人姓名不能为空!"); 
		return false;
	}else{
		var en_consignee = encodeURIComponent(encodeURIComponent(consignee));
	}
	//地区串拼接以及验证
	$("[name=area_attr]").each(function(){
		if(this.value != "" && this.value !="0"){
			if(area_attr != ""){
				area_attr = area_attr +","+this.value;
			}else{
				area_attr = this.value;
			}
		}else{
			jNotify("请选择地区!"); 
			toback=true;
			return false;
		}
	});
	if(toback)
	{
		return false;
	}
	//验证街道地址是否为空
	if(address=="" || address==null){
		jNotify("街道地址不能为空!"); 
		return false;
	}else{
		var en_address = encodeURIComponent(encodeURIComponent(address));
	}
	//验证邮政编码是否为空
	if(post_code == ""){
		jNotify("邮政编码不能为空!"); 
		return;
	}
	if(identitycard == ""){
		jNotify("身份证编码不能为空!"); 
		return;
	}
	
	//校验邮编格式
	var p1 = /^[0-9]{6}$/;
	if(!p1.test(post_code)){
		jAlert("邮编格式有误!","系统提示");
	   return false;
	} 
	//验证电话和手机请至少填写一个
	if(phone == "" && cell_phone==""){
	   jNotify("请填写至少一个联系方式");
	   return false;
	}

	//校验手机号码格式
	var p3 = /^1[3,4,5,7,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   jAlert("手机格式有误!","系统提示");
	   return false;
	} 
	
	var dataUrl="/mall/order!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&addr_id="+addr_id+"&phone="+phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr+"&identitycard="+identitycard;
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        		jNotify("请先登录！");
             	return false;
        	}else if(data=='1'){
        		jNotify("收货地址已达到最大保存个数！");
             	return false;
        	}else{
        		$("#addr_id").val(data);
        		jNotify("操作成功！");
				//隐藏地址输入框
				$(".addAddress").toggle("fast");
				initAddr();
        	}
        }
  	});
}


//AJAX新增收货地址
function addAddr(){
	var consignee = $("#consignee").val();
	var address = $("#address").val();
	var post_code = $("#post_code").val();
	var area_code = $("#area_code").val();
	var phone = $("#phone").val();
	var code_phone = "";
	var cell_phone = $("#cell_phone").val();
	var area_attr = "";
	var addr_id=$("#addr_id").val();
	var identitycard=$("#identitycard").val();
	
	var toback=false;
	//验证收货人是否为空
	if(consignee == ""){
		jNotify("收货人姓名不能为空!"); 
		return false;
	}else{
		var en_consignee = encodeURIComponent(encodeURIComponent(consignee));
	}
	if(identitycard == ""){
		jNotify("身份证编码不能为空!"); 
		return;
	}
	//地区串拼接以及验证
	$("[name=area_attr]").each(function(){
		if(this.value != "" && this.value !="0"){
			if(area_attr != ""){
				area_attr = area_attr +","+this.value;
			}else{
				area_attr = this.value;
			}
		}else{
			jNotify("请选择地区!"); 
			toback=true;
			return false;
		}
	});
	if(toback)
	{
		return false;
	}
	//验证街道地址是否为空
	if(address == ""){
		jNotify("街道地址不能为空!"); 
		return false;
	}else{
		var en_address = encodeURIComponent(encodeURIComponent(address));
	}
	if(cell_phone ==""){
		jNotify("手机号码必填!"); 
		return false;
	}
	//校验手机号码格式
	var p3 = /^1[3,4,5,7,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   jAlert("手机格式有误!","系统提示");
	   return false;
	} 
	
	
  if(identitycard != ""){
	  if(!validateIdCard(identitycard)){
	     jAlert("身份证格式不正确");
	     return false;
	  }
	}
	
	var dataUrl="/mall/order!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&addr_id="+addr_id+"&phone="+code_phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr+"&identitycard="+identitycard;
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        		jNotify("请先登录！");
             	return false;
        	}else if(data=='1'){
        		jNotify("收货地址已达到最大保存个数！");
             	return false;
        	}else{
        		jNotify("操作成功！");
				initAddr();			
				//获取配送方式
				getOrderShipPrice();   	
				//重新计算价格
				initShip();
        	}
        }
  	});
  	 	
  	 $("#addressId").saveBut();	
}

function validateIdCard(idCard){
 //15位和18位身份证号码的正则表达式
 var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

 //如果通过该验证，说明身份证格式正确，但准确性还需计算
 if(regIdCard.test(idCard)){
  if(idCard.length==18){
   var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
   var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
   var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
   for(var i=0;i<17;i++){
    idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
   }

   var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
   var idCardLast=idCard.substring(17);//得到最后一位身份证号码

   //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
   if(idCardMod==2){
    if(idCardLast=="X"||idCardLast=="x"){
     return true;
    }else{
     return false;
    }
   }else{
    //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
    if(idCardLast==idCardY[idCardMod]){
     return true;
    }else{
      return false;
    }
   }
  } 
 }else{
   return false;
 }
}


function auAddr(id){
    
     $("#addressId").popup({pWidth:"900",pHeight:"380",pTitle:"收货地址"});
     $("#addr_id").val(id);
	var dataUrl="/mall/order!auAddr.action?id="+id;
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        var obj = (new Function("return " + data))(); 
         $("#consignee").val(obj.consignee);
         $("#address").val(obj.address);
         $("#cell_phone").val(obj.cell_phone);
         $("#identitycard").val(obj.identitycard);
         ge('1111111111,'+obj.area_attr);
        }
  	});	
}

//AJAX删除收货地址
function delAddr(addr_id){
	jConfirm('确定要删除该地址？', '系统提示',function(r){
         if(r){ 
			var dataUrl="/mall/order!delAddr.action?addr_id="+addr_id;
			 $.ajax({  	 
		        type: "post",    	     
		        url: dataUrl,       
		        datatype:"json",
		        async:false,
		        success: function(data){
		        	if(data=='0'){
		        		jNotify("请先登录！");
		        	}else if(data=='1'){
		        		$("#"+addr_id).remove();
		        		jNotify("删除成功！");
		        		initAddr();
		        	}else{
		        		jNotify("删除失败！");
		        	}
		        }
		  	});
		 }
    });  
}

//选择收货地址
function chooseAddr(id){
 $("#addr_id").val(id);   	
   	//获取配送方式
	getOrderShipPrice();   	
	//重新计算价格
	initShip();
	//清楚优惠券，红包余额参数
	 $("input[name='coupon']").each(function(){
         $(this).attr("checked",false);
	 });
 	 $("#coupon_money").val("");
     $("#coupon_goods_id").val("");
     $("#coupon_id").val("");
     $("#comsumer_id").val("");
     $("#coupon_cust_id").val("");
     $("#red_money").val("");
     $("#redsumer_id").val("");
     $("#red_id").val("");
     $("#couponMoney").html("0");
     $("#usepayprice").html("0");
     $("#sub_total_negative").val(0);
     $("input[name='paytype_id']").each(function(){
         $(this).attr("checked",false);
	 });     
}

function ordersign(){
  var flage="1";
  var osign=$("#order_sign_str").val();
  var dataUrl="/mall/order!checkOrderSing.action?order_sign_str="+osign;
	 $.ajax({  	 
	       type: "post",    	     
	       url: dataUrl,       
	       datatype:"json",
	       async:false,
	       success: function(data){
	          flage=data;
	       }
	 	});
  return flage;
}

//提交实物订单
function submitOrder(){
    var ordersg=ordersign()
   if(ordersg=="0"){
     $("#jf_suborder").hide();
     $("#pt_suborder").hide();
     jAlert("您已经提交订单,请勿重复下单","系统提示");
   }else if(ordersg=="2"){
     jAlert("请先登录","系统提示");
   }else{
	var goods_amount_str=$("#goods_amount_str").val();
	var zy_count=$("#zy_count").val();
	var bx_count=$("#bx_count").val();
	var zy_num=$("#zy_num").val();
	var bx_num=$("#bx_num").val();
	var cfg_maxpay=$("#cfg_maxpay").val();
	cfg_maxpay=parseFloat(cfg_maxpay);
	//if(cust_id_str.indexOf("1")>-1){
		if((zy_num>1&&zy_count>cfg_maxpay)||(bx_num>1&&bx_count>cfg_maxpay)){
					jAlert("订单总额不能超过"+cfg_maxpay+"元","系统提示");
					return false;
		}
	//}
	
    var addr_id = $("input:[name='adrRadio']:checked").val();
   	if(addr_id == "" || addr_id == null || addr_id == undefined){
   		jNotify("请填写或选择收货地址！");
        return false;
   	}
  
	var cartId_str=$("#cartId_str").val();
	if(cartId_str!=null&&cartId_str!="undefined"){
	cartId_strs=cartId_str.substring(0,cartId_str.length-2)
     //删除服务器上的购物车商品
	  $.ajax({
	        type: "post",
	        url: "/cartgoods!delCartGoods.action",
	        data:{'trade_id':cartId_str},
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	        }
	   });
	   }
	   $("#addr_id").val(addr_id);
	   var goods_id_str=$("#goods_id_str").val();
	   var order_num_str=$("#order_num_str").val();
		var dataUrl="/mall/order!isLimit.action?goods_id_str="+goods_id_str+"&order_num_str="+order_num_str;
			 $.ajax({  	 
		        type: "post",    	     
		        url: dataUrl,       
		        datatype:"json",
		        async:false,
		        success: function(data){
		        	if(data=='2'){
		        		jNotify("请重新下单,您的订单中存在已下架的商品！");
		        	}else if(data=='3'){
		        		jNotify("请重新下单,您的订单中存在库存不足的商品！");
		        	}else{
		        			//验证优惠券是否已使用
		        			if($("#comsumer_id").val() != ""){
		        			   $.ajax({
								       type: "post",
								       url: "/mall/order!useState.action?use_id="+$("#comsumer_id").val()+"&use_type=1",
								       dataype:"json",
								       async:false,
								       success: function(data){
										         if(data=='0'){
                                                   jAlert("优惠券已使用！","系统提示");
								                   return false;
												  }else if($("#paytype_id").attr("checked")==true){
											   			 var psw=$("#pay_password").val();
														  if(psw=null||psw==""){
															   	jAlert("请输入支付密码！","系统提示");
															   	return false;
														  }
														//Ajax 判断会员输入的支付密码是否正确
													   $.ajax({
														       type: "post",
														       url: "/mall/order!is_PayPasswordRight.action?psw="+$("#pay_password").val()+"&use_paynum="+$("#use_paynum").val(),
														       dataype:"json",
														       async:false,
														       success: function(data){
														         if(data=='0'){
																     jAlert("支付密码不正确！","系统提示");
																	 return false;
																  } else if (data=='1'){
																  	jAlert("余额不足！","系统提示");
																  	return false;
																  }else{
																  	$("#subOrder").submit();
																  }
														       
														       }
													  });
							   		         }else{
						    	                    $("#subOrder").submit();
						    	                   } 
										       }
									  });
		        			   	
		        			}else if($("#redsumer_id").val() != ""){//验证红包是否已使用
		        			   $.ajax({
								       type: "post",
								       url: "/mall/order!useState.action?use_id="+$("#redsumer_id").val()+"&use_type=2",
								       dataype:"json",
								       async:false,
								       success: function(data){
										         if(data=='0'){
                                                   	jAlert("红包已使用！","系统提示");
								                   return false;
												  }else if($("#paytype_id").attr("checked")==true){
										   			 var psw=$("#pay_password").val();
													  if(psw=null||psw==""){
														   	jAlert("请输入支付密码！","系统提示");
														   	return false;
													  }
													//Ajax 判断会员输入的支付密码是否正确
												   $.ajax({
													       type: "post",
													       url: "/mall/order!is_PayPasswordRight.action?psw="+$("#pay_password").val()+"&use_paynum="+$("#use_paynum").val(),
													       dataype:"json",
													       async:false,
													       success: function(data){
													         if(data=='0'){
															     jAlert("支付密码不正确！","系统提示");
																 return false;
															  } else if (data=='1'){
															  	jAlert("余额不足！","系统提示");
															  	return false;
															  }else{
															  	$("#subOrder").submit();
															  }
													       
													       }
												  });
									            }else{
						    	                    $("#subOrder").submit();
						    	                   }  
										       }
									  });		        			
		        			}else if($("#paytype_id").attr("checked")==true){
							   			 var psw=$("#pay_password").val();
										  if(psw=null||psw==""){
											   	jAlert("请输入支付密码！","系统提示");
											   	return false;
										  }
										//Ajax 判断会员输入的支付密码是否正确
									   $.ajax({
										       type: "post",
										       url: "/mall/order!is_PayPasswordRight.action?psw="+$("#pay_password").val()+"&use_paynum="+$("#use_paynum").val(),
										       dataype:"json",
										       async:false,
										       success: function(data){
										         if(data=='0'){
												     jAlert("支付密码不正确！","系统提示");
													 return false;
												  } else if (data=='1'){
												  	jAlert("余额不足！","系统提示");
												  	return false;
												  }else{
												  	$("#subOrder").submit();
												  }
										       
										       }
									  });
									   
						 
			   		         } else{
						    	$("#subOrder").submit();
		  		  		     }
		        			
		        	}
		        }
		  	});
		}
}

//提交实物订单
function submitPreOrder(){
   
    var addr_id = $("input:[name='adrRadio']:checked").val();
   	if(addr_id == "" || addr_id == null || addr_id == undefined){
   		jNotify("请填写或选择收货地址！");
        return false;
   	}
   	var cookieId_str=$("#cookieId_str").val();
    if(cookieId_str!=null&&cookieId_str!="undefined"){
   	cookieId_strs=cookieId_str.substring(0,cookieId_str.length-2)
   	var cookies=cookieId_strs.split(",");
   	//清空购物车
   	 $.each(cookies,function(n,value){
   	 $.cookie(value, null, { expires: 7, path: '/' });
   	 var count=0;
   	 if(eval(($.cookie("ccn")))){
   	 	 count=eval(($.cookie("ccn"))) -1;
   	 }
   	
   	 $.cookie("ccn", count, { expires: 7, path: '/' });
   	});
   	}
	var cartId_str=$("#cartId_str").val();
	if(cartId_str!=null&&cartId_str!="undefined"){
	cartId_strs=cartId_str.substring(0,cartId_str.length-2)
   //删除服务器上的购物车商品
	  $.ajax({
	        type: "post",
	        url: "/cartgoods!delCartGoods.action",
	        data:{'trade_id':cartId_strs},
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	        	result = data;
	        }
	   });      	   
	  }
	  //判断库存是否为0
	   var direct_id = $("#direct_id").val();
	   var goodsnum = $("#goodsnum").val();
	   $("#addr_id").val(addr_id);
	   var dataUrl = "/mall/directOrder!stockstatus.action?direct_id=" + direct_id + "&goodsnum=" + goodsnum;
	   $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){ 
          	if(data=='1'){
          	  alert("库存不足");
          	}else if(data=='2') {
          	 alert("请重新下单,您的订单中存在超过限购的商品！");
          	}else if(data=='0') {
          	 alert("请重新下单,您的订单中存在已下架的商品！");
          	}else{
          	  $("#subOrder").submit();
          	}
          }	 
	   });
}

//商品评论提交执行的方法
function submitEvaluate(){
  jConfirm('您确定提交评价？', '系统提示',function(r){ 
    if(r){ 
			//获取商品的个数
		 	var detailcount=$("#count_list_numbe_id").val();
		 	var str_goods_id="";//商品ID
		 	var str_goods_feng="";//商品评分
		 	var str_goods_content="";//商品评价内容
		 	var str_goods_sharepic="";//商品晒图
			if(detailcount!=null&&detailcount!="0"){
			   for(var i=0;i<detailcount;i++){
			     var g_feng=$('input:radio[name="radio_'+i+'"]:checked').val();
			     var g_goods_id=$("#goods_"+i).val();
			     var g_content=$("#evaluate_content"+i).val();
			     if(g_content==null||g_content==""){
			     	g_content="好评";
			     }
			   //  var g_goods_sharepic=$("#uploadresult"+i).val();
			 var g_goods_sharepic="";
			  $("input:hidden[name=gimg"+i+"]").each(function(){
		   		g_goods_sharepic=g_goods_sharepic+this.value+",";
		    });	
		    var lastPos = g_goods_sharepic.lastIndexOf(',');
 		g_goods_sharepic=g_goods_sharepic.substring(0,lastPos);
		 //  g_goods_sharepic= g_goods_sharepic.substring(0,g_goods_sharepic.length-1);
			 alert(g_goods_sharepic);
			     str_goods_id+=g_goods_id+",";
			     str_goods_feng+=g_feng+",";
			     str_goods_content+=g_content+"##########";
			     str_goods_sharepic+=g_goods_sharepic+"##########";
			     
			   }
			   //获取所有商品ID串
			   if(str_goods_id!=null){
			   		str_goods_id=str_goods_id.substring(0,str_goods_id.length-1);
			   		$("#str_goods_id").val(str_goods_id);
			   }
			   //获取所以评分串
			   if(str_goods_feng!=null){
			   		str_goods_feng=str_goods_feng.substring(0,str_goods_feng.length-1);
			   		$("#str_goods_feng").val(str_goods_feng);
			   }
			   //获取所有评价内容串
			   if(str_goods_content!=null){
			   		str_goods_content=str_goods_content.substring(0,str_goods_content.length-10);
			   		$("#str_goods_content").val(str_goods_content);
			   }
			    //获取所有评价内容串
			   if(str_goods_sharepic!=null){
			   		str_goods_sharepic=str_goods_sharepic.substring(0,str_goods_sharepic.length-10);
			   		$("#str_goods_sharepic").val(str_goods_sharepic);
			   }
			}
			 //验证评价存在敏感字
			 var content=encodeURIComponent(encodeURIComponent($("#str_goods_content").val()));
			  $.ajax({
			       type: "post",
			       url: "/mall/goods!filterWord.action?ccontent="+content,
			       dataype:"json",
			       async:false,
			       success: function(data){
			         if(data!=''){
			           jAlert(data+"是敏感词!","系统提示");
			         }else{
			          $("#tradeForm").submit();
			         }
			       }
			  });
			 
		 } 
    });  

}

 //是否存在敏感字
function filterWord(){
  //获取商品的个数
   var detailcount=$("#count_list_numbe_id").val();
   for(var i=0;i<detailcount;i++){
			  var content=encodeURIComponent(encodeURIComponent($("#evaluate_content"+i).val()));
			  $.ajax({
			       type: "post",
			       url: "/mall/goods!filterWord.action?ccontent="+content,
			       dataype:"json",
			       async:false,
			       success: function(data){
			         if(data!=''){
			          jAlert(data+"是敏感词！","系统提示");
			         }
			       }
			  });
  }
}


//确认收货提交执行的方法
function submitConfirmReceip(){
  var str_password=$("#password_id").val();
  if(str_password==""||str_password==null){
   jAlert("请输入支付密码!","系统提示");
  }else{
	  jConfirm('您确认收货？', '系统提示',function(r){ 
	    if(r){ 
	      $("#pay").submit();
	    }
	  });  
  }
  
  
}
//申请退款提交执行的方法
function  isGet(){
		is_get=$('input[name="is_get"]:checked').val()
		
		if(is_get=='0'){
			$(".allr_efund").show();
			$(".isreturn").hide();
			$(".need_refund").hide();
			$(".proof").show();
			$(".refundmoney").show();
			$(".reason").show();
			$(".explain").show();
			$(".refundbut").show();
		}else if(is_get=='1'){
			$(".isreturn").show();
			$(".allr_efund").hide();
			$(".refundmoney").show();
			$(".need_refund").show();
			$(".proof").show();
			$(".reason").show();
			$(".explain").show();
			$(".refundbut").show();
		}
}

function submitRefund(){	
 var refundapp_id="";
 refundapp_id=$("#exchange_id").val();
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//手机格式正则表达式
    var value = $("#phone").val();
       if(value!='' && !reg_phone.test(value)){
   alert("手机号码格式不正确!");
	return;
    }
 if(refundapp_id!=""){
 	 document.getElementById('tradeForm').action='/bmall_Refundapp_orderBuyRefundmentUpdate.action';
 }
 $("#tradeForm").submit();
}
//同意退款提交执行的方法
function submitRefundAgree(){
  jConfirm('您确认同意退款申请1？', '系统提示',function(r){ 
    if(r){ 
      $("#tradeForm").attr("action","/mall/order!sellerAgreeIsneedReturn.action").submit();
    }
  });  
}

//卖家同意退款到买家账上的方法
function submitRefundMoney(){
  var cust_id = $("#sell_cust_id").val();
  var psw=$("#password_id").val();
  if(psw=null||psw==""){
	   	jAlert("请输入支付密码！","系统提示");
	   	return false;
  }
  
  var flag = true;
	//Ajax 判断会员输入的支付密码是否正确
   $.ajax({
	       type: "post",
	       url: "/mall/order!is_PayPasswordRight.action?cust_id="+cust_id+"&psw="+$("#password_id").val(),
	       dataype:"json",
	       async:false,
	       success: function(data){
	         if(data=='0'){
	            flag=false;
	         }
	       }
  });
  if(flag == false){
     jAlert("支付密码不正确！","系统提示");
	 return false;
  }else{
    jConfirm('您确认同意退款到买家账户上？', '系统提示',function(r){ 
    if(r){
      $("#tradeForm").attr("action","/mall/order!sellerAgreeRefundment.action").submit();
      }
    });  
  }

}
//拒绝退款提交执行的方法
function submitRefundDisAgree(){

  var rejectreason = $("#rejectreason").val();
  if(rejectreason=="" || rejectreason == null){
      jNotify("请输入拒绝原因");
      return false;
  
  }else{
    jConfirm('您确认拒绝退款？', '系统提示',function(r){ 
       if(r){ 
          $("#tradeForm").attr("action","/mall/order!sellerDisAgreeRefundment.action").submit();
       }
    });  
  }
  
  
}
//卖家同意退款后向买家提供退货地址提交执行的方法
function submitRefundAddr(){
    var cust_id = $("#seller_cust_id").val();
    
	var addr_id = $("input:[name='adrRadio']:checked").val();
   	if(addr_id == "" || addr_id == undefined){
   		jNotify("请填写或选择收货地址！");
        return false;
   	}

   	 var flag=true;
   	 $.ajax({
	       type: "post",
	       url: "/mall/order!is_address.action?cust_id="+cust_id,
	       dataype:"json",
	       async:false,
	       success: function(data){
	         if(data == '0'){
	           flag = false;
	         }
	       }
	  });
   	
   	if(flag == false){
   	    jNotify("请填写收货地址");
   	    return false;
   	}
   	
   	
	document.getElementById('tradeForm').action='/mall/order!setRefundAddr.action';
	$("#tradeForm").submit();
}
	

//计算运费（商品详细页）
function getOrderShipPrice(){
	//获取将要传递的URL参数
	var dataUrl;
   	var goods_id_str = "";//商品ID串
   	var order_num_str = "";//商品数量串
   	var spec_id_str="";//规格
   	var end_area_attr = "";//收货地址ID
   	end_area_attr=$("#end_area_attr").val();
  // 	spec_id_str=$("#spec_id_str").val();
   	$(".ordercont").each(function(){
   		//goods_id_str = $(this).find("#goods_id_str").val();
   		$(this).find("input:hidden[name='goods_id_str']").each(function(){
   			if(goods_id_str != ""){
   				goods_id_str = goods_id_str +","+ $(this).val();
   			}else{
   				goods_id_str = $(this).val();
   			}
   		});
   		$(this).find("input:hidden[name='order_num_str']").each(function(){
   			if(order_num_str != ""){
   				order_num_str = order_num_str +","+ $(this).val();
   			}else{
   				order_num_str = $(this).val();
   			}
   		});
   		$(this).find("input:hidden[name='spec_id_str']").each(function(){
   			if(spec_id_str != ""){
   				spec_id_str = spec_id_str +","+ $(this).val();
   			}else{
   				spec_id_str = $(this).val();
   			}
   		});
   		//order_num_str = $(this).find("#order_num_str").val();
   		var optionStr = "<option id='0' value='0'>免运费</option>";
   		dataUrl="/mall/goods!getOrderShipPrice.action?goods_id_str="+goods_id_str+"&buy_num_str="+order_num_str+"&end_area_attr="+end_area_attr+"&spec_id_str="+spec_id_str;
   		goods_id_str='';
   		order_num_str='';
   		   	$.ajax({
	        type: "post",
	        url: dataUrl,
	        datatype:"json",
	        async:false,
	        success: function(data){ 
	        	data=eval("("+data+")");
	        	//配送方式标识
	        	var smodeIdStr = "";
	        	//配送方式：配送方式+运费
	        	var shipStr = "";
	        	//配送名称
	        	var shipNameStr = "";
	        	//运费
	        	var shipPriceStr = "";
	        	//运费下拉选项
	        	var option = ""; 
	        	for(var i = 0; i < data.length; i++){
	        		if(option != ""){
	        			//option = option + "<option id='"+data[i].smode_id+"' value='"+data[i].ship_price+"'> "+data[i].ship_name+":"+data[i].ship_price+"元</option>";
	        		}else{
	        			if(data[i].ship_price==0){
	        				optionStr = "0";
	        			}else{
	        				option = data[i].ship_price;
	        			}
	        		}
				}
				if(option != ""){
					optionStr = option;
				}
	        }
	    });
	    $("#ship_fee").html(optionStr);
	    $("#total_ship").val(optionStr);
   	});

}

//加载物流动态
function getLOgisticsInfo(){
	     var showdata="";
	      var jq_lg_data=$("#logistics_query_id").val();
		 if(jq_lg_data!=null&&jq_lg_data!=""&&jq_lg_data.indexOf("ERROR")<0){
			 showdata="<iframe name='kuaidi100' id='kuaidi100' src='"+jq_lg_data+"' width='550' height='255' marginwidth='0' marginheight='0'"
		          + " hspace='0' vspace='0' frameborder='0' scrolling='no'></iframe>";
		  }else{
		     showdata=" 物流公司未返回数据，请到 <font color='#0579C6'>"+$("#kuai_company_id").val()+" </font> 物流官网查询或联系其公示电话";
		  }
		  $("#show_log").html(showdata);
}


function setHidden(id,value){
		$("#"+id).val(value);
}
function setPutong(){

        
         //发票抬头验证
        var look_up = $("#look_up").val();
        if(look_up==""){
              jAlert("发票抬头不能为空!","系统提示");
              return false;
        }
		$("#in_type").html("普通发票");
		$("#in_look_up").html($("#look_up").val());
		$("#in_content").html($("#p_content").val());
		$(".invStyle").hide();
		jNotify("已保存！");
}
function setZeng(){
      
          //单位名称验证
        var company_name = $("#company_name").val();
        if(company_name==""){
              jAlert("单位名称不能为空!","系统提示");
               return false;
        }
        
        //纳税人识别码验证
        var identifier = $("#identifier").val();
        if(identifier==""){
              jAlert("纳税人识别码不能为空!","系统提示");
               return false;
        }
        
        //注册地址验证
        var re_address = $("#re_address").val();
        if(re_address==""){
              jAlert("注册地址不能为空!","系统提示");
               return false;
        }

        	//校验固话格式
        var p2 = (/\d{3}-\d{8}|\d{4}-\d{7}/);
        var re_phone=$("#re_phone").val();
        if(re_phone==""){
                jAlert("注册电话不能为空!","系统提示");
			   return false;
        }else if(re_phone !="" && !p2.test(re_phone)){
			   jAlert("注册电话格式有误!","系统提示");
			   return false;
		} 
		
		  //开户银行验证
        var bank_name = $("#bank_name").val();
        if(bank_name==""){
              jAlert("开户银行不能为空!","系统提示");
               return false;
        }
		
		//银行卡号验证格式
		var cardid = /^\d{16|17|18|19}$/g; // 以19位数字开头，以19位数字结尾 
		var bank_id =  $("#bank_id").val();
		if(bank_id==""){
		       jAlert("银行卡号不能为空!","系统提示");
			   return false;
		}else if(bank_id !="" && !cardid.test(bank_id)){
			   jAlert("银行卡号格式有误!","系统提示");
			   return false;
		} 
		
		 //收票人姓名验证
        var ticket_name = $("#ticket_name").val();
        if(ticket_name==""){
              jAlert("收票人姓名不能为空!","系统提示");
               return false;
        }
		
		//收票人手机号码格式验证
		var p3 = /^1[3,4,5,7,8]\d{9}$/;
		var ticket_cell = $("#ticket_cell").val();
		if(ticket_cell==""){
		     jAlert("手机号不能为空!","系统提示");
		   return false;
		}else if(ticket_cell != "" && !p3.test(ticket_cell)){
		   jAlert("手机格式有误!","系统提示");
		   return false;
		} 
		
		
		//收票人省份不能为空
		 var v_areaDiv= jQuery("#v_area1111111111  option:selected").val();
		 if(v_areaDiv=="0"){
              jAlert("收票人省份不能为空!","系统提示");
               return false;
        }
        
        var areastr="";
        var optsel=$("select[name='v_area_attr']").find("option:selected");
       for(i=0;i<optsel.length;i++){
            areastr+=optsel[i].value+",";
       }
       areastr = areastr.substr(0, areastr.length-2);
       $("#v_area_attr").val(areastr);
		
		 //详细地址验证
        var addresstail = $("#addresstail").val();
        if(addresstail==""){
              jAlert("详细地址不能为空!","系统提示");
               return false;
        }
        
            //营业执照验证
        var license = $("#uploadresult0").val();
        if(license==""){
              jAlert("营业执照不能为空!","系统提示");
               return false;
        }
        
         //税务登记证验证
        var certificate = $("#uploadresult1").val();
        if(certificate==""){
              jAlert("税务登记证不能为空!","系统提示");
               return false;
        }
        $("#invoice_type").val("1");
		$("#in_type").html("增值税发票");
		$("#in_look_up").html($("#company_name").val());
		$("#in_content").html($("#z_content").val());
		$("#hz_content").html($("#z_content").val());
		$("#hcompany_name").val($("#company_name").val());
		$("#hidentifier").val($("#identifier").val());
		$("#hre_address").val($("#re_address").val());
		$("#hre_phone").val($("#re_phone").val());
		$("#hbank_name").val($("#bank_name").val());
		$("#hbank_id").val($("#bank_id").val());
		$("#hticket_name").val($("#ticket_name").val());
		$("#hticket_cell").val($("#ticket_cell").val());
		$("#haddresstail").val($("#addresstail").val());
		$("#hlicense").val($("#uploadresult0").val());
		$("#hcertificate").val($("#uploadresult1").val());
	    $("#invStyleId").saveBut();
		
		//$(".invStyle").hide();
		//jNotify("已保存！");
		
}

function setperson(){
     //开户银行验证
        var personlook_up = $("#personlook_up").val();
        if(personlook_up==""){
              jAlert("发票抬头不能为空!","系统提示");
               return false;
        }

	$("#in_type").html("普通发票");
		$("#in_look_up").html($("#personlook_up").val());
		var type=$("input[name='p_content_radio']:checked").val(); 
		if(type=='0'){
		$("#in_content").html("明细");
		$("#hp_content").val("明细");
		}else if(type=="1"){
		$("#in_content").html("办公用品(附购物清单)");
		$("#hp_content").val("办公用品(附购物清单)");
		}
		$("#invoice_type").val("0");
		$("#hpersonlook_up").val($("#personlook_up").val());
  $("#invStyleId").saveBut();
}

function noset(){
$("#in_type").html("不开发票");
$("#in_content").html("");
$("#hp_content").val("");
$("#in_look_up").html("");
$("#invoice_type").val("2");
 $("#invStyleId").saveBut();
}


//判断余额是否使用余额支付
function isUseNumPay(obj){
		var use_inter=0;
		var use_paynum=0;
		var shop_total_amount_str=0;
		var new_pay=0;
		var cfg_sc_exchscale=0;		
		cfg_sc_exchscale=$("#cfg_sc_exchscale").val();
		use_inter=$("#is_use_inter").val();
		use_paynum=$("#use_paynum").val();
		shop_total_amount_str=$("#now_order_allfund").val();
		shop_total_amount_str=	parseFloat(shop_total_amount_str);
		use_inter=parseInt(parseInt(use_inter)/parseInt(cfg_sc_exchscale));
		use_paynum=	parseFloat(use_paynum);
			shop_total_amount_str=shop_total_amount_str-eval(use_inter);
			if($("#paytype_id").attr("checked")==true){
						if(use_paynum>shop_total_amount_str){
						
							$("#use_paynum").val(shop_total_amount_str);
							$("#interprice").html(use_inter);
							$("#usepayprice").html(shop_total_amount_str);
							$("#overprice").html(0);
							$("#alltotal").html(0);
						}else{
							$("#interprice").html(use_inter);
							$("#usepayprice").html(use_paynum);
							$("#use_paynum").val(use_paynum);
							$("#overprice").html((shop_total_amount_str-use_paynum).toFixed(2));
							$("#alltotal").html((shop_total_amount_str-use_paynum).toFixed(2));
						}
			}else{
						$("#interprice").html(use_inter);
						$("#usepayprice").html(0);
					//	$("#use_paynum").val(0);
						$("#overprice").html(shop_total_amount_str);
						$("#alltotal").html(shop_total_amount_str);
			}
			
		
}
function useInter(){
		var use_inter=0;
		var use_paynum=0;
		var shop_total_amount_str=0;
		var new_pay=0;
		var cfg_sc_exchscale=0;		
		cfg_sc_exchscale=$("#cfg_sc_exchscale").val();
		use_inter=$("#is_use_inter").val();
		//$("#is_use_inter").val(use_inter);
		use_paynum=$("#use_paynum").val();
		shop_total_amount_str=$("#now_order_allfund").val();
		shop_total_amount_str=	parseFloat(shop_total_amount_str);
		use_inter=parseInt(parseInt(use_inter)/parseInt(cfg_sc_exchscale));
		use_paynum=	parseFloat(use_paynum);
		shop_total_amount_str=shop_total_amount_str-use_inter;
		if(shop_total_amount_str<0){
			use_inter=	parseFloat($("#now_order_allfund").val())*cfg_sc_exchscale/parseInt(cfg_sc_exchscale);
			$("#use_inter").val($("#now_order_allfund").val()*cfg_sc_exchscale)
			$("#is_use_inter").val($("#now_order_allfund").val()*cfg_sc_exchscale);
		}
	if($("#paytype_id").attr("checked")==true){
				if(use_paynum>shop_total_amount_str){
					$("#use_paynum").val(shop_total_amount_str);
					$("#interprice").html(use_inter);
					$("#usepayprice").html(shop_total_amount_str);
					$("#overprice").html(0);
				}else{
					$("#interprice").html(use_inter);
				//	$("#use_paynum").val(use_paynum);
					$("#usepayprice").html(use_paynum);
					$("#overprice").html(shop_total_amount_str-use_paynum);
				}
		}else{
			//	$("#use_paynum").val(0);
				$("#interprice").html(use_inter);
					$("#usepayprice").html(0);
					$("#overprice").html($("#now_order_allfund").val()-use_inter);
		}
	//	$("#is_use_inter").val(use_inter);
			
}
function getInter(){
	var all_use=0;
	var now_use=0;
	var cfg_sc_exchscale=0;		
	var max_inter=0;
	cfg_sc_exchscale=$("#cfg_sc_exchscale").val();
	$(".inter_sub").each(function(){
		now_use=$(this).val();
		max_inter=$(this).attr("max_inter");
		max_inter=	parseFloat(max_inter);
		now_use=	parseFloat(now_use);
		if(now_use>max_inter){
			now_use=max_inter;
			$(this).val(now_use);
		}
		now_use=parseInt(now_use);
		all_use+=now_use;
	});
	$("#is_use_inter").val(all_use);
	 useInter();
}

//积分订单提交
function submitIntegral(){
 //积分计算比例
 cfg_sc_exchscale=$("#cfg_sc_exchscale").val();
 //所有积分
 var allinter = $("#allinter").val();
 //计算需要使用积分
 var use_inter=	parseFloat($("#now_order_allfund").val())*cfg_sc_exchscale;
 if(eval(allinter) >= use_inter){
   $("#is_use_inter").val(use_inter);
   $("#inter_sub").val(use_inter);
   //提交订单
   submitOrder();
 }else{
   jAlert("兑换积分不够！");
 }
}
//使用优惠券 type：1优惠券 2 红包   index：优惠和红包累计索引值
function useCoupon(index,id,coupon_id,goods_id,type,red_money){
   //订单总价
   var total="0";
   //使用优惠券标识符
   var coupon_cust_id = $("#shop_cust_id"+goods_id).val();
   //包邮标识符
   var bao_ship = $("#bao_ship").val();
   //订单运费
   var total_ship = eval($("#total_ship").val());
   //优惠金额
   var money = eval(red_money);;
   //商品总关税
   var all_tax = $("#all_tax").val();
   //使用红包或者优惠券之后，出现总价是负数的时候 记录下来负数值
   var sub_total_negative=0;
   var flag=true; //选中标志 ture 选中
    $("input[name='coupon']").each(function(){
		 var isCheckd = $(this).attr("checked");
		 if($(this).val()==index){
		   flag=isCheckd;
		 }else if($(this).val()!=id&&isCheckd==true){
		   $(this).attr("checked",false);
		 }else{
		  $(this).attr("checked",false);
		 }
	 });
	
	if(flag){
		 total = $("#all_totals").val();
		 if(type=="1"){
		   //优惠券 商品打折后价格减去优惠价格
		  $("#goods_shop").html((eval($("#shoptotal_norate").val())-eval(money)).toFixed(2));
		 }else{
		  //恢复原来的价格
		    $("#goods_shop").html($("#shoptotal_norate").val());
		 }
	}else {
   	   if(type=="1"){
   	      //获取新的优惠价格
	      total = $("#coupon_total").val();
	   }else {
	      total = $("#red_total").val();
	   }
	   $("#goods_shop").html($("#shoptotal_norate").val());
	} 
   if(flag){
     //选中优惠券或者红包
     total = (eval(total)-money);
     if(total < 0){
       sub_total_negative=total;
       total = 0;
       //记录下金额出现负数的值
       $("#sub_total_negative").val(sub_total_negative);
     }
     //选中优惠券
     if(type=="1"){
         if(eval(all_tax) > 0) {
         //差值关税
         var newtax = 0;
         //获取所有商品总价
         var goods_ids = goods_id.split(",");
         //获取所有商品总价
         var shoptoatl = 0;
         $.each(goods_ids,function(n,value) { 
          var subtotal = eval($("#subtotal"+value).val()) * eval($("#discount").val());
          shoptoatl += subtotal;
         }); 
         $.each(goods_ids,function(n,value) {  
         //单个商品总价打折后
	     var subtotal = eval($("#subtotal"+value).val()) * eval($("#discount").val());
	     //商品税率
	     var taxrate = $("#taxrate"+value).val();
	     //商品原关系
	     var totaltax = eval($("#totaltaxrate"+value).val()) * eval($("#discount").val());
	      //计算优惠商品后的差值关税
	      newtax += (eval(totaltax) -((eval(subtotal)- subtotal/eval(shoptoatl)* money) * eval(taxrate) / 100));
          }); 
	      $("#newtax").val(newtax);
	      //判断总关税是否小于0
	      if((eval(all_tax) - newtax) >0){
	             total = total - newtax;
	             $("#duty").html("关税："+(eval(all_tax) - newtax).toFixed(2)+"元");      
	       }else{
	            total = total - eval(all_tax);
	            $("#duty").html("关税：0元");
	       }
	       //用于后台可用优惠券 商品总价
	       $("#shopgoodstotal").val(shoptoatl);
	      } 
	     $("#coupon_money").val(money);
	     $("#coupon_goods_id").val(goods_id);
	     $("#coupon_id").val(coupon_id);
	     $("#comsumer_id").val(id);
	     $("#coupon_cust_id").val(coupon_cust_id);
	     $("#coupon_total").val(total);
	     $("#red_total").val($("#all_total").val());
         $("#red_money").val("");
         $("#redsumer_id").val("");
         $("#red_id").val("");	     
     }else{
         if(eval(all_tax) > 0) {
         $("#duty").html("关税："+(eval(all_tax)).toFixed(2)+"元");
         }
         $("#red_money").val(money);
         $("#redsumer_id").val(id);
         $("#red_id").val(coupon_id);
         $("#red_total").val(total);
         $("#coupon_total").val($("#all_total").val());
	     $("#coupon_money").val("");
	     $("#coupon_goods_id").val("");
	     $("#coupon_id").val("");
	     $("#comsumer_id").val("");
	     $("#coupon_cust_id").val("");         
     }
   }else{
     //取消选中 优惠券或者红包
     if(eval($("#all_total").val())==0){
       //判断取消选择之后 金额是否小于0的情况
       if(eval($("#sub_total_negative").val())<0){
          total=eval($("#sub_total_negative").val())+money;
          $("#sub_total_negative").val(0);
       }else{
         total = (eval($("#total_amount").val()));
       }
     }else{
       total = (eval(total)+money);
     }
     money = "0";
     if(total < 0){
       total = 0;
     }
     //优惠券
     if(type=="1"){
         //返回原关税
         if(eval(all_tax) > 0) {
           $("#duty").html("关税："+all_tax+"元");
           total = (eval(total) + eval( $("#newtax").val())); 
         }
	     $("#coupon_money").val("");
	     $("#coupon_goods_id").val("");
	     $("#coupon_id").val("");
	     $("#comsumer_id").val("");
	     $("#coupon_cust_id").val("");
	     $("#coupon_total").val(total);
     }else {
         //红包
         $("#red_money").val("");
         $("#redsumer_id").val("");
         $("#red_id").val("");
         $("#red_total").val(total);     
     }
     $("#red_total").val($("#total_amount").val());
     $("#coupon_total").val($("#total_amount").val());
   }	
   //控制 使用优惠券之后，重新判断是否达到免费的条件
     if(bao_ship !="" && bao_ship !="0") {
         var ship_str ="运费：";
         var needs_ship_3=$("#needs_ship_3").val();
         var ship_goodstotal_id=$("#ship_goodstotal_id").val();
         if(ship_goodstotal_id >= eval(needs_ship_3)){
             ship_str += "<span>0</span>元<i>亲,"+$("#webname").val()+"给你包邮啦!!</i>";
             if($("#is_ship_free").val() == "0"){
               total = total - total_ship;
               $("#is_ship_free").val("1");
             }
         }else {
             total = eval(total) + eval(total_ship);
             ship_str += "<span class='ship_fee' id='ship_fee'>"+total_ship+"</span>元";
             $("#is_ship_free").val("0");
         }
         if(type=="1"){
            $("#coupon_total").val(total.toFixed(2));
         }else{
            $("#red_total").val(total.toFixed(2));
         } 
         $(".ship_fee1").html(ship_str);
         $("#all_total").val(total.toFixed(2));
     }
     $("#couponMoney").html(money);
     $("#orderprice").html("应付总额："+total.toFixed(2)+"元");
     $("#now_order_allfund").val(total.toFixed(2));
     //判断是否使用余额支付
     if($("#paytype_id").attr("checked")==true){
       if(eval($("#use_num").val()) > total) {
         $("#usepayprice").html(total.toFixed(2))
          $("#use_paynum").val(total.toFixed(2))
         total = 0.00;
       }else {
         total = total -eval($("#use_num").val()) ;
         $("#use_paynum").val($("#use_num").val());
         $("#usepayprice").html($("#use_num").val());
       } 
     }else {
       $("#use_paynum").val($("#use_num").val())
     }

     $("#overprice").html(total.toFixed(2));
     $("#alltotal").html(total.toFixed(2)); 
     $("#total_amount").val(total.toFixed(2)); 
     
}