package com.rbt.pay.wxpay.business;

import com.rbt.pay.wxpay.common.Log;
import com.rbt.pay.wxpay.common.Signature;
import com.rbt.pay.wxpay.common.Util;
import com.rbt.pay.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.rbt.pay.wxpay.protocol.pay_protocol.ScanPayResData;
import com.rbt.pay.wxpay.service.ScanPayService;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

/**
 * User: rizenguo
 * Date: 2014/12/1
 * Time: 17:05
 */
public class ScanPayBusiness {

    public ScanPayBusiness() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        scanPayService = new ScanPayService();
    }

    public interface ResultListener {

        //API返回ReturnCode不合法，支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问
        void onFailByReturnCodeError(ScanPayResData scanPayResData);

        //API返回ReturnCode为FAIL，支付API系统返回失败，请检测Post给API的数据是否规范合法
        void onFailByReturnCodeFail(ScanPayResData scanPayResData);

        //支付请求API返回的数据签名验证失败，有可能数据被篡改了
        void onFailBySignInvalid(ScanPayResData scanPayResData);


        //用户用来支付的二维码已经过期，提示收银员重新扫一下用户微信“刷卡”里面的二维码
        void onFailByAuthCodeExpire(ScanPayResData scanPayResData);

        //授权码无效，提示用户刷新一维码/二维码，之后重新扫码支付"
        void onFailByAuthCodeInvalid(ScanPayResData scanPayResData);

        //用户余额不足，换其他卡支付或是用现金支付
        void onFailByMoneyNotEnough(ScanPayResData scanPayResData);

        //支付失败
        void onFail(ScanPayResData scanPayResData);

        //支付成功
        void onSuccess(ScanPayResData scanPayResData);

    }

    //打log用
    private static Log log = new Log(LoggerFactory.getLogger(ScanPayBusiness.class));

    //每次调用订单查询API时的等待时间，因为当出现支付失败的时候，如果马上发起查询不一定就能查到结果，所以这里建议先等待一定时间再发起查询

    private int waitingTimeBeforePayQueryServiceInvoked = 5000;

    //循环调用订单查询API的次数
    private int payQueryLoopInvokedCount = 3;

    //每次调用撤销API的等待时间
    private int waitingTimeBeforeReverseServiceInvoked = 5000;

    private ScanPayService scanPayService;


    /**
     * 直接执行被扫支付业务逻辑（包含最佳实践流程）
     *
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
     * @throws Exception
     */
    public void run(ScanPayReqData scanPayReqData, ResultListener resultListener) throws Exception {

        //--------------------------------------------------------------------
        //构造请求“被扫支付API”所需要提交的数据
        //--------------------------------------------------------------------

        String outTradeNo = scanPayReqData.getOut_trade_no();

        //接受API返回
        String payServiceResponseString;

        long costTimeStart = System.currentTimeMillis();


        log.i("支付API返回的数据如下：");
        payServiceResponseString = scanPayService.request(scanPayReqData);

        long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        //打印回包数据
        log.i(payServiceResponseString);

        //将从API返回的XML数据映射到Java对象
        ScanPayResData scanPayResData = (ScanPayResData) Util.getObjectFromXML(payServiceResponseString, ScanPayResData.class);

     
        if (scanPayResData == null || scanPayResData.getReturn_code() == null) {
            log.e("【支付失败】支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            resultListener.onFailByReturnCodeError(scanPayResData);
            return;
        }

        if (scanPayResData.getReturn_code().equals("FAIL")) {
            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            log.e("【支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
            resultListener.onFailByReturnCodeFail(scanPayResData);
            return;
        } else {
            log.i("支付API系统成功返回数据");
            //--------------------------------------------------------------------
            //收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
            //--------------------------------------------------------------------
            if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString)) {
                log.e("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
                resultListener.onFailBySignInvalid(scanPayResData);
                return;
            }

            //获取错误码
            String errorCode = scanPayResData.getErr_code();
            //获取错误描述
            String errorCodeDes = scanPayResData.getErr_code_des();

            if (scanPayResData.getResult_code().equals("SUCCESS")) {

                //--------------------------------------------------------------------
                //1)直接扣款成功
                //--------------------------------------------------------------------

                log.i("【一次性支付成功】");
                resultListener.onSuccess(scanPayResData);
            }else{

                //出现业务错误
                log.i("业务返回失败");
                log.i("err_code:" + errorCode);
                log.i("err_code_des:" + errorCodeDes);

                //业务错误时错误码有好几种，商户重点提示以下几种
                if (errorCode.equals("AUTHCODEEXPIRE") || errorCode.equals("AUTH_CODE_INVALID") || errorCode.equals("NOTENOUGH")) {

                    //--------------------------------------------------------------------
                    //2)扣款明确失败
                    //--------------------------------------------------------------------


                    //以下几种情况建议明确提示用户，指导接下来的工作
                    if (errorCode.equals("AUTHCODEEXPIRE")) {
                        //表示用户用来支付的二维码已经过期，提示收银员重新扫一下用户微信“刷卡”里面的二维码
                        log.w("【支付扣款明确失败】原因是：" + errorCodeDes);
                        resultListener.onFailByAuthCodeExpire(scanPayResData);
                    } else if (errorCode.equals("AUTH_CODE_INVALID")) {
                        //授权码无效，提示用户刷新一维码/二维码，之后重新扫码支付
                        log.w("【支付扣款明确失败】原因是：" + errorCodeDes);
                        resultListener.onFailByAuthCodeInvalid(scanPayResData);
                    } else if (errorCode.equals("NOTENOUGH")) {
                        //提示用户余额不足，换其他卡支付或是用现金支付
                        log.w("【支付扣款明确失败】原因是：" + errorCodeDes);
                        resultListener.onFailByMoneyNotEnough(scanPayResData);
                    }
                } else if (errorCode.equals("USERPAYING")) {

                    //--------------------------------------------------------------------
                    //3)需要输入密码
                    //--------------------------------------------------------------------
                } else {

                    //--------------------------------------------------------------------
                    //4)扣款未知失败
                    //--------------------------------------------------------------------
                }
            }
        }
    }


    //是否需要再调一次撤销，这个值由撤销API回包的recall字段决定
    private boolean needRecallReverse = false;

    /**
     * 设置循环多次调用订单查询API的时间间隔
     *
     * @param duration 时间间隔，默认为10秒
     */
    public void setWaitingTimeBeforePayQueryServiceInvoked(int duration) {
        waitingTimeBeforePayQueryServiceInvoked = duration;
    }

    /**
     * 设置循环多次调用订单查询API的次数
     *
     * @param count 调用次数，默认为三次
     */
    public void setPayQueryLoopInvokedCount(int count) {
        payQueryLoopInvokedCount = count;
    }

    /**
     * 设置循环多次调用撤销API的时间间隔
     *
     * @param duration 时间间隔，默认为5秒
     */
    public void setWaitingTimeBeforeReverseServiceInvoked(int duration) {
        waitingTimeBeforeReverseServiceInvoked = duration;
    }

    public void setScanPayService(ScanPayService service) {
        scanPayService = service;
    }


}
