<html>
  <head>
    <title>添加会员级别信息</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/admin/js/malllevelset.js"></script>
     <link rel="stylesheet" href="/include/common/css/zTreeStyle.css" type="text/css">
	  <script type="text/javascript" src="/include/common/js/jquery.ztree.core-3.5.js"></script>
	  <script type="text/javascript" src="/include/common/js/jquery.ztree.excheck-3.5.js"></script>
	  <script type="text/javascript">
		$(document).ready(function(){
			var treeObj = $.fn.zTree.init($("#treeRole"), setting, ${jsonMenu?if_exists});
			$("#saveMenu").click(function(){
				var node_id_str="";
				var nodes = treeObj.getCheckedNodes(true);
				for(var i=0; i<nodes.length;i++){
		           node_id_str +=  nodes[i].menu_id+",";
				}
				$("#menu_right").val(node_id_str);
				if(checkInter_height()){
						$("#roleadd").submit();   
				} 
			
			})
		});
	 </script>	   
  </head>
  <body>
  <@s.form action="/admin_Malllevelset_buyinsert.action" method="post" validate="true" id="roleadd">
<div class="postion">
  <!--当前位置-->当前位置:会员管理 > 级别管理 > 会员级别 > 添加会员级别信息
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr>
		             <td class="table_name" >级别编码<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.level_code" cssClass="txtinput" maxLength="20" cssStyle="width:220px;" />
		             	<@s.fielderror><@s.param>malllevelset.level_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">级别名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.level_name" cssClass="txtinput" maxLength="20" cssStyle="width:220px;"/>
		             	<@s.fielderror><@s.param>malllevelset.level_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">成长值下限<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.inter_lower" id="inter_lower" cssClass="txtinput" maxLength="9" cssStyle="width:220px;" />
		             	<@s.fielderror><@s.param>malllevelset.inter_lower</@s.param></@s.fielderror>
		             	说明：0 代表 无
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">成长值上限<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.inter_height"id="inter_height" cssClass="txtinput" maxLength="9" cssStyle="width:220px;" />
		             	<@s.fielderror><@s.param>malllevelset.inter_height</@s.param></@s.fielderror>
		             	说明：0 代表 无，999999999 代表 无上限
		             </td>
		           </tr>
	           
		          
             			<@s.hidden name="malllevelset.mem_type" cssClass="txtinput" maxLength="20" value="1" cssStyle="width:220px;"/> 
		             	<@s.fielderror><@s.param>malllevelset.mem_type</@s.param></@s.fielderror>
		              
		           </tr>
	           
	               <tr>
		             <td class="table_name">折扣率<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.discount" cssClass="txtinput" maxLength="6" />
		             	<@s.fielderror><@s.param>malllevelset.discount</@s.param></@s.fielderror>
		             	说明：${malllevelset.discount?if_exists} 代表 ${malllevelset.discount?if_exists}
		             </td>
		           </tr>
	               
		           <tr>
				       <tr>
								 <td class="table_name" cssStyle="width:220px;height:60px;" >级别图标:</td>
				             <td>
				             		<table border="0" cellpadding="0" cellspacing="0">
					             		<tr>
					             			<td style="padding:0px;">
					             			    <div id="fileQueue"></div>
						              			 <@s.textfield name="malllevelset.img_url" id="uploadresult" cssStyle="width:220px;"/>
					             			</td>
					             			<td style="padding-left:3px;">
					             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
					             			</td>
					             			<td style="padding-left:3px;">
					             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
					             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽110px高25px">
						              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
					             			</td>
					             		</tr>
					             	</table>
		             	<@s.fielderror><@s.param>malllevelset.img_url</@s.param></@s.fielderror>
		             </td>
		           </tr>
	        
	         </tr>
		           <tr>
            	 <td width="19%" class="table_name">级别菜单权限<font color='red'>*</font></td>
             		<td>
               			 <div class="zTreeDemoBackground left">
							<ul id="treeRole" class="ztree"></ul>
						</div>
						<@s.fielderror><@s.param>malllevelset.menu_right</@s.param></@s.fielderror>
            		 </td>
           </tr>
		           
	           
		           <tr>
		             <td class="table_name">级别操作权限:</td>
		             <td>
		             	<@s.textfield name="malllevelset.oper_right" cssClass="txtinput" maxLength="20" cssStyle="width:220px;"/>
		             	<@s.fielderror><@s.param>malllevelset.oper_right</@s.param></@s.fielderror>
		             </td>
		           </tr>
	              
		          <tr>
		             <td class="table_name">有效期:</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.validity_period" cssClass="txtinput"  />
		             	<@s.fielderror><@s.param>malllevelset.validity_period</@s.param></@s.fielderror>
		             	说明：以月为单位，例：3 表示 3个月 
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">扣除成长值:</font></td>
		             <td>
		             	<@s.textfield name="malllevelset.dedu_growth_value" cssClass="txtinput"  />
		             	<@s.fielderror><@s.param>malllevelset.dedu_growth_value</@s.param></@s.fielderror>
		             </td>
		            </tr>
		           
		            <tr>
		             <td class="table_name">备注:</font></td>
		             <td>
		             	<@s.textarea name="malllevelset.note" cssClass="txtinput" maxLength="200" cssStyle="width:320px;"  onkeyup="checkLength(this,200)"/>
		             	<@s.fielderror><@s.param>malllevelset.note</@s.param></@s.fielderror>
		             </td>
		            </tr>
		           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    <@s.hidden name="token_value" value="${get_token_value?if_exists}"/> 
	       <@s.hidden id="menu_right" name="malllevelset.menu_right"/>
	       <@s.hidden id="mr_list" />
	       <@s.hidden id="op_list"/>       
	       ${listSearchHiddenField?if_exists}
          <@s.token/>
           <input id="saveMenu" type="button" name="saveMenu" value="保存"/>
	       <@s.reset value="重置"/>
	      <input type="button" name="returnList" value="返回列表" onclick="document.forms[0].action='/admin_Malllevelset_buylist.action';document.forms[0].submit();"/>         
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
</body>
</html>

