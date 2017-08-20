<html>
  <head>
    <title>会员实名认证审核</title>

	<script type="text/javascript">
  
    </script>
  </head>
  <body>
<@s.form action="/admin_Certification_auditList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：会员管理 > 企业会员 > 实名认证审核列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>公司名称:</td>
			<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">搜索状态:</td>
			<td>
				<@s.select name="state_s" list=r"#{'':'请选择','0':'新加入','1':'认证中','2':'被驳回','3':'已通过'}" />
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
			<th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('certification.cust_id')"/></th>
		    <th width="20%" align="center" >公司名称</th>
		    <th width="20%"  align="center" >法定代表人</th>
		    <th width="27%"  align="center" >注册资本</th>
		    <th width="10%" align="center" >申请人姓名</th>
		    <th width="10%" align="center" >状态</th>
		    <th width="10%" align="center" >查看</th>
	  </tr>
	  
	   <#list certificationList as cert>
		  <tr>
		    <td><input type="checkbox" name="certification.cust_id" value="${cert.cust_id?if_exists}"/></td>
		    <td align="center">
		    		${cert.cust_name?if_exists}
		    </td>
		    <td align="center">
		    	  ${cert.corporate?if_exists}
		    </td>
		    <td align="center">
		       ${cert.reg_money?if_exists}万
		    </td>
		    <td align="center">
		       ${cert.app_name?if_exists}
		    </td>
		    <td  align="center">
		    <a onclick="linkToInfo('/admin_Certification_auditList.action','state_s=${cert.info_state?if_exists}');">
		    <#if cert.info_state?if_exists=='0'><font color="#999999">新加入</font></a></#if>
		    <#if cert.info_state?if_exists=='1'><font class="greencolor">认证中</font></a></#if>
		    <#if cert.info_state?if_exists=='2'><font class="bluecolor">被驳回</font></a></#if>
		    <#if cert.info_state?if_exists=='3'><font class="redcolor">已通过</font></a></#if>    
		    </td>
		    <td align="center"><a onclick="linkToInfo('/admin_Certification_audit.action','certification.cust_id=${cert.cust_id?if_exists}')"><img src="/include/common/images/view.gif"></a></td>
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
     <input type="button" class="rbut" onclick="delInfo('certification.cust_id','/admin_Certification_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden  name="corporate_s"/>
  <@s.hidden  name="app_name_s"/>    
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Certification_auditList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	    <tr>
			<td class="searchDiv_td" style="width:90px;">公司名称:</td>
			<td><@s.textfield name="cust_name_s" cssStyle="width:260px;"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">法定代表人:</td>      
			<td><@s.textfield name="corporate_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">申请人姓名:</td>
			<td><@s.textfield name="app_name_s"/></td>
		</tr>
		 <tr>
	         <td class="searchDiv_td">搜索状态:</td>
		     <td>
			    <@s.select name="state_s" list=r"#{'':'请选择','0':'新加入','1':'认证中','2':'被驳回','3':'已通过'}" />
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