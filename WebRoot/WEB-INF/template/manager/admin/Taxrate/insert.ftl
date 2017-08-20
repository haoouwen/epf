<html>
  <head>
    <title>添加进出口税率</title>
    <script type="text/javascript" src="/include/common/js/pinyin.js"></script> 
  </head>
  <body>  
<@s.form action="/admin_Taxrate_insert.action" method="post" validate="true"  id="modiy_form">
<div class="postion">
	当前位置：系统管理 > 系统管理 > 添加进出口税率
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name"width="20%;">品名及规格<font color='red'>*</font></td>
	           <td >
	             	<@s.textfield  id="tax_name" name="taxrate.tax_name" cssStyle="width:150px;" maxLength="100" />
	             	<@s.fielderror><@s.param>taxrate.tax_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	            
	            <script type="text/javascript">  
	              $('#tax_name').bind('keyup', function(){   
	              	  var tax_value=$('#tax_name').val();   
		              var getword=Turnpingyin(tax_value);
		              var en_word="";
		              if(getword.length>100){
		              	en_word=getword.substring(0,99);
					  }else{
					     en_word=getword;
					  }
		              $('#tax_en_name').val(en_word);
	              })
	           </script>  
	         
	         <tr>
	           <td class="table_name">英文名称<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="taxrate.tax_en_name" cssStyle="width:300px;" maxLength="300" id="tax_en_name" />
	             	<@s.fielderror><@s.param>taxrate.tax_en_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
	         <tr>
	           <td class="table_name">税率号<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="taxrate.tax_number" cssStyle="width:80px" maxLength="8" />
	             	<@s.fielderror><@s.param>taxrate.tax_number</@s.param></@s.fielderror>
	             	<img class='ltip' src="/include/common/images/light.gif"alt="提示:长度不能大于8位"/>
	           </td>
	        </tr>	
	        
			 <tr>
	           <td class="table_name">税率级别<font color='red'>*</font></td>
	           <td>
	             	${(taxrate.tax_level)?if_exists}级
	             	<@s.fielderror><@s.param>taxrate.tax_level</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	     
	       <tr>
	           <td class="table_name">排序<font color='red'>*</font></td>
	           <td>
	             	<@s.textfield name="taxrate.sort_no"  value="0" maxLength="10" onkeyup="checkNum(this);" cssStyle="width:50px;"  />
	             	<@s.fielderror><@s.param>taxrate.sort_no</@s.param></@s.fielderror>
	             	<img class='ltip' src="/include/common/images/light.gif" alt='<@s.property value="%{getText('manager.sort_no')}"/> '>
	           </td>
	        </tr>	
	           
			 <tr>
	           <td class="table_name">单位</td>
	           <td>
	             	<@s.textfield name="taxrate.tax_unit" cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>taxrate.tax_unit</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">完税价格</td>
	           <td>
	             	<@s.textfield name="taxrate.tax_price" cssStyle="width:50px;" maxLength="10"/>
	             	<@s.fielderror><@s.param>taxrate.tax_price</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">税率%</td>
	           <td>
	             	<@s.textfield name="taxrate.tax_rate" cssStyle="width:50px;" maxLength="10" />
	             	<img class='ltip' src="/include/common/images/light.gif" alt="提示:税率是百分号的"/>
	             	<@s.fielderror><@s.param>taxrate.tax_rate</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">单位备注</td>
	           <td>
	             	<@s.textfield name="taxrate.tax_unit_remark" cssStyle="width:300px;" maxLength="100" />
	             	<@s.fielderror><@s.param>taxrate.tax_unit_remark</@s.param></@s.fielderror>
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
           <@s.hidden name="taxrate.up_tax_id" value="${(taxrate.up_tax_id)?if_exists}" />
	       <@s.hidden name="taxrate.tax_level" value="${(taxrate.tax_level)?if_exists}" />
	       <@s.hidden  id="back_sel_taxrate"  name="back_sel_taxrate" />
			<@s.hidden  id="back_sel_taxrate_name"  name="back_sel_taxrate_name" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Taxrate_list.action';document.forms[0].submit();"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

