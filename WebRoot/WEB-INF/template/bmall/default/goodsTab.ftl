<!--商品详情-->
<div class="goodsdetail" id="goodsdetail">
            <div class="tabbar" >
	                 <ul class="goodsdeul" id="goodsduulId">
			           <li class="gdbut" >商品详情</li>
			           <#if selfparagroupList?if_exists && (selfparagroupList?size > 0)>
			           		<li>详细参数</li>
			           </#if>
	                   <li>累计评价<span class="blue">(${evalNum?if_exists})</span></li>
	                   <li>月成交记录<span class="blue">(${monthSaleNum?if_exists}件)</span></li>
	                   <li>商品咨询</li>
	                   <li>售后服务</li>
				     </ul>
				     <a name="tab"></a>  
             </div>
             
             <div class="tabdiv" style="display:block;">
               <!--产品的具体描述-->
                <#if selfextendattrList?if_exists && (selfextendattrList?size > 0)>
	                <div class="goodsdecontul">
	                   <ul>
	                   	 <#list selfextendattrList as selfExtendAttr>
	                   	 	<#if (selfExtendAttr.ex_attr_value)?if_exists!="">
				           		<li alt="${selfExtendAttr.ex_attr_value}" title="${selfExtendAttr.ex_attr_value}">
				           			${(selfExtendAttr.ex_attr_alias)?if_exists}: 
				           			<#if (selfExtendAttr.ex_attr_value)?if_exists?length lt 12>
				           				${selfExtendAttr.ex_attr_value}
				           			<#else>
				           				${selfExtendAttr.ex_attr_value[0..11]}...
				           			</#if>
				           		</li>
				            </#if>
					 	 </#list>
	                   </ul>
	                   <br class="clear"/>
	                 </div>
	            </#if>
               <!--产品视频-->
               <#if (goods.goods_video)?if_exists!="">
               	  <div class="videoDiv">
		          	<#include "WEB-INF/template/bmall/default/cuSunPlayer.ftl">
		          </div>
		       </#if>
               <!--产品图片-->
               <div class="ckfont">
               	${(goods.goods_detail)?if_exists}
               </div>
             </div>
             <!--参数-->
             <#if selfparagroupList?if_exists && (selfparagroupList?size > 0)>
	           	 <div class="tabdiv">
	           	 <div class='tabletitle'>详细参数</div>
	               	 <table class="wwtable" cellspacing="1" cellpadding="8" >
						<#list paragroupList as pg>
							   <#list selfparagroupList as spg>
								   	<#if pg.para_group_id==spg.para_group_id>
										<tr>
											<td colspan="2" class="table_goods_td1">
												${pg.para_name}
											</td>
										</tr>
										<#list 	paravalueList as pv>
											<#list 	selfparavalueList as spv>
										
										            <#if spg.slef_para_group_id==spv.slef_para_group_id && pv.para_id==spv.para_id >
													  	<tr>
												             <td  class="table_goods_td3">	
												             		${pv.para_name}
												             </td>
												             <td>
												             		${spv.slef_para_value?if_exists}
												             </td>
											           	</tr>
										           	</#if>
										     </#list>
									           
								        </#list>
								  </#if>     
						 	</#list>
						  
					   </#list>
	       	       </table>
	             </div>     
             </#if>
             <!--累计评价-->
             <div class="tabdiv">
               <div class="proAppraise">
                 <div class="proAppCont" id="goodsEval">

                 </div>
               </div>
             </div>     
                
             <!--月成交记录-->
             <div class="tabdiv">
               <div class="bidHistory">
                 <div class="bidHistorycont" id="monthSale">

                 </div>
               </div>
             </div>     
             <!--商品咨询-->
             <div class="tabdiv" >

				 <div  id="consult">
						
                 </div>

				 <div class="clear"></div>
				   
				 <div class="message">
				   <h2>发表咨询</h2>
				    <div class="messcont">
				      <p>声明： 您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复。因厂家随时会更改一些产品的包装、颜色、产地等参数，所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为： 周一至周五，9:00至18:00，请耐心等候工作人员回复。</p>
				      <p>
				      	<strong>咨询类型：</strong>
					      	<#list commparaList as commpara>
					      		<span><input type="radio" name="con_type" value="${(commpara.para_value)?if_exists}" onfocus="isLogin();"/>${(commpara.para_key)?if_exists}</span>
					      	</#list>
				      <p><strong>咨询内容：</strong></p>
							 <textarea id="c_content"  onblur="filterWord();" class="messtarea"  onfocus="isLogin();" onkeyup="checkLength();" maxlength="150"></textarea>
				
				<span class="word_count2">150</span>/<span class="word_surplus">150</span>
				      </p>
				      <p>
				      	<input class="subMess" type="button" onclick="subGoodsConsult();">
				      	<input type="text" maxlength="4" name="commentrand" id="rands" class="messtext"onfocus="isLogin();">
				      	<img src="/imgrand.action" style="vertical-align:middle;" onclick="changeValidateCode(this)"/>
				      </p>
				    </div>
				 </div>
             </div>
             <!--售后服务-->
             <div class="tabdiv">
               <div class='tabletitle'>售后服务</div>
               <div class="proLook">
					${(aftersales.content)?if_exists}
               </div>
             </div>
              
           </div>
                   
        </div>