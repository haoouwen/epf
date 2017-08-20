package com.rbt.pay.wxpay.protocol.scan_pay_protocol;

import com.rbt.pay.wxpay.common.Configure;
import com.rbt.pay.wxpay.common.RandomStringGenerator;
import com.rbt.pay.wxpay.common.Signature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * User: rizenguo
 * Date: 2014/10/25
 * Time: 16:42
 */
public class WXScanPayReqData {
	 //每个字段具体的意思请查看API文档
    private String appid = "";//公众账号ID 是
    private String mch_id = "";//商户号 是
    private String device_info = "";//设备号 否
    private String nonce_str = "";//随机字符串 是
    private String sign = "";//签名 是
    private String body = "";//商品描述 是
    private String detail="";//商品详情 否
    private String attach = "";//附加数据 否
    private String out_trade_no = "";//商户订单号 是
    private String fee_type="CNY";//货币类型 否
    private int    total_fee = 0;//总金额 是 Int
    private String spbill_create_ip = "";//终端IP 是
    private String time_start = "";//交易起始时间 否
    private String time_expire = "";//交易结束时间 否
    private String goods_tag = "";//商品标记 否
    private String notify_url="";//通知地址 是
    private String trade_type="";//交易类型 是
    private String product_id="";// 商品ID 否
    private String openid = "";//用户标识 否
    /**
     * @param body (必填)要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
     * @param attach (非必填) 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
     * @param outTradeNo (必填) 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
     * @param totalFee (必填) 订单总金额，单位为“分”，只能整数
     * @param deviceInfo (非必填) 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
     * @param spBillCreateIP (必填) 订单生成的机器IP
     * @param timeStart (非必填) 订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     * @param timeExpire  (非必填) 订单失效时间，格式同上
     * @param goodsTag (非必填) 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
     * @param detail (非必填) 商品名称明细列表
     * @param fee_type (必填) 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     * @param notify_url (必填) 接收微信支付异步通知回调地址
     * @param trade_type  (必填) 取值如下：JSAPI，NATIVE，APP
     * @param product_id (非必填) trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义
     * @param openid (非必填) trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
     */
    public WXScanPayReqData(String body,String attach,String outTradeNo,
    		int totalFee,String deviceInfo,String spBillCreateIP,String timeStart,String timeExpire,String goodsTag
    		,String fee_type,String detail,String notify_url,String trade_type,String product_id,String openid){

        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMchid());

        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setBody(body);

        //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
        setAttach(attach);

        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);

        //订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);

        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDevice_info(deviceInfo);

        //订单生成的机器IP
        setSpbill_create_ip(spBillCreateIP);

        //订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
        setTime_start(timeStart);

        //订单失效时间，格式同上
        setTime_expire(timeExpire);

        //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        setGoods_tag(goodsTag);

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        
        //商品详情
        setDetail(detail);
        
        //货币类型
        setFee_type(fee_type);
        
        //通知地址
        setNotify_url(notify_url);
        
       //交易类型
        setTrade_type(trade_type);
        
       // 商品ID
        setProduct_id(product_id);
        
       //用户标识
        setOpenid(openid);

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
