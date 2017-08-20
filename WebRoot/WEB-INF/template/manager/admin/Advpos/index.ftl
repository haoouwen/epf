<html>
  <head>
    <title>广告位管理</title>
     <script type="text/javascript" src="/include/admin/js/advpos.js"></script>
	 <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript" src="/include/components/artDialog4.0.3/artDialog.js?skin=default"></script>
    <script type="text/javascript" src="/include/components/artDialog4.0.3/jquery.artDialog.js?skin=default"></script>
    <script type="text/javascript">
     function updateAdvposBatch(actionName)
      {
           var admin_pos_posid='';
	       var chks =document.getElementsByTagName('input');//得到所有input
           for(var i=0;i <chks.length;i++)
          { 
            
           if(chks[i].type.toLowerCase() == 'checkbox'&&chks[i].value!='on')
           {
                //得到所名为checkbox的input
                admin_pos_posid+=chks[i].value+',';
               
             }
           }
            var len=admin_pos_posid.length;
            var pos_id=admin_pos_posid.substring(0,len-1);
            document.getElementById('batch_update_hidden_posid').value = pos_id;//用于隐藏所有的ID
            document.getElementById('indexForm').action=actionName;
		    document.getElementById('indexForm').submit();
      }   
    </script>
  </head>
  <body>
