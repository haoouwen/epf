/*
 *选项卡切换
 *bruinHu
*/
;(function($){

	$.fn.setTab = function(liSdiv,tabSdiv,curLi) {
		 
		var tabSdiv = "."+tabSdiv,
			liSdiv = "."+liSdiv,
			tabLi =  $(this).children(liSdiv).find("li"),
			tabDiv = $(this).children(tabSdiv).children("div");
			
			tabLi.eq(0).addClass(curLi);
			tabDiv.eq(0).show();
			
			this.each(function(){
				
				tabLi.click(function() {
					
					var index = $.inArray(this, $(this).parent().find("li"));
					
					if (tabDiv.eq(index)[0]){
						
						tabLi.removeClass(curLi); 
						$(this).addClass(curLi);
						tabDiv.css("display", "none").eq(index).css("display", "block");
					}
			}); 
			  
		});
		 
	}
	
})(jQuery); 