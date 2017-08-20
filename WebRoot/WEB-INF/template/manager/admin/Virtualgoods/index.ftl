<html>
  <head>
    <title>虚拟商品列表</title> 
      <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
      <script type="text/javascript" src="/include/admin/js/goodsindex.js"></script> 
      <script type="text/javascript"/>
      $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","virtual");
	   });
      </script>  
  </head>



<body>
<@s.form action="/admin_Virtualgoods_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商品管理 > 虚拟商品管理 > 虚拟商品列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >商品名称:</td> 
			<td><@s.textfield name="goods_name_s"  cssStyle="width:180px;"/></td>
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
	       <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		     <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/></th>
   
	         <th width="10%"  >商品编号</th>
	         
	         <th width="20%"  >商品名称</th>
	     	
	     	 <th width="15%"  >所属分类</th>
	    
	         <th width="10%"  >发布人</th>
	         
	     	 <th width="7%"  >审核状态</th>
	    
	     	 <th width="8%"  >发布时间</th>
    	    
    	     <th width="5%" >操作</th>
  </tr>
  
  <#list goodsList as goods>
  <tr>
    <td><input type="checkbox" name="goods.goods_id" value="${goods.goods_id?if_exists}"/></td>    
	      <td align="center">
    	<#if goods.goods_no?if_exists!=null && goods.goods_no?if_exists!=''>
	    		${goods.goods_no?if_exists}
	    	<#else>
	    	 	-
	    	</#if>
    	</td>
	      
	      <td align="left" >
	      <#if goods.goods_name?if_exists?length lt 30>
	      	<a onclick="linkToInfo('/admin_Virtualgoods_view.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists} </a>
		 <#else>
		 	<a onclick="linkToInfo('/admin_Virtualgoods_view.action','goods.goods_id=${(goods.goods_id)?if_exists}');" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists[0..29]}</a>
		 	..
		 </#if>
	      
	      </td>
	    
	    <td align="center">${goods.cat_attr?if_exists}</td>
    	
  	 		 <td align="center">
		    	<#if goods.goods_user_name?if_exists!=null && goods.goods_user_name?if_exists!=''>
			    		${goods.goods_user_name?if_exists}
			    	<#else>
			    	 	-
		    	</#if>
    	 	</td>
    	
    	<td align="center">
    		<#list infoStateList as infoState>
				<#if goods.info_state?if_exists==infoState.para_value>
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
    
    	<td align="center">${goods.in_date?if_exists}</td>
    
    
          
        <td align="center"><a onclick="linkToInfo('/admin_Virtualgoods_view.action','goods.goods_id=${goods.goods_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Virtualgoods_selcat.action','');" value="添加虚拟商品">
     <input type="button" class="rbut" onclick="delInfo('goods.goods_id','/admin_Virtualgoods_delete.action')" value="删除">
             <input type="button" class="rbut" onclick="updateState('1','goods.goods_id','/admin_Virtualgoods_storedown.action');" value="下架">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="cat_attr_s"/>
  <@s.hidden name="goods.is_up" id="admin_goods_is_ip"/>
  <@s.hidden name="goods_no_s" />
  <@s.hidden name="brand_name_s" />
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Virtualgoods_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td">商品编号:</td> 
				<td><@s.textfield name="goods_no_s"  cssStyle="width:180px;"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">商品名称:</td> 
				<td><@s.textfield name="goods_name_s"  cssStyle="width:180px;"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">所属分类:</td> 
				<td>
         			<div id="catDiv" ></div>
				</td>
			</tr>
			<tr>
				<td class="searchDiv_td">商品品牌:</td> 
				<td><@s.textfield name="brand_name_s"  cssStyle="width:180px;"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">审核状态:</td> 
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
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>

