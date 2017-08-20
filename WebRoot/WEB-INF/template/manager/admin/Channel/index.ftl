<html>
  <head>
	<title>网站栏目</title>
    <link rel="stylesheet" href="/include/common/css/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="/include/admin/css/channel.css" type="text/css">
    <script type="text/javascript" src="/include/common/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="/include/admin/js/channelTree.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var treeSysObj = $.fn.zTree.init($("#treeweb"), Colsetting,${jsonMenu?if_exists});
		});
	</script>
  </head>
<body>
<@s.form action="/admin_Channel_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：网站管理 > 基本功能 > 网站栏目管理</div>
<!--当前位置结束-->
    <div class="rtdcont">
      <div class="rtable">  
     <div class="tabdiv" <#if menu_code=='sys'>style="display:block"</#if> >
					<div class="showtitle"><b >&nbsp;网站栏目</b></div>
					<div class="zTreeDemoBackground left">
						<input id="menu_type" type="hidden" name="menu_code" />
						<ul id="treeweb" class="ztree"></ul>
	 </div>
      </div>
      <input type="button" class="rbut" onclick="linkToInfo('/admin_Channel_add.action','');" value="添加">
     <input type="button" class="rbut" onclick="updateSort('channel.ch_id','isort_no','/admin_Channel_updateSort.action');" value="排序">
     </div> 
<!--表单框隐藏域存放-->
<@s.hidden name="admin_Sort_id" id="admin_Sort_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>