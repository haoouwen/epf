<html>
  <head>
    <title>会员余额变动</title>
 	  <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
<body>
<@s.form action="/admin_Memberfund_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->当前位置:<#if enter?if_exists==''>财务统计 >财务管理 ><#elseif enter?if_exists=='0'> 会员管理 ><#elseif enter?if_exists=='1'> 企业会员 ></#if> 余额管理 > 会员余额变动
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
             <td class="table_name">可用余额:</td>
             <td>
             	${(memberfund.use_num)?if_exists}
             </td>
           </tr>
           
           <tr>
             <td class="table_name">类型<font color='red'>*</font></td>
             <td>
             	<@s.radio name="radiochecked" list=r"#{'0':'收入','1':'支出'}" value="0"/>
             </td>
           </tr>
           <tr>
             <td class="table_name">余额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="fund_num" cssClass="txtinput" maxLength="8"/>
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
             	<font color="red">此表单一经提交，将不可以再修改或删除，请务必谨慎操作</font>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
  		  <@s.token/>
	       <@s.hidden name="memberfund.cust_id"/>
	       <@s.hidden name="cust_id" value="${memberfund.cust_id?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="提交"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Memberfund_list.action','enter=${enter?if_exists}&cust_id=${memberfund.cust_id?if_exists}');"/>
   </div>
</div>
<div class="clear"></div>
<@s.hidden name="enter"/>
</@s.form>

  </body>
</html>