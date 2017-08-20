$(document).ready(function() {
	//首页未隐藏的菜单
	createClaMenu("claMain","claMeun","claItem",1);
	
    //首页大图轮播
	if($("div").hasClass("iFocus")){
		aFocus("iFocusId",3,"nSpan","selSpan",false);
	}
	//大图轮播下的广告图
	if($("div").hasClass("advFocus")){
		
		var advliSize = $(".advFocus li").length;
		
		if(advliSize>3){
			
			$('#advulId').bxCarousel({
			display_num:3, 
			move:3, 
			auto:true, 
			controls:true,
			auto_hover:true,
			margin:0
		   });
		   
		}
		
		$(".advFocus").hover(function(){
			
			$(".advFocus  .bx_wrap .prev").show();
			$(".advFocus  .bx_wrap .next").show();
			
			},function(){
				
			$(".advFocus  .bx_wrap .prev").hide();
			$(".advFocus  .bx_wrap .next").hide();
		})		
		
	}
	//新闻快讯
	if($("div").hasClass("iNews")){
		$("#iNesId").setTab("inh2","tabDiv","selli");
	}
	
	//楼层
	if($("div").hasClass("floDiv")){
		
		var floorNum = $(".floor").length;
		
		for(i=1;i<=floorNum;i++){
			
			//楼层左边热销商品切换
			var hotPContId = "hotPContId"+i;
			$(".floor .hotPro .hotPCont:eq("+(i-1)+")").attr("id",hotPContId);
			aFocus(hotPContId,4," "," ",false);
			
			//楼层右边table切换
			var rfloorId = "rfloorId"+i;
			$(".floor .rfloDiv:eq("+(i-1)+")").attr("id",rfloorId);
			$("#"+rfloorId).setTab("fch2","tabDiv","selli");
			
			//楼层右边第一页轮播
			var fFocusDivId = "fFocusDivId"+i;
			$(".floor  .floCont .fFocusDiv:eq("+(i-1)+")").attr("id",fFocusDivId);
			aFocus(fFocusDivId,4," "," ",false);
				
		}
		
		$(".fFocusDiv").hover(function(){
			 $(this).find(".pre").show();
			 $(this).find(".next").show();
			},function(){
			 $(this).find(".pre").hide();
			 $(this).find(".next").hide();
		})
		
	}
	//楼层描点
	if($("div").hasClass("flPoint")){
		
		flootMain();
		
		$(window).resize(function(){
			flootMain();
		})
	}	
	
});

//换一换品牌
function refbandlist(){
   var strTable="";
	$.ajax({
          type: "post",
          url: "/mall/goodsbrand!getRandBrandList.action?band_number=20",
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
          	  for(var i=0;i<data.length;i++){
			      strTable+="<li><a href='/mall-searchbrand-"+data[i].brand_id+".html' target='_blank' title='"+data[i].brand_name+"'><img src='"+data[i].logo+"' width='140px' height='80px'  title='"+data[i].brand_name+"'  /></a></li>";
			    }  
          }	 
	}); 
	$("#bands_id").html(strTable);
}

//楼层的条件隐藏
function flootMain(){
	
	var cWidth = document.documentElement.clientWidth;
	
	if(cWidth < 1300){
		$(".flPoint").hide();
	}else{
		$(".flPoint").css({"left":((cWidth-1180)/2-55)+"px"});
		flootPos("flPointId");
	}
}
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
		topPos[i] = $(".fh2:eq("+i+")").offset().top;
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
		var cWidth = document.documentElement.clientWidth;
		
		if(scrollPos > 950 && cWidth > 1300){
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
		var fLen =1050;
		var sLen =1950;
		var tLen =2750;
		var foLen =3620;
		var fiLen =4450;
		var siLen =5300;
		var seLen =6050;
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
