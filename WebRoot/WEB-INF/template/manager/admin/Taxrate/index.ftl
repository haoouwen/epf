<html>
  <head>
    <title>进出口税率管理</title>
    <link href="/include/admin/css/category.css" rel="stylesheet"type="text/css"/>
    <script language="javascript" src="/include/admin/js/taxrate.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Taxrate_list.action" method="post" id="taxrateform">
 <div class="postion">当前位置：系统管理 > 系统管理 > 进出口税率管理</div>
   <!--当前位置-->
    <div class="rtdcont">
     
      <div class="rtable">  
           <input type="button" class="rbut" onclick="exprotTaxExcel();"  value="导出全部税率">
	 	</div>
        <div  id="taxrateTable"  class="op_content" style="height:880px;">   
        </div>
      
      </div>
     
     </div> 
<!--表单框隐藏域存放-->
<@s.hidden name="taxrate.tax_level" id="level"/>	
<@s.hidden  id="up_tax_id"  name="taxrate.up_tax_id" />
<@s.hidden  id="tax_id" name="tax_id"/>
<@s.hidden  id="back_sel_taxrate"  name="back_sel_taxrate" />
<@s.hidden  id="back_sel_taxrate_name"  name="back_sel_taxrate_name" />
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

