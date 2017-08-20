//搜索优惠券开始
var ColumnSearchCoupon = [
    {
		header : "<input id='checkall' type='checkbox' onclick=\"selectAll('coupon_id')\" name='checkbox'>",
		width:"3%",
		tphtml:"<input  type='checkbox' value='$' class='coupon_id' name='coupon_id'>",
	    tphtmlval:"coupon_id"			
	},{
		header : '号码',
		headerIndex : 'coupon_no',
		width:"17%"			
	},{
		header : '优惠券名称',
		width:"42%",
		cpos:"left",
		tphtml:"<span id='coupon$' | title='$'>|$</span>",
	    tphtmlval:"coupon_id|coupon_name|coupon_name?20"				
	},{
		header : '会员级别',
		width:"25%",
		tphtml:"<span  title='$'>|$</span>",
	    tphtmlval:"member_level|member_level"			
	}];
//搜索优惠券结束

//添加优惠券
function addCoupon(){
	$("#searchgoods").jqmShow();
	$("#title").html("添加优惠券");
	$("#coupon_name").html("优惠券名称");
	$("#search").html('<input type="button" value="搜索" onclick="searchCoupon();" />');
	$("#save").html('<input type="button" value="保存" onclick="savecoupon();"/>');	
	$("#searchtitle").hide();
	//搜索商品控件
	searchCoupon();
}

//保存相关优惠券
function savecoupon(){
 	var coupon_gi_str="";
 	var falg=false;
    var aleady_coupon_name="";
    var coupon_plan=$("#coupon_plan").val();
 	var rgs=coupon_plan.split(",");
 	$(".coupon_id").each(function(){
 		if(falg){
 			return;
 		}
 		if(this.checked){
 			var sel_value=this.value;
 			for(var i=0;i<rgs.length;i++){
 				if(sel_value==rgs[i]){
 					falg=true;
 					aleady_coupon_name=$("#coupon"+sel_value).html();
 				}
 			}
 			if(!falg){
 				coupon_gi_str += this.value+",";
 			}
 		}
 	});
 	if(falg){
 		jNotify("您已添加优惠券["+aleady_coupon_name+"]!"); 
 		return;
 	}
 	coupon_gi_str=deleteLastChar(coupon_gi_str,",");
 	if(coupon_gi_str!=""){
 		//加载已选的相关商品
		loadcoupon(coupon_gi_str);
 	}
	//选择所有相关商品的goods_id
	var al_coupon_id="";
	$(".al_coupon_id").each(function(){
		al_coupon_id+=this.value+",";
	});
	al_coupon_id=deleteLastChar(al_coupon_id,",");
	$("#coupon_plan").val(al_coupon_id);
	$("#searchgoods").jqmHide();
}

//搜索优惠券
function searchCoupon(){
    var s_goods_name =$("#sg_name").val();
	$('#searchgoodslist').mall({
		    actionName:'/coupon!searchCoupon.action?s_goods_name='+s_goods_name,
		 	columnModel:ColumnSearchCoupon,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"未搜索到优惠券!"
	});
}


//加载相关优惠券数据
function loadcoupon(sgis){
	$.ajax({
          type: "post",
          url: "/coupon!searchCoupon.action?sgis="+sgis,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.data.length;i++){
				var liStr="<tr id='coupon_li"+data.data[i].coupon_id+"'><td class='span_td1'><img  onclick='del_coupon("+data.data[i].coupon_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_coupon_id' value='"+data.data[i].coupon_id+"'/></td>";
				liStr+="<td class='span_td2'>"+data.data[i].coupon_name+"</td></tr>";
				$("#selulcp").append(liStr);
			 }
          }	 
	});   
}

//删除优惠券
function del_coupon(coupon_id){
	$("#coupon_li"+coupon_id).remove();
	//选择所有相关优惠券的goods_id
	var al_coupon_id="";
	$(".al_coupon_id").each(function(){
		al_coupon_id+=this.value+",";
	});
	al_coupon_id=deleteLastChar(al_coupon_id,",");
	$("#coupon_plan").val(al_coupon_id);
}







