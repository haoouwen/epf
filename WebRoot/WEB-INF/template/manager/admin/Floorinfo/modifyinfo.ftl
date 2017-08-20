<html>
  <head>
    <title> 编辑楼层信息</title>
     <#include "/include/uploadInc.html">
       <script type="text/javascript" src="/include/admin/js/floorinfo.js"></script>
       <script src="/include/admin/js/tab.js" type="text/javascript"></script>
	  <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
       <link href=" /include/admin/css/floorinfo.css" rel="stylesheet" type="text/css" />
       <script type="text/javascript"  src="/include/common/js/jquery.masonry.min.js"></script>
	    <script type="text/javascript">
			$(document).ready(function() {
			      //所属分类的回选
			       loadCatSeCat("${floorinfo.cat_attr?if_exists}","","","goods");
			});
		</script>
  </head>
  <body>
  <#include "/WEB-INF/template/manager/searchCheckBoxGoodsTab.ftl"/>
<@s.form action="/admin_Floorinfo_updatedata.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:网站管理 > 首页楼层  > 楼层列表  > 编辑楼层信息
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td width="13%" class="table_name">楼标</td>
             <td  width="15%">
              <font color='red'> ${(floorinfo.f_tag)?if_exists}</font>
             </td>
               <td  class="table_name" width="10%">楼层名称</td>
             <td width="15%">
                  <font color='red'> ${(floorinfo.f_name)?if_exists}</font>
             </td>
               <td  class="table_name" width="12%">楼层tab[0]名称</td>
             <td>
                  <font color='red'> ${(advfoolmark_tab)?if_exists}</font>
             </td>
           </tr>
            <tr>
             <td class="table_name">楼层数据及<br/>楼层tab[0]数据<font color='red'>*</font></td>
             <td  colspan="5">
             	 <!--广告位--> 
			        <div class="advDiv" id="oper_div">
			          <!---->
				          <div class="ladvDiv">
						             <ul>
							               <li  class="firstli">
									                 <p><b>广告位大小(220px*330px)：</b></p>
									                 <p >
														   <table border="0" cellpadding="0" cellspacing="0"  width="300px">
											             		<tr width="300px">
											             		   <td  width="50px">
											             			  广告图片
											             			</td>
											             			<td >
											             				    <img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult_ad');"/>
											             			</td>
											             			<div id="fileQueue"></div>
											             			<td >
												              			<@s.hidden type="hidden" id="uploadresult_ad"  name="floormodel.tab0_pos1_img"/>
											             				<input type="file" name="uploadifyfile" id="uploadifyfile_ad"/>
											             				<script>sysUploadImg("uploadifyfile_ad","uploadresult_ad","fileQueue");</script>
											             			</td>
											             			
											             			<td id="show_pic_tip"></td>
											             		</tr>
											             		 <tr> 
														             <td  width="50px">
											             			  链接地址
											             			</td>
											             			<td  colspan="3">
											             			   <@s.textfield id="specname" name="floormodel.tab0_pos1_url"  id="tab0_pos1_url"   cssStyle="width:200px"  maxLength="200"/>
											             			</td>
											             		</tr>
											             	</table>  
									             	</p>
							               </li>
							               <li  class="midli">
							               			  <p><b>推荐二级分类(最多6个)：</b></p>
							               			  <p  class="ladvDivMidp" id="show_cat">
							               			  
							               			      <@s.hidden type="hidden" id="cat_attr_str" name="floormodel.cat_attr_list"/>
											             	 <div class="morecatdiv">
												                 <table border="0" cellpadding="0" cellspacing="0">
												                 	<tr>
													                 	<td  colspan="2" class="tdbottom">
													                 		<div id="catDiv" style="margin-left:-5px;"></div>
													                 	</td>
												                 	   <td class="tdbottom" >
													                 	   <a class="oper_add" href="###" onclick="com_add_cat()" >[新增分类]</a>
													                 	   <@s.fielderror><@s.param>specname.cat_attr</@s.param></@s.fielderror>
												                 	   </td>
												                 	</tr>
												                 </table>	
												                 <#if cat_attr_list?if_exists?size gt 0>
													                 <div id="show_add_cat" class="show_add_cat">
													                     <#list cat_attr_list as cat>
													                             <span>
													                             <a class='oper' href='###' onclick='del_add_cat(this)'><img src='/include/admin/images/delete.png'></a>
														                          <input type='hidden' name='all_cat_id_str' value="${cat.cat_id?if_exists} "/><a href='#'>${cat.cat_name?if_exists} </a>
														                         </span>
													                     </#list>
													                 </div>
												                 </#if>
													         </div>
							               			  </p> 
							               </li>
							               <li class=" lastli">
									                 <p><b>热销Top3：</b><font color="#990000;">系统自动获取Top3的商品</font></p>
							               </li>
						             </ul>
				          </div>
			             <!--标签广告-->
				          <div class="radvDiv">
					           
		             		<div class="wf_main masonry" style="width: 680px; height: 290px; position: relative;float:left;">
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item00" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item00');">	 	
					             		<img src="<#if (floormodel.img_tab0_0_1)?if_exists>${floormodel.img_tab0_0_1}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_0_1)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_1_item00"  name="floormodel.img_tab0_0_1"   />
				             		    <@s.hidden  id="title_1_item00"  name="floormodel.title_tab0_0_1" />
				             		    <@s.hidden  id="href_1_item00"  name="floormodel.url_tab0_0_1" />
				             		    
				             		     <@s.hidden  id="img_2_item00"  name="floormodel.img_tab0_0_2"   />
				             		    <@s.hidden  id="title_2_item00"  name="floormodel.title_tab0_0_2" />
				             		    <@s.hidden  id="href_2_item00"  name="floormodel.url_tab0_0_2" />
				             		    
				             		     <@s.hidden  id="img_3_item00"  name="floormodel.img_tab0_0_3"   />
				             		    <@s.hidden  id="title_3_item00"  name="floormodel.title_tab0_0_3" />
				             		    <@s.hidden  id="href_3_item00"  name="floormodel.url_tab0_0_3" />
				             		    
				             		     <@s.hidden  id="img_4_item00"  name="floormodel.img_tab0_0_4"   />
				             		    <@s.hidden  id="title_4_item00"  name="floormodel.title_tab0_0_4" />
				             		    <@s.hidden  id="href_4_item00"  name="floormodel.url_tab0_0_4" />
				             		    <input type="hidden"  id="img_number_item00"  value="4"/>
				             		     <@s.hidden  id="img_item00" name="tab0_00_img"   />
				             		     <div><input type="hidden"  value="310px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item01" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item01');">	 	
					             		<img src="<#if (floormodel.img_tab0_1)?if_exists>${floormodel.img_tab0_1}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_1)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item01"  name="floormodel.img_tab0_1"   />
				             		    <@s.hidden  id="title_item01"  name="floormodel.title_tab0_1" />
				             		    <@s.hidden  id="href_item01"  name="floormodel.url_tab0_1"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item02" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item02');">	 	
				             			<img src="<#if (floormodel.img_tab0_2)?if_exists>${floormodel.img_tab0_2}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_2)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item02"  name="floormodel.img_tab0_2"   />
				             		    <@s.hidden  id="title_item02"  name="floormodel.title_tab0_2"  />
				             		    <@s.hidden  id="href_item02"  name="floormodel.url_tab0_2"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item03" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item03');">	 	
				             			<img src="<#if (floormodel.img_tab0_3)?if_exists>${floormodel.img_tab0_3}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_3)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item03"  name="floormodel.img_tab0_3"  />
				             		    <@s.hidden  id="title_item03"  name="floormodel.title_tab0_3" />
				             		    <@s.hidden  id="href_item03"  name="floormodel.url_tab0_3"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item04" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item04');">	 	
				             		<img src="<#if (floormodel.img_tab0_4)?if_exists>${floormodel.img_tab0_4}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_4)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item04"  name="floormodel.img_tab0_4"  />
				             		    <@s.hidden  id="title_item04"  name="floormodel.title_tab0_4" />
				             		    <@s.hidden  id="href_item04"  name="floormodel.url_tab0_4"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item05" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item05');">	 	
				             		<img src="<#if (floormodel.img_tab0_5)?if_exists>${floormodel.img_tab0_5}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_5)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item05"  name="floormodel.img_tab0_5"  />
				             		    <@s.hidden  id="title_item05"  name="floormodel.title_tab0_5"  />
				             		    <@s.hidden  id="href_item05"  name="floormodel.url_tab0_5"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item06" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item06');">	 	
				             		<img src="<#if (floormodel.img_tab0_6)?if_exists>${floormodel.img_tab0_6}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_6)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item06"  name="floormodel.img_tab0_6"   />
				             		    <@s.hidden  id="title_item06"  name="floormodel.title_tab0_6"  />
				             		    <@s.hidden  id="href_item06"  name="floormodel.url_tab0_6"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
				             		
				             		<div style="position: absolute; top: 0px; left: 0px;"  id="item07" class="item masonry-brick">
				             		<a href="###" onclick="showpicture('img_item07');">	 	
				             		<img src="<#if (floormodel.img_tab0_7)?if_exists>${floormodel.img_tab0_7}<#else>/include/admin/images/nopic.jpg</#if>" 
					             		 style="width:160px;height:145px;" title="${(floormodel.title_tab0_7)?if_exists}" height="160px" width="145px">
				             		</a>
				             		    <@s.hidden  id="img_item07"  name="floormodel.img_tab0_7"  />
				             		    <@s.hidden  id="title_item07"  name="floormodel.title_tab0_7"  />
				             		    <@s.hidden  id="href_item07"  name="floormodel.url_tab0_7"  />
				             		     <div><input type="hidden"  value="215px*422px"/></div>
				             		</div>
		             		
		             		</div>
					            
				          </div>
       			 </div>
             </td>
           </tr>
            <tr>
             <td class="table_name">楼层tab[1-4]<font color='red'>*</font></td>
             <td  colspan="5">
 				<!--table页开始-->
			        <div class="ntTabDiv" id="ntTabDivId">                                         
					          <div class="ntTitle">
						             <ul>
						               
					                   <#list floormarkList as flmark>
					                    <li <#if flmark.index==0>class="selli" </#if> >tab[${(flmark_index+1)?if_exists}]${(flmark.fm_name)?if_exists}</li>
						               </#list>
						             </ul>
					          </div>
					          <div class="tabDiv">
					                <#assign fmstr="">
					                <#list floormarkList as flmark>
					                  <#assign fmstr=fmstr+flmark.fm_id+",">
						                     <div class="ntTabCont">
									              <div class="showtitle"><b>${(flmark.fm_name)?if_exists}--[最多8个商品]</b></div>
													<div  class="shoucontext">
										       	    	<table class="selecttable" cellspacing="1" cellpadding="8" >
															  <tr>
													             <td class="table_name"  width="10%">推荐商品:<span id="join_goods_${(flmark.fm_id)?if_exists}"></span></td>
													             <td>
													             	<input type="button" value="添加商品" onclick="addRalateGoodsTab(${(flmark.fm_id)?if_exists},'searchgoods','0',this);"/>
													             	<div id="selralategoods_${(flmark.fm_id)?if_exists}" class="selralategoods">
													             		<table width="100%"><tr class="rg_title">
														             		<td class="span_td2" width="40%">商品名称</td>
														             		<td class="span_td2" width="30%">所属分类</td>
														             		<td class="span_td2" width="10%">销售价</td>
														             		<td class="span_td2" width="10%">库存数量</td>
														             		<td class="span_td1" width="10%">操作</td>
													             		</tr></table>
													             		<table id="selulrg_${(flmark.fm_id)?if_exists}" class="selulrg"  width="100%">
													             		        <#assign goodstrs="">
													             		        <#if goodsList?if_exists?size gt 0>
															             		<#list goodsList as glist>
																             		    <#if glist.fm_id==flmark.fm_id>
																             		    	 <#assign goodstrs=goodstrs+glist.goods_id+"_">
														             			    		<tr id="ralate_li_${(flmark.fm_id)?if_exists}_${(glist.goods_id)?if_exists}" >
																							<td class='span_td4' width='40%' title="${(glist.goods_name)?if_exists}"><#if glist.is_up=='1'><font color='red'>[已下架]</font></#if>
																							${(glist.goods_name)?if_exists}
																							</td>
																							<td class='span_td4' width='30%'>${(glist.cat_attr)?if_exists}</td>
																							<td class='span_td4' width='10%'>${(glist.min_sale_price)?if_exists}</td>
																							<td class='span_td4' width='10%'>${(glist.total_stock)?if_exists}</td>
																							<td class='span_td3' width='10%'><img  onclick="del_ralate('${(glist.goods_id)?if_exists}','${(flmark.fm_id)?if_exists}');" src='/include/common/images/delete.png' >
																							<input type='hidden' class="al_goods_id_${(flmark.fm_id)?if_exists}"  value="${(glist.goods_id)?if_exists}"/></td>
																							</tr>
																						  </#if>
																				 </#list>
																				</#if>
													             		</table>
													             	</div>
													             </td>
													           </tr>
										       	    	</table>
										       	    </div>
									            </div>
									            <#if goodstrs!=null&&goodstrs!="">
									               <#assign goodstrs=goodstrs?substring(0,goodstrs?length-1)>
									            </#if>
									            <@s.hidden  id="relate_goods_${(flmark.fm_id)?if_exists}"  name="goods_relate_str" value="${goodstrs}"/>
									            
							         </#list>
							         <@s.hidden  name="floormark_str" value="${fmstr}"/>
			        </div>
			        <!--table页结束-->
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    		<@s.token/>
	       <@s.hidden name="floorinfo.f_id"/>
	       ${listSearchHiddenField?if_exists}
	       <input type="button" value="保存" onclick="submitinfo();"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Floorinfo_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
<div  style="display:none;"  id="editDIV"  class="searchDiv"></div>
  </body>
</html>