<html>
  <head>
    <title>审核荣誉资质</title>
	  <script src="/include/js/admin/pitureshow.js" type="text/javascript"></script>	
	  <script type="text/javascript">
	  $(document).ready(function(){    
         //图片展示
         firstaddimges("mypicid","addimg","100","100");
  	  });
	</script> 
  </head>
  <body>
   	<@s.form action="/admin_Membercert_auditState.action" method="post" validate="true" onsubmit="return noreasron('membercert.cert_state','noreason',2);">
<div class="postion">
  <!--当前位置-->当前位置:会员管理 > 企业会员 > 荣誉资质管理 > 审核荣誉资质
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td width="19%" class="table_name">证书标题:</td>
             <td>
             	<@s.label name="membercert.title" />
             </td>
           </tr>
            <tr>
             <td class="table_name">发证机构:</td>
             <td>
             	<@s.label name="membercert.organize" />
             </td>
           </tr> 
            <tr>
             <td class="table_name">发证日期:</td>
             <td>
                <@s.label name="membercert.start_date.substring(0,19)" />   
             </td>
           </tr> 
            <tr>
             <td class="table_name">到期日期:</td>
             <td>
                <@s.label name="membercert.end_date.substring(0,19)" /> 
             </td>
           </tr> 
            <tr>
             <td class="table_name">证书图片:</td>
             <td>
               <@s.hidden name="membercert.img_path" id="mypicid"/>   
               <div id="addimg">
               </div>
             </td>
           </tr>
           <tr>
             <td class="table_name">证书介绍:</td>
             <td>  
                ${membercert.cert_desc?if_exists}
             </td>
           </tr>  
           <tr>
             <td class="table_name">添加时间</td>
             <td>
                <@s.label name="membercert.in_date.substring(0,19)" />
             </td>
           </tr> 
           <#if membercert.cert_state!="0">
	            <tr>
	             <td class="table_name">审核状态:</td>
	             <td>
	                 <@s.radio name="membercert.cert_state" list=r"#{'0':'审核通过','2':'审核未通过'}" onclick="getRadioValue('membercert.cert_state','certstate','noreason','2');"/>
	                 <@s.fielderror><@s.param>membercert.cert_state</@s.param></@s.fielderror>
	             </td>
	           </tr>
           </#if>
           <tr id="certstate" style="display:<#if certstateTip=='2'><#else>none</#if>">
             <td class="table_name">拒绝理由<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="membercert.no_reason" cssClass="txtinput" cssStyle="width:600px;" maxLength="100" id="noreason"/>
             	<@s.fielderror><@s.param>membercert.no_reason</@s.param></@s.fielderror>
             </td>
           </tr>     
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    		<@s.token/> 
	       <@s.hidden name="membercert.cert_id"/>
	       <@s.hidden name="membercert.cust_id"/>
	       
	       ${listSearchHiddenField?if_exists}     
	       <@s.submit value="审核"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Membercert_list.action','');"/>s
   </div>
</div>
<div class="clear"></div>
</@s.form>

  </body>
</html>