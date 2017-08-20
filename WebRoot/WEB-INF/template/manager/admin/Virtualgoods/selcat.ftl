<html>
  <head>
    <title>选择虚拟商品分类</title>
    <link href="/include/admin/css/selcat.css" rel="stylesheet" type="text/css" />  
	<script language="javascript" src="/include/admin/js/selvirtualcat.js"></script>
  </head>
 <body>
 
 <@s.form action="/admin_Virtualgoods_add.action" method="post" validate="true" id="modiy_form">

<!--当前位置开始-->
     <div class="postion">
 		当前位置:商品管理 > 虚拟商品管理 >选择虚拟商品分类
   	 </div>
   	 
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		 
		 			 <div  id="catTable"  class="op_content" style="background:#FFFFFF;height:470px;">     
				
	      	 </div>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	  <@s.submit value="添加"/> 
	  <@s.reset value="重置" style="cursor:pointer;"/>
	  <@s.hidden  id="back_sel_cat"  name="back_sel_cat" />
	  <@s.hidden  id="back_sel_cat_name"  name="back_sel_cat_name" />
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>
