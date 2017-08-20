<html>
  <head>
    <title>修改红包</title>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>    
  </head>
  <body>  
<@s.form action="/admin_Redpacket_update.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：营销推广>红包管理 > 修改红包
</div>
<div class="rtdcont">
	<div class="rdtable">
<h2>基本信息</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name"><font color='red'>*</font>名称</td>
	           <td class="table_right">
	             	<@s.textfield name="redpacket.red_name" cssClass="txtinput" maxLength="50" />
	             	<@s.fielderror><@s.param>redpacket.red_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>号码</td>
	           <td class="table_right">
	             	<@s.textfield name="redpacket.red_no" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>redpacket.red_no</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">状态:</td>
	           <td class="table_right">
	             	<@s.radio name="redpacket.info_state" list=r"#{'1':'启用','0':'禁用'}" />
	             	<@s.fielderror><@s.param>redpacket.info_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">是否支持前台领取:</td>
	           <td class="table_right">
	             	<@s.radio name="redpacket.is_show" list=r"#{'1':'是','0':'否'}"/>
	             	<@s.fielderror><@s.param>redpacket.is_show</@s.param></@s.fielderror>
	           </td>
	        </tr>		    
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>金额</td>
	           <td class="table_right">
	             	<@s.textfield name="redpacket.money" cssClass="txtinput" maxLength="20" onkeyup="checkNum(this)"/>
	             	<@s.fielderror><@s.param>redpacket.money</@s.param></@s.fielderror>
	           </td>
	        </tr>		
	        
			 <tr>
	           <td class="table_name"><font color='red'>*</font>发布数量</td>
	           <td class="table_right">
	             	<@s.textfield name="redpacket.red_num" cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>redpacket.red_num</@s.param></@s.fielderror>
	           </td>
	        </tr>		        
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>描述</td>
	           <td class="table_right">
	             	<@s.textarea name="redpacket.content" style="width: 300px; height: 100px;" maxLength="50" onpropertychange="if(value.length>50) value=value.substr(0,49)" />
	             	<@s.fielderror><@s.param>redpacket.content</@s.param></@s.fielderror>
	             	*(规则描述限制输入50个字)
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>开始时间</td>
	           <td class="table_right">
	             	<input type="text"  name="redpacket.start_time" value="<#if redpacket!=null>${redpacket.start_time?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
	             	<@s.fielderror><@s.param>redpacket.start_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>结束时间</td>
	           <td class="table_right">
	             	<input type="text"  name="redpacket.end_time" value="<#if redpacket!=null>${redpacket.end_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
	             	<@s.fielderror><@s.param>redpacket.end_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>会员级别</td>
	           <td class="table_right">
	             	<#list commparaList as commpara>
	             	  <input type="checkbox" name="redpacket.member_level" value="${commpara.level_code}" <#if redpacket.member_level?if_exists?index_of('${commpara.level_code?trim}') gt -1>checked</#if>/>${commpara.level_name}
	             	</#list>
	             	<@s.fielderror><@s.param>redpacket.member_level</@s.param></@s.fielderror>
	           </td>
	        </tr>	

        </table>	
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="redpacket.red_id"/>
           <@s.token/> 
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Redpacket_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

