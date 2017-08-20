//删除阶梯价格
function dellab(id){
	$("#uptr"+id).remove();
}
//新增阶梯价格
function addladder(){
		var upLength=$(".lownum").length;
		var uptrHtml="<tr id='uptr"+upLength+"'><td width='60px;' align='center'><img onclick=\"dellab('"+upLength+"');\" src='/include/common/images/delete.png'></td>";
		uptrHtml+="<td class='table_name'>购买数量下限:</td>";
		uptrHtml+="<td><input type=\"text\" name=\"lownums\"  id=\"lownum\" style=\"width:142px;\" onkeyup=\"checkDigital(this)\" maxlength=\"6\"/></td>";
		uptrHtml+="<td class='table_name'>购买价格:</td>";		
		uptrHtml+="<td><input type=\"text\" name=\"ladprices\" id=\"ladprice\"style=\"width:142px;\" onkeyup=\"checkRMB(this)\"maxlength=\"6\"/></td>";			
	uptrHtml+="</tr>";		
	$("#groupladder").append(uptrHtml);
}
function showprice(obj){
  //obj:0: 一口价  1：阶梯价格
  if(obj.value=="0"){
		$("#ladder").hide();
		$("#shot").show();
  }else if(obj.value=="1"){
		$("#ladder").show();
		$("#shot").hide();
  }
}
	//阶梯价格处理开始
	var lab_num_str="";
	var lab_pri_str="";
	$(".num").each(function(i){
		if($(this).val()==""){
			return;
		}
		lab_num_str+=$(this).val()+mark;
		lab_pri_str+=$(".pri").eq(i).val()+mark;
	});
	goods_up_str=deleteLastChar(goods_up_str,mark);
	goods_down_str=deleteLastChar(goods_down_str,mark);
	$("#lab_num_str").val(lab_num_str);
	$("#lab_pri_str").val(lab_pri_str);