//验证只能输入 >=0(非负整数)
function checkDigital(obj)
{
    var num_value=$(obj).val();
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(obj.value)){
	     if(isNaN(obj.value)){
	        obj.value="";
	        obj.focus();
	        jNotify("只能输入大于等于0的数字,请正确输入!"); 
	        return false;
	     }
	     obj.value="";
     	 obj.focus();
         jNotify("只能输入大于等于0的数字,请正确输入!"); 
         return false;
	}
}

//验证只能输入 >=0(非负整数)，显示为空值，而不是默认为1
function checkDigitalNoNone(obj)
{
    var num_value=$(obj).val();
    var re =/^(0|([1-9]\d*))$/;
	if (!re.test(obj.value)){
	     if(isNaN(obj.value)){
	        obj.value="";
	        obj.focus();
	        jNotify("该文本框只能输入正整数,请正确输入!"); 
	        return false;
	     }
	     obj.value="";
     	 obj.focus();
         jNotify("请输入有效的整数!"); 
         return false;
	}
}

//验证只能输入正整数 >01
function checkDigitals(obj)
{
    var num_value=$(obj).val();
    var re =/^[1-9]\d*$/;
	if (!re.test(obj.value)){
	     if(isNaN(obj.value)){
	        obj.value="1";
	        obj.focus();
	        jNotify("该文本框只能输入正整数,请正确输入!"); 
	        return false;
	     }
	     obj.value="1";
     	 obj.focus();
         jNotify("该文本框只能输入正整数,请正确输入!"); 
         return false;
	}
}
//验证只能输入整数 -1，0，1
function checkNum(obj)
{
    var re = /^-?[1-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	if (!re.test(obj.value))
	  {
	    if(isNaN(obj.value)){
		   obj.value="";
	       obj.focus();
	       jNotify("该文本框只能输入整数,请正确输入!"); 
	       return false;
	    }
	 }
 } 

//验证RMB类型
function checkRMB(obj){
    var obj_value=$(obj).val();
	//验证是否为数字正则表达式
	var reg = /^\d{1,8}(\.\d{0,2})?$/;
	if(!reg.test(obj_value)){		
		jNotify("无效输入!");		
		$(obj).val("");
		return false;
	}else{
		return true;
	}
}

//验证RMB不能为0开头
function checkZero(obj){
   var obj_value=$(obj).val();
   var FirstChar=obj_value.substr(0,1);
   if(FirstChar=="0"){
      jNotify('不能为0开头！');
      $(obj).val("");
      return false;
   }
   return true;
}
//验证邮编
function checkPost(obj){
   var obj_value=$(obj).val();
	//验证是否为数字正则表达式
	var reg =/^[0-9]{6}$/;
	if(obj_value ==null||obj_value==""){
		jNotify("邮政编码不能为空!"); 
		return false;
	}
	if(!reg.test(obj_value)){		
		jNotify("邮编格式有误");		
		$(obj).val("");
		return false;
	}else{
		return true;
	}
}
//验证手机号码
function checkMobile(obj)
{   
    var tel=obj.value;
    if(tel==null || tel==""){
    	return true;
    }
    var re =/^1[3|4|5|8][0-9]\d{4,8}$/;
	if (!re.test(obj.value)||tel.length<11){
	     obj.value="";
     	 obj.focus();
         jNotify("请输入正确手机号码!"); 
         return false;
	}else{
	 return true;
	}
}
//验证浮点类型
function checkFloat(obj){
    var obj_value=$(obj).val();
	var reg = /^(\d){1,8}(\.)?(\d(\d(\d(\d)?)?)?)?$/;
	if(!reg.test(obj_value)){		
		jNotify("请正确输入,最多保留四位小数!");		
		$(obj).val("");
		return false;
	}else{
		return true;
	}
}

//控制textarea框的长度
function checkLength(obj,num){
    var obj_value=$(obj).val();
    if(obj_value.length>num){    
       var get_value=obj_value.substring(0,num);
       $(obj).val(get_value);
       jNotify("该输入框字符数不能超过"+num+"字!"); 
    }
}

//去掉字符串中，最后一个字符
function deleteLastChar(str,ch){
 	if(str.indexOf(ch)>-1){
 		var lastPos = str.lastIndexOf(ch);
 		str=str.substring(0,lastPos);
 	}
 	return str;
 }
 

//列表批量删除操作
function delInfo(field_name,actionName){
	// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要删除?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}



//列表批量删除关联分类操作
function delRelate(field_name,actionName){
	// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要删除,会把它关联的子分类删除?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}




//删除单个操作
function delOneInfo(field_name,actionName,infoid){
	art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+'确定要删除?'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        document.getElementById('commonForm').action=actionName;
			document.getElementById('commonText').name = field_name;
			document.getElementById('commonText').value = infoid;
			document.getElementById('commonForm').submit();	
	    },
	    cancel: function () {
			return true;
	    }
   });
}


