<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>修改门店</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<#include "/include/uploadInc.html">
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<@s.form action="/agent_Asysuser_listcode.action" method="post" validate="true" id="modiy_form">
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>门店管理<span class="crumb-step">&gt;</span><span>修改门店</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                    <table class="user_tab" width="100%">
                        <tbody>
                         <tr class="tr_tab">
                            <th class="th_tab"  width="120">用户名：</th>
                            <td class="td_tab">
                             ${(asysuser.user_name)?if_exists}
                            </td>
                        </tr>
                             <#assign registertype="">
                            <#if asysuser.agent_type=="0">
                                <#assign registertype="1"> 
                            <#elseif asysuser.agent_type=="2">
                                <#assign registertype="2"> 
                            </#if>
                            <tr class="tr_tab">
                                <th class="th_tab">门店：</th>
                                <td class="td_tab">门店号：
                                 	${(asysuser.nike_name)?if_exists}
                                <#if asysuser.agent_type!="1">
                                	</br>
                                	门店二维码：
                                	   
                                	   <@s.hidden value="${cfg_mobiledomain}/webapp/memberuser!webappRegister.action?custnum=${asysuser.nike_name}&registertype=${registertype}" id="code_url"/>
                                	<div  id="qrcode"></div>
                                </#if>
                                </td>
                                
                            </tr>
                            <tr>
                                <th class="th_tab">公司名称：</th>
                                <td class="td_tab">
                                <#if (asysuser.com_name)?if_exists!=null && (asysuser.com_name)?if_exists!="" > 
                                ${(asysuser.com_name)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                            
                           
                            <tr>
                                <th class="th_tab">联系人：</th>
                                <td class="td_tab">
                                 <#if (asysuser.real_name)?if_exists!=null && (asysuser.real_name)?if_exists!="">
                                ${(asysuser.real_name)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="th_tab">邮箱：</th>
                                <td class="td_tab"> 
                                <#if (asysuser.email)?if_exists!=null && (asysuser.email)?if_exists!="">
                                ${(asysuser.email)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                             <tr>
                                <th class="th_tab">固定电话：</th>
                                <td class="td_tab">
                                <#if (asysuser.phone)?if_exists!=null && (asysuser.phone)?if_exists!="">
                                ${(asysuser.phone)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                 </#if>
                                </td>
                            </tr>
                              <tr>
                                <th class="th_tab">所在地区：</th>
                                <td class="td_tab"><#if (asysuser.detai_area_attr)?if_exists!=null && (asysuser.detai_area_attr)?if_exists!="">
            	             	    ${(asysuser.detai_area_attr)?if_exists}
            	             	    <#else>
            	             	    <font color="grey">暂无内容</font>
            	             	    </#if>
                                </td>
                            </tr>
                             <tr>
                                <th class="th_tab">详细地址：</th>
                                <td class="td_tab">
                                <#if (asysuser.address)?if_exists!=null && (asysuser.address)?if_exists!="">
                                ${(asysuser.address)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="th_tab">手机号码：</th>
                                <td class="td_tab">
                                <#if (asysuser.cellphone)?if_exists!=null && (asysuser.cellphone)?if_exists!="">
                                ${(asysuser.cellphone)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                             
                             <tr>
                                <th class="th_tab">加盟商等级：</th>
                                <td class="td_tab">
                                 <#if (asysuser.level)?if_exists!=null && (asysuser.level)?if_exists!="">
            	             	  ${(asysuser.level)?if_exists}
            	             	  <#else>
            	             	  <font color="grey">暂无内容</font>
            	             	  </#if>
                                </td>
                            </tr>
                            
                             <tr>
                                <th class="th_tab">加盟费用：</th>
                                <td class="td_tab">
                                <#if (asysuser.cost)?if_exists!=null && (asysuser.cost)?if_exists!="">
                                ${(asysuser.cost)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                            
                             <tr>
                                <th class="th_tab">加盟店面积：</th>
                                <td class="td_tab">
                                 <#if (asysuser.spread)?if_exists!=null && (asysuser.spread)?if_exists!="">
                                 ${(asysuser.spread)?if_exists}
                                 <#else>
                                 <font color="grey">暂无内容</font>
                                 </#if>
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="th_tab">门店名称：</th>
                                <td class="td_tab">
                                <#if (asysuser.store_name)?if_exists!=null && (asysuser.store_name)?if_exists!="">
                                ${(asysuser.store_name)?if_exists}
                                <#else>
                                <font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                          
                            <tr>
                           
                                <th class="th_tab">门店照片：</th>
                                <td class="td_tab">
                                 <#if (asysuser.store_img)?if_exists!=null && (asysuser.store_img)?if_exists!="">   
                                <a href="${asysuser.store_img?if_exists}" target="_blank"> <img src="${(asysuser.store_img)?if_exists}" width="80px" height="80px"/></a>
                                <#else>
                                <font color="grey">暂无图片</font>
                                </#if>
                             </td>    
                           </tr>
                                  
                            <tr>
                                <th class="th_tab">门店经营时间：</th>
                                <td class="td_tab">
                                 <#if (asysuser.store_opentime)?if_exists !=null && (asysuser.store_opentime)?if_exists!="">
            	             	${(asysuser.store_opentime)?if_exists}
            	             	<#else>
            	             	<font color="grey">暂无内容</font>
                                </#if>
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="th_tab">公交路线：</th>
                                <td class="td_tab">
                                  <#if (asysuser.bus_line)?if_exists!=null && (asysuser.bus_line)?if_exists!="">
            	             	 ${(asysuser.bus_line)?if_exists}
            	             	 <#else>
            	             	 <font color="grey">暂无内容</font>
            	             	 </#if>
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="th_tab">地铁路线：</th>
                                <td class="td_tab">
                                <#if (asysuser.railway_line)?if_exists!=null && (asysuser.railway_line)?if_exists!="">
            	             	   ${(asysuser.railway_line)?if_exists}
            	             	   <#else>
            	             	   <font color="grey">暂无内容</font>
            	             	   </#if>
                                </td>
                            </tr>
                            
                            <tr>
                                <th class="th_tab">停车服务：</th>
                                <td class="td_tab"><#if (asysuser.parking_service)?if_exists!=null && (asysuser.parking_service)?if_exists!=""> 
            	             	 ${(asysuser.parking_service)?if_exists}
            	             	 <#else>
            	             	 <font color="grey">暂无内容</font>
                                  </#if> 
                                </td>
                            </tr>
                            <tr>
                                <th class="th_tab">状态：</th>
                                <td class="td_tab"><#if (asysuser.state)?if_exists=='0'>
                                     <font color="green">启用</font>
                                     <#else>
                                     <font color="red">禁用</font>
                                     </#if>
                                </td>
                            </tr>
                            <tr>
                            <th class="th_tab">门店服务</th>
	                        <td class="td_tab">
	                        
	                          <#list storeservceList as store> 
		                         <#if store.store_id==asysuser.store_servce>
                                ${store.store_name}
			                    </#if> 
			                   </#list>
	                       </td>
	                       </tr>
                            <@s.token/>	
					       <@s.hidden name="org_hidden_value" id="org_value"/>
				            <@s.hidden name="asysuser.user_id"/>
					         <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
					         <@s.hidden name="asysuser.state" value="${(asysuser.state)?if_exists}"/>
					          ${listSearchHiddenField?if_exists}
                                
                            <tr class="tr_tab">
                                <th class="th_tab"></th>
                                <td class="td_tab">
                                    <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_listcode.action','');" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody>
                        </table>
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
