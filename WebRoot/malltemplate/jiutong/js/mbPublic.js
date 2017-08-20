$(document).ready(function() {
	
	//搜索
	if($("div").hasClass("iSearTDiv")){
		
		$(".iSearTDiv").click(function(){
			
			$("html").css({"overflow":"hidden"});
		    var spdWidth = document.documentElement.clientWidth;
		    var spbHeight = document.body.clientHeight;
			var spdHeight = document.documentElement.clientHeight;
			
			if(spdHeight > spbHeight){
				
				$(".searPage").css({"width":spdWidth+"px","height":spdHeight+"px"});
			}else{
				$(".searPage").css({"width":spdWidth+"px","height":spbHeight+"px"});
			}
			
			$(".searPage").show();
			$(".searPage .searText").focus();
				
		})
		
	}
	if($("div").hasClass("top")){
		
		$(".topBut").click(function(){
			
			$("html").css({"overflow":"hidden"});
		    var spdWidth = document.documentElement.clientWidth;
		    var spbHeight = document.body.clientHeight;
			var spdHeight = document.documentElement.clientHeight;
			
			if(spdHeight > spbHeight){
				
				$(".searPage").css({"width":spdWidth+"px","height":spdHeight+"px"});
			}else{
				$(".searPage").css({"width":spdWidth+"px","height":spbHeight+"px"});
			}
			
			$(".searPage").show();
			$(".searPage .searText").focus();
				
		})
		$(".top .topCls").toggle(function(){
			$(".sinav").show();	
		},function(){
			$(".sinav").hide();	
		})
		
	}
	if($("div").hasClass("searPage")){
		
		$(".topClose").click(function(){
			
			$(".searPage").hide();
			$("html").css({"overflow":"auto"});
				
		})
	}
	//返回顶部
	if($("div").hasClass("returnTop")){
			
		$(".returnTop").click(function(){
		   $("html,body").animate({scrollTop:0},350);
		})
		
	   $(window).scroll(function(){
							
		scrollPos = document.documentElement.scrollTop || document.body.scrollTop;
		
		if(scrollPos > 300){
			$(".returnTop").show("fast");
		}else{
			$(".returnTop").hide("fast");
		}
		
	  })
	  
    }
	
	//商品分类
	if($("div").hasClass("classDiv")){
		
		var topHeight = $(".top").height();
		//var sinavHeight = $(".sinav").height();
		var sinavHeight =0;
		var wHeight = document.documentElement.clientHeight;
		var totalHeight = wHeight-topHeight-sinavHeight-8;
		
		$(".rightCDiv").css({"height":totalHeight+"px"});
		$(".leftCDiv").css({"height":totalHeight+"px"});
		//
		$("#classDivId").setTab("leftCDiv","tabDiv","selli"); 
		$(".botDiv").hide();
	}
})

//判断回退历史
function gbackhistory12312(){
  
  if(history.go(-1)==undefined){
    $(".topReturn").attr("href","http://m.epff.cc/mindex.html");
  }
}

//判断回退历史
function gbackhistory(){
    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){ // IE  
	     if(history.length > 0){  
	          //if(history.go(-1)==undefined){
	          //  window.location.href="http://m.epff.cc/mindex.html";
	         // }else{
	            history.go(-1);
	         // }
	     }else{  
	         window.location.href="http://m.epff.cc/mindex.html";
	     }  
	 }else{ //非IE浏览器  
	     if (navigator.userAgent.indexOf('Firefox') >= 0 ||  
	         navigator.userAgent.indexOf('Opera') >= 0 ||  
	         navigator.userAgent.indexOf('Safari') >= 0 ||  
	         navigator.userAgent.indexOf('Chrome') >= 0 ||  
	         navigator.userAgent.indexOf('WebKit') >= 0){
	         if(history.length > 1){  
	              //if(history.go(-1)==undefined){
		          //  window.location.href="http://m.epff.cc/mindex.html";
		         // }else{
		            history.go(-1);
		        //  }
	         }else{  
	            window.location.href="http://m.epff.cc/mindex.html";
	         }  
	     }else{ 
	         //未知的浏览器  
	         if(history.length > 0){ 
	            // if(history.go(-1)==undefined){
		         //   window.location.href="http://m.epff.cc/mindex.html";
		         // }else{
		            history.go(-1);
		         // }
	         }else{  
	            window.location.href="http://m.epff.cc/mindex.html";
	         }  
	     }  
    }
}







