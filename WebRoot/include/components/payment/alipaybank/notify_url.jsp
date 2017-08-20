<%
/* *
 功能：支付宝服务器异步通知页面
 版本：3.3
 日期：2012-08-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
 该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.rbt.pay.alipaybank.util.*"%>
<%@ page import="com.rbt.function.GoodsOrderFuc"%>
<%@ page import="com.rbt.function.PaymentFuc"%>
<%@ page import="com.rbt.function.FundrechargeFuc"%>
<%
	//获取支付宝GET过来反馈信息:所有通知数据
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
		params.put(name, valueStr);
	}
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	
	//商户订单号：对应商户网站的订单系统中的唯一订单号， 非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数，原样返回。
	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
    
    //付款金额
    String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");

	//支付宝交易号：该交易在支付宝系统中的交易流水号。最长 64 位。
	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//交易状态：交易目前所处的状态。成功状态的值只有两个：TRADE_FINISHED（普通即时到账的交易成功状态）,TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的交易成功状态）
	String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
    
    //支付宝买家的账号
    String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"),"UTF-8");
	
	//系统需用到的公共参数
	String extra_common_param = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");
	
	//支付类型：支付宝
	String pay_id = PaymentFuc.getPaymentID("alipay");

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

	if(AlipayNotify.verify(params)){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		
		if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				//判断该笔订单是否在商户网站中已经做过处理
				
				   //支付成功，应该把数据库中订单支付状态改为已支付，否则容易出现"无限刷点卡"的BUG
           	 String remark2s[]=extra_common_param.split("\\_");
			 String pay_type=remark2s[1].toString();
		    if(pay_type.equals("1")){//订单付款
		       if(GoodsOrderFuc.verifyOrderState(out_trade_no)){
			       GoodsOrderFuc.useOnlinePay(out_trade_no,total_fee,trade_no,pay_id,remark2s[0].toString());
			   }
		    }else if(pay_type.equals("2")){//会员充值
		       FundrechargeFuc.insertChongZhi(pay_id,total_fee,out_trade_no,buyer_email,remark2s[0].toString());
		    }
			//注意：
			//该种交易状态只在两种情况下出现
			//1、开通了普通即时到账，买家付款成功后。
			//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
		}

		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			
		out.println("success");	//请不要修改或删除

		//////////////////////////////////////////////////////////////////////////////////////////
	}else{//验证失败
		out.println("fail");
	}
%>