<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="x-ua-compatible" content="ie=7" />
        <title>
            会员登录-${cfg_webname?if_exists}
        </title>
        <script src="/wro/bmall_login.js" type="text/javascript"></script>
        <script src="/malltemplate/bmall/js/jt_public.js" type="text/javascript"></script>
      <link href="/wro/bmall_login.css" rel="stylesheet" type="text/css"/>
      <link href="/malltemplate/bmall/css/login.css" rel="stylesheet" type="text/css">
    </head>
    <body>
<@s.form action="/mall/memberuser!loginUser.action"  method="post"  validate="true"  id="login">

<#include "/WEB-INF/template/bmall/default/publictop.ftl">
        <!--内容-->
        <div class="loginb">
        
            <div class="login1">
            
                <div class="advertising">
                   <a  href="${(advinfo.link_url)?if_exists}"  target="_blank">
                    <img src="${(advinfo.img_path)?if_exists}" width="570" height="340" />
                   </a>
                </div>
               
                    <div class="logincont">
                        <table cellpadding="0" cellspacing="0" class="logintab">
                            <tr>
                                <td>
                                    <p>用户名/邮箱/手机号码：</p>
                                    <p>
                                        <@s.textfield id="user_name" name="memberuser.user_name" maxLength="20"cssClass="zctext" />
                                      <span <#if i_username=='1'|| i_username=='2'> class="prompt"</#if>      >
                                            <@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror>
                                       <@s.fielderror><@s.param>login_limit</@s.param></@s.fielderror>
                                       </span>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p>密码：</p>
                                    <p>
                                     <@s.password id="passwd" name="memberuser.passwd" maxLength="20"  cssClass="mmtext"/>
               					 <span <#if i_password=='1' > class="prompt"</#if>    > 	<@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror> </span>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p> 验证码：</p>
                                    <p>
                                    	<@s.textfield name="commentrand" maxLength="4" cssClass="yztext f_left"/>
						                <img src="/imgrand.action" class="yzmPic"  onclick="changeValidateCode(this)"/>
						               <#if i_c=='1'><img src="/malltemplate/bmall/images/del_mem.jpg"></#if>    
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td class="logintd">
                                    <p>
                                        <span>
                                            <input type="checkbox" name="mallusername"  id="mallusername"/>记住用户名
                                        </span>
                                        <a href="/mall/member!executepwd.action">忘记密码</a>
                                    </p>
                                    <p>
                                    	<input type="hidden" name="loc" id="refloc" value=""/>
                                    	<input type="button" class="loginbut" onclick="sunbmit()" value="登 录"  id="login_user"/>
                                    </p>
                                    <p class="hzP"> 合作账号登录：<a href="/mall/memberuser!qqlogin.action" title="QQ" target="_blank"><img src="/malltemplate/bmall/images/qq_logo.png"/></a> <a href="javascript:open_weixin()" title="weixin"><img src="/malltemplate/bmall/images/weixinlogo2.png" /></a></p>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="regbut">
                        <a href="/mall/member!joinus.action"></a>
                    </div>
              </div>
        </div>
        <div class="clear">
        </div>
        <@s.hidden name="refloc"/>
        <@s.hidden name="state" id="state"/>
 		  <#include "/a/bmall/mallbottom.html">
        </@s.form>
    </body>

</html>