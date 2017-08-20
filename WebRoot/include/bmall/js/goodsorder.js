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
