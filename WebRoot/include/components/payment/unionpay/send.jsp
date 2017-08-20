<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rbt.common.util.DateUtil"%>
<%@page import="com.rbt.pay.unionpay.util.UnionpaySubmit"%>
<%@page import="com.rbt.pay.unionpay.config.UnionpayConfig"%>
<%@ page import="com.rbt.function.DetailOrderFuc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>银联在线网关接口</title>
		<%	

		    String is_recharge="",order_id="",total_amount="",orderDesc="",frontUrl="",backUrl="",reqReserved="";
		    Integer txnAmt=0;
		    request.setCharacterEncoding("UTF-8");
		    if(request.getParameter("is_recharge")!=null && !"".equals(request.getParameter("is_recharge"))){
		         is_recharge = request.getParameter("is_recharge").toString();
		    }
		    
		    if(is_recharge.equals("0")){
		        //商户网站唯一订单号和商品名称
				order_id = DateUtil.getOrderNum();
		        //orderDesc = "EPF-O2O会员充值"+order_id;
		        //交易金额
				if (request.getParameter("total_amount") != null
						&& !"".equals(request.getParameter("total_amount"))) {
					total_amount = request.getParameter("total_amount").toString();
		            //银联在线 支付接口 交易金额是采用 分 为单位 
       	            double amount = Double.parseDouble(total_amount); 
       	            amount = amount*100;
		            txnAmt = (int)Math.round(amount);
				}
				 //异步通知
				frontUrl = UnionpayConfig.recharge_front_url;
			    //同步通知 
			    backUrl = UnionpayConfig.recharge_back_url;
			    
		    }else if(is_recharge.equals("1")){
		        if(request.getParameter("order_id_str")!=null && !"".equals(request.getParameter("order_id_str"))){
		         order_id = request.getParameter("order_id_str").toString();
		         //orderDesc = DetailOrderFuc.getDetailOrderForGoodsName(order_id);
			    }
			    if(request.getParameter("total_amount")!=null && !"".equals(request.getParameter("total_amount"))){
			         total_amount = request.getParameter("total_amount").toString();
			         //银联在线 支付接口 交易金额是采用 分 为单位 
		      	         double amount = Double.parseDouble(total_amount); 
		      	         amount = amount*100;
			         txnAmt = (int)Math.round(amount);
			    }
			   
			    //异步通知
				frontUrl = UnionpayConfig.frontUrl;
			    //同步通知 
			    backUrl = UnionpayConfig.backUrl;
			    
		    }
		    
		    
		    
		    if(request.getParameter("pa_MP")!=null && !"".equals(request.getParameter("pa_MP"))){
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
			// 交易类型 01-消费
			data.put("txnType", UnionpayConfig.txnType);
			// 交易子类型 01:自助消费 02:订购 03:分期付款
			data.put("txnSubType", UnionpayConfig.txnSubType);
			// 业务类型
			data.put("bizType", UnionpayConfig.bizType);
			// 渠道类型，07-PC，08-手机
			data.put("channelType", UnionpayConfig.channelType);
			// 前台通知地址 ，控件接入方式无作用
			data.put("frontUrl",frontUrl);
			// 后台通知地址
			data.put("backUrl",backUrl);
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
			// 交易币种
			data.put("currencyCode", UnionpayConfig.currencyCode);
			// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
			data.put("reqReserved", reqReserved);
			// 订单描述，可不上送，上送时控件中会显示该信息
			//data.put("orderDesc", orderDesc);

            //校验签名
			Map<String, String> submitFromData = UnionpaySubmit.signData(data);

			//创建表单
			String html = UnionpaySubmit.createHtml(submitFromData);
			out.println(html);
		%>
	</head>
	<body>
	</body>
</html>
