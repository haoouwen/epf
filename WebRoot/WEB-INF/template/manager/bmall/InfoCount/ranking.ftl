<html>
<head>
	<title>销售量/额排行</title> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
    <script type="text/javascript">  
                function submitrank(){
                   $("#Fromrank").submit();
                }
                function showSearch(xstype){
                  if(xstype=='momeny'){
                     $("#momeny").val("1");
                     $("#count").val("");
                     $("#Fromrank").submit();
                  }
                  if(xstype=='count'){
                     $("#count").val("1");
                     $("#momeny").val("");
                     $("#Fromrank").submit();
                  }
                }
            </script>  
</head>
<body>

<@s.form id="Fromrank" action="/bmall_InfoCount_ranking.action" method="post">
<div class="rightside f_right">
   <div class="postion">
	数据统计</a><span>></span><a href="#">销售统计</a><span>></span><a href="#">销售量/额排行</a>
   </div>
   <div class="ropot">
       <h2 class="rotitle">
       		<span><td class="fthstyle1">销售量/额排行</td></span>
       </h2>
       
       <div class="rosearch">
         <input type="button" value="按销售额" class="submitbut"  onclick="showSearch('momeny');"/>
         <input type="button" value="按销售量" class="submitbut"  onclick="showSearch('count');"/>
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate"  readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				      &nbsp;至&nbsp;
		    <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate"readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
				                <input type="button" name="search" value="搜索" onclick="submitrank();"/>&nbsp;
       </div>
       
       <table cellspacing="0" class="bmall_list_table">
         <tr>
   
     	 <th width="10%" class="fthstyle1" align="left" >排名</th>
    
     	 <th width="50%"  class="fthstyle1">商品名称</th>
    
         <th width="20%"  class="fthstyle1">销售量</th>
    
     	 <th width="20%"   class="fthstyle1">销售额</th>
    
  </tr>
  <#assign n = 0 />
   <#list inforankingList as inforanking>
  <#assign n = n+1 />
  <tr>
    
        <td align="center">${n}</td>
        
    	<td align="center">${inforanking.goods_name?if_exists}</td>
    
    	<td align="center">${inforanking.num?if_exists}</td>
    
    	<td align="center">${inforanking.price?if_exists}</td>
          
  </tr>
  </#list>
        </table>
        <div class="listbottom">
        	${pageBar?if_exists}
        </div>
   </div>
</div>
<div class="clear"></div>
<@s.hidden name="momeny" id="momeny" />
<@s.hidden name="count" id="count" />	
</@s.form>
</body>
</html>

















