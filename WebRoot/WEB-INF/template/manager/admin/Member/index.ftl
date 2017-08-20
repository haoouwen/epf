<html>
  <head>
    <title>会员列表</title>
    <script type="text/javascript" src="/include/admin/js/member.js"></script>
    <script type="text/javascript" src="/include/admin/js/chartAnalysis.js"></script>
    <script src="/include/components/datadrawinglist/js/highcharts.js"></script>
    <script src="/include/components/datadrawinglist/js/modules/exporting.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
	 		var chagestr=true;
	 		$(".chartaction").click(function(){
               $(".chart_tr").animate(
                   {height: 'toggle', opacity: 'toggle'}, "2500"
               );
               
               $(".chart_type").animate(
                   {height: 'toggle', opacity: 'toggle'}, "2500"
               );
               if(chagestr==true){
                     $(".chartaction").html("收缩图形");
                                        $("#chart_data").css("display","block");
                     chagestr=false;
               }else{
                    $(".chartaction").html("展开图形");

                     $("#chart_data").css("display","none");
                     chagestr=true;
               }
            
            }); 
	 		
	    });

    </script>
    <script type="text/javascript" src="/include/admin/js/chartAnalysis.js"></script>
    <script src="/include/components/datadrawinglist/js/highcharts.js"></script>
    <script src="/include/components/datadrawinglist/js/modules/exporting.js"></script>
    
    <style>
    
      ul li{
         float:left;
      }
    </style>
    
  </head>
  <body>
  

<body>
<@s.form action="/admin_Member_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 会员管理 > 会员列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>会员名称:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:100px;"/></td>
			<td>&nbsp;地区:</td>
			<td><@s.select id="area_attr"  name="area_attr_s"  list="areaList" listValue="area_name"
						       listKey="area_id"   headerKey="" headerValue="请选择地区" /></td>
		    <td>&nbsp;门店:</td>
		    <td><@s.textfield name="store_code_s"  cssStyle="width:100px;"/></td>
	        <td><input type="submit" class="rbut"  value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
  
  <div class="rtable"></div>
   <div class="bsbut">
      &nbsp;&nbsp;会员总数量：<#if totalMemberMap !=null && totalMemberMap!="">${totalMemberMap.get("totalMember")} </#if>  &nbsp;&nbsp;
      消费总金额：<#if totalAmountMap !=null && totalAmountMap!="">${totalAmountMap.get("totalAmount")} </#if>元
      
      <span  class="chartaction" style="float:right;padding-right:20px;font-weight:bold;">展开图形</span>
      
   </div>
   
    <div class="rtable" ></div>
     <div class="rtable" id="chart_data" style="display:none;">
     <table cellpadding="0" cellspacing="0" width="100%" >
	   <tr class="chart_type">
	     <td>
	       <ul class="inforstul">
	        <li><a href="javascript:void(0);" onclick="changeChart('column');"k><img src="/include/admin/images/poll.gif">柱状图</a></li> 
	        <li><a href="javascript:void(0);" onclick="changeChart('pie');"><img src="/include/admin/images/chart.gif">饼状图</a></li>
	        <!--<li><a href="javascript:void(0);" onclick="changeChart('line');"><img src="/include/admin/images/chart.gif">折线图</a></li>-->
	       </ul>
	     </td>
	   </tr>
	   
	    <tr class="chart_tr">
	     <td align="center">
	        
	        <div id="container_columnmember" style="width:85%;height: 400px; "></div>
	        
            <hr  id="container_hr" style="border:1px dotted #B3EE3A;"/>
	        
	        <div id="container_columnamount" style="width:85%;height: 400px;"></div>
	        
	        <div id="container_pie" style="width:80%;height: 400px;display:none;"></div>
	        
	     </td>
	   </tr>
	</table>
   </div>

<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	     <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('member.cust_id')"/></th>
   
    
     	 <th width="8%">会员编号</th>

     	 <th width="8%">姓名</th>
    
     	 <th width="8%">邮箱</th>
    
     	 <th width="8%">手机</th>
     	 
     	 <th width="8%">注册时间</th>
     	 
     	 <th width="10%">所属区域</th>
     	 
     	 <th width="8%">消费金额(元)</th>
     	 
     	 <th width="8%">会员级别</th>
     	 
     	 <th width="8%">成长值</th>
     	 
     	  <th width="5%">状态</th>
    
         <th width="10%">操作</th>
  </tr>
  
  <#list memberList as member>
  <tr>
    <td><input type="checkbox" name="member.cust_id" value="${member.cust_id?if_exists}"/></td> 
 		<td align="center">${member.membernum?if_exists}</td>
 		<td align="center">${member.user_name?if_exists}</td>
        <td align="center">${(member.email)?if_exists}</td>
        <td align="center">${member.cellphone?if_exists}</td>
        <td align="center">${member.in_date?if_exists}</td>
        <td align="center">${member.area_attr?if_exists}</td>
        <td align="center">${member.total_amount?if_exists}</td>
        <td align="center">${member.buy_level_name?if_exists}</td>
        <td align="center">${member.growthvalue?if_exists}</td>
        <td align="center">
	    	<#list infoStateList as infoState>
				<#if member.info_state?if_exists==infoState.para_value>
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
    	 <a onclick="linkToInfo('/admin_Member_view.action','member.cust_id=${member.cust_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
         <a onclick="linkToInfo('/admin_Memberinter_list.action','custId=${member.cust_id?if_exists}&memberinter_name_s=${member.cust_name?if_exists}&two_pages_link=no&enter=0');" title="会员积分管理"><img src="/include/admin/images/inter.gif" /></a>
         <a onclick="linkToInfo('/admin_Memberfund_list.action','cust_id=${member.cust_id?if_exists}&memberfund_name_s=${member.cust_name?if_exists}&two_pages_link=no&enter=0');" title="会员余额管理"><img src="/include/admin/images/money.gif" /></a>
    </td>
  </tr>
  </#list>
      </tr>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Member_add.action','');" value="添加会员">
     <input type="button" class="rbut" onclick="delInfo('member.cust_id','/admin_Member_delete.action')" value="删除">
   
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="nav_post_s"/>
  <@s.hidden name="isshow_s"/>
   <@s.token/> 
   <@s.hidden name="memberuser.user_id" value="${(memberuser.user_id)?if_exists}"/>
 <!--表单框隐藏域存放-->  
</@s.form>

  </body>
</html>




</body>
</html>

