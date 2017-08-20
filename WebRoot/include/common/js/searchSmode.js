//搜索运费模板开始
var ColumnSearchShip = [
	{ 
		header : '选择模板',
		width:"12%",
		tphtml:"<input type='radio' value='$' class='s_ship_id' name='s_ship_id'>|<input type='hidden' id='ship$' | value='$'/>|<input type='hidden' id='shipname$' | value='$'/>",
	    tphtmlval:"ship_id|ship_id|valuation_mode|ship_id|ship_name"			
	},{
		header : '模板名称',
		headerIndex : 'ship_name',
		width:"40%"			
	},{
		header : '模板类型',
		headerIndex : 'valuation_mode',
		width:"18%",
		changeval:"1:按件数,2:按重量,3:按体积"			
	},{
		header : '发货地址',
		headerIndex : 'start_area',
		width:"30%"			
	}];
	
//显示模板框
function  selsmodetemplate(){
	$("#searchsmode").jqmShow();
	searchsmode();
}

//关闭模板框
function closesmode(){
	$("#searchsmode").jqmHide();
}

//保存所选模板
function savesmode(){
	var valuation_mode="";
	var ship_id="";
	if(typeof($("input:radio[name='s_ship_id']:checked").val())!="undefined"){
		ship_id=$("input:radio[name='s_ship_id']:checked").val();
		valuation_mode = $("#ship"+ship_id).val();
	}else{
		jNotify("请选择模板!"); 
		return;
	}
	$("#showtemplatename").html("已选择模板名称:<font color='red'>"+$("#shipname"+ship_id).val()+"</font>");
	$("#ship_id").val(ship_id);
	$("#searchsmode").jqmHide();
}

//搜索模板
function searchsmode(){
	var ship_name =$("#ship_name").val();
	$('#searchsmodelist').mall({
		    actionName:'/shiptemplate!searchShip.action?ship_name='+ship_name,
		 	columnModel:ColumnSearchShip,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"未检测到运费模板!请点击<a href='/admin_Shiptemplate_add.action' target=‘_blank’>添加运费模板</a>"
	});
}

//选择运费
function selshipval(obj){
	var isfreeship=$(obj).val();
	if(isfreeship==0){
		$(".ship").css("display","none");
	}else{
		$(".ship").css("display","block");
	}
}


