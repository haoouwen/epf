var no_spec_image = "";$("#no_spec_image").val();

//新增规格值
function addSpecValue(){
	no_spec_image=$("#no_spec_image").val();
	var spectype= $("input[name='specname.show_type']:checked").val();
	createSpecValueTable("","","",spectype);
}

//更新规格值
function updateSpecValue(pId){  
	var showtype=$("#radiovalueid").val();
	$.ajax({
        type: "post",
        url: "/admin_Spec_specvalueAttrList.action?spec_id="+pId,
        datatype:"json",
        async:false,
        success: function(data){ 
        	data=eval("(" +data+")");
        	for(var i=0;i<data.length;i++){
        		createSpecValueTable(data[i].sort,data[i].value_name,data[i].img_path,showtype);	
			}
        }	 
	}); 
}

//建立表格
function createSpecValueTable(sortno,specvalue,specimg ,spectype){
	var radid=getRndStr(10);//随机数ID
	var varspecimg="";//规格值图片
    var varmssortno="0";//排序
    var varspecvalue="";//规格值名称
    var varspectype="";//图片控件显示方式
    var varhiddenimgurl="";//隐藏的图片路径
    if(specvalue!=null&&specvalue!=""){
    	varspecvalue=" value='"+specvalue+"'";
    }
    if(sortno!=null&&sortno!="") {
     varmssortno=sortno;
    }
    if(specimg!=null&&specimg!="") {
    	varspecimg=" src='"+specimg+"'";
    	varhiddenimgurl=specimg;
    }else{
    	varspecimg=" src='"+no_spec_image+"'";
    	varhiddenimgurl=no_spec_image;
    }
    //spectype==/'0':'文字','1':'图片'
    if(spectype!=null&&spectype!=""&&spectype=="0"){
    	 varspectype=" style='display:none;'";
    }else{
    	 varspectype=" style='display:block;'";
    }
	var specTabel="";
	specTabel="<tr  bgcolor='#FFFFFF' id=\'spec"+radid+"\'>" +
			"<td align='center'>" +
			"<input name='all_spec_sort_no_attr' value='"+varmssortno+"' type='text' style='width:50px' onkeyup='checkDigital(this)' /></td>" +
			"<td align='center'> <input name='all_spec_value_attr'maxlength='20' type='text' "+varspecvalue+"/> </td>" +
			"<td align='center'> <span class='showimageattr' "+varspectype+"> " +
			"<input type='hidden' name='all_spec_img_attr' value='"+varhiddenimgurl+"' id='uploadresult"+radid+"' />" +
			"<img height='25px;'  width='25px;' style='margin-right:20px;' name='all_spec_img_attr' id='getimg"+radid+"'" +varspecimg+" />" +
			"<input type='file' name='uploadifyfile"+radid+"' id='uploadifyfile"+radid+"'/>" +
			"<script>uploadSpecImg('"+radid+"','uploadifyfile"+radid+"','uploadresult"+radid+"','fileQueue','"+radid+"');</script>" +
			"<span></td>" +
			"<td align='center'><a onclick="+"delrow(\'spec"+radid+"\')"+"><img src='/include/common/images/delete.png' /></a></td>" +
			" </tr>";
	$("#addspecvalue_id").append(specTabel);
}

//删除行
function delrow(menu_id){
   $("#"+menu_id+"").remove();    	
}

//显示图片与文字的切换
function getshowtype(name){
	$("#radiovalueid").val(name);
	if(name=="0"){
		$("#specimgid").hide();
		$(".showimageattr").hide();
	}else{
		$("#specimgid").show();
		$(".showimageattr").show();
	}
}

//获取随机数方法
function getRndStr(le){
  var str='0123456789';
  var l = parseInt(le)||Math.ceil(Math.random() * 5);
  var ret = '';
  for(var i=0;i<l;i++){
   ret += str.charAt(Math.ceil(Math.random()*(str.length-1)));
  }
  return ret;
}

//提交规格表单的时候，验证是否全部输入数据
function submitSpecInfo(actionName){
	//标志
	var flage=0;
	//提示内容
	var tipContent="";
	var spec_name = $("#specname").val();
	if(spec_name==null || spec_name==""){
		flage=1;
		if(tipContent==""){
			tipContent = "请输入规格名称";
		}
	}
	$("#specform").find("input:[name='all_spec_value_attr']").each(function(){
		if($(this).val()==null||$(this).val()==""){
			flage=1;
			if(tipContent==""){
				tipContent = "请输入规格值";
			}
		}
	});
	//判断
	if(flage=="1"){ 
		jNotify(tipContent);
	}else{
		//分类集合
		var cat_attr_str="";
	    $("input:hidden[name='all_cat_id_str']").each(function(){
	        cat_attr_str+=$(this).val()+"|";
	    }) 
	    //赋值
	    $("#cat_attr_str").val(cat_attr_str);    
		$("#specform").submit();
	}
}












