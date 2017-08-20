<html>
<head>
<title>添加我的收藏</title>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
<script type="text/javascript" src="/include/common/js/common.js"></script>
<!--整合弹出层-->
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
    <script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
	<script type="text/javascript" src="/include/admin/js/goods.js"></script>
	<script type="text/javascript" src="/include/common/js/jqModal.js"></script>
    <link href="/include/common/css/jqModal.css" rel="stylesheet" type="text/css" />  
    <link href="/include/admin/css/goods.css" rel="stylesheet" type="text/css" />  
   <script>
    function geturlinfo()
    {
       var page_url = window.location.href;
       document.getElementById("urlid").value=page_url;
    }
    </script>
</head>
<body>
	
<@s.form action="/bmall_Collect_insert.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
<div class="postion">
  		<a href="#">我是买家</a><span>></span><a href="#">我的交易</a><span>></span><a href="#">我的收藏</a><span>></span><a href="#">添加我的收藏</a>
    </div>
     <div class="rpostion">
     	<h2>添加我的收藏</h2>
     </div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table width="100%">
		    <tr>
			<td class="firsttd" >商品名称:</td>
						<td>
			             	<span id="goodsname"></span>
			             	<a onclick="addRalateGoods();"><input type="button" value="添加商品" /></a>
							<#include "/WEB-INF/template/manager/searchRadioGoods.ftl"/>
			             </td>
			</tr>
            <tr><td  class="firsttd">收藏标题<font color='red'>*</font></td><td>
        	<@s.textfield name="collect.title" cssClass="txtinput" maxLength="100"/>
        	<@s.fielderror><@s.param>collect.title</@s.param></@s.fielderror>
            </td></tr>
            
            <tr><td  class="firsttd">链接地址<font color='red'>*</font></td><td>
        	<@s.textfield name="collect.link_url" cssClass="txtinput" maxLength="100"value="http://"/>
        	<@s.fielderror><@s.param>collect.link_url</@s.param></@s.fielderror>
            </td></tr>  
            
             <tr><td  class="firsttd">备注:</td><td>
        	<@s.textarea name="collect.remark" cssClass="txtinput" maxLength="100" cssStyle="width:260px;height:60px;"onkeyup="checkLength(this,100)"/>
		    <@s.fielderror><@s.param>collect.remark</@s.param></@s.fielderror>
            </td></tr> 

            <tr>
             <td colspan="2" align="center">
             <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
             <@s.hidden id="goods_id" name="collect.goods_id"/>    
	       ${listSearchHiddenField?if_exists}
	       <@s.token/> 
             	<@s.submit value="提  交" cssClass="submitbut"/>
		     	<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Collect_list.action','');"/>
             </td>
            </tr> 
            
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

