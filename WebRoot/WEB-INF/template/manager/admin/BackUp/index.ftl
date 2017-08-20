<html>
  <head>
    <title>数据库备份与还原</title>
	<script type="text/javascript" src="/include/js/admin/backup.js"></script>	
	<script  type="text/javascript">	
	$(document).ready(function(){ 
		$(".seesql").click(function() {
	        var sql= $(this).parent("span").children("font").html();
		    alert(sql);	   
	    });	
    });  	
	</script>
  </head>
  <body>

<@s.form id="form" action="/admin_BackUp_list.action" method="post">
<div class="main_index f_left">
 <div class="pageLocation">
 	当前位置:系统管理 > 系统工具 > 数据库备份与还原
 </div>
 <div class="main_top">
   <ul class="main_top_ul">
     
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="1%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('cbtable')"/>&nbsp;</td>
    <td width="20%" align="center" class="top_td">数据库表名</td>    
    <td width="20%" align="center" class="top_td">表记录数</td>    
    <td width="30%" align="center" class="top_td">查看表结构</td>  
  </tr>
  <div style="font-size:20px;padding:6px 0px 0px 3px;">
  </div>

  <#list tableList as tablist>
    <tr bgcolor="<#if tablist_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="cbtable" value="${tablist.tableName?if_exists}"/></td>
    <td align="center">${tablist.tableName?if_exists}</td>
    <td align="center">${tablist.tableCount?if_exists}</td>
    <td align="center">
    <span>   
    <a class="seesql" ><img src="/include/common/images/view.gif" border="0"/></a>
    <font style="display:none;" >${tablist.structure?if_exists}</font>
    </span>    
    </td>        
   </tr> 
  </#list>  
</table>

 </div>

 <div class="clear">
 </div>
</div>
</div>
<div class="clear"></div>

<div id="sqlNameDiv" style="width:100%;margin:0 auto;text-align:center;">
</div>

<div class="buttom" style="text-align:center; height:45px;">
   <input style="cursor:pointer;" type="button" name="returnList" value="备份数据库" onclick="backDb();"/>    
   <input style="cursor:pointer;" type="button" name="returnList" value="还原数据库" onclick="showSqlName('DB');"/>  
   <input style="cursor:pointer;" type="button" name="returnList" value="备份表" onclick="return checkbu()";/>    
   <input style="cursor:pointer;" type="button" name="returnList" value="还原表" onclick="showSqlName('TABLE');"/>         
</div> 

<input type="hidden" name="sqlFileName" id="sqlFileName" value="" />

</@s.form>
  </body>
 <body>
<@s.form action="/admin_BackUp_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统工具 > 数据库备份与还原</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	    <th width="1%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('cbtable')"/>&nbsp;</th>
	    <th width="30%" align="center" >数据库表名</th>    
	    <th width="20%" align="center" >表记录数</th>    
	    <th width="49%" align="center" >查看表结构</th>  
	  </tr>
	 <#list tableList as tablist>
	    <tr>
	    <td><input type="checkbox" name="cbtable" value="${tablist.tableName?if_exists}"/></td>
	    <td align="center">${tablist.tableName?if_exists}</td>
	    <td align="center">${tablist.tableCount?if_exists}</td>
	    <td align="center">
	    <span>   
	    <a class="seesql" ><img src="/include/common/images/view.gif" border="0"/></a>
	    <font style="display:none;" >${tablist.structure?if_exists}</font>
	    </span>    
	    </td>        
	   </tr> 
	  </#list>  
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
   <input class="rbut" type="button" name="returnList" value="备份数据库" onclick="backDb();"/>    
   <input class="rbut" type="button" name="returnList" value="还原数据库" onclick="showSqlName('DB');"/>  
   <input class="rbut" type="button" name="returnList" value="备份表" onclick="return checkbu()";/>    
   <input class="rbut" type="button" name="returnList" value="还原表" onclick="showSqlName('TABLE');"/>  
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>