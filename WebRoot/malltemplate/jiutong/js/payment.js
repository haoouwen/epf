$(document).ready(function(){
  var type = $("#type").val();
  if(type=="0"){
    $("#weixinpay").show();
  }else{
    $("#weixinpay").hide();
  }
  //判断微信打开 去掉支付宝支付图标
  var ua = window.navigator.userAgent.toLowerCase();
   if(ua.match(/MicroMessenger/i) == 'micromessenger'){
       $("#alipay_id").hide();
   }else{
       $("#alipay_id").show();
   }
})


//进入金币支付
function subConfirmPay(){
    
    //属性值
	var cust_id = $("#session_cust_id").val();
	var pay_password = $("#pay_password").val();
	var use_num = $("#use_num").val();
	var pay_price = $("#total_amount").val();
	var order_id_str = $("#order_id_str").val();
	var order_type=$("#order_type").val(); 

	//判断账户余额是否足够支付
	if(eval(use_num) < eval(pay_price)){
		jNotify("账户余额不足！");
		return false;
	}
	
	if(pay_password==null || pay_password==""){
	    jNotify("请输入支付密码");
	    return false;
	}
	//校验支付密码
	if(!verifyPassword(cust_id,pay_password)){
	   jNotify("支付密码不正确");
	   return false;
	}
	
	//是否为预售订单，需进行支付时间判断
	if(order_type=="6"){
	    //校验预售订单
	    var flagtime = verifyPreOrder(order_id_str);
		if(flagtime=="0"){
	       jNotify("抱歉,该订单已经超过定金支付时间!");
	       return false;
		}else if(flagtime=="1"){
		   jNotify("抱歉,该订单已经超过尾款支付时间!");
		   return false;
		}
	}

    $("#goConfirmPay").submit();

}

//校验支付密码
function verifyPassword(cust_id,pay_password){
	var flag = true;
	//Ajax 判断会员输入的支付密码是否正确
    $.ajax({
	       type: "post",
	       url: "/mall/order!is_PayPasswordRight.action?cust_id="+cust_id+"&psw="+pay_password,
	       dataype:"json",
	       async:false,
	       success: function(data){
	         if(data=='0'){
	            flag=false;
	         }
	       }
	});
	return flag;
}

//校验预售订单 定金和尾款支付的截止时间
function verifyPreOrder(order_id_str){
	//判断支付定时时间过期或者支付尾款时间过期
	var flagtime;
    $.ajax({
    type:"post",
    url:"/mall/directOrder!pretimeout.action?order_id_str="+order_id_str,
    datatype:"json",
    async:false,
    success:function(data){
	       if(data=="0"){//定金支付超时判断
	          flagtime="0";
	       }else if(data=="1"){//尾款支付超时判断
	          flagtime="1";
	       }else if(data=="2"){//时间正常
	          flagtime="2";
	       }
	   }
	});
	return flagtime;
} 

//进入在线支付
function paySubmit(){

   var checkPayValue =  $("input:radio[name='paymentMethod'][checked]").val(); 
   if(checkPayValue=="" || checkPayValue==null || checkPayValue==undefined){
      jNotify("请选择在线支付的方式");
      return false;
   }else{
       //属性值
	    var order_id_str = $("#order_id_str").val();
		var order_type=$("#order_type").val();
		//是否为预售订单，需进行支付时间判断
		if(order_type=="6"){
		    //校验预售订单-如果是订单类型是 预售订单，需做支付截止时间的判断
			var flagtime = verifyPreOrder(order_id_str);
			if(flagtime=="0"){
		       jNotify("抱歉,该订单已经超过定金支付时间!");
		       return false;
			}else if(flagtime=="1"){
			   jNotify("抱歉,该订单已经超过尾款支付时间!");
			   return false;
			}
		}
	    $("#setPay").popup_search({move_top:0, p_height:230, pop_title:"在线支付提示"});
	    
	    $("#goConfirmPay").attr("target","_blank");
	    if(checkPayValue=="alipay"){
	       $("#goConfirmPay").attr("action","/mall-alipay-send.html");
	    }else if(checkPayValue=="yeepay"){
	       $("#goConfirmPay").attr("action","/mall-yeepay-send.html");
	    }else if(checkPayValue=="weixinpay"){
	       $("#goConfirmPay").attr("action","/mall-weixinpay-send.html");
	    }else if(checkPayValue=="unionpay"){
	       $("#goConfirmPay").attr("action","/mall-unionpay-wapsend.html");
	    }
	   	$("#goConfirmPay").submit();
   }
    
}

