<html>
  <head>
    <title>平台资金</title>
  </head>
  <body>  
<div class="postion">
	当前位置：财务统计 >财务管理  > 平台资金 
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			<tr>
	           <td class="table_right"colspan="4">
                  <h2>平台资金</h2>
	           </td>
	           
	        </tr>
		
			 <tr>
	           <td class="table_name" width="30%">总余额 ：</td>
	           <td class="table_right">
	             ${sysfund.fund_num}
	           </td>
	        </tr>
	        <tr>
	           <td class="table_right"colspan="4">
                  <h2>余额信息</h2>
	           </td>
	           
	        </tr>
        </table>
        </div>
       <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab" align="center">
               <tr> 
                   <th width="10%">可用余额</th> 
                   <th  width="10%">冻结余额</th>
               </tr>
				  <tr>
				   <td align="center">${sysfund.use_num}</td>
				   <td align="center">${sysfund.freeze_num}</td>
			      </tr>
             </table>
           </div>
	<div class="clear"/>
	<div class="bsbut">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Fundhistory_list.action','');" value="查看余额流">
   </div>
</body>
</html>

