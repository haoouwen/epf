$(document).ready(function(){
	/*************商品列表页**************/
	/*条件选择商品*/
	if($("div").hasClass("selmain")){
		$(".rscont  a").click(function(){
			$(this).addClass("sela").siblings().removeClass("sela");
		})
		$(".rscont ul li").click(function(){
			$(this).addClass("selli").siblings().removeClass("selli");
		})
		$(".selcont:last").css({"border":"none"});
		/*默认，人气，销售，价格*/
        selSort();
		selShow();
		$("#proSortId").floatDiv("floatTop");
	}
	/*列表的效果*/
	if($("div").hasClass("proList")){
		
		$(".bProShow li").hover(function(){
		 	 $(this).addClass("addli");
		    },function(){
				$(this).removeClass("addli");
			}
		)
	}
	/*热卖*/
	if($("div").hasClass("hsale")){
		$("#hsalesId").setTab("hsh2","tabDiv","selli");
	}
	
	/*************商品详细页**************/
	var goods_id = $("#goods_id").val();
	/*放大镜*/
	if($("div").hasClass("prozoom")){
		useZoom();
	}
	/*规格选择*/
	if($("div").hasClass("specifica")){
		selectNorms("spetabId","spttext","seldiv")//文字规格
	    selectNorms("spetabId","sptpic","seldiv")//图片规格
	    //点击购买按钮
		$(".sptbut .buyNow,.sptbut .addCart").click(function(){
			
		  if(!isFindSpt()){
			  $(".sptbut").hide();
			  $(".specifica").addClass("addSpecifica");
			  $(".specifica .speh2").show();
			  $(".speh2").addClass("addSpeh2");
		  } 
		         
		})
	    //进入选择规格
		 $((".specifica .spttext li,.specifica .sptpic li")).click(function(){
			 
			    if($(".specifica  h2").hasClass("addSpeh2")){
			    
			        if(isFindSpt()){
					    $(".sptbut").show();
					 }else{
					     $(".sptbut").hide();
					} 			 
			   }	  
		 })
		 //退出选择规格
		  $(".speh2 span").click(function(){
			   $(".speh2").removeClass("addSpeh2");
			   $(".specifica .speh2").hide();
			   $(".specifica").removeClass("addSpecifica");
			   $(".sptbut").show();
		  })
	}
	/*选择快递地区*/
	if($("div").hasClass("selaDiv")){
		selectArea();
	}
	//排行推荐
	if($("div").hasClass("rank")){
		$(".rankcont ul li:gt(2)").find("span").css({"background":"#868686"});
	}
	/*清空所有浏览记录*/
	if($("div").hasClass("browse")){
		clearRecord();
	}
	/*评价*/
	if($("div").hasClass("prodDiv")){
		//浮动
		$("#prodh2Id").floatDiv("floatTop");
		//描点
		//tracingPoint();
		//tab切换 细节处理
		$("#proDId").setTab("prodDiv","tabDiv","selli");
		
		var prodDivHeight = $(".prodDiv").offset().top;
		
		$(".prodh2 ul li:gt(0)").click(function(){
			$(".prodh2 ul li:first").addClass("addli");
			$("html,body").animate({scrollTop:prodDivHeight},0);
		})
		//商品咨询
	  	$("#evahsLiId li").click(function(){
			$(this).addClass("selli").siblings().removeClass("selli");
			var c_type=$(this).attr("c_type");
				//商品咨询记录插件
		$('#consult').mall({
			    actionName:'/mall/goods!goodsConsultComment.action?goods_id='+goods_id+'&c_type='+c_type,
			 	columnModel:ColumnConsultation,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人咨询",
			 	tabletitle:"商品咨询"
		});
//		
		})
	}
		//推荐商品
	if($("div").hasClass("recProduct")){
		aFocus("recProId",3,"nSpan","selSpan",false,4000);
	}
})
/*默认，人气，销售，价格*/
function selSort(){
	$(".proSort b:eq(0)").addClass("mrsel");
	$(".proSort b:eq(1)").addClass("rqnsel");
	$(".proSort b:eq(2)").addClass("xlnsel");
	$(".proSort b:eq(3)").addClass("jgnsel");
	var sort=$("#sort").val();
	if(sort=='0'){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrsel");
		$(".proSort b:eq(1)").addClass("rqnsel");
		$(".proSort b:eq(2)").addClass("xlnsel");
		$(".proSort b:eq(3)").addClass("jgnsel");
	}
	if(sort=='collNum_desc'){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrnsel");
		$(".proSort b:eq(1)").addClass("rqsel");
		$(".proSort b:eq(2)").addClass("xlnsel");
		$(".proSort b:eq(3)").addClass("jgnsel");
	}
	if(sort=='sale_num_desc'){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrnsel");
		$(".proSort b:eq(1)").addClass("rqnsel");
		$(".proSort b:eq(2)").addClass("xlsel");
		$(".proSort b:eq(3)").addClass("jgnsel");
	}
	if(sort=='price_asc'){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrnsel");
		$(".proSort b:eq(1)").addClass("rqnsel");
		$(".proSort b:eq(2)").addClass("xlnsel");
		$(".proSort b:eq(3)").addClass("jgsel");
	}
	$(".proSort b:eq(0)").click(function(){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrsel");
		$(".proSort b:eq(1)").addClass("rqnsel");
		$(".proSort b:eq(2)").addClass("xlnsel");
		$(".proSort b:eq(3)").addClass("jgnsel");
	})
	$(".proSort b:eq(1)").click(function(){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrnsel");
		$(".proSort b:eq(1)").addClass("rqsel");
		$(".proSort b:eq(2)").addClass("xlnsel");
		$(".proSort b:eq(3)").addClass("jgnsel");
	})
	$(".proSort b:eq(2)").click(function(){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrnsel");
		$(".proSort b:eq(1)").addClass("rqnsel");
		$(".proSort b:eq(2)").addClass("xlsel");
		$(".proSort b:eq(3)").addClass("jgnsel");
	})
	$(".proSort b:eq(3)").click(function(){
		$(".proSort b:lt(4)").removeAttr("class");
		$(".proSort b:eq(0)").addClass("mrnsel");
		$(".proSort b:eq(1)").addClass("rqnsel");
		$(".proSort b:eq(2)").addClass("xlnsel");
		$(".proSort b:eq(3)").addClass("jgsel");
	})
}
/*大图小图显示*/
function selShow(){
	$(".proShow b:eq(0)").addClass("dtsel");
	$(".proShow b:eq(1)").addClass("xtnsel");
	$(".proShow b:eq(0)").click(function(){
		$(".proShow b:lt(2)").removeAttr("class");
		$(".proShow b:eq(0)").addClass("dtsel");
		$(".proShow b:eq(1)").addClass("xtnsel");
	})
	$(".proShow b:eq(1)").click(function(){
		$(".proShow b:lt(2)").removeAttr("class");
		$(".proShow b:eq(0)").addClass("dtnsel");
		$(".proShow b:eq(1)").addClass("xtsel");
	})
}

