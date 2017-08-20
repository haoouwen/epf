<html>
  <head>
    <title>商城模板管理</title>  
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script type="text/javascript" src="/include/admin/js/malltemplate.js"></script>	 
    <link href="/include/admin/css/malltemplate.css" rel="stylesheet" type="text/css" /> 
    <link rel="StyleSheet"  type="text/css" href="/include/admin/css/common.css"/>
   </head>
  <body>
  
  
/****************************/



<body>
<@s.form action="/admin_Malltemplate_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:网站管理 > 网站页面 > 商城模板管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >模板代码:</td> 
			<td><@s.textfield name="template_code_s"  cssStyle="width:150px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
 <div class="checkTemplete">
				<#list mallEnableTemplateList as malltemplate>
					<#if malltemplate.is_enable=='0'>
						   <p><h2>正使用的模板</h2></p>
			   	<div class="mainpic">
					<#list malltemplate.template_image?if_exists?split(',') as img>
					       <a class="group" href="${img?if_exists}">
					      	 <img class="showimgstyle" src="${img?if_exists}"/>
					       </a> 
					</#list>
			    </div>
		   
		   <div class="maincont">
		     <p >模板名称:${malltemplate.template_name?if_exists}</p>
		      <ul>
		        <li>
		          作者:${malltemplate.author?if_exists}
		        </li>
		     </ul>
		    	<p><a onclick="linkToInfo('/admin_Malltemplate_view.action','malltemplate.trade_id=${malltemplate.trade_id?if_exists}');">
		    	<img src="/include/admin/images/but1.gif">
		    	</a>
		    	</p>
		   </div>
		   
	   </div>
	   <div class="clear"></div>
 	</#if>
</#list>	

   
<div class="selTemplete">	   
<p><h2>可以选用的模板</h2></p>
	   
<#list malltemplateList as malltemplate>
	<#if malltemplate.is_enable=='1'>
			<div class="mb">
			    <center> <div class="imgdiv">
			    <#list malltemplate.template_image?if_exists?split(',') as img>
				       <a class="group" href="${img?if_exists}">
				      	 <img class="showimgstyle" src="${img?if_exists}"/>
				       </a> 
				</#list>
			</div>
			</center>
		     <p class="butt">
		     
		     <a onclick="linkToInfo('/admin_Malltemplate_view.action','malltemplate.trade_id=${malltemplate.trade_id?if_exists}');"><img src="/include/admin/images/but1.gif"></a>
		     <a onclick="linkToInfo('/admin_Malltemplate_selTemplete.action','malltemplate.trade_id=${malltemplate.trade_id?if_exists}');"><img src="/include/admin/images/but3.gif"></a>
             <a onclick="delOneInfoa('malltemplate.trade_id','/admin_Malltemplate_delete.action','${malltemplate.trade_id?if_exists}');"><img src="/include/admin/images/but2.gif"></a>
		     
		      <p class="templname" >模板名称:${malltemplate.template_name?if_exists}</p>
		      
		     <p class="templname1"> 作者:${malltemplate.author?if_exists}|<span class="time">${malltemplate.in_date?if_exists}</span></p>
		     
		      
		      
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
   <div class="bsbut">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Malltemplate_add.action','');" value="添加商城模板">
   </div>
<!--按钮操作存放结束-->

<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden name="nav_post_s"/>
  <@s.hidden name="isshow_s"/>
  <@s.token/>
 <!--表单框隐藏域存放-->  
</@s.form>


  </body>
</html>




  
  
  
</body>
</html>

