/*
 *所以商品分类
 *bruinHu
*/
$(document).ready(function(){
	$(".claSpan").hover(function(){
			$(".allClass").show();
			$(this).addClass("addclaSpan");
	   },function(){
		   $(".allClass").hide();
			$(this).removeClass("addclaSpan");
		 }
	 )
});