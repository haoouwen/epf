$(document).ready(function(){
	var is_illegal=$("#is_illegal").val();
	var goods_id=$("#goods_id_str").val();
	if(is_illegal!=null&&is_illegal=='1'){
		 jNotify("非法操作！请重新选择购买数量!"); 
	}
     var isBuySelf=$("#isBuySelf").val();
	 if(isBuySelf=='0'){
		  	jAlert("卖家不能购买自己的商品!","系统提示");
		}
		
	//跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/mall';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	   updateEndTimes();
	/*返回首页*/
	$("#returnTopId").floatdiv("float-right-bottom");
	/*分类显示和隐藏*/
	$('.classcont').mousemove(function(){
		$(this).find('#classlistId').show();
		$(this).find('h3,p').addClass('lclaconthover');
	});
	$('.classcont').mouseleave(function(){
		$(this).find('#classlistId').hide();
		$(this).find('h3,p').removeClass('lclaconthover');
	});
	$(".lclacont:odd").addClass("addclabg");
	$("#classMainId").hide();
	$("#h2classId").hover(
		function(){
			$("#classMainId").show();
			$(this).removeClass("h2down");
			$(this).addClass("h2up");
			$("#classMainId").hover(
			  function(){
				  $("#classMainId").show();
				  $("#h2classId").removeClass("h2down");
			      $("#h2classId").addClass("h2up");
			  },
			  function(){
				  $("#classMainId").hide();
				  $("#h2classId").removeClass("h2up");
				  $("#h2classId").addClass("h2down");
			  }
			);
		},
		function(){
			$("#classMainId").hide();
			$(this).removeClass("h2up");
			$(this).addClass("h2down");
		}
	);
	/*顶部全部分类*/
	$("#topclaId").hover(
		function(){
			$("#topclaId").removeClass("atopclass");
			$("#topclaId").addClass("ahtopclass");
			$("#topclacontId").show();
			$("#topclacontId").hover(
			   function(){
				   $("#topclacontId").show();
				   $("#topclaId").removeClass("atopclass");
				   $("#topclaId").addClass("ahtopclass");
				},
			    function(){
					$("#topclacontId").hide();
					$("#topclaId").removeClass("ahtopclass");
					$("#topclaId").addClass("atopclass");	
				}
			)
		},
		function(){
			$("#topclaId").removeClass("ahtopclass");
			$("#topclaId").addClass("atopclass");
			$("#topclacontId").hide();
		}
	)
	
	/*推荐商品*/
	$('.groupBuyList>ul>li').hover(
		function(){
			$(this).addClass("addborder");
		},
		function(){
			$(this).removeClass("addborder");
		}
	);

   //获取当前样式状态
   var status= $("#cssstatus").val();
   if(status == '1'){
    $("#default").addClass('tgnselmr');
    $("#paynum").addClass('tgselxl');
    $("#price").addClass('tgnseljg');
   }else if(status == '2'){
     $("#default").addClass('tgnselmr');
     $("#paynum").addClass('tgnselxl');
     $("#price").addClass('tgseljg');
   }else{
     $("#default").addClass('tgselmr');
     $("#paynum").addClass('tgnselxl');
     $("#price").addClass('tgnseljg');
   }
   
   /*评价*/
	if($("div").hasClass("groupDetail")){
		
		$("#grouph2Id").floatDiv("floatTop");
		
		if($("div").hasClass("evaluation")){
			//描点
		    tracingPoint();
		    //商品评论
			$("#evahLiId li").click(function(){
				$(this).addClass("selli").siblings().removeClass("selli");
			})
		}
	}
   	//累计评价插件
		$('#evaluMain').mall({
			    actionName:'/mall/goods!goodsEvalComment.action?goods_id='+goods_id+'&e_type=1',
			 	columnModel:ColumnEval,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人评价",
			 	tabletitle:"累计评价"
		});
   /*评价*/
	if($("div").hasClass("groupDiv")){
		//浮动
		$("#grouph2Id").floatDiv("floatTop");
		//描点
		tracingPoint();
		//商品评论
		$("#evahLiId li").click(function(){
			$(this).addClass("selli").siblings().removeClass("selli");
			var g_eval=$(this).attr("g_eval");
			//累计评价插件
		$('#evaluMain').mall({
			    actionName:'/mall/goods!goodsEvalComment.action?goods_id='+goods_id+'&g_eval='+g_eval+'&e_type=1',
			 	columnModel:ColumnEval,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人评价",
			 	tabletitle:"累计评价"
		});
		})
   }
   
    //预售时间倒计时
    //var difftime = $("#difftime").val(); 
    //groupshowtime(difftime);
});
/*按销量排序*/
function grouptype(type){
  if(type=='num'){
    
    $("#cssstatus").val("1");
    $("#searchtype").val('paynum');
    $("#groupgoods").submit();
   
  }else if(type=='price'){
   $("#cssstatus").val("2");
    $("#searchtype").val('price');
    $("#groupgoods").submit();
   
  }else{
    $("#cssstatus").val("0");
    $("#searchtype").val('default');
    $("#groupgoods").submit();
    
  }
}

