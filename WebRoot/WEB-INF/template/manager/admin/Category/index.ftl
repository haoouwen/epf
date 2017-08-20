<html>
  <head>
    <title>分类管理</title>
<script src="/wro/admin_category_index.js" type="text/javascript"></script>
<link href="/wro/admin_category_index.css" rel="stylesheet" type="text/css"/>
  </head>
<body>
<@s.form action="/admin_Category_list.action" method="post"  id="catform">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 分类管理 > 分类列表</div>
<!--当前位置结束-->
    <div class="rtdcont">
      <div class="rtable">  
        <div id="modletype">请选择所属的模块:<@s.select id="module_type" name="module_type" list="moduleLists" listValue="module_name" listKey="module_type" />
           <input type="button" class="rbut"onclick="exprotCatExcel();"  value="导出分类">
	 	</div>
         <div  id="catTable"  class="op_content" ></div>
      </div>
     </div> 
<!--表单框隐藏域存放-->
<@s.hidden name="category.cat_level" id="level"/>	
<@s.hidden  id="up_cat_id"  name="category.up_cat_id" />
<@s.hidden  id="cat_id"  name="cat_id" />
<@s.hidden  id="mod_type"  name="category.module_type" />
<@s.hidden name="cat_select_moudle" id="cat_select_moudle"/>	
<@s.hidden  id="back_sel_cat"  name="back_sel_cat" />
<@s.hidden  id="back_sel_cat_name"  name="back_sel_cat_name" />
<@s.hidden  id="is_del" name="is_del"   />
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>