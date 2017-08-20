<html>
  <head>
    <title>添加区域代理</title>
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
	     checkback("1111111111",1);	    
	  });
	</script>
  </head>
<body>
<@s.form action="/admin_Asysuser_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:系统管理 > 会员管理 > 区域代理 > 添加区域代理
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		     <tr>
             <td class="table_name">所属地区<font color='red'>*</font></td>
             <td>
             <@s.select id="type" name="asysuser.area_attr"  list="areaList" listValue="area_name" listKey="area_number" headerKey="" headerValue="请选择"/>        
               <@s.fielderror><@s.param>asysuser.area_attr</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">用户名<font color="red">*</font></td>
             <td>
             	<@s.textfield name="asysuser.user_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.user_name</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.user_name')}"/>">        
             </td>
           </tr>
           <tr>
             <td class="table_name">密码<font color="red">*</font></td>
             <td>
             	<@s.password name="asysuser.passwd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.passwd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">
             </td>
           </tr>
           <tr>
             <td class="table_name">确认密码<font color="red">*</font></td>
             <td>
             	<@s.password name="confirmpasswd" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Sysuser.passwd')}"/>">	
             </td>
           </tr>
            <tr>
             <td width="19%" class="table_name">公司名称:</td>
             <td>
             	<@s.textfield name="asysuser.com_name" cssClass="txtinput"/>
             	<@s.fielderror><@s.param>asysuser.com_name</@s.param></@s.fielderror>
             </td>
           </tr>
         <#--  <tr>
             <td class="table_name">角色<font color="red">*</font></td>
             <td>
               <@s.select id="type" name="asysuser.role_id"  list="roleList" listValue="role_name" listKey="role_id" headerKey="" headerValue="请选择"/>        
               <@s.fielderror><@s.param>asysuser.role_id</@s.param></@s.fielderror>
             </td>
           </tr>-->
           <tr>
             <td class="table_name">联系人<font color="red">*</font></td>
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
             	<img class='ltip' src="/include/common/images/light.gif" alt="格式：xxx@xxx.com">
             </td>
           </tr>
             <tr>
             <td class="table_name">加盟商等级</td>
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
             <td class="table_name"  >详细地址<font color="red">*</font></td>
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
    <!--操作按钮-->
      <@s.token/>	
	       <@s.hidden name="org_hidden_value" id="org_value"/>
	       <@s.hidden name="asysuser.cust_num"  value="0"/>
	       <@s.hidden name="asysuser.cust_amount" value="0"/>
	       <@s.hidden name="asysuser.state" value="0"/>
	       <@s.hidden name="asysuser.agent_type" value="1"/>
           <@s.hidden name="agent_type_s"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>	       
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Asysuser_list.action?agent_type_s=1','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>