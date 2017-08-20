$(document).ready(function(){
	$("#dstableId").click(function(){
		$(".dstable").toggle("fast");
	});
});
//选择发货方式
function chooseSendMode(){
   
   var sendMode = $("input:[name='sendMode']:checked").val();
   if(sendMode=="" || sendMode==null){     
      alert("请选择发货方式");
      return false;
   }
   
   $("#tradeForm").attr("action","/admin_Goodsorder_DeliveryView.action").submit();
   
}

function chooseMode(flag){
   if(flag == "1"){
      $("#order_send_mode").css("display","inline");
      $("#businessTypeSpan").css("display","none");
   }else if(flag=="2"){
      $("#businessTypeSpan").css("display","inline");
      $("#order_send_mode").css("display","none");
   }else if(flag=="3"){
      $("#businessTypeSpan").css("display","none");
      $("#order_send_mode").css("display","none");
   }
}

//内部订单发货处理
function neibuChooseSendMode(){
   var sendMode = $("input:[name='sendMode']:checked").val();
   if(sendMode=="" || sendMode==null){     
      alert("请选择发货方式");
      return false;
   }
   
   if(sellerSend_num()==true){
       $("#tradeForm").attr("action","/admin_Goodsorder_neibuDelivery.action").submit();
   }
   
}


//ems确定发货
function emsConfirmDelivery(){
 	jConfirm('您确定要发货？', '系统提示',function(r){ 
 		if(r){ 
			$("#tradeForm").submit();
		 }
    });  
}

//中心发货
function confirmDeliveryGoods(){
    if(sellerSend_num()==true && sellerOrderWeight()==true){
	    jConfirm('确认发货信息？', '系统提示',function(r){ 
	 		if(r){ 
	 		     $("#tradeForm").submit();
			 }
	    });  
    }
}

//确定发货
function finalConfirmDeliver(){
    var flag= true;
    var order_id = $("#goods_order_ids").val();
    $.ajax({
		type:"post",
		url:"/goodsorder!getPrintKuaiDiState.action?goods_order_id="+order_id,
		datatype:"json",
		async:false,
		success:function(data){
			if(data=="0"){
				flag =true;
			}else{
				flag =false;
			}
		}
	});
    
    if(flag==true){
         jConfirm('确定发货？', '系统提示',function(r){ 
	 		if(r){ 
	 		     $("#tradeForm").submit();
			 }
	    });
	    return true;
    }else if(flag==false){
        alert("请先打印运单");
        return false;
    }
   
}

//中心发货
function adminSend_num(){
    var order_send_num_id=$("#order_send_num_id").val();
    if(order_send_num_id==""||order_send_num_id==null){
      alert("请输入运单号码");
      return ;
    }
}

//中心同意退款方法
function adminConfirmRefund(order_id,os){
	 jConfirm('您确定同意退款？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/admin_Goodsorder_adminConfirmRefund.action");
		}
    });  
}


//中心拒绝退款方法
function adminRefusedRefund(order_id,os){
	 jConfirm('您确定拒绝退款？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/admin_Goodsorder_adminRefusedRefund.action");
		}
    });  
}

//中心取消订单
function adminCancelOrder(order_id,os){
	 jConfirm('您确定取消订单？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/admin_Goodsorder_adminCancelOrder.action");
		}
    });  
}

//中心消费
function adminVirtualOrder(order_id,os){
	 jConfirm('您确定消费订单？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/admin_Goodsorder_adminVirtualUpdate.action");
		}
    });  
}

