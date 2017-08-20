<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rbt.pay.alipay.util.*"%>
<%@ page import="com.rbt.pay.alipay.config.AlipayConfig"%>
<%@ page import="com.rbt.function.DetailOrderFuc"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到帐付款</title>
        <%
			request.setCharacterEncoding("UTF-8");
		
			/**********支付宝请求基本参数开始******************/
			//
			String service =AlipayConfig.pay_service;
			//
			String partner =AlipayConfig.partner;
			//
			String input_charset =AlipayConfig.input_charset;
			//
			String notify_url ="";
			//
			String return_url ="";
			/**********支付宝请求基本参数结束******************/
		
			/**********支付宝请求业务参数开始******************/
		
			String is_recharge = "",out_trade_no = "", total_fee = "", subject = "",extra_common_param="";
			
			if(request.getParameter("is_recharge") != null
					&& !"".equals(request.getParameter("is_recharge"))){
			   is_recharge = request.getParameter("is_recharge").toString();
			}
			if(is_recharge.equals("0")){
			    
			    //商户网站唯一订单号和商品名称
				out_trade_no = UtilDate.getOrderNum();
		        subject = "EPF-O2O会员充值"+out_trade_no;
				//交易金额
				if (request.getParameter("total_amount") != null
						&& !"".equals(request.getParameter("total_amount"))) {
					total_fee = request.getParameter("total_amount").toString();
				}
				//异步通知
				notify_url =AlipayConfig.recharge_notify_url;
			    //同步通知 
			    return_url =AlipayConfig.recharge_return_url;
				
			}else if(is_recharge.equals("1")){
			    //商户网站唯一订单号和商品名称
				if (request.getParameter("order_id_str") != null
						&& !"".equals(request.getParameter("order_id_str"))) {
					out_trade_no = request.getParameter("order_id_str").toString();
					subject = DetailOrderFuc
							.getDetailOrderForGoodsName(out_trade_no);
				}
				//交易金额
				if (request.getParameter("total_amount") != null
						&& !"".equals(request.getParameter("total_amount"))) {
					total_fee = request.getParameter("total_amount").toString();
				}
				//异步通知
				notify_url =AlipayConfig.notify_url;
			    //同步通知页面
			    return_url =AlipayConfig.return_url;
				
			}
			
			//公用回传参数:如果用户请求时传递了该参数，则返回给商户时会回传该参数。系统需要的参数
			if(request.getParameter("pa_MP")!= null && !"".equals(request.getParameter("pa_MP"))){
	            extra_common_param =request.getParameter("pa_MP").toString();
	        }
			
			//支付类型
			String payment_type =AlipayConfig.payment_type;
			//卖家支付宝用户号
			String seller_id =AlipayConfig.seller_id;
			
			//卖家支付宝账号
			String seller_email =AlipayConfig.seller_email;
		    
		    //防钓鱼时间戳
			String anti_phishing_key = "";
			//若要使用请调用类文件submit中的query_timestamp函数
		
			//客户端的IP地址
			String exter_invoke_ip = "";
			//非局域网的外网IP地址，如：221.0.0.1
			
			//订单描述:对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给 body。
			String body = DetailOrderFuc.getDetailOrderForGoodsNameBody(out_trade_no);
			
			//商品展示地址:需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
			String show_url =AlipayConfig.show_url;
		    
			/**********支付宝请求业务参数结束******************/
			
			//////////////////////////////////////////////////////////////////////////////////
				
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", service);
	        sParaTemp.put("partner", partner);
	        sParaTemp.put("_input_charset", input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("seller_id", seller_id);
			sParaTemp.put("seller_email", seller_email);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			sParaTemp.put("extra_common_param", extra_common_param);
			
			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
			out.println(sHtmlText);
%>
	</head>
	<body>
	</body>
</html>
