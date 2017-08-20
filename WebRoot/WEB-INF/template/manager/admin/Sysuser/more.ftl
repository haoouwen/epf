<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>待审核信息</title>
<link href="/include/css/admin/backindex.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="rtdcont">
	<div class="rttable">
	    <table width="100%">
                 <#list countlist as count>
					 <li>
                        <span class="yw_abc yw_a" >${count.module_name?if_exists}:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_${count.table_name?if_exists}_auditList.action?info_state_s=0">${count.countaudit?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_${count.table_name?if_exists}_list.action?today=now">${count.todaycount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	                  </li>
                  </#list>
                  <li>
                        <span class="yw_abc yw_a" >会员升级:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Memberupgrade_list.action?audit_state_s=0">${upgroups?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_Memberupgrade_list.action?today=now">${upcount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
	               <li>
                        <span class="yw_abc yw_a" >实名认证:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Certification_auditList.action?state_s=0">${auditcount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_Memberupgrade_list.action?today=now"></a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
	              <li>
                        <span class="yw_abc yw_a" >荣誉资质:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Membercert_list.action?cert_state_s=1">${auditmcertcount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_Membercert_list.action?today=now">${certcount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
	               <li>
                        <span class="yw_abc yw_a" >域名绑定:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Topdomain_list.action?enabled_s=0">${toaudit?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_Topdomain_list.action?today=now">${tocount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
	               <li>
                        <span class="yw_abc yw_a" >商机订阅:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Subscribe_list.action?enabled_s=1">${subaudit?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_Subscribe_list.action?today=now">${subcount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
	              <li>
                        <span class="yw_abc yw_a" >评论:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Membercomment_list.action?comm_state_s=1">${comaudit?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="admin_Membercomment_list.action?today=now">${commcount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
	              <li>
                        <span class="yw_abc yw_a" >留言:</span>
                        <span class="yw_abc yw_b" >未审核</span> 
                        <span class="yw_abc yw_c" ><a  href="/admin_Memberleave_list.action?is_del_s=1">${leaveaudit?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>    
                        <span class="yw_abc yw_bb">今日新增:</span> 
                        <span class="yw_abc yw_c" ><a href="/admin_Memberleave_list.action?today=now">${leavecount?if_exists}</a></span> 
                        <span class="yw_abc yw_d" > 条 </span>                    
	              </li>
	              
                 </table>	 
	</div>
	<div class="clear"/>
</div>
</body>
</html>
