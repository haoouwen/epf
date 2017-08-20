$(document).ready(function(){
	/*全宽轮播*/
	 aFocus("fullFocusId",3,"nSpan","selSpan",true);
	/*预售中*/
	if($("div").hasClass("openBookingList")){
		
		$(".ysul > li").hover(
			function(){
				$(this).addClass("addli");
				$(this).find(".ysz").hide();
				$(this).find(".ystime").css({"opacity": "0.90"});
				$(this).find(".ystime").show("fast");
			},
			function(){
				$(this).removeClass("addli");
				$(this).find(".ystime").hide("fast");
				$(this).find(".ysz").show();
			}
		)
		
		var $ysli = $("."+"ysul"+" "+"li");
		var len = $ysli.length;
		for(var i=0 ;i<len;i++){
			if((i+1)%4 == 0){
				$(".ysul > li:eq("+i+")").css({"margin-right": "0px"});
			}
		};
	}
})