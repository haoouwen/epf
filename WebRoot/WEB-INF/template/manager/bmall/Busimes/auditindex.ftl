 <html>
<head>
<title>我的留言</title>
 <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js" ></script> 
</head>
<body>

<@s.form action="/bmall_Busimes_auditList.action" method="post" id="indexForm">
   <div class="rightside f_right">
   <div class="postion">
 		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的留言</a>
	</div>
     <div class="rpostion"><h2>
	         <td class="fthstyle1">我的交易</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">我的留言</td>
       </span></h2>
      <table width="100%">
       		<tr>
					<td align="right">商店名称:</td> 
					<td><@s.textfield name="cust_name_s"  maxLength="50"/></td>
					<td align="right">状态:</td>
					<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}" /></td>	
					
					<td rowspan="2" width="20%">
						<@s.submit name="" value="" cssClass="sbut"/>
					</td>
			</tr>
			<tr>
					<td align="right">留言时间段:</td> 
					<td>
						<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 						
					至						
						<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
						               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>			
					<td align="right">回复时间段:</td>
					<td>
						<@s.textfield id="txtstartDate" name="rstart_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 		
						至		
						<@s.textfield id="txtendDate" name="rend_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>		
		  </tr>
       	</table>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr>
	         <td class="fthstyle1" width="15%">商店名称</td>
	         <td class="fthstyle1" width="15%">留言内容</td>
	         <td class="fthstyle1" width="10%">留言时间</td>
	         <td class="fthstyle1" width="10%">回复人</td>
	         <td class="fthstyle1" width="10%">回复内容</td>
	         <td class="fthstyle1" width="10%">回复时间</td>
	         <td class="fthstyle1" width="10%">状态</td>
	         <td class="fthstyle1" width="5%">操作</td>
         <tr>
         <#list busimesList as busimes>
         <tr style="text-align:center;">
		     <td>
	         ${busimes.cust_name?if_exists}
		     </td>
		     <td>
		     <#if busimes.msg_content?length lt 10 > 
    	     ${busimes.msg_content?if_exists}
    	     <#else>
    	     ${busimes.msg_content?if_exists[0..9]}
    	     </#if>
		     </td>
		     <td>${busimes.msg_date?if_exists}</td>
		     <td>
		     <#if busimes.get_user_id?if_exists==''>暂无回复<#else>${busimes.get_user_id?if_exists}</#if>
		     </td>
		     <td>${busimes.re_content?if_exists}</td>
		     <td>${busimes.re_date?if_exists}</td>
		     <td><#if busimes.is_enable?if_exists=='0'>有效<#else>无效</#if></td>
		     <td> 
	         <a onclick="linkToInfo('/bmall_Busimes_audit.action','busimes.trade_id=${busimes.trade_id?if_exists}');">查看</a>
	         </a>
	         </td>
         </tr>
         </#list>
       </table>
      
        <div class="listbottom">
        ${pageBar?if_exists}
        </div>
      </div>     
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

