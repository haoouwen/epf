<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>修改门店</title>
</head>
<body>
<#include "/include/uploadInc.html">
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<script type="text/javascript">
  $(document).ready(function(){ 	    
	  loadArea("${detail_area_attr?if_exists}","");
  });
  function download_qcode(cfg_mobiledomain,custnum,registertype){
     window.open("/include/download_qcode.jsp?cfg_mobiledomain="+cfg_mobiledomain+"&custnum="+custnum+"&registertype="+registertype,null, "height=380,width=500,status=no,toolbar=no,menubar=no,location=no");
}
 </script>

<@s.form action="/agent_Asysuser_updateStore.action" method="post" validate="true" id="modiy_form">
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
                             <@s.textfield name="asysuser.user_name" cssStyle="border:0;background-color:#FFFFFF;" id="sys_user_name" readonly="true"/>
                              <@s.hidden name="oldusername" value="${asysuser.user_name}"/>
                            </td>
                        </tr>
                             <#assign registertype="">
                            <#if asysuser.agent_type=="0">
                                <#assign registertype="1"> 
                            <#elseif asysuser.agent_type=="2">
                                <#assign registertype="2"> 
                            </#if>
                            <tr class="tr_tab">
                                <th class="th_tab">门店</th>
                                <td class="td_tab">门店号
                                 	${asysuser.nike_name}
                                <#if asysuser.agent_type!="1">
                                	</br>
                                	门店二维码<input type="button" value="查看" onclick="download_qcode('${cfg_mobiledomain}','${asysuser.nike_name}','${registertype}');"/>
                                	   
                                	   <@s.hidden value="${cfg_mobiledomain}/webapp/memberuser!webappRegister.action?custnum=${asysuser.nike_name}&registertype=${registertype}" id="code_url"/>
                                	<div  id="qrcode"></div>
                                </#if>
                                </td>
                                
                            </tr>
                             
                          
                            <tr class="tr_tab">
                                <th  class="th_tab">公司名称：</th>
                                <td  class="td_tab">
                                <@s.textfield name="asysuser.com_name" cssClass="common-text"/>
             					<@s.fielderror><@s.param>asysuser.com_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                           
                            <tr class="tr_tab">
                                <th  class="th_tab">联系人<i class="require-red">*</i></th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.real_name" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.real_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th  class="th_tab">邮箱<i class="require-red">*</i></th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.email" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.email</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             <tr class="tr_tab">
                                <th  class="th_tab">固定电话：</th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.phone" cssClass="common-text"maxLength="20"/>
         						  <@s.fielderror><@s.param>asysuser.phone</@s.param></@s.fielderror>
                                </td>
                            </tr>
                              <tr class="tr_tab">
                                <th  class="th_tab">所属区域<i class="require-red">*</i></th>
                                <td  class="td_tab">
            	             	   ${area_attr?if_exists}
                                </td>
                            </tr>
                             <tr class="tr_tab">
                                <th  class="th_tab">所在地区<i class="require-red">*</i></th>
                                <td  class="td_tab"><div id="areaDiv"></div>
            	             	 <@s.textfield name="asysuser.address" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.address</@s.param></@s.fielderror>
                                
                                <@s.fielderror><@s.param>asysuser.address</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th  class="th_tab">手机号码<i class="require-red">*</i></th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.cellphone" cssClass="common-text" maxLength="20"onkeyup="checkNum(this)"/>
             						<@s.fielderror><@s.param>asysuser.cellphone</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             
                             <tr class="tr_tab">
                                <th  class="th_tab">加盟商等级：</th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.level" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.level</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                             <tr class="tr_tab">
                                <th  class="th_tab">加盟费用：</th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.cost" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.cost</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                             <tr class="tr_tab">
                                <th  class="th_tab">加盟店面积：</th>
                                <td  class="td_tab">
            	             	  <@s.textfield name="asysuser.spread" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.spread</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th  class="th_tab">门店名称<i class="require-red">*</i></th>
                                <td  class="td_tab">
            	             	 <@s.textfield name="asysuser.store_name" cssClass="common-text" maxLength="99" cssStyle="width:300px;" onkeyup="checkLength(this,99)"/>
             					<@s.fielderror><@s.param>asysuser.store_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                          
                            <tr class="tr_tab">
                           
                                <th  class="th_tab">门店图片<i class="require-red">*</i></th>
                                <td  class="td_tab">
                                
                                  <table border="0" cellpadding="0" cellspacing="0">
             		                   <tr>
             			               <td style="padding:0px;">
             			               <div id="fileQueue"></div>
	              			            <@s.textfield name="asysuser.store_img" id="uploadresult" cssStyle="width:200px;"/>
                                        </td>
             			                <td style="padding-left:3px;">
             				            <input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			                  </td>
             			                <td style="padding-left:3px;">
             				             <img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
	              			               <script>uploadOneImg();</script>
             			              </td>
             			           <td> 
             			            <@s.fielderror><@s.param>asysuser.store_img</@s.param></@s.fielderror>
             			          </td>
             		             </tr>
                             	</table>
                             </td>    
                           </tr>
                                  
                            <tr class="tr_tab">
                                <th  class="th_tab">门店经营时间：</th>
                                <td  class="td_tab">
            	             	 <@s.textfield name="asysuser.store_opentime" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.store_opentime</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th  class="th_tab">公交路线：</th>
                                <td  class="td_tab">
            	             	 <@s.textfield name="asysuser.bus_line" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.bus_line</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th  class="th_tab">地铁路线：</th>
                                <td  class="td_tab">
            	             	 <@s.textfield name="asysuser.railway_line" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.railway_line</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr class="tr_tab">
                                <th class="th_tab">停车服务：</th>
                                <td  class="td_tab">
            	             	 <@s.textfield name="asysuser.parking_service" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.parking_service</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            <tr class="tr_tab">
                            <th  class="th_tab">门店服务：</th>
	                        <td  class="td_tab">
		                         <#list storeservceList as store> 
		                  <#if ((asysuser.store_servce)?if_exists?string?index_of((store.store_id)?if_exists?string)>-1)>
		                  <input type="checkbox" name="asysuser.store_servce"    value="${store.store_id }" checked="true"  />${store.store_name }
			        	<#else>
			        	    <input type="checkbox" name="asysuser.store_servce"   value="${store.store_id }" /><font color="green">${store.store_name }</font>
			        	</#if>
			          </#list>
			          <@s.fielderror><@s.param>asysuser.store_servce</@s.param></@s.fielderror>
	                       </td>
	                       </tr>
                             <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
                             <@s.token/>	
					       <@s.hidden name="org_hidden_value" id="org_value"/>
				           <@s.hidden name="asysuser.user_id"/>
		                    <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
                             <@s.hidden name="asysuser.state" />
					          ${listSearchHiddenField?if_exists}
                                
                            <tr class="tr_tab">
                                <th class="th_tab"></th>
                                <td class="td_tab">
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_list.action','');" value="返回" type="button">
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






