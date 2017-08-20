<html>
  <head>
    <title>添加充值卡</title>
   
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
     <#include "/include/uploadInc.html">
  </head>
  <body>  
<@s.form action="/admin_Rechargeablecard_update.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：营销推广 > 促销管理 > 更新充值卡
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_name">充值卡名称<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="rechargeablecard.cardname" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>rechargeablecard.cardname</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">金额<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="rechargeablecard.cardmoney" onkeyup="checkRMB(this);"  cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>rechargeablecard.cardmoney</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">数量<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="rechargeablecard.cardnum" onkeyup="checkRMB(this);" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>rechargeablecard.cardnum</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			  <tr>
			             <td class="table_name">截止日期<font color='red'>*</font></td>
			             <td>
			             	<@s.textfield type="text"  name="rechargeablecard.carddate"   class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'2020-4-3'})"/>
			             	<@s.fielderror><@s.param>rechargeablecard.carddate</@s.param></@s.fielderror>
			             </td>
			             
			           </tr>

        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
          <@s.hidden name="rechargeablecard.cardid"/>   
          <@s.hidden name="rechargeablecard.cardused"/>
          <@s.hidden name="rechargeablecard.cardstate"/>
          <@s.token/>   
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Rechargeablecard_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

