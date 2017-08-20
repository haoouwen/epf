var default_search = "";
//初始化加载
$(document).ready(function(){
    //初始化搜索框
    var swd=queryUrlvalue('wd');
    var enwd=queryUrlvalue('en_wd');
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
  //检查自动登录
  autoLoginCheck();
});
//检查自动登录
function autoLoginCheck(){
 //相反，跳转到本页面,等待登陆处理  
  if(window.localStorage){
       // alert("readylogin");
	    var storage = window.localStorage;  
	    var getUserName =localStorage.getItem("username");//用户名
	    var getPwd =localStorage.getItem("password");//密码   
	    var getisautologin =localStorage.getItem("isautologin");//是否自动登陆
        if(getUserName!=""&&getUserName!=null&&getUserName!=undefined&&getPwd!=null&&getPwd!=""&&getPwd!=undefined){
            //lacoste  已经保存 登陆信息 直接登陆  
             $.ajax({
			         type: "post",
			         url: "/webapp/memberuser!webappUserAutoLogin.action?user_name="+getUserName+"&passwd="+getPwd+"&getisautologin="+getisautologin,
			         datatype:"json",
			         async:false,
			         success: function(data){ 
			         	if(data!=null&&data!=""&&data!="1"&&data!="0"){
			         	  if($("#logincenter").val()!=undefined&&$("#logincenter").val()=="1"){
			         	    window.location.href="/webappmembercenter.html";
			         	  }else{
			         	   $("#mLoginId").html("<a href=\"/webappmembercenter.html\">"+data+"</a><a onclick=\"exit();\" href=\"/webappexit.html\">&nbsp;退出</a>");
			         	  }
			         	}
			         }                 
			 });
         }else{
            return false;
         }
    }
}
//搜索普通商品
function searchgoods(){
	 var keyword=$("#keyword").val();
	 var en_wd=$("#en_word").val();
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
        if( en_wd==null||en_wd==""){
           en_wd="enno";
        }
		window.location.href="/webapp/goods!list.action?en_wd="+en_wd+"&wd="+sel;
     }
}

//搜索普通商品
function hotsearchgoods(keyword,en_wd){
	 var sel = encodeURIComponent(encodeURIComponent(keyword));
     if( keyword!=null&&keyword!=""){
       if( en_wd==null||en_wd==""){
           en_wd="enno";
        }
		window.location.href="/webapp/goods!list.action?en_wd="+en_wd+"&wd="+sel;
     }
}


//搜索获取焦点
function searchstart(){
     if($("#keyword").val()==default_search){
       $("#keyword").val("");
     }
}
//搜索失去焦点
function searchend(){
     if( $("#keyword").val()==null||$("#keyword").val()==""||$("#keyword").val()==default_search){
	     $("#keyword").val(default_search);
      }
}
//获取URL路径传过来的值
function queryUrlvalue(key){
  var regex_str="^.+\\?.*?\\b"+ key +"=(.*?)(?:(?=&)|$|#)"
  var regex=new RegExp(regex_str,"i");
  var url=window.location.toString();
  if(regex.test(url)) return RegExp.$1;
  return undefined;
}
function exit(){
  if(window.localStorage){
	    var storage = window.localStorage;	
		storage.removeItem("username");
		storage.removeItem("password");
		storage["isautologin"]="no";
	}
}
//AJAX获取操作获取关键字
var keywordData="";
 $.ajax({
         type: "post",
         url: "/mall/goods!associationkeywordsList.action?ajaxRequestRandom="+Math.random(),
         datatype:"json",
         async:false,
         success: function(data){ 
         		keywordData=eval("("+data+")");
         }                 
 });
$(function() { 
	$('#keyword').autocomplete(keywordData, { 
	max: 10, //列表里的条目数 
	minChars: 0, //自动完成激活之前填入的最小字符 
	width:"100%", //提示的宽度，溢出隐藏 
	scrollHeight: 300, //提示的高度，溢出显示滚动条 
	matchContains: true, //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示 
	autoFill: false, //自动填充 
	selectFirst:false,// 如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true
	formatItem: function(row, i, max) { 
		return row.ass_key_words_title+"<span>在<font color='#e03434'>"+row.goods_cat_last_name+"</font>下搜索   约 "+row.gcount+"个商品</span>"; 
	}, 
	formatMatch: function(row, i, max) { 
		return row.ass_key_words_title + row.en_name; 
	}, 
	formatResult: function(row) { 
		return row.ass_key_words_title; 
	} 
	}).result(function(event, row, formatted) { 
		var sel=encodeURIComponent(encodeURIComponent(row.ass_key_words_title));
		window.location.href=window.location.href="/webapp/goods!list.action?en_wd="+row.en_name+"&wd="+sel+"&cat_attr_top_s="+row.search_cat_attr;
	}); 
}); 