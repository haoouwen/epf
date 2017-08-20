<html>
  <head>
    <title>添加配送方式</title>
  </head>
  <body>
  
  <@s.form action="/admin_Sendshipmode_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
当前位置:商城管理 > 物流管理 > 添加配送方式
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
		             <td width="19%" class="table_name">配送方式名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendshipmode.smode_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>sendshipmode.smode_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
		            <tr>
			             <td width="19%" class="table_name">快递公司代码<font color='red'>*</font></td>
			             <td>
			             	<@s.textfield name="sendshipmode.en_name" cssClass="txtinput" maxLength="40"/>
			             	 <a target="_blank" href="/include/commonfiles/api_kuaidi100_com.doc" 
			             	 style="color: #448FF4;cursor: pointer;outline: medium none;text-decoration: none;">《查看所支持的快递公司名称及公司代码》</a>
			             	<@s.fielderror><@s.param>sendshipmode.en_name</@s.param></@s.fielderror>
			             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">配送方式描述:</td>
		             <td>
		               <@s.textarea name="sendshipmode.smode_desc" cssClass="txtinput" cssStyle="width:400px;height:150px;" maxLength="200" />
		             	<@s.fielderror><@s.param>sendshipmode.smode_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
	              <tr>
		             <td class="table_name">是否支持货到付款:</td>
		             <td>
		                 <@s.radio name="sendshipmode.reach_pay" list=r"#{'1':'否','0':'是'}" value="1"/>
		             	<@s.fielderror><@s.param>sendshipmode.reach_pay</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">是否支持保价:</td>
		             <td>
		                <@s.radio name="sendshipmode.is_insured" list=r"#{'1':'否','0':'是'}" value="1"/>
		             	<@s.fielderror><@s.param>sendshipmode.is_insured</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">保价费率:</td>
		             <td>
		             	<@s.textfield name="sendshipmode.rate" value='0' cssClass="txtinput" cssStyle="width:80px;" maxLength="11"/>
		             	<@s.fielderror><@s.param>sendshipmode.rate</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最低保价额:</td>
		             <td>
		             	<@s.textfield name="sendshipmode.mix_insured" value='0' cssClass="txtinput" cssStyle="width:100px;" maxLength="11"/>
		             	<@s.fielderror><@s.param>sendshipmode.mix_insured</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">最高保价额:</td>
		             <td>
		             	<@s.textfield name="sendshipmode.max_insured" value='0' cssClass="txtinput" cssStyle="width:100px;" maxLength="11"/>
		             	<@s.fielderror><@s.param>sendshipmode.max_insured</@s.param></@s.fielderror>
		             </td>
		           </tr>         
	           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="sendshipmode.sort_no" cssStyle="width:80px;" size="12" onkeyup="checkNum(this);"  value="0" maxLength="20"/>
		             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>">
		             	<@s.fielderror><@s.param>sendshipmode.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Sendshipmode_list.action','');"/>

   </div>
</div>
<div class="clear"></div>
</@s.form>
<div class="clear"></div>
</body>
</html>

