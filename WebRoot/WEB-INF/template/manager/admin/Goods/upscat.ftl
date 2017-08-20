<html>
  <head>
    <title>选择商品分类</title>
       <script src="/wro/admin_goods_selcat.js" type="text/javascript"></script>
      <link href="/wro/admin_goods_selcat.css" rel="stylesheet" type="text/css"/>
  </head>
 <body>
 <@s.form action="/admin_Goods_view.action" method="post" validate="true" id="modiy_form">
 <@s.hidden    name="goods.goods_id" />
<@s.hidden  id="back_sel_cat"  name="back_sel_cat" />
<@s.hidden  id="back_sel_cat_name"  name="back_sel_cat_name" />
<div class="postion_index"></div>
<div class="rtdcont">
	<div class="rttable">
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
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
 
</html>

