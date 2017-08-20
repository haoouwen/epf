<html>
  <head>
	 <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
     <link rel="stylesheet" href="/include/admin/css/pagetip.css" type="text/css" />
  </head>
<body>


<@s.form action="/admin_Asysuser_wordpdfupdate.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置 > 会员管理 >区域代理>word/pdf文件 
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <div class="pagetip_main">	
		        <p class="p1">
						<#if (pagetip.page_code)?if_exists='word_pdffile_page'>
	                          word/pdf文件
				    	</#if>
				    	<span><img class='ltip' src="/include/common/images/light.gif" alt="编辑保存后,请点击更新缓存" />[编辑保存后,请点击<a href="###" onclick="renewload();">更新缓存</a>]</span>
			    </p>
			    
			    <div class="pagecontent">
			         <div class="pagecontentsize">
			             <@s.textarea id="content" name="pagetip.page_content" cssClass="txtinput" />
			             	<@s.fielderror><@s.param>pagetip.page_content</@s.param></@s.fielderror>
							<script type="text/javascript">
						     	CKEDITOR.replace('content');  
							</script>
		              </div>
	           </div>
			   
   	  </div>	
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   	      <@s.hidden name="pagetip.page_code" value="${(pagetip.page_code)?if_exists}"/>  
	       <@s.hidden name="pagetip.state" value="0"/>  
	        <@s.hidden name="pagetip.remark" value="${(pagetip.remark)?if_exists}"/>  
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	       <@s.token/>
           <@s.submit value="保存" /><img class='ltip' src="/include/common/images/light.gif"  alt="系统提示：编辑保存之后，请点击更新缓存！" /> 
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

