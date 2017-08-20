<html>
  <head>
    <title>修改导航</title>
    <script type="text/javascript">
     $(document).ready(function(){
          var backmod=$("#navcode").val();
          $("#codeselect").val(backmod);
          $("#codeselect").change(function(){
               var mod=$(this).val();
               $("#navcode").val(mod);
          });        
          $("#navform").submit(function(){
              return true;
          });  
     })        
	</script>
  </head>
  <body>
<@s.form action="/admin_Nav_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:网站管理 > 基本功能 > 导航管理 > 修改导航
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		   <tr>
             <td class="table_name">导航名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="nav.nav_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>nav.nav_name</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td width="19%" class="table_name">链接地址<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="nav.link_url" cssClass="txtinput" maxLength="100"/>
             	<@s.fielderror><@s.param>nav.link_url</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Link.url')}"/>"> 
             </td>
           </tr>
             <tr>
             <td class="table_name">放置位置:</td>
             <td>
             	<@s.select name="nav.nav_post" list=r"#{'1':'上部','2':'中部','3':'下部'}" />  
             		<img class='ltip' src="/include/common/images/light.gif"  alt="上部:网站最顶部 中部:网站导航位置 下部:网站尾部"> 
             	<@s.fielderror><@s.param>nav.nav_post</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">高亮编码</td>
             <td>
             ${(nav.nav_code)?if_exists}&nbsp;<font color="red">(主要用于前台导航页面)</font>
             </td>
           </tr>
            <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="nav.sort_no" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>nav.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
            <tr>
             <td class="table_name">链接类型:</td>
             <td>
             	<@s.select name="nav.isopen" list=r"#{'_self':'当前窗口打开','_blank':'新窗口中打开'}"  />
             	<@s.fielderror><@s.param>nav.isopen</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">导航图标</td>
             <td>
             	<@s.select name="nav.nav_icon" list="nav_List" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
             	<@s.fielderror><@s.param>nav.nav_icon</@s.param></@s.fielderror>
             	<img class="ltip" src="/include/common/images/light.gif" alt="提示:适用于前台导航商品图标">
             </td>
           </tr>
            <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="nav.isshow" list=r"#{'0':'有效','1':'无效'}"/>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.admin.Commpara.enabled')}"/>"> 
             </td>
           </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.token/>
       <@s.hidden name="nav.nav_id"/>	
       <@s.hidden name="mall_type" />       
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存"/>
       <@s.reset value="重置"/>
   	   <input type="button"  name="returnList" value="返回列表" onclick="linkToInfo('/admin_Nav_list.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>