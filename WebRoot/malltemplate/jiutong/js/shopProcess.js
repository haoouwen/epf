$(document).ready(function() {
	//结算div隐藏
	if($("div").hasClass("hasCart")){
		
		if($("div").hasClass("hcDiv")){
			$(".settlement").show();
			
		}else{
			$(".settlement").hide();
		}
	}
		
	/*支付,配送方式及发票信息*/
	if($("div").hasClass("way")){
		$(".way .fph3").toggle(function(){
			  $(this).addClass("fpdh3");
			  $(this).siblings(".fpDiv").show();
			},function(){
			  $(this).removeClass("fpdh3");
			  $(this).siblings(".fpDiv").hide();	
			}
	     )
		 
		 $(".way .instDiv span:eq(0)").addClass("selSpan")
		 $(".way .fpCont:eq(0)").show();
		 
		 $(".way .instDiv span:eq(0)").click(function(){
			 if($(this).hasClass("selSpan")){
				 return;
				}else{
					$(this).addClass("selSpan");
					$(this).siblings().removeClass("selSpan");
					$(".way .fpCont:eq(0)").show();
					$(".way .fpCont:eq(1)").hide();
					$(".way .fpCont:eq(2)").hide();
			  }
		  })
		  $(".way .instDiv span:eq(1)").click(function(){
			 if($(this).hasClass("selSpan")){
				 return;
				}else{
					$(this).addClass("selSpan");
					$(this).siblings().removeClass("selSpan");
					$(".way .fpCont:eq(0)").hide();
					$(".way .fpCont:eq(1)").show();
					$(".way .fpCont:eq(2)").hide()
			  }
		  })
		   $(".way .instDiv span:eq(2)").click(function(){
			 if($(this).hasClass("selSpan")){
				 return;
				}else{
					$(this).addClass("selSpan");
					$(this).siblings().removeClass("selSpan");
					$(".way .fpCont:eq(0)").hide();
					$(".way .fpCont:eq(1)").hide();
					$(".way .fpCont:eq(2)").show()
			  }
		  })
		  
	}
	
	/*使用积分*/
	if($("div").hasClass("hcDiv")){
		$(".hcDiv .jfspan").toggle(function(){
			$(this).parents(".hcDiv ").find(".jfp").show();
		},function(){
			$(this).parents(".hcDiv ").find(".jfp").hide();
		})
	}
	/*优惠劵*/
	if($("div").hasClass("youhui")){
		
		$(".yhDiv .fph3").toggle(function(){
			 $(this).addClass("fpdh3");
			 $(this).siblings(".fpDiv").show();
			},function(){
			 $(this).removeClass("fpdh3");
			 $(this).siblings(".fpDiv").hide();
		})
	}
	
})
