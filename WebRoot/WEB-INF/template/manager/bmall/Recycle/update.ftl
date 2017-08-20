<html>
  <head>
    <title>查看站内信</title>
    <link rel="stylesheet" href="/include/bmall/css/receivebox.css" type="text/css">
  </head>
  <body>
<@s.form action="/bmall_Sendbox_update.action" method="post" validate="true" >
<div class="rightside f_right">
    <div class="postion">
		<a href="#">账户管理</a><span>></span><a href="#">站内信</a><span>></span><a href="#">查看站内信</a>
	</div>
	
<div class="base_infor">
   <h2>查看站内信</h2>
   <div class="receivebg">
      <div class="memlogo">
       		<#list logo_path?if_exists?split(',') as img>
	                  	 <img src="${img?if_exists}"  style="width:80px;height:80px;"/>
	        </#list>
       </div>
       
    <div class="sendtable">
	        <div class="title"><b class="sendtalbe_b">${sendbox.title?if_exists}</b></div>
	        <div  class="sendth">发&nbsp;件&nbsp;人:<span class="sendvalue">${cust_name?if_exists}</span></div>
            <div  class="sendth">时&nbsp;&nbsp;&nbsp;&nbsp;间:${(sendbox.in_date)?if_exists[0..18]}</div >
	        <div  class="sendth">收&nbsp;件&nbsp;人:<span class="receive">${sendbox.recevie_name?if_exists}</span></div >
	     	<@s.hidden name="sendbox.send_cust_id"/>
	     	<@s.hidden name="sendbox.is_send_del" value="1"/>
    </div>
          
     <div class="content">
     ${sendbox.content?if_exists}
     </div>
     <div class="response">
           <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/bmall_Recycle_list.action','');" class="submitbut"/>
     </div>
  <@s.token/>  
 </@s.form>
</div>
</div>
<!--cont结束-->
</body>
</html>

