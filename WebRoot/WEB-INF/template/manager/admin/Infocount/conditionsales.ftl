<html>
<head>
    <title>销售统计</title>
</head>
<body>
<div class="postion">
  <!--当前位置-->
  当前位置:运营统计 > 报表统计  > 销售统计
</div>
<div class="rtdcont">
	<div class="rdtable">
		<div class="adv_pos">
			<ul style="font-size:16px;font-weight:bold;color:#336699;">	   					   
			  	 <#list salesconditionList as salescondition >			  	    
					       <li style="border:#cccccc solid 1px ;width:150px;height:50px;background:#efefef;text-align:center;line-height:50px;float:left;margin:0 0 20px 40px;">
					       	<div>
						       	<a style="text-decoration:none;"  onclick="linkToInfo('/admin_Infocount_countsalesinfo.action','sales_condition_s=${(salescondition.para_key)?if_exists}');" >
						       	 ${(salescondition.para_value)?if_exists}
						       	</a>
					       	</div>
					       </li>
				 </#list>
		    </ul>
		   <div class="clear"></div>
	    </div>		
	</div>

</div>
<div class="clear"></div>
</body>
</html>