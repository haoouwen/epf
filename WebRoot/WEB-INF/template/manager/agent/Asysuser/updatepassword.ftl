<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>修改密码</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />


</head>
<body>
<@s.form action="/agent_Asysuser_updatepwd.action" method="post" validate="true" id="modiy_form">
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>基本信息<span class="crumb-step">&gt;</span><span>修改密码</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120">用户名：</th>
                            <td>
                             ${asysuser.user_name}
                            </td>
                        </tr>
                            <tr>
                                <th>旧密码<i class="require-red">*</i></th>
                                <td>
                                	<@s.password name="oldpasswd" cssClass="common-text" maxLength="100" />
       							  	<@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror>
                                </td>
                            </tr>
                            <tr>
                                <th>新密码<i class="require-red">*</i></th>
                                <td>
                                <@s.password name="newpasswd" cssClass="common-text" maxLength="100" />
             					<@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror>
                                </td>
                            </tr>
                             <tr>
                                <th>确认密码<i class="require-red">*</i></th>
                                <td>
                                <@s.password name="confirmpasswd" cssClass="common-text" maxLength="100" />
             					<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
                                </td>
                            </tr>
                           
                            <@s.token/>	
				            <@s.hidden name="asysuser.user_id"/>
					          ${listSearchHiddenField?if_exists}
       
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                </td>
                            </tr>
                        </tbody></table>
            </div>
        </div>

   </div>
   </@s.form>
</body>
</html>
