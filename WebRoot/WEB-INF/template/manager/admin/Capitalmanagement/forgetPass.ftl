<html>
  <head>
    <title>找回通过人密码</title>
  </head>
  <body>
  <@s.form action="/admin_Capitalmanagement_forgetUpdate.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:财务统计 >财务管理 >  找回通过人密码
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
	            	 <td class="table_name">密保问题:</td>
	           		 <td>
	        				${capitalmanagement.question?if_exists}
	      			 </td>
  			 </tr>
                        
             <tr>
			         <td  class="table_name">密保答案<font color="red">*</font></td>
			         <td>
			    		<@s.textarea name="answer"  cssClass="txtinput" maxLength="50" cssStyle="width:500px;height:20px;"/>
			    		<@s.fielderror><@s.param>answer</@s.param></@s.fielderror>
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

