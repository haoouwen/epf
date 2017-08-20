 <html>
<head>
<title>咨询列表</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
</head>
<body>
<@s.form action="/bmall_Consultation_auditList.action" method="post" id="indexForm">

<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>商品咨询</span></h2>
        <!----> 
        <div class="usedTDiv padDiv">
            <!---->
            <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
                <tr>
                    <th width="45%">咨询商品</th>
                    <th width="45%">咨询详情</th>
                    <th width="10%">操作</th>
                </tr> 
                <!---->
               <#list consultationList as consultation> 
                <tr valign="top">
                	<td class="lUsedTd">
                     <div class="lUsedImg"><a href="/mall-goodsdetail-${consultation.goods_id?if_exists}.html"><img src="${consultation.list_img?if_exists}"></a></div>
                     <div class="lUsedDiv">
                         <p><a href="/mall-goodsdetail-${consultation.goods_id?if_exists}.html">
		                    <#if consultation.goods_name?if_exists!=null && consultation.goods_name?if_exists!=''>
								 ${consultation.goods_name?if_exists}
							<#else>
							 	-
							</#if>
                          </a></p>
                     </div>
                    </td>
                    <td class="lUsedTd">
                        <p>咨询内容：</p>
                        <p><div style="word-break:break-all; width:300px;" >${consultation.c_content?if_exists}</div> </p>
                        <p class="graySpan">${consultation.c_date?if_exists}</p>
                        <#if consultation.re_content?if_exists!=''>
                        <p>咨询回复：</p>
                        <div style="word-break:break-all; width:300px;" ><p  class="blueSpan">${consultation.re_content?if_exists} </p></div>
                        <p class="graySpan">
                        	<#if consultation.re_date?if_exists!=null && consultation.re_date?if_exists!=''>
								${consultation.re_date?if_exists}
							 <#else>
						     	-
							</#if>
                        </p>
                       </#if> 
                    </td>
                    <td>
                      <a href="###" class="bluea" onclick="delB2cOneInfo('/bmall_Consultation_delete.action','consultation.trade_id=${consultation.trade_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')">删除</a>
                    </td>
                </tr>
               </#list> 
            </table>
                         
        </div>
        <!---->
        <div class="listbottom">${pageBar?if_exists}</div>
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/> 
</@s.form>
</body>
</html>

