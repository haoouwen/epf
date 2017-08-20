<html>
<head>
<title>收货地址列表</title>
<link href="/include/bmember/css/email.css" rel="stylesheet" type="text/css">
<script src="/include/bmember/js/member.js" type="text/jscript"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script type="text/javascript" src="/include/common/js/getArea.js"></script>
<script type="text/javascript">
  $(function(){
    	loadAreas("${areaIdStr?if_exists}","");
  })
</script>
</head>
<body>

<@s.form action="/bmall_Buyeraddr_list.action" method="post" id="indexForm">

<div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>收货地址</span></h2>
        <div class="address">
           <!---->
           <div class="addrDiv">		
               <p><input type="button" class="addrbut">&nbsp;&nbsp;(最多保存&nbsp;${cfg_maxaddressnumber}&nbsp;个收货地址)</p>
               <table width="100%" cellpadding="0" cellspacing="0" class="addAtab" id="addAtab">
                    <tr><th width="15%"><span>*</span>收货人：</th><td width="85%"><input type="text" class="aifText" id="consignee" name="consignee" maxLength="10"></td></tr>
                    <tr><th><span>*</span>身份证：</th><td width="90%"><input type="text" class="aifText" id="identitycard" name="identitycard"  maxLength="18"></td></tr>
                    <tr><th><span>*</span>所在地区：</th><td><div id="areaDiv"></div></td></tr>
                    <tr><th><span>*</span>详细地址：</th><td><input type="text" class="aisText" id="address"  name="address"  maxLength="50"></td></tr>
                    <tr><th>邮编：</th><td><input type="text" class="aifText" id="post_code"  name="post_code" onkeyup="checkNum(this)"  maxLength="6"></td></tr>
                    <tr><th><span>*</span>固定电话：</th><td><input type="text"  id="area_code" name="area_code"  maxLength="4" onkeyup="checkNum(this)" class="qhtext">-<input type="text" class="aifText" id="phone" onkeyup="checkNum(this)" maxLength="8" ><span>格式：区号 - 电话号码</span></td></tr>
                    <tr><th><span>*</span>手机号码：</th><td><input type="text" class="aifText" id="cell_phone" name="cell_phone" onkeyup="checkNum(this)" maxLength="11" /><span>电话和手机请至少填写一个</span></td></tr>
                    <tr><th></th><td><input type="button" class="submitbut" value="提交" onclick="addAddr();">&nbsp;&nbsp;<input type="button" class="exitbut" value="退出" onclick="exit('addAtab')"></td></tr>
               </table>
           </div>
           <!---->
          <#list buyeraddrList as buyeraddr>
           <table width="100%" cellpadding="0" cellspacing="0" class="addrTab" id="${buyeraddr.addr_id?if_exists}">
           	   <tr><th width="15%">收货人</th><td  width="35%">
           	   <#if buyeraddr.consignee?if_exists!=null && buyeraddr.consignee?if_exists!=''>
          	       ${buyeraddr.consignee?if_exists}
          	  <#else>
          	     -
          	  </#if> 
          	   </td><th  width="15%">身份证</th><td  width="35%">${buyeraddr.identitycard?if_exists}</td></tr>
               <tr><th>所在地区</th><td>${buyeraddr.area_attr?if_exists}</td><th>地址</th><td>${buyeraddr.address?if_exists}</td></tr>
               <tr><th>手机</th><td><#if buyeraddr.cell_phone?if_exists>${buyeraddr.cell_phone?if_exists}<#else>-</#if></td><th>固定电话</th><td><#if buyeraddr.phone?if_exists>${buyeraddr.phone?if_exists}<#else>-</#if></td></tr>
               <tr><th  width="15%">邮编</th><td colspan="3">${buyeraddr.post_code?if_exists}</td></tr>
               <tr><td colspan="4"><input type="button" class="graybut" value="编辑" onclick="getAddr('${buyeraddr.addr_id?if_exists}')">&nbsp;&nbsp;<input type="button" class="deletebut" value="删除" onclick="delAddr('${buyeraddr.addr_id?if_exists}');">
               <table width="100%" cellpadding="0" cellspacing="0" class="addAtab" id="up${buyeraddr.addr_id?if_exists}">
                    
               </table>
               </td>
               </tr>
           </table>
          </#list> 
           <!---->
        </div>     
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden value="${areaIdStr?if_exists}" id="areaIdStr"/>  
</@s.form>
</body>
</html>

