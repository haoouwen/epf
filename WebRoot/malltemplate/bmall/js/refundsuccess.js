var timerID = null;  
var send_timerID = null; 
var sure_timerID = null; 
var timerRunning = false;  
var send_timerRunning = false;  
var sure_timerRunning = false;  
var now="";
$(document).ready(function(){
								var dataUrl="/base!nowTime.action";
								$.ajax({
							        type: "post",
							        url:dataUrl,
							        datatype:"json",
							        async:false,
							        success: function(data){ 
							        	now=data;
							        }
							    });
								
								if(now==null||now==""){
									alert("服务器时间异常");
									return ;
								}
								
								//显示输入支付密码
								$("#zfmm").hide();
								$("#zfmmbut").click(function(){
									$("#zfmm").toggle("fast");
								});
								
								//卖家处理退款时间倒计时
								var addDate =$("#diftime").val();
								var refundDealtime =$("#refundDealtime").val();
								if(addDate!=null){
								//注意时间格式是：2013-07-05 11:41:30.910 
								var date = eval('new Date(' + addDate.replace(/\d+(?=-[^-]+$)/,    
					           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
					           now = eval('new Date(' + now.replace(/\d+(?=-[^-]+$)/,    
					           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
								now = new Date(Date.parse(now));
								addDate = new Date(Date.parse(date));
								if(timerRunning) { 
									stopclock(timerID);
								}
								var difftime = addDate.getTime()-now.getTime() +refundDealtime*24*3600000;
								//if(parseInt(difftime)<=0){
									//$("#subok").addClass("timeout");
								//}
								 //卖家处理退款时间倒计时
							  timerID= showtime(difftime,0);
								}
								
								//买家发货时间倒计时
								var addDate1 =$("#seller_date").val();
								var sendtime =$("#sendtime").val();
								if(addDate1!=null){
									//alert(addDate);
								//注意时间格式是：2013-07-05 11:41:30.910 
								var date1 = eval('new Date(' + addDate1.replace(/\d+(?=-[^-]+$)/,    
					           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
								addDate1 = new Date(Date.parse(date1));
								now = eval('new Date(' + now.replace(/\d+(?=-[^-]+$)/,    
					           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
								now = new Date(Date.parse(now));
								if(send_timerRunning) { 
									stopclock(send_timerID);
								}
								var difftime1 = addDate1.getTime()-now.getTime() +sendtime*24*3600000;
								//if(parseInt(difftime)<=0){
									//$("#subok").addClass("timeout");
								//}
								 //买家发货时间倒计时
							  send_timerID= showtime(difftime1,1);
								}
								
								//卖家确认收货时间倒计时
								var addDate2 =$("#send_time").val();
								var suretime =$("#suretime").val();
								if(addDate2!=null){
									//alert(addDate);
								//注意时间格式是：2013-07-05 11:41:30.910 
								var date2 = eval('new Date(' + addDate2.replace(/\d+(?=-[^-]+$)/,    
					           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
								addDate2 = new Date(Date.parse(date2));
								now = eval('new Date(' + now.replace(/\d+(?=-[^-]+$)/,    
					           function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
								now = new Date(Date.parse(now));
								if(sure_timerRunning) { 
									stopclock(sure_timerID);
								}
								var difftime2 = addDate2.getTime()-now.getTime() +suretime*24*3600000;
								//if(parseInt(difftime)<=0){
									//$("#subok").addClass("timeout");
								//}
								 //卖家确认收货时间倒计时
							  sure_timerID= showtime(difftime2,2);
								}
								
								/*物流--*/
							 	var showdata="";
								var jq_lg_data=$("#logistics_query_id").val();
								 if(jq_lg_data!=null&&jq_lg_data!=""&&jq_lg_data.indexOf("ERROR")<0){
									 showdata="<iframe name='kuaidi100' id='kuaidi100' src='"+jq_lg_data+"' width='550' height='255' marginwidth='0' marginheight='0'"
								          + " hspace='0' vspace='0' frameborder='0' scrolling='no'></iframe>";
								  }else{
								     showdata=" 物流公司未返回数据，请到 <font color='#0579C6'>"+$("#kuai_company_id").val()+" </font> 物流官网查询或联系其公示电话";
								  }
								$("#show_log").html(showdata);
								/*--物流*/
					});
					//倒计时开始
					function showtime(difftime,type){
					    var nd = 1000*24*60*60;
					    var nh = 1000*60*60;
					    var nm = 1000*60;
					    var ns = 1000;
					    var diffday = parseInt(difftime/nd);
					    var diffhour = parseInt(difftime%nd/nh);
					    var difmin = parseInt(difftime%nd%nh/nm);
					    var difsec = parseInt(difftime%nd%nh%nm/ns);
						difftime = difftime - 1000;
						if(type==0){
							timerID = setTimeout('showtime(' + difftime + ','+type+')',1000);  
							timerRunning = true;  
						}else if(type==1){
							send_timerID = setTimeout('showtime(' + difftime + ','+type+')',1000);  
							send_timerRunning= true;  
						}else if(type==2){
							sure_timerID = setTimeout('showtime(' + difftime + ','+type+')',1000);  
							sure_timerRunning= true;  
						}

						if(diffday <10 ){
							diffday = "0"+diffday;
						}
						if(diffhour <10 ){
							diffhour = "0"+diffhour;
						}
						if(difmin <10 ){
							difmin = "0"+difmin;
						}
						if(difsec <10 ){
							difsec = "0"+difsec;
						}
						
						if (diffday <1 && diffhour < 1 && diffhour < 1 && difsec < 1){   
							if(type==0){
							var timerID = null;  
								stopclock(timerID,type);
							}else if(type==1){
								stopclock(send_timerID,type);
							}else if(type==2){
								stopclock(sure_timerID,type);
							}
							
						}	
						if(difftime<0){
						    if(type==0){
							var timerID = null;  
								stopclock(timerID,type);
							}else if(type==1){
								stopclock(send_timerID,type);
							}else if(type==2){
								stopclock(sure_timerID,type);
							}
						    return;
						}  
						temp=diffday + '天'+ diffhour + '时' + difmin + '分' + difsec + '秒';
						if(type==0){
							$(".dealtime").html(temp);
						}else if(type==1){
							$(".sendtime").html(temp);
						}else if(type==2){
							$(".suretime").html(temp); 
						}
						if(type==0){
							return timerID;
						}else if(type==1){
							return send_timerID;
						}else if(type==2){
							return sure_timerID;
						}
						
					}
					
					//关闭定时器
					function stopclock(timerID,type) { 
						if(timerRunning)  
						clearTimeout(timerID);  
						if(type==0){
							timerRunning = false;  
						}else if(type==1){
							send_timerRunning= false;  
						}else if(type==2){
							sure_timerRunning= false;  
						}
						//$("#subok").addClass("timeout");
						 // alert(timerID);
					} 
					
					//修改退款申请
					function updateRefund(){
						var order_id=$("#g_order_id").val();
						location.href = "/mall-orderbuy-refund-"+order_id+".html";
					}
					//申请官方介入
					function getInvolved(){
						jConfirm('申请官方介入？', '系统提示',function(r){ 
						    if(r){ 
						    var order_id=$("#g_order_id").val();
						    var dataUrl="/mall/order!getInvolved.action?order_id="+order_id;
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
					
					//取消退款申请
					function cancelRefund(){
						 jConfirm('您确认取消退款？', '系统提示',function(r){ 
						    if(r){ 
						    var order_id=$("#g_order_id").val();
							location.href = "/mall-order-cancelRefund-"+order_id+".html";
						    }
						  });  
						
					}
					//弹出向卖家发货页面
					function goSend(){
						$("#goSend").show();
						//$("#goSend").popup({p_width:"716", p_height:"438", pop_title:"请填写退货物流及相关信息"});
					}
					//买家填写物流退货
					function toSendRefund(){
					    var send_mode=$("#send_mode").val();
					    var send_num=$("#send_num").val();
					    if(send_mode==null||send_mode==""){
					      jAlert("请选择物流公司!","系统提示");
					      return ;
					    }
					    if(send_num==null||send_num==""){
					      jAlert("请输入运单号码!","系统提示");
					      return ;
					    }
					     var uploadresult="";
						uploadresult=$("#uploadresult").val();
						if(uploadresult.indexOf(',')>-1){
							var uploadresults=uploadresult.split(',');
							if(uploadresults.length>=4){
								jAlert("图片最多3张！","系统提示");
								return ;
							}
						}
					 	jConfirm('您确定要寄出宝贝？', '系统提示',function(r){ 
					 		if(r){ 
					 			 $("#toSend").submit();
							 }
					    });  
					}
					
					//卖家没收到退货
					function noGetRefund(){
						jConfirm('您确认拒绝退款,并要求官方介入？', '系统提示',function(r){ 
					    if(r){ 
					      $("#getReturn").attr("action","/mall/order!noGetRefund.action").submit();
					    }
					  });  
					}	
					//同意退款提交执行的方法
					function submitRefundAgree(){
					  jConfirm('您确认同意退款？', '系统提示',function(r){ 
					    if(r){ 
					      $("#getReturn").attr("action","/mall/order!sellerAgreeIsneedReturn.action").submit();
					    }
					  });  
					}
