<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—搜索</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbSearchApp.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
</head>
<body>

<div class="searPage">

  <div class="top">
    <div class="searDiv">
      <div class="sptcDiv">
       <input type="text" class="searText" id="keyword" onfocus="searchstart();" onblur="searchend();"/>
      </div>
    </div>
     <input type="button" class="topPageSear" onclick="searchgoods();"/>
     <input type="hidden" id="en_word" />
     <input type="hidden" id="default_search" value="${cfg_default_search?if_exists}" />
  </div>
  <!--热门搜索-->
  <div class="hotSear">
     <p class="hoth2">热门搜索</p>
     <p>
      <#if serachkeyList?if_exists && serachkeyList.size() gt 0>
          <#list serachkeyList as slist>
                <a href="###" onclick="hotsearchgoods('${slist.key_name?if_exists}','${slist.en_name?if_exists}');" >${slist.key_name?if_exists}</a>
           </#list>
     </#if>
      </p>
  </div>
</div>


</body>

</html>
