//退出
function exit(id){
 if(id=='addAtab'){
   $("#"+id).hide();
 }else{
   $("#up"+id).hide();
 }
}

//AJAX新增收货地址
function addAddr(){
	var consignee = $("#consignee").val();
	var identitycard = $("#identitycard").val();
	var address = $("#address").val();
	var post_code = $("#post_code").val();
	var area_code = $("#area_code").val();
	var phone = $("#phone").val();
	var code_phone = "";
	var cell_phone = $("#cell_phone").val();
	var area_attr = "";
	var regex_cs = /^[\u4e00-\u9fa5\w]+$/;//正则表达式：只能输入中文、英文、数字和_
	//验证收货人是否为空
	if(consignee == ""){
		jNotify("收货人不能为空!"); 
		return;
	}else if(!regex_cs.test(consignee)){
		jNotify("收货人不合法!"); 
		return;
	}else{
		var en_consignee = encodeURIComponent(encodeURIComponent(consignee));
	}
	if(identitycard==""){
	    jNotify("身份证不能为空!"); 
	    return;
	}
	if(!validateIdCard(identitycard)){
	 return;
	}
	var flag=false;
	//地区串拼接以及验证
	$("[class^=select areaDiv]").each(function(){
		if(this.value != "" && this.value !="0"){
			if(area_attr != ""){
				area_attr = area_attr +","+this.value;
			}else{
				area_attr = this.value;
			}
		}else{
          flag=true;
		}
	});
	//验证地区
	if(flag){
		jNotify("请选择地区!"); 
		return;
	}
	//验证街道地址是否为空
	if(address == ""){
		jNotify("详细地址不能为空!"); 
		return;
	}else{
		var en_address = encodeURIComponent(encodeURIComponent(address));
	}
	/*验证邮政编码是否为空
	if(post_code == ""){
		jNotify("邮政编码不能为空!"); 
		return;
	}
	校验邮编格式
	var p1 = /^[0-9]{6}$/;
	if(!p1.test(post_code)){
		jAlert("邮编格式有误!","系统提示");
	   return false;
	} 
	*/
	//验证电话和手机请至少填写一个
	if(area_code == "" && phone == "" && cell_phone ==""){
		jNotify("电话和手机请至少填写一个!"); 
		return;
	}
	//拼接固定电话
	if(area_code!=""&&phone!=""){
	  code_phone = area_code+"-"+phone; 
	}
	//校验固话格式
	var p2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if(code_phone !="" && code_phone !="-" && !p2.test(code_phone)){
	   jAlert("固定电话格式有误!","系统提示");
	   return false;
	} 
	//校验手机号码格式
	var p3 = /^1[3,4,5,7,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   jAlert("手机格式有误!","系统提示");
	   return false;
	} 
	
	var dataUrl="/mall/order!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&post_code="+post_code+"&phone="+code_phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr+"&identitycard="+identitycard;
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
        		$("#indexForm").submit();
        	}
        }
  	});
}

