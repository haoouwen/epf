/*
 
 * Package:com.rbt.action
 * FileName: SaleorderAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Saleorder;
import com.rbt.service.ICouponService;
import com.rbt.service.IFreegoodsService;
import com.rbt.service.IGenericService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IRedpacketService;
import com.rbt.service.ISaleorderService;
import com.rbt.service.impl.GoodsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 订单促销action类
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 11:01:23 CST 2015
 */
@Controller
public class SaleorderAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 订单促销对象
	 */
	private Saleorder saleorder;
	/*******************业务层接口****************/
	/*
	 * 订单促销业务层接口
	 */
	@Autowired
	private ISaleorderService saleorderService;
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	@Autowired
	private IFreegoodsService freegoodsService;
	@Autowired
	private IGoodsService goodsService;
	
	/*********************集合*******************/
	/*
	 * 订单促销信息集合
	 */
	public List saleorderList;
	public List goodCouponList;
	public List orderCouponList;
	public List couponList;
	public List redpacketList;
	public List freegoodsList;
	public List ralateList;
	
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String sale_name_s;//名称
	public String is_recome_s;//号码
	public String info_state_s;//状态
	public String start_time_s;//开始时间
	public String end_time_s;//结束时间
	public String member_level_s;//会员级别
	public String platform_s;//生效平台
		
	/**
	 * 方法描述：返回新增订单促销页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//回选优惠券，红包
		backSalegoods();
		//获取参数
		commpara();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增订单促销
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(saleorder);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.saleorderService.insert(saleorder);
		this.addActionMessage("新增订单促销成功！");
		this.saleorder = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改订单促销信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(saleorder);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.saleorderService.update(saleorder);
		this.addActionMessage("修改订单促销成功！");
		return list();
	}
	/**
	 * 方法描述：删除订单促销信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
   /**
    * 禁用，与启用
    * @return
    * @throws Exception
    */ 
	public String updateStateB2C() throws Exception {
		commonUpdate("禁用成功","启用成功","info_state");
		return list();	
	}
	
   /**
    * 排它，与取消排它
    * @return
    * @throws Exception
    */ 
	public String updateIsrecome() throws Exception {
		commonUpdate("取消排它成功","排它成功","is_recome");
		return list();	
	}

   
   /**
    * 公共修改状态
    * @return
    * @throws Exception
    */
   public void commonUpdate(String str, String tips, String state) throws Exception {
		boolean flag = this.saleorderService.updateBatchState(chb_id, state_val, "sale_id", state);
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage(str);
			} else if (state_val.equals("1")) {
				this.addActionMessage(tips);
			}
		}else{
			this.addActionMessage("操作失败");
		}
   }	
	
	
	/**
	 * 方法描述：公用删除订单促销信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.saleorderService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除订单促销信息成功!");
		}else{
			this.addActionMessage("删除订单促销信息失败!");
		}
	}
	
	/**
	 * 获取会员级别
	 */
	public void commpara() {
		Map map = new HashMap();
		map.put("mem_type", "1");
		commparaList = this.malllevelsetService.getList(map);
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//名称
		if(!ValidateUtil.isRequired(sale_name_s)) {
			pageMap.put("sale_name", sale_name_s);
		}
		//排它
		if(!ValidateUtil.isRequired(is_recome_s)) {
			pageMap.put("is_recome", is_recome_s);
		}
        //状态
		if(!ValidateUtil.isRequired(info_state_s)) {
			pageMap.put("info_state", info_state_s);
		}
		//开始时间
		if(!ValidateUtil.isRequired(start_time_s)) {
			pageMap.put("start_time", start_time_s);
		}
		//结束时间
		if(!ValidateUtil.isRequired(end_time_s)) {
			pageMap.put("end_time", end_time_s);
		}
		//会员级别
		if(!ValidateUtil.isRequired(member_level_s)) {
			pageMap.put("member_level", member_level_s);
		}	
		//生效平台
		if(!ValidateUtil.isRequired(platform_s)) {
			pageMap.put("platform", platform_s);
		}	
		//默认排序
		pageMap.put("default", "1");
		//根据页面提交的条件找出信息总数
		int count=this.saleorderService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		saleorderList = this.saleorderService.getList(pageMap);
		
		saleorderList = ToolsFuc.replaceList(saleorderList, "");
		//获取会员级别
	    commpara();
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出订单促销信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.saleorder.getSale_id();
		if(id==null || id.equals("")){
			saleorder = new Saleorder();
		}else{
			saleorder = this.saleorderService.get(id);
		}
		
		//回选优惠券，红包
		backSalegoods();
		//获取参数
		commpara();
		return goUrl(VIEW);
	}
	
	
	/**
	 * 回选优惠券，红包
	 */
	private void backSalegoods() {
        
		if(!ValidateUtil.isRequired(saleorder.getCoupon_state())) {
			
			if(saleorder.getTerm_state().equals("3")) {
				//回选商品
				ralateList = commonBack(goodsService, saleorder.getTerm());
				ralateList = ToolsFuc.replaceList(ralateList, "");
			}
			
			if(saleorder.getCoupon_state().equals("1")) {
				//回选优惠券
				couponList = commonBack(couponService, saleorder.getCoupon_plan());
			} else if(saleorder.getCoupon_state().equals("2")) {
				//回选红包
				redpacketList = commonBack(redpacketService, saleorder.getCoupon_plan());
			}else if(saleorder.getCoupon_state().equals("4")) {
				//回选赠品
				freegoodsList = commonBack(freegoodsService, saleorder.getCoupon_plan());
				freegoodsList = ToolsFuc.replaceList(freegoodsList, "");
			}
		}
	}
	
	
	/**
	 * @author : XBY
	 * @date : Apr 17, 2014 7:12:25 PM
	 * @Method Description :查询商品，优惠，红包
	 */
	private List commonBack(IGenericService t, String sgis){
		// 获取商品的相关商品串
		List list = new ArrayList();
		if(saleorder!=null && !saleorder.equals("")){
			if(sgis!=null&&!sgis.equals("")){
				Map sgisMap = new HashMap();
				sgisMap.put("sgis", sgis);
				list = t.getList(sgisMap);
			}
		}
		return list;
	}	
	
	/**
	 * @return the SaleorderList
	 */
	public List getSaleorderList() {
		return saleorderList;
	}
	/**
	 * @param saleorderList
	 *  the SaleorderList to set
	 */
	public void setSaleorderList(List saleorderList) {
		this.saleorderList = saleorderList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(saleorder == null){
			saleorder = new Saleorder();
		}
		String id = this.saleorder.getSale_id();
		if(!this.validateFactory.isDigital(id)){
			saleorder = this.saleorderService.get(id);
		}
	}
	/**
	 * @return the saleorder
	 */
	public Saleorder getSaleorder() {
		return saleorder;
	}
	/**
	 * @param Saleorder
	 *            the saleorder to set
	 */
	public void setSaleorder(Saleorder saleorder) {
		this.saleorder = saleorder;
	}
}

