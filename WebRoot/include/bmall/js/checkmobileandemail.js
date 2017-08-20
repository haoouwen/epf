
//éªè¯ææºéªè¯ç çæ­£ç¡®æ§
function cpIsNull(){
	$("#cpError").html("");
	$("#cpError").removeClass("error");
	var tips=true;
	var phone=$("#phone").val();
	var cp_check=$("#cp_check").val();
	if(cp_check==""){
		$("#cpError").html("ç­ä¿¡éªè¯ç ä¸è½ä¸ºç©º!");
		$("#cpError").addClass("error");
		return false;
	}
	if(phone==""){
		return false;
	}
	var cp_check = $("#cp_check").val();
	$.ajax({
		type:"post", 
		url:"/msgcheck!checkPhoneCode.action?phone=" + phone+"&cp_check="+cp_check, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if(data==1){
					tips=true;
					$("#cpc").val("è·åéªè¯ç !");
					$("#cpError").addClass("success_form");
				}else{
					tips=false;
					$("#cpError").html("éªè¯ç å·²è¿æ¶ï¼è¯·éæ°è·å!");
				    $("#cpError").addClass("error");
				}
		  	}
		});
	return tips;
}


//éªè¯ææºå·ç æ­£ç¡®æ§ä¸å¯ä¸æ§
function CellphoneIsNull(){
   $("#phoneError").html("");
   $("#phoneError").removeClass();
   var reg_phone = /^0{0,1}(1[0-9]{10})$/;//ææºæ ¼å¼æ­£åè¡¨è¾¾å¼
   var value = $("#phone").val();
   if(value!='' && !reg_phone.test(value)){
	    $("#phoneError").html("ææºå·ç æ ¼å¼ä¸æ­£ç¡®!");
	    $("#phoneError").addClass("error");
		return false;
   }else if(reg_phone.test(value)){
	    var fp=true;
	    if(!onlyphone(value)){
		    $("#phoneError").html("è¯¥ææºå·²ç»æ³¨å!");
		    $("#phoneError").addClass("error");
		    fp=false;
	    }else{
	    	$("#phoneError").html("");
	    	$("#phoneError").addClass("success_form");
	    	fp=true;
	    }
	    if(fp==true){
	       if($("#timeMinus").val()>0){
				$("#cpc").attr('disabled',true);
			}else{
				$("#cpc").attr('disabled',false);
			}
	    }
	    return fp;
   }else{
	    $("#phoneError").html("ææºå·ç æ ¼å¼ä¸æ­£ç¡®!");
	    $("#phoneError").addClass("error");
		return false;
	}
	
	//å¤æ­å®æ¶å¨
	if($("#timeMinus").val()>0){
		$("#cpc").attr('disabled',true);
	}else{
		$("#cpc").attr('disabled',false);
	}
}

//éªè¯ææºçå¯ä¸æ§
function onlyphone(p_val){
 	var tips=true;
	$.ajax({
		type:"post", 
		url:"/mall/member!checkPhone.action?phone=" + p_val, 
		datatype:"json",
		async:false, 
		success:function (data) {
				if (data == "1") { 
					tips=false;
				}else{
				   $("#phoneError").html("");
				   tips=true;
				}
		  	}
		});
	return tips;
}


//éªè¯ææºå·éè¯¯æç¤º
function CellphoneForm(){
    if($("#phone").val() == ""){
        $("#phoneError").html("");
		$("#phoneError").removeClass();
	    $("#phoneError").html("ææºå·ç æ ¼å¼ä»¥13/14/15/18ç­å¼å¤´!");
	    $("#phoneError").addClass("tipform");
	    return false;
	}
}



//åééªè¯ç 
function sendcode(){
	var cphone = $("#phone").val();
	var cp_type = $("#cp_type").val();
	if(cphone==""){
		$("#phoneError").html("è¯·å¡«åææºå·ç !");
	}else if(!CellphoneIsNull()){
		alert("not null");
	}else{
		$.ajax({
		type:"post", 
		url:"/mall/member!sendPhoneCode.action?phone=" + cphone+"&c_type="+cp_type, 
		datatype:"json",
		async:false, 
		success:function (data) {
				alert("åéæå,è¯·æ¥å°ä¿¡æ¯åè¾å¥éªè¯ç !");
				$("#cpc").attr('disabled',true);
				$("#timeMinus").val("60");
				window.setInterval("run();", 1000);
		  	}
		});
	}
}


