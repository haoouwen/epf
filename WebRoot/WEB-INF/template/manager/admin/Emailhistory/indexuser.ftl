<html>
  <head>
    <title>会员信息</title>
    <script>
    function selInfo(field_name,actionName){
	var flag = false;
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			flag=true;
			break;
		}
	}
	if(flag==false){
		alertShow('请至少选择一条记录!','warning');
		runCount(3);
		return false;
	}
	if(flag==true){
	   art.dialog({
		title: '系统信息提示',
		content: '<div class="decorate">您确定吗？</div>',
		width: '15%',
		icon: 'question',
		yesFn: function () {
		    document.forms[0].action=actionName;
		    document.forms[0].submit();
		    return false;
		},
		noText: '关闭',
		noFn: true //为true等价于function(){}
		});
	
	}
}
    </script>
  </head>
 <body>
<@s.form action="/admin_Emailhistory_indexuser.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：会员管理 > 会员相关 > 会员信息</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>会员名称:</td>
			<td><@s.textfield name="user_name_s" cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		    <th width="5%"> <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.email')"/></th>
		    <th width="40%">会员名称</th>
		    <th width="30%">会员邮箱</th>
		    <th width="25%">会员注册时间</th>
      </tr>
			  
		 <#list memberList as member>
		  <tr>
		    <td><input type="checkbox" name="member.email" value="${member.email?if_exists}"/></td>
		    <td align="center">${member.cust_name?if_exists}</td>
		    <td align="center">${member.email?if_exists}</td>
		    <td align="center">${member.in_date?if_exists}</td>
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
     <input type="button" class="rbut" onclick="selInfo('member.email','/admin_Emailhistory_getemail.action')" value="选中">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body> 
</html>