//删除单个操作
function delOneInfoMemcat(field_name,actionName,infoid){
    //判断该分类是否有绑定商品
	   $.ajax({
	         type: "post",
	         url: "/membercat!ajaxCatGoods.action?cat_id="+infoid,
	         datatype:"json",
	         async:false,
	         success: function(data){ 
	         	if(data=="1"){
		         	art.dialog({
						title: '系统提示',
						content:'<div class="decorate">'+'确定要删除?'+'</div>',
						okValue: '确定',
						width: '238px',
						cancelValue: '取消',
					    ok: function () {
					        document.getElementById('commonForm').action=actionName;
							document.getElementById('commonText').name = field_name;
							document.getElementById('commonText').value = infoid;
							document.getElementById('commonForm').submit();	
					    },
					    cancel: function () {
							return true;
					    }
				   });
	         	}else if(data=="2"){
	         	    alert("该自定义分类下有商品信息，不能删除");
	         	}
	         }	 
	});   
    

	
}




//密码重置操作
function resterPwd(actionName){
	art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+'确定要重置?'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        $("#modify_id").attr("actionName",actionName);
			$("#modify_id").submit();	
	    },
	    cancel: function () {
			return true;
	    }
   });
}

//加入回收站
function recycle(field_name,actionName){
	// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确认将选中的订单转移到订单回收站?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}


//进入修改页面
function linkToInfo(actionName,paraStr){
	$("#hidden_div").html("");//清空
	var ppstr = paraStr.split("&");
	if(ppstr.length > 0){
		for(var i=0;i<ppstr.length;i++){
			var para = ppstr[i];
			if(para.indexOf("=") > -1){
				var paraSon = para.split("=");
				if(paraSon.length > 1){
					var key = paraSon[0];
					var value = paraSon[1];
					var hidden_name ="<input type='hidden' name='"+key+"' value='"+value+"' />";
					$("#hidden_div").append(hidden_name);
				}
			}
		}
	}
	document.getElementById("commonForm").action=actionName;
	document.getElementById("commonForm").submit();
}



//列表批量选择操作
function selectAll(field_name){
	var checkall = document.getElementById('checkall');
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checkall.checked){
			checks[i].checked = true;
		}else{
			checks[i].checked = false;
		}
	}
}

//列表批量选择操作
function selectAllBoxs(id,field_name){
	var checkall = document.getElementById(id);
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].disabled==false){
			if(checkall.checked){
				checks[i].checked = true;
			}else{
				checks[i].checked = false;
			}
		}
	}
}
//赋隐藏域的值用于回选，不丢掉搜索值
function saveHiddenVal(){
	//清空
	$("#hidden_div").html("");
	$("input[name$='_s'],select[name$='_s']").each(function(){
		//alert($(this).attr("name"));
		var fn =$(this).attr("name");
		var fn_val =$(this).val();
		var hidden_name ="<input type='hidden' name='"+fn+"' value='"+fn_val+"' />";
		$("#hidden_div").append(hidden_name);
	});  
}


//批量操作状态开始
function updateBatchState(state,field_name,actionName,tip){
        var flag = false;
        var tf_id="";
		$("input:checkbox[name='"+field_name+"']").each(function(i){
			if(this.checked==true){
				flag=true;
				tf_id+=$(this).val()+",";
				
			}	      
      	});
		if(flag==false){
			art.dialog({
			  title: '系统提示',
		      content: '请至少选择一条记录!'
		  });
		  return false;
		}
		//id串
      	tf_id=deleteLastChar(tf_id,",");
		if(flag==true){
	   		art.dialog({
				title: '系统信息提示',
				content:'<div class="decorate">确定'+tip+'?</div>',
				okValue: '确定',
				width: '238px',
				cancelValue: '取消',
			    ok: function () {
				    document.getElementById('commonId').name = "chb_id";
			    	document.getElementById('commonId').value = tf_id;
			    	document.getElementById('commonText').name = "state_val";
			    	document.getElementById('commonText').value = state;
			    	document.getElementById('commonForm').action=actionName;
			    	document.getElementById('commonForm').submit();	  
			        return false;
			    },
		   		cancel: function () {
					return true;
			    }
		    });
		}
}
//批量操作状态结束

