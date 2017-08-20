<html>
  <head>
    <title>国际运费模版</title>
  </head>
  <body>
<@s.form action="/admin_Internationaltariff_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：商城管理 > 物流管理 > 国际运费模版</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td>国际物流名称:</td><td><@s.textfield name="title_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                  	<th width="10%">国际物流名称</th>
                  	
                  	<th width="10%">国际运单单价<font color="red">(元)</font></th>
			    
                 	<th width="10%">立方数<font color="red">(立方厘米)</font></th>
			    
                 	<th width="10%">超重重量<font color="red">(KG)</font></th>
			    
                 	<th width="10%">超重运费单价<font color="red">(元)</font></th>
			    
                 	
			    
                 <th  width="10%">操作</th>
               </tr>
			    <#list internationaltariffList as internationaltariff>
				 <tr >
			            <td align="center">${internationaltariff.ief_name?if_exists}</td>				    
			         	<td align="center">${internationaltariff.ief_price?if_exists}</td>
				    
			         	<td align="center">${internationaltariff.ief_cubic?if_exists}</td>
				    
			         	<td align="center">${internationaltariff.ief_overweight?if_exists}</td>
				    
			         	<td align="center">${internationaltariff.ief_overweight_price?if_exists}</td>
				    <td align="center"><a onclick="linkToInfo('/admin_Internationaltariff_view.action','internationaltariff.ief_id=${internationaltariff.ief_id?if_exists}');">
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
             <input type="button" style="display:none;" class="rbut" onclick="linkToInfo('/admin_Internationaltariff_add.action','');" value="添加">
             <input type="button" style="display:none;" class="rbut"onclick="delInfo('internationaltariff.ief_id','/admin_Internationaltariff_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
  </body>
</html>

