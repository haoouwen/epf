<html>
  <head>
    <title>地区管理</title>
    <link href="/include/admin/css/category.css" rel="stylesheet" type="text/css" />  
    <script language="javascript" src="/include/admin/js/area.js"></script>
      <#include "/include/uploadInc.html">
      <script type="text/javascript"/>
		 //导入地区
		function importGoods(actionName){
		$("#iname_s").val($("#uploadresult").val());
		var iname=$("#iname_s").val();
		if(iname==''){
		alert("请选择xls文件");
		return false ;
			}
			if(window.confirm("确定要导入地区吗?")) {
				document.forms["areaform"].action=actionName;
				document.forms["areaform"].submit();
			}
		}
      </script>      
  </head>
<body>
<@s.form action="/admin_Area_list.action" method="post"  id="areaform">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统管理 > 地区管理</div>
<!--当前位置结束-->
    <div class="rtdcont">
      <div class="rtable">  
        <input type="button" class="rbut"onclick="exprotAreaExcel();"  value="导出地区">
        <input type="button" class="rbut"onclick="exportShowDIV('importDiv','300px','220px','导入地区');;"  value="导入地区">
        <div  id="areaTable"  class="op_content" style="height:880px;">     
			
      </div>
      </div>
     </div> 
<!--表单框隐藏域存放-->
<@s.hidden  id="up_area_id"  name="area.up_area_id" />
<@s.hidden  id="area_id" name="area_id"/>
<@s.hidden  id="back_sel_area"  name="back_sel_area" />
<@s.hidden  id="back_sel_area_name"  name="back_sel_area_name" />
<@s.hidden  name="iname" id="iname_s"/>
 <!--表单框隐藏域存放--> 
 <!--搜索区域开始-->
<div  style="display:none;"  id="importDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			
			<tr>
				<td>
	             	  <table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			<@s.textfield name="img_pathmulti"   id="uploadresult" cssStyle="width:200px;" readonly="true"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
		              		 <script>uploadCommonComponent("","uploadifyfile","uploadresult","fileQueue","file",false);</script>
	             		</tr>
	             	</table> 
	             </td>
			</tr>
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	             	 <input  id="d_goods" type="button" id="b_goods" onclick="importGoods('/admin_Area_importAreaInfo.action');"  value="导入地区">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('importDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--搜索区域结束--> 
</@s.form>
  </body>
</html>