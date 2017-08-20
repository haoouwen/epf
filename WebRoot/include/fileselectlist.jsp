<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>
<%@ page import="com.rbt.common.util.PropertiesUtil"%>
<%@ page import="com.rbt.function.*"%>
<%@page import="com.rbt.common.Constants"%>
<%
    //文件选择器，主要用于栏目模板页的选择（页面模板、详细页模板）的选择模板
    
    String template_path = "";
    if (null != request.getParameter("template_code")&& !"".equals(request.getParameter("template_code"))) {
		template_path = request.getParameter("template_code");
	}else{
	    template_path ="cfg_templatefolder";//默认PC端模板路劲
	}
	
    
    
	String retStr = "";
	//项目路径
	String webpath = PropertiesUtil.getRootpath()+Constants.TEMPLATE_PORTAL_FOLDER+SysconfigFuc.getSysValue(template_path);
	
	//返回所带的路劲
	String backPath = "";
	
	String servletFile = "";
	//上一个文件夹
	String perFile = "";
	String objId = "";
	if (null != request.getParameter("servletFile")&& !"".equals(request.getParameter("servletFile"))) {
		servletFile = request.getParameter("servletFile");
	}
	
	if (null != request.getParameter("id")) {
		objId = request.getParameter("id");
	}
	if (null != servletFile && !"".equals(servletFile)) {
		String files[] = servletFile.split("/");
		for (int j = 0; j < files.length - 1; j++) {
			perFile = perFile + files[j] + "/";
		}
	}

	if (webpath.equals("/")) {
		webpath = webpath + "/";
	}
	webpath = webpath + servletFile +"/";
	File file = new File(webpath);
	String files[] = file.list();
	if (!servletFile.endsWith("/")&& !"".equals(servletFile + servletFile)) {
		retStr = servletFile;
		servletFile = servletFile + "/";

	}else{
		
	}
%>

<html>
<head>
	<title>文件选择</title>
	<style type="text/css">
 	#name_list{height:450px;width:650px;overflow:auto;overflow-x:hidden;border:1px solid #dddddd;padding:0 5px;}
 	#name_list ul{margin-left:15px; padding:0px; list-style-image:url(/include/admin/images/mfolder.gif);}
	#name_list ul li{ padding-left:5px; margin-top:0px;margin-bottom:5px;}
	#name_list ul li ul{margin-left:15px; padding:0px; list-style-image:url(/include/admin/images/mleaf.gif);}
	#name_list ul li ul li{ padding-left:5px; margin-top:0px;margin-bottom:5px;}
</style>
</head>
<script>
	function  selectFile(){
		window.opener.document.getElementById("<%=objId%>").value = "<%=retStr%>";
		window.close();
	} 
</script>
<body>
	<table align="center">
	    <tr>
	    <td> 
	      文件选择器
	    </td>
	    </tr>
		<tr>
			<td>
				<div id="name_list">
				<%
					if( null != files )
					{
		  				for( int i = 0; i < files.length; i++ )
		  				{
		  					int j = files[i].indexOf(".");
							if(j == -1)
							{
				%>
							
							<a style="text-decoration: none;color: black;font-weight: bold;" href="/include/fileselectlist.jsp?id=<%=objId%>&servletFile=<%=servletFile + files[i]%>">
							<img src="/include/admin/images/mfolder.gif" border="0">
							<%=files[i]%></a><br/>
				<% 
						}else if(files[i].endsWith(".html")){
				%>
							
							<a style="text-decoration: none;" href="/include/fileselectlist.jsp?id=<%=objId%>&servletFile=<%=servletFile + files[i]%>">
							<img src="/include/admin/images/mleaf.gif" border="0" >
							<%=files[i]%></a>
							<br/>
				<% 
						}
		  			}
				}else{
		  	 	%>
  	 				<script language="javascript">
						function  selectFile(){
							window.opener.document.getElementById("<%=objId%>").value = "<%=backPath%><%=retStr%>";
							window.close();
						} 
						selectFile();
					</script>
		  	 	<% 
		  	 	}
		  	 	%>
			</div>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" value="向上" onClick="window.location.href = '/include/fileselectlist.jsp?id=<%=objId%>&servletFile=<%=perFile%>'"/> 
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="关闭" onClick="window.close();"/> 
		</td>
	</tr>
	</table>
</body>
</html>