/*预售分类筛选*/
function groupattr(cat_attr){
$("#cat_attr").val(cat_attr);
$("#groupgoods").submit();
}
/*搜索预售商品*/
function searchgroup(){
 var str = $("#groupname").val();
 $("#searchname").val(str);
 $("#groupgoods").submit();
}
/*清空session值*/
function directsub(){
 var is_virtual = $("#is_virtual").val();
//统计出选中规格值个数
	var checkedNums = 0;
	//商品属性名称
	var spec_name_str = "";
	//商品属性ID
	var spec_id_str = "";
//	$(".specstr > p > a").each(function(){
//  		if($(this).attr("class") == "checked" || $(this).children("i").attr("class") == "checked"){
//  			//判断是标签属性还是图片属性
//  			if($(this).parent().attr("class") == "selsize"){//标签属性
//  				var current_spec_name = $(this).parent().parent().children("span").html()+":"+$(this).html();
//  				var current_spec_id = $(this).attr("id");
//  				if(spec_name_str == "" && spec_id_str ==""){
//  					spec_name_str = current_spec_name;
//  					spec_id_str = current_spec_id;
//  				}else{
//  					spec_name_str = spec_name_str +"<br>"+current_spec_name;
//  					spec_id_str = spec_id_str+":"+current_spec_id;
//  				}
//  			}else{
//  				var current_spec_name = $(this).parent().parent().children("span").html()+":"+$(this).attr("title");
//  				var current_spec_id = $(this).attr("id");
//				if(spec_name_str == "" && spec_id_str == ""){
//  					spec_name_str = current_spec_name;
//  					spec_id_str = current_spec_id;
//  				}else{
//  					spec_name_str = spec_name_str +"<br>"+current_spec_name;
//  					spec_id_str = spec_id_str+":"+current_spec_id;
//  				}
//  			}
//  			$("#spec_name_str").val(spec_name_str);
//  			$("#spec_id_str").val(spec_id_str);
//  			checkedNums++;
//  		}
//  	});
//	//自定义规格个数
//	var count = $("#spec_nums").val();
//	var boolean=checkBuyNum();
//	if(!boolean){
//	}else if(checkedNums != count){
//		$("#error_span").show();
//		$(".selproul").addClass("redborder");
//	}else{
//		$("#error_span").hide();
//		$(".selproul").removeClass("redborder");
		if(is_virtual=='1'){
		 $("#direct").submit();
		}else{
		 $("#vgroupbuy").submit();
		}
	//}
}
//加
function add(){    
	var num = $("#buy_nums").val();
	num=eval(num)+1;
	$("#buy_nums").val(num);
	//计算运费
//	getShipPrice();
	if(!checkBuyNum('0')){
		 return false;
	}
	var limit_num = $("#limit_num").val();
		var is_limit = $("#is_limit").val ();
		if(is_limit!=null&&is_limit=='0'){
		if(num > limit_num){
			$("#buy_nums").val(limit_num); 
			$("#order_num_str").val(limit_num);
	        $("#buy_nums").focus();
	        jNotify("超过该商品限购量!"); 
	        return false;
		}
		}
			$("#buy_nums").val(num);
		$("#order_num_str").val(num);
}
   //减
