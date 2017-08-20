/**
 * Created by DS05 on 2016/4/1.
 */
$(function(){
	if($("div").hasClass("specialOffer")){
        $(".specialOffer .p").eq(1).css("float","right");
    }
	if ($("div").hasClass("rtCont")) {
	    //合作登录
	    $(".cLoginSpan").hover(function () {
	        $(this).addClass("addCLS");
	        $(this).find(".clsDiv").stop(true,true).slideDown(150);
	    }, function () {
	        $(this).removeClass("addCLS");
	        $(this).find(".clsDiv").stop(true,true).slideUp(50);
	    })
	    //手机版本
	    $(".phoneSpan").hover(function () {
	        $(this).addClass("addPhone");
	        $(this).find(".phoneDiv").stop(true,true).slideDown(150);
	    }, function () {
	        $(this).removeClass("addPhone");
	        $(this).find(".phoneDiv").stop(true,true).slideUp(50);
	    })
	}
	//右侧显示
	if ($("div").hasClass("leftSide")) {
	
	    $(".returnTop").hide();
	
	    leftSide();
	
	    //购物车
	    $(".leftSideDiv .lsCart").hover(function () {
	        $(this).find(".cartSpan").addClass("addCartSpan");
	    }, function () {
	        $(this).find(".cartSpan").removeClass("addCartSpan");
	    });
	
	    //控制弹出隐藏
	    var t_1 = true;
	    $(".leftSideDiv .lsCart").click(function () {
	        if (t_1 == true) {
	            $(".leftSideDiv .lsCart .cartSpan").addClass("selCartSpan");
	            $(".leftSide").animate({"right": "0px"}, 200);
	            t_1 = false;
	        } else if (t_1 == false) {
	            $(".leftSideDiv .lsCart .cartSpan").removeClass("selCartSpan");
	            $(".leftSide").animate({"right": "-242px"}, 200);
	            t_1 = true;
	        }
	    })
	
	
	    //领取优惠劵
	    leftOutS(".lsCoupon", "addlsCoupon");
	    //领取红包
	    leftOutS(".lsRedBag", "addlsRedBag");
	    //客服
	    leftOutS(".lsChat", "addlsChat");
	    //返回顶部
	    leftOutS(".lsReTop", "addlsReTop");
	    $(window).resize(function () {
	        leftSide();
	    })
	}
	
	
	
	//获取图片的个数
	if ($("div").hasClass("headerBanner")) {
	    var lbxg = {
	        num: 0,
	        t: null,
	        n: 0,
	        i: 0
	    };
        lbxg.num = $("#headerBannerImg a").length;
        $("#headerBannerUl li").eq(0).addClass("liLoad");
        //除了第一张图片其他的隐藏(也就是不是第一个a 其他的隐藏)
        $("#headerBannerImg  a:not(:first-child)").hide();
        $("#headerBannerUl li").mouseover(function () {
            lbxg.i = $(this).text() - 1;
            lbxg.n = lbxg.i;
            if (lbxg.i < lbxg.num) {
                if (!$(this).hasClass("liLoad")) {
                    $("#headerBannerImg a").filter(":visible").fadeOut(500).parent().children().eq(lbxg.i).fadeIn(500);
                    //更换li标签背景效果
                    $(this).addClass("liLoad");
                    $(this).siblings().removeClass();
                }
            }
        });
        function startTimer() {
            lbxg.t = setInterval(function () {
                lbxg.n = lbxg.n >= (lbxg.num - 1) ? 0 : ++lbxg.n;
                $("#headerBannerUl li").eq(lbxg.n).trigger("mouseover");
            }, 5000);
        }
        startTimer();
	}
	if ($("div").hasClass("headerListBot")) {
        $(".headerListBot .nav .li .DetailPages").each(function (index) {
            $(this).css({top:0-(120*index)+"px"})
        })
        $(".headerListBot .nav .li").hover(function () {
            $(this).addClass("liHover");
            $(this).find(".DetailPages").css({display:"block"});
        }, function () {
            $(this).removeClass("liHover");
            $(this).find(".DetailPages").css({display:"none"});
        })
    }
	//返回顶部
	$(".leftSideDiv .lsReTop").click(function () {
	    $("html,body").animate({scrollTop: 0}, 350);
	});
	if ($("div").hasClass("returnTop")) {
	
	    $(".returnTop").hover(function () {
	        $(this).addClass("reAddTop");
	    }, function () {
	        $(this).removeClass("reAddTop");
	    })
	
	    $(".returnTop").click(function () {
	        $("html,body").animate({scrollTop: 0}, 350);
	    })
	
	    $(window).scroll(function () {
	
	        scrollPos = document.documentElement.scrollTop || document.body.scrollTop;
	
	        if (scrollPos > 300) {
	            $(".returnTop").show("fast");
	        } else {
	            $(".returnTop").hide("fast");
	        }
	
	    })
	}
	
	//
	
	
	//顶部在线客服
	if ($("p").hasClass("topChat")) {
	    $(".topChat").click(function () {
	        $("#serverId").popup({pWidth: "300", pHeight: "300", pTitle: "客户服务"});
	    })
	}
	function leftOutS(add, addTop) {
	    $(".leftSideDiv " + add).hover(function () {
	        $(this).find("a").addClass(addTop);
	        $(this).find("span").animate({"left": "-80px"}, 200);
	    }, function () {
	        $(this).find("a").removeClass(addTop);
	        $(this).find("span").animate({"left": "0px"}, 200);
	    });
	
	}
	
	//头部购物车
	if ($("div").hasClass("iCart")) {
	    $(".iCart").hover(function () {
	        $(this).find(".ica").addClass("addIca");
	        $(this).find(".icCont").show();
	        var icTabHeight = $(".icTab").height();
	        if (icTabHeight > 155) {
	            $(".icTab").css({"height": "150px", "overflow": "auto"});
	        }
	    }, function () {
	        $(this).find(".ica").removeClass("addIca");
	        $(this).find(".icCont").hide();
	    })
	}
	
    jQuery.jqtab = function (tabtit, tab_conbox, shijian) {
        $(tab_conbox).find("div").hide();
        $(tabtit).find("li:first").addClass("current").show();
        $(tab_conbox).find("div:first").show();

        $(tabtit).find("li").bind(shijian, function () {
            $(this).addClass("current").siblings("li").removeClass("current");
            var activeindex = $(tabtit).find("li").index(this);
            $(tab_conbox).children().eq(activeindex).show().siblings().hide();
            return false;
        });

    };
    /*调用方法如下：*/
    //$.jqtab("#tabs","#tab_conbox","click");
    //
    $.jqtab(".floorTopRight", ".floorBotRight", "mouseenter");
	

    //设置楼层颜色
    if ($("div").hasClass("flPoint")) {
        var floorTops = [];
        var CscrollTop = $(window).scrollTop();
        var Cwidth = $(window).width();
        var Cheigth = $(window).height();
        var flPointIdLi = $("#flPointId li");
        var flPointId = $("#flPointId");
        var WZcolor = ["#f1a849", "#bc8bfe", "#6f6bfe", "#21ca91", "#df71c8", "#3c9edf"];
        flPointId.hide();
        function findDimensions() {
            if (window.innerWidth) {
                Cwidth = window.innerWidth;
            }
            else if ((document.body) && (document.body.clientWidth)) {
                Cwidth = document.body.clientWidth;
            }
            if (window.innerHeight) {
                Cheigth = window.innerHeight;
            }
            else if ((document.body) && (document.body.clientHeight)) {
                Cheigth = document.body.clientHeight;
            }
            if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
                Cheigth = document.documentElement.clientHeight;
                Cwidth = document.documentElement.clientWidth;
            }
            if (CscrollTop > (floorTops[0] - 255) && Cwidth > 1200) {
                flPointId.show("fast");
                if (Cwidth > 1300) {
                    flPointId.css({"left": ((Cwidth - 1180) / 2 - 55) + "px"});
                } else if (Cwidth < 1300 && Cwidth > 1200) {
                    flPointId.css({"left": ((Cwidth - 1180) / 2 - 40) + "px"});
                }else{
                    flPointId.hide();
                }
            } else {
                flPointId.hide("fast");
            }
        }
        findDimensions();
        window.onresize = findDimensions;
        marginTop10(".floor .floorBot .other ul li");
        $(".floor").each(function (index) {
            floorTops[index] = $(this).offset().top;
            $(".floor .floorTop .title span").eq(index).css("color", WZcolor[index]);
            $(".floor .floorBot .floorBotLeft").eq(index).css("border-top-color", WZcolor[index]);
            flPointIdLi.eq(index).hover(function () {
                $(this).css({"background": WZcolor[index], "color": "#fff"});
            }, function () {
                $(this).css({"background": "#fff", "color": "#b0b0b0"});
            })
        });
        flPointIdLi.each(function (index) {
            flPointIdLi.eq(index).click(function () {
                $(this).css({"background": WZcolor[index], "color": "#fff"}).siblings().css({
                    "background": "#fff",
                    "color": "#b0b0b0"
                });
                $("html,body").animate({scrollTop: floorTops[index]}, 300).dequeue();
            })
        });
        $(window).scroll(function () {
            CscrollTop = $(window).scrollTop();
            // 上下滚动
            if (CscrollTop > (floorTops[0] - 255) && Cwidth > 1200) {
                flPointId.show("fast");
                if (Cwidth > 1300) {
                    flPointId.css({"left": ((Cwidth - 1180) / 2 - 55) + "px"});
                } else if (Cwidth < 1300 && Cwidth > 1200) {
                    flPointId.css({"left": ((Cwidth - 1180) / 2 - 40) + "px"});
                }else{
                    flPointId.hide();
                }
            } else {
                flPointId.hide("fast");
            }
            for (var i = 0; i < flPointIdLi.length - 1; i++) {
                if (CscrollTop > (floorTops[i] - 255) && CscrollTop < (floorTops[i + 1]) - 255) {
                    flPointIdLi.eq(i).css({
                        "background": WZcolor[i],
                        "color": "#fff"
                    }).siblings().css({"background": "#fff", "color": "#b0b0b0"});
                } else if (CscrollTop > (floorTops[flPointIdLi.length - 1] - 255)) {
                    flPointIdLi.eq(flPointIdLi.length - 1).css({
                        "background": WZcolor[i + 1],
                        "color": "#fff"
                    }).siblings().css({"background": "#fff", "color": "#b0b0b0"});
                }
            }
        })
    }
    //在线质询
    if ($("div").hasClass("list_right")) {
        $(".headerListBot .list_right .consultation").hover(function () {
            $(this).find("span").fadeIn();
        }, function () {
            $(this).find("span").fadeOut().finish();
        })

    }
    //每日新品鼠标滑过上移10像素
    marginTop10(".newDays ul li");
    //获取未读信件数量
    getMsg_num();
});

