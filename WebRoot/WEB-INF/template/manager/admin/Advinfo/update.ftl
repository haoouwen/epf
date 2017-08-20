<html>
  <head>
    <title>修改广告信息</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <#include "/include/uploadInc.html">
     <script type="text/javascript" src="/include/admin/js/advinfo.js"></script>
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
  </head>
  <body>
 <@s.form action="/admin_Advinfo_update.action?posid=${posid?if_exists}" method="post" validate="true" onsubmit="return showPicPath();" id="advinfos">
    <@s.hidden name="type" id="type"/>
<div class="postion">
  <!--当前位置-->
  当前位置:网站管理 > 广告管理 > 广告管理 > 修改广告
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr>
             <td class="table_name">广告位名称:</td>
             <td>
                <@s.label name="dds" value="${(advpos.pos_name)?if_exists}"/>
                </span>
             </td>
              <td class="table_name">广告类型:</td>
             <td style="display:none;">
             	<@s.select id="advtype" name="advpos.pos_type" list="advcommparaList" listValue="para_key" listKey="para_value"/>
             </td>
             <td ><span id="idvalue"></span></td>
             </td>
           </tr>
            <tr id="category" style="display:none;">
             <td class="table_name" width="19%">模板类型:</td>
             <td ><span id="modulevalue"></span></td>
             <td style="display:none;">
             	<@s.select name="advpos.module_type" list="modulecommparaList" listValue="module_name" listKey="module_type" headerKey="0" headerValue="无所属模快"/>
             </td>
             <td class="table_name" width="19%">分类:</td>
             <td>
                <div id="divlist"></div>  
                <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>            	            
             </td>      
           </tr>
           
           
            <tr>
             <td width="19%" class="table_name">广告名称<font color='red'>*</font></td>
             <td colspan="5">
             	<@s.textfield name="advinfo.adv_name" cssClass="txtinput" cssStyle="width:600px;" maxLength="100"/>
             	<@s.fielderror><@s.param>advinfo.adv_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr style="display:none;">
             <td class="table_name" >所在地区:</td>             
              <td colspan="3">
             	<table>
             		<tr>
             			<td class="tdbottom">
             				<div id="areaDiv"></div>
             			</td>
             			<td class="tdbottom">
             				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
	              		</td>
	              	</tr>
	            </table>       
             </td>
           </tr>
          
           <tr id="moduletype" style="display:none;">
             <td class="table_name">所属模块<font color='red'>*</font></td>
             <td colspan="5">
                <@s.select name="module_type" list="modulecommparaList" listValue="para_key" id="paratype" listKey="para_value" headerKey="0" headerValue="请选择" />
             	<@s.fielderror><@s.param>advinfo.module_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="keyword" style="display:none;">
             <td class="table_name">关键字<font color='red'>*</font></td>
             <td colspan="5">
	            <@s.textfield name="advinfo.keyword" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>advinfo.keyword</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="catattr" style="display:none;">
             <td class="table_name">分类标识串<font color='red'>*</font></td>
             <td colspan="5">
	             <div id="divlist"></div>  
                 <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror> 
             </td>
           </tr>
             <tr id="advimagemulti" style="display:none;">
             <td class="table_name" >上传图片<font color='red'>*</font></td>
             <td colspan="5">
             
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="img_pathmulti" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImgMulti();</script>
             			</td>
             			<td>
             			   &nbsp;&nbsp;<@s.fielderror><@s.param>img_pathmulti</@s.param></@s.fielderror>       
             			</td>
             		</tr>
             	</table>
	        </td>
	       </tr >  
            <tr  id="advimage" >
             <td class="table_name">上传图片<font color='red'>*</font></td>
             <td colspan="5">
                          
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			<@s.textfield name="advinfo.img_path" id="uploadresult1" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>uploadImg("uploadifyfile1","uploadresult1","fileQueue1");</script>
             			</td>
             			<td>
             			   【建议图片${(advpos.p_width)?if_exists}px * ${(advpos.p_height)?if_exists}px】
             			   &nbsp;&nbsp;<@s.fielderror><@s.param>advinfo.img_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>
             </td>
           </tr>
           
           
           <tr style="display:none;">
	           	<td  class="table_name">自定义背景色：</td>
	           	<td td colspan="3">
		           <@s.textfield name="advinfo.bg_color" id="title_color" cssClass="iColorPicker" maxLength="20" />
				   <@s.fielderror><@s.param>advinfo.bg_color</@s.param></@s.fielderror>
	            </td>
            </tr> 
           
           <tr id="advlink" >
             <td class="table_name">链接地址<font color='red'>*</font></td>
             <td colspan="5">
	            <@s.textfield name="advinfo.link_url" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
	            <@s.fielderror><@s.param>advinfo.link_url</@s.param></@s.fielderror> 
	            <img src="/include/common/images/light.gif" style="vertical-align:middle;" alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
            <tr id="advflash" style="display:none;">
             <td class="table_name">上传flash<font color='red'>*</font></td>
             <td colspan="5">
             
               <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue2"></div>
	              			<@s.textfield name="advinfo.flash_url"    id="uploadresult2" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile2" id="uploadifyfile2"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult2');"/>
	              			<script>uploadComponent("uploadifyfile2","uploadresult2","fileQueue2","flash",false);</script>
             			</td>
             			<td>
             			   &nbsp;&nbsp;<@s.fielderror><@s.param>advinfo.flash_url</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>   
             </td>
           </tr>
           <tr id="advtitle" style="display:none;">
             <td class="table_name">显示标题<font color='red'>*</font></td>
             <td colspan="5">
	            <@s.textfield name="advinfo.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
	            <@s.fielderror><@s.param>advinfo.title</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr id="advcontent" style="display:none;">
             <td class="table_name">显示描述<font color='red'>*</font></td>
             <td colspan="5">
                <@s.textarea name="advinfo.content" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
                <@s.fielderror><@s.param>advinfo.content</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr id="advcode" style="display:none;">
             <td class="table_name">广告代码<font color='red'>*</font></td>
             <td colspan="5">
	            <@s.textarea name="advinfo.adv_code" cssClass="txtinput" rows="5" cssStyle="width:600px;"/>
	            <@s.fielderror><@s.param>advinfo.adv_code</@s.param></@s.fielderror> 
             </td>
           </tr>
            <tr style="display:none;">
             <td class="table_name">广告介绍:</td>
             <td colspan="5">
	            <@s.textarea name="advinfo.adv_desc" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
	            <@s.fielderror><@s.param>advinfo.adv_desc</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">播放时间<font color='red'>*</font></td>
             <td colspan="5">
               <#if advinfo.start_date?if_exists?length lt 10>
              <@s.textfield id="txtstartDate" name="advinfo.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
             <#else>
             <@s.textfield id="txtstartDate" name="advinfo.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"
		               value="${advinfo.start_date?if_exists[0..9]}"  />          
             </#if>至
               <#if advinfo.end_date?if_exists?length lt 10>
               <@s.textfield id="txtendDate" name="advinfo.end_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
             <#else>
             <@s.textfield id="txtendDate" name="advinfo.end_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})" 
		               value="${advinfo.end_date?if_exists[0..9]}" />         
             </#if>
             	<@s.fielderror><@s.param>advinfo.start_date</@s.param></@s.fielderror>
             	<@s.fielderror><@s.param>advinfo.end_date</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr style="display:none;">
             <td class="table_name">客户标识:</td>
             <td colspan="5">
	            <@s.textfield name="advinfo.cust_id" cssClass="txtinput" maxLength="20"/>
	            <@s.fielderror><@s.param>advinfo.cust_id</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr style="display:none;">
             <td class="table_name">广告状态:</td>
             <td colspan="5">
	            <@s.radio name="advinfo.adv_state" list=r"#{'0':'已审核','1':'未审核'}"/>
	            <@s.fielderror><@s.param>advinfo.adv_state</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr style="display:none;">
             <td class="table_name">点击统计:</td>
             <td colspan="5">
             	<@s.radio name="advinfo.iscount" list=r"#{'0':'开启','1':'关闭'}"/>
             	<@s.fielderror><@s.param>advinfo.iscount</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr style="display:none;">
             <td class="table_name">点击次数:</td>
             <td colspan="5">
             	<@s.textfield name="advinfo.addnum"  cssClass="txtinput" cssStyle="width:50px;" maxLength="11"/>
             	<@s.fielderror><@s.param>advinfo.addnum</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr style="display:none;">
             <td class="table_name">备注:</td>
             <td colspan="5">
             	<@s.textarea name="advinfo.remark" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>advinfo.remark</@s.param></@s.fielderror> 
             </td>
           </tr>
                <tr>
	            <tr id="advintr" >
	             <td width="19%" class="table_name">排序<font color='red'>*</font></td>
	             <td colspan="3">
	             	<@s.textfield  name="advinfo.info_id"  cssClass="txtinput" cssStyle="width:50px;"  maxLength="11" onkeyup="checkNum(this)"/>
	             	【数字越小,越排前面】
	             	<@s.fielderror><@s.param>advinfo.info_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
     <@s.hidden name="token_value" value="${get_token_value?if_exists}" />    
           <@s.token/> 
	       <@s.hidden name="advinfo.adv_id"/>
	       <@s.hidden name="posid"/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <!--所属分类插件隐藏字段结束区域-->
		   <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value" />
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="document.getElementById('advinfos').action='/admin_Advinfo_list.action';document.getElementById('advinfos').submit();"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>


${listSearchHiddenField?if_exists}   
<@s.hidden name="advpos.pos_id"/>    
<!--显示信息结束-->
  
  </body>
</html>