<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="com.rbt.common.util.DateUtil"%>
<%@page import="com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReqData"%>
<%@page import="com.rbt.pay.wxpay.WXPay"%>
<%@page import="com.rbt.pay.wxpay.common.XMLParser"%>
<%@ page import="com.rbt.pay.wxpay.common.Configure"%>
<%@ page import="com.rbt.function.DetailOrderFuc"%>
<%@ page import="com.rbt.pay.wxpay.business.WXScanPayBusiness"%>
<%

		String body="";
		String attach="";
		String outTradeNo="";
		//终端设备号
		String deviceInfo=Configure.deviceInfo;
		//活动访问者IP
		String spBillCreateIP=Configure.spBillCreateIP;
		String timeStart=""; 
		String timeExpire="";
		String goodsTag=""; 
		//默认人民币：CNY
		String fee_type=Configure.fee_type; 
		String detail="";
		//接收微信支付异步通知回调地址
		String notify_url=Configure.notify_url; 
		//取值如下：JSAPI，NATIVE，APP
		String trade_type=Configure.trade_type; 
		String product_id=""; 
		String openid="";
		int totalFee=0;
		
		String is_recharge ="",total_amount="",wxpay_scan="";
		if(request.getParameter("is_recharge") != null && !"".equals(request.getParameter("is_recharge"))){
		    is_recharge = request.getParameter("is_recharge").toString();
		}
		
		if(request.getParameter("order_id_str") != null && !"".equals(request.getParameter("order_id_str"))){
		     outTradeNo = request.getParameter("order_id_str").toString();
		     HashMap smap = DetailOrderFuc.getGoodsNameBody(outTradeNo);
		     if(smap!=null && smap.get("product_id")!=null && smap.get("body")!=null){
		        product_id = smap.get("product_id").toString();
		        body = smap.get("body").toString();
		     }
		}
		
		if(request.getParameter("total_amount") != null && !"".equals(request.getParameter("total_amount"))){
		     total_amount = request.getParameter("total_amount").toString();
		     //由于以分为单位 所以要乘以100
       	     double amount = Double.parseDouble(total_amount); 
       	     amount = amount*100;
       	     totalFee = (int)Math.round(amount);
		}
		
		if(request.getParameter("pa_MP") != null && !"".equals(request.getParameter("pa_MP"))){
		     attach = request.getParameter("pa_MP").toString();
		}
		
		if(is_recharge.equals("1")){//商品消费
			
			//判断订单号不为空
			if(outTradeNo!=null&&!"".equals(outTradeNo)){
		      //判断订单的状态是不是等于1， 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
					    

			}
			
		}else if(is_recharge.equals("0")){//会员充值
			
			//充值订单号
			outTradeNo = DateUtil.getOrderNum();
			body = "EPF-O2O会员充值"+outTradeNo;

		}
		
		WXScanPayReqData  wxScanPayReqData=new WXScanPayReqData( body, attach, outTradeNo,
       		 totalFee, deviceInfo, spBillCreateIP, timeStart, timeExpire, goodsTag
       		, fee_type, detail, notify_url, trade_type, product_id, openid);
	   	String getXmlString=WXPay.reqScanPayService(wxScanPayReqData);
	   	System.out.println(getXmlString);
	   	Map xmlMap=new HashMap();
	   	xmlMap=XMLParser.getMapFromXML(getXmlString);
	   	if(xmlMap!=null){
	   		//先判断返回的标志是否成功
	   		if(xmlMap.get("return_code")!=null&&"SUCCESS".equals(xmlMap.get("return_code"))){
	   			//判断返回值的appid和mch_id是否和请求的一样
	   			if(xmlMap.get("appid")!=null&&xmlMap.get("mch_id")!=null){
	   				//判断返回值的appid和mch_id是否和请求的一样
	   				if(Configure.getAppid().equals(xmlMap.get("appid").toString())&&Configure.getMchid().equals(xmlMap.get("mch_id").toString())){
	   					//判断返回结果的值 是不是成功的，如果是成功的话，获取返回的的url生成二维码
	   					if(xmlMap.get("result_code")!=null&&xmlMap.get("code_url")!=null&&"SUCCESS".equals(xmlMap.get("result_code"))){
	   						wxpay_scan=xmlMap.get("code_url").toString();
	   						//跳转到生成二维码页面
	   					}else {
	   						//输出失败原因，跳转到失败提示页面
			        			System.out.println(xmlMap.get("err_code_des"));
							}
	   				}else{
	   					//输出失败原因，跳转到失败提示页面
		        			System.out.println(xmlMap.get("return_msg"));
	   				}
	   			}else {
	   				//输出失败原因，跳转到失败提示页面
	       			System.out.println(xmlMap.get("return_msg"));
					}
	   		}else{
	   			//输出失败原因，跳转到失败提示页面
	   			System.out.println(xmlMap.get("return_msg"));
	   		}
	   	}
	   	
	   	//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("wxpay_scan", wxpay_scan);
        sParaTemp.put("order_id_str", outTradeNo);
        sParaTemp.put("sub_total_price", total_amount);
		sParaTemp.put("is_recharge", is_recharge);
		//建立请求
		String sHtmlText = WXScanPayBusiness.buildRequest(sParaTemp,"post","确认");
		out.println(sHtmlText);
	   	
		
%>
