<html>
  <head>
    <title>商品库存列表</title> 
      <script src="/wro/admin_goods_index.js" type="text/javascript"></script>
      <script type="text/javascript" src="/include/common/js/get-taxrate.js"></script>
      <script type="text/javascript"/>
      $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	       loadTax("${taxIdStr?if_exists}","");
	   });
      </script>  
  </head>
  <body>
<@s.form action="/admin_Goods_gcountlist.action" method="post"  id="indexForm">
<@s.hidden name="goods.is_up" id="admin_goods_is_ip"/>
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 库存管理 > 商品库存</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
		     <td>商品编号:</td>
		     <td><@s.textfield name="goods_no_s" cssClass="txtmaxinput"/>&nbsp;</td>
		     <td class="tdpad">商品名称:</td>
		     <td><@s.textfield name="goods_name_s"  cssStyle="width:250px;"/>&nbsp;</td>
			 </td>
			 <td>库存提示:</td>
			 <td><@s.select name="stock_str" list=r"#{'':'请选择','0':'警告','1':'未警告'}"/>&nbsp;</td>
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
		    <th width="3%"></th>
		    <th width="5%"  >商品编号</th>
		    <th width="25%"  >商品名称</th>
		    <th width="15%"  >所属分类</th>
		    <th width="5%"  >原价(元)</th>
		    <th width="5%"  >售价(元)</th>
		    <th width="5%"  >贸易方式</th>
		    <th width="5%"  >库存量</th>
		    <th width="5%">操作</th>
	  </tr>

	  <#list goodsList as goods>
	    <tr  <#if (goods.total_stock lte cfg_Cautionstock)> style="color:red;"</#if> >
		    <td>
		    <#if goods.trade_way?if_exists=='1'>
		     <#if (goods.total_stock lte cfg_Cautionstock)>
		      <img class="ltip" src="/include/common/images/light.gif" alt="提示：当前库存数量小于/等于安全库存数,请注意补充库存!">
		      <#else>
		         -
		    </#if>
		    <#else>
		     - -
		     </#if>
		    </td>
		    <td align="center">
		    	<#if (goods.goods_no)?if_exists!=null && (goods.goods_no)?if_exists!=''>
		    		${(goods.goods_no)?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
		    </td>
		    <td style="text-align:center;">
				<#if (goods.goods_name)?if_exists?length lt 21>
		      		<a onclick="linkToInfo('/admin_Goods_gcountview.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="${(goods.goods_name)?if_exists}">${(goods.goods_name)?if_exists} </a>
				 <#else>
				 	<a onclick="linkToInfo('/admin_Goods_gcountview.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="${(goods.goods_name)?if_exists}">${(goods.goods_name)?if_exists[0..20]}</a>
				 	...
				 </#if>
		    </td>
		    <td align="center">
				${goods.cat_attr?if_exists}
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
				${goods.total_stock?if_exists}
		    </td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Goods_gcountview.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="修改"><img src="/include/common/images/bj.gif" /></a>  
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
<!--  <div class="bsbut">
       
   </div>-->
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cat_attr_s"/>  
  <#include "/WEB-INF/template/manager/admin/Goods/searchmodehidden.ftl">
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->	 
<#include "/WEB-INF/template/manager/admin/Goods/searchmode.ftl">  
<!--搜索区域结束-->
  </body>
</html>