function sub(){     	
	var num = $("#buy_nums").val();
	if(eval(num) > 1){
		num=eval(num)-1;
		$("#buy_nums").val(num);
		var limit_num = $("#limit_num").val();
	var is_limit = $("#is_limit").val ();
	if(is_limit!=null&&is_limit=='0'){
	if(num > limit_num){
		$("#buy_nums").val(limit_num); 
		$("#order_num_str").val(num);
        $("#buy_nums").focus();
        jNotify("超过该商品限购量!"); 
        return false;
	}
	}
	$("#buy_nums").val(num);
	$("#order_num_str").val(num);
	}
	//计算运费
//getShipPrice();
	checkBuyNum('0');
}
//验证只能输入正整数 >=0且不能大于库存
function checkBuyNum(i){
	obj=$("#buy_nums");
    var num_value=obj.val();
    var reg = new RegExp("^[0-9]*$");
    if(num_value=='0'){
       $("#buy_nums").val("1");
	        jNotify("该文本框只能输入非0整数,请正确输入!"); 
	        return false;
    }
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(num_value))
	  {    
	       $("#buy_nums").val("1");
	        jNotify("该文本框只能输入整数,请正确输入!"); 
	        return false;
	  }
		if(i=='1'){
			var limit_num = $("#limit_num").val();
			var is_limit = $("#is_limit").val ();
			if(is_limit!=null&&is_limit=='0'){
				if((num_value- limit_num)>0){
					$("#buy_nums").val(limit_num); 
			         obj.focus();
			        jNotify("超过该商品限购量!"); 
			        return false;
				}
			}
		}
		var total_stock = eval($("#total_stock").html());
		
			if((num_value - total_stock)>0){
				 if(i=='1'){
						var limit_num = $("#limit_num").val();
						var is_limit = $("#is_limit").val ();
						if(is_limit!=null&&is_limit=='0'){
							if((num_value - limit_num)>0){
								$("#buy_nums").val(limit_num); 
						         obj.focus();
						        jNotify("超过该商品限购量!"); 
						        return false;
							}
						}
					}
				obj.val(total_stock); 
				$("#order_num_str").val(total_stock);
		        obj.focus();
		        jNotify("购买数量超过库存量!"); 
		        return false;
			}
	
	
	$("#buy_nums").val(obj.val());
	$("#order_num_str").val(obj.val());
	return true;
}

//计算运费（商品详细页）
function getShipPrice(){
	var ship_id = $("#ship_id").val();//运费模板id
	var buy_num = $("#buy_nums").val();//购买数量
	var volume = $("#volume").val();//商品体积
	var logsweight = $("#logsweight").val();//商品重量
	var end_area = $("#sareaid").val();//到达地址
	var dataUrl="/mall/goods!getShipPrice.action?ship_id="+ship_id+"&buy_num="+buy_num+"&volume="+volume+"&logsweight="+logsweight+"&end_area="+end_area;
	//是否免运费 0：免运费
        var is_ship = $("#is_ship").val();
        //获取默认运费
        if(is_ship != 0){
        	$.ajax({
		        type: "post",
		        url: dataUrl,
		        datatype:"json",
		        async:false,
		        success: function(data){ 
		        	data=eval("("+data+")");
		        	//配送方式：配送方式+运费
		        	var shipStr = "";
		        	for(var i = 0; i < data.length; i++){
						if(shipStr != ""){
							shipStr = shipStr + "," + data[i].ship_name+":"+data[i].ship_price +"元";
						}else{
							shipStr = data[i].ship_name +":"+ data[i].ship_price +"元";
						}
					}
		        	var subShipStr = shipStr;
			        if(shipStr.length > 20){
			        	subShipStr = shipStr.substring(0,19) + "...";
			        	$("#shipSpan").attr("title",shipStr);
			        	$("#shipSpan").attr("alt",shipStr);
			        }
			        $("#shipSpan").html(subShipStr);
		        }
		    });
        }
}
		/*预售规则*/
		function showGZ(){
			$("#ysimg").popup({p_width:"550", p_height:"320", pop_title:"请先阅读预售规则"});
		}
