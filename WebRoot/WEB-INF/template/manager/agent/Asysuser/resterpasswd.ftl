<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>久通区域代理/密码重置</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<@s.form action="/agent_Asysuser_sendPwd.action" method="post" validate="true" id="modiy_form">
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/agentindex.action">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">密码重置</a><span class="crumb-step">&gt;</span><span>新增门店</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120"><i class="require-red">*</i>用户名：</th>
                            <td>
                              ${asysuser.user_name}   
                            </td>
                        </tr>
                            <tr>
                                <th>邮箱：</th>
                                <td>
                               	${asysuser.email}
                                </td>
                            </tr>
                          
                             <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
      						 <@s.token/>
						   <@s.hidden name="asysuser.user_id"/>
					        
					          ${listSearchHiddenField?if_exists}
       
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="重置密码" type="submit" onclick="resterPwd('/admin_Asysuser_sendPwd.action');">
                                    <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_list.action','');" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
            </div>
        </div>

   </div>
   <@s.hidden name="pwd_state"/>
   </@s.form>
</body>
</html>
