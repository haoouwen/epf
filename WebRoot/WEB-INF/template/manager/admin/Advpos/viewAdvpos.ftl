<html>
<head>
    <title>广告位</title>
</head>
<body>
<div class="postion">
  <!--当前位置-->
  当前位置:网站管理 > 广告管理  > 广告位
</div>
<div class="rtdcont">
	<div class="rdtable">
<div >
					<div class="adv_pos">
						<ul style="font-size:16px;font-weight:bold;color:#336699;">	   					   
						  	 <#list adv_posList as advLists >			  	    
								       <li style="border:#cccccc solid 1px ;width:150px;height:50px;background:#efefef;text-align:center;line-height:50px;float:left;margin:0 0 20px 40px;">
								       	<div>
								       	<a style="text-decoration:none;"  onclick="linkToInfo('/admin_Advpos_list.action','adv_pos_s=${advLists.para_value?if_exists}');" >
								       	 ${advLists.para_key?if_exists}</a>
								       	</div>
								       </li>
							 </#list>
					   </ul>
					   <div class="clear"></div>
				   </div>
				</div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</body>
</html>