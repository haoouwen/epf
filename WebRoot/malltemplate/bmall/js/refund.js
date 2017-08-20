$(document).ready(function(){
							var order_state="";
							order_state=$("#order_state").val();	
							var isget=$('input[name="is_get"]:checked').val();
							if(order_state=='3'){
								 $(".isget").show();
							}else if(order_state=='2'){
								$(".allr_efund").show();
								$(".reason").show();
								$(".explain").show();
								$(".refundmoney").show();
								$(".refundbut").show();
							}else if(order_state=='4'){
								if(isget==null||isget==""){
									$(".allr_efund").show();
									$(".reason").show();
									$(".explain").show();
									$(".refundmoney").show();
									$(".refundbut").show();
								}else{
										if(isget=='0'){
											$(".allr_efund").show();
											$(".isreturn").hide();
											$(".need_refund").hide();
											$(".proof").show();
											$(".refundmoney").show();
											$(".reason").show();
											$(".explain").show();
											$(".refundbut").show();
										}else if(isget=='1'){
											$(".isreturn").show();
											$(".allr_efund").hide();
											$(".refundmoney").show();
											$(".need_refund").show();
											$(".proof").show();
											$(".reason").show();
											$(".explain").show();
											$(".refundbut").show();
										}
								}
							}
  
					});

					
