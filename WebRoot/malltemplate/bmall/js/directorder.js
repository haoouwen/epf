//初始化加载
$(document).ready(function(){
	//渲染
	addrHover();
	//计算运费
	getOrderShipPrice();
	initShip();
	//绑定配送方式select事件
	$('.ship_fee').change(function(){
		initShip();
	}) 
});
function initShip(){
	var total_ship_price = 0;
	$('.ship_fee').each(function(){
		var ship_price = $(this).children('option:selected').val();//selected的值
		total_ship_price = total_ship_price + eval(ship_price);
		var smode_id = $(this).children('option:selected').attr("id");//selected的值
		//更新店铺价格
		var shop_price = $(this).parent().parent().prev().find("#goods_amount_str").val();//店铺小计不含运费
		$(this).parent().parent().last("p").find(".orderprice").html(eval(shop_price)+eval(ship_price));
		//加上运费后的价格
		var goods_price = $("#goods_price").val();//商品总价
		var total_amount = eval(goods_price);//订单总价
		$("#total_amount").val(total_amount);//总价
		//运费
		$(this).parent().parent().next("p").find("#ship_price").val(ship_price);
		$(".cartprice").html("￥"+total_amount);
		//配送方式ID
		$(this).parent().parent().next("p").find("#smode_id_str").val(smode_id);
		
	}) 
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
	//验证收货人是否为空
	if(consignee == ""){
		jNotify("收货人姓名不能为空!"); 
		return;
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
		}
	});
	//验证街道地址是否为空
	if(address == ""){
		jNotify("街道地址不能为空!"); 
		return;
	}else{
		var en_address = encodeURIComponent(encodeURIComponent(address));
	}
	//验证邮政编码是否为空
	if(post_code == ""){
		jNotify("邮政编码不能为空!"); 
		return;
	}
	//校验邮编格式
	var p1 = /^[0-9]{6}$/;
	if(!p1.test(post_code)){
		jAlert("邮编格式有误!","系统提示");
	   return false;
	} 
	//拼接固定电话
	code_phone = area_code+"-"+phone; 
	//验证电话和手机请至少填写一个
	if(area_code == "" && phone == "" && cell_phone ==""){
		jNotify("电话和手机请至少填写一个!"); 
		return;
	}
	//校验固话格式
	var p2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if(code_phone !="" && code_phone !="-" && !p2.test(code_phone)){
	   jAlert("固定电话格式有误!","系统提示");
	   return false;
	} 
	//校验手机号码格式
	var p3 = /^1[3,4,5,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   jAlert("手机格式有误!","系统提示");
	   return false;
	} 
	
	var dataUrl="/mall/order!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&post_code="+post_code+"&phone="+code_phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr;
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
        		jNotify("添加成功！");
        		//去除选中样式	
        		$(".useAdd").each(function(){
        			$(this).removeClass("useAddclass");
        		})
        		$("#addressDiv").append(data);
        		//渲染
				addrHover();
				//隐藏地址输入框
				$(".addAddress").toggle("fast");
				//获取配送方式
				getOrderShipPrice();   	
				//重新计算价格
				initShip();
        	}
        }
  	});
}
//AJAX删除收货地址
function delAddr(obj){
	var addr_id = $(obj).parent().parent().attr("id");
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
		        	}else{
		        		jNotify("删除失败！");
		        	}
		        }
		  	});
		 }
    });  
}
//鼠标滑过地址触发事件
function addrHover(){
	$(".useAdd").hover(
	  function () {
	  	 var addr_id = $(this).children("input").val();
	     var position =$(this).css("position");
	     var width =$(this).css("width");
	     var height =$(this).css("height");
	     var left = parseInt(width);
	     var top = parseInt(height)-10;
	     $(this).append("<span class='remove'><img onclick='delAddr(this);' src='/include/common/images/delete.png' alt='删除' title='删除'/></span>");
	     $(this).find(".remove").css({
	     	"position":'absolute',
	     	"left":left,
	     	"top":top
	     });
	  },function () {
	     $(this).find(".remove").remove();
	  });
}
//选择收货地址
function chooseAddr(obj){
	//去除选中样式	
   	$(".useAdd").each(function(){
   		$(this).removeClass("useAddclass");
   	})
   	//当前地址添加选中样式
   	$(obj).addClass("useAddclass");
   	
   	//获取配送方式
	getOrderShipPrice();   	
	//重新计算价格
	initShip();
}
//提交实物订单
function submitOrder(){
   	//将选中的收货地址标识放入隐藏域
   	var addr_id = $(".useAddclass").attr("id");
   	if(addr_id == "" || addr_id == undefined){
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
          	}else{
          	  $("#subOrder").submit();
          	}
          }	 
	   });
}
//提交虚拟订单
function submitVirtualOrder(){
   	$("#subVirtualOrder").submit();
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
			if(detailcount!=null&&detailcount!="0"){
			   for(var i=0;i<detailcount;i++){
			     var g_feng=$('input:radio[name="radio_'+i+'"]:checked').val();
			     var g_goods_id=$("#goods_"+i).val();
			     var g_content=$("#evaluate_content"+i).val();
			     if(g_content==null||g_content==""){
			     	g_content="暂无";
			     }
			     str_goods_id+=g_goods_id+",";
			     str_goods_feng+=g_feng+",";
			     str_goods_content+=g_content+"##########";
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
			  $("#tradeForm").submit();
			}
		 } 
    });  
    

}

