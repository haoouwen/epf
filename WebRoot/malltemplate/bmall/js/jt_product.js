$(document).ready(function(){
	/*商品列表页*/
	//商品选择
	if($("div").hasClass("selmain")){
		//商品品牌
		if($("div").hasClass("rsPicDiv")){
			
			var pdHeight = $(".rsPicDiv").height();
			if(pdHeight > 220){
			   $(".rsPicDiv").css({"height":"186px","overflow":"auto"});
			}
			
			$(".rsPicDiv li").click(function(){
				
				if($(this).hasClass("selli")){
					$(this).removeClass("selli");
				}else{
					$(this).addClass("selli");
					$(this).siblings("li").removeClass("selli");
				}
			})
		}
		//商品文字
		if($("div").hasClass("rsTextDiv")){
			
			$(".rsTextDiv li").click(function(){
				
				if($(this).hasClass("selli")){
					$(this).removeClass("selli");
				}else{
					$(this).addClass("selli");
					$(this).siblings("li").removeClass("selli");
				}
			})
		}
		//没有内容的标签隐藏
		if($("div").hasClass("selcont")){
			
			$(".rsTextDiv").each(function(){
				
               if(!$(this).text()){
				   $(this).parents(".selcont").hide();
			    }
            });
			
		}
	}
	//商品排序
	if($("div").hasClass("proDiv")){
		
	   //大图小图切换
	   $(".proShow b").click(function(){
		   if($(this).hasClass("dtsel")){
			   return;
		    }else if($(this).hasClass("xtsel")){
				return;
			}else if($(this).hasClass("xtnsel")){
				//小图显示
				$("#listshow").val("0");
			}else{
				//大图显示
				$("#listshow").val("1");
			}
			shoulisttype();
	   })
	}
	
	//产品列表
	if($("div").hasClass("proList")){
		
		$(".bProShow li").hover(function(){
		 	 $(this).addClass("addli");
		    },function(){
			 $(this).removeClass("addli");
			}
		)
	}
	//列表显示方式
	shoulisttype();
	/*商品详细页*/
	
	//放大镜
	if($("div").hasClass("prozoom")){
		useZoom();
	}	
	//选择产品规格
	if($("div").hasClass("specifica")){
		selectNorms("spetabId","spttext","seldiv")//文字规格
	    selectNorms("spetabId","sptpic","seldiv")//图片规格
	}
	
	//产品详细尾部
	if($("div").hasClass("pdbwDiv")){
		
		$(".pdbwDiv .prodh2 li:last").css({"border-right":"#e4e4e4 solid 1px"})
		//tab
		$("#pdbwDivId").setTab("prodh2","tabDiv","selli")
	}
	//隐藏分类导航
	if($("div").hasClass("headerListBot")){
		$(".headerListBot").hover(function(){
			$(".headerListBot .nav").show();
		},function(){
			$(".headerListBot .nav").hide();
		});
	}
})

//放大镜
function useZoom(){
	
	var SPEC_SIZE = 5;
	
	var specLength = $(".speclist li").length;
	
	$(".jqzoom").jqZoom({
		xzoom:400,
		yzoom:290,
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
	
	    //控制显示详细页图片水印的 暂时不启用
		//var src=$(this).attr("jmidimg");
		//var jbigimg=$(this).attr("jbigimg");
		//$(".jqzoom img").eq(0).attr({src:src.replace("\/n5\/","\/n1\/"),jqimg:jbigimg.replace("\/n5\/","\/n0\/")});
		//$(this).css({"border":"2px solid #e4b632"});

		var src=$(this).attr("src");
		$(".jqzoom img").eq(0).attr({src:src.replace("\/n5\/","\/n1\/"),jqimg:src.replace("\/n5\/","\/n0\/")});
		$(this).css({"border":"2px solid #e4b632"});
		
	}).bind("mouseout",function(){
		
		$(this).css({"border":"1px solid #ececec"});
	});
}

//详细页规格的选择
function selectNorms(id,selType,selClass){
	
	var $selCont = $("#"+id+" "+"."+selType+" "+"li"+" "+"div");
	
	$selCont.click(function(){
			
		var dClass = $(this).attr("class");
			
		if(dClass == selClass){
			
			$(this).removeClass(selClass);
			$(this).find("b").remove();
			
		}else{
				
			$(this).addClass(selClass);
			$(this).append("<b> </b>");
			$(this).parent("li").siblings().children("div").removeClass(selClass).find("b").remove();
		}
	})
	
}
///////////////////////////////////////////////////
//列表显示方式
function  shoulisttype(){
    if($("#listshow").val()=="1"){
     //大图标显示
      $("#small_ico").removeClass("xtsel").addClass("xtnsel");
	  $("#big_ico").removeClass("dtnsel").addClass("dtsel");
     //大图列表显示
      $("#big_showlis").show();
	  $("#small_showlist").hide();
    }else{
      //小图标显示
	  $("#big_ico").removeClass("dtsel").addClass("dtnsel");
	  $("#small_ico").removeClass("xtnsel").addClass("xtsel");
	  //小图列表显示
	   $("#big_showlis").hide();
	   $("#small_showlist").show();
    }
}
function toSubmit(sort_type,order_by){
    $("#order_by_s").val(order_by);
    if(order_by=="collnum" && sort_type==""){
       sort_type = "collNum_desc";
    }else if(order_by=="salenum" && sort_type==""){
       sort_type = "sale_num_desc";
    }else if(order_by=="price" && sort_type==""){
       sort_type = "price_desc";
    }
	$("#sort").val(sort_type);
	$("#goodsList").submit();
}
function prepare_AddCart(cartpos,goods_id){
	//准备信息
	$("#cart_goods_id").val($("#cart_goods_id_"+goods_id).val());
	$("#cart_goods_name").val($("#cart_goods_name_"+goods_id).val());
	$("#cart_is_virtual").val($("#cart_is_virtual_"+goods_id).val());
	$("#cart_goods_img_path").val($("#cart_goods_img_path_"+goods_id).val());
	$("#cart_shop_cust_id").val($("#cart_shop_cust_id_"+goods_id).val());
	$("#cart_shop_name").val($("#cart_shop_name_"+goods_id).val());
	$("#cart_give_inter").val($("#cart_give_inter_"+goods_id).val());
	$("#cart_goods_sale_price").val($("#cart_goods_sale_price_"+goods_id).val());
	$("#cart_sepc_name").val($("#cart_sepc_name_"+goods_id).val());
	$("#cart_sepc_name").val($("#cart_sepc_name_"+goods_id).val());
	$("#cart_sepc_name").val($("#cart_sepc_name_"+goods_id).val());
	$("#cart_sepc_name").val($("#cart_sepc_name_"+goods_id).val());
	//加入购物车
	addCart(cartpos);
}