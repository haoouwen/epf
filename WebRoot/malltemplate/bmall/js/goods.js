//初始化加载
$(document).ready(function(){
		var isBuySelf=$("#isBuySelf").val();
		var is_check=false;
		if(isBuySelf=='0'){
		  	jAlert("自己不能购买自己的商品!","系统提示");
		}
		//跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/mall';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
		addHistory();//插入最近浏览记录
        //获取默认运费
       // getShipPrice();
		//tab最后一个li增加右边框
		$("#goodsduulId > li").last().addClass("lastli");
		var goods_id = $("#goods_id").val();
		//累计评价插件
		$('#evaluMain').mall({
			    actionName:'/mall/goods!goodsEvalComment.action?goods_id='+goods_id+'&e_type=0',
			 	columnModel:ColumnEval,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人评价",
			 	tabletitle:"累计评价"
		});
		//月成交记录插件
		$('#monthSale').mall({
			    actionName:'/mall/goods!goodsMonthSaleComment.action?goods_id='+goods_id,
			 	columnModel:ColumnMonthDeal,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人购买",
			 	tabletitle:"月成交记录"
		});
		//商品咨询记录插件
		$('#consult').mall({
			    actionName:'/mall/goods!goodsConsultComment.action?goods_id='+goods_id,
			 	columnModel:ColumnConsultation,
			 	pageSize:10,
			 	otherMethod:"",
			 	nullData:"还未有人咨询",
			 	tabletitle:"商品咨询"
		});
	addimgsrc();
    //限时时间倒计时
    var difftime = $("#difftime").val();
    if(difftime !="undefined"){ 
     groupshowtime(difftime);
    }
});

//查询商品评价
function clickRadio(goods_id,g_eval,is_img){
	//累计评价插件
	$('#evaluMain').mall({
		    actionName:'/mall/goods!goodsEvalComment.action?goods_id='+goods_id+'&g_eval='+g_eval+'&is_img='+is_img+'&e_type=0',
		 	columnModel:ColumnEval,
		 	pageSize:10,
		 	otherMethod:"",
		 	nullData:"还未有人评价",
		 	tabletitle:"累计评价"
	});
	addimgsrc();
}

//查询咨询
function clickConsult(goods_id,c_type){
	//商品咨询记录插件
	$('#consult').mall({
		    actionName:'/mall/goods!goodsConsultComment.action?goods_id='+goods_id+"&c_type="+c_type,
		 	columnModel:ColumnConsultation,
		 	pageSize:10,
		 	otherMethod:"",
		 	nullData:"还未有人咨询",
		 	tabletitle:"商品咨询"
	});
}

//商品评价获取图片，加载src
function addimgsrc(){
 $(".imgsrc").each(function(){
   if($(this).html()==null ||$(this).html()=='null'||$(this).html()==""){	
    	$(this).parent().hide();
   }
   var srcs=$(this).html().split('##');
   var fstc="";
   if(srcs!=null){
     for(i=0;i<srcs.length;i++){
        fstc+="<a target='_blank' href='"+srcs[i]+"'><img src='"+srcs[i]+"' width='60px' height='60px'/></a>&nbsp;&nbsp;";
     }
   }
   $(this).html(fstc);
});
}
//根据选中规格替换相册	
function replacePhoto(imgStr){
	var first_img_html = ""
  	var li_img_html = "";
  	var self_img = imgStr.split("|");
  	if(self_img.length == 1){
  		self_img = imgStr.split(",");
  	}
  	//获取相册中的第一张图片作为大图显示
  	first_img_html = "<img jqimg='"+self_img[0]+"' src='"+self_img[0]+"'>"
	for(var i = 0;i < self_img.length;i++){
		//循环拼接小图
		li_img_html += "<li><img src='"+self_img[i]+"'</li>";
	}
	//替换大图
	$("#spec-n1").children("img").remove();
	$("#spec-n1").append(first_img_html);
	//替换小图
	$(".listul").children("li").remove();
	$(".listul").append(li_img_html);
	//渲染放大镜
	loadGoodsImage();
}	
//判断库存（暂时没用）
function checkStock() {
	//判断库存
    var stock = eval($("#total_stock").html());
    if(stock <= 0){
    	$(".cartbutp > a").css({cursor:"not-allowed"});
    	$(".cartbutp > a").removeAttr("onclick");
    }else{
    	$(".cartbutp").show();
    }
}	
	
	
 //记录最近浏览过的商品  
