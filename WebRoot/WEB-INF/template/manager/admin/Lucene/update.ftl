<html>
  <head>
     <title>更新管理</title>
     <script type="text/javascript" src="/include/common/js/jqModal.js"></script>
     <script type="text/javascript" src="/include/components/progressbar/js/jquery.progressbar.min.js"></script>
     <script type="text/javascript"  src="/include/common/js/jquery.timers.js"></script>
	 <script type="text/javascript" src="/include/common/js/commonplugin.js"></script>
	 <script type="text/javascript" src="/include/admin/js/luindex.js"></script>
	 <link href="/include/admin/css/seoset.css" rel="stylesheet" type="text/css">
	 
	 <!--栏目页引入开始-->
	 	<link rel="stylesheet" href="/include/common/css/zTreeStyle.css" type="text/css">	
		<script type="text/javascript" src="/include/admin/js/channel.js"></script>
		<script type="text/javascript" src="/include/admin/js/channelTree.js"></script>
		<script type="text/javascript" src="/include/common/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="/include/common/js/jquery.ztree.excheck-3.5.js"></script>
	 <!--栏目页引入结束--->
	 <script type="text/javascript">
		$(document).ready(function(){
		 	$.fn.zTree.init($("#channe"), setting,${jsonMenu?if_exists});
			//tab切换页
			$("#oper_seo_div").mallTab({
			
			});
		});
		function updateVal(index){
			if(index=='1'){
				$("#menu_code").val("first");
			}else if(index=='2'){
				$("#menu_code").val("column");
			}
		}
	</script>
	 <style type="text/css">
     .mod_ul{width:100%}
     .mod_li{width:17%;float:left;}
    </style>
  </head>
<body >
<@s.form action="" method="post" id="indexForm">

<!--当前位置开始-->
<div class="postion">
 当前位置:网站管理 > 网站更新 > 更新管理
</div>

 <div class="rtdcont">
  
		<div id="oper_seo_div" class="oper_seo_div">   
		 		<div class="tabbar">
				    <ul id="meu">
				   		<li id="index" <#if menu_code=='first'>class="selected"</#if> onclick="updateVal(1);">模块索引</li>
				   		<li id="column" <#if menu_code=='column'>class="selected"</#if> onclick="updateVal(2);">栏目页面HTML</li>
				    </ul>
		        </div>
		       
		<div class="clear"></div>
					       
	       
	        <div class="tabdiv" <#if menu_code=='first'>style="display:block"</#if>>
				<div class="rdtable">
					<table class="wwtable"  width="100%" cellspacing="1" cellpadding="8" >
				           <tr>
				            <td height="10">
				           <input type="checkbox" name="checkbox" id="checkall" onclick="selectAllBoxs('checkall','commpara.para_value')">全选  
				           </td>
				           </tr>
				           <tr>
				                  <td  height="10">
				                   <ul class="mod_ul"> 
									<#list commparalist as commpara>
									<li class="mod_li" style="margin-top:10px;padding-bottom:10px;"><input type="checkbox" name="commpara.para_value" value="${commpara.para_value?if_exists}" />&nbsp;${commpara.para_key}</li>
									</#list>
									</ul>
									  </td></tr>
									  
									  <tr><td align="center" height="30">
									<input type="button" onclick="createAddIndex('commpara.para_value');" value="增量更新索引"/>
									<input type="button" onclick="updateAllIndex('commpara.para_value');" value="全量更新索引"/>
									<input style="display:none;" id="cancelIndex" type="button" onclick="stopindex();" value="取消更新索引"/>
									<span id="updatebar" style="display:none;"><span>更新进度:</span><span class="progressBar" id="msg"></span><span id="successmsg"></span><span>
							        <span id="text"></span>
									  </td></tr>
									  <tr><td>&nbsp描述：<font color="red">增量更新索引</font>是更新新添加的数据，<font color="red">全量更新索引</font>是更新所有数据。</td></tr>
									   <tr>
										<div class="bsbut">
											<b>&nbsp;系统提示: </b>当前采用索引的更新机制为:<font color="red"><#if cfg_indexing=="0">Solr<#else>Lucene</#if></font> <font color="green">${sys_search_time}</font>
											</div>
										</tr>
				         </table>
					
				</div>
	       </div>
	       <div class="tabdiv" <#if menu_code=='column'>style="display:block"</#if> >
	       <div class="rdtable">
					<table class="wwtable"  width="100%" cellspacing="1" cellpadding="8" >
				           <tr>
				            <td height="10">
				            <input type="checkbox" name="checkboxchannel" id="checkalls" onclick="selectAllBoxs('checkalls','channel.ch_id')">全选 
				           </td>
				           </tr>
				           <tr>
				                  <td  height="10">
				                   <ul class="mod_ul"> 
									 <#list channelList as channel>
									<li class="mod_li" style="margin-top:10px;padding-bottom:10px;"><input type="checkbox" name="channel.ch_id" value="${channel.ch_id?if_exists}" />&nbsp;${channel.ch_name?if_exists}</li>
									</#list>
									</ul>
									  </td></tr>
									  
									  <tr><td align="center" height="30">
									<input  value="更新PC端栏目" type="button" onclick="doHtmlPage('channel.ch_id','0');">
						          <div id="loading" style="display:none;">
						           <img src="/include/admin/images/upLoading.gif" style="width:30px; height:30px;">&nbsp;更新中...
						          </div>
						          <font color="red"><label id="msg2" ></label></font>
									  </td></tr>
									  <tr><td>&nbsp描述：<font color="red">更新PC版栏目页</font>是更新PC版栏目展示。</td></tr>
									   <tr>
										<div class="bsbut">
											<b>&nbsp;系统提示:</b>更新PC版或者触屏版栏目页
											</div>
										</tr>
				         </table>
					
				</div>
				
				
				<div class="rdtable">
					<table class="wwtable"  width="100%" cellspacing="1" cellpadding="8" >
				           <tr>
				            <td height="10">
				            <input type="checkbox" name="checkboxchannel" id="checkwebappalls" onclick="selectAllBoxs('checkwebappalls','channel.webapp_ch_id')">全选 
				           </td>
				           </tr>
				           <tr>
				                  <td  height="10">
				                   <ul class="mod_ul"> 
									 <#list channelWebAppList as channel>
									<li class="mod_li" style="margin-top:10px;padding-bottom:10px;"><input type="checkbox" name="channel.webapp_ch_id" value="${channel.webapp_ch_id?if_exists}" />&nbsp;${channel.ch_name?if_exists}</li>
									</#list>
									</ul>
									  </td></tr>
									  
									  <tr>
									    <td align="center" height="30">
											<input  value="更新触屏版栏目" type="button" onclick="doHtmlPage('channel.webapp_ch_id','1');">
								            <div id="webAppLoading" style="display:none;">
								               <img src=/include/admin/images/upLoading.gif style="width:30px; height:30px;">&nbsp;更新中...
								            </div>
								            <font color="red"><label id="msg3" ></label></font>
										</td>
									  </tr>
									  <tr><td>&nbsp描述：<font color="red">更新触屏版栏目页</font>是更新触屏版栏目展示。</td></tr>
				         </table>
					
				</div>
	       
	       </div>

	 </div>
</div>

<div class="clear"></div>

<!--停止进度条开始-->
<div id="loaderbar" class="jqmWindow loaderbar">
	<img src="/include/common/images/loaderbar.gif"/>
</div>
<!--停止进度条结束-->   
<@s.hidden id="menu_code" name="menu_code"/>
<@s.hidden name="sysmenu_sortno_id" id="sysmenu_sortno_id"/>
</@s.form>  
</body>
</html>