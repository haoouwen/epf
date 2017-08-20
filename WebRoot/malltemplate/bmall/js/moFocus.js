/*
 *一行多图轮播
 *bruinHu  2014/6/16
*/
function moFocus(focusId,numbe,nClass,selClass){
		
	var index = 0,
	    aLen = 0,
		picTimer,
		btn,
		$focusmspan,
		ful;
		
	var $focusmId = $("#"+focusId),
	    $aElement  = $("#"+focusId+" "+"span"),
		len = Math.ceil($aElement.length/numbe);
		
	ful = "<ul>"	
	for(var i=0;i<len;i++){		
		ful += "<li>";
		for(var t=0;t<numbe;t++){
			if(aLen == $aElement.length){
				break;
			}else{
				ful += $aElement.eq(aLen).html();
				aLen++;	
			}
		}
		ful +="</li>";
	}
	ful +="</ul>";
	$focusmId.append(ful);
	
	var $focusmul = $("#"+focusId+" "+"ul"),
	    sWidth =$focusmul.width();//获取ul宽度	
			
	btn = "<div class='btn'>";
	for(var j=0; j < len; j++) {
		btn += "<span></span>";
	}
	btn +="</div>"
	$focusmId.append(btn);
	
	$focusmspan = $("#"+focusId+" ."+"btn"+" "+"span");
	
	$focusmspan.mouseenter(function() {
		index = $focusmspan.index(this);
		showPics(index);
	}).eq(0).trigger("mouseenter");
		
	//显示图片函数
	function showPics(index) {
		$focusmul.css("width",sWidth * (len));
		var nowLeft = -index*sWidth; 
		$focusmul.stop(true,false).animate({"left":nowLeft},300); 
		$focusmspan.removeClass(selClass);
		$focusmspan.addClass(nClass);
		$focusmspan.eq(index).addClass(selClass);
	}
		
}