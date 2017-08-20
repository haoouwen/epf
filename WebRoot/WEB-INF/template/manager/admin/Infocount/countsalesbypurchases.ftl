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
	 		countsalesbypurchases();
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
    </style>
  </head>
  <body>
<@s.form action="/admin_Infocount_countsalesbypurchases.action" id="indexForm" method="post" validate="true" >
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


   <div class="rtable" ></div>
     <div class="rtable">
     <table cellpadding="0" cellspacing="0" width="100%" >
	   <tr>
	     <td>
	       <ul class="inforstul">
	        <!--<li><a href="javascript:void(0);" >订单成交额</a></li> 
	        <li><a href="javascript:void(0);" onclick="changeChart('count');">订单成交量</a></li>
	        <li><a href="javascript:void(0);" onclick="changeChart('line');"><img src="/include/admin/images/chart.gif">折线图</a></li>-->
	       </ul>
	     </td>
	   </tr>
	   
	    <tr>
	     <td>
	        
	        <div id="container_columngoodscount" style="min-width:400px;height: 400px; margin: 0 auto;"></div>
	        
	     </td>
	   </tr>
	</table>
   </div>
   
   <!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr>

     	 <th width="10%">会员</th>
    
     	 <th width="10%">购买量</th>
    
      </tr>
  
  <#list salesAmountList as salesAmount>
  <tr>
 		<td align="center">${(salesAmount.user_name)?if_exists}</td>
        <td align="center">${(salesAmount.mem_goods_num)?if_exists}</td>
        
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
