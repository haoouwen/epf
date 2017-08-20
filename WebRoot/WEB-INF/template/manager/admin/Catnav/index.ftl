<html>
  <head>
    <title>分类导航列表</title>
  </head>
  <body>
<@s.form action="/admin_Catnav_list.action" method="post" id="indexForm">

        <div class="postion">当前位置： 网站管理> 商城导航 > 分类导航</div>
        <div class="rtdcont">
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('catnav.cn_id')"/></th>
                  	<th width="8%">排序</th>
			        <th width="20%">分类别名</th>
                 	<th width="40%">分类名称</th>
			    
                 <th  width="5%">操作</th>
               </tr>
			    <#list catnavList as catnav>
				 <tr >
			         <td><input type="checkbox" name="catnav.cn_id" value="${catnav.cn_id?if_exists}"/></td>
			         
			         	<td align="center"><input name="isort_no" value="${catnav.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/></td>
				         <td align="center">
	    			      ${catnav.custom_name?if_exists}
    	                 </td>    
			         	  <td align="center">
			         	  
	    			      ${catnav.cat_attr?if_exists}
	    		          
    	                 </td>      
				    <td align="center"><a onclick="linkToInfo('/admin_Catnav_view.action','catnav.cn_id=${catnav.cn_id?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Catnav_add.action','');" value="添加">
             <input type="button" class="rbut" onclick="updateSort('catnav.cn_id','isort_no','/admin_Catnav_updateSort.action');" value="排序"> 
             <input type="button" class="rbut"onclick="delInfo('catnav.cn_id','/admin_Catnav_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		<@s.hidden   name="cat_attr_s"/>
		<@s.hidden  name="catnav.cn_id"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

