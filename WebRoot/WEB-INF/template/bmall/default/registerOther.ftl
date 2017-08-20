<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员注册-${cfg_webname?if_exists}</title>
		<script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.autocomplete.js" type="text/javascript"></script>
		<script src="/include/common/js/common.js" type="text/javascript"></script>
		<script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.masonry.min.js" type="text/javascript"></script>
		<script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
		<script src="/malltemplate/bmall/js/register.js" type="text/javascript"></script>
		<script type="text/javascript" src="/include/common/js/jquery.jNotify.js"></script>
		<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
		<link rel="StyleSheet" href="/malltemplate/bmall/css/memberuser.css" type="text/css" />
		<link href="/malltemplate/bmall/css/regist.css" rel="stylesheet" type="text/css">
		<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
<@s.form action="/mall/memberuser!regist.action" method="post" id="registForm">
<!--内容-->
<div class="registb">
    <div class="regist">
        <!--步骤提示-->
        <!---->
        <div class="regMain">
        <h2><span>会员注册</span><a href="/">返回首页</a></h2>
            <div class="regcont">
            
               <div class="kjdlDiv">
                  <table width="100%" cellpadding="0" cellspacing="0">
	                <tr>
	                    <th><img src="${qq_pic?if_exists}"/></th>
	                    <td>
	                       <p>来至${come_here?if_exists}的<span>${nickName?if_exists}</span>,您好。</p>
	                       <p>立即设置一个地区,以后就可以直接登录${cfg_webname}了。</p>
	                   </td>
	                </tr>
                  </table>
                </div>
                
                <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                    <tr>
                        <th width="13%"><span>*</span>手机号码：</th>
                        <td width="30%">
                            <@s.textfield id="phone" name="regist_phoen" cssClass="sjtext"   maxlength="15" onblur="CellphoneIsNull();"  onfocus="CellphoneForm();" />
                        </td>
                        <td width="57%"><p id="phoneError"></p></td>
                    </tr>                     
                     <tr>
                        <th><span>*</span>所属地区：</th>
                        <td>
                           <@s.select  id="selarea" name="membernum"  list="areaList" listValue="area_name"
						       listKey="area_number"   headerKey="" headerValue="--请选择地区--" value=""/>
						       说明:地区注册之后不可修改
                        </td>
                        <td><p id="areaError"></p></td>
                    </tr>                    
                    
                </table>
                
                <div class="protocol">   
                    <p>
                        <input type="button" class="proBut" onclick="subregist();"/>
                    </p>
                     <p>
                        <input type="checkbox" id="agreeId" checked="checked"/>
                        <span class="proSpan">
                            我已阅读并同意<a >《${cfg_webname}注册协议》</a>
                        </span>
                        <div class="proDiv">${serviceterms?if_exists}</div>
                    </p>
                </div>
                
            </div>
        </div>
    </div>
</div>
<div class="clear">
</div>
<!--尾部-->
<!--底部开始-->

<#include "/a/bmall/mallbottom.html">

 <@s.hidden  name="open_id"/>
 <@s.hidden  name="regist_type"/>
<input type="hidden" id="timeMinus" value="0"/>
 </@s.form> 
  <script type="text/javascript">
            $(document).ready(function() {
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
        </script>
</body>
</html>
