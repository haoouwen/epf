<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码-${cfg_webname?if_exists}</title>
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
		<script type="text/javascript" src="/malltemplate/bmall/js/updatepass.js"></script>
		
</head>

<body>
<@s.form id="forms" action="/mall/member!updatePass.action" method="post">
 <!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
   <!--内容-->
<div class="registb">
    <div class="regist">
        <!--步骤提示-->
        <div class="pswAgain">
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
                        <th>
                            <span>
                                *
                            </span>
                            新登录密码：
                        </th>
                        <td width="30%">
                        	<input id="password" name="password" type="password" class="mmtext" onfocus="PasswordForm()" onblur="PasswdIsNull()">
                        </td>
                        <td width="55%">
                            <p <#if p=='0'>class="not"<#elseif p=='1'>class="yes"</#if>id="passwdError">
                             <@s.fielderror><@s.param>password</@s.param></@s.fielderror>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span>
                                *
                            </span>
                            确认新密码：
                        </th>
                        <td>
                        	<input id="rep_pass" name="rep_pass" type="password" class="mmtext" onblur="Confirm_pwdIsNull();" onfocus="Confirm_pwdForm()" >
                        </td>
                        <td>
                            <p <#if rp=='0'>class="not"<#elseif rp=='1'>class="yes"</#if>id="confirm_pwdError">
                              	<@s.fielderror><@s.param>rep_pass</@s.param></@s.fielderror>
                            </p>
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="button" class="proBut"  onclick="subRet();"/>
                </p>
            </div>
        </div>
    </div>
</div>
<@s.hidden name="sl" />
<@s.hidden name="username" />
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
