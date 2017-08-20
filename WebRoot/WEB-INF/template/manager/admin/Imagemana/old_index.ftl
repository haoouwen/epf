<html>
  <head>
    <title>图片管理</title>
    	<link href="/include/admin/css/imagemana.css" rel="stylesheet" type="text/css" />  
    	
  </head>
  <body>
<@s.form action="/admin_Imagemana_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 空间管理 > 图片管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
	     	<td><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('imagemana.serial_id')"/><span> 全选</span></td> 
	  	 </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
	   <#list imagemanaList as imagemana>
		<div class="mb">
		     <div class="imgdiv">
		     <#list imagemana.img_path?if_exists?split(',') as img>
			       <a class="group" href="${img?if_exists}">
			      	  <img class="showimgstyle lazyload"  src="${img?if_exists}" style="width:210px;height:210px;" />
			       </a> 
			 </#list>
			 </div>
			 <div class="txtarea">
					<@s.textarea name="imagemana.note" value="${imagemana.note?if_exists}"   maxlength="100" cssStyle="width:200px;height:80px;resize:none;" readonly="true"/> 
					<input class="txbox" type="checkbox" name="imagemana.serial_id" value="${imagemana.serial_id?if_exists}" />
					<a onclick="linkToInfo('/admin_Imagemana_view.action','imagemana.serial_id=${imagemana.serial_id?if_exists}');"><img src="/include/common/images/bj.gif"></a>
			    	<a onclick="delOneInfo('imagemana.serial_id','/admin_Imagemana_delete.action',${imagemana.serial_id?if_exists})"><img src="/include/common/images/delete.png"></a>
					<span>${imagemana.in_date?string("yyyy-MM-dd HH:mm:ss")}</span>
              </div>
			    
	 	</div>
	 	

	</#list>
  </div>
<!--后台table结束-->
<div class="clear"/>
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
     <input type="button" class="rbut" onclick="delInfo('imagemana.serial_id','/admin_Imagemana_alldelete.action')" value="批量删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>


  </body>
</html>
