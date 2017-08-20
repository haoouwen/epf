<html>
  <head>
    <title>添加广告位</title>
     <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/js/admin/advpos.js"></script>
    <script type="text/javascript" src="/include/js/getcatarea.js"></script> 
    <script type="text/javascript">    
     function isdisplay(){
      var sel=$("select[id='pos_type']").val();
      var pos=$("#moduel");
      var si=$("#size");
      if(sel=='g'||sel=='f'){
        pos.show();
      } 
      else{
       $("#module_type").attr("value","0");
        pos.hide();
       }
       if(sel=='c'||sel=='d'||sel=='e'){
         si.show();
       }else{
        $("#width").val("0");
        $("#height").val("0");
        si.hide();
       }
     }
      $(document).ready(function(){ 
          var sel=$("select[id='pos_type']").val();
	      var pos=$("#moduel");
	      var si=$("#size");
	      if(sel=='g'||sel=='f'){
	        pos.show();
	      } 
	      else{
	        pos.hide();
	      }
	      if(sel=='c'||sel=='d'||sel=='e'){
	         si.show();
	      }else{
	         si.hide();
	      }
	  });
    </script>
  </head>
  <body>
<@s.form action="/admin_Advpos_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:网站管理 > 广告管理 > 广告位管理 > 修改广告位
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td width="19%" class="table_name">广告位名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="advpos.pos_name" maxLength="50" size="70"/>
             	<@s.fielderror><@s.param>advpos.pos_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">广告类型<font color='red'>*</font></td>
             <td>
             	 <@s.select id="pos_type" name="advpos.pos_type" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" onchange="isdisplay()" />
             	 <@s.fielderror><@s.param>advpos.pos_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">广告所属模块<font color='red'>*</font></td>
             <td>
             	 <@s.select id="adv_pos" name="advpos.adv_pos" list="adv_posList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择" onchange="isdisplay()" />
             	 <@s.fielderror><@s.param>advpos.adv_pos</@s.param></@s.fielderror>
             </td>
           </tr>  
             <tr id="moduel" style="display:none;">
             <td class="table_name">所属模块<font color='red'>*</font></td>
             <td>
             <@s.select id="module_type" name="advpos.module_type" list="commpara_modelList" listValue="module_name" listKey="module_type" headerKey="0" headerValue="请选择"/>
             <@s.fielderror><@s.param>advpos.module_type</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr id="size" style="display:none;">
             <td class="table_name">宽*高:</td>
             <td>
               <@s.textfield id="width" name="advpos.p_width" cssClass="txtinput"  cssStyle="width:60px;"/>*<@s.textfield id="height" name="advpos.p_height" cssClass="txtinput"  cssStyle="width:60px;"/>（单位px）
               <@s.fielderror><@s.param>advpos.p_width</@s.param></@s.fielderror><@s.fielderror><@s.param>advpos.p_height</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">价格:</td>
             <td>
             	<@s.textfield name="advpos.price" value="0" cssClass="txtinput" cssStyle="width:100px;" onkeyup="checkNum(this)" />
             	<img class='ltip' src="/include/common/images/light.gif"  alt='<@s.property value="%{getText('manager.price')}"/>'> 
             	<@s.fielderror><@s.param>advpos.price</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name"  >示意图:</td>
             <td colspan="3" >
              <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="advpos.img_path" id="uploadresult" cssStyle="width:300px;"/>
             			</td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   &nbsp;&nbsp;<@s.fielderror><@s.param>advpos.img_path</@s.param></@s.fielderror>
             			</td>
             		</tr>
             	</table>  
             
             </td>
             
           </tr>
           <tr>
             <td class="table_name">前台是否显示:</td>
             <td>
             	<@s.radio name="advpos.isshow" list=r"#{'0':'显示','1':'不显示'}"/>
             	<@s.fielderror><@s.param>advpos.isshow</@s.param></@s.fielderror>
             </td>
           </tr>  <tr>
             <td class="table_name">广告描述:</td>
             <td>
             	<@s.textarea name="advpos.pos_desc" cssClass="txtinput" rows="5" cssStyle="width:400px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>advpos.pos_desc</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">无广告代码:</td>
             <td>
             	<@s.textarea name="advpos.default_code" cssClass="txtinput" rows="5" cssStyle="width:400px;" onkeyup="set_textarea_length(this,200);"/>
             	<@s.fielderror><@s.param>advpos.default_code</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="advpos.sort_no"  cssClass="txtinput" cssStyle="width:80px;" maxLength="11" onkeyup="checkNum(this)" />
             	<@s.fielderror><@s.param>advpos.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
          <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	      	 ${listSearchHiddenField?if_exists}
           <@s.token/>  
	        <!--所属地区插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <@s.hidden name="advpos.pos_id"/>  
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
		   <!--所属地区插件隐藏字段结束区域-->
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Advpos_list.action','adv_pos_s=${adv_pos_s}');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
  
  </body>
</html>