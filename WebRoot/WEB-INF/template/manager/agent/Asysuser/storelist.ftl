<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>门店销售统计</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <script src="/include/admin/js/sysuser.js" type="text/javascript"></script>	
	<@s.form action="/agent_Asysuser_list.action" method="post" id="indexForm">
<@s.hidden name="asysuser.state" id="admin_user_state"/>
 <div class="crumb-wrap">
          <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
			经营统计<span class="crumb-step">&gt;</span>门店销售统计</div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="70">门店号:</th>
                            <td>
                            <@s.textfield name="nike_name_s" cssClass="common-text" cssStyle="width:260px;"/>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="search-content">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp${store_name}销售总金额：<#if totalAreaAmountMap !=null && totalAreaAmountMap!="">${totalAreaAmountMap.get("totalAreaAmount")} </#if>元 
            </div>
            
        </div>
        <div class="result-wrap">
                
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>门店号</th>
                            <th>门店销售额（元）</th>
                            <th>状态</th>
                        </tr>
                        <#list userList as user>
                        <tr align="center">
                            <td>${user.nike_name?if_exists}</td>
                             <td>${user.stroe_amount?if_exists}</td>
                            <td ><#if user.state?if_exists=='0'><font color='green'>启用</font><#else><font color='red'>禁用</font></#if>
                        </tr>
                        </#list>
                    </table>
                  <div class="pages">
				     ${pageBar?if_exists}
				   </div>
                </div>
           
        </div>
   </div>
   
</@s.form>
</body>
</html>
