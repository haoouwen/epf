	var other_money;
	var refund_amount;
	function outbuy(){
		if(checkHow(getRedundMoney())){
			if($("#admin_reason").val()==null||$("#admin_reason").val()==''){
				jAlert("请输入处理理由！","系统提示");
			}else{
				jConfirm('将退给买家'+refund_amount+'元,退给卖家'+other_money+'元', '系统提示',function(r){ 
						 		if(r){ 
						 			document.getElementById("modiy_form").action="/admin_Refundapp_update.action?type=buy";
									document.getElementById("modiy_form").submit();
								 }
				});
			}
				 
		}
		
		
	}
	function outsell(){
		if(checkHow(getRedundMoney())){
			jConfirm('将退给卖家'+refund_amount+'元,退给买家'+other_money+'元', '系统提示',function(r){ 
			if(r){ 
						 			document.getElementById("modiy_form").action="/admin_Refundapp_update.action?type=sell";
									document.getElementById("modiy_form").submit();
								 }
			}); 
		}
			
		
	}
	//校验退款总金额小于订单总金额
	function checkHow(obj){
		var r_f;
		if($(obj).val()!=null){
			checkRMB(obj);
			r_f=$(obj).val();
		}else{
			r_f=obj;
		}
		if(r_f==null||r_f==''){
			jAlert("请输入退款总金额!","系统提示");
				return false;
		}
		var  all_money=$("#order_count").val();
		if(all_money!=null&&all_money!=''){
			all_money=parseFloat(all_money);
			if(r_f>all_money){
				jAlert("退款总金额应小于等于订单总金额","系统提示");
				return false;
			}else{
				other_money=all_money-r_f;
				$("#seller_amount").val(other_money);
				return true;
			}
		}
		
	}
	//取得退款金额
	function getRedundMoney(){
		refund_amount=$("#refund_amount").val();
		return refund_amount;
	}
	/*******************************************************************************************/
   /** 共用**/
	var seller_state_str;
	function getSellerState(seller_state){
	  seller_state_str = seller_state;
	  $("#refund_seller_state").val(seller_state);
	  if(seller_state=="0"){
	     $(".rejectReason").css("display","none");
	     $("#agreeState").css("display","");
	     $("#disAgreeState").css("display","none");
	  }
	  else if(seller_state=="1"){//拒接
	     $(".rejectReason").css("display","");
	     $("#agreeState").css("display","none");
	     $("#disAgreeState").css("display","");
	  }
	}
	
	
	//拒接退款处理
	function handleRefundGoods(){
	  if(seller_state_str=="0"){//同意
	     var pay_code = $("#pay_type").val();
	     var order_id = $("#order_id_str").val();
	     if(pay_code == "alipay" || pay_code =="alipaywap"){
	        $.ajax({
		     type:"post",
		     url:"/refundapp!verifyPay.action?order_id="+order_id,
		     datatype:"json",
		     async:false,
		     success:function(data){
		         if(data=='0'){
		            $("#modiy_form").attr("target", "_blank");
		            $("#modiy_form").attr("action","/mall-alipay-refund.html");
		            $("#setPay").popup_search({move_top:1,p_width:300,p_height:150,pop_title:"在线退款提示"});
		            $(".img_td").css("display","none");
		         }else if(data=='1'){
		            $("#modiy_form").attr("action","/admin_Refundapp_agreeRefundGoodsByAlipay.action");
		         }
		     }
		  });
	          
	     
	     }else if(pay_code =="wxpay"){
	          $("#modiy_form").attr("action","/admin_Refundapp_agreeRefundGoodsByWX.action");
	     }else if(pay_code == "unionpay"){
	          $("#modiy_form").attr("action","/admin_Refundapp_agreeRefundGoodsByUnionpay.action");
	     }else if(pay_code == "goldpay"){
	          $("#modiy_form").attr("action","/admin_Refundapp_agreeRefundGoods.action");
	     }
         
	     $("#modiy_form").submit();
          
	  }else if(seller_state_str=="1"){//拒接
	     var seller_reason =  $("#seller_reason").val();
	     if(seller_reason=="" || seller_reason==null){
	        alert("请输入拒接理由");
	        return false;
	     }else{
	        document.getElementById("modiy_form").action="/admin_Refundapp_disAgreeRefundGoods.action";
			document.getElementById("modiy_form").submit();
	     }
	  }else{
	     alert("请选择处理意见");
	     return false;
	  }
	    
	}
	
	var flag = true;
	
	function confirmRefund(){

	  $("#setPay").popup_search({move_top:1,p_width:300,p_height:150,pop_title:"在线退款提示"});
	  $("#form_search_id").submit();
	
	}
	
	//退货处理
	function handleReturnGoods(){
	  if(seller_state_str=="0"){//同意
           document.getElementById("modiy_form").action="/admin_Refundapp_agreeReturnGoods.action";
		   document.getElementById("modiy_form").submit();
	  }else if(seller_state_str=="1"){//拒接
	     var seller_reason =  $("#seller_reason").val();
	     if(seller_reason=="" || seller_reason==null){
	        alert("请输入拒接理由");
	        return false;
	     }else{
	        document.getElementById("modiy_form").action="/admin_Refundapp_disAgreeReturnGoods.action";
			document.getElementById("modiy_form").submit();
	     }
	  }else{
	     alert("请选择处理意见");
	     return false;
	  }
	    
	}
	//已选择同意退货未发送收货地址
	function agreeChoosedAddress(){
	    document.getElementById("modiy_form").action="/admin_Refundapp_agreeReturnGoods.action";
        document.getElementById("modiy_form").submit();
	}
	
    //发送收货地址给会员
	function sendAddressToMember(refundapp_id,field_name,actionName){
		// 值拼串
		var filedVal = '';	
		var checks = document.getElementsByName(field_name);
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked){
				if(checks[i].value!=""){
					filedVal += checks[i].value+',';
				}
			}
		}
		// 删除最后一个字符
		filedVal = deleteLastChar(filedVal,",");
		if(filedVal==''){
			art.dialog({
				title: '系统提示',
			    content: '请选择一个发送地址'
			});
			return false;
		}else{
			 document.getElementById('commonForm').action=actionName;
			 document.getElementById('commonText').name = "refundapp_id";
			 document.getElementById('commonText').value = refundapp_id;
			 document.getElementById('commonId').name = "addr_id";
			 document.getElementById('commonId').value = filedVal;
			 document.getElementById('commonForm').submit();	  
		}
	}
	
