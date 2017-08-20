package com.rbt.pay.unionpay.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.rbt.pay.unionpay.config.UnionpayConfig;

/**
 * 名称： 基础参数<br>
 * 功能： 提供基础数据<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。
 */
public class UnionpaySubmit {
    
    /**
     * 中国银联提供给商户的服务接入网关URL(新)
     */
    private static final String UNIONPAY_GATEWAY = "https://gateway.95516.com/gateway/api/frontTransReq.do";
    
    private static final String UNIONPAY_REFUND_GATEWAY="https://gateway.95516.com/gateway/api/backTransReq.do";
	
    /**
	 * 构造HTTP POST交易表单的方法示例
	 * 
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body>");
		sf.append("<form id = \"pay_form\" action=\"" + UNIONPAY_GATEWAY
				+ "\" method=\"post\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		System.out.println(sf.toString()+"===========");
		return sf.toString();
	}
	
	
	/**
	 * 功能：后台交易给银联地址发请求
	 * @param contentData
	 * @return 返回报文 map
	 */
	public static Map<String, String> submitUrl(Map<String, String> submitFromData) {
		String resultString = "";
		//LogUtil.writeLog("请求银联地址:" + requestUrl);
		/**
		 * 发送后台请求数据
		 */
		HttpClient hc = new HttpClient(UNIONPAY_REFUND_GATEWAY, 30000, 30000);
		try {
			int status = hc.send(submitFromData, UnionpayConfig.encoding);
			if (200 == status) {
				resultString = hc.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> resData = new HashMap<String, String>();
		/**
		 * 验证签名
		 */
		if (null != resultString && !"".equals(resultString)) {
			// 将返回结果转换为map
			resData = SDKUtil.convertResultStringToMap(resultString);
			if (SDKUtil.validate(resData, UnionpayConfig.encoding)) {
				LogUtil.writeLog("验证签名成功,可以继续后边的逻辑处理");
			} else {
				LogUtil.writeLog("验证签名失败,必须验签签名通过才能继续后边的逻辑...");
			}
		}
		return resData;
	}
	
	/**
	 * java main方法 数据提交 　　 对数据进行签名
	 * 
	 * @param contentData
	 * @return　签名后的map对象
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> signData(Map<String, ?> contentData) {
		Entry<String, String> obj = null;
		Map<String, String> submitFromData = new HashMap<String, String>();
		for (Iterator<?> it = contentData.entrySet().iterator(); it.hasNext();) {
			obj = (Entry<String, String>) it.next();
			String value = obj.getValue();
			if (StringUtils.isNotBlank(value)) {
				// 对value值进行去除前后空处理
				submitFromData.put(obj.getKey(), value.trim());
				System.out
						.println(obj.getKey() + "-->" + String.valueOf(value));
			}
		}
		/**
		 * 签名
		 */
		SDKUtil.sign(submitFromData, UnionpayConfig.encoding);

		return submitFromData;
	}
	
	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					// System.out.println("======为空的字段名===="+en);
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	/**
	 * @function 组装消费撤销类交易
	 * @param order_id
	 * @param txnAmt
	 * @param origQryId
	 * @return
	 */
	public static Map<String,String> getRevocationDataMap(String order_id,Integer txnAmt,String origQryId){
		// 组装请求报文
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", UnionpayConfig.version);
		// 字符集编码 默认"UTF-8"
		data.put("encoding", UnionpayConfig.encoding);
		// 签名方法 01 RSA
		data.put("signMethod", UnionpayConfig.signMethod);
		// 交易类型 01-消费 04-退货
		data.put("txnType", UnionpayConfig.revocation_txnType);
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", UnionpayConfig.revocation_txnSubType);
		// 业务类型
		data.put("bizType", "000201");
		// 渠道类型，07-PC，08-手机
		data.put("channelType", UnionpayConfig.channelType);
		// 后台通知地址
		data.put("backUrl", UnionpayConfig.refund_notify_url);
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", UnionpayConfig.accessType);
		// 商户号码，请改成自己的商户号
		data.put("merId", UnionpayConfig.merId);
		// 订单发送时间，取系统时间
		data.put("txnTime", UnionpayConfig.txnTime);
		// 商户订单号，8-40位数字字母
		data.put("orderId", "R"+order_id);
		// 交易金额，单位分
		data.put("txnAmt", String.valueOf(txnAmt));
		//交易币种
		data.put("currencyCode", UnionpayConfig.currencyCode);
		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		data.put("reqReserved", "");
		//原始消费交易的流水號 queryId
		data.put("origQryId", origQryId);
		
		return data;
	}
	
	
	/**
	 * @function 组装银联在线退货的数据
	 * @param order_id
	 * @param txnAmt
	 * @param origQryId
	 * @return
	 */
	public static Map<String,String> getRefundDataMap(String order_id,Integer txnAmt,String origQryId){
		
		// 组装请求报文
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", UnionpayConfig.version);
		// 字符集编码 默认"UTF-8"
		data.put("encoding", UnionpayConfig.encoding);
		// 签名方法 01 RSA
		data.put("signMethod", UnionpayConfig.signMethod);
		// 交易类型 01-消费 04-退货
		data.put("txnType", UnionpayConfig.refund_txnType);
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", UnionpayConfig.refund_txnSubType);
		// 业务类型
		data.put("bizType", UnionpayConfig.bizType);
		// 渠道类型，07-PC，08-手机
		data.put("channelType", UnionpayConfig.channelType);
		// 后台通知地址
		data.put("backUrl", UnionpayConfig.refund_notify_url);
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", UnionpayConfig.accessType);
		// 商户号码，请改成自己的商户号
		data.put("merId", UnionpayConfig.merId);
		// 订单发送时间，取系统时间
		data.put("txnTime", UnionpayConfig.txnTime);
		// 商户订单号，8-40位数字字母
		data.put("orderId", "T"+order_id);
		// 交易金额，单位分
		data.put("txnAmt", String.valueOf(txnAmt));
		//交易币种
		data.put("currencyCode", UnionpayConfig.currencyCode);
		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		data.put("reqReserved", "");
		//原始消费交易的流水號 queryId
		data.put("origQryId", origQryId);
		
		return data;
	}
    
}
