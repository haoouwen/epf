//初始化加载
$(document).ready(function(){
	//渲染
	addrHover();
	//绑定配送方式select事件
	$('.ship_fee').change(function(){
		initShip();
	}) 
});
//AJAX新增收货地址
function addAddr(){
	var consignee = $("#consignee").val();
	var address = $("#address").val();
	var post_code = $("#post_code").val();
	var area_code = $("#area_code").val();
	var phone = $("#phone").val();
	var code_phone = "";
	var cell_phone = $("#cell_phone").val();
	var area_attr = "";
	//验证收货人是否为空
	if(consignee == ""){
		jNotify("收货人姓名不能为空!"); 
		return;
	}else{
		var en_consignee = encodeURIComponent(encodeURIComponent(consignee));
	}
	//地区串拼接以及验证
	$("[name=area_attr]").each(function(){
		if(this.value != "" && this.value !="0"){
			if(area_attr != ""){
				area_attr = area_attr +","+this.value;
			}else{
				area_attr = this.value;
			}
		}else{
			jNotify("请选择地区!"); 
		}
	});
	//验证街道地址是否为空
	if(address == ""){
		jNotify("街道地址不能为空!"); 
		return;
	}else{
		var en_address = encodeURIComponent(encodeURIComponent(address));
	}
	//验证邮政编码是否为空
	if(post_code == ""){
		jNotify("邮政编码不能为空!"); 
		return;
	}
	//校验邮编格式
	var p1 = /^[1-9][0-9]{5}$/;
	if(!p1.test(post_code)){
	   alert("邮编格式有误!");
	   return false;
	} 
	//拼接固定电话
	code_phone = area_code+"-"+phone; 
	//验证电话和手机请至少填写一个
	if(area_code == "" && phone == "" && cell_phone ==""){
		jNotify("电话和手机请至少填写一个!"); 
		return;
	}
	//校验固话格式
	var p2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if(code_phone !="" && code_phone !="-" && !p2.test(code_phone)){
	   alert("固定电话格式有误!");
	   return false;
	} 
	//校验手机号码格式
	var p3 = /^1[3,4,5,8]\d{9}$/;
	if(cell_phone != "" && !p3.test(cell_phone)){
	   alert("手机格式有误!");
	   return false;
	} 
	
	var dataUrl="/mall/order!addAddr.action?consignee="+en_consignee+"&address="+en_address+"&post_code="+post_code+"&phone="+code_phone+"&cell_phone="+cell_phone+"&area_attr="+area_attr;
	 $.ajax({  	 
        type: "post",    	     
        url: dataUrl,       
        datatype:"json",
        async:false,
        success: function(data){
        	if(data=='0'){
        		jNotify("请先登录！");
             	return false;
        	}else if(data=='1'){
        		jNotify("收货地址已达到最大保存个数！");
             	return false;
        	}else{
        		jNotify("添加成功！");
        		//去除选中样式	
        		$(".useAdd").each(function(){
        			$(this).removeClass("useAddclass");
        		})
        		$("#addressDiv").append(data);
        		//渲染
				addrHover();
				//隐藏地址输入框
				$(".addAddress").toggle("fast");
        	}
        }
  	});
}
//AJAX删除收货地址
function delAddr(obj){
	var addr_id = $(obj).parent().parent().attr("id");
	jConfirm('确定要删除该地址？', '系统提示',function(r){
         if(r){ 
			var dataUrl="/mall/order!delAddr.action?addr_id="+addr_id;
			 $.ajax({  	 
		        type: "post",    	     
		        url: dataUrl,       
		        datatype:"json",
		        async:false,
		        success: function(data){
		        	if(data=='0'){
		        		jNotify("请先登录！");
		        	}else if(data=='1'){
		        		$("#"+addr_id).remove();
		        		jNotify("删除成功！");
		        	}else{
		        		jNotify("删除失败！");
		        	}
		        }
		  	});
		 }
    });  
}
//鼠标滑过地址触发事件
function addrHover(){
	$(".useAdd").hover(
	  function () {
	  	 var addr_id = $(this).children("input").val();
	     var position =$(this).css("position");
	     var width =$(this).css("width");
	     var height =$(this).css("height");
	     var left = parseInt(width);
	     var top = parseInt(height)-10;
	     $(this).append("<span class='remove'><img onclick='delAddr(this);' src='/include/common/images/delete.png' alt='删除' title='删除'/></span>");
	     $(this).find(".remove").css({
	     	"position":'absolute',
	     	"left":left,
	     	"top":top
	     });
	  },function () {
	     $(this).find(".remove").remove();
	  });
}
//选择收货地址
function chooseAddr(obj){
	//去除选中样式	
   	$(".useAdd").each(function(){
   		$(this).removeClass("useAddclass");
   	})
   	//当前地址添加选中样式
   	$(obj).addClass("useAddclass");
}
//提交实物订单
function submitOrder(){
   	//将选中的收货地址标识放入隐藏域
   	var addr_id = $(".useAddclass").attr("id");
   	if(addr_id == "" || addr_id == undefined){
   		jNotify("请填写或选择收货地址！");
        return false;
   	}
   	$("#addr_id").val(addr_id);
   	var goods_id_str = "";
   	$("[name=goods_id_str]").each(function(){
		if(this.value != "" && this.value !=""){
			if(goods_id_str != ""){
				goods_id_str = goods_id_str +","+this.value;
			}else{
				goods_id_str = this.value;
			}
		}
	});
   	$("#subOrder").submit();
   	//清空购物车
	EmptyCart(goods_id_str);
}









