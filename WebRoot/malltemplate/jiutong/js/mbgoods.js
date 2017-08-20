$(document).ready(function() {
	var isBuySelf=$("#isBuySelf").val();
		var is_check=false;
		if(isBuySelf=='0'){
		  	jAlert("卖家不能购买自己的商品!","系统提示");
		}
		//跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/webapp';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	
		
	/*商品详情*/
	if($("div").hasClass("proDetail")){
		$("#proDeId").setTab("proDeh2","tabDiv","selli");
		
		$(".evah2 span").click(function(){
			$(this).addClass("selSapn");
			$(this).siblings().removeClass("selSapn");
		})
	}
	
	
	var goods_id = $("#goods_id").val();
		//累计评价插件
		$('#evaluMain').mall({
			    actionName:'/webapp/goods!goodsEvalComment.action?goods_id='+goods_id+'&e_type=0',
			 	columnModel:ColumnEval,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人评价",
			 	tabletitle:"累计评价"
		});
		
		
		 //商品评论
		$(".evah2 span").click(function(){
			var g_eval=$(this).attr("g_eval");
			//累计评价插件
			$('#evaluMain').mall({
				    actionName:'/mall/goods!goodsEvalComment.action?goods_id='+goods_id+'&g_eval='+g_eval+'&e_type=0',
				 	columnModel:ColumnEval,
				 	pageSize:10,
				 	otherMethod:"",
				 	nullData:"还未有人评价",
				 	tabletitle:"累计评价"
			});
		})
	//限时时间倒计时
    var difftime = $("#difftime").val();
    if(difftime !="undefined"){ 
     groupshowtime(difftime);
    }
    //取消详细内容的宽度
     $("#phone_detai_id").find("table").removeAttr('width');
     $("#phone_detai_id").find("td").removeAttr('width');
     $("#phone_detai_id").find("td").css('width','auto');
})

//加
function add(){   
	if(checkBuyNum('0')){
	var num = $("#buy_nums").val();
	num=eval(num)+1;
	$("#buy_nums").val(num);
	//计算运费
//	getShipPrice();
	checkBuyNum('0'); 
		var limit_num = $("#limit_num").val();
		var is_limit = $("#is_limit").val ();
		if(is_limit!=null&&is_limit=='0'){
		if(num > limit_num){
			$("#buy_nums").val(limit_num); 
	        $("#buy_nums").focus();
	        jNotify("超过该商品限购量!"); 
	        return false;
		}
		}
	}
}
   //减
function sub(){   
 	if(checkBuyNum('0')){
	var num = $("#buy_nums").val();
	if(eval(num) > 1){
		num=eval(num)-1;
		$("#buy_nums").val(num);
	}
	 //计算运费
	//getShipPrice();
	is_check=true;
	checkBuyNum('0'); 
	var limit_num = $("#limit_num").val();
	var is_limit = $("#is_limit").val ();
	if(is_limit!=null&&is_limit=='0'){
	if(num > limit_num){
		$("#buy_nums").val(limit_num); 
        $("#buy_nums").focus();
        jNotify("超过该商品限购量!"); 
        return false;
	}
	}
	}
}
//验证只能输入正整数 >=0且不能大于库存
function checkBuyNum(i)
{
	var obj=$("#buy_nums");
    var num_value=$("#buy_nums").val();
    var re =/^(([1-9]\d*))$/;
	if (!re.test(num_value)){
        obj.val(1);
        obj.focus();
        jNotify("购买数量只能大于0的整数,请正确输入!"); 
        return false;
	 }
	 
	var total_stock1 = $("#active_MaxBuy").val();
	if(total_stock1!=null){
		if(eval(num_value) > eval(total_stock1)){
			obj.val(total_stock1); 
	        obj.focus();
	        jNotify("该商品为活动商品，同一个会员不能超过"+total_stock1+"!"); 
	        return false;
		}
	}

	var total_stock = eval($("#total_stock").html());
	if(num_value > total_stock){
				if(i=='1'){
					var limit_num = $("#limit_num").val();
					var is_limit = $("#is_limit").val ();
					if(is_limit!=null&&is_limit=='0'){
					if((limit_num - num_value)<0){
						obj.val(limit_num); 
      				  obj.focus();
				        jNotify("超过该商品限购量!"); 
				        return false;
					}
					}
			}
		obj.val(total_stock); 
        obj.focus();
        jNotify("购买数量超过库存量!"); 
        return false;
	}
		if(i=='1'){
		var limit_num = $("#limit_num").val();
			var is_limit = $("#is_limit").val ();
			if(is_limit!=null&&is_limit=='0'){
			if((limit_num - num_value)<0){
				obj.val(limit_num); 
      				  obj.focus();
		        jNotify("超过该商品限购量!"); 
		        return false;
			}
			}
	}
	return true;
}
//用于印证输入框库存大小判断
function BuyNumCheck(){
	var obj=$("#buy_nums");
    var num_value=$("#buy_nums").val();
    var re =/^([1-9]*)$/;
    var reg =/[0-9]/;
    var str="";
    if (!re.test(num_value)){
		 var len=num_value.length;
		 for( i = 0; i < len; i ++ ){
	    	var char=num_value.charAt(i);
		 	if(i==0){
		 		if(char!="0"&&reg.test(char)){
		 			str+=char;
		 		}
		 	}else{
		 		if(re.test(char)){
			 		str+=char;
		 		}
		 	}
		 	//判断输入长度为9位
		 	if(str.length=="9"){
		 		break;
		 	}
		  }
		  if(str.length=="0"){
		 		str="1"
		  }
		  obj.val(str);
	}
	 
	var total_stock1 = $("#active_MaxBuy").val();
	if(total_stock1!=null){
		if(eval(num_value) > eval(total_stock1)){
			obj.val(total_stock1); 
	        obj.focus();
	        jNotify("该商品为活动商品，同一个会员不能超过"+total_stock1+"!"); 
		}
	}
	
	var limit_num = $("#limit_num").val();
	var is_limit = $("#is_limit").val ();
	if(is_limit!=null&&is_limit=='0'){
	if(num_value > limit_num){
		obj.val(limit_num); 
        obj.focus();
        jNotify("超过该商品限购量!"); 
        return false;
	}
	}
	var total_stock = eval($("#total_stock").html());
	if(num_value > total_stock){
		obj.val(total_stock); 
        obj.focus();
        jNotify("购买数量超过库存量!"); 
	}
}

