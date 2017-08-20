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
		<link href="/malltemplate/bmall/css/twpublic.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/regist.css" rel="stylesheet" type="text/css"/>
		<link href="/malltemplate/bmall/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/malltemplate/bmall/js/register.js"></script>
</head>
<body>
<!--顶部-->
<#include "/WEB-INF/template/bmall/default/publictop.ftl">
<@s.form action="" method="post">
<!--内容-->
<div class="registb">
    <div class="regist">
        <!--步骤提示-->
        <div class="regVerify">
        </div>
        <!---->
        <div class="regMain">
            <h2>
                <span>
                    会员注册
                </span>
                <p>
                    我已经注册了，现在就
                    <a href="/login.html">
                        登录
                    </a>
                </p>
            </h2>
           
            <div class="regcont">
                <ul class="regul">
                  <#if selway?if_exists='3'||selway?if_exists='1'>
                    <li>
                        <h3>
                            邮箱验证
                        </h3>
                        <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                            <tr>
                                <th width="15%">
                                    <span>
                                        *
                                    </span>
                                    邮箱：
                                </th>
                                <td width="30%">
                               		 <@s.textfield id="email" name="u_email" cssClass="yxtext" onblur="EmailIsNull();" onfocus="EmailForm();"maxLength="30" />
                                </td>
                                <td width="55%">
                                    <p id="emailError">
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
                              		<@s.textfield  id="ce_check" cssStyle="width: 60px;" onblur="cpIsNull();"maxlength="6" cssClass="sjyztext"/> 
                              		<input id="cpe"  type="button" onclick="sendcode();" value="获取验证码" style="width:100px;height:24px;"/>
                                </td>
                                <td>
                                    <img src="/malltemplate/bmall/images/mobile.gif">
                                    &nbsp;验证码在30分钟内有效！
                                    <p id="ceError">
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                </th>
                                <td>
                                    <input type="button" class="yzzhbut"onclick="checkMsgForm();"/>
                                </td>
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
                   <#if selway?if_exists=='3'||selway?if_exists=='0'>
                    <li>
                        <h3>
                            手机验证
                        </h3>
                        <table width="100%" cellpadding="0" cellspacing="0" class="regtab">
                            <tr>
                                <th width="15%">
                                    <span>
                                        *
                                    </span>
                                    手机号码：
                                </th>
                                <td width="30%">
                            		<@s.textfield id="phone" name="phone" cssClass="sjtext"   maxlength="15" onblur="CellphoneIsNull();"  onfocus="CellphoneForm();" />
                                </td>
                                <td width="55%">
                                    <p id="phoneError">
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span>
                                        *
                                    </span>
                                    短信验证码：
                                </th>
                                <td>
                              		<@s.textfield  id="cp_check" cssStyle="width: 60px;" onblur="cpIsNull();"maxlength="6" cssClass="sjyztext"/> 
                              		<input id="cpc"  type="button" onclick="sendcode();" value="获取验证码" style="width:100px;height:24px;"/>
                                </td>
                                <td>
                                   <img src="/malltemplate/bmall/images/mobile.gif">
                                    &nbsp;验证码在30分钟内有效！
                                    <p id="cpError">
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                </th>
                                <td>
                                    <input type="button" class="yzzhbut"onclick="checkMsgForm();"/>
                                </td>
                            </tr>
                        </table>
                    </li>
                  </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="clear"></div>
<!--尾部-->
<@s.hidden  name="user_name" />
<@s.hidden  name="passwd" />
<@s.hidden  name="confirm_pwd" />
<@s.hidden  name="psw_strong"/>
<@s.hidden  name="selway" id="selway"/>
<input type="hidden" id="timeMinus" value="0"/>
 <!--底部开始-->
<#include "/a/bmall/mallbottom.html">
 <!--底部结束-->
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
