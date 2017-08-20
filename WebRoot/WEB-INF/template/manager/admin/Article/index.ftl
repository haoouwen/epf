<html>
  <head>
    <title>文章列表</title>   
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>	 
    <!--所属分类开始-->
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","article");
      });
	</script>
	<!--所属分类结束-->
  </head>
<body>
<@s.form action="/admin_Article_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 文章管理 > 文章列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td class="tdpad">标题:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:200px;"/></td>
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
         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('article.article_id')"/></th>
     	 <th width="33%"  >文章标题</th>
     	 <th width="25%"  >所属分类</th>
     	 <th width="10%"  >是否置顶</th>
     	 <th width="10%"  >是否显示</th>
     	 <th width="15%"  >发布时间</th>
         <th width="5%" >操作</th>
	  </tr>
	  
<#list articleList as article>
  <tr>
    <td><input type="checkbox" name="article.article_id" value="${article.article_id?if_exists}"/></td>    
 	
    
    	<td align="leftr">
    	<a onclick="linkToInfo('/admin_Article_view.action','article.article_id=${article.article_id?if_exists}');">
              <#if article.title?if_exists!=''>
					<#if article.title? length lt 40 >
						${article.title?if_exists}
					<#else>
						${article.title[0..39]?if_exists}...    
					</#if>
			</#if>
			</a>
        </td>
    
    	<td align="center">${article.cat_attr?if_exists}</td>
    
    	<td align="center">
			<#if article.is_sticky?if_exists=='0'>
			<font color='green'>是</font>
			<#else>
			<font color='red'>否</font>
			</#if>
    	
    	</td>
    
    	<td align="center">
    	    <#if article.is_display?if_exists=='0'>
			<font color='green'>是</font>
			<#else>
			<font color='red'>否</font>
			</#if>
    	</td>
    	<td align="center">${article.in_date?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Article_view.action','article.article_id=${article.article_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Article_add.action','');" value="添加">
     <input type="button" class="rbut"onclick="delInfo('article.article_id','/admin_Article_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden  name="is_sticky_s"/>
  <@s.hidden  name="is_display_s"/>	
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Article_list.action" method="post"  id="form_search_id">
	<table width="100%" border="0" cellspacing="0">
    <tr>
		<td class="searchDiv_td">作者:</td> 
		<td><@s.textfield name="art_author_s"  cssStyle="width:226px;"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">标题:</td> 
		<td><@s.textfield name="title_s" cssStyle="width:226px;"/></td>
	</tr>
	<tr>
		<td class="searchDiv_td">所属分类:</td> 
		<td>
          <div id="catDiv"></div>
        </td>
	</tr>
	<tr>
		<td class="searchDiv_td">是否显示:</td> 
		<td><@s.radio name="is_display_s" list=r"#{'0':'是','1':'否'}" /> </td>
	</tr>
	<tr>
		<td class="searchDiv_td">是否置顶:</td> 
		<td><@s.radio name="is_sticky_s" list=r"#{'0':'是','1':'否'}" /> </td>
   </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('','cat_attr','','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>

