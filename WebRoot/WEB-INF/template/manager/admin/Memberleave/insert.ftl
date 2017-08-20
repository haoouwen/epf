<html>
  <head>
    <title>留言</title>
  </head>
  <body>
<@s.form action="/admin_Memberleave_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->   当前位置:会员管理 > 企业相关 > 留言管理 > 留言
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name">留言标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="memberleave.title" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>memberleave.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">接收人<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="cust_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">留言内容<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="memberleave.content" cssClass="txtinput" onkeyup="checkLength(this,300);" cssStyle="width:400px;height:100px"/>
             	<@s.fielderror><@s.param>memberleave.content</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">信息来源：</td>
             <td>
             	<@s.textfield name="memberleave.source" cssClass="txtinput" maxLength="20"/>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    <@s.token/>
	       <@s.hidden name="nav_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Memberleave_list.action';document.forms[0].submit();"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
  </body>
</html>