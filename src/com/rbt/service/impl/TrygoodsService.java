/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: TrygoodsService.java 
 */
package com.rbt.service.impl;

import java.net.URLDecoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.dao.IBuyeraddrDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.IOrdertransDao;
import com.rbt.dao.ITrygoodsDao;
import com.rbt.function.AreaFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Goods;
import com.rbt.model.Goodsorder;
import com.rbt.model.Orderdetail;
import com.rbt.model.Ordertrans;
import com.rbt.model.Trygoods;
import com.rbt.service.ITrygoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 试用商品Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Tue Jun 17 13:55:37 CST 2014
 */
@Service
public class TrygoodsService extends GenericService<Trygoods,String> implements ITrygoodsService {
	@Autowired
	IBuyeraddrDao buyeraddrDao;
	@Autowired
	IGoodsorderDao goodsorderDao;
	@Autowired
	IOrderdetailDao orderdetailDao;
	@Autowired
	IOrdertransDao ordertransDao;
	@Autowired
	IGoodsDao goodsDao;
	ITrygoodsDao trygoodsDao;
	@Autowired
	public TrygoodsService(ITrygoodsDao trygoodsDao) {
		super(trygoodsDao);
		this.trygoodsDao = trygoodsDao;
	}
	
	public String getAddrDiv(HttpServletRequest request,String cust_id,String user_id) throws Exception {
		//获取参数
		String consignee = request.getParameter("consignee");
		String de_consignee = URLDecoder.decode(consignee,"UTF-8");
		String address = request.getParameter("address");
		String de_address = URLDecoder.decode(address,"UTF-8");
		String post_code = request.getParameter("post_code");
		String phone = request.getParameter("phone");
		String cell_phone = request.getParameter("cell_phone");
		String area_attr = request.getParameter("area_attr");
		//最后一级地区ID
		String end_area_attr = area_attr.substring(area_attr.lastIndexOf(",")+1, area_attr.length());
		Buyeraddr ba = new Buyeraddr();
		ba.setConsignee(de_consignee);
		ba.setAddress(de_address);
		ba.setPost_code(post_code);
		ba.setPhone(phone);
		ba.setCell_phone(cell_phone);
		ba.setArea_attr(area_attr);
		ba.setCust_id(cust_id);
		ba.setUser_id(user_id);
		ba.setSel_address("1");
		String addr_id = this.buyeraddrDao.insertGetPk(ba);
		//地区ID转名称
		area_attr = AreaFuc.getAreaNameByMap(area_attr);
		String addrDiv ="<tr>"
			+"<td width='15%'><b><input type='radio' checked='checked' name='adrRadio' value='"+addr_id+"'>商品寄至</b></td>"
			+"<td width='85%'><span>"+area_attr+"</span><span>"+de_address+"</span><span>"+de_consignee+"(收)</span><span>"+cell_phone+"</span></td>"
			+"</tr>";
		return addrDiv;
	}
	
	/**
	 * 生成订单
	 * @return
	 */
	public String addOrder(Map map)throws Exception{
		Buyeraddr buyeraddr = new Buyeraddr();
		String end_area_attr=null;
		String addr_id=map.get("addr_id").toString();
		String session_cust_id=map.get("session_cust_id").toString();
		String session_user_id=map.get("session_user_id").toString();
		String try_id=map.get("try_id").toString();
		String session_level_code = map.get("session_level_code").toString();
		Trygoods trygoods=this.trygoodsDao.get(try_id);
	    Goods goods=this.goodsDao.getByPkNoDel(trygoods.getGoods_id());
		//实例化收货地址对象
		if(addr_id != null && !addr_id.equals("")){
			buyeraddr = this.buyeraddrDao.get(addr_id);
			end_area_attr=buyeraddr.getArea_attr();
			end_area_attr=end_area_attr.split(",")[1].replace(" ", "");
		}
		// 按照日期生成随机数作为订单号
		String order_id = RandomStrUtil.getDateRand()+RandomStrUtil.getNumberRand();
		//店铺佣金
		Double comm_free = 0.00;
		//生成订单
		Goodsorder goodsorder = new Goodsorder();
		goodsorder.setBuy_cust_id(session_cust_id);
		goodsorder.setSell_cust_id(trygoods.getCust_id());
		goodsorder.setSend_mode("0");
		goodsorder.setGoods_amount(Double.parseDouble(goods.getMin_sale_price()));			
		goodsorder.setTatal_amount(Double.parseDouble(trygoods.getPostage()));
		goodsorder.setShip_free(Double.parseDouble(trygoods.getPostage()));
		goodsorder.setOrder_state("1");
		goodsorder.setOrder_id(order_id);
		goodsorder.setComm_free(comm_free);
		goodsorder.setOrder_type("7");//订单类型
		goodsorder.setIs_virtual("1");//实物商品
		//收货地址相关
		goodsorder.setConsignee(buyeraddr.getConsignee());
		goodsorder.setArea_attr(buyeraddr.getArea_attr());
		goodsorder.setBuy_address(buyeraddr.getAddress());
		goodsorder.setZip_code(buyeraddr.getPost_code());
		goodsorder.setTelephone(buyeraddr.getPhone());
		goodsorder.setMobile(buyeraddr.getCell_phone());
		if(Constants.NEIBU_LEVEL_CODE.equals(session_level_code)){
			goodsorder.setIs_vip("1");
		}else {
			goodsorder.setIs_vip("0");
		}
		this.goodsorderDao.insert(goodsorder);
		
		//生成订单详情
		Orderdetail orderdetail = new Orderdetail();
		orderdetail.setOrder_id(order_id);
		orderdetail.setGoods_price(Double.parseDouble(goods.getMin_sale_price()));
		orderdetail.setGoods_id(goods.getGoods_id());
		orderdetail.setOrder_num("1");
		orderdetail.setTemporary_goodsimg(goods.getList_img());
		orderdetail.setTemporary_goodsname(goods.getGoods_name());
		orderdetail.setOrderdetail_state("0");
		if(try_id!=null&&!try_id.equals("")){
			orderdetail.setRemark(try_id);
		}
		this.orderdetailDao.insert(orderdetail);
		// 插入订单异动记录
		Ordertrans ordertrans=new Ordertrans();
		ordertrans.setOrder_id(order_id);
		ordertrans.setCust_id(session_cust_id);
		ordertrans.setUser_id(session_user_id);
		ordertrans.setOrder_state("1");
		ordertrans.setReason(CommparaFuc.getReason("1", "买家下订单"));
		this.ordertransDao.insert(ordertrans);	
		//发送信息提醒
		//mestipByBuyer("3",order_id);
		return order_id;
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
}

