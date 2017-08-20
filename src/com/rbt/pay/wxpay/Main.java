package com.rbt.pay.wxpay;
import java.io.UnsupportedEncodingException;

import com.rbt.pay.wxpay.common.Configure;
import com.rbt.pay.wxpay.common.Util;
import com.rbt.pay.wxpay.protocol.refund_protocol.RefundReqData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReqData;
public class Main {
	
	public static String urlEncodeUTF8(String source){
        String result = source;
        try {
                result = java.net.URLEncoder.encode(source,"utf-8");
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }
        return result;
}
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {

        try {

        	
        	//RefundReqData wxScanPayRefundData=new RefundReqData
           // ("1010160023201504110057438357","2015041115311641557257672","WEB","t35df215041114524212231255164",10,1,Configure.getMchid(),"CNY");
        	//System.out.println(WXPay.doRefundBusiness(wxScanPayRefundData));
        	//WXScanPayReqData  wxScanPayReqData=new WXScanPayReqData("test_bod速度y","20150241111082191358418523","401504124323301231358418524221",1,"1","127.0.0.1","",
        	//		"","","CNY","test","http://www.epff.cc/mall/pay!wxScanPayData.action","NATIVE","111","");
       	   // String sString=WXPay.reqScanPayService(wxScanPayReqData);
        	//System.out.println(sString);
//            // InputStream is =  Util.getStringStream(sString);
//             //parseXml(is);
//            // readStringXml(sString);
//        	XMLParser.getMapFromXML(sString);
//        	
//        	WXScanPayResData  wxScanPayResData=new WXScanPayResData();
//    		wxScanPayResData.setReturn_code("SUCCESS");
//    		wxScanPayResData.setReturn_msg("OK");
//    	   	String getXmlString=WXPay.responeScanPayService(wxScanPayResData);
//    	   	System.out.println(getXmlString);
        	
        	
//        	Map map=new HashMap ();
//        	map.put("body", "测试支付");
//        	map.put("attach", "");
//        	map.put("outTradeNo", "dh7788878787");
//        	map.put("totalFee", 1);
//        	map.put("deviceInfo", "WEB");
//        	map.put("spBillCreateIP", "192.168.10.120");
//        	map.put("timeStart", "20150409141010");
//        	map.put("timeExpire", "20150410141010");
//        	map.put("goodsTag", "");
//        	map.put("detail", "");
//        	map.put("fee_type", "CNY");
//        	map.put("notify_url", "http://www.epff.cn/WebRoot/include/components/payment/weixinpay/notify_url.jsp");
//        	map.put("trade_type", "NATIVE");
//        	map.put("product_id", "12456789");
//        	map.put("openid", "");
//        	WXScanPayReqData  wxScanPayReqData=new WXScanPayReqData(map);
//        	System.out.println( WXPay.reqScanPayService(wxScanPayReqData));
//        	
        	
        	
            //--------------------------------------------------------------------
            //温馨提示，第一次使用该SDK时请到com.rbt.pay.wxpay.common.Configure类里面进行配置
            //--------------------------------------------------------------------



            //--------------------------------------------------------------------
            //PART One:基础组件测试
            //--------------------------------------------------------------------

            //1）https请求可用性测试
            //HTTPSPostRquestWithCert.test();

            //2）测试项目用到的XStream组件，本项目利用这个组件将Java对象转换成XML数据Post给API
            //XStreamTest.test();


            //--------------------------------------------------------------------
            //PART Two:基础服务测试
            //--------------------------------------------------------------------

            //1）测试被扫支付API
            //PayServiceTest.test();

            //2）测试被扫订单查询API
            //PayQueryServiceTest.test();

            //3）测试撤销API
            //温馨提示，测试支付API成功扣到钱之后，可以通过调用PayQueryServiceTest.test()，将支付成功返回的transaction_id和out_trade_no数据贴进去，完成撤销工作，把钱退回来 ^_^v
            //ReverseServiceTest.test();

            //4）测试退款申请API
            //RefundServiceTest.test();

            //5）测试退款查询API
            //RefundQueryServiceTest.test();

            //6）测试对账单API
            //DownloadBillServiceTest.test();


            //本地通过xml进行API数据模拟的时候，先按需手动修改xml各个节点的值，然后通过以下方法对这个新的xml数据进行签名得到一串合法的签名，最后把这串签名放到这个xml里面的sign字段里，这样进行模拟的时候就可以通过签名验证了
           // Util.log(Signature.getSignFromResponseString(Util.getLocalXMLString("/test/com/tencent/business/refundqueryserviceresponsedata/refundquerysuccess2.xml")));

           // Util.log(new Date().getTime());
           // Util.log(System.currentTimeMillis());

        } catch (Exception e){
            Util.log(e.getMessage());
        }

    }

}
