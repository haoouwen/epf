<html>
  <head>
    <title>修改敏感字</title>
  </head>
  <body>

<@s.form action="/admin_Filterword_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:系统管理 > 系统管理 > 敏感字管理  > 修改敏感字
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td width="19%" class="table_name">敏感字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="filterword.name" cssClass="txtinput" style="width:250px;" maxLength="20"/>
             	<@s.fielderror><@s.param>filterword.name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">替换字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="filterword.rep_name" cssClass="txtinput" style="width:250px;" maxLength="20"/>
             	<@s.fielderror><@s.param>filterword.rep_name</@s.param></@s.fielderror>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    		<@s.token/>
	       <@s.hidden name="filterword.word_id"/>
	       <@s.hidden name="oldinfotitle" value="${(filterword.name)?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Filterword_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>