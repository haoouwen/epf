<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${cfg_webname?if_exists}-优惠劵领取</title>
<script src="/wro/mallpublic.js" type="text/javascript"></script>
<script type="text/javascript" src="/malltemplate/bmall/js/coupon.js"></script>
<link href="/wro/mallpublic.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/bmall/css/coupon.css" rel="stylesheet" type="text/css">
<script src="/malltemplate/bmall/js/jt_product.js" type="text/javascript"></script>
</head>

<body>

<!--头部--> 
<#include "/a/bmall/malltop.html">
<!--导航-->
<div class="headerList">
    <div class="w1180">
	  <!-- -导航 -->
       <#include "/a/bmall/mallnav.html">
      <!-- --分类 -->
       <#include "/a/bmall/mallcat.html">
   </div>
</div>

<!--内容-->
<div class="postion"></div>
<div class="couponBack">
   <div class="couponCont">
     <!--领取优惠劵-->
      <div class="lqyhj">
        <h2></h2>
         <table width="100%" cellpadding="0" cellspacing="0">
          <tr>
          <#list couponList as coupon>
           <td align="center">
             <div>
               <p class="size22">￥<b>${coupon.coupon_money?if_exists}</b>优惠券</p><p>(${coupon.coupon_name?if_exists})</p>
             </div>
             <p class="sysm">使用说明：${coupon.content?if_exists}</p>
             <p class="padp"><span onclick="getCoupon('${coupon.coupon_id?if_exists}')"><img src="/malltemplate/bmall/images/ljlqbut.png" /></span></p>
           <#if coupon_index!=0&&coupon_index%3==0>
               </tr>
               <tr>
           <#else>
            </td>
           </#if>
            
           </#list>
          </tr>
        </table>
        <ul>
          <br class="clear"/>
        </ul>
      </div>
   </div>
</div>

<!--尾部-->
<#include "/a/bmall/mallbottom.html">
<@s.hidden name="loc" id="refloc" />
<!--返回顶部-->
<div class="returnTop"></div>

</body>

<script src="js/jquery-1.4.4.js" type="text/javascript"></script>
<script src="js/claMenu.js" type="text/javascript"></script>
<script src="js/public.js" type="text/javascript"></script>


</html>
