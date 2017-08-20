<html>
  <head>
    <title>修改楼层管理</title>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script type="text/javascript" src="/include/admin/js/morecat.js"></script>	
    <link rel="stylesheet" href="/include/admin/css/morecat.css" type="text/css" />
    <script type="text/javascript">
	   $(document).ready(function(){
	     //所属分类的回选
	       loadCat("${catIdStr?if_exists}","1","goods");
	    });
	</script>	
  </head>
<body >
<@s.form action="/admin_Floorinfo_update.action"  method="post" validate="true"  id="pgform">
<div class="postion">
 当前位置：商城管理 > 楼层管理 > 修改楼层管理
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_name" width="20%">楼层标志<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="floorinfo.f_tag" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>floorinfo.f_tag</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">楼层名称<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="floorinfo.f_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>floorinfo.f_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">所属分类<font color='red'>*</font></td>
	           <td class="table_right">
	                <@s.select id="type" name="floorinfo.cat_attr"  list="catList" listValue="cat_name" listKey="cat_id" headerValue="请选择"/>
					<@s.fielderror><@s.param>floorinfo.cat_attr</@s.param></@s.fielderror>
			   </td>
			 </tr> 	
	    
			 <tr>
	           <td class="table_name">tab[0]标签<font color='red'>*</font></td>
	           <td class="table_right">
	             	  <#if floormarkList!=0>
		              <#list floormarkList as flist> 
				          <#if flist.fm_id==floorinfo.f_admark>
			        		  <input type="radio" name="floorinfo.f_admark"  checked="true"   value="${flist.fm_id }" /><font color="green">${flist.fm_name }[${flist.fm_remark }]</font>
			        	<#else>
			        	     <input type="radio" name="floorinfo.f_admark"    value="${flist.fm_id }" /><font color="green">${flist.fm_name }[${flist.fm_remark }]</font>
			        	</#if>
			          </#list>
		             </#if>
	           </td>
	        </tr>	
	         
	          <tr>
	           <td class="table_name">tab[1-4]标签<img class="ltip" src="/include/common/images/light.gif" alt="标签字体：红色[已被选],绿色[可选]"></td>
	           <td class="table_right">
	            <#if fmList!=0>
	            <#list fmList as flist> 
			        <#if flist.f_id!='0'>
			        	<#if flist.f_id!=floorinfo.f_id>
			        		 <input type="checkbox" name="fgoodsmark" disabled="disabled"   value="${flist.fm_id }" /><font color="red">${flist.fm_name }[${flist.f_tag }]</font>
			        	<#else>
			        	    <input type="checkbox" name="fgoodsmark" checked="true"   value="${flist.fm_id }" /><font color="green">${flist.fm_name  }</font>
			        	</#if>
			        <#else>
			        	 <input type="checkbox" name="fgoodsmark" value="${flist.fm_id }" /><font color="green">${flist.fm_name }</font>
			        </#if>
			          <#if (flist_index+1)%5=0><br/></#if>
		       </#list>
		       <#else>
		         <b><a href="/admin_Floormark_add.action">[添加楼层标签]</a></b>
	            </#if>
	            <@s.fielderror><@s.param>fgoodsmark</@s.param></@s.fielderror>
	           </td>
	        </tr>	
			 <tr>
	           <td class="table_name">楼层排序<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="floorinfo.f_sort" cssStyle="width:50px;"/>
	             	<img class="ltip" src="/include/common/images/light.gif" alt="格式:数字越小排的越前">
	             	<@s.fielderror><@s.param>floorinfo.f_sort</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">楼层状态<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.radio name="floorinfo.f_state" list=r"#{'0':'显示','1':'隐藏'}"/>
	             	<@s.fielderror><@s.param>floorinfo.f_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	       
	        <tr>
	           <td class="table_name">是否为系统内置<img class="ltip" src="/include/common/images/light.gif" alt="[是]选择之后不能删除,[否]选择之后可删除"><font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.radio name="floorinfo.is_sys" list=r"#{'0':'是','1':'否'}"/>
	             	<@s.fielderror><@s.param>floorinfo.is_sys</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
         <@s.token/>
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
		   <@s.hidden name="floorinfo.f_id"/>
           <@s.submit value="保存"/>
           <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Floorinfo_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

