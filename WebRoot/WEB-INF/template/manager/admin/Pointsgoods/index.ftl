<html>
  <head>
    <title>积分商品</title>   
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","points");
      });
	</script>
  </head>
<body>
<@s.form action="/admin_Pointsgoods_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:营销推广 > 积分商品 > 积分商品</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
	        <td >商品名称:</td> 
			<td><@s.textfield name="goods_name_s" cssStyle="200px"/></td>
			<td class="tdpad">会员名称:</td> 
			<td><@s.textfield name="cust_name_s" cssStyle="200px"/></td>
			<td class="tdpad">审核状态:</td> 
			<td>
			<select name="info_state_s" style="width:70px;">
				<option value="" selected>请选择
				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
				       <option value="${infoState.para_value?if_exists}" <#if info_state_s?if_exists==infoState.para_value?if_exists>selected</#if>>${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='3'>
				       <option value="${infoState.para_value?if_exists}" <#if info_state_s?if_exists==infoState.para_value?if_exists>selected</#if>>${infoState.para_key?if_exists}
				    </#if>
    			</#list>
			</select>
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
	    	 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('pointsgoods.trade_id')"/></th>
	   
	    	 <th width="5%" >排序</th>
	   
		 	 <th width="30%" >商品名称</th>
		 	 
		 	 <th width="10%" >购买积分</th>
		
		 	 <th width="8%" >审核状态</th>
		
		 	 <th width="10%" >会员名称</th>
		
		 	 <th width="7%" >库存</th>
		 	 
		 	 <th width="10%" >发布日期</th>
	    
	      	 <th width="7%">操作</th>
      </tr>
	  
	   <#list pointsgoodsList as pointsgoods>
		  <tr>
		    <td><input type="checkbox" name="pointsgoods.trade_id" value="${pointsgoods.trade_id?if_exists}"/></td>    
		 	    
		 	    <td align="center"><input name="isort_no" value="${pointsgoods.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/></td>
		 	
		    	<td align="left">
		    	<a onclick="linkToInfo('/admin_Pointsgoods_view.action','pointsgoods.trade_id=${pointsgoods.trade_id?if_exists}');" title=" ${pointsgoods.goods_name?if_exists}">
		    	     <#if  pointsgoods.goods_name?if_exists!=null && pointsgoods.goods_name?if_exists!='' >
					       <#if pointsgoods.goods_name?if_exists? length lt 36>
				            	 ${pointsgoods.goods_name?if_exists}
				            <#else>
				            	 ${pointsgoods.goods_name[0..35]?if_exists}
				            </#if>
				</a>
					<#else>
					该商品已删除
					</#if>
		    	</td>
		    
		    	<td align="center">${pointsgoods.buy_points?if_exists}</td>
		    
		    	<td align="center">
				    <#list infoStateList as infoState>
	    				<#if pointsgoods.info_state?if_exists==infoState.para_value>
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
		    
		    	<td align="center">
		    		<#if pointsgoods.cust_name?if_exists!=''&& pointsgoods.cust_name?if_exists!=null>
		    			${pointsgoods.cust_name?if_exists}
			    	<#else>
			    	-
			    	</#if>
		    	</td>
		    
		    	<td align="center">${pointsgoods.stock?if_exists}</td>
		    	
		    	<td align="center">${pointsgoods.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Pointsgoods_view.action','pointsgoods.trade_id=${pointsgoods.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Pointsgoods_add.action','');" value="添加积分商品">
     <input type="button" class="rbut" onclick="updateSort('pointsgoods.trade_id','isort_no','/admin_Pointsgoods_updateSort.action');" value="排序">
     <input type="button" class="rbut" onclick="delInfo('pointsgoods.trade_id','/admin_Pointsgoods_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="pointsgoods_sortno_id" id="pointsgoods_sortno_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

