<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码-${cfg_webname?if_exists}</title>
		<link href="/malltemplate/bmall/css/jt_public.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/retrievePsw.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
		<script src="/include/common/js/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.autocomplete.js" type="text/javascript"></script>
		<script src="/include/common/js/common.js" type="text/javascript"></script>
		<script src="/include/common/js/commonplugin.js" type="text/javascript"></script>
		<script src="/include/common/js/jquery.masonry.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="/malltemplate/bmall/js/updatepass.js"></script>
		<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
		<script src="/malltemplate/index/js/mall_top.js" type="text/javascript"></script>
</head>

<body>
<@s.form id="forms" action="/mall/member!passReset.action" method="post">
<!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
   <!--内容-->
<div class="registb">
    <div class="regist">
        <!--步骤提示-->
        <div class="pswVerify">
        </div>
        <!---->
        <div class="regMain">
            <h2><span>找回密码</span></h2>
            <div class="regcont">
                
                <h3 class="yzsh3"><@s.radio name="c_t" id="c_t" list=r"#{'2':'邮箱验证','1':'手机验证'}" onclick="sele()" value="1"/></h3>
                       <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                            <tr>
                                <th width="15%">
                                    昵称：
                                </th>
                                <td width="30%">
                                    <b>${username}</b>
                                </td>
                                <td width="55%"></td>
                            </tr>
                            <tr>
                                <th width="15%" id="mobel1">已验证手机：</th>
                                <td width="30%">
                                    <b  id="mobel">${phone}</b>
                                </td>
                                <td width="55%"></td>
                            </tr>
                            <tr>
                                <th>
                                    <span> *</span>验证码：</th>
                                <td>
                                  <@s.textfield id="checkcode" name="checkcode" cssClass="yzmtext2" maxLength="6"/>
                                  <input id="cpc" type="button" class="yzmBut" onclick="send();" value="获取验证码">
                                </td>
                                <td>
                                    <p <#if cc=='0'>class="not"<#elseif cc=='1'>class="yes"></#if>>
                                       <@s.fielderror><@s.param>checkcode</@s.param></@s.fielderror>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td><input type="button" class="yzzhbut" onclick="sub();"/></td>
                                <td></td>
                            </tr>
                       </table>
            </div>
        </div>
    </div>
</div>
      <div class="clear"></div>
   </div>
</div>
<script type="text/javascript">
function sub() {
	var check=$("#checkcode").val();
	if(check==''){
		alert("请输入验证码！");
		return false;
	}
	 $("#forms").submit();
 }
 function sele(){
 	var sel=$('input[name="c_t"]:checked').val();
 	var phone="${phone?if_exists}";
 	var email="${email?if_exists}";
 	if(phone==''){
 		phone="还未验证手机！";
 	}
 	if(email==''){
 		email="还未验证邮箱！";
 	}
 	if(sel=="1"){
 		$("#mobel1").html("您的手机号码：");
 		$("#mobel").html(phone);
 		$("#deflag").val('1');
 	}else if(sel=="2"){
 		$("#mobel1").html("您的邮箱号码：");
 		$("#mobel").html(email);
 		$("#deflag").val('2');
 	}
 }


</script> 
<@s.hidden name="sl" />
<@s.hidden name="username" />
<@s.hidden id="phone" name="phone"/>
<@s.hidden id="email" name="email"/>
<@s.hidden id="deflag" name="deflag"/>
<input type="hidden" id="timeMinus" value="0"/>
<div class="clear"></div>
<!--尾部-->
<!--底部开始-->
<div style="padding:20px 0px 50px 0px;">
<#include "/a/bmall/mallbottom.html" >
 </div>
 <!--底部结束-->
 </@s.form> 
</body>
</html>