//AJAX删除收货地址
function delAddr(addr_id){
	jConfirm('确定要删除该地址？', '友情提示',function(r){
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

//获取地址
function getAddr(addr_id){
	 var dataUrl="/mall/order!getAddr.action?addr_id="+addr_id;
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        		jNotify("请先登录！");
        	}else{
        	 var jsonData=eval("("+data+")");
             var addrHtml="";  
             var area_code="";
             var phone="";
             var html="";
             if(jsonData[0].phone!=""){
                var area_phone=jsonData[0].phone.split("-");
                area_code=area_phone[0];
                phone=area_phone[1];
             }  
	        addrHtml+='<tr><th width="15%"><span>*</span>收货人：</th><td width="85%"><input type="text" class="aifText" id="consignees" name="consignee" value="'+jsonData[0].consignee+'"  maxLength="10"></td></tr>'+
	          '<tr><th width="15%"><span>*</span>身份证：</th><td width="85%"><input type="text" class="aifText" id="identitycards" name="identitycard" value="'+jsonData[0].identitycard+'"  maxLength="18"></td></tr>'+
	          '<tr><th><span>*</span>所在地区：</th><td><div id="viewDiv" style="margin-left:-5px;"></div></td></tr>'+
	          '<tr><th><span>*</span>详细地址：</th><td><input type="text" class="aisText" id="addresss"  maxLength="50" name="address" value="'+jsonData[0].address+'"  ></td></tr>'+
	          '<tr><th>邮政编码：</th><td><input type="text" class="aifText" id="post_codes"  name="post_code" onkeyup="checkDigital(this)" value="'+jsonData[0].post_code+'"  maxLength="6"></td></tr>'+
	          '<tr><th>固定电话：</th><td><input type="text" id="area_codes" name="area_code"  maxLength="4" onkeyup="checkNum(this)" class="qhtext" value="'+area_code+'"  >-<input type="text" class="aifText" id="phones" onkeyup="checkNum(this)" value="'+phone+'"  maxLength="8"><span>格式：区号 - 电话号码</span></td>'+
	          '<tr><th>手机号码：</th><td><input type="text" class="aifText" id="cell_phones" name="cell_phone" onkeyup="checkDigital(this)"  maxLength="11" value="'+jsonData[0].cell_phone+'"><span>电话和手机请至少填写一个</span></td></tr>'+
	          '<tr><th></th><td><input type="button" onclick="updateAddr('+jsonData[0].addr_id+')" class="submitbut" value="提交">&nbsp;&nbsp;<input type="button" class="exitbut" value="退出" onclick="exit('+jsonData[0].addr_id+')"></td></tr>';
	          $(".addAtab").hide();
	          $(".addAtab").html("");
	          html+='<tr><th width="15%"><span>*</span>收货人：</th><td width="85%"><input type="text" class="aifText" id="consignee" name="consignee" maxLength="20"></td></tr>'+
                    '<tr><th width="15%"><span>*</span>身份证：</th><td width="85%"><input type="text" class="aifText" id="identitycard" name="identitycard" maxLength="20"></td></tr>'+ 
                    '<tr><th><span>*</span>所在地区：</th><td><div id="areaDiv"></div></td></tr>'+
                    '<tr><th><span>*</span>详细地址：</th><td><input type="text" class="aisText" id="address"  name="address"></td></tr>'+
                    '<tr><th>邮编：</th><td><input type="text" class="aifText" id="post_code"  name="post_code" onkeyup="checkNum(this)"></td></tr>'+
                    '<tr><th><span>*</span>固定电话：</th><td><input type="text"  id="area_code" name="area_code"  maxLength="4" onkeyup="checkNum(this)" class="qhtext">-<input type="text" class="aifText" id="phone" onkeyup="checkNum(this)"><span>格式：区号 - 电话号码</span></td></tr>'+
                    '<tr><th><span>*</span>手机号码：</th><td><input type="text" class="aifText" id="cell_phone" name="cell_phone" onkeyup="checkNum(this)"><span>电话和手机请至少填写一个</span></td></tr>'+
                    '<tr><th></th><td><input type="button" class="submitbut" value="提交" onclick="addAddr();">&nbsp;&nbsp;<input type="button" class="exitbut" value="退出" onclick=\'exit("addAtab")\'></td></tr>';
	          $("#up"+addr_id).append(addrHtml);
	           $("#addAtab").append(html);
	          //所属地区的回选
	          var areaIdStr=$("#areaIdStr").val();
		      loadArea(""+areaIdStr+","+jsonData[0].area_attr+"","","viewDiv");
		      loadAreas(areaIdStr,"");
			  $("#up"+addr_id).show();
        	}
        }
  	});

}

//编辑地址
function updateAddr(addr_id){
    var consignee = $("#consignees").val();
    var identitycard =$("#identitycards").val();
	var address = $("#addresss").val();
	var post_code = $("#post_codes").val();
	var area_code = $("#area_codes").val();
	var phone = $("#phones").val();
	var code_phone = "";
	var cell_phone = $("#cell_phones").val();
	var area_attr = "";
	var regex_cs = /^[\u4e00-\u9fa5\w]+$/;//正则表达式：只能输入中文、英文、数字和_
	//验证收货人是否为空
	if(consignee == ""){
		jNotify("收货人不能为空!"); 
		return;
	}else if(!regex_cs.test(consignee)){
		jNotify("收货人不合法!"); 
		return;
	}else{
		var en_consignee = encodeURIComponent(encodeURIComponent(consignee));
	}
	if(identitycard==""){
	    jNotify("身份证不能为空!"); 
	    return;
	}
	if(!validateIdCard(identitycard)){
	 return;
	}
	var flag=false;
	//地区串拼接以及验证
	$("[class^=select viewDiv]").each(function(){
		if(this.value != "" && this.value !="0"){
			if(area_attr != ""){
				area_attr = area_attr +","+this.value;
			}else{
				area_attr = this.value;
			}
		}else{
          flag=true;
		}
	});
	//验证地区
	if(flag){
		jNotify("请选择地区!"); 
		return;
	}
	//验证街道地址是否为空
	if(address == ""){
		jNotify("详细地址不能为空!"); 
		return;
	}else{
		var en_address = encodeURIComponent(encodeURIComponent(address));
	}
	/*验证邮政编码是否为空
	if(post_code == ""){
		jNotify("邮政编码不能为空!"); 
		return;
	}
	
	校验邮编格式
	var p1 = /^[0-9]{6}$/;
	if(!p1.test(post_code)){
		jAlert("邮编格式有误!","系统提示");
	   return false;
	} 
	*/
	//验证电话和手机请至少填写一个
	if(area_code == "" && phone == "" && cell_phone ==""){
		jNotify("电话和手机请至少填写一个!"); 
		return;
	}
	//拼接固定电话
	if(area_code!=""&&phone!=""){
	  code_phone = area_code+"-"+phone; 
	}
	//校验固话格式
	var p2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if(code_phone !="" && code_phone !="-" && !p2.test(code_phone)){
	   jAlert("固定电话格式有误!","系统提示");
	   return false;
	} 
	//校验手机号码格式
	var p3 = /^1[3,4,5,7,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   jAlert("手机格式有误!","系统提示");
	   return false;
	}   
	
	var dataUrl="/mall/order!updateAddr.action?consignee="+en_consignee+"&address="+en_address+"&post_code="+post_code+"&phone="+code_phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr+"&addr_id="+addr_id+"&identitycard="+identitycard;
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        		jNotify("请先登录！");
             	return false;
        	}else{
        	  $("#indexForm").submit();
        	}
        }
  	});  

}

