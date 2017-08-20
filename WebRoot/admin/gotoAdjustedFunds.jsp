<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.rbt.function.*" %>
<%@ page import="com.rbt.action.CapitalmanagementAction" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rbt.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>
   输入密码获得调整资金资格
</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="/include/admin/css/index.css" rel="stylesheet" type="text/css" />
<link href="/include/common/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/include/common/js/commonplugin.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
 		$("#gotoAdjusted").popup({p_width:"479", p_height:"185", pop_title:"3个密码输入正确方可调整运营资金。"});
 		})
   </script>
   <div style="display:none;"  id="gotoAdjusted" >
      <s:form  action="/admin_Capitalmanagement_IsPass.action"  method="post"  >
	        <table  >
	           <tr>
	           	<td  class="table_name">提示：</td>
	           	<td>输入密码前，请先确保系统安全！</td>
	          </tr>
	          
	          <tr><td  class="table_name">通过人A的密码<font color="red">*</font></td><td>
	         	<s:password  name="passwordA"  maxLength="16"style="width:260px;height:20px;"/>
	          </td></tr>  
	          <tr><td  class="table_name">通过人B的密码<font color="red">*</font></td><td>
	         	<s:password  name="passwordB" maxLength="16"style="width:260px;height:20px;"/>
	          </td></tr>
	          <tr><td  class="table_name">通过人C的密码<font color="red">*</font></td><td>
	         	<s:password  name="passwordC"  maxLength="16"style="width:260px;height:20px;"/>
	          </td></tr>
	          
	          <tr>
	           <td  colspan="2"align="center">
	           	<s:submit value="提 交" cssClass="submitbut" />
	            <a href="/admin_Capitalmanagement_list.action">返回</a>
	            <%
	            	String passMessage=(String)session.getAttribute("passMessage");
	            	if(passMessage!=null&&!passMessage.equals("")){
	            		%>
	            		<font color="red">${passMessage}</font>
	            		<% 
	            	}
	            	
	            %>
	           </td>
	          </tr> 
	       </table>
	     </s:form>
	<div>




