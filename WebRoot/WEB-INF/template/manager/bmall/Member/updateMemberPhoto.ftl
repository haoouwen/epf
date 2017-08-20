<html>
  <head>
    <title>修改头像</title>
    <#include "/include/uploadifive.html">
    <link href="/include/bmember/css/account.css" rel="stylesheet" type="text/css">	
  </head>
  <body>
	
<@s.form action="/bmall_Member_updatePhoto.action" method="post" validate="true" >

  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>账户信息</span></h2>
        <div class="accontDiv">
            <div class="tabIDiv">
               <a href="/bmall_Memberuser_view.action?parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}" class="niBut">基本信息</a><a href="#" class="siBut">修改头像</a>
            </div>
            <table width="100%" cellpadding="0" cellspacing="0" class="ainfoTab">
       		    <tr>
       		    	<th>我的头像</th>
       		    	<td colspan="3">
       		    	  <#if (member.logo_path)?if_exists!=null && (member.logo_path)?if_exists!=''>
			       	    <img src="${member.logo_path?if_exists}"  width="110" height="110">
			      	  <#else>
			       		<img src="/include/bmember/images/defalutHead.gif" width="110" height="110">
			      	  </#if>
       		    	</td>
       		    </tr>
       		    
       		    <tr>
       		    	<th>上传头像</th>
       		    	<td class="headTd">
       		    	 <input type="text" name="logo_path" id="uploadresult" style="float:left;width:260px;height:25px;line-height:25px;"/>
       		    	 <input id="file_upload" type="file" name="file" />
       		    	 <input id="showpic" type="button" onclick="showpicture('uploadresult');" value="预览" style="float:left;background:#f1f9eb;border:1px solid #bfd7af;color:#333;text-align:center;width:60px;height:32px;"/>
		             <div id="tip-queue" style="clear:both;"></div>
			         <script>uploadone('file_upload','uploadresult',false,'tip-queue');</script>
       		    	</td>
       		    </tr>
       		    
       		    
       		    

       		    <tr>
       		    	<th>.</th>
       		    	<td colspan="3">
       		    	  <input type="submit" class="submitbut" value="提交"/>
       		    	</td>
       		    </tr>
            </table>
        </div>
                
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
  </@s.form>
</body>

</html>

