//初始化加载
var inz_cat_id="1111111111";
var inz_cat_name="商品分类";
var inz_mod_type="goods";
$(document).ready(function(){
	var mod_type="";
	 var idstr=$("#back_sel_cat").val();
	 inz_cat_name=$("#modletype option:selected").text()+"分类";   
	 inz_mod_type=$("#modletype option:selected").val(); 
	 if(idstr==""){
	 	getChildMenu(inz_cat_id,inz_mod_type,inz_cat_name);
	 }else{
		backSelectList(idstr,inz_mod_type);
	 }
      //select回选
      selectCheckObj("cate.module_type",mod_type);  
      //菜单名称改变触发事件
	  $("#modletype").change(function(){//事件触发 
	   	   $('#catTable').html(''); 
		   $('option:selected', this).each(function(){
                 inz_mod_type=this.value;
                 inz_cat_name=$("#modletype option:selected").text()+"分类";  
                 $("#mod_type").val(inz_mod_type);
                 getChildMenu(inz_cat_id,inz_mod_type,inz_cat_name);                 	
		   });  	
  		});         	  
	  if($("#is_del").val()!=null&&$("#is_del").val()!=''&&$("#is_del").val()=='1'){
	 	artTipMessage("该分类已删除，不能添加");
	 }
	 //初始化加载的获取被选中的分类模块值 放入隐藏域，提供给删除的时候，去判断改分类是否有关联商品
	 if($("#mod_type").val()==null||$("#mod_type").val()==""){
		 $('option:selected').each(function(){
	          $("#mod_type").val(this.value);
	    });
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
			 var tbTree=createTalbe(data,pId,mod_type,cat_name);	
			 $("#catTable").append(tbTree);	
          }	 
	});    	     
	
}

//分类回选列表
function backSelectList(idStr,mod_type){
	var ids = idStr.split(",");
	var nameStr = $("#back_sel_cat_name").val();
	var names = nameStr.split(",");
	for(var i=0;i<ids.length;i++){
		if(i==0){
			getChildMenu(ids[i],mod_type,inz_cat_name);
		}else{
			getChildMenu(ids[i],mod_type,names[i]);
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

//新建分类
function add(id,mod_type){
	$("#up_cat_id").val(id);
	$("#mod_type").val(mod_type);
	$("#catform").attr("action","/admin_Category_add.action").submit();
}
//编辑分类
function edit(id){
	$("#cat_id").val(id);
	$("#catform").attr("action","/admin_Category_view.action").submit();
}

//分类排序
function sort(){
	$("#catform").attr("action","/admin_Category_updateSort.action").submit();
}

//删除分类
function del(id){
	var s_module_type=$("#mod_type").val();
	   //判断该分类是否有绑定商品
	   $.ajax({
	         type: "post",
	         url: "/category!ajaxCatGoods.action?cat_id="+id+"&module_type="+s_module_type,
	         datatype:"json",
	         async:false,
	         success: function(data){ 
	         	if(data=="1"){
		         	art.dialog({
						title: '系统提示',
						content:'<div class="decorate">'+'确定删除?删除后子分类也会被删除！'+'</div>',
						okValue: '确定',
						width: '238px',
						cancelValue: '取消',
					    ok: function () {
						    $("#cat_id").val(id);
						    $("#catform").attr("action","/admin_Category_delete.action").submit();
					    },
					    cancel: function () {
							return true;
					    }
				    });
	         	}else if(data=="2"){
				    artTipMessage("该分类下已关联商品，不能删除！");
	         	}
	         }	 
	});   
}

function artTipMessage(mes_text){
  art.dialog({
		title: '系统提示',
		content:'<div class="decorate">'+mes_text+'</div>',
		okValue: '确定',
		width: '238px',
	    ok: function () {
	    }
   });
}

//构造分类列表
function createTalbe(data,pId,mod_type,cat_name){

	var strTable="";
	strTable+="<div class='contentDiv'>";
	strTable+="<h3 class='contitle'>"+cat_name+"</h3>";
	strTable+="<ul class='licontent'>";
	
	for(var i=0;i<data.length;i++){
		var strColor="";
		if(data[i].is_display=='0'){
			strColor="style='color:rgb(153, 153, 153);'";
		}
		//判断是否有图标
		var imgico="";
		if(data[i].img_ico!=null&&data[i].img_ico!=""){
			imgico="<img src='"+data[i].img_ico+"' width='20px' height='20px' />";
		}
		strTable+="<li id='"+data[i].cat_id+"' onclick='loadNextLevel(\""+data[i].cat_id+"\",this);'>"
		strTable+="<input type='text' class='sortno' name='category.sort_no' value='"+data[i].sort_no+"'>";
		strTable+="<input type='hidden'  name='category.cat_id' value='"+data[i].cat_id+"'>";
		strTable+="<span class='contxt'"+strColor+">"+imgico+" "+data[i].cat_name+"</span>";
		strTable+="<span class='operbt'><img class='operimg' src='/include/common/images/bj.gif'  onclick='edit(\""+data[i].cat_id+"\");'/>";
		if(data[i].is_sys=='0'){
			strTable+="<img class='ltip' src='/include/common/images/light.gif' title='系统预设分类不能删除'style='margin-right:12px;margin-left:3px;'></span>";
		}else{
			strTable+="<img class='operimg' src='/include/common/images/delete.png' onclick='del(\""+data[i].cat_id+"\",this);'/></span>";
		}
		strTable+="<div class='clear'></div>";
	}
	strTable+="</ul><h3 class='bottomoper'>";
	strTable+="<img class='operimg' src='/include/common/images/add.png'  onclick='add(\""+pId+"\",\""+mod_type+"\");'title='添加分类'/>";
	strTable+="<img class='operimg' src='/include/common/images/edit.png'  onclick='sort();'title='排序'/>";
	strTable+="</h3></div>";
	 
    return strTable;
}
