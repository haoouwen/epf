/*
 
 * Package:com.rbt.action
 * FileName: CartgoodsAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rbt.action.BaseAction;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SalegoodsFuc;
import com.rbt.model.Cartgoods;
import com.rbt.model.Goods;
import com.rbt.model.Salegoods;
import com.rbt.service.ICartgoodsService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.ISalegoodsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 购物车action类
 * @author 创建人 WXP
 * @date 创建日期 Mon May 13 14:10:06 CST 2014
 */
@Controller
public class CartgoodsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Cartgoods cartgoods;

	/*******************业务层接口****************/
	@Autowired
	private ICartgoodsService cartgoodsService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsattrService goodsattrService;
	@Autowired
	private ISalegoodsService salegoodsService;

	/*********************集合******************/
	public List cartgoodsList;// 购物车信息集合


	
	/**
	 * 方法描述：返回新增购物车页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增购物车
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(cartgoods);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.cartgoodsService.insert(cartgoods);
		this.addActionMessage("新增购物车成功！");
		this.cartgoods = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改购物车信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(cartgoods);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.cartgoodsService.update(cartgoods);
		this.addActionMessage("修改购物车成功！");
		return list();
	}
	/**
	 * 方法描述：删除购物车信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.cartgoods.getTrade_id();
		id = id.replace(" ", "");
		this.cartgoodsService.delete(id);
		this.addActionMessage("删除购物车成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		//根据页面提交的条件找出信息总数
		int count=this.cartgoodsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		cartgoodsList = this.cartgoodsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出购物车信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.cartgoods.getTrade_id();
		if(id==null || id.equals("")){
			cartgoods = new Cartgoods();
		}else{
			cartgoods = this.cartgoodsService.get(id);
		}
		return goUrl(VIEW);
	}

	/**
	 * 加入购物车
	 * @throws IOException
	 */
	public void addCart() throws IOException{
		//------------------获取URL参数开始--------------
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		PrintWriter out=response.getWriter();
		//商品ID
		String goods_id = request.getParameter("goods_id");
		//单价
		String sale_price = request.getParameter("sale_price");
		String goods_id_str[]=goods_id.split("#");
		String sale_price_str[]=sale_price.split("#");
        boolean flag=true;
		for(int i=0;i<goods_id_str.length;i++){
			Goods goods=this.goodsService.get(goods_id_str[i]);
			Map pageMap=new HashMap();
			pageMap.put("goods_id", goods_id_str[i]);
			int total=this.goodsattrService.getTotalStock(pageMap);
		    pageMap.put("cust_id",this.session_cust_id);
		    List list=this.cartgoodsService.getList(pageMap);
		    //判断是否库存不足
		    if(total<=0){
		    	flag=false;
		    	break;
		    }else if(list!=null&&list.size()>0){//判断购物车是否存在该商品
		    	HashMap cartMap=(HashMap)list.get(0);
		    	String trade_id=cartMap.get("trade_id").toString();
		    	cartgoods=this.cartgoodsService.get(trade_id);
		        //在原来购买商品的数量基础+1
		    	int buy_num=Integer.valueOf(cartgoods.getBuy_num());
		        buy_num=buy_num+1;
		        cartgoods.setBuy_num(buy_num+"");
		        this.cartgoodsService.update(cartgoods);
		    }else{//否则插入购物车信息
		    	cartgoods=new Cartgoods();
		    	cartgoods.setBuy_num("1");
		    	cartgoods.setCookie_id(goods_id_str[i]);
		    	cartgoods.setCust_id(this.session_cust_id);
		    	cartgoods.setUser_name(this.session_user_name);
		    	cartgoods.setGoods_id(goods_id_str[i]);
		    	cartgoods.setImg_path(goods.getList_img());
		    	cartgoods.setIs_virtual("1");
		    	cartgoods.setGoods_name(goods.getGoods_name());
		    	cartgoods.setIntegral(Double.valueOf(sale_price_str[i]));
		    	cartgoods.setSale_price(Double.valueOf(sale_price_str[i]));
		    	cartgoods.setShop_cust_id(goods.getIs_international());
		    	this.cartgoodsService.insert(cartgoods);
		    }
		}
		if(flag){
			out.write("1");
		}else{
			out.write("0");
		}
		
	}
	
	
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 13, 2014 1:02:09 PM
	 * @Method Description :加入购物车
	 *//*
	public void addCartGoods() throws IOException{
		//------------------获取URL参数开始--------------
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		HttpSession session=getSession();
		response.setCharacterEncoding("UTF-8");
		//商品ID
		String goods_id = request.getParameter("goods_id");
		cartgoods.setGoods_id(goods_id);
		//购买数量
		String buy_num = request.getParameter("buy_num");
		cartgoods.setBuy_num(buy_num);
		//cookie唯一编码
		String cookie_id = request.getParameter("cookie_id");
		cartgoods.setCookie_id(cookie_id);
		//店铺名称
		String shop_name = request.getParameter("shop_name");
		cartgoods.setShop_name(shop_name);
		//店铺QQ
		String shop_qq = request.getParameter("shop_qq");
		cartgoods.setShop_qq(shop_qq);
		//卖家标识
		String shop_cust_id = request.getParameter("shop_cust_id");
		cartgoods.setShop_cust_id(shop_cust_id);
		//用户名
		String user_name = request.getParameter("user_name");
		cartgoods.setUser_name(user_name);
		//商品图片
		String img_path = request.getParameter("img_path");
		cartgoods.setImg_path(img_path);
		//商品名称
		String goods_name = request.getParameter("goods_name");
		cartgoods.setGoods_name(goods_name);
		//规格名称组合
		String spec_name = request.getParameter("spec_name");
		cartgoods.setSpec_name(spec_name);
		//规格值ID组合
		String spec_id = request.getParameter("spec_id");
		cartgoods.setSpec_id(spec_id);
		//单价
		String sale_price = request.getParameter("sale_price");
		cartgoods.setSale_price(Double.valueOf(sale_price));
		//获赠积分
		String integral = request.getParameter("integral");
		cartgoods.setIntegral(Double.valueOf(integral));
		//是否为虚拟商品
		String is_virtual = request.getParameter("is_virtual");
		cartgoods.setIs_virtual(is_virtual);
		if(!ValidateUtil.isRequired(this.session_cust_id)){
			cartgoods.setCust_id(session_cust_id);
		}
		//------------------获取URL参数结束--------------
		//加入购物操作
		String outStr = this.cartgoodsService.addCartGoods(cartgoods, this.session_cust_id);
		PrintWriter out = response.getWriter();
		out.print(outStr);
	}*/
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 14, 2014 9:52:09 AM
	 * @Method Description :统计购物车商品个数总价
	 */
	public void countCartGoods() throws IOException{
		//------------------获取URL参数开始--------------
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		//cookie唯一编码
		String cookie_id = request.getParameter("cookie_id");
		//------------------获取URL参数结束--------------
		String outStr = "";
		try {
			Map map = new HashMap();
			map.put("cookie_id", cookie_id);
			List list = this.cartgoodsService.getList(map);
			outStr = JsonUtil.list2json(list);
		} catch (Exception e) {
			e.printStackTrace();
			outStr = "0";
		}
		
		PrintWriter out = response.getWriter();
		out.print(outStr);
	}
	
	/**	
	 * @author : WXP
	 * @param :trade_id
	 * @date May 15, 2014 9:52:09 AM
	 * @Method Description :删除购物车商品
	 */
	public void delCartGoods() throws IOException{
		//------------------获取URL参数开始--------------
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		//标识
		String trade_id = request.getParameter("trade_id");
		//------------------获取URL参数结束--------------
		//删除操作处理
		String outStr = this.cartgoodsService.delCartGoods(trade_id);
		PrintWriter out = response.getWriter();
		out.print(outStr);
	}
	
	/**	
	 * @author : WXP
	 * @param :trade_id,buy_num
	 * @date May 15, 2014 10:52:09 AM
	 * @Method Description :删除购物车商品
	 */
	public void updateCartGoods() throws IOException{
		//------------------获取URL参数开始--------------
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		//标识
		String trade_id = request.getParameter("trade_id");
		//商品个数
		String buy_num = request.getParameter("buy_num");
		//商品促销ID
		String sale_id = request.getParameter("sale_id");
		//商品总价
		String sale_price = request.getParameter("sale_price");
		//------------------获取URL参数结束--------------
		try {
			Cartgoods cartgoods = new Cartgoods();
			cartgoods = this.cartgoodsService.get(trade_id);
			if(cartgoods != null){
				cartgoods.setBuy_num(buy_num);
				this.cartgoodsService.update(cartgoods);
			}
			if(!ValidateUtil.isRequired(sale_id)) {
				String[] sale_ids = sale_id.trim().split(",");
				for(int i = 0; i < sale_ids.length; i++) {
					Salegoods salegoods = this.salegoodsService.get(sale_ids[i]);
					if(salegoods != null && salegoods.getTerm_state().equals("4")) {
						if(Double.valueOf(sale_price) >= Double.valueOf(salegoods.getNeed_money())) {
							sale_price = SalegoodsFuc.getSalePrice(sale_ids[i], sale_price);
						}
					}else if(salegoods != null && salegoods.getCoupon_state().equals("6")) {
						sale_price = SalegoodsFuc.getSalePrice(sale_ids[i], sale_price);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(sale_price);
	}
	
	/**
	 * @return the cartgoods
	 */
	public Cartgoods getCartgoods() {
		return cartgoods;
	}
	/**
	 * @param Cartgoods
	 *            the cartgoods to set
	 */
	public void setCartgoods(Cartgoods cartgoods) {
		this.cartgoods = cartgoods;
	}
	
	/**
	 * @return the CartgoodsList
	 */
	public List getCartgoodsList() {
		return cartgoodsList;
	}
	/**
	 * @param cartgoodsList
	 *  the CartgoodsList to set
	 */
	public void setCartgoodsList(List cartgoodsList) {
		this.cartgoodsList = cartgoodsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(cartgoods == null){
			cartgoods = new Cartgoods();
		}
		String id = this.cartgoods.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			cartgoods = this.cartgoodsService.get(id);
		}
	}

}

