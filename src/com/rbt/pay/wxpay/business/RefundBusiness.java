package com.rbt.pay.wxpay.business;

import java.util.HashMap;
import java.util.Map;

import com.rbt.pay.wxpay.common.Log;
import com.rbt.pay.wxpay.common.Signature;
import com.rbt.pay.wxpay.common.XMLParser;
import com.rbt.pay.wxpay.protocol.refund_protocol.RefundReqData;
import com.rbt.pay.wxpay.service.RefundService;
import org.slf4j.LoggerFactory;

/**
 * User: rizenguo
 * Date: 2014/12/2
 * Time: 17:51
 */
public class RefundBusiness {

    public RefundBusiness() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        refundService = new RefundService();
    }
    //打log用
    private static Log log = new Log(LoggerFactory.getLogger(RefundBusiness.class));

    //执行结果
    private static String result = "";

    private RefundService refundService;

    /**
     * 调用退款业务逻辑
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 业务逻辑可能走到的结果分支，需要商户处理
     * @throws Exception
     */
    public Map run(Object refundReqData,String wtype) throws Exception {

        //--------------------------------------------------------------------
        //构造请求“退款API”所需要提交的数据
        //--------------------------------------------------------------------

        //API返回的数据
        String refundServiceResponseString;
        long costTimeStart = System.currentTimeMillis();
        log.i("退款查询API返回的数据如下：");
        refundServiceResponseString = refundService.request(refundReqData,wtype);
        long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");
        log.i(refundServiceResponseString);
        Map xmlMap=new HashMap();
    	xmlMap=XMLParser.getMapFromXML(refundServiceResponseString);
        if (xmlMap == null || xmlMap.get("return_code") == null) {
            setResult("Case1:退款API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问",Log.LOG_TYPE_ERROR);
            return null;
        }
        if (xmlMap.get("return_code").equals("FAIL")) {
            ///注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            setResult("Case2:退款API系统返回失败，请检测Post给API的数据是否规范合法",Log.LOG_TYPE_ERROR);
            return null;
        } else {
            log.i("订单号：的退款API系统成功返回数据");
            //--------------------------------------------------------------------
            //收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
            //--------------------------------------------------------------------
            if(wtype.equals("1")){
            	 if (!Signature.checkIsSignValidFromResponseStringAPP(refundServiceResponseString)) {
                     setResult("Case3:退款请求API返回的数据签名验证失败，有可能数据被篡改了",Log.LOG_TYPE_ERROR);
                     return null;
                 }
            }else {
            	 if (!Signature.checkIsSignValidFromResponseString(refundServiceResponseString)) {
                     setResult("Case3:退款请求API返回的数据签名验证失败，有可能数据被篡改了",Log.LOG_TYPE_ERROR);
                     return null;
                 }
			}
            if (xmlMap.get("result_code").equals("FAIL")) {
                log.i("出错，错误码：" + xmlMap.get("err_code") + "     错误信息：" +  xmlMap.get("err_code_des"));
                setResult("Case4:【退款失败】",Log.LOG_TYPE_ERROR);
                //退款失败时再怎么延时查询退款状态都没有意义，这个时间建议要么再手动重试一次，依然失败的话请走投诉渠道进行投诉
                return null;
            } else {
                //退款成功
                setResult("Case5:【退款成功】",Log.LOG_TYPE_INFO);
                return xmlMap;
            }
        }
    }

    public void setRefundService(RefundService service) {
        refundService = service;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        RefundBusiness.result = result;
    }

    public void setResult(String result,String type){
        setResult(result);
        log.log(type,result);
    }
}
