<html>
  <head>
    <title>添加配送摸版</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
  
  <@s.form action="/admin_Smodetemplete_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
当前位置:商城管理 > 配送管理 > 添加配送摸版
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
		             <td class="table_name">模板内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="smodetemplete.smode_templete" cssClass="txtinput" cssStyle="width:400px;height:150px" onkeyup="checkLength(this,10000)"/>
		             	<@s.fielderror><@s.param>smodetemplete.smode_templete</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">标签解释：</td>
		             <td>
		             	<@s.textarea name="smodetemplete.tag" cssClass="txtinput" cssStyle="width:400px;height:150px" onkeyup="checkLength(this,300)"/>
		             	<@s.fielderror><@s.param>smodetemplete.tag</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
       <@s.hidden name="smodetemplete.smode_id" value="${smode_id?if_exists}" />
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
       ${listSearchHiddenField?if_exists}
       <@s.token/> 
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Smodetemplete_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

