<html>
  <head>
    <title>红包下载记录</title>
  <script type="text/javascript" src="/include/admin/js/redpacket.js"></script>  
  </head>
  <body>
<@s.form action="/admin_Redpacket_redbaglist.action" method="post" id="indexForm">

        <div class="postion">当前位置：营销推广>红包管理 > 红包列表 > 红包下载记录</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >红包兑换码:</td><td><@s.textfield name="dowm_red_id_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('exredbag.ex_id')"/></th>
                 
                 	<th width="30%">红包名称</th>
                 
                 	<th width="20%">兑换码</th>
			    
                 	<th width="20%">状态</th>
			    
                 	<th width="27%">下载时间</th>
               </tr>
			    <#list exredbagList as exredbag>
				 <tr >
			         <td><input type="checkbox" name="exredbag.ex_id" value="${exredbag.ex_id?if_exists}"/></td>
			         
			         	<td align="center">${exredbag.red_name?if_exists}</td>
			         	
			         	<td align="center">${exredbag.red_no?if_exists}</td>
			         	
			         	<td align="center"><#if exredbag.ex_state=="0"><font color='green'>未兑换<#else><font color='red'>已兑换</#if></td>
				    
			         	<td align="center">${exredbag.in_date?if_exists}</td>
			         	
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
              <input type="button" class="rbut"onclick="exprotredbagExcel('exredbag.ex_id','/admin_Redpacket_exportredbagInfo.action');"  value="重新下载">
           </div>
        </div>
        <@s.hidden name="exredbag.ex_id"/>
        <@s.hidden id="redkeyId" name="redkeyId"/>
</@s.form>
<!--下载区域开始-->
<div  style="display:none;"  id="downDiv"  class="searchDiv">
		<table width="100%" border="0" cellspacing="0">
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

