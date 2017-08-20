$(document).ready(function(){
	
	$(".helpul li").each(function(){
		
		$(this).find("ul li:last").css({"border":"none"});
	})
	
	$(".helpul li h3").toggle(function(){
		
			$(this).siblings("ul").hide();
			$(this).addClass("addh3");
		},function(){
			
			$(this).siblings("ul").show();
			$(this).removeClass("addh3");
		}
	)
})