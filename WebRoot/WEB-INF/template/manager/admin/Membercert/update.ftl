<html>
  <head>
    <title>修改荣誉资质</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
	<#include "/include/uploadInc.html">
  </head>
  <body>

<div class="tj_main f_left">
   <div class="pageLocation">
 	   当前位置:会员管理 > 企业会员 > 荣誉资质管理 > 修改荣誉资质
   </div>
   <div class="tj_main_cont">
   
   	<@s.form action="/admin_Membercert_update.action" method="post" validate="true">
   	
        <table class="wwtable" cellspacing="1" cellpadding="8">  
           <tr>
             <td width="19%" class="table_name">证书标题<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membercert.title" cssClass="txtinput" cssStyle="width:400px;" maxLength="50"/>
             	<@s.fielderror><@s.param>membercert.title</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">发证机构<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membercert.organize" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>membercert.organize</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">发证日期<font color='red'>*</font></td>
             <td>
             
             <#if membercert.start_date?if_exists?length lt 10>
               <@s.textfield id="txtstartDate" name="membercert.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
             <#else>
             <@s.textfield id="txtstartDate" name="membercert.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
             value="${membercert.start_date?if_exists[0..9]}" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />          
             </#if>
             	<@s.fielderror><@s.param>membercert.start_date</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">到期日期<font color='red'>*</font></td>
             <td>
              <#if membercert.end_date?if_exists?length lt 10>
              <@s.textfield id="txtendDate" name="membercert.end_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
             <#else>
             <@s.textfield id="txtendDate" name="membercert.end_date" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
             value="${membercert.end_date?if_exists[0..9]}" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />         
             </#if>
             	<@s.fielderror><@s.param>membercert.end_date</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">证书图片<font color='red'>*</font></td>
             <td>
             
               <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			 <@s.textfield name="membercert.img_path" id="uploadresult" cssStyle="width:300px;" />
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>&nbsp;&nbsp;<@s.fielderror><@s.param>membercert.img_path</@s.param></@s.fielderror></td>
             		</tr>
             	</table>
             
             </td>
           </tr>
           <tr>
             <td class="table_name">证书介绍:</td>
             <td>
                <@s.textarea name="membercert.cert_desc" id="cert_desc" cssClass="txtinput" cssStyle="width:300px;height:200px;" />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js" ></script>
				<script type="text/javascript" >
			     CKEDITOR.replace( 'cert_desc');  
				</script>
				<@s.fielderror><@s.param>membercert.cert_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
            <td class="table_name">证书状态:</td>
             <td>
                 <@s.radio name="membercert.cert_state" list=r"#{'0':'通过','1':'未审核','2':'未通过'}"/>
                 <@s.fielderror><@s.param>membercert.cert_state</@s.param></@s.fielderror>     	
             </td>
           </tr>   
         </table>
         
	     <div class="buttom">
	       <@s.token/>
	       <@s.hidden name="membercert.cert_id"/>
	       <@s.hidden name="membercert.cust_id"/>  
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membercert_list.action','');"/>
	     </div>
	     
	  </@s.form>
	  
   </div>
</div>

</div>
<div class="clear"></div>
  </body>
</html>