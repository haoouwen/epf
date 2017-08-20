<html>
  <head>
    <title>修改楼层标签</title>
  </head>
<body >
<@s.form action="/admin_Floormark_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：网站管理 > 首页楼层 > 修改楼层标签
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
	        
	         <tr>
	           <td class="table_name"  width="20%">楼层标签名称<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="floormark.fm_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>floormark.fm_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	      <tr>
	           <td class="table_name">楼层标签备注</td>
	           <td class="table_right">
	             <@s.textfield name="floormark.fm_remark" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>floormark.fm_remark</@s.param></@s.fielderror>
	           </td>
	        </tr>	
			 
	      <tr>
	           <td class="table_name">楼层标签类型<font color='red'>*</font></td>
	           <td class="table_right">
	                <@s.radio name="floormark.fm_type" list=r"#{'0':'商品','1':'图片'}"/>
	             	<@s.fielderror><@s.param>floormark.fm_type</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	         <tr>
	           <td class="table_name">排序<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="floormark.fm_sort" cssStyle="width:50px;" maxLength="10" />
	             	<img class="ltip" src="/include/common/images/light.gif" alt="格式:数字越小排的越前">
	             	<@s.fielderror><@s.param>floormark.fm_sort</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
	        
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.token/>            
           <@s.hidden name="floormark.fm_id"/>
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Floormark_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

