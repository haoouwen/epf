package com.rbt.pay.wxpay.service;
import com.rbt.pay.wxpay.common.Configure;
import com.rbt.pay.wxpay.protocol.refund_protocol.RefundReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:04
 */
public class RefundService extends BaseService{

    public RefundService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.REFUND_API);
    }

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(Object refundReqData,String wtype) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
    	String responseString="";
    	if(wtype.equals("0")){
    		//PC端
    		responseString = sendPostInfo(refundReqData);
    	}else {
    		//手机端
    		responseString = sendPostAppInfo(refundReqData);
		}
        return responseString;
    }

}
