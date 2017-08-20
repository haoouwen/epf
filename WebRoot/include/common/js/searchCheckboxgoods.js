//搜索商品开始
var ColumnSearchGoods = [
    {
		header : "<input id='checkall' type='checkbox' onclick=\"selectAll('ralate_goods_id')\" name='checkbox'>",
		width:"3%",
		tphtml:"<input  type='checkbox' value='$' class='ralate_goods_id' name='ralate_goods_id'>",
	    tphtmlval:"goods_id"			
	},{
		header : '商品编号',
		headerIndex : 'goods_no',
		width:"12%"			
	},{
		header : '商品名称',
		width:"42%",
		cpos:"left",
		tphtml:"<span id='ralate$' | title='$'>|$</span>",
	    tphtmlval:"goods_id|goods_name|goods_name?20"				
	},{
		header : '商品分类',
		cpos:"left",
		tphtml:"<span  title='$'>|$</span>",
	    tphtmlval:"cat_attr|cat_attr?15",	
		width:"28%"			
	},{
		header : '商品价格',
		width:"15%",
		tphtml:"￥$元",
	    tphtmlval:"min_sale_price"			
	}];
//搜索商品结束
//保存相关商品
function saveralate(){
 	var ralate_gi_str="";
 	var falg=false;
 	var falg_combo=false;
 	var relate_goods=$("#relate_goods").val();
 	var rgs=relate_goods.split(",");
    var aleady_goods_name="";
 	$(".ralate_goods_id").each(function(){
 		if(falg){
 			return;
 		}
 		if(this.checked){
 			var sel_value=this.value;
 			for(var i=0;i<rgs.length;i++){
 				if(sel_value==rgs[i]){
 					falg=true;
 					aleady_goods_name=$("#ralate"+sel_value).html();
 				}
 			}
 			if(!falg){
 				ralate_gi_str += this.value+",";
 				//获取商品个数
 				num=(ralate_gi_str.split(',')).length-1
 				
 			}
 		}
 	});
 	if(falg){
 		jNotify("您已添加商品["+aleady_goods_name+"]!"); 
 		return;
 	}
 	 //判断所选的最少商品数量，和最多商品数量
	if(num<2){
		alert("请至少添加两个商品！");
		return null;
	}
	if(num>6){
		alert("对不起，最多只能添加六个商品！");
		return null;
	}
	$.ajax({
          type: "post",
          url: "/combo!getregoods.action?ralate="+ralate_gi_str,
          datatype:"json",
          async:false,
          success: function(data){ 
          	if(data=='1'){
          	  falg_combo=true;
          	}
          }	 
	});   
	if(falg_combo){
 		jNotify("您已添过改组合商品"); 
 		return;
 	}
 	ralate_gi_str=deleteLastChar(ralate_gi_str,",");
 	if(ralate_gi_str!=""){
 		//加载已选的相关商品
		loadralate(ralate_gi_str);
 	}
	//选择所有相关商品的goods_id
	var al_goods_id="";
	$(".al_goods_id").each(function(){
		al_goods_id+=this.value+",";
	});
	al_goods_id=deleteLastChar(al_goods_id,",");
	$("#relate_goods").val(al_goods_id);
	closeRalateGoods('searchgoods');
}
//搜索商品
function search(){
	var s_goods_name =$("#sg_name").val();
	s_goods_name=encodeURI(encodeURI(s_goods_name));
	$('#searchgoodslist').mall({
		    actionName:'/goods!searchGoods.action?s_goods_name='+s_goods_name,
		 	columnModel:ColumnSearchGoods,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"请先添加商品!"
	});
}

//显示相并商品数据
function showralatelist(){
	if($("#relate_goods")!=""){
		var sgis = $("#relate_goods").val();
	}
	
}
//加载相关商品数据
function loadralate(sgis){
	$.ajax({
          type: "post",
          url: "/goods!searchGoods.action?sgis="+sgis,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.data.length;i++){
				var liStr="<tr id='ralate_li"+data.data[i].goods_id+"'><td class='span_td1'><img  onclick='del_ralate("+data.data[i].goods_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_goods_id' value='"+data.data[i].goods_id+"'/></td>";
				liStr+="<td class='span_td2'>"+data.data[i].goods_name+"</td></tr>";
				$("#selulrg").append(liStr);
			 }
          }	 
	});   
}

//删除相关商品
function del_ralate(goods_id){
	$("#ralate_li"+goods_id).remove();
	//选择所有相关商品的goods_id
	var al_goods_id="";
	$(".al_goods_id").each(function(){
		al_goods_id+=this.value+",";
	});
	al_goods_id=deleteLastChar(al_goods_id,",");
	$("#relate_goods").val(al_goods_id);
}
