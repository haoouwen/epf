$(document).ready(function() {
   	
   	//隐藏的头部一级，二级分类
	if($("div").hasClass("postion")){
		createClaMenu("claMain","claMeun","claItem",2);
	}
	if ($("div").hasClass("rtCont")) {
	    //合作登录
	    $(".cLoginSpan").hover(function () {
	        $(this).addClass("addCLS");
	        $(this).find(".clsDiv").stop(true,true).slideDown(150);
	    }, function () {
	        $(this).removeClass("addCLS");
	        $(this).find(".clsDiv").stop(true,true).slideUp(50);
	    })
	    //手机版本
	    $(".phoneSpan").hover(function () {
	        $(this).addClass("addPhone");
	        $(this).find(".phoneDiv").stop(true,true).slideDown(150);
	    }, function () {
	        $(this).removeClass("addPhone");
	        $(this).find(".phoneDiv").stop(true,true).slideUp(50);
	    })
	}
	/*头部购物车*/
	if($("div").hasClass("iCart")){
		
		$(".iCart").hover(function(){
			
			$(this).find(".ica").addClass("addIca");
			$(this).find(".icCont").show();
			var icTabHeight = $(".icTab").height();
			if(icTabHeight > 155){
				$(".icTab").css({"height":"150px","overflow":"auto"});
			}
			
			},function(){
				
			 $(this).find(".ica").removeClass("addIca");
			 $(this).find(".icCont").hide();
		})
		
	}
	//右侧显示
	if($("div").hasClass("leftSide")){
		
		$(".returnTop").hide();
		
		leftSide();
		
	    //购物车
		$(".leftSideDiv .lsCart").hover(function(){
			$(this).find(".cartSpan").addClass("addCartSpan");
		 },function(){
			$(this).find(".cartSpan").removeClass("addCartSpan");
		});
		$(".leftSideDiv .lsCart").toggle(function(){
			$(this).find(".cartSpan").addClass("selCartSpan");
			$(".leftSide").animate({"right":"0px"},200);
			},function(){
			$(this).find(".cartSpan").removeClass("selCartSpan");
			$(".leftSide").animate({"right":"-242px"},200);
		 })
		//领取优惠劵
		$(".leftSideDiv .lsCoupon").hover(function(){
			$(this).find("a").addClass("addlsCoupon");
			$(this).find("span").animate({"left":"-80px"},200);
			},function(){
			  $(this).find("a").removeClass("addlsCoupon");
			 $(this).find("span").animate({"left":"0px"},200);
		});
		 //领取红包
		$(".leftSideDiv .lsRedBag").hover(function(){
			$(this).find("a").addClass("addlsRedBag");
			$(this).find("span").animate({"left":"-80px"},200);
			},function(){
			  $(this).find("a").removeClass("addlsRedBag");
			 $(this).find("span").animate({"left":"0px"},200);
		});
		//客服
		$(".leftSideDiv .lsChat").hover(function(){
			$(this).find("a").addClass("addlsChat");
			$(this).find("span").animate({"left":"-80px"},200);
			},function(){
			  $(this).find("a").removeClass("addlsChat");
			 $(this).find("span").animate({"left":"0px"},200);
		});
		//返回顶部
		$(".leftSideDiv .lsReTop").hover(function(){
			$(this).find("a").addClass("addlsReTop");
			$(this).find("span").animate({"left":"-80px"},200);
			},function(){
			 $(this).find("a").removeClass("addlsReTop");
			 $(this).find("span").animate({"left":"0px"},200);
		});	
		
		$(window).resize(function(){
			leftSide();
		})
		//在线客服弹出
		if($("p").hasClass("lsChat")){
			$(".lsChat").click(function(){
			$("#serverId ").popup({pWidth:"300",pHeight:"300",pTitle:"客户服务"});
		    })
		}
	}
    //返回顶部
	$(".leftSideDiv .lsReTop").click(function(){
	    $("html,body").animate({scrollTop:0},350);
	 });
	if($("div").hasClass("returnTop")){
		
		$(".returnTop").hover(function(){
			   $(this).addClass("reAddTop");
			},function(){
			   $(this).removeClass("reAddTop");
		})
		
		$(".returnTop").click(function(){
		   $("html,body").animate({scrollTop:0},350);
		})
		
	   $(window).scroll(function(){
							
		scrollPos= document.documentElement.scrollTop || document.body.scrollTop;
		
		if(scrollPos > 300){
			$(".returnTop").show("fast");
		}else{
			$(".returnTop").hide("fast");
		}
		
	  })
	  
    }	
	//顶部在线客服
	if($("p").hasClass("topChat")){
		$(".topChat").click(function(){
		$("#serverId").popup({pWidth:"300",pHeight:"300",pTitle:"客户服务"});
		})
	}
	//隐藏分类导航
	if($("div").hasClass("headerListBot")){
		$(".headerListBot").hover(function(){
			$(".headerListBot .nav").show();
		},function(){
			$(".headerListBot .nav").hide();
		});
	}
	//获取未读消息数
	getMsg_num();
});

//最右侧条
function leftSide(){
	
	var cWidth = document.documentElement.clientWidth;
	var cHeight = document.documentElement.clientHeight;
		
	$(".leftSide").css({"height":cHeight+"px"});
	$(".leftSideDiv").css({"height":cHeight+"px"});
	$(".rightCart").css({"height":cHeight+"px"});
	$(".rCartTab").css({"height":(cHeight-83)+"px"});
	
	if(cWidth < 1300){
		 $(".leftSideDiv").css({"background":"none"});
		 $(".leftSideDiv .lssys").hide();
	}else{
		 $(".leftSideDiv").css({"background":"#383838"});
		 $(".leftSideDiv .lssys").show();
	}
	
}

//获取未读信件数量
function getMsg_num(){
	$.ajax({
          type: "post",
          url: "/mall/memberuser!getMsgNum.action",
          datatype:"json",
          async:false,
          success: function(data){ 
        	  $(".msg_num_span").html(data);
          }	 
	}); 
}
