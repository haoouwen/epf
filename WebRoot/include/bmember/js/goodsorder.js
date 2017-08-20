//买家付款
function buyPayed(order_id,os){
   jConfirm('您确定要付款？', '系统提示',function(r){ 
	    if(r){ 
			 setstate_value(order_id,os,"/bmall_Goodsorder_buyPayed.action");
		 } 
    });  
}
//买家取消订单
function buyCancelOrder(){
   jConfirm('您确定关闭订单？', '系统提示',function(r){ 
	    if(r){ 
			 $("#tradeForm").submit();
		 } 
    });  
}
//买家删除订单
function buyDelOrder(id){
   jConfirm('删除后可以在回收站找回。', '您确定删除订单？',function(r){ 
	    if(r){ 
			linkToInfo('/bmall_Goodsorder_delOrder.action','goodsorder.order_id='+id);
		 } 
    });  
}
//买家删除订单
function buyReOrder(id){
   jConfirm('您确定还原订单？', '系统提示',function(r){ 
	    if(r){ 
			linkToInfo('/bmall_Goodsorder_reOrder.action','goodsorder.order_id='+id);
		 } 
    });  
}

//买家确认收货
function buyConfirmReceipt(order_id,os){
   jConfirm('您确认收货？', '系统提示',function(r){ 
	    if(r){ 
			 setstate_value(order_id,os,"/bmall_Goodsorder_buyConfirmReceipt.action");
		 } 
    });  
}

//买家评价
function buyEvaluation(order_id,os){
   jConfirm('您确定要评价？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/bmall_Goodsorder_buyEvaluation.action");
		}
    });  
}

//买家申请退款
function buyRefund(order_id,os){
   jConfirm('您确定要申请退款？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/bmall_Goodsorder_buyRefund.action");
		}
    });  
}

//卖家发货
function sellerDelivery(os){
    sellerSend_num();
 	jConfirm('您确定要发货？', '系统提示',function(r){ 
 		if(r){ 
 		     var order_id=$("#goods_order_id").val();
			 setstate_value(order_id,os,"/bmall_Goodsorder_sellerDelivery.action");
		 }
    });  
}
//卖家发货
function sellerSend_num(){
    var order_send_num_id=$("#order_send_num_id").val();
    if(order_send_num_id==""||order_send_num_id==null){
      jAlert("请输入运单号码!","系统提示");
      return ;
    }
    var reg = /^[A-Za-z0-9]+$/;
    if(!reg.test(order_send_num_id)){
    	jAlert("请输入正确的运单号码!","系统提示");
    	return ;
    }
}

//卖家同意退款方法
function sellerConfirmRefund(order_id,os){
	 jConfirm('您确定同意退款？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/bmall_Goodsorder_sellerConfirmRefund.action");
		}
    });  
}


//卖家拒绝退款方法
function sellerRefusedRefund(order_id,os){
	 jConfirm('您确定拒绝退款？', '系统提示',function(r){ 
	    if(r){ 
			setstate_value(order_id,os,"/bmall_Goodsorder_sellerRefusedRefund.action");
		}
    });  
}

//卖家取消订单
function sellerCancelOrder(){
	 jConfirm('您确定取消订单？', '系统提示',function(r){ 
	    if(r){ 
			$("#tradeForm").submit();
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
//卖家取消订单
function sellerVirtual(){
	 jConfirm('您确定消费订单？', '系统提示',function(r){ 
	    if(r){ 
			$("#tradeForm").submit();
		}
    });  
}


//运营商同意退款方法
function adminAccessReBuy(order_id,os){
	 jConfirm('您确定同意退款？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/admin_Goodsorder_accessReBuy.action").submit();
		}
    });  
}


//运营商拒绝退款方法
function adminRefuseReBuy(order_id,os){
	 jConfirm('您确定拒绝退款？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/admin_Goodsorder_refuseReBuy.action").submit();
		}
    });  
}





//卖家拒绝退货方法
function refuseReGoods(order_id,os){
	 jConfirm('您确定拒绝退货？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/bmall_Goodsorder_refuseReGoods.action").submit();
		}
    });  
};


//卖家同意退货方法
function accessReGoods(order_id,os){
	 jConfirm('您确定同意退货？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/bmall_Goodsorder_accessReGoods.action").submit();
		}
    });  
};





//买家取消订单
function buyCancelOrder(order_id,os){
    jConfirm('您确定要取消订单？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/bmall_Goodsorder_buyCancelOrder.action").submit();
		}
    });  
};

//卖家取消订单
function sellCancelOrder(order_id,os){
    jConfirm('您确定要取消订单？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/bmall_Goodsorder_sellCancelOrder.action").submit();
		}
    });  
};

//运营商取消订单
function adminCancelOrder(order_id,os){
    jConfirm('您确定要取消订单？', '系统提示',function(r){ 
	    if(r){ 
			$("#order_id").val(order_id);
			$("#order_state").val(os);
			$("#tradeForm").attr("action","/admin_Goodsorder_sellCancelOrder.action").submit();
		}
    });  
}


 //控制涨价或者折扣，修改订单总额
  function addprice(){
     var upprice=$("#id_addprice").val();
     var forprice=$("#id_total_amount").val();
     var totalprice=0;
     var checkprice=$('input:radio[name="updown_price"]:checked').val();
     if(checkprice=="0"){
       if(parseInt(forprice)>parseInt(upprice)){
        	totalprice= parseInt(forprice)-parseInt(upprice);
       }else  if(parseInt(forprice)<parseInt(upprice)){
       		alert("请输入正确的价格!");
       		totalprice=parseInt(forprice);
       		return false;
       }
     }else if(checkprice=="1"){
       totalprice= parseInt(upprice)+parseInt(forprice);
     }
     $("#id_total_amount").val(totalprice);
  }




