//修改退款申请
	function updateRefund(obj){
		var id=(obj+"").trim();
		location.href = "/mall-orderbuy-refund-"+id+".html";
	}
	
//申请官方介入
	function getInvolved(obj){
		jConfirm('申请官方介入？', '系统提示',function(r){ 
		    if(r){ 
		    var dataUrl="/mall/order!getInvolved.action?order_id="+obj;
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
		        		jNotify("官方已介入！");
		             	return false;
		        	}else{
		        		jNotify("介入失败！");
		        	}
		        }
		  	});
		    
		    }
		  });  
	}
	function isReturn(obj){
		$("#is_return").val(obj);
	}
	function getAll(){
		selectAll('re_goods_id_str');
		getRenM();
	}
	
	function getRenM(){
		var filedVal = '';	
				var checks = document.getElementsByName("re_goods_id_str");
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
				$.ajax({
							          type: "post",
							          url: "/bmall_Refundapp_allRefund.action?orderde_id="+filedVal,
							          contentType:"application/x-www-form-urlencoded;charset=utf-8", 
							          datatype:"json",
							          async:false,
							          success: function(data){ 
								         if(data!=""){
								         		$("#all_refund").val(data);
								         		$("#sx").html(data);
								         		$("#re_goods_id_str").html(filedVal);
								         } 	
							          }	 
								});   
				
				
	}