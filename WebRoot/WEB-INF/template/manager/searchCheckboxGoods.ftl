<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
<link href="/include/common/css/ajaxpage.css" rel="stylesheet" type="text/css" /> 
<link href="/include/common/css/searchgoodscom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/searchCommonGoods.js"></script>
<#if cheked!=""> 
<script type="text/javascript" src="/include/common/js/searchCheckboxgoods.js"></script>
<#else>
<script type="text/javascript" src="/include/common/js/searchsigleCheckboxgoods.js"></script>
</#if>
<div id="searchgoods"   style="display:none;"  >
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
