<html>
  <head>
    <title>充值卡列表</title>
  </head>
  <body>
<@s.form action="/admin_Rechargeablecards_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销管理 > 促销管理 > 充值卡</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >充值卡卡号:</td><td><@s.textfield name="cardskey" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('rechargeablecards.cardid')"/></th>
                 
                 	<th width="10%">充值卡编号</th>
			    
                 	<th width="10%">充值卡卡号</th>
			    
                 	<th width="10%">金额(元)</th>
			    
                 	<th width="10%">截至有效日期</th>
			    
                 	<th width="10%">状态</th>
			    
                 	<th width="10%">充值时间</th>
			    
               </tr>
			    <#list rechargeablecardsList as rechargeablecards>
				 <tr >
			         <td><input type="checkbox" name="rechargeablecards.cardid" value="${rechargeablecards.cardid?if_exists}"/></td>
			         
			         	<td align="center">${rechargeablecards.cardid?if_exists}</td>
				    
			         	<td align="center">${rechargeablecards.cardskey?if_exists}</td>
				    
			         	<td align="center">${rechargeablecards.cardsmoney?if_exists}</td>
				    
			         	<td align="center">${rechargeablecards.cardsdate?if_exists}</td>
				    
			         	<td align="center">
			         	<#if rechargeablecards.cardsstate?if_exists=="0">
			         	<font color="blue">未充值</font>
			         	<#elseif  rechargeablecards.cardsstate?if_exists=="1">
			         	<font color="green">已充值</font>
			         	</#if>
			         	</td>
				    
			         	<td align="center">${rechargeablecards.cardsuseddate?if_exists}</td>
				      
				    
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Rechargeablecard_list.action','');" value="返回">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放-->
<@s.hidden  name="rechargeablecards.cardid"/>
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Rechargeablecards_list.action"   method="post"  id="form_search_id">
<!--dd-->
	<table>
	<tr>
		<td class="searchDiv_td">标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	
    <tr>
		<td align="center" colspan="2" style="border:0px;">
		<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
		   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
		</td>
	</tr>
	</table>
</@s.form>
</div>

  </body>
</html>

