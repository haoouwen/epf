<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>门店列表</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <script src="/include/admin/js/sysuser.js" type="text/javascript"></script>	
	<@s.form action="/agent_Asysuser_listcode.action" method="post" id="indexForm">
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
                            <th width="70">门店号:</th>
                            <td>
                            <@s.textfield name="nike_name_s" cssClass="common-text"/>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
                <div class="result-title">
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th align="center">用户名</th>
                            <th align="center">门店号</th>
                            <th align="center">门店名称</th>
                            <th align="center">手机</th>
                            <th align="center">邮箱</th>
                            <th align="center">状态</th>
                            <th align="center">操作</th>
                        </tr>
                        <#list userList as user>
                        <tr>
                            <td align="center">
                              ${user.user_name?if_exists}
                            </td>
                            
                            <td align="center">${user.nike_name?if_exists}</td>
                            <td align="center">${user.store_name?if_exists}</td>
                            <td align="center">${user.cellphone?if_exists}</td>
                            <td align="center">${user.email?if_exists}</td>
                            <td align="center">
                               <#if (user.state)?if_exists=='0'>
                               <font color="green">启用</font>
                               <#else>
                               <font color="red">禁用</font>
                               </#if>
                            </td>
                            <td align="center">
                            	<a onclick=linkToInfo("/agent_Asysuser_codeview.action","asysuser.user_id=${user.user_id?if_exists}");><img src="/include/common/images/audit.png" /></a>
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
   
</@s.form>
</body>
</html>
