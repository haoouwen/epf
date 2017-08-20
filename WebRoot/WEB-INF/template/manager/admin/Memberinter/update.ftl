<html>
  <head>
    <title>会员积分变动</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
<body>
<@s.form action="/admin_Memberinter_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置--> 	  当前位置:会员管理 > 财务管理 > 会员积分管理表 > 会员积分变动
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name">会员名称:</td>
             <td>
             	 <@s.label name="cust_name" cssClass="txtinput"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">类型<font color='red'>*</font></td>
             <td>
             	<@s.radio name="radiochecked" list=r"#{'0':'添加','1':'减少'}" value="0"/>
             </td>
           </tr>   <tr>
             <td class="table_name">积分<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fund_num" cssClass="txtinput" maxLength="6"/>
                <@s.fielderror><@s.param>fund_num</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">事由<font color='red'>*</font></td>
             <td>
         	 	<@s.textarea name="reason" cssClass="txtinput" maxLength="100" cssStyle="width:260px;height:60px;"onkeyup="checkLength(this,100)"/>
                <@s.fielderror><@s.param>reason</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">注意<font color='red'>*</font></td>
             <td>
             	此表单一经提交，将不可以再修改或删除，请务必谨慎操作
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
   		    <@s.token/>
	       <@s.hidden name="cust_id" value="${memberinter.cust_id?if_exists}"/>
	       <@s.hidden name="member.cust_id" value="${custId?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberinter_list.action','');" />
	     </div>
   </div>
</div>
<div class="clear"></div>
</@s.form>



  </body>
</html>