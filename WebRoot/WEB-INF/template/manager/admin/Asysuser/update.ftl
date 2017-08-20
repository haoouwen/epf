<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
<html>
  <head>
    <title>维护区域代理</title>
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
  </head>
 <body>
<@s.form action="/admin_Asysuser_update.action" method="post" validate="true">
<div class="postion">当前位置：系统管理 > 会员管理 > 区域代理 > 维护区域代理</div>
<div class="rtdcont">
   <!--后台table-->
   <div class="rdtable">
    <table  width="100%" cellspacing="0" cellpadding="0">
       <tr>
             <td class="table_name">所属地区<font color='red'>*</font></td>
             <td>
             	${area_attr?if_exists}
             </td>
           </tr>
       <tr>
         <td class="table_name">用户名<font color="red">*</font></td>
         <td>
         	<@s.textfield name="asysuser.user_name" cssStyle="border:0;background-color:#FFFFFF;" id="sys_user_name" readonly="true"/>
            <@s.hidden name="oldusername" value="${asysuser.user_name}"/>
         </td>
       </tr>
       
         <td width="19%" class="table_name">区域号:</td>
         <td>
         	${asysuser.nike_name}
         </td>
         </tr>
        
       <tr>
             <td width="19%" class="table_name">公司名称:</td>
             <td>
             	<@s.textfield name="asysuser.com_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.com_name</@s.param></@s.fielderror>
             </td>
           </tr>
       <tr>
         <td class="table_name">联系人:</td>
         <td>
            <@s.textfield name="asysuser.real_name" cssClass="txtinput"/>
            <@s.fielderror><@s.param>asysuser.real_name</@s.param></@s.fielderror>
         </td>
       </tr>
       <tr>
         <td class="table_name">邮箱<font color="red">*</font></td>
         <td>
         	<@s.textfield name="asysuser.email" cssClass="txtinput"/>
         	<@s.fielderror><@s.param>asysuser.email</@s.param></@s.fielderror>
         	<img class='ltip' src="/include/common/images/light.gif" alt="格式:xxx@xxx.com">
         </td>
        </tr>
             <tr>
             <td class="table_name">加盟商等级<</td>
             <td>
             	<@s.textfield name="asysuser.level" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.level</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">加盟费用</td>
             <td>
             	<@s.textfield name="asysuser.cost" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.cost</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">加盟店面积</td>
             <td>
             	<@s.textfield name="asysuser.spread" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.spread</@s.param></@s.fielderror>
             </td>
          
           
           <tr>
       
        <tr>
             <td class="table_name"  >详细地址</td>
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
		           </tr>
	           
		           <tr>
		             <td class="table_name"  >手机号码<font color="red">*</font></td>
		             <td>
		             	<@s.textfield name="asysuser.cellphone" cssClass="txtinput" maxLength="20"onkeyup="checkNum(this)"/>
		             	<@s.fielderror><@s.param>asysuser.cellphone</@s.param></@s.fielderror>
		             	<img class='ltip' src="/include/common/images/light.gif" alt="格式:以13、15、18开头的11位数字">
		             </td>
		           </tr>
     </table>
   </div>
   <div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
       <@s.token/>
       <@s.hidden name="asysuser.user_id"/>
       <@s.hidden name="org_hidden_value" id="org_value"/>
	       <@s.hidden name="asysuser.cust_num" />
	       <@s.hidden name="asysuser.cust_amount"/>
	       <@s.hidden name="asysuser.state"/>
	       <@s.hidden name="asysuser.agent_type"/>
           <@s.hidden name="agent_type_s"/>
       <@s.submit value="保存"/>
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Asysuser_list.action?agent_type_s=1','');"/>
   </div>
</div>
</@s.form>
  </body>
</html>