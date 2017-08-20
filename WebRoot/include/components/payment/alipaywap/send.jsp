<%
/* *
 *功能：手机网页支付接入页
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.pay.alipaywap.config.*"%>
<%@ page import="com.rbt.pay.alipaywap.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="com.rbt.function.DetailOrderFuc"%>
<%@ page import="com.rbt.function.FundrechargeFuc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝手机网页支付</title>
	</head>
	<%
		//支付宝网关地址
		String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

		////////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////
		
		//wap授权接口名称。必填
		String service = AlipayConfig.wap_pay_service;
	    
	    //WAP支付请求接口名称。必填
	    String auth_service = AlipayConfig.auth_service;
		
		//请求参数格式。固定取值 xml。必填
		String format = AlipayConfig.format;
		
		//接口版本号。固定取值 2.0。必填
		String v = AlipayConfig.v;
		
		//商户签约的支付宝账号对应的支付宝唯一用户号。
		String partner = AlipayConfig.partner;
		
		//字符编码格式
		String input_charset = AlipayConfig.input_charset;
		
		//请求号。用于关联请求与响应， 防止请求重播。
		String req_id = UtilDate.getOrderNum();
		//必填，须保证每次请求都是唯一
		
		String sec_id = AlipayConfig.sign_type;
		
		String notify_url = AlipayConfig.notify_url;
		
		String call_back_url = AlipayConfig.call_back_url;
		
		String merchant_url = AlipayConfig.merchant_url;
		
		String seller_email=AlipayConfig.seller_email;
		
		//req_data详细信息

		String is_recharge = "",out_trade_no = "", total_fee = "", subject = "",pavaValue="";
		if(request.getParameter("is_recharge")!=null && !"".equals(request.getParameter("is_recharge"))){
		    is_recharge = request.getParameter("is_recharge").toString();
		}
		if(is_recharge.equals("0")){//会员手机充值
		   
	       //商户网站唯一订单号和商品名称
		   out_trade_no = UtilDate.getOrderNum();
	       subject = "EPF-O2O会员充值"+out_trade_no;
		   
		   //交易金额
			if (request.getParameter("recharge_amount") != null
					&& !"".equals(request.getParameter("recharge_amount"))) {
				total_fee = request.getParameter("recharge_amount").toString();
			}
			//异步通知
			notify_url =AlipayConfig.recharge_notify_url;
		    //同步通知 
		    call_back_url =AlipayConfig.recharge_back_url;
		    
		     //自定义参数
			if (request.getParameter("pa_MP") != null
					&& !"".equals(request.getParameter("pa_MP"))) {
				pavaValue = request.getParameter("pa_MP").toString();
			}
		     //先插入一条充值记录
		    FundrechargeFuc.createFundrecharge(out_trade_no,total_fee,pavaValue);
		    
		}else if(is_recharge.equals("1")){//会员手机付款
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
		    //同步通知 
		    call_back_url =AlipayConfig.call_back_url;
				
		}
		
		System.out.println("=============="+out_trade_no+"================="+total_fee);
		
		//请求业务参数详细
		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
		//必填
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", service);
		sParaTempToken.put("partner", partner);
		sParaTempToken.put("_input_charset", input_charset);
		sParaTempToken.put("sec_id", sec_id);
		sParaTempToken.put("format", format);
		sParaTempToken.put("v", v);
		sParaTempToken.put("req_id", req_id);
		sParaTempToken.put("req_data", req_dataToken);
		
		//建立请求
		String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
		//URLDECODE返回的信息
		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
		//获取token
		String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
		//System.out.println(request_token);
		
		////////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////
		
		//业务详细
		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
		//必填
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", auth_service);
		sParaTemp.put("partner", partner);
		sParaTemp.put("_input_charset", input_charset);
		sParaTemp.put("sec_id", sec_id);
		sParaTemp.put("format", format);
		sParaTemp.put("v", v);
		sParaTemp.put("req_data", req_data);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
