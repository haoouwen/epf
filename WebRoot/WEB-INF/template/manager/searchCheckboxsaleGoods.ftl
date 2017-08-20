<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
<link href="/include/common/css/ajaxpage.css" rel="stylesheet" type="text/css" /> 
<link href="/include/common/css/searchcomnongoods.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/searchCommonGoods.js"></script>
<script type="text/javascript" src="/include/common/js/searchCheckboxsalegoods.js"></script>
<div id="searchgoods" style="display:none;"  >
	<div class="sg_contain_up">
		<div class="searchtitle" id="searchtitle">
		<#include "/WEB-INF/template/manager/searchModeGoods.ftl"/> 
		</div>
	 	<div id="searchgoodslist" style="height:340px;overflow: auto">
	 	</div>
	    <p class="updownoper">
	    	<span id="save"><input type="button" value="保存当前选择" onclick="saveralate();"/></span>
	    	<span id="save1"><input type="button" value="保存全部商品" onclick="loadALLralate();"/></span>
	    	<input type="button" value="关闭" onclick="closeRalateGoods('searchgoods');"/>
	    </p>
	</div>
</div>
