<html>
  <head>
     <title>批量生成手机详细</title>
     <script src="/include/admin/js/goodsdetailToAPPDetail.js" type="text/javascript"></script>
  </head>
<body >
<!--当前位置开始-->
<div class="postion">
 当前位置:网站管理 > 网站更新 > 批量生成手机详细	
</div>
<div class="rtdcont">
  
		<div id="oper_seo_div" class="oper_seo_div">   
		<div class="clear"></div>
		
	        <div class="tabdiv" >
				<div class="rdtable">
					<table class="wwtable"  width="100%" cellspacing="1" cellpadding="8" >
				           <tr>
				            <td height="10">
				         <span style="font-size:13pt">商品总数量:</span><span style="font-size:13pt;color:red;">${goodsnum?if_exists}</span>
				         <span  style="font-size:13pt;padding-left:50px;" >已生成手机详情数量:</span><span style="font-size:13pt;color:red;">${yesgoodsnum?if_exists}</span>
				         <span  style="font-size:13pt;padding-left:50px;">待生成手机详情数量:</span><span style="font-size:13pt;color:red;">${nogoodsnum?if_exists}</span>
				         <span id="show_success_id" style="display:none;">
				         <span><span style="font-size:13pt;padding-left:50px;">成功生成:</span><span id="success_id" style="font-size:13pt;color:red;">0</span>
				         <span style="font-size:13pt;padding-left:50px;">生成失败:</span><span id="faile_id" style="font-size:13pt;color:red;">0</span>
				         </span>
				           </td>
				           </tr>
						  <tr><td align="center" height="30">
						     <#if goodsnum?if_exists&&goodsnum=='0'>
						    		 <span>找不到商品</span>
						      <#else>
									 <span id="scbtn_id"> <a href="###" onclick="toSetAppDetailMore('gid');" title="全量生成手机版宝贝详情"><img src="/include/admin/images/qlscsjxq.jpg"></a>
									  <a href="###" onclick="toSetAppDetailMore('ngid');" title="增量生成手机版宝贝详情"><img src="/include/admin/images/zlscsjxq.jpg"></a></span>
									  <!--
									   <a href="###" onclick="toDetailPic();" title="检查商品详细图片并导出图片异常的商品">检查商品详细图片</a></span>-->
									 <span id="show_id" style="display:none;"><img src=/include/admin/images/upLoading.gif style="width:30px; height:30px;">&nbsp;执行中...</span>
						    </#if>
						  </td></tr>
						  <tr><td>&nbsp描述：<font color="red">全量生成手机详情</font>是系统根据全部商品详细内容,全部重新生成手机详细内容,之前已经生成的将被覆盖! <br/><br/>
						&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<font color="red">增量生成手机详情</font>是系统根据商品详细内容中没有生成手机详情内容的商品,生成手机详细内容,之前已经生成的手机详情商品不会受到影响!</td></tr>
						   <tr>
							<div class="bsbut">
								<b>&nbsp;系统提示: </b>执行<font color="red">全量生成手机详情</font>,之前的手机详细页将会被覆盖,请谨慎操作！
								</div>
							</tr>
				        </table>
				</div>
	       </div>
	 </div>
</div>

<div class="clear"></div>
<@s.hidden id="gid"  name="goodsid" />
<@s.hidden id="ngid"  name="nogoodsid" />
<div id="appdetail_conten_id" style="display:none;"></div>
<div class="clear"></div>  
<@s.form action="/admin_Lucene_exportPIC.action" method="post"  id="indexFormpic">
  <@s.hidden id="pic_goods_id"  name="pic_goods_id" />
</@s.form>
</body>
</html>