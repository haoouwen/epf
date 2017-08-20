<html>
<head>
<title>修改收货地址</title>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script type="text/javascript">
  $(function(){
    	loadArea("${areaIdStr?if_exists}","");
  })
</script>
</head>
<body>
	
<@s.form action="/bmall_Buyeraddr_update.action"  method="post"  name="formshopcongif" validate="true">
<div class="rightside f_right">
<div class="postion">
  		 <a href="#">账户管理</a><span>></span><a href="#">账号管理</a><span>></span><a href="#">收货地址</a><span>></span><a href="#">修改收货地址</a>
    </div>
     <div class="rpostion">
     	<h2>修改收货地址</h2>
     </div>
     <div class="base_infor">
       <div class="table_infor f_left">
          <table width="100%">
          
	        <tr><td class="firsttd">收货人姓名<font color="red">*</font></td><td>
		    <@s.textfield name="buyeraddr.consignee"  maxLength="20"/>
		    <@s.fielderror><@s.param>buyeraddr.consignee</@s.param></@s.fielderror>
	        </td></tr>
	          
	        <tr><td class="firsttd">地区<font color="red">*</font></td>
	    	 <td>
	           <div id="areaDiv"></div>
        	 	<@s.fielderror><@s.param>areaDiv</@s.param></@s.fielderror>
             </td>             	           	            
	         </td>
	        </tr>  
	        
	        <tr><td  class="firsttd">详细地址<font color="red">*</font></td><td>
	    	<@s.textfield name="buyeraddr.address" cssClass="txtinput" maxLength="100"  onkeyup="checkLength(this,100)"/>
		    <@s.fielderror><@s.param>buyeraddr.address</@s.param></@s.fielderror>
	        </td></tr>  
	        
	         <tr><td class="firsttd">邮编<font color="red">*</font></td><td>
		   <@s.textfield name="buyeraddr.post_code"  maxLength="6" onkeyup="checkDigital(this)"/>
	       <@s.fielderror><@s.param>buyeraddr.post_code</@s.param></@s.fielderror>
	        </td></tr> 
	        
	         <tr><td class="firsttd">固定电话:</td><td>
		    <@s.textfield name="buyeraddr.phone"  maxLength="20"/>
	        <@s.fielderror><@s.param>buyeraddr.phone</@s.param></@s.fielderror>
	        </td></tr> 
	        
	        <tr><td class="firsttd">手机：</td><td>
		    <@s.textfield name="buyeraddr.cell_phone"  maxLength="20"onkeyup="checkNum(this)"/>
		     <img class="ltip" src="/include/common/images/light.gif" alt="电话手机请至少填写一个">
	        <@s.fielderror><@s.param>buyeraddr.cell_phone</@s.param></@s.fielderror>
	        </td></tr> 
	        
	         <tr><td class="firsttd">是否为默认地址：</td><td>
    	    <@s.radio name="buyeraddr.sel_address" list=r"#{'0':'是','1':'否'}" />
	        <@s.fielderror><@s.param>buyeraddr.sel_address</@s.param></@s.fielderror>
            </td></tr> 
	        <tr>
             <td colspan="2" align="center">
			   <@s.hidden name="buyeraddr.addr_id"/> 
			   <@s.hidden name="buyeraddr.cust_id"/>  
			   ${listSearchHiddenField?if_exists}                
			   <@s.token/>  
	             <@s.submit value="提  交" cssClass="submitbut"/>
	             <input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Buyeraddr_list.action','');"/>
	            
	           
	         </td>
	             
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
 </div>
<div class="clear"></div>
</body>
</html>

