<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>门店列表</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
  
	<@s.form action="/agent_Asysuser_list.action" method="post" id="indexForm">
<@s.hidden name="asysuser.state" id="admin_user_state"/>
 <div class="crumb-wrap">
          <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
			门店管理<span class="crumb-step">&gt;</span>门店列表</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="70">用户名:</th>
                            <td>
                            <@s.textfield name="user_name_s" cssClass="common-text"/>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                            <td><input type="button" class="btn btn-primary btn2" onclick="searchShowDIV('searchDiv','300px','220px');"  value="高级查询"></td>
                             <td><input type="button" class="btn btn-primary btn2" onclick="store_qrcode_print('1');"  value="批量打印二维码"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                           <th width="3%"> <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('user.user_id')"/></th>
                            <th align="center">用户名</th>
                            <th align="center">门店号</th>
                            <th align="center">门店名称</th>
                            <th align="center">手机</th>
                            <th align="center">邮箱</th>
                            <th align="center">操作</th>
                        </tr>
                        <#list userList as user>
                        <tr>
                            <td align="center"><input type="checkbox" name="user.user_id" value="${user.user_id?if_exists}"/></td>
                            <td align="center">
                              ${user.user_name?if_exists}
                            </td>
                            
                            <td align="center">${user.nike_name?if_exists}</td>
                            <td align="center">${user.store_name?if_exists}</td>
                            <td align="center">${user.cellphone?if_exists}</td>
                            <td align="center">${user.email?if_exists}</td>
                            <td align="center">
                            	<a onclick=linkToInfo("/agent_Asysuser_viewStore.action","asysuser.user_id=${user.user_id?if_exists}");><img src="/include/common/images/bj.gif"/></a>
                            </td>
                        </tr>
                        </#list>
                    </table>
                  <div class="pages">
				     ${pageBar?if_exists}
				   </div>
                </div>
        </div>
   </div>
   <@s.hidden  name="nike_name_s" />
   <@s.hidden  name="store_name_s" />
   <@s.hidden  name="cellphone_s" />
   <@s.hidden  name="email_s" />
</@s.form>
<#include "/WEB-INF/template/manager/admin/Goodsorder/commonprint.ftl">
<script type="text/javascript" src="/include/agent/js/batch_qcode.js"></script>
<script src="/include/admin/js/orderprint.js" type="text/javascript"></script>
<script src="/include/admin/js/sysuser.js" type="text/javascript"></script>	
<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/agent_Asysuser_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">门店号：</td>
			<td><@s.textfield name="nike_name_s"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">门店名称：</td>
			<td><@s.textfield name="store_name_s"/></td>
		</tr>
		<tr>
            <td class="searchDiv_td">手机：</td>
            <td><@s.textfield name="cellphone_s"/></td>
        </tr>
	   	<tr>
	         <td class="searchDiv_td">邮箱：</td>
	        <td><@s.textfield name="email_s"/></td>
	    </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>
<!--搜索区域结束-->
</body>
</html>
