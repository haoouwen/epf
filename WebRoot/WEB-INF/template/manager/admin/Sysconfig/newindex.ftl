<html>
  <head>
    <title>系统参数设置</title>
    <#include "/include/uploadInc.html">
    <link href="/include/admin/css/sysconfig.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/include/admin/js/sysconfig.js"></script>
    <script type="text/javascript">
		$(function(){
			//tab切换页
			$("#oper_seo_div").mallTab({});
		})
		function setHiddenVal(val_id,val){
			document.getElementById("set_"+val_id).value = val;
		}
	</script>
  </head>





<body>
<@s.form id="myform" action="/admin_Sysconfig_updateConfig.action" method="post" >
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 系统设置 > 系统参数设置</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--后台table-->
   <div class="rtable">
   <div id="oper_seo_div" class="oper_seo_div">      
					       <div class="tabbar">
							    <ul>
									<#list commparaList as commpara>
								      <li <#if commpara.para_value?if_exists==men_index> class="selected" </#if>>
									         <a onclick="getindex('${commpara.para_value?if_exists}');">         	
									         	 ${commpara.para_key?if_exists}
									         </a>
								      </li>
							       </#list>
							    </ul>
					       </div>
					       
					       	<div class="clear"></div>
					       	<#list commparaList as commpara>
					       	
					       		<#if commpara.para_value==0>
							      	<div class="tabdiv"  <#if commpara.para_value==men_index>style="display:block;" </#if>>
							      		<#include "/WEB-INF/template/manager/admin/Sysconfig/baseconfig.ftl">
							      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==1>
						      	<div class="tabdiv"  <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/mallconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==2>
						      	<div class="tabdiv"  <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/imageconfig.ftl">
						      	</div>
						      	</#if>
						      	<#if commpara.para_value==3>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/pointsconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==4>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/modubleconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==5>
						      	<div class="tabdiv">
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/effconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==6>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/postconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==7>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/memberconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==8>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/affixconfig.ftl">
						      	</div>
						      	</#if>
						      	
						      	<#if commpara.para_value==9>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/seoconfig.ftl">
						      	</div>
						      	</#if>
						      	<#if commpara.para_value==11>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/smodeconfig.ftl">
						      	</div>
						      	</#if>
						      	<#if commpara.para_value==12>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/safety.ftl">
						      	</div>
						      	</#if>
						      <#if commpara.para_value==13>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/indexconfig.ftl">
						      	</div>
						      	</#if>
						      	   <#if commpara.para_value==14>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/orderconfig.ftl">
						      	</div>
						      	</#if>
						      	 <#if commpara.para_value==15>
						      	<div class="tabdiv" <#if commpara.para_value==men_index>style="display:block;" </#if>>
						      		<#include "/WEB-INF/template/manager/admin/Sysconfig/kjtconfig.ftl">
						      	</div>
						      	</#if>
						    </#list>
						    
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
	   <div class="bsbut_detail">
			   <@s.token/>
		    <@s.hidden name="sysdesc" id="sysdesc" />
		    <@s.hidden name="sysvalue" id="sysvalue" />
		    <@s.hidden name="syssort" id="syssort" />
		    <@s.hidden name="men_index" id="men_index" />
		   <input type="button" value="保存" onclick="syssubmi();"/>
		   <@s.reset value="重置" style="cursor:pointer;"/>
		</div>
	</div>
</div>
<!--按钮操作存放结束-->
<!--表单框隐藏域存放-->
  <@s.hidden name="nav_post_s"/>
  <@s.hidden name="isshow_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>




