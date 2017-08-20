
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title></title>
	</head>
	<body>

<div id="main">
<#list adminmenuList  as menufirst>
   <div class="first">    
     <#if menufirst.menu_level=='1'>
                <div style="height:23px;">  
                <#if menufirst.menu_level=='1'>  
                      <a href="${menufirst.url}">
                       <span style="font-size:14px;font-weight:bold;color:#F88207;">${menufirst.menu_name}</span>
                      </a>   
                </#if>
                 </div>
                 <div class="third">  
                <#list adminmenuList  as menusecond> 
	                     <#if menusecond.up_menu_id==menufirst.menu_id>  
	                          <div style="height:21px;">
	                            
	                           <a>
	                            <span style="font-size:13px;font-weight:bold;color:#252525;margin-left:20px;margin-right:20px;">${menusecond.menu_name}</span>
	                           </a>
	                           	  
	                       	      <#list adminmenuList  as menuthird> 
                                       <#if menuthird.up_menu_id==menusecond.menu_id> 
	                                       <a href="${menuthird.url}" style="width:90px;display:inline-block;">
	                                         <span style="font-size:12px;color:#252525;">${menuthird.menu_name}</span>
	                                       </a> 
	                                  </#if>
	                              </#list>
	                          </div>
	                     </#if>
                </#list>
                </div>
     </#if>
     </div>         
</#list>  
</div>
			
</body>
</html>

