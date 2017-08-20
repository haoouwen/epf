//修改退款申请
function updateRefund(obj){
	var id=(obj+"").trim();
	location.href = "/mall-orderbuy-refund-"+id+".html";
}
//选择是退款还是退货的样式效果
function isReturn(obj){
	if(obj=="0"){
		//退款
		$("#tkbutton_id").remove("class").attr("class","siBut");
		$("#thbutton_id").remove("class").attr("class","niBut");
		$("#hhbutton_id").remove("class").attr("class","niBut");
		$("#liyou_id").html("退款原因");
		$("#jine_id").html("退款金额");
		$("#hh").hide();
		$("#th").show();
	}else if(obj=="1"){
		//退货
		$("#thbutton_id").remove("class").attr("class","siBut");
		$("#tkbutton_id").remove("class").attr("class","niBut");
		$("#hhbutton_id").remove("class").attr("class","niBut");
		$("#liyou_id").html("退货原因");
		$("#jine_id").html("退货金额");
		$("#hh").hide();
		$("#th").show();
	}else if(obj=="2"){
		//换货
		$("#thbutton_id").remove("class").attr("class","niBut");
		$("#tkbutton_id").remove("class").attr("class","niBut");
		$("#hhbutton_id").remove("class").attr("class","siBut");
		$("#liyou_id").html("退货原因");
		$("#jine_id").html("退货金额");
		$("#th").hide();
		$("#hh").show();
	}else{
	    //退款
		$("#tkbutton_id").remove("class").attr("class","siBut");
		$("#thbutton_id").remove("class").attr("class","niBut");
		$("#hhbutton_id").remove("class").attr("class","niBut");
		$("#liyou_id").html("退款原因");
		$("#jine_id").html("退款金额");
		$("#hh").hide();
		$("#th").show();
		obj="0";
	}
	$("#is_return").val(obj);
}
//全选商品
function getAll(){
	selectAll('re_goods_id_str');
	getRenM();
}
//通过选择商品，获得需要退款的总额
function getRenM(){
	var filedVal = '';
	var apply_num='';
	var back_goods_num = 0;
	var total_num ='';	
	//获取订单详情的交易ID,也就是对于的商品ID
	var checks = document.getElementsByName("re_goods_id_str");
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
				apply_num += $("#trade_"+checks[i].value).val()+',';
				back_goods_num += Number($("#trade_"+checks[i].value).val());
			}
			
		}
	}
	apply_num=apply_num.replace(/\undefined/g,'0');
	apply_num = deleteLastChar(apply_num,",");
	total_num = back_goods_num.toString()
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		
		 //退款商品总额，隐藏域保存到后台
  		$("#all_refund").val("0");
  		//显示退款上线
  		$("#sx").html("0");
  		//隐藏交易ID 也就是商品ID
  		$("#re_goods_id_str").val("");
		return false;
	}
	//AJAX去获取选择商品的价格和数量，来显示退款的上限金额
	$.ajax({
         type: "post",
         url: "/bmall_Refundapp_allRefund.action?orderde_id="+filedVal,
         contentType:"application/x-www-form-urlencoded;charset=utf-8", 
         datatype:"json",
         async:false,
         success: function(data){ 
	         if(data!=""){
	                //退款商品总额，隐藏域保存到后台
	         		$("#all_refund").val(data);
	         		//显示退款上线
	         		$("#sx").html(data);
	         		//隐藏交易ID 也就是商品ID
	         		$("#re_goods_id_str").val(filedVal);
	         		//提交可申请的商品数量
	         		$("#apply_num_str").val(apply_num);
	         		$("#back_goods_num").val(total_num);
	         } 	
         }	 
    });   
    return true;
}


//
function apply_deal(apply_id,apply_num,num){
   //alert(apply_id+"=========="+apply_num+"=========="+num)
   
   if(eval(num)<0){
      alert("申请数量不能小于0");
   
      $("#trade_"+apply_id).focus();
      
      return false;
   }
   
   if( eval(num) > eval(apply_num) ){
      
      alert("不能超过可申请的数量");
   
      $("#trade_"+apply_id).focus();
      $("#trade_"+apply_id).val(apply_num);
      return false;
   }
}

//加载页面的时候 默认值选中商品和获取选中商品id
function selectRadioGoods(){
//默认选中所有商品
		var str_goods="";
		$('input:checkbox').each(function() {
	        $(this).attr('checked', true);
	        str_goods=str_goods+$(this).val()+",";
	    });
	    //获取默认选中的的详情ID
	    if(str_goods!=null&&str_goods!=""){
	      str_goods=str_goods.replace("on,","");
	      str_goods=str_goods.substring(0,str_goods.length-1);
	    }
	    $("#re_goods_id_str").val(str_goods);
}
//撤销申请弹出层
function cancelOrder(trade_id,ret_price_str,ret_number_str,list_img_str,ret_type_str,ret_liyou_str,ret_desc_str,goods_id_str){
	if(list_img_str.indexOf("#")>-1){
	   list_img_str_s=list_img_str.split("#"); 
	}
	var orderHtml='<div class="popOrderDiv">'+
  	 '<table width="100%" cellpadding="0" bordercolorlight="0">'+
        '<tr><td width="16%" class="potd">售后类型：</td><td ><b class="mgreen">'+ret_type_str+'</b></td></tr>'+
        '<tr><td width="16%" class="potd">申请编号：</td><td ><b class="mgreen">'+ret_number_str+'</b></td></tr>'+
         '<tr><td width="16%" class="potd">申请金额：</td><td ><b class="mgreen">'+ret_price_str+'</b>元</td></tr>';
        orderHtml+='<tr><td width="16%" class="potd">申请原因：</td><td >'+ret_liyou_str+'</td></tr>';
        orderHtml+='<tr><td width="16%" class="potd">问题描述：</td><td >'+ret_desc_str+'</td></tr>';
        orderHtml+= '<tr><td class="potd"><span>*</span>撤销理由：</td><td><textarea  id="cancel_text_id" /></td></tr>';
        orderHtml+='<tr><td></td><td colspan="3"><input type="button" class="graybut" value="确定" onclick=\'submitCancel("'+trade_id+'")\'>&nbsp;&nbsp;<input type="button" class="graybut" value="取消" id="ddbutId"></td></tr>'+
     '</table>'+
    '</div>';
   $("#cancelOrderId").html(orderHtml);
	$("#cancelOrderId").popup({
		pOpacity:"0.7",
		ptBackground:"#f5f5f5",
		pcBackground:"#fff",
		pWidth:"500",
		pHeight:"380",
		pTitle:"撤销申请",
		oprid:"ddbutId"
  });
}

//取消订单处理
function submitCancel(order_id){
 var cancel_desr=$("#cancel_text_id").val();
 if(cancel_desr==null||cancel_desr==""){
  alert("请输入撤销理由");
  return;
 }
 $("#cancel_trade_id").val(order_id);
 $("#cancel_desr_id").val(cancel_desr);
 $("#indexForm").attr("action","/bmall_Refundapp_cancelRefund.action");
 $("#indexForm").submit();
}
//提交申请
function submitapprefund(){
    if(getRenM()){
	    $("#tradeForm").attr("action","/bmall_Refundapp_orderBuyRefundmentView.action");
	    $("#tradeForm").submit();
    }
}


//检查金额不能超过最大金额
function checkAll(obj,t_amount){
   var money=$(obj).val();
  if(eval(money) > eval(t_amount)){
     jNotify("退款金额不能超过退款上限!");	
    $("#need_refund").val(t_amount);
  }
}


