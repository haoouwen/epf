/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: PointsgoodsService.java 
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
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IBuyeraddrDao;
import com.rbt.dao.IDirectladderDao;
import com.rbt.dao.IDirectsellDao;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsevalDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IOrdertransDao;
import com.rbt.dao.IPointsgoodsDao;
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
import com.rbt.model.Interhistory;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Pointsgoods;
import com.rbt.model.Refundapp;
import com.rbt.model.Sellerscore;
import com.rbt.model.Shopconfig;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IPointsgoodsService;
import com.rbt.service.ISysfundService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 记录积分商品信息Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Mon Mar 25 16:00:03 CST 2014
 */
@Service
public class PointsgoodsService extends GenericService<Pointsgoods,String> implements IPointsgoodsService {
	@Autowired
	IPointsgoodsDao pointsgoodsDao;
	@Autowired
	IGoodsDao goodsDao;
	@Autowired
	IMemberDao memberDao;
	@Autowired
	IShopconfigDao shopconfigDao;
	@Autowired
	private IDirectladderDao directladderDao;
	@Autowired
	private IDirectsellDao directsellDao;
	@Autowired
	private IGoodsorderDao goodsorderDao;
	@Autowired
	private IOrderdetailDao orderdetailDao;
	@Autowired
	private IOrdertransDao ordertransDao;
	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IFundhistoryDao fundhistoryDao;
	@Autowired
	private IGoodsevalDao goodsevalDao;
	@Autowired
	private IRefundappDao refundappDao;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMemberuserDao memberuserDao;
	@Autowired
	private IBuyeraddrDao buyeraddrDao;
	@Autowired
	private IMemberinterDao memberinterDao;
	@Autowired
	private IInterhistoryDao interhistoryDao;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private ISysfundService sysfundService;
	
	@Autowired
	public PointsgoodsService(IPointsgoodsDao pointsgoodsDao) {
		super(pointsgoodsDao);
		this.pointsgoodsDao = pointsgoodsDao;
	}
	
	/**
	 * @author：CYC
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：积分详细页面
	 */
	public Map detail(String pointgoods_id)throws Exception{
		//对象定义
		Goods goods=new Goods();
		Member member=new Member();
		Pointsgoods pointsgoods=new Pointsgoods();
		Shopconfig shopconfig=new Shopconfig(); 
		//字段定义
		int ordernum=0;//订单数量
		int total_stock=0;//库存
		String	gid="";//商品标识
		String oneimg="";//单张图片
		String timeout="0";//时间结束
		String min_sale_price="";//原价即促销最低价
	    String earnest="";//定金
	    String directprice="";//预售价格
		String pricediff="";//获取预售差价
		String pricepercent="";//获取折扣价
		String diffday="";
	    //集合定义
	    List imageList=new ArrayList();
	    List scoreList=new ArrayList();
	    List catgroupList=new ArrayList();	
		List directsellList=new ArrayList();
	    List directladderList=new ArrayList();
	    List shopconfigList =new ArrayList();
		if(pointgoods_id!=null&&!pointgoods_id.equals("")){
			//获取预售信息
			pointsgoods = pointsgoodsDao.get(pointgoods_id);
			//获取库存
		 total_stock=Integer.parseInt(pointsgoods.getStock());
		 gid = pointsgoods.getGoods_id();
			//获取该预售分类下推荐的4个商品
		    if(pointsgoods.getCat_attr()!=null){
		    	String[] catstr = pointsgoods.getCat_attr().split(",");
		    	if(catstr.length>0){
		    		String catvalue = catstr[catstr.length-1];
		    		HashMap catMap = new HashMap();
		    		catMap.put("cat_attr", catvalue);
		    		catMap.put("lab", "1");
		    		catMap.put("start", "0");
		    		catMap.put("limit", "4");
		    		catgroupList = pointsgoodsDao.getList(catMap);
		    	}
		    	
		    }
			//获取积分商品的名称
			if(pointsgoods!=null && pointsgoods.getGoods_id()!=null){
			goods = goodsDao.get(pointsgoods.getGoods_id());
				
				//获取商品图片列表 以前是获取预售图片列表 hong注释
				if(!ValidateUtil.isRequired(goods.getImg_path())){
					String[] arrayStr =new String[]{};
					arrayStr = goods.getImg_path().split(",");
					oneimg = arrayStr[0];
					imageList = java.util.Arrays.asList(arrayStr);
				}
				//获取用户资料
				if(goods!=null && goods.getCust_id()!=null){
					 member = memberDao.get(goods.getCust_id());
						if(member!=null && member.getCust_id()!=null){
						//获取用户店铺信息
						HashMap shopMap = new HashMap();
						shopMap.put("cust_id", member.getCust_id());
						shopconfigList = shopconfigDao.getList(shopMap);
						
					}
	
				}
				
			}
	}
		//获取积分价格
		String buy_points = pointsgoods.getBuy_points();
		//把对象封装到Map
		 Map pointsgoodsMap=new HashMap();
		 pointsgoodsMap.put("oneimg",oneimg);
		 pointsgoodsMap.put("ordernum",ordernum);
		 pointsgoodsMap.put("imageList", imageList);
		 pointsgoodsMap.put("total_stock", total_stock);
		 pointsgoodsMap.put("pointsgoods", pointsgoods);
		 pointsgoodsMap.put("catgroupList", catgroupList);
		 pointsgoodsMap.put("goods", goods);
		 pointsgoodsMap.put("timeout", timeout);
		 pointsgoodsMap.put("shopconfigList", shopconfigList);
		 pointsgoodsMap.put("shopconfig", shopconfig);
		 pointsgoodsMap.put("scoreList", scoreList);
		 pointsgoodsMap.put("ordernum", ordernum);
		 pointsgoodsMap.put("directsellList", directsellList);
		 pointsgoodsMap.put("min_sale_price", min_sale_price);
		 pointsgoodsMap.put("earnest", earnest);
		 pointsgoodsMap.put("directladderList", directladderList);
		 pointsgoodsMap.put("directprice", directprice);
		 pointsgoodsMap.put("gid", gid);
		 pointsgoodsMap.put("pricediff", pricediff);
		 pointsgoodsMap.put("pricepercent", pricepercent);
		 pointsgoodsMap.put("member", member);
		 pointsgoodsMap.put("diffday", diffday);
		 return	pointsgoodsMap;
	}
	
