<html>
  <head>
    <title>禁用IP管理</title>
   <script type="text/javascript" src="/include/admin/js/banip.js"></script>
   <link rel="StyleSheet" type="text/css" href="/include/admin/css/banip.css"/>
  </head>
 <body>
<@s.form action="/admin_BanIp_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统管理 > 禁止IP管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>IP地址:</td>
			<td><@s.textfield name="ip_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	    <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('banip.ip_id')"/></th>
	    <th align="left" >&nbsp;&nbsp;IP段地址</th>
	    <!--
	    <th width="15%"  align="center" >操作人</th>
	    <th width="15%"  align="center" >操作时间</th>
	    -->
	  </tr>
	  <#list banipList as ban_ips>
	    <tr>
	    <td><input type="checkbox" name="banip.ip_id" value="${ban_ips.ip_id?if_exists}"/></td>
	    <td align="left">&nbsp;&nbsp;
	     <@s.textfield name="banip.ip" id="${ban_ips.ip_id?if_exists}"  onblur="checkupdateIp(this);" onfocus="tipsget(this);"
	     value="${ban_ips.ip?if_exists}" cssStyle="width:300px;"  cssClass="txtinput" maxLength="60"/>
	     <img id="imes_${ban_ips.ip_id?if_exists}" src="/include/admin/images/errors.png" style="display: none;" />
	     <br/>
	     <span id="tip_${ban_ips.ip_id?if_exists}" style="display: none;color:blue;">*(IP段的格式为：0.0.0.0-255.255.255.255)</span>
	     <@s.fielderror><@s.param>banip.ip</@s.param></@s.fielderror></td>
	     <!--
	    <td align="center">${ban_ips.user_name?if_exists}</td>
	    <td align="center">${ban_ips.in_date?if_exists}</td>
	     -->
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
     <input type="button" class="rbut" onclick="addipShowDIV('addDiv','300px','200px');" value="添加">
     <input type="button" class="rbut" onclick="delInfo('banip.ip_id','/admin_BanIp_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updatebanip('/admin_BanIp_updateAllIP.action');" value="批量更新">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden name="admin_ip" id="adminip"/>
  <@s.hidden name="admin_ip_id" id="admin_ipid"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--添加区域开始-->
<div class="searchDiv" style="display:none;" id="addDiv">
  <@s.form id="addForm" action="/admin_BanIp_insert.action" method="post"  validate="true" onsubmit="return checkip('ipname','ipnameend');" >
	<table>
	  <tr>
             <td align="left" style="width:50px;">IP地址:</td>
             <td>
             	<@s.textfield name="ipname" id="ipname" />-<@s.textfield name="ipnameend" id="ipnameend"/>
             </td>
      </tr>
      <tr>
		<td ></td>
		<td >IP段的格式为：0.0.0.0-255.255.255.255</td>
	</tr>
      <tr >
           <@s.token/>
	       <td colspan="2" align="center" ><input type="button" value="保存" onclick="subCheck('ipname','ipnameend');" />&nbsp;<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('addDiv')"/></td>
	  </tr>
	</table>
	</@s.form>
</div>
<!--添加区域结束-->
  </body>
</html>
