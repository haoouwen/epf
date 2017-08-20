<html>
<head>
<title>久通区域代理/订单统计</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
<@s.form action="/agent_Asysuser_orderAnalysis.action" method="post" id="indexForm">
 <div class="crumb-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
			<span class="crumb-name">经营统计</span><span class="crumb-step">&gt;</span>订单列表</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                    <table class="search-tab">
                        <tr>
                            <th>&nbsp;&nbsp;&nbsp;订单号:</th>
                            <td>
                            <@s.textfield name="order_id_s" cssClass="common-text" cssStyle="width:240px;" maxlength="25"/>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                             <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="btn btn-primary btn2" value="高级查询"></td>
                        </tr>
                    </table>
            </div>
            
        </div>
        <div class="result-wrap">
               
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="20%">订单号</th>
                            <th width="10%">订单总额(元)</th>
                            <th width="15%">会员名</th>
                            <th width="15%">下单时间</th>
                            <th width="15%">订单状态</th>
                            <th width="15%">订单类型</th>
                            <th width="10%">操作</th>
                        </tr>
                        <#list areaOrderList as areaOrder>
                        <tr align="center">
                            <td>${(areaOrder.order_id)?if_exists}</td>
                            <td>${(areaOrder.tatal_amount)?if_exists}</td>
                            <td>${(areaOrder.user_name)?if_exists}</td>
							<td>${(areaOrder.order_time)?if_exists}</td>
							 <td>
	                          <#if areaOrder.order_state?if_exists!=null && areaOrder.order_state?if_exists!=''>
	                           <#list orderStateList as orderState>
								  <#if areaOrder.order_state?if_exists==orderState.para_value>
									${orderState.para_key?if_exists}
								    <#break/>
							     </#if>
			                  </#list>
	                        <#else>
	                          -
	                        </#if>
	                        </td>
                            <td>
                              <#if areaOrder.is_sea=="1">
				                     <font color="green">直邮订单</font>
				               <#else>
				                	<font color="#005ea7">保税订单</font>
				               </#if>
                            </td>
                          
                          
                            <td>
                            	<a onclick=linkToInfo("/agent_Asysuser_orderdetails.action","order_id=${areaOrder.order_id?if_exists}");><img src="/include/common/images/view.gif" /></a>
                            </td>
                        </tr>
                        </#list>
                    </table>
                  <div class="pages">
				     ${pageBar?if_exists}
				   </div>
                </div>
        </div>
   </div>
    <@s.hidden  name="buy_cust_s" />
    <@s.hidden  name="order_state_s" />
    <@s.hidden  name="order_type_s" />
    <@s.hidden  name="starttime_s" />
    <@s.hidden  name="endtime_s" />
 </@s.form>

<!--搜索区域开始-->
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/agent_Asysuser_orderAnalysis.action" method="post"  id="form_search_id">
	<table width="100%" border="0" cellspacing="0">
	<tr>
		<td class="searchDiv_td">订单编号:</td>
		<td><@s.textfield name="order_id_s"  cssStyle="width:240px;" /></td>
	</tr>
	<tr>
		<td class="searchDiv_td">会员名称:</td>
		<td><@s.textfield name="buy_cust_s"  /></td>
	</tr>
	<tr>
		<td class="searchDiv_td">交易状态:</td>
		<td><@s.select  name="order_state_s"  list="orderStateList" listValue="para_key"  listKey="para_value" headerKey="" headerValue="--请选择--" />   </td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">订单类型:</td>
		<td><@s.select name="order_type_s" list=r"#{'':'--请选择--','1':'直邮订单','0':'保税订单'}"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">订单时间:</td>
		<td> <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  /></td>
	</tr>
	<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','','search_area_attr','','form_search_id');"/>&nbsp;
		<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
		</td>
   </tr>
	</table>
	<!--搜索框隐藏域存放-->
	</@s.form>
</div>		   


</body>
</html>
