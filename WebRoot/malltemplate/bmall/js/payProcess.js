$(document).ready(function(){
	
   /*购物车中的table*/
   if($("div").hasClass("cart")){
	   $(".cartTab").each(function(){
		  $(this).find("tr:last td").css("border","none");
		  $(this).find("tr:odd").addClass("addtd");
		  $(this).find("tr").hover(function(){
			    $(this).addClass("seltd");
		     },function(){
				 $(this).removeClass("seltd");
			 }
		   )
	   })
	   $(".cartTab .slP").hover(function(){
		   $(this).find("i").show();
		  },function(){
			 $(this).find("i").hide();
		}); 
	   //随便带走 最近浏览过的
	   $("#collectId").setTab("hsh2","tabDiv","selli");
	      
	}
	//关税提示
	if($("div").hasClass("delDiv")){
		$(".delDiv .gup .close").click(function(){
			$(".delDiv .gup").find("i").hide();
	    })
	}
	//
    if($("div").hasClass("emptyCart")){
		//随便带走 最近浏览过的
	   $("#collectId").setTab("hsh2","tabDiv","selli");
	}
	/*确认收货地址*/
	if($("div").hasClass("address")){
		
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
	}
	/*发票信息*/
	if($("div").hasClass("invoice")){
		//
		$(".invoice .iModify").click(function(){
			 $("#invStyleId").popup({pWidth:"920",pHeight:"480",pTitle:"发票信息"});
			 $("#instDivId .instDiv li:gt(0)").removeClass("selli");
			 $("#instDivId .insfTabDiv:gt(0)").hide();
			 $("#instDivId").setTab("instDiv","tabDiv","selli");
			 
		})
	}
  
	/*优惠信息*/
	if($("div").hasClass("youhuiDiv")){
		$(".youhuiDiv li h3").toggle(function(){
			$(this).addClass("jjh3");
			$(this).siblings(".yhDiv").slideDown("fast");
			},function(){
			$(this).removeClass("jjh3");
			$(this).siblings(".yhDiv").slideUp("fast");
		})
	}
	/*确认订单*/
	if($("div").hasClass("order")){
		
		$(".cartTab").each(function(){
		  $(this).find("tr:last td").css("border","none");
		  $(this).find("tr:even").addClass("addtd");
	   })
	   
	    //使用积分支付
	   $(".textDiv .syjfP").toggle(function(){
		   $(this).siblings(".jfDiv").slideDown("fast");;
		   },function(){
			  $(this).siblings(".jfDiv").slideUp("fast");
	   })
	   
	}
	/*提交订单成功*/	
	 if($("div").hasClass("sucOrder")){
	   $("#selPayStyleId").setTab("payh2","tabDiv","selli");
	 }
	 //判断使用余额判断异常现在在余额付款的tab
	 if($("#payTypeFlag").val()=="1"){
	 	$(".payh2").each(function(){
		  $(this).find("ul li").attr("class","");
	    })
	    $("#usePayAcount").attr("class","selli");
	    $(".payCont").each(function(){
		  $(this).attr("style","display: none;");
	    })
	    $("#usepayAccountDiv").attr("style","display: block;");
	    if($("#pay_passwordtip").html()!=""){
	       //替换账户支付信息错误的提示样式
	       $("#pay_passwordtip").find("span").attr("class","errTip");
	    }
	    
	 }
});