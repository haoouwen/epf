<html>
<head>
<title>更新栏目页</title>
	<link rel="stylesheet" href="/include/common/css/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="/include/admin/css/channel.css" type="text/css">
	<script type="text/javascript" src="/include/admin/js/channel.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="/include/admin/js/channelTree.js"></script>
	<script type="text/javascript">
  		$(document).ready(function(){
		 	$.fn.zTree.init($("#channe"), setting,${jsonMenu?if_exists});
		});
    </script>
</head>
<body >
	<@s.form  method="post" validate="true">
<div class="postion">
  <!--当前位置-->
  当前位置:网站管理 > 网站更新 > 更新栏目页
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  
          <tr >
	          <td height="65px" width="10%"> 
	          <input  value="更新全部栏目页" type="button" onclick="doHtmlPage('all','');">
	          </td>
	          <td align="left" >
	          <div id="loading" style="display:none;">
	           <img src=/include/admin/images/upLoading.gif style="width:30px; height:30px;">&nbsp;更新中...
	          </div>
	          <font color="red"><label id="msg" ></label></font>
	          </td>
          </tr>
             <tr>
	             <td  colspan="2" >
			        <div class="zTreeDemoBackground left">
						<ul id="channe" class="ztree"></ul>
					</div>
	             </td>
             </tr>
             
		</table>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>