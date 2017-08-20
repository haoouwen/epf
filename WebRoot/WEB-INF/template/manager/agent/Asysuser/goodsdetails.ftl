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
			             <td class="td_tab">商品:</td>
			             <td>
			            	<#if goods.goods_name?if_exists!=null && goods.goods_name?if_exists!=''>
				              商品名称： ${goods.goods_name?if_exists}
			               <#else>
				               -
			               </#if>
			               <#assign registertype="">
                            <#if asysuser.agent_type=="0">
                                <#assign registertype="1"> 
                            <#elseif asysuser.agent_type=="2">
                                <#assign registertype="2">
                            </#if>
                            <br/>
                                商品二维码：<input type="button" value="查看" onclick="download_qcode('${cfg_mobiledomain}','${asysuser.nike_name}','${registertype}','${(goods.goods_no)?if_exists}','${(goods.goods_id)?if_exists}');"/>
			               <@s.hidden value="${cfg_mobiledomain}/webapp/goods!detail.action?gid=${(goods.goods_id)?if_exists}&custnum=${asysuser.nike_name}&registertype=${registertype}" id="code_url"/>
            	           <div  id="qrcode"></div>
            	            
			             </td>
			             <td class="td_tab" style="width:200px;">商品图片:</td>
				             
				             <td class="td_tab">
				             	<img src="${goods.list_img?if_exists}" width="100px" height="100px"/>

				             </td> 
				             
			             
			             
			           </tr>
		               
						<tr class="tr_tab">
						   <td class="td_tab">英文名称</td>
			             <td class="td_tab">${goods.goods_en_name?if_exists}</td>
					             <td class="table_name fixheight" style="width:200px;">商品分类:</td>
					             
					             <td>
					             	${cat_name?if_exists}

					             </td>
			             
					             
			           </tr>	            

					     <tr class="tr_tab">
					            
					             <td class="td_tab">重量:</td>
					             <td class="td_tab">
					             	${goods.weight?if_exists}
					             </td>
					             
					              <td class="td_tab">产地:</td>
					             <td class="td_tab">
					             	${goods.goods_place?if_exists}
					             </td>
					             
					    </tr>	
		              
			            <tr class="tr_tab">
			             <td class="td_tab">条形码:</td>
			             <td class="td_tab">
								${goods.bar_code?if_exists}
			             </td>
			             
			             <td class="td_tab">商品编号:</td>
			             <td class="td_tab">
				             <@s.hidden name="goods.goods_no" id="goods_no"/>
				             <@s.label name="goods.goods_no" value=" ${goods.goods_no?if_exists}"/>
			             </td>
			             
			           </tr>
			           
			           <tr class="tr_tab">
			             <td class="td_tab">生产商</td>
			             <td class="td_tab">${goods.producer?if_exists}</td>
			             <td class="td_tab">生产地址:</td>
			             <td class="td_tab">
								${goods.product_address?if_exists}
			             </td>
			           </tr>
			           
				
				
				<#if is_more_attr=='1' || is_more_attr==null>    
										<#list goodsattrList as attr>
							            
										           <tr class="tr_tab">
										             <td class="td_tab">原价</td>
										             <td class="td_tab">
										             	
										             	${attr.market_price_str?if_exists}
										             </td>
										             <td class="td_tab">销售价</td>
										             <td class="td_tab">
										            	
										             		${attr.sale_price_str?if_exists}
										             </td>
										             
										           </tr>
										           
									               	 <tr class="tr_tab">
										             <td class="td_tab">货号:</td>
										             <td class="td_tab">
										             	
										             	${attr.goods_item?if_exists}
										             </td>
										             
										             <td class="td_tab">库存:</td>
										             <td class="td_tab">
										             	
										             	${attr.stock_str?if_exists}
										             </td>
										             
										           </tr>	

	
							            </#list>
						             </#if>
                     </table>
            </div>
            
            
            
            <div class="result-content">
              
		          <span style="padding-left:500px;"> <input class="btn btn6" onclick="linkToInfo('/agent_Asysuser_goodslist.action','');" value="返回" type="button"></span>
		              
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
