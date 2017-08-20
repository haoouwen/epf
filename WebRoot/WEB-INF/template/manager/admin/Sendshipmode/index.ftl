<html>
  <head>
    <title>配送方式表列表</title>   
  </head>




<body>
<@s.form action="/admin_Sendshipmode_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion"> 当前位置:商城管理 > 物流管理 > 配送方式列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >配送方式名称:</td> 
			<td><@s.textfield name="smode_name_s"  cssStyle="width:200px;"/></td>
			<td class="tdpad">是否支持保价:</td> 
			<td><@s.radio name="is_insured_s" list=r"#{'1':'否','0':'是'}" /></td>
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
         <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sendshipmode.smode_id')"/></th>
    
         <th width="6%">排序</th>
         
     	 <th width="20%"  >配送方式名称</th>
     	 
     	  <th width="15%"  >快递公司代码</th>
    
     	 <th width="10%"  >是否支持保价</th>
    
     	 <th width="8%"  >保价费率</th>
    
     	 <th width="8%"  >最低保价额</th>
    
     	 <th width="8%"  >最高保价额</th>
 
    	 <th width="10%" >操作</th>
  </tr>
  
  <#list sendshipmodeList as sendshipmode>
  <tr bgcolor="<#if sendshipmode_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="sendshipmode.smode_id" value="${sendshipmode.smode_id?if_exists}"/></td>    
 	
   		 <td align="center"><input name="isort_no" value="${sendshipmode.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
   		 
    	<td align="center">
    	  <a onclick="linkToInfo('/admin_Sendshipmode_view.action','sendshipmode.smode_id=${sendshipmode.smode_id?if_exists}');">
    	${sendshipmode.smode_name?if_exists}
    	</a>
    	</td>
       <td align="center">
    	${sendshipmode.en_name?if_exists}
    	</td>

    	<td align="center">
    	<#if sendshipmode.is_insured?if_exists='0'>
	    <a href="/admin_Sendshipmode_list.action?is_insured_s=0">
	   	   <font class="greencolor">是</font>
	   </a>
	    <#elseif sendshipmode.is_insured?if_exists='1'>
	     <a href="/admin_Sendshipmode_list.action?is_insured_s=1">
	   		<font class="redcolor">否</font>
	   	</a>
    	</#if>
    	
    	</td>
    
    
    	<td align="center">${sendshipmode.rate?if_exists}</td>
    
    	<td align="center">${sendshipmode.mix_insured?if_exists}</td>
    
    	<td align="center">${sendshipmode.max_insured?if_exists}</td>
    
    <td align="center">
    
	    <a onclick="linkToInfo('/admin_Sendshipmode_view.action','sendshipmode.smode_id=${sendshipmode.smode_id?if_exists}');">
	    	<img src="/include/common/images/bj.gif" />
	    </a>
    	
    </td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Sendshipmode_add.action','');" value="添加配送方式">
     <input type="button" class="rbut" onclick="delInfo('sendshipmode.smode_id','/admin_Sendshipmode_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateSort('sendshipmode.smode_id','isort_no','/admin_Sendshipmode_updateSort.action');" value="排序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="rate_s"/>
  <@s.hidden  name="mix_insured_s"/>
  <@s.hidden  name="max_insured_s"/>
  <@s.hidden  name="reach_pay_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Sendshipmode_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">配送方式名称:</td> 
			<td><@s.textfield name="smode_name_s"  cssStyle="width:200px;"/></td>
	    </tr>
	    <tr>
			<td class="searchDiv_td">是否支持保价:</td> 
			<td><@s.radio name="is_insured_s" list=r"#{'1':'否','0':'是'}" /></td>
	    </tr>
	    <tr>
			<td class="searchDiv_td">保价费率:</td> 
			<td><@s.textfield name="rate_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
			<td class="searchDiv_td">最低保价额:</td> 
			<td><@s.textfield name="mix_insured_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
			<td class="searchDiv_td">最高保价额:</td> 
			<td><@s.textfield name="max_insured_s"  cssStyle="width:100px;"/></td>
	    </tr>
	    <tr>
			<td class="searchDiv_td">是否支持货到付款:</td> 
			<td><@s.radio name="reach_pay_s" list=r"#{'1':'否','0':'是'}"/></td>
	    </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
