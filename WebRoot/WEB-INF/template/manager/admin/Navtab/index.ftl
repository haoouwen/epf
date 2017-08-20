<html>
  <head>
    <title>商城标签列表</title>
  </head>
  <body>
<@s.form action="/admin_Navtab_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：网站管理 > 商城导航 >商城标签</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >商城标签名称:&nbsp;</td>
                  <td><@s.textfield name="title_s" cssClass="search_input"/>&nbsp;</td>
                  <td class="tdpad">标签类型:</td>
		          <td><@s.select name="touch_s" list=r"#{'':'请选择','0':'手机端','1':'PC端'}"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                    <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('navtab.tab_id')"/></th>
                 	<th width="5%">排序</th>
                 	<th width="20%">标签名称</th>
                 	<th width="12%">备注</th>
                 	<th width="8%">商品数量</th>
                 	<th width="10%">显示端</th>
                 	<th width="30%">链接地址</th> 
                 <th  width="10%">操作</th>
               </tr>
			    <#list navtabList as navtab>
				 <tr >
				     <td><input type="checkbox" name="navtab.tab_id" value="${navtab.tab_id?if_exists}"/></td>
                   	<td align="center"><input name="isort_no"  value="${navtab.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/></td>
			         	<td align="center">${navtab.tab_name?if_exists}</td>
			         	<td align="center">${navtab.tab_remark?if_exists}</td>
				     	<td align="center"> 
		                 ${navtab.gcount?if_exists}
				     	 </td>
			         	 <td align="center">
			         	  <#if navtab.touch?if_exists=='0'>
			         	   <font color="red">手机端</font>
			         	  </#if>
			         	  <#if navtab.touch?if_exists=='1'>
			         	   <font color="green">PC端</font>
			         	 </#if>
			         	 </td>
			         	 <td align="center">
			         	  ${navtab.tab_url?if_exists}
			         	</td>
			         	
				      <td align="center"><a onclick="linkToInfo('/admin_Navtab_view.action','navtab.tab_id=${navtab.tab_id?if_exists}');">
					  <img src="/include/common/images/bj.gif" /></a>
					  <a onclick="linkToInfo('/admin_Navigation_list.action','tab_id_s=${(navtab.tab_id)?if_exists}&tab_number_s=${(navtab.tab_number)?if_exists}');" title="查看该标签下商品">
					  <img src="/include/common/images/audit.png"/></a>
					  
					     <#if navtab.start?if_exists=='0'>
			         	       <img src="/include/common/images/light.gif" title="系统默认标签,无法删除" border="0" /> 
			         	  <#else>
			         	     <#if (navtab.gcount)?if_exists==0>
							    <a href="javascript:delOneInfo('chb_id','/admin_Navtab_delete.action','${(navtab.tab_id)?if_exists}');">
			    				<img src="/include/admin/images/delete.png" border="0"/></a>
			    			  <#else>
			    			    <a href="javascript:void(0);"> 
			    				  <img src="/include/admin/images/delete_navtab.png" border="0" title="无法删除该标签,请先清空标签下的商品"/>
			    				</a>
			    			  </#if>
			         	</#if>
					
					  </td>
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Navtab_add.action','');" value="添加">
             <input type="button" class="rbut" onclick="updateSort('navtab.tab_id','isort_no','/admin_Navtab_updateSort.action');" value="排序">
             
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
		 <@s.hidden name="navtab.tab_id"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

