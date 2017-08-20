<html>
  <head>
    <title>修改联想关键词</title>
    <script type="text/javascript" src="/include/common/js/pinyin.js"></script> 
	<!--所属分类开始-->
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","goods");
      });
	</script>
  </head>
<body >
<@s.form action="/admin_Associationkeywords_update.action"  method="post" validate="true"  id="modiy_form">
<div class="postion">
 当前位置：网站管理 > 联想关键字 > 修改联想关键字
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			   <tr>
	             <td class="table_name" style="width:220px;" height="60px;">联想关键字标题<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="associationkeywords.ass_key_words_title" id="ass_key_words_title_id"  cssStyle="width:300px" maxLength="100"/>
	             	<@s.fielderror><@s.param>associationkeywords.ass_key_words_title</@s.param></@s.fielderror>
	             </td>
	           </tr>
	           
	            <script type="text/javascript">  
	                  $('#ass_key_words_title_id').bind('keyup', function(){   
	              	  var cat_value=$('#ass_key_words_title_id').val();   
		              var getword=Turnpingyin(cat_value);
		              var en_word="";
		              if(getword.length>50){
		              	en_word=getword.substring(0,49);
					  }else{
					     en_word=getword;
					  }
		              $('#en_name').val(en_word);
	              })
	           </script>  
           <tr>
             <td class="table_name">英文名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="associationkeywords.en_name" cssStyle="width:300px" maxLength="300"  id="en_name" readonly="true"/>
             </td>
           </tr>
	           
	           
			   <tr>
	             <td class="table_name">所属分类</td>
	             <td>
	             	<div id="catDiv"></div>
         		   <@s.fielderror><@s.param>associationkeywords.cat_attr</@s.param></@s.fielderror>
	             </td>
	           </tr>
	        
			   <tr>
	           <td class="table_name">是否显示<font color='red'>*</font></td>
	           <td>
	               <@s.radio name="associationkeywords.ass_key_words_show" list=r"#{'0':'是','1':'否'}"/>
	           </td>
	        </tr>
	         <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="associationkeywords.sort_no" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>associationkeywords.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="排序值越大，越靠前!"> 
             </td>
           </tr>
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
   <@s.token/> 
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
 		    <@s.hidden name="associationkeywords.ass_key_words_id"/> 
 		    <@s.hidden name="associationkeywords.ass_key_words_number" />
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Associationkeywords_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

