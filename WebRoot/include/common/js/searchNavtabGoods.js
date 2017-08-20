//搜索商品开始
var ColumnSearchGoods = [
    {
		header : "<input id='checkallmore' type='checkbox' onclick=\"selectAllBoxs('checkallmore','ralate_goods_id')\" name='checkbox'>",
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
//添加相关商品
function addRalateGoodsNav(tab_id,tab_number_id,sid,type,obj){
    $("#tab_number_id").val(tab_number_id);
    $("#tab_id_id").val(tab_id);
    addRalateGoods(sid,type,obj);	
    $("#p_c_img").hide();
}

//保存相关商品
function saveralate(){
 	var ralate_gi_str="";
 	var tab_id_id=$("#tab_id_id").val();
 	var tab_number_s=$("#tab_number_id").val();
 	var num=0;
 	$(".ralate_goods_id").each(function(){
 		if(this.checked){
			ralate_gi_str += this.value+",";
			//获取商品个数
			num=(ralate_gi_str.split(',')).length-1;
 		}
 	});
 	ralate_gi_str=deleteLastChar(ralate_gi_str,",");
 	if(ralate_gi_str!=""){
 		//加载已选的相关商品
		var falsenum=insertNavGoods(ralate_gi_str,tab_id_id,tab_number_s);
	 	jNotify("您本次共选择"+num+"个相关商品，成功添加"+(parseInt(num)-parseInt(falsenum))+"个相关商品，重复出现相关商品["+falsenum+"]个!"); 
	 	return;
	 	
 	}else{
 		jNotify("请选择要添加的商品!");
 		return; 
 	}
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


//加载相关商品数据
function insertNavGoods(sgis,tab_id_id,tab_number_s){
   var fnum=0;
	$.ajax({
          type: "post",
          url: "/navigation!insertNavGoods.action?sgis="+sgis+"&nav_id="+tab_id_id+"&tab_number_s="+tab_number_s,
          datatype:"json",
          async:false,
          success: function(data){ 
          	fnum=data;
          
          }	 
	});   
	
	return fnum;
}

