
<%
	/* *
	 *功能：纯网关接口接入页
	 *版本：3.3
	 *日期：2012-08-14
	 *说明：
	 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

	 *************************注意*****************
	 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
	 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
	 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
	 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
	 *如果不想使用扩展功能请把扩展功能参数赋空值。
	 **********************************************
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.rbt.pay.alipaybank.config.*"%>
<%@ page import="com.rbt.pay.alipaybank.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.rbt.function.DetailOrderFuc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝纯网关接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////
		request.setCharacterEncoding("UTF-8");

		/**********支付宝请求基本参数开始******************/
		//接口名称
		String service = AlipayConfig.pay_service;
		//合作者身份ID
		String partner = AlipayConfig.partner;
		//参数编码字符集
		String input_charset = AlipayConfig.input_charset;
		//服务器异步通知页面路径
		String notify_url = AlipayConfig.notify_url;
		//页面跳转同步通知页面路径
		String return_url = AlipayConfig.return_url;
		//支付类型
		String payment_type = AlipayConfig.payment_type;
		//支付方式
		String paymethod = AlipayConfig.paymethod;
		/**********支付宝请求基本参数结束******************/

		String out_trade_no = "", total_fee = "", subject = "", extra_common_param = "",defaultbank="";
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
		//公用回传参数:如果用户请求时传递了该参数，则返回给商户时会回传该参数。系统需要的参数
		if (request.getParameter("pa_MP") != null
				&& !"".equals(request.getParameter("pa_MP"))) {
			extra_common_param = request.getParameter("pa_MP").toString();
		}

		//必填
		//默认网银
		if(request.getParameter("defaultbank") != null		
	     		&& !"".equals(request.getParameter("defaultbank"))){
               defaultbank = request.getParameter("defaultbank").toString();
		}
		
		//必填，银行简码请参考接口技术文档

		//卖家支付宝用户号
		String seller_id =AlipayConfig.seller_id;
			
		//卖家支付宝账号
		String seller_email =AlipayConfig.seller_email;
        
        //订单描述:对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给 body。
		String body = DetailOrderFuc.getDetailOrderForGoodsNameBody(out_trade_no);
			
		//商品展示地址:需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
		String show_url =AlipayConfig.show_url;
		
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";

		//////////////////////////////////////////////////////////////////////////////////

		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", service);
		sParaTemp.put("partner", partner);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("_input_charset", input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("paymethod", paymethod);
		sParaTemp.put("defaultbank", defaultbank);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		sParaTemp.put("extra_common_param", extra_common_param);

		//建立请求
		String sHtmlText = AlipaySubmit
				.buildRequest(sParaTemp, "post", "确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
