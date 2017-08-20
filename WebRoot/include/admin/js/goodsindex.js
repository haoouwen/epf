$(document).ready(function(){
      //提交表单
	  $("#indexForm").submit(function(){
          var cat_attr_str="";
          $("input:hidden[name='cat_attr_s']").each(function(){
              cat_attr_str+=$(this).val()+"|"; 
          }) 
          $("#cat_attr_str").val(cat_attr_str);     
          return true;
	   });
});
	    
   //批量操作
  	function updateState(is_ip,field_name,actionName){
  	var goods_ids="",goodsnutip="";
	var flag = false;
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			flag=true;
			break;
		}
	}
	if(flag==false){
	    art.dialog({
		title: '系统提示',
	    content: '请至少选择一条记录!'
	    });
		return false;
	}
	if(flag==true){
	    for(var i=0;i<checks.length;i++){
		   if(checks[i].checked){
		   	  goods_ids+=checks[i].value+",";
		   }
		}
		if(goods_ids!=null&&goods_ids!=""){
		  goods_ids=goods_ids.substring(0,goods_ids.length-1);
		}
	    var tip = '';
		if(is_ip=='1'){
			tip = '确认下架吗?';
		}
		goodsnutip=rgGoodsInFloor(goods_ids);
		if(goodsnutip!=""){
		   tip = "您选择的商品编号:"+goodsnutip+"存在楼层,确认下架吗?";
		}
		art.dialog({
		title: '系统信息提示',
		content:'<div class="decorate">'+tip+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        document.getElementById('admin_goods_is_ip').value = is_ip;
		    document.forms["indexForm"].action=actionName;
		    document.forms["indexForm"].submit();
	        return false;
	    },
	   cancel: function () {
				return true;
		    }
	    });
		
	}
}
//验证下架商品是否在商城楼层里面
function rgGoodsInFloor(goods_id){
  var retInfo="";
  $.ajax({
         type: "post",
         url: "/goods!rgGoodsInFloor.action?goods_id="+goods_id,
         datatype:"json",
         async:false,
         success: function(data){ 
		   if(data!=null&&data!=""){
		    retInfo=data;
		   }         
         }	 
 }); 
 return retInfo;
}


function pricepage(field_name){
// 值拼串
	var filedVal = '';	
	
  var flag = false;
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			flag=true;
			break;
		}
	}
	if(flag==false){
	    art.dialog({
		title: '系统提示',
	    content: '请至少选择一条记录!'
	    });
		return false;
	}
	//document.getElementById('chb_id').value = filedVal;
	$("#chb_id").val(filedVal);
	exportShowDIV('priceDiv','400px','320px','统一改价');

	
}
//批量修改价格
function updateprice(){
	var updatetype = $("input[name='updatetype']:checked").val();
	$("#hidden_updatetype").val(updatetype);  //修改类型
	if(updatetype==undefined){
	 alert('请选择改价方式');
	 return;
}
//updatetype: 0表示直接改价 1表示公式改价
if(updatetype=='0'){
$("#hidden_pricetype").val($(".pricetype").val());  //价格类型-成本价 销售价
var  nowprice = $("#nowprice").val();
	if(nowprice==''){
	  alert("修改价格不能为空！");
	  return;
	}else{
	$("#hidden_nowprice").val(nowprice);  //修改价格
	}
}else if(updatetype=='1'){
 $("#hidden_pricetype").val($(".pricetype2").val());  //价格类型-0成本价 1销售价
 $("#hidden_pricetype2").val($(".pricetype3").val());  //价格类型-0成本价 2销售价
 $("#hidden_mouth").val($(".mouth").val());  //0加1减2乘3除
 var  pricevalue = $("#pricevalue").val();
	if(pricevalue==''){
	  alert("操作价格不能为空！");
	  return;
	}else{
	$("#hidden_pricevalue").val(pricevalue);  //修改价格
	}
}
           document.forms["indexForm"].action='/admin_Goods_updateprice.action';
     document.forms["indexForm"].submit();
        return;
}
 //批量操作
  	function updateT(is_ip,field_name,actionName){
  		// 值拼串
	var filedVal = '';	
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			if(checks[i].value!=""){
				filedVal += checks[i].value+',';
			}
		}
	}
	// 删除最后一个字符
	filedVal = deleteLastChar(filedVal,",");
	if(filedVal==''){
		art.dialog({
			title: '系统提示',
		    content: '请至少选择一条记录!'
		});
		return false;
	}
	    var tip = '';
		if(is_ip=='4'){
			tip = '确认推荐吗?';
		}else if(is_ip=='0'){
			tip = '确认取消推荐吗?';
		}
		art.dialog({
		title: '系统信息提示',
		content:'<div class="decorate">'+tip+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
		    document.forms["indexForm"].action=actionName;
		    document.getElementById('chb_id').name = "chb_id";
 		   	document.getElementById('chb_id').value = filedVal;
		    document.forms["indexForm"].submit();
	        return false;
	    },
	   cancel: function () {
				return true;
		    }
	    });
		
}

//批量操作
  	function updateStateup(is_ip,field_name,actionName){
	var flag = false;
	var checks = document.getElementsByName(field_name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			flag=true;
			break;
		}
	}
	if(flag==false){
	    art.dialog({
		title: '系统提示',
	    content: '请至少选择一条记录!'
	    });
		return false;
	}
	if(flag==true){
	    var tip = '';
		if(is_ip=='1'){
			tip = '确认上架吗?';
		}
		art.dialog({
		title: '系统信息提示',
		content:'<div class="decorate">'+tip+'</div>',
		okValue: '确定',
		width: '238px',
		cancelValue: '取消',
	    ok: function () {
	        document.getElementById('admin_goods_is_ip').value = is_ip;
		    document.forms["indexForm"].action=actionName;
		    document.forms["indexForm"].submit();
	        return false;
	    },
	   cancel: function () {
				return true;
		    }
	    });
		
	}
}
		
		
		