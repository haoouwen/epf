<html>
  <head>
    <title>添加友情链接</title>
     <#include "/include/uploadInc.html">	 
     <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
     <script type="text/javascript">
	  $(document).ready(function(){ 
		 //所属地区的回选
         loadArea("${areaIdStr?if_exists}","");
	  });
	</script>
  </head>
  <body>
  <@s.form action="/admin_Link_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置--> 当前位置:网站管理 > 友情链接 > 添加友情链接
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		   <tr>
             <td class="table_name">友情链接名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="link.link_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>link.link_name</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">友情链接分组<font color='red'>*</font></td>
             <td> 
             <@s.select name="link.link_group" list="link_groupList" listValue="name" listKey="id" headerKey="0" headerValue="请选择" />
             <a href="<#if mall_type?if_exists=='b2b'>/admin_Linkgroup_list.action?ajaxRequestRandom=1'<#elseif (mall_type?if_exists)=='b2c'>/admin_Linkgroup_malllist.action?ajaxRequestRandom=1</#if>" 
             target="_blank">[分组管理]</a>
             <@s.fielderror><@s.param>link.link_group</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <td class="table_name">所属地区:</td>
             <td >
             <table>
             <tr>
                  <td>
             	         <div id="areaDiv" style="margin-left:-4px;margin-right:-8px;"> </div>
             	 </td>
             	 <td>
             	        <img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Link.area_attr')}"/>"  style="margin-left:-4px;">       	
             	 </td>
             	 </tr> 
             </table>	 
             </td>
             
             </tr>
           <tr>
             <td width="19%" class="table_name">链接地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="link.url" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>link.url</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">链接图片:</td>
             <td>
                 <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="link.img_path" id="uploadresult" maxLength="100" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>link.img_path</@s.param></@s.fielderror>
             			   【建议图片宽100px，高50px】
             			</td>
             		</tr>
             	</table>
             </td>
           </tr>
            <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="link.sort_no" value="0" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>link.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="link.is_display" list=r"#{'0':'有效','1':'无效'}" value="0"/>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.is_display')}"/>"> 
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    <@s.token/>
	       <@s.hidden name="link_name"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表"
	        onclick="linkToInfo(<#if mall_type?if_exists=='b2b'>'/admin_Link_list.action'<#elseif (mall_type?if_exists)=='b2c'>'/admin_Link_malllist.action'</#if>,'');"/>
	     <@s.hidden id="search_area_attr" name="area_attr_s"/>
		 <!--搜索区域结束-->
		 <!--所属地区插件隐藏字段开始区域-->
		 <@s.hidden id="hidden_area_value" name="hidden_area_value" value="${hidden_area_value?if_exists}"/>
		 <!--所属地区插件隐藏字段结束区域-->
		 <@s.hidden name="mall_type" />
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>