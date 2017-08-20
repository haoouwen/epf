/**
 * 初始化iScroll控件
 */
 var myScroll,myScrolls,
	pullUpEl, pullUpOffset,
	generatedCount = 0,cp=1,is_goods="0",cps=1,pullUpEls, pullUpOffset,
	generatedCounts = 0;
function loaded() {
		pullUpEls = document.getElementById('pullUps');	
	    myScrolls = new iScroll('wrappers', {
		scrollbarClass: 'myScrollbar',
		useTransition: false, 
		
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEls.className.match('flip')) {
				pullUpEls.className = 'flip';
				pullUpEls.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEls.className.match('flip')) {
				pullUpEls.className = '';
				pullUpEls.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEls.className.match('flip')) {
				pullUpEls.className = 'loading';
				pullUpEls.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';				
				pullUpActions();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrappers').style.left = '0'; }, 800);
}


document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', loaded, false); 



function pullUpActions () {
	    setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
	    var search_tj=""; 
		var catEn_name = "";
	    catEn_name = $("#catEn_name").val();
	    var key_word = "";
	    key_word = encodeURIComponent(encodeURIComponent($("#serach_word").val()));
		cps=cps+1;
		search_tj="?cp="+ cps+"&catEn_name="+catEn_name+"&key_word="+key_word;
		var brand_id_s=$("#brand_id").val();
		if(brand_id_s!=null&&brand_id_s!=""){
		  search_tj+="&brand_id_s="+brand_id_s;
		}
		var goods_cat_attr_s=$("#goods_cat_attr_s").val();
		if(goods_cat_attr_s!=null&&goods_cat_attr_s!=""){
		  search_tj+="&goods_cat_attr_s="+goods_cat_attr_s;
		}
		var l_price_id=$("#l_price_id").val();
		if(l_price_id!=null&&l_price_id!=""){
		  search_tj+="&min_price="+l_price_id;
		}
		var h_price_id=$("#h_price_id").val();
		if(h_price_id!=null&&h_price_id!=""){
		  search_tj+="&max_price="+h_price_id;
		}
		var liStr="";
		$.ajax({  	 
          type: "post",    	     
     	  url: "/webapp/goods!pageLists.action"+search_tj,
          datatype:"json",
          success: function(data){
	          if(data==''){
	          	$("#pullUps").remove();
	          }else{
	           data=eval("("+data+")");
	           for(var i = 0; i < data.length; i++){
					  history_goods_id =data[i].goods_id;
					  liStr+="<li><div class=\"sPic\"><a href=\"/webapp/goodsdetail/"+data[i].goods_id+".html\">"
							+"  <img src=\""+data[i].list_img+"\"/>"
							+"</a></div>"
							+"<div class=\"sPCont\">"
							+"  <p><a href=\"/webapp/goodsdetail/"+data[i].goods_id+".html\">"+data[i].goods_name+"</a></p>"
							+"  <p><b>￥"+data[i].sale_price+"</b><span>￥"+data[i].market_price+"</span></p>"
							+"<p>销量："+data[i].order_num+" &nbsp;|&nbsp; 评价："+data[i].eval_num+"</p>"
							+"</div><br class=\"clear\"/></li>";
	            }
				$("#thelists").append(liStr);
	          }
          }
      }); 
		myScrolls.refresh();		
	}, 1000);	
}
