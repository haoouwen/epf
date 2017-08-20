package com.rbt.pay.wxpay;

import java.util.Map;

import com.rbt.pay.wxpay.business.RefundBusiness;
import com.rbt.pay.wxpay.business.WXAPPPayBusiness;
import com.rbt.pay.wxpay.business.WXScanPayBusiness;
import com.rbt.pay.wxpay.common.Configure;
import com.rbt.pay.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.rbt.pay.wxpay.protocol.refund_protocol.RefundAppReqData;
import com.rbt.pay.wxpay.protocol.refund_protocol.RefundReqData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanAppPayReqData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReqData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReturnData;
import com.rbt.pay.wxpay.service.*;
/**
 * SDK总入口
 */
public class WXPay {

    /**
     * 初始化SDK依赖的几个关键配置
     * @param key 签名算法需要用到的秘钥
     * @param appID 公众账号ID
     * @param mchID 商户ID
     * @param sdbMchID 子商户ID，受理模式必填
     * @param certLocalPath HTTP证书在服务器中的路径，用来加载证书用
     * @param certPassword HTTP证书的密码，默认等于MCHID
     */
    public static void initSDKConfiguration(String key,String appID,String mchID,String sdbMchID,String certLocalPath,String certPassword){
        Configure.setKey(key);
        Configure.setAppID(appID);
        Configure.setMchID(mchID);
        Configure.setSubMchID(sdbMchID);
        Configure.setCertLocalPath(certLocalPath);
        Configure.setCertPassword(certPassword);
    }
    /**
     * 被扫描请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String requestScanPayService(ScanPayReqData scanPayReqData) throws Exception{
        return new ScanPayService().request(scanPayReqData);
    }
    /**
     * 扫描请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String reqScanPayService(WXScanPayReqData wxScanPayReqData) throws Exception{
        return new WXScanPayBusiness().run(wxScanPayReqData);
    }
    /**
     * 扫描请求支付服务-接受返回结果之后的回传信息
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String responeScanPayService(WXScanPayReturnData wxScanPayReturnData) throws Exception{
        return new WXScanPayService().returnSuccessInfo(wxScanPayReturnData);
    }
    /**
     * 调用退款业务逻辑 PC端
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 业务逻辑可能走到的结果分支，需要商户处理
     * @throws Exception
     */
    public static Map doRefundBusiness(RefundReqData refundReqData) throws Exception {
    	return new RefundBusiness().run(refundReqData,"0");
    }
    /**
     * 调用退款业务逻辑 APP端
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 业务逻辑可能走到的结果分支，需要商户处理
     * @throws Exception
     */
    public static Map doRefundAppBusiness(RefundAppReqData refundAppReqData)throws Exception{
    	return new RefundBusiness().run(refundAppReqData,"1");
    }
    
    /**
     * 扫描请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String reqAPPPayService(WXScanPayReqData wxScanPayReqData) throws Exception{
        return new WXScanPayBusiness().run(wxScanPayReqData);
    }
    /**
     * APP支付请求
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String reqAppPayService(WXScanAppPayReqData wxScanAppPayReqData) throws Exception{
        return new WXAPPPayBusiness().run(wxScanAppPayReqData);
    }
    
    /**
     * 扫描请求支付服务-接受返回结果之后的回传信息
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String responeAppScanPayService(WXScanPayReturnData wxScanPayReturnData) throws Exception{
        return new WXScanPayService().returnAPPSuccessInfo(wxScanPayReturnData);
    }
    

}
