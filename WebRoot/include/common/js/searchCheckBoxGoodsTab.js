
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

//添加相关商品
function addRalateGoodsTab(fm_id,sid,type,obj){
	addRalateGoods(sid,type,obj);
	$("#fm_id_id").val(fm_id);
}
//保存相关商品
function saveralate(){
    fm_id=$("#fm_id_id").val();
 	var ralate_gi_str="";
 	var falg=false;
 	var relate_goods=$("#relate_goods_"+fm_id).val();
 	var rgs=relate_goods.split("_");
    var aleady_goods_name="";
 	$(".ralate_goods_id").each(function(){
 		if(falg){
 			return;
 		}
 		if(this.checked){
 			var sel_value=this.value;
 			if(relate_goods!=null&&relate_goods!="")
	 			for(var i=0;i<rgs.length;i++){
	 				if(sel_value==rgs[i]){
	 					falg=true;
	 					aleady_goods_name=$("#ralate"+sel_value).html();
	 				}
	 			}
 			if(!falg){
 				ralate_gi_str += this.value+"_";
 				//获取商品个数
 				num=(ralate_gi_str.split('_')).length-1
 				
 			}
 		}
 	});
 	if(falg){
 		jNotify("您已添加商品["+aleady_goods_name+"]!"); 
 		return;
 	}
 	 //判断所选的最少商品数量，和最多商品数量
	if(num<1){
	    jNotify("请至少添加1个商品！"); 
		return null;
	}
	if(num>8){
		jNotify("对不起，最多只能添加8个商品！"); 
		return null;
	}else{
		if(relate_goods!=null&&relate_goods!=""){
		    if((rgs.length+num)>8){
			    jNotify("对不起，最多只能添加8个商品！"); 
				return null;
		    }
		}
	}
 	ralate_gi_str=deleteLastChar(ralate_gi_str,"_");
 	if(ralate_gi_str!=""){
 		//加载已选的相关商品
		loadralate(ralate_gi_str,fm_id);
 	}
	//选择所有相关商品的goods_id
	var al_goods_id="";
	$(".al_goods_id_"+fm_id).each(function(){
		al_goods_id+=this.value+"_";
	});
	al_goods_id=deleteLastChar(al_goods_id,"_");
	$("#relate_goods_"+fm_id).val(al_goods_id);
	$("#fm_id_id").val("");
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
//加载相关商品数据
function loadralate(sgis,fm_id){
    if(sgis!=null&&sgis!=""){
        sgis=sgis.replace(/_/g,",");
    }
	$.ajax({
          type: "post",
          url: "/goods!searchGoods.action?sgis="+sgis,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.data.length;i++){
				var liStr="<tr id='ralate_li_"+fm_id+"_"+data.data[i].goods_id+"'>"
				liStr+="<td class='span_td4' width='40%'>"+data.data[i].goods_name+"</td>"
				liStr+="<td class='span_td4' width='30%'>"+data.data[i].cat_attr+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].min_sale_price+"</td>"
				liStr+="<td class='span_td4' width='10%'>"+data.data[i].total_stock+"</td>"
				liStr+="<td class='span_td3' width='10%'><img  onclick='del_ralate("+data.data[i].goods_id+","+fm_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_goods_id_"+fm_id+"' value='"+data.data[i].goods_id+"'/></td>"
				liStr+="</tr>";
				$("#selulrg_"+fm_id).append(liStr);
			 }
          }	 
	});   
}

//删除相关商品
function del_ralate(goods_id,fm_id){
	$("#ralate_li_"+fm_id+"_"+goods_id).remove();
	//选择所有相关商品的goods_id
	var al_goods_id="";
	$(".al_goods_id_"+fm_id).each(function(){
		al_goods_id+=this.value+"_";
	});
	al_goods_id=deleteLastChar(al_goods_id,"_");
	$("#relate_goods_"+fm_id).val(al_goods_id);
}


