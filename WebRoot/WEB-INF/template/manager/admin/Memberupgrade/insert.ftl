<html>
  <head>
    <title>会员在线升级</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
  </head>
  <body>

<@s.form action="/admin_Memberupgrade_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:会员管理 > 会员管理 > 会员升级 > 会员在线升级
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td width="19%" class="table_name">当前级别<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberupgrade.now_level" cssClass="txtinput" maxLength="11"/>
             	<@s.fielderror><@s.param>memberupgrade.now_level</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">申请级别<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberupgrade.apply_level" cssClass="txtinput" maxLength="11"/>
             	<@s.fielderror><@s.param>memberupgrade.apply_level</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">申请时间<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberupgrade.in_date" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>memberupgrade.in_date</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">申请用户名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberupgrade.user_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>memberupgrade.user_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">审核时间</td>
             <td>
               <@s.textfield name="memberupgrade.audit_date" cssClass="txtinput" cssStyle="width:400px;" maxLength="60"/>
               <@s.fielderror><@s.param>memberupgrade.audit_date</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">审核用户名</td>
             <td>
               <@s.textfield name="memberupgrade.audit_user" cssClass="txtinput" maxLength="20"/>
               <@s.fielderror><@s.param>memberupgrade.audit_user</@s.param></@s.fielderror>
             </td>
           </tr> 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	   <@s.token/> 
       ${listSearchHiddenField?if_exists}     
       <@s.submit value="保存"/>
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberupgrade_list.action','');" />
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>