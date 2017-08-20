package com.rbt.pay.alipaymobile.config;

import com.rbt.function.PaymentFuc;
import com.rbt.function.SysconfigFuc;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2014-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓配置基本参数↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	//商户私钥，pkcs8格式
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANfdDe5zFzJ6nM4L0SH1HkbqXe+sDRzCbLPaOIWmymlpKRfqjwylvYkMu/aXQpgOTQRnrFMlfb9Xo4sdzPJOhBfEIA/z2xPAqUy9VjMlwazb7OjKgV+VHzq4EtNFq1C1Ahuzt/FPornniosHBB7BVggkcH2scvfZuYQjYFbwAzGNAgMBAAECgYEApCUREfBshFMeZ8QunD7TbiOLmNPhCKyDkmPvNRp2sp9nwyr26iwiK1QGnmW5Gv1M7XuUW4mXzGdtEj1v+hVgdXh0SLju29bimsz4L7UalcPjic1BdSH37OZJgPtLBilGTbaf3VptO749hrz1Vlr+0C0wlGi096FD02+/0YzVFoECQQDv4eAyyCACobN9qhqGqyI3c6rbRMIyr34XMt0rXRvbB2Agk47mggFRt0O9czUXzMgkW/JI2N7GKclM2aWqAaihAkEA5l4KNQRmejmKrBfDYeu9vL/QoarfUKredENz01f23hOKA4pyC8lQnwYyL/qATTlnzSitorpfJ0znnnXLpftFbQJAdOAttzBQgFe+tzx43iJlcnUVyu2uXLmqiq0Km4Uq/EWUrWqXlnHDKMhM5fJK7QSmgyiVXK7lo9FaNepZUAKs4QJAYouJ7EIUoGZrFtEXiJTy1oJrei0BWJ2viaatLpRri3ZqTLd1r+sPbOeWooWTSvWqnM2w8XKub75rOpMDad6CRQJAWGSP/tuJp5Op5/P4/cE5uuOe4uGXQXfkEsUw16vfC5DiW8Ey4Vo+HC0mW4wUh9dHo110Say/b6u41bD7iAnPpg=="; 
	
	//即时到账接口名称。不可空
	public static String service="mobile.securitypay.pay";
	
	//合作者身份 ID：签约的支付宝账号对应的支付宝唯一用户号。以 2088 开头的 16 位纯数字组成。不可空--PaymentFuc.getPayment("alipay").getPay_account()
	public static String partner = PaymentFuc.getPayment("alipay").getPay_account();
	
	// 字符编码格式 目前支持 gbk 或 utf-8。不可空
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改。不可空
	public static String sign_type = "RSA";
    
	//↑↑↑↑↑↑↑↑↑↑配置基本参数↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径。正式环境要注意去掉
	public static String log_path = "E:\\";
	
	// 支付类型：1-商品购买  4-捐赠  47-电子卡券。不可空
	public static String payment_type="1";
	
	//卖家支付宝用户号。卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。不可空
	public static String seller_id=PaymentFuc.getPayment("alipay").getSeller_name();
	
	//服务器异步通知页面路径:支付宝服务器主动通知商户网站里指定的页面 http 路径。可空
	public static String notify_url="http://m.epff.cc/include/components/payment/alipaymobile/notify_url.jsp";
	
	public static String it_b_pay = "1440m";
	
	/************************以下为退款需要的参数***************************************/
	
	//批量退款接口名称。不可空
	public static String refund_service="refund_fastpay_by_platform_pwd";
	
	//服务器异步通知页面路径:退款请求支付宝服务器主动通知商户网站里指定的页面 http 路径。不可空
	public static String refund_notify_url=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/alipaymobile/refund_notify_url.jsp";
	
	//服务器异步通知页面路径:退款请求支付宝服务器主动通知商户网站里指定的页面 http 路径。可空
	public static String admin_refund_notify_url=SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/alipaymobile/admin_refund_notify_url.jsp";
	
	/************************以下为支付宝交易关闭需要的参数***************************************/
	//交易关闭接口名称。不可空
	public static String close_service="close_trade";
	
}
