<html>
  <head>
    <title>直营店</title>
    <script src="/include/admin/js/sysuser.js" type="text/javascript"></script>	
     <script type="text/javascript" src="/include/admin/js/organize.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){ 	    
	     checkback("1111111111",1);	     
	  });
	</script>
  </head>
  <body>

<@s.form action="/admin_Asysuser_shoplist.action" method="post" id="indexForm">
<@s.hidden name="asysuser.state" id="admin_user_state"/>
<div class="postion">当前位置：会员管理 > 区域代理 > 直营店</div>
<div class="rtdcont">
   <!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
       <tr>
         <td class="tdpad">直营店号：</td>
          <td><@s.textfield name="nike_name_s"/>&nbsp;</td>
         <td>用户名：</td>
         <td><@s.textfield name="user_name_s"/>&nbsp;</td>
         <td><input type="submit" class="rbut" value="查询"></td>
       <tr>
     </table>
   </div>
   <!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
       <tr>
         <th class="tc" width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('asysuser.user_id')" class="allChoose"/></th>
                             <th>直营店号</th>
                            <th>用户名</th>
                            <th>直营店名称</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th>状态</th>
                            <th>操作</th>
       </tr>
      <#list userList as user>
	 <tr>                    
	 <td class="tc"><input type="checkbox" name="asysuser.user_id" value="${user.user_id?if_exists}"/></td>
	                         <td>${user.nike_name?if_exists}</td>
                            <td>
                              ${user.user_name?if_exists}
                            </td>
                            <td>${user.store_name?if_exists}</td>
                            <td>${user.cellphone?if_exists}</td>
                            <td>${user.email?if_exists}</td>
                            <td align="center">
	                        <#if user.state?if_exists=='0'>
	                        <span><font color="green">启用</font></span>
	                           </#if>
	                          <#if user.state?if_exists=='1'>
	                           <span><font color="red">禁用</font></span>
	                             </#if> 
	                             </td>                          
                            <td>
                            	<a onclick=linkToInfo("/admin_Asysuser_Shopview.action","asysuser.user_id=${user.user_id?if_exists}");><img src="/include/common/images/bj.gif" /></a>
 	    	                    <a onclick="linkToInfo('/admin_Asysuser_resterView.action','asysuser.user_id=${user.user_id?if_exists}&pwd_state=3');" title="密码重置"><img src="/include/admin/images/accedit.gif" /></a>
                                
                            
                            </td>
                        </tr>
                        </#list>
                    </table>
   </div>
   <!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
   <!--翻页-->
   <div class="bsbut">
   <#if grade!='3'>
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Asysuser_shopadd.action','');" value="添加">
 	</#if>
 	<input type="button" class="rbut" onclick="updateBatchState('0','asysuser.user_id','/admin_Asysuser_updateshop.action','启用');" value="启用">
    <input type="button" class="rbut" onclick="updateBatchState('1','asysuser.user_id','/admin_Asysuser_updateupshop.action','禁用');" value="禁用">
   </div>
</div>
<!--搜索框隐藏域存放-->
		  <@s.hidden name="org_hidden_value" id="org_value"/>
</@s.form>
</body>
</html>