//公共批量排序方法
function updateSort(cid,s_val,actionName){  
	  var sort_count=0;
      var tf_id="";
      var tf_sort="";
      $("input:checkbox[name='"+cid+"']").each(function(i){
			if(this.checked==true){
				tf_id+=$(this).val()+",";
				tf_sort+=$("input:text[name='"+s_val+"']").eq(i).val()+",";
				sort_count++;
			}	      
      });
      //id串
      tf_id=deleteLastChar(tf_id,",");
      //值串
      tf_sort=deleteLastChar(tf_sort,",");
      if(sort_count==0){
		  art.dialog({
			  title: '系统提示',
		      content: '请至少选择一条记录!'
		  });
		  return false;
	  }else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要排序'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonId').name = "chb_id";
		    	document.getElementById('commonId').value = tf_id;
		    	document.getElementById('commonText').name = "sort_val";
		    	document.getElementById('commonText').value = tf_sort;
		    	document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
} 

//获取URL路径传过来的值
function queryUrlvalue(key){
  var regex_str="^.+\\?.*?\\b"+ key +"=(.*?)(?:(?=&)|$|#)"
  var regex=new RegExp(regex_str,"i");
  var url=window.location.toString();
  if(regex.test(url)) return RegExp.$1;
  return undefined;
}

//是否存在敏感字
function filterWord(id){
  var content=encodeURIComponent(encodeURIComponent($("#"+id).val()));
  $.ajax({
       type: "post",
       url: "/mall/goods!filterWord.action?ccontent="+content,
       dataype:"json",
       async:false,
       success: function(data){
         if(data!=''){
         jNotify(data+"是敏感词！");
         }
       }
  });

}


//对查找出的select 进行回选
function selectCheckObj(objid,thisvalue){
	var levelone = document.getElementById(objid);
	if(levelone!=null){
		for (var j = 0; j < levelone.options.length; j++) {
	        if (levelone.options[j].value == thisvalue) {
	            levelone.options[j].selected = true;
	        }
        }
	}
}

//列表对地区和分类的搜索获取值功能
function showSelectDiv(area_attr_id,cat_atrr_id,bandareaid,bandcatid){
	   //获取地区
	   if(area_attrid!=""){
		 	var area_attr = document.getElementsByName(area_attrid);
		 	var area_attr_str = '';
		 	for(var i=0;i<area_attr.length;i++){
				if(area_attr[i].value!='0'){
					area_attr_str += area_attr[i].value+',';
				}
			 }
		    if(area_attr_str!=""){
			   area_attr_str=area_attr_str.substring(0,area_attr_str.length-1);
			}
			$("#"+bandareaid+"").val(area_attr_str);
		}
		//分类获取
		if(cat_atrrid!=""){
			var cat_attr= document.getElementsByName(cat_atrrid);
			var cat_attr_str = '';
			for(var i=0;i<cat_attr.length;i++){
				if(cat_attr[i].value!='0'){
					cat_attr_str += cat_attr[i].value+',';
				}
			}
			if(cat_attr_str!=""){
			 cat_attr_str=cat_attr_str.substring(0,cat_attr_str.length-1);
			}
			$("#"+bandcatid+"").val(cat_attr_str);
		}
		document.forms[0].submit();
	}


//公共通过脚本处理分类提交表单
function searchSubmit(){
	/****搜索分类加载开始****/
	if($("#cat_attr_s").length > 0){
		var cstr="";
		$("select[name='cat_attr']").each(function(){
			cstr+=$(this).val()+",";
		});
		//去除请选择值
		cstr=cstr.replace("0,","");
		//去掉最后一个逗号
		cstr=deleteLastChar(cstr,",");
		//赋值传给后台搜索
		$("#cat_attr_s").val(cstr);
	}
	/****搜索分类加载结束****/
	/****搜索地区加载开始****/
	if($("#area_attr_s").length > 0){
		var astr="";
		$("select[name='area_attr']").each(function(){
			astr+=$(this).val()+",";
		});
		//去除请选择值
		astr=astr.replace("0,","");
		//去掉最后一个逗号
		astr=deleteLastChar(astr,",");
		//赋值传给后台搜索
		$("#area_attr_s").val(astr);
	}
	/****搜索地区加载结束****/
	document.forms[0].submit();
}


