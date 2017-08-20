<html>
  <head>
	 <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
     <link rel="stylesheet" href="/include/admin/css/pagetip.css" type="text/css" />
  </head>
<body>


<@s.form action="/admin_Pagetip_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
 	当前位置 > 网站管理 > 网站显示页面 > 
 	<#if (pagetip.page_code)?if_exists='system_error_page'>
		 系统错误页
	<#elseif (pagetip.page_code)?if_exists='not_found_page' >
		无法找到页面
	<#elseif (pagetip.page_code)?if_exists='serach_null_page' >
	 	搜索为空页面
	<#elseif (pagetip.page_code)?if_exists='register_agreement_page'>
		网站注册协议
	<#elseif (pagetip.page_code)?if_exists='token_error_page'>
		重复提交页面
	<#elseif (pagetip.page_code)?if_exists='illegal_shop_page'>
		店铺不存在页面
	<#elseif (pagetip.page_code)?if_exists='illegal_goods_page'>
		商品不存在页面
	<#elseif (pagetip.page_code)?if_exists='not_oper_right_page'>
		没有操作权限页面
	<#elseif (pagetip.page_code)?if_exists='freeuse_agreement_page'>
		试用条款
	<#elseif (pagetip.page_code)?if_exists='yushou_agreement_page'>
		预售规则
	<#elseif (pagetip.page_code)?if_exists='customs_publicly_page'>
	   海关总署公告
	 <#elseif (pagetip.page_code)?if_exists='app_contact_us'>
	    手机联系我们
	<#else>
		页面管理
	</#if>
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <div class="pagetip_main">	
   		  
		        <p class="p1">
		             	<#if (pagetip.page_code)?if_exists='system_error_page'>
			    			 系统错误页（系统500页面）时，显示如下内容：
				    	<#elseif (pagetip.page_code)?if_exists='not_found_page' >
				    		无法找到页面（系统404页面）时，显示如下内容：
				    	<#elseif (pagetip.page_code)?if_exists='serach_null_page' >
				    	 	搜索为空页面，显示如下内容：
				    	<#elseif (pagetip.page_code)?if_exists='register_agreement_page'>
				    		网站注册协议
			    		<#elseif (pagetip.page_code)?if_exists='token_error_page'>
				    		重复提交页面
				    	<#elseif (pagetip.page_code)?if_exists='illegal_shop_page'>
				    		店铺不存在页面
				    	<#elseif (pagetip.page_code)?if_exists='illegal_goods_page'>
				    		商品不存在页面
				        <#elseif (pagetip.page_code)?if_exists='not_oper_right_page'>
				    		没有操作权限页面
				    	<#elseif (pagetip.page_code)?if_exists='freeuse_agreement_page'>
				    		  试用条款
				    	<#elseif (pagetip.page_code)?if_exists='yushou_agreement_page'>
							预售规则
						<#elseif (pagetip.page_code)?if_exists='customs_publicly_page'>
	                          海关总署公告
	                     <#elseif (pagetip.page_code)?if_exists='app_contact_us'>
	   					 手机联系我们
				    	<#else>
				    		页面管理
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

