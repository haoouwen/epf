$(document).ready(function() {
	
	/*门店*/
	if($("div").hasClass("shopIntro")){
		 $("#shopIntroId").setTab("introh2","tabDiv","selli");
	} 
		/*门店列表*/
	if($("div").hasClass("fShopList")){
		
		$(".fShopList li").hover(function(){
			$(this).addClass("addli");
			},function(){
			 $(this).removeClass("addli");
		})
	}
	/*门店详细*/
	if($("div").hasClass("fShopDetail")){	
		$(".mapSpan").click(function(){
			$("#mapId").popup({pWidth:"920",
							   pHeight:"590",
							   pTitle:"地图"
							});
		})
	}
	if($("div").hasClass("fShopServer")){
		$("#fserContId").setTab("fscul","tabDiv","selli");
	}
	
	/*品牌列表*/
	
   //字母筛选
   if($("div").hasClass("zmTop")){
	   
	   $(".zmTop li").click(function(){
		   if($(this).hasClass("selli")){
			   return;
			}else{
			  $(this).addClass("selli");
			  $(this).siblings("li").removeClass("selli");
			}
	   });
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
		});
	}
	//品牌收藏
	if($("div").hasClass("brandList")){
		//
		$(".brandList h2 span").toggle(function(){
			$(this).parent("h2").siblings(".rsPicDiv").hide();
			},function(){
			$(this).parent("h2").siblings(".rsPicDiv").show();
		});	
		//
		$(".brandList .rsPicDiv li").hover(function(){
			$(this).find(".ppsc").show();
			},function(){		
			$(this).find(".ppsc").hide();
		});
	}
});

/////////////////以下程序新增 HXK///////////////////////////
//门店查找
function storeSearch(){
$("#asysuserList").submit();
}









