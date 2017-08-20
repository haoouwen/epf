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
		width:"32%",
		cpos:"left",
		tphtml:"<span id='ralate$' | title='$'>|$</span>",
	    tphtmlval:"goods_id|goods_name|goods_name?20"				
	},{
		header : '商品分类',
		tphtml:"<span title='$'>|$</span>",
	    tphtmlval:"cat_attr|cat_attr?15",
		width:"28%"			
	},{
		header : '销售价格',
		width:"15%",
		tphtml:"￥<span id='saleprice$'>|$</span>元",
	    tphtmlval:"goods_id|min_sale_price"			
	},{
		header : '市场价格',
		width:"10%",
		tphtml:"￥<span id='marketprice$'>|$</span>元",
	    tphtmlval:"goods_id|goods_market_price"			
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
    var selCheck_goods_saleprice="";
    var selCheck_goods_marketprice="";
    //将数据清空，在重新复制
    if($("#goodsname").text()!=null || $("#goodsanme").text()!=""){
 		$("#goodsname").text("");
 	}
 	if($("#goodssaleprice").text()!=null || $("#goodssaleprice").text()!=""){
 		$("#goodssaleprice").text("");
 	}
 	if($("#goodsmarketprice").text()!=null || $("#goodsmarketprice").text()!=""){
 		$("#goodsmarketprice").text("");
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
 			//商品销售价格
 			selCheck_goods_saleprice=$("#saleprice"+sel_value).html();
 			//商品市场价格
 			selCheck_goods_marketprice=$("#marketprice"+sel_value).html();
 			//获取商品id
 			ralate_gi_str = this.value;
 		}
 	});
 	if(selCheck_goods_name==null||selCheck_goods_name==''){
 		alert("请添加商品！");
 		return;
 	}
 	//页面显示
 	$(".danweiShow").css("display","none");
	$("#goodsname").html("商品名称："+selCheck_goods_name);
	//存入隐藏域
	$("#hidden_goodsname").val("商品名称："+selCheck_goods_name);
	$("#goodssaleprice").html("商品销售价格："+selCheck_goods_saleprice+"元");
	//存入隐藏域
	$("#hidden_goodssaleprice").val("商品销售价格："+selCheck_goods_saleprice);
	$("#goodsmarketprice").html("商品市场价格："+selCheck_goods_marketprice+"元");
	//存入隐藏域
	$("#hidden_goodsmarketprice").val("商品市场价格："+selCheck_goods_marketprice);
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
	var fla=$("#flag").val();
	s_goods_name=encodeURI(encodeURI(s_goods_name));
	$('#searchgoodslist').mall({
		    actionName:'/goods!searchGoods.action?s_goods_name='+s_goods_name+"&active_state_flag=0"+"&flag="+fla+"&is_up=1",
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



