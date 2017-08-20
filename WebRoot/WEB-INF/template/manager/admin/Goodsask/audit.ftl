<html>
<head>
<title>查看咨询</title>
<script type="text/javascript">
 $(document).ready(function(){
     var sell=$("#c_type").find("option:selected").text();
     $("#c_value").html(sell);
 });
</script>
</head>
<body>
<@s.form action="/bmall_Goodsask_update.action" method="post" name="formshopcongif" validate="true">
<div class="rightside f_right">
     <div class="rpostion"><h2>我的咨询</h2></div>
     <div class="base_infor">
       <h2 class="base_title">查看咨询</h2>
       <div class="table_infor f_left">
          <table style="width:750px;" >

            <tr><td  class="firsttd3">商店名称：</td><td>
        	<@s.label name="goods.goods_name" cssClass="txtinput" maxLength="20"/>
            </td></tr>

            <tr>
             <td  class="firsttd3">咨询类型：</td><td>
             <td style="display:none;">
             <@s.select id="c_type" name="goodsask.c_type" list="commparaList" listValue="para_key" listKey="para_value"/>
             </td>
             <td ><span id="c_value"></span></td>
            </tr>  
            
            <tr><td  class="firsttd3">咨询内容：</td><td>
        	<@s.label name="goodsask.c_content" cssStyle="width:400px;height:100px" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            
            <tr><td  class="firsttd3">提问时间：</td><td>
        	<@s.label name="goodsask.c_date" cssClass="txtinput" maxLength="20"/>
            </td></tr>  
            
            <tr><td  class="firsttd3">回复内容：</td><td>
        	<@s.label name="goodsask.re_content" cssClass="txtinput" />
            </td></tr>
            
            <tr><td  class="firsttd3">回复时间：</td><td>
        	<@s.label name="goodsask.re_date" cssClass="txtinput" />
            </td></tr>  
       
            <tr>
             <td><p class="psub"><input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Goodsask_auditList.action','');"/></p></td>
             <@s.hidden name="goodsask.trade_id"/>
            </tr> 
          </table>
       </div>  
     </div> 
</div>
 </@s.form>
</body>
</html>

