<html>
  <head>
    <title>门店服务介绍列表</title>
      <#include "/include/uploadInc.html">
  </head>
  <body>
<@s.form action="/admin_Storeintro_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：会员管理 > 区域代理 > 门店服务介绍</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >门店服务介绍名称:</td><td><@s.textfield name="title_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('storeintro.sto_id')"/></th>
                 
                 	<th width="6%">排序</th>
			    
                 	<th width="25%">门店服务介绍名称</th>
			    
                 	<th width="20%">门店服务介绍图标</th>
			    
                 	<th width="8%">是否显示</th>
			    
                 <th  width="6%">操作</th>
               </tr>
			    <#list storeintroList as storeintro>
				 <tr >
			         <td><input type="checkbox" name="storeintro.sto_id" value="${storeintro.sto_id?if_exists}"/></td>
			             <td align="center">
				       <input name="isort_no"  value="${storeintro.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" />
		                </td>
			         	<td align="center"><a onclick="linkToInfo('/admin_Storeintro_view.action','storeintro.sto_id=${storeintro.sto_id?if_exists}');">${storeintro.sto_name?if_exists}</a></td>
				        
				        <td align="center">
				        <a onclick="showpictureSrc('${(storeintro.img_path)?if_exists}');" href="###">
				        <img src="${(storeintro.img_path)?if_exists}" width="50px" height="50px" />
				        </a>
			         	</td>
				    
			         	<td align="center">
			         	<#if (storeintro.is_show)?if_exists=='0'>
			         	 <font color="green">显示</font>
			         	 <#else>
			         	 <font color="red">不显示</font>
                        </#if>
			         	</td>
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Storeintro_view.action','storeintro.sto_id=${storeintro.sto_id?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Storeintro_add.action','');" value="添加">
             <input type="button" class="rbut" onclick="updateBatchState('0','storeintro.sto_id','/admin_Storeintro_updateState.action','显示');" value="显示">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','storeintro.sto_id','/admin_Storeintro_updateNoState.action','不显示');" value="不显示">  
			 <input type="button" class="rbut"onclick="updateSort('storeintro.sto_id','isort_no','/admin_Storeintro_updateSort.action');" value="排序">
             <input type="button" class="rbut"onclick="delInfo('storeintro.sto_id','/admin_Storeintro_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

