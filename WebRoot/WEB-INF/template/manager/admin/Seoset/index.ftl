<html>
  <head>
    <title>SEO设置</title>
    <link href="/include/admin/css/seoset.css" rel="stylesheet" type="text/css">
    <script type="text/javascript"  src="/include/common/js/insert.position.js"></script>
    <script type="text/javascript"  src="/include/admin/js/seoset.js"></script>
    <script type="text/javascript" src="/include/admin/js/sysconfig.js"></script>
    <script type="text/javascript">
		$(function(){
			//tab切换页
			$("#oper_seo_div").mallTab({
			});
		})
	</script>
  </head>
<body>
<@s.form action="/admin_Seoset_insert.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：营销推广 > 搜索引擎优化 > SEO设置</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
<!--条件结束-->
<!--后台table-->
 <div id="oper_seo_div" class="oper_seo_div">      
					       <div class="tabbar">
							    <ul>
							   		<li <#if men_index==0>class="selected"</#if> onclick="getindex('0');">商城首页</li>
							   		<li <#if men_index==1>class="selected"</#if> onclick="getindex('1');">商品列表</li>
							   		<li <#if men_index==2>class="selected"</#if> onclick="getindex('2');">商品详细</li>
							   		<li <#if men_index==3>class="selected"</#if> onclick="getindex('3');">品牌列表</li>
									<li <#if men_index==4>class="selected"</#if> onclick="getindex('4');">品牌详细</li>
									<li <#if men_index==5>class="selected"</#if> onclick="getindex('5');">文章列表</li>
									<li <#if men_index==6>class="selected"</#if> onclick="getindex('6');">文章详细</li>
							    </ul>
					       </div>
					       
					       <div class="clear"></div>
					       
					       
					       <div class="tabdiv" <#if men_index==0> style="display:block"</#if> >
							<div class="showtitle"><b>商城首页</b></div>
							<#list seosetList as seo>   
						       	 <#if seo.seo_code=='index'>     
					      	  <table class="op_table" cellspacing="1" cellpadding="8"  >       
					           <tr>
					             <td class="ctd">TITLE(首页标题):</td>
					             <td>
					             	 <@s.hidden  name="code" value="index"   value="${seo.seo_code?if_exists}"/>
					             	 <@s.textarea id="index_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_index('index_title',this);" value="${seo.seo_title?if_exists}"/>
					            	<br class="clear" />
					             </td>
					           </tr>
					              
					           <tr>
					             <td class="ctd">META_KEYWORDS(关键词):</td>
					             <td>
					            	 <@s.textarea id="index_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_index('index_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
					             </td>
					           </tr>
					           
					              
					           <tr>
					             <td class="ctd">META_DESCRIPTION(页面描述):</td>
					             <td>
					            	<@s.textarea id="index_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_index('index_descri',this);" value="${seo.seo_decri?if_exists}"/>
					             </td>
					           </tr>
				              </table>
				               </#if>
					           </#list>
					       </div>
					       
					       <div class="tabdiv" <#if men_index==1> style="display:block"</#if>>
					       <div class="showtitle"><b>商品列表</b></div>
					       <#list seosetList as seo>   
						       	 <#if seo.seo_code=='goodslist'>     
					      	     <table class="op_table" cellspacing="1" cellpadding="8"  >       
					       	      
					           <tr>
					             <td class="ctd">TITLE(首页标题):</td>
					             <td>
					             	 <@s.hidden  name="code" value="goodslist"  value="${seo.seo_code?if_exists}"  />
					             	 <@s.textarea id="goods_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_goodsList('goods_title',this);" value="${seo.seo_title?if_exists}"/>
					            	<br class="clear" />
					             </td>
					           </tr>
					              
					           <tr>
					             <td class="ctd">META_KEYWORDS(关键词):</td>
					             <td>
					            	 <@s.textarea id="goods_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_goodsList('goods_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
					             </td>
					           </tr>
					           
					              
					           <tr>
					             <td class="ctd">META_DESCRIPTION(页面描述):</td>
					             <td>
					            	<@s.textarea id="goods_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_goodsList('goods_descri',this);" value="${seo.seo_decri?if_exists}"/>
					             </td>
					           </tr>
				              </table>
				               </#if>
					           </#list>
					       </div>
					       
					       <div class="tabdiv" <#if men_index==2> style="display:block"</#if>>
					       <div class="showtitle"><b>商品详细</b></div>
					       <#list seosetList as seo>   
						       	 <#if seo.seo_code=='goodsdetail'>     
					       		 <table class="op_table" cellspacing="1" cellpadding="8"  >       
					       	      
					           <tr>
					             <td class="ctd">TITLE(首页标题):</td>
					             <td>
					             	 <@s.hidden  name="code"  value="goodsdetail" value="${seo.seo_code?if_exists}"/>
					             	 <@s.textarea id="goods_detail_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_goods('goods_detail_title',this);"  value="${seo.seo_title?if_exists}"/>
					            	<br class="clear" />
					             </td>
					           </tr>
					              
					           <tr>
					             <td class="ctd">META_KEYWORDS(关键词):</td>
					             <td>
					            	 <@s.textarea id="goods_detail_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_goods('goods_detail_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
					             </td>
					           </tr>
					           
					              
					           <tr>
					             <td class="ctd">META_DESCRIPTION(页面描述):</td>
					             <td>
					            	<@s.textarea id="goods_detail_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_goods('goods_detail_descri',this);" value="${seo.seo_decri?if_exists}"/>
					             </td>
					           </tr>
				              </table>
				               </#if>
					           </#list>
					       </div>
					       <div class="tabdiv" <#if men_index==3> style="display:block"</#if>>
					       <div class="showtitle"><b>品牌列表</b></div>
					       <#list seosetList as seo>   
						       	 <#if seo.seo_code=='brandlist'>     
					       	 <table class="op_table" cellspacing="1" cellpadding="8"  >       
					       	      
					           <tr>
					             <td class="ctd">TITLE(首页标题):</td>
					             <td>
					             	 <@s.hidden  name="code"  value="brandlist"  value="${seo.seo_code?if_exists}" />
					             	 <@s.textarea id="brand_list_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_brand('brand_list_title',this);"  value="${seo.seo_title?if_exists}"/>
					            	<br class="clear" />
					             </td>
					           </tr>
					              
					           <tr>
					             <td class="ctd">META_KEYWORDS(关键词):</td>
					             <td>
					            	 <@s.textarea id="brand_list_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_brand('brand_list_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
					             </td>
					           </tr>
					           
					              
					           <tr>
					             <td class="ctd">META_DESCRIPTION(页面描述):</td>
					             <td>
					            	<@s.textarea id="brand_list_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_brand('brand_list_descri',this);" value="${seo.seo_decri?if_exists}"/>
					             </td>
					           </tr>
				              </table>
					             </#if>
					           </#list>
					       </div>
					       <div class="tabdiv" <#if men_index==4> style="display:block"</#if>>
					       <div class="showtitle"><b>品牌详细</b></div>
					       <#list seosetList as seo>   
						       	 <#if seo.seo_code=='branddetail'>     
									<table class="op_table" cellspacing="1" cellpadding="8"  >       
						       	      
						           <tr>
						             <td class="ctd">TITLE(首页标题):</td>
						             <td>
						             	 <@s.hidden  name="code"  value="branddetail"   value="${seo.seo_code?if_exists}"/>
						             	 <@s.textarea id="brand_detail_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_brand('brand_detail_title',this);"  value="${seo.seo_title?if_exists}"/>
						            	<br class="clear" />
						             </td>
						           </tr>
						              
						           <tr>
						             <td class="ctd">META_KEYWORDS(关键词):</td>
						             <td>
						            	 <@s.textarea id="brand_detail_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_brand('brand_detail_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
						             </td>
						           </tr>
						           
						              
						           <tr>
						             <td class="ctd">META_DESCRIPTION(页面描述):</td>
						             <td>
						            	<@s.textarea id="brand_detail_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_brand('brand_detail_descri',this);" value="${seo.seo_decri?if_exists}"/>
						             </td>
						           </tr>
					              </table>
					               </#if>
					           </#list>
					       </div>
					       <div class="tabdiv" <#if men_index==5> style="display:block"</#if>>
					       <div class="showtitle"><b>文章列表</b></div>
					       <#list seosetList as seo>   
						       	 <#if seo.seo_code=='articlelist'>     
					            <table class="op_table" cellspacing="1" cellpadding="8"  >       
					       	      
					           <tr>
					             <td class="ctd">TITLE(首页标题):</td>
					             <td>
					             	 <@s.hidden  name="code"  value="articlelist" value="${seo.seo_code?if_exists}"/>
					             	 <@s.textarea id="article_list_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_articleList('article_list_title',this);"  value="${seo.seo_title?if_exists}"/>
					            	<br class="clear" />
					             </td>
					           </tr>
					              
					           <tr>
					             <td class="ctd">META_KEYWORDS(关键词):</td>
					             <td>
					            	 <@s.textarea id="article_list_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_articleList('article_list_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
					             </td>
					           </tr>
					           
					              
					           <tr>
					             <td class="ctd">META_DESCRIPTION(页面描述):</td>
					             <td>
					            	<@s.textarea id="article_list_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_articleList('article_list_descri',this);" value="${seo.seo_decri?if_exists}"/>
					             </td>
					           </tr>
				              </table>
				               </#if>
					           </#list>
					       </div>
					       <div class="tabdiv" <#if men_index==6> style="display:block"</#if>>
					       <div class="showtitle"><b>文章详细</b></div>
					       <#list seosetList as seo>   
						       	 <#if seo.seo_code=='articledetail'>     
					            <table class="op_table" cellspacing="1" cellpadding="8"  >       
					       	      
					           <tr>
					             <td class="ctd">TITLE(首页标题):</td>
					             <td>
					             	 <@s.hidden  name="code"  value="articledetail"  value="${seo.seo_code?if_exists}" />
					             	 <@s.textarea id="article_detail_title" name="title" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;" onclick="set_article('article_detail_title',this);"  value="${seo.seo_title?if_exists}"/>
					            	<br class="clear" />
					             </td>
					           </tr>
					              
					           <tr>
					             <td class="ctd">META_KEYWORDS(关键词):</td>
					             <td>
					            	 <@s.textarea id="article_detail_keyword" name="keyword" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_article('article_detail_keyword',this);"  value="${seo.seo_keyword?if_exists}"/>
					             </td>
					           </tr>
					           
					              
					           <tr>
					             <td class="ctd">META_DESCRIPTION(页面描述):</td>
					             <td>
					            	<@s.textarea id="article_detail_descri" name="descri" cssClass="input" onkeyup="checkLength(this,200);" maxlength="200" cssStyle="width:430px;"  onclick="set_article('article_detail_descri',this);" value="${seo.seo_decri?if_exists}"/>
					             </td>
					           </tr>
				              </table>
				               </#if>
					           </#list>
					       </div>
  </div>
<!--后台table结束-->
<!--翻页-->
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
   	   <@s.token/>
	   <@s.hidden name="men_index" id="men_index" />
	   <@s.submit  value="保存"/> 
	   <@s.reset   value="重置" style="cursor:pointer;"/>
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

