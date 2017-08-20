 $(document).ready(function(){ 
   selectRadio('coupon.need_state','need');
   selectRadio('coupon.coupon_state','coupon');
 });

 
//选择优惠情况
function selectRadio(radio_name,div_name){
  $("input[name='"+radio_name+"']").each(function(){
		var isCheckd = $(this).attr("checked");
		if(isCheckd==true){
		   $("#"+div_name+$(this).val()).show();
		}else {
		   $("#"+div_name+$(this).val()).hide();  
		}
	});
}


//检验表单
function checkForm(){
    var flag=true;
    //验证条件输入
    $("input[name='coupon.need_state']").each(function(){
		var isCheckd = $(this).attr("checked");
		 if(isCheckd==true){
		    if($(this).val()=="1"){
		       if($("#relate_goods").val() == "") {
			        jNotify("请添加商品!");
			        flag=false;  
			        return;
		        }else if($("#need_money1").val() == "") {
			        jNotify("请填写金额!");
			        flag=false;  
			        return;		        
		        }else {
		           $("#need_money").val($("#need_money1").val()); 
		        } 
		   }else if($(this).val()=="2"){
			     if($("#need_money2").val() == "") {
			           jNotify("请填写金额!");
				        flag=false;  
				        return;		 
			     }else{
			       $("#need_money").val($("#need_money2").val());
			     }
		   }else if($(this).val()=="3") {
			      if($("#need_money3").val() == "") {
				           jNotify("请填写金额!");
					        flag=false;  
					        return;	
			      }else {
                     $("#need_money").val($("#need_money3").val());
                     $("#relate_goods").val("0");
			      }
		   } 
		}
	});
	
    //验证优惠情况
    $("input[name='coupon.coupon_state']").each(function(){
		var isCheckd = $(this).attr("checked");
		if(isCheckd==true){
		   if($(this).val()=="1") {
		      if($("#coupon_money1").val() != "") {
		        $("#coupon_money").val($("#coupon_money1").val());
		      }else {
		        jNotify("请填写优惠值!");
		        flag=false;  
		        return;
		      }
		   } else if($(this).val()=="2") {
		      if($("#coupon_money2").val() != "") {
		        $("#coupon_money").val($("#coupon_money2").val());
		      }else {
		        jNotify("请填写优惠值!"); 
		        flag=false; 
		        return;
		      }
		   
		   } else if($(this).val()=="3") {
		      if($("#coupon_money3").val() != "") {
		        $("#coupon_money").val($("#coupon_money3").val());
		      }else {
		        jNotify("请填写优惠值!"); 
		        flag=false; 
		        return;
		      }
		   } else if($(this).val()=="4") {
		      if($("#coupon_money4").val() != "") {
		        $("#coupon_money").val($("#coupon_money4").val());
		      }else {
		        jNotify("请填写优惠值!"); 
		        flag=false; 
		        return;
		      }		   
		   }
		}
	});	
    
    //提交表单
    if(flag) {
     $("#detailForm").submit();
    }
}

//弹出下载框
function showDownDiv(coupon_id){
//下载优惠券的ID
$("#coupon_id").val(coupon_id);
exportShowDIV('downDiv','300px','130px','下载优惠券')

}

//下载优惠券
function downCoupon(actionName){
  var down_nmu=$("#down_num").val();
  if(down_nmu==''){
	alert("请填写下载数量");
	return false ;
  }else{
   $("#down_nmu_s").val(down_nmu);
  }
  var coupon_id=$("#coupon_id").val();
  $.ajax({
		type:"post",
		url:"/coupon!maxcoupon.action?coupon_id="+coupon_id+"&down_max="+down_nmu,
		datatype:"json",
		async:false,
		success:function(data){
			if(data=="0"){
				alert("请重新填写下载数量");
				return false 
			}else{
			 if(window.confirm("确定要下载优惠券吗?")) {
				document.forms["indexForm"].action=actionName;
				document.forms["indexForm"].submit();
   				}
			}
		}
	});
  
  
  
  
   
   
   
   
   
   
   
   
   
   
   
   
   
}