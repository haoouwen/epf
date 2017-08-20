<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码${cfg_webname?if_exists}</title>

		<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/retrievePsw.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
		<script src="/malltemplate/index/js/mall_top.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.autocomplete.js" type="text/javascript"></script>
		<script src="/include/common/js/common.js" type="text/javascript"></script>
		<script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.masonry.min.js" type="text/javascript"></script>
		
		

<script type="text/javascript">
$(document).ready(function(){ 
	     var un=$("#username");
	     var un_conent="用户名/邮箱/手机号";
		 if($(un).val()=='' || $(un).val()==un_conent){
		     $(un).val(un_conent);
		     $(un).addClass("pasttext");
		 }	
		 //获得焦点事件
		 $(un).focus(function(){	
			 if($(un).val()=='' || $(un).val()==un_conent){	     
			     $(un).val("");
			      $(un).removeClass("error");
			  }
		 });	
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
				          		if(cart_num ==null || cart_num==''){
									$("#cart_id_num").html("0");
								}else{
									$("#cart_id_num").html(cart_num);
								}
				          }
				      });  
		 //失去焦点事件
		 $(un).blur(function(){
		     if($(un).val()==''){
			    $(un).val(un_conent);
			    $(un).addClass("pasttext");
			 }
		 });		 
   });
function sub() {
	 $("#forms").submit();
 }
 //验证码功能
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src = "/imgrand.action?d=" + timenow;
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
</script>
</head>
<body>
<@s.form id="forms" action="/mall/member!checkCode.action" method="post">
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
		<!--内容-->
		<div class="registb">
		<div class="regist">
		    <!--步骤提示-->
		    <div class="pswInfor">
		    </div>
		    <!---->
		    <div class="regMain">
		        <h2>
		            <span>
		                找回密码
		            </span>
			        </h2>
		        <div class="regcont">
		            <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
		                <tr>
		                    <th width="15%">
		                        <span>
		                            *
		                        </span>
		                       用户名：
		                    </th>
		                    <td width="30%">
		                    	<@s.textfield id="username" name="username" cssClass="zctext" maxLength="20"/>
		                    </td>
		                    <td width="55%">
			                    <p <#if i_u=='0'>class="not"<#elseif i_u=='1'>class="yes"</#if>>
		                        	<@s.fielderror><@s.param>username</@s.param></@s.fielderror>
		                        </p>
		                    </td>
		                </tr>
		                <tr>
		                    <th>
		                        <span>
		                            *
		                        </span>
		                        验证码：
		                    </th>
		                    <td>
		                    	<@s.textfield name="commentrand" cssClass="yzmtext f_left" maxLength="4"/>
			      			   	<img src="/imgrand.action" class="yzmPic" onclick="changeValidateCode(this)"/ >
		                    </td>
		                    <td>
		                    		<p <#if i_b=='0'>class="not"<#elseif i_b=='1'>class="yes"</#if>>
		                        	<@s.fielderror><@s.param>trand</@s.param></@s.fielderror>
		                        </p>
		                    </td>
		                </tr>
		            </table>
		            <p>
		                <input type="button" class="proBut"onclick="sub();"/>
		            </p>
		        </div>
		    </div>
		</div>
		</div>

      <div class="clear"></div>
   </div>
</div>
<input type="hidden" id="hidden_area_value" name="hidden_area_value"/>
<@s.hidden name="sl" />
<div class="clear"></div>
<!--尾部-->
<!--底部开始-->
<#include "/a/bmall/mallbottom.html" >
 <!--底部结束-->
 </@s.form> 
</body>
</html>
