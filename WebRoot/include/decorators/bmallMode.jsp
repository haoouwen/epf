<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.rbt.function.*" %>
<%@ page import="com.rbt.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@include file="/WEB-INF/template/manager/bmall/includejs_member.ftl" %>
<decorator:head />
<title>
   <%=SysconfigFuc.getSysValue("cfg_webname") %>-<decorator:title default="Welcome" />
</title>
<s:hidden id="ser_type" value="goods"/>
<s:hidden id="selli" name="selli"/>
<s:hidden id="parentMenuId" name="parentMenuId"/>
</head>

<body>
<input type="hidden" id="file_size" value="<%=SysconfigFuc.getSysValue("cfg_filesize")%>"/>
<input type="hidden" id="flash_size" value="<%=SysconfigFuc.getSysValue("cfg_flashsize")%>"/>
<input type="hidden" id="image_size" value="<%=SysconfigFuc.getSysValue("cfg_imgsize")%>"/>
<input type="hidden" id="image_type" value="<%=SysconfigFuc.getSysValue("cfg_imgtype")%>"/>
<input type="hidden" id="flash_type" value="<%=SysconfigFuc.getSysValue("cfg_mediatype")%>"/>
<input type="hidden" id="file_type" value="<%=SysconfigFuc.getSysValue("cfg_attachtype")%>"/>
<input type="hidden" id="img_cust_id" value="<%=session.getAttribute(Constants.CUST_ID)%>"/>
<input type="hidden" id="req_session_cust_id" value="<%=session.getAttribute(Constants.CUST_ID)%>"/>
<input type="hidden" id="req_session_cust_type" value="<%=session.getAttribute(Constants.CUST_TYPE)%>"/>

<!--顶部-->
<%@include file="/a/bmall/malltop.html" %>
<!--导航-->
<div class="headerList">
    <div class="w1180">
    <%@include file="/a/bmall/mallnav.html" %>
 	<%@include file="/a/bmall/mallcat.html" %>
   </div>
</div>
<% 
	String parentMenuId = "";
	if (request.getParameter("parentMenuId") != null && !request.getParameter("parentMenuId").toString().equals("")) {
		parentMenuId = request.getParameter("parentMenuId");
	} else if (session.getAttribute("parentMenuId") != null && !session.getAttribute("parentMenuId").toString().equals("")) {
		parentMenuId = session.getAttribute("parentMenuId").toString();
	}
	session.setAttribute("parentMenuId", parentMenuId);
	if (request.getParameter("up_menu_id") != null && !request.getParameter("up_menu_id").toString().equals("")) {
		parentMenuId = "";
	} 
%>     
<div class="clear"></div>
<!-------------------内容------------------------>
<div class="postion">
  <span class="lpos"><a href="#">会员中心</a> >  </span>