//å®æ¶åééªè¯ç 
function run(){
	var s = $("#timeMinus").val();
	var is=s*1;
	if(is>=1){
		if(is == 1){
			$("#timeMinus").val(is - 1);
			$("#cpc").attr('disabled',false);
			$("#cpc").val("è·åéªè¯ç ");
		}else{
			$("#timeMinus").val(is - 1);
			$("#cpc").val("è¿æ"+(is-1)+"ç§");
		}
	}
}



//å¤±å»ç¦ç¹éªè¯çµå­é®ä»¶
function EmailIsNull(){
   $("#emailError").html("");
   $("#emailError").removeClass("tipform");
   var reg_email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//çµå­é®ç®±æ­£åè¡¨è¾¾å¼
   if ($.trim($("#email").val()) == "") {
		$("#emailError").html("çµå­é®ç®±ä¸è½ä¸ºç©º");
		$("#emailError").addClass("error");
		return false;
	}else if(!reg_email.test($("#email").val())){
	    $("#emailError").html("çµå­é®ç®±æ ¼å¼ä¸æ­£ç¡®!");
	    $("#emailError").addClass("error");
		return false;
	}//else if(!checkEmail()){
	    //$("#emailError").addClass("error");
	   // return false;
	//}
	else{
	    $("#emailError").html("");
	    $("#emailError").addClass("success_form");
	    return true;
	}
}
//è·åç¦ç¹éªè¯é®ç®±
function EmailForm(value){
   $("#emailError").html("");
   $("#emailError").removeClass("error");
   if($.trim($("#email").val()) == ""){
     $("#emailError").html("è¯·è¾å¥å¸¸ç¨ççµå­é®ç®±,ä¾¿äºæ¥åæ¾åå¯ç !");
     $("#emailError").addClass("tipform");
   }
}

//éªè¯ææºåç­ä¿¡éªè¯ç 
function checkMsgForm(){
   var flag = true;
   if(!CellphoneIsNull()){
      flag = false;
   }
   if(!cpIsNull()){
      flag = false;
   }
   if(flag){
   		document.forms[0].action = "";
	 	document.forms[0].submit();
   }else{
   		return false;
   }
}


//æ¸ç©ºéè¯¯ææ¬åå®¹
function ClearErrorInfo() {
    $("#user_nameError").html("");
	$("#passwdError").html("");
	$("#confirm_pwdError").html("");
	$("#emailError").html("");
	$("#randsError").html("");
}

//éªè¯ç åè½
function changeValidateCode(obj) {   
        //è·åå½åçæ¶é´ä½ä¸ºåæ°ï¼æ å·ä½æä¹   
	var timenow = new Date().getTime();   
        //æ¯æ¬¡è¯·æ±éè¦ä¸ä¸ªä¸åçåæ°ï¼å¦åå¯è½ä¼è¿ååæ ·çéªè¯ç 
        //è¿åæµè§å¨çç¼å­æºå¶æå³ç³»ï¼ä¹å¯ä»¥æé¡µé¢è®¾ç½®ä¸ºä¸ç¼å­ï¼è¿æ ·å°±ä¸ç¨è¿ä¸ªåæ°äºã   
	obj.src = "imgrand.action?d=" + timenow;
}


	
//å¼æ­¥éªè¯é®ç®±æ¯å¦å·²è¢«æ³¨åè¿
function checkEmail(){
      var tips = true;
      var  email = $("#email").val();
      $.ajax({
        type:"post",
        url:"/mall/member!checkEmail.action?email="+email,
        datatype:"json",
        async:false,
        success:function(data){
            if(data == "1"){
               $("#emailError").html("é®ç®±å·²è¢«å ç¨ï¼è¯·éæ°è¾å¥!");
               tips = false;
            }else{
               $("#emailError").html("");
			   tips = true;
            }
        }
      });
      return tips;
 }

 