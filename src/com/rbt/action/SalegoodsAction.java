/*
 
 * Package:com.rbt.action
 * FileName: SalegoodsAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Salegoods;
import com.rbt.service.ICouponService;
import com.rbt.service.IFreegoodsService;
import com.rbt.service.IGenericService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IRedpacketService;
import com.rbt.service.ISalegoodsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品促销信息action类
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 09:31:09 CST 2015
 */
@SuppressWarnings("unchecked")
@Controller
public class SalegoodsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 商品促销信息对象
	 */
	private Salegoods salegoods;
	/*******************业务层接口****************/
	/*
	 * 商品促销信息业务层接口
	 */
	@Autowired
	private ISalegoodsService salegoodsService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	@Autowired
	private IFreegoodsService freegoodsService;
	
	/*********************集合*******************/
	/*
	 * 商品促销信息信息集合
	 */
	public List salegoodsList;
	public List goodCouponList;
	public List orderCouponList;
	public List ralateList;
	public List couponList;
	public List redpacketList;
	public List goodsList;
	public List freegoodsList;
	
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
	 * 方法描述：返回新增商品促销信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//回选分类，优惠券，红包
		backSalegoods();
		//获取参数
		commpara();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增商品促销信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		commonCheck();
		super.commonValidateField(salegoods);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.salegoodsService.insert(salegoods);
		this.addActionMessage("新增商品促销信息成功！");
		this.salegoods = null;
		//获取参数
		commpara();
		return INPUT;
	}

	
	/**
	 * @author : XBY
	 * @date : Jul 17, 2014 4:06:29 PM
	 * @Method Description :新增和更新的校验
	 */
	public void commonCheck() throws Exception {
		if(!ValidateUtil.isRequired(salegoods.getMember_level())) {
			//去除空格
			String member_level = salegoods.getMember_level();
			salegoods.setMember_level(member_level.trim());
		}
	
		if(!ValidateUtil.isRequired(salegoods.getTerm_state()) &&salegoods.getTerm_state().equals("2")) {
	        if(ValidateUtil.isRequired(cat_attr) || cat_attr.equals("0")) {
				this.addFieldError("salegoods.term_state", "请选择分类");
			}else {
				
				cat_attr = cat_attr.replaceAll(" ", "").replace(",0", "");
				salegoods.setTerm(cat_attr.trim());
			}			
		}
		
	}
	
	/**
	 * 方法描述：修改商品促销信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		commonCheck();
		super.commonValidateField(salegoods);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.salegoodsService.update(salegoods);
		this.addActionMessage("修改商品促销信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除商品促销信息信息
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
		boolean flag = this.salegoodsService.updateBatchState(chb_id, state_val, "sale_id", state);
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
	 * 获取会员级别
	 */
	public void commpara() {
		Map map = new HashMap();
		map.put("mem_type", "1");
		commparaList = this.malllevelsetService.getList(map);
	}
	
	
	/**
	 * 方法描述：公用删除商品促销信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.salegoodsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除商品促销信息信息成功!");
		}else{
			this.addActionMessage("删除商品促销信息信息失败!");
		}
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
		int count=this.salegoodsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		salegoodsList = this.salegoodsService.getList(pageMap);
		
		salegoodsList = ToolsFuc.replaceList(salegoodsList, "");
		
		//获取会员级别
	    commpara();
		
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出商品促销信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.salegoods.getSale_id();
		if(id==null || id.equals("")){
			salegoods = new Salegoods();
		}else{
			salegoods = this.salegoodsService.get(id);
		}
		
		//回选分类，优惠券，红包
		backSalegoods();
		//获取参数
		commpara();
		return goUrl(VIEW);
	}
	
	
	/**
	 * 回选分类，优惠券，红包
	 */
	private void backSalegoods() {
		if(!ValidateUtil.isRequired(salegoods.getTerm_state())) {
			if( salegoods.getTerm_state().equals("1")) {
				//回选商品
				ralateList = commonBack(goodsService,salegoods.getTerm());
			}else if(salegoods.getTerm_state().equals("2")) {
				//商品分类
				viewCat(salegoods.getTerm());
			}else if(salegoods.getTerm_state().equals("4")) {
				//回选商品
				goodsList = commonBack(goodsService,salegoods.getTerm());
			}
		}
        
		if(!ValidateUtil.isRequired(salegoods.getCoupon_state())) {
			if(salegoods.getCoupon_state().equals("1")) {
				//回选优惠券
				couponList = commonBack(couponService,salegoods.getCoupon_plan());
			} else if(salegoods.getCoupon_state().equals("2")) {
				//回选红包
				redpacketList = commonBack(redpacketService,salegoods.getCoupon_plan());
			}else if(salegoods.getCoupon_state().equals("7")) {
				//回选赠品
				freegoodsList = commonBack(freegoodsService,salegoods.getCoupon_plan());
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
		if(sgis!=null&&!sgis.equals("")){
			Map sgisMap = new HashMap();
			sgisMap.put("sgis", sgis);
			list = t.getList(sgisMap);
			list = ToolsFuc.replaceList(list, "");
		}
		return list;
	}
	
	/**
	 * @return the SalegoodsList
	 */
	public List getSalegoodsList() {
		return salegoodsList;
	}
	/**
	 * @param salegoodsList
	 *  the SalegoodsList to set
	 */
	public void setSalegoodsList(List salegoodsList) {
		this.salegoodsList = salegoodsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(salegoods == null){
			salegoods = new Salegoods();
		}
		String id = this.salegoods.getSale_id();
		if(!this.validateFactory.isDigital(id)){
			salegoods = this.salegoodsService.get(id);
		}
	}
	/**
	 * @return the salegoods
	 */
	public Salegoods getSalegoods() {
		return salegoods;
	}
	/**
	 * @param Salegoods
	 *            the salegoods to set
	 */
	public void setSalegoods(Salegoods salegoods) {
		this.salegoods = salegoods;
	}
}

