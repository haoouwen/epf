<html>
  <head>
    <title>商品列表</title> 
      <script src="/wro/admin_goods_index.js" type="text/javascript"></script>
      <script type="text/javascript" src="/include/common/js/get-taxrate.js"></script>
      <#include "/include/uploadInc.html">
      <script type="text/javascript"/>
      $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	        loadTax("${taxIdStr?if_exists}","");
	   });
		 //导入订单
		function importGoods(actionName){
		$("#iname_s").val($("#uploadresult").val());
		var iname=$("#iname_s").val();
		if(iname==''){
		alert("请选择xls文件");
		return false ;
			}
			if(window.confirm("确定要导入商品吗?")) {
				document.forms["indexForm"].action=actionName;
				document.forms["indexForm"].submit();
			}
		}
      </script>  
  </head>
  <body>
<@s.form action="/admin_Goods_list.action" method="post"  id="indexForm">
<@s.hidden name="goods.is_up" id="admin_goods_is_ip"/>
<@s.hidden  id="admin_hidden_action" value="/admin_Goods_list.action"/>
<@s.hidden  id="admin_goods_tab"/>
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 商品管理 > 商品列表</div>
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
	       <td><input type="button" onclick="goodsShowDiv('/admin_Goods_list.action','搜索','高级查询');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="2%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/></th>
		    <th width="5%"  >商品图片</th>
		    <th width="25%"  >商品名称</th>
		    <th width="15%"  >所属分类</th>
		    <th width="5%"  >库存(件)</th>
		    <th width="5%"  >原价(元)</th>
		    <th width="5%"  >售价(元)</th>
		     <th width="5%"  >贸易方式</th>
		    <th width="8%"  >发布时间</th>
		    <th width="4%"  >操作</th>
	  </tr>
	  
	  <#list goodsList as goods>
	    <tr >
		    <td><input type="checkbox" name="goods.goods_id" value="${goods.goods_id?if_exists}"/></td>
		    <td align="center">
				<img src="${goods.list_img?if_exists}" width="50px" height="50px">
		    </td>
		    <td style="text-align:left;padding-left:10px;line-height:20px">
		        <#if (goods.lab)?if_exists?index_of('1')gt -1 >
		    		<font class="redcolor" title="热卖商品">[热]</font>
		    	</#if>
				<#if (goods.lab)?if_exists?index_of('4')gt -1>
		    		<font class="redcolor" title="推荐商品">[荐]</font>
		    	</#if>
		    	<#if (goods.lab)?if_exists?index_of('3') gt -1>
		    		<font class="redcolor" title="精品商品">[精]</font>
		    	</#if>
		    	<#if goods.is_ship ='0'>
		    		<font class="redcolor" title="免运费商品">[免]</font>
		    	</#if>
		      	<a onclick="document.getElementById('indexForm').action='/admin_Goods_view.action?goods.goods_id=${goods.goods_id?if_exists}';document.getElementById('indexForm').submit();" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists} </a>
		    	<br/>
		    	 商品编号：
		    	<#if goods.goods_no?if_exists!=null && goods.goods_no?if_exists!=''>
		    		${goods.goods_no?if_exists}
		    	<#else>
		    	 	-
		    	</#if> 
		    	<#if goods.sale_name?if_exists !=null && goods.sale_name?if_exists !="">
		    	 <font color="red">[${goods.sale_name?if_exists}]</font>
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
		    <td align="center">
				<a onclick="document.getElementById('indexForm').action='/admin_Goods_view.action?goods.goods_id=${goods.goods_id?if_exists}';document.getElementById('indexForm').submit();" title="编辑商品"><img src="/include/common/images/bj.gif" /></a>
				<a href="/admin_Goods_view.action?goods.goods_id=${goods.goods_id?if_exists}" target="_blank" title="新窗口打开"><img src="/include/admin/images/new_window.gif" /></a>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Goods_selcat.action','');" value="添加商品">
     <input type="button" class="rbut"onclick="delInfo('goods.goods_id','/admin_Goods_delete.action')" value="删除">
     <input type="button" class="rbut"onclick="updateState('1','goods.goods_id','/admin_Goods_storedown.action');" value="下架">
	  <input type="button" class="rbut"onclick="updateT('4','goods.goods_id','/admin_Goods_tJ.action');" value="推荐">
	   <input type="button" class="rbut"onclick="updateT('0','goods.goods_id','/admin_Goods_nTJ.action');" value="取消推荐">
	   <input type="button" onclick="importGoodsDiv('0','导入商品');" class="rbut" value="导入商品">
	   <input type="button" class="rbut" onclick="goodsShowDiv('/admin_Goods_exportGoods.action','导出商品','导出商品');" value="导出商品"/>
	   <input type="button" class="rbut" onclick="goodsShowDiv('/admin_Goods_exportKJTGoods.action','KJT商品导出','KJT商品导出-改商品编号、KJT商品ID、KJT仓库编码');" value="KJT商品导出">
	   <input type="button" onclick="importGoodsDiv('1','KJT商品导入');" class="rbut" value="KJT商品导入">
	   <input type="button" onclick="pricepage('goods.goods_id');" class="rbut" value="统一调价">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cat_attr_s"/> 
  <@s.hidden  id="sgis" name="sgis"/>   
  <@s.hidden  name="iname" id="iname_s"/>
   <@s.hidden  id="chb_id" name="chb_id"/>   
   <@s.hidden  id="hidden_nowprice" name="hidden_nowprice"/>   
   <@s.hidden  id="hidden_updatetype" name="hidden_updatetype"/>   
   <@s.hidden  id="hidden_pricetype" name="hidden_pricetype"/>   
   <@s.hidden  id="hidden_pricetype2" name="hidden_pricetype2"/> 
   <@s.hidden  id="hidden_mouth" name="hidden_mouth"/> 
   <@s.hidden  id="hidden_pricevalue" name="hidden_pricevalue"/> 
   
   <@s.hidden  name="tax_attr" />
  <#include "/WEB-INF/template/manager/admin/Goods/searchmodehidden.ftl">
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
<#include "/WEB-INF/template/manager/admin/Goods/searchmode.ftl">
<!--搜索区域结束-->
<!--搜索区域开始-->
<div  style="display:none;"  id="importDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			
			<tr>
			     <td class="searchDiv_td" width="70px">选择文件:</td>
				<td>
	             	  <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			<@s.textfield name="img_pathmulti"   id="uploadresult" cssStyle="width:200px;" readonly="true"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
		              		 <script>uploadCommonComponent("","uploadifyfile","uploadresult","fileQueue","file",false);</script>
	             		</tr>
	             	</table> 
	             </td>
			</tr>
			<tr id="m_down_id">
			     <td class="searchDiv_td" width="70px">模版下载:</td>
				<td>
				 <a href="/include/commonfiles/goods20150918.xls" id="download"><font color="blue">点击下载模板</font></a>
	             </td>
			</tr>
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	                
	             	 <input  id="d_goods" type="button" id="b_goods" onclick="importGoods('/admin_Goods_importGoods.action');"  value="导入商品">
	             	  <input style="display:none;" id="k_goods" type="button"  onclick="importGoods('/admin_Goods_importKtjGoods.action');"  value="KJT商品导入">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('importDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--搜索区域结束-->

<!--批量修改价格开始-->
<div  style="display:none;"  id="priceDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			
			<tr>
			     <td><input type="radio" name="updatetype" value='0'></td>
			     <td  width="70px">直接修改:</td>
				<td>
	             	<select class="pricetype">
	 							<option  value="0">
	 								销售价
	 							</option>
	 							<option value="1">
	 								原价
	 							</option>
			 				</select>
			 				= <input  id="nowprice" value='' onkeyup="checkRMB(this);"/>
	             </td>
			</tr>
			<tr id="m_down_id">
			<td><input type="radio" name="updatetype" value='1'></td>
			     <td  width="70px">公式修改</td>
				<td>
				<select class="pricetype2">
	 							<option value="0">
	 								销售价
	 							</option>
	 							<option  value="1">
	 								原价
	 							</option>
			 				</select>
			 				=
			 					<select class="pricetype3">
			 				<option  value="0">
	 								销售价
	 							</option>
	 							<option  value="1">
	 								原价
	 							</option>
			 				</select>
			 				<select class="mouth">
			 				<option  value="0">
	 								+
	 							</option>
	 							<option  value="1">
	 								-
	 							</option>
	 							<option  value="2">
	 								*
	 							</option>
	 							<option  value="3">
	 								/
	 							</option>
			 				</select>
			 				<input  name="pricevalue" id="pricevalue" Style="width:60px;"  onkeyup="checkRMB(this);"/>
	             </td>
			</tr>
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	             	 <input  id="d_goods" type="button" id="b_goods" onclick="updateprice();"  value="确定">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('importDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--批量修改价格结束-->
  </body>
</html>