<@s.form action="/admin_Advpos_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 广告管理 >广告位 > 广告位列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>广告位名称:</td>
			<td><@s.textfield name="pos_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">前台是否显示:</td>
			<td>
				<@s.select name="isshow_s" list=r"#{'':'请选择','0':'显示','1':'不显示'}"/>
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
		    <th wihth="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('advpos.pos_id')"/></th>
		    <th wihth="8%" align="center" >排序</th>
		    <th wihth="11%" align="center" >广告位ID号</th>
		    <th wihth="10%"  align="center" >广告类型</th>
		    <th wihth="20%" align="center" >广告位名称</th>
		    <th wihth="8%"  align="center" >规格(宽*高px)</th>
		    <!--
		    <th wihth="8%"  align="center" >所属模块</th>
		    <th wihth="8%"  align="center" >所属地区</th>
		    -->
		    <th wihth="8%"  align="center" >价格</th>
		     <th wihth="8%"  align="center" >示意图</th>
		    <th wihth="8%" align="center" >前台是否显示</th>
		    <th wihth="8%" align="center" >JS调用代码</th>
		    <th wihth="8%" align="center" >操作</th>
	     </tr>
  <#list advposList as pos>
  <tr>
    <td><input type="checkbox" name="advpos.pos_id" value="${pos.pos_id?if_exists}"/></td>
    <td align="center"><@s.textfield name="advpos.sort_no" value="${pos.sort_no?if_exists}" cssStyle="width:50px;" onkeyup="checkNum(this)"  /></td>
    <td align="center">${pos.pos_id?if_exists}</td>
    <td align="center">${pos.para_key?if_exists}</td>
    <td align="left"><a href="/admin_Advinfo_list.action?posid_s=${pos.pos_id?if_exists}">${pos.pos_name?if_exists}</a></td>
    <td align="center">${pos.p_width?if_exists}*${pos.p_height?if_exists}</td>
    <!--
    <td align="center"><#if pos.model_value?if_exists==''>无模块类型<#else>${pos.model_value?if_exists}</#if></td>
    <td align="center"><#if pos.area_attr?if_exists==''>无地区分类<#else>${pos.area_attr?if_exists}</#if></td>
    -->
    <td align="center"><#if pos.price?if_exists==0>面议<#else>${pos.price?if_exists}</#if></td>
     <td align="center"><#if pos.img_path?if_exists==''><font color='red'>暂无</font><#else><a href="${pos.img_path?if_exists}" target="_blank"><font color='green'>查看</font></a></#if> </td>
    <td align="center">
    
    <a onclick="linkToInfo('/admin_Advpos_list.action','isshow_s=${pos.isshow?if_exists}');">
    
    <#if pos.isshow?if_exists=='0'>
    
    <font color='green'>显示</font>
    
    <#else>
      <font color='red'>不显示</font>
      
      </#if> 
      
    </a>  
    </td>
    <td align="center">
       <#if (pos.pos_type)?if_exists!='f' && (pos.pos_type)?if_exists!='g'><a onclick="linkToInfo('/admin_Advpos_viewJS.action','pos_id_s=${(pos.pos_id)?if_exists}');" >查看</a></#if>
    </td>
    <td align="center">
    	<a onclick="linkToInfo('/admin_Advpos_view.action','advpos.pos_id=${pos.pos_id?if_exists}&adv_pos_s=${adv_pos_s?if_exists}');" title="修改广告位"><img src="/include/common/images/bj.gif" /></a>
    	<a onclick="linkToInfo('/admin_Advinfo_list.action','posid=${pos.pos_id?if_exists}&two_pages_link=no');" title="查看此广告位下的广告列表"><img src="/include/common/images/view.gif" /></a>
    	<#if pos.advposNum gte 1>
    		<#if pos.pos_type=='e'>
   			<a onclick="linkToInfo('/admin_Advinfo_add.action','advpos.pos_id=${pos.pos_id?if_exists}&type=${pos.pos_type?if_exists}&tablename=${pos.module_type?if_exists}&posid=${pos.pos_id?if_exists}');" title="在此广告位添加广告"><img src="/include/common/images/add.gif" /></a>
			<#else>    		
    		<a onclick="addInfo();"><img src="/include/common/images/add.gif" /></a>
    		</#if>
    	<#else>
   		<a onclick="linkToInfo('/admin_Advinfo_add.action','advpos.pos_id=${pos.pos_id?if_exists}&type=${pos.pos_type?if_exists}&tablename=${pos.module_type?if_exists}&posid=${pos.pos_id?if_exists}');" title="在此广告位添加广告"><img src="/include/common/images/add.gif" /></a>
   		</#if>
    	<!--
    	<#if (pos.sys_adv)?if_exists == '0'>
    		<a href="javascript:delOneInfo('advpos.pos_id','/admin_Advpos_delete.action','${pos.pos_id?if_exists}');"><img src="/include/admin/images/delete.png" border="0"/></a>
    	</#if>
    	-->
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
     <!--<input type="button" class="rbut" onclick="linkToInfo('/admin_Advpos_add.action','');" value="添加">-->
     <input type="button" class="rbut"onclick="updateAdvposBatch('/admin_Advpos_updateAdvposBatch.action');" value="排序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="batch_update_hidden_posid" id="batch_update_hidden_posid"/>
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden  name="area_attr_s"/>
  <@s.hidden  name="module_type_s"/>
  <@s.hidden  name="pos_type_s"/>
  <@s.hidden name="p_width"/>
  <@s.hidden name="p_height"/>
  <@s.hidden  name="pos_price_s"/>  
  <@s.hidden name="adv_pos_s" value="${adv_pos_s}"/>
  <@s.hidden name="advpos.pos_id" id="advpos.pos_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Advpos_list.action" method="post"  id="form_search_id">
	<table width="100%" border="0" cellspacing="0">
    <tr>
		<td class="searchDiv_td">广告位名称:</td>
		<td><@s.textfield name="pos_name_s" maxLength="20"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">广告类型:</td>
		<td><@s.select name="pos_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择" /></td>
	</tr>
	<tr>
         <td class="searchDiv_td">所属模块</td>
         <td>
         <@s.select name="module_type_s" list="commpara_modelList" listValue="module_name" listKey="module_type" headerKey="" headerValue="请选择"/>
         </td>
    </tr>
	<tr>
		<td class="searchDiv_td">规格(宽):</td>
		<td><@s.textfield name="p_width" maxLength="100"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">规格(高):</td>
		<td><@s.textfield name="p_height" maxLength="100"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">价格:</td>
		<td><@s.textfield name="pos_price_s" maxLength="100"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">前台是否显示:</td>
		<td><@s.select name="isshow_s" list=r"#{'':'请选择','0':'显示','1':'不显示'}"/> </td>
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
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
			<@s.hidden name="adv_pos_s" value="${adv_pos_s}"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>