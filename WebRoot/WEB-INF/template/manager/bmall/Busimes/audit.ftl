<html>
<head>
<title>查看留言</title>
</head>
<body>

<@s.form action="/bmall_Busimes_update.action" method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
<div class="postion">
 		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的留言</a><span>></span><a href="#">查看留言</a>
	</div>
     <div class="rpostion"><h2>我的交易</h2></div>
     <div class="base_infor">
       <h2 class="base_title">查看留言</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >
          
            <tr><td  class="firsttd">商店名称：</td><td>
        	<@s.label name="cust_name" value="${member.cust_name?if_exists}" cssClass="txtinput" />
            </td></tr>  
            
            <tr><td  class="firsttd">留言内容：</td><td>
        	<@s.label name="busimes.msg_content" cssClass="txtinput" />
            </td></tr> 
             
            <tr><td  class="firsttd">留言时间：</td><td>
        	<@s.label name="busimes.msg_date.substring(0,19)" cssClass="txtinput" />
            </td></tr>  

            <tr><td  class="firsttd">回复内容：</td><td>
        	<@s.label name="busimes.re_content" cssClass="txtinput" />
            </td></tr>  
            <tr><td  class="firsttd">回复时间：</td><td>
        	<@s.label name="busimes.re_date.substring(0,19)" cssClass="txtinput" />
            </td></tr>  
            
            <tr>
             <td colspan="2" align="center">
             	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Busimes_auditList.action','');"/>
             	<@s.hidden name="busimes.trade_id"/>
             </td>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

