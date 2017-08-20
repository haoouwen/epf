var t = 5;
var url;
$(document).ready(function(){
			var cart_num=0;
				if($.cookie("ccn")!=null){
					var cart_cookieNum =parseInt( $.cookie("ccn"));
					for(var i=1;i<=cart_cookieNum;i++){
						cart_num+=parseInt($.cookie("buy_nums"+i));
			    	}
				}
            	 $.ajax({  	 
				          type: "post",    	     
				          url: "/mall/goods!getCarNumCount.action",
				          datatype:"json",
				          success: function(data){
				          		if(cart_num!=null){
				          			cart_num=parseInt(data)+cart_num;
				          		}else{
				          				$.cookie("ccn", null, { expires: 7, path: '/' });
				          		}
				          		if(cart_num ==null || cart_num=='' || cart_num<0){
									$("#cart_id_num").html("0");
									$(".numi").html("0");
								}else{
									$("#cart_id_num").html(cart_num);
									$(".numi").html(cart_num);
								}
				          }
				      });  
		url=$("#returnUrl").val();
		fun();
		var inter = setInterval("fun()",1000);
});
//加入收藏
			function addFavorite() {   
			   if (document.all) {
			      window.external.addFavorite(location.href,document.title);   
			   } else if (window.sidebar) {   
			   	 $(".store").attr("rel","sidebar");
			     window.sidebar.addPanel(document.title,location.href, "");   
			   } else{
			 	 alert("加入收藏失败，请使用Ctrl+D进行添加");
			   }
			   }
function fun(){
		t--;
		$("#time").html(t);
		if(t<=0){
		location.href = url;
		clearInterval(inter);
		}
}
	 
	