<html>
  <head>
    <title>修改门店服务介绍</title>
    <#include "/include/uploadInc.html">
  </head>
<body >
<@s.form action="/admin_Storeintro_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：会员管理 > 区域代理 > 修改门店服务介绍
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_name" width="20%">服务介绍标题<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="storeintro.sto_name" cssClass="txtinput" maxLength="100" />
	             	<@s.fielderror><@s.param>storeintro.sto_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	          
	           <tr>
	           <td class="table_name">图片<font color='red'>*</font></td>
	           <td class="table_right">
	                <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="storeintro.img_path" id="uploadresult" cssStyle="width:275px;"/>
                        </td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>storeintro.img_path</@s.param></@s.fielderror>
             			  【图片宽275px  高200px】
             			</td>
             		</tr>
             	</table>
	          
	             	
	           </td>
	        </tr>	
	    
	        <tr>
	           <td class="table_name">服务内容</td>
	           <td class="table_right">
	             	<@s.textarea name="storeintro.content" cssStyle="width:500px;height:150px"    maxLength="500" />
	             	<@s.fielderror><@s.param>storeintro.content</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	          <tr>
	           <td class="table_name">排序<font color='red'>*</font></td>
	           <td class="table_right">
	               <@s.textfield name="storeintro.sort_no" cssClass="txtinput" maxLength="5" cssStyle="width:50px;" onkeyup="checkNum(this);"/>
		            <@s.fielderror><@s.param>storeintro.sort_no</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
			 <tr>
	           <td class="table_name">是否显示<font color='red'>*</font></td>
	           <td class="table_right">
	            <@s.radio name="storeintro.is_show" list=r"#{'0':'显示','1':'不显示'}"  onclick="getMemValue();"/>
	             	<@s.fielderror><@s.param>storeintro.is_show</@s.param></@s.fielderror>
	           </td>
	        </tr>	
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
          <@s.token/>
          <@s.hidden name="storeintro.sto_id"/>
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Storeintro_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

