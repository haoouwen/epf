<html>
  <head>
    <title>评价回复</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
   <script type="text/javascript">
 $(document).ready(function(){
     var sell=$("#c_type").find("option:selected").text();
     $("#c_value").html(sell);
     var s= $("input[name=goodseval.g_eval][checked]").val();
     if(s=='1'){
     $("#g_value").html('好评');
     }else if(s=='0'){
     $("#g_value").html('中评');
     }else{
     $("#g_value").html('差评');
     }
 });

</script>
  </head>
  <body>
  <@s.form action="/admin_Goodseval_update.action" method="post" validate="true" id="modiy_form"name="formshopcongif">
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 商品相关 > 商品评价 > 评价回复
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		     <tr><td  class="table_name">商品名称:</td><td>
        	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
            </td></tr>
            
            <tr>
             <td  class="table_name">商品评级:</td>
             <td >
              <#if (goodseval.g_eval)?if_exists=="1">
                 好评
              <#elseif (goodseval.g_eval)?if_exists=="0">
                 中评
              <#elseif (goodseval.g_eval)?if_exists=="-1">
                差评
              </#if>
             </td>
            </tr>  
            <tr><td  class="table_name" width="15%">评论内容:</td>
            <td >
		    ${(goodseval.g_comment)?if_exists}${(goodseval.is_img)}
            </td></tr>  
            <#if share_pic!=null>
             <tr><td  class="table_name">晒图:</td><td>
		    		
		      		<#list share_pic?if_exists?split(',') as pic>
		            <img src="${pic?if_exists}"  width="90"  height="90">
					</#list>
					
             </td></tr>  
            </#if>
            <tr><td  class="table_name">评论时间:</td><td>
        	${(goodseval.eval_date)?if_exists}
            </td>
            </tr>  
            <tr><td  class="table_name">
              回复内容<font color='red'>*</font>
            </td>
            <td>
			<@s.textarea name="goodseval.explan_content" cssStyle="width:500px;height:100px" cssClass="txtinput" onkeyup="checkLength(this,600)"/>
		    <@s.fielderror><@s.param>goodseval.explan_content</@s.param></@s.fielderror>
            </td>
            </tr>
            
            <tr>
             <td  class="table_name">是否有效:</td>
             <td >
             <@s.radio id="g_eval" name="goodseval.is_enable" list=r"#{'1':'无效','0':'有效'}" />
              <@s.fielderror><@s.param>goodseval.is_enable</@s.param></@s.fielderror>
             </td>
            </tr>  
            <tr>
             <td colspan="2" align="center">
             <@s.token/>   
           
             </td>
            </tr> 
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
  		  <@s.submit value="确定" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表"onclick="linkToInfo('/admin_Goodseval_list.action','');"/>
	       <@s.hidden name="goodseval.trade_id"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
</body>
</html>

