<html>
<head>
<title>查看余额</title>
</head>
<body>
<@s.form action="/bmall_Memberfund_update.action" method="post" validate="true">
<!--右边-->
<div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>查看余额</span></h2>
        <!----> 
            <div class="cancelDiv">
	            <!---->
	            <div class="opeDiv padDiv">
	              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
	              	<tr><th width="15%">总余额：</th>
	              	<td width="85%">
	              		${memberfund.fund_num?if_exists}元
	              	</td></tr>
	                <tr><th width="15%">可用余额：</th>
	              	<td width="85%">
		               ${memberfund.use_num?if_exists}元
	              	</td></tr>
                    <tr><th width="15%">冻结余额：</th>
                    <td width="85%">
		        	${memberfund.freeze_num?if_exists}元 &nbsp;&nbsp;<a href="###" style="color: #448FF4;cursor: pointer;outline: medium none;text-decoration: none;" onclick="linkToInfo('/bmall_Fundhistory_frostList.action','');">查看冻结明细</a>
		            </td></tr>
	                
	              </table>
	            </div>
          </div>
   </div>
</div>
  <div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
</body>
</html>

