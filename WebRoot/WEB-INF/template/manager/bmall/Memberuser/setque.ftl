<html>
<head>
<title>设置密保</title>
<link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
<script type="text/javascript" src="/include/bmember/js/memberuserque.js"></script>

</head>
<body >
	
<@s.form action="/bmall_Memberuser_setque.action"  method="post" name="formshopcongif" validate="true" onsubmit="return checkinput();">
<div class="wR810">
    <div class="rwTitle">
     	<h2><span>设置密保</span></h2>
      <div class="accontDiv">
          <table width="100%" cellpadding="0" cellspacing="0" class="ainfoTab">
         
           <tr id="xitongwenti">
           <th>提示问题<font color="red">*</font></th>
            <td>
          		<@s.select name="passwd_ques"id="passwd_ques" list="commparaList" listValue="para_key" listKey="para_key" headerKey="" headerValue="-------请选择-------"cssStyle="height:23px;"/>
        		<@s.fielderror><@s.param>passwd_ques</@s.param></@s.fielderror>
        		<input type="button" onclick="showque('0');"  value="使用自定义问题">
            </td>
             </tr>
             <tr style="display:none;" id="zidingyiwenti">
             <th>自定义问题<font color="red">*</font></th>
             <td >
        		<@s.textfield name="passwd_selfques"id="passwd_selfques"  cssClass="winput" maxLength="20" cssStyle="width:169px;"/>
        		<@s.fielderror><@s.param>passwd_answer</@s.param></@s.fielderror>
        		<input type="button" onclick="showque('1');"   value="使用系统问题">
            </td>
            </tr>
            
            
          	<tr><th>密保答案<font color="red">*</font></th>
             <td>
        		<@s.textfield name="apasswd_answer_s" id="apasswd_answer_s" cssClass="winput" maxLength="20" cssStyle="width:169px;"/>
        		<@s.fielderror><@s.param>apasswd_answer_s</@s.param></@s.fielderror>
            </td></tr>
            <tr>
            	<td  class="firsttd">提示：</td>
            	<td>设置密保问题，防止会员账号被盗密码被修改！</td>
            </tr>
            <tr>
             	<td colspan="2" style="padding-left:210px;">
         	        <@s.token/>   
             		<@s.submit value="提  交" cssClass="submitbut"/>
             		
             	</td>
            </tr> 
            
       </table>
       </div>  
</div>
<@s.hidden id="flag" name="flag"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

