/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: OrderdetailService.java 
 */
package com.rbt.service.impl;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IGoodsService;
import com.rbt.service.ISysfundService;
import com.rbt.dao.IComsumerDao;
import com.rbt.dao.ICouponDao;
import com.rbt.dao.IGoodsevalDao;
import com.rbt.dao.IGoodsshareDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IInternationaltariffDao;
import com.rbt.dao.IMemberDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IBuyeraddrDao;
import com.rbt.dao.IComboDao;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsattrDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IGroupladderDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IOrderinvoiceDao;
import com.rbt.dao.IOrdertransDao;
import com.rbt.dao.IRedsumerDao;
import com.rbt.dao.IRefundappDao;
import com.rbt.dao.ISalegoodsDao;
import com.rbt.dao.ISaleorderDao;
import com.rbt.dao.IShiptemplateDao;
import com.rbt.dao.IShopconfigDao;
import com.rbt.dao.ISpikegoodsDao;
import com.rbt.dao.ITryapplyDao;
import com.rbt.dao.ITrygoodsDao;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.MembercreditFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SalegoodsFuc;
import com.rbt.function.SaleorderFuc;
import com.rbt.function.StockFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Combo;
import com.rbt.model.Comsumer;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Goodsshare;
import com.rbt.model.Groupgoods;
import com.rbt.model.Interhistory;
import com.rbt.model.Internationaltariff;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Ordertrans;
import com.rbt.model.Redsumer;
import com.rbt.model.Refundapp;
import com.rbt.model.Salegoods;
import com.rbt.model.Saleorder;
import com.rbt.model.Sellerscore;
import com.rbt.model.Shiptemplate;
import com.rbt.model.Spikegoods;
import com.rbt.model.Tryapply;
import com.rbt.model.Trygoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录订单商品详细信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 11 16:38:19 CST 2014
 */
@Service
public class OrderdetailService extends GenericService<Orderdetail,String> implements IOrderdetailService {
	
	IOrderdetailDao orderdetailDao;
	@Autowired
	IBuyeraddrDao buyeraddrDao;
	@Autowired
	IGroupladderDao groupladderDao;
	@Autowired
	IGoodsDao goodsDao;
	@Autowired
	IComboDao comboDao;
	@Autowired
	IGoodsorderDao goodsorderDao;
	@Autowired
	IShopconfigDao shopconfigDao;
	@Autowired
	IOrdertransDao ordertransDao;
	@Autowired
	IRefundappDao refundappDao;
	@Autowired
	IMemberfundDao memberfundDao;
	@Autowired
	IFundhistoryDao fundhistoryDao;
	@Autowired
	IGoodsattrDao goodsattrDao;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	IGoodsevalDao goodsevalDao;
	@Autowired
	ISpikegoodsDao spikegoodsDao;
	@Autowired
	IMemberinterDao memberinterDao;
	@Autowired
	IInterhistoryDao interhistoryDao;
	@Autowired
	IMemberfundService memberfundService;
	@Autowired
	IInternationaltariffDao internationaltariffDao;
	@Autowired
	private ISysfundService sysfundService;
	@Autowired
	private ITrygoodsDao trygoodsDao;
	@Autowired
	private ITryapplyDao tryapplyDao;
	@Autowired
	private IGoodsshareDao goodsshareDao;
	@Autowired
	private IOrderinvoiceDao orderinvoiceDao;
	@Autowired
	private IComsumerDao comsumerDao;
	@Autowired
	private IRedsumerDao redsumerDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private ISalegoodsDao salegoodsDao;
	@Autowired
	private ICouponDao couponDao;
	@Autowired
	private ISaleorderDao saleorderDao;
	@Autowired
	private IShiptemplateDao shiptemplateDao;
	@Autowired
	private IMemberuserDao memberuserDao;
	
