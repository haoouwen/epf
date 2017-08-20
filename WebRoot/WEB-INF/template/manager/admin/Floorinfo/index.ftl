<html>
  <head>
    <title>楼层列表</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	   });
     
    </script>
  </head>
  <body>
<@s.form action="/admin_Floorinfo_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：网站管理 > 首页楼层 > 首页楼层列表</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >楼层标志：&nbsp;</td>
                  <td><@s.textfield name="f_tag_s" cssStyle="width:150px;"/>&nbsp;</td>
                  <td>楼层名称：&nbsp;</td>
                  <td><@s.textfield name="f_name_s" cssStyle="width:150px;"/>&nbsp;</td>
                 <td><input type="submit" class="rbut" value="查询"></td>
                 
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('floorinfo.f_id')"/></th>
                
                 	<th width="10%">楼层标志</th>
			    
                 	<th width="10%">楼层名称</th>
			    
                 	<th width="10%">所属分类</th>
			    
                 	<th width="10%">是否显示</th>
			   
                 <th  width="10%">操作</th>
               </tr>
			    <#list floorinfoList as floorinfo>
				 <tr >
			         <td>
			           <#if floorinfo.is_sys?if_exists=='0'>
			             <img class="ltip" src="/include/common/images/light.gif" alt="提示:系统内置,无法删除">
			            <#else>
			            <input type="checkbox" name="floorinfo.f_id" value="${floorinfo.f_id?if_exists}"/></td>
			            </#if>
			         	<td align="center">${floorinfo.f_tag?if_exists}</td>
				    
			         	<td align="center">${floorinfo.f_name?if_exists}</td>
				    
			         	<td align="center">${floorinfo.cat_attr?if_exists}</td>
				    
			         	<td align="center">
			         	<#if floorinfo.f_state?if_exists=='0'>
			         	  <font color="green">显示</font>
			         	<#else>
			         	  <font color="red">隐藏</font>
			         	</#if>
			         	</td>
			         	<td align="center"><a onclick="linkToInfo('/admin_Floorinfo_view.action','floorinfo.f_id=${floorinfo.f_id?if_exists}');">
					    <img src="/include/common/images/bj.gif" title="维护楼层基本信息" /></a>
					    <a onclick="linkToInfo('/admin_Floorinfo_modifyview.action','floorinfo.f_id=${floorinfo.f_id?if_exists}');">
					    <#if  floorinfo.fcount==0>
					       <img src="/include/common/images/add.png" title="新增楼层数据" />
					    <#else>
					        <img src="/include/common/images/text.gif" title="编辑楼层数据" />
					    </#if>
					    </a>
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Floorinfo_add.action','');" value="添加">
             <input type="button" class="rbut"onclick="delRelate('floorinfo.f_id','/admin_Floorinfo_delete.action')" value="删除">
             <input type="button" class="rbut" onclick="updateBatchState('0','floorinfo.f_id','/admin_Floorinfo_updateIsshow.action','显示');" value="显示">
             <input type="button" class="rbut" onclick="updateBatchState('1','floorinfo.f_id','/admin_Floorinfo_updateUnshow.action','隐藏');" value="隐藏">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
		 <@s.hidden name="cat_attr_s"/>
		 <@s.hidden name="floorinfo.f_id"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
</body>
</html>

