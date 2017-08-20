<html>
  <head>
    <title>修改品牌</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/common/js/pinyin.js"></script>
    <script type="text/javascript" src="include/common/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript" src="/include/admin/js/morecat.js"></script>	
	<link rel="stylesheet" href="/include/admin/css/morecat.css" type="text/css" />
	<script type="text/javascript" src="/include/admin/js/category.js"></script>
	<script type="text/javascript" src="/include/admin/js/adminbrand.js"></script>	
	<script type="text/javascript">
	  $(document).ready(function(){
	     //加载分类
		  loadCat("${catIdStr?if_exists}","","","goods");
	  });
	</script> 
  </head>
  <body>
<@s.form action="/admin_Goodsbrand_update.action" method="post" validate="true" id="gpform" >
<div class="postion">
  <!--当前位置-->
  	当前位置:商品管理 > 品牌管理 > 修改品牌
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
		             <td class="table_name" style="width:220px;" height="60px;">品牌名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsbrand.brand_name" cssClass="txtinput" maxLength="30" id="brand_name" cssStyle="width:300px;"  onblur="checkBrandName();"   />
		             	<@s.fielderror><@s.param>goodsbrand.brand_name</@s.param></@s.fielderror>
		             	<span id="ertip"></span>
		             </td>
		           </tr>
		           <script type="text/javascript">      
			              var brand_value=$('#brand_name').val(); 
			              $('#brand_name').bind('keyup', function(){   
				              var brand_value=$('#brand_name').val();   
				              var getword=Turnpingyin(brand_value);
				              var en_word="";
				              if(getword.length>50){
				              	 en_word=getword.substring(0,49);
							  }else{
							     en_word=getword;
							  }
				              var word=getword.substring(0,1);            
				              $('#en_name').val(en_word);
				              $('#en_index').val(word);
			              })         
			           </script>  
		             <td class="table_name">英文名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="en_name" name="goodsbrand.en_name" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
		             	<@s.fielderror><@s.param>goodsbrand.en_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">字母索引<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="en_index" name="goodsbrand.en_index" cssClass="txtinput" maxLength="10" cssStyle="width:50px;" readonly="true"/>【自动生成,用于前台品牌搜索】
		             	<@s.fielderror><@s.param>goodsbrand.en_index</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">品牌logo:</td>
		             <td>
		               <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			  <@s.textfield name="goodsbrand.logo" id="uploadresult" cssStyle="width:300px;"maxLength="100"readonly="true"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
		              			<script>uploadOneImg();</script>
	             			</td>
	             			<td style="padding-left:3px;">
		              			<script>uploadOneImg();</script>【建议图片宽140px,高70px】
	             			</td>
	             		</tr>
	             	</table>  
		             	<@s.fielderror><@s.param>goodsbrand.logo</@s.param></@s.fielderror>
		             </td>
		           </tr>

		           <tr>
		             <td class="table_name">商品分类串关联:</td>
		             <td>
						 <@s.hidden type="hidden" id="cat_attr_str" name="cat_attr_str"/>
						 <div class="morecatdiv">
						     <table border="0" cellpadding="0" cellspacing="0">
						     	<tr>
						         	<td  colspan="2" class="tdbottom">
						         		<div id="catDiv" style="margin-left:-5px;"></div>
						         	</td>
						     	   <td class="tdbottom" >
						         	   <a class="oper_add" href="###" onclick="com_add_cat()" >[新增分类]</a>
						         	   <@s.fielderror><@s.param>goodsbrand.goods_attr</@s.param></@s.fielderror>
						     	   </td>
						     	</tr>
						     </table>	
						     
						     <#if cat_attr_list?if_exists?size gt 0>
						         <div id="show_add_cat" class="show_add_cat">
						             <#list cat_attr_list as cat>
						                  <div style='line-height:20px;'>
						                      <input type='hidden' name='all_cat_id_str' value="${cat.id?if_exists} "/>${cat.val?if_exists} 
						                      <a class='oper' href='###' onclick='del_add_cat(this)'>[删除]</a>
						                  </div>
						             </#list>
						         </div>
						     </#if>
						     
						 </div>
		          
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="goodsbrand.sort_no" cssClass="txtinput" maxLength="11"  cssStyle="width:80px;" onkeyup="checkNum(this);"/>
		             	<@s.fielderror><@s.param>goodsbrand.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">状态:</td>
		             <td>
			             <#list infoStateList as infoState>
							<#if infoState.para_value?if_exists='1'>
							   <input type="radio" name="goodsbrand.info_state" value="${infoState.para_value?if_exists}" onclick="showTr(this)" <#if goodsbrand.info_state='1'>checked</#if>>${infoState.para_key?if_exists}
						    <#elseif infoState.para_value?if_exists='3'>
						       <input type="radio" name="goodsbrand.info_state" value="${infoState.para_value?if_exists}" onclick="showTr(this)" <#if goodsbrand.info_state='3'>checked</#if>>${infoState.para_key?if_exists}
						    </#if>
					      </#list>
           			  <@s.fielderror><@s.param>goodsbrand.info_state</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		        
		            <tr  style="display:none;">
		             <td class="table_name">详细说明:</td>
		             <td>
		             <@s.textarea name="goodsbrand.content" id="content" onkeyup="checkLength(this,800);" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
					<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
					<script type="text/javascript">
				     var editor=CKEDITOR.replace( 'content');  
					</script>
		            <@s.fielderror><@s.param>goodsbrand.content</@s.param></@s.fielderror>
		            <#include "/WEB-INF/template/manager/admin/AutoFCK/fck.ftl">
		             </td>
		           </tr>
		             <tr  style="display:none;">
                    <td class="table_name">SEO标题:</td>
		             <td>
		             		<@s.textarea id="" name="goodsbrand.seo_title" cssClass="mailCss"  onkeyup="checkLength(this,100);" />
		             		<@s.fielderror><@s.param>goodsbrand.seo_title</@s.param></@s.fielderror> 
		             </td>
                </tr>
                <tr  style="display:none;">
                    <td class="table_name">SEO关键字:</td>
		             <td>
		             		<@s.textarea name="goodsbrand.seo_keyword" cssClass="mailCss"  onkeyup="checkLength(this,100);" />
		             		<@s.fielderror><@s.param>goodsbrand.seo_keyword</@s.param></@s.fielderror> 
		             </td>
                </tr>
                <tr   style="display:none;">
                    <td class="table_name">SEO描述:</td>
		             <td>
		             		<@s.textarea name="goodsbrand.seo_descri" cssClass="mailCss"  onkeyup="checkLength(this,100);" />
		             		<@s.fielderror><@s.param>goodsbrand.seo_descri</@s.param></@s.fielderror> 
		             </td>
                </tr>
                  <tr>
	             <td class="table_name">系统提示:</td>
	             <td>
	             	<span><img class='ltip' src="/include/common/images/light.gif" alt="编辑保存后,请点击更新缓存" />
	             	[编辑保存后,请点击<a href="###" onclick="renewload();"><font color="red">更新缓存</font></a>]</span>
	             </td>
	           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
   		   <@s.token/>    
	       ${listSearchHiddenField?if_exists}
	        <@s.hidden name="goodsbrand.brand_id"  id="b_id"/>
	        <@s.hidden name="goodsbrand.is_recom" value="0"/>
	        <input type="hidden" id="b_type" value="1" />
	          <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
           <input type="button" name="returnList" value="保存" onclick="brandsumit();"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsbrand_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>

</body>
</html>

