<html>
<head>
<title>售后维护</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>

</head>
<body>
	
<@s.form action="/bmall_Aftersales_update.action" method="post" validate="true" >
<div class="rightside f_right">
<div class="postion">
  		 <a href="#">我是卖家</a><span>></span><a href="#">商品管理</a><span>></span><a href="#">售后维护</a>
    </div>
     <div class="rpostion">
     	<h2>售后维护内容</h2>
     </div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table width="100%">
	          
		           <tr>
			             <td>
				             <@s.textarea id="content" name="aftersales.content" cssClass="txtinput" onkeyup="checkLength(this,10);" />
				             	<@s.fielderror><@s.param>saftersales.content</@s.param></@s.fielderror>
								<script type="text/javascript">
							     	CKEDITOR.replace('content');  
								</script>
				             
			             </td>
		           </tr>
	
			        <tr>
			         <td colspan="2" align="center">
					   <@s.hidden name="aftersales.cust_id"/>  
					   ${listSearchHiddenField?if_exists}                
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

