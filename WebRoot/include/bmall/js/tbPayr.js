	$(document).ready(function(){ 
		var rate;
		//页面输出运费
		$(".sendMore").change(function() {  
			countFare();
        });
		//初始货计算运费、总运费、总额
       countFare();
	});
	
	function countFare(){
		var allfare=0.0;
		var payTotal=0.0;
		var allM=0.00;
		$(".sendMore").each(function() {  
			rate=$(this).val().split(',');
			var fare=parseInt(rate[0]);
			$(this).parent(".paytid").find(".onefare").html(fare);
			allfare+=parseInt(rate[0]);
        }); 
        	payTotal=$("#payTotalMoney").val();
		    allM=parseFloat(allfare)+parseFloat(payTotal);
	        $(".allfare").html(allfare);
	        $(".allpay").html(allM);
	}
	
	
	function addMoreFare(){
			var fare=0.0;
			var send_code="";
			var flag=0;
			$(".sendMore").each(function() {  
	            if($(this).val()==0||$(this).val()==''){
				flag=2;
				}else{
					rate=$(this).val().split(',');
					fare+=parseInt(rate[0]);
					send_code+=rate[1]+",";
				}
	         }); 
	         if($("#oldpasswd").val()==""){
				jAlert("请输入支付密码!","系统提示");
				return false;
			}
			if(flag==2){
				jAlert("您还有订单未选择物流!","系统提示");
				return false;
			}
			 $("#smodecodes").val(send_code);//要保存的物流英文名串
	         return true;
		} 
	
	