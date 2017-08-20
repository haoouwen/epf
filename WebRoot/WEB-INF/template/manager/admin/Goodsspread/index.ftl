<html>
  <head>
    <title>商品推广</title>   
  </head>
  <body>
<@s.form action="/admin_Goodsspread_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：营销推广 > 推广管理 > 商品推广</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>推广位置:</td>
			<td><@s.select name="spread_position_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
			</td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsspread.trade_id')"/></th>
		    <th width="11%"  >推广位置</th>
		    <th width="19%"  >推广商品</th>
		    <th width="13%"  >所属分类</th>
		    <th width="13%"  >所属地区</th>
		    <th width="12%"  >推广开始时间</th>
		    <th width="12%"  >推广结束时间</th>
		    <th width="12%"  >操作时间</th>
		    <th width="5%"  >操作</th>
	  </tr>
	  
	  <#list goodsspreadList as goodsspread>
	    <tr >
		    <td><input type="checkbox" name="goodsspread.trade_id" value="${goodsspread.trade_id?if_exists}"/></td>
		    <td align="center">
				${goodsspread.para_key?if_exists}
		    </td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Goodsspread_view.action','goodsspread.trade_id=${goodsspread.trade_id?if_exists}');" title="${goodsspread.goods_name?if_exists}">
		    	 <#if goodsspread.goods_name?if_exists?length lt 30>
		    	 ${goodsspread.goods_name?if_exists}
				 <#else>
				 ${goodsspread.goods_name[0..29]?if_exists}
				 </#if>
				</a>
		    </td>
		    <td align="center">
				<#if goodsspread.cat_attr?if_exists!=null && goodsspread.cat_attr?if_exists!=''>
	    			<#if goodsspread.cat_attr ?length lt 20>
		    			${goodsspread.cat_attr?if_exists}
		    		<#else>
		    			${goodsspread.cat_attr[0..19]?if_exists}...
			    		</#if>
		    	<#else>
		    	 	-
		    	</#if>
		    </td>
		    <td align="center">
				<#if goodsspread.area_attr?if_exists!=null && goodsspread.area_attr?if_exists!=''>
	    		${goodsspread.area_attr?if_exists} 
		    	<#else>
		    	 	-
		    	</#if>
		    </td>
		    <td align="center">
				${goodsspread.spread_starttime?if_exists?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
		    <td align="center">
				${goodsspread.spread_endtime?if_exists?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
		    <td align="center">
				${goodsspread.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
		      <td align="center">
				<a onclick="linkToInfo('/admin_Goodsspread_view.action','goodsspread.trade_id=${goodsspread.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Goodsspread_add.action','');" value="添加商品推广">
     <input type="button" class="rbut"onclick="delInfo('goodsspread.trade_id','/admin_Goodsspread_delete.action')" value="删除">
     <img class="ltip" src="/include/common/images/light.gif" alt="前台推广商品顺序按结束时间降序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>

  </body>
</html>
