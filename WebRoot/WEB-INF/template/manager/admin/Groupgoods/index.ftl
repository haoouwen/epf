<html>
  <head>
    <title>团购商品列表</title> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
     <!--所属分类开始-->
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","group");
      });
	</script>
	<!--所属分类结束-->
  </head>
  <body>
<@s.form action="/admin_Groupgoods_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：营销推广 > 团购商品 > 团购商品列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>团购标题:</td>
			<td><@s.textfield name="group_title_s"  cssStyle="width:200px;"/></td>
			<td class="tdpad">审核状态:</td>
			<td>
			<select name="info_state_s" style="width:70px;">
				<option value="" selected>请选择
				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='3'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    </#if>
    			</#list>
			</select>
			</td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	        <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAllBoxs('checkall','groupgoods.trade_id')"/></th>
		    <th width="5%"  >排序</th>
		    <th width="22%"  >团购标题</th>
		    <th width="13%"  >开始时间</th>
		    <th width="13%"  >结束时间</th>
		    <th width="13%"  >发布时间</th>
		    <th width="14%"  >会员名称</th>
		    <th width="6%"  >审核状态</th>
		    <th width="5%"  >操作</th>
	  </tr>
	  
	   <#list groupgoodsList as groupgoods>
	    <tr >
		    <td><#if groupgoods.active_state?if_exists="2" &&  groupgoods.info_state?if_exists="1"> 
					<input type="checkbox" name="groupgoods.trade_id" disabled="true"/>
		    	<#else>
		    		<input type="checkbox" name="groupgoods.trade_id" value="${groupgoods.trade_id?if_exists}"/>
		    	</#if>
		    </td>
		    <td align="center">
				<input name="isort_no" value="${groupgoods.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/>
		    </td>
		     <td align="center">
				<a onclick="linkToInfo('/admin_Groupgoods_view.action','groupgoods.trade_id=${groupgoods.trade_id?if_exists}');" title="${groupgoods.group_title?if_exists}">
	        	   <#if groupgoods.group_title?if_exists? length lt 36>
	                 ${groupgoods.group_title?if_exists}
	                <#else>
	                 ${groupgoods.group_title[0..35]?if_exists}..
	                </#if>
         	  </a> 
                <#if groupgoods.active_state?if_exists="1">
                	<font class="bluecolor">未开始</font>
                <#elseif groupgoods.active_state?if_exists="2">
                	<font color="green">进行中</font>
                <#elseif groupgoods.active_state?if_exists="3">
                	<font color="red">已结束</font>
                </#if>
		    </td>
		    <td align="center">
				${groupgoods.start_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
		     <td align="center">
				${groupgoods.end_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
		     <td align="center">
				${groupgoods.in_date?string("yyyy-MM-dd HH:mm:ss")}
		    </td>
		     <td align="center">
				 <#if groupgoods.cust_name?if_exists!=''&& groupgoods.cust_name?if_exists!=null>
    			${groupgoods.cust_name?if_exists}
		    	<#else>
		    	-
		    	</#if>	
		    </td>
		     <td align="center">
			     <#list infoStateList as infoState>
    				<#if groupgoods.info_state?if_exists==infoState.para_value>
    					<#if infoState.para_value?if_exists='1'>
					        <font class='greencolor'>${infoState.para_key?if_exists}</font>
						<#elseif infoState.para_value?if_exists='3'>
						    <font class="redcolor">${infoState.para_key?if_exists}</font>
    					<#elseif infoState.para_value?if_exists='0'>
					       <font class="redcolor">${infoState.para_key?if_exists}</font>
					    <#elseif infoState.para_value?if_exists='2'>
					        <font class="bluecolor">${infoState.para_key?if_exists}</font>
					    </#if>
					    <#break/>
				    </#if>
    			 </#list>
		    </td>
		     <td align="center">
				<a onclick="linkToInfo('/admin_Groupgoods_view.action','groupgoods.trade_id=${groupgoods.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
	  </tr>
	  </#list>
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Groupgoods_add.action','');" value="添加团购商品">
     <input type="button" class="rbut"onclick="updateSort('groupgoods.trade_id','isort_no','/admin_Groupgoods_updateSort.action');" value="排序">
     <input type="button" class="rbut"onclick="delInfo('groupgoods.trade_id','/admin_Groupgoods_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cust_name_s"/>
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden  name="st_put_date_s"/>
  <@s.hidden  name="st_en_date_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Groupgoods_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">发布会员:</td>
			<td><@s.textfield name="cust_name_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">团购标题:</td>
			<td><@s.textfield name="group_title_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">是否推荐:</td>
			<@s.radio name="is_recom_s" list=r"#{'0':'是','1':'否'}"  />
		</tr>
		 
		<tr>
			<td class="searchDiv_td">所属分类:</td>
			<td> 
			<table>
         		<tr>
         			<td class="tdbottom">
         				<div id="catDiv" style="margin-left:-5px;"></div>
         			</td>
              	</tr>
         	</table>
         </td>
		</tr>
		<tr>
			<td class="searchDiv_td">审核状态:</td>
			<td>
			<select name="info_state_s" style="width:70px;">
				<option value="" selected>请选择
				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='3'>
				       <option value="${infoState.para_value?if_exists}">${infoState.para_key?if_exists}
				    </#if>
    			</#list>
			</select>
			</td>
		</tr>
		<tr>
			<td class="searchDiv_td">开始时间:</td>
			<td>
				 <input type="text"  name="st_put_date_s"  value="${st_put_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
				&nbsp;至&nbsp;
		        <input type="text"  name="en_put_date_s"  value="${en_put_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			</td>
		</tr>
		<tr>
			<td class="searchDiv_td">结束时间:</td>
			<td>
				<input type="text"  name="st_en_date_s"  value="${st_en_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown3" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown4\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
				&nbsp;至&nbsp;
		        <input type="text"  name="en_en_date_s"   value="${en_en_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown4" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown3\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('','cat_attr','','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