function addHistory() { 
	    //最新访问的商品ID  
	    var history_goods_id = $("#goods_id").val(); 
	    //最近浏览个数
	    var history_goods_cookieNum=1;
	    //设置cookie保存的浏览记录的条数                         
	    var N = 5;  
        //判断是否存在cookie  
        if ($.cookie("hgcn") == null||$.cookie("hgcn")=="") //cookie 不存在  
        {  
   			    var historygoodsList = new Array();
   				var historygoods =new Object();
   				history_goods_cookieIndex=1;
				historygoods.hi= history_goods_id;
				historygoods.hn = history_goods_cookieIndex;
				historygoodsList[0]=historygoods;
				var json = JSON.stringify(historygoodsList);
				$.cookie("hglt",json, { expires: 7, path: '/' });
				$.cookie("hgcn",1, { expires: 7, path: '/' });
        }else{  
            //从cookie获取索引
	    	history_goods_cookieNum = parseInt($.cookie("hgcn"));
			var historygoodsList=JSON.parse($.cookie("hglt"));
	    	//统计相同商品cookie个数
	    	var samecount = 0;
	    	for(var i = 0; i< history_goods_cookieNum; i++){
	    	    var goodsFlag=historygoodsList[i];
		    	var historyId =goodsFlag.hi;
				  if(historyId == history_goods_id){
				  	 samecount++;
				  }
	    	}
	    	//说明cookie中不存在该商品
	    	if(samecount == 0){
	    	   //获取对象中的最大索引
	    	   var historygoodsList3=JSON.parse($.cookie("hglt"));
	    	   var goodsFlag2=historygoodsList3[history_goods_cookieNum-1];
	    	   var h_index=goodsFlag2.hn+1;
		    	//cookie个数自增
        		history_goods_cookieNum++;
        		var historygoods =new Object();
				historygoods.hi = history_goods_id;
				historygoods.hn = h_index;
	   			historygoodsList[history_goods_cookieNum-1]=historygoods;
	   			var json = JSON.stringify(historygoodsList);
	            //cookie个数更改
	   			$.cookie("hglt",json, { expires: 7, path: '/' });
		        $.cookie("hgcn",history_goods_cookieNum, { expires: 7, path: '/' });
		        if(history_goods_cookieNum>N){
		            //控制浏览历史cookie的个数为5个
		            history_goods_cookieNum=history_goods_cookieNum-1;
					var historygoodsList2=JSON.parse($.cookie("hglt"));
					//移除第一个对象
			    	historygoodsList2.splice(0,1);
			    	var json2 = JSON.stringify(historygoodsList2);
			    	 //cookie个数更改
		   			$.cookie("hglt",json2, { expires: 7, path: '/' });
			        $.cookie("hgcn",history_goods_cookieNum, { expires: 7, path: '/' });
		        }
	    	}
	    }
 }
 //记录最近浏览过的商品存入json里面
