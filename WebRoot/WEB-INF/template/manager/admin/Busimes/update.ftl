<html>
  <head>
    <title>回复留言</title>
  </head>
  <body>
  <@s.form action="/admin_Busimes_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 店铺管理 > 回复留言 
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		        		   
		           <tr>
		             <td class="table_name">商铺名称:</td>
		             <td>
		             	<@s.label name="cust_name" value="${(member.cust_name)?if_exists}" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>	
		             <td class="table_name">留言人:</td>
		             <td>
		             	<@s.label name="user_name" value="${(memberuser.user_name)?if_exists}" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	            
		           <tr>
		             <td class="table_name">留言内容:</td>
		             <td>
		             	<@s.label name="busimes.msg_content" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">留言日期：</td>
		             <td>
		             	<@s.label name="busimes.msg_date" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">回复内容:</td>
		             <td>
		             	<@s.textarea name="busimes.re_content" cssStyle="width:500px;height:100px" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>busimes.re_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否有效</td>
		             <td>
		              <@s.radio name="busimes.is_enable" list=r"#{'0':'是','1':'否'}"/>
		             </td>
		           </tr>
		           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.hidden name="busimes.trade_id"/>
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Busimes_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

