<html>
  <head>
    <title>修改汇率信息</title>
    <#include "/include/uploadInc.html">
    <link rel="StyleSheet"  type="text/css" href="/include/admin/css/member.css"/>
    <script language="javascript" type="text/javascript" src="/include/admin/js/rate1.js"></script>
  </head>
  <body>
<@s.form action="/admin_Rate_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:系统管理 > 系统管理 > 修改汇率
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">汇率名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="rate.rate_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>rate.rate_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">单位<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="rate.rate_unit" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>rate.rate_unit</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">符号<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="rate.rate_mark" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>rate.rate_mark</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">图片<font color='red'>*</font></td>
            <td colspan="3">
            <table border="0" cellpadding="0" cellspacing="0">
     		<tr>
     			<td style="padding:0px;">
     			<div id="fileQueue"></div>
          			<@s.textfield name="rate.rate_img" id="uploadresult" cssStyle="width:200px;"/>
                </td>
     			<td style="padding-left:3px;">
     				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
     			</td>
     			<td style="padding-left:3px;">
     				<img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
     				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽150px高80px">
          			<script>uploadOneImg();</script>
     			</td>
     			<td>
     			   <@s.fielderror><@s.param>rate.rate_img</@s.param></@s.fielderror>
     			</td>
     		</tr>
     	</table>
     
     </td>
           </tr>
       
            <tr>
             <td class="table_name">是否启用:</td>
             <td>
	             <@s.radio name="rate.enables" list=r"#{'0':'是','1':'否'}" onclick="getMemValue();"/>
	 	         <@s.fielderror><@s.param>rate.enables</@s.param></@s.fielderror>
    		 </td>
           </tr>
       
           <tr>
             <td class="table_name">汇率<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="rate.exchangerate" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this)"/>
             	<img class="ltip" src="/include/common/images/light.gif" alt="汇率最多保留三位有效数字">
             	<input type="button" onclick="lookRate();"value="查看最新汇率">
             	<@s.fielderror><@s.param>rate.exchangerate</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <#if rate.endefault?if_exists=="0">
	    	 	<@s.hidden name="rate.endefault"/>
	       <#else>
	    		 <tr>
		             <td class="table_name">是否默认:</font></td>
		             <td>
						<@s.radio name="rate.endefault" list=r"#{'0':'是','1':'否'}"  onclick="getMemValue();"/>
	    	     		<@s.fielderror><@s.param>rate.endefault</@s.param></@s.fielderror>
            		 </td>
		           </tr>
	        </#if>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	   <@s.hidden name="rate.rate_id"/>
	   ${listSearchHiddenField?if_exists}
	   <@s.submit value="保存" />
	   <@s.reset value="重置"/>
	   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Rate_list.action','');"/>
	   <@s.token/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
<div id="lookRate"style="display:none;overflow:hidden;margin:0">
	<iframe id="frame-content" src="http://toolsapp.duapp.com/exchange_rate.php" name="content" height="235" width="560" frameborder="0" scrolling="no" style="margin-top:-25px;overflow:hidden;margin-left:0px;">
	</iframe>
</div>
</body>
</html>

