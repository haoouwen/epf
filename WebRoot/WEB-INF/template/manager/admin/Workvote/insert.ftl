<html>
  <head>
    <title>投票</title>
  </head>
  <body>
  
  <@s.form action="/admin_Workvote_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置:商城管理 > 商城活动 > 投票
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		 <tr>
	             <td class="table_name">随机码<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="workvote.radom_code" cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>workvote.radom_code</@s.param></@s.fielderror>
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
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Workvote_list.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

