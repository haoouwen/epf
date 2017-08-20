<html>
<head>
<title>重置密保</title>
<script type="text/javascript" src="/include/common/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="/include/bmall/js/memberuserque.js"></script>

</head>
<body >
	
<@s.form action="/bmall_Memberuser_updateque.action"  method="post"  validate="true" onsubmit="return checkinput();">
<div class="rightside f_right">
<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">重置密保</a>
    </div>
     <div class="rpostion">
     	<h2>重置密保</h2>
     </div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table width="550px">
         
           <tr id="xitongwenti"><td class="firsttd">提示问题<font color="red">*</font></td>
            <td>
           		<@s.select name="passwd_ques"id="passwd_ques" list="commparaList" listValue="para_key" listKey="para_key" headerKey="" headerValue="-------请选择-------"cssStyle="height:23px;"/>
        		<@s.fielderror><@s.param>passwd_ques</@s.param></@s.fielderror>
        		<input type="button" onclick="showque('0');"  value="使用自定义问题">
            </td>

             </tr>
             <tr style="display:none;" id="zidingyiwenti"><td  class="firsttd">自定义问题<font color="red">*</font></td>
             <td >
        		<@s.textfield name="passwd_selfques"id="passwd_selfques"  cssClass="winput" maxLength="20" cssStyle="width:169px;"/>
        		<@s.fielderror><@s.param>passwd_answer</@s.param></@s.fielderror>
        		<input type="button" onclick="showque('1');"   value="使用系统问题">
            </td>
           </tr>
            
            
          	<tr><td  class="firsttd">密保答案<font color="red">*</font></td>
             <td>
        		<@s.textfield name="apasswd_answer_s" id="apasswd_answer_s"  cssClass="winput" maxLength="20" cssStyle="width:169px;"value=""/>
        		<@s.fielderror><@s.param>apasswd_answer_s</@s.param></@s.fielderror>
            </td></tr>
            <tr>
            	<td  class="firsttd">提示：</td>
            	<td>设置密保问题，防止会员账号被盗密码被修改！</td>
            </tr>
            <@s.hidden name="memprotect_id" value="${memprotect.id}"/>  
            <tr>
             	<td colspan="2" style="padding-left:210px;">
         	        <@s.token/>   
             		<@s.submit value="提  交" cssClass="submitbut" />
             		
             	</td>
            </tr> 
            
       </table>
       </div>  
     </div> 
</div>
<@s.hidden id="flag" name="flag"/>
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

