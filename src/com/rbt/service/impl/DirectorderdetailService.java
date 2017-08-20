/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: DirectorderdetailService.java 
 */
package com.rbt.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.browseengine.bobo.api.BrowseException;
import com.rbt.common.Md5;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.dao.IBuyeraddrDao;
import com.rbt.dao.IDirectladderDao;
import com.rbt.dao.IDirectorderdetailDao;
import com.rbt.dao.IDirectsellDao;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsevalDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IOrdertransDao;
import com.rbt.dao.IRefundappDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.StockFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Directsell;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goods;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Memberfund;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Refundapp;
import com.rbt.model.Sellerscore;
import com.rbt.model.Shopconfig;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.ISysfundService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 预售订单商品详细信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:26:38 CST 2014
 */
@Service
public class DirectorderdetailService extends GenericService<Directorderdetail,String> implements IDirectorderdetailService {
	
	IDirectorderdetailDao directorderdetailDao;
    @Autowired
	private IMemberuserDao memberuserDao;
	@Autowired
	private IBuyeraddrDao buyeraddrDao;
	@Autowired
	private IDirectladderDao directladderDao;
	@Autowired
	private IDirectsellDao directsellDao;
	@Autowired
	private IGoodsDao goodsDao;
	@Autowired
	private IShopconfigDao shopconfigDao;
	@Autowired
	private IGoodsorderDao goodsorderDao;
	@Autowired
	private IOrderdetailDao orderdetailDao;
	@Autowired
	private IOrdertransDao ordertransDao;
	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IMemberinterDao memberinterDao;
	@Autowired
	private IFundhistoryDao fundhistoryDao;
	@Autowired
	private IGoodsevalDao goodsevalDao;
	@Autowired
	private IRefundappDao refundappDao;
	@Autowired
	private IInterhistoryDao interhistoryDao;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	public DirectorderdetailService(IDirectorderdetailDao directorderdetailDao) {
		super(directorderdetailDao);
		this.directorderdetailDao = directorderdetailDao;
	}
	public Directorderdetail getByOrderId(String order_id){
		return  (Directorderdetail) this.directorderdetailDao.getByOrderId(order_id);
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 12:50:31 PM
	 * @Method Description：构建店铺列表和商品列表
	 */
	public Map createList(Map StrMap){
		String cust_id_str=StrMap.get("cust_id_str").toString();
		String goods_id_str=StrMap.get("goods_id_str").toString();
		String goods_length_str=StrMap.get("goods_length_str").toString();
		String goods_name_str=StrMap.get("goods_name_str").toString();
		String goods_img_str=StrMap.get("goods_img_str").toString();
		String spec_id_str=StrMap.get("spec_id_str").toString();
		String spec_name_str=StrMap.get("spec_name_str").toString();
		String sale_price_str=StrMap.get("sale_price_str").toString();
		String give_inter_str=StrMap.get("give_inter_str").toString();
		String order_num_str=StrMap.get("order_num_str").toString();
		String shop_name_str=StrMap.get("shop_name_str").toString();
		String radom_no_str=StrMap.get("radom_no_str").toString();
		String shop_qq_str=StrMap.get("shop_qq_str").toString();
		String earnest=StrMap.get("earnest").toString();
		String direct_id=StrMap.get("direct_id").toString();
		String session_cust_id=StrMap.get("session_cust_id").toString();
		String[] cust_id = cust_id_str.split(",");
		String[] user_name=new String[cust_id.length];
		String[] goods_id = goods_id_str.split(",");
		String[] goods_length = goods_length_str.split(",");
		String[] goods_name = goods_name_str.split(",");
		String[] goods_img = goods_img_str.split(",");
		String[] spec_id = spec_id_str.split(",");
		String[] spec_name = spec_name_str.split(",");
		String[] sale_price = sale_price_str.split(",");
		String[] give_inter = give_inter_str.split(",");
		String[] order_num = order_num_str.split(",");
		String[] shop_name = shop_name_str.split(",");
		String[] radom_no = radom_no_str.split(",");
		String[] shop_qq = shop_qq_str.split(",");
		if(cust_id_str!=null&&!cust_id_str.equals("")){
			for(int i=0;i<cust_id.length;i++){
				user_name[i]=this.memberuserDao.getPKByCustID(cust_id[i]).getUser_name();
			}
		}
		List shopList=new ArrayList();
		List orderList=new ArrayList();
		int x= 0,y = 0;
		for(int i = 0; i < cust_id.length; i++){
			Map shopMap = new HashMap();
			shopMap.put("shop_cust_id", cust_id[i]);
			shopMap.put("shop_name", shop_name[i]);
			shopMap.put("shop_qq", shop_qq[i]);
			shopMap.put("goods_length", goods_length[i]);
			shopMap.put("user_name", user_name[i].trim());
			shopMap.put("radom_no", radom_no[i].trim());
			shopList.add(shopMap);	
			if(i == 0){
				x = 0;
			}else{
				x += Integer.parseInt(goods_length[i-1].trim());//
			}
			y = Integer.parseInt(goods_length[i].trim()) + x;//
			for(int j = x; j < y; j++){
				Map orderMap = new HashMap();
				orderMap.put("cust_id", cust_id[i]);
				orderMap.put("goods_id", goods_id[j].trim());
				orderMap.put("goods_name", goods_name[j]);
				orderMap.put("goods_img", goods_img[j]);
				orderMap.put("spec_id", spec_id[j]);
				orderMap.put("spec_name", spec_name[j]);
				orderMap.put("sale_price", sale_price[j].trim());
				orderMap.put("earnest",earnest);
				orderMap.put("give_inter", give_inter[j]);
				orderMap.put("order_num", order_num[j].trim());
				orderMap.put("direct_id",direct_id);
				orderList.add(orderMap);	
			}
				
		}
		//获取买家收货地址
	   	List addrList=getBuyerAddrList(session_cust_id);
		Map map=new HashMap();
		map.put("shopList", shopList);
		map.put("orderList", orderList);
		map.put("addrList", addrList);
		return map;
	}
	
	/**
	 * @author : HZX
	 * @date : Jul 23, 2014 9:24:54 AM
	 * @Method Description :获取用户的收货地址
	 */
	public List getBuyerAddrList(String session_cust_id){
		Map addrMap = new HashMap();
		addrMap.put("cust_id", session_cust_id);
		List addrList = this.buyeraddrDao.getList(addrMap);
		addrList = ToolsFuc.replaceList(addrList,"");
	    return addrList;
	}
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 1:27:07 PM
	 * @Method Description：提交订单（实物商品）
	 */
	public String subOrder(Map map)throws IOException, ParseException, BrowseException{
		HttpServletResponse response=ServletActionContext.getResponse();
		String cust_id_str=map.get("cust_id_str").toString();
		String goods_id_str=map.get("goods_id_str").toString();
		String goods_name_str=map.get("goods_name_str").toString();
		String goods_img_str = map.get("goods_img_str").toString();
		String goods_length_str=map.get("goods_length_str").toString();
        String spec_id_str=map.get("spec_id_str").toString();
        String spec_name_str=map.get("spec_name_str").toString();
        String order_num_str=map.get("order_num_str").toString();
//        String mem_remark_str=map.get("mem_remark_str").toString();
        String end_area_attr=map.get("end_area_attr").toString();
        String earnest=map.get("earnest").toString();
        String direct_id=map.get("direct_id").toString();
        String addr_id=map.get("addr_id").toString();
        String session_cust_id=map.get("session_cust_id").toString();
        String session_user_id=map.get("session_user_id").toString();
        String endpay_time=map.get("endpay_time").toString();
        String end_time=map.get("end_time").toString();
		String[] cust_id = cust_id_str.split(",");
		String[] goods_id = goods_id_str.split(",");
		String[] goods_name= goods_name_str.split(",");//预售商品标题
		String[] goods_img = goods_img_str.split(",");//预售商品图片
		String[] goods_length = goods_length_str.split(",");
		String[] spec_id = spec_id_str.split(",");
		String[] spec_name = spec_name_str.split(",");
		String[] sale_price = new String[goods_id.length];
		String[] give_inter = new String[goods_id.length];
		String[] order_num = order_num_str.split(",");
		String[] goods_amount = new String[cust_id.length];
		String[] shop_total_amount = new String[cust_id.length];
		String[] ship_free = new String[goods_id.length];
		String[] smode_id = new String[goods_id.length];
//		String[] mem_remark = mem_remark_str.split(",");
		String[] end_area=end_area_attr.split(",");
		String valuation_mode;
		double ear=Double.parseDouble(earnest);
		if(direct_id==null&&direct_id.equals("")){
			return "1";
		}
		Directsell  directsell =this.directsellDao.get(direct_id);
		String direct_cust_id=directsell.getCust_id();
		String direct_goods_id=directsell.getGoods_id();
		if(!direct_cust_id.equals(cust_id[0].trim())){
			response.sendRedirect("/login.html");//异常情况重新登录
			return "1";
		}
		sale_price[0]=getdirectPrice(direct_id);
		give_inter[0]=getdirectPrice(direct_id);
		ear=directsell.getEarnest();
		shop_total_amount[0]=(Double.parseDouble(sale_price[0])*Double.parseDouble(order_num[0])+"").trim();
		goods_amount[0]=shop_total_amount[0];
		//运费集合
		List shipList = new ArrayList();
		Goods goods = this.goodsDao.get(direct_goods_id);
		if(goods.getIs_ship().equals("0")){
			ship_free[0]="0";
			smode_id[0]="0";
		}else{
			if(goods != null && !goods.getShip_id().equals("")){
				    shipList =goodsService.dealShipPrice(goods.getIs_ship(),goods.getShip_id(), order_num[0], goods.getVolume(), goods.getLogsweight(), end_area_attr);
					Map ship_freeMap=(Map) shipList.get(0);
					ship_free[0]=ship_freeMap.get("ship_price").toString();
					smode_id[0]=ship_freeMap.get("smode_id").toString();
			}else{
				ship_free[0]="0";
				smode_id[0]="0";
			}
		}
		Buyeraddr buyeraddr = new Buyeraddr();
		//实例化收货地址对象
		if(addr_id != null && !addr_id.equals("")){
			buyeraddr = this.buyeraddrDao.get(addr_id);
		}
		//生成订单
		int x= 0,y = 0;
		//订单号
		String order_id = "";
		String order_type = "6";//预售订单
		String order_id_str="";
		if(map.get("order_id_str")!=null){
			order_id_str=map.get("order_id_str").toString();
		}
		for(int i = 0; i < cust_id.length; i++){
			// 按照日期生成随机数作为订单号
			order_id = RandomStrUtil.getDateRand()+RandomStrUtil.getNumberRand();
			//临时存储订单号
			if(order_id_str != null && !order_id_str.equals("")){
				order_id_str = order_id_str +","+ order_id;
			}else{
				order_id_str = order_id;
			}
			//店铺佣金
			Double comm_free = 0.00;
			//生成订单
			Goodsorder goodsorder = new Goodsorder();
			goodsorder.setBuy_cust_id(session_cust_id);
			goodsorder.setSell_cust_id(cust_id[i]);
			//goodsorder.setGoods_amount(Double.parseDouble(goods_amount[i]));
			goodsorder.setGoods_amount(Double.parseDouble(shop_total_amount[i]));
			//goodsorder.setTatal_amount(Double.parseDouble(shop_total_amount[i])+Double.parseDouble(ship_free[i]));
			goodsorder.setTatal_amount(Double.parseDouble(shop_total_amount[i])+Double.parseDouble(ship_free[i]));
			goodsorder.setSend_mode(smode_id[i]);
			goodsorder.setShip_free(Double.parseDouble(ship_free[i]));
			goodsorder.setOrder_state("1");
			goodsorder.setDeliver_state("0");
			goodsorder.setOrder_id(order_id);
			goodsorder.setComm_free(comm_free);
			goodsorder.setOrder_type(order_type);//订单类型
			goodsorder.setIs_virtual("1");//实物商品
//			goodsorder.setMem_remark(mem_remark[i]);
			goodsorder.setInfo_status("0");
			goodsorder.setIs_eval("0");
			//收货地址相关
			goodsorder.setConsignee(buyeraddr.getConsignee());
			goodsorder.setArea_attr(buyeraddr.getArea_attr());
			goodsorder.setBuy_address(buyeraddr.getAddress());
			goodsorder.setZip_code(buyeraddr.getPost_code());
			goodsorder.setTelephone(buyeraddr.getPhone());
			goodsorder.setMobile(buyeraddr.getCell_phone());
			this.goodsorderDao.insert(goodsorder);
			if(i == 0){
				x = 0;
			}else{
				x += Integer.parseInt(goods_length[i-1].trim());//
			}
			y = Integer.parseInt(goods_length[i].trim()) + x;//
			for(int j = x; j < y; j++){
				//生成订单详情
				/*Orderdetail orderdetail = new Orderdetail();
				orderdetail.setOrder_id(order_id);
				orderdetail.setGoods_attr(spec_name[j]);
				orderdetail.setGoods_price(Double.parseDouble(sale_price[j]));
				orderdetail.setGoods_id(goods_id[j]);
				orderdetail.setOrder_num(order_num[j]);
				this.orderdetailDao.insert(orderdetail);*/
				//更新商品库存
				//updateStock('6', direct_id, goods_id[j], spec_id[j], Integer.parseInt(order_num[j].trim()));
				//生成订单详情
				Directorderdetail directorderdetail = new Directorderdetail();
				directorderdetail.setOrder_id(order_id);
				directorderdetail.setGoods_attr(spec_name[j]);
				directorderdetail.setGoods_price(Double.parseDouble(sale_price[j]));
				directorderdetail.setGoods_id(goods_id[j]);
				directorderdetail.setTemporary_title(goods_name[j]);//存入临时预售商品标题
				directorderdetail.setTemporary_img(goods_img[j]);//存入临时预售商品图片
				directorderdetail.setOrder_num(order_num[j]);
				directorderdetail.setEndpay_time(endpay_time);
				directorderdetail.setPay_state("0");
				directorderdetail.setEarnest(ear);
				directorderdetail.setDirect_id(direct_id);
				directorderdetail.setEnd_time(end_time);
				this.directorderdetailDao.insert(directorderdetail);
			}
			// 插入订单异动记录
			Ordertrans ordertrans = new Ordertrans();
			ordertrans.setOrder_id(order_id);
			ordertrans.setCust_id(session_cust_id);
			ordertrans.setUser_id(session_user_id);
			ordertrans.setOrder_state("1");
			ordertrans.setReason("买家下订单");
			this.ordertransDao.insert(ordertrans);	
			//发送信息提醒
			String sumstr = Double.toString(ear);
			mestipByBuyer("3",order_id,sumstr);
	 }
	   return order_id_str;	
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 16, 2014 11:22:36 AM
	 * @Method Description :提交虚拟订单
	 */
	public String subVirtualOrder(Map map) throws IOException, ParseException, BrowseException{
		String cust_id_str=map.get("cust_id_str").toString();
		String goods_amount_str=map.get("goods_amount_str").toString();
		String session_cust_id=map.get("session_cust_id").toString();
		String mem_remark_str=map.get("mem_remark_str").toString();
		String session_user_id=map.get("session_user_id").toString();
		String spec_name_str=map.get("spec_name_str").toString();
		String sale_price_str=map.get("sale_price_str").toString();
		String goods_id_str=map.get("goods_id_str").toString();
		String order_num_str=map.get("order_num_str").toString();
		String spec_id_str=map.get("spec_id_str").toString();
		// 按照日期生成随机数作为订单号
		String order_id = RandomStrUtil.getDateRand()+RandomStrUtil.getNumberRand();
		//用于传递至支付页面
		String order_id_str = order_id;
		//获取店铺配置
		Shopconfig sc= this.shopconfigDao.getByCustID(cust_id_str);
		
		//生成订单
		Goodsorder goodsorder = new Goodsorder();
		goodsorder.setBuy_cust_id(session_cust_id);
		goodsorder.setSell_cust_id(cust_id_str);
		goodsorder.setGoods_amount(Double.parseDouble(goods_amount_str));
		goodsorder.setTatal_amount(Double.parseDouble(goods_amount_str));
		goodsorder.setSend_mode("0");
		goodsorder.setShip_free(0.0);
		goodsorder.setOrder_state("1");
		goodsorder.setOrder_id(order_id);
		goodsorder.setOrder_type("0");//虚拟商品
		goodsorder.setIs_virtual("0");//虚拟商品
		goodsorder.setMem_remark(mem_remark_str);
		this.goodsorderDao.insert(goodsorder);
			
		//生成订单详情
		Orderdetail  orderdetail = new Orderdetail();
		orderdetail.setOrder_id(order_id);
		orderdetail.setGoods_attr(spec_name_str);
		orderdetail.setGoods_price(Double.parseDouble(sale_price_str));
		orderdetail.setGoods_id(goods_id_str);
		orderdetail.setOrder_num(order_num_str);
		this.orderdetailDao.insert(orderdetail);
		//更新库存
		//updateStock('0', "", goods_id_str, spec_id_str, Integer.parseInt(order_num_str));
		// 插入订单异动记录
		Ordertrans ordertrans = new Ordertrans();
		ordertrans.setOrder_id(order_id);
		ordertrans.setCust_id(session_cust_id);
		ordertrans.setUser_id(session_user_id);
		ordertrans.setOrder_state("1");
		ordertrans.setReason("买家下订单");
		this.ordertransDao.insert(ordertrans);
		return order_id_str;
	}
	
		/**
		 * @author : HZX
		 * @date : Oct 10, 2014 4:43:43 PM
		 * @Method Description :
		 */
		public String getdirectPrice(String direct_id){
			String directprice="";
			//获取预售价格
			HashMap ladderMap = new HashMap();
			//目前采用一口价交易方式
			ladderMap.put("onlylownum", "1");
			ladderMap.put("direct_id", direct_id);
			List directladderList = this.directladderDao.getList(ladderMap);
			if(directladderList!=null && directladderList.size()>0){
				HashMap laddervalue = (HashMap)directladderList.get(0);
				if(laddervalue!=null && laddervalue.get("price")!=null){
					directprice = laddervalue.get("price").toString();
				}
			}
			return directprice;
		}
		
		/**	
		 * @author : HXK
		 * @param :mes_id：消息提醒模版 order_id：订单ID
		 * @date Mar 11, 2014 2:35:04 PM
		 * @Method Description :前台订单提醒-发送给卖家
		 */
		public void mestipBySeller(String mes_id,String order_id) throws UnsupportedEncodingException{
			Goodsorder gorder = new Goodsorder();
			gorder = goodsorderDao.get(order_id);
			MessageAltFuc mesalt=new MessageAltFuc();
			mesalt.messageAutoSend(mes_id,gorder.getSell_cust_id(),gorder);
		}
		
		/**	
		 * @author : CYC
		 * @param :mes_id：消息提醒模版 order_id：订单ID
		 * @date Mar 11, 2014 2:35:04 PM
		 * @Method Description :前台订单提醒-发送给卖家
		 */
		public void mestipBySeller(String mes_id,String order_id,String sum) throws UnsupportedEncodingException{
			Goodsorder gorder = new Goodsorder();
			gorder = goodsorderDao.get(order_id);
			MessageAltFuc mesalt=new MessageAltFuc();
			mesalt.messageAutoSend(mes_id,gorder.getSell_cust_id(),gorder,sum,"");
		}
		
		/**	
		 * @author : HXK
		 * @param :mes_id：消息提醒模版 order_id：订单ID
		 * @date Mar 11, 2014 2:35:04 PM
		 * @Method Description :前台订单提醒-发送给买家
		 */
		public void mestipByBuyer(String mes_id,String order_id) throws UnsupportedEncodingException{
			Goodsorder gorder = new Goodsorder();
			gorder = goodsorderDao.get(order_id);
			if(gorder != null){
				MessageAltFuc mesalt=new MessageAltFuc();
				mesalt.messageAutoSend(mes_id,gorder.getBuy_cust_id(),gorder);
			}
		}
		
		/**	
		 * @author : CYC
		 * @param :mes_id：消息提醒模版 order_id：订单ID
		 * @date Mar 11, 2014 2:35:04 PM
		 * @Method Description :前台订单提醒-发送给买家
		 */
		public void mestipByBuyer(String mes_id,String order_id,String sum) throws UnsupportedEncodingException{
			Goodsorder gorder = new Goodsorder();
			gorder = goodsorderDao.get(order_id);
			if(gorder != null){
				MessageAltFuc mesalt=new MessageAltFuc();
				mesalt.messageAutoSend(mes_id,gorder.getBuy_cust_id(),gorder,sum,"");
			}
		}
		
		/**
		 * @author : HZX
		 * @date : Jul 23, 2014 9:25:58 AM
		 * @Method Description :更新库存
		 */
		public void updateStock(char order_type, String trade_id, String goods_id, String spec_id, int diff){
			Map map = new HashMap();
			if(trade_id != null && !trade_id.equals("")){
				map.put("trade_id", trade_id);
			}
			if(goods_id != null && !goods_id.equals("")){
				map.put("goods_id", goods_id);		
			}
			if(spec_id != null && !spec_id.equals("")){
				map.put("spec_id", spec_id);
			}
			StockFuc.updateStock(order_type, map, diff);
		}
		
		/**
		 * @author：XBY
		 * @date：Feb 18, 2014 3:50:22 PM
		 * @Method Description：新增收货地址
		 */
		public String addAddr(Map strMap){
			String session_cust_id=strMap.get("session_cust_id").toString();
			String session_user_name=strMap.get("session_user_name").toString();
			String area_attr=strMap.get("area_attr").toString();
			String phone=strMap.get("phone").toString();
			String de_address=strMap.get("de_address").toString();
			String de_consignee=strMap.get("de_consignee").toString();
			String post_code=strMap.get("post_code").toString();
			String cell_phone=strMap.get("cell_phone").toString();
			String addrDiv="";
			//商城保存收货地址最大数
			String cfg_maxaddressnumber = SysconfigFuc.getSysValue("cfg_maxaddressnumber");
			if(session_cust_id != null && !session_cust_id.equals("")){
				Map map = new HashMap();
				map.put("cust_id",session_cust_id);
				int count = this.buyeraddrDao.getCount(map);
				if(count >= Integer.parseInt(cfg_maxaddressnumber)){
					addrDiv="1";
				}else{
					//最后一级地区ID
					String end_area_attr = area_attr.substring(area_attr.lastIndexOf(",")+1, area_attr.length());
					Buyeraddr ba = new Buyeraddr();
					ba.setConsignee(de_consignee);
					ba.setAddress(de_address);
					ba.setPost_code(post_code);
					ba.setPhone(phone);
					ba.setCell_phone(cell_phone);
					ba.setArea_attr(area_attr);
					ba.setCust_id(session_cust_id);
					ba.setUser_id(session_user_name);
					ba.setSel_address("1");
					String addr_id = this.buyeraddrDao.insertGetPk(ba);
					//地区ID转名称
					area_attr = AreaFuc.getAreaNameByMap(area_attr);
					addrDiv = "<div id='"+addr_id+"' class='useAdd useAddclass' onclick='chooseAddr(this)'>"
						 +"<table width='100%' cellspacing='0'>"
				         +"<tr><td width='25%'>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td width='75%'>"+de_consignee+"</td></tr>"
				         +"<tr><td>省&nbsp;&nbsp;&nbsp;&nbsp;份：</td><td>"+area_attr+"</td></tr>"
				         +"<input type='hidden' name='end_area_attr' value='"+end_area_attr+"' />"
				         +"<tr><td>街道地址：</td><td>"+de_address+"</td></tr>"
				         +" <tr><td>手机号码：</td><td>"+cell_phone+"</td></tr>"
				         +"<tr><td>固定电话：</td><td>"+phone+"</td></tr>"
				         +"<tr><td>邮政编码：</td><td>"+post_code+"</td></tr>"
				         +"</table></div>";
					 
					
				}
			}else{
				addrDiv="0";
			}
			return addrDiv;
		}
		/**
		 * @author：XBY
		 * @date：Feb 18, 2014 4:44:52 PM
		 * @Method Description：账户余额支付（余额支付）
		 */
	    public void useNumPay(String[] order_id,String session_cust_id,String session_user_id,Double use_num_pay)throws Exception{
	    	
	    	 Integer cfg_yushouendpaytime=1;//预售尾款结束时间
	    	 if(SysconfigFuc.getSysValue("cfg_yushouendpaytime")!=null&&!"".equals(SysconfigFuc.getSysValue("cfg_yushouendpaytime"))){
	    		 //取系统默认配置预售支付尾款结束时间
	    		 cfg_yushouendpaytime=Integer.parseInt(SysconfigFuc.getSysValue("cfg_yushouendpaytime"));
	    	 }
	    	 for(int i = 0; i < order_id.length ; i++){	
				//更新预售订单付款状态
			    Directorderdetail directorderdetail=this.directorderdetailDao.getByOrderId(order_id[i]);
				//记录预售付款状态 0：未执行付款  1：已付定金 2:已付尾款
				String direPayState=directorderdetail.getPay_state();
				Directsell directsell=directsellDao.get(directorderdetail.getDirect_id());
				//获取倒计时时间秒数
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				
				String flagEndTime="0";//标记付定金时间
				if(directsell.getEnd_time()!=null){
					Date date = sd.parse(directsell.getEnd_time());
					//付定金结束时间-当前时间，结果大于0表示还能继续付定金，结果小于0表示不能继续付定金
					long difftime =date.getTime() - new Date().getTime();
					if(0 > difftime){
						//flagEndTime=0能付定金，flagEndTime=1不能付定金
						flagEndTime = "1";
					}
				}
				
				
				String flagTailTime="0";//标记付尾款时间
				if(directsell.getTail_time()!=null){
					Date date = sd.parse(directsell.getTail_time());
					Calendar calendar = Calendar.getInstance();  
					calendar.setTime(date);   
					//加上系统设置支付尾款有限时间--20141208
					calendar.add(Calendar.DATE, +cfg_yushouendpaytime);
					//获取三天前的日期
					long threedate = calendar.getTime().getTime();
					long difftime =threedate - new Date().getTime();
					if(0 > difftime){
						//flagTailTime=0能付尾款，flagTailTime=1不能付尾款
						flagTailTime = "1";
					}
				}
				
				if(direPayState.equals("0")){
						if("0".equals(flagEndTime)){
							directorderdetail.setPay_state("1");//已付定金
							if(directorderdetail.getEarnest()-directorderdetail.getGoods_price()==0){
								directorderdetail.setPay_state("2");//已尾款
								direPayState="1";
							}
							Map map=new HashMap();
							map.put("stock", directorderdetail.getOrder_num());
							map.put("trade_id", directorderdetail.getDirect_id());
							//执行修改库存动作之前判断  
							if((Integer.parseInt(directsell.getStock())-Integer.parseInt(directorderdetail.getOrder_num()))>=0){
								directsellDao.updateStock(map);   //执行修改库存
							}else{
								//提示库存不够  hong后台控制前台显示不会
							}
						}else{
							//提示支付定金过期
							directorderdetail.setPay_state("3");//已移到action层处理和web层js验证，Service层一般只做业务逻辑处理，数据过滤和判断应该阻止在action里。
							
						}
						
				}else if(direPayState.equals("1")){
					if("0".equals(flagTailTime)){
						directorderdetail.setPay_state("2");//已付尾款
					}else{
						directorderdetail.setPay_state("3");//提示支付尾款过期
						//需要将定金转入卖家账户 待做 ：已移到action层处理和web层js验证,Service层一般只做业务逻辑处理，数据过滤和判断应该阻止在action里。
					}
				}
				this.directorderdetailDao.update(directorderdetail);
				//更新订单状态，交易成功操作
				Goodsorder goodsorder = new Goodsorder();
				goodsorder = this.goodsorderDao.get(order_id[i]);
				if(goodsorder != null){
					//若是虚拟商品，生成消费码
					if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("0")){
						//生成消费码
						String mark_id = RandomStrUtil.getRand("12");
						goodsorder.setMark_id(mark_id);
					}
					//获取当前时间
					String cur_date_str = "";
					SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
					Calendar cal = Calendar.getInstance();
					cur_date_str = format.format(cal.getTime());
					// 修改订单状态值 9:支付定金  2：支付尾款或者是支付完成
					if(direPayState.equals("0")){
						goodsorder.setOrder_state("9");
					}else if(direPayState.equals("1")){
						goodsorder.setOrder_state("2");
					}
					goodsorder.setPay_id("4");//添加付款方式
					goodsorder.setPay_time(cur_date_str);
					this.goodsorderDao.update(goodsorder);
				}
				// 插入订单异动记录
				Ordertrans ordertrans = new Ordertrans();
				ordertrans.setOrder_id(order_id[i]);
				ordertrans.setCust_id(session_cust_id);
				ordertrans.setUser_id(session_user_id);

				if(direPayState.equals("0")){
					ordertrans.setOrder_state("9");
					ordertrans.setReason("定金已支付");
				}else if(direPayState.equals("1")){
					ordertrans.setOrder_state("2");
					ordertrans.setReason("尾款已支付");
				}
				
				this.ordertransDao.insert(ordertrans);
				//处理资金及流水
				fundManage(order_id[i],session_user_id,use_num_pay,"0");
				String sumstr = Double.toString(use_num_pay);
				//若是虚拟商品，使用不懂模板
				if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("0")){
					//发送信息提醒
					mestipByBuyer("6",order_id[i],sumstr);
				}else{
					//发送信息提醒
					mestipByBuyer("4",order_id[i],sumstr);
				}
			}
	   }
	    
	    
	    
