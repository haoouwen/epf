<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
<link href="/include/common/css/ajaxpage.css" rel="stylesheet" type="text/css" /> 
<link href="/include/common/css/searchcomnongoods.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/searchCommonGoods.js"></script>
 <script src="/include/common/js/searchCheckBoxGoodsTab.js" type="text/javascript"></script>
<div id="searchgoods"  style="display:none;" >
    <input type="hidden" id="fm_id_id"/>
	<div class="sg_contain_up">
		<div class="searchtitle">
		<#include "/WEB-INF/template/manager/searchModeGoods.ftl"/> 
		</div>
	 	<div id="searchgoodslist">
	 	</div>
	    <p class="updownoper">
	    	<input type="button" value="保存" onclick="saveralate();"/>
	    	<input type="button" value="关闭" onclick="closeRalateGoods('searchgoods');"/>
	    </p>
	</div>
</div>
