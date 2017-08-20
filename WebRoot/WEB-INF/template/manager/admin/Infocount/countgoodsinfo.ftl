<html>
  <head>
    <title>商品消费信息</title>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
       function showMember(paravalue){
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
         .rtable ul li{
             height:30px;
             line-height:25px;
             font-size:14px;
             border:1px dotted #185598;
             border-bottom:0px;
         }
         .rtable .title_goods{
             font-weight:bold;
             font-size:16px;
         }
    </style>
  </head>
  <body>
<@s.form action="/admin_Infocount_countgoodsinfo.action" id="indexForm" method="post" validate="true" >
<!--当前位置-->
	<div class="postion">当前位置：运营统计 > 商品消费信息</div>
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
		   <td>&nbsp;地区:</td>
		   <td><@s.select id="area_attr"  name="area_attr_s"  list="areaList" listValue="area_name"
						       listKey="area_id"   headerKey="" headerValue="请选择地区" /></td>
		   <td>&nbsp;门店码:<@s.textfield name="store_code_s"  cssStyle="width:100px;"/></td>
	       <td><input type="button" class="rbut" value="查询" onclick="submitSearch();"></td>
	       <td> <input type="button"  onclick="showMember('todayMember');" class="rbut" value="今日"></td>
	       <td> <input type="button"  onclick="showMember('weekMember');" class="rbut" value="本周"></td>
	       <td> <input type="button"  onclick="showMember('monthMember');" class="rbut" value="本月"></td>
	       <td> <input type="button"  onclick="showMember('yearMember');" class="rbut" value="今年"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table cellpadding="0" cellspacing="0" width="100%">
	   <tr>
	     <td colspan="2" style="height:30px;font-size:14px;"><span>被会员消费的商品总数量：<#if totalgoodsMap !=null && totalgoodsMap!="">${totalgoodsMap.get("total_goods")} <#else>0</#if></span></td>
	   </tr>
	   <tr>
	     <td colspan="2" style="height:30px;font-size:14px;"><span>被会员消费同类商品数量信息</span></td>
	   </tr>
	   <tr>
	     <td>
	        <ul>
	            <li class="title_goods">商品类别  </li>
	           <#list categoryList as category>
	              <li>${category.cat_name?if_exists}</li>
	            </#list>       
	        </ul>
	     </td>
	     <td>
	        <ul>
	            <li class="title_goods">商品数量</li>
	           <#list catgoodsnumList as catgoodsnum>
	              <li>${catgoodsnum.get("cat_goods_num")}</li>
	            </#list>       
	        </ul>
	     </td>
	   </tr>
	</table>
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
