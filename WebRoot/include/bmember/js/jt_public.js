$(document).ready(function() {
   
	if($("div").hasClass("rtCont")){
	    //合作登录
		$(".cLoginSpan").hover(function(){
			$(this).addClass("addCLS");
			$(this).find(".clsDiv").slideDown("fast");
			},function(){
			$(this).removeClass("addCLS");
			$(this).find(".clsDiv").hide();
		})
		 //手机版本
		 $(".phoneSpan").hover(function(){
			$(this).addClass("addPhone");
			$(this).find(".phoneDiv").slideDown("fast");
			},function(){
			$(this).removeClass("addPhone");
			$(this).find(".phoneDiv").hide();
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
	//顶部在线客服
	if($("p").hasClass("topChat")){
		$(".topChat").click(function(){
		$("#serverId").popup({pWidth:"300",pHeight:"300",pTitle:"客户服务"});
		})
	}
	
});