//	 	function hideGZ(){
//	 	 setTimeout('hidden()',15000);
//	 	}
//		function hidden(){
//			$("#ysimg").ccover();
//		}
	function updateEndTimes() {
    var now ;
    var dataUrl="/base!nowTime.action";
								$.ajax({
							        type: "post",
							        url:dataUrl,
							        datatype:"json",
							        async:false,
							        success: function(data){ 
							        	now=data;
							        now= eval('new Date(' + now.replace(/\d+(?=-[^-]+$)/,
							        function(a) {
							            return parseInt(a, 10) - 1;
							        }).match(/\d+/g) + ')');
							       now = now.getTime();
							        }
							    });

    $(".times").each(function(i) {
        var endDate = this.getAttribute("endTime");
        var endDate1 = eval('new Date(' + endDate.replace(/\d+(?=-[^-]+$)/,
        function(a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');
        var endTime = endDate1.getTime();
        var lag = (endTime - now) / 1000;
        if (lag > 0) {
            var second = Math.floor(lag % 60);
            var minite = Math.floor((lag / 60) % 60);
            var hour = Math.floor((lag / 3600) % 24);
            var day = Math.floor((lag / 3600) / 24);
            var flag = this.getAttribute("flag");
            var phtml = '<b>' + day + '</b>天<b>' + hour + '</b>时<b>' + minite + '</b>分<b>' + second + '</b>秒';
            if (flag == null || flag == '1') {
                phtml = '剩余时间: <b>' + day + '</b>天<b>' + hour + '</b>时<b>' + minite + '</b>分<b>' + second + '</b>秒';
            }
            $(this).find(".pTimes").html(phtml);
        } else {
        	$(this).find(".pTimes").html("预售已结束,请等待下一批预售");
        }
    });
    setTimeout("updateEndTimes()", 1000);
}



//描点平滑定位
function tracingPoint(){
	
	var fPos = $("#groupDContId").offset().top-50,
		sPos = $("#evaluaId").offset().top-60;
	
	$("#profId").addClass("selli");
	
	$("#profId").click(function(){
		$("html,body").animate({scrollTop:fPos}, 500);
	})
	$("#prosId").click(function(){
		$("html,body").animate({scrollTop:sPos}, 500);
	})
	
	$(window).scroll(function(){
		
		var scrollPos= $("#prosId").offset().top;
		
		if(scrollPos >= sPos) {
			
			$("#prosId").addClass("selli").siblings().removeClass("selli");
			
		}else{
			
		    $("#profId").addClass("selli").siblings().removeClass("selli");
		}
	});
	
}



//商品累计评价
var ColumnEval = [
				{
					header : '',
					headerIndex : '',
					width:"10%",
					cpos:"left",
					tphtml:"<div><img src='$'></div><p>|$</p>",
					tphtmlval:"logo_path|buy_cust_name"
				},
                {
					header : '',
					headerIndex : '',
					width:"90%",
					cpos:"left",
					tphtml:"<div class='revacont'><div class='jtback'></div> <div class='revtab'><table> <tr><th>评论：</th><td> $</td></tr> <tr><th>用户晒图：</th><td>|$</td></tr> <tr><th>评论日期：</th><td>|$</td></tr><tr><th>回复：</th><td class='hftd'>|$</td></tr></table></div></div>",
					tphtmlval:"g_comment|share_pic|eval_date|explan_content"	
				}];
