
function selckcat(){
	
	var cat_id_s="";
	var cat_name_s="";
	$("#catDiv").find(".select").each(function(){
		if($(this).val()==0 && cat_id_s==""){
			jNotify("请选择分类!");
		}else{
			if($(this).val()!='0'){
				cat_id_s+=$(this).val()+":";
				cat_name_s+=$(this).find("option:selected").text()+"/";
			}
		}
	})
	cat_id_s=deleteLastChar(cat_id_s,":");
	cat_name_s=deleteLastChar(cat_name_s,"/");
	//判断是否已存在记录
	var flag=false;
	$(".selcatli").each(function(){
		var li_val = $(this).find(".li_val").val();
		if(li_val==cat_id_s){
			flag=true;
		}
	});
	//判断是否存在
	if(flag){
		jNotify("您已添加该分类!");
	}else{
		var liHtml="";
		liHtml+="<li class='selcatli'><p>"+cat_name_s+"</p><input type='hidden' class='li_val' name='li_id' value='"+cat_id_s+"'//>"
		+"<input type='hidden' class='val_name' name='li_val' value='"+cat_name_s+"'/>"+
		"<img src='/includes/common/images/delete.png' onclick='delli(this);'/></li><div class='clear'/>";
		$("#catList").append(liHtml);
	}
}

function delli(obj){
	$(obj).parent("li").remove();
	jNotify("您删除分类成功!");
}