<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>账户安全</title>

<link href="/include/bmember/css/account.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="wR810">

	<div  class="rwTitle">
	   <h2><span>账号安全</span></h2>
	   <div class="accSafety">
         <p class="safetyP"><b>安全级别：</b><#if  is_check_email?if_exists=='0' && is_check_mobile?if_exists=='0'><i class="heightSafety"></i><#elseif is_check_email?if_exists=='0' || is_check_mobile?if_exists=='0'><i class="midSafety"></i><#else><i class="lowSafety"></i></#if><span>建议您启动全部安全设置，以保障账户及资金安全 </span></p>
         <table width="100%" cellpadding="0" cellspacing="0" class="safeTab">
            <tr><th><img src="/include/bmember/images/yVerify.gif" align="absmiddle">登录密码</th><td>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全</td><td class="modTd"><a href="/bmall_Memberuser_updatePw.action?parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}">修改</a></td></tr>
            <tr><th><#if is_check_email?if_exists=='1' || is_check_email?if_exists==''><img src="/include/bmember/images/nVerify.gif" align="absmiddle"><#elseif  is_check_email?if_exists=='0'><img src="/include/bmember/images/yVerify.gif" align="absmiddle"></#if>邮箱验证</th><td>验证后，可用于快速找回登录密码，接收账户余额变动提醒</td><td class="modTd"><#if is_check_email?if_exists=='1' || is_check_email?if_exists==''><a  href="#" onclick="linkToInfo('/bmall_Msgcheck_checkemail.action','memberuser.cust_id=${memberuser.cust_id}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="na">立即验证</a><#elseif  is_check_email?if_exists=='0'><a  href="###" onclick="linkToInfo('/bmall_Memberuser_updateEmail.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">修改</a></#if></td></tr>
            <tr><th><#if is_check_mobile?if_exists=='1' || is_check_mobile?if_exists==''><img src="/include/bmember/images/nVerify.gif" align="absmiddle"><#elseif  is_check_mobile?if_exists=='0'><img src="/include/bmember/images/yVerify.gif" align="absmiddle"></#if>手机验证</th><td>您验证的手机： ${telphoneStr?if_exists}   若已丢失或停用，请立即更换，<span>避免账户被盗</span></td><td class="modTd"><#if is_check_mobile?if_exists=='1' || is_check_mobile?if_exists==''><a  href="###" onclick="linkToInfo('/bmall_Msgcheck_checkmobile.action','memberuser.cust_id=${memberuser.cust_id}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="na">立即验证</a><#elseif  is_check_mobile?if_exists=='0'><a  href="###" onclick="linkToInfo('/bmall_Memberuser_updatePhone.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">修改</a></#if></td></tr>
            <tr><th><#if pay_pass?if_exists==''><img src="/include/bmember/images/nVerify.gif" align="absmiddle"><#else><img src="/include/bmember/images/yVerify.gif" align="absmiddle"></#if>余额密码</th><td>安全程度：<b>低</b>建议您设置更高强度的密码</td><td class="modTd"><#if pay_pass?if_exists!=''><p><a href="/bmall_Memberfund_isNotSetUp.action?parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}" class="cbule">修改</a></p><p><a  href="#" onclick="linkToInfo('/bmall_Memberfund_goPass.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="na">忘记支付密码</a></p><#else><a  href="###" onclick="linkToInfo('/bmall_Memberfund_PayPass.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="na">立即验证</a></#if></td></tr>
         </table>
         
         <div class="tipDiv">
            <h3>安全服务提示</h3>
            <p>1.确认您登录的是${cfg_mallwebname?if_exists}，网址是<a href="${cfg_basehost}">${cfg_basehost}</a>，注意防范进入钓鱼网站，不要轻信各种即时通讯工具
               发送的商品或支付链接，谨防网购诈骗。</p>
            <p>2.建议您安装杀毒软件，并定期更新操作系统等软件补丁，确保账户及交易安全。</p>
         </div>
         
       </div>
	
	</div>


</body>
</html>
