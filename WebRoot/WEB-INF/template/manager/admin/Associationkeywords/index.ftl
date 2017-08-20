<html>
  <head>
    <title>联想关键词列表</title>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","goods");
      });
	</script>
  </head>
  <body>
<@s.form action="/admin_Associationkeywords_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：网站管理 > 联想关键字 >联想关键字列表</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td>联想关键字标题:&nbsp;&nbsp;</td><td><@s.textfield name="title_s" cssStyle="width:200px" cssClass="search_input"/>&nbsp;&nbsp;</td>
                 <td><input type="submit" class="rbut" value="查询"></td>
                 <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
               </tr>
             </table>

           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('associationkeywords.ass_key_words_id')"/></th>
                 
                   <th width="5%"  align="center" >排序</th>
                
                 	<th width="30%">联想关键字标题</th>
			    
                 	<th width="15%">所属分类</th>
			    
                 	<th width="10%">前台显示</th>
			    
                 	<th width="18%">搜索时间</th>
			    
                 <th  width="5%">操作</th>
               </tr>
			    <#list associationkeywordsList as associationkeywords>
				 <tr >
			         <td><input type="checkbox" name="associationkeywords.ass_key_words_id" value="${associationkeywords.ass_key_words_id?if_exists}"/></td>
			          <td align="center"><input name="isort_no" value="${associationkeywords.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
			         	<td align="center" title="${associationkeywords.ass_key_words_title?if_exists}">
                          <a onclick="linkToInfo('/admin_Associationkeywords_view.action','associationkeywords.ass_key_words_id=${associationkeywords.ass_key_words_id?if_exists}');">
			             	  <#if associationkeywords.ass_key_words_title?if_exists!=''>
					          <#if associationkeywords.ass_key_words_title? length lt 40 >
						         ${associationkeywords.ass_key_words_title?if_exists}
					          <#else>
						          ${associationkeywords.ass_key_words_title[0..39]?if_exists}...    
					          </#if>
			                  </#if>
                          </a>
			         	</td>
			         	<td align="center">
			         	    ${associationkeywords.cat_attr?if_exists}
			         	</td>
			         	<td align="center">
			         	<#if (associationkeywords.ass_key_words_show)?if_exists=="0">
				          <font color='green'>显示</font>
				        <#else>
				           <font color='red'>隐藏</font>
				        </#if>
			         	</td>
			         	<td align="center">
			         	    ${associationkeywords.in_date?if_exists}
			         	</td>
				        <td align="center"><a onclick="linkToInfo('/admin_Associationkeywords_view.action','associationkeywords.ass_key_words_id=${associationkeywords.ass_key_words_id?if_exists}');">
					                       <img src="/include/common/images/bj.gif" /></a>
					    </td>
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Associationkeywords_add.action','');" value="添加">
             <input type="button" class="rbut" onclick="updateSort('associationkeywords.ass_key_words_id','isort_no','/admin_Associationkeywords_updateSort.action');" value="排序">
             <input type="button" class="rbut"onclick="delInfo('associationkeywords.ass_key_words_id','/admin_Associationkeywords_delete.action')" value="删除">
             <input type="button" class="rbut" onclick="updateBatchState('0','associationkeywords.ass_key_words_id','/admin_Associationkeywords_updateIsshow.action','显示');" value="显示">
             <input type="button" class="rbut" onclick="updateBatchState('1','associationkeywords.ass_key_words_id','/admin_Associationkeywords_updateUnshow.action','隐藏');" value="隐藏">
             
          </div>
        </div>
		<!--表单框隐藏域存放-->
		  <!-- <@s.hidden  name="xxx_s"/>-->
		  <@s.hidden name="ass_key_words_show_s"/>
		  <@s.hidden  name="cat_attr_s"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
<!--高级搜索开始-->
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Associationkeywords_list.action"   method="post"  id="form_search_id">
<!--dd-->
	<table>
	<tr>
		<td class="searchDiv_td">标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	 <tr>
		<td class="searchDiv_td">所属分类:</td> 
		<td>
			<div id="catDiv" ></div>
        </td>
	</tr>
	 <tr>
		<td class="searchDiv_td">是否显示:</td> 
		<td><@s.radio name="ass_key_words_show_s" list=r"#{'0':'是','1':'否'}" /> </td>
	</tr>
    <tr>
		<td align="center" colspan="2" style="border:0px;">
		<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
		   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
		</td>
	</tr>
	</table>
	        <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
</@s.form>
</div>
<!--高级搜索结束-->
  </body>
</html>

