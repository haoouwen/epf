//初始化加载
var inz_area_id="9999999999";
var inz_area_name="一级地区";
$(document).ready(function(){
	 var idstr=$("#back_sel_area").val();
	 if(idstr==""){
	 	getChildMenu(inz_area_id,inz_area_name);
	 }else{
		backSelectList(idstr);
	 }
});

//获取菜单的子菜单
function getChildMenu(pId,area_name){
	$.ajax({
          type: "post",
          url: "/area!childList.action?pId="+pId,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");    
			 var tbTree=createTalbe(data,pId,area_name);	
			 $("#areaTable").append(tbTree);	
          }	 
	});    	   
}

//分类回选列表
function backSelectList(idStr){
	var ids = idStr.split(",");
	var nameStr = $("#back_sel_area_name").val();
	var names = nameStr.split(",");
	for(var i=0;i<ids.length;i++){
		if(i==0){
			getChildMenu(ids[i],inz_area_name);
		}else{
			getChildMenu(ids[i],names[i]);
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
    var area_name=$(obj).find(".contxt").html();
	getChildMenu(id,area_name);
    //获取被选中的ID串
    var areaIdStr=inz_area_id+",";
    var areaNameStr="";
    $(obj).closest(".op_content").find(".libackground").each(function(){
    	areaIdStr+=$(this).attr("id")+",";
    });
    $(".op_content").find(".contitle").each(function(){
    	areaNameStr+=$(this).html()+",";
    });
    areaIdStr = deleteLastChar(areaIdStr,",");
    areaNameStr = deleteLastChar(areaNameStr,",");
	$("#back_sel_area").val(areaIdStr);
	$("#back_sel_area_name").val(areaNameStr);
}

//新境分类
function add(id){
	$("#up_area_id").val(id);
	$("#areaform").attr("action","/admin_Area_add.action").submit();
}
//编辑分类
function edit(id){
	$("#area_id").val(id);
	$("#areaform").attr("action","/admin_Area_view.action").submit();
}

//分类排序
function sort(){
	$("#areaform").attr("action","/admin_Area_updateSort.action").submit();
}

//删除分类
function del(id){
	art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+'确定要删除?删除后子地区也会相应的删除！'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	    	$("#area_id").val(id);
			$("#areaform").attr("action","/admin_Area_delete.action").submit();
	    },
	    cancel: function () {
			return true;
	    }
   });
}

//构造分类列表
function createTalbe(data,pId,area_name){
	var strTable="";
	strTable+="<div class='contentDiv'>";
	strTable+="<h3 class='contitle'>"+area_name+"</h3>";
	strTable+="<ul class='licontent'>";
	
	for(var i=0;i<data.length;i++){
		strTable+="<li id='"+data[i].area_id+"' onclick='loadNextLevel(\""+data[i].area_id+"\",this);'>"
		strTable+="<input type='text' class='sortno' name='area.sort_no' value='"+data[i].sort_no+"'>";
		strTable+="<input type='hidden'  name='area.area_id' value='"+data[i].area_id+"'>";
		strTable+="<span class='contxt'>"+data[i].area_name+"</span>";
		strTable+="<span class='operbt'><img class='operimg' src='/include/common/images/bj.gif'  onclick='edit(\""+data[i].area_id+"\");'/>";
		strTable+="<img class='operimg' src='/include/common/images/delete.png' onclick='del(\""+data[i].area_id+"\");'/></span>";
		strTable+="<div class='clear'></div>";
	}
	
	strTable+="</ul><h3 class='bottomoper'>";
	strTable+="<img class='operimg' src='/include/common/images/add.png'  onclick='add(\""+pId+"\");' title='新增' />";
	strTable+="<img class='operimg' src='/include/common/images/edit.png'  onclick='sort();' title='排序'/>";
	strTable+="</h3></div>";
	 
    return strTable;
}