//放大镜
function useZoom(){
	
	var SPEC_SIZE = 5;
	
	var specLength = $(".speclist li").length;
	
	$(".jqzoom").jqZoom({
		xzoom:405,
		yzoom:320,
		offset:10,
		position:"right",
		preload:1,
		lens:1
	});
	$(".speclist").jdMarquee({
		deriction:"left",
		width:385,
		height:68,
		step:1,
		speed:4,
		delay:10,
		control:true,
		_front:".preBut",
		_back:".nextBut"
	});
	
	if(specLength > 5){
		$(".preBut").show();
		$(".nextBut").show();
	}
	
	$(".speclist img").bind("mouseover",function(){
		
		var src=$(this).attr("src");
		$(".jqzoom img").eq(0).attr({src:src.replace("\/n5\/","\/n1\/"),jqimg:src.replace("\/n5\/","\/n0\/")});
		$(this).css({"border":"2px solid #bc0911"});
		
	}).bind("mouseout",function(){
		
		$(this).css({"border":"1px solid #ececec"});
	});
}
//规格的选择
function selectNorms(id,selType,selClass){
	
	var $selCont = $("#"+id+" "+"."+selType+" "+"li"+" "+"div");
	
	$selCont.click(function(){
			
		var dClass = $(this).attr("class");

	})
	
}
//判断有没有寻找规格
function isFindSpt(){
			
	var sptTextNum = $(".specifica .spttext").length;
	var sptSelDiv = $(".specifica .spttext").find(".seldiv").length;
	var sptPicNum = $(".specifica .sptpic").length;
	var sptSelPicDiv = $(".specifica .sptpic").find(".seldiv").length;
	
	if( sptTextNum===sptSelDiv && sptPicNum ===sptSelPicDiv){
	   return true;
	}else{
		return false;
	}
			
}
//快递地区选择
function selectArea(){
	$(".selArea i").click(function(){
		$(".selaDiv").show();
	})
	$(".selaDiv p span").click(function(){
		$(".selaDiv").hide();
	})
}
/*最近浏览记录*/
function clearRecord(){
	var broNum = $(".broCont ul li").length;
	if(broNum > 5){
		$(".brop").show();
	}
}
//描点平滑定位
function tracingPoint(){
	
	var fPos = $("#parameterId").offset().top-50,
		sPos = $("#evaluaId").offset().top-40,
		tPos = $("#recordId").offset().top-40,
		foPos = $("#consultId").offset().top-40;
		doPos = $("#safter").offset().top-40;
	
	$("#profId").addClass("selli");
	
	$("#profId").click(function(){
		$("html,body").animate({scrollTop:fPos}, 500);
	})
	$("#prosId").click(function(){
		$("html,body").animate({scrollTop:sPos}, 500);
	})
	$("#protId").click(function(){
		$("html,body").animate({scrollTop:tPos}, 500);

	})
	$("#profoId").click(function(){
		$("html,body").animate({scrollTop:foPos}, 500);
	})
	
	$("#prodId").click(function(){
		$("html,body").animate({scrollTop:doPos}, 500);
	})
	$(window).scroll(function(){
		
		var scrollPos= $("#prosId").offset().top;
		
		if(scrollPos >= sPos && scrollPos < tPos) {
			
			$("#prosId").addClass("selli").siblings().removeClass("selli");
			$("#profId").addClass("addli");
			
		}else if(scrollPos >= tPos && scrollPos < doPos){
			
			$("#protId").addClass("selli").siblings().removeClass("selli");
			
		}else if(scrollPos >= doPos&& scrollPos < foPos){
			
			$("#prodId").addClass("selli").siblings().removeClass("selli");
		}else if(scrollPos >= foPos){
			
			$("#profoId").addClass("selli").siblings().removeClass("selli");
		}
		else{
			
		    $("#profId").addClass("selli").siblings().removeClass("selli");
		}
	});
	
}