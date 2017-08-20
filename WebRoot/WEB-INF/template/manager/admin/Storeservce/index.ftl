<html>
  <head>
    <title>门店服务列表</title>
  </head>
  <body>
<@s.form action="/admin_Storeservce_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：会员管理 > 区域代理 > 门店服务</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >服务名称:</td><td><@s.textfield name="title_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('storeservce.store_id')"/></th>
                    <th width="25%">服务名称</th>
                    
                     <th width="10%">背景颜色</th>
			    
                 	<th width="20%">服务图标</th>
			    
                 	<th width="8%">状态</th>
			    
                 <th  width="5%">操作</th>
               </tr>
			    <#list storeservceList as storeservce>
				 <tr >
			         <td><input type="checkbox" name="storeservce.store_id" value="${(storeservce.store_id)?if_exists}"/></td>
			         
			         	<td align="center" title="${(storeservce.store_name)?if_exists}"> 
			         	<a onclick="linkToInfo('/admin_Storeservce_view.action','storeservce.store_id=${(storeservce.store_id)?if_exists}');">
			         	${(storeservce.store_name)?if_exists}
			         	</a>
			         	</td>
			         	<td >
			         	     <span style='background-color:${(storeservce.store_color)?if_exists}'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			         	</td>
			         	<td >
			         	     <img src="${(storeservce.store_img)?if_exists}" width="30px" height="30px"/>
			         	</td>
			         	<td align="center">
                          <#if storeservce.state?if_exists=='0'>
			         	  <font color="green">启用</font>
                          <#else>
                          <font color="red">禁用</font>
                          </#if>
                          </td>
				    <td align="center"><a onclick="linkToInfo('/admin_Storeservce_view.action','storeservce.store_id=${(storeservce.store_id)?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Storeservce_add.action','');" value="添加">
             <input type="button" class="rbut" onclick="updateBatchState('0','storeservce.store_id','/admin_Storeservce_updateState.action','启用');" value="启用">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','storeservce.store_id','/admin_Storeservce_updateNoState.action','禁用');" value="禁用">  
             <input type="button" class="rbut"onclick="delInfo('storeservce.store_id','/admin_Storeservce_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
 </body>
</html>

