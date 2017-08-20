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
    var aleady_goods_name="";
    var relate_goods=$("#relate_goods").val();
 	var rgs=relate_goods.split(",");
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
 			}
 		}
 	});
 	if(falg){
 		jNotify("您已添加商品["+aleady_goods_name+"]!"); 
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
  var searchtj="";
	searchtj=geturlsearchinfo();
	$('#searchgoodslist').mall({
		    actionName:'/goods!searchGoods.action'+searchtj,
		 	columnModel:ColumnSearchGoods,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"请先添加商品或者重新搜索!"
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
                var liStr="<tr id='ralate_li"+data.data[i].goods_id+"'>"
				liStr+="<td class='span_td4' width='40%'>"+data.data[i].goods_name+"</td>"
				liStr+="<td class='span_td4' width='30%'>"+data.data[i].cat_attr+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].min_sale_price+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].total_stock+"</td>"
				liStr+="<td class='span_td3' width='10%'><img  onclick='del_ralate("+data.data[i].goods_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_goods_id' value='"+data.data[i].goods_id+"'/></td>"
				liStr+="</tr>";	
				if($("#type").val() == "0") {
				   $("#selulrg").append(liStr);
				}else {
				   $("#selulzg").append(liStr);
				}
			 }
          }	 
	});   
}

//加载相关商品数据
function loadALLralate(){
    $("#relate_goods").val();
    $("#save1").html("<img src=\"/include/admin/images/upLoading.gif\">");
    var sgis ="";
	$.ajax({
          type: "post",
          url: "/goods!searchAllGoods.action",
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.data.length;i++){
                var liStr="<tr id='ralate_li"+data.data[i].goods_id+"'>"
				liStr+="<td class='span_td4' width='40%'>"+data.data[i].goods_name+"</td>"
				liStr+="<td class='span_td4' width='30%'>"+data.data[i].cat_attr+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].min_sale_price+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].total_stock+"</td>"
				liStr+="<td class='span_td3' width='10%'><img  onclick='del_ralate("+data.data[i].goods_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_goods_id' value='"+data.data[i].goods_id+"'/></td>"
				liStr+="</tr>";	
				if($("#type").val() == "0") {
				   $("#selulrg").append(liStr);
				}else {
				   $("#selulzg").append(liStr);
				}
				sgis=sgis+data.data[i].goods_id+",";
			 }
          }	 
	});  
	if(sgis!=null&&sgis!=""){
		sgis=deleteLastChar(sgis,",");
	} 
	$("#relate_goods").val(sgis);
	$("#save1").html("<input type=\"button\" value=\"保存全部商品\" onclick=\"loadALLralate();\"/>");
	jNotify("添加全部商品成功!"); 
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




