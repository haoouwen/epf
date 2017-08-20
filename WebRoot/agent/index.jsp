<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.*" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title><%=SysconfigFuc.getSysValue("cfg_webname") %>区域代理/直营店/门店-登录</title>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="/agent/css/admin_login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/jquery-1.7.min.js"></script>
<script type="text/javascript">
  //获取cookeis的值
$(document).ready(function () {
	var names = getck("loginName");
	document.getElementById("uname").value=names;
	var stip=$.trim($("#show_tip_id").html());
	if(stip!=""&&stip!=null){
	 $("#show_tip_id").attr("class","ts");
	}else{
	 $("#show_tip_id").attr("class","t4");
	}
});
function getck(sname) {//获取单个cookies
	var acookie = document.cookie.split("; ");
	for (var i = 0; i < acookie.length; i++) {
		var arr = acookie[i].split("=");
		if (sname == arr[0]) {
			if (arr.length > 1) {
				return unescape(arr[1]);
			} else {
				return "";
			}
		}
	}
	return "";
}
function changeValidateCode(obj) {   
        //获取当前的时间作为参数，无具体意义   
        var timenow = new Date().getTime();   
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
        obj.src="/imgrand.action?d="+timenow;
}   
</script>
</head>
<body>
<s:form action="/agentlogin.action" method="post" validate="true">
<div class="admin_login_back">
	<div class="admin_login_wrap">
	    <h1>区域代理/直营店/门店-登录</h1>
	    <div class="adming_login_border">
	        <div class="admin_input">
	           
	                <ul class="admin_items">
	                    <li>
	                        <label for="user">用户名：</label>
	                        	<s:textfield name="asysuser.user_name" id="user_name" maxLength="20" size="36" cssClass="admin_input_style" />
	                    </li>
	                    <li>
	                        <label for="pwd">密码：</label>
	                        	<s:password name="asysuser.passwd" maxLength="20" id="pwd" size="36" cssClass="admin_input_style"/>
	                    </li>
	                      <li>
	                           <label for="pwd">验证码：</label>
				          		<s:textfield name="userrand" maxLength="4" size="20" cssClass="admin_input_yzm"/>
				          		<img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
				          </li>
				          
				          <li>
				          		 <div class="show_tip_id">
						          	<s:fielderror><s:param>adminloginMessage</s:param></s:fielderror>
						         </div>
				          </li>
				           
				           <li>
	                        <s:submit tabindex="3" value="提交" cssClass="btn" />
	                        <s:token/>
	                    </li>
	                </ul>
	           
	        </div>
	         
	    </div>
	  
	</div>
<div>
 </s:form>
</body>
</html>