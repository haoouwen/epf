<html>
<head>
<title>优惠劵-未使用优惠券</title>
    <script type="text/javascript" src="/include/bmember/js/coupon.js"></script> 
</head>
<body>

<@s.form action="/bmall_Comsumer_list.action" method="post" id="indexForm">

<div class="wR810">
	
	<div class="rwTitle">
		
		<h2><span>优惠券</span></h2>
		
		<div class="tabIDiv">
			<input type="button" class="siBut" value="未使用优惠券" onclick="linkToInfo('/bmall_Comsumer_list.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="niBut" value="已使用优惠券" onclick="linkToInfo('/bmall_Comsumer_useList.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
			<input type="button" class="niBut" value="已过期优惠券" onclick="linkToInfo('/bmall_Comsumer_outList.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')"/>
	        <input type="button" class="niBut" value="兑换优惠券"   onclick="exportShowDIV('exCouponDiv','300px','130px','兑换优惠券')"/>
	    </div>
	    
	    <div class="usedTDiv">
	    
	    	<table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
	    	
	    		<tr>
	    		 <th width="3%"></th>
                 <th width="10%">优惠券号码</th>
                 <th width="17%">名称</th>
                 <th width="20%">有效期</th>
                 <th width="25%">会员级别</th>
                 <th width="6%">状态</th>
	    		</tr>
	    		
	    		<#list comsumerList as comsumer>
	    		<tr>
	    		    <td><img src="/include/bmember/images/youhuiquanimg.png"></td>
	    		   <td><span class="blueSpan">${comsumer.comsumer_no?if_exists}</span></td>
	    		   <td>${comsumer.coupon_name?if_exists}</td>
	    		    <td><span class="mgray">${comsumer.start_time?if_exists}&nbsp;至&nbsp;${comsumer.end_time?if_exists}</span></td>
	    		    <td>${comsumer.member_level?if_exists}</td>
	    		    <td><#if comsumer.use_state?if_exists =="0">未使用</#if></td>
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
<div  style="display:none;"  id="exCouponDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td">优惠券号码:</td>
				<td><@s.textfield name="coupon_no_s"  id="coupon_no"/></td>
			</tr>
			
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	             	 <input type="button"  onclick="exCoupon();"  value="兑换">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('exCouponDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--兑换区域结束-->
</body>
</html>

