<html>
  <head>
    <title>修改会员</title>
    <#include "/include/uploadInc.html">
    <link rel="StyleSheet"  type="text/css" href="/include/admin/css/member.css"/>
     <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/js/admin/member.js"></script>	
   <script type="text/javascript" src="/include/admin/js/menberuser.js"></script>
   <link rel="StyleSheet" href="/include/admin/css/memberuser.css" type="text/css" />
  </head>
  <body>
<@s.form action="/admin_Member_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:会员管理 > 会员管理 > 修改会员
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="btitle" colspan="4" align="left">会员账号信息</td>
           </tr>
        <tr>
            <td class="table_name" style="width:220px;" height="60px;">用户名<font color='red'>*</font></td>
             <td colspan="3">
                ${memberuser.user_name?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">密&nbsp;&nbsp;码:</td>
             <td colspan="3">
             	  <@s.password name="memberuser.passwd"value="" cssClass="winput" maxLength="20"  onkeyup="javascript:checkpass(this)"  id="pass" onblur="setpswstrong(this)"/>
             	 <img class="ltip" src="/include/common/images/light.gif" alt="密码只能由6-20个字母、数字、下划线组成!">
        		<@s.fielderror><@s.param>memberuser.passwd</@s.param></@s.fielderror>
        		<@s.label id="password_label" cssClass="winput"  maxLength="32" value=""/>
        		<@s.hidden name="memberuser.pass_strength" id="psw_strong" />
             </td>
           </tr>
           <tr>
            	<td  class="table_name">确认密码:</td>
	                <td colspan="3">
			            <@s.password name="confirmpasswd" cssClass="winput" maxLength="20"/>
			            <font color="red">(默认为空则不修改密码)</font>
			        	<@s.fielderror><@s.param>confirmpasswd</@s.param></@s.fielderror>
		            </td>
            </tr>
           <tr>
           <tr>
             <td  class="btitle" colspan="4" align="left">会员基本信息</td>
           </tr>  
           <tr>
             <td class="table_name">会员名称<font color='red'>*</font></td>
             <td colspan="3">
             	<@s.textfield name="member.cust_name" cssClass="txtinput" cssStyle="width:300px;" maxLength="100"/>
             	<img class="ltip" src="/include/common/images/light.gif" alt="会员名称以字母开头，由数字、字母、下划线组成!">
             	<@s.fielderror><@s.param>member.cust_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">会员头像:</td>
             <td >
             
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="member.logo_path" id="uploadresult" cssStyle="width:250px;"/>
                        </td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>member.logo_path</@s.param></@s.fielderror>
             			   【建议图片宽110px,高110px】
             			</td>
             		</tr>
             	</table>
             
             </td>
             
              <td class="table_name">会员生日:</td>
	              <td >
		               <input type="text"  name="memberuser.birthday"value="<#if memberuser!=null&&memberuser.birthday!=null>${memberuser.birthday[0..10]?if_exists}</#if>" class="Wdate upWdate" style="width:80px;"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D({M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
		               <@s.fielderror><@s.param>memberuser.birthday</@s.param></@s.fielderror>
	             </td>
           </tr>  
           </tr>
           <tr>
           
                <td class="table_name">
                       真实姓名
                </td>
	            <td><@s.textfield name="memberuser.real_name" cssClass="txtinput"  maxLength="50" cssStyle="width:200px;"/>
	            <@s.fielderror><@s.param>memberuser.real_name</@s.param></@s.fielderror>
	            <td class="table_name">skype:</td>
	            <td>
	             	<@s.textfield name="memberuser.skype" cssClass="txtinput"  maxLength="100"  cssStyle="width:200px;"/>
	             	<@s.fielderror><@s.param>memberuser.skype</@s.param></@s.fielderror>
	           </td>
            </tr>
                <td class="table_name" >性别<font color='red'>*</font></td>
             <td>
                <@s.radio name="memberuser.sex" list=r"#{'0':'先生','1':'女士'}" value="0" onclick="getMemValue();"/>
                <@s.fielderror><@s.param>memberuser.sex</@s.param></@s.fielderror>
             </td>
	             <td class="table_name">电子邮箱</td>
	             <td >
             	<@s.textfield name="memberuser.email" cssClass="txtinput" maxLength="50"  cssStyle="width:200px;"/>
         		<img class="ltip" src="/include/common/images/light.gif" alt="格式如:xxxx@xxx.com">	
             	<@s.fielderror><@s.param>memberuser.email</@s.param></@s.fielderror>
             </td>
             
           </tr>
           <tr>
             <td class="table_name">电话:</td>
             <td>
             	<@s.textfield name="memberuser.phone" cssClass="txtinput" maxLength="50"  cssStyle="width:200px;"/> 
             	<@s.fielderror><@s.param>memberuser.phone</@s.param></@s.fielderror>
             </td> 
              <td class="table_name">联系人QQ:</td>
             <td>
             	<@s.textfield name="memberuser.qq" cssClass="txtinput" maxLength="50" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>memberuser.qq</@s.param></@s.fielderror>
             </td> 
           </tr>
            <tr>
             <td class="table_name">联系手机</td>
             <td>
             	<@s.textfield name="memberuser.cellphone" cssClass="txtinput" maxLength="50"  cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>memberuser.cellphone</@s.param></@s.fielderror>
             </td>
              <td class="table_name">联系人微信:</td>
             <td>
             	<@s.textfield name="memberuser.msn" cssClass="txtinput" maxLength="100" cssStyle="width:200px;"/>
             	<@s.fielderror><@s.param>memberuser.msn</@s.param></@s.fielderror>
             </td>
           </tr> 
           <tr>
             <td class="table_name">会员级别<font color="red">*</font></td>
	              <td >
		               <@s.select id="type" name="member.buy_level"  list="malllevelsetbuyList" listValue="level_name" listKey="level_code" headerKey="" headerValue="请选择"/>        
		               <@s.fielderror><@s.param>member.buy_level</@s.param></@s.fielderror>
	             </td>
	             <td class="table_name">审核状态<font color="red">*</font></td>                
	              <td>
		             <#list infoStateList as infoState>
						<#if infoState.para_value?if_exists='1'>
						   <input type="radio" name="member.info_state"  <#if member.info_state?if_exists==infoState.para_value>checked="true"</#if> value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
					    <#elseif infoState.para_value?if_exists='3'>
					       <input type="radio" name="member.info_state" value="${infoState.para_value?if_exists}" <#if member.info_state?if_exists==infoState.para_value>checked="true"</#if> onclick="checkinput(this)">${infoState.para_key?if_exists}
					    </#if>
	    			 </#list> 
		              <img class="ltip" src="/include/common/images/light.gif" alt="若禁用请点击禁用选项填写理由">
		             <@s.fielderror><@s.param>member.info_state</@s.param></@s.fielderror>
		              <br/>
	             	<@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;" />
		        </td>
           </tr>
           
            <tr>
             <td class="table_name">登陆次数</td>
             <td>
             	 ${(memberuser.login_count)?if_exists}
             </td>
              <td class="table_name">登陆IP:</td>
             <td>
             	 ${(memberuser.login_ip)?if_exists}
             </td>
           </tr>
           
           <tr>
             <td  class="table_name">最后登录时间</td>
             <td colspan="3">
             	 ${(memberuser.login_time)?if_exists}
             </td>
             
           </tr>  
		 
		 
		 <!--详细页table的数据结束-->
		</table>
		<#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   		  <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       
	       <!--默认已经审核通过-->
	      <@s.hidden name="member.cust_id" />
	      <@s.hidden name="is_nal" />
	      <@s.hidden name="memberuser.cust_id" />
	       <@s.hidden name="memberuser.user_id" />
	       <@s.hidden name="memberuser.role_code" value="0"/>
	       <@s.hidden name="memberuser.user_type"  value="1"/>
	       <@s.hidden name="memberuser.user_name"/>
	       <@s.hidden name="member.in_date"/>
	       <@s.hidden name="member.sell_level"/>
	       <@s.hidden name="member.mem_type"/>
	       <@s.hidden name="memberuser.login_time"/>
	       <@s.hidden name="malllevelset.level_code"/>
	       
	       <@s.hidden name="olduser_name" value="${(memberuser.user_name)?if_exists}"/>
	       <@s.hidden name="oldinfotitle" value="${(member.cust_name)?if_exists}"/>
	       <@s.hidden name="oldemail" value="${(memberuser.email)?if_exists}"/>
	       <@s.hidden name="oldcellphone" value="${(memberuser.cellphone)?if_exists}"/>
	       <@s.hidden name="oldphone" value="${(memberuser.phone)?if_exists}"/>
	       <@s.hidden name="buy_level" value="${member.buy_level}"/>
	       
	        <@s.hidden name="is_update" value="1"/>
	       <!--企业站是否激活：否-->
	       <@s.hidden name="member.is_active"/>
	       <!--所属分类插件隐藏字段开始区域-->
	
	       <input type="button" name="returnList" value="返回列表" onClick="linkToInfo('/admin_Member_list.action','');"/>
           <@s.token/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>