	/**
	 * @author：CYC
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
		String give_inter_str=StrMap.get("give_inter_str").toString();
		String order_num_str=StrMap.get("order_num_str").toString();
		String shop_name_str=StrMap.get("shop_name_str").toString();
		String shop_qq_str=StrMap.get("shop_qq_str").toString();
		String earnest=StrMap.get("earnest").toString();
		String direct_id=StrMap.get("direct_id").toString();
		String session_cust_id=StrMap.get("session_cust_id").toString();
		String buy_points = StrMap.get("buy_points").toString();
		String pointsgoods_id = StrMap.get("pointsgoods_id").toString();
		String[] cust_id = cust_id_str.split(",");
		String[] user_name=new String[cust_id.length];
		String[] goods_id = goods_id_str.split(",");
		String[] goods_length = goods_length_str.split(",");
		String[] goods_name = goods_name_str.split(",");
		String[] goods_img = goods_img_str.split(",");
		String[] spec_id = spec_id_str.split(",");
		String[] spec_name = spec_name_str.split(",");
		String[] give_inter = give_inter_str.split(",");
		String[] order_num = order_num_str.split(",");
		String[] shop_name = shop_name_str.split(",");
		String[] shop_qq = shop_qq_str.split(",");
		String[] sale_points = buy_points.split(",");
		String[] points_id = pointsgoods_id.split(",");
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
				orderMap.put("sale_points", sale_points[j].trim());
				orderMap.put("earnest",earnest);
				orderMap.put("give_inter", give_inter[j]);
				orderMap.put("order_num", order_num[j].trim());
				orderMap.put("direct_id",direct_id);
				orderMap.put("points_id", points_id[j].trim());
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
	 * @author : CYC
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
	 * @author：CYC
	 * @date：Feb 18, 2014 1:27:07 PM
	 * @Method Description：提交订单（积分商品）
	 */
	public String subOrder(Map map)throws IOException, ParseException, BrowseException{
		HttpServletResponse response=ServletActionContext.getResponse();
		String cust_id_str=map.get("cust_id_str").toString();
		String goods_id_str=map.get("goods_id_str").toString();
		String goods_length_str=map.get("goods_length_str").toString();
        String spec_id_str=map.get("spec_id_str").toString();
        String spec_name_str=map.get("spec_name_str").toString();
        String order_num_str=map.get("order_num_str").toString();
        String mem_remark_str=map.get("mem_remark_str").toString();
        String end_area_attr=map.get("end_area_attr").toString();
        String pointsgoods_id=map.get("trade_id").toString();
        String addr_id=map.get("addr_id").toString();
        String session_cust_id=map.get("session_cust_id").toString();
        String session_user_id=map.get("session_user_id").toString();
		String[] cust_id = cust_id_str.split(",");
		String[] goods_id = goods_id_str.split(",");
		String[] goods_length = goods_length_str.split(",");
		String[] spec_id = spec_id_str.split(",");
		String[] spec_name = spec_name_str.split(",");
		String[] give_inter = new String[goods_id.length];
		String[] order_num = order_num_str.split(",");
		String[] goods_amount = new String[cust_id.length];
		String[] shop_total_amount = new String[cust_id.length];
		String[] ship_free = new String[goods_id.length];
		String[] smode_id = new String[goods_id.length];
		String[] mem_remark = mem_remark_str.split(",");
		String[] end_area=end_area_attr.split(",");
		String valuation_mode;
		if(pointsgoods_id==null&&pointsgoods_id.equals("")){
			return "1";
		}
		Pointsgoods  pointsgoods =this.pointsgoodsDao.get(pointsgoods_id);
		String points_cust_id=pointsgoods.getCust_id();
		String points_goods_id=pointsgoods.getGoods_id();
		if(!points_cust_id.equals(cust_id[0].trim())){
			response.sendRedirect("/login.html");//异常情况重新登录
			return "1";
		}
		shop_total_amount[0]=(Double.parseDouble(pointsgoods.getBuy_points())*Double.parseDouble(order_num[0])+"").trim();
		goods_amount[0]=shop_total_amount[0];
		//运费集合
		List shipList = new ArrayList();
		Goods goods = this.goodsDao.get(points_goods_id);
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
		String order_type = "2";//积分订单
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
			goodsorder.setOrder_id(order_id);
			goodsorder.setOrder_type(order_type);//订单类型
			goodsorder.setIs_virtual("1");//实物商品
			goodsorder.setMem_remark(mem_remark[i]);
			goodsorder.setInfo_status("0");
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
				Orderdetail orderdetail = new Orderdetail();
				orderdetail.setOrder_id(order_id);
				orderdetail.setGoods_attr(spec_name[j]);
				orderdetail.setGoods_price(Double.parseDouble(pointsgoods.getBuy_points()));
				orderdetail.setGoods_id(goods_id[j]);
				orderdetail.setOrder_num(order_num[j]);
				this.orderdetailDao.insert(orderdetail);
				//更新商品库存
				updateStock('2', pointsgoods_id, goods_id[j], spec_id[j], Integer.parseInt(order_num[j].trim()));
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
			mestipByBuyer("3",order_id);
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
		updateStock('0', "", goods_id_str, spec_id_str, Integer.parseInt(order_num_str));
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
		 * @author：CYC
		 * @date：Feb 18, 2014 4:44:52 PM
		 * @Method Description：积分支付
		 */
	    public void useNumPay(String[] order_id,String session_cust_id,String session_user_id,Double use_num_pay)throws Exception{
	    	for(int i = 0; i < order_id.length ; i++){	
				//更新积分订单付款状态为已付款
	    		HashMap map = new HashMap();
	    		if(order_id[0]!=null && !"".equals(order_id[0])){
	    			Goodsorder goodsorder = goodsorderDao.get(order_id[0]);
	    			goodsorder.setOrder_state("2");
	    			goodsorderDao.update(goodsorder);
	    		}
	    		
				// 插入订单异动记录
				Ordertrans ordertrans = new Ordertrans();
				ordertrans.setOrder_id(order_id[i]);
				ordertrans.setCust_id(session_cust_id);
				ordertrans.setUser_id(session_user_id);
				ordertrans.setOrder_state("2");
				ordertrans.setReason("已支付积分");
				this.ordertransDao.insert(ordertrans);
				
				//处理积分及流水
				fundManage(order_id[i],session_user_id,use_num_pay);
				String sumstr = Double.toString(use_num_pay);
				//发送信息提醒
				mestipByBuyer("4",order_id[i],sumstr);
			}
	   }
	   
	    /**
		 * @author : CYC
		 * @date : Jul 23, 2014 9:27:21 AM
		 * @Method Description :积分处理
		 */
		private void fundManage(String  oid,String session_user_id,Double use_num_pay){
			Goodsorder order = this.goodsorderDao.get(oid);
			//获取买家的ID
			String buy_cust_id = order.getBuy_cust_id();
			//买家积分处理
			Double buy_fund_num = 0.0;//总金额
			Double buy_use_num = 0.0;//可用金额
			Memberinter buy_mf = this.memberinterDao.get(buy_cust_id);
			String leftinter="";
			if(buy_mf != null){
				buy_fund_num = Double.parseDouble(buy_mf.getFund_num());
				leftinter = Double.toString(buy_fund_num-use_num_pay);
				buy_mf.setFund_num(leftinter);
				this.memberinterDao.update(buy_mf);
			}
			
			//买家的资金异动
			Interhistory buy_ih = new Interhistory();
			buy_ih.setInter_in("0");
			buy_ih.setCust_id(buy_cust_id);
			buy_ih.setInter_out(Double.toString(use_num_pay));
			buy_ih.setThisinter(leftinter);
			buy_ih.setUser_id(session_user_id);
			buy_ih.setReason("买家为订单号:"+oid+"支付积分"+use_num_pay);
			interhistoryDao.insert(buy_ih);
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
					sysfundService.freezeNum( use_num_pay, 1);
				}else{
					sysfundService.freezeNum( order_momey, 1);
				}
				
				//卖家资金处理
				Memberfund seller_mf = this.memberfundDao.get(seller_cust_id);
				double j1=0.00;
				if(seller_mf != null){
					if("1".equals(directorder)){
						j1=memberfundService.outAndInNum(seller_cust_id, use_num_pay, 1);
					}else{
						j1=memberfundService.outAndInNum(seller_cust_id, order_momey, 1);
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
}

