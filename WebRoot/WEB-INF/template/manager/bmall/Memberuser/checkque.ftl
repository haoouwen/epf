<html>
<head>
<title>验证密保</title>

</head>
<body >
	
<@s.form action="/bmall_Memberuser_checkque.action"  method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">验证密保</a>
    </div>
     <div class="rpostion">
     	<h2>验证密保</h2>
     </div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table width="550px">
         
           <tr ><td class="firsttd">您的密保问题：</td>
            <td>
            ${memprotect.question?if_exists}
            </td>
            
          	<tr><td  class="firsttd">请输入答案<font color="red">*</font></td>
             <td>
        		<@s.textfield name="apasswd_answer_s"  cssClass="winput" maxLength="20" cssStyle="width:169px;"/>
        		<@s.fielderror><@s.param>apasswd_answer_s</@s.param></@s.fielderror>
            </td></tr>
            
            <tr>
             	<td colspan="2" style="padding-left:210px;">
         	        <@s.token/>   
             		<@s.submit value="提  交" cssClass="submitbut"/>
             		
             	</td>
            </tr> 
            
       </table>
       </div>  
     </div> 
</div>
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

