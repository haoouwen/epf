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
//初始化加载
$(document).ready(function(){
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
      $("#keyword").removeClass();
      $("#keyword").addClass("seartext_none");
      $("#keyword").val(default_search);
  }
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
