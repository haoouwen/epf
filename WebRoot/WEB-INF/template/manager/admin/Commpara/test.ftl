<html>
  <head>
    <title>测试升级上传控件</title>
         <link rel="stylesheet" href="/include/components/uploadify/uploadify.css" />  
	     <script src="/include/common/js/jquery-1.7.min.js"></script>  
	     <script src="/include/components/uploadify/uploadify.js"></script>  
	     <script src="/include/components/uploadify/upload.js"></script> 
	     <script type="text/javascript">  
		    $(document).ready(function() {  
			    test();
		    });  
		    </script> 
	    
  </head>
  <body>
  <@s.form action="/admin_Commpara_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:系统管理 > 系统管理 > 参数管理 > 添加参数
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
           
            <tr><td>上传测试</td>
			    <td >    
			      <div id="fileQueue"></div>  
	            <input type="file" name="uploadify" id="uploadify" />  
			    </td>    
		     </tr> 
           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.token/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Commpara_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>