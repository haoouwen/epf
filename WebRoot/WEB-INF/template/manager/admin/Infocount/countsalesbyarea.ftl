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
	 	   countsalesbyarea();
	    }); 
        
    </script>
    <style>
      ul li{
         float:left;
      }
    </style>
  </head>
  <body>
<@s.form action="/admin_Infocount_countsalesbyarea.action" id="indexForm" method="post" validate="true" >
<!--当前位置-->
	<div class="postion">当前位置：运营统计 > 销售统计 > 按地区</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
		   <td>&nbsp;地区:</td>
		   <td> <@s.select id="area_attr"  name="area_attr_s"  list="areaList" listValue="area_name"
						       listKey="area_id"   headerKey="" headerValue="请选择地区" /></td>
		   
	       <td><input type="submit" class="rbut" value="查询"></td>
	      
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
	        <li><a href="javascript:void(0);" onclick="changeColumnChart('column_sales');">订单成交额</a></li> 
	        <li><a href="javascript:void(0);" onclick="changeColumnChart('column_count');">订单成交量</a></li>
	        <!--<li><a href="javascript:void(0);" onclick="changeChart('line');"><img src="/include/admin/images/chart.gif">折线图</a></li>-->
	       </ul>
	     </td>
	   </tr>
	   
	    <tr>
	     <td>
	        
	        <div id="container_columnamount" style="min-width:400px;height: 400px; margin: 0 auto;"></div>
 
	        <div id="container_columncount"  style="min-width:85%;height: 400px; margin: 0 auto;display:none;"></div>
	        
	     </td>
	   </tr>
	</table>
   </div>
   
  
<!--后台table结束-->
   <div class="clear"/>
<!--翻页结束-->
</div>

</@s.form>

  </body>
</html>
