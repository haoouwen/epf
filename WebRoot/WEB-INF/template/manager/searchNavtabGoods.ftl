<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
<link href="/include/common/css/ajaxpage.css" rel="stylesheet" type="text/css" /> 
<link href="/include/common/css/searchgoodscom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/searchCommonGoods.js"></script>
<script type="text/javascript" src="/include/common/js/searchNavtabGoods.js"></script>
<div id="searchgoods"   style="display:none;"  >
	<div class="sg_contain_up">
		<div class="searchtitle">
		 <#include "/WEB-INF/template/manager/searchModeGoods.ftl"/> 
		</div>
	 	<div id="searchgoodslist">
	 	</div>
	    <p class="updownoper">
	    	<input type="button" value="保存" onclick="saveralate();"/>
	    	<input type="button" value="关闭" onclick="closeRalateGoodsRef('searchgoods');"/>
	    </p>
	</div>
	<@s.hidden  name="tab_id_s" id="tab_id_id"/>
	<@s.hidden  name="tab_number" id="tab_number_id"/>
</div>、
