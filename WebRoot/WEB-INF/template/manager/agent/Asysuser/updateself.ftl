<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>修改账户信息</title>


</head>
<body>

<script type="text/javascript">

function download_qcode(cfg_mobiledomain,custnum,registertype){
     window.open("/include/download_qcode.jsp?cfg_mobiledomain="+cfg_mobiledomain+"&custnum="+custnum+"&registertype="+registertype,null, "height=380,width=500,status=no,toolbar=no,menubar=no,location=no");
}

</script>

<@s.form action="/agent_Asysuser_update.action" method="post" validate="true" id="modiy_form">
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>基本信息<span class="crumb-step">&gt;</span><span>修改账户信息</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                    <table class="user_tab"  width="100%">
                        <tbody><tr class="tr_tab">
                            <th width="120" class="th_tab">用户名：</th>
                            <td class="td_tab">
                              ${asysuser.user_name}    
                            </td>
                        </tr>
                            <#assign registertype="">
                            <#if asysuser.agent_type=="0">
                                <#assign registertype="1"> 
                            <#elseif asysuser.agent_type=="2">
                                <#assign registertype="2"> 
                            </#if>
                            <tr class="tr_tab">
                                <th class="th_tab">${store_name}：</th>
                                <td class="td_tab">${store_name}号：
                                 	${asysuser.nike_name}
                                <#if asysuser.agent_type!="1">
                                	</br>
                                	${store_name}二维码：<input type="button" value="查看" onclick="download_qcode('${cfg_mobiledomain}','${asysuser.nike_name}','${registertype}');"/>
                                	   
                                	   <@s.hidden value="${cfg_mobiledomain}/webapp/memberuser!webappRegister.action?custnum=${asysuser.nike_name}&registertype=${registertype}" id="code_url"/>
                                	<div  id="qrcode"></div>
                                	
                                </#if>
                                </td>
                                
                            </tr>
                            
                            <tr class="tr_tab">
                                <th class="th_tab">公司名称：</th>
                                <td class="td_tab">
                                  <#if asysuser.com_name?if_exists!="" || asysuser.com_name?if_exists!=null>
                                    ${asysuser.com_name}
                                    <#else>
                                     --
                                    </#if>
             					
                                </td>
                            </tr>
                            
                            
                            <tr class="tr_tab">
                                <th class="th_tab">联系人：</th>
                                <td class="td_tab">
            	             	  <@s.textfield name="asysuser.real_name" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.real_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th><i class="require-red">*</i>邮箱：</th>
                                <td class="td_tab">
            	             	  <@s.textfield name="asysuser.email" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.email</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                             <tr class="tr_tab">
                                <th class="th_tab">固定电话：</th>
                                <td class="td_tab">
            	             	  <@s.textfield name="asysuser.phone" cssClass="common-text"maxLength="20"/>
         						  <@s.fielderror><@s.param>asysuser.phone</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th class="th_tab">手机号码：</th>
                                <td class="td_tab">
            	             	  <@s.textfield name="asysuser.cellphone" cssClass="common-text" maxLength="20"onkeyup="checkNum(this)"/>
             						<@s.fielderror><@s.param>asysuser.cellphone</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             <#if asysuser.agent_type!="2">
                             <tr class="tr_tab">
                                <th class="th_tab">所属地区<i class="require-red">*</i></th>
            	             	 <td class="td_tab">
						              ${area_attr}
                                </td>
                            </tr>
                            </#if>
                        </tbody></table>
            </div>
        </div>

   </div>
   </@s.form>
   <script type="text/javascript" src="/malltemplate/bmall/js/jquery.qrcode.min.js"></script>
   <script type="text/javascript">
	 $(document).ready(function(){
	  var code_url=$("#code_url").val();
	  $('#qrcode').qrcode({width:120,height:120,text:code_url});
	});
	</script>
	
</body>
</html>
