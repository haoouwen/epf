$(document).ready(function() {
	
	/*商品列表*/
	
	//排序选择
	if($("div").hasClass("sortDiv")){
		
		//筛选
		if($("div").hasClass("filterDiv")){
			
			var spdHeight = document.documentElement.clientHeight;
			$(".filterDiv").css({"height":spdHeight+"px"});
			//选择
			$(".filsDiv .pfil a").click(function(){
				if($(this).hasClass("sela")){
					$(this).removeClass("sela");
				}else{
					$(this).addClass("sela");
					$(this).siblings().removeClass("sela");
				}
			});
			//
			$(".sxDiv").click(function(){
				$(".filterDiv").show();
				$(".filClose").show();
				$(".filterDiv").animate({"right":"0px"},350);
				$(".filClose").animate({"right":"250px"},350);
			});
			$(".filClose").click(function(){
				$(".filterDiv").animate({"right":"-250px"},350);
				$(".filClose").animate({"right":"-250px"},350);
				
			});
			
		}
		
	}
	
	/*产品详细*/
	
	if($("div").hasClass("proFocus")){
		$(".proFocus").mobileSlider({width:620,during:5000});
	}
	
	/*选择规格*/
	if($("div").hasClass("ggDiv")){
		
		if($("div").hasClass("ggSDiv")){
			 
			//$(".cartDiv").hide();
			
			$(".ggDiv .ggSDiv li").click(function(){
				
				if($(this).hasClass("selli")){
					$(this).removeClass("selli");
				}else{
				   $(this).addClass("selli");
				   $(this).siblings().removeClass("selli");
				}
			
				var ggsDivNum = $(".ggDiv .ggSDiv").length;
				var ggsDivSelli= $(".ggDiv .ggSDiv").find(".selli").length;
				
				if(ggsDivNum===ggsDivSelli || ggsDivSelli === 0){
					$(".cartDiv").show();
				}else{
					$(".cartDiv").hide();
			    }
			
		   });
		   
		}	
		
	}
	/*商品详情*/
	if($("div").hasClass("proDetail")){
		
		$("#proDeId").setTab("proDeh2","tabDiv","selli");
		
		$(".evah2 span").click(function(){
			$(this).addClass("selSapn");
			$(this).siblings().removeClass("selSapn");
		});
		
	}
		
});

//列表显示方式
function  big_small_submit(show_type){
    if(show_type=="0"){
      $("#listshow").val("1");
	  $("#smBut").val("1");
     //大图列表显示
    }else{
       $("#listshow").val("0");
       $("#smBut").val("0");
    }
    $("#indexForm").submit();
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
	$("#indexForm").submit();
}

function toSubmitBrand(brand_id){
	$("#brand_id_s").val(brand_id);
	$("#indexForm").submit();
}

function toSubmitCat(cat_id){
    $("#goods_cat_attr_s").val(cat_id);
	$("#indexForm").submit();
}

var brand_id,en_name;

function chooseCat(cat_en_name){
   en_name = cat_en_name;
}

function chooseBrand(brand_id_str){
  brand_id = brand_id_str;
}

function toSearch(){
	$("#goods_cat_attr_s").val(en_name);
	$("#brand_id").val(brand_id);
	$("#indexForm").submit();
}

function clearAll(){
  $("#l_price_id").val("");
  $("#h_price_id").val("");
  $("#catEn_name").val("");
  $("#catdiv_id").find("i").each(function(){
		$(this).removeAttr("class");
  });
}