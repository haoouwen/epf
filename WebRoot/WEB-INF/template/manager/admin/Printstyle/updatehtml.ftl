<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title><#if printstyle.template_code=="gouwu">购物单<#elseif printstyle.template_code=="fahuo">发货单</#if>样式</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script src="/include/admin/js/printstyle.js" type="text/javascript"></script>
    <link href="/include/admin/css/printstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
<@s.form action="/admin_Printstyle_updateHtml.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:商城管理 > 打印样式 > <#if printstyle.template_code=="gouwu">购物单<#elseif printstyle.template_code=="fahuo">发货单</#if>样式
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		   <tr>
             <td class="table_name">模板代码:</td>
             <td>
                   ${printstyle.template_code?if_exists}
             </td>
           </tr>
       
           <tr>
             <td class="table_name">模板名称:</td>
             <td>
              ${printstyle.template_name?if_exists}
             </td>
           </tr>
       	   
       	   <tr>
             <td class="table_name">标签解释:</td>
             <td>
             	<@s.textarea  name="printstyle.label_explan" cssClass="txtinput" onkeyup="checkLength(this,500);" maxlength="500" cssStyle="width:860px;height:100px;" readonly="true"/>
             	<@s.fielderror><@s.param>printstyle.label_explan</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">打印内容<font color='red'>*</font></td>
             <td>
             	<div class="tabbar">
				    <ul>
				      <li  id="dm">
				         <a onclick="showEffact('content_daima');" >设计源码</a>
				      </li>
				      <li class="selected" id="xg">
				         <a onclick="showEffact('content_xiaoguo');" >设计效果</a>
				      </li>
				    </ul>
			     </div>
			      
	            <div class="clear"></div>
                <div id="content_daima" class="tabdiv" style="display:none;" >
	              <@s.textarea id="content" name="printstyle.print_content" cssClass="txtinput"onkeyup="checkLength(this,25000);" maxlength="25000" cssStyle="width:860px;height:430px;"/>
	              <@s.fielderror><@s.param>printstyle.print_content</@s.param></@s.fielderror>
	              <br/>
	              <font color="red">非专业人员请勿随便修改。</font>
	            </div>
	            <div class="clear"></div>
	            <div id="content_xiaoguo" class="tabdiv">
	            ${printstyle.print_content?if_exists}
	            </div>
	            </div>
             </td>
           </tr>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="printstyle.trade_id"/> 
       <@s.hidden name="printstyle.template_coded"/> 
       <@s.hidden name="printstyle.template_name"/>  
       <@s.token/>  
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

