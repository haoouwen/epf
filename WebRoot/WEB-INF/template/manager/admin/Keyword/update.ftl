<html>
  <head>
    <title>修改关键字</title>
  </head>
  <body>
<@s.form action="/admin_Keyword_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置--> 当前位置:网站管理 > 基本功能 > 关键字管理 > 修改关键字
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		   
           <tr>
             <td class="table_name">关键字名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="keyword.key_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>keyword.key_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">关键字类型<font color='red'>*</font></td>
             <td>
             <@s.select name="keyword.module_type" list="commparaList" listValue="module_name" listKey="module_type" />
             </td>
           </tr>
             <tr>
             <td class="table_name">关键字搜索频率<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="keyword.num" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>keyword.num</@s.param></@s.fielderror>
             </td>
           </tr>
           
             <tr>
             <td class="table_name">前台显示<font color='red'>*</font></td>
             <td>
             	<@s.radio name="keyword.is_show" list=r"#{'0':'显示','1':'不显示'}" />
             </td>
           </tr>
           
             <tr>
             <td class="table_name">搜索IP<font color='red'>*</font></td>
             <td>
                ${(keyword.m_ip)?if_exists}
             </td>
           </tr>
           
             <tr>
             <td class="table_name">搜索时间<font color='red'>*</font></td>
             <td>
             	${(keyword.in_date)?if_exists}
             </td>
           </tr>
           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    		<@s.token/>
	       <@s.hidden name="keyword.key_id"/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Keyword_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>