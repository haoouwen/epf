<html>
  <head>
    <title>会员级别</title>   
  </head>
<body>
<@s.form action="/admin_Malllevelset_buylist.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 级别管理 > 会员级别</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td align="right">级别名称:</td> 
			<td><@s.textfield name="level_name_s"  cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >

	     	 <th width="10%">级别编码</th>
	    
	     	 <th width="10%">级别名称</th>
	    
	     	 <th width="20%"  colspan="2">成长值范围</th>
	     	  
	     	 <th width="10%">折扣率</th>    
	     	     
	     	 <th width="12%">级别图标</th>
	     	 
	     	 <th width="10%">会员有效期（月数）</th>
	     	 
	     	 <th width="12%">扣除成长值</th>
	    
	   		 <th width="7%">操作</th>
	  </tr>
	  
	  <#list malllevelsetList as malllevelset>
		  <tr>
		 	
		    	<td align="center">${malllevelset.level_code?if_exists}</td>
		    
		    	<td align="center">${malllevelset.level_name?if_exists}</td>
		    
		    	
		    
		    	<td colspan='2' align="center">
		    	  <#if (malllevelset.inter_lower)?if_exists=='0' && (malllevelset.inter_height)?if_exists=='0'>
			                   无
			      <#else>
			           ${(malllevelset.inter_lower)?if_exists}&nbsp;&sim;&nbsp;
			           <#if (malllevelset.inter_height)?if_exists=='999999999'>
			    	         无上限
			    	  <#else>
			    	      ${malllevelset.inter_height?if_exists}
			    	  </#if>
			      </#if> 
		    	    
		       </td>
		       
		       <td align="center">${malllevelset.discount?if_exists}%</td>
		       
		    	<td align="center"><img  src="${malllevelset.img_url?if_exists}" /></td>
		    	
		    	<td align="center">${malllevelset.validity_period?if_exists}</td>
		    	
		    	<td align="center">${malllevelset.dedu_growth_value?if_exists}</td>
		    
		    <td align="center">
		    
		        <a onclick="linkToInfo('/admin_Malllevelset_buyview.action','malllevelset.level_code=${malllevelset.level_code?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
		       	<!--<a href="javascript:delOneInfo('chb_id','/admin_Malllevelset_buydelete.action','${(malllevelset.level_code)?if_exists}');"><img src="/include/admin/images/delete.png" border="0"/></a>-->
		       
		    
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
   <!--<div class="bsbut">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Malllevelset_buyadd.action','');" value="添加会员级别">
   </div>
   -->
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>

