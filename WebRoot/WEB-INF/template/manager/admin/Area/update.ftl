<html>
  <head>
    <title>修改地区</title>
    <script type="text/javascript" src="/include/common/js/pinyin.js"></script> 
  </head>
  <body>
  <@s.form action="/admin_Area_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:系统管理 > 系统管理 > 地区管理 > 修改地区
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td width="19%" class="table_name">地区名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield  id="cat_name"  name="area.area_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>area.area_name</@s.param></@s.fielderror>
             </td>
           </tr>
             <script type="text/javascript">  
	              $('#cat_name').bind('keyup', function(){   
	              	  var cat_value=$('#cat_name').val();   
		              var getword=Turnpingyin(cat_value);
		              var en_word="";
		              if(getword.length>50){
		              	en_word=getword.substring(0,49);
					  }else{
					     en_word=getword;
					  }
		              $('#en_name').val(en_word);
	              })
	           </script> 
           <tr>
             <td class="table_name">英文名称<font color='red'> *</font></td>
             <td>
               <@s.textfield name="area.en_name" cssClass="txtinput" maxLength="50"  id="en_name"/>
             	<@s.fielderror><@s.param>area.en_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">跨境通地区编码:</td>
             <td>
             	<@s.textfield name="area.kjtareaid" cssClass="txtinput" maxLength="6"  id="en_name"/>
             	<@s.fielderror><@s.param>area.kjtareaid</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">区域划分<font color='red'> *</font></td>
             <td>
             	<@s.select name="area.area_have" list="areahave_List" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
             	<@s.fielderror><@s.param>area.area_have</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">地区区号<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="area.area_number"  cssClass="txtinput" cssStyle="width:50px;" maxLength="3" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>area.area_number</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt='格式如010 三位数字，如果区号为2位数，前面补0'>
             </td>
           </tr>
           <tr>
             <td class="table_name">地区级别:</td>
             <td>
             	${lel_level?if_exists}级
             	<@s.fielderror><@s.param>area.area_level</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">地区排序:</td>
             <td>
             	<@s.textfield name="area.sort_no" value="${area.sort_no?if_exists}" cssClass="txtinput" cssStyle="width:50px;" maxLength="4" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>area.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif" alt='<@s.property value="%{getText('manager.sort_no')}"/> '>
             </td>
           </tr>   
            <tr>
             <td class="table_name">系统提示:</td>
             <td>
             	<span><img class='ltip' src="/include/common/images/light.gif" alt="编辑保存后,请点击更新缓存" />
             	[编辑保存后,请点击<a href="###" onclick="renewload();"><font color="red">更新缓存</font></a>]</span>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	       <@s.token/>
	       <@s.submit value="保存"/>
           <@s.hidden name="area.area_id"/>
           <@s.hidden name="area.up_area_id"/>
           <@s.hidden name="area.area_level" value="${lel_level?if_exists}"/>
           <@s.hidden  id="back_sel_area"  name="back_sel_area" />
		   <@s.hidden  id="back_sel_area_name"  name="back_sel_area_name" />
		   <@s.hidden name="oldinfotitle" value="${area.area_name?if_exists}"/>
	       <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Area_list.action';document.forms[0].submit();"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>