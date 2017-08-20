$(document).ready(function(){
    updateEndTime();
	//分类菜单
	createClaMenu("claMain","claMeun","claItem",1);
	/*全宽轮播*/
	 aFocus("fullFocusId",3,"nSpan","selSpan",true);
	 //热门商品
	$("#hsaslId").setTab("hsh2","tabDiv","selli");
	//公告
	$("#noticeId").setTab("noh2","tabDiv","selli");
	//限时抢购
	aFocus("fbuyId",3,"nSpan","selSpan",false,4000);
	//试用
	var div = document.getElementById('fbuyId');
	var ul = div.getElementsByTagName('ul')[0];
	var hasChild = ul.children.length ? true : false;
	if(!hasChild){
		$("#fbuyId").addClass("try_no");
	}
	/*楼层颜色*/
	for(var i=1;i<8;i++){
		
		var floorTopStr = "floorTop"+(i+1);
		var h2Str = "h2"+(i+1);
		var lfContStr = "lfCont"+(i+1); 
		
		$(".floorTop:eq("+i+")").addClass(floorTopStr);//标题下面的边框				
		$(".floorTop h2:eq("+i+")").addClass(h2Str);//标题		
		$(".floorCont .lfCont:eq("+i+")").addClass(lfContStr);//左侧的颜色
		
		var rfContId = "rfContId"+i;
		$(".floorCont .rfCont:eq("+(i-1)+")").attr("id",rfContId);
		rfContId = "#"+rfContId;
		lrScroll(rfContId,".rfContul",1,false);
		
	}
	
	/*楼层描点*/
	flootPos("flPointId");
	/*回到顶部*/
	$("#reTopId").click(function(){
		$("html,body").animate({scrollTop:0}, 500);
	})
});

//楼层描点
function flootPos(id){
	
	var  scrollPos,
	     topPos= new Array(),
		 $floatId = $('#'+id),
	     $floatLi = $('#'+id+" "+"ul"+" "+"li"),
		 liLenght = $floatLi.length;
	
	 $floatId.hide();
	//触摸
	 $("#flPointId li").hover(function(){
		  $(this).addClass("hli");
		},function(){
			$(this).removeClass("hli");
	 })
	 
	  $("#flPointId li").click(function(){
		  $(this).addClass("selli").siblings().removeClass("selli");
		 })
		
	//滑动	 		
	for(var i= 0;i<liLenght;i++){	
		topPos[i] = $(".floorTop:eq("+i+")").offset().top;
 	}
		
	$('#'+id+" "+"ul"+" "+"li:eq(0)").click(function(){
		$("html,body").animate({scrollTop:topPos[0]}, 500);
	})
	$('#'+id+" "+"ul"+" "+"li:eq(1)").click(function(){
		$("html,body").animate({scrollTop:topPos[1]}, 500)
	})
	$('#'+id+" "+"ul"+" "+"li:eq(2)").click(function(){
		$("html,body").animate({scrollTop:topPos[2]}, 500);
	})
	$('#'+id+" "+"ul"+" "+"li:eq(3)").click(function(){
		$("html,body").animate({scrollTop:topPos[3]}, 500);
	})
	$('#'+id+" "+"ul"+" "+"li:eq(4)").click(function(){
		$("html,body").animate({scrollTop:topPos[4]}, 500);
	})
	$('#'+id+" "+"ul"+" "+"li:eq(5)").click(function(){
		$("html,body").animate({scrollTop:topPos[5]}, 500);
	})
	$('#'+id+" "+"ul"+" "+"li:eq(6)").click(function(){
		$("html,body").animate({scrollTop:topPos[6]}, 500);
	})
	
	//滚动事件
	$(window).scroll(function(){
							
		scrollPos= document.documentElement.scrollTop || document.body.scrollTop;
		
		if(scrollPos > 900){
			$floatId.show("fast");
		}else{
			$floatId.hide("fast");
		}
		 $("#flPointId li").hover(function(){
		  $(this).addClass("hli");
		},function(){
			$(this).removeClass("hli");
	 })
	 
	  $("#flPointId li").click(function(){
		  $(this).addClass("selli").siblings().removeClass("selli");
		 })
		var fLen =1000;
		var sLen =1350;
		var tLen =1800;
		var foLen =2150;
		var fiLen =2550;
		var siLen =2850;
		var seLen =3350;
		if(scrollPos > fLen && scrollPos < sLen){
			$('#'+id+" "+"ul"+" "+"li:eq(0)").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos > sLen && scrollPos < tLen){
			$('#'+id+" "+"ul"+" "+"li:eq(1)").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos > tLen && scrollPos < foLen ){
			$('#'+id+" "+"ul"+" "+"li:eq(2)").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos > foLen && scrollPos < fiLen ){
			$('#'+id+" "+"ul"+" "+"li:eq(3)").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos > fiLen && scrollPos < siLen ){
			$('#'+id+" "+"ul"+" "+"li:eq(4)").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos > siLen && scrollPos < seLen ){
			$('#'+id+" "+"ul"+" "+"li:eq(5)").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos > seLen ){
			$('#'+id+" "+"ul"+" "+"li:eq(6)").addClass("selli").siblings().removeClass("selli");
		}else{
			$('#'+id+" "+"ul"+" "+"li").removeClass("selli");
		}
		
	 });
	
}

