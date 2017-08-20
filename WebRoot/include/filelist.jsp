<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rbt.common.util.BackMysql"%>
<%//数据库的备份与还原
	String type = "DB";
	if(request.getParameter("type")!=null){
	 	type = request.getParameter("type");
	}
	BackMysql bm = new BackMysql();
	List sqlList = bm.getSqlList(type);
	if(sqlList!=null && sqlList.size()>0){
%>

<table width="320" align="center">

<%
		for(Iterator it = sqlList.iterator();it.hasNext();){
			String fileName = (String)it.next();
%>
<tr>
<td align="left"><%=fileName %></td>
<td><input type="button" name="huany" value="还原" onclick="checkload('<%=fileName %>')"/></td>
<td><input type="button" name="huany" value="下载" onclick="downlaodTable('<%=fileName %>')"/></td>
<td><input type="button" name="huany" value="删除" onclick="deleteTable('<%=fileName %>')"/></td>
 </tr>
<%
	}
%>
</table>
<%
	}
%>