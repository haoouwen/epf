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
           //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	 	   countsalesbycat();
	    }); 
        
    </script>
    <style>
    
      ul li{
         float:left;
      }
    </style>
  </head>
  <body>
<@s.form action="/admin_Infocount_countsalesbycat.action" id="indexForm" method="post" validate="true" >
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
		      <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		  </td>

		   <td>&nbsp;商品种类:</td>
		   <td><div id="catDiv" ></div></td>
		   
	       <td><input type="button" class="rbut" value="查询" onclick="showSearchDiv('','cat_attr','','search_cat_attr','indexForm');"/></td>
	      
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
   
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >

     	 <th>商品名称</th>

     	 <th>该商品成交量</th>
    
  </tr>
  
  <#list salesByCatList as salesByCat>
     <tr>
 		<td align="center">${salesByCat.goods_name?if_exists}</td>
 		<td align="center">${salesByCat.goods_volume?if_exists}</td>
     </tr>
  </#list>
  
	</table>
  </div>

   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
</div>
<!--搜索框隐藏域存放-->
<@s.hidden name="firstAccess" id="firstAccess"/>
<@s.hidden id="search_cat_attr"  name="cat_attr_s"/>
<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
</@s.form>

  </body>
</html>
