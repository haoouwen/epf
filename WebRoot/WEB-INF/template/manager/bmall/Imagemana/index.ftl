<html>
  <head>
    <title>图片管理</title>
    	<link href="/include/bmall/css/imagemana.css" rel="stylesheet" type="text/css" />  
  </head>
  <body>
<@s.form action="/bmall_Imagemana_list.action" method="post" id="indexForm">

<div class="main_index f_left">
   <div class="pageLocation">
 	帐户管理 > 空间管理 > 图片管理
   </div>
 <div class="main_top">
      <input type="button" value="批量删除" class="submitbut"  onclick="delInfo('imagemana.serial_id','/bmall_Imagemana_alldelete.action')"/>
 </div>
 <div class="main_cont" style="background:#ffffff;margin-left:10px;">
 <div class="selTemplete">
 <div class="selckb"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('imagemana.serial_id')"/><span> 全选</span></div>	   
  <#list imagemanaList as imagemana>
		<div class="mb">
		     <div class="imgdiv">
		     <#list imagemana.img_path?if_exists?split(',') as img>
			       <a class="group" href="${img?if_exists}">
			      	   <img class="showimgstyle lazyload"  src="${img?if_exists}" style="width:160px;height:160px;" />
			       </a> 
			 </#list>
			 </div>
			 <div class="txtarea">
					<!--<@s.textarea name="imagema.note" value="${imagemana.note?if_exists}"   maxlength="100" cssStyle="width:160px;height:80px;resize:none;" readonly="true" /> -->
					<input class="txbox" type="checkbox" name="imagemana.serial_id" value="${imagemana.serial_id?if_exists}" />
					<a onclick="linkToInfo('/bmall_Imagemana_view.action','imagemana.serial_id=${imagemana.serial_id?if_exists}');"><img src="/include/common/images/bj.gif"></a>
			    	<a onclick="delOneInfo('imagemana.serial_id','/bmall_Imagemana_delete.action',${imagemana.serial_id?if_exists})"><img src="/include/common/images/delete.png"></a>
					<span>${imagemana.in_date?string("yyyy-MM-dd HH:mm:ss")}</span>
              </div>
			    
	 	</div>
	 	

</#list>
 </div>
 
 <div class="clear"></div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
</div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

