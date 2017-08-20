//设置主页
function setMyHome() {
	if (document.all) {
		document.body.style.behavior = "url(#default#homepage)";
		document.body.setHomePage(location.href);
	} else {
		if (window.sidebar) {
			if (window.netscape) {
				try {
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}
				catch (e) {
					alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true");
				}
			}
			var prefs = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref("browser.startup.homepage",location.href);
		}
	}
}
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

//加载独立访问数
function loadIpVisit(){
	if($.cookie("hy_visit_id")!=null && $.cookie("hy_visit_id")!=""){
	}else{
		//更新访问数量
		$.ajax({  	 
        type: "post",    	     
        url: "/visitnum!updatePvNum.action?ajaxRequestRandom="+Math.random(),       
        datatype:"json",
        success: function(data){   
			}
		});  	
		//存入cookie
		$.cookie("hy_visit_id" , "1", { expires: 1 , path: '/'});
	}
}
var default_search = "";
//初始化加载
$(document).ready(function(){
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
    //初始化搜索框
    var swd=queryUrlvalue('wd');
    var enwd=queryUrlvalue('en_wd');
    //修改导航栏最后一个li样式
    $(".navcont ul li:last-child").addClass("navli-last");
    /*二级分类鼠标滑过样式
	$(".clistMain").hover(function(){
		$(this).addClass('addclaborder');
	  },function () {
		$(this).removeClass('addclaborder');
	});
	*/
    //默认搜索值
    var default_search = $("#default_search").val();
     if(default_search==undefined){
       default_search ="";
    }
    //默认搜索值拼音
    var getword=Turnpingyin(default_search);
    $('#en_word').val(getword);
    if(swd!=null&&swd!=""&&enwd!=""){
    	//将搜索值保存于动态页中
    	swd=decodeURIComponent(decodeURIComponent(swd));
    	$("#serach_word").val(swd);
    	$("#serach_enword").val(enwd);
    }
    var serch_word=$("#serach_word").val();
    var serach_enword=$("#serach_enword").val();
    if(serch_word!=null&&serch_word!=""&&serach_enword!=""&&serch_word!=default_search){
      $("#keyword").val(serch_word);
      $("#en_word").val(serach_enword);
    }else{
      $("#keyword").val(default_search);
  }
	//loadIpVisit();
	var cart_num=0;
	if($.cookie("ccn")!=null){
		cart_num=parseInt($.cookie("ccn"));
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
				  /*加载购物车信息*/
				loadCartGoods(cart_num);   
          }
      });   
	//搜索关键字拼音
	$('#keyword').bind('keyup', function(){   
        var keyword=$('#keyword').val();   
        var getword=Turnpingyin(keyword)
        var en_word="";
        if(getword.length>50){
           en_word=getword.substring(0,49);
		}else{
		   en_word=getword;
		}
    	$('#en_word').val(en_word);
	}) ;
});
//搜索普通商品
function searchgoods(){
	 var keyword=$("#keyword").val();
	 var en_wd=$("#en_word").val();
	 keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/Search.html?en_wd="+en_wd+"&wd="+sel;
     }
}
//搜索品牌
function searchbrand(){
	 var keyword=$("#keyword").val();
	 var en_wd=$("#en_word").val();
	  keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/mall/goodsbrand!list.action?en_wd="+en_wd+"&wd="+sel;
     }
}
//搜索店铺
function searchshop(){
	 var keyword=$("#keyword").val();
	 var en_wd=$("#en_word").val();
	  keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/mall/goods!shopList.action?en_wd="+en_wd+"&wd="+sel;
     }
}

//搜索团购商品
function searchGroupName(){
	 var keyword=$("#keywords").val();
	  keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if(keyword!=null&&keyword!=""){
		window.location.href="/mall/groupbuy!groupBuyIndex.action?searchGroupName="+sel;
     }
}


//搜索秒杀商品
function searchspikegoods(){
	 var keyword=$("#keywords").val();
	  keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/mall/spikegoods!list.action?wd="+sel;
     }
}

