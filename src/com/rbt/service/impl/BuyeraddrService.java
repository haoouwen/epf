/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: BuyeraddrService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.IBuyeraddrDao;
import com.rbt.function.AreaFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.service.IBuyeraddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 收货地址管理Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Dec 27 16:33:56 CST 2014
 */
@Service
public class BuyeraddrService extends GenericService<Buyeraddr,String> implements IBuyeraddrService {
	
	IBuyeraddrDao buyeraddrDao;

	@Autowired
	public BuyeraddrService(IBuyeraddrDao buyeraddrDao) {
		super(buyeraddrDao);
		this.buyeraddrDao = buyeraddrDao;
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map){
		return this.buyeraddrDao.getListByGroup(map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map){
		return this.buyeraddrDao.getGroupCount(map);
	}
	
    /**
	 * @Method Description :获取商家退货地址
	 * @author: HXK
	 * @date : Oct 31, 2015 10:13:41 AM
	 * @param 
	 * @return return_type
	 */
	public Buyeraddr getbuyerByCust_id(Buyeraddr buyeraddr ,String sell_cust_id){
		Map buyerMap = new HashMap();
		buyerMap.put("cust_id", sell_cust_id);
		List bList=new ArrayList();
		bList=buyeraddrDao.getList(buyerMap);
		buyeraddr=new Buyeraddr();
		if(bList!=null&&bList.size()>0){
			HashMap bmap=(HashMap)bList.get(0);
			String b_address="",b_consignee="",b_area_attr="",b_cell_phone="",b_phone="";
			if(bmap!=null&&bmap.get("address")!=null){
				b_address=bmap.get("address").toString();
			}
			if(bmap!=null&&bmap.get("consignee")!=null){
				b_consignee=bmap.get("consignee").toString();
			}
			if(bmap!=null&&bmap.get("area_attr")!=null){
				b_area_attr=bmap.get("area_attr").toString();
				b_area_attr=AreaFuc.getAreaNameByMap(b_area_attr);
			}
			if(bmap!=null&&bmap.get("cell_phone")!=null){
				b_cell_phone=bmap.get("cell_phone").toString();
			}
			if(bmap!=null&&bmap.get("phone")!=null){
				b_phone=bmap.get("phone").toString();
			}
			buyeraddr.setAddress(b_address);
			buyeraddr.setPhone(b_phone);
			buyeraddr.setConsignee(b_consignee);
			buyeraddr.setCell_phone(b_cell_phone);
			buyeraddr.setArea_attr(b_area_attr);
		}
		return buyeraddr;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

