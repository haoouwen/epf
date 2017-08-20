$(document).ready(function(e) {
	//筛选
	if($("div").hasClass("filterDiv")){
			
			var spdHeight = document.documentElement.clientHeight;
			$(".filterDiv").css({"height":spdHeight+"px"});
			//选择
			$(".filsDiv .pfil a").click(function(){
				if($(this).hasClass("sela")){
					$(this).removeClass("sela");
				}else{
					$(this).addClass("sela");
					$(this).siblings().removeClass("sela");
				}
			})
			//
			$(".sxDiv span").click(function(){
				$(".filterDiv").show();
				$(".filClose").show();
				$(".filterDiv").animate({"right":"0px"},350);
				$(".filClose").animate({"right":"250px"},350);
			})
			$(".filClose").click(function(){
				$(".filterDiv").animate({"right":"-250px"},350);
				$(".filClose").animate({"right":"-250px"},350);
				
			})
			
	 }
});