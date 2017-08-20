<html>
  <head>
    <title>添加seo优化设置表</title>
  </head>
  <body>
  <@s.form action="/admin_Seoset_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  当前位置:(一级菜单) > (二级菜单) > (三级菜单)
</div>
<div class="rtdcont">
	<div class="rdtable">
		       <fieldset class="fset" >
		    <legend >必填项<font color='red'>*</font></legend>
		<table  width="100%" cellspacing="0" cellpadding="0">
		  <tr>
	             <td class="ctd">描述名称seo_code<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="seoset.seo_code" cssClass="input" onkeyup="checkLength(this,20);" maxlength="20"/>
	             	<@s.fielderror><@s.param>seoset.seo_code</@s.param></@s.fielderror>
	             </td>
	           </tr>
                
	           <tr>
	             <td class="ctd">描述名称seo_title<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="seoset.seo_title" cssClass="input" onkeyup="checkLength(this,100);" maxlength="100"/>
	             	<@s.fielderror><@s.param>seoset.seo_title</@s.param></@s.fielderror>
	             </td>
	           </tr>
                
	           <tr>
	             <td class="ctd">描述名称seo_keyword<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="seoset.seo_keyword" cssClass="input" onkeyup="checkLength(this,150);" maxlength="150"/>
	             	<@s.fielderror><@s.param>seoset.seo_keyword</@s.param></@s.fielderror>
	             </td>
	           </tr>
                
	           <tr>
	             <td class="ctd">描述名称seo_decri<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="seoset.seo_decri" cssClass="input" onkeyup="checkLength(this,150);" maxlength="150"/>
	             	<@s.fielderror><@s.param>seoset.seo_decri</@s.param></@s.fielderror>
	             </td>
	           </tr>
		</table>
		  </fieldset>
		    <fieldset class="fset" >
	  <legend >其它项</legend>
   	    <table class="op_table" cellspacing="1" cellpadding="8"  >            
            copy the unrequired options to here
	      
         </table>   
     </fieldset>   
   <div class="linebox"/>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    	           <@s.token/>
		       <@s.submit value="保存"/> 
		       <@s.reset value="重置" style="cursor:pointer;"/>
		       <input type="button" name="returnList" style="cursor:pointer;" value="返回列表"  onclick="linkToInfo('/admin_Seoset_list.action','');"/> 
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
  </body>
</html>

