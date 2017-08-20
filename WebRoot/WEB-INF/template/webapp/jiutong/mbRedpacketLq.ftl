<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}领取${redpacket.money?if_exists}元红包</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/youhui.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>领取${redpacket.money?if_exists}元红包<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--领取红包-->
<div class="lqhb">
    <h2></h2>
    <ul>
      <li><div><b>${redpacket.money?if_exists}</b>元</div>
      <p class="sysm">(${redpacket.content?if_exists})</p>
      <p><span onclick="getRedpacket('${redpacket.red_id?if_exists}')"><img src="/malltemplate/jiutong/images/ljlqBut.png" /></span></p></li>
      <br class="clear"/>
    </ul>
</div>


<#if platformType =="1">
 <div class="shareDiv">
    <p><a href="/webapp/shareRedpacket_${redpacket.red_id?if_exists}.html" ><img src="/malltemplate/jiutong/images/appshare.gif" /></a></p>
 </div>
<#else>
<div class="shareDiv">
    <p>分享</p>
	<div class="jiathis_style_32x32">
	 <a class="jiathis_button_qzone"></a>
	 <a class="jiathis_button_tsina"></a>
	 <a class="jiathis_button_tqq"></a>
	 <a class="jiathis_button_renren"></a>
	</div>
    <!-- JiaThis Button END -->
    <div id="nativeShare"></div>
    <div class="clear"></div>
</div>
</#if>

<#include "/a/webapp/mbCommon.html">

</body>
<script src="/wro/webapp_common_publicjs.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/coupon.js" type="text/javascript"></script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<script type="text/javascript" src="/malltemplate/jiutong/js/nativeShare.js" charset="utf-8"></script>
<script>
    var config = {
        url:'http://m.epff.cc/webapp/redpacket_${redpacket.red_id?if_exists}.html?platformType=0',// 分享的网页链接
        title:'${redpacket.money?if_exists}元红包-点击领取',// 标题
        desc:'点击领取${redpacket.money?if_exists}元红包',// 描述
        from:'epffo2o' // 来源
    };
    var share_obj = new nativeShare('nativeShare',config);
</script>


</html>
