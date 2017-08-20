<html>

<head>

	<title>账户管理</title>
	
	<link href="/include/bmember/css/account.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="/include/common/js/common.js"></script>
	 <!--日期-->
	<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	
</head>
	
	
<body>
	
<@s.form action="/bmall_Memberuser_updateuser.action"  method="post"  name="formshopcongif" validate="true">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>账户信息</span></h2>
        <div class="accontDiv">
            <div class="tabIDiv">
               <a href="#" class="siBut">基本信息</a><a href="/bmall_Member_photoview.action?parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}" class="niBut">修改头像</a>
            </div>
            <table width="100%" cellpadding="0" cellspacing="0" class="ainfoTab">
                <tr>
                   <th>会员图片</th>
                   <td>
                   <#if member.logo_path?if_exists!="">
                   <img src="${member.logo_path}" width="110" height="110">
                   <#else>
                   <img src="/include/bmember/images/defalutHead.gif" width="110" height="110">
                   </#if>
                   </td>
            	</tr>
            	<tr>
                   <th>会员名称</th>
                   <td><@s.label name="memberuser.user_name"/><span><@s.fielderror><@s.param>memberuser.user_name</@s.param></@s.fielderror></span></td>
            	</tr>
                <tr>
               		<th>性别</th>
               		<td>
               			 <#if memberuser.sex?if_exists!=null && memberuser.sex?if_exists!=''>
		    				    <@s.radio name="memberuser.sex" list=r"#{'2':'保密','0':'男','1':'女'}" />
		    			<#else>
		    					<@s.radio name="memberuser.sex" list=r"#{'2':'保密','0':'男','1':'女'}"  value="2" />
		    			</#if>
               		</td>
            	</tr>
              	<tr>
               		<th>生日</th>
               		<td>
               			 <input type="text"  name="memberuser.birthday"  value="<#if memberuser!=null&&memberuser.birthday!=null>${memberuser.birthday[0..10]?if_exists}</#if>"   class="Wdate upWdate" style="width:100px;"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D({M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			       		 <span><@s.fielderror><@s.param>memberuser.birthday</@s.param></@s.fielderror></span>
               		</td>
            	 </tr>
                  <tr>
                   <th>真实姓名</th>
                   <td>
                   	   
	               	    	<@s.textfield name="memberuser.real_name" cssClass="aifText" maxLength="10"/>
	               	    	<@s.fielderror><@s.param>memberuser.real_name</@s.param></@s.fielderror>
	               	    
                   </td>
                 </tr>
 	             <tr>
	               <th>手机</th>
	               <td>
	               			<@s.textfield name="memberuser.cellphone" cssClass="aifText" maxLength="11"/>
	               			 <@s.fielderror><@s.param>memberuser.cellphone</@s.param></@s.fielderror>
	               </td>
	             </tr>
	             
	             <tr>
	               <th>电话</th>
	               <td>
	               		<@s.textfield name="memberuser.phone" cssClass="aifText" maxLength="20"/>
	               		<@s.fielderror><@s.param>memberuser.phone</@s.param></@s.fielderror>
	               </td>
	             </tr>
	             
	             <tr>
	               <th>电子邮箱</th>
	               <td>
	               		<@s.textfield name="memberuser.email" cssClass="aifText" maxLength="60"/>
	               		<@s.fielderror><@s.param>memberuser.email</@s.param></@s.fielderror>
	               </td>
	             </tr>
	             
	             <tr>
	               <th>QQ</th>
	               <td>
	               		<@s.textfield name="memberuser.qq" cssClass="aifText" maxLength="15" onkeyup="checkDigital(this)"/>
	               		<@s.fielderror><@s.param>memberuser.qq</@s.param></@s.fielderror>
	               </td>
	             </tr>
	             
	             <tr>
	               <th>微信</th>
	               <td>
	               		<@s.textfield name="memberuser.msn" cssClass="aifText" maxLength="50"/>
	               		<@s.fielderror><@s.param>memberuser.msn</@s.param></@s.fielderror>
	               </td>
	             </tr>	             
                 
                 <tr>
                   <th>.</th>
                   <td><input type="submit" class="submitbut" value="提交"/></td>
                 </tr>
            </table>
        </div>
                
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="memberuser.user_id"/>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
</@s.form>
</body>

</html>

