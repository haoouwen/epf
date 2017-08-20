package com.rbt.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.sf.json.JSONObject;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Md5;
import com.rbt.function.AreaFuc;
import com.rbt.function.RefundappFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Area;
import com.rbt.model.Goodsorder;
import com.rbt.model.Kjtrecall;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.service.IAreaService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IKjtrecallService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
@Controller
public class KjtapiAction extends AdminBaseAction implements Preparable {
	private static final long serialVersionUID = 1027085257799611267L;
	public Goodsorder goodsorder;
	public 	Memberuser memberuser;
	public Area area;
	@Autowired
	private IGoodsorderService goodsorderService;
	public Orderdetail Orderdetail;
	@Autowired
	private IOrderdetailService orderdetailService;
	@Autowired
	private IAreaService areaService;
	public Kjtrecall kjtrecall;
	@Autowired
	private IKjtrecallService kjtrecallService;
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	private IOrdertransService ordertransService;
	//订单ID
	public String goodsorderid;
	public String recallstatus;
	public List memberuserList;

	public void kjtjson() throws Exception{
		
		//获取脚本传递的orderid
		String orderid=this.getRequest().getParameter("orderid");
		goodsorder = goodsorderService.get(orderid);
		//AJAX获取操作获取关键字
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//判断当前是否已经提交跨境通
		recallstatus = goodsorder.getRecallstatus();
		if("1".equals(recallstatus)){
			out.print("1");	
			 return;
		}
		//更新是否提交跨境通
		goodsorder.setRecallstatus("1");
		goodsorderService.update(goodsorder);
		//获取跨境通参数配置
	   String appid =  SysconfigFuc.getSysValue("cfg_kjtappid");//跨境通appid
	   String secretkey =  SysconfigFuc.getSysValue("cfg_kjtsecretkey");//跨境通秘钥
	   String channelid =  SysconfigFuc.getSysValue("cfg_channelid");//跨境通渠道编号
	   String depotid =  SysconfigFuc.getSysValue("cfg_depotid");//跨境通仓库编号
	   String posttax =  SysconfigFuc.getSysValue("cfg_posttax");//跨境通行邮税总金额
	   String poundage =  SysconfigFuc.getSysValue("cfg_poundage");//下单支付产生的手续费
	   String logistics =  SysconfigFuc.getSysValue("cfg_logistics");//跨境通物流公司编号
	   String kjtdefaultmail =  SysconfigFuc.getSysValue("cfg_kjtdefaultmail");//默认邮件地址
		
		//返回值
		String  recallstr="";
		if(goodsorder!=null){
		//提交给跨境通参数
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SaleChannelSysNo", channelid);                               //渠道编号
		map.put("MerchantOrderID", goodsorder.getOrder_id());                                //订单编号kjt平台创建一个订单
		map.put("WarehouseID", depotid);                                           //订单出库编号,仓库编号
		
		//订单支付信息
		Map PayInfo = new HashMap();
		double goods_amount= goodsorder.getGoods_amount();
		goods_amount = goods_amount * Double.valueOf("0.8");
		PayInfo.put("ProductAmount", goods_amount);         //商品总金额（保留2位小数）
		PayInfo.put("ShippingAmount", goodsorder.getShip_free());        //运费总金额（保留2位小数）
		PayInfo.put("TaxAmount", posttax);                 //商品行邮税总金额（保留2位小数）
		PayInfo.put("CommissionAmount", poundage);   //下单支付产生的手续费（保留2位小数）
	
		//支付方式111: 东方 支付 112: 支付宝 11 4: :财付通 117:银联支付 118 : 微信支付
		if("8".equals(goodsorder.getPay_id())||"10".equals(goodsorder.getPay_id())){
			PayInfo.put("PayTypeSysNo", "114"); 
		}else if("11".equals(goodsorder.getPay_id())){
			PayInfo.put("PayTypeSysNo", "117"); 
		}else if("12".equals(goodsorder.getPay_id())){
			PayInfo.put("PayTypeSysNo", "118"); 
		}else{
			PayInfo.put("PayTypeSysNo", "114"); 
		}
		PayInfo.put("PaySerialNumber", goodsorder.getPay_trxid());        //支付流水号
		map.put("PayInfo", PayInfo);
		
		//获取地区
		String  areastr =  AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		if(goodsorder.getArea_attr().contains("9554517676")){
			//天津
			areastr+="河北省,"+areastr;
		}else if(goodsorder.getArea_attr().contains("1881644855")){
			//北京
			areastr+="河北省,"+areastr;
		}else if(goodsorder.getArea_attr().contains("5556783571")){
			//上海
			areastr+="江苏省,"+areastr;
		}else if(goodsorder.getArea_attr().contains("3779153854")){
			//重庆
			areastr+="四川省,"+areastr;
		}
		
		//获取地区最后级别id
		int arealength = goodsorder.getArea_attr().length();
		String areastrlevel =  goodsorder.getArea_attr().substring(arealength-10,arealength);
		area = areaService.get(areastrlevel);
		
		//订单配送信息
		Map ShippingInfo = new HashMap();
		ShippingInfo.put("ReceiveName", goodsorder.getConsignee());               //收件人姓名
		ShippingInfo.put("ReceivePhone", goodsorder.getMobile());                    //收件人电话
		ShippingInfo.put("ReceiveAddress", goodsorder.getBuy_address());         //收件人收货地址 
		ShippingInfo.put("ReceiveAreaCode", area.getKjtareaid());                       //收货地区编号
		ShippingInfo.put("ShipTypeID", logistics);                                                         //物流公司编号 kjt提供
		ShippingInfo.put("ReceiveAreaName",areastr);                                       //收件省市区名称
		map.put("ShippingInfo", ShippingInfo);
		
		
		//如果下单客户有自己的邮箱，采用客户邮箱
		Map usermap = new HashMap();
		usermap.put("cust_id", goodsorder.getBuy_cust_id());
		memberuserList = memberuserService.getList(usermap);
		if(memberuserList!=null && memberuserList.size()>0){
			HashMap mapvalue = (HashMap)memberuserList.get(0);
			if(mapvalue.get("email")!=null && !"".equals(mapvalue.get("email"))){
				String email = mapvalue.get("email").toString();
				kjtdefaultmail = email;
			}
		}
		//下单用户实名认证信息
		Map AuthenticationInfo = new HashMap();
		AuthenticationInfo.put("Name", goodsorder.getConsignee());                       //下单用户真实姓名
		AuthenticationInfo.put("IDCardType", 0);                                                      //下单用户证件类型
		AuthenticationInfo.put("IDCardNumber",  goodsorder.getIdentitycard());     //下单用户名证件编码
		AuthenticationInfo.put("PhoneNumber", goodsorder.getMobile());               //下单用户联系电话
		AuthenticationInfo.put("Email", kjtdefaultmail);                                           //下单用户电子邮件
		AuthenticationInfo.put("Address", goodsorder.getBuy_address());                 //下单用户联系地址
		map.put("AuthenticationInfo", AuthenticationInfo);
		
		//获取订单商品列表
		double taxprice=0.00;
		Map ordermap = new HashMap();
		ordermap.put("order_id", goodsorder.getOrder_id());
		List orderdetailList = orderdetailService.getList(ordermap);
		int ordersize = orderdetailList.size();
		//一个订单多个商品循环数组加入map
		if(orderdetailList!=null &&ordersize>0){
			Map[] ItemList = new Map[ordersize]; 
			for(int i=0;i<orderdetailList.size();i++){
				HashMap mapvalue = (HashMap)orderdetailList.get(i);
				ItemList[i] = new HashMap(); 
				ItemList[i].put("ProductID", mapvalue.get("kjt_id").toString());
				ItemList[i].put("Quantity", mapvalue.get("order_num").toString());
				//通过计算商品单价 ，减掉 优惠券 红包 订单优惠的 实际商品单价
				String subTotal_str = RefundappFuc.getSubTotal(goodsorder.getOrder_id(), mapvalue.get("trade_id").toString());
				double subTotal = Double.parseDouble(subTotal_str) * Double.valueOf("0.8");
				ItemList[i].put("SalePrice", subTotal);
				//计算税
				taxprice = Double.parseDouble(mapvalue.get("goods_price").toString()) * Double.parseDouble(mapvalue.get("tax_rate").toString())/100;
				System.out.println("===========kjtapi=============="+taxprice);
				BigDecimal bd = new BigDecimal(taxprice);  
				taxprice=bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				System.out.println("===========kjtapi=======BigDecimal======="+taxprice);
				ItemList[i].put("TaxPrice", taxprice);
			}
			map.put("ItemList", ItemList);
		}

		//map转换为json格式
		JSONObject jsObj = JSONObject.fromObject(map);  
		String datajson = jsObj.toString();
		String   mydata   =   java.net.URLEncoder.encode(datajson,"utf-8"); 
		
		//4位随机数
		int nonce = (int) (Math.random()*9000+1000);
		//时间搓
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHmmss");//可以方便地修改日期格式
		String timestamp = dateFormat.format( now ); 
		//提交的参数串
		String signdata =	"appid="+ appid +"&data="+ mydata +"&format=json&method=order.socreate&nonce="+ nonce+"&timestamp="+ timestamp +"&version=1.0&"+secretkey;
		//MD5加密生成签名
		String sign = md5code(signdata);
		String param =	signdata + "&sign="+sign;
		String url = "http://api.kjt.com/open.api"; 
		//url和param参数post提交，返回json。
		recallstr = JsonDate(url,param);
		System.out.println(recallstr);
		//返回值转化为json
		JSONObject jsondata = JSONObject.fromObject(recallstr);  
		JSONObject  Data=jsondata.getJSONObject("Data");
		//获取状态值
		String Code = jsondata.getString("Code");
		String Desc = jsondata.getString("Desc");
		if("2".equals(Code)){
			goodsorder.setIs_kjtsuccess("2");
			goodsorder.setFalsereason(Desc);
			goodsorder.setRecallstatus("0");
			goodsorderService.update(goodsorder);
		}
		
		if("0".equals(Code)&&"SUCCESS".equals(Desc)){
		//获取kjt系统订单号
		String SOSysNo=	SOSysNo=Data.getString("SOSysNo");
		//获取商家订单号
		String MerchantOrderID=MerchantOrderID=Data.getString("MerchantOrderID");
		//获取商品总金额
		String ProductAmount=ProductAmount = Data.getString("ProductAmount");
		//kjt计算的税费总金额
		String TaxAmount=TaxAmount = Data.getString("TaxAmount");
		//kjt计算的运费总金额
		String ShippingAmount = Data.getString("ShippingAmount");
		kjtrecall = new Kjtrecall();
		kjtrecall.setSosysno(SOSysNo);
		kjtrecall.setOrder_id(orderid);
		kjtrecall.setTatal_amount(Double.toString(goods_amount));
		kjtrecall.setTaxes(String.valueOf(taxprice));
		kjtrecall.setShip_free(goodsorder.getShip_free().toString());
		kjtrecall.setMerchantorderid(MerchantOrderID);
		kjtrecall.setProductamount(ProductAmount);
		kjtrecall.setTaxamount(TaxAmount);
		kjtrecall.setShippingamount(ShippingAmount);
		kjtrecall.setSostatus("0");
		kjtrecallService.insert(kjtrecall);
		//更新提交跨境通状态
		goodsorder.setCustoms_type("002");//设置海关类型：002-跨境通
		goodsorder.setIs_kjtsuccess("1");
		goodsorder.setKjtorder_state("0");
		goodsorder.setRecallstatus("0");
		goodsorderService.update(goodsorder);
		//插入订单异动
		ordertransService.inserOrderTran(orderid, session_cust_id, session_user_id, "提交海关", goodsorder.getOrder_state(), session_user_name);
		}
	   }
		//更新是否提交跨境通
		out.print(recallstr);	
	}
	
	
	public String md5code(String mydata){
		return Md5.getMD5(mydata.getBytes());
	}
	
	public String JsonDate(String url,String param) throws Exception {
		StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection(); 
            uc.setDoOutput(true);  
            uc.setDoInput(true);  
            PrintWriter out = null;  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(uc.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return json.toString();
	}
	
	
	
}   
	
	 
	

	

	
	
	
    

	
	

	


