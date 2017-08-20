<html>
  <head>
    <title>添加收货地址</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadArea("${areaIdStr?if_exists}","");
      });
	</script>
  </head>
  <body>
  <@s.form action="/admin_Buyeraddr_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:会员管理 > 会员相关 >  收货地址管理 > 添加收货地址
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		       
		           <tr>
		             <td class="table_name"  >收货人姓名<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="buyeraddr.consignee" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>buyeraddr.consignee</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name"  >省/市区串<font color='red'>*</font></td>
		             <td>
			          	  <table>
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
		             <td class="table_name"  >地址<font color="red">*</font></td>
		             <td>
		             	<@s.textfield name="buyeraddr.address" cssClass="txtinput" maxLength="101" cssStyle="width:300px;" onkeyup="checkLength(this,100)"/>
		             	<@s.fielderror><@s.param>buyeraddr.address</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name"  >邮政编码<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="buyeraddr.post_code" cssClass="txtinput" maxLength="20" onkeyup="checkDigital(this)"/>
		             	<@s.fielderror><@s.param>buyeraddr.post_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name"  >固定电话:</td>
		             <td>
		             	<@s.textfield name="buyeraddr.phone" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>buyeraddr.phone</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name"  >手机号码:</td>
		             <td>
		             	<@s.textfield name="buyeraddr.cell_phone" cssClass="txtinput" maxLength="20"onkeyup="checkNum(this)"/>
		             	<@s.fielderror><@s.param>buyeraddr.cell_phone</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
	            <tr><td class="table_name">是否为默认地址:</td><td>
    	    <@s.radio name="buyeraddr.sel_address" list=r"#{'0':'是','1':'否'}"/>
	        <@s.fielderror><@s.param>buyeraddr.sel_address</@s.param></@s.fielderror>
            </td></tr> 
		      
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.token/> 
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Buyeraddr_list.action','');"/>
	       <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

