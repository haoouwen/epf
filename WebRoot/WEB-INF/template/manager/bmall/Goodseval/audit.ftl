<html>
<head>
<title>我的评价</title>
<link href="/include/bmember/css/account.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/include/common/js/common.js"></script>

</head>
<body>
	
<@s.form action="/bmall_Goodseval_buyupdate.action"  method="post"  name="formshopcongif" validate="true">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>商品评价</span></h2>
        <!----> 
        <div class="cancelDiv">
            <!----> 
             <div class="opeDiv padDiv">
                <!---->
                <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
                    <tr><th width="20%">商品图片</th><td><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html"><img src="${goods.list_img?if_exists}"  width="90"  height="90"></a></td></tr>  
                    <tr><th>商品名称</th><td><a href="/mall-goodsdetail-${goods.goods_id?if_exists}.html" class="bluea">			    	
                    <#if goods!=null && goods.goods_name?if_exists!=null && goods.goods_name?if_exists!=''>
						 ${goods.goods_name?if_exists}
					<#else>
					 	该商品已下架
					</#if></a>
					</td></tr>  
                    <tr><th>评价等级</th>
                    <td>
                         <#if goodseval.g_eval?if_exists=="1">
                               好评
                         <#elseif goodseval.g_eval?if_exists=="0"> 
                               中评
                         <#elseif goodseval.g_eval?if_exists=="-1"> 
                               差评
                         </#if> 
                    </td></tr>
                   <tr><th width="20%">商品晒图</th>
                    <td>
			      		<#if share_pic!=null>
			      		<#list share_pic?if_exists?split(',') as pic>
			            <img src="${pic?if_exists}"  width="90"  height="90">
						</#list>
						</#if>
                    </td>
                    </tr>
                    <tr><th>商品评价</th>
                    	<td>
                        	<p>${goodseval.g_comment?if_exists}</p>
                            <p class="mgray">
                            <#if goodseval.eval_date?if_exists?length lt 18>
                              ${goodseval.eval_date?if_exists}
                            <#else>
                              ${goodseval.eval_date?if_exists[0..17]}
                            </#if>
                            </p>
                        </td>
                    </tr> 
                    <tr><th>评价回复</th>
                    	<td>
                        	<p class="morange">
                        	<#if goodseval.explan_content?if_exists!=null && goodseval.explan_content?if_exists!=''>
				    		<@s.label name="goodseval.explan_content" cssClass="txtinput" maxLength="20"/>
					    	</#if>
                        	</p>
                            <p class="mgray">
                           <#if goodseval.explan_date?if_exists!=null && goodseval.explan_date?if_exists!=''>
					    		   <#if goodseval.explan_date?if_exists?length lt 18>
		                              ${goodseval.explan_date?if_exists}
		                            <#else>
		                              ${goodseval.explan_date?if_exists[0..17]}
		                            </#if>
					    	</#if>
                            </p>
                        </td>
                    </tr> 
                </table>
                             
            </div>
            
        </div>
   </div>
   
  </div>
  <div class="clear"></div>
</div>
</@s.form>
</body>
</html>

