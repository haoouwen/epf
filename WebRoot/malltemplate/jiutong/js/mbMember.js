$(document).ready(function() {
	/*全部订单*/
	if($("div").hasClass("claOrder")){

	    $(".topSpan").click(function(){
			
			if($(".topSpan").hasClass("addSpan")){
				
				    $(this).removeClass("addSpan");
				    $(".claOrder").slideUp(50);	
				  
				}else{
					$(this).addClass("addSpan");
				    $(".claOrder").slideDown(50);	
				}
		 })
		 
		$(".claOrder a").click(function(){
			 $(".topSpan").removeClass("addSpan");
			$(".claOrder").hide();
		})
	}
})