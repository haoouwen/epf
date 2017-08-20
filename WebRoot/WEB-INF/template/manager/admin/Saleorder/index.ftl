<html>
  <head>
    <title>订单促销列表</title>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>    
  </head>
  <body>
<@s.form action="/admin_Saleorder_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销推广 > 促销管理 > 订单促销列表</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >规则名称:</td><td><@s.textfield name="sale_name_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
                 <td><input type="button" onclick="searchShowDIV('searchDiv','300px','130px');" class="rbut" value="高级查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('saleorder.sale_id')"/></th>
                 
                 	<th width="15%">规则名称</th>
			    
                 	<th width="5%">状态</th>
			    
                 	<th width="5%">优先级</th>
                 	
                 	<th width="10%">生效平台</th>                 	
			    
                 	<th width="5%">是否排它</th>
			    
                 	<th width="15%">开始时间</th>
			    
                 	<th width="15%">结束时间</th>
			    
                 	<th width="20%">会员级别</th>
			    
                    <th  width="5%">操作</th>
               </tr>
			    <#list saleorderList as saleorder>
				 <tr >
			         <td><input type="checkbox" name="saleorder.sale_id" value="${saleorder.sale_id?if_exists}"/></td>
			         
			         	<td align="center">${saleorder.sale_name?if_exists}</td>
				    
			         	<td align="center"><#if saleorder.info_state=="1"><font color='green'>启用<#else><font color='red'>禁用</#if></td>
				    
			         	<td align="center">${saleorder.priority?if_exists}</td>
			         	
			         	<td align="center">
			         	<#list saleorder.platform?trim?split(',') as platform>
			         	   <#if platform?trim=='0'>
			         	     PC端
			         	   <#elseif platform?trim=='1'>
			         	     触屏端 
			         	   </#if>
			         	</#list>
			         	</td>			         	
				    
			         	<td align="center"><#if saleorder.is_recome=="1"><font color='green'>是<#else><font color='red'>否</#if></td>
				    
			         	<td align="center">${saleorder.start_time?if_exists}</td>
				    
			         	<td align="center">${saleorder.end_time?if_exists}</td>
				    
			         	<td align="center">${saleorder.member_level?if_exists}</td>
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Saleorder_view.action','saleorder.sale_id=${saleorder.sale_id?if_exists}');">
					  <img src="/include/common/images/bj.gif" /></a></td>
				    
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Saleorder_add.action','');" value="添加订单促销">
             <input type="button" class="rbut" onclick="updateBatchState('1','saleorder.sale_id','/admin_Saleorder_updateStateB2C.action','启用');" value="启用">             
			 <input type="button" class="rbut" onclick="updateBatchState('0','saleorder.sale_id','/admin_Saleorder_updateStateB2C.action','禁用');" value="禁用">  
             <input type="button" class="rbut" onclick="updateBatchState('1','saleorder.sale_id','/admin_Saleorder_updateIsrecome.action','排它');" value="排它">             
			 <input type="button" class="rbut" onclick="updateBatchState('0','saleorder.sale_id','/admin_Saleorder_updateIsrecome.action','取消排它');" value="取消排它">  			 
             <input type="button" class="rbut"onclick="delInfo('saleorder.sale_id','/admin_Saleorder_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <@s.hidden  name="info_state_s"/>
		 <@s.hidden  name="is_recome_s"/>
		 <@s.hidden  name="start_time_s"/>
		 <@s.hidden  name="end_time_s"/>
		 <@s.hidden  name="member_level_s"/>
		 <@s.hidden  name="platform_s"/>			 	
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Saleorder_list.action"   method="post"  id="form_search_id">
<!--dd-->
	<table>
	<tr>
		<td class="searchDiv_td">规则名称:</td>
		<td><@s.textfield name="sale_name_s"/></td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','1':'启用','0':'禁用'}"/></td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">生效平台:</td>
		<td>
            <input type="checkbox" name="platform_s" value="0"<#if platform_s?if_exists?trim?index_of('0') gt -1>checked</#if>/>PC端
         	<input type="checkbox" name="platform_s" value="1"<#if platform_s?if_exists?trim?index_of('1') gt -1>checked</#if>/>触屏端
		</td>
	</tr>	
	
	<tr>
		<td class="searchDiv_td">排它:</td>
		<td><@s.select name="is_recome_s" list=r"#{'':'请选择','1':'是','0':'否'}"/></td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">会员级别:</td>
		<td>
	         <#list commparaList as commpara>
         	  <input type="checkbox" name="member_level_s" value="${commpara.level_code}" <#if member_level_s?if_exists?index_of('${commpara.level_code?trim}') gt -1>checked</#if>>${commpara.level_name}
         	</#list>
		</td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">开始时间:</td>
		<td><input type="text"  name="start_time_s" value="<#if coupon!=null>${coupon.start_time?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/></td>
	</tr>					

	<tr>
		<td class="searchDiv_td">结束时间:</td>
		<td><input type="text"  name="end_time_s" value="<#if coupon!=null>${coupon.end_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/></td>
	</tr>
	
    <tr>
		<td align="center" colspan="2" style="border:0px;">
		<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
		   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
		</td>
	</tr>
	</table>
</@s.form>
</div>

  </body>
</html>