//获取收货人信息的值
function gettext(){
	var value="";
	value+=$("#name").text();
	value+=":";
	value+=$("#nametext").val();
	value+=";";
	
	value+=$("#mobile").text();
	value+=":";
	value+=$("#mobiletext").val();
	value+=";";
	
	value+=$("#phone").text();
	value+=":";
	value+=$("#phonetext").val();
	value+=";";
	
	value+=$("#send").text();
	value+=":";
	value+=$("#sendtext").val();
	value+=";";
	
	value+=$("#post").text();
	value+=":";
	value+=$("#posttext").val();
	value+=";";
	
	value+=$("getaddress").text();
	value+=":";
	value+=$("getaddresstext").val();
	value+=";";
	return value;
}
//切换导航样式
function re_show(val,num,btnName,divName,btncss1,btncss2){
	for(var i=1;i<=num;i++)
	{
		if(val==i){
			//document.getElementById(divName+i).style.display = 'block';
			document.getElementById(btnName+i).className = btncss1;
		}else{
			//document.getElementById(divName+i).style.display = 'none';
			document.getElementById(btnName+i).className = btncss2;
		}
	}
}


	function showUpdateShip(oid,ship_free){
			$("#ship_oid").val(oid);
			$("#oldship_free").val(ship_free);
			$("#ship").popup({p_width:"230", p_height:"150", pop_title:"修改运费"});
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
				$("#invoice").invoice_ccover();
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
//确认收货弹出层
function orderconfirmreceipt(order_id,total_amont){
	var orderHtml='<div class="popOrderDiv">'+
  	 '<table width="100%" cellpadding="0" bordercolorlight="0">'+
     	'<tr><th colspan="2">订单信息<span>(请您收到货后，再确认收货)</span></th></tr>'+
        '<tr><td  class="potd">订单编号：</td><td  >'+order_id+'</td></tr>'+
        '<tr><td  class="potd">订单金额：</td><td  ><b class="mred">￥'+total_amont+'</b></td></tr>'+
        '<tr><td></td><td colspan="1"><input type="button" class="graybut" value="确定" onclick=\'linkToInfo("/bmall_Goodsorder_buyConfirmReceipt.action","goods_order_id='+order_id+'&parentMenuId=4637243721&selli=2442547481")\'>&nbsp;&nbsp;<input type="button" class="graybut" value="取消" id="shbutId"></td></tr>'+
     '</table>'+
    '</div>';
   $("#confirmOrderId").html(orderHtml);
	$("#confirmOrderId").popup({
			pOpacity:"0.7",
			ptBackground:"#f5f5f5",
			pcBackground:"#fff",
			pWidth:"500",
			pHeight:"200",
			pTitle:"确认收货",
			oprid:"shbutId"
	  });
}

//取消订单弹出层
function cancelOrder(order_id,total_amont,goods_id_str,list_img_str,goods_name_str){
	var str=goods_id_str;
	if(goods_id_str.indexOf("#")>-1){
	   goods_id_str=goods_id_str.split("#"); 
	}
	if(list_img_str.indexOf("#")>-1){
	   list_img_str=list_img_str.split("#"); 
	}
	if(goods_name_str.indexOf("#")>-1){
	   goods_name_str=goods_name_str.split("#"); 
	}
	
	var jsdata=$("#jsontotal_id").val();
	    jsdata=eval("(" +jsdata+")"); 
	var orderHtml='<div class="popOrderDiv">'+
  	 '<table width="100%" cellpadding="0" bordercolorlight="0">'+
     	'<tr><th colspan="4">订单信息</th></tr>'+
        '<tr><td width="16%" class="potd">订单编号：</td><td width="30%" >'+order_id+'</td><td class="potd" width="15%">订单金额：</td><td width="39%"><b class="mred">￥'+total_amont+'</b></td></tr>'+
        '<tr><td class="potd">订单商品：</td><td colspan="3">';
        if(str.indexOf("#")>-1){
          for(var i=0;i<goods_id_str.length;i++){
          orderHtml+='<a href="/mall-goodsdetail-'+goods_id_str[i]+'.html" title="'+goods_name_str[i]+'"><img src="'+list_img_str[i]+'" width="50" height="50"></a>';
        }
        }else{
        orderHtml+='<a href="/mall-goodsdetail-'+goods_id_str+'.html" title="'+goods_name_str+'"><img src="'+list_img_str+'" width="50" height="50"></a>';
        }
        orderHtml+='</td></tr>'+
        '<tr><td class="potd">取消原因：</td><td colspan="3"><select name="refund_buy_reason" id="reason">';
        for(var j=0;j<jsdata.length;j++){ 
         orderHtml+='<option value="'+jsdata[j].para_key+'">'+jsdata[j].para_key+'</option>';
        }
        orderHtml+='</select></td></tr>'+
        '<tr><td></td><td colspan="3"><input type="button" class="graybut" value="确定" onclick=\'submitCancel("'+order_id+'")\'>&nbsp;&nbsp;<input type="button" class="graybut" value="取消" id="ddbutId"></td></tr>'+
     '</table>'+
    '</div>';
   $("#cancelOrderId").html(orderHtml);
	$("#cancelOrderId").popup({
		pOpacity:"0.7",
		ptBackground:"#f5f5f5",
		pcBackground:"#fff",
		pWidth:"500",
		pHeight:"250",
		pTitle:"取消订单",
		oprid:"ddbutId"
  });
}

//取消订单处理
function submitCancel(order_id){
 $("#order_id").val(order_id);
 var reason=$("#reason").val();
 $("#refund_buy_reason").val(reason);
 $("#indexForm").attr("action","/bmall_Goodsorder_buyCancelOrder.action");
 $("#indexForm").submit();
}
