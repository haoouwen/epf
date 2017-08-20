<html>
  <head>
    <title>会员统计</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
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
  </head>
  <body>
<@s.form action="/admin_Infocount_countmemberinfo.action" id="indexForm" method="post" validate="true" >
<!--当前位置-->
	<div class="postion">当前位置：运营统计 > 会员统计</div>
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
		   <td>&nbsp;地区:</td>
		   <td>    <@s.select id="area_attr"  name="area_attr_s"  list="areaList" listValue="area_name"
						       listKey="area_id"   headerKey="" headerValue="请选择地区" /></td>
		   <td>&nbsp;门店:<@s.textfield name="store_code_s"  cssStyle="width:100px;"/></td>
		   
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
	     <td><span>新增会员：<#if growMemberMap !=null && growMemberMap!="">${growMemberMap.get("growMember")} </#if>/占比${memberPercent}</span></td>
	     <td><span>会员总数：<#if totalMemberMap !=null && totalMemberMap!="">${totalMemberMap.get("totalMember")} </#if></span></td>
	   </tr>
	   <tr>
	     <td><span>会员消费总金额：<#if totalAmountMap !=null && totalAmountMap!="">${totalAmountMap.get("totalAmount")} </#if>元</span></td>
	     <td></td>
	   </tr>
	</table>
  </div>
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >

     	 <th width="10%">会员编号</th>

     	 <th width="10%">姓名</th>
    
     	 <th width="9%">邮箱</th>
    
     	 <th width="9%">手机</th>
     	 
     	 <th width="10%">注册时间</th>
     	 
     	 <th width="10%">所属区域</th>
     	 
     	 <th width="9%">消费金额(元)</th>
     	 
     	 <th width="9%">会员级别</th>
     	 
     	 <th width="9%">成长值</th>
     	 
     	  <th width="6%">状态</th>
    
  </tr>
  
  <#list memberList as member>
  <tr>
 		<td align="center">${member.membernum?if_exists}</td>
 		<td align="center">${member.user_name?if_exists}</td>
        <td align="center">${(member.email)?if_exists}</td>
        <td align="center">${member.cellphone?if_exists}</td>
        <td align="center">${member.in_date?if_exists}</td>
        <td align="center">${member.area_attr?if_exists}</td>
        <td align="center">${member.total_amount?if_exists}</td>
        <td align="center">${member.buy_level_name?if_exists}</td>
        <td align="center">${member.growthvalue?if_exists}</td>
        <td align="center">
	    	<#list infoStateList as infoState>
				<#if member.info_state?if_exists==infoState.para_value>
					<#if infoState.para_value?if_exists='1'>
				        <font class='greencolor'>${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='3'>
					    <font class="redcolor">${infoState.para_key?if_exists}</font>
					<#elseif infoState.para_value?if_exists='0'>
				       <font class="redcolor">${infoState.para_key?if_exists}</font>
				    <#elseif infoState.para_value?if_exists='2'>
				        <font class="bluecolor">${infoState.para_key?if_exists}</font>
				    </#if>
				    <#break/>
			    </#if>
			</#list>
	    </td>	
  </tr>
  </#list>
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
</div>

<@s.hidden name="count_type" id="count_type"/>
<@s.hidden name="firstAccess" id="firstAccess"/>
</@s.form>

  </body>
</html>
