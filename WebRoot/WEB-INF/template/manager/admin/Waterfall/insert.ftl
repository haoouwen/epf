<head>
    <title>添加瀑布流</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript"  src="/include/common/js/jquery.masonry.min.js"></script>
    <script type="text/javascript"  src="/include/admin/js/waterfall.js"></script>
    <link href="/include/admin/css/waterfall.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  	<@s.form id="water" action="/admin_Waterfall_insert.action" method="post" validate="true" > 
   <div class="postion">
 	当前位置:网站管理 > 网站页面 > 瀑布管理 > 添加瀑布流
   </div>
<div class="rtdcont">
   <div class="rdtable">
        <table class="wwtable" cellspacing="1" cellpadding="8">
	           <tr>
	             <td class="table_name" style="width:220px;">瀑布名称<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="waterfall.wf_code" cssClass="input" onkeyup="checkLength(this,20);" maxlength="20" cssStyle="width:220px;"/>
	             	<@s.fielderror><@s.param>waterfall.wf_code</@s.param></@s.fielderror>
	             </td>
	           </tr>
	           <tr>
	             <td class="table_name" style="width:220px;">模板<font color='red'>*</font></td>
	             <td>
	             	<@s.select name="waterfall.temp_type" cssClass="input" list="templateList" listKey="template_id" listValue="template_name" headerValue="请选择" headerKey="" onchange="selected()" id="wf_code"/>
	             	<@s.fielderror><@s.param>waterfall.temp_type</@s.param></@s.fielderror>
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
		             	<div id="templateShow"  style="margin-left:20px;width:${waterfall.width?if_exists}px; height:${waterfall.height?if_exists}px;">
		             	  <#if waterfall!=null>
		             			${waterfall.template_content?if_exists}
		             	  </#if>
		             	</div>
		               <div id="templateShowInfo"  style="display:none;">
		             		<@s.textarea id="showInfo" cssStyle="width:800px;height:320px;" cssClass="input"  readonly="true"/>
		             	</div>
               		</td>
               </tr>
	           <tr style="display:none">
	             <td class="table_name" >模板内容:</td>
	             <td>
	             	<@s.textarea id="template" name="waterfall.template_content" cssClass="input" cssStyle="width:800px;height:450px;" readonly="true"/>
	             	<@s.fielderror><@s.param>waterfall.template_content</@s.param></@s.fielderror>
	             </td>
	           </tr> 
         </table>
	     <div class="fixbuttom">
	       <@s.token/>
	       ${listSearchHiddenField?if_exists}
	        <input type="button" value="保存" onclick="savetemplate();" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Waterfall_list.action','');"/>
	      <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
	     </div>
	  </@s.form>
   </div>
</div>

</div>
<div class="clear"></div>
<!--搜索区域开始-->
<div  style="display:none;"  id="editDIV"  class="searchDiv">
</div>
<!--搜索区域结束-->
</body>
</html>

