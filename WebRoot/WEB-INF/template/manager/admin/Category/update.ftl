<html>
  <head>
    <title>修改分类</title>
    <#include "/include/uploadInc.html">
<script src="/wro/admin_category_update.js" type="text/javascript"></script>
	<script type="text/javascript">
	  $(document).ready(function(){
	     //加载分类
		  loadCat("${catIdStr?if_exists}","","","goods");
         $("#gpform").submit(function(){
              var cat_attr_str="";
              $("input:hidden[name='all_cat_id_str']").each(function(){
                  cat_attr_str+=$(this).val()+"|";
              }) 
              $("#cat_attr_str").val(cat_attr_str);        
              return true;
         }); 
             
	  });
	  
	</script> 
  </head>
  <body>
  <@s.form action="/admin_Category_update.action" method="post" validate="true" id="gpform"> 
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 分类管理 >修改${modules?if_exists}分类
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
           <tr>
             <td width="19%" class="table_name">分类名称df<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="cat_name" name="category.cat_name" cssClass="txtinput" maxLength="40" />
             	<@s.fielderror><@s.param>category.cat_name</@s.param></@s.fielderror>                                   
             </td>
           </tr>
            <script type="text/javascript">      
              var cat_value=$('#cat_name').val(); 
              $('#cat_name').bind('keyup', function(){   
	              var cat_value=$('#cat_name').val();   
	              var getword=Turnpingyin(cat_value)
	              var word=getword.substring(0,1);            
	              $('#en_name').val(getword);
	              $('#word_index').val(word);
              })         
            </script> 
           <tr>
             <td class="table_name">分类拼音名<font color='red'>*</font></td>
             <td>
             	<@s.textfield  id="en_name" name="category.en_name" cssClass="txtinput" maxLength="150" /> 
                <@s.fielderror><@s.param>category.en_name</@s.param></@s.fielderror> 
             </td>
           </tr>
           
            <td class="table_name">分类图片:</td>
             <td colspan="3">
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="category.img_ico" id="uploadresult" cssStyle="width:200px;"/>
                        </td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>category.img_ico</@s.param></@s.fielderror>
             			   【图片宽200px,高200px】
             			</td>
             		</tr>
             	</table>
             </td>
           
           
           <tr>
             <td class="table_name">字母索引<font color='red'>*</font></td>
             <td>
             	<@s.textfield id="word_index" name="category.word_index" cssClass="txtinput" maxLength="1" readonly="true"/>
             	 <@s.hidden name="category.up_cat_id" cssClass="input" />
                <@s.fielderror><@s.param>category.word_index</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">上级分类名称:</td>
             <td>                
                  ${up_cat_name?if_exists}
                   <@s.fielderror><@s.param>category.up_cat_name</@s.param></@s.fielderror> 
             </td>
           </tr>
           <tr>
             <td class="table_name">分类级别:</td>
             <td>                
             		${category.cat_level?if_exists}级
                   <@s.hidden name="category.cat_level" cssClass="input" />
	             	<@s.fielderror><@s.param>category.cat_level</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">是否显示:</td>
             <td>
             	<@s.radio name="category.is_display" list=r"#{'1':'全部显示','2':'分类导航不显示','0':'全部不显示'}"  checked="true" />
             	<@s.fielderror><@s.param>category.is_display</@s.param></@s.fielderror> 
             </td>
           </tr> 
           <tr>
             <td class="table_name">分类属性:</td>
             <td>
             	<@s.radio name="category.cat_attribute" list=r"#{'0':'不选','1':'热门分类'}"  checked="true" />
             	<@s.fielderror><@s.param>category.cat_attribute</@s.param></@s.fielderror> 
             </td>
           </tr> 
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield  id="sort_no"  name="category.sort_no" cssStyle="width:50px;" maxLength="6" onkeyup="checkNum(this);"/>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
                <@s.fielderror><@s.param>category.sort_no</@s.param></@s.fielderror>  
             </td>
           </tr>  
           <tr  style="display:none;">
             <td class="table_name">点击量:</td>
             <td>                
				<@s.textfield name="category.cat_click" cssClass="txtinput"  cssStyle="width:180px;" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>category.cat_click</@s.param></@s.fielderror>
             </td>
           </tr>          
           <tr  style="display:none;">
             <td class="table_name">是否允许会员发布:</td>
             <td>
             	<@s.radio name="category.member_add" list=r"#{'0':'允许','1':'禁止'}"  checked="true" />
             	<@s.fielderror><@s.param>category.member_add</@s.param></@s.fielderror> 
             </td>
           </tr>       
           <tr  style="display:none;">
             <td class="table_name">会员类型:</td>
             <td>
             	<@s.radio name="category.mem_type" list=r"#{'2':'不限','0':'企业','1':'个人'}"  />
             	<@s.fielderror><@s.param>category.mem_type</@s.param></@s.fielderror> 
             </td>
           </tr>     
           <tr  style="display:none;">
	             <td class="table_name">分类简介:</td>
	             <td>
	             	<@s.textarea name="category.cat_simple" cssClass="mailCss"  onkeyup="checkLength(this,100);" />
	             	<@s.fielderror><@s.param>category.cat_simple</@s.param></@s.fielderror> 
	             </td>
            </tr>    
            
            <tr style="display:none;">
             <td class="table_name">分类描述:</td>
             <td colspan="3">
             	<@s.textarea name="category.cat_intr" id="content" cssClass="txtinput" cssStyle="width:300px;height:200px;"  />
				<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace( 'content');  
				</script>
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
	       <@s.hidden name="category.cat_id" />
	       <@s.hidden name="cat_id"  value="${cat_id?if_exists}"/>
	       <@s.hidden name="category.module_type" value="${category.module_type}"/>
	       <@s.token/>
	       <@s.hidden   name="category.tariff" value="0" />
	       <@s.submit value="保存"/>
			<@s.hidden  id="back_sel_cat"  name="back_sel_cat" />
			<@s.hidden  id="back_sel_cat_name"  name="back_sel_cat_name" />
	       <@s.hidden name="type"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Category_list.action?module_type=${module_type}';document.forms[0].submit();"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>