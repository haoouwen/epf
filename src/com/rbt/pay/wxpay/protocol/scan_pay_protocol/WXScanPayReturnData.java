package com.rbt.pay.wxpay.protocol.scan_pay_protocol;

/**
 * User: rizenguo
 * Date: 2014/10/25
 * Time: 16:43
 */
public class WXScanPayReturnData {

    //协议层
    private String return_code = "";
    private String return_msg = "";

   

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

}
