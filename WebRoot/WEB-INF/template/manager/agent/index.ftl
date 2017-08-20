<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>首页</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script>
   function download_qcode(cfg_mobiledomain,custnum,registertype){
     window.open("/include/download_qcode.jsp?cfg_mobiledomain="+cfg_mobiledomain+"&custnum="+custnum+"&registertype="+registertype,null, "height=380,width=500,status=no,toolbar=no,menubar=no,location=no");
}

</script>
 <div class="crumb-wrap"> 
         <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用${cfg_webname}${store_name}</span></div>
       <div class="result-wrap">
            <div class="result-title">
                <h1>基本信息</h1>
            </div>
            <div class="result-content">
                
                <ul class="sys-info-list">
                    <li>
                         <label class="res-lab">用户名</label>
                        <#if user_name?if_exists!="" || user_name?if_exists!=null>
                        <span class="res-info">${(user_name)?if_exists} </span>
                        <#else>
                         - -
                        </#if>
                    </li>
                    
                    <li>
                        <#if (asysuser.agent_type)?if_exists=='1'>
                         <label class="res-lab">区域号</label>
                        <#if asysuser.nike_name?if_exists!="" || asysuser.nike_name?if_exists!=null>
                        <span class="res-info">${(asysuser.nike_name)?if_exists} </span>
                         <#else>
                          - -
                         </#if>
                         </#if>
                        <#if (asysuser.agent_type)?if_exists=='0'>
                         <label class="res-lab">门店号</label>
                            <#if asysuser.nike_name?if_exists!="" || asysuser.nike_name?if_exists!=null>
                             <span class="res-info">${(asysuser.nike_name)?if_exists} </span>
                            <#else>
                             - -
                             </#if>
                         </#if>
                         
                    </li>
                    <li>
                      <label class="res-lab">联系人</label>
                       <#if asysuser.real_name?if_exists!="" || asysuser.real_name?if_exists!=null>
                        <span class="res-info">${(asysuser.real_name)?if_exists} </span>
                        <#else>
                         - -
                        </#if>
                    </li>
                   
                    <li>
                        <label class="res-lab">所属区域</label>
                        <#if area_attr?if_exists!="" || area_attr?if_exists!=null>
                        <span class="res-info">${area_attr}</span>
                        <#else>
                         - -
                        </#if>
                    </li>
                   
                    <li>
                       <label class="res-lab">邮箱</label>
                    <#if asysuser.email?if_exists!="" || (asysuser.email)?if_exists!=null>
                        <span class="res-info">${(asysuser.email)?if_exists} </span>
                     <#else>
                         - -
                        </#if>
                    </li>
                    <li>
                      <label class="res-lab">详细地址</label>
                      
                   <#if (asysuser.address)?if_exists!="" || (asysuser.address)?if_exists!=null>
                   ${(asysuser.address)?if_exists}
                   <#else>--</#if>
                     </li>
                    <li>
                      <label class="res-lab">固定电话</label>
                    <#if asysuser.phone?if_exists!="" || asysuser.phone?if_exists!=null>
                        <span class="res-info">${(asysuser.phone)?if_exists} </span>
                    <#else>
                         - -
                        </#if>
                    </li>
                    <li>
                     <label class="res-lab">手机</label>
                    <#if asysuser.cellphone?if_exists!="" || asysuser.cellphone?if_exists!=null>
                        <span class="res-info">${(asysuser.cellphone)?if_exists} </span>
                    <#else>
                         - -
                        </#if>
                    </li>
                      <#if (asysuser.agent_type)?if_exists=='0' || (asysuser.agent_type)?if_exists=='2'>
                     <li>
                         <label class="res-lab fl">门店二维码</label>
                         <#assign registertype="">
                            <#if asysuser.agent_type=="0">
                                <#assign registertype="1"> 
                            <#elseif asysuser.agent_type=="2">
                                <#assign registertype="2"> 
                            </#if>
                         <@s.hidden value="${cfg_basehost}/webapp/memberuser!webappRegister.action?custnum=${asysuser.nike_name}&registertype=${registertype}" id="code_url"/>
                         <span id="qrcode"></span>
                         <input type="button" value="查看" onclick="download_qcode('${cfg_mobiledomain}','${asysuser.nike_name}','${registertype}');"/>
                    </li>    
                      </#if>
                </ul>
             </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>系统信息</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">最近登录时间</label><span class="res-info">${lastlogintime[0..18]}</span>
                    </li>
                    <li>
                        <label class="res-lab">最近登录ip</label><span class="res-info">${lastIp}</span>
                    </li>
                </ul>
            </div>
        </div>
   </div>
    <script type="text/javascript" src="/malltemplate/bmall/js/jquery.qrcode.min.js"></script>
   <script type="text/javascript">
	 $(document).ready(function(){
	  var code_url=$("#code_url").val();
	  $('#qrcode').qrcode({width:120,height:120,text:code_url});
	});
	</script>
</body>
</html>
