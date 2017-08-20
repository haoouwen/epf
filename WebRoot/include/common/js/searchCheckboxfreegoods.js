//搜索赠品开始
var ColumnSearchFreeGoods = [
    {
		header : "<input id='checkall' type='checkbox' onclick=\"selectAll('fg_id')\" name='checkbox'>",
		width:"3%",
		tphtml:"<input  type='checkbox' value='$' class='fg_id' name='fg_id'>",
	    tphtmlval:"fg_id"			
	},{
		header : '赠品编号',
		headerIndex : 'fg_no',
		width:"12%"			
	},{
		header : '赠品名称',
		width:"42%",
		cpos:"left",
		tphtml:"<span id='ralate$' | title='$'>|$</span>",
	    tphtmlval:"fg_id|fg_name|fg_name?20"				
	},{
		header : '赠品分类',
		cpos:"left",
		tphtml:"<span  title='$'>|$</span>",
	    tphtmlval:"cat_attr|cat_attr?15",	
		width:"28%"			
	},{
		header : '赠品价格',
		width:"15%",
		tphtml:"￥$元",
	    tphtmlval:"fg_price"			
	}];
//搜索赠品结束

//添加赠品
function addFreeGoods(){
	$("#searchgoods").jqmShow();
	$("#title").html("添加赠品");
	$("#coupon_name").html("赠品名称");
	$("#search").html('<input type="button" value="搜索" onclick="searchfreegoods();" />');
	$("#save").html('<input type="button" value="保存" onclick="savefreegoods();"/>');
	$("#searchtitle").hide();	
	//搜索商品控件
	searchfreegoods();
}

//保存赠品
function savefreegoods(){
 	var ralate_gi_str="";
 	var falg=false;
    var aleady_goods_name="";
    var coupon_plan=$("#coupon_plan").val();
 	var rgs=coupon_plan.split(",");
 	$(".fg_id").each(function(){
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
 		jNotify("您已添加赠品["+aleady_goods_name+"]!"); 
 		return;
 	}
 	ralate_gi_str=deleteLastChar(ralate_gi_str,",");
 	if(ralate_gi_str!=""){
 		//加载已选的相关商品
		loadFreegoods(ralate_gi_str);
 	}
	//选择所有相关商品的goods_id
	var al_goods_id="";
	$(".al_fg_id").each(function(){
		al_goods_id+=this.value+",";
	});
	al_goods_id=deleteLastChar(al_goods_id,",");
	$("#coupon_plan").val(al_goods_id);
	$("#searchgoods").jqmHide();
}

//搜索赠品
function searchfreegoods(){
    var s_goods_name =$("#sg_name").val();
	$('#searchgoodslist').mall({
		    actionName:'/freegoods!searchGoods.action?s_goods_name='+s_goods_name,
		 	columnModel:ColumnSearchFreeGoods,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"请先添加赠品或者重新搜索!"
	});
}

//显示相并商品数据
function showralatelist(){
	if($("#relate_goods")!=""){
		var sgis = $("#relate_goods").val();
	}
	
}

//加载赠品数据
function loadFreegoods(sgis){
	$.ajax({
          type: "post",
          url: "/freegoods!searchGoods.action?sgis="+sgis,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.data.length;i++){
                var liStr="<tr id='fg_li"+data.data[i].fg_id+"'>"
				liStr+="<td class='span_td4' width='40%'>"+data.data[i].fg_name+"</td>"
				liStr+="<td class='span_td4' width='30%'>"+data.data[i].cat_attr+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].fg_price+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].fg_number+"</td>"
				liStr+="<td class='span_td3' width='10%'><img  onclick='del_fg("+data.data[i].fg_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_fg_id' value='"+data.data[i].fg_id+"'/></td>"
				liStr+="</tr>";	
				$("#selulfg").append(liStr);
			 }
          }	 
	});   
}

//删除赠品
function del_fg(goods_id){
	$("#fg_li"+goods_id).remove();
	//选择所有相关商品的goods_id
	var al_goods_id="";
	$(".al_fg_id").each(function(){
		al_goods_id+=this.value+",";
	});
	al_goods_id=deleteLastChar(al_goods_id,",");
	$("#coupon_plan").val(al_goods_id);
}