//调整在线支付页面
function onlineSubComPay(){
    var momey_num=$("#id_momey").val();
    var plat = $("#recharge_platform").val();
    if(momey_num!=null&&momey_num!=""){
	    $("#indexForm").attr("target","_blank");
	    if(plat == "alipay"){
	       	$("#indexForm").attr("action","/mall-alipay-send.html");
	    }else if(plat == "wxpay"){
	        $("#indexForm").attr("action","/mall-wxpay-send.html");
	    }else if(plat == "unionpay"){
	        $("#indexForm").attr("action","/mall-unionpay-send.html");
	    }	   
	   	$("#indexForm").submit();
    }else{
      $("#tipmomey").show();
    }
}

//调整在线支付页面
function cardSubComPay(){
    var id_num=$("#id_num").val();
    var id_num2=$("#id_num2").val();
    var id_num3=$("#id_num3").val();
    var id_num4=$("#id_num4").val();
    var plat = $("#recharge_platform").val();
    if(id_num!=null&&id_num!=""&&id_num2!=null&&id_num2!=""&&id_num3!=null&&id_num3!=""&&id_num4!=null&&id_num4!=""){
        var cardkey = id_num+"-"+id_num2+"-"+id_num3+"-"+id_num4;
        if(cardkey.length!=19){
         $("#tipcard").show();
         return;
        }
        
        //判断卡号是否过期或者已经充值过
     var dataUrl="/bmall_Fundrecharge_checkcardkey.action?cardkey="+cardkey;
     var flag="0";
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        		jNotify("充值卡不存在！");
             	return false;
        	}else if(data=='1'){
        	 jNotify("该充值卡已充值！");
             	return false;
        	}else if(data=='2'){
        	 //充值卡状态正常可以进行充值
        	   flag="1";
        	}else if(data=='3'){
        	 	 jNotify("充值卡过期！");
             	return false;
        	}else if(data=='4'){
        	 	 jNotify("系统异常！");
             	return false;
        	}
        }
  	});  
        //进行充值卡充值操作
        if(flag=='1'){
        $("#cardskey").val(cardkey);
	    $("#indexForm").attr("target","_blank");
	    $("#indexForm").attr("action","/bmall_Fundrecharge_recardkey.action");
	   	$("#indexForm").submit();
	   	}
    }else{
      $("#tipcard").show();
    }
}
/*
 * 身份证15位编码规则：dddddd yymmdd xx p
 * dddddd：6位地区编码
 * yymmdd: 出生年(两位年)月日，如：910215
 * xx: 顺序编码，系统产生，无法确定
 * p: 性别，奇数为男，偶数为女
 *
 * 身份证18位编码规则：dddddd yyyymmdd xxx y
 * dddddd：6位地区编码
 * yyyymmdd: 出生年(四位年)月日，如：19910215
 * xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
 * y: 校验码，该位数值可通过前17位计算获得
 *
 * 前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
 * 如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
 * i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
 */
function validateIdCard(idCard){
 //15位和18位身份证号码的正则表达式
 var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
 //如果通过该验证，说明身份证格式正确，但准确性还需计算
 if(regIdCard.test(idCard)){
  if(idCard.length==18){
   var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
   var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
   var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
   for(var i=0;i<17;i++){
    idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
   }
   var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
   var idCardLast=idCard.substring(17);//得到最后一位身份证号码

   //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
   if(idCardMod==2){
    if(idCardLast=="X"||idCardLast=="x"){
    return  true;
    }else{
     jNotify("身份证号码错误！");
     return  false;
    }
   }else{
    //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
    if(idCardLast==idCardY[idCardMod]){
      return  true;
    }else{
     jNotify("身份证号码错误！");
     return  false;
    }
   }
  } 
 }else{
  jNotify("身份证格式不正确!");
  return  false;
 }
}