<html>
  <head>
    <title>添加票据</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/components/print/LodopFuncs.js" ></script> 
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/admin/js/printstyle.js"></script>
  </head>
  <body>  
<@s.form action="/admin_Invoice_insert.action" method="post" validate="true"  id="detailForm"  onSubmit="doSomething()">
<div class="postion">
	当前位置：票据管理 > 票据列表 > 添加票据
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
	    
			 <tr>
	           <td class="table_name">票据名称<font color='red'>*</font></td>
	           <td >
	             	<@s.textfield name="invoice.invoice_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>invoice.invoice_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
	        <tr>
             <td class="table_name">票据单图片:</td>
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
	           <td class="table_name">票据打印项:</td>
	           <td >
	             	<#list commparaList as commpara>
						<#assign a1="1" />
						<#list printCommparaString as para1>
							<#if commpara.para_key==para1>
								<#assign a1="2" />
							</#if>						 	
						 </#list>
						 <#if a1="1">
						 	<input type="checkbox" class="print_param_class" onclick="Moditify(this)" value="${commpara.para_value}" name="${commpara.para_key}">${commpara.para_value}</input>
						 <#else>
							<input type="checkbox" class="print_param_class" onclick="Moditify(this)" value="${commpara.para_value}" name="${commpara.para_key}" checked>${commpara.para_value}</input>
						 </#if>
					</#list>
	             	<@s.fielderror><@s.param>invoice.print_param</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">打印内容<font color='red'>*</font></td>
	           <td style="padding-top:100px;">
             	 <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=900 height=600> 
					  <param name="Caption" value="内嵌显示区域">
					  <param name="Border" value="1">
					  <param name="Color" value="#C0C0C0">
					  <embed id="LODOP_EM" type="application/x-print-lodop" width=900 height=600></embed>
				  </object> 
             	  <@s.fielderror><@s.param>invoice.show_content</@s.param></@s.fielderror>
	           </td>
	        </tr>	
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.token/>
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="invoice.show_content" id="content_id"/>
 		   <@s.hidden name="invoice.print_param"  id="param_id"/>
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Invoice_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

