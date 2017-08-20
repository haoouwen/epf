<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>商品列表</title>
<link href="/include/admin/css/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>

<@s.form action="/agent_Asysuser_goodslist.action" method="post" id="indexForm" name="indexForm">
<@s.hidden name="asysuser.state" id="admin_user_state"/>
 <div class="crumb-wrap">
          <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
			<span class="crumb-name">商品列表</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                    <table class="search-tab">
                        <tr>
                            <td>商品名称:</td>
						    <td><@s.textfield name="goods_name_s" cssClass="common-text"/></td>
						    <td class="tdpad">商品编号:</td>
						    <td><@s.textfield name="goods_no_s"  cssClass="common-text"/></td>
					        <td><input type="submit" class="btn btn-primary btn2" value="查询"></td>
					        <td><input type="button" class="btn btn-primary btn2" onclick="searchShowDIV('searchDiv','300px','220px');"  value="高级查询"></td>
					        <td><input type="button" class="btn btn-primary btn2" onclick="goods_qrcode_print('1');"  value="批量打印二维码"></td>
                        </tr>
                    </table>
            </div>
        </div>
        <div class="result-wrap">
            
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="2%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goods.goods_id')"/></th>
						    <th width="6%"  >商品编号</th>
						    <th width="20%"  >商品名称</th>
						    <th width="18%"  >所属分类</th>
						    <th width="8%"  >条形码</th>
						    <th width="5%"  >原价(元)</th>
						    <th width="5%"  >售价(元)</th>
						    <th width="5%"  >贸易方式</th>
						    <th width="3%"  >操作</th>
                        </tr>
                        <#list goodsList as goods>
					    <tr align="center">
					        <td><input type="checkbox" name="goods.goods_id" value="${goods.goods_id?if_exists}"/></td>
						    <td align="center">
								<#if goods.goods_no?if_exists!=null && goods.goods_no?if_exists!=''>
						    		${goods.goods_no?if_exists}
						    	<#else>
						    	 	-
						    	</#if>
						    </td>
						    <td style="text-align:center;">
								<#if goods.goods_name?if_exists?length lt 31>
						      		<a onclick="linkToInfo('/agent_Asysuser_goodsdetails.action','goods_id=${goods.goods_id?if_exists}');" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists} </a>
								 <#else>
								 	<a onclick="linkToInfo('/agent_Asysuser_goodsdetails.action','goods_id=${goods.goods_id?if_exists}');" title="${goods.goods_name?if_exists}">${goods.goods_name?if_exists[0..30]}</a>
								 </#if>
						    </td>
						    <td align="center">
								${goods.cat_attr?if_exists}
						    </td>
						   
						    <td align="center">
								${goods.bar_code?if_exists}
						    </td>
						    <td align="center">
								${goods.goods_market_price?if_exists}
						    </td>
						      <td align="center">
								${goods.min_sale_price?if_exists}
						    </td>
				             <td align="center">
						      <#if goods.depot_id?if_exists=='1'>
						      <font color="blue">跨境直邮</font>
						      <#else>
						      <font color="green">保税仓</font>
						      </#if>  
						    </td>
						    <td align="center">
								<a onclick="linkToInfo('/agent_Asysuser_goodsdetails.action','goods_id=${goods.goods_id?if_exists}');"><img src="/include/common/images/view.gif" /></a>
						    </td>
					  </tr>
					  </#list>
                    </table>
                  <div class="pages" >
				     ${pageBar?if_exists}
				   </div>
                </div>
        </div>
   </div>
   <@s.hidden name="org_hidden_value" id="org_value"/>
     <#include "/WEB-INF/template/manager/agent/Asysuser/searchmodehidden.ftl">
</@s.form>
<#include "/WEB-INF/template/manager/agent/Asysuser/searchmode.ftl">
<#include "/WEB-INF/template/manager/admin/Goodsorder/commonprint.ftl">
<script type="text/javascript" src="/include/agent/js/batch_qcode.js"></script>
<script src="/include/admin/js/orderprint.js" type="text/javascript"></script>
 <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
  <script type="text/javascript"/>
      $(document).ready(function(){
	       //所属分类的回选
	       loadCat("${catIdStr?if_exists}","","","goods");
	   });
      </script> 
</body>
</html>
