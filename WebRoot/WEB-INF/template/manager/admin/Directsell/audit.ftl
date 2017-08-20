<html>
  <head>
    <title>审核预售商品</title>
	 <!--图片-->
  <#include "/include/uploadInc.html">
  </head>
  <body>
  <@s.form action="/admin_Directsell_auditState.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:营销推广 > 预售商品 > 审核预售商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		          <tr>
		             <td class="table_name">预售标题:</td>
		             <td>
						${directsell.sale_title?if_exists}
		             </td>
		           </tr>
					
		            <tr>
	                  <td class="table_name">商品名称:</td>
		             <td>
			                <#if hidden_goodsname!=null>
			                ${hidden_goodsname?if_exists}
			                <#else>
			       			  该商品已删除
			                </#if>
	                </td>
		           </tr>
					
				   <tr>
		             <td class="table_name">所属分类:</td>
		               <td>
	                	  ${cat_name?if_exists}
		             </td>
		           </tr>
					
					<tr>
		             <td class="table_name">定金:</td>
		             <td>
		             	￥${directsell.earnest?if_exists}
		             </td>
		           </tr>
	           
		          <tr>
		             <td class="table_name">预售价格:</td>
		             <td>
		             	￥${directprice?if_exists}
		             </td>
		           </tr>
					
					<tr>
			             <td class="table_name">预售图片:</td>
			              <td width="500px" valign="top">
									             		<!--显示图片-->	             		
									             	    <ul id="show_pic" class="show_pic">
									             	    	<#if directsell.img_path!=null && directsell.img_path!=null>
									             	    	<#list directsell.img_path?if_exists?split(",") as img>
										             	   		<li>
											             	   		<input type="hidden" value="${img?if_exists}" name="gimg">
											             	   		<a href="${img?if_exists}" class="group cboxElement">
											             	   			<img src="${img?if_exists}" style="width:100px;height:100px;"></a>
											             	   		<div>
											             	   		</div>
										             	   		</li>
									             	   		</#list>
									             	   		</#if>
									             	    </ul>   
							               </div>  
							             </td>
		      </tr>
	           
	           
		            <tr>
			             <td class="table_name">详细描述:</td>
			             <td>
			             	       <div style="width:900px;height:auto;overflow:auto;" >
						             ${directsell.saledesc?if_exists}
						           </div>
			             </td>
			      </tr>
		           
	           
		           <tr>
			             <td class="table_name">预售开始时间:</td>
			             <td>
			             	${directsell.start_time?if_exists[0..18]}
			             </td>
			           </tr>
		           
			           <tr>
			             <td class="table_name">预售结束时间:</td>
			             <td>
			             	${directsell.end_time?if_exists[0..18]}
			             </td>
			      </tr>
			      
			       <tr>
			             <td class="table_name">付尾款期限时间:</td>
			             <td>
			             	  ${directsell.tail_time?if_exists[0..18]}
			             </td>
			      </tr>
	           
		           <tr>
		             <td class="table_name">排序:</td>
		             <td>
						${directsell.sort?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">库存:</td>
		             <td>
						${directsell.stock?if_exists}
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">最大购买量:</td>
		             <td>
						${directsell.max_buy?if_exists}
		             </td>
		           </tr>

	           <tr>
	             <td class="table_name">审核状态<font color='red'>*</font></td>
	             <td>
	              <#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="directsell.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <input type="radio" name="directsell.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
				    </#if>
				   </#list>
	             <@s.fielderror><@s.param>directsell.info_state</@s.param></@s.fielderror>
	                  <img class="ltip" src="/include/common/images/light.gif" alt="若审核不通过请点击审核不通过选项填写理由">
	           	    <br/>
	             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
	             </td>
	           </tr>
	           
		</table>
		 <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	      <@s.hidden name="directsell.trade_id" />
	       <@s.hidden name="directsell.user_id"/> 
	       <@s.hidden name="directsell.cust_id"/> 
	       <@s.token/>  
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Directsell_auditList.action','');"/>    
   </div>
</div>

<div class="clear"></div>
</@s.form>
</body>
</html>

