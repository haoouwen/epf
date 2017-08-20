<html>
  <head>
    <title>商城标签商品</title>
    <script src="/wro/admin_goods_update.js" type="text/javascript"></script>
	<link href="/wro/admin_goods_update.css" rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
	 <#include "/WEB-INF/template/manager/searchNavtabGoods.ftl"/> 
  </head>
  <body>
<@s.form action="/admin_Navigation_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：网站管理 > 商城导航 >商城标签商品</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
             	 	<td >商品名称:&nbsp;</td>
                  <td><@s.textfield name="title_s" cssClass="search_input"/>&nbsp;</td>
                  <td><input type="submit" class="rbut" value="查询">&nbsp;</td>
                 <td><input type="button" class="rbut" value="添加商品" onclick="addRalateGoodsNav(${tab_id_s},${tab_number_s},'searchgoods','0',this);"/></td>
             </tr>
             </table>
           </div>
           <!--后台table-->
           <div class="rtable">
             <table id="selulrg" width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                    <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('navigation.n_id')"/></th>
                 	<th width="8%">排序</th>
                 	<th width="20%">商品名称</th>
			        <th width="15%">所属分类</th>
			        <th width="10%">价格</th> 
                 	<th width="20%">时间</th>
               </tr>
			    <#list navigationList as navigation>
				 <tr >
                       <td><input type="checkbox" name="navigation.n_id" value="${navigation.n_id?if_exists}"/></td>
				       <td align="center"><input name="isort_no"  value="${navigation.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/></td>
			         	<td align="center">${navigation.goods_name?if_exists}</td>
				        <td align="center">${navigation.cat_attr?if_exists}</td>
			         	 <td align="center">${navigation.sale_price?if_exists}</td>
			         	<td align="center">${navigation.in_date?if_exists}</td>
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="updateSort('navigation.n_id','isort_no','/admin_Navigation_updateSort.action');" value="排序">
             <input type="button" class="rbut" onclick="delInfo('navigation.n_id','/admin_Navigation_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		<@s.hidden  name="tab_id_s"/>
		<@s.hidden  name="tab_number_s"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

