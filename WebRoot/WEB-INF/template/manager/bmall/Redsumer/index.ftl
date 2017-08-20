<html>
<head>

<title>红包-未使用红包</title>
<script type="text/javascript" src="/include/bmember/js/coupon.js"></script> 
</head>
<body>

<@s.form action="/bmall_Comsumer_list.action" method="post" id="indexForm">

<div class="wR810">
	
	<div class="rwTitle">
		
		<h2><span>红包</span></h2>
		
		<div class="tabIDiv">
			<input type="button" class="siBut" value="未使用红包" onclick="linkToInfo('/bmall_Redsumer_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="niBut" value="已使用红包" onclick="linkToInfo('/bmall_Redsumer_useList.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="niBut" value="已过期红包" onclick="linkToInfo('/bmall_Redsumer_outList.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
	        <input type="button" class="niBut" value="兑换红包"   onclick="exportShowDIV('exRedpacketDiv','300px','130px','兑换红包')"/>
	    </div>
	    
	    <div class="usedTDiv">
	    
	    	<table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
	    	
	    		<tr>
	    		 <th width="3%"></th>
                 <th width="11%">红包号码</th>
                 <th width="15%">名称</th>
                 <th width="5%">金额</th>
                 <th width="20%">有效期</th>
                 <th width="26%">会员级别</th>
                 <th width="6%">状态</th>
	    		</tr>
	    		
	    		<#list redsumerList as redsumer>
	    		<tr>
	    		   <td><img src="/include/bmember/images/hongbaoimg.png"></td>
	    		   <td><span class="blueSpan">${redsumer.redsumer_no?if_exists}</span></td>
	    		   <td>${redsumer.red_name?if_exists}</td>
	    		   <td>${redsumer.money?if_exists}</td>
	    		    <td><span class="mgray">${redsumer.start_time?if_exists}&nbsp;至&nbsp;${redsumer.end_time?if_exists}</span></td>
	    		    <td>${redsumer.member_level?if_exists}</td>
	    		    <td><#if redsumer.use_state?if_exists =="0">未使用</#if></td>
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

