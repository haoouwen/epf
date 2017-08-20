<html>
  <head>
    <title>商品咨询详情</title>
    <script type="text/javascript">
	  $(document).ready(function(){ 
		  var selvalue=$("select[name='consultation.c_type']").find('option:selected').text(); 
          $("#idvalue").html(selvalue);
		  });
    </script>
  </head>
  <body>
  <@s.form action="/admin_Consultation_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 商品管理 > 商品咨询 > 商品咨询详情
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		           <tr>
		             <td class="table_name">商品名称:</td>
		             <td>
		             	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">咨询类型:</td>
		             <td style="display:none;">
		                <@s.select name="consultation.c_type" list="commparaList" listValue="para_key" listKey="para_value"/>
		             	<@s.fielderror><@s.param>consultation.c_type</@s.param></@s.fielderror>
		             </td>
		             <td ><span id="idvalue"></span></td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">咨询内容:</td>
		             <td>
		                 <div style="width:630px;height:auto;overflow:auto;" >
	                      ${consultation.c_content?if_exists}
	                     </div>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">提问时间:</td>
		             <td>
		             	<@s.label name="consultation.c_date" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>consultation.c_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">回复内容<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="consulting.re_content" cssStyle="width:400px;height:100px" cssClass="txtinput"  onkeyup="checkLength(this,600);"/>
		             	<@s.fielderror><@s.param>consulting.re_content</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否有效:</td>
		             <td>
		                <@s.radio name="consulting.is_display" list=r"#{'0':'有效','1':'无效'}" value="${consultation.is_display?if_exists}"/>
		             	<@s.fielderror><@s.param>consulting.is_display</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="consulting.re_id"/>
	       <@s.hidden name="consulting.trade_id"/>
	       <@s.hidden name="consultation.trade_id"/>
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Consultation_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>