//设置订单编号和订单状态和执行的ACTION
function setstate_value(order_id,os,acitonname){
    //订单编号
	$("#goods_order_id").val(order_id);
	//订单状态
	$("#goods_order_state").val(os);
	$("#tradeForm").attr("action",acitonname).submit();
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
function ajaxLogisctic(o_id){
    var showdata="";
	$.ajax({
          type: "post",
          url: "/goodsorder!ajaxlogistics.action?o_id="+o_id,
          datatype:"json",
          async:false,
          success: function(data){ 
			 if(data!=null&&data!=""&&data.indexOf("ERROR")<0){
			       showdata="<iframe name='kuaidi100' id='kuaidi100' src='"+data+"' width='530' height='255' marginwidth='0' marginheight='0'"
		          + " hspace='0' vspace='0' frameborder='0' scrolling='no'></iframe>";
			  }else{
			 	 showdata="<tr><td>物流公司未返回数据</td></tr>";
			  }
	      }	 
	});   
   return showdata;
}
/*弹出物流跟踪*/
function tab_showLogistics(){
	$(".orderTab1 .wltd").hover(
		function(){
		    var order_id=$(this).find(".order_class").val();
		    if($(this).find(".send_namer_class").val()!=null&&$(this).find(".send_namer_class").val()!=""){
			    var loinfotitle="<tr style='color:0F0F0F;font-weight: bold;'><td width='15%'> 物流公司</td><td width='35%'><b class='bred' style='color:#0579C6;'>"+$(this).find(".send_namer_class").val()+"</b></td><td width='15%'> 运单号</td><td width='35%'><b style='color:#1B730C;'>"+$(this).find(".send_number_class").val()+"</b> </td></tr>";
			    $(this).find(".logisticsTabTitle").html(loinfotitle);
		    }
		     var loinfo=ajaxLogisctic(order_id);
		    $(this).find(".logisticsTab").html(loinfo);
			$(this).find(".wlDiv").show("fast");
			$(".wlpbut2").click(function(){
				$(".wlDiv").hide();
			})
		},
		function(){
			$(this).find(".wlDiv").hide();
		}
	) 
}
	
//验证运单
function sellerSend_num(){
    var order_send_num_id=$("#order_send_num_id").val();
    if(order_send_num_id==""||order_send_num_id==null){
      $("#ydhtip").html("请输入运单号码");
      $("#ydhtip").show();
      return false;
    }
    //获取输入配送单号的长度
    var sendnum_length=$("#order_send_num_id").attr("maxlength");
    if(parseInt(order_send_num_id.length)<parseInt(sendnum_length)){	
       $("#ydhtip").html("运单号不符合规则");
       $("#ydhtip").show();
       return false;
    }
    var reg = /^[A-Za-z0-9]+$/;
    if(!reg.test(order_send_num_id)){
      $("#ydhtip").html("运单号不符合规则");
      $("#ydhtip").show();
      return false;
    }
    $("#ydhtip").html("");
    $("#ydhtip").hide();
    return true;
}
//验证运单
function ValidateSend_num(send_num){
    if(send_num==""||send_num==null){
      $("#ydhtip").html("请输入运单号码");
      $("#ydhtip").show();
      return false;
    }
    var reg = /^[A-Za-z0-9]+$/;
    if(!reg.test(send_num)){
      $("#ydhtip").html("运单号不符合规则");
      $("#ydhtip").show();
      return false;
    }
    $("#ydhtip").html("");
    $("#ydhtip").hide();
    return true;
}
//判断订单商品重量是否填写
function sellerOrderWeight(){
    
    var order_weight=$("#orderweight").val();
    if(order_weight==""||order_weight==null){
      $("#weighttip").html("请称重并记录订单商品重量");
      $("#weighttip").show();
      return false;
    }else{
      $("#weighttip").html("");
      $("#weighttip").hide();
      return true;
    }
}

//修改运费
function showUpdateShip(oid,ship_free){
	$("#ship_oid").val(oid);
	$("#oldship_free").val(ship_free);
	$("#ship").popup_ship({p_width:"300", p_height:"150", pop_title:"修改运费"});
}
//打印票据
function showPintInvoice(oid){
	$("#invoice_order_id").val(oid);
	$("#invoice").popup_invoice({p_width:"230", p_height:"90", pop_title:"打印票据"});
}
//使用ajax访问后台，实现打印
function getContentByAjaxtoPrint(){
	var order_id= $("#invoice_order_id").val();
	var invoice_id= $("#id_invoice_id").val();
	$.ajax({
		type:"post",
		url:"/goodsorder!printInvoice.action?goodsorder.order_id="+order_id+"&flag_invoice_id="+invoice_id+"&ajaxRequestRandom="+Math.random(),
		datatype:"json",
		async:false,
		success:function(data){
			if(data!=null && data!=""){
				//调用打印票据
				RealPrint(data);
				$("#invoice").invoice_ccover();
			}else{
				alert("打印内容为空，请确认一下票据样式内容是否为空！");
			}
		}
	}
	);
}
//传送命令到打印机
function RealPrint(data) {
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	eval(data);	
		if (LODOP.PRINTA()) 
		   alert("已发出实际打印命令！"); 
		else
		   alert("放弃打印！"); 
	};

//打印多个快递单
function printMoreKuaiDiDan(){
    var goods_order_ids =  $("#goods_order_ids").val();
    var print_template_code = $("#print_template_code").val();
    //定义数组
    var goods_order_ids_str = new Array();
    goods_order_ids_str= goods_order_ids.split(",");
    for(var i=0;i<goods_order_ids_str.length;i++){
           //获取打印数据
		var data=ajaxDaYin(goods_order_ids_str[i],print_template_code);
		if(data!=null&&data!=""){
		        ajaxPrint_kuaidi(goods_order_ids_str[i],'1','4883647376');
			    eval(data);
	  			LODOP.PRINT();
	  			jNotify("打印中...");	
		}else{
		    jNotify("打印失败!");
		}
      }
}
	
	//ajax获取打印模板中的内容
	function ajaxDaYin(tid,print_template_code){
		var msg="";
		$.ajax({
	          type: "post",
	          data:{'oid':tid,'print_template_code':print_template_code},		      
	          url: "/goodsorder!ajaxGetDaYin.action",
	          datatype:"json",
	          async:false,
	          success: function(data){ 
	          	if(data!=null&&data!=""){
					msg=data; 
	          	}
	          },error:function(){
	          	jNotify("获取打印订单号"+tid+"运信息失败!");
	          }	 
		});  
		return msg;
	}
	//改变订单快递单打印状态
	function ajaxPrint_kuaidi(tid,type,p_smode_id){
	   $.ajax({
	          type: "post",
	          data:{'oid':tid,'ptype':type,'p_smode_id':p_smode_id},		      
	          url: "/goodsorder!ajaxPrintkuaidi.action",
	          datatype:"json",
	          async:false,
	          success: function(data){ 
	          	if(data=="0"){
	          	   return true;
	          	}
	          },error:function(){
	          	 alert("运单没有成功打印");
	          	 return false;
	          }	 
		});  
	}
	
	var seller_state_str;
	function getSellerState(seller_state){
	  var pay_code = $("#payCode").val();
	  seller_state_str = seller_state;
	  if(seller_state=="1"){
	    if(pay_code=="alipay" || pay_code == "alipaywap"){
	        $(".alipay_tip").show();
	    }else if(pay_code=="wxpay"){
	        $(".wxpay_tip").show();
	    }else if(pay_code=="unionpay"){
	        $(".unionpay_tip").show();
	    }else if(pay_code=="goldpay"){
	        $(".goldpay_tip").show();
	    }else if(pay_code=="integral"){
	        $(".integral_tip").show();
	    }
	    $(".reject_tip").hide();
	    $(".rejectReason").css("display","none");
	    $("#refund_seller_state").val(seller_state);
	     
	  }
	  else if(seller_state=="2"){//拒接
	     $(".rejectReason").css("display","");
	     $(".reject_tip").show();
	     $(".alipay_tip").hide();
	     $(".wxpay_tip").hide();
	     $(".unionpay_tip").hide();
	     $(".goldpay_tip").hide();
	     $(".integral_tip").hide();
	  }
	}

function handleCancelOrder(actionName){
    jConfirm('您确定提交操作？', '系统提示',function(r){ 
	    if(r){ 
		if(seller_state_str=="1"){//同意
           document.getElementById("tradeForm").action=actionName;
           var paycode =  document.getElementById("payCode").value;
           if(paycode=="alipay" || paycode=="alipaywap"){//支付宝退款
              document.getElementById("tradeForm").target="_blank";
              $("#setPay").popup_search({move_top:1,p_width:300,p_height:150,pop_title:"在线退款提示"});
           } 
		   document.getElementById("tradeForm").submit();
	    }else if(seller_state_str=="2"){//拒接
	      var seller_reason =  $("#cancelrejectreason").val();
	      if(seller_reason=="" || seller_reason==null){
	         alert("请输入拒绝原因");
	         return false;
	      }else{
	         document.getElementById("tradeForm").action="/admin_Goodsorder_rejectCancleOrder.action";
			 document.getElementById("tradeForm").submit();
	     }
	  }else{
	     alert("请选择处理意见");
	     return false;
	  }
		}
    });  
}

//拒接会员取消订单的操作
function rejectCancleOrder(){
   var seller_reason =  $("#cancelrejectreason").val();
   if(seller_reason=="" || seller_reason==null){
        alert("请输入拒绝原因");
        return false;
    }else{
        document.getElementById("tradeForm").action="/admin_Goodsorder_rejectCancleOrder.action";
	    document.getElementById("tradeForm").submit();
    }

}

//公共批量填写重量方法
function batchConfirmDelivery(cid,s_val,actionName){  
	  var sort_count=0;
      var tf_id="";
      var tf_sort="";
      $("input:checkbox[name='"+cid+"']").each(function(i){
			if(this.checked==true){
				tf_id+=$(this).val()+",";
				tf_sort+=$("input:text[name='"+s_val+"']").eq(i).val()+",";
				sort_count++;
			}	      
      });
      //id串
      tf_id=deleteLastChar(tf_id,",");
      //值串
      tf_sort=deleteLastChar(tf_sort,",");
      if(sort_count==0){
		  art.dialog({
			  title: '系统提示',
		      content: '请至少选择一条记录!'
		  });
		  return false;
	  }else{
        document.getElementById('commonId').name = "chb_id";
    	document.getElementById('commonId').value = tf_id;
    	document.getElementById('commonText').name = "orderweight";
    	document.getElementById('commonText').value = tf_sort;
    	document.getElementById('commonForm').action=actionName;
    	document.getElementById('commonForm').submit();	    
	}
} 
//批量审核订单
function batchauditOrder(){
      var o_count=0;
      var order_id="";
      $("input:checkbox[name='goodsorder.order_id']").each(function(i){
			if(this.checked==true){
				order_id+=$(this).val()+",";
				o_count++;
			}	      
      });
      //id串
      order_id=deleteLastChar(order_id,",");
      if(o_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:15px;width:300px;color:#FE7E03;">'+'请选择一个订单'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
            art.dialog({
			title: '<font style="font-weight:bold;font-size:15px;">订单审核</font>',
			content:'<div class="decorateaduit"><font color="black">'+'你确认要审核所选[<font size="5px;"  color="#CD3700;">'+o_count+'</font>]条订单吗?<br/><br/>审核后订单将会在【<font color="#CD3700;">发货管理</font>】可以进行打印运单，发货等操作。'+'</font></div>',
			okValue: '确定',
			width: '400px',
			cancelValue: '取消',
		    ok: function () {
			     $.ajax({
		          type: "post",
		          data:{'order_id':order_id},		      
		          url: "/goodsorder!auditOrderState.action",
		          datatype:"json",
		          async:false,
		          success: function(data){ 
			          	if(data=="0"){
			          	  jNotify("订单审核成功!");
			          	}
				      }
				 });  
		        //重新加载页面
		        location.reload();
				return true;
		    },
		    cancel: function () {
				return true;
		    }
		 });
	}

}
//批量提交待确认订单
function batchsumitWaitOrder(){
      var o_count=0;
      var order_id="";
      $("input:checkbox[name='goodsorder.order_id']").each(function(i){
			if(this.checked==true){
				order_id+=$(this).val()+",";
				o_count++;
			}	      
      });
      //id串
      order_id=deleteLastChar(order_id,",");
      if(o_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:15px;width:300px;color:#FE7E03;">'+'请选择一个订单'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
            art.dialog({
			title: '<font style="font-weight:bold;font-size:15px;">提交待发货</font>',
			content:'<div class="decorateaduit"><font color="black">'+'你确认要提交所选【<font size="5px;" color="#CD3700;">'+o_count+'</font>】条订单到待发货吗?<br/><br/>提交后订单将会在【<font color="#CD3700;">发货管理</font>】->【<font color="#CD3700;">待发货订单</font>】可以重新打印运<br/><br/>单、打印出库单、订单最终确认发货等操作。'+'</font></div>',
			okValue: '确定',
			width: '400px',
			cancelValue: '取消',
		    ok: function () {
			     $.ajax({
		          type: "post",
		          data:{'order_id':order_id},		      
		          url: "/goodsorder!submitWaitState.action",
		          datatype:"json",
		          async:false,
		          success: function(data){ 
			          	if(data=="0"){
			          	  jNotify("订单提交待确认发货成功!");
			          	}
				      }
				 });  
		        //重新加载页面
		        location.reload();
				return true;
		    },
		    cancel: function () {
				return true;
		    }
		 });
	}

}
//提交海关弹窗
function SubmitToCustomsShowDIV(id,order_id){
  $("#submit_order_id").val(order_id);
  $("#"+id).popup_search({move_top:0, p_height:190, pop_title:"提交海关"});
}
//弹出发货按钮
function fahuoShowDIV(id,order_id,order_money,order_date,order_name,order_area,order_tel,print_smode_no,send_mode,send_num){
  var order_info="";
  var shouhuo_info="";
  order_info="<br/>订单编号："+order_id+" <br/><br/> 订单金额："+order_money+" 元  <br/><br/>  下单时间："+order_date+"<br/>";
  shouhuo_info="<br/>收件人："+order_name+"<br/><br/>地&nbsp;&nbsp;址："+order_area+"<br/><br/>电&nbsp;&nbsp;话："+order_tel;
  $("#orderinfo_id").html(order_info);
  $("#shouhuoren_id").html(shouhuo_info);
  $("#fahuo_order_id").val(order_id);
  $("#orderweight").val("");
  $("#sendmode_id ").val(send_mode); 
  $("#order_send_num_id").val(send_num);
  loadsendmodelenth("sendmode_id","order_send_num_id");
  $("#"+id).popup_search({move_top:0, p_height:340, pop_title:"确认发货"});
}

//弹出发货按钮
function modifyfahuoShowDIV(id,order_id,order_money,order_date,order_name,order_area,order_tel,order_number,order_weight,send_mode){
  var order_info="";
  var shouhuo_info="";
  order_info="<br/>订单编号："+order_id+" <br/><br/> 订单金额："+order_money+" 元  <br/><br/>  下单时间："+order_date+"<br/>";
  shouhuo_info="<br/>收件人："+order_name+"<br/><br/>地&nbsp;&nbsp;址："+order_area+"<br/><br/>电&nbsp;&nbsp;话："+order_tel;
  $("#orderinfo_id").html(order_info);
  $("#shouhuoren_id").html(shouhuo_info);
  $("#fahuo_order_id").val(order_id);
  $("#order_send_num_id").val(order_number);
  $("#orderweight").val(order_weight);
  $("#sendmode_id").val(send_mode);
  $("#"+id).popup_search({move_top:0, p_height:340, pop_title:"确认修改物流"});
}

//确定发货
function postConfirmDelivery(){
    var order_send_num_id=$("#order_send_num_id").val();
    if(order_send_num_id==""||order_send_num_id==null){
      $("#ydhtip").show();
      $("#ydhtip").html("请输入运单号");
      return false;
    }else{
    	$("#ydhtip").hide();
    }
 	jConfirm('您确定要发货？', '系统提示',function(r){ 
 		if(r){ 
			$("#DeliveryForm").submit();
		 }
    });  
}
//确定修改发货
function modifypostConfirmDelivery(){
    var order_send_num_id=$("#order_send_num_id").val();
    if(order_send_num_id==""||order_send_num_id==null){
      $("#ydhtip").show();
      $("#ydhtip").html("请输入运单号");
      return false;
    }else{
    	$("#ydhtip").hide();
    }
 	jConfirm('您确定要修改物流？', '系统提示',function(r){ 
 		if(r){ 
			$("#DeliveryForm").submit();
		 }
    });  
}
  //单个提交待确认订单
function onesumitWaitOrder(order_id,print_state){
	var s_tip="";
	if(print_state==null||print_state==""){
	   s_tip="<font color='red' >【尚未打印运单】</font>";
	}
    art.dialog({
	title: '<font style="font-size:15px;">提交待发货订单</font>'+s_tip,
	content:'<div class="decorateaduit"><font color="black">'+'你确认要提交所选订单到待发货订单吗?<br/><br/>提交后订单将会在【<font color="#CD3700;">发货管理</font>】->【<font color="#CD3700;">待发货</font>】可以重新打印运<br/><br/>单、打印出库单、订单最终确认发货等操作。'+'</font></div>',
	okValue: '确定',
	width: '400px',
	cancelValue: '取消',
	   ok: function () {
	     $.ajax({
	         type: "post",
	         data:{'order_id':order_id},		      
	         url: "/goodsorder!submitWaitState.action",
	         datatype:"json",
	         async:false,
	         success: function(data){ 
	          	if(data=="0"){
	          	  jNotify("订单提交待发货成功!");
	          	}
		      }
		 });  
	       //重新加载页面
	       location.reload();
		return true;
	   },
	   cancel: function () {
		return true;
	   }
	});
}       
//不可打印提示框
function pintShowGrayDIV(id){
	alert("等待跨境通返回物流信息");
}  		
//弹出打印快递单子选择框
function pintExpressShowDIV(id,order_id,order_money,order_date,order_name,order_area,order_tel,printState,printnum,send_mode,send_num){
  var order_info="";
  var shouhuo_info="";
  var fahuo_tip="";
  order_info="<br/>订单编号："+order_id+" <br/><br/> 订单金额："+order_money+" 元  <br/><br/>  下单时间："+order_date+"<br/>";
  shouhuo_info="<br/>收件人："+order_name+"<br/><br/>地&nbsp;&nbsp;址："+order_area+"<br/><br/>电&nbsp;&nbsp;话："+order_tel;
  $("#send_orderinfo_id").html(order_info);
  $("#send_shouhuoren_id").html(shouhuo_info);
  $("#send_order_id").val(order_id);
  $("#print_sendmode_id ").val(send_mode); 
  $("#print_send_num_id").val(send_num);
  $("#ydhtip").hide();
  if(printState=="0"){
  	$("#repeat_tip").show();
  	$("#ptype").val("2");
  	if(printnum!=null&& printnum!="" && parseInt(printnum+1)>0){
   		 fahuo_tip="[补"+(parseInt(printnum)+1)+"]";
    }
  }else{
  	$("#repeat_tip").hide();
  	$("#ptype").val("1");
  }
  loadsendmodelenth("print_sendmode_id","print_send_num_id");
  $("#"+id).popup_search({move_top:0, p_height:340, pop_title:"打印运单"+fahuo_tip});
}          		
           		 
//打印快递单
function printKuaiDiDan(){
	if(ValidateSend_num($("#print_send_num_id").val())==false){
		return false;
	}
   jConfirm('您确定打印?', '系统提示',function(r){ 
		if(r){ 
		var goods_order_ids =  $("#send_order_id").val();
	    var semo_id = $("#print_sendmode_id").val();
	    var waybill_id = $("#print_send_num_id").val();
	    var print_template_code="";
	    //获取打印模版
	    $.ajax({
	          type: "post",
	          data:{'s_id':semo_id,'o_id':goods_order_ids,'w_id':waybill_id},		      
	          url: "/goodsorder!ajaxGetkuaidiMode.action",
	          datatype:"json",
	          async:false,
	          success: function(data){ 
	          	print_template_code=data;
	          }	 
		});  
	    //获取打印数据
		var data=ajaxDaYin(goods_order_ids,print_template_code);
		var ptype=$("#ptype").val();
		if(data!=null&&data!=""){
		        ajaxPrint_kuaidi(goods_order_ids,ptype,semo_id);
			    eval(data);
	  			LODOP.PRINT();
	  			jNotify("打印中...");
		}else{
		    jNotify("打印失败!");
		}
	 }
	 //重新加载页面
	  location.reload();
   }); 
}

//弹出批量打印快递单子选择框
function batchpintExpressShowDIV(id,type){
    var m_tip="打印";
    if(type=="1"){
      m_tip="打印";
    }else if(type=="2"){
      m_tip="补打";
    }

 var o_count=0;
     var order_id="";
     $("input:checkbox[name='goodsorder.order_id']").each(function(i){
		if(this.checked==true){
			order_id+=$(this).val()+",";
			o_count++;
		}	      
     });
     //id串
     order_id=deleteLastChar(order_id,",");
     if(o_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:15px;width:300px;color:#FE7E03;">'+'请选择一个订单'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
	    $("#batch_send_order_id").val(order_id);
	    $("#batch_info_id").html("您已选【<font size='5px;'>"+o_count+"</font>】条订单<br/><br/>"+m_tip+"后,订单将会在【发货管理】->【确认发货】<br/><br/>可以进行最终确认发货。<br/>");
  		$("#"+id).popup_search({move_top:0, p_height:200, pop_title:m_tip+"运单----共有"+o_count+"个运单需要打印"});
	  }
}
//批量打印快递单
function printMoreExpress(){
 jConfirm('您确定打印?', '系统提示',function(r){ 
 		if(r){ 
			var goods_order_ids =  $("#batch_send_order_id").val();
		    var semo_id=$("#batchprint_sendmode_id").val();
		    var ptype=$("#ptype").val();
		    var print_template_code="";
		    //获取打印模版
		    $.ajax({
		          type: "post",
		          data:{'s_id':semo_id},		      
		          url: "/goodsorder!ajaxGetkuaidiMode.action",
		          datatype:"json",
		          async:false,
		          success: function(data){ 
		          	print_template_code=data;
		          }	 
			});  
			var goods_order_ids_str = new Array();
		    goods_order_ids_str= goods_order_ids.split(",");
		    for(var i=0;i<goods_order_ids_str.length;i++){
		           //获取打印数据
				var data=ajaxDaYin(goods_order_ids_str[i],print_template_code);
				if(data!=null&&data!=""){
				        ajaxPrint_kuaidi(goods_order_ids_str[i],ptype,semo_id);
					    eval(data);
			  			LODOP.PRINT();
			  			jNotify("打印中...");
				}
		      }
		      //重新加载页面
			  location.reload();
		 }
    }); 
}

function submitCustoms(){
	var type = $("#customs_id").val();
	var order_id= $("#submit_order_id").val();
	
	closeSearchShowDIV('customs');//关闭窗口
	jQuery('body').showLoading();//Loading
	
	if(type=="001"){
		submitNone(order_id,'1');
	}else if(type=="002"){
		submitdirectkjt(order_id,'1')
	}else if(type=="003"){
		alert("杭州海关(功能开发中)");
		jQuery('body').hideLoading();//隐藏Loading画面
	}
}
//提交久通宏达（像酒类等线下交易）-直邮
function submitNone(orderid,type){
	//提交跨境通返回状态
 	var reminder ="";
 	$.ajax({
         type: "post",    
         url: "/admin_Offline_start.action?orderid="+orderid,
         datatype:"json",
         async:false,
         success: function(data){ 
	         if(data=='success'){
	         	jQuery('body').hideLoading();//隐藏Loading画面
	            art.dialog({
					title: '系统提示',
					content:'<div class="decorate">订单处理成功!</div>',
					okValue: '确定',
					width: '238px',
				    ok: function () {
				       window.location.href="/admin_Goodsorder_submitKJTList.action";
				    }
                });
	         }else{
	            alert("订单处理失败！");
             }
         }	 
	});  
}
//提交跨境通-直邮
function submitdirectkjt(orderid,type){
//提交跨境通返回状态
 var reminder ="";
 $.ajax({
		          type: "post",    
		          url: "/admin_KjtDirect_kjtjson.action?orderid="+orderid,
		          datatype:"json",
		          async:false,
		          success: function(data){ 
		          if(data=='1'){
		          alert("订单处于提交跨境通状态，请稍等！");
		          }else{
		           var jsonobj=eval('('+data+')'); 
		            if(jsonobj.Code=='0'&&jsonobj.Desc=='SUCCESS'){
		              reminder = "成功提交给跨境通";
		            }else{
		              reminder = jsonobj.Desc;
		            }
		            jQuery('body').hideLoading();//隐藏Loading画面
		            art.dialog({
							title: '系统提示',
							content:'<div class="decorate">'+reminder+'</div>',
							okValue: '确定',
							width: '238px',
						    ok: function () {
						      if(type=='1'){
							   window.location.href="/admin_Goodsorder_submitKJTList.action";
							   }else{
							   window.location.href="/admin_Goodsorder_submitKJTFailList.action";
							   }
						    }
                      });
                     }
		          }	 
			});  
}
//提交跨境通-保税
function submitkjt(orderid,type){
//提交跨境通返回状态
 var reminder ="";
 $.ajax({
		          type: "post",    
		          url: "/admin_Kjtapi_kjtjson.action?orderid="+orderid,
		          datatype:"json",
		          async:false,
		          success: function(data){ 
		          if(data=='1'){
		          alert("订单处于提交跨境通状态，请稍等！");
		          }else{
		           var jsonobj=eval('('+data+')'); 
		            if(jsonobj.Code=='0'&&jsonobj.Desc=='SUCCESS'){
		              reminder = "成功提交给跨境通";
		            }else{
		              reminder = jsonobj.Desc;
		            }
		            art.dialog({
							title: '系统提示',
							content:'<div class="decorate">'+reminder+'</div>',
							okValue: '确定',
							width: '238px',
						    ok: function () {
						      if(type=='1'){
							   window.location.href="/admin_Goodsorder_clearanceBslist.action";
							   }else{
							   window.location.href="/admin_Goodsorder_clearanceFaileBslist.action";
							   }
						    }
                      });
                     }
		          }	 
			});  
}
//加载快递单长度
function loadsendmodelenth(smid,smtip_id){
  var sendmode_id=$("#"+smid+"  option:selected").val();
  var sendmodelength="";
   $.ajax({
          type: "post",
          data:{'sendmode_id':sendmode_id},		      
          url: "/goodsorder!ajaxsendmodeLength.action",
          datatype:"json",
          async:false,
          success: function(data){ 
          	sendmodelength=data;
          }	 
	});  
	$("#s_length").html(sendmodelength);
	$("#"+smtip_id).attr("maxLength",sendmodelength);
}

//批量打印购物单 type 打印类型：1：第一次打印，2表示补打快递
function order_print_fahuo_all(type) {
    var m_tip="打印";
    var p_tip="";
    if(type=="1"){
      m_tip="打印";
      p_tip='<br/><br/>'+m_tip+'后订单将会在【<font color="#CD3700;">发货管理</font>】->【<font color="#CD3700;">配/运单打印</font>】您可以继续打印运单。'+'</font></div>';
    }else if(type=="2"){
      m_tip="补打";
      p_tip="";
    }
     var o_count=0;
      var order_id="";
      $("input:checkbox[name='goodsorder.order_id']").each(function(i){
			if(this.checked==true){
				order_id+=$(this).val()+",";
				o_count++;
			}	      
      });
      //id串
      order_id=deleteLastChar(order_id,",");
      if(o_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:15px;width:300px;color:#FE7E03;">'+'请选择一个订单'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
            art.dialog({
			title: '<font style="font-weight:bold;font-size:15px;">'+m_tip+'配货单</font>',
			content:'<div class="decorateaduit"><font color="black">'+'你确认要'+m_tip+'所选【<font size="5px;" color="#CD3700;">'+o_count+'</font>】条订单吗?'+p_tip,
			okValue: '确定',
			width: '400px',
			cancelValue: '取消',
		    ok: function () {
		         var checksgid = order_id.split(',');
					for(var i=0;i<checksgid.length;i++){
				     $.ajax({
				          type: "post",
				          url: "/goodsorder!getOrderPrint.action?order_id="+checksgid[i]+"&type=2&ptype="+type,
				          contentType:"application/x-www-form-urlencoded;charset=utf-8", 
				          datatype:"json",
				          async:false,
				          success: function(data){ 
					         if(data!=""){
					          CreateOneFormPages(data);
					          LODOP.PRINT();
					          jNotify("打印中...");
					         } 	
				          }	 
					 });  
				}
		        //重新加载页面
		        location.reload();
				return true;
		    },
		    cancel: function () {
				return true;
		    }
		 });
	}
} 

//删除订单回收站
function delInfo_order(field_name,actionName){
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
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '<font style="font-size:15px;">系统提示</font>',
			content:'<div class="decorateaduit"><font color="black">'+'确认将选中的订单永久删除?<br/><br/>删除之后的订单,将无法恢复,请谨慎操作！'+'</font></div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}
//弹出修改收货人信息
function modifyshouhuorenShowDIV(id,order_id,order_money,order_date,order_name,order_area,order_tel,is_return_page,idCard){
  var order_info="";
  var shouhuo_info="";
  order_info="<br/>订单编号："+order_id+" <br/><br/> 订单金额："+order_money+" 元  <br/><br/>  下单时间："+order_date+"<br/>";
  shouhuo_info="<br/>收件人："+order_name+"<br/><br/>身份证："+idCard+"<br/><br/>地&nbsp;&nbsp;址："+order_area+"<br/><br/>电&nbsp;&nbsp;话："+order_tel;
  $("#borderinfo_id").html(order_info);
  $("#bshouhuoren_id").html(shouhuo_info);
  $("#b_order_id").val(order_id);
  $("#consignee_id").val("");
  $("#identitycard_id").val("");
  $("#buy_address_id").val("");
  $("#mobile_id").val("");
  $("#"+id).popup_search({move_top:0, p_height:430, pop_title:"修改收货人信息"});
}
//确定修改收货地址
function modifyBuerAdress(){
 	jConfirm('您确定要修改？', '系统提示',function(r){ 
 		if(r){ 
			$("#ModifybuyerForm").submit();
			
		 }
    });  
}
//还原订单回收站
function reinfo_order(field_name,actionName){
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
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '<font style="font-size:15px;">系统提示</font>',
			content:'<div class="decorate">'+'确认将选中的订单还原?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}
//弹出取消订单
function cancelOrderShowDIV(id,order_id,order_money,order_date,order_name,order_area,order_tel,pay_name,pay_id,pay_time,order_state,pay_code,pay_trxid,is_return_page){
 
  var order_info="";
  var shouhuo_info="";
  var pay_info="";
  order_info="<br/>订单编号："+order_id+" <br/><br/> 订单金额："+order_money+" 元  <br/><br/>  下单时间："+order_date+"<br/>";
  shouhuo_info="<br/>收件人："+order_name+"<br/><br/>地&nbsp;&nbsp;址："+order_area+"<br/><br/>电&nbsp;&nbsp;话："+order_tel;
  pay_info="<br/>支付方式："+pay_name+"<br/><br/>支付金额："+order_money+" 元<br/><br/>支付时间："+pay_time+"<br/><br/>";
  $("#orderinfo_cancel_id").html(order_info);
  $("#shouhuoren_cancel_id").html(shouhuo_info);
  $("#payinfo_cancel_id").html(pay_info);
  $("#cg_order_id").val(order_id);
  $("#co_order_id").val(order_id);
  $("#cpay_code_id").val(pay_code);
  $("#corder_state_id").val(order_state);
  $("#cpay_trxid_id").val(pay_trxid);
  $("#ct_amount_id").val(order_money);
  $("#is_return_page").val(is_return_page);
  $("#is_sea").val($("#is_sea").val());
  
  if(pay_code=="wxpay"){
      $("#wxpay_id").show();
  }else{
     $("#wxpay_id").hide();
  }
  if(pay_code=="alipay"){
      $("#alipay_id").show();
  }else{
     $("#alipay_id").hide();
  }
  if(pay_code=="goldpay"){
      $("#goldpay_id").show();
  }else{
     $("#goldpay_id").hide();
  }
  if(pay_code=="alipaywap"){
      $("#alipaywap_id").show();
  }else{
     $("#alipaywap_id").hide();
  }
  if(pay_code=="unionpay"){
      $("#unionpay_id").show();
  }else{
     $("#unionpay_id").hide();
  }
  if(pay_code=="integral"){
      $("#integral_id").show();
  }else{
     $("#integral_id").hide();
  }
  $("#"+id).popup_search({move_top:0, p_height:350, pop_title:"取消订单并退款"});
  
  
}
function handleCancelOrderadmin(actionName){
    jConfirm('取消订单并退款？', '系统提示',function(r){ 
	    if(r){ 
           document.getElementById("tradecancelForm").action=actionName;
           var is_sea = $("#is_sea").val();
           var paycode =$("#cpay_code_id").val();
           if(paycode=="alipay" || paycode=="alipaywap"){//支付宝退款
              document.getElementById("tradecancelForm").target="_blank";
                var order_id=$("#co_order_id").val();
			   //运营商后台取消订单
			   adminCancleOrder(order_id);
           }
           closeSearchShowDIV('cancelorderinfo');//关闭当前div层
           if(paycode=="alipay" || paycode=="alipaywap"){
			   $("#setPay").popup_search({move_top:1,p_width:300,p_height:150,pop_title:"取消订单并退款"});
			   $(".img_td").css("display","none");
		   }
		   document.getElementById("tradecancelForm").submit();
		   
		}
    });  
}
function adminrefindex(){
 location.reload();
}
function adminCancleOrder(order_id){
   $.ajax({
         type: "post",
	     url: "/goodsorder!adminAjaxCancelOrder.action?order_id="+order_id,
         datatype:"json",
         async:false,
         success: function(data){ 
	     }
	 });  
}

//使用支付宝支付，退款中处理退款
function alipayRefund(id,order_id,order_money,order_date,order_name,order_area,order_tel,pay_name,pay_id,pay_time,order_state,pay_code,pay_trxid,is_return_page){
      
    $("#cg_order_id").val(order_id);
    $("#co_order_id").val(order_id);
    $("#cpay_code_id").val(pay_code);
    $("#corder_state_id").val(order_state);
    $("#cpay_trxid_id").val(pay_trxid);
    $("#ct_amount_id").val(order_money);
    $("#is_return_page").val(is_return_page);
    document.getElementById("tradecancelForm").action="/mall-alipay-refund.html";
	document.getElementById("tradecancelForm").target="_blank";
	$("#setPay").popup_search({move_top:1,p_width:300,p_height:150,pop_title:"在线退款提示"});
	$(".img_td").css("display","none");
	document.getElementById("tradecancelForm").submit();

}

function newOrder(new_order){
 	$("#new_order").val(new_order);
 	$("#indexForm").submit();
 }
 function numOrder(){
 	if($("#new_order").val()=='num_asc'){
 	newOrder('num_desc');
 	}else{
 	newOrder('num_asc');
 	}
 }
 function nameOrder(){
 	if($("#new_order").val()=='buy_custname_asc'){
 	newOrder('buy_custname_desc');
 	}else{
 	newOrder('buy_custname_asc');
 	}
 }
 function tcountOrder(){
 	if($("#new_order").val()=='tatal_amount_asc'){
 	newOrder('tatal_amount_desc');
 	}else{
 	newOrder('tatal_amount_asc');
 	}
 }
 
 //修改通关状态
function clearanceShowDIV(id,order_id,order_money,order_date,order_name,order_area,order_tel){
  var order_info="";
  var shouhuo_info="";
  order_info="<br/>订单编号："+order_id+" <br/> 订单金额："+order_money+" 元  <br/><br/>  下单时间："+order_date+"<br/>";
  shouhuo_info="<br/>收件人："+order_name+"<br/><br/>地&nbsp;&nbsp;址："+order_area+"<br/><br/>电&nbsp;&nbsp;话："+order_tel;
  $("#tgorderinfo_id").html(order_info);
  $("#tgshouhuoren_id").html(shouhuo_info);
  $("#clearance_oid").val(order_id);
  $("#clearance").popup_search({move_top:0, p_height:300, pop_title:"确认通关"});
}

//弹出批量通关操作层
function batchclearanceShowDIV(id){
    var o_count=0;
    var order_id="";
     $("input:checkbox[name='goodsorder.order_id']").each(function(i){
		if(this.checked==true){
			order_id+=$(this).val()+",";
			o_count++;
		}	      
     });
     //id串
     order_id=deleteLastChar(order_id,",");
     if(o_count==0){
		  art.dialog({
			title: '系统提示',
			content:'<div style="font-size:15px;width:300px;color:#FE7E03;">'+'请选择一条订单'+'</div>',
			cancelValue: '确定',
			width: '300px',
		    cancel: function () {
				return true;
		    }
		 });
	  }else{
	    $("#order_id_str").val(order_id);
	    $("#selectnumber").val(o_count);
	    $("#batch_clearance_id").html("您已选中【<font size='5px;'>"+o_count+"</font>】条订单");
  		$("#"+id).popup_search({move_top:0, p_height:200, pop_title:"订单通关已选中"+o_count+"条订单需要通关操作"});
	  }
}

 //批量通关订单
function batchclearanceshow(){

     var o_count="", order_id="",tgrid="";
      o_count=$("#selectnumber").val();
      order_id=$("#order_id_str").val();
       tgrid=$('input:radio[name=oidis_clearance1]:checked').val();
         if(tgrid==""){
             alert("请选中一个!");
             return false;
         }
     jConfirm('确认提交？', '系统提示',function(r){ 
	    if(r){ 
	           $.ajax({
		          type: "post",
				  url: "/goodsorder!batchClearance.action?order_id="+order_id+"&tgrid="+tgrid,
		          datatype:"json",
		          async:false,
		          success: function(data){ 
			          	if(data=="0"){
			          	  jNotify("订单通关成功!");
			          	}
				      }
				 });  
		        //重新加载页面
		        location.reload();
				return true;
		}
    });  
    
  }


 function doPrint() { 
	bdhtml=window.document.body.innerHTML; 
	sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符
	eprnstr="<!--endprint-->"; //结束打印标识字符串
	prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容
	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.SET_PRINT_STYLE("fontsize",18);
	LODOP.SET_PRINT_STYLE("Bold",1);
	LODOP. SET_PRINT_PAGESIZE (1, 0, 0,"A4");
	LODOP.ADD_PRINT_HTM(30,0,0,1000,prnhtml);
	  LODOP.PRINT();
	  jNotify("打印中...");
}
//导出订单
function exprotExcel(){
	if(window.confirm("确定要导出订单吗?")) {
		document.forms["form_search_id"].action='/admin_Goodsorder_export.action';
		document.forms["form_search_id"].submit();
	}
}
function batchSend(field_name,actionName){
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
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		document.getElementById('commonForm').action=actionName;
    	document.getElementById('commonText').name = "chb_id";
    	document.getElementById('commonText').value = filedVal;
    	document.getElementById('commonForm').submit();	  
	}
}
function submitCustoms(caller){
	var type = $("#customs_id").val();
	var order_id= $("#submit_order_id").val();
	
	closeSearchShowDIV('customs');//关闭窗口
	jQuery('body').showLoading();//Loading
	
	if(type=="001"){
		submitNone(order_id,caller);
	}else if(type=="002"){
		submitdirectkjt(order_id,caller)
	}else if(type=="003"){
		alert("杭州海关(功能开发中)");
		jQuery('body').hideLoading();//隐藏Loading画面
	}
}
//提交久通宏达（像酒类等线下交易）-直邮
function submitNone(orderid,type){
	//提交跨境通返回状态
 	var reminder ="";
 	$.ajax({
         type: "post",    
         url: "/admin_Offline_start.action?orderid="+orderid,
         datatype:"json",
         async:false,
         success: function(data){ 
	         if(data=='success'){
	         	jQuery('body').hideLoading();//隐藏Loading画面
	            art.dialog({
					title: '系统提示',
					content:'<div class="decorate">订单处理成功!</div>',
					okValue: '确定',
					width: '238px',
				    ok: function () {
				       if(type=='1'){
					   		window.location.href="/admin_Goodsorder_submitKJTList.action";
					   }else{
					   		window.location.href="/admin_Goodsorder_submitKJTFailList.action";
					   }
				    }
                });
	         }else{
	            alert("订单处理失败！");
             }
         }	 
	});  
}
function export_order(type){
	var remark_action = $("#indexForm").attr("action");
	if(type=="prepare_goods"){
		//导出备货清单
		$("#indexForm").attr("action","/admin_Goodsorder_prepareGoodsExport.action");
		$("#indexForm").submit();
	}else if(type="deliver_goods"){
		//导出发货清单
		$("#indexForm").attr("action","/admin_Goodsorder_deliverGoodsExport.action");
		$("#indexForm").submit();
	}
	$("#indexForm").attr("action",remark_action);
	
}
