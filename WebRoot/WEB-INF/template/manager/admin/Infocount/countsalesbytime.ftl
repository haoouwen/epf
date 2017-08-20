<html>
  <head>
    <title>销售统计</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/admin/js/infocount.js"></script>
    <script type="text/javascript" src="/include/admin/js/chartAnalysis.js"></script>
    <script src="/include/components/datadrawinglist/js/highcharts.js"></script>
    <script src="/include/components/datadrawinglist/js/modules/exporting.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
	 		countsalesbytime();
	 		linesalesbytime();
	    });
    
        function showAmount(paravalue){
                $("#txtstartDate").val("");
                $("#txtendDate").val("");
                $("#count_type").val(paravalue);
                $("#indexForm").submit();
        }  
        function submitSearch(){
                $("#count_type").val("");
                $("#indexForm").submit();
        }  
    </script>
    <style>
    
      ul li{
         float:left;
      }
      .char_type{
            float:right;
            padding-right:20px;
      }
    </style>
  </head>
  <body>
<@s.form action="/admin_Infocount_countsalesbytime.action" id="indexForm" method="post" validate="true" >
<!--当前位置-->
	<div class="postion">当前位置：运营统计 > 销售统计</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td> 
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				      &nbsp;至&nbsp;
		    <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  /></td>

	       <td><input type="button" class="rbut" value="查询" onclick="submitSearch();"></td>
	       <td> <input type="button"  onclick="showAmount('todayAmount');" class="rbut" value="今日"></td>
	       <!--<td> <input type="button"  onclick="showMember('weekAmount');" class="rbut" value="本周"></td>-->
	       <td> <input type="button"  onclick="showAmount('monthAmount');" class="rbut" value="本月"></td>
	       <!--<td> <input type="button"  onclick="showMember('yearAmount');" class="rbut" value="今年"></td>-->
	   </tr>
	  </table>
	</div>
<!--条件结束-->


   <div class="rtable" >
     
   </div>
   
     <div class="rtable">
     <table cellpadding="0" cellspacing="0" width="100%" >
	   <tr>
	     <td>
	       <ul id="column_ul">
	        <li><a href="javascript:void(0);" onclick="changeColumnChart('column_sales');">订单成交额</a></li> 
	        <li><a href="javascript:void(0);" onclick="changeColumnChart('column_count');">订单成交量</a></li>
	       </ul>
	       
	       <ul id="line_ul" style="display:none;">
	        <li><a href="javascript:void(0);" onclick="changeLineChart('line_sales');">订单成交额</a></li> 
	        <li><a href="javascript:void(0);" onclick="changeLineChart('line_count');">订单成交量</a></li>
	       </ul>
	       
	       <ul id="char_type_ul">
	           <li class="char_type"><a href="javascript:void(0);" onclick="changeChartType('zline');"><img src="/include/admin/images/zline.gif">折线图</a></li>
	           <li class="char_type"><a href="javascript:void(0);" onclick="changeChartType('poll');"><img src="/include/admin/images/poll.gif">柱状图</a></li>
	       </ul>
	     </td>
	   </tr>
	   
	    <tr>
	     <td>
	        
	        <div id="container_columnamount" style="min-width:400px;height: 400px; "></div>
 
	        <div id="container_columncount"  style="min-width:85%;height: 400px;display:none;"></div>
	        
	        <div id="container_lineamount" style="min-width:82%;height: 400px;display:none;"></div>
 
	        <div id="container_linecount"  style="min-width:82%;height: 400px;display:none;"></div>
	        
	        <div style="padding-bottom:10px;"></div>
	        
	     </td>
	   </tr>
	</table>
   </div>
   
   <!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr>
      
     	 <th width="10%">时间</th>

     	 <th width="10%">订单成交额</th>
    
     	 <th width="10%">订单成交量</th>
    
      </tr>
  
  <#list salesAmountList as salesAmount>
  <tr>
 		<td align="center">${(salesAmount.sales_time)?if_exists}</td>
 		<td align="center">${(salesAmount.order_total_amount)?if_exists}</td>
        <td align="center">${(salesAmount.order_count)?if_exists}</td>
        
  </tr>
  </#list>
      </tr>
	</table>
  </div>
  
  <!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
  
<!--后台table结束-->
   <div class="clear"/>
<!--翻页结束-->
</div>

<@s.hidden name="count_type" id="count_type"/>
<@s.hidden name="firstAccess" id="firstAccess"/>
</@s.form>

  </body>
</html>
