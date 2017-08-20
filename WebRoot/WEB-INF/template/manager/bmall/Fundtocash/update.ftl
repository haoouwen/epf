<html>
<head>
<title>查看余额提现</title>
<link rel="StyleSheet" href="/include/bmember/css/account.css" type="text/css" />
</head>
<body>
	
<@s.form action="/bmall_Fundtocash_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="wR810">
    <div class="rwTitle">
     	<h2><span>查看余额提现</span></h2>
      <div class="accontDiv">
          <table width="100%" cellpadding="0" cellspacing="0" class="ainfoTab">
          
	        <tr><th>余额：</th><td>
        	<@s.label name="fundtocash.fund_num" cssClass="txtinput"/>
            </td></tr>
              
            <tr><th>收款方式：</th>
        	 <td>
                 <@s.label name="sgetcash_type" cssClass="txtinput"/>        	           	            
             </td>
            </tr>  
            
            <tr><th>收款账号：</th><td>
        	 <@s.label name="fundtocash.account" cssClass="txtinput"/>
            </td></tr>  
            
            <tr><th>账号姓名：</th><td>
        	<@s.label name="fundtocash.account_name" cssClass="txtinput"/>
            </td></tr> 
            
			<tr><th>开户网点：</th><td>
			<@s.label name="fundtocash.branch" cssClass="txtinput"/>
	        </td></tr> 
            <tr>
	            <th>申请时间：</th><td>
	        		<#if fundtocash.in_date?if_exists?length gt 19>
	        				${fundtocash.in_date?if_exists[0..18]}
		           	<#else>
		           			${fundtocash.in_date?if_exists}
		           	</#if>
	            </td>
            </tr>
            
            <tr><th>状态：</th><td>
        	<#if fundtocash.order_state?if_exists=='0'>未审核
		    <#elseif fundtocash.order_state?if_exists=='1'>审核通过
		    <#elseif fundtocash.order_state?if_exists=='2'>审核未通过
		    <#elseif fundtocash.order_state?if_exists=='3'>已处理
		    <#elseif fundtocash.order_state?if_exists=='4'>作废</#if>
            </td></tr> 
            
            <tr>
			    <th>备注：</th>
			    <td>
			     <@s.label name="fundtocash.remark" cssClass="txtinput"/>
			    </td>
		    </tr>

            <tr>
             <td colspan="2" align="center">
	             <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundtocash_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"/>
	         </td>
	             
            </tr> 
          </table>
       </div>  
     </div> 
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

