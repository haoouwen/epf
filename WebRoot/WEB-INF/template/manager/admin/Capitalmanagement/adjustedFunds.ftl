<html>
  <head>
    <title>调整运营商资金</title>
 	  <script type="text/javascript" src="/include/common/js/common.js"></script>
 	   <script type="text/javascript" src="/include/admin/js/adjusteFund.js"></script>
  </head>
<body>
<div class="postion">
  <!--当前位置-->
  当前位置:财务管理 > 财务管理 > 余额管理 > 调整运营商资金
</div>
<div class="rtdcont">
	<div class="rdtable">
		<@s.hidden name=" trueToAdjus" id="trueToAdjus"/>
		<@s.hidden name=" ssuccessToAdjus" id="ssuccessToAdjus"/>
		
   <#if trueToAdjus==true>
   	<div id="zjgl"style="display:none;border:dotted 5px red;" class="zjgl">
	<@s.form action="/adminAdjustedFundUpdate.action" method="post" validate="true">
        <table class="wwtable" cellspacing="1" cellpadding="8">
           <tr>
             <td class="table_name" style="width:220px;" height="60px;">会员名称:</td>
             <td>
             	运营商
             </td>
           </tr>
           <tr>
             <td class="table_name">类型<font color='red'>*</font></td>
             <td>
             	<@s.radio name="radiochecked" list=r"#{'0':'收入','1':'支出'}" value="0"/>
             </td>
           </tr>   <tr>
             <td class="table_name">余额<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="fund_num" cssClass="txtinput" maxLength="6"onkeyup="checkRMB(this)"cssStyle="width:142px;height:20px;"/>
                <@s.fielderror><@s.param>fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">事由<font color='red'>*</font></td>
             <td>
             	<@s.textarea name="reason" cssClass="txtinput" maxLength="101" cssStyle="width:260px;height:60px;"onkeyup="checkLength(this,100)"/>
                <@s.fielderror><@s.param>reason</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">注意<font color='red'>*</font></td>
             <td>
             	</br>
             	带<font color='red'>*</font>为必填，此表单一经提交，将不可以再修改或删除，请务必谨慎操作 
             	</br></br>
             	<font color="red">请在红框内操作，鼠标不在红框内将退出操作</font>
				<span style="float:right;margin-right:10px;">  请在 <span  id="time" style="color:red;font-size:30px;"></span> 内完成操作</span>
             </td>
           </tr>
         </table>
	     <div class="fixbuttom">
	         <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交" onsubmit="closec();"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Capitalmanagement_list.action','');"/>
	     </div>
	     
	  </@s.form>
</div>
</#if>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
  </body>
</html>