function addHistory_old() {
//最新访问的商品ID  
  var history_goods_id = $("#goods_id").val(); 
  //商品名称
  var history_goods_name = $("#goods_name_str").val(); 
  if(history_goods_name.length > 30){
  	history_goods_name = history_goods_name.substring(0,29) + "...";
  }
  //商品图片
  var history_list_img = $("#singleImg").val(); 
  //商品价格
  var history_sale_price = $("#min_sale_price").val(); 
     var history_market_price = $("#min_market_price").val(); 
  //最近浏览个数
  var history_goods_cookieNum=1;
  //cookie索引
  var history_goods_cookieIndex=1;
  //设置cookie保存的浏览记录的条数                         
  var N = 10;  
  //判断是否存在cookie  
  if ($.cookie("history_goods_cookieNum") == null) //cookie 不存在  
  {  
      //创建新的cookie,保存浏览记录  
      $.cookie("history_goods_id" + history_goods_cookieNum, history_goods_id, { expires: 7, path: '/' });  
      $.cookie("history_goods_name" + history_goods_cookieNum, history_goods_name, { expires: 7, path: '/' });  
      $.cookie("history_list_img" + history_goods_cookieNum, history_list_img, { expires: 7, path: '/' });  
      $.cookie("history_sale_price" + history_goods_cookieNum, history_sale_price, { expires: 7, path: '/' });  
      $.cookie("history_market_price" + history_goods_cookieNum, history_market_price, { expires: 7, path: '/' });  
      //cookie索引
	$.cookie("history_goods_cookieIndex" + history_goods_cookieNum, history_goods_cookieIndex, { expires: 7, path: '/' });
      //最近浏览个数
	$.cookie("history_goods_cookieNum", history_goods_cookieNum, { expires: 7, path: '/' });
  }else{  
         //从cookie获取索引
  	history_goods_cookieNum = parseInt($.cookie("history_goods_cookieNum"));
  	//统计相同商品cookie个数
  	var samecount = 0;
  	for(var i = 1; i<= history_goods_cookieNum; i++){
	  var historyId = $.cookie("history_goods_id" + i);
	  if(historyId == history_goods_id){
	  	samecount++;
	  }
  	}
  	//说明cookie中不存在该商品
  	if(samecount == 0){
  		//cookie个数自增
     		history_goods_cookieNum++;
     	  $.cookie("history_goods_id" + history_goods_cookieNum, history_goods_id, { expires: 7, path: '/' });  
          $.cookie("history_goods_name" + history_goods_cookieNum, history_goods_name, { expires: 7, path: '/' });  
          $.cookie("history_list_img" + history_goods_cookieNum, history_list_img, { expires: 7, path: '/' });  
          $.cookie("history_sale_price" + history_goods_cookieNum, history_sale_price, { expires: 7, path: '/' }); 
          $.cookie("history_market_price" + history_goods_cookieNum, history_market_price, { expires: 7, path: '/' });  
          //cookie索引
 			$.cookie("history_goods_cookieIndex" + history_goods_cookieNum, history_goods_cookieNum, { expires: 7, path: '/' }); 
          //cookie个数更改
 			$.cookie("history_goods_cookieNum", history_goods_cookieNum, { expires: 7, path: '/' });
 		
  	}
  }
}
 

      
//获取最近浏览过的商品  
function bindHistory() {  
        //最新访问的商品ID  
	    var history_goods_id; 
	    //商品名称
	    var history_goods_name; 
	    //商品图片
	    var history_list_img; 
	    //商品价格
	    var history_sale_price; 
	    var history_market_price; 
	    //cokie索引值
	    var history_goods_cookieIndex;
	    //cookie个数
	    var history_goods_cookieNum;
	    var liStr="";	
	    var historyIdsindex="";
        if ($.cookie("hgcn") != null) //cookie 不存在history_goods_cookieNum  
        {  
            //获取cookie个数
	    	history_goods_cookieNum = parseInt($.cookie("hgcn"));
			var historygoodsList=JSON.parse($.cookie("hglt"));
			var historyIds="";
			for(var i = 0; i< history_goods_cookieNum; i++){
	    	      var goodsFlag=historygoodsList[i];
		    	   historyIds +=goodsFlag.hi+",";
		    	   historyIdsindex+=goodsFlag.hn+",";
	    	}
			if(historyIds!=null&&historyIds!=""){
			   //商品ID串
			   historyIds=historyIds.substring(0,historyIds.length-1);
			   //cookies索引串
			   historyIdsindex=historyIdsindex.substring(0,historyIdsindex.length-1);
			}
			var historyIdsindexs=historyIdsindex.split(',');//cookies索引串数组
			  //获取商品信息
			  $.ajax({  	 
		          type: "post",    	     
		          url: "/mall/goods!getGoodsInfoByIdStr.action?gidstr="+historyIds,
		          datatype:"json",
		          success: function(data){
		           data=eval("("+data+")");
		           for(var i = 0; i < data.length; i++){
						  history_goods_id =data[i].goods_id;
						  history_goods_name = data[i].goods_name;
						  if(history_goods_name!=null&&history_goods_name!=""){
						    //截取商品名称标题
						    if(history_goods_name.length>12){
						      history_goods_name=history_goods_name.substring(0,11);
						    }
						  }
						  history_list_img = data[i].list_img;
						  history_sale_price =data[i].min_sale_price;
						  history_goods_cookieIndex =historyIdsindexs[i];
						  history_market_price = data[i].max_market_price;
						  if(history_market_price==null){
								history_market_price=	'';
							}else{
								history_market_price=	'￥<b>'+history_market_price+'</b>';
							}
						  if(history_goods_id != null && history_goods_id != ""){
						  	liStr+="<li id='item"+history_goods_cookieIndex+"'><div>"
						  	+"<a href='/mall-goodsdetail-"+history_goods_id+".html'>"
						  	+"<img src='"+history_list_img+"' /></a></div>"
						  	+"<p><span class='lpspan'>￥"+history_sale_price+"</span>"
						  	+"<span class='rsspan'></span><br class='clear'></p>"
						  	+"<p><a href='/mall-goodsdetail-"+history_goods_id+".html'>"+history_goods_name+"</a></p></li>";
						  }
					}
					//输出li节点
        			$("#historyCarRecord").append(liStr);
		          }
	           });  
      	}
}
//删除全部浏览记录
function delAll(){
		$.cookie("hgcn", null, { expires: 7, path: '/' });
		$.cookie("hglt", null, { expires: 7, path: '/' });
		window.location.reload();//刷新页面
}

