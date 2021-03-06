<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.rbt.function.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rbt.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
    <link rel="stylesheet" type="text/css" href="/include/agent/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/include/agent/css/main.css"/>
    <script type="text/javascript" src="/include/agent/js/libs/modernizr.min.js"></script>
<link href="/include/common/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/include/common/js/jquery-1.4.4.min.js"></script>  
<script src="/include/components/artDialog5.0/artDialog.min.js"></script>
<link href="/include/components/artDialog5.0/skins/blue.css" rel="stylesheet" />
<script type="text/javascript" src="/include/common/js/common.js"></script>
<script type="text/javascript" src="/include/common/js/commonplugin.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.jNotify.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.floatDiv.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.colorbox.js"></script>
<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
<link href="/include/common/css/alert.css" rel="stylesheet" type="text/css" />
    <title>
	 <decorator:title default="Welcome" />-<%=SysconfigFuc.getSysValue("cfg_webname") %>-${store_name}管理
    </title>
    <script type="text/javascript">
$(document).ready(function(){
	//隔行变色,判断是否存该控件
	if($(".indexTab").length>0){
		$(".indexTab").mallTable();	
	}
	//操作框
	$(".buttom").floatdiv("center-bottom");
	//提示信息
	$(".ltip").lighttip();
	//图片展示
	$(".group,.showpic").click(function(){
		$(this).colorbox({rel:'group'});
	});
})
</script>
</head>
<body>

<input type="hidden" id="file_size" value="<%=SysconfigFuc.getSysValue("cfg_filesize")%>"/>
<input type="hidden" id="flash_size" value="<%=SysconfigFuc.getSysValue("cfg_flashsize")%>"/>
<input type="hidden" id="image_size" value="<%=SysconfigFuc.getSysValue("cfg_imgsize")%>"/>
<input type="hidden" id="image_type" value="<%=SysconfigFuc.getSysValue("cfg_imgtype")%>"/>
<input type="hidden" id="flash_type" value="<%=SysconfigFuc.getSysValue("cfg_mediatype")%>"/>
<input type="hidden" id="file_type" value="<%=SysconfigFuc.getSysValue("cfg_attachtype")%>"/>
<input type="hidden" id="img_cust_id" value="<%=session.getAttribute(Constants.CUST_ID)%>"/>

<s:form  action="" id="commonForm">
	<s:hidden name="" id="commonId" value=""/>	
	<s:hidden name="" id="commonText" value=""/>
	<input type="hidden" name="token_value" value="${get_token_value}"/>	
	<s:hidden name="" id="rsrv1" value=""/>
	<s:hidden name="" id="rsrv2" value=""/>
	<s:hidden name="" id="rsrv3" value=""/>
	<s:hidden name="" id="rsrv4" value=""/>
	<s:hidden name="" id="rsrv5" value=""/>
	<s:hidden name="" id="rsrv6" value=""/>
	<s:hidden name="" id="rsrv7" value=""/>
	<s:hidden name="" id="rsrv8" value=""/>
	<s:hidden name="" id="rsrv9" value=""/>
	<s:hidden name="" id="rsrv10" value=""/>
	<div id="hidden_div">
		${listSearchHiddenField}
	</div>
</s:form>
 <%! 
    	public String getFirstMenuIdByList(List topMenuList,String menu_rights,String user_type) {
			String menu_id = "";
			if (topMenuList == null || topMenuList.size() == 0){
					return "";
			}else{
				if(user_type!=null && "3".equals(user_type)){
					HashMap menuMap = (HashMap) topMenuList.get(0);
				    return menu_id=menuMap.get("menu_id").toString();
				}				
				for(int i=0;i<topMenuList.size()-1;i++){
				     HashMap menuMap = (HashMap) topMenuList.get(i);
				    System.out.println("zmenu_id:"+menuMap.get("menu_id").toString() );
				     if(menuMap.get("menu_id") != null&&menu_rights.contains(menuMap.get("menu_id").toString())){
					      menu_id=menuMap.get("menu_id").toString();
					      break;
				     }
				}
			}
			return menu_id;
		}
    %>   
    <%    
    	//syscode sys:运营商 com:会员
		//user_type: 1：会员管理员 2：会员子账号 3：运营商管理员 4：运营商子账号
		
		//根据上一级ID找出属于上一级的列表
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", "sys");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		map.put("enabled","0");
		//菜单权限串，登陆的时候放进去的
		String menu_right = "";
		if(session.getAttribute("menu_right")!=null){
			menu_right = session.getAttribute("menu_right").toString();
		}
			System.out.println(menu_right);    
		//操作权限串，登陆的时候放进去的
		String oper_right = "";
		if(session.getAttribute("oper_right")!=null){
			oper_right = session.getAttribute("oper_right").toString();
		}		
		//out.println(menu_right+"==="+oper_right);   
    	//user_type: 1：会员管理员 2：会员子账号 3：运营商管理员 4：运营商子账号
    	String user_type = "";
		if(session.getAttribute(Constants.USER_TYPE)!=null){
			user_type = session.getAttribute(Constants.USER_TYPE).toString();
		}  
		//根据系统后台、一级菜单找出菜单信息
		List topMenuList = SysmenuFuc.getSysmenuList(map);
		//out.println(topMenuList.size());    
    	String frist_menu_id = "";
		if (request.getParameter("up_menu_id") != null && !request.getParameter("up_menu_id").toString().equals("")) {
			frist_menu_id = request.getParameter("up_menu_id");
		} else if (session.getAttribute("first_menu_id") != null && !session.getAttribute("first_menu_id").toString().equals("")) {
			frist_menu_id = session.getAttribute("first_menu_id").toString();
		}else{
		    frist_menu_id = getFirstMenuIdByList(topMenuList,menu_right,user_type);
		}
		if(frist_menu_id!=null&&!"".equals(frist_menu_id)){
			session.setAttribute("first_menu_id", frist_menu_id);
		}
    %>   
     <!-- 一级菜单读取开始 -->    
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
     			String cssStr = "";
     			if(frist_menu_id.equals(menu_id)){
     				cssStr = " class=\"hli\"";
     			}
     			if(!user_type.equals("3")){
     				if(menu_right.indexOf(menu_id) == -1){
	     				continue;
	     			}
     			}
     %>    
            
             <%    			 
     		}
     	}
     	
     %>
