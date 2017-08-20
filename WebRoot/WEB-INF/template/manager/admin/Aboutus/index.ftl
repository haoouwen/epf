<html>
  <head>
    <title>关于我们管理</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Aboutus_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 基本功能 > 关于我们</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>标题:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	       <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
	    <tr>
    <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('aboutus.info_id')"/></th>
    <th width="30%" align="center" >关于我们标题</th>
    <th width="30%" align="center" >栏目标识</th>
    <th width="20%" align="center" >发布时间</th>
    <th width="17%" align="center">操作</th>
  </tr>
	  <#list aboutusList as aboutus>
  <tr>
    <td><input type="checkbox" name="aboutus.info_id" value="${aboutus.info_id?if_exists}"/></td>
    <td align="center">${aboutus.title?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Aboutus_list.action','aboutus.ch_id=${aboutus.ch_id?if_exists}');">
    ${aboutus.model_value?if_exists}</a></td>
    <td align="center">${aboutus.in_date?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Aboutus_view.action','aboutus.info_id=${aboutus.info_id?if_exists}');">
    <img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Aboutus_add.action','');" value="添加">
     <input type="button" class="rbut"onclick="delInfo('aboutus.info_id','/admin_Aboutus_delete.action.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="ch_id_s"/>
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Aboutus_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
			<tr>
		<td class="searchDiv_td">关于我们标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	<tr>
             <td class="searchDiv_td">栏目标识:</td>
             <td>
             <@s.select name="ch_id_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
             </td>
           </tr>
	<tr>
	 <td class="searchDiv_td">时间段:</td>
        <td>  	
        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
                    readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
        至
        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
        </td>
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
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
  
</html>