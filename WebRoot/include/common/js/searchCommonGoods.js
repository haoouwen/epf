//添加相关商品
function addRalateGoods(sid,type,obj){
    $("#type").val(type);
    loadallinfo();	
	//弹出高级查找
    addRgoodsShowDIV(sid,760,490,$(obj).val());		
	$("#searchtitle").show();
	//搜索商品控件
	search();
	
}
//弹出高级搜索框
function addRgoodsShowDIV(id,s_width,s_height,s_title){
    $("#"+id).popup_search({p_width:s_width, p_height:s_height, pop_title:s_title});
}
//关闭相关商品
function closeRalateGoods(sid){
	$("#"+sid).ccover();
}
//关闭相关商品并刷新页面
function closeRalateGoodsRef(sid){
	$("#"+sid).ccover();
	window.location.reload();
}