<html>
  <head>
    <title>订单数</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	<script type="text/javascript" src="/include/admin/js/swfobject2.2.js"></script>
	<script type="text/javascript" src="/include/admin/js/json2.js"></script>
	<script type="text/javascript" src="/include/admin/js/chart.js"></script>
	<@s.hidden id="x_axis" name="x_axis" />
	<@s.hidden id="elements" name="elements" />
	<@s.hidden id="max" name="max" />
	<@s.hidden id="steps" name="steps" />
	<@s.hidden id="xtitle" name="xtitle" />
	<script type="text/javascript">
		var title = $("#xtitle").val();  //标题
	    var y_text = "单位个";
	    
	    var x_axisstr = $("#x_axis").val(); //x轴坐标
	    var x_axis = x_axisstr.split(",");
	    var elementsstr = $("#elements").val(); //y轴坐标
	    var elements1 = elementsstr.split(",");
	    var elements = new Array();
	            for(var i=0;i<elements1.length;i++)
	            {
	                elements.push(parseInt(elements1[i]));
	            }
	    
	    var max = $("#max").val(); //y坐标最大值
	    var steps = $("#steps").val();; //每进步值
	
	    var ofc2_chart_id="my_ofc2_chart";  
	    swfobject.embedSWF("/include/admin/js/open-flash-chart.swf", ofc2_chart_id,  
	      "1050", "520", "9.0.0", "/include/admin/js/expressInstall.swf",  
	      {"get-data":"getDemoInitData"},  
	      {"wmode":"transparent"}  
	    );  
	    
	    function findSWF(movieName) {  
	      if (navigator.appName.indexOf("Microsoft")!= -1) {  
	        return window[movieName];  
	      } else {  
	        return document[movieName];  
	      }  
	    }  
	
	    function getDemoInitData(){  
	        return JSON.stringify(chartbar(title,y_text,elements,x_axis,max,steps));     
	    }
	    function load_data0(movieName) {  
	        var tmp  = findSWF(movieName);  
	        tmp.load(JSON.stringify(chartbar(title,y_text,elements,x_axis,max,steps)));  
	    }  
	    function load_data1(movieName) {  
	        var tmp  = findSWF(movieName);  
	        tmp.load(JSON.stringify(chartpie(title,y_text,elements,x_axis,max,steps)));  
	    }  
	     function load_data2(movieName) {  
	        var tmp  = findSWF(movieName);  
	        tmp.load(JSON.stringify(charthbar(title,y_text,elements,x_axis,max,steps)));   
	     }   
	      function load_data3(movieName) {  
	        var tmp  = findSWF(movieName);  
	        tmp.load(JSON.stringify(chartline_dot(title,y_text,elements,x_axis,max,steps)));   
	     }     
	     function load_data4(movieName) {  
	        var tmp  = findSWF(movieName);  
	        tmp.load(JSON.stringify(chartbar_3d(title,y_text,elements,x_axis,max,steps)));   
	     }    
	    </script>
            
  </head>
  <body>
<@s.form action="/admin_InfoCount_membernum.action" id="indexForm" method="post" validate="true" >
<!--当前位置-->
	<div class="postion">当前位置：财务统计 > 销售统计 > 会员数</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td> 
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				      &nbsp;至&nbsp;
		    <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate"readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  /></td>
	       <td><input type="submit" class="rbut" value="查询"onclick="submittotal();"></td>
	       <td> <input type="button"  onclick="exportShowDIV('searchDiv','300px','130px','导出数据');" class="rbut" value="导出数据"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table cellpadding="0" cellspacing="0" width="100%">
	   <tr>
	     <td width="1070"><div id="my_ofc2_chart"></div> </td>
	     <td valign="top">
	       <ul class="inforstul">
	        <li><img src="/include/admin/images/poll.gif"><a href="javascript:load_data0('my_ofc2_chart')">柱状图</a></li> 
	        <li><img src="/include/admin/images/chart.gif"><a href="javascript:load_data1('my_ofc2_chart')">饼状图</a></li>      
	        <li><img src="/include/admin/images/zline.gif"><a href="javascript:load_data3('my_ofc2_chart')">折线图</a></li>   
	        <li><img src="/include/admin/images/3dzx.gif"><a href="javascript:load_data4('my_ofc2_chart')">3D柱状图</a></li> 
	       </ul>
	     </td>
	   </tr>
	</table>
  </div>
<!--后台table结束-->
   <div class="clear"/>
<!--翻页结束-->
</div>
<@s.hidden name="count_type" value="membernum"/>
</@s.form>
<div style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Infocount_exportInfo.action"   method="post">
    <table>
    <tr>
    <td class="searchDiv_td">时间段:</td>
	<td> 
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				      &nbsp;至&nbsp;
		    <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate"readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
	</td>
    </tr>
     <tr>
		<td align="center" colspan="2" style="border:0px;">
		<input type="button" name="search" value="导出" onclick="exprotExcel();"/>&nbsp;
		<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
		</td>
	</tr>
    </table>
</@s.form>
</div>
  </body>
</html>
