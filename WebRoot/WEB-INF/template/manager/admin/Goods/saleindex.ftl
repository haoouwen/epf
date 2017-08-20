<html>
  <head>
    <title>促销商品</title> 
      <script src="/wro/admin_goods_index.js" type="text/javascript"></script>
      <script type="text/javascript" src="/include/common/js/get-taxrate.js"></script>
  </head>
  <body>
<@s.form action="/admin_Goods_saleList.action" method="post"  id="indexForm">
<@s.hidden name="goods.is_up" id="admin_goods_is_ip"/>
<@s.hidden  id="admin_goods_tab"/>
<!--当前位置-->
	<div class="postion">当前位置：营销推广 > 促销管理 > 促销商品</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
		   <td>商品名称:</td>
		   <td><@s.textfield name="goods_name_s" cssStyle="width:250px;"/></td>
		   <td class="tdpad">商品编号:</td>
		   <td><@s.textfield name="goods_no_s"  cssStyle="width:150px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="5%"  >商品图片</th>
		    <th width="25%"  >商品名称</th>
		    <th width="15%"  >所属分类</th>
		    <th width="5%"  >库存(件)</th>
		    <th width="5%"  >原价(元)</th>
		    <th width="5%"  >售价(元)</th>
		     <th width="5%"  >贸易方式</th>
		    <th width="8%"  >发布时间</th>
	  </tr>
	  
	  <#list goodsList as goods>
	    <tr >
		    <td align="center">
				<img src="${goods.list_img?if_exists}" width="50px" height="50px">
		    </td>
		    <td style="text-align:left;padding-left:10px;line-height:20px">
				<#if goods.goods_name?if_exists?length lt 31>
		      		<a onclick="linkToInfo('/admin_Goods_view.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists} </a>
				 <#else>
				 	<a onclick="linkToInfo('/admin_Goods_view.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists[0..30]}</a>
				 </#if>
		    	<br/>
		    	 商品编号：
		    	<#if goods.goods_no?if_exists!=null && goods.goods_no?if_exists!=''>
		    		${goods.goods_no?if_exists}
		    	<#else>
		    	 	-
		    	</#if> 
		    	<#if goods.sale_name?if_exists !=null && goods.sale_name?if_exists !="">
		    	 <#list goods.sale_name?split(',') as sale_name>
		    	 <font color="red">[${sale_name?if_exists}] </font>
		    	 </#list>
		    	 </#if>
		    </td>
		    <td align="center">
				${goods.cat_attr?if_exists}
		    </td>
		      <td align="center">
				${goods.total_stock?if_exists}
		    </td>
		      <td align="center">
				${goods.goods_market_price?if_exists}
		    </td>
		      <td align="center">
				${goods.min_sale_price?if_exists}
		    </td>
             <td align="center">
		      <#if goods.depot_id?if_exists=='1'>
		      <font color="blue">跨境直邮</font>
		      <#else>
		      <font color="green">保税仓</font>
		      </#if>  
		    </td>
		    <td align="center">
				${goods.in_date?if_exists}
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
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cat_attr_s"/> 
  <@s.hidden  id="sgis" name="sgis"/>   
  <@s.hidden  name="iname" id="iname_s"/>
   <@s.hidden  id="chb_id"/>   
  <#include "/WEB-INF/template/manager/admin/Goods/searchmodehidden.ftl">
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
<#include "/WEB-INF/template/manager/admin/Goods/searchmode.ftl">
<!--搜索区域结束-->
  </body>
</html>
