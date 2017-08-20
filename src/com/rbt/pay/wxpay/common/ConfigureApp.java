package com.rbt.pay.wxpay.common;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.util.IpSeekerInit;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.function.PaymentFuc;
import com.rbt.function.SysconfigFuc;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:40
 * 这里放置各种配置数据
 */
public class ConfigureApp {
//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	//微信支付商户号（开通公众号的微信支付功能之后可以获取到）
	private static String key = PaymentFuc.getPayment("wxapppay").getPasswd();

	//微信支付分配的商户密钥（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = PaymentFuc.getPayment("wxapppay").getPay_account();

	//受理模式下给子商户分配的子商户号
	private static String subMchID = "";

	
	//HTTPS证书的本地路径
	private static String certLocalPath =PropertiesUtil.getRootpath()+"/include/commonfiles/apiclient_certapp.p12".replace("/",File.separator) ;

	//HTTPS证书密码，默认密码等于商户号
	private static String certPassword = PaymentFuc.getPayment("wxapppay").getPay_account();

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;
	
	//微信支付应用ID（开通公众号的微信支付功能之后可以获取到）
	private static String appID = PaymentFuc.getPayment("wxapppay").getAppID();
	//微信支付应用密钥（开通公众号的微信支付功能之后可以获取到）
	private static String appSecret=PaymentFuc.getPayment("wxapppay").getAppSecret();

	//机器IP
	private static String ip = "";
	
	//终端设备号
	public static String deviceInfo="WEB";
    //活动访问者IP
	public static String spBillCreateIP=IpSeekerInit.getIpAddr(ServletActionContext.getRequest());
    //默认人民币：CNY
	public static String fee_type="CNY";
    //取值如下：JSAPI，NATIVE，APP
	public static String trade_type="APP";
	 //接收微信支付异步通知回调地址
	public static String notify_url_app=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/weixinpay/call_back_url.jsp";
		
	//以下是几个API的路径：
	//1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";
	//2）扫苗支付API
	public static String Scan_PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		ConfigureApp.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.rbt.pay.wxpay.common.HttpsRequest";
	
	public static String HttpsRequestRClassName = "com.rbt.pay.wxpay.common.HttpsRequestAppR";

	public static void setKey(String key) {
		ConfigureApp.key = key;
	}

	public static void setAppID(String appID) {
		ConfigureApp.appID = appID;
	}

	public static void setMchID(String mchID) {
		ConfigureApp.mchID = mchID;
	}

	public static void setSubMchID(String subMchID) {
		ConfigureApp.subMchID = subMchID;
	}
	public static void setIp(String ip) {
		ConfigureApp.ip = ip;
	}
	public static void setCertLocalPath(String certLocalPath) {
		ConfigureApp.certLocalPath = certLocalPath;
	}

	public static void setCertPassword(String certPassword) {
		ConfigureApp.certPassword = certPassword;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}
	
	public static String getMchid(){
		return mchID;
	}

	public static String getSubMchid(){
		return subMchID;
	}

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}

	public static String getAppSecret() {
		return appSecret;
	}
	public static String getCertLocalPath(){
		return certLocalPath;
	}
	
	public static String getCertPassword(){
		return certPassword;
	}
	public static void setAppSecret(String appSecret) {
		ConfigureApp.appSecret = appSecret;
	}

	public static void setHttpsRequestRClassName(String httpsRequestRClassName) {
		HttpsRequestRClassName = httpsRequestRClassName;
	}

}