</div>
<div class="w990">
<% 
		//根据上一级ID找出属于上一级的列表
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", "b2c");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		map.put("enabled","0");
		//根据系统后台、一级菜单找出菜单信息
		List topMenuList = SysmenuFuc.getSysmenuList(map);
		//菜单权限串，登陆的时候放进去的
		String menu_right = "";
		
		if(session.getAttribute("menu_right")!=null){
			menu_right = session.getAttribute("menu_right").toString();
		}
    	String frist_menu_id = "";
    	if(session.getAttribute("first_menu_id") == null || session.getAttribute

       ("first_menu_id").toString().equals("")){
    	    HashMap topMap=new HashMap();
    	    if(topMenuList!=null&&topMenuList.size()>0){
    			
    	       topMap=(HashMap)topMenuList.get(0);
    	       frist_menu_id=topMap.get("menu_id").toString();    	       
    	       session.setAttribute("first_menu_id", frist_menu_id);
    	    }    	    
    	}else{ 	
    		frist_menu_id = session.getAttribute("first_menu_id").toString();
    	}
    %>
  <!--左边-->
  <div class="wL170">
    <div id="wLid">
        <% 
   	  if(topMenuList!=null && topMenuList.size()>0){
     		HashMap mMap = new HashMap();
     		for(Iterator it = topMenuList.iterator();it.hasNext();){
     			mMap = (HashMap)it.next();
     			String menu_name = "",menu_id = "",url = "",target = "";
     			if(mMap.get("menu_name")!=null) menu_name = mMap.get("menu_name").toString();
     			if(mMap.get("menu_id")!=null) menu_id = mMap.get("menu_id").toString();
     			if(mMap.get("url")!=null) url = mMap.get("url").toString();
     			if(mMap.get("target")!=null) target = mMap.get("target").toString();
     			System.out.print(menu_id);
     			String cssStr = "";
     			if(frist_menu_id.equals(menu_id)){
     				cssStr = " class=\"title_navli\"";
     			}
     			if(menu_right.indexOf(menu_id) == -1){
     				continue;
     			}
     			
     %>
        <h2><a target="<%=target %>" onclick="setSession('<%=url%>','<%=menu_id %>');"><%=menu_name %></a></h2>	
        <ul class="wLul">
         <%
        //第二级菜单绑定
		HashMap twoMap = new HashMap();
		List twoList = SysmenuFuc.getMenuListByUpmenuid(frist_menu_id,"b2c");			
		if(twoList!=null && twoList.size()>0){
			for (int i=0;i<twoList.size();i++) {
				twoMap = (HashMap)twoList.get(i);
				String tow_menu_id = "",tow_menu_name="";
				if(twoMap.get("menu_id")!=null) tow_menu_id = twoMap.get("menu_id").toString();
				if(twoMap.get("menu_name")!=null) tow_menu_name = twoMap.get("menu_name").toString();
				if(menu_right.indexOf(tow_menu_id) == -1){
     				continue;
     			}
     			String dis_down = "";
				if(parentMenuId.equals("") && i == 0){
					dis_down = "";
				}else if(tow_menu_id.equals(parentMenuId)){
					dis_down = "";
				}
				//三级菜单获取  
				int downSize = 0;
				String menuid ="";
				 //三级菜单获取
                List downList = new ArrayList();       
				downList = SysmenuFuc.getMenuListByUpmenuid(tow_menu_id,"b2c");
				System.out.print(tow_menu_id);
			%>
		   <li>
	           <h3><span id="<%=tow_menu_id %>"><%=tow_menu_name%></span></h3>
	           <ul> 
	            <%
				if(downList!=null && downList.size()>0){
					for(int j=0;j<downList.size();j++){
						Map threeMap = (HashMap)downList.get(j);
						String down_menu_id = "",down_menu_name="",down_url="",down_target="";
						if(threeMap.get("menu_id")!=null) down_menu_id = threeMap.get("menu_id").toString();
						if(threeMap.get("menu_name")!=null) down_menu_name = threeMap.get("menu_name").toString();
						if(threeMap.get("url")!=null) down_url = threeMap.get("url").toString();
						if(threeMap.get("target")!=null) down_target = threeMap.get("target").toString();
						if(menu_right.indexOf(down_menu_id) == -1){
		     				continue;
		     			}
						String para = "?";
		     			if(down_url.indexOf("?") > -1){
		     				para = "&";
		     			}		
               %>
	          	 		<li  <%=dis_down %>  id="<%=down_menu_id %>" ><a href="<%=down_url %><%=para %>parentMenuId=<%=tow_menu_id %>&selli=<%=down_menu_id %>" target="<%=down_target %>"><%=down_menu_name %></a></li>
	           <%
	           		}
	           }
	           %>
	           </ul>
	         </li>
			<%  
				}
			}
	%>
        </ul>
       <%
     		}
     	}
     %>   
    </div>
  </div>
  <decorator:body />
 </div>
	<script type="text/javascript">
	function setSession(actionName,fieldVal){	
	   	document.getElementById('commonForm').action=actionName;
	   	document.getElementById('top_menu').value = fieldVal;  
	    document.getElementById('commonForm').submit();	   
	}
	</script>
 
<div class="clear"></div>


<!---尾部-->
<%@include file="/a/bmall/mallbottom.html" %>
<script type="text/javascript">  
	var actionMessage = '<s:actionmessage/>';
	if(actionMessage!=''){
	var dialogTip = art.dialog({
	    button: [{
	        id: 'confirm',
	        value: '确定 (3)',
	        width: '8em',
	        callback: function () {
	        	clearInterval(timer);
	       		dialogTip.close();
	            return true;
	        }
	    }]
	});
	var msgsrc="<img class='msgsrc' src='/include/common/images/msgsuccess.png'/>&nbsp;"
	dialogTip.title('系统提示').content(msgsrc+actionMessage);
	var i = 3;
	var timer = setInterval(function () {
	    i--;	    
	    dialogTip.button({
	        id: 'confirm',
	        value: '确定 (' + i + ')'
	    });	    
	    if (i === 0) {
	        clearInterval(timer);
	        dialogTip.close();	       
	    };
	}, 1000); 
}
</script>
<s:form  action="" id="commonForm">
	<s:hidden name="top_menu" id="top_menu"/>
	<s:hidden name="" id="commonId" value=""/>	
	<s:hidden name="" id="commonText" value=""/>	
	<div id="hidden_div">
		${listSearchHiddenField}
	</div>
</s:form>

</body>
</html>
