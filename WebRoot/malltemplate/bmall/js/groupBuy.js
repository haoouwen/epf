 $(document).ready(function(){
	var is_illegal=$("#is_illegal").val();
	if(is_illegal!=null&&is_illegal=='1'){
		 jNotify("非法操作！请重新选择购买数量!"); 
	}
	updateEndTime();
	//跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/mall';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
	/*最热团购*/
	if($("div").hasClass("hotBuy")){
	  $('#hotBuyId').sFocus("itemDIv","butDiv","current",6000,1000,3);
	  $(".clatr").hide();
	  var gbLenth=$(".gbCla").height();
	  $(".gbclaDiv").css("height",gbLenth+"px");
	  /*一级分类选择*/
	  $(".gbclah2 a").click(function(){
	  		   var cat_id=this.getAttribute("cat_id");
		   var now_car_id=$("#one_cat_attr").val();
		    $("#one_cat_attr").val(cat_id);
		    $("#cat_attr").val(cat_id);
		   gbLenth=$(".gbCla").height();
	       $(".gbclaDiv").css("height",gbLenth+"px");
		   $("#gbClaId").floatDiv("floatTop");
		 $("#groupgoods").submit();
	   })
	   	  /*一级分类选择*/
	  $(".gbclah2 a").each(function(){
	  		   var cat_id=this.getAttribute("cat_id");
		   var now_car_id=$("#one_cat_attr").val();
		   if(cat_id==now_car_id){
		      $(this).addClass("sela").siblings("a").removeClass("sela");
		   }
		     gbLenth=$(".gbCla").height();
	       $(".gbclaDiv").css("height",gbLenth+"px");
		   $("#gbClaId").floatDiv("floatTop");
		   if(cat_id==''){
			   $(".clatr").hide();
			}else{
				$(".clatr").show();	
			}
			
	   })
	   
	    /*er级分类选择*/
	  $(".gbClaDiv2 a").click(function(){
	  		   var cat_id=this.getAttribute("cat_id");
		    $("#cat_attr").val(cat_id);
		 $("#groupgoods").submit();
	   })
	   	  /*er级分类选择*/
	  $(".gbClaDiv2 a").each(function(){
	  		   var cat_id=this.getAttribute("cat_id");
		   var now_car_id=$("#cat_attr").val();
		   if(cat_id==now_car_id){
		      $(this).addClass("sela").siblings("a").removeClass("sela");
		   }
	   })
	  /*价格选择*/
	  $(".ptd a").click(function(){
	  		   var price=this.getAttribute("price");
		    $("#price").val(price);
		 $("#groupgoods").submit();
	   })
	  $(".ptd a").each(function(){
	  		var price=this.getAttribute("price");
	  		var now_price=$("#price").val();
	  		if(now_price==price){
	  			 $(this).addClass("sela").siblings("a").removeClass("sela");
	  		}
		  
		 })
	   /*分类选择浮动*/
	  $("#gbClaId").floatDiv("floatTop");
	 } 
	/*团购列表*/
	$(".gbList ul li").hover(function(){
		$(this).addClass("addli");
		$(this).find(".imgDiv").children("p").show();
	},function(){
		$(this).removeClass("addli");
		$(this).find(".imgDiv").children("p").hide();
	});
	/*推荐团购*/
	if($("div").hasClass("recommend")){
		aFocus("recommendId",3,"nSpan","selSpan",false);
		$("#grouph2Id").floatDiv("floatTop");
	}
});
function updateEndTime() {
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
            var phtml = '<b>' + day + '</b> 天 <b>' + hour + '</b> 时 <b>' + minite + '</b> 分 <b>' + second + '  </b>秒';
            if (flag == null || flag == '1') {
                phtml = '剩余时间: <b>' + day + '</b> 天 <b>' + hour + '</b> 时 <b>' + minite + '</b> 分 <b>' + second + '  </b>秒';
            }
            $(this).find(".pTimes").html(phtml);
        } else {
        	$(this).find(".pTimes").html("团购过期啦！");
        }
    });
    setTimeout("updateEndTime()", 1000);
}