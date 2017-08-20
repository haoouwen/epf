<html>
  <head>
    <title>优惠券下载记录</title>
    <script type="text/javascript" src="/include/admin/js/coupon.js"></script>     
  </head>
  <body>
<@s.form action="/admin_Coupon_couponlist.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销推广>优惠券管理 > 优惠券列表 > 下载记录 </div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >优惠券兑换码:</td><td><@s.textfield name="dowm_coupon_id_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('excoupons.ex_id')"/></th>
                 
                 	<th width="30%">优惠券名称</th>
                 
                 	<th width="20%">兑换码</th>
			    
                 	<th width="20%">状态</th>
			    
                 	<th width="27%">下载时间</th>
               </tr>
			    <#list excouponsList as excoupons>
				 <tr >
			         <td><input type="checkbox" name="excoupons.ex_id" value="${excoupons.ex_id?if_exists}"/></td>
			         
			         	<td align="center">${excoupons.coupon_name?if_exists}</td>
			         	
			         	<td align="center">${excoupons.coupon_no?if_exists}</td>
			         	
			         	<td align="center"><#if excoupons.ex_state=="0"><font color='green'>未兑换<#else><font color='red'>已兑换</#if></td>
				    
			         	<td align="center">${excoupons.in_date?if_exists}</td>
			         	
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
             <input type="button" class="rbut"onclick="exprotcouponExcel('excoupons.ex_id','/admin_Coupon_exportcouponInfo.action');"  value="重新下载">
           </div>
        </div>
	   <!--表单框隐藏域存放--> 
	   <@s.hidden name="excoupons.ex_id"/>
	   <@s.hidden name="coupon_id"/>
	   
	   <@s.hidden name="couponkey" id="couponkey"/>
</@s.form>
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