//删除单个最近浏览过的商品  
function delOneHistory(number) {  
        if(confirm("确定删除该记录？")) {
	    if($.cookie("history_goods_cookieIndex"+number) != null&&$.cookie("history_goods_cookieIndex"+number)!=""){
		    //清空该商品所有cookie
		    $.cookie("history_goods_cookieIndex"+number, null, { expires: 7, path: '/' });
		    $.cookie("history_goods_id"+number, null, { expires: 7, path: '/' });
		    $.cookie("history_goods_name"+number, null, { expires: 7, path: '/' });
		    $.cookie("history_sale_price"+number, null, { expires: 7, path: '/' });
		    $.cookie("history_list_img"+number, null, { expires: 7, path: '/' });
		    //获取cookie个数
	    	history_goods_cookieNum = parseInt($.cookie("history_goods_cookieNum"));
	    	history_goods_cookieNum--;
	    	if(history_goods_cookieNum <= 0){
	    		$.cookie("history_goods_cookieNum", null, { expires: 7, path: '/' });
	    	}else{
	    		$.cookie("history_goods_cookieNum", history_goods_cookieNum, { expires: 7, path: '/' });
	    	}
		    //移除li
			$("#item"+number).remove();
		}
	}
	window.location.reload();//刷新页面
 }

