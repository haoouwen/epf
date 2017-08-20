<html>
  <head>
    <title>选择商品分类</title>
    <link href="/includes/admin/css/selcat.css" rel="stylesheet" type="text/css" />  
	<script language="javascript" src="/includes/admin/js/selcat.js"></script>
  </head>
 <body>
<@s.form action="/admin_Goods_view.action" method="post">
<@s.hidden    name="goods.goods_id" />
<@s.hidden  id="back_sel_cat"  name="back_sel_cat" />
<@s.hidden  id="back_sel_cat_name"  name="back_sel_cat_name" />
<div class="main">
	<div class="op_main">
		 <div class="pageNav">
		 	当前位置:商品管理 > 商品管理 > 更新商品分类
		 </div>
		 <div  id="catTable"  class="op_content" >     
			
      	 </div>
      	 <div class="btbox">
	          <div class="op_box">
		           <@s.token/>
			       <@s.submit value="保存"/> 
			       <@s.reset value="重置" style="cursor:pointer;"/>
	          </div>		
        </div> 
    </div>		 
</div>
</@s.form>
  </body>
</html>

