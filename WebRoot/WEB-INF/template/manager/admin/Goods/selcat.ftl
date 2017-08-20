<html>
  <head>
    <title>选择商品分类</title>
   <script src="/wro/admin_goods_selcat.js" type="text/javascript"></script>
<link href="/wro/admin_goods_selcat.css" rel="stylesheet" type="text/css"/>
  </head>
 <body>
 <@s.form action="/admin_Goods_add.action" method="post" validate="true" id="modiy_form">
 <@s.hidden  id="back_sel_cat"  name="back_sel_cat" />
<@s.hidden  id="back_sel_cat_name"  name="back_sel_cat_name" />
<@s.hidden  name="goods.goods_id"/> 
<div class="postion">当前位置:商品管理 > 商品管理 > 添加商品</div>
<div class="rtdcont">
	<div class="rttable">
	<div>
	  <@s.fielderror><@s.param>back_sel_cat</@s.param></@s.fielderror>
	</div>    
	  <div  id="catTable"  class="op_content" style="background:#FFFFFF;height:470px;"> 
	      	 </div>
	      	 
	      	 <div class="fixbuttom" style="float:right;">
	      		  <@s.submit value="添加"/> 
				  <@s.reset value="重置" style="cursor:pointer;"/>
	    	 </div>
	 </div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

