//搜索红包开始
var ColumnSearchRedpacket = [
    {
		header : "<input id='checkall' type='checkbox' onclick=\"selectAll('red_id')\" name='checkbox'>",
		width:"3%",
		tphtml:"<input  type='checkbox' value='$' class='red_id' name='red_id'>",
	    tphtmlval:"red_id"			
	},{
		header : '号码',
		headerIndex : 'red_no',
		width:"17%"			
	},{
		header : '红包名称',
		width:"42%",
		cpos:"left",
		tphtml:"<span id='coupon$' | title='$'>|$</span>",
	    tphtmlval:"red_id|red_name|red_name?20"				
	},{
		header : '金额',
		cpos:"left",
		tphtml:"$元",
	    tphtmlval:"money",	
		width:"13%"			
	},{
		header : '会员级别',
		width:"25%",
		tphtml:"<span  title='$'>|$</span>",
	    tphtmlval:"member_level|member_level"			
	}];
//搜索红包结束

//添加红包
function addRedpacket(){
	$("#searchgoods").jqmShow();
	$("#title").html("添加红包");
	$("#coupon_name").html("红包名称");
	$("#search").html('<input type="button" value="搜索" onclick="searchRedpacket();" />');
	$("#save").html('<input type="button" value="保存" onclick="saveredpacket();"/>');
	$("#searchtitle").hide();	
	//搜索商品控件
	searchRedpacket();
}

//保存相关红包
function saveredpacket(){
 	var redpacket_gi_str="";
 	var falg=false;
    var aleady_redpacket_name="";
    var coupon_plan=$("#coupon_plan").val();
 	var rgs=coupon_plan.split(",");
 	$(".red_id").each(function(){
 		if(falg){
 			return;
 		}
 		if(this.checked){
 			var sel_value=this.value;
 			for(var i=0;i<rgs.length;i++){
 				if(sel_value==rgs[i]){
 					falg=true;
 					aleady_redpacket_name=$("#redpacket"+sel_value).html();
 				}
 			}
 			if(!falg){
 				redpacket_gi_str += this.value+",";
 			}
 		}
 	});
 	if(falg){
 		jNotify("您已添加红包["+aleady_redpacket_name+"]!"); 
 		return;
 	}
 	redpacket_gi_str=deleteLastChar(redpacket_gi_str,",");
 	if(redpacket_gi_str!=""){
 		//加载已选的相关红包
		loadredpacket(redpacket_gi_str);
 	}
	//选择所有相关红包的red_id
	var al_redpacket_id="";
	$(".al_redpacket_id").each(function(){
		al_redpacket_id+=this.value+",";
	});
	al_redpacket_id=deleteLastChar(al_redpacket_id,",");
	$("#coupon_plan").val(al_redpacket_id);
	$("#searchgoods").jqmHide();
}

//搜索红包
function searchRedpacket(){
    var s_goods_name =$("#sg_name").val();
	$('#searchgoodslist').mall({
		    actionName:'/redpacket!searchRedpacket.action?s_goods_name='+s_goods_name,
		 	columnModel:ColumnSearchRedpacket,
		 	pageSize:10,
		 	otherMethod:"",
		 	tableTrFirst:"#F3F7FA",
		 	nullData:"未搜索到红包!"
	});
}


//加载相关红包数据
function loadredpacket(sgis){
	$.ajax({
          type: "post",
          url: "/redpacket!searchRedpacket.action?sgis="+sgis,
          datatype:"json",
          async:false,
          success: function(data){ 
          	 data=eval("(" +data+")");
         	 for(var i=0;i<data.data.length;i++){
				var liStr="<tr id='redpacket_li"+data.data[i].red_id+"'><td class='span_td1'><img  onclick='del_redpacket("+data.data[i].red_id+");' src='/include/common/images/delete.png' >";
				liStr+="<input type='hidden' class='al_redpacket_id' value='"+data.data[i].red_id+"'/></td>";
				liStr+="<td class='span_td2'>"+data.data[i].red_name+"</td></tr>";
				$("#selulrp").append(liStr);
			 }
          }	 
	});   
}

//删除红包
function del_redpacket(red_id){
	$("#redpacket_li"+red_id).remove();
	//选择所有相关红包的red_id
	var al_redpacket_id="";
	$(".al_redpacket_id").each(function(){
		al_redpacket_id+=this.value+",";
	});
	al_redpacket_id=deleteLastChar(al_redpacket_id,",");
	$("#coupon_plan").val(al_redpacket_id);
}







