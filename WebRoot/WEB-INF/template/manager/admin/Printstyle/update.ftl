<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>修改样式</title>
      <#include "/include/uploadInc.html">
      <script type="text/javascript" src="/include/common/js/common.js"></script>
      <script type="text/javascript" src="/include/admin/js/printstyle.js"></script>
      <script type="text/javascript" src="/include/components/print/LodopFuncs.js" ></script>    
  </head>
  <body>
<@s.form action="/admin_Printstyle_update.action" method="post" validate="true" id="modiy_form" onSubmit="doSomething()">
<!--当前位置开始-->
<div class="postion">
当前位置:商城管理 > 配送管理 > 样式管理 > 修改样式
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		   <tr>
             <td class="table_name" width="20%">模板代码:</td>
             <td>
                   ${printstyle.template_code?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">模板名称:</td>
             <td>
              ${printstyle.template_name?if_exists}
             </td>
           </tr>
           
            <tr>
             <td class="table_name">图片:</td>
             <td>
	       		<table border="0" cellpadding="0" cellspacing="0">
	         		<tr>
	         			<td style="padding-left:3px;">
	         				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	         				<div id="fileQueue"></div>
	              			<script>uploadPrintBackgroupImg("uploadifyfile","uploadresult","fileQueue");</script>
	         			</td>
	         		</tr>
	         	</table>
	         </td>
       	   </tr>
           
           <tr>
             <td class="table_name">打印项:</td>
             <td>
				<#list commparaList as commpara>
					<#assign a1="1" />
					<#list printCommparaString as para1>
						<#if commpara.para_key==para1>
							<#assign a1="2" >
						</#if>						 	
					 </#list>
					 <#if a1="1">
					 	<input type="checkbox" class="print_param_class" onclick="Moditify(this)" value="${commpara.para_value}" name="${commpara.para_key}">${commpara.para_value}</input>
					 <#else>
						<input type="checkbox" class="print_param_class" onclick="Moditify(this)" value="${commpara.para_value}" name="${commpara.para_key}" checked>${commpara.para_value}</input>
					 </#if>
				</#list>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">打印内容<font color='red'>*</font></td>
             <td>
	          <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=900 height=600> 
				  <param name="Caption" value="内嵌显示区域">
				  <param name="Border" value="1">
				  <param name="Color" value="#C0C0C0">
				  <embed id="LODOP_EM" type="application/x-print-lodop" width=900 height=600></embed>
			  </object>    	
             </td>
           </tr>
           
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="printstyle.trade_id"/> 
       <@s.hidden name="printstyle.template_name"/>
       <@s.hidden name="printstyle.print_param" id="param_id"/>
       <@s.hidden name="printstyle.show_content" id="content_id"/>  
       <@s.token/>  
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Printstyle_list.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

