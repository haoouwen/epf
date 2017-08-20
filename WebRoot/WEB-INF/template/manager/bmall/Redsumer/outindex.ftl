<html>
<head>

	<title>红包-已过期红包</title>
<script type="text/javascript" src="/include/bmember/js/coupon.js"></script> 	
</head>
<body>

<@s.form action="/bmall_Comsumer_outList.action" method="post" id="indexForm">

<div class="wR810">

   <div class="rwTitle">
   	
   		<h2><span>红包</span></h2>
   		
		<div class="tabIDiv">
			<input type="button" class="niBut" value="未使用红包" onclick="linkToInfo('/bmall_Redsumer_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="niBut" value="已使用红包" onclick="linkToInfo('/bmall_Redsumer_useList.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="siBut" value="已过期红包" onclick="linkToInfo('/bmall_Redsumer_outList.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
	        <input type="button" class="niBut" value="兑换红包"   onclick="exportShowDIV('exRedpacketDiv','300px','130px','兑换红包')"/>
	    </div>
	    
	     <div class="usedTDiv">
	    
	    	<table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
	    	
	    		<tr>
	                 <th width="13%">红包号码</th>
	                 <th width="17%">名称</th>
	                 <th width="10%">金额</th>
	                 <th width="12%">有效期</th>
	                 <th width="7%">状态</th>
	                 <th width="6%">操作</th>
	    		</tr>
	    		
	    		<#list redsumerList as redsumer>
	    		<tr>
	    		  <td>${redsumer.redsumer_no?if_exists}</td>
	    		  <td class="mblue">${redsumer.red_name?if_exists}</td>
	    		  <td>${redsumer.money?if_exists}</td>
	    		   <td>￥${redsumer.money?if_exists}</td>
	    		   <td class="mgray">${redsumer.start_time?if_exists} 至 ${redsumer.end_time?if_exists}</td>
	    		     <td class="mgray">已过期</td>
	    		     <td><a href="#" onclick="delB2cOneInfo('/bmall_Comsumer_outDelete.action','redsumer.redsumer_id=${redsumer.redsumer_id}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" class="bluea">删除</a></td>
	    		</tr>
	    		 </#list>
	    	 </table>
	    	 
	    </div>
	    
	    <!--翻页控件-->
	    <div class="listbottom">
        	${pageBar?if_exists}
        </div>
   
   </div>

</div>
<div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
</@s.form>
<!--兑换区域开始-->
<div  style="display:none;"  id="exRedpacketDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td">红包号码:</td>
				<td><@s.textfield name="red_no_s"  id="red_no"/></td>
			</tr>
			
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	             	 <input type="button"  onclick="exRedpacket();"  value="兑换">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('exRedpacketDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--兑换区域结束-->
</body>
</html>

