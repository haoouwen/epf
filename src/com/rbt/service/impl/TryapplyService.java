/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: TryapplyService.java 
 */
package com.rbt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IBuyeraddrDao;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.IGoodsorderDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IOrderdetailDao;
import com.rbt.dao.ITryapplyDao;
import com.rbt.dao.ITrygoodsDao;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Goods;
import com.rbt.model.Goodsorder;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Tryapply;
import com.rbt.model.Trygoods;
import com.rbt.service.ITryapplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 申请试用Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Sat Jul 12 09:25:36 CST 2014
 */
@Service
public class TryapplyService extends GenericService<Tryapply,String> implements ITryapplyService {
	@Autowired
	ITrygoodsDao trygoodsDao;
	@Autowired
	IGoodsDao goodsDao;
	@Autowired
	IGoodsorderDao goodsorderDao;
	@Autowired
	IOrderdetailDao orderdetailDao;
	@Autowired
	IBuyeraddrDao buyeraddrDao;
	@Autowired
	IMemberuserDao memberuserDao;
	
	ITryapplyDao tryapplyDao;
	@Autowired
	public TryapplyService(ITryapplyDao tryapplyDao) {
		super(tryapplyDao);
		this.tryapplyDao = tryapplyDao;
	}
	
    /**
     *生成订单
     */
	public void addOrder(String chb_id){
		if(!ValidateUtil.isRequired(chb_id)){
			String[] ids=chb_id.split(",");
			for(int i=0;i<ids.length;i++){
				Tryapply tryapply=tryapplyDao.get(ids[i]);
				if(tryapply.getTry_type().equals("1")){
				 //获取试用商品 
				Trygoods trygoods=trygoodsDao.get(tryapply.getTry_id()); 
				//判断活动是否结束
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				String date=sdf.format(new Date());
				if(trygoods.getEndtime().compareTo(date)>0){
				//获取商品信息 
				Goods goods=goodsDao.get(trygoods.getGoods_id());	
				//获取会员信息
				Memberuser memberuser=memberuserDao.get(tryapply.getUser_id());
				// 按照日期生成随机数作为订单号
				String	order_id = RandomStrUtil.getDateRand()+RandomStrUtil.getNumberRand();
				//生成订单
				Goodsorder goodsorder = new Goodsorder();
				goodsorder.setBuy_cust_id(memberuser.getCust_id());
				goodsorder.setSell_cust_id(goods.getCust_id());
				goodsorder.setGoods_amount(0.0);			
				goodsorder.setTatal_amount(0.0);
				goodsorder.setSend_mode("0");
				goodsorder.setShip_free(0.0);
				goodsorder.setOrder_state("2");
				goodsorder.setOrder_id(order_id);
				goodsorder.setComm_free(0.0);
				goodsorder.setOrder_type("7");//订单类型
				goodsorder.setIs_virtual("1");//实物商品
				
				Buyeraddr buyeraddr=buyeraddrDao.get(tryapply.getAdd_id());
				//收货地址相关
				goodsorder.setConsignee(buyeraddr.getConsignee());
				goodsorder.setArea_attr(buyeraddr.getArea_attr());
				goodsorder.setBuy_address(buyeraddr.getAddress());
				goodsorder.setZip_code(buyeraddr.getPost_code());
				goodsorder.setTelephone(buyeraddr.getPhone());
				goodsorder.setMobile(buyeraddr.getCell_phone());
				this.goodsorderDao.insert(goodsorder);
				
				//生成订单详情
				Orderdetail orderdetail = new Orderdetail();
				orderdetail.setOrder_id(order_id);
				orderdetail.setGoods_price(0.0);
				orderdetail.setGoods_id(goods.getGoods_id());
				orderdetail.setOrder_num("1");
				orderdetail.setTemporary_goodsimg(goods.getList_img());
				orderdetail.setTemporary_goodsname(goods.getGoods_name());
				orderdetail.setOrderdetail_state("0");
				orderdetail.setRemark(tryapply.getTry_id());
				this.orderdetailDao.insert(orderdetail);
				
				//修改试用信息
				tryapply.setOrder_id(order_id);
				tryapplyDao.update(tryapply);
				}
			  }
			}
		}
		
	}
	
	/**
	 *  删除订单
	 */
	public void delOrder(String chb_id){
	  if(!ValidateUtil.isRequired(chb_id)){
		  String[] ids=chb_id.split(",");
		  for(int i=0;i<ids.length;i++){
				Tryapply tryapply=tryapplyDao.get(ids[i]);
				if(tryapply.getTry_type().equals("1")){
				 String order_id=tryapply.getOrder_id();
				 if(!ValidateUtil.isRequired(order_id)){
				 this.goodsorderDao.delete(order_id);
				 Map map=new HashMap();
				 map.put("order_id", order_id);
				 List orderList=this.orderdetailDao.getList(map);
				 if(orderList!=null&&orderList.size()>0){
					 for(int j=0;j<orderList.size();j++){
						 Map detailMap=(HashMap)orderList.get(j);
						 this.orderdetailDao.delete(detailMap.get("trade_id").toString());
					 }
				 }
				 }
		       }
		}
	  }
	} 
}

