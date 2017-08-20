<html>
  <head>
    <title>楼层标签</title>
  </head>
  <body>
<@s.form action="/admin_Floormark_list.action" method="post" id="indexForm">
        <div class="postion">当前位置：网站管理 > 首页楼层  > 楼层标签</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td>楼层标签名称:&nbsp;</td>
                  <td><@s.textfield name="fm_name" cssStyle="width:150px;"/>&nbsp;</td>
                   <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('floormark.fm_id')"/></th>
                 
                     <th width="5%">排序</th>
                     
                 	<th width="20%">标签名称</th>
                 	
                 	<th width="10%">标签备注</th>
			    
                 	<th width="10%">标签类型</th>
                 	
                 	<th width="18%">所属楼层</th>
			    
                 <th  width="5%">操作</th>
               </tr>
			    <#list floormarkList as floormark>
				 <tr >
			         <td>
			          <#if (floormark.f_name)?if_exists>
			             <img class="ltip" src="/include/common/images/light.gif" alt="系统提示:该标签已经被应用到楼层中,无法删除!如果要删除,请到对应的楼层移除该标签" />
			            <#else>
			              <input type="checkbox" name="floormark.fm_id" value="${floormark.fm_id?if_exists}"/></td>
			            </#if>
			            
			          </td>
			         
			         	<td align="center"><input name="isort_no" value="${floormark.fm_sort?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/></td>
			        
			         	<td align="center">${floormark.fm_name?if_exists}</td>
			         	
			         		<td align="center">${floormark.fm_remark?if_exists}</td>
				    
			         	<td align="center">
			         	<#if floormark.fm_type?if_exists==0>
			         	 <font color="red">商品</font>
			         	 <#else>
			         	  <font color="green">图片</font>
			         	  </#if>
			         	</td>
			         	
			         	<td align="center">
			         	<#if floormark.f_name?if_exists==''|| floormark.f_name?if_exists==null>
			         	  — —
			         	<#else>
			         	${floormark.f_name?if_exists}</td>
				        </#if>
				    <td align="center"><a onclick="linkToInfo('/admin_Floormark_view.action','floormark.fm_id=${floormark.fm_id?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Floormark_add.action','');" value="添加">
             <input type="button" class="rbut"onclick="delInfo('floormark.fm_id','/admin_Floormark_delete.action')" value="删除">
             <input type="button" class="rbut" onclick="updateSort('floormark.fm_id','isort_no','/admin_Floormark_updateSort.action');" value="排序">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		<@s.hidden name="floormark.fm_id"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

