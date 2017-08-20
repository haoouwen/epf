$(document).ready(function(){
    /*图片列表*/
	if($("div").hasClass("toList")){
		$(".toList ul li").hover(function(){
			$(this).addClass("addli");
		  },function(){
			  $(this).removeClass("addli");
			 }
		);
	}
	/*选择地区*/
	selectArea();
	/*试用详情*/
	if($("div").hasClass("tryTDiv")){
		if($(".tryTCont").children().is(".evacont")){
			$(".tryTDiv ul li:gt(0)").show();
			$("#tryDetailId").setTab("tryTDiv","tabDiv","selli");
		}else{
			$(".tryTDiv ul li:gt(0)").hide();
			$(".tabDiv .tryTCont:eq(0)").show();
		}
	}
	/*免费使用弹出的地址*/
	if($("div").hasClass("popupDiv")){
		
		var isCheckd = false;
		var radioNum = $("input[name='adrRadio']").length;
		
		$("input[name='adrRadio']").each(function(){
			isCheckd = $(this).attr("checked");
			if(isCheckd==true){
				$(this).parents("tr").addClass("selTr");
			}
		});
		
		$("input[name='adrRadio']").click(function(){
			isCheckd=$(this).attr("checked");
			if(isCheckd==true){
				$(this).parents("tr").addClass("selTr").siblings().removeClass("selTr");
			}
		})
		/*增加收货地址*/
		$(".addrsp span").click(function(){
			$(".addAddress").show();
		})
		$(".addAddress .backBut").click(function(){
			$(".addAddress").hide();
		})
	}
		//推荐商品
	if($("div").hasClass("recProduct")){
		aFocus("recProId",3,"nSpan","selSpan",false,4000);
	}
})
/**/
function selectArea(){
	$(".selArea i").click(function(){
		$(".selDiv").show();
	})
	$(".selDiv p span").click(function(){
		$(".selDiv").hide();
	})
}
 
/*弹出层*/
function showDiv(){
	//获取单选框状态
	var isCheckd=$("#agree").attr("checked");
	
	//判断是否同意试用条款
	if(!isCheckd){
	
	 jAlert("请同意试用条款！","系统提示");
	 return;
	
	}
	
	//获取当前浏览器地址
	var link_url = document.location.href;
    
    //通过ajax判断用户是否登录
    $.ajax({  	 
          type: "post",    	     
          url: "/mall/trygoods!isLogin.action",
          datatype:"json",
          success: function(data){
             
             //判断返回值 
             if(data=='1'){
               
                jAlert("您还没登陆，请先登陆","系统提示");
                document.location.href="/login.html?loc="+link_url;
             
             }else{
              
              //试用类型
              var try_type=$("#try_type").val();
              //试用商品数量
              var surplus=$("#surplus").val();
              //弹出层标题
              var p_title="付邮试用";
              
              //判断试用类型是否为免费试用
              if(try_type=="1"){
                 
                 //给p_title赋值
                 p_title="免费试用";
                 
              }
              
              //判断是为付邮试用且试用数量为0，否则弹出层
              if(try_type=="0"&&surplus=="0"){
              
                 //提示信息
                 jAlert("试用商品的数量为0，您不能申请试用","系统提示");
              
              }else{
              
               //弹出数据填充
              $("#popupContId").popup({pOpacity:"0.7",
							 ptBackground:"#f5f5f5",
							 pcBackground:"#fff",
							 pWidth:"700",
							 pHeight:"450",
							 pop_title:p_title
				 });
				 
			   }	 
             }
          }
      });  
	
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
		jNotify("收货人不能为空!"); 
		return;
	}else{
		var en_consignee = encodeURIComponent(encodeURIComponent(consignee));
	}
	var flag=false;
	//地区串拼接以及验证
	$("[name=area_attr]").each(function(){
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
	
	var dataUrl="/mall/trygoods!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&post_code="+post_code+"&phone="+code_phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr;
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
        		$("#addressDiv").append(data);
        		var isCheckd = false;
				$("input[name='adrRadio']").each(function(){
					isCheckd = $(this).attr("checked");
					if(isCheckd==true){
						$(this).parents("tr").addClass("selTr").siblings().removeClass("selTr");
					}
				});
				//隐藏地址输入框
				$(".addAddress").toggle("fast");
        	}
        }
  	});
}

//提交申请地址
function submitAddr(){
    var try_id=$("#try_id").val();
    var try_type=$("#try_type").val();
    var weiboid=$("#weiboid").val();
    var add_id="";
    var isCheckd = false;
	$("input[name='adrRadio']").each(function(){
		isCheckd = $(this).attr("checked");
		if(isCheckd==true){
			add_id=$(this).val();
		}
	});
	if(add_id==""){
	 jAlert("请填写收货地址！","系统提示");
	 return;
	}
	//试用类型
	var try_type=$("#try_type").val();
	if(try_type=="1"&&weiboid==""){
     jAlert("请填写新浪微博ID！","系统提示");
	 return;
	}
	//ajax返回的结果
	var result="";
	//ajax添加试用申请
	 $.ajax({  	 
          type: "post",    	     
          url: "/mall/trygoods!addTryApply.action?try_id="+try_id+"&add_id="+add_id+"&try_type="+try_type+'&weiboid='+weiboid,
          datatype:"json",
          async:false,
          success: function(data){
             result=data;
             if(data=='1'){
                if(try_type=="0"){
                 jAlert("试用申请成功,请尽快付邮！","系统提示");
                }else{
                 jAlert("试用申请成功！","系统提示");
                }
             }else if(data=='2'){
                jAlert("您已经申请过！","系统提示");
             }else{
                jAlert("商家不能申请自己的商品！","系统提示");
             }
          }
      });
      if(try_type=="0"&&result=="1"){
         document.location.href="/bmall_Goodsorder_postageList.action";
      }else{
         $(".popLayer").remove();
	 	 $(".tPopupDiv").remove();
		 $("html").css({"overflow-x":"hidden","overflow-y":"auto"});	
		 window.location.reload();//刷新页面
      }

}

