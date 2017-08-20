<html>
<head>
<title>回复留言</title>
</head>
<body>
	
<@s.form action="/bmall_Busimes_update.action" method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
<div class="postion">
 		<a href="#">我是卖家</a><span>></span><a href="#">客服服务</a><span>></span><a href="#">买家留言</a><span>></span><a href="#">回复留言</a>
	</div>
     <div class="rpostion"><h2>客服服务</h2></div>
     <div class="base_infor">
       <h2 class="base_title">回复留言</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >
          
            <tr><td  class="firsttd">客户名称：</td><td>
        	<@s.label name="cust_name" value="${memberuser.user_name?if_exists}" cssClass="txtinput" />
            </td></tr>  
            
            <tr><td  class="firsttd">留言内容：</td><td>
        	<@s.label name="busimes.msg_content" cssClass="txtinput" />
            </td></tr>  
            
            <tr><td  class="firsttd">留言时间：</td><td>
        	<@s.label name="busimes.msg_date.substring(0,19)" cssClass="txtinput" />
            </td></tr>  

            <tr><td class="firsttd">回复内容<font color='red'>*</font></td><td>
        	<@s.textarea cssStlye="width:500px;height:100px;" name="busimes.re_content" cssClass="txtinput" />
            </td></tr>  
            
             <tr><td  class="firsttd">状态：</td><td>
        	<@s.radio name="busimes.is_enable" list=r"#{'0':'有效','1':'无效'}"/>
            </td></tr> 
            
            <tr>
             	<td colspan="2" align="center">
             		<@s.submit value="提  交" cssClass="submitbut"/>
					<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Busimes_list.action','');"/>
				</td>
             <@s.hidden name="busimes.trade_id"/>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

