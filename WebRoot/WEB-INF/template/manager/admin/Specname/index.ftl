<html>
  <head>
    <title>规格列表</title>  
     <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	 <script type="text/javascript">
	  $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	   });
     
    </script> 
  </head>
<body>
<@s.form action="/admin_Specname_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商品管理 > 规格管理 > 规格列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>规格名称:</td> 
			<td><@s.textfield name="spe_name"  cssStyle="width:245px;"/></td>
            <td><input type="submit" class="rbut" value="查询"></td>
            <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		     <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('specname.spec_code')"/> </th>
   
	     	 <th width="10%"  >规格名称</th>
	    
	     	 <th width="10%"  >规格别名</th>
	    
	     	 <th width="10%"  >显示类型</th>
	     	 
	     	 <th width="10%"   style="display:none;">显示方式</th>
	     	 
	     	 <th width="20%"  >所属类目</th>
	    
	     	 <th width="25%"  >规格值</th>
	    
	   		 <th width="10%" >操作</th>
  </tr>
  
  <#list specnameList as specname>
  <tr>
    <td align="center"><input type="checkbox" name="specname.spec_code" value="${specname.spec_code?if_exists}"/></td>    
 	
    	<td align="left">
	    	<#if specname.sname!=null>
	    		${specname.sname?if_exists}
	    	<#else>
	    		-
	    	</#if>
	    	
	    	<#if specname.snote!=null>
	    		<img class="ltip" alt="${specname.snote?if_exists}" src="/include/common/images/light.gif">
	    	</#if>
    	</td>
       
    	<td align="left">
    		<#if specname.salias!=null>
	    		${specname.salias?if_exists}
	    	<#else>
	    		-
	    	</#if>
    	</td>
    
    	<td align="center">
    	<#if specname.show_type!=null>
    			<#if specname.show_type=='0'>
    				<font color='red'>文字</font>
    			<#else>
    				<font color='green'>图片</font>
    			</#if>
	    	<#else>
	    		-
	    	</#if>
    	</td>
    
    	<td align="left" style="display:none;">
    		<#if specname.show_method!=null>
	    		<#if specname.show_method=='0'>
    				<font color='red'>平铺</font>
    			<#else>
    				<font color='green'>下拉</font>
    			</#if>
	    	<#else>
	    		-
	    	</#if>
    	</td>
    	
    	 <td align="left" style="line-height:18px;">
    		<#if specname.cat_attr!=null>
    			<#if specname.cat_attr ?length lt 20>
	    			${specname.cat_attr?if_exists}
	    		<#else>
	    			${specname.cat_attr[0..19]?if_exists}...
	    		</#if>
	    	<#else>
	    		-
	    	</#if>
    	</td>
    
    	<td align="left">
    		<#assign   count=0>
           <#list specsizevalueList as specsecond> 
                 <#if specsecond.spec_code==specname.spec_code>  
                      ${specsecond.sv_name?if_exists} |
                      <#assign   count=count+1>
            	</#if>
            	<#if count gt 6>...<#break></#if>
           </#list>			
        </td>
        
          
    <td align="center"><a onclick="linkToInfo('/admin_Specname_view.action','specname.spec_code=${specname.spec_code?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
  </tr>
  </#list>
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Specname_add.action','');" value="添加规格">
     <input type="button" class="rbut" onclick="delInfo('specname.spec_code','/admin_Specname_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden   name="cat_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Specname_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
			
			<tr>
				<td class="searchDiv_td">规格名称:</td>
				<td><@s.textfield name="spe_name"  /></td>
			</tr>
			
			<tr>
				<td class="searchDiv_td" whidth="80px">所属分类:</td>
				<td width="300px">
			          	<div id="catDiv" ></div>
	         	</td>
			</tr>
			<tr>
				<td align="center" colspan="2" style="border:0px;">
					<input type="button" name="search" value="搜索" onclick="showSearchDiv('','cat_attr','','search_cat_attr','form_search_id');"/>&nbsp;
				<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
				</td>
		   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr"  name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
