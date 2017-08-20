<html>
  <head>
    <title>添加商机订阅</title>
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){ 	
	      if($("#modle_type").val()=='0'){
	          cate_select("${cfg_topcatid?if_exists}",1,'supply');
	      }else{  
              cate_select("${cfg_topcatid?if_exists}",1,'buy');
          }
	  	  $("#modle_type").change(function(){//事件触发  
			   $('option:selected', this).each(function(){
			   		$("#divlist").html("");
			   		if(this.value=='0'){
			   			cate_select("${cfg_topcatid?if_exists}",1,'supply');
			   		}else{
			   			cate_select("${cfg_topcatid?if_exists}",1,'buy');
			   		}                    
			   });  	
		  });    
          //所属地区的回选
          loadArea("${areaIdStr?if_exists}","");
	  });
	  
	//验证数字 > 0
	function checkZeroNum(obj)
	{
	    var num_value=$(obj).val();
	    var re =/^(([1-9]\d*))$/;
		if (!re.test(obj.value))
		  {
		     if(isNaN(obj.value)){
		        obj.value="1";
		        obj.focus();
		        jNotify("频率天数不能小于1"); 
		        return false;
		     }
		  }
	}
	</script>
  </head>
  <body>
  <@s.form action="/admin_Subscribe_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
 	   当前位置:会员管理 > 企业相关 > 商机订阅 > 添加商机订阅
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <tr>
             <td class="table_name">信息类型<font color='red'>*</font></td>
             <td>
             	<@s.select id="modle_type" name="subscribe.info_type" list=r"#{'0':'供应','1':'求购'}" />  
             	<@s.fielderror><@s.param>subscribe.info_type</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">关键字<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="subscribe.keyword" cssClass="txtinput" maxLength="60"/>
             	<@s.fielderror><@s.param>subscribe.keyword</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">所属分类<font color='red'>*</font></td>
             <td>
             	 <div id="divlist"></div>  
                <@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror> 
             </td>
           </tr>        
            <tr>
             <td class="table_name" >所在地区:<font color="red">*</font></td>
             <td>
                 <div id="areaDiv" style="margin-left:-5px;"></div>
                 <span id="span_area" class="error_msg"/>     
                 <@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>     	           	            
             </td>
           </tr>
          <tr>
           
           <tr>
             <td width="19%" class="table_name">频率天数<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="subscribe.period" cssClass="txtinput" maxLength="4"  onkeyup="checkZeroNum(this)"/>
             	<@s.fielderror><@s.param>subscribe.period</@s.param></@s.fielderror>
             </td>
           </tr>
           
            <tr>
             <td class="table_name">发送类型<font color='red'>*</font></td>
             <td>
             	<@s.select name="subscribe.send_type" list=r"#{'0':'邮箱','1':'站内信'}" headerKey="" headerValue="请选择"/>  
             	<@s.fielderror><@s.param>subscribe.send_type</@s.param></@s.fielderror>	
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    	       <@s.token/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Subscribe_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>