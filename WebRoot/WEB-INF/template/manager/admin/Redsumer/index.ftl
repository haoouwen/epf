<html>
  <head>
    <title>红包消费码列表</title>
  </head>
  <body>
<@s.form action="/admin_Redsumer_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：1 > 2 > 3</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >标题:</td><td><@s.textfield name="title_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
                 <td><input type="button" onclick="searchShowDIV('searchDiv','300px','130px');" class="rbut" value="高级查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('redsumer.redsumer_id')"/></th>
                 
                 	<th width="10%">redsumer_id</th>
			    
                 	<th width="10%">red_id</th>
			    
                 	<th width="10%">use_state</th>
			    
                 	<th width="10%">cust_id</th>
			    
                 	<th width="10%">use_date</th>
			    
                 	<th width="10%">order_id</th>
			    
                 <th  width="10%">操作</th>
               </tr>
			    <#list redsumerList as redsumer>
				 <tr >
			         <td><input type="checkbox" name="redsumer.redsumer_id" value="${redsumer.redsumer_id?if_exists}"/></td>
			         
			         	<td align="center">${redsumer.redsumer_id?if_exists}</td>
				    
			         	<td align="center">${redsumer.red_id?if_exists}</td>
				    
			         	<td align="center">${redsumer.use_state?if_exists}</td>
				    
			         	<td align="center">${redsumer.cust_id?if_exists}</td>
				    
			         	<td align="center">${redsumer.use_date?if_exists}</td>
				    
			         	<td align="center">${redsumer.order_id?if_exists}</td>
				      
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Redsumer_view.action','redsumer.redsumer_id=${redsumer.redsumer_id?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Redsumer_add.action','');" value="添加红包消费码">
             <input type="button" class="rbut" onclick="updateBatchState('0','redsumer.redsumer_id','/admin_Redsumer_updateStateB2C.action','推荐');" value="推荐">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','redsumer.redsumer_id','/admin_Redsumer_updateStateB2C.action','取消推荐');" value="取消推荐">  
             <input type="button" class="rbut"onclick="delInfo('redsumer.redsumer_id','/admin_Redsumer_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Redsumer_list.action"   method="post"  id="form_search_id">
<!--dd-->
	<table>
	<tr>
		<td class="searchDiv_td">标题:</td>
		<td><@s.textfield name="title_s"/></td>
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

