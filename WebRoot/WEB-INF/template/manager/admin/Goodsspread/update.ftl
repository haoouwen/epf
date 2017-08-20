<html>
  <head>
    <title>修改商品推广</title>
    <!--图片-->
    <#include "/include/uploadInc.html">
    <#include "/WEB-INF/template/manager/searchRadioGoods.ftl"/>
    <script type="text/javascript">
					     	CKEDITOR.replace('content');  
	</script>
    <!-- 地区-->
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadArea("${areaIdStr?if_exists}","");
      });
	</script>
    <!--日历-->
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
		<!--所属分类开始-->
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","goods");
	  });
	</script>
	<!--所属分类结束-->
  </head>
  <body>
  <@s.form action="/admin_Goodsspread_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->当前位置:营销推广 > 推广管理 > 商品推广 > 修改商品推广
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
		             <td class="table_name">推广位置<font color='red'>*</font></td>
		             <td>
			             <@s.select name="goodsspread.spread_position" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/>
		         		<@s.fielderror><@s.param>goodsspread.spread_position</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">推广商品<font color='red'>*</font></td>
		             <td>
		             <span id="goodsname">
		             			<#if hidden_goodsname!=null>
					                ${hidden_goodsname}
					             <#else>
					       		 	 该商品已删除
					            </#if>
		             </span>
			             	<a onclick="addRalateGoods();"><input type="button" value="添加商品" /></a>
							 <@s.fielderror><@s.param>goodsspread.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	            <tr>
		             <td class="table_name">所属分类<font color='red'>*</font></td>
		             <td>
         				<div id="catDiv"></div>
         				<@s.fielderror><@s.param>goodsspread.cat_attr</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">所属地区:</td>
		             <td>
         				<div id="areaDiv" style="margin-left:-5px;"></div>
                        <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
			             <td class="table_name">推广图片:</td>
			             <td>
			              <table border="0" cellpadding="0" cellspacing="0">
				             		<tr>
				             			<td style="padding:0px;">
				             			    <div id="fileQueue3"></div>
					              			 <@s.textfield name="goodsspread.spread_img" id="uploadresult3" cssStyle="width:300px;"/>
				             			</td>
				             			<td style="padding-left:3px;">
				             				<input type="file" name="uploadifyfile" id="uploadifyfile3"/>
				             			</td>
				             			<td style="padding-left:3px;">
				             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult3');"/>
					              			<script>uploadImg("uploadifyfile3","uploadresult3","fileQueue3");</script>
				             			</td>
				             		</tr>
				             	</table>
			             	<@s.fielderror><@s.param>goodsspread.spread_img</@s.param></@s.fielderror>
			             	
			             </td>
				    </tr>
				    <tr>
			             <td class="table_name">推广口号<font color='red'>*</font></td>
			             <td>
			             	<@s.textarea name="goodsspread.slogan" cssClass="txtinput" cssStyle="width:400px;height:35px;" maxLength="100"/>
			             	<@s.fielderror><@s.param>goodsspread.slogan</@s.param></@s.fielderror>
			             </td>
			       </tr>
			       
			       <tr>
			             <td class="table_name">打折数<font color='red'>*</font></td>
			             <td>
			             	<@s.textfield name="goodsspread.discounts" cssClass="txtinput" cssStyle="width:30px;" onkeyup="checkRMB(this);" maxlength="6"/>
			             	<@s.fielderror><@s.param>goodsspread.discounts</@s.param></@s.fielderror>
			             	<img class="ltip" src="/include/common/images/light.gif" alt="请输入10以内的正数，10代表不打折">
			             </td>
			      </tr>
	           
	                <tr>
		             <td class="table_name">推广开始时间<font color='red'>*</font></td>
	              <td>
	             			<input type="text"  name="goodsspread.spread_starttime" value="${goodsspread.spread_starttime[0..18]?if_exists} "class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			             <@s.fielderror><@s.param>goodsspread.spread_starttime</@s.param></@s.fielderror>
		             </td>
		             
		           </tr>
	           
		           <tr>
	           		<td class="table_name">推广结束时间<font color='red'>*</font></td>
		             <td>
		              <input type="text"  name="goodsspread.spread_endtime"value="${goodsspread.spread_endtime[0..18]?if_exists}"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
		             	<@s.fielderror><@s.param>goodsspread.spread_endtime</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
 		   <@s.hidden id="goods_id" name="goodsspread.goods_id"/>   
		  <@s.hidden id=" trade_id" name="goodsspread.trade_id"/>   	
	      <!--所属分类插件隐藏字段开始区域-->
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value"/>
		   <!--所属分类插件隐藏字段结束区域-->
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Goodsspread_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
</body>
</html>

