<html>
  <head>
    <title>设置通过人新密码</title>
    <script type="text/javascript" src="/include/admin/js/menberuser.js"></script>
  </head>
  <body>
  <@s.form action="/admin_Capitalmanagement_setNewPass.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:财务统计 >财务管理 >  设置通过人新密码
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		           <tr>
			             <td class="table_name" style="width:220px;" height="60px;">通过人:</td>
			             <td>
			             		${capitalmanagement.pass_men?if_exists}
			             </td>
		           </tr>
	                 
             <tr>
			         <td  class="table_name">新密码<font color="red">*</font></td>
			         <td>
			    		<@s.password name="newpasswd" cssClass="winput" maxLength="18" onkeyup="javascript:checkpass(this)"  id="pass" onblur="setpswstrong(this)"/>
			    		<@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror>
			    		<@s.label id="password_label" cssClass="winput"  maxLength="32" value=""/>
			        </td>
            </tr>
            <tr>
							 
            </tr>
            <tr>
		            <td  class="table_name">确认新密码<font color="red">*</font></td>
		            <td>
		        		<@s.password name="confirmpasswd" cssClass="winput" maxLength="18"/>
		        		<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
		        		<img class="ltip" src="/include/common/images/light.gif" alt="密码长度最小6位，最大18位，两次输入须一致！">
		            </td>
            </tr>  
		 
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	   <@s.hidden name="capitalmanagement.trade_id"/>
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Capitalmanagement_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