//确认收货提交执行的方法
function submitConfirmReceip(){
  var str_password=$("#password_id").val();
  if(str_password==""||str_password==null){
   jAlert("请输入支付密码!","系统提示");
  }else{
	  jConfirm('您确认收货？', '系统提示',function(r){ 
	    if(r){ 
	      $("#tradeForm").submit();
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
  jConfirm('您确认申请退款？', '系统提示',function(r){ 
    if(r){  
    		var issubmit=true;
    		var order_state="";
			order_state=$("#order_state").val();	
    		if($("#buy_refund_type").val()==null||$("#buy_refund_type").val()=="--请选择退款理由--"){
    			jAlert("请选择退款原因！","系统提示");
    			issubmit=false;
    		}
    		
    		if($("#buy_refund_reason").val()==null||$("#buy_refund_reason").val()==""){
    			jAlert("请填写退款说明！","系统提示");
    			issubmit=false;
    		}
    		
    		if(order_state=='3'){
    			if(is_get!=''){
    			if(is_get=='1'){
    				var need_refund=$("#need_refund").val();
    				var all_refund=$("#all_refund").val();
    				if(need_refund==null||need_refund==""){
    					jAlert("请填写需退款金额！","系统提示");
    					issubmit=false;
    				}
    				if(parseInt(need_refund)>parseInt(all_refund)){
    					jAlert("退款金额超过已付款金额！","系统提示");
    					issubmit=false;
    				}
    				//alert($('input[name="is_return"]:checked').val());
    				if($('input[name="is_return"]:checked').val()==null||$('input[name="is_return"]:checked').val()==""){
    					jAlert("请选择是否退货！","系统提示");
    					issubmit=false;
    				}
    				
    			}
    			
    		}
		        var uploadresult="";
				uploadresult=$("#uploadresult").val();
				if(uploadresult.indexOf(',')>-1){
					var uploadresults=uploadresult.split(',');
					if(uploadresults.length>=4){
						jAlert("图片最多不操作3张！","系统提示");
						issubmit=false;
					}
				}
			}
			
			
			if(issubmit){
				 var refundapp_id="";
				 refundapp_id=$("#refundapp_id").val();
				 if(refundapp_id!=""){
				 	 document.getElementById('tradeForm').action='/mall/order!orderBuyRefundmentUpdate.action';
				 }
				 $("#tradeForm").submit();
				 
				 
				 
			}
     
    }
  });  
}
//同意退款提交执行的方法
function submitRefundAgree(){
  jConfirm('您确认同意退款申请？', '系统提示',function(r){ 
    if(r){ 
      $("#tradeForm").attr("action","/mall/order!sellerAgreeIsneedReturn.action").submit();
    }
  });  
}
//卖家同意退款到买家账上的方法
function submitRefundMoney(){
 	var psw=$("#password_id").val();
	if(psw=null||psw==""){
    	jAlert("请输入支付密码！","系统提示");
    	return false;
    }
  jConfirm('您确认同意退款到买家账户上？', '系统提示',function(r){ 
    if(r){
      $("#tradeForm").attr("action","/mall/order!sellerAgreeRefundment.action").submit();
    }
  });  
}
//拒绝退款提交执行的方法
function submitRefundDisAgree(){
  jConfirm('您确认拒绝退款？', '系统提示',function(r){ 
    if(r){ 
      $("#tradeForm").attr("action","/mall/order!sellerDisAgreeRefundment.action").submit();
    }
  });  
}
//卖家同意退款后向买家提供退货地址提交执行的方法
function submitRefundAddr(){
	//将选中的收货地址标识放入隐藏域
   	var addr_id = $(".useAddclass").attr("id");
   	//alert(addr_id);
   	if(addr_id == "" || addr_id == undefined){
   		jNotify("请填写或选择收货地址！");
        return false;
   	}
   	$("#sell_addrid").val(addr_id);
	 document.getElementById('tradeForm').action='/mall/order!setRefundAddr.action';
	 $("#tradeForm").submit();
}
	
//计算运费（商品详细页）
function getOrderShipPrice(){
	//获取将要传递的URL参数
	var dataUrl;
   	var goods_id_str = "";//商品ID串
   	var order_num_str = "";//商品数量串
   	var end_area_attr = $(".useAddclass").find("input:hidden[name='end_area_attr']").val();//收货地址ID
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
   		//order_num_str = $(this).find("#order_num_str").val();
   		var optionStr = "<option id='0' value='0'>免运费</option>";
   		dataUrl="/mall/goods!getOrderShipPrice.action?goods_id_str="+goods_id_str+"&buy_num_str="+order_num_str+"&end_area_attr="+end_area_attr;
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
	        			option = option + "<option id='"+data[i].smode_id+"' value='"+data[i].ship_price+"'> "+data[i].ship_name+":"+data[i].ship_price+"元</option>";
	        		}else{
	        			if(data[i].ship_price==0){
	        				optionStr = "<option id='0' value='0'>免运费</option>";
	        			}else{
	        				option = "<option id='"+data[i].smode_id+"' value='"+data[i].ship_price+"'> "+data[i].ship_name+":"+data[i].ship_price+"元</option>";
	        			}
	        		}
				}
				if(option != ""){
					optionStr = option;
				}
	        }
	    });
	    $(this).children("p").find(".ship_fee").html(optionStr);
   	});

}












