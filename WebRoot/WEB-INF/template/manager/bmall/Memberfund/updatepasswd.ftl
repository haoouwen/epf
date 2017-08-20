<html>
<head>

<title>修改支付密码</title>

 <link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
 
</head>
<body>
	
<@s.form action="/bmall_Memberfund_updatepasswd.action"  method="post"  name="formshopcongif" validate="true">

<div class="wR810">

	<div class="rwTitle">
		<h2><span>修改支付密码</span></h2>
		
		<div class="verifyDiv">
		  <!--步骤-->
          <h3><span class="morange">1.修改支付密码</span><span>2.修改支付密码完成</span></h3>
          
          <div class="verifyCont">
          
             <table width="100%" cellpadding="0" cellspacing="0">
                 <tr>
                    <th><span>*</span>输入旧密码：</th>
                    <td>
                       <input type="password" name="oldpasswd" class="pswText">
	        		   <span> <@s.fielderror><@s.param>oldpasswd</@s.param></@s.fielderror></span>  
                    </td>
                 </tr>
                 <tr>
                    <th><span>*</span>输入新密码：</th>
                    <td>
                       <input type="password" class="pswText" name="newpasswd">
	        		   <span> <@s.fielderror><@s.param>newpasswd</@s.param></@s.fielderror></span>   
                    </td>
                 </tr>
                 <tr>
                    <th><span>*</span>再次输入密码：</th>
                    <td>
                       <input type="password" class="pswText" name="confirmpasswd">
                       <span> <@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror></span>
                    </td>
                 </tr>
                 <tr>
                    <th></th><td><input type="submit" class="graybut" value="提交"></td>
                 </tr>
             </table>
             
          </div>
		
		</div>
	</div>
 
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

