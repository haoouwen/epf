//初始化加载
var inz_tax_id="1111111111";
var inz_tax_name="进出口税率";
$(document).ready(function(){
	 var idstr=$("#back_sel_taxrate").val();
	 if(idstr==""){
	 	getChildMenu(inz_tax_id,inz_tax_name);	
	 }else{
		backSelectList(idstr);
	 }
});
//获取税率的子税率
function getChildMenu(pId,tax_name){
   $.ajax({
       type: "post",
       url: "/taxrate!childList.action?pId="+pId,
       datatype: "json",
       async:false,
       success: function(data){
         data=eval("("+data+")");
         var tbTree=createTalbe(data,pId,tax_name);
         $("#taxrateTable").append(tbTree);
       }
   });
}

//税率回选列表
function backSelectList(idStr){
	var ids = idStr.split(",");
	var nameStr = $("#back_sel_taxrate_name").val();
	var names = nameStr.split(",");
	for(var i=0;i<ids.length;i++){
		if(i==0){
			getChildMenu(ids[i],inz_tax_name);
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
    //获取税率名称
    var tax_name=$(obj).find(".contxt").html();
	getChildMenu(id,tax_name);
    //获取被选中的ID串
    var taxIdStr=inz_tax_id+",";
    var taxNameStr="";
    $(obj).closest(".op_content").find(".libackground").each(function(){
    	taxIdStr+=$(this).attr("id")+",";
    });
    $(".op_content").find(".contitle").each(function(){
    	taxNameStr+=$(this).html()+",";
    });
    taxIdStr = deleteLastChar(taxIdStr,",");
    taxNameStr = deleteLastChar(taxNameStr,",");
	$("#back_sel_taxrate").val(taxIdStr);
	$("#back_sel_taxrate_name").val(taxNameStr);
}

//新增税率
function add(id){
	$("#up_tax_id").val(id);
 	$("#taxrateform").attr("action","/admin_Taxrate_add.action").submit();
}
//编辑税率
function edit(id){
	$("#tax_id").val(id);
	$("#taxrateform").attr("action","/admin_Taxrate_view.action").submit();
}

//税率排序
function sort(){
	$("#taxrateform").attr("action","/admin_Taxrate_updateSort.action").submit();
}

//删除税率
function del(id){
	art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+'确定要删除?删除子税率也会相应的删除！'+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	    	$("#tax_id").val(id);
			$("#taxrateform").attr("action","/admin_Taxrate_delete.action").submit();
	    },
	    cancel: function () {
			return true;
	    }
   });
}
//构造税率列表
function createTalbe(data,pId,tax_name){
    var strTable="";
    strTable+="<div class='contentDiv'>";
    strTable+="<h3 class='contitle'>"+tax_name+"</h3>";
    strTable+="<ul class='licontent'>";
    
    
    for(var i=0;i<data.length;i++){
        var tax_name="";
        tax_name=data[i].tax_name;
        if(tax_name.length>11){
         tax_name=tax_name.substring(0,10);
        }
        
       strTable+="<li id='"+data[i].tax_id+"' onclick='loadNextLevel(\""+data[i].tax_id+"\",this);'>"
        strTable+="<input type='text' class='sortno' name='taxrate.sort_no' value='"+data[i].sort_no+"'>";
        strTable+="<input type='hidden'  name='taxrate.tax_id' value='"+data[i].tax_id+"'>";
		strTable+="<span class='contxt' title='"+data[i].tax_name+"'>"+tax_name+"<span style=\"color:red\"> "+data[i].tax_rate+"% </span></span>";
		strTable+="<span class='operbt'><img class='operimg' src='/include/common/images/bj.gif'  onclick='edit(\""+data[i].tax_id+"\");'/>";
		strTable+="<img class='operimg' src='/include/common/images/delete.png' onclick='del(\""+data[i].tax_id+"\");'/></span>";
		strTable+="<div class='clear'></div>";
    }

    strTable+="</ul><h3 class='bottomoper'>";
	strTable+="<img class='operimg' src='/include/common/images/add.png'  onclick='add(\""+pId+"\");' title='新增' />";
	strTable+="<img class='operimg' src='/include/common/images/edit.png'  onclick='sort();' title='排序'/>";
	strTable+="</h3></div>";
    return strTable;
}
