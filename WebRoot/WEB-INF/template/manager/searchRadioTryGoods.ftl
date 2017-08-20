<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
<script type="text/javascript" src="/include/common/js/jqModal.js"></script>
<link href="/include/common/css/jqModal.css" rel="stylesheet" type="text/css" />  
<link href="/include/common/css/ajaxpage.css" rel="stylesheet" type="text/css" /> 
<link href="/include/common/css/searchgoods.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="/include/common/js/searchRadioTryGoods.js"></script>

<div id="searchgoods"   class="jqmWindow searchgoods" >
	<p class="updown_mana">
		<img src="/include/common/images/closecover.png"  style="float:right;margin:-3px -5px auto;cursor:pointer;" onclick="closeralate();">
		<b>添加相关商品</b>
	</p>
	<div class="sg_contain_up">
		<div class="searchtitle">
		<b>商品名称:</b><span><input type="text" id="sg_name" class="txt_goods_name"/></span>
		<input type="button" value="搜索" onclick="search();"/>
		</div>
	 	<div id="searchgoodslist">
	 	
	 	</div>
	 	
	    <p class="updownoper">
	    	<input type="button" value="保存" onclick="saveGoods();"/>
	    	<input type="button" value="关闭" onclick="closeralate();"/>
	    </p>
	</div>
</div>
