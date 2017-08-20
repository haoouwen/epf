<html>
  <head>
    <title>积分规则管理</title>     
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>	 
  </head>
  <body>
<@s.form action="/admin_Interrule_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：系统管理 > 系统设置 > 积分规则管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('interrule.rule_id')"/> 全选</td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
          	<th width="3%"></th>
		    <th width="25%"  >规则编码</th>
		    <th width="47%"  >规则名称</th>
		    <th width="25%"  >操作积分数</th>
	  </tr>
	  
	   <#list interruleList as rule>
	    <tr >
		    <td><input type="checkbox" name="interrule.rule_id" value="${rule.rule_id?if_exists}"/></td>
		    <td align="center">
				${rule.rule_code?if_exists}
		    </td>
		     <td align="center">
				${rule.rule_name?if_exists}
		    </td>
		     <td align="center">
				<input value="${rule.internum?if_exists}" id="inputNum${rule.rule_id?if_exists}" style="width:120px;text-align:right;"onkeyup="check_zen_num(this)"/>
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
     <input type="button" class="rbut" onclick="updateNum('/admin_Interrule_updateScore.action');" value="修改">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
<script type="text/javascript">
      function updateNum(actionName)
      {  
         var flag = false;
		 var checks = document.getElementsByTagName('input');//得到所有input
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
	      art.dialog({
		   title: '系统信息提示',
	       content: '<div class="decorate">您确定要修改吗？</div>',
	       width: '15%',
	       icon: 'question',
	       yesFn: function () {
	         var admin_group_id='';
	           var admin_group_num='';
		        $("input:checkbox").each(function(){	                 	  
		              if(this.checked&&this.value!="on"){
		                 //得到所名为checkbox的id
		                 admin_group_id+=this.value+',';	
		                 admin_group_num+=$("#inputNum"+this.value).val()+',';     
		              }                             		             
				 }); 	        
	           var len=admin_group_id.length;
	           var lenNum=admin_group_num.length;
	           admin_group_id=admin_group_id.substring(0,len-1);
	           admin_group_num=admin_group_num.substring(0,lenNum-1);               
	           document.getElementById('scoreid').value = admin_group_id;//用于隐藏所有的ID
	           document.getElementById('scoreNum').value = admin_group_num;//用于隐藏文本所有的值
			   document.forms[0].action=actionName;
			   document.forms[0].submit();
	        return false;
	    },
	    noText: '关闭',
	    noFn: true //为true等价于function(){}
	    });	   
     } 
    </script> 

  </body>
</html>
