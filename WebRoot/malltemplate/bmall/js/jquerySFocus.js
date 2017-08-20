; (function ($) {
	
	$.fn.sFocus = function(itemDiv,butDiv,dotCurrent,time,fadeTime,num){
		
		var $this = $(this),$nav = $this.find('.'+butDiv),
			t = time || 3000,
			fadeTime = fadeTime || 500,
			i = 0,autoChange =0,timer=0;
			
		$('.'+itemDiv+':eq(0)').css('display', 'block');
		$('.'+butDiv+" "+"span"+':eq(0)').addClass(dotCurrent);
		
		 autoChange = function (){
			 $nav.find('span:eq(' + (i + 1 === num ? 0 : i + 1) + ')').addClass(dotCurrent).siblings().removeClass(dotCurrent);
			 $this.find('.'+itemDiv+':eq(' + i + ')').css('display', 'none').end().find('.'+itemDiv+':eq(' + (i + 1 === num ? 0 : i + 1) + ')').
			 css({display: 'block',opacity: 0}).animate({opacity: 1}, fadeTime, function (){i = i + 1 === num ? 0 : i + 1;}).siblings('.'+itemDiv).css({display: 'none',opacity: 0});
		  }
		   
		 timer = setInterval(autoChange, t);
		  
	     $this.hover(function () {
				 clearInterval(timer);
				 return false;
			  }, function () {
				  timer = setInterval(autoChange, t);
				  return false;
			  });
			  
		   $this.find('.'+butDiv+''+'>span').bind('click', function () {
				  var current = $nav.find(dotCurrent).index();
				  autoChange();
				  return false;
			  }).end().find('.'+butDiv+''+'>span').bind('click', function () {
				  i = $(this).index() - 1;
				  autoChange();
				  return false;
			});
				
		 return $this;

	}
}(jQuery));