//加
function add(){   
	if(checkBuyNum('0')){
	var num = $("#buy_nums").val();
	num=eval(num)+1;
	$("#buy_nums").val(num);
	//计算运费
	getShipPrice();
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
	getShipPrice();
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
	$(".specstr >td > div >ul >li>div >a").each(function(){

  		if($(this).parent().attr("class") == "seldiv" || $(this).children("i").attr("class") == "checked"){
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
		$("#error_span").show();
		$(".selproul").addClass("redborder");
	}else{
		$("#error_span").hide();
		$(".selproul").removeClass("redborder");
		$("#gotourl").val(window.location.href);
		$("#buy").submit();
	}
}

//虚拟商品购买
function virBuyNow(){     
	//统计出选中规格值个数
	var checkedNums = 0;
	//商品属性名称
	var spec_name_str = "";
	//商品属性ID
	var spec_id_str = "";
	$(".specstr >td > div >ul >li>div >a").each(function(){
  		if( $(this).children("i").attr("class") == "checked"||$(this).parent().attr("class") == "seldiv"){
  			//判断是标签属性还是图片属性
  			if($(this).parent().parent().parent().attr("class") == "selsize"){//标签属性
  				var current_spec_name = $(this).parent().parent().parent().parent().parent().parent().find("th") .html()+":"+$(this).html();
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
		$("#error_span").show();
		$(".selproul").addClass("redborder");
	}else{
		$("#error_span").hide();
		$(".selproul").removeClass("redborder");
		$("#buy").attr("action","/mall/order!goVirtualOrder.action");
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
	
	$(".specstr >td > div >ul >li>div >a").each(function(){
  		if($(this).children("i").attr("class") == "checked"||$(this).parent().attr("class") == "seldiv"){
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
			$("#error_span").show();
			$(".selproul").addClass("redborder");
		}else{
			$("#error_span").hide();
			$(".selproul").removeClass("redborder");
			addCookie(cartpos);
			loadShowCartGoods();
			$("#cartDiv").show();
		}
	}
	
   }

//商品累计评价

				

var ColumnEval= [
				{
					header : '',
					headerIndex : '',
					tphtml:"<div><img src='$'/></div><p>|$</p>",
					tphtmlval:"logo_path|buy_cust_name"
				},
                {
					header : '',
					headerIndex : '',
					tphtml:"<p><span>评价</span>$</p><p><span>时间</span>|$</p><p><span class='f_left'>晒图</span>|<i class='imgsrc'>$</i></p><p><span class='hfSpan'>回复</span>|$</p>",
					tphtmlval:"g_comment|eval_date|share_pic|explan_content"	
				}];				

//月成交记录

var ColumnMonthDeal = [
				{
					header : '买家',
					headerIndex : '',
					width:"10%",
					tphtml:"<b>$</b>",
					tphtmlval:"buy_cust_name"	
				},
				{
					header : '商品名称',
					headerIndex : '',
					width:"40%",
					tphtml:"$",
					tphtmlval:"goods_name"	
				},
				{
					header : '规格',
					headerIndex : '',
					width:"15%",
					tphtml:"$",
					tphtmlval:"goods_attr"	
				},
				{
					header : '购买数量',
					headerIndex : 'order_num',
					width:"10%"
				},
				{
					header : '交易时间',
					headerIndex : 'order_time',
					width:"20%"
				}];
//商品咨询                       
var ColumnConsultation = [
				{
					header : '',
					headerIndex : '',
					width:"10%",
					cpos:"left",
					tphtml:"<div><img src='$'></div><p>|$</p>",
					tphtmlval:"logo_path|cust_name"
				},
                {
					header : '',
					headerIndex : '',
					width:"90%",
					cpos:"left",
					tphtml:"<p>$</p><p><span>咨询内容</span>|$</p><p><span class='hfSpan'>回复</span>|$</p>",
					tphtmlval:"c_content|c_date|re_content"	
				}];
//商品咨询
function subGoodsConsult(){
	  var item =  $("input[name='con_type']:checked").val(); 
	  if(item==undefined){
	 	 jAlert("请选择咨询类型!","系统提示");
	     return false;
	  }
	  var ccontent = encodeURIComponent(encodeURIComponent($("#c_content").val()));
	  if(ccontent==""){
	     jAlert("请输入咨询内容!","系统提示");
	     return false;
	  }
	  var goodsid = $("#goods_id").val();
	  //验证验证码
	  var rands = $("#rands").val()
	  //被咨询人标识
	  var cust_id = $("#cust_id_str").val();
	  $.ajax({  	 
	            type: "post",    	     
	            url: "/mall/goods!subGoodsConsult.action?rand_code=" + rands + "&cust_id=" + cust_id + "&ccontent=" + ccontent + "&goodsid=" + goodsid + "&c_type=" +item,    
	            datatype:"json",
	            async:false,
	            success: function(data){
	              if(data=='0'){
	                jAlert("成功提交商品咨询!","系统提示");
	                window.location.href="/mall-goodsdetail-" + goodsid + ".html";
	              }
	              if(data=='1'){
	              // jAlert("您还没有登录!","系统提示");
	               window.location.href="/login.html?loc=/mall-goodsdetail-" + goodsid + ".html";
	              }
	              if(data=='2'){
	              	jAlert("验证码错误!","系统提示");
	              }
	              if(data=='3'){
	                jAlert("卖家不能咨询自己!","系统提示");
	              }
	              if(data=='4'){
	                jAlert("内容存在敏感词!","系统提示");
	              }
	            }
	   });
}
	//是否登录
function isLogin(){
	  var goodsid = $("#goods_id").val();
	  $.ajax({  	 
	            type: "post",    	     
	            url: "/mall/goods!isLogin.action",    
	            datatype:"json",
	            async:false,
	            success: function(data){
	              if(data=='1'){
	              // jAlert("您还没有登录!","系统提示");
	               window.location.href="/login.html?loc=/mall-goodsdetail-" + goodsid + ".html";
	              }
	            }
	   });
}

//是否存在敏感字
function filterWord(){
  var content=encodeURIComponent(encodeURIComponent($("#c_content").val()));
  $.ajax({
       type: "post",
       url: "/mall/goods!filterWord.action?ccontent="+content,
       dataype:"json",
       async:false,
       success: function(data){
         if(data!=''){
          jAlert(data+"是敏感词！","系统提示");
         }
       }
  });

}
	
	
//商铺左边搜索
function priceselect(){
     var selValue=$("#bselValue").val();
     $("#selName").val(selValue);
     var upprice=$("#upprice").val();
     $("#uppri").val(upprice);
     var downprice=$("#downprice").val();
     $("#downpri").val(downprice);
	    if((selValue==''||selValue=="请输入搜索条件")&& upprice=='' && downprice==''){
	      $("#bselValue").val("请输入搜索条件");
	    }else{
	     document.forms[0].action="/shopList.action";
	     document.forms[0].submit();
	    }
}
//type按类型排序，show展示样式
function goodsactionbmall(type,show){
		
        if(type!=''){
        	//赋值给隐藏域
        	$("#type").val(type);
        }
        if(show!=''){
        	//赋值给隐藏域
            $("#show").val(show);
        }
		document.forms[0].action="/shopList.action";
		document.forms[0].submit();
}
 //店铺左边的分类查询
function catsearch(cat_id ){
	document.forms[0].action="/shopList.action?cat_id="+cat_id;
	document.forms[0].submit();
}


function loadGoodsImage(){
$(".jqzoom").jqueryzoom({
		xzoom:400,
		yzoom:400,
		offset:10,
		position:"right",
		preload:1,
		lens:1
	});
	$("#spec-list").jdMarquee({
		deriction:"left",
		width:320,
		height:56,
		step:2,
		speed:4,
		delay:10,
		control:true,
		_front:"#spec-right",
		_back:"#spec-left"
	});
	$("#spec-list img").bind("mouseover",function(){
		var src=$(this).attr("src");
		var midsrc=src;
		var bigsrc=src;
		if(src.indexOf("small")>0){
			midsrc=src.replace("small","mid");
			bigsrc=src.replace("small","big");
		}
		$("#spec-n1 img").eq(0).attr({
			src:midsrc.replace("\/n5\/","\/n1\/"),
			jqimg:bigsrc.replace("\/n5\/","\/n0\/")
		});
		$(this).css({
			"border":"2px solid #ff6600",
			"padding":"1px"
		});
	}).bind("mouseout",function(){
		$(this).css({
			"border":"1px solid #ccc",
			"padding":"2px"
		});
	});			
}
	function checkMessageLength(){
		var now_length=0;
			now_length=$("#c_content").val().length;
	      //限制字数
	      var limitNum = 149;  //设定限制字数
	      var pattern = '还可以输入' + limitNum + '字';
	      $('.word_surplus').html(pattern); //输入显示
	      if(now_length <= limitNum) {
	       //计算剩余字数
	      	 var result = limitNum - now_length+1;
	       
	       } else {
	       		result=0;
	      		pattern = ('字数已达到上限');
	      	    jAlert(pattern,"系统提示");
	      	    $("#c_content").val($("#c_content").val().substr(0,150));
	       }
	       $('.word_surplus').html(limitNum+1); //限制字数
	       $('.word_count2').html(result); //输入显示
	       
	}

//关闭提示框
function closeSpan(){
 $("#error_span").hide();
}
	
/**计算运费（商品详细页）
function getShipPrice(){
	var is_ship = $("#is_ship").val();//是否免运费
	var ship_id = $("#ship_id").val();//运费模板id
	var buy_num = $("#buy_nums").val();//购买数量
	var volume = $("#volume").val();//商品体积
	var logsweight = $("#logsweight").val();//商品重量
	var end_area = $("#sareaid").val();//到达地址
	var dataUrl="/mall/goods!getShipPrice.action?is_ship="+is_ship+"&ship_id="+ship_id+"&buy_num="+buy_num+"&volume="+volume+"&logsweight="+logsweight+"&end_area="+end_area;
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
							//shipStr = data[i].ship_name +":"+ data[i].ship_price +"元";
							shipStr =  data[i].ship_price +"元";
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

*/
