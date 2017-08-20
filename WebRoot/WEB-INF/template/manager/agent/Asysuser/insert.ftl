<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增门店</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<#include "/include/uploadInc.html">
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<script type="text/javascript">
  $(document).ready(function(){ 	    
	  loadArea("${detail_area_attr?if_exists}","");
  });
 </script>
<@s.form action="/agent_Asysuser_insert.action" method="post" validate="true" id="modiy_form">
 <div class="crumb-wrap">
      
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>门店管理<span class="crumb-step">&gt;</span><span>新增门店</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                         
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120">用户名<i class="require-red">*</i></th>
                            <td>
                               <@s.textfield name="asysuser.user_name" cssClass="common-text"/>
             					<@s.fielderror><@s.param>asysuser.user_name</@s.param></@s.fielderror>
             					<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.user_name')}"/>">        
                            </td>
                        </tr>
                            <tr>
                                <th>密码<i class="require-red">*</i></th>
                                <td>
                                	<@s.password name="asysuser.passwd" cssClass="common-text"/>
							     	<@s.fielderror><@s.param>asysuser.passwd</@s.param></@s.fielderror>
							     	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">
                                </td>
                            </tr>
                            <tr>
                                <th>确认密码<i class="require-red">*</i></th>
                                <td>
                                	<@s.password name="confirmpasswd" cssClass="common-text"/>
					             	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
					             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">	
                                </td>
                            </tr>
                            <tr>
                                <th>公司名称：</th>
                                <td>
                                <@s.textfield name="asysuser.com_name" cssClass="common-text"/>
             					<@s.fielderror><@s.param>asysuser.com_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                           
                            <tr>
                                <th>联系人<i class="require-red">*</i></th>
                                <td>
            	             	  <@s.textfield name="asysuser.real_name" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.real_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>邮箱<i class="require-red">*</i></th>
                                <td>
            	             	  <@s.textfield name="asysuser.email" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.email</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             <tr>
                                <th>固定电话：</th>
                                <td>
            	             	  <@s.textfield name="asysuser.phone" cssClass="common-text"maxLength="20"/>
         						  <@s.fielderror><@s.param>asysuser.phone</@s.param></@s.fielderror>
                                </td>
                            </tr>
                              <tr>
                                <th>所在地区<i class="require-red">*</i></th>
                                <td>
            	             	<div id="areaDiv"></div>
             					<@s.fielderror><@s.param>asysuser.address</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             <tr>
                                <th>详细地址<i class="require-red">*</i></th>
                                <td>
            	             	 <@s.textfield name="asysuser.address" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.address</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>手机号码<i class="require-red">*</i></th>
                                <td>
            	             	  <@s.textfield name="asysuser.cellphone" cssClass="common-text" maxLength="20"onkeyup="checkNum(this)"/>
             						<@s.fielderror><@s.param>asysuser.cellphone</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             
                             <tr>
                                <th>加盟商等级：</th>
                                <td>
            	             	  <@s.textfield name="asysuser.level" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.level</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                             <tr>
                                <th>加盟费用：</th>
                                <td>
            	             	  <@s.textfield name="asysuser.cost" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.cost</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                             <tr>
                                <th>加盟店面积：</th>
                                <td>
            	             	  <@s.textfield name="asysuser.spread" cssClass="common-text"/>
         						  <@s.fielderror><@s.param>asysuser.spread</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>门店名称<i class="require-red">*</i></th>
                                <td>
            	             	 <@s.textfield name="asysuser.store_name" cssClass="common-text" maxLength="99" cssStyle="width:300px;" onkeyup="checkLength(this,99)"/>
             					<@s.fielderror><@s.param>asysuser.store_name</@s.param></@s.fielderror>
                                </td>
                            </tr>
                          
                            <tr>
                           
                                <th>门店照片<i class="require-red">*</i></th>
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
             			          </td>
             		             </tr>
                             	</table>
                             </td>    
                           </tr>
                                  
                            <tr>
                                <th>门店经营时间：</th>
                                <td>
            	             	 <@s.textfield name="asysuser.store_opentime" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.store_opentime</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>公交路线：</th>
                                <td>
            	             	 <@s.textfield name="asysuser.bus_line" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.bus_line</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>地铁路线：</th>
                                <td>
            	             	 <@s.textfield name="asysuser.railway_line" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.railway_line</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                            <tr>
                                <th>停车服务：</th>
                                <td>
            	             	 <@s.textfield name="asysuser.parking_service" cssClass="common-text" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,200)"/>
             					<@s.fielderror><@s.param>asysuser.parking_service</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            
                              <tr>
                              <th class="table_name">门店服务</th>
	                             <td>
	             	              <@s.checkboxlist name="asysuser.store_servce" list="storeservceList" listValue="store_name" listKey="store_id" headerKey="" headerValue="请选择"/>
	                              <@s.fielderror><@s.param>asysuser.store_servce</@s.param></@s.fielderror>
	                             </td>
                              </tr>
                            
                            <@s.hidden name="asysuser.agent_type" value="0"/>
                            <@s.token/>	
					       <@s.hidden name="org_hidden_value" id="org_value"/>
					        <@s.hidden name="asysuser.cust_num"  value="0"/>
					         <@s.hidden name="asysuser.cust_amount" value="0"/>
					             <@s.hidden name="asysuser.role_id" value="7"/>
					             <@s.hidden name="asysuser.state" value="0"/>
					         <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
					          ${listSearchHiddenField?if_exists}
       
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_list.action','');" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
            </div>
        </div>

   </div>
   </@s.form> 
</body>
</html>
