<html>
  <head>
    <title>定时更新设置管理</title>
     <script type="text/javascript" src="/include/js/admin/regularlyupdated.js"></script> 
  </head>
  <form>
  <body>
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统设置 > 定时更新设置管理
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="xg" ><a onclick="uxmldata();">更新</a></li>
     <li><input id="rdxml" type="hidden" name="allXmldateString" value="${allXmldateString}" style="width:500px;"/></li>
   </ul>
 </div>
 <div class="main_cont">
 <input id="rdxmls" type="hidden" name="redXmlDateString" value="${redXmlDateString}" style="width:1000px;"/>
  <table width="100%" border="0" cellspacing="0" class="indexTab" id="alladdhtmltext">
  </table>
  <div id="addlis">
  </div>
 </div>
 <div class="clear"></div>
</div>
  </body>
  </form>
</html>