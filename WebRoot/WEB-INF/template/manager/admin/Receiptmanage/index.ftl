<html>
  <head>
    <title>单据模板管理</title>
    <!--fck开始-->
    <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
    <!--fck结束-->
    <link href="/include/admin/css/receiptmanage.css" rel="stylesheet" type="text/css">
    <script type="text/javascript"  src="/include/common/js/insert.position.js"></script>
    <script type="text/javascript">
		$(function(){
			//tab切换页
			$("#oper_receiptmanage_div").mallTab({
			});
		})
	</script>
  </head>
 
<body>
<@s.form action="/admin_Receiptmanage_update.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:网站管理 > 单据模板管理 > 单据模板管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--后台table-->
   <div class="rtable">
      <div id="oper_receiptmanage_div" class="oper_receiptmanage_div">      
					       <div class="tabbar">
							    <ul>
							   		<li class="selected">购物清单</li>
							   		<li>发票单据</li>
							   		<li>发货单据</li>
							    </ul>
					       </div>
					       
					   <div class="clear"></div>
					   <div class="tabdiv" style="display:block;">
					       	<#list receiptmanageList as receiptmanage>
					       	  <#if receiptmanage.receipt_code?if_exists='1'>
							       	 <div class="showtitle">
								       	 <b>
								       	 ${receiptmanage.receipt_name?if_exists}
					    		         </b>
				    		         </div>
				    		         
								     <div >
						             	<@s.textarea id="content1" name="receiptmanage.receipt_enable" value="${receiptmanage.receipt_enable?if_exists}" cssClass="txtinput" />
						             	<@s.fielderror><@s.param>receiptmanage.receipt_enable</@s.param></@s.fielderror>
									    <script type="text/javascript">
									     	CKEDITOR.replace('content1');  
									     </script>
								     </div>
									 <@s.hidden name="receiptmanage.trade_id" value="${receiptmanage.trade_id?if_exists}"/> 
									 <@s.hidden name="receiptmanage.receipt_code" value="${receiptmanage.receipt_code?if_exists}"/> 
							    </#if>
							</#list>
						</div>
						
						 <div class="tabdiv" >
					       	<#list receiptmanageList as receiptmanage>
					       	  <#if receiptmanage.receipt_code?if_exists='2'>
							       	 <div class="showtitle">
								       	 <b>
								       	  ${receiptmanage.receipt_name?if_exists}
					    		         </b>
				    		         </div>
				    		         
								     <div >
						             	<@s.textarea id="content2" name="receiptmanage.receipt_enable" value="${receiptmanage.receipt_enable?if_exists}" cssClass="txtinput" />
						             	<@s.fielderror><@s.param>receiptmanage.receipt_enable</@s.param></@s.fielderror>
									    <script type="text/javascript">
									     	CKEDITOR.replace('content2');  
									     </script>
								     </div>
									 <@s.hidden name="receiptmanage.trade_id" value="${receiptmanage.trade_id?if_exists}"/> 
									  <@s.hidden name="receiptmanage.receipt_code" value="${receiptmanage.receipt_code?if_exists}"/> 
							    </#if>
							</#list>
						</div>
					       
					      <div class="tabdiv">
					       	<#list receiptmanageList as receiptmanage>
					       	  <#if receiptmanage.receipt_code?if_exists='3'>
							       	 <div class="showtitle">
								       	 <b>
						       	 			 ${receiptmanage.receipt_name?if_exists}	
					    		         </b>
				    		         </div>
				    		         
								     <div >
						             	<@s.textarea id="content3" name="receiptmanage.receipt_enable" value="${receiptmanage.receipt_enable?if_exists}" cssClass="txtinput" />
						             	<@s.fielderror><@s.param>receiptmanage.receipt_enable</@s.param></@s.fielderror>
									    <script type="text/javascript">
									     	CKEDITOR.replace('content3');  
									     </script>
								     </div>
									 <@s.hidden name="receiptmanage.trade_id" value="${receiptmanage.trade_id?if_exists}"/> 
								    <@s.hidden name="receiptmanage.receipt_code" value="${receiptmanage.receipt_code?if_exists}"/> 
							    </#if>
							</#list>
					  </div>
                      
                     </div>
  </div>
<!--后台table结束-->

<!--按钮操作存放-->
   <div class="bsbut">
       <@s.token/>
	   <@s.submit value="保存"/> 
   </div>
<!--按钮操作存放结束-->
</div>

</@s.form>


  </body>
</html>



