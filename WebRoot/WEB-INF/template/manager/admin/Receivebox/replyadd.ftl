<html>
  <head>
    <title>收件箱</title>
    <link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
  </head>
  <body>
   	<@s.form action="/admin_Receivebox_replyadd.action" method="post" validate="true" >
<div class="postion">
当前位置:会员管理 > 通讯管理 > 收件箱回复</div>
  <div class="rtdcont">
   <!--最新订单-->
   <div class="rdtable">
        <div class="email">
             <table width="100%" cellpadding="0" cellspacing="0" class="writeTab">
             
                 <tr>
                 	<td class="table_name" width="10%">收件人<span>*</span></td>
                 	<td> 
                 	    <@s.textfield name="cust_name" cssClass="aisText" onkeyup="checkLength(this,100);" maxlength="100" readonly="true"/>
		             	<@s.fielderror><@s.param>cust_name</@s.param></@s.fielderror>
                 	</td>
                 </tr>
                 
                 <tr>
                 	<td class="table_name">主题<span>*</span></td>
                 	<td>
                 		<@s.textfield name="title" cssClass="aisText" onkeyup="checkLength(this,100);" maxlength="100" readonly="true"/>
		             	<@s.fielderror><@s.param>title</@s.param></@s.fielderror>
                 	</td>
                 </tr>
                 
                 <tr>
                 	<td class="table_name">内容<span>*</span></td>
                 	<td class="cktd">
                 		<@s.textarea  name="re_content"  value="" cssStyle="width:660px;height:300px;"/>
			            <@s.fielderror><@s.param>re_content</@s.param></@s.fielderror>
                 	</td>
                 </tr>
             </table>
       </div>  
       <div class="clear"/>
         <div class="bsbut_detail">
          <@s.hidden name="sendbox.send_id" />
		               <@s.hidden name="sendbox.in_date"/>
		               <@s.hidden name="receivebox.send_id"/>
		               <@s.hidden name="receivebox.get_cust_id"/>
		               <@s.hidden name="sendbox.is_read"/>
		               <@s.hidden id="sendman_content" name="sendman_content"/>
		               <@s.hidden name="sendbox.is_get_del"/>
				       <@s.token/>
				       ${listSearchHiddenField?if_exists}
			           <@s.submit value="回复" cssClass="rbut"/>
				       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Receivebox_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="rbut"/>
				        <@s.hidden name="selli"/>
                       <@s.hidden name="parentMenuId"/> 

   </div>
</div>
<div class="clear"></div>
</@s.form>
<div class="clear"></div>
</body>
</html>