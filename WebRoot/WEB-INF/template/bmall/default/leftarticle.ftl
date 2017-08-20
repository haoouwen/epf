<#if article.cat_attr !='2624811457'>
    <div class="w245">
       <h2>帮助中心</h2>
       <div class="helpCla">
          <#list articleMap.categoryList as catone>
            <#if catone.cat_level=='2'>
              <ul class="helpul">
               <li>
                 <h3>${catone.cat_name?if_exists}</h3>
                  <ul>
                   <#list articleMap.categoryList as cattwo>
                     <#if catone.cat_id == cattwo.up_cat_id>
                      <li><a href="/mall-articleattrdetail-${cattwo.cat_id}.html">${cattwo.cat_name?if_exists}</a></li>
                     </#if>
                   </#list>
                 </ul>
               </li>     
             </ul>
          </#if>
         </#list>
       </div>
    </div>
 <#else>
    <div class="w245">
       <h2><a href="/mall-sitenotice-2624811457.html" target="_self" style="color:#fff;">网站公告</a></h2>
       
    </div>
 </#if>