<html>
  <head>
    <title>待购汇账单</title>
  </head>
  <body>
<@s.form action="/admin_Fepbill_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：商城管理 > 保税发货 > 待购汇账单</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >账单编号:</td><td><@s.textfield name="fepbill_id_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fepbill.fepbill_id')"/></th>
                 
                 	<th width="10%">账单编号</th>
			    
                 	<th width="10%">购汇总金额</th>
		   			
		   		    <th width="10%">日期</th>
		   		    
                 <th  width="10%">操作</th>
               </tr>
			    <#list fepbillList as fepbill>
				 <tr >
			         <td><input type="checkbox" name="fepbill.fepbill_id" value="${fepbill.fepbill_id?if_exists}"/></td>
			         
			         	<td align="center">${fepbill.fepbill_id?if_exists}</td>
				    
			         	<td align="center">${fepbill.purchasingtotalamount?if_exists}</td>
			         	
			         	<td align="center">${fepbill.in_date?if_exists}</td>
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Fepbill_view.action','fepbill.fepbill_id=${fepbill.fepbill_id?if_exists}');">
					  <img src="/include/common/images/view.gif" /></a></td>
				    
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
             <input type="button" class="rbut"onclick="exprotExcel('fepbill.fepbill_id','/admin_Fepbill_exportInfo.action');"  value="导出汇账单">
             <input type="button" class="rbut"onclick="delInfo('fepbill.fepbill_id','/admin_Fepbill_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <@s.hidden  name="excid" id="excid"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