//楼层商品左右移动
function lrScroll(wraper,wraperCont,speed,isFlag){  
    
	var flag = "left";
	var $wraper = $(wraper); 
	var $wraperCont = $wraper.children(wraperCont);
	var $prev = $wraper.find(".prevBut"); 
	var $next = $wraper.find(".nextBut"); 
	
	
	var liSize = $wraperCont.find("li").size();
	var liWidth = $wraperCont.find("li").outerWidth();
	var ulWidht = liSize*liWidth;
	$wraperCont.find("ul").css({"width":ulWidht+"px"})
	
	
	var $wraperul = $wraperCont.find('ul');
	var moveNum = $wraperul.find('li').outerWidth(); 
	
	var vSpeed = speed; 
	
	$next.click(function(){ 
		$wraperul.animate({'margin-left':-moveNum},function(){ 
			$wraperul.find('li').eq(0).appendTo($wraperul); 
			$wraperul.css({'margin-left':0}); 
		}); 
		flag = "left";
	}); 
	
	$prev.click(function(){ 
		$wraperul.find('li:last').prependTo($wraperul); 
		$wraperul.css({'margin-left':-moveNum}); 
		$wraperul.animate({'margin-left':0}); 
		flag = "right";
	}); 
	
	if (isFlag == true){ 
	
		times = setInterval(function() { flag=="left"?$next.click():$prev.click()},vSpeed*1000); 
		
		$wraper .hover(function(){
			clearInterval(times);
		},function(){
			times= setInterval(function() {flag=="left"?$next.click():$prev.click()},vSpeed*1000);
		});
	} 
} 
function updateEndTime()
{
	var date = new Date();
	var time = date.getTime(); 
	
	$(".settime").each(function(i){
	
	var endDate =this.getAttribute("endTime");
	var endDate1 = eval('new Date('+ endDate.replace(/\d+(?=-[^-]+$)/, function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
	var endTime = endDate1.getTime();
	
	var lag = (endTime - time) / 1000; 
	if(lag > 0)
	{
	var second = Math.floor(lag % 60); 
	var minite = Math.floor((lag / 60) % 60);
	var hour = Math.floor((lag / 3600) % 24);
	var day = Math.floor((lag / 3600) / 24);
	$(this).find(".pTime").html('剩<b>' + day + '</b>天<b>'+ hour + '</b>:<b>' + minite + '</b>:<b>' + second + '</b>结束');
	}
	else
	$(this).find(".pTime").html("抢购过期啦！");
	});
	setTimeout("updateEndTime()",1000);
}
function goAdv(obj){
	location.href=obj;
}