//退货申请
function confirmReturnGoods(){
     
     var pay_code = $("#pay_type").val();
     var order_id = $("#order_id_str").val();
     if(pay_code == "alipay" || pay_code =="alipaywap"){
        $.ajax({
	     type:"post",
	     url:"/refundapp!verifyPay.action?order_id="+order_id,
	     datatype:"json",
	     async:false,
	     success:function(data){
	         if(data=='0'){
	            $("#form_search_id").attr("target", "_blank");
	            $("#form_search_id").attr("action","/mall-alipay-refund.html");
	            $("#setPay").popup_search({move_top:1,p_width:300,p_height:150,pop_title:"在线退款提示"});
	            $(".img_td").css("display","none");
	         }else if(data=='1'){
	            $("#form_search_id").attr("action","/admin_Refundapp_agreeRefundGoodsByAlipay.action");
	         }
	     }
	  });
          
     }else if(pay_code =="wxpay"){
          $("#form_search_id").attr("action","/admin_Refundapp_agreeRefundGoodsByWX.action");
     }else if(pay_code == "unionpay"){
          $("#form_search_id").attr("action","/admin_Refundapp_agreeRefundGoodsByUnionpay.action");
     }else if(pay_code == "goldpay"){
          $("#form_search_id").attr("action","/admin_Refundapp_agreeRefundGoods.action");
     }
        
     $("#form_search_id").submit();
         
    
}
	