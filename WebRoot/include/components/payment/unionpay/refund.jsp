
<%/* *
 *功能：即时到账批量退款有密接口接入页
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
 */%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.util.DateUtil"%>
<%@page import="com.rbt.pay.unionpay.util.UnionpaySubmit"%>
<%@page import="com.rbt.pay.unionpay.config.UnionpayConfig"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>银联在线网关支付批量退款接口</title>
	</head>
	<%
		String order_id = "", origQryId = "", total_amount = "", backUrl = "", reqReserved = "";
		Integer txnAmt = 0;
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("order_id_str") != null
				&& !"".equals(request.getParameter("order_id_str"))) {
			order_id = request.getParameter("order_id_str").toString();
		}
		
		if (request.getParameter("origQryId") != null
				&& !"".equals(request.getParameter("origQryId"))) {
			origQryId = request.getParameter("origQryId").toString();
		}
		
		if (request.getParameter("total_amount") != null
				&& !"".equals(request.getParameter("total_amount"))) {
			total_amount = request.getParameter("total_amount").toString();
			//银联在线 支付接口 交易金额是采用 分 为单位 
			double amount = Double.parseDouble(total_amount);
			amount = amount * 100;
			txnAmt = (int) Math.round(amount);
		}

		if (request.getParameter("pa_MP") != null
				&& !"".equals(request.getParameter("pa_MP"))) {
			reqReserved = request.getParameter("pa_MP").toString();
		}
		//System.out.println("======================"+order_id+"======================"+total_amount+"================="+reqReserved);     
		// 组装请求报文
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", UnionpayConfig.version);
		// 字符集编码 默认"UTF-8"
		data.put("encoding", UnionpayConfig.encoding);
		// 签名方法 01 RSA
		data.put("signMethod", UnionpayConfig.signMethod);
		// 交易类型 01-消费 04-退货
		data.put("txnType", UnionpayConfig.refund_txnType);
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", UnionpayConfig.refund_txnSubType);
		// 业务类型
		data.put("bizType", UnionpayConfig.bizType);
		// 渠道类型，07-PC，08-手机
		data.put("channelType", UnionpayConfig.channelType);
		// 后台通知地址
		data.put("backUrl", UnionpayConfig.refund_notify_url);
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", UnionpayConfig.accessType);
		// 商户号码，请改成自己的商户号
		data.put("merId", UnionpayConfig.merId);
		// 订单发送时间，取系统时间
		data.put("txnTime", UnionpayConfig.txnTime);
		// 商户订单号，8-40位数字字母
		data.put("orderId", order_id);
		// 交易金额，单位分
		data.put("txnAmt", String.valueOf(txnAmt));
		//交易币种
		data.put("currencyCode", UnionpayConfig.currencyCode);
		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		data.put("reqReserved", reqReserved);
		//原始消费交易的流水號 queryId
		data.put("origQryId", origQryId);

		//校验签名
		Map<String, String> submitFromData = UnionpaySubmit.signData(data);

		//创建表单
		Map<String, String> resmap= UnionpaySubmit.submitUrl(submitFromData);
		if(resmap.get("respCode").equals("00")){
			//交易已受理，等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
			//TODO
		}else if(resmap.get("respCode").equals("03") || 
				 resmap.get("respCode").equals("04") ||
				 resmap.get("respCode").equals("05")){
			//后续需发起交易状态查询交易确定交易状态
			//TODO
		}else{
			//其他应答码为失败请排查原因
			//TODO
		}
	%>
	<body>
	</body>
</html>
