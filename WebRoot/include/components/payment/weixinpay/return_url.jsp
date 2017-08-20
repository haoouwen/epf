<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="com.rbt.common.util.XmlUtil"%>
<%@ page import="com.rbt.function.GoodsOrderFuc"%>
<%@ page import="com.rbt.function.PaymentFuc"%>
<%@ page import="com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReturnData"%>
<%@ page import="com.rbt.pay.wxpay.WXPay"%>
<%@ page import="com.rbt.function.FundrechargeFuc"%>
<%
		System.out.println("######################## 接受微信支付返回值开始########################");
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		//用户在商户appid下的唯一标识
		String openid="";
		//商户系统的订单号，与请求一致。
		String out_trade_no="";
		//业务结果 SUCCESS/FAIL
		String result_code="";
		//返回状态 SUCCESS/FAIL
		String return_code="";
		//签名
		String sign="";
		//支付完成时间
		String time_end="";
		//总金额
		String total_fee="";
		//交易类型 JSAPI、NATIVE、APP
		String trade_type="";
		//微信支付订单号
		String transaction_id="";
		String attach="";
		double recharge_amount=0;
		
		//支付类型：微信支付
	    String pay_id = PaymentFuc.getPaymentID("wxpay");
	
        Map gscanMap=new HashMap();
        gscanMap=XmlUtil.parseXml(request);
        if(gscanMap!=null){
            if(gscanMap.get("openid")!=null){
              openid=gscanMap.get("openid").toString();
            }
            if(gscanMap.get("out_trade_no")!=null){
              out_trade_no=gscanMap.get("out_trade_no").toString();
            }
            if(gscanMap.get("result_code")!=null){
              result_code=gscanMap.get("result_code").toString();
            }
            if(gscanMap.get("return_code")!=null){
              return_code=gscanMap.get("return_code").toString();
            }
            if(gscanMap.get("time_end")!=null){
              time_end=gscanMap.get("time_end").toString();
            }
            if(gscanMap.get("total_fee")!=null){
              total_fee=gscanMap.get("total_fee").toString();
              recharge_amount = Double.valueOf(total_fee)/100;
            }
            if(gscanMap.get("trade_type")!=null){
              trade_type=gscanMap.get("trade_type").toString();
            }
            if(gscanMap.get("transaction_id")!=null){
              transaction_id=gscanMap.get("transaction_id").toString();
            }
            if(gscanMap.get("attach")!=null){
              attach=gscanMap.get("attach").toString();
            }
            //先判断返回的状态不能为空
            if(return_code!=null&&!"".equals(return_code)&&result_code!=null&&!"".equals(result_code)){
              //判断返回的状态是不是显示 SUCCESS
              if(return_code.equals("SUCCESS")&&result_code.equals("SUCCESS")){
                  String attachs[]=attach.split("\\_");
                  String attach_str = attachs[1].toString();
                  if(attach_str.equals("1")){
                     if(GoodsOrderFuc.verifyOrderState(out_trade_no)){
			            GoodsOrderFuc.useWXOnlinePay(out_trade_no,recharge_amount+"",transaction_id,pay_id,"0");
			         }
                  }else if(attach_str.equals("0")){
                        FundrechargeFuc.insertChongZhi(pay_id,total_fee,String.valueOf(recharge_amount),transaction_id,attachs[0].toString());
                  }
                  
                   //回传信息给微信服务器
		           WXScanPayReturnData  wxScanPayResData=new WXScanPayReturnData();
				   wxScanPayResData.setReturn_code("SUCCESS");
				   wxScanPayResData.setReturn_msg("OK");
                   String getXmlString=WXPay.responeScanPayService(wxScanPayResData);
				   System.out.println("########################接受微信支付返回值结束########################return="+getXmlString);
			  }
            }
        }
		
        System.out.println("########################接受微信支付返回值结束########################");
%>
