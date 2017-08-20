<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	
	<% 
	    String areaname=(String)session.getAttribute("areaName");
		String setarea="所有地区";
		if(areaname!=null && !areaname.equals("")) {
			setarea=areaname;
		}
	%>
	document.write("<a href='/mall/groupbuy!getarea.action'><span id='areabotton' class='sendarea' ><%=setarea%></span></a>");

