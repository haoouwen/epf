<html>
  <head>
    <title>添加审核模型信息</title>
    <script type="text/javascript" src="/include/admin/js/auditmodel.js"></script>	
      <script type="text/javascript">
		  $(document).ready(function(){
	         $("#com_insert").submit(function(){
	              var mem_attr_str="";
	              $("input:hidden[name='all_member_id_str']").each(function(){
	                 var strtext=$(this).attr("id");//获取用户名
	                 strtext=strtext.substring(7,strtext.length);
	                 var strid=$(this).val();//获取用户ID
	                  mem_attr_str+=strid+","+strtext+"|";
	              }) 
	              if(mem_attr_str!=null&&mem_attr_str!=""){
	                mem_attr_str=mem_attr_str.substring(0,mem_attr_str.length-1);
	              }
	              $("#sel_mem_str").val(mem_attr_str); 
	              return true;
	         }); 
	         removeupanddown();
		  });
	  </script>
    </head>
  <body>
  <@s.form action="/admin_Auditmodel_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:系统管理 > 系统设置 > 添加审核模型
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr >
             <td class="table_name" width="20%">模型类型<font color='red'>*</font></td>
             <td>
	             <@s.select name="auditmodel.model_type" list="auditmodeltypeList"   listValue="para_key"  listKey="para_value"   headerKey="0" headerValue="请选择"/>
	             <@s.fielderror><@s.param>auditmodel.model_type</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
	            <td class="table_name">审核人员<font color='red'> *</font></td>
	             <td colspan="3">
	             	<div>
	             	 	<div id="show_add_mem">
	             	 	    <#assign allcount=0>
	             	 	     <#assign allcount=mem_count>
	             	 	     <#assign count=1>
		                     <#list sel_member_list as smem>
		                         <#if smem.val!=' '>
		                          <div style='line-height:20px;' id='div_${count?if_exists}'>
	                              <span style='width:160px;display:inline-block;'>[${count?if_exists}].
		                          ${(smem.val)?if_exists} &nbsp;&nbsp;&nbsp;&nbsp;</span>
		                          
				   		     	 <a style='width:14px;display:inline-block;margin-right: 0px;*margin-right: 5px;'  href='###'  onclick='up_member_opt(${count?if_exists})'>
			   		     	      <img title='向上排序' class='img_up' <#if smem_index==0>style="display:none;"</#if>
				   		     	   id='img_up_${count?if_exists}' src='/include/admin/images/upmem.jpg' />
					   		     	 </a>
					   		     	 	 
					   		     	 <a style='width:14px;display:inline-block;margin-right: 0px;*margin-right: 5px;'  href='###' onclick='down_member_opt(${count?if_exists})'>
					   		     	 <img title='向下排序' class='img_down' <#if (smem_index+1)==(allcount)>style="display:none;"</#if>
					   		     	   id='img_down_${count?if_exists}'  src='/include/admin/images/downmem.jpg' />
					   		     	 </a>

					   		     	 <a  style='width:14px;display:inline-block;margin-right: 0px;*margin-right: 5px;'  href='###' onclick="del_add_mem(this,'${count?if_exists}')"><img title='删除' src='/include/admin/images/del_mem.jpg' /></a>
					   		     	 <input type='hidden' name='all_member_id_str'  id="hidden_${(smem.val)?if_exists}" value="${(smem.id)?if_exists}"/>
			                          </div>
			                          <#assign count=count+1>
			                         </#if>
			                     </#list>
			                 </div>
			                 <table border="0" cellpadding="0" cellspacing="0" style='padding-top:10px;'>
			                 	<tr>
			                 	<td  colspan="2" class="tdbottom">
			                 	 <@s.select  list="sysuserList"  id="sel_mem_id"
		                		 listValue="user_name" listKey="user_id" headerKey="0" headerValue="请选择"/>&nbsp;
			                 	</td>
			                 	<td class="tdbottom" ><a class="oper_add" href="###" onclick="add_audit_member()">
			                 	<input type='button' value="点击添加"/></a><@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>   
			                 	</td></tr>
			                 </table>		            
				         </div>
				         <input type="hidden" id="sel_mem_str" name="sel_mem_str" value="${sel_mem_str?if_exists}">
				         <input type="hidden" id="mem_count" value="${mem_count?if_exists}" name="mem_count"  />
				         <@s.fielderror><@s.param>mem_count</@s.param></@s.fielderror>   
		             </td>
		</tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
   <@s.token/>    
   ${listSearchHiddenField?if_exists}
   <@s.submit value="保存" />
   <@s.reset value="重置"/>
   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Auditmodel_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

