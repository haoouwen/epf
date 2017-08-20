package com.rbt.pay.wxpay.service;

import com.rbt.pay.wxpay.common.*;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanAppPayReqData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReqData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayResData;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReturnData;
/**
 * User: rizenguo 扫描支付
 * Date: 2014/10/29
 * Time: 16:03
 */
public class WXScanPayService extends BaseService{

    public WXScanPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.Scan_PAY_API);
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(WXScanPayReqData wxScanPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(wxScanPayReqData);

        return responseString;
    }
    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String returnSuccessInfo(WXScanPayReturnData wxScanPayReturnData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(wxScanPayReturnData);

        return responseString;
    }
    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String requestAPP(WXScanAppPayReqData wxScanAppPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(wxScanAppPayReqData);

        return responseString;
    }
    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String returnAPPSuccessInfo(WXScanPayReturnData wxScanPayReturnData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(wxScanPayReturnData);

        return responseString;
    }
    
}
