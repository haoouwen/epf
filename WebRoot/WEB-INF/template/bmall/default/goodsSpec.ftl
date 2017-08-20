<link href="/malltemplate/bmall/css/spec.css" rel="stylesheet" type="text/css"/>
<script src="/malltemplate/bmall/js/goodsSpec.js" type="text/javascript"></script>
<!--产品规格-->
<#if is_more_attr=='0'>		
 <#if selfspecnameList?if_exists && (selfspecnameList?size > 0)>
    <#list selfspecnameList as ssn>	
    	<#if (ssn.show_type)?if_exists !="1">
              <tr class="specstr"><th id="${(ssn.spec_serial_id)?if_exists}">${(ssn.sname)?if_exists}</th>
                  <td>
                    <div class="spttext">
                      <ul class="selsize">
                      <#list selfsepcvalueList as ssv> 
		                <#if ssv.spec_serial_id==ssn.spec_serial_id>
		                 <li><div><a title="${(ssv.self_spec_value)?if_exists}" alt="${(ssv.self_spec_value)?if_exists}" id="${(ssv.self_size_id)?if_exists}" href="javascript:void(0);">${(ssv.self_spec_value)?if_exists}</a></div></li>  
		            	</#if>
           			</#list>
                       
                      </ul>
                    </div>
                  </td>
              </tr>
               <#else>
              <tr class="specstr"><th id="${(ssn.spec_serial_id)?if_exists}">${(ssn.sname)?if_exists}</th>
                  <td >
                    <div class="sptpic">
                      <ul class="selcolor">
                      	<#list selfsepcvalueList as ssv> 
		                <#if ssv.spec_serial_id==ssn.spec_serial_id>  
		                	 <li>
		                	 <div>
		                	 <a title="${(ssv.self_spec_value)?if_exists}" alt="${(ssv.self_spec_value)?if_exists}" id="${(ssv.self_size_id)?if_exists}" href="javascript:void(0);" style="position:relative;">
		                		<img src="${(ssv.self_spec_img)?if_exists}"/>
		                		<i id="${(ssv.self_size_id)?if_exists}" img="${(ssv.self_img)?if_exists}" style="width:32px;height:32px;padding-left:-60px;position:absolute;left:-1px;top:-1px;"></i>
		                	</a>
		                	 
		                	 </div></li>  
		                	
		            	</#if>
           				</#list>
                        
                      </ul>
                    </div>
                  </td>
              </tr>
		 </#if>
    </#list>
</#if>
</#if>