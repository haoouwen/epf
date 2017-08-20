<html>
<head>
	<title>财务报表</title> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <script type="text/javascript">  
                function submitrank(){
                   $("#Fromrank").submit();
                }
            </script>  
</head>
<body>

<@s.form id="Fromrank" action="/bmall_InfoCount_moneycount.action" method="post" id="indexForm">
<div class="rightside f_right">
   <div class="postion">
	数据统计</a><span>></span><a href="#">销售统计</a><span>></span><a href="#">财务报表</a>
   </div>
   <div class="ropot">
       <h2 class="rotitle">
       		<span><td class="fthstyle1">财务报表</td></span>
       </h2>
       
       <div class="rosearch">
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				      &nbsp;至&nbsp;
		    <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate"readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
				                <input type="button" name="search" value="搜索" onclick="submitrank();"/>&nbsp;<b> ${xtitle?if_exists}</b>
       </div>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr>
   
     	 <th width="20%" class="fthstyle1">时间段</th>
    
     	 <th width="20%"  class="fthstyle1">手续费费用</th>
    
         <th width="20%"  class="fthstyle1">配送费用</th>
    
     	 <th width="20%"   class="fthstyle1">保价费用</th>
     	 
     	 <th width="20%"   class="fthstyle1">订单总金额</th>
    
  </tr>
   <#list moneycountList as moneycount>
  <tr>
    
        <td align="center">${moneycount.timetype?if_exists}</td>
        
        <td align="center">${moneycount.comm_free?if_exists}</td>
        
    	<td align="center">${moneycount.ship_free?if_exists}</td>
    
    	<td align="center">${moneycount.insured?if_exists}</td>
    
    	<td align="center">${moneycount.total_amount?if_exists}</td>
          
  </tr>
  </#list>
        </table>
        <div class="listbottom">
        	${pageBar?if_exists}
        </div>
   </div>
</div>
<div class="clear"></div>
<@s.hidden name="momeny" id="momeny"/>
<@s.hidden name="count" id="count"/>
</@s.form>
</body>
</html>

















