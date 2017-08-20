<script type="text/javascript" src="/include/common/js/get-goods-cat-area.js"></script>
<style type="text/css" >
.seartjDiv{
	height:80px;
	padding:10px;
	overflow:auto;
	display:none;
}
.seartjDiv p{
padding:7px 0;
}
.tjMore{
	padding:0 10px;
	cursor:pointer;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(".tjMore").toggle(function(){
		$(".seartjDiv").show();
	},function(){
		$(".seartjDiv").hide();
	})
})
function loadallinfo(){
	//清除前次选择结果
	$("#br_id").html("");
	$("#tw_id").html("");
	$("#ar_id").html("");
	$("#active_id").html("");
	$("#goodscatDiv").html("");
	//所属分类的回选
	loadGoodsCat("${catIdStr?if_exists}","","","goods"); 
	getBrandAllList("br_id");
	gettradeType("tw_id");
	gettopAreaType("ar_id");
	gettopActive("active_id");
	$(".tjMore").toggle(function(){
	  $(".seartjDiv").show();
	},function(){
	  $(".seartjDiv").hide();
	});
}
</script>
<b>商品名称:</b><span><input type="text" id="sg_name" class="txt_goods_name"/></span>
<input type="button" value="搜索" onclick="search();"/>
<span class="tjMore">更多+</span>
 <div class="seartjDiv">
 
	   <p>商品分类:<span id="goodscatDiv" ></span></p>
	   <p> 商品来源:<span><@s.select name="g_goods_source_s" list=r"#{'':'请选择','0':'自主运营','1':'供货商供货'}" id="g_goods_source_s"/></span>
		   贸易方式:<span id="tw_id"></span>
		   商品品牌:<span id="br_id"></span>
		   积分消费:<span><@s.select name="g_goods_useintegral_s" list=r"#{'':'请选择','0':'否','1':'是'}" id="g_goods_useintegral_s"/></span>
	   </p>
	   <p>英文名称:<span><@s.textfield name="g_goods_en_name_s"  cssStyle="width:150px;" id="g_goods_en_name_s"  /></span>
	       商品编号:<span><@s.textfield name="goods_no_s" cssStyle="width:120px;" id="goods_no_s" /></span>
	      条形码:<span><@s.textfield name="g_goods_barcode_s" cssStyle="width:120px;" id="g_goods_barcode_s" /></span> 
	   </p>
	   <p>商品原价:<span><@s.textfield name="g_goods_marketstart_s" cssStyle="width:45px;" id="g_goods_marketstart_s"  />至<@s.textfield name="g_goods_marketend_s"  cssStyle="width:45px;" id="g_goods_marketend_s" /></span>
	      商品售价:<span><@s.textfield name="g_goods_salestart_s" cssStyle="width:45px;" id="g_goods_salestart_s" />至<@s.textfield name="g_goods_saleend_s" cssStyle="width:45px;" id="g_goods_saleend_s" /></span>
	      商品库存:<span><@s.textfield name="g_goods_stockstart_s" cssStyle="width:45px;" id="g_goods_stockstart_s" />至<@s.textfield name="g_goods_stockend_s" cssStyle="width:45px;" id="g_goods_stockend_s" /></span>
	   </p>
	  <p> 
	        商品产地:<span><span id="ar_id"></span></span>
	       生产商:<span><@s.textfield name="g_goods_producer_s" cssStyle="width:120px;" id="g_goods_producer_s" /></span>
	  </p>
	  <p> 
	        活动商品:<span><span id="active_id"></span></span>
	  </p>
	   
	   
 </div>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 