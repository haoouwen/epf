	
	/*******************************************************************************************/
   /** 共用**/
	var seller_state_str;
	function getSellerState(seller_state){
	  seller_state_str = seller_state;
	  if(seller_state=="0"){
	     $(".rejectReason").css("display","none");
	     $("#agreeState").css("display","");
	     $("#disAgreeState").css("display","none");
	     $("#refund_seller_state").val(seller_state);
	  }
	  else if(seller_state=="1"){//拒接
	     $(".rejectReason").css("display","");
	     $("#agreeState").css("display","none");
	     $("#disAgreeState").css("display","");
	  }
	}
	
	function getExState(seller_state){
	  seller_state_str = seller_state;
	  if(seller_state=="1"||seller_state=="6"||seller_state=="a"){
	     $(".rejectReason").css("display","none");
	     $("#agreeState").css("display","");
	     $("#disAgreeState").css("display","none");
	  }
	  else if(seller_state=="2"||seller_state=="b"||seller_state=="9"){//拒接
	     $(".rejectReason").css("display","");
	     $("#agreeState").css("display","none");
	     $("#disAgreeState").css("display","");
	  }
	}
	
	function fgoOver(){
		getExState('9');
		$("#refund_state").val("9");
		 $("#newseller_reason").val( $("#seller_reason").val());
		goOver();
	}
	
	function goOver(){
	  if(seller_state_str=="1"){//同意
	  }else if(seller_state_str=="2"||seller_state_str=="b"||seller_state_str=="9"){//拒接
	     var seller_reason =  $("#seller_reason").val();
	     if(seller_reason=="" || seller_reason==null){
	        alert("请输入拒接理由");
	        return false;
	     }else{
	        $("#newseller_reason").val($("#seller_reason").val());
	        document.getElementById("modiy_form").action="/admin_Exchange_goOver.action";
			document.getElementById("modiy_form").submit();
	     }
	  } else if(seller_state_str=="a"){
	        document.getElementById("modiy_form").action="/admin_Exchange_goOver.action";
			document.getElementById("modiy_form").submit();
	  }else{
	     alert("请选择处理意见");
	     return false;
	  }
	}
	
	
	
	//拒接退款处理
	function handleRefundGoods(){
	  if(seller_state_str=="0"){//同意
           
	  }else if(seller_state_str=="1"){//拒接
	     var seller_reason =  $("#seller_reason").val();
	     if(seller_reason=="" || seller_reason==null){
	        alert("请输入拒接理由");
	        return false;
	     }else{
	        document.getElementById("modiy_form").action="/admin_Exchange_disAgreeRefundGoods.action";
			document.getElementById("modiy_form").submit();
	     }
	  }else{
	     alert("请选择处理意见");
	     return false;
	  }
	    
	}
	
	  var flag = true;
	   $(document).ready(function(){
	     var seller_cust_id = $("#seller_cust_id").val();
	     var payPassword =  $("#pay_password").val(); 
		
       });
	
	//换货处理
	function handleReturnGoods(){
	  if(seller_state_str=="0"){//同意
           document.getElementById("modiy_form").action="/admin_Exchange_agreeReturnGoods.action";
		   document.getElementById("modiy_form").submit();
	  }else if(seller_state_str=="1"){//拒接
	     var seller_reason =  $("#seller_reason").val();
	     if(seller_reason=="" || seller_reason==null){
	        alert("请输入拒接理由");
	        return false;
	     }else{
	        $("#newseller_reason").val(seller_reason);
	        document.getElementById("modiy_form").action="/admin_Exchange_disAgreeReturnGoods.action";
			document.getElementById("modiy_form").submit();
	     }
	  }else{
	     alert("请选择处理意见");
	     return false;
	  }
	    
	}
	//已选择同意退货未发送收货地址
	function agreeChoosedAddress(){
	    document.getElementById("modiy_form").action="/admin_Exchange_agreeReturnGoods.action";
        document.getElementById("modiy_form").submit();
	}
	function sellDelivery() {
	    document.getElementById("modiy_form").action="/admin_Exchange_sellDelivery.action";
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
			 document.getElementById('commonText').name = "exchange_id";
			 document.getElementById('commonText').value = refundapp_id;
			 document.getElementById('commonId').name = "addr_id";
			 document.getElementById('commonId').value = filedVal;
			 document.getElementById('commonForm').submit();	  
		}
	}
	
	
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

//AJAX新增收货地址
function addSellerAddr(){
	var consignee = $("#consignee").val();
	var address = $("#address").val();
	var post_code = $("#post_code").val();
	var phone = $("#phone").val();
	var cell_phone = $("#cell_phone").val();
	var addr_id=$("#addr_id").val();
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
	//校验固话格式
//	var p2 = (/\d{3}-\d{8}|\d{4}-\d{7}/);
//	if(phone !="" && !p2.test(phone)){
//	   jAlert("固定电话格式有误!","系统提示");
//	   return false;
//	} 
	//校验手机号码格式
	var p3 = /^1[3,4,5,7,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   jAlert("手机格式有误!","系统提示");
	   return false;
	} 
	
	var dataUrl="/mall/order!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&addr_id="+addr_id+"&phone="+phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr;
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

function auAddr(id){
	  $("#addr_id").val(id);
	  $(".addAddress").show();
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
	