
           
         <tr>
             <td width="19%" class="table_name">广告名称<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="advinfo.adv_name" cssClass="txtinput" cssStyle="width:450px;" maxLength="100"/>
             	<@s.fielderror><@s.param>advinfo.adv_name</@s.param></@s.fielderror>
             </td>
         </tr>
         
         <tr id="keyword" style="display:none;">
             <td class="table_name">关键字<font color='red'>*</font></td>
             <td colspan="3">
	            <@s.textfield name="advinfo.keyword" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>advinfo.keyword</@s.param></@s.fielderror>
             </td>
         </tr>
               
          <tr>
             <td class="table_name">上传图片<font color='red'>*</font></td>
             <td colspan="3">
              <table border="0" cellpadding="0" cellspacing="0">
             	   <tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue1"></div>
	              			<@s.textfield  name="img_path"   id="uploadresult1" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile1" id="uploadifyfile1"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult1');"/>
	              			<script>sysuploadOneImgMulti();</script>
             			</td>
             			<td>
             			  &nbsp;&nbsp;<@s.fielderror><@s.param>advinfo.img_path</@s.param></@s.fielderror>
             			</td>
           			</tr>
             	</table>
             </td>
           </tr>
           
           <tr id="advlink" >
             <td class="table_name">链接地址<font color='red'>*</font></td>
             <td colspan="3">
	            <@s.textfield name="advinfo.link_url" cssClass="txtinput" cssStyle="width:400px;" maxLength="100"/>
	            <@s.fielderror><@s.param>advinfo.link_url</@s.param></@s.fielderror> 
	            <img src="/include/common/images/light.gif" style="vertical-align:middle;" alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
          
           
           <tr>
             <td class="table_name">广告介绍:</td>
             <td colspan="3">
	            <@s.textarea name="advinfo.adv_desc" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="checkLength(this,200);"/>
	            <@s.fielderror><@s.param>advinfo.adv_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr id="advintr">
             <td width="19%" class="table_name" style="display:none;">信息标识<font color='red'>*</font></td>
             <td colspan="3" style="display:none;">
             	<@s.textfield id="intrvalue" name="advinfo.info_id" value="0" cssClass="txtinput" cssStyle="width:50px;" maxLength="100"/>
             	<input type="button" name="info_id" value="选择" onclick="isview('${(advpos.module_type)?if_exists}');"/>
             	<@s.fielderror><@s.param>advinfo.info_id</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">播放时间<font color='red'>*</font></td>
             <td colspan="3">
             
             <#if advinfo.start_date?if_exists?length lt 10>
              		<@s.textfield id="txtstartDate" name="advinfo.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
             <#else>
             <@s.textfield id="txtstartDate" name="advinfo.start_date"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"
		               value="${advinfo.start_date?if_exists[0..9]}"  />          
             </#if> -
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
           
           <tr>
             <td class="table_name" style="display:none;">客户标识:</td>
             <td colspan="3" style="display:none;">
	            <@s.textfield name="advinfo.cust_id" value="0" cssClass="txtinput" maxLength="20"/>
	            <@s.fielderror><@s.param>advinfo.cust_id</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">广告状态:</td>
             <td colspan="3">
	            <@s.radio name="advinfo.adv_state" list=r"#{'0':'已审核','1':'未审核'}" value="0"/>
	            <@s.fielderror><@s.param>advinfo.adv_state</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">点击统计:</td>
             <td colspan="3">
             	<@s.radio name="advinfo.iscount" list=r"#{'0':'开启','1':'关闭'}" value="1"/>
             	<@s.fielderror><@s.param>advinfo.iscount</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">点击次数:</td>
             <td colspan="3">
             	<@s.textfield name="advinfo.addnum" value="0" cssClass="txtinput" cssStyle="width:50px;" maxLength="11"/>
             	<@s.fielderror><@s.param>advinfo.addnum</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">备注:</td>
             <td colspan="3">
             	<@s.textarea name="advinfo.remark" cssClass="txtinput" rows="5" cssStyle="width:600px;" onkeyup="checkLength(this,200);"/>
             	<@s.fielderror><@s.param>advinfo.remark</@s.param></@s.fielderror>
             </td>
           </tr>
    