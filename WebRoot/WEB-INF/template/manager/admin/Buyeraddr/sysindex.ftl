<html>
  <head>
    <title>退/换地址</title>   			
	<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>	 
	 <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
     <script type="text/javascript">
	  $(document).ready(function(){ 
         //所属地区的回选
         loadArea("${areaIdStr?if_exists}","","");
	  });
	</script>
  </head>
<body>
<@s.form action="/admin_Buyeraddr_sysList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 配送管理 > 退/换地址</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>联系人姓名:</td>
			<td><@s.textfield name="consignee_s"  cssStyle="width:245px;"/></td>
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
	         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('buyeraddr.addr_id')"/></th>

             <th width="6%"  align="center" >联系人</th>
               
	     	 <th width="10%"  align="center" >所在区域</th>
	    
	         <th width="20%"  align="center" >详细地址</th>
	    
	     	 <th width="6%"  align="center" >手机号码</th>
	    
	     	 <th width="6%"  align="center" >固定电话</th>
	    
	     	 <th width="6%"  align="center" >邮政编码</th>
	    
	         <th width="5%" align="center" >操作</th>
	  </tr>
	  
	 <#list buyeraddrList as buyeraddr>
	  <tr>
	    <td><input type="checkbox" name="buyeraddr.addr_id" value="${buyeraddr.addr_id?if_exists}"/></td>    
	 	
	 	    <td align="center">
	    		<#if buyeraddr.consignee?if_exists!=null && buyeraddr.consignee?if_exists!=''>
		    		${buyeraddr.consignee?if_exists}<#if buyeraddr.sel_address?if_exists=='0'><font color='red'>(默认)</font></#if>
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	 	
	    	<td align="center">
	    	<#if buyeraddr.area_attr?if_exists!=null && buyeraddr.area_attr?if_exists!=''>
		    		${buyeraddr.area_attr?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	    	
	    	<td align="center">
	    	<#if buyeraddr.address?if_exists!=null && buyeraddr.address?if_exists!=''>
		    		${buyeraddr.address?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>

            <td align="center">
	    	<#if buyeraddr.cell_phone?if_exists!=null && buyeraddr.cell_phone?if_exists!=''>
		    		${buyeraddr.cell_phone?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
             
	    	<td align="center">
	    	<#if buyeraddr.phone?if_exists!=null && buyeraddr.phone?if_exists!=''>
		    		${buyeraddr.phone?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	    
	    	<td align="center">
	    	<#if buyeraddr.post_code?if_exists!=null && buyeraddr.post_code?if_exists!=''>
		    		${buyeraddr.post_code?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	          
	    <td align="center"><a onclick="linkToInfo('/admin_Buyeraddr_sysView.action','buyeraddr.addr_id=${buyeraddr.addr_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Buyeraddr_sysAdd.action','');" value="添加">
     <input type="button" class="rbut" onclick="delInfo('buyeraddr.addr_id','/admin_Buyeraddr_sysDelete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cell_phone_s"/>
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden  name="area_attr_s"/>
  <@s.hidden name="cust_id_s" />
 <!--表单框隐藏域存放-->  
</@s.form>
<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Buyeraddr_sysList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">联系人姓名:</td> 
			<td><@s.textfield name="consignee_s" cssStyle="width:226px;"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">所在区域:</td> 
			<td><div id="areaDiv" ></div></td>
		</tr>
				<tr>
			<td class="searchDiv_td">手机号码:</td> 
			<td><@s.textfield name="cell_phone_s" cssStyle="width:226px;" /></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
			<@s.hidden name="cust_id_s" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>