//搜索预售商品
function searchdirectgoods(){
	 var keyword=$("#keywords").val();
	  keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/mall/directSell!moreIndex.action?wd="+sel;
     }
}


//搜索积分商品
function searchpointsgoods(){
	 var keyword=$("#keywords").val();
	  keyword=keyword.toLowerCase();
	 keyword=keyword.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
		window.location.href="/mall/pointsgoods!moreIndex.action?wd="+sel;
     }
}

//热门搜索
function hot_search(s_type,s_wd){
	var en_wd=$("#en_word").val();
   var s_wd=encodeURIComponent(encodeURIComponent(s_wd));
   searchClass(en_wd,s_wd);
}
//搜索的ACTION类
function  searchClass(en_wd,s_wd){
 s_wd=s_wd.toLowerCase();
 s_wd=s_wd.replace(/\"/g,'').replace(/\//g,'').replace(/\'/g,"").replace(/\,/g,"").replace(/\</g,"").replace(/\>/g,"");
 window.location.href="/Search.html?en_wd="+en_wd+"&wd="+s_wd;  
}
//搜索获取焦点
function searchstart(){
   default_search = $("#default_search").val();
   if(default_search==undefined){
     default_search ="";
   }
   $("#keyword")[0].focus();
   if(default_search!=null&&default_search!=""&&$("#keyword").val()==default_search){
     $("#keyword").val("");
   }
}
//搜索失去焦点
function searchend(){
     default_search = $("#default_search").val();
     if(default_search==undefined){
       default_search ="";
     }
     if( $("#keyword").val()==null||$("#keyword").val()==""||$("#keyword").val()==default_search){
	     $("#keyword").removeClass();
	     $("#keyword").addClass("seatext");
	     $("#keyword").val(default_search);
      }
}


//AJAX获取操作获取关键字
var keywordData="";
 $.ajax({
         type: "post",
         url: "/associationkeywords!associationkeywordsList.action?ajaxRequestRandom="+Math.random(),
         datatype:"json",
         async:false,
         success: function(data){ 
         		keywordData=eval("("+data+")");
         }                 
 });

$(function() { 
	$('#keyword').autocomplete(keywordData, { 
	max: 12, //列表里的条目数 
	minChars: 0, //自动完成激活之前填入的最小字符 
	width: 325, //提示的宽度，溢出隐藏 
	scrollHeight: 300, //提示的高度，溢出显示滚动条 
	matchContains: true, //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示 
	autoFill: false, //自动填充 
	formatItem: function(row, i, max) { 
		return row.ass_key_words_title+"<span style='float:right'>约 "+row.searchcount+" 个商品</span>"; 
	}, 
	formatMatch: function(row, i, max) { 
		return row.ass_key_words_title + row.en_name; 
	}, 
	formatResult: function(row) { 
		return row.ass_key_words_title; 
	} 
	}).result(function(event, row, formatted) { 
		var sel=encodeURIComponent(encodeURIComponent(row.ass_key_words_title));
		window.location.href="/Search.html?en_wd="+row.en_name+"&wd="+sel+"&cat_attr_top_s="+row.cat_attr;
	}); 
}); 

/*加载购物车信息，加载购物车商品个数上限为5*/
function loadCartGoods(cart_num){
  //判断样式是存在
   if($("div").hasClass("icCont")){
	  //html对象
	  var cartHtml="";
	  //右侧购物车
	  var indexHtml="";
	  //本地cookie存放商品个数
	  var count=0;
	  //商品总类
	  var goodscount=0;
	  //商品总件数
	  var gcount=0;
	  //商品总价
      var total=0.00;  
	  
	  //判断购物车商品数量是否不等于0 
	  if(cart_num!=0){
	   
	   //编辑购物车html
        cartHtml=" <p class='icp'><b>最新加入的商品</b></p> <div class='icTab'><table cellpadding='0' cellspacing='0'>";
	    indexHtml="<div class='rCartTab'><b class='nb'>最新加入的商品</b><table cellpadding='0' cellspacing='0'>";
	   //先判断本地cookie是否存在加入购物车的商品
	   if($.cookie("twgl")!=null&&$.cookie("twgl")!=""){
			//获取商品列表
			var goodsList=JSON.parse($.cookie("twgl"));
	        //判断商品列表长度是否小于5
	        if(goodsList.length<5){
	          	//小于5的，把商品列表长度赋值给对象count
	         	 count=goodsList.length;
	        }else{
	        	 //大于5的，把对象count值为5
	        	 count=5;
	        }
	        //商品总计赋值；
	        goodscount=count;
	        //获取商品列表的商品信息
	        for(var i=0;i<count;i++){
	          //创建购物车对象
	          var goodsFlag=goodsList[i];
	          //根据|分割对象
	          var carts=goodsFlag.i.split('|');
	          var cart_img="/include/common/images/nopic.jpg",cart_goods_name="获取商品名称失败",cart_goods_price="0.0";
	           $.ajax({
		            type:'post',
		            url:'/mall/goods!getGoodsInfoByIdStr.action?gidstr='+carts[2],
		            datatype:'json',
		            async:false,
		            success:function(data){
		                //接收返回值
             			 var cartgoodsList=eval("(" +data+")");
             			 if(cartgoodsList!=""&&cartgoodsList.length >0){
             			 	cart_img=cartgoodsList[0].img_path;
             			 	cart_goods_name=cartgoodsList[0].goods_name;
             			 	cart_goods_price=cartgoodsList[0].min_sale_price;
             			 }
		            }
	           });
	           //编辑购物车html
	           cartHtml+="<tr><td class='imgtb'><img src='"+cart_img+"' width='50' height='50'  /></td>"+
                 "<td class='texttb'><a href='/mall-goodsdetail-"+carts[2]+".html'>"+cart_goods_name+"</a></td>"+
                 "<td class='cztab'>"+
                   "<p>￥"+cart_goods_price+" X "+carts[1]+"</p>"+
                   "<p><span class='scSpan'><a href='###' onclick='delcart("+carts[3]+")'>删除</a></span></p>"+
                 "</td>"+
                "</tr>";
                //编辑右侧购物车
                indexHtml+="<tr><td class='imgtb'><img src='"+cart_img+"' width='50' height='50'  /></td>"+
                 "<td class='texttb'><a href='/mall-goodsdetail-"+carts[2]+".html'>"+cart_goods_name+"</a></td>"+
                 "<td class='cztab'>"+
                   "<p>￥"+cart_goods_price+" X "+carts[1]+"</p>"+
                   "<p><span class='scSpan'><a href='###' onclick='delcart("+carts[3]+")'>删除</a></span></p>"+
                 "</td>"+
                "</tr>";
	          //计算商品总价格
	          total = (eval(total) + eval(cart_goods_price) * eval(carts[1])).toFixed(2);
	          //商品总量
	          gcount = gcount + eval(carts[1]);
	        }
	    }
        //如果本地cookie的商品列表长度小于5，从会员账号中购物车商品的数量补全
        if(count<5){

          //ajax请求后台获取购物车数据
          $.ajax({
            type:'post',
            url:'/mall/goods!getCartGoods.action',
            datatype:'json',
            async:false,
            success:function(data){
              //接收返回值
              var cartgoodsList=eval("(" +data+")");
              //循环商品的个数
              count=5-count;
              
              //判断数据库中购物车商品的个数是否小于count
              if(cartgoodsList.length<count){
                
                //小于count,把值赋给count
                count= cartgoodsList.length;  
              }
              
              //商品总计赋值；
              goodscount+=count;

              
              if(cartgoodsList!=""&&cartgoodsList.length >0){
              
              //获取商品列表的商品信息
		      for(var i=0;i<count;i++){
		          
		          //编辑购物车html
		          cartHtml+="<tr><td class='imgtb'><img src='"+cartgoodsList[i].img_path+"' width='50' height='50'  /></td>"+
                  "<td class='texttb'><a href='/mall-goodsdetail-"+cartgoodsList[i].goods_id+".html'>"+cartgoodsList[i].goods_name+"</a></td>"+
                  "<td class='cztab'>"+
                    "<p>￥"+cartgoodsList[i].sale_price+" X "+cartgoodsList[i].buy_num+"</p>"+
                    "<p><span class='scSpan'><a href='###' onclick='delcartgoods("+cartgoodsList[i].trade_id+")'>删除</a></span></p>"+
                  "</td>"+
                 "</tr>";
		          //编辑右侧购物车
		          indexHtml+="<tr><td class='imgtb'><img src='"+cartgoodsList[i].img_path+"' width='50' height='50'  /></td>"+
                  "<td class='texttb'><a href='/mall-goodsdetail-"+cartgoodsList[i].goods_id+".html'>"+cartgoodsList[i].goods_name+"</a></td>"+
                  "<td class='cztab'>"+
                    "<p>￥"+cartgoodsList[i].sale_price+" X "+cartgoodsList[i].buy_num+"</p>"+
                    "<p><span class='scSpan'><a href='###' onclick='delcartgoods("+cartgoodsList[i].trade_id+")'>删除</a></span></p>"+
                  "</td>"+
                 "</tr>";                 
		          //计算商品总价格
		          total = (eval(total) +eval(cartgoodsList[i].sale_price) * eval(cartgoodsList[i].buy_num)).toFixed(2);
		          //商品总量
	              gcount = gcount + eval(cartgoodsList[i].buy_num);
		      }
             }  
            }
          });
        }
        //编辑购物车html
        cartHtml+="</table>"+
        "</div>"+
         "<p class='zjP'><span class='f_left'>共 <b>"+gcount+"</b> 件商品&nbsp;&nbsp;共计：<b>￥"+total+"</b></span>"+
         "<a href='/cart.html' class='goa'>去购物车</a></p>";
        //编辑右侧购物车
        indexHtml+="</table>"+
        "</div>"+
        "<div class='rCartBut'>"+
        "<p>共<b>&nbsp;"+gcount+"&nbsp;</b>件商品&nbsp;&nbsp;共计：<b>￥"+total+"</b></p>"+
        "<a href='/cart.html' class='goa'>去购物车</a></p>"+
        "</div>";     	   
	 }else{
	 
	      //html对象赋值 
	      cartHtml+=" <p><img src='/malltemplate/bmall/images/ieCart.gif'  /></p>"
	      //编辑右侧购物车
	      indexHtml+="<p style='padding-top:40px'><img src='/malltemplate/bmall/images/ieCart2.gif' width='240'/></p>"
	 }
	 //给样式为cartcDiv对象赋值  
	 $(".icCont").html(cartHtml);
	 $(".rightCart").html(indexHtml);  
	
	} 
}
//删除购物车中的商品
function delcartgoods(cart_id){
    //ajax删除购物车中的商品
    $.ajax({
      type:'post',
      url:'/mall/goods!delcartgoods.action?cart_id='+cart_id,
      datatype:'json',
      async:false,
      success:function(data){
       window.location.reload();
      }
    });

}
 //清除购物项
function delcart(cookie_id){
 	  
 	  //获取购物车商品总数
 	  var cart_num=parseInt($.cookie("ccn"));
 	  //获取本地购物车商品
	  var goodsListdel = new Array();
	  goodsListdel=JSON.parse($.cookie("twgl"));
	  for(var i1=0;i1<goodsListdel.length;i1++){
	      //创建购物车对象
          var goodsFlag=goodsListdel[i1];
          //根据|分割对象
          var carts=goodsFlag.i.split('|');
	      
	       //判断cookie_id是否相同
	      if(carts[3]==cookie_id){
	       //删除cookie
	        goodsListdel.splice(i1,1);
	        break;
	       }
	  }
	  //购物车数量减一
	  cart_num--;
	  if(cart_num<0){
	 	 cart_num=0;
	  }
	  //保存本地cookie
	  var json = JSON.stringify(goodsList);
	  $.cookie("twgl", json, { expires: 7, path: '/' });
	  $.cookie("ccn",cart_num, { expires: 7, path: '/' });
	  window.location.reload();
 }


