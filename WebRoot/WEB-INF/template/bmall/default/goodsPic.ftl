<!--大图显示产品-->
<div class="debigpic">
 	  <div class="jqzoom" id="spec-n1">
  			<#if imgGroup?if_exists!=''>
  				<#list imgGroup?split(',') as firstImg>
  					<#if firstImg_index='0'>
  						<#if firstImg?index_of('big') gt 0>
  							<img src="${firstImg?if_exists?replace('big','mid')}" jqimg="${firstImg?if_exists}">
  						<#else>
  							<img src="${firstImg?if_exists}" jqimg="${firstImg?if_exists}">
  						</#if>
	  				</#if>
	  			</#list>
			<#else>
				<img src="${cfg_nopic?if_exists}" jqimg="${cfg_nopic?if_exists}">
	        </#if>
	  </div>
	  
	  <div  class="specn5" id="spec-n5">
	  <div class="lcontrol" id="spec-left"><img src="/malltemplate/bmall/images/left.gif"/></div>
	    
		    <div  class="speclist" id="spec-list">
		        <ul class="listul">
		        <#if imgGroup!=''>
		  			<#list (imgGroup)?if_exists?split(",") as img>
			          <li><img src="${img?if_exists?replace('big','small')}"></li>
				 	</#list>
				 	<input type="hidden" id="img_str" value="${(imgGroup)?if_exists}">
				<#else>
					<li><img src="${cfg_nopic?if_exists}"></li>
		        </#if>
		        </ul>
		    </div>
	    
	  <div class="rcontrol" id="spec-right"><img src="/malltemplate/bmall/images/right.gif" /></div>
  </div>
</div>