<html>
  <head>
  
    <title>收件箱</title>
    
    <link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
	
  </head>
  <body>
   	<@s.form action="/bmall_Receivebox_replyadd.action" method="post" validate="true" >
   	
<div class="wR810">


     <div class="rwTitle">
     
         <h2><span>收件箱</span></h2>	
         
         <div class="email">
         
             <table width="100%" cellpadding="0" cellspacing="0" class="writeTab">
             
                 <tr>
                 	<th>收件人<span>*</span></th>
                 	<td> 
                 	    <@s.textfield name="cust_name" cssClass="aisText" onkeyup="checkLength(this,100);" maxlength="100" readonly="true"/>
		             	<@s.fielderror><@s.param>cust_name</@s.param></@s.fielderror>
                 	</td>
                 </tr>
                 
                 <tr>
                 	<th>主题<span>*</span></th>
                 	<td>
                 		<@s.textfield name="title" cssClass="aisText" onkeyup="checkLength(this,100);" maxlength="100" readonly="true"/>
		             	<@s.fielderror><@s.param>title</@s.param></@s.fielderror>
                 	</td>
                 </tr>
                 
                 <tr>
                 	<th>内容<span>*</span></th>
                 	<td >
                 		<@s.textarea  name="re_content"   cssStyle="width:660px;height:300px;"/>
			            <@s.fielderror><@s.param>re_content</@s.param></@s.fielderror>
                 	</td>
                 </tr>
                 
                 <tr>
                 	<th></th>
                 	<td  align="center">
                 	   <@s.hidden name="sendbox.send_id" />
		               <@s.hidden name="sendbox.in_date"/>
		               <@s.hidden name="receivebox.send_id"/>
		               <@s.hidden name="receivebox.get_cust_id"/>
		               <@s.hidden name="sendbox.is_read"/>
		               <@s.hidden id="sendman_content" name="sendman_content"/>
		               <@s.hidden name="sendbox.is_get_del"/>
				       <@s.token/>
				       ${listSearchHiddenField?if_exists}
			           <@s.submit value="回复" cssClass="submitbut"/>
				       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/bmall_Receivebox_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="submitbut"/>
                 	</td>
                 </tr>
                 
             </table>
             
         </div>
         
     </div>
     
</div>
 <@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
 </@s.form>
</body>
</html>