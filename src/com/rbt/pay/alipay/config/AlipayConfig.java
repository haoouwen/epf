package com.rbt.pay.alipay.config;

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
	
	//即时到账接口名称。不可空
	public static String pay_service="create_direct_pay_by_user";
	
	//合作者身份 ID：签约的支付宝账号对应的支付宝唯一用户号。以 2088 开头的 16 位纯数字组成。不可空--PaymentFuc.getPayment("alipay").getPay_account()
	public static String partner = PaymentFuc.getPayment("alipay").getPay_account();
	
	// 商户的私钥：查询安全校验码(Key)。不可空
	public static String key = PaymentFuc.getPayment("alipay").getPasswd();
	
	// 字符编码格式 目前支持 gbk 或 utf-8。不可空
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改。不可空
	public static String sign_type = "MD5";
    
	//↑↑↑↑↑↑↑↑↑↑配置基本参数↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径。正式环境要注意去掉
	public static String log_path = "E:\\";
	
	// 支付类型：1-商品购买  4-捐赠  47-电子卡券。不可空
	public static String payment_type="1";
	
	//卖家支付宝用户号。卖家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。不可空
	public static String seller_id=PaymentFuc.getPayment("alipay").getPay_account();
	
	//卖家支付宝账号：卖家支付宝账号，格式为邮箱或手机号。可空
	public static String seller_email=PaymentFuc.getPayment("alipay").getSeller_name();
	
	//页面跳转同步通知页面路径:支付宝处理完请求后，当前页面自动跳转到商户网站里指定页面的http 路径。可空
	public static String return_url=SysconfigFuc.getSysValue("cfg_basehost")+"/mall-alipay-return_url.html";
	
	//服务器异步通知页面路径:支付宝服务器主动通知商户网站里指定的页面 http 路径。可空
	public static String notify_url=SysconfigFuc.getSysValue("cfg_basehost")+"/include/components/payment/alipay/notify_url.jsp";
	
	//充值 同步通知页面
	public static String recharge_return_url = SysconfigFuc.getSysValue("cfg_basehost")+"/include/components/payment/alipay/recharge_return_url.jsp";
	//充值 异步通知页面
	public static String recharge_notify_url = SysconfigFuc.getSysValue("cfg_basehost")+"/include/components/payment/alipay/recharge_notify_url.jsp";
	
	//商品展示网址:收银台页面上，商品展示的超链接。可空
	public static String show_url="";
	
	//防钓鱼时间戳:若要使用请调用类文件submit中的query_timestamp函数。可空
	public static String anti_phishing_key ="";
	
	//客户端的IP地址:非局域网的外网IP地址，如：221.0.0.1。可空
	public static String exter_invoke_ip="";
	
	/************************以下为退款需要的参数***************************************/
	
	//批量退款接口名称。不可空
	public static String refund_service="refund_fastpay_by_platform_pwd";
	
	//服务器异步通知页面路径:退款请求支付宝服务器主动通知商户网站里指定的页面 http 路径。不可空
	public static String refund_notify_url=SysconfigFuc.getSysValue("cfg_basehost")+"/include/components/payment/alipay/refund_notify_url.jsp";
	
	//服务器异步通知页面路径:退款请求支付宝服务器主动通知商户网站里指定的页面 http 路径。可空
	public static String admin_refund_notify_url=SysconfigFuc.getSysValue("cfg_basehost")+"/include/components/payment/alipay/admin_refund_notify_url.jsp";
	
	/************************以下为支付宝交易关闭需要的参数***************************************/
	//交易关闭接口名称。不可空
	public static String close_service="close_trade";
	
}
