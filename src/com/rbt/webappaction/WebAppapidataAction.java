package com.rbt.webappaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ImageZipUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AdvinfoFuc;
import com.rbt.function.DetailOrderFuc;
import com.rbt.function.MarkGoodsFuc;
import com.rbt.function.SalegoodsFuc;
import com.rbt.pay.alipaymobile.config.AlipayConfig;
import com.rbt.pay.alipaymobile.util.SignUtils;
import com.rbt.pay.wxpay.WXPay;
import com.rbt.pay.wxpay.common.ConfigureApp;
import com.rbt.pay.wxpay.common.Signature;
import com.rbt.pay.wxpay.common.XMLParser;
import com.rbt.pay.wxpay.protocol.scan_pay_protocol.WXScanAppPayReqData;
import com.rbt.model.Coupon;
import com.rbt.model.Goods;
import com.rbt.model.Goodsorder;
import com.rbt.model.Member;
import com.rbt.model.Messagepush;
import com.rbt.model.Navtab;
import com.rbt.model.Redpacket;
import com.rbt.model.Salegoods;
import com.rbt.model.Sysconfig;
import com.rbt.model.TokensInfo;
import com.rbt.service.ICollectService;
import com.rbt.service.ICouponService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IKeywordService;
import com.rbt.service.IMessagepushService;
import com.rbt.service.INavigationService;
import com.rbt.service.IRedpacketService;
import com.rbt.service.ISalegoodsService;
import com.rbt.service.IMemberService;
import com.rbt.service.ISysconfigService;
import com.rbt.service.ITokensInfoService;

public class WebAppapidataAction extends WebAppgoodsModelAction implements Preparable {

	private static final long serialVersionUID = -3757793885975765700L;
	/*******************实体层****************/
	public Member member;
	public Goods goods;
	public Salegoods  salegoods;
	public Coupon coupon;
	public Redpacket redpacket;
	public Messagepush messagepush;
	/*******************业务层接口****************/
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	public ICollectService collectService;
	@Autowired
    private INavigationService navigationService;
	@Autowired
	private ISalegoodsService salegoodsService;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	public ISysconfigService sysconfigService;
	@Autowired
	public ITokensInfoService tokensInfoService;
	@Autowired
	public IMessagepushService messagepushService;
	
	/*********************集合******************/
	public List goodsList;//商品
	public String tab_number;
	public Navtab navtab;
	public String cat_attr_top_s;
	public List serachkeyList;//获取热门搜索关键词

	/*********************字段******************/
	public String key_word;// 搜索关键字
	public String en_word;//
	public String search_null_page;// 搜索为空页面
	public String catEn_name;//分类英文
	public String catName;//分类名称
	public String catList_id;//分类ID
	public String sale_names;//促销活动名称
	public long difftime=0;//倒计时
	String timeout="0";//时间结束
	public String platformType="0";//0表示触屏版端 1表示APP
	
