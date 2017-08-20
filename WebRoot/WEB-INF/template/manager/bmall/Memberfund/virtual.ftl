<html>
<head>
<title>查看余额</title>
</head>
<body>
	
<@s.form action="/bmall_Memberfund_update.action" method="post" validate="true">
<div class="rightside f_right">
	<div class="postion">
  		 <a href="#">账号管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">查看余额</a><span>
    </div>
     <div class="rpostion"><h2>查看余额</h2></div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table style="width:750px;" >
          
            <tr><td  class="firsttd">总余额：</td><td>
        	<@s.label name="memberfund.fund_num" cssClass="txtinput" maxLength="9"/>余额
            </td></tr>
            
            <tr>
             <td  class="firsttd">可用余额：</td>
             <td><@s.label name="memberfund.use_num" cssClass="txtinput" maxLength="10"/>余额</td>
            </tr>  
            
            <tr>
             <td  class="firsttd">冻结余额：</td>
             <td><@s.label name="memberfund.freeze_num" cssClass="txtinput" maxLength="10"/>余额</td>
            </tr>  
 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

