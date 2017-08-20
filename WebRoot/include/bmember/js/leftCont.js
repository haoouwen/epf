$(document).ready(function(){
 	var selli=$("#selli").val();
	var parentMenuId=$("#parentMenuId").val();
	var tow_meun_name="";
    var down_meun_name="";
	 if(parentMenuId!=""){
		 $("#"+parentMenuId).addClass("addspan").parent("h3").siblings("ul").show();
	     $("#"+parentMenuId).parents("li").siblings().find("h3 span").removeClass("addspan");
	     $("#"+parentMenuId).parents("li").siblings().children("ul").hide();
	     tow_meun_name=$("#"+parentMenuId).html();
	     $(".lpos").html("会员中心 >  "+tow_meun_name);
	 }else{
	 	//左边下拉菜单
		$(".wLul li ul li:last-child").addClass("addli");
		$(".wLul li:first span").addClass("addspan");
		$(".wLul li ul:first").show();
	 }
	 if(selli!=""){
		  $("#"+selli).addClass("selli");
		  down_meun_name=$("#"+selli).find("a").html();
		  $(".lpos").html("会员中心 >  "+tow_meun_name+">  "+down_meun_name);
	 }
	$(".wLul li h3 span").click(function(){
		$(this).addClass("addspan").parent("h3").siblings("ul").show();
		$(this).parents("li").siblings().find("h3 span").removeClass("addspan");
		$(this).parents("li").siblings().children("ul").hide();
		tow_meun_name=$(this).html();
		$(".lpos").html("会员中心 >  "+tow_meun_name);
	})
	
	//订单物流跟踪
	if($("div").hasClass("logistics")){
		$(".logistics i").hover(function(){
				$(this).siblings("div").show();
			},function(){
				$(this).siblings("div").hide();
			}
		)
	}
	//常用的table
	if($("div").hasClass("usedTDiv")){
		$(".usedTab tr th:last").css({"border-right":"none"});
		$(".usedTab tr td:last-child").css({"border-right":"none"});
	}
	
	//写信息
	if($("table").hasClass("writeTab")){
		
		$("input[name='member']").click(function(){
			
			if($("#yunys").attr("checked")){
				$("#memberId").hide();
			}
			if($("#huiy").attr("checked")){
				$("#memberId").show();
			}
			
		})
	}
	//收件箱
	if($("table").hasClass("inboxTab")){
		$(".inboxTab tr").hover(function(){
				$(this).addClass("addtr");
			},function(){
				$(this).removeClass("addtr");
			}
		)
	}
	//在线充值
	if($("div").hasClass("topupDiv")){
		$(".topupDiv").hide();
		$(".rechargeBtn").toggle(function(){
				$(".topupDiv").show();
				$(".cardDiv").hide();
			},function(){
				$(".topupDiv").hide();
				$(".cardDiv").hide();
		})
	}
	
	//充值卡充值
	if($("div").hasClass("cardDiv")){
		$(".cardDiv").hide();
		$(".rechargeCrad").toggle(function(){
				$(".cardDiv").show();
				$(".topupDiv").hide();
			},function(){
				$(".cardDiv").hide();
				$(".topupDiv").hide();
		})
	}
	//增加收货地址
	if($("div").hasClass("address")){
		
		$(".addrDiv .addrbut").click(function(){
			$(".addAtab").hide();
			$(".addrDiv .addAtab").show();
		})
	}

	
})


//加入购物车
function addCart(goods_id,sale_price){
  $.ajax({
  type:'post',
  url:'/cartgoods!addCart.action',
  data:{'goods_id':goods_id,'sale_price':sale_price},
  datatype:"json",
  async:false,
  success:function(data){
    if(data=="0"){
     jNotify("库存不足！");
    }else if(data=="1"){
     window.location.href="/cart.html";
    }
   }
  })
}

//收藏商品
function collect(goods_id,title){
   link_url ="/mall-goodsdetail-"+goods_id+".html";   
   $.ajax({  	 
          type: "post",    	     
          url: "/collect!ajxinsert.action",
          data:{title:title,link_url:link_url,goods_id:goods_id},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("您还没登陆，请先登陆!","系统提示");
             	window.location.href="/login.html?loc="+loc;
             }else if(data=='2'){
             	jAlert("已经收藏过此商品!","系统提示");
             }else if(data=='0'){
                jAlert("收藏成功!","系统提示");
             }else if(data=='3')
                jAlert("不能收藏自己的商品!");
          }
      });  
}