	@Autowired
	public OrderdetailService(IOrderdetailDao orderdetailDao) {
		super(orderdetailDao);
		this.orderdetailDao = orderdetailDao;
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Sep 20, 2016 4:47:10 PM
	 * @Method Description :优惠券生成10位随机数
	 */
	public String str(){
		 Random rrr =new Random();
		 long num = Math.abs(rrr.nextLong() % 10000000000L);
	        String s = String.valueOf(num);
	        return s;
	        
	};
	
	private String cfg_order_allfund = SysconfigFuc.getSysValue("cfg_order_allfund");
	private String cfg_freight = SysconfigFuc.getSysValue("cfg_freight");
	/**
	 * @author : HZX
	 * @date : Feb 14, 2014 11:00:53 AM
	 * @Method Description :判断是否团购
	 */
	public boolean isGroup(Groupgoods groupgoods, String order_num_str) {
		int stock=Integer.parseInt(groupgoods.getStock());
		int cust_maxbuy=Integer.parseInt(groupgoods.getCust_maxbuy());
		int buy_nums=Integer.parseInt(order_num_str);
		if(buy_nums>stock||buy_nums>cust_maxbuy){
			return false;
		}
		return true;
	}
	public String getAddrDiv(HttpServletRequest request,String cust_id,String user_id) throws Exception {
		//获取参数
		String consignee = request.getParameter("consignee");
		String de_consignee = URLDecoder.decode(consignee,"UTF-8");
		String identitycard = request.getParameter("identitycard");
		String address = request.getParameter("address");
		String de_address = URLDecoder.decode(address,"UTF-8");
		if(!ValidateUtil.isRequired(de_address)){
			//替换详细地址的特殊符号( [ { /^ -    $     ¦    }    ]    )    ?    *    +    .
	         String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";     
	         Pattern   p   =   Pattern.compile(regEx);        
	         Matcher   m   =   p.matcher(de_address);     
	         de_address=m.replaceAll("").trim();        

		}
		String addr_id = request.getParameter("addr_id");
		String phone = request.getParameter("phone");
		String cell_phone = request.getParameter("cell_phone");
		String post_code = request.getParameter("post_code");
		String area_attr = request.getParameter("area_attr");
		//最后一级地区ID
		String end_area_attr = area_attr.substring(area_attr.lastIndexOf(",")+1, area_attr.length());
		if(addr_id!=null&&!"".equals(addr_id)){
			Buyeraddr b=buyeraddrDao.get(addr_id);
			if(b!=null){
				changeAdd(cust_id);
				b.setConsignee(de_consignee);
				b.setIdentitycard(identitycard);
				b.setAddress(de_address);
			    b.setPost_code(post_code);
				b.setPhone(phone);
				b.setCell_phone(cell_phone);
				b.setArea_attr(area_attr);
				b.setCust_id(cust_id);
				b.setUser_id(user_id);
				b.setSel_address("0");
				b.setIdentitycard(identitycard);
				this.buyeraddrDao.update(b);
			}
		}else {
			changeAdd(cust_id);
			Buyeraddr ba = new Buyeraddr();
			ba.setConsignee(de_consignee);
			ba.setIdentitycard(identitycard);
			ba.setAddress(de_address);
			ba.setPost_code(post_code);
			ba.setPhone(phone);
			ba.setCell_phone(cell_phone);
			ba.setArea_attr(area_attr);
			ba.setCust_id(cust_id);
			ba.setUser_id(user_id);
			ba.setSel_address("0");
			ba.setIdentitycard(identitycard);
			addr_id = this.buyeraddrDao.insertGetPk(ba);
		
		}
		
		//地区ID转名称
		//area_attr = AreaFuc.getAreaNameByMap(area_attr);
		return addr_id;
	}
	public void changeAdd(String cust_id){
		List buyeraddrList;
		Map hasdfoaddr = new HashMap();
    		hasdfoaddr.put("cust_id", cust_id);
    		hasdfoaddr.put("sel_address", "0");
    		buyeraddrList = buyeraddrDao.getList(hasdfoaddr);
	    	if(buyeraddrList!=null&&buyeraddrList.size()>0){
	    		Map isdefo = (Map) buyeraddrList.get(0);
	    		String addr_id=isdefo.get("addr_id").toString();
	    		Buyeraddr	setdefoaddr =new Buyeraddr();
	    		setdefoaddr = buyeraddrDao.get(addr_id);
	    		setdefoaddr.setSel_address("1");
	    		buyeraddrDao.update(setdefoaddr);
	    		
	    	}
	}
	
	/**
	    * 修改地址
	    * @param request
	    * @param cust_id
	    * @param user_id
	    * @return
	    * @throws Exception
	    */
		public String updateAddrDiv(HttpServletRequest request,String cust_id,String user_id) throws Exception {
			//获取参数
			String consignee = request.getParameter("consignee");
			String de_consignee = URLDecoder.decode(consignee,"UTF-8");
			String identitycard = request.getParameter("identitycard");
			String address = request.getParameter("address");
			String de_address = URLDecoder.decode(address,"UTF-8");
			if(!ValidateUtil.isRequired(de_address)){
				//替换详细地址的特殊符号( [ { /^ -    $     ¦    }    ]    )    ?    *    +    .
		         String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";     
		         Pattern   p   =   Pattern.compile(regEx);        
		         Matcher   m   =   p.matcher(de_address);     
		         de_address=m.replaceAll("").trim();        

			}
			String post_code = request.getParameter("post_code");
			String phone = request.getParameter("phone");
			String cell_phone = request.getParameter("cell_phone");
			String area_attr = request.getParameter("area_attr");
			String addr_id =request.getParameter("addr_id");
			//最后一级地区ID
			String end_area_attr = area_attr.substring(area_attr.lastIndexOf(",")+1, area_attr.length());
			Buyeraddr ba = new Buyeraddr();
			ba.setConsignee(de_consignee);
			ba.setIdentitycard(identitycard);
			ba.setAddress(de_address);
			ba.setPost_code(post_code);
			ba.setPhone(phone);
			ba.setCell_phone(cell_phone);
			ba.setArea_attr(area_attr);
			ba.setCust_id(cust_id);
			ba.setUser_id(user_id);
			ba.setSel_address("1");
			ba.setAddr_id(addr_id);
			this.buyeraddrDao.update(ba);
			//地区ID转名称
			area_attr = AreaFuc.getAreaNameByMap(area_attr);
			String addrDiv ="<input type='hidden' name='end_area_attr' value='"+end_area_attr+"'/>"+
			         "<td width='10%'><input type='radio' checked='checked' name='adrRadio' value='"+addr_id+"' onclick=\"onCheck('adrRadio');\">商品寄至</td>"+
			         "<td width='78%'><span>"+area_attr+"</span><span>"+de_address+"</span><span>"+de_consignee+"(收)</span><span>"+cell_phone+"</span></td>"+
			         "<td width='12%'><b class='editor' onclick='getAddr("+addr_id+");'>编辑</b><b onclick='delAddr("+addr_id+");'>删除</b>"+
			         "</td>";
			return addrDiv;
		}
	/**
	 * @author : HZX
	 * @throws Exception 
	 * @date : Feb 18, 2014 9:57:41 AM
	 * @Method Description :生成订单
	 */
		public String addOrder(Map order_varMap,HttpServletResponse response) throws Exception{
			Buyeraddr buyeraddr = new Buyeraddr();
			String[] cust_id = (String[]) order_varMap.get("cust_id");
			String[] goods_id = (String[]) order_varMap.get("goods_id");
			String[] inter_subs = (String[]) order_varMap.get("inter_subs");
			String[] inter_subcount = new String[cust_id.length];
			String[] goods_length = (String[]) order_varMap.get("goods_length");
			String[] spec_id = (String[]) order_varMap.get("spec_id");
			String[] spec_name = (String[]) order_varMap.get("spec_name");
			String[] sale_price = (String[]) order_varMap.get("sale_price");
			String[] tax_rate = new String[goods_id.length];
			String[] give_inter = (String[]) order_varMap.get("give_inter");
			String[] order_num = (String[]) order_varMap.get("order_num");
			String[] goods_amount = (String[]) order_varMap.get("goods_amount");
			String[] shop_total_amount = (String[]) order_varMap.get("shop_total_amount");
			String[] tax_rate_amount=  new String[cust_id.length];
			String[] give_inter_amount=  new String[cust_id.length];
			String[] ship_free = (String[]) order_varMap.get("ship_free");
			String[] smode_id = (String[]) order_varMap.get("smode_id");
			String[] subtotal = (String[]) order_varMap.get("subtotal");
			String order_id_str=(String) order_varMap.get("order_id_str");
			String addr_id=(String) order_varMap.get("addr_id");
			String order_type=(String) order_varMap.get("order_type");
			String goods_id_str=(String) order_varMap.get("goods_id_str");
			String order_num_str=(String) order_varMap.get("order_num_str");
			String group_id=(String) order_varMap.get("group_id");
			String combo_id=(String) order_varMap.get("combo_id");
			String spike_id=(String) order_varMap.get("spike_id");
			String trade_id=(String) order_varMap.get("trade_id");
			String coupon_id=(String) order_varMap.get("coupon_id");
			String coupon_goods_id=(String) order_varMap.get("coupon_goods_id");
			String coupon_money=(String) order_varMap.get("coupon_money");
			String comsumer_id=(String) order_varMap.get("comsumer_id");
			String coupon_cust_id=(String) order_varMap.get("coupon_cust_id");
			String red_id=(String) order_varMap.get("red_id");
			String redsumer_id=(String) order_varMap.get("redsumer_id");
			String red_money=(String) order_varMap.get("red_money");
			String is_webapp_order=(String) order_varMap.get("is_webapp_order");
			String is_ship_free=(String) order_varMap.get("is_ship_free");
			String all_total_money= (Double)order_varMap.get("all_total") + "";
			String session_cust_id=(String) order_varMap.get("session_cust_id");
			String session_user_id=(String) order_varMap.get("session_user_id");
			Orderinvoice orderinvoice=(Orderinvoice)order_varMap.get("orderinvoice");
			String paytype_id=(String) order_varMap.get("paytype_id");
			String is_use_inter=(String) order_varMap.get("is_use_inter");
			String integral_state=(String) order_varMap.get("integral_state");
			String order_sign="";
			if(order_varMap.get("order_sign")!=null){
				order_sign=order_varMap.get("order_sign").toString();
			}else {
				order_sign=RandomStrUtil.generateString(18);
			}
			double use_paynum=Double.parseDouble((String) order_varMap.get("use_paynum"));
			double discount=(Double)order_varMap.get("discount");
			double shoptotal_norate = (Double)order_varMap.get("shoptotal_norate");
			double remain_pay=0;
			boolean is_one_pay=false;
			String end_area_attr=null;
			String area_attr_array[];
			Member member = memberDao.get(session_cust_id);
			//实例化收货地址对象
			if(addr_id != null && !addr_id.equals("")){
				buyeraddr = this.buyeraddrDao.get(addr_id);
				end_area_attr=buyeraddr.getArea_attr();
				area_attr_array=end_area_attr.split(",");
				if(area_attr_array.length>1){
					end_area_attr=area_attr_array[1].replace(" ", "");
				}else{
					end_area_attr=area_attr_array[0].replace(" ", "");
				}
			}
			int goodsnum=0;
			for(int j=0;j<cust_id.length;j++){
				double shopall=0.0;
				double v_all=0.0;//体积
				double w_all=0.0;//重量
				String ief_id="1";//国际运费模版
				String ship_id="1";//国内运费模版
				double tax_rate_all=0.0;//税费
				double inter_sub_all=0.0;//积分
				double  give_inter_all=0.0;//获得积分
				double sea_ship=0.0;//国际运费
				double shop_ship_free=0.0;//国内运费
				double shop_ship_free_all=0.0;//运费总和
				for(int k=0;k<Integer.parseInt(goods_length[j].trim());k++){
						if(goods_id_str!=null&&!goods_id_str.equals("")&&order_num_str!=null&&!order_num_str.equals("")){
							       Goods goods = this.goodsDao.get(goods_id[goodsnum+k]);
						           //判断商品是否有促销活动
						            String sale_id = SalegoodsFuc.getSaleId(goods, is_webapp_order, member.getBuy_level());
							        //商品价格
									String goods_cust_id=this.goodsDao.get(goods_id[goodsnum+k]).getCust_id();
									if(goods_cust_id.equals(session_cust_id.trim())){
										response.sendRedirect("/login.html");
									}
									
									if(group_id!=null&&!group_id.equals("")){
										sale_price[goodsnum+k]=getGrouprice(group_id);
									}else if(combo_id!=null&&!combo_id.equals("")){
										sale_price[goodsnum+k]=getComborice(combo_id);
									}else if(spike_id!=null&&!spike_id.equals("")){
										sale_price[goodsnum+k]=getSpikeprice(spike_id);
									}else{
										sale_price[goodsnum+k]=getKey(goods_id[goodsnum+k],spec_id[goodsnum+k],"sale_price");
										tax_rate[goodsnum+k]=getTax_rate(goods_id[goodsnum+k]);
									}
									if(combo_id!=null&&!combo_id.equals("")){
										shopall=Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k]);
									}else{
										//判断商品满减活动
										if(!ValidateUtil.isRequired(sale_id) && integral_state.equals("0")) {
											String[] sale_ids = sale_id.trim().split(",");
											for(int m = 0; m < sale_ids.length; m ++) {
												Salegoods salegoods = salegoodsDao.get(sale_ids[m]);
												if(!salegoods.getTerm_state().equals("4") && !salegoods.getCoupon_state().equals("6")){
													sale_price[goodsnum+k] = SalegoodsFuc.getSalePrice(sale_ids[m], sale_price[goodsnum+k]);
												}
											}
											String goodstotal =Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k]) +"";
											for(int i = 0; i < sale_ids.length; i ++) {
												Salegoods salegoods = salegoodsDao.get(sale_ids[i]);
												//满减活动
												goodstotal = Double.valueOf(SalegoodsFuc.goSaleprice(salegoods.getSale_id(), goodstotal)) + "";
																						
											}
											shopall += Double.valueOf(goodstotal);
										}else {
											shopall+=Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k]);//非套餐商品
										}
										give_inter_all+=Double.parseDouble(give_inter[goodsnum+k]);
										//判断商品满减活动
										if(!ValidateUtil.isRequired(sale_id) && integral_state.equals("0")){
											String goodstotal =Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k]) +"";
											String[] sale_ids = sale_id.trim().split(",");
											for(int i = 0; i < sale_ids.length; i ++) {
												Salegoods salegoods = salegoodsDao.get(sale_ids[i]);
												//满减活动
												goodstotal = Double.valueOf(SalegoodsFuc.goSaleprice(salegoods.getSale_id(), goodstotal)) +"";
											}
											tax_rate_all +=  Double.valueOf(goodstotal)* (discount) * Double.parseDouble(tax_rate[goodsnum+k])/100;
										}else if(coupon_goods_id.contains(goods_id[goodsnum+k].replaceAll(" ", ""))) {//判断商品使用优惠券
											tax_rate_all+=(Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k])*(discount) - ( ((Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k])*(discount))/shoptotal_norate) * Double.valueOf(coupon_money))) *Double.parseDouble(tax_rate[goodsnum+k])/100;
										}else {
											if(integral_state.equals("0")) {
											    tax_rate_all+=Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k]) *(discount) *Double.parseDouble(tax_rate[goodsnum+k])/100;
											}else {
												tax_rate_all+=Double.parseDouble(sale_price[goodsnum+k])*Double.parseDouble(order_num[goodsnum+k])  *Double.parseDouble(tax_rate[goodsnum+k])/100;
											}
										}
										
										inter_sub_all+=Double.parseDouble(inter_subs[goodsnum+k]);
									}
									//国内运费
									if(goods.getIs_ship().equals("0")){
										smode_id[goodsnum+k]="0";
									}else{
										//按照每个商品选择的 运费模版 来计算国内运费
										if(goods != null){
											//获取累计每个商品的体积
											v_all+= (Double.parseDouble(getKey(goods_id[goodsnum+k],spec_id[goodsnum+k],"volume"))
													*Double.parseDouble(order_num[goodsnum+k]));
											//获取累计每个商品的重量
											w_all+=(Double.parseDouble(getKey(goods_id[goodsnum+k],spec_id[goodsnum+k],"logsweight"))
													*Double.parseDouble(order_num[goodsnum+k]));
											//系统默认就一种运费模版
											Shiptemplate gShiptemplate=new Shiptemplate();
											gShiptemplate=shiptemplateDao.get("1");
											smode_id[goodsnum+k]=gShiptemplate.getSmode_attr();
										}else{
											smode_id[goodsnum+k]="0";
								        }
					             }
						}
					
				}
				
			    //获取判断体积重量
				Internationaltariff  ief =new Internationaltariff();
				ief=this.internationaltariffDao.get(ief_id);
				String ief_v= ief.getIef_cubic();//体积立方数5000
				String ief_over_w=ief.getIef_overweight();//超重重量
				String ief_price=ief.getIef_price();//首重单价
				String ief_over_price=ief.getIef_overweight_price();//超重单价
				double v_w=0.0;
				//体积重量算法 体积/5000
				//体积重量 是否 大于商品重量
				if((v_all/Double.parseDouble(ief_v))>w_all){
					//体积重量大于商品重量 取体积重量算运费
					v_w=v_all/Double.parseDouble(ief_v);
				}else {
					//取商品体积算运费
					v_w=w_all;
				}
				//国内运费计算
				List shipList = new ArrayList();
				//is_ship_str 0为免运费，1运费模版
				shipList = goodsService.dealShipPrice("1",ship_id,"1","0",String.valueOf(v_w),end_area_attr);
				if(shipList!=null&&shipList.size()>0){
					Map ship_freeMap=(Map) shipList.get(0);
					//国内运费总和
					shop_ship_free=Double.parseDouble(ship_freeMap.get("ship_price").toString());
				}
				//直邮运费算法
				if("1".equals(cust_id[j].replace(" ", ""))){
					//判断是否超重
					if(v_w<Double.parseDouble(ief_over_w)){
						//没有超重 获取默认超重重量 也就是取默认首重价格
						//获取默认最低价格
						if(v_w<=1){
							v_w=1;
						}
						sea_ship=v_w*Double.parseDouble(ief_price);
					}else {
						//超重 价格算法  3*首重价格+超重重量*超重价格
						sea_ship=Double.parseDouble(ief_over_w)*Double.parseDouble(ief_price)+
						Double.parseDouble(ief_over_price)*(w_all-Double.parseDouble(ief_over_w));
					}
			   }else {
				   //如果是保税区的运费为0
				   sea_ship=0;
			   }
				//总运费
				shop_ship_free_all=shop_ship_free+sea_ship;
				//判断直邮
				if("1".equals(cust_id[j].replace(" ", ""))){
					//判断如果直邮运费大于480 直接算为480
					if(shop_ship_free_all>480){
						shop_ship_free_all=480;
					}
				}
				//判断是否免运费
				if(is_ship_free.equals("1")) {
					ship_free[j]="0";
				}else {
					ship_free[j]=((shop_ship_free_all)+"").trim();
				}
				shop_total_amount[j]=(shopall+"").trim();
				give_inter_amount[j]=(give_inter_all+"").trim();
				tax_rate_amount[j]=(tax_rate_all+"").trim();
				inter_subcount[j]=(inter_sub_all+"").trim();
				goods_amount[j]=shop_total_amount[j];
				goodsnum+=Integer.parseInt(goods_length[j].trim());
			}
			//生成订单
			int x= 0,y = 0;
			//订单号
			String order_id = "";
			order_type = "1";//普通订单 订单类型
			for(int i = 0; i < cust_id.length; i++){
				// 按照日期生成随机数作为订单号
				order_id = RandomStrUtil.getDateRand()+RandomStrUtil.getNumberRand();
				//赠送优惠券ID
				String send_coupon_id="";
				//赠送红包ID
				String send_red_id="";
				//赠送商品ID
				String fg_ids = "";
				//临时存储订单号
				if(order_id_str != null && !order_id_str.equals("")){
					order_id_str = order_id_str +","+ order_id;
				}else{
					order_id_str = order_id;
				}
				double rate_all=0.00,tatal_amount=0.00,inttatal=0.00; 
				rate_all=Double.parseDouble(tax_rate_amount[i]);
				//if("1".equals(cust_id[i])){
					//if(rate_all<50){
					//	rate_all=0;
					//}
			//	}
				
				tatal_amount=Double.parseDouble(shop_total_amount[i])+Double.parseDouble(ship_free[i])+rate_all;
				inttatal=(Double.parseDouble(shop_total_amount[i])*(discount));
				//算积分
				Double comm_free= 0.00;
				//生成订单
				Goodsorder goodsorder = new Goodsorder();
				goodsorder.setBuy_cust_id(session_cust_id);
				goodsorder.setSell_cust_id("0");
				goodsorder.setIs_sea(cust_id[i].replace(" ", ""));
				goodsorder.setGoods_amount(Double.parseDouble(String.format("%.2f", Double.parseDouble(shop_total_amount[i]))));			
				double all_total =0.0;
				if(integral_state.equals("0")) {
					 all_total =Double.parseDouble(String.format("%.2f", tatal_amount-(Double.parseDouble(shop_total_amount[i])*(1-discount))));
				}else {
					 all_total =Double.parseDouble(String.format("%.2f", tatal_amount));
				}
				//计算优惠券
				if(coupon_cust_id.equals(cust_id[i])){
					//扣除优惠金额
					all_total = all_total - Double.valueOf(coupon_money);
					inttatal=inttatal-Double.valueOf(coupon_money);
				}
				//计算红包
				if(!ValidateUtil.isRequired(red_id) && !red_money.equals("0")) {
					goodsorder.setRed_id(red_id);
					if( all_total >= Double.valueOf(red_money)){
						goodsorder.setRed_money(red_money);
						all_total = all_total - Double.valueOf(red_money);
						inttatal=inttatal-Double.valueOf(red_money);
						//判读赠送积分是否小于0
						if(inttatal < 0) {
							inttatal = 0;
						}
					}else {
						goodsorder.setRed_id(red_id);
						goodsorder.setRed_money(all_total+"");
						all_total = 0;
						//订单等于零时，赠送积分也等于0
						inttatal = 0; 
					}
				}
				//判断订单是否有满减活动
				if(integral_state.equals("0")) {
				String  saleorder_id = SaleorderFuc.getSaleorderId(is_webapp_order, member.getBuy_level());
				if(!ValidateUtil.isRequired(saleorder_id)) {
				String[] saleorder_ids = saleorder_id.trim().split(",");
				double coupon_plan = 0.0;
				for(int k = 0; k < saleorder_ids.length; k ++ ) {
					Saleorder saleorder = saleorderDao.get(saleorder_ids[k]);
					//部分商品总价
					double goodsall = 0.0;
					if(saleorder != null) {
						//判断订单是否有满减活动
							for(int m = 0; m < goods_id.length; m ++) {
								if(ifcontansInfo(goods_id[m],saleorder.getTerm().trim())==true)  {
									goodsall += Double.parseDouble(sale_price[m])*Double.parseDouble(order_num[m]);
								}
							}
						//判断订单是否赠送优惠券或者红包或者赠品
						//1优惠券，2红包，3赠品
						String state = SaleorderFuc.getCoupon(saleorder, all_total_money, goodsall+"");
						if(state.equals("1")) {
			        		if(ValidateUtil.isRequired(send_coupon_id)) {
			        			send_coupon_id = saleorder.getCoupon_plan();
			        		}else {
			        			send_coupon_id += "," + saleorder.getCoupon_plan();
			        		}
			        	} else if(state.equals("2")) {
			        		if(ValidateUtil.isRequired(send_red_id)) {
			        			send_red_id = saleorder.getCoupon_plan();
			        		}else {
			        			send_red_id += "," + saleorder.getCoupon_plan();
			        		}			        		
			        	}else if(state.equals("3")) {
			        		if(ValidateUtil.isRequired(fg_ids)) {
			        			fg_ids = saleorder.getCoupon_plan();
			        		}else {
			        			fg_ids += "," + saleorder.getCoupon_plan();
			        	    }
			        	}	
				        
						if(saleorder.getTerm_state().equals("2") && saleorder.getCoupon_state().equals("5")){
							//累计优惠金额
							coupon_plan +=  Double.valueOf(saleorder.getCoupon_plan());
						}else if(saleorder.getTerm_state().equals("3") && saleorder.getCoupon_state().equals("5")) {
							goodsall = Double.parseDouble(String.format("%.2f",goodsall * (discount)));
							//满足条件
							if(goodsall >= Double.valueOf(saleorder.getNeed_money())) {
								//累计优惠金额
								coupon_plan +=  Double.valueOf(saleorder.getCoupon_plan());
							}
						}else if(saleorder.getTerm_state().equals("4") && saleorder.getCoupon_state().equals("5")) {
							//满足条件
							if((goodsorder.getGoods_amount()*(discount)) >= Double.valueOf(saleorder.getNeed_money())) {
								//累计优惠金额
								coupon_plan +=  Double.valueOf(saleorder.getCoupon_plan());
							}
						}
					}
				}
				
                if(coupon_plan > 0) {
                	//扣除订单优惠金额
                	all_total = all_total - coupon_plan;
                	//扣除订单优惠积分
                	inttatal=inttatal-Double.valueOf(coupon_plan);
                	goodsorder.setCoupon_money(coupon_plan +"");
                }
				}
				}
				goodsorder.setTatal_amount(all_total);
				goodsorder.setSend_mode(smode_id[i]);
				goodsorder.setShip_free(Double.parseDouble(ship_free[i]));
				goodsorder.setOrder_state("1");
				goodsorder.setDeliver_state("0");
				goodsorder.setIs_webapp_order(is_webapp_order);//PC端订单
				goodsorder.setOrder_id(order_id);
				goodsorder.setComm_free(comm_free);
				if(!ValidateUtil.isRequired(is_use_inter) && !"0".equals(is_use_inter)) {
					goodsorder.setOrder_type("2");//订单类型 积分订单
				}else {
					goodsorder.setOrder_type(order_type);//订单类型
				}
				goodsorder.setIs_del("1");
				goodsorder.setIs_virtual("1");//实物商品
				goodsorder.setIs_vip("0");//是否为内部员工下单，0：不是，1是
				goodsorder.setPrint_fahuo("1");//打印发货0：是，1不是
				goodsorder.setPrint_kuaidi("1");//打印快递0：是，1不是
				goodsorder.setPrint_gouwu("1");//打印购物0：是，1不是
				goodsorder.setMemberdiscount(discount+"");//会员折扣
				if(inttatal!=0.0){
					// 统计当前订单的总额数为获得积分总额数+当前积分数
					inttatal = inttatal* Double.parseDouble(SysconfigFuc.getSysValue("cfg_sc_pointsrule"))/100;
				}
				//如果使用积分支付，不赠送积分
				if(!is_use_inter.equals("0")){
					inttatal = 0;
				}
				if(inttatal<=0){
					//判断获得积分是否小于0 
					inttatal=0;
				}
				goodsorder.setPresent_integral(String.format("%.0f",inttatal));//先定义
				goodsorder.setDiscount(String.format("%.2f", (Double.parseDouble(shop_total_amount[i])*(1-discount))));
				goodsorder.setTaxes(String.format("%.2f",Double.valueOf(rate_all)));
				int cfg_sc_exchscale=Integer.parseInt(SysconfigFuc.getSysValue("cfg_sc_exchscale"));
				goodsorder.setIntegral_use(is_use_inter);
				Memberfund	memberfund = memberfundDao.get(session_cust_id);
				String fun_num = memberfund.getUse_num();
				String use_num = memberfund.getUse_num();
				String freeze_num = memberfund.getFreeze_num();
				Memberinter memberinter=memberinterDao.get(session_cust_id);
				String fund_num_inter=memberinter.getFund_num();
				fund_num_inter=(Double.parseDouble(fund_num_inter)-Integer.parseInt(is_use_inter))+"";
				fund_num_inter = String.format("%.0f",Double.valueOf(fund_num_inter));
				memberinter.setFund_num(fund_num_inter);
				memberinterDao.update(memberinter);
				if(!ValidateUtil.isRequired(is_use_inter) && !"0".equals(is_use_inter)) {
				// 插入积分流
				Interhistory interhistory = new Interhistory();
				interhistory.setCust_id(session_cust_id);
				interhistory.setInter_in("0");
				interhistory.setInter_out(is_use_inter);
				interhistory.setThisinter(fund_num_inter);
				interhistory.setUser_id(session_user_id);
				interhistory.setReason("购买商品扣掉积分,商品订单号为:" + order_id);
				interhistoryDao.insert(interhistory);
				}
				if("on".equals(paytype_id)){
					
						if(!is_one_pay){
							remain_pay=use_paynum;
						}
						if(remain_pay<=(all_total-Double.parseDouble(inter_subcount[i])/cfg_sc_exchscale)){
							goodsorder.setBalance_use(String.format("%.2f", (remain_pay)));
							use_num=(Double.parseDouble(use_num)-remain_pay)+"";
							is_one_pay=true;
						}else {
							goodsorder.setBalance_use(String.format("%.2f", (all_total-Double.parseDouble(inter_subcount[i])/cfg_sc_exchscale)));
							use_num=(Double.parseDouble(use_num)-all_total-Double.parseDouble(inter_subcount[i])/cfg_sc_exchscale)+"";
							remain_pay=remain_pay-(all_total-Double.parseDouble(inter_subcount[i])/cfg_sc_exchscale);
							is_one_pay=true;
						}
						memberfund.setUse_num(use_num);
						memberfund.setFreeze_num(String.valueOf(Double.valueOf(fun_num)-Double.valueOf(use_num)+Double.valueOf(freeze_num)));
						memberfundDao.update(memberfund);
						
				}else {
					goodsorder.setBalance_use("0");
				}
				//goodsorder.setMem_remark(mem_remark[i]);
				//收货地址相关
				goodsorder.setConsignee(buyeraddr.getConsignee());
				goodsorder.setArea_attr(buyeraddr.getArea_attr());
				goodsorder.setBuy_address(buyeraddr.getAddress());
				String post_code=buyeraddr.getPost_code();
				if(!"".equals(post_code)){
					goodsorder.setZip_code(post_code);
				}
				goodsorder.setTelephone(buyeraddr.getPhone());
				goodsorder.setMobile(buyeraddr.getCell_phone());
				goodsorder.setIdentitycard(buyeraddr.getIdentitycard());
				goodsorder.setInvoice_id(order_id);
				goodsorder.setOrder_sign(order_sign);
				this.goodsorderDao.insert(goodsorder);
				if(order_varMap.get("orderinvoice")!=null){
					orderinvoice.setInvoice_id(order_id);
					this.orderinvoiceDao.insert(orderinvoice);
				}
				if(i == 0){
					x = 0;
				}else{
					x += Integer.parseInt(goods_length[i-1].trim());//
				}
				y = Integer.parseInt(goods_length[i].trim()) + x;//
				for(int j = x; j < y; j++){
					//去空格
					String goods_ids ="",spec_ids="",order_nums="";
					Goods goodsdetail =new Goods();
			        goodsdetail = this.goodsDao.get(goods_id[j]);
			        //判断商品是否促销
			        String sale_id = SalegoodsFuc.getSaleId(goodsdetail, is_webapp_order, member.getBuy_level());
					//生成订单详情
					Orderdetail orderdetail = new Orderdetail();
					if(!ValidateUtil.isRequired(goods_id[j])){
						goods_ids=goods_id[j].replace(" ", "");
					}
					if(!ValidateUtil.isRequired(spec_id[j])){
						spec_ids=spec_id[j].replace(" ", "");
					}
					if(!ValidateUtil.isRequired(order_num[j])){
						order_nums=order_num[j].replace(" ", "");
					}
					orderdetail.setOrder_id(order_id);
					orderdetail.setGoods_attr(spec_name[j]);
					orderdetail.setGoods_price(Double.parseDouble(sale_price[j]));
					orderdetail.setTax_rate(tax_rate[j]);
					orderdetail.setGoods_id(goods_ids);
					orderdetail.setOrder_num(order_nums);
					orderdetail.setSpec_id(spec_ids);
					orderdetail.setSubtotal(subtotal[j].trim());
					if(coupon_goods_id.contains(goods_ids)) {
						if(!ValidateUtil.isRequired(coupon_id)){
							orderdetail.setCoupon_id(coupon_id);
						}
						if(!ValidateUtil.isRequired(coupon_money)){
							orderdetail.setCoupon_money(coupon_money);
						}
						//判断商品是否使用优惠券
						orderdetail.setUse_coupon("1");
					}else {
						//判断商品是否使用优惠券
						orderdetail.setUse_coupon("0");
					}
					if(group_id!=null&&!group_id.equals("")){
						orderdetail.setRemark(group_id);
					}
					if(spike_id!=null&&!spike_id.equals("")){
						orderdetail.setRemark(spike_id);
					}
					if(combo_id!=null&&!combo_id.equals("")){
						orderdetail.setRemark(combo_id);
					}
					
					//判断商品是否赠送优惠券或者红包或者赠品
					if(!ValidateUtil.isRequired(sale_id) && integral_state.equals("0")){
						String[] sale_ids = sale_id.trim().split(",");
						for(int k = 0; k < sale_ids.length; k ++) {
						  Salegoods salegoods = salegoodsDao.get(sale_ids[k]);
							if(salegoods != null) {
								//1优惠券，2红包，3赠品
					        	String state = SalegoodsFuc.getCoupon(salegoods, subtotal[j]);
					        	if(state.equals("1")) {
					        		if(ValidateUtil.isRequired(send_coupon_id)) {
					        			send_coupon_id = salegoods.getCoupon_plan();
					        		}else {
					        			send_coupon_id += "," + salegoods.getCoupon_plan();
					        		}
					        	} else if(state.equals("2")) {
					        		if(ValidateUtil.isRequired(send_red_id)) {
					        			send_red_id = salegoods.getCoupon_plan();
					        		}else {
					        			send_red_id += "," + salegoods.getCoupon_plan();
					        		}			        		
					        	} else if(state.equals("3")) {
					        		if(ValidateUtil.isRequired(fg_ids)) {
					        			fg_ids = salegoods.getCoupon_plan();
					        		}else {
					        			fg_ids += "," + salegoods.getCoupon_plan();
					        		}			        		
					        	}
							}
						}
					}
			        if(goodsdetail.getGoods_name()!=null){
			        	orderdetail.setTemporary_goodsname(goodsdetail.getGoods_name());
			        }
			        if(goodsdetail.getList_img()!=null){
			        	orderdetail.setTemporary_goodsimg(goodsdetail.getList_img());
					}
					this.orderdetailDao.insert(orderdetail);
				}
				
				//插入优惠券ID红包ID
				if(!ValidateUtil.isRequired(send_coupon_id) || !ValidateUtil.isRequired(send_red_id) || !ValidateUtil.isRequired(fg_ids)) {
					goodsorder.setSend_coupon_id(send_coupon_id);
					goodsorder.setSend_red_id(send_red_id);
					goodsorder.setFg_ids(fg_ids);
					this.goodsorderDao.update(goodsorder);
				}
				
				//修改优惠券使用状态
				if(!ValidateUtil.isRequired(comsumer_id)) {
					Comsumer comsumer = this.comsumerDao.get(comsumer_id);
					comsumer.setUse_state("1");
					SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					String nowtime = df.format(new Date());
					comsumer.setUse_date(nowtime);
					comsumer.setOrder_id(order_id);
					this.comsumerDao.update(comsumer);
				}

				
				//修改红包使用状态
				if(!ValidateUtil.isRequired(redsumer_id)) {
					Redsumer redsumer = this.redsumerDao.get(redsumer_id);
					redsumer.setUse_state("1");
					SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					String nowtime = df.format(new Date());
					redsumer.setUse_date(nowtime);
					redsumer.setOrder_id(order_id);
					this.redsumerDao.update(redsumer);
				}
				
				// 插入订单异动记录
				Ordertrans ordertrans=new Ordertrans();
				ordertrans.setOrder_id(order_id);
				ordertrans.setCust_id(session_cust_id);
				ordertrans.setUser_id(session_user_id);
				ordertrans.setOrder_state("1");
				ordertrans.setReason(CommparaFuc.getReason("1", "会员下订单"));
				Memberuser memberuser = memberuserDao.getPKByCustID(session_cust_id);
				ordertrans.setOpt_username(memberuser.getUser_name());
				this.ordertransDao.insert(ordertrans);	
				//发送信息提醒
				//mestipByBuyer("3",order_id);
			}
			
           //首次下单插入优惠券--cjy
		    int q = 	this.csid(session_cust_id);
		    if(q==1){
		    	Comsumer comsumer = new Comsumer();
				comsumer.setCoupon_id("28");
				comsumer.setCust_id(session_cust_id);
				comsumer.setUse_state("0");
			     comsumer.setComsumer_no(str());
				this.comsumerDao.insert(comsumer);
				comsumer.setCoupon_id("29");
				comsumer.setCust_id(session_cust_id);
				comsumer.setUse_state("0");
				comsumer.setComsumer_no(str());
			    this.comsumerDao.insert(comsumer);
			    comsumer.setCoupon_id("30");
				comsumer.setCust_id(session_cust_id);
				comsumer.setUse_state("0");
				comsumer.setComsumer_no(str());
			    this.comsumerDao.insert(comsumer);
			    comsumer.setCoupon_id("31");
				comsumer.setCust_id(session_cust_id);
				comsumer.setUse_state("0");
				comsumer.setComsumer_no(str());
				this.comsumerDao.insert(comsumer);
				comsumer.setCoupon_id("32");
				comsumer.setCust_id(session_cust_id);
				comsumer.setUse_state("0");
				comsumer.setComsumer_no(str());
				this.comsumerDao.insert(comsumer);
				comsumer.setCoupon_id("33");
				comsumer.setCust_id(session_cust_id);
				comsumer.setUse_state("0");
				comsumer.setComsumer_no(str());
				this.comsumerDao.insert(comsumer);
		    	
		    }
			
			return order_id_str;
		}
		 public boolean ifcontansInfo(String gid,String gstr){
				boolean  fage=false;
				if(gid!=null&&!"".equals(gid)&&gstr!=null&&!"".equals(gstr)){
					String gstrs[]=gstr.split(",");
					if(gstrs!=null){
						for(int i=0;i<=gstrs.length-1;i++){
							if(gstrs[i]!=null){
								if(gid.equals(gstrs[i].toString())){
									fage=true;
								}
							}
						}
					}
				}
				return fage;
			}
		
		
		//判断是否是新用户下单 0：已有订单 1：新下单
	  	
	  	public int csid(String CustID){
	  		List or   = this.goodsorderDao.getCustlist(CustID);
	  		if(or==null&& or.size()<1){
	  		    return 1;
	  		}else if(or!=null&&or.size()>1){
	  			 return 0;
	  		}else{
	  			return 1;
	  		}
	  	}
		
	/**	
	 * @author : HXK
	 * @param :mes_id：消息提醒模版 order_id：订单ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台订单提醒-发送给买家
	 */
	public void mestipByBuyer(String mes_id,String order_id) throws Exception{
		Goodsorder gorder = new Goodsorder();
		gorder = goodsorderDao.get(order_id);
		if(gorder != null){
			MessageAltFuc mesalt=new MessageAltFuc();
			mesalt.messageAutoSend(mes_id,gorder.getBuy_cust_id(),gorder);
		}
	}
	/**
	 * @author : HZX
	 * @date : Sep 9, 2014 1:16:31 PM
	 * @Method Description :取商品价格
	 */
	public String getKey(String goods_id,String spec_id,String key){
		String new_key ="0";
		Map priMap=new HashMap();
		priMap.put("goods_id", goods_id.replace(" ", ""));
		if(spec_id!=null&&!"".equals(spec_id)&&!"0".equals(spec_id.replace(" ", ""))){
			priMap.put("spec_id", spec_id.replace(" ", ""));
		}
		List goodsList=this.goodsattrDao.getWebList(priMap);
		if(goodsList!=null&&goodsList.size()>0){
				Map pMap=(Map) goodsList.get(0);
				if(pMap.get(key)!=null){
					new_key=pMap.get(key).toString();
				}
		}
		return new_key;
	}
	public String getTax_rate(String goods_id_one){
		String tax_rate ="";
		Goods goods =new Goods();
		goods =this.goodsDao.get(goods_id_one);
		if(goods!=null){
				if(goods.getTax_rate()!=null){
					tax_rate=goods.getTax_rate();
				}
		}
		return tax_rate;
	}
	/**
	 * @author : HZX
	 * @date : Nov 13, 2014 9:47:31 AM
	 * @Method Description :取团购价格
	 */
	public String  getGrouprice(String group_id){
		String groupprice;//团购价格
		//获取团购价格
		HashMap ladderMap = new HashMap();
		//目前采用一口价交易方式
		ladderMap.put("onlylownum", "1");
		ladderMap.put("group_id", group_id);
		List groupladderList = groupladderDao.getList(ladderMap);
		if(groupladderList!=null && groupladderList.size()>0){
			HashMap laddervalue = (HashMap)groupladderList.get(0);
			if(laddervalue!=null && laddervalue.get("price")!=null){
				groupprice = laddervalue.get("price").toString();
				return groupprice;
			}
			
		}
		return null;
	}
	
	/**
	 * @author : HZX
	 * @date : Nov 13, 2014 9:47:31 AM
	 * @Method Description :取秒杀价格
	 */
	public String  getSpikeprice(String spike_id){
		String spikeprice;//秒杀价格
		//获取秒杀价格
		Spikegoods spikegoods=new Spikegoods();
		spikegoods = this.spikegoodsDao.get(spike_id);
		if(spikegoods!=null){
			spikeprice=spikegoods.getPrice().toString();
			return spikeprice;
		}
		return null;
	}
	/**
	 * @author : HZX
	 * @date : Nov 21, 2014 11:22:52 AM
	 * @Method Description :取套餐价格
	 */
	public String  getComborice(String combo_id){
		String comboprice;//套餐价格
		//获取套餐价格
		Combo combo=new Combo();
		combo = this.comboDao.get(combo_id);
		if(combo!=null){
			comboprice=combo.getCombo_price();
			return comboprice;
		}
		return null;
			
	}
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 21, 2014 10:14:17 AM
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
	 * 会员使用余额支付
	 */
	public String useNumPay(String session_user_id,String buy_cust_id, String[] order_id) throws Exception {
			//更新库存
			Map maps=new HashMap();
			maps.put("order_id", order_id[0]);
			List sdetailList=orderdetailDao.getList(maps);
			if(sdetailList!=null&&sdetailList.size()>0){
				for(int j=0;j<sdetailList.size();j++){
				    Map detail = (HashMap)sdetailList.get(j);
				    if(detail!=null){
				    	//获取购买数量
				    	String num ="";
				    	String goods_id="";
				    	String specstr="";
				    	String stocknum="";
				    	HashMap attrmap = new HashMap();
				    	if(detail.get("order_num")!=null){
				    		num = detail.get("order_num").toString();
				    	}
				    	//获取goodsID
				    	if(detail.get("goods_id")!=null){
				    		goods_id = detail.get("goods_id").toString();
				    		attrmap.put("goods_id", goods_id);
				    	}
				    	if(detail.get("specstr")!=null&&!"0".equals(detail.get("specstr"))){
				    		specstr = detail.get("specstr").toString().replace(" ","");
				    		attrmap.put("specstr", specstr);
				    	}
				    	
				    	
				    	List attrlist = goodsattrDao.getList(attrmap);
				    	if(attrlist!=null && attrlist.size()>0){
				    		HashMap mapvalue = new HashMap();
				    		mapvalue =(HashMap)attrlist.get(0);
				    		stocknum=mapvalue.get("stock").toString();
				    		String goods_item = mapvalue.get("goods_item").toString();
				    		Goodsattr goodsattr = goodsattrDao.get(goods_item);
				    		if(Integer.parseInt(stocknum)<Integer.parseInt(num)){
					    		//库存不足
					    		return"";
					    	}else{
					    		//减少库存
					    		goodsattr.setStock(Integer.toString(Integer.parseInt(stocknum)-Integer.parseInt(num)));
					    		goodsattrDao.update(goodsattr);
					    		//增加销量
					    		//更新商品销售量, 销量记录 在付款之后就加销量
								updateGoodsSales(order_id[0]);
					    	}
				    	}
				    	
				    	
				    }
				}
			}
			
			
			//更新订单状态，交易成功操作
			Goodsorder goodsorder = new Goodsorder();
			goodsorder = this.goodsorderDao.get(order_id[0]);
			Double buyuse_money=0.0,ordercount=0.0;
			if(goodsorder != null){
				Memberfund buy_mf = this.memberfundDao.get(buy_cust_id);
				ordercount=goodsorder.getTatal_amount();
			    buyuse_money=Double.parseDouble(buy_mf.getUse_num());
				if(ordercount>buyuse_money){//余额不足
					return "0";
				}
				Map map=new HashMap();
				map.put("order_id", order_id[0]);
				List detailList=orderdetailDao.getList(map);
				if(detailList!=null&&detailList.size()>0){
					for(int j=0;j<detailList.size();j++){
					    Map detail = (HashMap)detailList.get(j);
					    if(detail!=null){
					    	if(detail.get("goods_id")!=null){
					    		Goods goods = goodsDao.get(detail.get("goods_id").toString());
					    		if(goods==null||goods.equals("")||goods.getIs_del().equals("0")||(!goods.getActive_state().equals("4")&&goods.getIs_up().equals("1"))){
					    			return detail.get("order_id").toString()+"#"+detail.get("goods_id").toString();
					    		}
					    	}
					    }
					}
				}
				//处理资金及流水
				fundManage(order_id[0],buy_cust_id);
				//若是虚拟商品，生成消费码
				if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("0")){
					//生成消费码
					String mark_id = RandomStrUtil.getRand("12");
					goodsorder.setMark_id(mark_id);
				}
				//若是试用订单,减掉剩余库存
				if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("7")){
                   Map detailMap=new HashMap();
                   detailMap.put("order_id", goodsorder.getOrder_id());
                   List list=this.orderdetailDao.getList(detailMap);
                   if(list!=null&&list.size()>0){
                	   Map tryMap=(HashMap)list.get(0);
                	   //商品ID
                	   String goods_id=tryMap.get("goods_id").toString();
                	   if(!ValidateUtil.isRequired(goods_id)){
                		   Map trygoodsMap=new HashMap(); 
                		   trygoodsMap.put("goods_id", goods_id);
                		   List trygoodsList=trygoodsDao.getList(trygoodsMap);
                		   if(trygoodsList!=null&&trygoodsList.size()>0){
                	    	   //获取试用商品
                			   Map trymap=(HashMap)trygoodsList.get(0);
                	    	   Trygoods trygoods=trygoodsDao.get(trymap.get("try_id").toString());
                	    	   //试用商品剩余量-1
                	    	   String surplus=(Integer.valueOf(trygoods.getSurplus())-1)+"";
                	    	   trygoods.setSurplus(surplus);
                	    	   trygoodsDao.update(trygoods);
                	    	   //获取试用申请对象
                	    	   Map tryapplyMap=new HashMap();
                	    	   tryapplyMap.put("order_id", goodsorder.getOrder_id());
                	    	   List tryapplyList=tryapplyDao.getList(tryapplyMap);
                	    	   if(tryapplyList!=null&&tryapplyList.size()>0){
                	    		   //修改试用状态
                	    		   Map tryapMap=(HashMap)tryapplyList.get(0);
                	    		   Tryapply tryapply=tryapplyDao.get(tryapMap.get("tryapply_id").toString());
                	    		   tryapply.setStatus("1");
                	    		   tryapplyDao.update(tryapply);
                	    	   }
                	       }
                	   }
                   }
				}
				//获取当前时间
				String cur_date_str = "";
				SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				Calendar cal = Calendar.getInstance();
				cur_date_str = format.format(cal.getTime());
				goodsorder.setOrder_state("2");
				//4:支付类型为：余额支付
				goodsorder.setPay_id("4");
				goodsorder.setPay_time(cur_date_str);
				goodsorder.setBalance_use(ordercount.toString());
				goodsorder.setPay_trxid(DateUtil.getFormatLong()+DateUtil.getSix());
				this.goodsorderDao.update(goodsorder);
			}
			// 插入订单异动记录
			Ordertrans ordertrans = new Ordertrans();
			ordertrans.setOrder_id(order_id[0]);
			ordertrans.setCust_id(buy_cust_id);
			ordertrans.setUser_id(session_user_id);
			ordertrans.setOrder_state("2");
			ordertrans.setReason(CommparaFuc.getReason("2", "会员已付款"));
			Memberuser memberuser = memberuserDao.getPKByCustID(buy_cust_id);
			ordertrans.setOpt_username(memberuser.getUser_name());
			this.ordertransDao.insert(ordertrans);	
			
			//若是虚拟商品，使用不同模板
			if(goodsorder.getOrder_type() != null && goodsorder.getOrder_type().equals("0")){
				//发送信息提醒
				mestipByBuyer("6",order_id[0]);
			}else{
				//发送信息提醒
				mestipByBuyer("4",order_id[0]);
			}
		
		return "11";//表示正常情况
	}
	 /**
 	 * @author QJY
 	 * @function 商量销售量更新
 	 * @date 2015-08-24
 	 * @throws Exception
 	 */
 	@SuppressWarnings("unchecked")
	public void updateGoodsSales(String order_id)throws Exception{
 		Map detailMap = new HashMap();
 		detailMap.put("order_id_s", order_id);
 		List orderdetailList = this.orderdetailDao.getList(detailMap);
 		if(orderdetailList !=null && orderdetailList.size()>0){
 			Map salesMap = new HashMap();
 			Map volumeMap =new HashMap();
 			String goods_id = "",sale_num="0";
 			Integer sales_volume = 0;
 			Goods goods = new Goods();
 			for(int i=0;i<orderdetailList.size();i++){
 				salesMap = (Map) orderdetailList.get(i);
 				if(salesMap!=null && salesMap.get("goods_id")!=null){
 					goods_id = salesMap.get("goods_id").toString();
 					goods = this.goodsDao.get(goods_id);
 					if(goods.getSale_num()!=null&&!"".equals(goods.getSale_num())){
 						sale_num = goods.getSale_num();
 					}
 				}
 				if(salesMap!=null && salesMap.get("order_num")!=null){
 					sales_volume = Integer.valueOf(sale_num)+Integer.valueOf(salesMap.get("order_num").toString());
 				}else {
 					sales_volume =0;
 				}
 				volumeMap.put("goods_id", goods_id);
 				volumeMap.put("sales_volume", sales_volume+"");
 				this.goodsDao.updateSalesVolume(volumeMap);//订单中每个商品的销售数量的更新
 			}
 		}
 	}
	/**	
	 * @author : WXP
	 * @param :
	 * @date Mar 12, 2014 1:33:55 PM
	 * @Method Description :资金处理
	 */
	private void fundManage(String  oid,String cust_id){
		Goodsorder order = this.goodsorderDao.get(oid);
		double final_amount = order.getTatal_amount()-Double.parseDouble(order.getBalance_use())-Double.parseDouble(order.getIntegral_use());
		Memberuser memberuser = this.memberuserDao.getPKByCustID(cust_id);
		double use_num =  memberfundService.freezeNum(cust_id, final_amount, 0);
		//运营商的资金异动
		sysfundService.freezeNum(final_amount, 0);
		//买家的资金异动
		Fundhistory buy_fh = new Fundhistory();
		buy_fh.setBalance(use_num);
		buy_fh.setCust_id(cust_id);
		buy_fh.setFund_in(0.0);
		buy_fh.setFund_out(final_amount);
		buy_fh.setUser_id(memberuser.getUser_id());
		buy_fh.setAction_type("2");
		buy_fh.setReason("会员为订单号:"+oid+"余额支付"+final_amount+"元");
		this.fundhistoryDao.insert(buy_fh);
		
	}
	
	
	public void updateRefund(Refundapp refundapp,String seller_state,String session_user_id,String refund_state,String reason) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//付款到买家
		refundapp.setSeller_date(df.format(new Date()));
		refundapp.setSure_time(df.format(new Date()));
		refundapp .setSeller_user_id(session_user_id);
		refundapp.setIs_treated("0");
		//0：同意退款，1：不同意退款
		refundapp .setSeller_state(seller_state);
		refundapp.setRefund_state(refund_state);
		if(refund_state.equals("0")){
			if(seller_state.equals("1")){
				int deny_num=Integer.parseInt(refundapp.getDeny_num());
				if(deny_num==0){
					refundapp.setSeller_reason(reason+"["+df.format(new Date())+"]");
				}else{
					refundapp.setSeller_reason(refundapp.getSeller_reason()+"</br><font ></font></br>"+reason);
				}
				deny_num++;
				refundapp.setDeny_num((deny_num+"").trim());
				refundapp.setRefund_state("2");
			}else if(seller_state.equals("0")){
				refundapp.setInfo_state("0");
				refundapp.setRefund_state("1");
			}
		}else{
			refundapp.setInfo_state("1");
		}
		
		refundappDao.update(refundapp);
		
	}
	public void saveEval(Map evalMap) {
		String sell_cust_id= (String) evalMap.get("sell_cust_id");
		String[] order_goods_id_strs =(String[]) evalMap.get("order_goods_id_strs");
		String[] order_goods_feng_strs =(String[]) evalMap.get("order_goods_feng_strs");
		String[] order_goods_content_strs=(String[]) evalMap.get("order_goods_content_strs");
		String[] order_share_pic_strs=(String[]) evalMap.get("order_share_pic_strs");
		Integer  orderdetaiCount=(Integer) evalMap.get("orderdetaiCount");
		String session_cust_id =(String) evalMap.get("session_cust_id");
		String session_user_id =(String) evalMap.get("session_user_id");
		String order_delivery_speed=(String) evalMap.get("order_delivery_speed");
		String order_service_attitude=(String) evalMap.get("order_service_attitude");
		String order_id=(String) evalMap.get("order_id");
		String order_desc=(String) evalMap.get("order_desc");
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
			//如果好评或的额外积分
			/*if("1".equals(order_goods_feng_strs[i])){
				Memberinter meminter = new Memberinter();
				// 获取会员当前的积分
				meminter = memberinterDao.get(session_cust_id);
				String cfg_comment = SysconfigFuc.getSysValue("cfg_comment");
				Double coutinter = 0.0;
				// 统计当前订单的总额数为获得积分总额数+当前积分数
				coutinter = Double.parseDouble(cfg_comment) * Double.parseDouble(SysconfigFuc.getSysValue("cfg_sc_pointsrule"))/100
						+ Double.parseDouble(meminter.getFund_num());
				meminter.setFund_num(coutinter.toString());
				memberinterDao.update(meminter);
				// 插入积分流
				Interhistory interhistory = new Interhistory();
				interhistory.setCust_id(session_cust_id);
				interhistory.setInter_in(cfg_comment);
				interhistory.setInter_out("0");
				interhistory.setThisinter(coutinter.toString());
				interhistory.setUser_id(session_user_id);
				interhistory.setReason("好评获得积分");
				interhistoryDao.insert(interhistory);
				
			}*/
			goodseval.setG_comment(str_comment);
			goodseval.setG_eval(order_goods_feng_strs[i]);
			goodseval.setGoods_id(order_goods_id_strs[i]);
			goodseval.setExplan_cust_id(sell_cust_id);//评价对象
			goodseval.setIs_enable("0");
			goodseval.setIs_two("0");
			goodseval.setOrder_id(order_id);
			goodseval.setUser_id(session_user_id);
			if(!ValidateUtil.isRequired(order_share_pic_strs[i])){
				goodseval.setIs_img("1");//有晒图
			}else{
				goodseval.setIs_img("0");//无晒图
			}
			String eval_id=goodsevalDao.insertGetPk(goodseval);
			MembercreditFuc.updateSellNum(sell_cust_id, Integer.valueOf(order_goods_feng_strs[i]));
			//插入晒图
			if(!ValidateUtil.isRequired(order_share_pic_strs[i])&&!order_share_pic_strs[i].equals("暂无晒图")){
				Goodsshare goodsshare =  new Goodsshare();
    			goodsshare.setEval_id(eval_id);
    			goodsshare.setCust_id(session_cust_id);
    			goodsshare.setUser_id(session_user_id);
    			goodsshare.setGoods_id(order_goods_id_strs[i]);
    			goodsshare.setShare_pic(order_share_pic_strs[i]);
				goodsshareDao.insert(goodsshare);
			}
		}
		//插入店铺评分
		Sellerscore sellerscore =new Sellerscore();
		sellerscore.setDelivery_score(order_delivery_speed);
		sellerscore.setDesc_score(order_desc);
		sellerscore.setService_score(order_service_attitude);
		sellerscore.setGet_cust_id(sell_cust_id);
		sellerscore.setSelf_cust_id(session_cust_id);
		sellerscore.setUser_id(session_user_id);
		
	}
	
	public int getBuyCount(Map map) {
		return orderdetailDao.getBuyCount(map);
	}
	
	public void updateState(Map map){
		this.orderdetailDao.updateState(map);
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情 
	 */
	public int deleteByOrderId(String order_id) {
		return  this.orderdetailDao.deleteByOrderId(order_id);
	}
	/**
	 * @Method Description :更新商品库存 下订单 支付完成减少库存
	 * @author: HXK
	 * @date : Oct 31, 2015 1:54:42 PM
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public void updateStockBypayment(String orderId){
		if(!ValidateUtil.isRequired(orderId)){
			List orderdetaiupList=new ArrayList();
			HashMap putorder= new HashMap();
			putorder.put("order_id", orderId);
			orderdetaiupList=orderdetailDao.getList(putorder);
			if(orderdetaiupList!=null&&orderdetaiupList.size()>0){
				for(int i=0;i<orderdetaiupList.size();i++){
					HashMap gorderMap= new HashMap();
					gorderMap=(HashMap)orderdetaiupList.get(i);
					if(gorderMap!=null){
						String goods_id="",specstr_s="";
						Integer stock_s=0;
						if(gorderMap.get("goods_id")!=null&&!"".equals(gorderMap.get("goods_id"))){
							goods_id=gorderMap.get("goods_id").toString();
						}
						if(gorderMap.get("spec_id")!=null&&!"".equals(gorderMap.get("spec_id"))&&!"0".equals(gorderMap.get("spec_id"))){
							specstr_s=gorderMap.get("spec_id").toString();
						}
						if(gorderMap.get("order_num")!=null&&!"".equals(gorderMap.get("order_num"))){
							stock_s=Integer.valueOf(gorderMap.get("order_num").toString());
						}
						updateStock('0',"",goods_id,specstr_s,stock_s);
					}
					
				}
			}
		}
	}
	
	
	
	/**
	 * @Method Description :更新商品库存 下订单 支付完成减少库存
	 * @author: HXK
	 * @date : Oct 31, 2015 1:54:42 PM
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean updateStockByOnlinepayment(String orderId){
		boolean fage=true;
		if(!ValidateUtil.isRequired(orderId)){
			//更新库存
			Map maps=new HashMap();
			maps.put("order_id", orderId);
			List sdetailList=orderdetailDao.getList(maps);
			if(sdetailList!=null&&sdetailList.size()>0){
				for(int j=0;j<sdetailList.size();j++){
				    Map detail = (HashMap)sdetailList.get(j);
				    if(detail!=null){
				    	//获取购买数量
				    	String num ="";
				    	String goods_id="";
				    	String specstr="";
				    	String stocknum="";
				    	HashMap attrmap = new HashMap();
				    	if(detail.get("order_num")!=null){
				    		num = detail.get("order_num").toString();
				    	}
				    	//获取goodsID
				    	if(detail.get("goods_id")!=null){
				    		goods_id = detail.get("goods_id").toString();
				    		attrmap.put("goods_id", goods_id);
				    	}
				    	if(detail.get("specstr")!=null&&!"0".equals(detail.get("specstr"))){
				    		specstr = detail.get("specstr").toString().replace(" ","");
				    		attrmap.put("specstr", specstr);
				    	}
				    	
				    	
				    	List attrlist = goodsattrDao.getList(attrmap);
				    	if(attrlist!=null && attrlist.size()>0){
				    		HashMap mapvalue = new HashMap();
				    		mapvalue =(HashMap)attrlist.get(0);
				    		stocknum=mapvalue.get("stock").toString();
				    		String goods_item = mapvalue.get("goods_item").toString();
				    		Goodsattr goodsattr = goodsattrDao.get(goods_item);
				    		if(Integer.parseInt(stocknum)<Integer.parseInt(num)){
				    			//库存不足
				    			goodsattr.setStock("0");
					    		goodsattrDao.update(goodsattr);
					    		fage= false;
					    	}else{
					    		goodsattr.setStock(Integer.toString(Integer.parseInt(stocknum)-Integer.parseInt(num)));
					    		goodsattrDao.update(goodsattr);
					    		fage= true;
					    	}
				    	}
				    }
				}
			}
	    }else {
	    	fage=false;
		}
	   return fage;
	}
	
	
	
	/**
	 * @Method Description :在线付款的时候 检查库存是否够
	 * @author: HXK
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean checkGoodsNum(String orderId){
		boolean fage=true;
		if(!ValidateUtil.isRequired(orderId)){
			Map maps=new HashMap();
			maps.put("order_id", orderId);
			List sdetailList=orderdetailDao.getList(maps);
			if(sdetailList!=null&&sdetailList.size()>0){
				for(int j=0;j<sdetailList.size();j++){
				    Map detail = (HashMap)sdetailList.get(j);
				    if(detail!=null){
				    	//获取购买数量
				    	String num ="";
				    	String goods_id="";
				    	String specstr="";
				    	String stocknum="";
				    	HashMap attrmap = new HashMap();
				    	if(detail.get("order_num")!=null){
				    		num = detail.get("order_num").toString();
				    	}
				    	//获取goodsID
				    	if(detail.get("goods_id")!=null){
				    		goods_id = detail.get("goods_id").toString();
				    		attrmap.put("goods_id", goods_id);
				    	}
				    	if(detail.get("specstr")!=null&&!"0".equals(detail.get("specstr"))){
				    		specstr = detail.get("specstr").toString().replace(" ","");
				    		attrmap.put("specstr", specstr);
				    	}
				    	List attrlist = goodsattrDao.getList(attrmap);
				    	if(attrlist!=null && attrlist.size()>0){
				    		HashMap mapvalue = new HashMap();
				    		mapvalue =(HashMap)attrlist.get(0);
				    		stocknum=mapvalue.get("stock").toString();
				    		if(Integer.parseInt(stocknum)<Integer.parseInt(num)){
				    			//库存不足
					    		fage= false;
					    	}
				    	}
				    }else {
				    	fage=false;
					}
				}
			}else {
		    	fage=false;
			}
	    }else {
	    	fage=false;
		}
	   return fage;
	}
	
	
	
	/**
	 * @Method Description :更新商品库存 支付完成 会员取消订单
	 * @author: HXK
	 * @date : Oct 31, 2015 1:54:42 PM
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean addStockByCancleOrder(String orderId){
		boolean fage=true;
		if(!ValidateUtil.isRequired(orderId)){
			//更新库存
			Map maps=new HashMap();
			maps.put("order_id", orderId);
			List sdetailList=orderdetailDao.getList(maps);
			if(sdetailList!=null&&sdetailList.size()>0){
				for(int j=0;j<sdetailList.size();j++){
				    Map detail = (HashMap)sdetailList.get(j);
				    if(detail!=null){
				    	//获取购买数量
				    	String num ="";
				    	String goods_id="";
				    	String specstr="";
				    	String stocknum="";
				    	HashMap attrmap = new HashMap();
				    	if(detail.get("order_num")!=null){
				    		num = detail.get("order_num").toString();
				    	}
				    	//获取goodsID
				    	if(detail.get("goods_id")!=null){
				    		goods_id = detail.get("goods_id").toString();
				    		attrmap.put("goods_id", goods_id);
				    	}
				    	if(detail.get("specstr")!=null&&!"0".equals(detail.get("specstr"))){
				    		specstr = detail.get("specstr").toString().replace(" ","");
				    		attrmap.put("specstr", specstr);
				    	}
				    	List attrlist = goodsattrDao.getList(attrmap);
				    	if(attrlist!=null && attrlist.size()>0){
				    		HashMap mapvalue = new HashMap();
				    		mapvalue =(HashMap)attrlist.get(0);
				    		stocknum=mapvalue.get("stock").toString();
				    		String goods_item = mapvalue.get("goods_item").toString();
				    		Goodsattr goodsattr = goodsattrDao.get(goods_item);
				    		goodsattr.setStock(Integer.toString(Integer.parseInt(stocknum)+Integer.parseInt(num)));
				    		goodsattrDao.update(goodsattr);
				    		fage=true;
				    	}
				    }
				}
			}
	    }else {
	    	fage=false;
		}
	   return fage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


