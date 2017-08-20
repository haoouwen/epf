//初始化加载
var inz_cat_id="1111111111";
var inz_cat_name="虚拟商品分类";
var inz_mod_type="virtual";
$(document).ready(function(){
	 var idstr=$("#back_sel_cat").val();
	 if(idstr==""){
	 	getChildMenu(inz_cat_id,inz_mod_type,inz_cat_name);
	 }else{
		backSelectList(idstr);
	 }
});

//获取菜单的子菜单
function getChildMenu(pId,mod_type,cat_name){
	$.ajax({
          type: "post",
          url: "/category!childList.action?pId="+pId+"&mod_type="+mod_type,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");    
          	 if(data.length!=0){
          	 	var tbTree=createTalbe(data,pId,cat_name);	
			 	$("#catTable").append(tbTree);	
			 }
          }	 
	});    	     
}

//分类回选列表
function backSelectList(idStr){
	var ids = idStr.split(",");
	var nameStr = $("#back_sel_cat_name").val();
	var names = nameStr.split(",");
	for(var i=0;i<ids.length;i++){
		if(i==0){
			getChildMenu(ids[i],inz_mod_type,inz_cat_name);
		}else{
			getChildMenu(ids[i],inz_mod_type,names[i]);
		}
		$("#"+ids[i]).addClass("libackground");
	}
}

//获取下一级列表
function loadNextLevel(id,obj){
    //设置li背景颜色
    $(obj).closest(".contentDiv").find("li").removeClass("libackground");
    $(obj).addClass("libackground");
    //清除某id后的全部元素
    $(obj).closest(".contentDiv").nextAll().remove();
    //获取分类名称
    var cat_name=$(obj).find(".contxt").html();
	getChildMenu(id,inz_mod_type,cat_name);
    //获取被选中的ID串
    var catIdStr=inz_cat_id+",";
    var catNameStr="";
    $(obj).closest(".op_content").find(".libackground").each(function(){
    	catIdStr+=$(this).attr("id")+",";
    	
    });
    $(".op_content").find(".contitle").each(function(){
    	catNameStr+=$(this).html()+",";
    });
    catIdStr = deleteLastChar(catIdStr,",");
    catNameStr = deleteLastChar(catNameStr,",");
	$("#back_sel_cat").val(catIdStr);
	$("#back_sel_cat_name").val(catNameStr);
}



//构造分类列表
function createTalbe(data,pId,cat_name){
	var strTable="";
	strTable+="<div class='contentDiv'>";
	strTable+="<h3 class='contitle'>"+cat_name+"</h3>";
	strTable+="<ul class='licontent'>";
	
	for(var i=0;i<data.length;i++){
		strTable+="<li id='"+data[i].cat_id+"' onclick='loadNextLevel(\""+data[i].cat_id+"\",this);'>"
		strTable+="<input type='hidden'  name='cat_id' value='"+data[i].cat_id+"'>";
		strTable+="<span class='contxt'>"+data[i].cat_name+"</span>";
		strTable+="<div class='clear'></div>";
	}
	
	strTable+="</ul><h3 class='bottomoper'>";
	strTable+="</h3></div>";
	 
    return strTable;
}
