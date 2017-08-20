<html>
  <head>
    <title>添加支付方式</title>
 <#include "/include/uploadInc.html">
</head>
<body>
<@s.form action="/admin_Payment_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:系统管理 > 系统管理 > 支付方式管理 > 添加支付方式
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">支付接口编码<font color="red">*</font></td>
              <td width="83%">
             	<@s.textfield name="payment.pay_code" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>payment.pay_code</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">支付方式<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.pay_name" cssClass="txtinput" maxLength="50"/>
             	<@s.fielderror><@s.param>payment.pay_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">支付方式描述:</td>
             <td>
             	<@s.textarea name="payment.pay_desc" cssClass="txtinput" cssStyle="width:500px;height:100px"/>
             	<@s.fielderror><@s.param>payment.pay_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
         <td class="table_name">付款方式图标：</td>
         <td>
            <table border="0" cellpadding="0" cellspacing="0">
			             		<tr>
			             			<td style="padding:0px;">
			             			    <div id="fileQueue"></div>
				              			 <@s.textfield name="payment.pay_logo" id="uploadresult" cssStyle="width:300px;"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
			             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽220px高55px">
				              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
			             			</td>
			             		</tr>
			</table>
         	<@s.fielderror><@s.param>payment.pay_logo</@s.param></@s.fielderror>
         </td>
       </tr>
           
           <tr>
             <td class="table_name">卖家收款账户：</td>
             <td>
             	<@s.textfield name="payment.seller_name" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.seller_name</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">商户账号<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.pay_account" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.pay_account</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">商户密钥<font color="red">*</font></td>
             <td>
             	<@s.textfield name="payment.passwd" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.passwd</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">应用ID：</td>
             <td>
             	<@s.textfield name="payment.appID" cssClass="txtinput" maxLength="20" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.appID</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">应用密钥：</td>
             <td>
             	<@s.textfield name="payment.appSecret" cssClass="txtinput" maxLength="100" cssStyle="width:300px;"/>
             	<@s.fielderror><@s.param>payment.appSecret</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name" >是否启用:</td>
             <td>
         	 <@s.radio name="payment.enabled" list=r"#{'0':'是','1':'否'}" value="0" checked="true" />
         	 <@s.fielderror><@s.param>payment.enabled</@s.param></@s.fielderror>
             </td>
           </tr>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
   	   <@s.token/>
       <@s.hidden name="nav_name"/>
       <@s.hidden  name="payment.hand_fare" value="0"/>
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存"/>
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Payment_list.action';document.forms[0].submit();"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>