//div层定位
function getPosition(el){ 
	for (var lx=0,ly=0;el!=null;lx+=el.offsetLeft,ly+=el.offsetTop,el=el.offsetParent); 
	return {x:lx,y:ly} 
}


//列表筛选弹出层操作-开始
function divfixed2(r,name){ 
   var sug=document.getElementById(name);
   sug.style.left=getPosition(r).x+"px"; 
   sug.style.top=getPosition(r).y+r.offsetHeight+"px"; 
   sug.style.position="absolute"; 
   sug.style.display="block"; 
}

//显示搜索框
function showSearch(obj,val){
	var objdiv = document.getElementById(val);
	if(objdiv.style.display=="none"){
		divfixed2(obj,val);
	}else{
		objdiv.style.display="none"; 
	}
}

//关闭搜索框
function closeSearch(val){
	var obj_id = "searchDiv";
	if(val!=null && val!=""){
		obj_id=val;
	}
	document.getElementById(obj_id).style.display="none"; 
}

//分页控件跳转至第几页
function jumpTo(){
	//欲跳转到的页码
	var pages_s = $("input:text[name='pages_s']").val();  
	//总页码
	var totalPage = $("input:hidden[name='totalPage']").val();  
	if( eval(pages_s) > eval(totalPage)){
		pages_s = totalPage;
	}
	document.forms[0].pages_s.value = pages_s;
	document.forms[0].submit();
}

//若审核不通过，显示填写理由；若禁用，显示填写理由
function checkinput(obj){
	if (obj.value=='2'){
	   $("#reason").show();
	}else if(obj.value=='1'){
	  $("#reason").hide();
	}else if(obj.value=='3'){
	   $("#reason").show();
	}else{
	   return false;
	}
} 
  function showTr(obj){
	if (obj.value=='2'){
	   $("#trInput").show();
	}else if(obj.value=='1'){
	  $("#trInput").hide();
	}else if(obj.value=='3'){
	   $("#trInput").show();
	}else{
	   return false;
	}
 }
//弹出高级搜索框
function searchShowDIV(id,s_width,s_height){
  $("#"+id).popup_search({move_top:0, p_height:s_height, pop_title:"高级查找"});
}
//预售商品添加库存
function addShowDIV(id,s_width,s_height,s_flag){
    $("#s_flag").val(s_flag);
	if(s_flag == "1"){
	   $("#stock_maxbuy_name").html("库存");
	}else{
	   $("#stock_maxbuy_name").html("预售");
	}
  $("#"+id).popup_search({move_top:0, p_height:s_height,p_flag:s_flag, pop_title:"新增数量"});
  
}
//修改运单号
function showBackDIV(id,s_width,s_height){
     $("#"+id).popup_search({move_top:0, p_height:s_height, pop_title:"退货金额"});
}

//修改运单号
function updateBillnosDIV(id,s_width,s_height){
     $("#"+id).popup_search({move_top:0, p_height:s_height, pop_title:"修改运单号"});
}

