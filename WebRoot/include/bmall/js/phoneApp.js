$(document).ready(function(){
	if($("div").hasClass("twrTop")){
		$(".twrTi").hover(function(){
			$(this).find("div").show("fast");
		},function(){
			$(this).find("div").hide("fast");
		})
	}
})