 $(document).ready(function(){ 
   selectRadio('saleorder.term_state','term');
   selectRadio('saleorder.coupon_state','coupon');
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
    $("input[name='saleorder.term_state']").each(function(){
		var isCheckd = $(this).attr("checked");
		if(isCheckd==true){
		   if($(this).val()=="1") {
		      if($("#need_money1").val() != "") {
		        $("#need_money").val($("#need_money1").val());
		      }else {
		        jNotify("请填写条件!");
		        flag=false; 
		        return;
		      }
		   } else if($(this).val()=="2") {
		        $("#need_money").val("0");

		   } else if($(this).val()=="3") {
		      if($("#relate_goods").val() == "") {
		         jNotify("请选择商品!");
		         flag=false; 
		         return;
		      }else if($("#need_money2").val() != "") {
		        $("#need_money").val($("#need_money2").val());
		      }else {
		        jNotify("请填写条件!");
		        flag=false; 
		        return;
		      }
		   }else if($(this).val()=="4") {
              if($("#need_money3").val() != "") {
		        $("#need_money").val($("#need_money3").val());
		      }else {
		        jNotify("请填写条件!");
		        flag=false; 
		        return;
		      }
		   }
		}
	});
	
     //验证优惠情况
    $("input[name='saleorder.coupon_state']").each(function(){
		var isCheckd = $(this).attr("checked");
		if(isCheckd==true){
            if($(this).val()=="1") {
		      if($("#coupon_plan").val() == "") {
		        jNotify("请添加优惠券!");
		        flag=false;  
		        return;
		      }
		   } else if($(this).val()=="2") {
		      if($("#coupon_plan").val() == "") {
		        jNotify("请添加红包!");
		        flag=false;  
		        return;
		      }
		   }else if($(this).val()=="4") {
		      if($("#coupon_plan").val() == "") {
		        jNotify("请添加赠品!");
		        flag=false;  
		        return;
		      }
		   }else if($(this).val()=="5") {
		      if($("#coupon_money4").val() != "") {
		        $("#coupon_plan").val($("#coupon_money4").val());
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

//清除
function clearCoupon(){
  $("#relate_goods").val("");
  $("#selulrg").html("");
}

//隐藏优惠方案
function hideCoupon(){
  $("#couponDiv").hide();
}

//显示优惠方案
function showCoupon(){
$("#couponDiv").show(); 
}