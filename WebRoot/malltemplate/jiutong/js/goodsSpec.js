//初始化加载
$(document).ready(function() {
    //鼠标滑过规格触发事件
    var specstr_str_all = $("#specstr_str_all").val();
    $(".specstr >td > div >ul >li").hover(function() {
        var specstr = $(this).children("a").attr("id");
        var spec_id = $(this).parent().parent().parent().parent().parent().find("th").attr("id");
        //该规格值存在于商品属性中
        if (specstr_str_all.indexOf(specstr) > -1) {
            //遍历选中规格
            var specstr_str = "";
            $(".specstr >td > div >ul >li").each(function() {
                //判断选中规格是否与鼠标滑过规格同组
                if ($(this).parent().parent().parent().parent().parent().find("th").attr("id") != spec_id) {
                    if ($(this).attr("class") == "selli") {
                        var id = $(this).children("a").attr("id");
                        if (specstr_str == "") {
                            specstr_str = id;
                        } else {
                            specstr_str = specstr_str + "," + id;
                        }
                    }
                }
            });
            //拼凑规格串
            if (specstr_str == "") {
                specstr_str = specstr;
            } else {
                specstr_str = specstr_str + "," + specstr;
            }
            //根据规格获取库存
            var stock = getStock(specstr_str);
            if (eval(stock) <= 0) { //若该规格库存不足，禁止点击
                $(this).removeClass();
                 $(this).children("a").removeClass();
                   $(this).children("a").addClass("disable");
                $(this).addClass("disable");
            }
        } else {
            $(this).removeClass();
              $(this).children("a").removeClass();
             $(this).children("a").addClass("disable");
            $(this).addClass("disable");
           
        }
    },
    function() {
      $(this).removeClass("disable");
        $(this).children("a").removeClass("disable");
    });
    //选择商品规格(标签)
    $(".specstr >td > div > .selsize >li").click(function() {
        var css = $(this).attr("class");
        var id = $(this).children("a").attr("id");
        //禁止点击
        if (css == "disable") {
            return;
        }
        //规格串
        var specstr_str = "";
        if (css == "selli") {
            //$(this).removeClass("selli");
            //$(this).find("b").remove();
        } else {
            //该规格值存在于商品属性中
            if (specstr_str_all.indexOf(id) > -1) {
                $(this).parent("li").siblings().children("div").removeClass("selli");
                $(this).addClass("selli");
                  $(this).siblings().removeClass("selli");
                $(this).append("<b> </b>");
                $(this).parent("li").siblings().children("div").removeClass("selli").find("b").remove();
            } else {
                //$(this).removeClass();
            }
        }
        //遍历选中规格
        $(".specstr >td > div >ul >li").each(function() {
            if ($(this).attr("class") == "selli" ) {
                var id = $(this).children("a").attr("id");
                if (specstr_str == "") {
                    specstr_str = id;
                } else {
                    specstr_str = specstr_str + "," + id;
                }
            }
        });
        $("#spec_id_str").val(specstr_str);
        getGoodsAttr(specstr_str);
        checkBuyNum(buy_nums);
    });
    //选择商品规格(图片)
    $(".specstr >td > div > .selcolor > li").click(function() {
        var css = $(this).attr("class");
        var id = $(this).children("a").attr("id");
        //禁止点击
        if (css == "disable") {
            return;
        }
        //规格串
        var specstr_str = "";
        if (css == "selli") {
          //  $(this).children("a").children("i").removeClass();
            $(this).removeClass("selli");
            $(this).find("b").remove();
            //重置默认相册
           // replacePhoto($("#img_str").val());
        } else {
            $(this).addClass("selli");
              $(this).siblings().removeClass("selli");
            $(this).append("<b> </b>");
            $(this).parent("li").siblings().children("div").removeClass("selli").find("b").remove();
            //该规格值存在于商品属性中
            if (specstr_str_all.indexOf(id) > -1) {
               $(this).siblings().children("a").children("i").removeClass();
              $(this).children("a").children("i").addClass("checked");
                //替换放大镜相册
              //  var self_img_str = $(this).children("a").children("i").attr("img");
               // if (self_img_str != undefined && self_img_str != "") {
               //     replacePhoto(self_img_str);
              //  }

          } else {
               $(this).children("i").removeClass();
           }
        }
        //遍历选中规格
        $(".specstr >td > div >ul >li").each(function() {
            if ($(this).attr("class") == "selli" ) {
                var id = $(this).children("a").attr("id");
                if (specstr_str == "") {
                    specstr_str = id;
                } else {
                    specstr_str = specstr_str + "," + id;
                }
            }
        });
        $("#spec_id_str").val(specstr_str);
        getGoodsAttr(specstr_str);
        checkBuyNum(buy_nums);
    });
});

//根据规格值获取商品属性
function getGoodsAttr(specstr_str) {
    var sale_price = 0;
    var market_price = 0;
    var stock = 0;
    //市场价、销售价
    var min_market = 0,
    max_market = 0,
    min_sale = 0,
    max_sale = 0;
    //获取隐藏域中的json串
    var arr_specstr_attr = $("#specstr_attr").val();
    var dataObj = eval("(" + arr_specstr_attr + ")");
    //索引
    var index = 1;
    $.each(dataObj,
    function(idx, item) {
        var specstrs = specstr_str.split(",");
        var flag = "";
        if (specstrs != "") {
            for (var i = 0; i < specstrs.length; i++) {
                if (flag == "") {
                    flag = "item.specstr.indexOf(" + specstrs[i] + ") > -1";
                } else {
                    flag = flag + " && item.specstr.indexOf(" + specstrs[i] + ") > -1";
                }
            }
        }
        if (eval(flag) || flag == "") {
            //统计库存
            stock += eval(item.stock);
            if (index == 1) {
                min_market = item.market_price;
                min_sale = item.sale_price;
            }
            max_market = item.market_price;
            if (max_market < item.market_price) {
                max_market = item.market_price;
            }
            max_sale = item.sale_price;
            if (max_sale < item.sale_price) {
                max_sale = item.sale_price;
            }
            if (min_market != 0 && max_market != 0 && min_market != max_market) {
                market_price = min_market + "-" + max_market;
            } else {
                market_price = min_market;
            }
            if (min_sale != 0 && max_sale != 0 && min_sale != max_sale) {
                sale_price = min_sale + "-" + max_sale;
            } else {
                sale_price = min_sale;
            }
            index++;
        }
    }) 
    $("#market_price_str").html(market_price);
    $("#sale_price").html(sale_price);
    $("#price").val(sale_price);
    $("#cart_goods_sale_price").val(sale_price);
    $("#sale_price_str").val(sale_price);
    $("#total_stock").html(stock);
    $("#temp_stock").val(stock);
}

//根据规格值获取商品库存
function getStock(specstr_str) {
    var stock = 0;
    //获取隐藏域中的json串
    var arr_specstr_attr = $("#specstr_attr").val();
    var dataObj = eval("(" + arr_specstr_attr + ")");
    $.each(dataObj,
    function(idx, item) {
        var specstrs = specstr_str.split(",");
        var flag = "";
        for (var i = 0; i < specstrs.length; i++) {
            if (flag == "") {
                flag = "item.specstr.indexOf(" + specstrs[i] + ") > -1";
            } else {
                flag = flag + " && item.specstr.indexOf(" + specstrs[i] + ") > -1";
            }
        }
        if (eval(flag)) {
            //统计库存
            stock += eval(item.stock);
        }
    })
return stock;
}