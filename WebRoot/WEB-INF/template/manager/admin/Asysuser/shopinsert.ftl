<html>
  <head>
    <title>添加直营店</title>
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
<@s.form action="/admin_Asysuser_shopinsert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:系统管理 > 会员管理 > 区域代理 > 添加直营店
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		     <tr>
             <td class="table_name" width="15%">用户名<font color="red">*</font></td>
             <td width="30%">
             	<@s.textfield name="asysuser.user_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.user_name</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.user_name')}"/>">        
             </td>
             <td width="15%" class="table_name">公司名称:</td>
             <td width="35%">
             	<@s.textfield name="asysuser.com_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.com_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">密码<font color="red">*</font></td>
             <td>
             	<@s.password name="asysuser.passwd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.passwd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">
             </td>
          
             <td class="table_name">确认密码<font color="red">*</font></td>
             <td>
             	<@s.password name="confirmpasswd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">	
             </td>
           </tr>
            <tr>
             
           
             <td class="table_name">联系人<font color="red">*</font></td>
             <td>
                 <@s.textfield name="asysuser.real_name" cssClass="txtinput"/>
                 <@s.fielderror><@s.param>asysuser.real_name</@s.param></@s.fielderror>
             </td>
          
             <td class="table_name">邮箱<font color="red">*</font></td>
             <td>
             	<@s.textfield name="asysuser.email" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.email</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="格式:xxx@xxx.com">
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
            
            <tr>
             <td class="table_name">所在地区<font color="red">*</font></td>
           <td>
            <div id="areaDiv"></div>
            <@s.fielderror><@s.param>asysuser.detai_area_attr</@s.param></@s.fielderror>
            </td>
            
            <td class="table_name"  >详细地址<font color="red">*</font></td>
             <td>
             	<@s.textfield name="asysuser.address" cssClass="txtinput" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             	<@s.fielderror><@s.param>asysuser.address</@s.param></@s.fielderror>
             </td>
            </tr>
             <tr>
                       <td class="table_name" >直营店名称<font color="red">*</font></td>
                                <td>
            	             	 <@s.textfield name="asysuser.store_name" cssClass="common-text" maxLength="99" cssStyle="width:300px;" onkeyup="checkLength(this,99)"/>
             					<@s.fielderror><@s.param>asysuser.store_name</@s.param></@s.fielderror>
                                </td>
                            
                           
                                <td class="table_name" >直营店照片<font color="red">*</font></td>
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
             			            <img class='ltip' src="/include/common/images/light.gif" alt="宽300px,高300px">
             			          </td>
             		             </tr>
                             	</table>
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
                            <td class="table_name" >直营店经营时间：</td >
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
                            
                           <tr> <td class="table_name">直营店服务</td>
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
    <!--操作按钮-->
      <@s.token/>	
	       <@s.hidden name="org_hidden_value" id="org_value"/>
	       <@s.hidden name="asysuser.cust_num"  value="0"/>
	       <@s.hidden name="asysuser.cust_amount" value="0"/>
	       <@s.hidden name="asysuser.state" value="0"/>
	       <@s.hidden name="asysuser.agent_type" value="2"/>
		   <@s.hidden name="asysuser.role_id" value="7"/>
		   <@s.hidden name="asysuser.area_attr" value="000"/>
		    <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Asysuser_shoplist.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>