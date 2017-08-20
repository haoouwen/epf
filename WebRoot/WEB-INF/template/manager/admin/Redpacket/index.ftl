<html>
  <head>
    <title>红包列表</title>
  <script type="text/javascript" src="/include/admin/js/redpacket.js"></script>  
  </head>
  <body>
<@s.form action="/admin_Redpacket_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销推广>红包管理 > 红包列表</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >名称:</td><td><@s.textfield name="red_name_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
                 <td><input type="button" onclick="searchShowDIV('searchDiv','300px','130px');" class="rbut" value="高级查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('redpacket.red_id')"/></th>
                 
                 	<th width="10%">名称</th>
			    
                 	<th width="10%">号码</th>
			    
                 	<th width="5%">状态</th>
                 	
                 	<th width="7%">剩余总数量</th>
			    
                 	<th width="5%">金额(元)</th>
                 	
                 	<th width="11%">未使用/获取的总数量</th>
			    
                 	<th width="10%">开始时间</th>
			    
                 	<th width="10%">结束时间</th>
			    
                 	<th width="20%">会员级别</th>
			    
                 <th  width="10%">操作</th>
               </tr>
			    <#list redpacketList as redpacket>
				 <tr >
			         <td><input type="checkbox" name="redpacket.red_id" value="${redpacket.red_id?if_exists}"/></td>
			         
			         	<td align="center">${redpacket.red_name?if_exists}</td>
				    
			         	<td align="center">${redpacket.red_no?if_exists}</td>
				    
			         	<td align="center"><#if redpacket.info_state=="1"><font color='green'>启用<#else><font color='red'>禁用</#if></td>
			         	
			         	<td align="center">${redpacket.red_num?if_exists}</td>
				    
			         	<td align="center">${redpacket.money?if_exists}</td>
			         	
			         	<td align="center">${redpacket.down_count_no_use?if_exists}/${redpacket.down_count?if_exists}</td>
				    
			         	<td align="center">${redpacket.start_time?if_exists}</td>
				    
			         	<td align="center">${redpacket.end_time?if_exists}</td>
				    
			         	<td align="center">${redpacket.member_level?if_exists}</td>
				      
				        <td align="center" class="nborder">
					        <a onclick="linkToInfo('/admin_Redpacket_view.action','redpacket.red_id=${redpacket.red_id?if_exists}');"  href="###"> 
						    <img src="/include/common/images/bj.gif" title="修改红包信息" />
						    </a>
						       <a onclick="linkToInfo('/admin_Redpacket_redbaglist.action','redpaceket_id=${redpacket.red_id?if_exists}');"  href="###"> 
						     <img src="/include/common/images/view.gif" title="查看红包下载记录" />
						   </a>	
					       <a onclick="showDownDiv('${redpacket.red_id?if_exists}')"  href="###">
						   <img src="/include/common/images/down.gif" title="下载红包码" />
						   </a>	
					    </td>
				    
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Redpacket_add.action','');" value="添加红包">
             <input type="button" class="rbut" onclick="updateBatchState('1','redpacket.red_id','/admin_Redpacket_updateStateB2C.action','启用');" value="启用">             
			 <input type="button" class="rbut" onclick="updateBatchState('0','redpacket.red_id','/admin_Redpacket_updateStateB2C.action','禁用');" value="禁用">  
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <@s.hidden  name="red_no_s"/>
		 <@s.hidden  name="info_state_s"/>
		 <@s.hidden  name="money_s"/>
		 <@s.hidden  name="start_time_s"/>
		 <@s.hidden  name="end_time_s"/>
		 <@s.hidden  name="member_level_s"/>
		 <@s.hidden  name="down_num_s" id="down_nmu_s"/>
		 <@s.hidden  name="red_id_s" id="red_id"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Redpacket_list.action"   method="post"  id="form_search_id">
<!--dd-->
	<table>
	<tr>
		<td class="searchDiv_td">名称:</td>
		<td><@s.textfield name="red_name_s"/></td>
	</tr>
	
	
	<tr>
		<td class="searchDiv_td">号码:</td>
		<td><@s.textfield name="red_no_s"/></td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">状态:</td>
		<td><@s.select name="info_state_s" list=r"#{'':'请选择','1':'启用','0':'禁用'}"/></td>
	</tr>
	
	<tr>
		<td class="searchDiv_td">金额:</td>
		<td><@s.textfield name="money_s"/></td>
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
<!--下载区域开始-->
<div  style="display:none;"  id="downDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td class="searchDiv_td">下载数量:</td>
				<td><@s.textfield name="down_num_s" onkeyup="checkNum(this)" id="down_num"/></td>
			</tr>
			
			<tr>
	             <td align="center" colspan="2" style="border:0px;">
	             	 <input type="button"  onclick="downCoupon('/admin_Redpacket_downRedBag.action');"  value="下载">
	             	 <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('downDiv')"/>
	             </td>			
			</tr>
		</table>
	</div>		   
<!--下载区域结束-->
</body>
</html>

