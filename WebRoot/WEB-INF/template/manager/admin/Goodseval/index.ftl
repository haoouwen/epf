<html>
  <head>
     <title>商品评价</title>   
  </head>
  <body>
<@s.form action="/admin_Goodseval_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 商品相关 > 商品评价</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >商品评级:</td>
			<td><@s.select name="g_eval_s" list=r"#{'':'请选择','1':'好评','0':'中评','-1':'差评'}"  /></td>
			<td class="tdpad">是否有效:</td>
			<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}" /></td>
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
             <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodseval.trade_id')"/></th>
		    <th width="30%"  >商品名称</th>
		    <th width="5%"  >商品评级</th>
		    <th width="10%"  >评价时间</th>
		     <th width="13%"  >订单号</th>
		     <th width="8%"  >评价人</th>
		    <th width="8%"  >解释人</th>
		    <th width="10%"  >解释时间</th>
		    <th width="6%"  >是否有效</th>
		    <th width="6%"  >操作</th>
	  </tr>
	  
	  <#list goodsevalList as goodseval>
	    <tr >
	      <td><input type="checkbox" name="goodseval.trade_id" value="${goodseval.trade_id?if_exists}"/></td>
		    <td align="center">
		        
				 <#if goodseval.goods_name?if_exists!=''&&goodseval.goods_name?if_exists!=null>
				         <a  href="/mall-goodsdetail-${goodseval.goods_id?if_exists}.html"  target="_blank">
			    			${goodseval.goods_name?if_exists}
			    		 </a>
				 <#else>
				    该商品已下架
				 </#if>
				
		    </td>
		     <td align="center">
				<#if goodseval.g_eval?if_exists=='1'>好评</#if>
				<#if goodseval.g_eval?if_exists=='0'>中评</#if>
				<#if goodseval.g_eval?if_exists=='-1'>差评</#if>
				<#if goodseval.g_eval?if_exists=='2'>未评价</#if>
		    </td>
		     <td align="center">
				${goodseval.eval_date?if_exists}
		    </td>
		       <td align="center">
				${goodseval.order_id?if_exists}
		    </td>
		       <td align="center">
				${goodseval.buy_cust_name?if_exists}
		    </td>
		     <td align="center">
				<#if goodseval.sell_user_name?if_exists==''>暂无回复<#else>${goodseval.sell_user_name?if_exists}</#if>
		    </td>
		     <td align="center">
				<#if goodseval.sell_user_name?if_exists!=''>
			    		${goodseval.explan_date?if_exists}
				 <#else>
				 -
				 </#if>
		    </td>
		     <td align="center">
				<#if goodseval.is_enable?if_exists=='0'><font color='green'>有效</font><#else><font color='red'>无效</font></#if>
		    </td>
		     <td align="center">
				<a onclick="linkToInfo('/admin_Goodseval_view.action','goodseval.trade_id=${goodseval.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <div class="clear"/>
	<!--按钮操作存放-->
	      <div class="bsbut">
	     <input type="button" class="rbut"onclick="delInfo('goodseval.trade_id','/admin_Goodseval_delete.action')"  value="删除">
	<!--按钮操作存放结束-->
</div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="goods_id_s"/>
  <@s.hidden  name="explan_man_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodseval_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">商品评级:</td>
			<td><@s.select name="g_eval_s" list=r"#{'':'请选择','1':'好评','0':'中评','-1':'差评'}" /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">是否有效:</td>
			<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}" /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">解释人:</td>
			<td><@s.textfield name="explan_man_s"  /></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
			<@s.hidden  name="goods_id_s"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
