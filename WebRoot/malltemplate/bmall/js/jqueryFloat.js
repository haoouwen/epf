/*
 *浮动层
 *bruinHu
*/
;(function($){
	
	$.fn.floatDiv=function(location){
		
		var isIE6=false;
		if($.browser.msie && $.browser.version=="6.0"){
			isIE6=true;
		}
		
		this.each(function(){
			
			var $fdiv = $(this);
			var loc;
			if(location==undefined || location.constructor == String){
				
				switch(location){
					//浮动向上 			
					case("floatTop"):
					
						var offfsetLeft = $fdiv.offset().left,
							offsetTop = $fdiv.offset().top,
							subTop = 0; 
															
						$(window).scroll(function(){
							
							subTop= document.documentElement.scrollTop || document.body.scrollTop;
							subTop = offsetTop - subTop;				
									
							if(subTop <= 0 ){
								subTop=0;
							}
							loc={left:offfsetLeft+"px",top:subTop+"px"};
							$fdiv.css({position:"fixed","z-index":"99"}).css(loc);
								
						});		
						break;
											
					default:
						//没做功能
						break;
				}
			}else{
				loc=location;
			}
			
		});
	}

})(jQuery); 