//积分购买
function integralBuy(){
  $("#integral_state").val("1");
  buyNow();
}


//实物商品购买
function buyNow(){     
	//统计出选中规格值个数
	var checkedNums = 0;
	//商品属性名称
	var spec_name_str = "";
	//商品属性ID
	var spec_id_str = "";
	$(".specstr >td > div >ul >li >a").each(function(){

  		if($(this).parent().attr("class") == "selli" || $(this).children("i").attr("class") == "checked"){
  			//判断是标签属性还是图片属性
  			if($(this).parent().parent().parent().attr("class")  == "selsize"){//标签属性
  				var current_spec_name =$(this).parent().parent().parent().parent().parent().parent().find("th") .html()+":"+$(this).html();
  				var current_spec_id = $(this).attr("id");
  				if(spec_name_str == "" && spec_id_str ==""){
  					spec_name_str = current_spec_name;
  					spec_id_str = current_spec_id;
  				}else{
  					spec_name_str = spec_name_str +"<br>"+current_spec_name;
  					spec_id_str = spec_id_str+":"+current_spec_id;
  				}
  			}else{
  				var current_spec_name = $(this).parent().parent().parent().parent().parent().parent().find("th") .html()+":"+$(this).attr("title");
  				var current_spec_id = $(this).attr("id");
				if(spec_name_str == "" && spec_id_str == ""){
  					spec_name_str = current_spec_name;
  					spec_id_str = current_spec_id;
  				}else{
  					spec_name_str = spec_name_str +"<br>"+current_spec_name;
  					spec_id_str = spec_id_str+":"+current_spec_id;
  				}
  			}
  			$("#spec_name_str").val(spec_name_str);
  			$("#spec_id_str").val(spec_id_str);
  			checkedNums++;
  		}
  	});
	//自定义规格个数
	var count = $("#spec_nums").val();
	var boolean=checkBuyNum('0');
	if(!boolean){
	}else if(checkedNums != count){
		//$("#error_span").show();
		//$(".selproul").addClass("redborder");
	}else{
		//$("#error_span").hide();
		//$(".selproul").removeClass("redborder");
		$("#gotourl").val(window.location.href);
		$("#buy").submit();
	}
}

//加入购物车
function addCart(cartpos){
	//统计出选中规格值个数
	var checkedNums = 0;
	//商品属性名称串(用于购物车)
	var cart_sepc_name = "";
	//商品属性ID串(用于购物车)
	var cart_sepc_id = "";
	
	$(".specstr >td > div >ul >li>a").each(function(){
  		if($(this).children("i").attr("class") == "checked"||$(this).parent().attr("class") == "selli"){
  			//判断是标签属性还是图片属性
  			if($(this).parent().parent().parent().attr("class") == "selsize"){//标签属性
  				var current_spec_name = $(this).parent().parent().parent().parent().parent().parent().find("th") .html()+":"+$(this).html();
  				var current_spec_id = $(this).attr("id");
  				if(cart_sepc_name == "" && cart_sepc_id ==""){
  					cart_sepc_name = current_spec_name;
  					cart_sepc_id = current_spec_id;
  				}else{
  					cart_sepc_name = cart_sepc_name +"<br>"+current_spec_name;
  					cart_sepc_id = cart_sepc_id+":"+current_spec_id;
  				}
  			}else{
  				
  				var current_spec_name = $(this).parent().parent().parent().parent().parent().parent().find("th") .html()+":"+$(this).attr("title");
  				var current_spec_id = $(this).attr("id");
				if(cart_sepc_name == "" && cart_sepc_id == ""){
  					cart_sepc_name = current_spec_name;
  					cart_sepc_id = current_spec_id;
  				}else{
  					cart_sepc_name = cart_sepc_name +"<br>"+current_spec_name;
  					cart_sepc_id = cart_sepc_id+":"+current_spec_id;
  				}
  			}
  			$("#cart_sepc_name").val(cart_sepc_name);
  			$("#cart_sepc_id").val(cart_sepc_id);
  			checkedNums++;
  				
  		}
  	});
  	
  	var goods_stock = eval($("#num_id").val());
  	
	if(1 > goods_stock){
        jNotify("商品没有库存!"); 
	}else{
		//自定义规格个数
		var count = $("#spec_nums").val();
		if(checkedNums != count){
		//	$("#error_span").show();
		//	$(".selproul").addClass("redborder");
		}else{
			//$("#error_span").hide();
		//	$(".selproul").removeClass("redborder");
			addCookie(cartpos);
		//	$("#cartDiv").show();
		}
	}
	
   }


//商品累计评价
var ColumnEval= [
				{
					header : '',
					headerIndex : '',
					width:"100%",
					cpos:"left",
					tphtml:"<div class='evaCont'> <table width='100%' cellpadding='0' cellspacing='0'> <tr><td colspan='2'>$</td></tr><tr><td>|$</td><td align='right'>|$</td></tr><tr><td colspan='2' class='reply'>商家回复：|$</td></tr></table> </div>",
					tphtmlval:"g_comment|buy_cust_name|eval_date|explan_content"
				}];
