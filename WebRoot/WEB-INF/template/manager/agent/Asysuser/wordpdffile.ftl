<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>资料下载</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<@s.form action="/agent_Asysuser_updatepwd.action" method="post" validate="true" id="modiy_form">
 <div class="crumb-wrap">
      <div class="crumb-wrap">
        </div>
        <div class="result-wrap">
            <div class="result-content">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <td style="margin-left:30px">
                            ${(pagetip.page_content)?if_exists}
                            </td>
                        </tr>
                    </tbody></table>
            </div>
        </div>
   </div>
   </@s.form>
</body>
</html>