//弹出高级搜索框
function exportShowDIV(id,s_width,s_height,s_title){
  $("#"+id).popup_search({move_top:0, p_height:s_height, pop_title:s_title});
}
//弹出高级查找
function goodsShowDiv(actionName,val,title){
    $("#indexForm").attr("action",actionName);
    $("#searhGoods").val(val);
    exportShowDIV('searchDiv','300px','220px',title);
}
//导入商品弹出框
function importGoodsDiv(type,title){
  if(type == 0){
     $("#d_goods").show();
     $("#download").show();
     $("#k_goods").hide();
     $("#m_down_id").show();
  }else {
     $("#k_goods").show();
     $("#d_goods").hide();
     $("#download").hide();
      $("#m_down_id").hide();
  }
  exportShowDIV('importDiv','300px','220px',title);
}
//添加IP地址
function addipShowDIV(id,s_width,s_height){
  $("#"+id).popup_search({move_top:0, p_height:s_height, pop_title:"添加禁用IP"});
}
//弹出地区选择框
function searchShowareaDIV(id,s_width,s_height,sys_id){
  $("#"+id).popup({move_top:0, p_height:s_height, pop_title:"指定区域"});
  $("#sys_id").val(sys_id);
}
//关闭高级搜索框
function closeSearchShowDIV(id){
if($("#admin_hidden_action").val()!=null&&$("#admin_hidden_action").val()!=""){
   $("#indexForm").attr("action",$("#admin_hidden_action").val());
}
$("#"+id).ccover();
}
//分页控件处理脚本
function pages_util(num_pages){
	$("#pages_s").val(num_pages);
	$("#indexForm").submit();
}

//触屏版分页控件处理脚本
function pages_util_app(num_pages){
	$("#pages_s").val(num_pages);
	$("#indexForm").submit();
}

//列表对地区和分类的搜索获取值功能
function showSearchDiv(area_attr_id,cat_attr_id,bandareaid,bandcatid,formname){
 //获取地区
    if(area_attr_id!=""){
	 	var area_attr = document.getElementsByName(area_attr_id);
	 	var area_attr_str = '';
	 	for(var i=0;i<area_attr.length;i++){
			if(area_attr[i].value!='0'){
				area_attr_str += area_attr[i].value+',';
			}
		 }
		 if(area_attr_str!=""){
		   area_attr_str=area_attr_str.substring(0,area_attr_str.length-1);
		 }
		 $("#"+bandareaid+"").val(area_attr_str);
	}
	//分类获取
	if(cat_attr_id!="")
	{
		var cat_attr= document.getElementsByName(cat_attr_id);
		var cat_attr_str = '';
		for(var i=0;i<cat_attr.length;i++){
			if(cat_attr[i].value!='0'){
				cat_attr_str += cat_attr[i].value+',';
			}
		}
		if(cat_attr_str!="")
		{
		 cat_attr_str=cat_attr_str.substring(0,cat_attr_str.length-1);
		}
		$("#"+bandcatid+"").val(cat_attr_str);
	}
	
		$("#"+formname).submit();
}
//判断以不能为0开头，最低5位数 最高不能为12位数  验证qq hong注释
function checkQQ(obj){
    var num_value=$(obj).val();
    var re=/^[1-9]{1}[0-9]{4,12}$/;//开头不能以0开始的整数最少输入5位最多输入12位数字
    var reg=/[0-9]/;
    var str="";
	if (!re.test(num_value)){
		var len=num_value.length;
		 for( i = 0; i < len; i ++ ){
	    	var char=num_value.charAt(i);
		 	if(i==0){
		 		if(char!="0"&&reg.test(char)){
		 			str+=char;
		 		}
		 	}else{
		 		if(reg.test(char)){
			 		str+=char;
		 		}
		 	}
		 	//判断qq输入长度为12位
		 	if(str.length=="12"){
		 		break;
		 	}
		  }
		 $(obj).val(str);
		     jNotify("请输入你正确的QQ号!");
         return false;
	}
}
 //导出系统数据
function exprotExcel(){
	if(window.confirm("确定要导出数据吗?")) {
		 $("#indexForm").attr("action","/admin_InfoCount_exportInfo.action");
		 $("#indexForm").submit();
		 $("#searchDiv").ccover();
	}
}
 //导出系统数据
function exprotCatExcel(){
	if(window.confirm("确定要导出分类数据吗?")) {
		 $("#catform").attr("action","/admin_Category_exportCatInfo.action");
		 $("#catform").submit();
		 $("#searchDiv").ccover();
	}
}
 //导出系统数据
function exprotAreaExcel(){
	if(window.confirm("确定要导出地区数据吗?")) {
		 $("#areaform").attr("action","/admin_Area_exportAreaInfo.action");
		 $("#areaform").submit();
		 $("#searchDiv").ccover();
	}
}
 //导出品牌系统数据
function exprotgoodsbrandExcel(){
	if(window.confirm("确定要导出品牌数据吗?")) {
		 $("#indexForm").attr("action","/admin_Goodsbrand_exportbrandInfo.action");
		 $("#indexForm").submit();
		 $("#indexForm").attr("action","/admin_Goodsbrand_list.action");
		 $("#searchDiv").ccover();
		 
	}
}
 //导出税率数据
