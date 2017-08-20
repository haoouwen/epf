<html>
  <head>
    <title>订单管理</title>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
       $(document).ready(function(){ 
        //加载地区分类  第一个参数为上一级ID,第二个参数为所属级数
		 onlyshowarea("${cfg_topareaid?if_exists}",1);	
       });
    </script>    
  </head>
  
<body>
<@s.form action="/admin_Orderinfo_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:网站管理 > 订单管理 > 订单管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>商品标题:</td>
			<td><@s.textfield name="title_s" cssStyle="200px"/></td>
			<td class="tdpad">订单号:</td>
			<td><@s.textfield name="order_id_s" cssStyle="200px"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	        <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		<th width="3%" class="top_th"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('orderinfo.order_id')"/></th>
	    <th width="10%">订单号</th>
	    <th width="23%">供应信息</th>
	    <th width="7%">运费(元)</th>
	    <th width="10%">总共支付（元）</th>
	    <th width="10%">订单状态</th>
	    <th width="10%">下单时间</th>
	    <th width="7%">操作</th>
	  </tr>
	  
	  <#list orderinfoList as orderinfo>
	  <tr>
	    <td><input type="checkbox" name="orderinfo.order_id" value="${orderinfo.order_id?if_exists}"/></td>
	    <td align="center"><a href="/admin_Orderinfo_view.action?orderinfo.order_id=${orderinfo.order_id?if_exists}">${orderinfo.order_id?if_exists}</a></td>
	    <td>
	    <table cellspacing="0" boder="0">
	       <tr style="height:30px;">
	           <td align="left" rowspan="4" class="sty1" style="border-bottom: none;" >
	              <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.sin_date)?if_exists}')")}" target="_blank">
	                 <img src="${orderinfo.img_path?if_exists}" width="120" height="80" border="0" />
	              </a>
	           </td>
	           <td align="left" colspan="2" class="sty3" style="padding-left:10px;border-bottom: none;">
	             <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(orderinfo.supply_id)?if_exists}','${(orderinfo.sin_date)?if_exists}')")}" target="_blank">${orderinfo.title?if_exists}</a>
	           </td>
	       </tr>     
	       <tr style="height:30px;border-bottom: none;" ><td align="left" class="sty4" style="padding-left:10px;border-bottom: none;">数量:${orderinfo.goods_num?if_exists}</td><td class="sty4" style="border-bottom: none;">单价:${orderinfo.ord_price?if_exists}</td>
	       <tr style="height:30px;">
	       		<td align="left" colspan="2" class="sty3" style="padding-left:10px;border-bottom: none;">
	       			买家：${orderinfo.buyer_cust_name?if_exists}<br/>
	       			卖家：${orderinfo.seller_cust_name?if_exists}
	       		</td>
	       </tr>
	    </table>
	    </td>
	    <td align="center"><#if orderinfo.carriage_fee?if_exists==0>免运费<#else>${orderinfo.carriage_fee?if_exists}</#if></td>
	    <td align="center">${orderinfo.total_fee?if_exists}</td>
	    <td align="center">
	    	<a onclick="linkToInfo('/admin_Orderinfo_list.action','order_state_s=${(orderinfo.order_state)?if_exists}');">
	    		<#if orderinfo.order_state?if_exists=='0'>
	    			<span class="redcolor">等待买家付款</span>
	    		</#if>
	    		<#if orderinfo.order_state?if_exists=='1'>
	    			<span class="greencolor">等待卖家发货</span>
	    		</#if>
	    		<#if orderinfo.order_state?if_exists=='2'>
	    			<span class="bluecolor">等待买家确认收货</span>
	    		</#if>
	    		<#if orderinfo.order_state?if_exists=='3'>
	    			<span class="redcolor">交易成功</span>
	    		</#if>
	    		<#if orderinfo.order_state?if_exists=='4'>
	    			<span class="redcolor">交易关闭</span>
	    		</#if>
			</a>
	    </td> 
	    <td align="center">${orderinfo.ord_date?if_exists}</td>
	    <td align="center">
	    <a onclick="linkToInfo('/admin_Orderinfo_view.action','orderinfo.order_id=${orderinfo.order_id?if_exists}');"><img src="/include/common/images/view.gif" title="订单详情"/></a>
	    <a onclick="linkToInfo('/admin_Orderhistory_list.action','order_id_s=${orderinfo.order_id?if_exists}');"><img src="/include/common/images/bj.gif" title="订单历史"/></a>
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
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
     <input type="button" class="rbut" onclick="delInfo('orderinfo.order_id','/admin_Orderinfo_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="nav.isshow" id="admin_nav_state"/>
  <@s.hidden name="admin_nav_id" id="admin_nav_id"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden name="buyname_s"/>
  <@s.hidden name="buyname_s"/>
  <@s.hidden name="custname_s"/>
  <@s.hidden name="order_state_s"/>
  <@s.hidden name="starttime_s"/>
  <@s.hidden name="endtime_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Orderinfo_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		
			<tr>
				<td class="searchDiv_td">订单号:</td>
				<td><@s.textfield name="order_id_s"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">商品标题:</td>
				<td><@s.textfield name="title_s"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">卖家名称:</td>
				<td><@s.textfield name="sellername_s"/></td>
			</tr>
			<tr>
			    <td class="searchDiv_td">买家名称:</td>
			    <td><@s.textfield name="buyname_s"/></td>
		    </tr>
		   	<tr>
		     <td class="searchDiv_td">收货人姓名:</td>
		      <td><@s.textfield name="custname_s"/></td>
		    </tr>
		    <tr>
				<td class="searchDiv_td">地区:</td>
				<td><div id="areaDiv" style="margin-left:-5px;"></div></td>
			</tr>
		    <tr>
		    <td class="searchDiv_td">订单状态:</td>
			     <td>
			        <@s.select name="order_state_s" list="ordercommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
			     </td>
		    </tr>
		    <tr>
			    <td class="searchDiv_td" >下单时间段:</td>
			        <td>  
			        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
					               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 	
			        至
			        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
					               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		       </td>
		    </tr>
		
			<tr>
				<td align="center" colspan="2" style="border:0px;">
					<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
				<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
				</td>
		   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
