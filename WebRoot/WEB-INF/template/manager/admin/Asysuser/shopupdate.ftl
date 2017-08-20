<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<html>
  <head>
    <title>维护直营店</title>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script type="text/javascript">
    $(document).ready(function(){ 	    
	  loadArea("${areaIdStr?if_exists}","");
    });
 </script>
  </head>
 <body>
<@s.form action="/admin_Asysuser_shopupdate.action" method="post" validate="true">
<div class="postion">当前位置：系统管理 > 会员管理 > 区域代理 > 维护直营店</div>
<div class="rtdcont">
   <!--后台table-->
   <div class="rdtable">
    <table  width="100%" cellspacing="0" cellpadding="0">
       <tr>
         <td class="table_name" width="15%">用户名<font color="red">*</font></td>
         <td width="20%">
         	<@s.textfield name="asysuser.user_name" cssStyle="border:0;background-color:#FFFFFF;" id="sys_user_name" readonly="true"/>
            <@s.hidden name="oldusername" value="${asysuser.user_name}"/>
         </td>
         <td width="15%" class="table_name">门店号:</td>
         <td width="50%">
         	${asysuser.nike_name}
         </td>
         </tr>
       <tr>
         <td class="table_name">联系人<font color="red">*</font></td>
         <td>
            <@s.textfield name="asysuser.real_name" cssClass="txtinput"/>
            <@s.fielderror><@s.param>asysuser.real_name</@s.param></@s.fielderror>
         </td>
         
         <td  class="table_name">公司名称:</td>
             <td>
             	<@s.textfield name="asysuser.com_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.com_name</@s.param></@s.fielderror>
             </td>
       </tr>
        <tr>
            <tr>
             <td class="table_name">所在地区<font color="red">*</font></td>
           <td>
             <div id="areaDiv"></div>
            <@s.fielderror><@s.param>asysuser.detai_area_attr</@s.param></@s.fielderror>
            </td>
             <td class="table_name">详细地址<font color="red">*</font></td>
             <td>
             	<@s.textfield name="asysuser.address" cssClass="txtinput" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             	<@s.fielderror><@s.param>asysuser.address</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
           <td class="table_name"  >固定电话:</td>
		        <td>
		          <@s.textfield name="asysuser.phone" cssClass="txtinput" maxLength="20"/>
		           <@s.fielderror><@s.param>asysuser.phone</@s.param></@s.fielderror>
		   </td>
		   
		             <td class="table_name"  >手机号码<font color="red">*</font></td>
		             <td>
		             	<@s.textfield name="asysuser.cellphone" cssClass="txtinput" maxLength="20"onkeyup="checkNum(this)"/>
		             	<@s.fielderror><@s.param>asysuser.cellphone</@s.param></@s.fielderror>
		             <img class='ltip' src="/include/common/images/light.gif" alt="格式:以13、15、18开头的11位数字">
		             </td>
		             </tr>

                      
                      <td class="table_name" >门店名称<font color="red">*</font></td>
                         <td>
            	            <@s.textfield name="asysuser.store_name" cssClass="common-text" maxLength="99" cssStyle="width:300px;" onkeyup="checkLength(this,99)"/>
             			    <@s.fielderror><@s.param>asysuser.store_name</@s.param></@s.fielderror>
                        </td>
                      
                           
                                <td class="table_name" >门店照片<font color="red">*</font></td>
                                <td>
                                
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
             			             【建议图片宽300px,高300px】
             			          </td>
             		             </tr>
                             	</table>
                             </td>    
                           </tr>
                                  
                            <tr>
                                <td class="table_name" >经营时间：</td>
                                <td>
            	             	 <@s.textfield name="asysuser.store_opentime" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.store_opentime</@s.param></@s.fielderror>
                                </td>
                                
                                 <td class="table_name" >停车服务：</td>
                                <td>
            	             	 <@s.textfield name="asysuser.parking_service" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.parking_service</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <td class="table_name" >公交路线：</td>
                                <td>
            	             	 <@s.textfield name="asysuser.bus_line" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.bus_line</@s.param></@s.fielderror>
                                </td>
                           
                                <td class="table_name" >地铁路线：</td>
                                <td>
            	             	 <@s.textfield name="asysuser.railway_line" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.railway_line</@s.param></@s.fielderror>
                                </td>
                            </tr>
						     <tr>
					            <td class="table_name">邮箱<font color="red">*</font></td>
				                <td  colspan="3">
				             	  <@s.textfield name="asysuser.email" cssClass="txtinput"/>
				         	     <@s.fielderror><@s.param>asysuser.email</@s.param></@s.fielderror>
				         	     <img class='ltip' src="/include/common/images/light.gif" alt="格式:xxxx@xxx.com">
				                 </td>
						     </tr>
				             <tr>
				             <td class="table_name">直营店服务</td>
					           <td  colspan="3">
					            
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
     </table>
   </div>
   <div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
       <@s.token/>
       <@s.hidden name="asysuser.user_id"/>
		<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
       <@s.hidden name="asysuser.role_id"/>
       <@s.hidden name="asysuser.state" />
	   <@s.hidden name="asysuser.agent_type" />
        <@s.hidden name="agent_type_s"/>
       ${listSearchHiddenField?if_exists}
       <@s.hidden name="org_hidden_value" id="org_value"/>
       <@s.submit value="保存"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Asysuser_shoplist.action','');"/>
   </div>
</div>
</@s.form>
  </body>
</html>