	/////////////////////////////////首页数据JSON	格式安卓和IOS开始/////////////////////////////////
	public void indexJson() throws Exception{
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			//首页json数据
			Map<String,Object> indexJsonMap = new HashMap<String,Object>();
			//获取首页5涨大图数据 广告位ID=47
			indexJsonMap.put("advTopFive", AdvinfoFuc.getDisplayAdvList("47","10"));
	        //手机秒杀 1张图  广告位ID=79
			indexJsonMap.put("advPhoneSpike", AdvinfoFuc.getDisplayAdvList("79","1"));
			//倒计时 商城标签ID=4637384735
			indexJsonMap.put("advPhoneSpikeTime", indexDJSDate("4637384735"));
			//美食馆  1张图  广告位ID=44
			indexJsonMap.put("advFood", AdvinfoFuc.getDisplayAdvList("44","1"));
			//厨具馆  1张图  广告位ID=45
			indexJsonMap.put("advKitchenware", AdvinfoFuc.getDisplayAdvList("45","1"));
			//日化馆  1张图  广告位ID=46
			indexJsonMap.put("advIndustry", AdvinfoFuc.getDisplayAdvList("46","1"));
			//手机专享ID=1757438133
			indexJsonMap.put("exclusivePhoneGoods", MarkGoodsFuc.indexMarkGoodsJson("1757438133","15"));
			//国际优选广告位ID=48
			indexJsonMap.put("advInternational", AdvinfoFuc.getDisplayAdvList("48","8"));
			//长幅广告位1 ID=49
			indexJsonMap.put("advLongImgOne", AdvinfoFuc.getDisplayAdvList("49","1"));
			//压马路广告位 第一张 大图
			indexJsonMap.put("advPressRoad1", AdvinfoFuc.getDisplayAdvList("50","1"));
			//压马路广告位 13张小图
			indexJsonMap.put("advPressRoad13", AdvinfoFuc.getDisplayAdvList("51","13"));
			//长幅广告位2 ID=49
			indexJsonMap.put("advLongImgTwo", AdvinfoFuc.getDisplayAdvList("52","1"));
			//自主产品 5175343121
			indexJsonMap.put("autonomousGoods", MarkGoodsFuc.indexMarkGoodsJson("5175343121","15"));
			//热销产品 6416267167
			indexJsonMap.put("hotGoods", MarkGoodsFuc.indexMarkGoodsJson("6416267167","30"));
			//map转换为json格式
			JSONObject jsObj = JSONObject.fromObject(indexJsonMap);  
			String datajson = jsObj.toString();
			out.print(datajson);	
		} catch (Exception e) {
			  getResponse().sendRedirect(cfg_mobiledomain);
		}
	}
	
	
	//推送消息详细页
	public String appMessagePushPage(){
		if(!ValidateUtil.isRequired(getRequest().getParameter("mid"))){
			String id = getRequest().getParameter("mid").toString();
			messagepush=messagepushService.get(id);
		}
		return goUrl("mbMessagePush");
	}
	
	
	//推送消息详细页
	public void appMessagePushList() throws Exception{
		try {
			HttpServletRequest request = getRequest();
    		HttpServletResponse response = getResponse();
    		// 设置接收与发送的编码格式
    		request.setCharacterEncoding("UTF-8");
    		response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String dtime="";
			if (request.getParameter("time")!= null&&!"".equals(request.getParameter("time"))) {
				dtime=request.getParameter("time").toString();
			}
			Map<String,Object> indexJsonMap = new HashMap<String,Object>();
			indexJsonMap.put("messagePushList", getmesPushList(dtime));
			//map转换为json格式
			JSONObject jsObj = JSONObject.fromObject(indexJsonMap);  
			String datajson = jsObj.toString();
			out.print(datajson);	
		} catch (Exception e) {
			  getResponse().sendRedirect(cfg_mobiledomain);
		}
	}
	//获取消息列表
	public List getmesPushList(String dtime){
		HashMap map = new HashMap();
		map.put("info_state", "1");
		//获取时间比较
		if(dtime!=null&&!"".equals(dtime)){
			map.put("d_date", dtime);
		}
		map.put("start", 0);
		map.put("limit", 20);
		List mesList=new ArrayList();
		List newmesList=new ArrayList();
		mesList=messagepushService.getList(map);
		if(mesList!=null&&mesList.size()>0){
			for(int i=0;i<mesList.size();i++){
				HashMap gmap = new HashMap();
				HashMap ngmap = new HashMap();
				gmap=(HashMap)mesList.get(i);
				if(gmap!=null){
					ngmap.put("title", gmap.get("msgpush_name"));
					ngmap.put("content",gmap.get("mp_abstract"));
					ngmap.put("info_url",cfg_mobiledomain+"/webapp/MessagePushDetail_"+gmap.get("msgpush_id")+".html");
					ngmap.put("in_date",gmap.get("in_date"));
					ngmap.put("apns_type",gmap.get("apns_type"));
					ngmap.put("msgID",gmap.get("msgpush_id"));
					newmesList.add(ngmap);
				}
			}
		}
		return newmesList;
	}
	
	
	
	
	
	 /**
	 * 获得Token的值
	 * @return
     * @throws IOException 
	 */
	 public String appGetTokenInfo() throws IOException{
	    	try {
	    		HttpServletRequest request = getRequest();
	    		HttpServletResponse response = getResponse();
	    		// 设置接收与发送的编码格式
	    		request.setCharacterEncoding("UTF-8");
	    		response.setCharacterEncoding("UTF-8");
	    		response.setContentType("text/json; charset=UTF-8");
	    		String tokens="",types="0";
	    		if (request.getParameter("tokens")!= null&&!"".equals(request.getParameter("tokens"))) {
	    			tokens = getRequest().getParameter("tokens").toString();
	    			types = getRequest().getParameter("type").toString();
	    		    TokensInfo tokensInfo=new TokensInfo();
	    		    tokensInfo.setToken_name(tokens);
	    		    tokensInfoService.insert(tokensInfo);
	    		}
			} catch (Exception e) {
				getResponse().sendRedirect(cfg_mobiledomain);
			}
	    	return null;
	    }
	
	
	 /**
	 * 获取会员头像地址
	 * @return
     * @throws IOException 
	 */
	public void appMemberIco() throws IOException{
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String datajson="";
			if(!ValidateUtil.isRequired(getRequest().getParameter("user_id"))){
				String cust_id = getRequest().getParameter("user_id").toString();
				Member member=new Member();
				member=memberService.get(cust_id);
				if(member!=null&&member.getLogo_path()!=null&&!"".equals(member.getLogo_path())){
					Map<String,Object> indexJsonMap = new HashMap<String,Object>();
					indexJsonMap.put("memberImg", cfg_mobiledomain+member.getLogo_path().toString());
					JSONObject jsObj = JSONObject.fromObject(indexJsonMap);  
				    datajson = jsObj.toString();
				}
			}
			out.print(datajson);	
		} catch (Exception e) {
			  getResponse().sendRedirect(cfg_mobiledomain);
		}
	}
	
	/**
	 * @Method Description : APP上传更新头像
	 * @author: 胡惜坤
	 * @date : Dec 30, 2015 5:31:40 PM
	 * @param 
	 * @return return_type
	 * @throws IOException 
	 */
    public String appUpdateMemberIco() throws IOException{
    	try {
    		HttpServletRequest request = getRequest();
    		HttpServletResponse response = getResponse();
    		// 设置接收与发送的编码格式
    		request.setCharacterEncoding("UTF-8");
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("text/json; charset=UTF-8");
    		String cust_id="",imgUrl="";
    		if (request.getParameter("user_id")!= null&&!"".equals(request.getParameter("user_id"))&&request.getParameter("imgurl")!=null) {
    		    cust_id = getRequest().getParameter("user_id").toString();
    		    imgUrl=getRequest().getParameter("imgurl").toString();
				Member member=new Member();
				member=memberService.get(cust_id);
    			if(member!=null){
    				//获得新的图片路径
    				String newimgpath="";
    				//处理图片 将上传的图片从临时图片中 提取放入项目图片位置
    				ImageZipUtil imageZipUtil = new ImageZipUtil();
    				newimgpath=imageZipUtil.imageUploadZipApp(imgUrl);
    				member.setLogo_path(newimgpath);
    				memberService.update(member);
    			}
    		}
    		getResponse().sendRedirect("/webappmembercenter.html");
		} catch (Exception e) {
			getResponse().sendRedirect(cfg_mobiledomain);
		}
    	return null;
    }
	
    /**
	 * @Method Description : APP上传更新头像通过图片流
	 * @author: 胡惜坤
	 * @date : Dec 30, 2015 5:31:40 PM
	 * @param 
	 * @return return_type
	 * @throws IOException 
	 */
    public String appUpdateMemberImg() throws IOException{
    	try {
    		HttpServletRequest request = getRequest();
    		HttpServletResponse response = getResponse();
    		// 设置接收与发送的编码格式
    		request.setCharacterEncoding("UTF-8");
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("text/json; charset=UTF-8");
    		String cust_id="",imgData="";
    		if (request.getParameter("user_id")!= null&&!"".equals(request.getParameter("user_id"))&&request.getParameter("imgData")!=null) {
    		    cust_id = getRequest().getParameter("user_id").toString();
    		    imgData=getRequest().getParameter("imgData").toString();
    		    
				member=memberService.get(cust_id);
    			if(member!=null){
    				//获得新的图片路径
    				String newimgpath="";
    				//处理图片 将上传的图片从临时图片中 提取放入项目图片位置
    				ImageZipUtil imageZipUtil = new ImageZipUtil();
    				newimgpath=imageZipUtil.imageUploadZipApp(imgData);
    				//test
				      try {
					   response.setContentType("multipart/form-data; charset=UTF-8");
					   PrintWriter out = response.getWriter();
					   out.println("SUCCESS");
					   ServletInputStream in = request.getInputStream();
					   String str = readLine(in);// 这里是前台发起的所有参数的值，包括二进制形式的文件和其它形式的文件
					   out.println(str);
					   out.flush();
					  } catch (IOException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
					  }
    					  
    					  
    				//更新会员头像地址	  
    				member.setLogo_path(newimgpath);
    				memberService.update(member);
    			}
    		}
    		getResponse().sendRedirect("/webappmembercenter.html");
		} catch (Exception e) {
			getResponse().sendRedirect(cfg_mobiledomain);
		}
    	return null;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        PrintWriter writer = response.getWriter();  
        Map<String, String[]> params = request.getParameterMap();  
        String queryString = "";  
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "&";  
            }  
        }  
        // 去掉最后一个空格  
        queryString = queryString.substring(0, queryString.length() - 1);  
        writer.println("POST " + request.getRequestURL() + " " + queryString);  
    }  
    
    /*读取request数据*/  
    public static String getRequestData(HttpServletRequest request) throws IOException{  
        BufferedReader reader = request.getReader();  
        char[] buf = new char[512];  
        int len = 0;  
        StringBuffer contentBuffer = new StringBuffer();  
        while ((len = reader.read(buf)) != -1) {  
            contentBuffer.append(buf, 0, len);  
        }  
        String content = contentBuffer.toString();  
        if(content == null){  
            content = "";  
        }  
        return content;  
    }
    private String readLine(ServletInputStream in) throws IOException {
    	  byte[] buf = new byte[8 * 1024];
    	  StringBuffer sbuf = new StringBuffer();
    	  int result;
    	  do {
    	   result = in.readLine(buf, 0, buf.length); // does +=
    	   if (result != -1) {
    	    sbuf.append(new String(buf, 0, result, "UTF-8"));
    	   }
    	  } while (result == buf.length); // loop only if the buffer was filled

    	  if (sbuf.length() == 0) {
    	   return null; // nothing read, must be at the end of stream
    	  }
    	  int len = sbuf.length();
    	  if (sbuf.charAt(len - 2) == '\r') {
    	   sbuf.setLength(len - 2); // cut \r\n
    	  } else {
    	   sbuf.setLength(len - 1); // cut \n
    	  }
    	  return sbuf.toString();
    }
    
    
    
    
    
	///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @Method Description : 调整下载APP页面
	 * @author: 胡惜坤
	 * @date : Dec 30, 2015 5:31:40 PM
	 * @param 
	 * @return return_type
	 */
    public String appdownPage(){
    	return goUrl("mbappDown");
    }
    /**
	 * 跳转到领取优惠券 APP分享页面
	 * @return
	 */
	public String appShareCouponPage(){
		if(!ValidateUtil.isRequired(getRequest().getParameter("coupon_id"))){
			String id = getRequest().getParameter("coupon_id").toString();
			coupon = couponService.get(id);
		}
		return goUrl("mbYouHuiLq");
	}
	/**
	 * 跳转到领取红包和分享页面 APP分享页面
	 * @return
	 */
	public String appShareRedpacketPage(){
		if(!ValidateUtil.isRequired(getRequest().getParameter("redpacket_id"))){
			String id = getRequest().getParameter("redpacket_id").toString();
			redpacket = this.redpacketService.get(id);
		}
		return goUrl("mbRedpacketLq");
	}
	
	/**
	 * @author : HXK
	 * @throws Exception
	 * 
	 * @Method Description : APP搜索跳转页
	 */
	@SuppressWarnings("unchecked")
	public String searchlList() throws Exception {
	   
		Map pageMap = new HashMap();
		pageMap.put("is_show", "0");
		serachkeyList = this.keywordService.getList(pageMap);
	   return goUrl("mbappSearchIndex");

	}
	/**
	 * @author : HXK
	 * @throws Exception
	 * 
	 * @Method Description : 微信支付跳转页面
	 */
	@SuppressWarnings("unchecked")
	public String weixinPay() throws Exception {
	   return goUrl("mbWeixinPay");
	}
	
	/**
	 * @author : HXK
	 * @throws Exception
	 * 
	 * @Method Description : 微信支付跳转页面
	 */
	@SuppressWarnings("unchecked")
	public String weixinPayRespone() throws Exception {
		
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		//PrintWriter out = response.getWriter();
		String order_id_str="",total_amount="", datajson="";
		if (request.getParameter("order_id_str") != null) {
			//获取订单号
			order_id_str =request.getParameter("order_id_str");
			//获取订单金额
			Goodsorder goodsorder=new Goodsorder();
			goodsorder=goodsorderService.get(order_id_str);
			//订单状态存在和订单金额是对的 已经订单状态是未付款的
			if(goodsorder!=null&&"1".equals(goodsorder.getOrder_state())){
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
				total_amount=goodsorder.getLast_pay();
				String body="";
				String attach=this.session_cust_id+"-"+this.session_user_id+"-0_1";
				String outTradeNo=order_id_str;
				//终端设备号
				String deviceInfo="";
				//活动访问者IP
				String spBillCreateIP=ConfigureApp.spBillCreateIP;
				String timeStart=""; 
				String timeExpire="";
				String goodsTag=""; 
				//默认人民币：CNY
				String fee_type=ConfigureApp.fee_type; 
				String detail="";
				//接收微信支付异步通知回调地址
				String notify_url=ConfigureApp.notify_url_app; 
				//取值如下：JSAPI，NATIVE，APP
				String trade_type=ConfigureApp.trade_type; 
				String product_id=""; 
				String openid="";
				int totalFee=0;
			    HashMap smap = DetailOrderFuc.getGoodsNameBody(outTradeNo);
			     if(smap!=null && smap.get("product_id")!=null && smap.get("body")!=null){
			        product_id = smap.get("product_id").toString();
			        body = smap.get("body").toString();
			        detail=body;
			     }
			     //由于以分为单位 所以要乘以100
	       	     double amount = Double.parseDouble(total_amount); 
	       	     amount = amount*100;
	       	     totalFee = (int)Math.round(amount);
	       	     WXScanAppPayReqData  wxScanAppPayReqData=new WXScanAppPayReqData( body, attach, outTradeNo,
						 totalFee, deviceInfo, spBillCreateIP, timeStart, timeExpire, goodsTag
			       		, fee_type, detail, notify_url, trade_type, product_id, openid);
				   	String getXmlString=WXPay.reqAppPayService(wxScanAppPayReqData);
				   	if(!ValidateUtil.isRequired(getXmlString)){
				   		Map xmlMap=new HashMap();
					   	xmlMap=XMLParser.getMapFromXML(getXmlString);
					   	
					    //////////////////////传URL格式////////////////////
					    datajson+="order_id_str="+order_id_str;
					    //datajson+="&total_amount="+totalFee;
					    datajson+="&appid="+xmlMap.get("appid").toString();	
					    datajson+="&noncestr="+xmlMap.get("nonce_str").toString();
					    //datajson+="&package=SignWXPay";
					    datajson+="&partnerid="+xmlMap.get("mch_id").toString();
					    datajson+="&prepayid="+xmlMap.get("prepay_id").toString();
					    int time = (int) (System.currentTimeMillis() / 1000);
					    datajson+="&timestamp="+String.valueOf(time);
					    
					    System.out.println("获取sign值"+xmlMap.get("sign").toString());
					    String r_sign="";
					    //r_sign=Signature.getSignFromResponseString(getXmlString);
					    r_sign=wxAPPsign(xmlMap.get("prepay_id").toString(),String.valueOf(time),xmlMap.get("nonce_str").toString());
					    System.out.println("重新签名之后sign值"+r_sign);
					    //重新签名生成 sign
					    datajson+="&sign="+r_sign;
				   	}else {
				   		datajson="returnState=payFail";
					}
			}
		}
		 
		// --------------------------------生成完成---------------------------------------------  
	    getResponse().sendRedirect("/webapp/wxpaySend.html?"+datajson);
	    return NONE;
	}
    /////////////////////////////////首页数据JSON	格式结束/////////////////////////////////

	/**
	 * 支付宝钱包调起支付所需参数
	 * 
	 */
	public String alipayRespone()throws Exception{
		
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		StringBuffer sb = new StringBuffer();
		String order_id = "",payInfo="";
		if (request.getParameter("order_id_str") != null && request.getParameter("order_id_str")!="") {
			order_id = request.getParameter("order_id_str").toString();
			Goodsorder goodsorder = goodsorderService.get(order_id);
			if(goodsorder!=null){
				//接口名称
				String service = AlipayConfig.service;
				//合作者身份ID
				String partner = AlipayConfig.partner;
				//参数编码字符集
				String _input_charset = AlipayConfig.input_charset;
				//签名方式
				String sign_type = AlipayConfig.sign_type;
				//服务器异步通知页面路径
				String notify_url = AlipayConfig.notify_url;
				//支付类型
				String payment_type = AlipayConfig.payment_type;
				//卖家支付宝账号
				String seller_id = AlipayConfig.seller_id;
				//商品名称
				String subject = DetailOrderFuc.getDetailOrderForGoodsName(order_id);
				
				//商品详情
				String body = DetailOrderFuc.getDetailOrderForGoodsNameBody(order_id);
				
				//总金额
				String total_fee = String.valueOf(goodsorder.getTatal_amount());
				//
				String it_b_pay = AlipayConfig.it_b_pay;
				//RSA签名所需要的内容
				String content = getOrderInfo(subject,body,order_id,total_fee);
				System.out.println(content+"==");
				String sign = SignUtils.sign(content,AlipayConfig.private_key);
				System.out.println(sign+"++++++++++++++++++++++++++++++");
				//对sign做Base64编码
				//sign = Base64.encode(sign.getBytes());
				// 仅需对sign 做URL编码
				/*subject = URLEncoder.encode(subject, "UTF-8");
				body = URLEncoder.encode(body, "UTF-8");*/
				//sign = URLEncoder.encode(sign, "UTF-8");
				subject = URLEncoder.encode(subject, "UTF-8");
				body = URLEncoder.encode(body, "UTF-8");
				sign = URLEncoder.encode(sign, "UTF-8");
				String content1 = getOrderInfo2(subject,body,order_id,total_fee);
				payInfo = "sign=" + sign + "&sign_type="+AlipayConfig.sign_type+"&"+content1;
			}
			
		}
		
		getResponse().sendRedirect("/webapp/alipaymobile.html?"+payInfo);
		return NONE;
	}
	
    
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String body, String out_trade_no,String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + AlipayConfig.seller_id + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + AlipayConfig.notify_url + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service="+ "\"" + AlipayConfig.service + "\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type="+"\""+ AlipayConfig.payment_type +"\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset="+ "\"" + AlipayConfig.input_charset + "\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=" + "\"" + AlipayConfig.it_b_pay + "\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		//orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	public String getOrderInfo2(String subject, String body, String out_trade_no,String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + AlipayConfig.partner;

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + AlipayConfig.seller_id;

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + out_trade_no;

		// 商品名称
		orderInfo += "&subject=" + subject;

		// 商品详情
		orderInfo += "&body=" + body;

		// 商品金额
		orderInfo += "&total_fee=" + price;

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + AlipayConfig.notify_url;

		// 服务接口名称， 固定值
		orderInfo += "&service=" + AlipayConfig.service;

		// 支付类型， 固定值
		orderInfo += "&payment_type=" + AlipayConfig.payment_type;

		// 参数编码， 固定值
		orderInfo += "&_input_charset=" + AlipayConfig.input_charset;

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=" + AlipayConfig.it_b_pay;

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		//orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	//通过微信服务器传过来的数据 再次进行签名
	 public String wxAPPsign(String prepayid_n,String timestamp_n,String noncestr_n) throws IllegalAccessException{
	        
	        Map<String, Object> signParams = new HashMap<String, Object>();
	    	signParams.put("appid", ConfigureApp.getAppid());
	    	signParams.put("noncestr", noncestr_n);
	    	signParams.put("package", "Sign=WXPay");
	    	signParams.put("partnerid", ConfigureApp.getMchid());
	    	signParams.put("prepayid", prepayid_n);
	    	signParams.put("timestamp", timestamp_n);
	        //根据API给的签名规则再次进行签名
	        String sign = Signature.getSignapp(signParams);	
	        return sign;
	    }
	/**
	 * 手机首页获取倒计时时间
	 * @throws Exception 
	 */
	public String indexDJSDate(String req_tab_number) throws Exception {
		String timenumber="1";
		Map nMap=new HashMap();
		nMap.put("is_del", "1");
		nMap.put("tab_number", req_tab_number);
		nMap.put("is_up", "0");
		goodsList = this.navigationService.getWebList(nMap);
        if(goodsList!=null&&goodsList.size()>0){
        	Map gMap=new HashMap();
        	gMap=(HashMap)goodsList.get(0);
        	String goods_id="";
        	if(gMap!=null&&!"".equals(gMap.get(""))){
        		goods_id=gMap.get("goods_id").toString();
        		goods=goodsService.get(goods_id);
        		String member_level = "";
    			if(!ValidateUtil.isRequired(this.session_cust_id)) {
    				//获取会员对象
    				member = this.memberService.get(this.session_cust_id);
    				member_level = member.getBuy_level();
    			}
    			//判断商品是否有做促销活动
    			String s_id = SalegoodsFuc.getSaleId(goods, "1", member_level);
    			//判断商品是否有做促销活动
    			if(!ValidateUtil.isRequired(s_id)){
    				String[] s_ids = s_id.trim().split(",");
    				//从商品参加的活动中 提取出倒计时时间
    				for(int i=0;i<s_ids.length;i++){
    					salegoods = this.salegoodsService.get(s_ids[0]);
        				//是否限时倒计时
        				limit_time = salegoods.getLimit_time();
        				if("1".equals(limit_time)){
        					//获取倒计时时间秒数
            				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            				if(salegoods.getEnd_time()!=null){
            					Date date = sd.parse(salegoods.getEnd_time());
            					difftime =date.getTime() - new Date().getTime();
            					if(0 > difftime){
            						timeout = "1";
            					}
            				}
            				break;
        				}
        				
    				}
    				
    			}
        	}
        }
        if(difftime!=0.0){
        	timenumber=String.valueOf(difftime);
        }
        return timenumber;
	}
	
	public void prepare() throws Exception {
		// 初始化StringBuilder
		postsb = new StringBuilder();
	}
	public String getCat_attr_top_s() {
		return cat_attr_top_s;
	}

	public void setCat_attr_top_s(String cat_attr_top_s) {
		this.cat_attr_top_s = cat_attr_top_s;
	}

	public String getCatEn_name() {
		return catEn_name;
	}

	public void setCatEn_name(String catEn_name) {
		this.catEn_name = catEn_name;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public String downcount_statistics_android() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String type = request.getParameter("type");
		String var_name = type+"_downcount";
		Sysconfig mSysconfig = sysconfigService.getWebname(var_name);
		if(mSysconfig!=null){
			HashMap map = new HashMap();
			String var_value = mSysconfig.getVar_value();
			int count = Integer.parseInt(var_value);
			count++;
			String reVar_value = count+"";
			map.put("var_name", var_name);
			map.put("var_value", reVar_value);
			sysconfigService.updateByvarname(map);
		}
		return "success";
	}
}
