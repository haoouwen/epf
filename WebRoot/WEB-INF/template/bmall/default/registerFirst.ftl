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
		<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
<@s.form action="" method="post">
<!--内容-->
<div class="registb">
    <div class="regist">
        <!--步骤提示-->
        <!---->
        <div class="regMain">
        <h2><span>会员注册</span><a href="/">返回首页</a></h2>
            <div class="regcont">
                <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                    <tr>
                        <th width="15%"><span>*</span>用户名：</th>
                        <td width="32%">
                            <@s.textfield id="user_name" name="user_name" cssClass="zctext" onblur="UserNameIsNull();" onfocus="UserNameForm();" maxLength="20"/>
                        </td>
                        <td width="53%"><p id="user_nameError"></p>
                        </td>
                    </tr>
                    <tr>
                        <th><span>*</span>请输入密码：</th>
                        <td>
                        	<@s.password id="passwd" name="passwd" cssClass="mmtext"  onfocus="PasswordForm();"maxLength="20"  onkeyup="javascript:checkpass(this)"onblur="setpswstrong(this);" />
        					<@s.hidden name="psw_strong"  id="psw_strong" />
                        </td>
                        <td><p id="passwdError"></p></td>
                    </tr>
                     <tr id="pswState" class="pswState" ><th></th><td valign="top"><b id="ps_s"></b></td></tr>
                    <tr>
                        <th>
                            <span>*</span>请确认密码：
                        </th>
                        <td>
                      		  <@s.password id="confirm_pwd" name="confirm_pwd" cssClass="mmtext" onblur="Confirm_pwdIsNull();" onfocus="Confirm_pwdForm()" maxLength="20"/>
                        </td>
                        <td><p id="confirm_pwdError"></p></td>
                    </tr>
                     <tr>
                        <th><span> *</span>所属地区：</th>
                        <td>
                           <@s.select id="membernum"  name="membernum"  list="areaList" listValue="area_name"
						       listKey="area_number"   headerKey="" headerValue="--请选择地区--" value=""/>
						       说明：地区注册之后不可修改
                        </td>
                        <td><p id="areaError"></p></td>
                    </tr>                    
                    
                </table>
                <ul class="regul">
                	 <#if selway?if_exists=='3'||selway?if_exists=='0'>
                    <li>
                        <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                            <tr>
                                <th width="15%"><span>*</span>手机号码：</th>
                                <td width="32%">
                            		<@s.textfield id="phone" name="phone" cssClass="sjtext"   maxlength="15" onblur="CellphoneIsNull();"  onfocus="CellphoneForm();" />
                                </td>
                                <td width="53%"><p id="phoneError"></p>
                                   <#if selway?if_exists=='3'> <span class="yxyz">邮箱验证</span></#if>
                                </td>
                            </tr>
                             <tr>
                               <th ><span>*</span> 验证码：</th>
                                   <td  >
                                    	<@s.textfield name="commentrand_phone" maxLength="4" cssClass="sjyztext" id="commentrand_phone"/>
						                <img src="/imgrand.action" class="sjyrand"  onclick="changeValidateCode(this)" id="validateCode"/>
                                </td>
                                   <td>
                                   <p id="randsphoneError"></p>
                                   <span>看不清？<a class="flk13"  onclick="changeValidateCode('validateCode')"  href="javascript:void(0)">换一张</a></span></td>
                            </tr>
                            <tr>
                                <th>
                                    <span>*</span>短信验证码：
                                </th>
                                <td>
                              		<@s.textfield  id="cp_check" maxlength="6" cssClass="sjyztext"/> 
                              		<a id="cpc" href="###"  onclick="sendcode();" >获取短信验证码</a>
                                </td>
                                <td><p id="cpError"></p></td>
                            </tr>
                        </table>
                    </li>

	              </#if>
                  <#if selway?if_exists='3'||selway?if_exists='1'>
                    <li>
                        <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                            <tr>
                                <th width="15%">
                                    <span>*</span>邮箱：
                                </th>
                                <td width="32%">
                               		 <@s.textfield id="email" name="u_email" cssClass="yxtext" onblur="EmailIsNull();" onfocus="EmailForm();"maxLength="30" />
                                </td>
                                <td width="53%">
                                    <p id="emailError"></p>
                                	<#if selway?if_exists=='3'>  <span class="sjyz">手机验证</span></#if>
                                </td>
                            </tr>
                               <tr>
                               <th>
                                     <span>*</span>验证码：
                               </th>
                                   <td>
                                    	<@s.textfield name="commentrand" maxLength="4" cssClass="sjyztext" id="commentrand"/>
						                <img src="/imgrand.action" class="sjyrand"  onclick="changeValidateCode(this)"/>
                                </td>
                                <td>   <p id="randsmailError"></p>
                                   <span>看不清？<a class="flk13"  onclick="changeValidateCode('validateCode')"  href="javascript:void(0)">换一张</a></span></td>
                            </tr>
                              <tr>
                                <th>
                                    <span>*</span>邮箱验证码：
                                </th>
                                <td>
                              		<@s.textfield  id="ce_check"  maxlength="6" cssClass="sjyztext"/> 
                               		<a id="cpe" href="###"  onclick="sendcode();" >获取邮箱验证码</a>
                                </td>
                                <td><p id="ceError"></p></td>
                            </tr>
                            <tr> 
                               <th></th>
                               <td colspan="2">
                                      友情提醒：如未收到验证码邮件，建议您可以认真查看邮箱(如：垃圾邮箱等)或者重新点击发送验证码。
                               </td>
                            </tr>
                        </table>
                    </li>
                     
                   </#if>
                </ul>
                <div class="protocol">
                    <p>
                        <input type="checkbox" id="agreeId" checked="checked"/>
                        <span class="proSpan">
                            我已阅读并同意<a >《${cfg_webname}注册协议》</a>
                        </span>
                        <div class="proDiv">${serviceterms?if_exists}</div>
                    </p>                    
                       <p><input type="button" class="proBut" onclick="registerForm();"/></p>
                </div>
                
               <!--其他方式登录-->
               <div class="otherLogin">
                 <p>如果您已有账号</br><a href="/login.html">请点击登录</a></p>
                 <p>如果您有QQ或微信，可免注册直接登录</p>
                 <p><a href="/mall/memberuser!qqlogin.action" title="QQ" target="_blank"><img src="/malltemplate/bmall/images/reg_qq_logo.gif"></a></p>
                 <p><a href="javascript:open_weixin()" title="weixin"><img src="/malltemplate/bmall/images/reg_weixin_logo.gif"></a></p>
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

<@s.hidden  name="is_phone_email" id="is_phone_email" value="0"/>
 <@s.hidden  name="selway" id="selway"/>
 <@s.hidden  name="registertype" value="1"/>
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
