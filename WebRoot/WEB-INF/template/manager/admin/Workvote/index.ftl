<html>
  <head>
    <title>参赛投票列表</title>   
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script>
     <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  </head>
<body>
<@s.form action="/admin_Workvote_list.action" method="post"  id="indexForm">
<script>
    	//导出操作
    	function exportData(actionName){
    		var flag = true;
    		if(flag==true){
			    var tip = '确认导出吗';
				art.dialog({
				title: '系统信息提示',
			    content: '<div class="decorate">'+tip+'</div>',
			    width: '15%',
			    icon: 'question',
			    yesFn: function () {
				    document.forms[1].action=actionName;
				    document.forms[1].submit();
			        return true;
			    },
			    noText: '关闭',
			    noFn: true //为true等价于function(){}
			    });
			 }

		}		
		

    </script> 
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 商城活动 > 参赛投票列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >会员名称:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">作品名称:</td> 
			<td><@s.textfield name="ent_title_s"  cssStyle="width:245px;"/></td>
			<td class="tdpad">投票时间:</td> 
			<td>
				<@s.textfield id="txtstartDate" name="start_vote_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
				               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:0})}',readOnly:true})"  /> 
				&nbsp;至&nbsp;
				 <@s.textfield id="txtendDate" name="end_vote_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
				               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:0})}',readOnly:true})"  />
	         </td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		   <td width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('workvote.vote_id')"/></td>
   
    
     	 <td width="25%"  >会员名称</td>
    
     	 <td width="25%"  >投票作品</td>
    
     	 <td width="25%"  >投票时间</td>
    
     	 <td width="20%"  >随机码</td>
    
  </tr>
  
  <#list workvoteList as workvote>
  <tr>
    <td><input type="checkbox" name="workvote.vote_id" value="${workvote.vote_id?if_exists}"/></td>    
    
    	<td align="center">${workvote.cust_name?if_exists}</td>
    
    	<td align="center">${workvote.ent_title?if_exists}</td>
    
    	<td align="center">${workvote.vote_time?if_exists}</td>
    
    	<td align="center">${workvote.radom_code?if_exists}</td>
          
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
     <input type="button" class="rbut" onclick="exportData('/admin_Workvote_export.action');" value="导出">
     <input type="button" class="rbut" onclick="delInfo('workvote.vote_id','/admin_Workvote_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden name="ac_id" id="ac_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>




