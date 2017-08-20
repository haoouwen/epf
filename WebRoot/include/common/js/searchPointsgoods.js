$(document).ready(function(){
	$("#searchgoods,#searchsmode").jqm({
		overlay:30
	}); 
})

//搜索商品开始
var ColumnSearchGoods = [
    {
		header : "",
		width:"3%",
		tphtml:"<input type='radio' value='$' class='ralate_goods_id' name='ralate_goods_id'>",
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
		tphtml:"<span title='$'>|$</span>",
	    tphtmlval:"cat_attr|cat_attr?15",
		width:"28%"			
	},{
		header : '商品价格',
		width:"15%",
		tphtml:"￥$元",
	    tphtmlval:"min_sale_price"			
	}];
//搜索商品结束

//添加相关商品
function addRalateGoods(){
	$("#searchgoods").jqmShow();
	//搜索商品控件
	search();
}
function testDiv(){
	$("#searchgoods").jqmShow();
}
//保存设置支付密码
function saveTest(){
	var pass=$("#sg_name").val();
	if(pass==null||pass==''){
		return false;
	}
	$.ajax({
          type: "post",
          url: "/memberfund!updatePass.action",
          datatype:"json",
          data:{pawod:pass},
          async:false,
          success: function(data){ 
          	 	alert("设置支付密码成功！");
          }	 
	});
	$("#searchgoods").jqmHide();
}


//添加相关商品
function addCatGoods(){
	$("#searchgoods").jqmShow();
	//搜索商品控件
	search();
}

//关闭相关商品
function closeralate(){
	$("#searchgoods").jqmHide();
}


//加载直通车商品
function saveGoods(){
 	var ralate_gi_str="";
 	var relate_goods=$("#relate_goods").val();
    var selCheck_goods_name="";
    //将数据清空，在重新复制
    if($("#goodsname").text()!=null || $("#goodsanme").text()!=""){
 		$("#goodsname").text("");
 	}
 	$(".ralate_goods_id").each(function(){
 		if(this.checked){
 			var num=$("input[name='ralate_goods_id']:checked").length; 
 			//判断商品只能选一个
 			if(num>1){
 				alert("对不起，只能添加一条商品！");
 				return;
 			}
 			var sel_value=this.value;
 			//获取商品名称
 			selCheck_goods_name=$("#ralate"+sel_value).html();
 			//获取商品id
 			ralate_gi_str = this.value;
 		}
 	});
 	if(selCheck_goods_name==null||selCheck_goods_name==''){
 		alert("请添加商品！");
 		return;
 	}
 	//页面显示
	$("#goodsname").html(selCheck_goods_name);
	//存入隐藏域
	$("#hidden_goodsname").val(selCheck_goods_name);
	//赋值goods_id
	$("#goods_id").val(ralate_gi_str);
	$("#searchgoods").jqmHide();
}

//更改直通车商品
function updateGoods(){
 	var ralate_gi_str="";
 	var relate_goods=$("#relate_goods").val();
    var aleady_goods_name="";
    //将数据清空，在重新复制
    if($("#goodsname").text()!=null || $("#goodsanme").text()!=""){
 		$("#goodsname").text("");
 	}
 $(".ralate_goods_id").each(function(){
 		if(this.checked){
 			var sel_value=this.value;
 			//获取商品名称
 			aleady_goods_name=$("#ralate"+sel_value).html();
 			//获取商品id
 			ralate_gi_str = this.value;
 		}
 	});
	$("#goodsname").text(aleady_goods_name);
	$("#goods_id").val(ralate_gi_str);
	$("#searchgoods").jqmHide();
}
//搜索商品
function search(){
	var s_goods_name =$("#sg_name").val();
	var flag=$("#flag").val();
	var cust_id_flag=$("#cust_id_flag").val();
	s_goods_name=encodeURI(encodeURI(s_goods_name));
	$('#searchgoodslist').mall({
		    actionName:'/goods!searchGoods.action?s_goods_name='+s_goods_name+"&cust_id_flag="+cust_id_flag+"&active_state_flag=0"+"&flag="+flag,
		 	columnModel:ColumnSearchGoods,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"未搜索到相关商品!"
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
          url: "/goods!searchGoods.action?sgis="+sgis+"&flag="+fla,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.length;i++){
				var liStr="<tr id='ralate_li"+data[i].goods_id+"'><td class='span_td1'><img  onclick='del_ralate("+data[i].goods_id+");' src='/include/common/images/delete.png'>";
				liStr+="<input type='hidden' class='al_goods_id' value='"+data[i].goods_id+"'/></td>";
				liStr+="<td class='span_td2'>"+data[i].goods_name+"</td></tr>";
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



