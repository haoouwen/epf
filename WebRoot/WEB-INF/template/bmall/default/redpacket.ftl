<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>${cfg_webname?if_exists}-红包领取</title>
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
      <!--领取红包-->
      <div class="lqhb">
        <h2></h2>
        <table width="100%" cellpadding="0" cellspacing="0">
          <tr>
           <#list redpacketList as redpacket>
           <td align="center">
             <div></div>
              <!--<div><b>${redpacket.money?if_exists}</b>元</div>-->
              <p class="sysm">使用说明：${redpacket.content?if_exists}</p>
              <p><span onclick="getRedpacket('${redpacket.red_id?if_exists}')"><img src="/malltemplate/bmall/images/ljlqbut.png" /></span></p>
           <#if redpacket_index!=0&&redpacket_index%3==0>
               </tr>
               <tr>
           <#else>
            </td>
           </#if>
            
           </#list>
          </tr>
        </table>
      </div>
   </div>
</div>

<!--尾部-->
<#include "/a/bmall/mallbottom.html">

<!--返回顶部-->
<div class="returnTop"></div>

</body>

</html>
