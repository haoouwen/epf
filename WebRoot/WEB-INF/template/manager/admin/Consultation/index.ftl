<html>
  <head>
    <title>商品咨询</title>   
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  </head>
<body>
<@s.form action="/admin_Consultation_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 商品管理 > 商品咨询列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>咨询类型:</td> 
			<td><@s.select name="c_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
			
			<td class="tdpad">是否有效:</td>
			<td><@s.select name="is_display_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/> </td>
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
	         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('consultation.trade_id')"/></th>
	    
	     	 <th width="32%"  align="center" >咨询商品</th>
	    
	     	 <th width="8%"  align="center" >咨询类型</th>
	     	 
	     	 <th width="9%"  align="center" >咨询人</th>
	    
	     	 <th width="12%"  align="center" >咨询时间</th>
	    
	     	 <th width="10%"  align="center" >回复人</th>
	    
	     	 <th width="12%"  align="center" >回复时间</th>
	    
	     	 <th width="9%"  align="center" >是否有效</th>
	    
	         <th width="5%" align="center" >操作</th>
		</tr>
	  
		 <#list consultationList as consultation>
		  <tr>
		    <td><input type="checkbox" name="consultation.trade_id" value="${consultation.trade_id?if_exists}"/></td>    
		 	
		    	<td align="center">
		    	   <#if consultation.goods_name?if_exists!=null && consultation.goods_name?if_exists!=''>
					    		<a target='_blank' href="/mall-goodsdetail-${consultation.goods_id?if_exists}.html"> ${consultation.goods_name?if_exists}</a>
					    	<#else>
					    	 	该商品已下架
					    	</#if>
		    	
		    	</td>
		    
		    	<td align="center">${consultation.c_type?if_exists}</td>
		    
		    	<td align="center">
		    	          <#if consultation.cust_name?if_exists!=null && consultation.cust_name?if_exists!=''>
					    	  ${consultation.cust_name?if_exists}
					    	<#else>
					    	 	-
					    	</#if>
		    	
		    	</td>
		    
		    	<td align="center">${consultation.c_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		    
		        <td align="center">
		        <#if consultation.re_date?if_exists!=null && consultation.re_date?if_exists!=''>${consultation.user_name?if_exists}<#else>还没有回复！</#if>
		        </td>
		    
		    	<td align="center">
		    	            <#if consultation.re_date?if_exists!=null && consultation.re_date?if_exists!=''>
					    		 ${consultation.re_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}
					    	<#else>
					    	 	-
					    	</#if>
		    	
		    	</td>
		    	<td align="center">
		    	<#if consultation.is_display?if_exists=='0'><font color="green">有效</font><#else><font color="red">无效</font></#if>
		    	</td>  
		    <td align="center"><a onclick="linkToInfo('/admin_Consultation_view.action','consultation.trade_id=${consultation.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="delInfo('consultation.trade_id','/admin_Consultation_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="start_time_s"/>
  <@s.hidden  name="end_time_s"/>  
  <@s.hidden  name="rstart_time_s"/>
  <@s.hidden  name="rend_time_s"/>      
  <@s.hidden  name="goods_id_s"/>   
    <@s.hidden  name="daiId"/>    
  
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Consultation_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	    <tr>
			<td class="searchDiv_td">咨询类型:</td> 
			<td><@s.select name="c_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
	    </tr>
	  
	    <tr>
			<td class="searchDiv_td">是否有效:</td>
			<td><@s.select name="is_display_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/> </td>
	    </tr>
	    
	    <tr>
			    <td class="searchDiv_td">是否回复:</td>
			   <td><@s.select name="daiId" list=r"#{'':'请选择','1':'已回复','0':'未回复'}"/> </td>
		</tr>
        <tr>
			<td class="searchDiv_td">留言时间段:</td>
			<td>
			<@s.textfield id="txtstartdate" name="start_time_s"  type="text" cssStyle="widtd:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:0})}',readOnly:true})"  /> 
		   至
			<@s.textfield id="txtendDate" name="end_time_s" cssStyle="widtd:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartdate\\',{d:0})}',readOnly:true})"  />
			
			</td>
	   </tr>	 
	   <tr>
			<td class="searchDiv_td">回复时间段:</td>
			<td>
			<@s.textfield id="txtstartdate" name="rstart_time_s"  type="text" cssStyle="widtd:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		   至
			<@s.textfield id="txtendDate" name="rend_time_s" cssStyle="widtd:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartdate\\',{d:1})}',readOnly:true})"  />
			
			</td>
	    </tr>	 
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
			<@s.hidden  name="goods_id_s"/>   
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>


