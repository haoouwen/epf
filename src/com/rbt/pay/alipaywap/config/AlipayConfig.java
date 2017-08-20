package com.rbt.pay.alipaywap.config;

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
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	//授权接口名称。不可空
	public static String wap_pay_service="alipay.wap.trade.create.direct";
    
	public static String auth_service =  "alipay.wap.auth.authAndExecute";
	
	////请求参数格式。固定取值 xml。不可空
	public static String format = "xml";
	
	public static String v = "2.0";
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = PaymentFuc.getPayment("alipaywap").getPay_account();
	
	//收款支付宝帐户
	public static String seller_email = PaymentFuc.getPayment("alipaywap").getSeller_name();
	
	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	public static String key = "";
	
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALfa+MUyD0MtmT/7HXzrW6Jlbjj4i1OsP9Q+Zy+7MDF+v43yASVNwAAGgd6y2puPgw7XhOqqaQJmc/UvIn1VXk22USBgznQOVI9Jzy7aeWKp85sqDvaTAMc5W5WdMkOngks4PamkCe9UOrAKKP1zZIjPCIJdtSETidSqvZy4lSFZAgMBAAECgYEAjQUsIzUOd9yV23llWNe95YYfURty4q8QFl/3DalqgcxSaTHipxZH2r0jCAnvm7osMEom7UfgW7Lx5nJM5g2A0pV/A2f6TquP/TSXGQhJE9JWhrEn/ddvtFSe+3J9ABdfvIxmazbw8tcTbQEA8dTIzH1uVMoAEA+AyJEUzbqYnLECQQDjm4IvtOkUr8r3p2KldI9DS45vvcZ7t2qmyWc2V88Uaqu9XZuGbX4efivRc5JazGCiv7dJLahVXjgUv77P+82fAkEAzspE89DOOuXt39pxApcg38oqY6qZt28cI1RbAaDlCji/DdXkXnNYYLUPdC53t5h0vEqn9Y+rrA5wt9ITXVo+BwJAOWeSORgGpSoFJdr4nUQ9kbpvrS0O70r+QhyPxHv3BW417Ge8fvYElgo6YPQmGHJteP6janhyeT1vJYSKyof2fwJAblXOZKlM4HA+c+qB0fgBOmcMJxGn4xAN22vvAvduPf35MtClgR/aT/9Lt5nzlqpp3u2WoJd/6dXqnqogWvcDkQJAbfcGlqDlu4d91QeQb08u+mDB2igm1jkobHf7YWL2k1AJ4QQADzaCHPGfpbQMWie363F/vxpABjiIYspxjF4Zmw==";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPoU7Nvgh4qRwAeSfCalfQeShQJbtEv+rQgEX/9YfHO9BiRq/8FKfsiQLteRBHtGbgnR6ylQRIizzCzD1QuyBCwQRMccwzSFYVoK4R4g8VMWjSt1DE RH17RxT3DXMO8r7A88DnDgOWmxa/vyTydJQZv2Lp5RINifByAvXWynPT6QIDAQAB";

    //public static String ali_public_key = Base64.encode(ali_key.getBytes());

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "E:\\";

	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

	//页面跳转同步通知页面路径
	public static String call_back_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/alipaywap/call_back_url.jsp";
	//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	
	//服务器异步通知页面路径
	public static String notify_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/alipaywap/notify_url.jsp";
	//需http://格式的完整路径，不能加?id=123这类自定义参数

	//操作中断返回地址
	public static String merchant_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/webapp/merchant.html";
	//用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
	
	//手机端充值 同步通知页面
	public static String recharge_back_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/alipaywap/recharge_back_url.jsp";
	//手机端充值 异步通知页面
	public static String recharge_notify_url = SysconfigFuc.getSysValue("cfg_mobiledomain")+"/include/components/payment/alipaywap/recharge_notify_url.jsp";
	
}