<div class="topbar-wrap white">
    <div class="topbar-inner">
        <div class="topbar-logo-wrap">
          <a href="#">${cfg_webname}${store_name}</a>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="/" target="_blank" class="mapxd">商城首页</a></li>
			    <li><a href="/agentindex.action">桌面</a></li>
                <li><a href="#">欢迎您，${user_name} </a></li>
                <li><a href="/agent_Asysuser_logout.action">[退出]</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-content">
            <ul class="sidebar-list">
             <% 
	String parentMenuId = "";
	if (request.getParameter("parentMenuId") != null && !request.getParameter("parentMenuId").toString().equals("")) {
		parentMenuId = request.getParameter("parentMenuId");
	} else if (session.getAttribute("parentMenuId") != null && !session.getAttribute("parentMenuId").toString().equals("")) {
		parentMenuId = session.getAttribute("parentMenuId").toString();
	}
	session.setAttribute("parentMenuId", parentMenuId);
	if (request.getParameter("up_menu_id_o") == null || "".equals(request.getParameter("up_menu_id_o").toString())) {
		if (request.getParameter("up_menu_id") != null && !request.getParameter("up_menu_id").toString().equals("")) {
			parentMenuId = "";
		} 
	} 
	
	
%>
 <%	
		List downList = new ArrayList();
		HashMap downMap = new HashMap();
		List menuList = SysmenuFuc.getMenuListByUpmenuid(frist_menu_id,"sys");
		
		if(menuList!=null && menuList.size()>0){
			int s = 0;
			for (Iterator iter = menuList.iterator(); iter.hasNext();) {
				HashMap menuMap = (HashMap)iter.next();
				String menu_id = "",menu_name="";
				if(menuMap.get("menu_id")!=null) menu_id = menuMap.get("menu_id").toString();
				if(menuMap.get("menu_name")!=null) menu_name = menuMap.get("menu_name").toString();
				
				if(!user_type.equals("3")){
     				if(menu_right.indexOf(menu_id) == -1){
	     				continue;
	     			}
     			}
				
				
				//三级菜单获取
				downList = SysmenuFuc.getMenuListByUpmenuid(menu_id,"sys");
				
				int downSize = 0;
				if(downList!=null && downList.size()>0){
					downSize = downList.size();
				}
%>
			     <li>
                    <a href="#"><i class="icon-font">&#xe005;</i><%=menu_name %></a>
                    <ul class="sub-menu">
                     <% 
				  		
				  		if(menuList!=null && menuList.size()>0){
				  			int k = 0;
							for (Iterator iters = downList.iterator(); iters.hasNext();) {
								downMap = (HashMap)iters.next();
								String down_menu_id = "",down_menu_name="",down_url="",target="";
								if(downMap.get("menu_id")!=null) down_menu_id = downMap.get("menu_id").toString();
								if(downMap.get("menu_name")!=null) down_menu_name = downMap.get("menu_name").toString();
								if(downMap.get("url")!=null) down_url = downMap.get("url").toString();
								if(downMap.get("target")!=null) target = downMap.get("target").toString();
								String liCss = "";
								if(!user_type.equals("3")){
				     				if(menu_right.indexOf(down_menu_id) == -1){
					     				continue;
					     			}
				     			}
				     			String para = "?";
				     			if(down_url.indexOf("?") > -1){
				     				para = "&";
				     			}
				  %>
                        <li><a href="<%=down_url %><%=para %>parentMenuId=<%=menu_id %>" target="<%=target %>"><i class="icon-font">&#xe008;</i><%=down_menu_name %></a></li>
                         <%
								k++;
							}
						}
				  %>
                    </ul>
                </li>
               <%
    			s++;
			}
		}
%>       
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">
    <decorator:body/>
    </div>
    <!--/main-->
</div>

<script type="text/javascript">  
//提示信息
var actionMessage = '<s:actionmessage/>';
if(actionMessage!=''){
	var dialogTip = art.dialog({
	    button: [{
	        id: 'confirm',
	        value: '确定 (5)',
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
	var i = 5;
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
//更新缓存
function renewload(){
	$("#reloadimg").show();
	$.ajax({
        type: "post",
        url: "sysuser!reload.action",
        datatype:"json",
        async:false,
        success: function(data){ 
       		$("#reloadimg").hide();
        	if(data==1){
        		jSuccess("更新缓存成功!");
        	}else{
        		jNotify("更新缓存失败,请稍后重试!");
        	}
        }	 
	}); 
}
</script>
</body>
</html>