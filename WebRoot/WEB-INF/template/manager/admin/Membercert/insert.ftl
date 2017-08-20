<html>
  <head>
    <title>添加荣誉资质</title>
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
	 <#include "/include/uploadInc.html">
  </head>
  <body>
<@s.form action="/admin_Membercert_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置--> 当前位置:会员管理 > 企业会员 > 荣誉资质管理 > 添加荣誉资质
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
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
               <@s.textfield id="start_date" name="membercert.start_date"   cssClass="Wdate" 
			   onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'end_date\\',{d:-1})}',readOnly:true})" />
             	<@s.fielderror><@s.param>membercert.start_date</@s.param></@s.fielderror>
             </td>
           </tr> 
            <tr>
             <td class="table_name">到期日期<font color='red'>*</font></td>
             <td>
               <@s.textfield id="end_date" name="membercert.end_date"  cssClass="Wdate" 
                onclick="WdatePicker({minDate:'#F{$dp.$D(\\'start_date\\',{d:1})}',readOnly:true})" />
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
	              			 <@s.textfield name="membercert.img_path" id="uploadresult" cssStyle="width:300px;"/>
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
                 <@s.radio name="membercert.cert_state" list=r"#{'0':'通过','1':'未审核','2':'未通过'}" value="0"/>
                 <@s.fielderror><@s.param>membercert.cert_state</@s.param></@s.fielderror>     	
             </td>
           </tr> 
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
   </div>
</div>
<div class="clear"></div>
</@s.form>











  </body>
</html>