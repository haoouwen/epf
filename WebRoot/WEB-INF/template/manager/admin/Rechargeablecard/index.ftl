<html>
  <head>
    <title>充值卡列表</title>
  </head>
  <body>
<@s.form action="/admin_Rechargeablecard_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销推广 > 促销管理 > 充值卡管理</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >充值卡编号:</td><td><@s.textfield name="cardid" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('rechargeablecard.cardid')"/></th>
                 
                 	<th width="10%">充值卡编号</th>
			    
                 	<th width="10%">名称</th>
			    
                 	<th width="10%">金额(元)</th>
			    
                 	<th width="10%">总数</th>
			    
                 	<th width="10%">已充值数</th>
                 	
                 	<th width="10%">状态</th>
			    
                 	<th width="10%">截止有效日期</th>

                 <th  width="10%">操作</th>
               </tr>
			    <#list rechargeablecardList as rechargeablecard>
				 <tr >
			         <td><input type="checkbox" name="rechargeablecard.cardid" value="${rechargeablecard.cardid?if_exists}"/></td>
			         
			         	<td align="center">${rechargeablecard.cardid?if_exists}</td>
				    
			         	<td align="center">${rechargeablecard.cardname?if_exists}</td>
				    
			         	<td align="center">${rechargeablecard.cardmoney?if_exists}</td>
				    
			         	<td align="center">${rechargeablecard.cardnum?if_exists}</td>
				    
			         	<td align="center">${rechargeablecard.cardused?if_exists}</td>
			         	
			         	<td align="center">
			         	<#if rechargeablecard.cardstate?if_exists=='0'>
			         	  未生成充值卡
			         	<#elseif  rechargeablecard.cardstate?if_exists=='1'>
			         	  已生成充值卡
			         	</#if>
			         	</td>
				    
			         	<td align="center">${rechargeablecard.carddate?if_exists}</td>
				    
				    <td align="center">
				    <#if rechargeablecard.cardstate?if_exists=='0'>
				      <a onclick="linkToInfo('/admin_Rechargeablecard_view.action','rechargeablecard.cardid=${rechargeablecard.cardid?if_exists}');">
					  <img src="/include/common/images/bj.gif" /></a>
					  <a onclick="linkToInfo('/admin_Rechargeablecard_createcard.action','rechargeablecard.cardid=${rechargeablecard.cardid?if_exists}');">
					  生成充值卡</a>
					  <#elseif  rechargeablecard.cardstate?if_exists=='1'>
					  已生成不许修改
					  <a onclick="linkToInfo('/admin_Rechargeablecards_list.action','rechargeablecards.cardid=${rechargeablecard.cardid?if_exists}');">
					   查看</a>
					  </#if>
					  </td>
				    
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Rechargeablecard_add.action','');" value="添加充值卡">  
             <input type="button" class="rbut"onclick="exprotcardExcel('rechargeablecard.cardid','/admin_Rechargeablecard_exportcardInfo.action');"  value="导出充值卡">
             <input type="button" class="rbut"onclick="delInfo('rechargeablecard.cardid','/admin_Rechargeablecard_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <@s.hidden  name="exccardid" id="exccardid"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Rechargeablecard_list.action"   method="post"  id="form_search_id">
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

<!--下载区域开始-->
<div  style="display:none;"  id="downDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td">下载数量:</td>
				<td><@s.textfield name="down_num_s" onkeyup="checkNum(this)" id="down_num"/></td>
			</tr>
			
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	             	 <input type="button"  onclick="downCoupon('/admin_Coupon_downCoupon.action');"  value="下载">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('downDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--下载区域结束-->

  </body>
</html>

