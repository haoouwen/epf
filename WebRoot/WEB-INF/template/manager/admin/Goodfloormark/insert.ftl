<html>
  <head>
    <title>添加商品楼层信息</title>
  </head>
  <body>  
<@s.form action="/admin_Goodfloormark_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：1 > 2 > 3
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name">gm_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.gm_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.gm_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">goods_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.goods_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.goods_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">fm_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.fm_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.fm_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">f_id<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.f_id" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.f_id</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">gm_name<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.gm_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.gm_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">img_path<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.img_path" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.img_path</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">gm_url<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.gm_url" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.gm_url</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">gm_type<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.gm_type" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.gm_type</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">cat_attr<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.cat_attr" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.cat_attr</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">gm_sort<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="goodfloormark.gm_sort" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>goodfloormark.gm_sort</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodfloormark_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