	   public static String addDateMinut(String day, int x)//返回的是字符串型的时间，输入的
	            //是String day, int x
	   {   
	          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制  
	  //引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
	  //量day格式一致
	          Date date = null;   
	          try {   
	              date = format.parse(day);   
	          } catch (Exception ex) {   
	              ex.printStackTrace();   
	          }   
	          if (date == null)   
	              return "";   
	          System.out.println("front:" + format.format(date)); //显示输入的日期  
	          Calendar cal = Calendar.getInstance();   
	          cal.setTime(date);   
	          cal.add(Calendar.MINUTE, x);// 24小时制   
	          date = cal.getTime();   
	          System.out.println("after:" + format.format(date));  //显示更新后的日期 
	          cal = null;   
	          return format.format(date);   
	    
	      }  
	    
	    
	   
	    /**
		 * @Method Description :
		 * @author : HZX
		 * @date : Nov 14, 2014 10:48:15 AM
		 * （在线支付）
		 */
	    public void onlinePay(String[] order_id,String session_cust_id,String session_user_id,Double use_num_pay, HttpServletResponse response)throws Exception{
	    	
	    	 Integer cfg_yushouendpaytime=1;//预售尾款结束时间
	    	 if(SysconfigFuc.getSysValue("cfg_yushouendpaytime")!=null&&!"".equals(SysconfigFuc.getSysValue("cfg_yushouendpaytime"))){
	    		 //取系统默认配置预售支付尾款结束时间
	    		 cfg_yushouendpaytime=Integer.parseInt(SysconfigFuc.getSysValue("cfg_yushouendpaytime"));
	    	 }
	    	
	    	for(int i = 0; i < order_id.length ; i++){	
				//更新预售订单付款状态
			Directorderdetail directorderdetail=this.directorderdetailDao.getByOrderId(order_id[i]);
				//记录预售付款状态 0：未执行付款  1：已付定金 2:已付尾款
				String direPayState=directorderdetail.getPay_state();
				Directsell directsell=directsellDao.get(directorderdetail.getDirect_id());
				//获取倒计时时间秒数
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String flagEndTime="0";//标记付定金时间
				if(directsell.getEnd_time()!=null){
					Date date = sd.parse(directsell.getEnd_time());
					//付定金结束时间-当前时间，结果大于0表示还能继续付定金，结果小于0表示不能继续付定金
					long difftime =date.getTime() - new Date().getTime();
					if(0 > difftime){
						//flagEndTime=0能付定金，flagEndTime=1不能付定金
						flagEndTime = "1";
					}
				}
				
				
				String flagTailTime="0";//标记付尾款时间
				if(directsell.getEnd_time()!=null){
					Date date = sd.parse(directsell.getTail_time());
					Calendar calendar = Calendar.getInstance();  
					calendar.setTime(date);   
					//加上系统设置支付尾款有限时间--20141208
					calendar.add(Calendar.DATE, +cfg_yushouendpaytime);
					//获取三天前的日期
					long threedate = calendar.getTime().getTime();
					long difftime =threedate - new Date().getTime();
					if(0 > difftime){
						//flagTailTime=0能付尾款，flagTailTime=1不能付尾款
						flagTailTime = "1";
					}
				}
				
				if(direPayState.equals("0")){
						if("0".equals(flagEndTime)){
							directorderdetail.setPay_state("1");//已付定金
							if(directorderdetail.getEarnest()-directorderdetail.getGoods_price()==0){
								directorderdetail.setPay_state("2");//已尾款
								direPayState="1";
							}
							Map map=new HashMap();
							map.put("stock", directorderdetail.getOrder_num());
							map.put("trade_id", directorderdetail.getDirect_id());
							//执行修改库存动作之前判断  
							if((Integer.parseInt(directsell.getStock())-Integer.parseInt(directorderdetail.getOrder_num()))>=0){
								directsellDao.updateStock(map);   //执行修改库存
							}else{
								//提示库存不够  hong后台控制前台显示不会
							}
						}else{
							//提示支付定金过期
							directorderdetail.setPay_state("3");
						}
						
				}else if(direPayState.equals("1")){
					if("0".equals(flagTailTime)){
						directorderdetail.setPay_state("2");//已付尾款
					}else{
						//提示支付尾款过期
						directorderdetail.setPay_state("3");
						//需要将定金转入卖家账户 待做
					}
				}
				this.directorderdetailDao.update(directorderdetail);
				//更新订单状态，交易成功操作
				Goodsorder goodsorder = new Goodsorder();
				goodsorder = this.goodsorderDao.get(order_id[i]);
				if(goodsorder != null){
					//若是虚拟商品，生成消费码
					if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("0")){
						//生成消费码
						String mark_id = RandomStrUtil.getRand("12");
						goodsorder.setMark_id(mark_id);
					}
					//获取当前时间
					String cur_date_str = "";
					SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
					Calendar cal = Calendar.getInstance();
					cur_date_str = format.format(cal.getTime());
					// 修改订单状态值 9:支付定金  2：支付尾款或者是支付完成
					if(direPayState.equals("0")){
						goodsorder.setOrder_state("9");
					}else if(direPayState.equals("1")){
						goodsorder.setOrder_state("2");
					}
					goodsorder.setPay_time(cur_date_str);
					this.goodsorderDao.update(goodsorder);
				}
				// 插入订单异动记录
				Ordertrans ordertrans = new Ordertrans();
				ordertrans.setOrder_id(order_id[i]);
				ordertrans.setCust_id(session_cust_id);
				ordertrans.setUser_id(session_user_id);

				if(direPayState.equals("0")){
					ordertrans.setOrder_state("9");
					ordertrans.setReason("定金已支付");
				}else if(direPayState.equals("1")){
					ordertrans.setOrder_state("2");
					ordertrans.setReason("尾款已支付");
				}
				
				this.ordertransDao.insert(ordertrans);
				//处理资金及流水
				fundManage(order_id[i],session_user_id,use_num_pay,"1");
				String sumstr = Double.toString(use_num_pay);
				//若是虚拟商品，使用不懂模板
				if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("0")){
					//发送信息提醒
					mestipByBuyer("6",order_id[i],sumstr);
				}else{
					//发送信息提醒
					mestipByBuyer("4",order_id[i],sumstr);
				}
			}
	    	response.sendRedirect("http://www.epff.cc/bmall_Goodsorder_buyorderlist.action");
	   }
	    /**
		 * @author : HZX
		 * @date : Jul 23, 2014 9:27:21 AM
		 * @Method Description :资金处理
		 */
		private void fundManage(String  oid,String session_user_id,Double use_num_pay,String pay_type){
			Goodsorder order = this.goodsorderDao.get(oid);
			//获取买家的ID
			String buy_cust_id = order.getBuy_cust_id();
			
			double i1=0.00;
			//pay_type: 0是余额支付   1是在线支付
			if(pay_type.equals("0")){//会员使用余额支付 资金转移操作
				//买家资金处理	
				i1=memberfundService.outAndInNum(buy_cust_id, use_num_pay,0); 
				//运营商的资金异动
				sysfundService.freezeNum(use_num_pay, 0);
			}else if(pay_type.equals("1")){//会员使用在线支付 资金转移操作
				Memberfund memberfund = this.memberfundService.get(order.getBuy_cust_id());
				i1 = Double.valueOf(memberfund.getFund_num());
				sysfundService.addNumByOnLion(use_num_pay);
			}

            String message = "";			
			//买家的资金异动
			Fundhistory buy_fh = new Fundhistory();
			buy_fh.setBalance(i1);//该会员的余额
			buy_fh.setCust_id(buy_cust_id);
			buy_fh.setFund_in(0.0);
			if(pay_type.equals("0")){
				buy_fh.setFund_out(use_num_pay);
				message = "余额";
			}else if(pay_type.equals("1")){
				buy_fh.setFund_out(0.00);
				message = "在线";
			}
			
			buy_fh.setUser_id(session_user_id);
			buy_fh.setReason("买家为订单号:"+oid+" "+message+"支付"+use_num_pay+"元");
			this.fundhistoryDao.insert(buy_fh);
			
		}
		
		/**	
		 * @author : HXK
		 * @throws Exception 
		 * @date Mar 11, 2014 2:35:04 PM
		 * @Method Description :前台商品评价
		 */
		public void orderEvaluate(Map map)throws Exception{
			HttpServletResponse  response=ServletActionContext.getResponse();
			String  order_id=map.get("order_id").toString();
			Integer orderdetaiCount=Integer.valueOf(map.get("orderdetaiCount").toString()) ;
			String order_goods_id_str=map.get("order_goods_id_str").toString();
			String order_goods_feng_str=map.get("order_goods_feng_str").toString();
			String order_goods_content_str=map.get("order_goods_content_str").toString();
			String session_cust_id=map.get("session_cust_id").toString();
			String session_user_id=map.get("session_user_id").toString();
			String order_delivery_speed=map.get("order_delivery_speed").toString();
			String order_desc=map.get("orderdetaiCount").toString();
			String order_service_attitude=map.get("order_service_attitude").toString();
			String sell_cust_id=map.get("sell_cust_id").toString();
			String is_virtual=map.get("is_virtual").toString();
			if(order_id!=null){
				if(orderdetaiCount!=null&&!orderdetaiCount.equals("0")){
					String order_goods_id_strs[]=order_goods_id_str.split(",");
					String order_goods_feng_strs[]=order_goods_feng_str.split(",");
					String order_goods_content_strs[]=order_goods_content_str.split("##########");
					for(int i=0;i<orderdetaiCount;i++){
						//插入商品评论表
						Goodseval goodseval=new Goodseval();
						goodseval.setCust_id(session_cust_id);
						goodseval.setExplan_content("");
						//截取评论字数为200
						String str_comment="";
						if(order_goods_content_strs[i]!=null){
							if(order_goods_content_strs[i].length()<200){
								str_comment=order_goods_content_strs[i].toString();
							}else {
								str_comment=order_goods_content_strs[i].substring(0,199);
							}
							
						}
						goodseval.setG_comment(str_comment);
						goodseval.setG_eval(order_goods_feng_strs[i]);
						goodseval.setGoods_id(order_goods_id_strs[i]);
						goodseval.setIs_enable("0");
						goodseval.setIs_two("0");
						goodseval.setUser_id(session_user_id);
						goodsevalDao.insert(goodseval);
					}
					//插入店铺评分
					Sellerscore sellerscore =new Sellerscore();
					sellerscore.setDelivery_score(order_delivery_speed);
					sellerscore.setDesc_score(order_desc);
					sellerscore.setService_score(order_service_attitude);
					sellerscore.setGet_cust_id(sell_cust_id);
					sellerscore.setSelf_cust_id(session_cust_id);
					sellerscore.setUser_id(session_user_id);
					//更新订单状态为 已评价
					updateOrderState(order_id,"8",session_cust_id,session_user_id);
					if(is_virtual!=null&&!is_virtual.equals("")){
						response.sendRedirect("/bmall_Goodsorder_buyVirtualList.action");
					}else {
						response.sendRedirect("/bmall_Goodsorder_buyorderlist.action");
					}
				   
				}
			}
		}
		
		/**	
		 * @author : HXK
		 * @throws Exception 
		 * @date Mar 28, 2014 2:35:04 PM
		 * @Method Description :前台商品确认收货
		 */
		public boolean orderConfirmReceipt(String order_id,String pay_password,String session_cust_id,String session_user_id,Double use_num_pay)throws Exception{
			HttpServletResponse  response=ServletActionContext.getResponse();
			if(order_id!=null){
				String buy_cust_id =session_cust_id;
				//校验支付密码
				Map payMap=new HashMap();
				payMap.put("cust_id",buy_cust_id);
				String pwd= Md5.getMD5(pay_password.getBytes());
				payMap.put("pay_passwd", pwd);
				List list =this.memberfundDao.getList(payMap);
				if(list!=null&&list.size()>0){
					//付款到卖家
					sellerFundManage(order_id,use_num_pay,session_user_id);
					//更新订单状态为 已评价
					updateOrderState(order_id,"7",session_cust_id,session_user_id);
					//消息提醒
					mestipByBuyer("1",order_id);
				}else{
					return true;
				}
				response.sendRedirect("/mall-goodsevaluate-"+order_id+".html");
				return false;
			}else {
				return false;
			}
		}
		
		/**	
		 * @author : HXK
		 * @throws Exception 
		 * @date Mar 28, 2014 2:35:04 PM
		 * @Method Description :前台卖家退款申请-同意退款操作
		 */
		public boolean sellerAgreeRefundment(String order_id,String session_cust_id,String session_user_id,String pay_password,Double use_num_pay)throws Exception{
			String seller_cust_id =session_cust_id;
			//校验支付密码
			Map payMap=new HashMap();
			payMap.put("cust_id",seller_cust_id);
			String pwd= Md5.getMD5(pay_password.getBytes());
			payMap.put("pay_passwd", pwd);
			List list =this.memberfundDao.getList(payMap);
			if(list!=null&&list.size()>0){
				//操作退款
				updateRefund(order_id,"","0","1",session_cust_id,session_user_id);
				//付款到买家
				buyFundManage(order_id,session_user_id,use_num_pay);
				//更新订单状态为 退款成功
				updateOrderState(order_id,"5",session_cust_id,session_user_id);
				//消息提醒
				mestipByBuyer("12",order_id);
			}else{
				return true;
			}
			return false;
		}
		
		
		
		/**	
		 * @author : HXK
		 * @throws Exception 
		 * @date Mar 28, 2014 2:35:04 PM
		 * @Method Description :前台买家退款申请-操作
		 */
		public void orderBuyRefundment(String order_id,Refundapp refundapp,String session_cust_id,String session_user_id)throws Exception{
			Goodsorder goodsorder=new Goodsorder();
			goodsorder=goodsorderDao.get(order_id);
			//0:退款中，1：退款成功，2：退款失败
			refundapp.setRefund_state("0");
			refundapp.setInfo_state("0");
			refundapp.setSeller_cust_id(goodsorder.getSell_cust_id());
			refundappDao.insert(refundapp);
			//更新订单状态为 已评价
			updateOrderState(order_id,"4",session_cust_id,session_user_id);
			//消息提醒
			mestipBySeller("5",order_id);
		}
		
		/**	
		 * @author : HXK
		 * @throws Exception 
		 * @date Mar 28, 2014 2:35:04 PM
		 * @Method Description :前台卖家退款申请-不同意退款操作
		 */
		public void sellerDisAgreeRefundment(String order_id,String seller_refund_reason,String session_cust_id,String session_user_id)throws Exception{
			//操作退款
			updateRefund(order_id,seller_refund_reason,"1","2",session_cust_id,session_user_id);
			//更新订单状态为 退款是吧
			updateOrderState(order_id,"6",session_cust_id,session_user_id);
			//消息提醒
			mestipByBuyer("12",order_id);
		}
		
		/**	
		 * @author : CYC
		 * @param :0表示普通订单  1表示预售订单
		 * @date Mar 28, 2014 1:33:55 PM
		 * @Method Description :将资金从运营转入卖家
		 */
		public void sellerFundManage(String  oid,Double use_num_pay,String session_user_id){
			sellerFundManage(oid,use_num_pay,session_user_id,"0");
			
		}
		
		/**	
		 * @author : CYC
		 * @param :0表示普通订单  1表示预售订单
		 * @date Mar 28, 2014 1:33:55 PM
		 * @Method Description :将资金从运营转入卖家
		 */
		public void sellerFundManage(String  oid,Double use_num_pay,String session_user_id,String directorder,String trade_id){
			//订单详情支付状态改成3：付尾款过期
			Directorderdetail directorderdetail = this.get(trade_id);
			if(directorderdetail!=null){
				directorderdetail.setPay_state("3");
				this.update(directorderdetail);
			}
			sellerFundManage(oid,use_num_pay,session_user_id,directorder);
		}
		
		/**	
		 * @author : HXK
		 * @param :
		 * @date Mar 28, 2014 1:33:55 PM
		 * @Method Description :将资金从运营转入卖家
		 */
		public void sellerFundManage(String  oid,Double use_num_pay,String session_user_id,String directorder){
			Goodsorder order = this.goodsorderDao.get(oid);
			if(order!=null){
				Double order_momey=0.0;
				//订单总金额
				order_momey=order.getTatal_amount();
				//获取卖家的ID
				String seller_cust_id = order.getSell_cust_id();
				//运营商资金处理,将金额从运营商中扣除
				if("1".equals(directorder)){
					sysfundService.freezeNum(use_num_pay, 1);
				}else{
					sysfundService.freezeNum(order_momey, 1);
				}
				
				//卖家资金处理
				Double j1 = 0.0;//总金额
				Memberfund seller_mf = this.memberfundDao.get(seller_cust_id);
				if(seller_mf != null){
					if("1".equals(directorder)){
						j1=memberfundService.outAndInNum(seller_cust_id, use_num_pay, 1);
					}else{
						j1=memberfundService.outAndInNum(seller_cust_id, use_num_pay, 1);
					}
				}
				
				//卖家的资金异动
				Fundhistory buy_fh = new Fundhistory();
				if("1".equals(directorder)){
					buy_fh.setBalance(j1);
					buy_fh.setFund_in(use_num_pay);
				}else{
					buy_fh.setBalance(j1);
					buy_fh.setFund_in(order_momey);
				}
				buy_fh.setCust_id(seller_cust_id);
				buy_fh.setFund_out(0.0);
				buy_fh.setUser_id(session_user_id);
				buy_fh.setReason("卖家收到订单号:"+oid+"支付"+use_num_pay+"元");
				this.fundhistoryDao.insert(buy_fh);
			}
		}
		
		/**	
		 * @author : HXK
		 * @param :
		 * @date Mar 28, 2014 1:33:55 PM
		 * @Method Description :将资金从运营转入买家
		 */
		private void buyFundManage(String  oid,String session_user_id,Double use_num_pay){
			Goodsorder order = this.goodsorderDao.get(oid);
			if(order!=null){
				Double order_momey=0.0;
				//订单总金额
				order_momey=order.getTatal_amount();
				//获取卖家的ID
				String buy_cust_id = order.getBuy_cust_id();
				//买家资金处理
				Double buy_fund_num = 0.0;//总金额
				Double buy_use_num = 0.0;//可用金额
				Memberfund buy_mf = this.memberfundDao.get(buy_cust_id);
				if(buy_mf != null){
					buy_fund_num = Double.parseDouble(buy_mf.getFund_num());
					buy_use_num = Double.parseDouble(buy_mf.getUse_num());
					buy_mf.setFund_num((buy_fund_num+order_momey)+"");
					buy_mf.setUse_num(""+(buy_use_num+order_momey));
					this.memberfundDao.update(buy_mf);
				}
				
				//买家的资金异动
				Fundhistory buy_fh = new Fundhistory();
				buy_fh.setBalance(buy_use_num + order_momey);
				buy_fh.setCust_id(buy_cust_id);
				buy_fh.setFund_in(order_momey);
				buy_fh.setFund_out(0.0);
				buy_fh.setUser_id(session_user_id);
				buy_fh.setReason("买家收到订单号:"+oid+" 退款支付"+use_num_pay+"元");
				this.fundhistoryDao.insert(buy_fh);
			}
		}
		
		/**
		 * @author : HXK
		 * 方法描述：会员修改订单状态
		 * @return
		 * @throws Exception
		 */
		public void updateOrderState(String s_order_id,String s_order_state,String session_cust_id,String session_user_id) throws Exception {
			//订单状态表示：0：订单取消  1：未付款  2：已付款  3：已发货  4：退款中  5：退款成功  6：退款失败：7：交易成功  8：已评价
			Goodsorder goods_order=new Goodsorder();
			goods_order.setOrder_id(s_order_id);
			goods_order.setOrder_state(s_order_state);
			Map stateMap=new HashMap ();
			stateMap.put("order_state", s_order_state);
			stateMap.put("order_id", s_order_id);
			stateMap.put("order_state", s_order_state);
			if(s_order_state.equals("2")){
				stateMap.put("pay_time", "pay_time");
			}
			if(s_order_state.equals("3")){
				stateMap.put("send_time", "send_time");
			}
			if(s_order_state.equals("7")){
				stateMap.put("sure_time", "sure_time");
			}
			goodsorderDao.update(stateMap);
			
			//插入订单异动信息表
			String reason=CommparaFuc.getReason(s_order_state,null);
			insertOrderTrans(s_order_id,reason,s_order_state,session_cust_id,session_user_id);
		}
		
		
		/**
		 * @author : HXK--更新退款操作
		 * @param order_id 订单号
		 * @param reason  处理退款理由
		 * @param seller_state  0：同意退款，1：不同意退款
		 * @param refund_state 0:退款中，1：退款成功，2：退款失败
		 */
		public void updateRefund(String order_id,String reason,String seller_state,String refund_state,String session_cust_id,String session_user_id){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			Refundapp refundapp=new Refundapp();
			refundapp=refundappDao.getByOrderId(order_id);
			if(refundapp!=null){
				//付款到买家
				refundapp.setSeller_cust_id(session_cust_id);
				refundapp.setSeller_date(df.format(new Date()));
				refundapp .setSeller_reason(reason);
				refundapp .setSeller_user_id(session_user_id);
				//0：同意退款，1：不同意退款
				refundapp .setSeller_state(seller_state);
				//0:退款中，1：退款成功，2：退款失败
				refundapp.setRefund_state(refund_state);
				refundapp.setInfo_state("2");
				refundappDao.update(refundapp);
			}
		}
		
		/**
		 * 方法描述：修改订单的时候，插入订单异动表信息
		 * @return
		 * @throws Exception
		 */
		public void insertOrderTrans(String order_id,String reason,String order_state,String session_cust_id,String session_user_id){
			Ordertrans ordertrans=new Ordertrans();
			ordertrans.setCust_id(session_cust_id);
			ordertrans.setOrder_state(order_state);
			ordertrans.setUser_id(session_user_id);
			ordertrans.setReason(reason);
			ordertrans.setOrder_id(order_id);
			ordertransDao.insert(ordertrans);
		}
		
		/**
		 * @author：XBY
		 * @date：Feb 18, 2014 4:44:52 PM
		 * @Method Description：删除订单详情 
		 */
		public int deleteByOrderId(String order_id) {
			return  this.directorderdetailDao.deleteByOrderId(order_id);
		}
}

