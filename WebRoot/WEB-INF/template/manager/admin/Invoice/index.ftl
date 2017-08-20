<html>
  <head>
    <title>票据列表</title>
  </head>
  <body>
<@s.form action="/admin_Invoice_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：票据管理 > 票据列表 </div>
        <div class="rtdcont">
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 	<th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('invoice.invoice_id')"/></th>
                 
                 	<th width="80%">票据名称</th>
			    
                 	<th  width="15%">操作</th>
               </tr>
			    <#list invoiceList as invoice>
				 <tr>
			         <td><input type="checkbox" name="invoice.invoice_id" value="${invoice.invoice_id?if_exists}"/></td>
			         
			         <td align="center">${invoice.invoice_name?if_exists}</td>
				    
				     <td align="center"><a onclick="linkToInfo('/admin_Invoice_view.action','invoice.invoice_id=${invoice.invoice_id?if_exists}');">
					   <img src="/include/common/images/bj.gif" /></a></td>
			     </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Invoice_add.action','');" value="添加票据">
             <input type="button" class="rbut"onclick="delInfo('invoice.invoice_id','/admin_Invoice_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
</body>
</html>

