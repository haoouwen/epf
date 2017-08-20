 <html>
<head>
<title>评价列表</title>
<#include "/include/uploadInc.html">
<link href="/include/bmember/css/account.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="/include/bmember/js/goodseval.js"></script>
</head>
<body>
	
<@s.form action="/bmall_Goodseval_auditList.action" method="post" id="indexForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>商品评价</span></h2>
        <!----> 
        <div class="cancelDiv">
            <!---->
            <div class="opeDiv">
                <table width="100%" cellpadding="0" cellspacing="0" class="opeTab">
                	<tr>
                        <th width="10%">商品图片</th>
                        <th width="40%">商品名称</th>
                        <th width="20%">购买时间</th>
                        <th width="15%">状态</th>
                        <th width="15%">操作</th>
                    </tr>
                    <!---->
                    <#assign num=0>
                    <#assign goods_id="">
                    <#assign list_img="">
                    <#assign goods_name="">
                    <#assign goodsdetailurl="">
                    <#list goodsevalList as goodseval>
                     <#assign num=num+1>
                     <#assign trade_id=stack.findValue("@com.rbt.function.GoodsevalFuc@getTradeId('${(goodseval.goods_id)?if_exists}','${goodseval.order_id?if_exists}')")>    
                     <tr>
                  	   <td colspan="5">
                         <table  width="100%" cellpadding="0" cellspacing="0">
                           <tr>
                              <td width="10%">
		                         <#if goodseval.list_img!=''>
					      			<a href="/mall-goodsdetail-${goodseval.goods_id?if_exists}.html" target="_blank"><img src="${(goodseval.list_img)?if_exists}" class="f_left" align="absmiddle"></a>
					      		 <#else>
					      			<a href="/mall-goodsdetail-${goodseval.goods_id?if_exists}.html" target="_blank"><img src="${(cfg_nopic)?if_exists}"  class="f_left" align="absmiddle"></a>
					      		 </#if>
                              </td>
                              <td width="40%"><a href="/mall-goodsdetail-${goodseval.goods_id?if_exists}.html"><#if goodseval.goods_name?if_exists!="">${goodseval.goods_name?if_exists}<#else>-</#if></a></td> 
                              <td width="20%">${goodseval.order_time?if_exists[0..9]} </td>
                              <td width="15%"><span class="mblue"><#if trade_id?if_exists=="">未评价<#else>已评价</#if></span></td>
                              <td width="15%"><#if trade_id?if_exists==""><span class="evaSpan">发表评价</span><#else><p><a href="###" class="bluea" onclick="linkToInfo('/bmall_Goodseval_audit.action','trade_id=${trade_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">查看</a></p><p><a href="###" onclick="deleteEval('/bmall_Goodseval_deleteEval.action','goodseval.trade_id=${trade_id?if_exists}&trade_id=${goodseval.trade_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');">[删除]</a></p></#if></td>
                            </tr>
                            <tr class="evasTr">
                              <td colspan="5">
                                 <div class="evasDiv">
                                    <div class="jtPic"><img src="/include/bmember/images/fbjt_03.gif"></div>
                                    <table  width="100%" cellpadding="0" cellspacing="0">
                                    <tr>
			             	           <th>晒图：</th>
					       		    	<td>

					       		    	  
					       		    	  
					       		    	 <div id="fileQueue${num?if_exists}"></div>
					       		    	 <@s.textfield  id="uploadresult${num?if_exists}" cssStyle="display:none;" readonly="true"  />
					       		    	  <span style="position:absolute;top:21px;"><input type="file"  name="uploadifyfile${num?if_exists}"   id="uploadifyfile${num?if_exists}"  />
					       		    	  <span style="position:absolute;left:65px;top:-5px;"><img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult${num?if_exists}');"/ ></span>
					       		    	  <script>uploadImgMultis('uploadifyfile${num?if_exists}','uploadresult${num?if_exists}','fileQueue${num?if_exists}');</script>
					       		    	  
					       		    	  
					       		    	  
					       		    	  
					       		    	  
					       		    	</td>
                                       </tr>
                                    	
                                    	<tr>
                                          <th width="16%"><span>*</span>评分：</th>
                                          <td width="84%">
                                             <input type="radio" name="eval${num?if_exists}" value="1">好评
                                             <input type="radio" name="eval${num?if_exists}" value="0">中评
                                             <input type="radio" name="eval${num?if_exists}" value="-1">差评
                                           </td>
                                        </tr>
                                        
                                       
                                        <tr>
                                          <th><span>*</span>内容：</th>
                                          <td><textarea id="comment${num?if_exists}" onkeyup="checkLength(this,200);"></textarea></td>
                                        </tr>
                                        <tr>
                                          <th></th>
                                          <td><input type="button" class="submitbut" value="发表评论" onclick="submintEval('${num?if_exists}','${goodseval.goods_id?if_exists}','${goodseval.order_id?if_exists}')">&nbsp;<i>(只能输入200个字符)</i></td>
                                        </tr>
                                    </table>
                                 </div>
                              </td>
                            </tr>
                         </table>
                       </td> 
                    </tr>  
                   </#list>  
                </table>
            </div>
            
            <!----> 
            <div class="listbottom"> ${pageBar?if_exists}</div>
                           
        </div>
        
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
</@s.form>
</body>
</html>

