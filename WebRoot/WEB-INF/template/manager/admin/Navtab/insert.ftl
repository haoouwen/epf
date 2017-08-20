<html>
  <head>
    <title>添加商城标签</title>
  </head>
  <body>  
<@s.form action="/admin_Navtab_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：网站管理 > 商城导航 > 添加商城标签
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		  
		   <tr>
	           <td class="table_name" width="20%;">标签名称<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="navtab.tab_name" cssClass="txtmaxinput" maxLength="20" />
	             	<@s.fielderror><@s.param>navtab.tab_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	          
	          <tr>
	           <td class="table_name">标签备注<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="navtab.tab_remark"  maxLength="50" />
	             	<@s.fielderror><@s.param>navtab.tab_remark</@s.param></@s.fielderror>
	           </td>
	        </tr>
			 <tr>
	           <td class="table_name">排序<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="navtab.sort_no" cssStyle="width:50px;" maxLength="10" value="0"/>
	             	<img class="ltip" src="/include/common/images/light.gif" alt="格式：数字越小,排的越前!">
	             	<@s.fielderror><@s.param>navtab.sort_no</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	     
	        <tr>
	           <td class="table_name">显示端<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.radio name="navtab.touch" list=r"#{'1':'PC端','0':'手机端'}" value="1" />
	             	<@s.fielderror><@s.param>navtab.touch</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	         	
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
            <@s.token/>
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
             <@s.hidden name=" navtab.start" value="1"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Navtab_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

