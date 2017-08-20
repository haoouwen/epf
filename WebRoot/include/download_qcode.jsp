<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
    String cfg_mobiledomain = "",custnum="",registertype="";
    if (null != request.getParameter("cfg_mobiledomain")&& !"".equals(request.getParameter("cfg_mobiledomain"))) {
		cfg_mobiledomain = request.getParameter("cfg_mobiledomain");
	}
	if (null != request.getParameter("custnum")&& !"".equals(request.getParameter("custnum"))) {
		custnum = request.getParameter("custnum");
	}
	if (null != request.getParameter("registertype")&& !"".equals(request.getParameter("registertype"))) {
		registertype = request.getParameter("registertype");
	}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>文件选择</title>
</head>

<body>
  <table align="center">
    <tr align="center">
		<td>
	       门店<%=custnum %>二维码
		</td>
	</tr>
	 <tr>
		<td>
	       &nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td>
	    <input type="hidden" value="<%=cfg_mobiledomain %>/webapp/memberuser!webappRegister.action?custnum=<%=custnum %>&registertype=<%=registertype %>" id="code_url"/>
		<div id="qrcode"></div>
		</td>
	</tr>
  </table>
</body>
<script type="text/javascript" src="/include/common/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="/malltemplate/bmall/js/jquery.qrcode.min.js"></script>
    <script type="text/javascript">
	 $(document).ready(function(){
	  var code_url=$("#code_url").val();
	  $('#qrcode').qrcode({width:300,height:300,text:code_url});
	});
</script>
</html>