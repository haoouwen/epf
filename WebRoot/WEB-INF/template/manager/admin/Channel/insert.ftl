<html>
  <head>
    <title>添加栏目</title>
	<script type="text/javascript" src="/include/admin/js/channel.js"></script>
  </head>
  <body>
  <@s.form action="/admin_Channel_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:网站管理 > 基本功能 > 网站栏目管理 > 添加网站栏目
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
           <tr>
             <td width="19%" class="table_name">栏目名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="channel.ch_name" id="cnames" onblur="checkcname();" cssClass="txtinput" maxLength="20" cssStyle="width:350px;"/>
             	<@s.fielderror><@s.param>channel.ch_name</@s.param></@s.fielderror>
             	<font color="red"><label id="cname" ></label></font>
             </td>
           </tr>
           <tr>
             <td class="table_name">栏目标题:</td>
             <td>
             	<@s.textfield name="channel.ch_title" cssClass="txtinput" maxLength="100" cssStyle="width:350px;"/>
             	<@s.fielderror><@s.param>channel.ch_title</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">栏目级别:</td>
             <td>
              <#if channel.ch_level!=null>
             	${(channel.ch_level?number+1)!1}级    
             <#else>
               1级 
             </#if>   	
             <@s.fielderror><@s.param>channel.ch_level</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">栏目排序<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="channel.sort_no" value="0" cssClass="txtinput" cssStyle="width:50px;" maxLength="4"/>
             	<@s.fielderror><@s.param>channel.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">文件保存目录<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="channel.save_dir" cssClass="txtinput" cssStyle="width:350px;" maxLength="100"/>
             	<@s.fielderror><@s.param>channel.save_dir</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">文件默认名<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="channel.file_name" cssClass="txtinput" maxLength="50" cssStyle="width:250px;" value="index.html"/>
             	<@s.fielderror><@s.param>channel.file_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">页面模板:</td>
             <td>
             	<@s.textfield name="channel.temp_path" id="tempvalue" cssClass="txtinput" cssStyle="width:280px;"  maxLength="100"/>
             	<input type="button" value="选择PC端模板" onclick="selectchanneltemp_path('cfg_templatefolder');" />
             	<input type="button" value="选择触屏版模板" onclick="selectchanneltemp_path('cfg_webapptemplatefolder');" />
             	<@s.fielderror><@s.param>channel.temp_path</@s.param></@s.fielderror>
             </td>
           </tr>
            
           <tr>
             <td class="table_name">详细页模板:</td>
             <td>
             	<@s.textfield name="channel.article_temp" id="tempvalueart" cssClass="txtinput" cssStyle="width:280px;"  maxLength="100"/>
             	<input type="button" value="选择PC端模板" onclick="selectchannelarticle_temp('cfg_templatefolder');" />
             	<input type="button" value="选择触屏版模板" onclick="selectchannelarticle_temp('cfg_webapptemplatefolder');" />
             	<@s.fielderror><@s.param>channel.article_temp</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">是否PC端</td>
             <td>
               <@s.radio name="channel.is_webapp" list=r"#{'0':'PC端','1':'触屏版'}" value="0"/>
               <@s.fielderror><@s.param>channel.is_webapp</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">所属模块:</td>
             <td>
              <@s.select name="channel.module_type" list="commparaList"  listValue="module_name" listKey="module_type" headerKey="0" headerValue="请选择"/>
              <@s.fielderror><@s.param>channel.module_type</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">详细页命名规则:</td>
             <td>
             	<@s.textfield name="channel.article_rule" cssClass="txtinput" maxLength="50" cssStyle="width:280px;" value="{typedir}/{Y}/{M}{D}/{aid}.html "/>
             	<@s.fielderror><@s.param>channel.article_rule</@s.param></@s.fielderror>
             </td>
           </tr>

           
           <tr>
             <td class="table_name">meta关键字:</td>
             <td>
             	<@s.textarea name="channel.meta_keyword" cssClass="txtinput"  onkeyup="checkLength(this,200);"cssStyle="width:350px;"/>
             	<@s.fielderror><@s.param>channel.meta_keyword</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">meta描述:</td>
             <td>
             	<@s.textarea name="channel.meta_desc" cssClass="txtinput" onkeyup="checkLength(this,200);" cssStyle="width:350px;"/>
             	<@s.fielderror><@s.param>channel.meta_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">备注:</td>
             <td>
             	<@s.textarea name="channel.remark" cssClass="txtinput" onkeyup="checkLength(this,100);" cssStyle="width:350px;"/>
             	<@s.fielderror><@s.param>channel.remark</@s.param></@s.fielderror>
             </td>
           </tr>
           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	     <@s.token/>
	       <@s.submit value="保存"/>
	       <@s.hidden name="channel.up_ch_id" value="${(channel.up_ch_id)!'0'}" />
	        <#if channel.ch_level!=null>
	         <@s.hidden name="channel.ch_level" value="${(channel.ch_level?number+1)!1}" /> 
             <#else>
               <@s.hidden name="channel.ch_level" value="1" />
             </#if>  
              <@s.hidden name="mall_type" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Channel_list.action';document.forms[0].submit();"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>