
<link href="/include/components/videoJS/skin/video-js.css" rel="stylesheet" type="text/css"/>
<script src="/include/components/videoJS/video.js" type="text/javascript"></script>
<script>
    _V_.options.flash.swf = "/include/components/videoJS/skin/video-js.swf";
</script>
<video id="my_video_1" class="video-js vjs-default-skin" controls
  preload="auto" width="${video_width?if_exists}" height="${video_height?if_exists}" poster="${(goods.list_img)?if_exists}"
  data-setup="{}">
  	  <#assign pos = "${goods.goods_video?if_exists}"?index_of('.')>
	  <#if ((pos-1)>-1)>		          				  	
	  		<#assign type =(goods.goods_video)[(pos+1)..(goods.goods_video?length-1)]>
	  </#if>
	  <source src="${goods.goods_video}" type='video/${type}'>
</video>

