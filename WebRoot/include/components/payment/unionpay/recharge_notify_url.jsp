<%
 /**
 *
 *作者：QJY 
 *功能：中国银联页面跳转异步通知
 *版本：3.2
 *日期：2015-09-29
 *说明：
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@ page import="com.rbt.pay.unionpay.util.SDKConstants"%>
<%@ page import="com.rbt.pay.unionpay.util.SDKUtil"%>
<%@page import="com.rbt.pay.unionpay.util.UnionpaySubmit"%>
<%@ page import="com.rbt.function.PaymentFuc"%>
<%@ page import="com.rbt.function.FundrechargeFuc"%>
<%
	    //System.out.println("FrontRcvResponse前台接收报文返回开始");

		request.setCharacterEncoding("UTF-8");
		String encoding = request.getParameter(SDKConstants.param_encoding);
		Map<String, String> respParam = UnionpaySubmit.getAllRequestParam(request);

		Map<String, String> valideData = null;
		//StringBuffer page = new StringBuffer();
		if (null != respParam && !respParam.isEmpty()) {
			Iterator<Entry<String, String>> it = respParam.entrySet()
					.iterator();
			valideData = new HashMap<String, String>(respParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes("UTF-8"), encoding);
				valideData.put(key, value);
			}
		}
		String order_id = "",total_amount="",queryId="",pay_id="",reqReserved="";
		if (!SDKUtil.validate(valideData, encoding)) {//校验签名
		    //TODO:
			System.out.println("验证签名结果[失败].");
		} else {
		    order_id = valideData.get("orderId"); //其他字段也可用类似方式获取
		    total_amount= valideData.get("txnAmt");//以分为单位
		    total_amount = String.valueOf(Double.valueOf(total_amount)/100);//转化成元为单位
		    
		    queryId = valideData.get("queryId"); //查询交易流水号
	        pay_id = PaymentFuc.getPaymentID("unionpay"); //支付类型：银联在线
	         
		    reqReserved = valideData.get("reqReserved");//
		    
		    String remark2s[]=reqReserved.split("\\_");
			String pay_type=remark2s[1].toString();
		    if(pay_type.equals("2")){//会员充值
		       FundrechargeFuc.insertChongZhi(pay_id,total_amount,order_id,queryId,remark2s[0].toString());
		    }
		    
		    out.print("ok");
		}
		
		//System.out.println("前台接收报文返回结束");
%>
