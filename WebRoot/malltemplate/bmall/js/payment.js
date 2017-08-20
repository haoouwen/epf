
/**初始化加载
$(document).ready(function(){
    
	if(total_price==0){
		$("#goConfirmPay").submit();
	}

});
**/


//进入账户余额支付
function subConfirmPay(){
    
    //属性值
	var cust_id = $("#session_cust_id").val();
	var pay_password = $("#pay_password").val();
	var use_num = $("#use_num").val();
	var pay_price = $("#total_amount").val();
	var order_id_str = $("#order_id_str").val();
	var order_type=$("#order_type").val(); 
     //验证库存是否够
     $.ajax({
	    type:"post",
	    url:"/mall/order!checkGoodsNum.action?order_id_str="+order_id_str,
	    datatype:"json",
	    async:false,
	    success:function(data){
		       if(data=="0"){
		         jNotify("商品库存不足");
		         return false;
		       }
		   }
	  });

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
    $("#payTypeFlag").val("1");
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

//进入第三方平台支付
function paySubmit(checkPayValue){
       //属性值
	    var order_id_str = $("#order_id_str").val();
	    //验证库存是否够
	     $.ajax({
		    type:"post",
		    url:"/mall/order!checkGoodsNum.action?order_id_str="+order_id_str,
		    datatype:"json",
		    async:false,
		    success:function(data){
			       if(data=="1"){
			            //库存够
					    $("#setPay").popup_search({move_top:0, p_height:230, pop_title:"在线支付提示"});
					    $("#goConfirmPay").attr("target","_blank");
					    if(checkPayValue=="alipay"){
					       $("#goConfirmPay").attr("action","/mall-alipay-send.html");
					    }else if(checkPayValue=="wxpay"){
					       $("#goConfirmPay").attr("action","/mall-wxpay-send.html");
					    }else if(checkPayValue=="unionpay"){
					       $("#goConfirmPay").attr("action","/mall-unionpay-send.html");
					    }
					   	$("#goConfirmPay").submit();
			       }else{
			         jNotify("商品库存不足");
			       }
			   }
		});
}


//进入网上银行
function bankPaySubmit(){
     //属性值
	var order_id_str = $("#order_id_str").val();
     //验证库存是否够
     $.ajax({
	    type:"post",
	    url:"/mall/order!checkGoodsNum.action?order_id_str="+order_id_str,
	    datatype:"json",
	    async:false,
	    success:function(data){
		       if(data=="0"){
		           jNotify("商品库存不足");
		           return false;
		       }else{
		               var checkPayValue =  $("input:radio[name='bankCode'][checked]").val(); 
					   if(checkPayValue=="" || checkPayValue==null || checkPayValue==undefined){
					      jNotify("请选择网上银行");
					      return false;
					   }else{
							$("#defaultbank").val(checkPayValue);
							
						    $("#setPay").popup_search({move_top:0, p_height:230, pop_title:"在线支付提示"});
						    $("#goConfirmPay").attr("target","_blank");
						    
						   
						    $("#goConfirmPay").attr("action","/mall-bank-send.html");
						  
						   	
						   	$("#goConfirmPay").submit();
					   }
		       }
		   }
	});
    
}
 