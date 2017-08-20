package com.rbt.pay.unionpay.config;

import com.rbt.common.util.DateUtil;
import com.rbt.function.PaymentFuc;
import com.rbt.function.SysconfigFuc;

/* *
 * 邱景岩
 *类名：UnionpayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2013-08-10
 */

public class UnionpayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓配置基本参数↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 版本号
	public static String version="5.0.0";
	// 字符集编码 默认"UTF-8"
	public static String encoding="UTF-8";
	// 签名方法 01 RSA
	public static String signMethod="01";
	// 交易类型 01-消费
	public static String txnType="01";
	// 交易子类型 01:自助消费 02:订购 03:分期付款
	public static String txnSubType="01";
	//业务类型 B2C网关支付，手机wap支付
	public static String bizType="000201";
	// 渠道类型，07-PC，08-手机
	public static String channelType="07";
	// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
	public static String accessType="0";
	// 商户号码，请改成自己的商户号 PaymentFuc.getPayment("unionpay").getPay_account()
	public static String merId=PaymentFuc.getPayment("unionpay").getPay_account();
	//交易币种（境内商户一般是156 人民币）
	public static String currencyCode="156";
	// 前台通知地址 ，控件接入方式无作用
	public static String frontUrl=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/return_url.jsp";
	// 后台通知地址
	public static String backUrl=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/notify_url.jsp";
	// 订单发送时间，取系统时间
	public static String txnTime=DateUtil.getFormatLong();
    //银联在线充值同步返回
	public static String recharge_front_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/recharge_return_url.jsp";
	//银联在线充值异步返回
	public static String recharge_back_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/recharge_notify_url.jsp";
	
	/*以下手机网页支付基本参数****************************************************/
	// 渠道类型，07-PC，08-手机
	public static String wap_channelType="08";
	// 前台通知地址 ，控件接入方式无作用
	public static String wap_frontUrl=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/wap_return_url.jsp";
	// 后台通知地址
	public static String wap_backUrl=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/wap_notify_url.jsp";
	
	// 前台通知地址 ，控件接入方式无作用
	public static String wap_recharge_frontUrl=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/wap_return_url.jsp";
	// 后台通知地址
	public static String wap_recharge_backUrl=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/wap_notify_url.jsp";
	
	//↑↑↑↑↑↑↑↑↑↑配置基本参数↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	/************************以下为消费撤销类交易需要的参数********************************************************/
	
	//交易类型 31-撤销
	public static String revocation_txnType="31";
	//交易子类型  默认00
	public static String revocation_txnSubType="00";
	
	/************************以下为退货需要的参数***************************************/
	
	// 交易类型 01-消费 04-退货
	public static String refund_txnType="04";
	//交易子类型  默认00	
	public static String refund_txnSubType="00";
	
	//服务器异步通知页面路径:退款请求银联在线服务器主动通知商户网站里指定的页面 http 路径。可空
	public static String refund_notify_url=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/unionpay/refund_notify_url.jsp";
	
	
}
