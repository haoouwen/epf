<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>商品信息</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />

<style>

</style>

</head>
<body>
<script type="text/javascript">
  
  function download_qcode(cfg_mobiledomain,custnum,registertype,goods_no,goods_id){
     window.open("/include/download_goods_qcode.jsp?cfg_mobiledomain="+cfg_mobiledomain+"&custnum="+custnum+"&registertype="+registertype+"&goods_no="+goods_no+"&goods_id="+goods_id,null, "height=380,width=500,status=no,toolbar=no,menubar=no,location=no");
}
 </script>
 <div class="crumb-wrap">
      <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>商品列表<span class="crumb-step">&gt;</span><span>商品信息</span></div>
        </div>
        <div class="result-wrap">
            
            <div class="result-content">
                 <span style="font-size:16px;">基本信息：</span>
            </div>
           
            <div class="result-content">
               
               <table class="user_tab" width="100%">   
		               
		                <tr class="tr_tab">
			             <td class="td_tab">商品名称:</td>
			             <td>
			            	<#if goods.goods_name?if_exists!=null && goods.goods_name?if_exists!=''>
				             ${goods.goods_name?if_exists}
			               <#else>
				               <font color="grey">暂无内容</font>
			               </#if>
			               <#assign registertype="">
                            <#if asysuser.agent_type=="0">
                                <#assign registertype="1"> 
                            <#elseif asysuser.agent_type=="2">
                                <#assign registertype="2">
                            </#if>
			             </td>
			             <td class="td_tab" style="width:200px;">商品图片:</td>
				             <td class="td_tab">
				             	<#if goods.list_img?if_exists!="" || goods.list_img?if_exists!=Null>
				             	<a onclick="showpictureSrc('${(goods.list_img)?if_exists}');" href="###">
				                  <img src="${(goods.list_img)?if_exists}" width="100px" height="100px" />
				                  </a>
				             	<#else>
				             	<font color="grey">暂无图片</font>
				             	</#if>
				             </td> 
			           </tr>
						<tr class="tr_tab">
						   <td class="td_tab">英文名称:</td>
			             <td class="td_tab" title="${goods.goods_en_name?if_exists}">
			             <#if goods.goods_en_name?if_exists!=""||goods.goods_en_name?if_exists!=Null>
			               <#if goods.goods_en_name?if_exists?length lt 12>
			               ${goods.goods_en_name?if_exists}
			               <#else>
			               ${goods.goods_en_name?if_exists[0..11]}..</#if>
			             <#else>
			             <font color="grey">暂无内容</font>
			             </#if>
			             </td>
					             <td class="table_name fixheight" style="width:200px;">商品分类:</td>
					             <td>
					              <#if cat_name?if_exists!="" || cat_name?if_exists!=Null>
					             	${cat_name?if_exists}
					             	<#else>
					             	<font color="grey">暂无内容</font>
					             	</#if>
					             </td>
			           </tr>	            
					     <tr class="tr_tab">
					             <td class="td_tab">重量:</td>
					             <td class="td_tab">
					             <#if goods.weight?if_exists !="" || goods.weight?if_exists!=Null>
					             	${goods.weight?if_exists}
					             <#else>
					             <font color="grey">暂无内容</font>
					             </#if>
					             </td>
					             
					              <td class="td_tab">产地:</td>
					             <td class="td_tab">
					             <#if goods.goods_place?if_exists!="" || goods.goods_place?if_exists!=Null>
					             	${goods.goods_place?if_exists}
					             <#else>
					             <font color="grey">暂无内容</font>
					             </#if>	
					             </td>
					    </tr>	
			            <tr class="tr_tab">
			             <td class="td_tab">条形码:</td>
			             <td class="td_tab">
			             <#if goods.bar_code?if_exists!="" || goods.bar_code?if_exists!=Null>
								${goods.bar_code?if_exists}
						 <#else>
						 <font color="grey">暂无内容</font>
						 </#if>
			             </td>
			             
			             <td class="td_tab">商品编号:</td>
			             <td class="td_tab">
				             <@s.hidden name="goods.goods_no" id="goods_no"/>
				             <#if goods.goods_no?if_exists!=""||goods.goods_no?if_exists!=Null>
				             <@s.label name="goods.goods_no" value=" ${goods.goods_no?if_exists}"/>
				             <#else>
				             <font color="grey">暂无内容</font>
				             </#if>
			             </td>
			           </tr>
			           <tr class="tr_tab">
			             <td class="td_tab">生产商</td>
			             <td class="td_tab">
			             <#if goods.producer?if_exists!=""||goods.producer?if_exists!=Null>
			             ${goods.producer?if_exists}
                         <#else>
                         <font color="grey">暂无内容</font>
                         </#if> 
			             </td>
			             <td class="td_tab">生产地址:</td>
			             <td class="td_tab">
			             <#if goods.product_address?if_exists!=""|| goods.product_address?if_exists!=Null>
								${goods.product_address?if_exists}
						 <#else>
						 <font color="grey">暂无内容</font>
						 </#if>
			             </td>
			           </tr>
				<#if is_more_attr=='1' || is_more_attr==null>    
										<#list goodsattrList as attr>
										           <tr class="tr_tab">
										             <td class="td_tab">原价(元):</td>
										             <td class="td_tab">
										             <#if attr.market_price_str?if_exists!=""|| attr.market_price_str?if_exists!=Null>
										             	${attr.market_price_str?if_exists}
										             <#else>
										             <font color="grey">暂无内容</font>
										             </#if>
										             </td>
										             <td class="td_tab">销售价(元):</td>
										             <td class="td_tab">
										             <#if attr.sale_price_str?if_exists!=""||attr.sale_price_str?if_exists!=Null>
										             		${attr.sale_price_str?if_exists}
										             <#else>
										             <font color="grey">暂无内容</font>
										             </#if>		
										             </td>
										           </tr>
									               	 <tr class="tr_tab">
										             <td class="td_tab">库存:</td>
										             <td class="td_tab">
										             <#if attr.stock_str?if_exists!=""||attr.stock_str?if_exists!=Null>
										             	${attr.stock_str?if_exists}
										             <#else>
										             <font color="grey">暂无内容</font>
										             </#if>
										             </td>
										           </tr>	
							            </#list>
						             </#if>
                     </table>
            </div>
            <div class="result-content">
		          <span style="padding-left:500px;"> <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_dailigoods.action','');" value="返回" type="button"></span>
            </div>
        </div>
   </div>
   <script type="text/javascript" src="/malltemplate/bmall/js/jquery.qrcode.min.js"></script>
   <script type="text/javascript">
	 $(document).ready(function(){
	  var code_url=$("#code_url").val();
	  $('#qrcode').qrcode({width:120,height:120,text:code_url});
	});
	</script>
</body>
</html>
