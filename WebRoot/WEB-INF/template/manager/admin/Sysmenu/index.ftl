<html>
  <head>
    <title>菜单管理</title>
	<link rel="stylesheet" href="/include/common/css/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/include/common/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="/include/admin/js/sysmenu.js"></script>
	<link href="/include/admin/css/seoset.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function(){
			var treeSysObj = $.fn.zTree.init($("#treeRole"), SysSetting,${jsonMenu?if_exists});
			var treeMallObj = $.fn.zTree.init($("#treeMall"), MallSetting,${jsonMall?if_exists});
			//tab切换页
			$("#oper_seo_div").mallTab({});
		});
	</script>
  </head>
<body>

<@s.form action="/admin_Sysmenu_insert.action" method="post">
<@s.hidden name="sysmenu_sortno_id" id="sysmenu_sortno_id"/>
<@s.hidden id="menu_code" name="sysmenu.syscode" value="${sysmenu.syscode?if_exists}" />
<div class="postion"> 	当前位置:系统管理 > 系统管理 > 菜单管理</div>
<div class="rtdcont">
		 <input type="button" class="rbut" onclick="addMenu();" value="添加菜单">
		 <input type="button" class="rbut" onclick="updateSort('sysmenu.menu_id','isort_no','/admin_Sysmenu_updateSort.action');" value="排序">
	   	 <div id="oper_seo_div" class="oper_seo_div">   
	 		<div class="tabbar">
			    <ul>
			   		<li id="sys" <#if menu_code=='sys'>class="selected"</#if>>管理员后台菜单</li>
			   		<li id="b2c" <#if menu_code=='b2c'>class="selected"</#if>>商城后台菜单</li>
			    </ul>
	       </div>
	       
	 	   <div class="clear"></div>
					       
	       <div class="tabdiv" <#if menu_code=='sys'>style="display:block"</#if> >
				<div class="showtitle"><b>管理员后台菜单</b></div>
				<div class="zTreeDemoBackground left">
					<input id="menu_type" type="hidden" name="menu_code" />
					<ul id="treeRole" class="ztree"></ul>
				</div>
			
	       </div>
	       
	       <div class="tabdiv" <#if menu_code=='b2c'>style="display:block"</#if>>
				<div class="showtitle"><b>商城后台菜单</b></div>
				<div class="zTreeDemoBackground left">
						<ul id="treeMall" class="ztree"></ul>
				</div>
	       </div>
	 	
	 </div>	 
	</div>
	<div class="clear"/>
	<@s.hidden id="b2cAttr" name="b2cAttr"/>
</div>
</@s.form>

<div class="clear"></div>
