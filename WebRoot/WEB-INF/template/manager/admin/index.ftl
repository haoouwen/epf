<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>后台首页</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--特殊页面的容器-->
<@s.form action="/admin_Sysuser_update.action" method="post" validate="true" id="modiy_form">
<div class="postion_index"></div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	 
	   <div class="main_cont">
    <div class="w945  w_marlef">     
   <div class="lcon_main f_left">        
      <div class="connet w_padtop">
          <h2 class="con_title">订单消息</h2>
	          <div class="clear"></div>
	          <div class="con_connents">
		          <table width="100%" >
		          <tr>
			          <td class="yw_a2">
			          		总订单:<a href="/admin_Goodsorder_allorderlist.action?parentMenuId=1546322347&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${aOrder})</font></a>
			          </td>
			            <td class="yw_a2">
			          今日订单:<a href="/admin_Goodsorder_allorderlist.action?str_today=1&parentMenuId=1546322347&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${todayOrder})</font></a>  
			          </td>
		          </tr>
		            <tr>
			          <td class="yw_a2">
			          		 直邮订单:<a href="/admin_Goodsorder_operatorslist.action?parentMenuId=1546322347&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${yzorder})</font></a>
			          </td>
			            <td class="yw_a2">
			           保税订单:<a href="/admin_Goodsorder_baoTaxlist.action?parentMenuId=1546322347&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${bsorder})</font></a>
			          </td>
		          </tr>
		           <tr>
			          <td class="yw_a2">
			          		 待发货:<a href="/admin_Goodsorder_allorderlist.action?order_state_s=2&parentMenuId=1546322347&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${goCount})</font></a>
			          </td>
			            <td class="yw_a2">
			            待退款:<a href="/admin_Goodsorder_allorderlist.action?order_state_s=4&parentMenuId=1546322347&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${rCount})</font></a>
			          </td>
		          </tr>
		          </table>
	          	  <div class="clear"></div>
	          </div>
       </div>            
  </div>   
  
  
  
  
  
  
  
    <div class="rcon_main f_right">

      <div class="r_connent" style="margin-top:10px;">
           <div class="rcon_title"><h2 class="rcon_font">操作记录</h2><a href="/admin_Logs_list.action?user_name=${opername?if_exists}" class="more">更多>></a></div>
          <div class="clear"></div>
          <div class="rcon_connent">
            <ul>
              <#list loglist as logs>
                  <li >
                  	<span class="logname">
                  		<#if logs.content?if_exists!=''>
                  			<#if logs.content?length lt 30>
					 			${logs.content?if_exists}
				  			<#else>
					  			${logs.content[0..29]}...    
				   			</#if>
				   		</#if>
				   	</span>
                  	<span>${logs.in_date[0..9]?if_exists}</span>
                  </li>
              </#list>
            </ul>
          </div>     
      </div>
      
      
      
      <div class="r_connent w_padtop">
           <div class="rcon_title"><h2 class="rcon_font">帐号信息</h2></div>
          <div class="clear"></div>
          <div class="rcon_connent">
             <p class="p_font">尊敬的${opername?if_exists}先生：此次是你第${logintimes?if_exists}次登陆</p>
             <p class="p_font">${session.web_name?if_exists}</p>
             <p class="p_font">你上次登陆的时间是：
             <#if lastlogintime?if_exists gt 18>
             	${lastlogintime?if_exists[0..18]}
             </#if>
             </p>
             <p class="p_font w_padbot">你上次登陆的IP是：${lastIp?if_exists}</p>
             <div class="clear"></div>  
          </div>
      </div>  
    </div><!---rcon_main end-->
        
        
        
        
  
  <div class="lcon_main f_left">        
      <div class="connet w_padtop">
          <h2 class="con_title">咨询消息</h2>
	          <div class="clear"></div>
	          <div class="con_connents">
		          <table width="100%" >
		          <tr>
			          <td class="yw_a2">
			          		总咨询:<a href="/admin_Consultation_groupList.action?parentMenuId=4622455812&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${acoCount})</font></a>
			          </td>
			            <td class="yw_a2">
			            待回复:  <a href="/admin_Consultation_groupList.action?parentMenuId=4622455812&re_date=0&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${coCount})</font></a>
			          </td>
		          </tr>
		         
		          </table>
	          	  <div class="clear"></div>
	          </div>
       </div>            
  </div>   
        
        
        
       <div class="lcon_main f_left">        
      <div class="connet w_padtop">
          <h2 class="con_title">评价消息</h2>
	          <div class="clear"></div>
	          <div class="con_connents">
		          <table width="100%" >
		          <tr>
			          <td class="yw_a2">
			          		好评:<a href="/admin_Goodseval_alllist.action?parentMenuId=4622455812&g_eval_s=1&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${gehCount})</font></a>
			          </td>
			            <td class="yw_a2">
			          中评:<a href="/admin_Goodseval_alllist.action?parentMenuId=4622455812&g_eval_s=0&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${gezCount})</font></a>
			          </td>
			            <td class="yw_a2">
			         差评: <a href="/admin_Goodseval_alllist.action?parentMenuId=4622455812&g_eval_s=-1&up_menu_id=1215258758&up_menu_id_o=1215258758"><font color="red">&nbsp;(${gecCount})</font></a>
			          </td>
		          </tr>
		          </table>
	          	  <div class="clear"></div>
	          </div>
       </div>            
  </div>   
        
        
        
        
        
   <div class="lcon_main f_left">        
      <div class="connet w_padtop">
          <h2 class="con_title">系统消息</h2>
	          <div class="clear"></div>
	          <div class="con_connents">
		          <table width="100%" >
		           <tr ><td class="yw_a2">安装版本</td><td class="yw_a22">企业版本</td></tr>
		           <tr ><td class="yw_a2">安装时间</td><td class="yw_a22">${install_date?if_exists}</td></tr>
		           <tr ><td class="yw_a2">服务器时间</td><td class="yw_a22">${server_datetime?if_exists} </td></tr>
		           <#--
		           <tr ><td class="yw_a2">服务器信息</td><td class="yw_a22">${OS_name?if_exists}&nbsp;${server_info?if_exists}[${server_ip?if_exists}] </td></tr>
		           <tr ><td class="yw_a2">数据库版本</td><td class="yw_a22">MySQL&nbsp;${database_product_version?if_exists} </td></tr>
		           <tr ><td class="yw_a2">数据库名</td><td class="yw_a22">${database_name?if_exists}&nbsp; </td></tr>-->
		          </table>
	          	  <div class="clear"></div>
	          </div>
       </div>            
  </div>        
  
  
        
 </div><!--main end-->
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>


</body>
</html>