function exprotTaxExcel(){
	if(window.confirm("确定要导税率数据吗?")) {
		 $("#taxrateform").attr("action","/admin_Taxrate_exportTaxInfo.action");
		 $("#taxrateform").submit();
		 $("#searchDiv").ccover();
	}
}
 //导出红包系统数据
function exprotredbagExcel(field_name,actionName){

// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 判断
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要重新下载数据吗'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		    	$("#redkeyId").val(filedVal);
		    	document.getElementById('indexForm').action=actionName;
		    	document.getElementById('indexForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}

 //导出优惠卷系统数据
function exprotcouponExcel(field_name,actionName){
 // 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 判断
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要重新下载数据吗'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		    	document.getElementById('commonId').value = filedVal;
		    	$("#couponkey").val(filedVal);
		    	document.getElementById('indexForm').action=actionName;
		    	document.getElementById('indexForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}


//导出充值卡系统数据
function exprotcardExcel(field_name,actionName){
 // 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 判断
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要导出充值卡数据吗'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		    	document.getElementById('commonId').value = filedVal;
		    	$("#exccardid").val(filedVal);
		    	document.getElementById('indexForm').action=actionName;
		    	document.getElementById('indexForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}


//导出系统数据
function exprotExcel(field_name,actionName){
 // 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 判断
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要导出数据吗'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		    	$("#excid").val(filedVal);
		    	document.getElementById('indexForm').action=actionName;
		    	document.getElementById('indexForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}


function giveupOrder(){
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+'确定要放弃订单?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('subOrder').action="/cart.html";
		    	document.getElementById('subOrder').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
}

//列表批量删除操作
function delB2cInfo(actionName,paraStr,field_name){
	// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '友情提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '友情提示',
			content:'<div class="decorate">'+'确定要删除?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		       	$("#hidden_div").html("");//清空
				var ppstr = paraStr.split("&");
				if(ppstr.length > 0){
					for(var i=0;i<ppstr.length;i++){
						var para = ppstr[i];
						if(para.indexOf("=") > -1){
							var paraSon = para.split("=");
							if(paraSon.length > 1){
								var key = paraSon[0];
								var value = paraSon[1];
								var hidden_name ="<input type='hidden' name='"+key+"' value='"+value+"' />";
								$("#hidden_div").append(hidden_name);
							}
						}
					}
				}
				document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
				document.getElementById("commonForm").action=actionName;
				document.getElementById("commonForm").submit();
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}

//删除单个操作
function delB2cOneInfo(actionName,paraStr){
	art.dialog({
		title: '友情提示',
		content:'<div class="decorate">'+'确定要删除?'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        $("#hidden_div").html("");//清空
				var ppstr = paraStr.split("&");
				if(ppstr.length > 0){
					for(var i=0;i<ppstr.length;i++){
						var para = ppstr[i];
						if(para.indexOf("=") > -1){
							var paraSon = para.split("=");
							if(paraSon.length > 1){
								var key = paraSon[0];
								var value = paraSon[1];
								var hidden_name ="<input type='hidden' name='"+key+"' value='"+value+"' />";
								$("#hidden_div").append(hidden_name);
							}
						}
					}
				}
				document.getElementById("commonForm").action=actionName;
				document.getElementById("commonForm").submit();
	    },
	    cancel: function () {
			return true;
	    }
   });
}	
//单个操作，可以自定义提示
function commonB2cOneInfo(actionName,paraStr,tipStr){
	art.dialog({
		title: '友情提示',
		content:'<div class="decorate">'+tipStr+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        $("#hidden_div").html("");//清空
				var ppstr = paraStr.split("&");
				if(ppstr.length > 0){
					for(var i=0;i<ppstr.length;i++){
						var para = ppstr[i];
						if(para.indexOf("=") > -1){
							var paraSon = para.split("=");
							if(paraSon.length > 1){
								var key = paraSon[0];
								var value = paraSon[1];
								var hidden_name ="<input type='hidden' name='"+key+"' value='"+value+"' />";
								$("#hidden_div").append(hidden_name);
							}
						}
					}
				}
				document.getElementById("commonForm").action=actionName;
				document.getElementById("commonForm").submit();
	    },
	    cancel: function () {
			return true;
	    }
   });
}
//列表对地区和分类的搜索获取值功能
function  showSearchGoodDiv(area_attr_id,cat_attr_id,bandareaid,bandcatid,formname,actionId){
 //获取地区
    if(area_attr_id!=""){
	 	var area_attr = document.getElementsByName(area_attr_id);
	 	var area_attr_str = '';
	 	for(var i=0;i<area_attr.length;i++){
			if(area_attr[i].value!='0'){
				area_attr_str += area_attr[i].value+',';
			}
		 }
		 if(area_attr_str!=""){
		   area_attr_str=area_attr_str.substring(0,area_attr_str.length-1);
		 }
		 $("#"+bandareaid+"").val(area_attr_str);
	}
	//分类获取
	if(cat_attr_id!="")
	{
		var cat_attr= document.getElementsByName(cat_attr_id);
		var cat_attr_str = '';
		for(var i=0;i<cat_attr.length;i++){
			if(cat_attr[i].value!='0'){
				cat_attr_str += cat_attr[i].value+',';
			}
		}
		if(cat_attr_str!="")
		{
		 cat_attr_str=cat_attr_str.substring(0,cat_attr_str.length-1);
		}
		$("#"+bandcatid+"").val(cat_attr_str);
	}
	$("#"+formname).attr("action",$("#"+actionId).attr("action"));
	$("#"+formname).submit();
	if($("#admin_hidden_action").val()!=null&&$("#admin_hidden_action").val()!=""){
   		$("#indexForm").attr("action",$("#admin_hidden_action").val());
    }
}

//获取查询组合条件
function geturlsearchinfo(){
    var searchtj="";
	var s_goods_name =$("#sg_name").val();
	s_goods_name=encodeURI(encodeURI(s_goods_name));
	searchtj+="?s_goods_name="+s_goods_name;
	var g_goods_en_name_s =$("#g_goods_en_name_s").val();
	if(g_goods_en_name_s!=null&&g_goods_en_name_s!=""){
		g_goods_en_name_s=encodeURI(encodeURI(g_goods_en_name_s));
		searchtj+="&g_goods_en_name_s="+g_goods_en_name_s;
	}
	var g_goods_marketstart_s =$("#g_goods_marketstart_s").val();
	if(g_goods_marketstart_s!=null&&g_goods_marketstart_s!=""){
		g_goods_marketstart_s=encodeURI(encodeURI(g_goods_marketstart_s));
		searchtj+="&g_goods_marketstart_s="+g_goods_marketstart_s;
	}
	var g_goods_marketend_s =$("#g_goods_marketend_s").val();
	if(g_goods_marketend_s!=null&&g_goods_marketend_s!=""){
		g_goods_marketend_s=encodeURI(encodeURI(g_goods_marketend_s));
		searchtj+="&g_goods_marketend_s="+g_goods_marketend_s;
	}
	var g_goods_salestart_s =$("#g_goods_salestart_s").val();
	if(g_goods_salestart_s!=null&&g_goods_salestart_s!=""){
		g_goods_salestart_s=encodeURI(encodeURI(g_goods_salestart_s));
		searchtj+="&g_goods_salestart_s="+g_goods_salestart_s;
	}
	var g_goods_saleend_s =$("#g_goods_saleend_s").val();
	if(g_goods_saleend_s!=null&&g_goods_saleend_s!=""){
		g_goods_saleend_s=encodeURI(encodeURI(g_goods_saleend_s));
		searchtj+="&g_goods_saleend_s="+g_goods_saleend_s;
	}
	var g_goods_stockstart_s =$("#g_goods_stockstart_s").val();
	if(g_goods_stockstart_s!=null&&g_goods_stockstart_s!=""){
		g_goods_stockstart_s=encodeURI(encodeURI(g_goods_stockstart_s));
		searchtj+="&g_goods_stockstart_s="+g_goods_stockstart_s;
	}
	var g_goods_stockend_s =$("#g_goods_stockend_s").val();
	if(g_goods_stockend_s!=null&&g_goods_stockend_s!=""){
		g_goods_stockend_s=encodeURI(encodeURI(g_goods_stockend_s));
		searchtj+="&g_goods_stockend_s="+g_goods_stockend_s;
	}
	var g_goods_source_s =$("#g_goods_source_s").val();
	if(g_goods_source_s!=null&&g_goods_source_s!=""){
		g_goods_source_s=encodeURI(encodeURI(g_goods_source_s));
		searchtj+="&g_goods_source_s="+g_goods_source_s;
	}
	var g_goods_depot_s =$("#g_goods_depot_s").val();
	if(g_goods_depot_s!=null&&g_goods_depot_s!=""){
		g_goods_depot_s=encodeURI(encodeURI(g_goods_depot_s));
		searchtj+="&g_goods_depot_s="+g_goods_depot_s;
	}
	var brand_name_s =$("#brand_name_s").val();
	if(brand_name_s!=null&&brand_name_s!=""){
		brand_name_s=encodeURI(encodeURI(brand_name_s));
		searchtj+="&brand_name_s="+brand_name_s;
	}
	var tab_s =$("#tab_s").val();
	if(tab_s!=null&&tab_s!=""){
		tab_s=encodeURI(encodeURI(tab_s));
		searchtj+="&tab_s="+tab_s;
	}
	var g_goods_useintegral_s =$("#g_goods_useintegral_s").val();
	if(g_goods_useintegral_s!=null&&g_goods_useintegral_s!=""){
		g_goods_useintegral_s=encodeURI(encodeURI(g_goods_useintegral_s));
		searchtj+="&g_goods_useintegral_s="+g_goods_useintegral_s;
	}
	var goods_no_s =$("#goods_no_s").val();
	if(goods_no_s!=null&&goods_no_s!=""){
		goods_no_s=encodeURI(encodeURI(goods_no_s));
		searchtj+="&goods_no_s="+goods_no_s;
	}
	var g_goods_place_s =$("#g_goods_place_s").val();
	if(g_goods_place_s!=null&&g_goods_place_s!=""){
		g_goods_place_s=encodeURI(encodeURI(g_goods_place_s));
		searchtj+="&g_goods_place_s="+g_goods_place_s;
	}
	var g_goods_barcode_s =$("#g_goods_barcode_s").val();
	if(g_goods_barcode_s!=null&&g_goods_barcode_s!=""){
		g_goods_barcode_s=encodeURI(encodeURI(g_goods_barcode_s));
		searchtj+="&g_goods_barcode_s="+g_goods_barcode_s;
	}
	var g_goods_producer_s =$("#g_goods_producer_s").val();
	if(g_goods_producer_s!=null&&g_goods_producer_s!=""){
		g_goods_producer_s=encodeURI(encodeURI(g_goods_producer_s));
		searchtj+="&g_goods_producer_s="+g_goods_producer_s;
	}
	var g_goods_sale_id_s =$("#g_goods_sale_id_s").val();
	if(g_goods_sale_id_s!=null&&g_goods_sale_id_s!=""){
		g_goods_sale_id_s=encodeURI(encodeURI(g_goods_sale_id_s));
		searchtj+="&g_goods_sale_id_s="+g_goods_sale_id_s;
	}
	var good_cat_attr="";
	$("select[name='good_cat_attr']").each(function(){
		good_cat_attr+=$(this).val()+",";
	});
	//去掉最后一个逗号
	good_cat_attr=deleteLastChar(good_cat_attr,",");
	if(good_cat_attr!=null&&good_cat_attr!=""&&good_cat_attr!="0"){
	    if(good_cat_attr.indexOf(",0")){
	     good_cat_attr=good_cat_attr.substring(0,good_cat_attr.length-2);
	    }
		good_cat_attr=encodeURI(encodeURI(good_cat_attr));
		searchtj+="&good_cat_attr="+good_cat_attr;
	}
   return searchtj;
}

//列表批量删除操作,提示可以自定义
function delInfoTip(field_name,actionName,tipInfo){
	// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}else{
		art.dialog({
			title: '系统提示',
			content:'<div class="decorate">'+tipInfo+'?'+'</div>',
			okValue: '确定',
			width: '238px',
			cancelValue: '取消',
		    ok: function () {
		        document.getElementById('commonForm').action=actionName;
		    	document.getElementById('commonText').name = "chb_id";
		    	document.getElementById('commonText').value = filedVal;
		    	document.getElementById('commonForm').submit();	  
		    },
		    cancel: function () {
				return true;
		    }
		});
	}
}
