//进入在线支付
function webappPay(pay_type){
        //属性值
	    var order_id_str = $("#order_id_str").val();
	    //验证库存是否够
	     $.ajax({
		    type:"post",
		    url:"/webapp/order!checkGoodsNum.action?order_id_str="+order_id_str,
		    datatype:"json",
		    async:false,
		    success:function(data){
			       if(data=="1"){
			           if(pay_type=="" || pay_type==null || pay_type==undefined){
					      alert("请选择在线支付的方式");
					      return false;
					   }else{
					        var total_price=$("#total_price").val();
						    var order_id_str=$("#order_id_str").val();
						    
						    $("#goConfirmPay").attr("target","_blank");
						    if(pay_type=="alipaywap"){
						    
						      var is_app = $("#pay_terminal").val();
						      if(is_app=="1"){
						          $("#goConfirmPay").attr("action","/mall-alipaywap-send.html");
						      }else if(is_app=="2"){
						          $("#goConfirmPay").attr("action","/webapp/alipaymobileSendReq.html?order_id_str="+order_id_str);
						      }
						    }else if(pay_type=="unionpaywap"){
						       $("#goConfirmPay").attr("action","/mall-unionpaywap-send.html");
						    }else if(pay_type=="weixinpay"){
						       
						       $("#goConfirmPay").attr("action","/webapp/wxpaySendReq.html?order_id_str="+order_id_str+"&total_amount="+total_price);
						    }
						   	$("#goConfirmPay").submit();
					   }
					  $("#tipno").html("");
			       }else{
			         $("#tipno").html("&nbsp;&nbsp;&nbsp;<font color='red'>(商品库存不足)</font>");
			       }
			   }
		});





   
    
}

/**
 * 验证金额（可验证 大于等于零，小于等于99999999.99 的数字）
 * @param obj
 * @returns {Boolean}
 */
function checkMoney(obj){
    if(/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/.test(obj)){
        return true;
    }
}

function memberRecharge(){
     var val =  $("#recharge_amount").val();
     if(val==null || val==''){
        alert("请填写充值金额");
	    return false;
     }
     if(!checkMoney(val)){
	    alert("请填写正确的金额");
	    return false;
	 }
     
     var recharge_platform = $("#recharge_platform").val();
     if(recharge_platform=="alipaywap"){
	       $("#goConfirmPay").attr("action","/mall-alipaywap-send.html");
	    }else if(recharge_platform=="unionpay"){
	       $("#goConfirmPay").attr("action","/mall-unionpay-send.html");
	    }else if(recharge_platform=="weixinpay"){
	       $("#goConfirmPay").attr("action","/mall-weixinpay-send.html");
	    }
	   	$("#goConfirmPay").submit();
}


//调整在线支付页面
function cardSubComPay(){
    var id_num=$("#id_num").val();
    var id_num2=$("#id_num2").val();
    var id_num3=$("#id_num3").val();
    var id_num4=$("#id_num4").val();
    var plat = $("#recharge_platform").val();
    if(id_num!=null&&id_num!=""&&id_num2!=null&&id_num2!=""&&id_num3!=null&&id_num3!=""&&id_num4!=null&&id_num4!=""){
        var cardkey = id_num+"-"+id_num2+"-"+id_num3+"-"+id_num4;
        if(cardkey.length!=19){
         $("#tipcard").show();
          $("#tipcard").html("请输入16为充值卡号！");
         return;
        }else{
         $("#cardskey").val(cardkey);
         $("#tipcard").hide();
        }
        
        //判断卡号是否过期或者已经充值过
     var dataUrl="/bmall_Fundrecharge_checkcardkey.action?cardkey="+cardkey;
     var flag="0";
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        	 $("#tipcard").html("充值卡不存在！");
        	 $("#tipcard").show();
             	return false;
        	}else if(data=='1'){
        	 $("#tipcard").html("该充值卡已充值！");
        	 $("#tipcard").show();
             return false;
        	}else if(data=='2'){
        	 //充值卡状态正常可以进行充值
        	   flag="1";
        	}else if(data=='3'){
        	 $("#tipcard").html("该充值卡已过期！");
        	 $("#tipcard").show();
             return false;
        	}else if(data=='4'){
        	 $("#tipcard").html("系统异常！");
        	 $("#tipcard").show();
             return false;
        	}
        }
  	});  
        //进行充值卡充值操作
        if(flag=='1'){
        var cardskey= $("#cardskey").val();
	    var dataUrl="/webapp/memberuser!recardkey.action?cardskey="+cardskey;
        var flag="0";
	    $.ajax({  	 
          type: "post",    	     
          url: dataUrl,       
          datatype:"json",
          async:false,
          success: function(data){
        	if(data=='0'){
        	 $("#tipcard").html("充值卡不存在！");
        	 $("#tipcard").show();
             	return false;
        	}else if(data=='1'){
        	 $("#tipcard").html("该充值卡已充值！");
        	 $("#tipcard").show();
             return false;
        	}else if(data=='2'){
        	 $("#tipcard").html("充值成功！");
        	 $("#tipcard").show();
               return false;
        	}else if(data=='3'){
        	 $("#tipcard").html("该充值卡已过期！");
        	 $("#tipcard").show();
             return false;
        	}else if(data=='4'){
        	 $("#tipcard").html("系统异常！");
        	 $("#tipcard").show();
             return false;
        	}
         }
     });  
	   	
	   	}
    }else{
      $("#tipcard").show();
    }
}
