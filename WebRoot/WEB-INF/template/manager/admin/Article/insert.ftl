<html>
  <head>
    <title>添加文章</title>
    <!--约束-->
    <script type="text/javascript" src="/include/common/js/common.js"></script>
	<!--所属分类开始-->
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
		<script type="text/javascript" src="/include/admin/js/adminarticle.js"></script>	
	<script charset="utf-8" src="/kindeditor/kindeditor.js"></script>
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","article");
      });
	</script>
	<!--所属分类结束-->
  </head>
  <body>
  <@s.form action="/admin_Article_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:网站管理 > 文章管理 > 添加文章
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr>
		             <td class="table_name" style="width:220px;" height="60px;">文章标题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="article.title" cssClass="txtinput"   id="article_tilte"
		             	 onkeyup="checkLength(this,60);" maxlength="60" cssStyle="width:450px;height:20px;"  onblur="checkArticleName();" />
		             	<@s.fielderror><@s.param>article.title</@s.param></@s.fielderror>
		             	<span id="ertip"></span>
		             </td>
		           </tr>
	           
	           
		           <tr>
		             <td class="table_name">所属分类<font color='red'>*</font></td>
		             <td>
         				<div id="catDiv" style="margin-left:-5px;"></div>
         				<@s.fielderror><@s.param>article.cat_attr</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">文章来源:</td>
		             <td>
		             	<@s.textfield name="article.art_source" cssClass="txtinput" onkeyup="checkLength(this,40);" maxlength="40"/>
		             	<@s.fielderror><@s.param>article.art_source</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">文章作者:</td>
		             <td>
		             	<@s.textfield name="article.art_author_" cssClass="txtinput" onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>article.art_author_</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	           
		           <tr>
		             <td class="table_name">是否置顶<font color='red'>*</font></td>
		             <td>
		             	<@s.radio name="article.is_sticky" list=r"#{'0':'是','1':'否'}"value="1"/>
		             	<@s.fielderror><@s.param>article.is_sticky</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否显示<font color='red'>*</font></td>
		             <td>
		             	<@s.radio name="article.is_display" list=r"#{'0':'是','1':'否'}"value="0"/>
		             	<@s.fielderror><@s.param>article.is_display</@s.param></@s.fielderror>
		             </td>
		           </tr>
		         <!--
	              <tr>
		             <td class="table_name">文章内容<font color='red'>*</font></td>
			         <td>
			             <@s.textarea id="content" name="article.content" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		                 <@s.fielderror><@s.param>article.content</@s.param></@s.fielderror>
							<script type="text/javascript">
						     	CKEDITOR.replace('content');  
							</script>
			             </td>
		           </tr>
		           -->
		              <tr>
		             <td class="table_name">文章内容<font color='red'>*</font></td>
			         <td>
			             <@s.textarea id="content" name="article.content" cssClass="txtinput" cssStyle="width:100%;height:400px;" onkeyup="checkLength(this,25000);" maxlength="25000"/>
			             <script>
							KindEditor.ready(function(K) {
								var editor1 = K.create('textarea[name="article.content"]', {
									uploadJson : '/kindeditor/jsp/upload_json.jsp',
									fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
									    var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
										});
									},
									afterBlur: function(){this.sync();}
								});
							});
						</script>
		                 <@s.fielderror><@s.param>article.content</@s.param></@s.fielderror>
			             </td>
		           </tr>
		           
		           
		           
		           <tr>
		             <td class="table_name">引用外部链接:</td>
		             <td>
		             	<@s.textfield name="article.out_link" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100" value="http://"cssStyle="width:300px;"/>
		             	<@s.fielderror><@s.param>article.out_link</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">SEO标题:</td>
		             <td>
		             	<@s.textarea name="article.seo_title" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100" cssStyle="width:300px;height:50px;"/>
		             	<@s.fielderror><@s.param>article.seo_title</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">SEO关键字:</td>
		             <td>
		             	<@s.textarea name="article.seo_keyword" cssClass="txtinput" onkeyup="checkLength(this,100);" maxlength="100" cssStyle="width:400px;height:80px;"/>
		             	<@s.fielderror><@s.param>article.seo_keyword</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">SEO描述:</td>
		             <td>
		                <@s.textarea name="article.seo_descri" cssClass="txtinput" maxLength="100" cssStyle="width:500px;height:100px;"onkeyup="checkLength(this,200)" maxlength="200"/>
		             	<@s.fielderror><@s.param>article.seo_descri</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
            <input type="hidden" id="b_type" value="0" />
           <input type="button" name="returnList" value="保存" onclick="articlesumit();"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Article_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

