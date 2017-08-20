//收藏品牌
function ginsertColl(type){
   var title = $("#title").val();
   var goods_id=$("#goods_id").val();
   var price = $("#sale_price").text();
   var link_url = document.location.href;
   //跳回登陆前位置
		var urlhref = window.location.href;
	  	var locstr = '/webapp';
	    if(urlhref.indexOf(locstr) > -1){
	   	  var posi = urlhref.indexOf(locstr);
	   	  var loc = urlhref.substring(posi,urlhref.length);
	   	  $("#refloc").val(loc);
	   }
   $.ajax({  	 
          type: "post",    	     
          url: "/collect!ajxinsert.action",
          data:{title:title,link_url:link_url,goods_id:goods_id,price:price},
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("您还没登陆，请先登陆!","系统提示");
             	window.location.href="/webapplogin.html?loc="+loc;
             }else if(data=='2'){
             	jAlert("已经收藏过此商品!","系统提示");
             }else if(data=='0'){
                jAlert("收藏成功!","系统提示");
                $("#dshoucid").attr("class","yscSpan");
                $("#dshoucid").attr("title","已收藏");
             }else if(data=='3')
                jAlert("不能收藏自己的商品!");
          }
      });  
  }

function insertCollCat(type){
   var title = encodeURIComponent(encodeURIComponent($("#collect_title").val()));
   var link_url = $("#collect_link_url").val();
    //跳回登陆前位置
	var urlhref = window.location.href;
  	var locstr = '/webapp';
    if(urlhref.indexOf(locstr) > -1){
   	  var posi = urlhref.indexOf(locstr);
   	  var loc = urlhref.substring(posi,urlhref.length);
   	  $("#refloc").val(loc);
   }
   $.ajax({  	 
          type: "post",    	     
          url: "/collect!ajxinsert.action?title="+ title + "&link_url=" + link_url + "&colltype=" + type,
          datatype:"json",
          success: function(data){
             if(data=='1'){
             	jAlert("您还没登陆，请先登陆!","系统提示");
             	window.location.href="/webapplogin.html?loc="+loc;
             }
             if(data=='0'){
             	jAlert("收藏成功!","系统提示");
             }
          }
      });  
  }
  


