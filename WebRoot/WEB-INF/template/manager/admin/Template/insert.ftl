<head>
    <title>添加模板</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript"  src="/include/common/js/jquery.masonry.min.js"></script>
    <script type="text/javascript"  src="/include/admin/js/template.js"></script>
    <link href="/include/admin/css/template.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  	<@s.form id="water" action="/admin_Template_insert.action" method="post" validate="true" > 
   <div class="postion">
 	当前位置:网站管理 > 页面管理 > 网站页面 > 添加模板
   </div>
<div class="rtdcont">
   <div class="rdtable">
        <table class="wwtable" cellspacing="1" cellpadding="8">
	           <tr>
	             <td class="table_name">模板名称<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="template.template_name" cssClass="input" onkeyup="checkLength(this,20);" maxlength="20" cssStyle="width:220px;"/>
	             	<@s.fielderror><@s.param>template.template_name</@s.param></@s.fielderror>
	             </td>
	           </tr>
	           <tr>
	             <td class="table_name">容器宽*高<font color='red'>*</font></td>
	             <td>
	             	<span>宽:</span><@s.textfield id="contants_width"  name="template.width" cssClass="input" cssStyle="width:40px;" value="800"/>px *
	             	<span>高:</span><@s.textfield id="contants_height"  name="template.height" cssClass="input" cssStyle="width:40px;" value="320"/>px
	             	<img class="ltip" alt="本系统容器宽*高:800px*320px" src="/include/common/images/light.gif">
	                <@s.fielderror><@s.param>template_wf_code</@s.param></@s.fielderror>
	             </td>
	           </tr>
                
	           
	           <tr>
	             <td class="table_name">瀑布布局:</td>
	             <td>
	             	  <input type="button" value="新增" onclick="showAddDiv();" />
	             </td>
	           </tr>
	           <tr>
	           <td colspan="2">	             
	            <div class="tabbar">
				    <ul>
				      <li  id="dm">
				         <a onclick="showEffact('templateShowInfo');" >设计源码</a>
				      </li>
				      <li class="selected" id="xg">
				         <a onclick="showEffact('templateShow');" >设计效果</a>
				      </li>
				    </ul>
			     </div>
			     </td>
			    <tr>
               <tr>
	               <td colspan="2">
		             	<div id="templateShow"  style="margin-left:20px;width:<#if template!=null>${template.width?if_exists}</#if>px; height:<#if template!=null>${template.height?if_exists}</#if>px;">
		             	  <#if template!=null>
		             			${template.template_content?if_exists}
		             	  </#if>
		             	</div>
		               <div id="templateShowInfo"  style="display:none;">
		             		<@s.textarea id="showInfo" cssStyle="width:800px;height:320px;" cssClass="input"  readonly="true"/>
		             	</div>

               		</td>
               </tr>
	           <tr style="display:none;">
	             <td class="table_name">模板内容:</td>
	             <td>
	             	<@s.textarea id="template" name="template.template_content" cssClass="input" cssStyle="width:800px;height:450px;" readonly="true"/>
	             	<@s.fielderror><@s.param>template.template_content</@s.param></@s.fielderror>
	             </td>
	           </tr> 
         </table>
	     <div class="fixbuttom">
	       <@s.token/>
	       ${listSearchHiddenField?if_exists}
	        <input type="button" value="保存" onclick="savetemplate();" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Template_list.action','');"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
<div  style="display:none;"  id="editDIV"  class="searchDiv">
</div>
</body>
</html>

