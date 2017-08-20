<html>
  <head>
    <title>添加快递公司</title>
  </head>
  <body>
  
  <@s.form action="/admin_Sendmode_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
当前位置:商城管理 > 配送管理 > 快递公司 > 添加快递公司
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
		             <td width="19%" class="table_name">快递公司名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendmode.smode_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sendmode.smode_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
			             <td width="19%" class="table_name">快递公司代码<font color='red'>*</font></td>
			             <td>
			             	<@s.textfield name="sendmode.en_name" cssClass="txtinput" maxLength="40"/>
			             	 <a target="_blank" href="/include/commonfiles/api_kuaidi100_com.doc" 
			             	 style="color: #448FF4;cursor: pointer;outline: medium none;text-decoration: none;">《查看所支持的快递公司名称及公司代码》</a>
			             	<@s.fielderror><@s.param>sendmode.en_name</@s.param></@s.fielderror>
			             </td>
		           </tr>
		           
		            <tr>
			             <td width="19%" class="table_name">选择运单样式<font color='red'>*</font></td>
			             <td>
			             	<@s.select name="sendmode.trade_id" cssClass="txtinput" list="printStyleList" listKey="trade_id" listValue="template_name"/>
			             	 &nbsp;<a  href="/admin_Printstyle_add.action" 
			             	 style="color: #448FF4;cursor: pointer;outline: medium none;text-decoration: none;">添加运单样式</a>
			             	<@s.fielderror><@s.param>sendmode.trade_id</@s.param></@s.fielderror>
			             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">快递公司描述:</td>
		             <td>
		               <@s.textarea name="sendmode.smode_desc" cssClass="txtinput" cssStyle="width:400px;height:150px;" maxLength="200" />
		             	<@s.fielderror><@s.param>sendmode.smode_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		             <tr>
		             <td class="table_name">运单号长度<font color='red'>*</font></td>
		             <td>
		              <@s.textfield name="sendmode.sendlength" cssStyle="width:80px;" size="12" onkeyup="checkNum(this);"   maxLength="11"/>
		             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>">
		             	<@s.fielderror><@s.param>sendmode.sendlength</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendmode.sort_no" cssStyle="width:80px;" size="12" onkeyup="checkNum(this);"  value="0" maxLength="20"/>
		             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>">
		             	<@s.fielderror><@s.param>sendmode.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <@s.token/>    
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="sendmode.is_insured" value="0"/>
	       <@s.hidden name="sendmode.reach_pay" value="0"/>
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sendmode_list.action','');"/>

   </div>
</div>
<div class="clear"></div>
</@s.form>
<div class="clear"></div>
</body>
</html>

