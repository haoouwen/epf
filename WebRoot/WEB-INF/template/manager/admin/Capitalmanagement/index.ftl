<html>
  <head>
    <title>平台资金管理</title>  
	 <script type="text/javascript" >
	 	function showMmsy(){
			$("#mmsy").popup({p_width:"550", p_height:"295", pop_title:"运营商资金管理密码使用说明与建议"});
		}
	 </script> 
	<link href="/include/admin/css/directsell.css" rel="stylesheet" type="text/css"/> 
  </head>
<body>
<@s.form action="/admin_Capitalmanagement_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：财务管理 > 财务管理 > 平台资金管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		    <th width="20%"  align="center" >通过人</th>
		    
		    <th width="30%" align="center" >修改密码</th>
		    
		    <th width="30%" align="center" >忘记密码?</th>
		    
		    <th width="20%" align="center" >重设密保</th>
	  </tr>
	  
		 <#list capitalmanagementList as capitalmanagement>
		  <tr>
			 	
			    <td align="center">${capitalmanagement.pass_men?if_exists}</td>
			    
			    <td align="center"><a onclick="linkToInfo('/admin_Capitalmanagement_view.action','capitalmanagement.trade_id=${capitalmanagement.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
			    <td align="center"><a onclick="linkToInfo('/admin_Capitalmanagement_forgetPassView.action','capitalmanagement.trade_id=${capitalmanagement.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
			     <td align="center"><a onclick="linkToInfo('/admin_Capitalmanagement_setSecretSecurityView.action','capitalmanagement.trade_id=${capitalmanagement.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="radminbut" onclick="linkToInfo('/adminAdjustedFundView.action','');" value="平台资金调整">
     <a onclick="showMmsy()"><img class="ltip" src="/include/common/images/light.gif" alt="点击灯泡查看资金管理密码使用说明与建议"></a>
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Memberfund_count.action','');" value="返回">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="capitalmanagement.is_recom" id="admin_capitalmanagement_state"/>
 <!--表单框隐藏域存放-->  
</@s.form>
<#if istoAdjus!=null&&istoAdjus!=''&&istoAdjus=='1'>
<script type="text/javascript">
   $(document).ready(function(){
 		$("#gotoAdjusted").popup({p_width:"479", p_height:"185", pop_title:"3个密码输入正确方可调整运营资金。"});
 		})
   </script>
</#if>
   <div style="display:none;"  id="gotoAdjusted" >
      <@s.form  action="/admin_Capitalmanagement_IsPass.action"  method="post"  >
	        <table  >
	           <tr>
	           	<td  class="table_name">提示：</td>
	           	<td>输入密码前，请先确保系统安全！</td>
	          </tr>
	          
	          <tr><td  class="table_name">通过人A的密码<font color="red">*</font></td><td>
	         	<@s.password  name="passwordA"  maxLength="16"cssStyle="width:260px;height:20px;"/>
	          </td></tr>  
	          <tr><td  class="table_name">通过人B的密码<font color="red">*</font></td><td>
	         	<@s.password  name="passwordB" maxLength="16"cssStyle="width:260px;height:20px;"/>
	          </td></tr>
	          <tr><td  class="table_name">通过人C的密码<font color="red">*</font></td><td>
	         	<@s.password  name="passwordC"  maxLength="16"cssStyle="width:260px;height:20px;"/>
	          </td></tr>
	          
	          <tr>
	           <td  colspan="2"align="center">
	           	<@s.submit value="提 交" cssClass="submitbut" />
	            <#if passMessage!=null>
	            		<font color="red">${passMessage}</font>
            	</#if>
	           </td>
	          </tr> 
	       </table>
	     </@s.form>
	<div>
	   <div style="display:none;"  id="mmsy" class="mmsy">
	        <p><b>1、</b>修改平台资金需过三关，具体权力请自行分配。总密码设3个，默认权力分3份平衡。掌权人数不为3的情况下可以一人掌管多个密码或多人掌管一个密码，即把一个密码分成N段一个人记住1/N段，密保和密码相互牵制，密保尤其重要；</p>
	  		<p><b>2、</b>初始密码默认6个1，初始密保默认为"空"；</p>
	  		<p><b>3、</b>为安全起见，每次进入平台资金调整后只能调整一次资金且当系统每个90秒内检测不到任意后台操作将退出后台(重新登录系统即可解除)；</p>
	  		<p><b>4、</b>建议:若多人掌管一个密码，该密码的密保由除掌管改密码且非运营商后台管理员(即不能操作后台)且不掌管另两个密保和密码的人外掌管。(登陆系统或修改密码或密保系统均在日志做记录)</p>
	   <div>
  </body>
</html>

