<html>
  <head>
    <title>组织部门管理</title>
	<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<link rel="StyleSheet" href="/include/common/css/alert.css" type="text/css" />
	<link rel="StyleSheet" href="/include/admin/css/organize.css" type="text/css" />	
	<script type="text/javascript">	 
	 $(document).ready(function(){ 
	     //加载第一级部门
	    showorga("1111111111",0);	
     });	    
    </script>
  </head>
<body>
<@s.form action="/admin_Organize_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 帐号管理 > 组织部门管理</div>
<!--当前位置结束-->
<div class="rtdcont">

<!--后台table-->
   <div class="rtable">
 	 <div id="orgalist" style="height:400px;"> 	 
 	 
 	 </div>
   </div>
<!--后台table结束-->
<div class="clear"/>
</div>
</@s.form>
  </body>
</html>





