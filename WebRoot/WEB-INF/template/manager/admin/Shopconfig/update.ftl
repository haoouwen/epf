<html>
  <head>
    <title>发货地址</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
	<!--地区-->
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script type="text/javascript">
  $(document).ready(function(){ 
  	 //所属地区的回选
	 loadArea("${area_attr?if_exists}","");
  });
</script>
  </head>
  <body>
  
<@s.form action="/admin_Shopconfig_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  当前位置:商城管理 > 配送管理 > 发货地址
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">

           <tr>
             <td class="table_name">所属地区<font color='red'>*</font></td>
             <td>
               <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td class="tdbottom">
             				<div id="areaDiv" style="margin-left:-5px;"></div>
             			</td>
             			<td class="tdbottom">
             				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
	              		</td>
	              	</tr>
	            </table>
             	
             </td>
           </tr>
           
           <tr>
             <td class="table_name">详细地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="shopconfig.address" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>shopconfig.address</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">联系人<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="shopconfig.contant_man" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>shopconfig.contant_man</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">手机<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="shopconfig.mobile" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this)"/>
             	<@s.fielderror><@s.param>shopconfig.mobile</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">固定电话<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="shopconfig.phone" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this)"/>
             	<@s.fielderror><@s.param>shopconfig.phone</@s.param></@s.fielderror>
             </td>
           </tr>
          
            <tr>
             <td class="table_name">邮编<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="shopconfig.postcode" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this)"/>
             	<@s.fielderror><@s.param>shopconfig.postcode</@s.param></@s.fielderror>
             </td>
           </tr>
          
           <tr>
             <td class="table_name">email:</td>
             <td>
             	<@s.textfield name="shopconfig.email" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>shopconfig.email</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">QQ:</td>
             <td>
             	<@s.textfield name="shopconfig.qq" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this)"/>
             	<@s.fielderror><@s.param>shopconfig.qq</@s.param></@s.fielderror>
             </td>
           </tr>

		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="shopconfig.shop_id"/>
    	   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
           <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
   </div>
</div>
</@s.form>
</body>
</html>
