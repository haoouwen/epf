<html>
  <head>
    <title>初始化数据库</title>
    <script type="text/javascript" src="/include/admin/js/backup.js"></script> 
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" />
  </head>
<body>
<!--当前位置-->
	<div class="postion">	
当前位置:系统管理 > 系统设置 > 初始化数据库</div>
<!--当前位置结束-->
<div class="rtdcont">
<div style="width:1100px; height:600px;line-height:600px;text-align:center;display:table-cell; vertical-align:middle;">
		<input type="button" value="初始化数据库"  onclick="initDb();" style="width:350px; height: 175px;font-size:45px;">
		<@s.fielderror><@s.param>passmessage</@s.param></@s.fielderror>
</div>
<div style="display:none;"  id="dbinit" >
      <@s.form  action="/admin_BackUp_dbInit.action"  method="post"  >
	        <table  >
	           <tr>
	           	<td  class="table_name">提示：请输入超级管理员：<font color="red">${admin_name?if_exists} </font>的密码</td>
	          </tr>
	          <tr><td>
	         	<@s.password  name="initPassword"  maxLength="16"cssStyle="width:260px;height:20px;"/>
	          </td></tr>  
	          
	          <tr>
	           <td  colspan="2"align="center">
	           	<@s.submit value="提 交" cssClass="submitbut" />
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
	<div>	
  </body>
</html>