//最右侧条
function leftSide() {
    var cWidth = document.documentElement.clientWidth;
    var cHeight = document.documentElement.clientHeight;
    $(".leftSide").css({"height": cHeight + "px"});
    $(".leftSideDiv").css({"height": cHeight + "px"});
    $(".rightCart").css({"height": cHeight + "px"});
    $(".rCartTab").css({"height": (cHeight - 83) + "px"});

//    if (cWidth < 1300) {
//        $(".leftSideDiv").css({"background": "none"});
//        $(".leftSideDiv .lssys").hide();
//        //$(".leftSideDiv .lsCart .cartSpan").find(".cartSpan").addClass("selCartSpan");
//        //$(".leftSide").animate({"right": "200px"}, 200);
//    } else {
//        $(".leftSideDiv").css({"background": "#383838"});
//        $(".leftSideDiv .lssys").show();
//    }

}

//客服
function service(){
	$("#serverId").popup({pWidth:"300",pHeight:"300",pTitle:"客户服务"});
}

//换一换
function refbandgoodslist(){
   var strTable="";
	$.ajax({
          type: "post",
          url: "/mall/goods!getEverydayGoodsList.action?num=4",
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
          	  for(var i=0;i<data.length;i++){
			      strTable+="<li><a href='/mall-goodsdetail-"+data[i].goods_id+".html' target='_blank' class='img'><img src='"+data[i].list_img+"' alt=''/></a><div class='title_bot'><p class='title_bot1'><a href='/mall-goodsdetail-"+data[i].goods_id+".html' target='_blank'>"+data[i].goods_name+"</a></p></div><div class='bot'><a href=''><span class='price'>&yen; <i class='newPrice'>"+data[i].sale_price+"</i></span></a><a href=''><span class='oldPrice'> &yen; <i>"+data[i].market_price+"</i></span></a></div></li>";
			    }  
          }	 
	}); 
	$("#everyday_goods").html(strTable);
}
//商品上上移动10像素
function marginTop10(moude) {
    $(moude).hover(function () {
        $(this).find(".img img").animate({marginTop: "-10px"}, 200).dequeue();
    }, function () {
        $(this).find(".img img").animate({marginTop: "0px"}, 300).dequeue();
    });
}
//获取未读信件数量
function getMsg_num(){
	$.ajax({
          type: "post",
          url: "/mall/memberuser!getMsgNum.action",
          datatype:"json",
          async:false,
          success: function(data){ 
        	  $(".msg_num_span").html(data);
          }	 
	}); 
}