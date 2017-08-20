<html>
  <head>
    <title>赠品列表</title>
  </head>
  <body>
<@s.form action="/admin_Freegoods_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销推广 > 赠品 > 赠品列表</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >赠品名称:</td><td><@s.textfield name="title_s" cssStyle="width:150px;"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('freegoods.fg_id')"/></th>
			    
                 	<th width="6%">排序</th>
			    
                 	<th width="15%">赠品名称</th>
			    
                 	<th width="15%">所属分类</th>

                 	<th width="8%">是否启用</th>
			    
                 	<th width="16%">发布时间</th>
			    
                 	 <th  width="6%">操作</th>
               </tr>
			    <#list freegoodsList as freegoods>
				 <tr >
			         <td><input type="checkbox" name="freegoods.fg_id" value="${freegoods.fg_id?if_exists}"/></td>
				    
			         	<td align="center"> 
			         	<input name="isort_no"  value="${freegoods.fg_sort?if_exists}" style="width:50px" onkeyup="checkNum(this)" />
			         	</td>
			         	<td align="center">
			         	<a onclick="linkToInfo('/admin_Freegoods_view.action','freegoods.fg_id=${freegoods.fg_id?if_exists}');">
			         	${freegoods.fg_name?if_exists}
			         	</a></td>
				        <td align="center">${freegoods.cat_attr?if_exists}</td>
			         	<td align="center">
			         	  <#if freegoods.fg_state?if_exists=='0'> 
			         	    <font color="green">启用</font>
			         	   <#else>
			         	   <font color="red">禁用</font> 
			         	   </#if>
			         	</td>
				        
			         	<td align="center">${freegoods.fg_date?if_exists}</td>
				    <td align="center"><a onclick="linkToInfo('/admin_Freegoods_view.action','freegoods.fg_id=${freegoods.fg_id?if_exists}');">
					  <img src="/include/common/images/bj.gif" /></a></td>
				    
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Freegoods_add.action','');" value="添加赠品">
             <input type="button" class="rbut" onclick="updateBatchState('0','freegoods.fg_id','/admin_Freegoods_updateState.action','启用');" value="启用">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','freegoods.fg_id','/admin_Freegoods_updateNoState.action','禁用');" value="禁用">  
			 <input type="button" class="rbut"onclick="updateSort('freegoods.fg_id','isort_no','/admin_Freegoods_updateSort.action');" value="排序">
             <input type="button" class="rbut"onclick="delInfo('freegoods.fg_id','/admin_Freegoods_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
</body>
</html>

