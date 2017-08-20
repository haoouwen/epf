package com.rbt.pay.wxpay.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.function.SysconfigFuc;
import com.rbt.pay.wxpay.common.Log;
import com.rbt.pay.wxpay.common.Signature;
import com.rbt.pay.wxpay.common.XMLParser;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanPayReqData;
import com.rbt.pay.wxpay.service.WXScanPayService;
import org.slf4j.LoggerFactory;

/**
 * User: rizenguo
 * Date: 2014/12/1
 * Time: 17:05
 */
public class WXScanPayBusiness {

    public WXScanPayBusiness() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
    	wxScanPayService = new WXScanPayService();
    }
    //打log用
    private static Log log = new Log(LoggerFactory.getLogger(WXScanPayBusiness.class));

    private WXScanPayService wxScanPayService;


    /**
     * 直接执行被扫支付业务逻辑（包含最佳实践流程）
     *
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
     * @throws Exception
     */
    public String run(WXScanPayReqData wxScanPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //构造请求“被扫支付API”所需要提交的数据
        //--------------------------------------------------------------------

        //接受API返回
        String payServiceResponseString;

        long costTimeStart = System.currentTimeMillis();


        log.i("订单号："+wxScanPayReqData.getOut_trade_no()+"支付API返回的数据如下：");
        payServiceResponseString = wxScanPayService.request(wxScanPayReqData);

        long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");
        //打印回包数据
        log.i(payServiceResponseString);
        
        Map xmlMap=new HashMap();
    	xmlMap=XMLParser.getMapFromXML(payServiceResponseString);

        if (xmlMap == null || xmlMap.get("return_code")== null) {
            log.e("【支付失败】支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            //resultListener.onFailByReturnCodeError(scanPayResData);
            return null;
        }

        if (xmlMap.get("return_code").equals("FAIL")) {
            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            log.e("【支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
            //resultListener.onFailByReturnCodeFail(scanPayResData);
            return null;
        } else {
            log.i("支付API系统获取二维码成功返回数据");
            //--------------------------------------------------------------------
            //收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
            //--------------------------------------------------------------------
            if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString)) {
                log.e("【请求支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
               // resultListener.onFailBySignInvalid(scanPayResData);
                return null;
            }

            //获取错误码
            String errorCode ="";
            if(xmlMap.get("err_code")!=null){
            	 errorCode=xmlMap.get("err_code").toString();
            }
            //获取错误描述
            String errorCodeDes ="";
            if(xmlMap.get("err_code_des")!=null){
            	errorCodeDes=xmlMap.get("err_code_des").toString();
	        }
            if (xmlMap.get("result_code").equals("SUCCESS")) {
                log.i("【订单号："+wxScanPayReqData.getOut_trade_no()+"请求支付获取二维码链接成功】");
                return payServiceResponseString;
            }else{
                //出现业务错误
                log.i("业务返回失败");
                log.i("err_code:" + errorCode);
                log.i("err_code_des:" + errorCodeDes);
                return null;
            }
        }
    }
    
    /**
     * 提交到微信授权成功后生成的二维码页面
     */
    private static final String WECHAT_ACTION = SysconfigFuc.getSysValue("cfg_basehost")+"/mall/pay!wxScanPayreqData.action";
    
    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"wechatsubmit\" name=\"wechatsubmit\" action=\"" + WECHAT_ACTION + "\" method=\"" + strMethod+ "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sParaTemp.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['wechatsubmit'].submit();</script>");

        return sbHtml.toString();
    }

    

}
