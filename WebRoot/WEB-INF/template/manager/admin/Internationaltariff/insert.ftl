<html>
  <head>
    <title>添加国际运费模版</title>
  </head>
  <body>  
<@s.form action="/admin_Internationaltariff_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：商城管理 > 物流管理 > 添加国际运费模版
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 
		    <tr>
	           <td class="table_name" width="20%">国际物流名称<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="internationaltariff.ief_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>internationaltariff.ief_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">国际运单单价<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="internationaltariff.ief_price" cssStyle="width:80px;" maxLength="20"/>
	             	<@s.fielderror><@s.param>internationaltariff.ief_price</@s.param></@s.fielderror><font color="red">(元)</font>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">立方数<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="internationaltariff.ief_cubic" cssStyle="width:80px;" maxLength="20" /><font color="red">(立方厘米)</font>
	             	<@s.fielderror><@s.param>internationaltariff.ief_cubic</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">超重重量<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="internationaltariff.ief_overweight" cssStyle="width:80px;" maxLength="20" />
	             	<@s.fielderror><@s.param>internationaltariff.ief_overweight</@s.param></@s.fielderror><font color="red">(Kg)</font>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">超重运费单价<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="internationaltariff.ief_overweight_price" cssStyle="width:80px;" maxLength="20" />
	             	<@s.fielderror><@s.param>internationaltariff.ief_overweight_price</@s.param></@s.fielderror><font color="red">(元)</font>
	           </td>
	        </tr>	
	     </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
            <@s.token/>
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Internationaltariff_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

