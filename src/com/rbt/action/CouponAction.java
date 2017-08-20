/*
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CouponAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Coupon;
import com.rbt.model.Excoupons;
import com.rbt.service.ICouponService;
import com.rbt.service.IExcouponsService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IMalllevelsetService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 优惠券action类
 * @author 创建人 XBY
 * @date 创建日期 Fri Aug 07 14:37:49 CST 2015
 */
@SuppressWarnings("unchecked")
@Controller
public class CouponAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 优惠券对象
	 */
	private Coupon coupon;
	/*******************业务层接口****************/
	/*
	 * 优惠券业务层接口
	 */
	@Autowired
	private ICouponService couponService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	/*
	 * 优惠券兑换表对象
	 */
	private Excoupons excoupons;
	/*******************业务层接口****************/
	/*
	 * 优惠券兑换表业务层接口
	 */
	@Autowired
	private IExcouponsService excouponsService;
	
	/*
	 * 优惠券兑换表信息集合
	 */
	public List excouponsList;
	/*********************集合*******************/
	/*
	 * 优惠券信息集合
	 */
	public List couponList;
	public List ralateList;
	
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String coupon_name_s;//名称
	public String coupon_no_s;//号码
	public String info_state_s;//状态
	public String coupon_type_s;//类型
	public String start_time_s;//开始时间
	public String end_time_s;//结束时间
	public String member_level_s;//会员级别
	public String s_goods_name;//搜索名称
	public String sgis;//搜索ID
	public String down_num_s;//下载数量
	public String coupon_id_s;//下载优惠券ID
	public String coupon_id;
	public String dowm_coupon_id_s;//下载ID
	public String couponkey;
	/**
	 * 方法描述：返回新增优惠券页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取参数
		commpara();
		//回选商品
		backGoods();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增优惠券
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		commonCheck();
		super.commonValidateField(coupon);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.couponService.insert(coupon);
		this.addActionMessage("新增优惠券成功！");
		this.coupon = null;
		//获取参数
		commpara();	
		return INPUT;
	}

	/**
	 * 方法描述：修改优惠券信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		commonCheck();
		super.commonValidateField(coupon);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.couponService.update(coupon);
		this.addActionMessage("修改优惠券成功！");
		return list();
	}
	/**
	 * 方法描述：删除优惠券信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
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
	 * 方法描述：公用删除优惠券信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.couponService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除优惠券信息成功!");
		}else{
			this.addActionMessage("删除优惠券信息失败!");
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
		if(!ValidateUtil.isRequired(coupon_name_s)) {
			pageMap.put("coupon_name", coupon_name_s);
		}
		//号码
		if(!ValidateUtil.isRequired(coupon_no_s)) {
			pageMap.put("coupon_no", coupon_no_s);
		}
        //状态
		if(!ValidateUtil.isRequired(info_state_s)) {
			pageMap.put("info_state", info_state_s);
		}
		//类型
		if(!ValidateUtil.isRequired(coupon_type_s)) {
			pageMap.put("coupon_type", coupon_type_s);
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
		//根据页面提交的条件找出信息总数
		int count=this.couponService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		couponList = this.couponService.getList(pageMap);
		couponList = ToolsFuc.replaceList(couponList,"");
		//获取会员级别
	    commpara();
		return goUrl(INDEXLIST);
	}
	
	
	public void maxcoupon() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String red_id_max=request.getParameter("coupon_id");
		String down_max=request.getParameter("down_max");
		if(red_id_max!=null&&!"".equals(red_id_max)){
		    coupon=this.couponService.get(red_id_max);
		    if(coupon!=null&&coupon.getCoupon_num()!=null&&coupon.getCoupon_num().equals("不限制")){
		    	out.write("1");//可以下载
		    }else {
		    	if(down_max!=null&&!"".equals(down_max)){
		    		if(Integer.parseInt(down_max)>Integer.parseInt(coupon.getCoupon_num())){
						out.write("0");//不能下载
					}else {
						out.write("1");//能下载
					}
		    	}else {
		    		out.write("0");//不能下载
				}
			}
		}else{
			out.write("0");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String couponlist() throws Exception {
		Map pageMap = new HashMap();
		//根据页面提交的条件找出信息总数
		pageMap.put("coupon_id", coupon_id);
		//下载码搜索
		if(!ValidateUtil.isRequired(dowm_coupon_id_s)) {
			pageMap.put("coupon_no", dowm_coupon_id_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.excouponsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		excouponsList = this.excouponsService.getList(pageMap);
		return goUrl("couponindex");
	}
	
	
	/**
	 * 方法描述：根据主键找出优惠券信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.coupon.getCoupon_id();
		if(id==null || id.equals("")){
			coupon = new Coupon();
		}else{
			coupon = this.couponService.get(id);
		}
		//获取参数
		commpara();
		//回选商品
		backGoods();
		return goUrl(VIEW);
	}
	
	
	
   /**
    * 禁用，与启用优惠券
    * @return
    * @throws Exception
    */ 
	public String updateStateB2C() throws Exception {
		boolean flag = this.couponService.updateBatchState(chb_id, state_val, "coupon_id", "info_state");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("禁用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("启用成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	    return list();	
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 7:12:25 PM
	 * @Method Description :回选相关商品
	 */
	private void backGoods(){
		// 获取商品的相关商品串
		if(coupon!=null && !ValidateUtil.isRequired(coupon.getNeed_state())  && coupon.getNeed_state().equals("1")){
			String sgis = coupon.getTerm();
			if(sgis!=null&&!sgis.equals("")){
				Map sgisMap = new HashMap();
				sgisMap.put("sgis", sgis);
				ralateList = this.goodsService.getList(sgisMap);
				ralateList = ToolsFuc.replaceList(ralateList, "");
			}
		}else if(coupon!=null && !ValidateUtil.isRequired(coupon.getNeed_state())  && coupon.getNeed_state().equals("2")) {
			//商品分类
			viewCat(coupon.getTerm());
		}	
	}
	
	/**
	 * @author : XBY
	 * @date : Jul 17, 2014 4:06:29 PM
	 * @Method Description :新增和更新的校验
	 */
	public void commonCheck() throws Exception {
		if(!ValidateUtil.isRequired(coupon.getMember_level())) {
			//去除空格
			String member_level = coupon.getMember_level();
			coupon.setMember_level(member_level.trim());
		}
	
		if(!ValidateUtil.isRequired(coupon.getNeed_state()) &&coupon.getNeed_state().equals("2")) {
			if(cat_attr.contains(", 0")){
				this.addFieldError("coupon.need_state", "请选择分类到最后一级");
			}else if(ValidateUtil.isRequired(cat_attr) || cat_attr.equals("0")) {
				this.addFieldError("coupon.need_state", "请选择分类");
			}else {
				cat_attr = cat_attr.replace(" ", "");
				coupon.setTerm(cat_attr.trim());
			}			
		}
		
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索优惠券
	 */
	public void searchCoupon() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 商品id标识
		Map couponMap = new HashMap();
		// 搜索商品名称
		if (s_goods_name != null && !s_goods_name.equals("")) {
			s_goods_name = URLDecoder.decode(s_goods_name, "UTF-8");
			couponMap.put("coupon_name", s_goods_name);
		}
		if (sgis != null && !sgis.equals("")) {
			couponMap.put("sgis", sgis);
		}
		couponMap.put("time", "1");
		couponMap.put("info_state", "1");
		couponMap = ajaxMap(couponMap);
		int totalNum = this.couponService.getCount(couponMap);
		List list = this.couponService.getList(couponMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	
	
	/**
	 * 下载优惠券
	 * @return
	 * @throws Exception
	 */
	public String downCoupon() throws Exception {
		if(this.couponService.downCoupon(down_num_s, coupon_id_s)){
			this.addActionMessage("下载优惠券成功！");
		}else{
			this.addActionMessage("下载优惠券失败！");
		}
		return list();
	}
	
	/**
	 * @Method Description : 导出统计信息
	 * @param 
	 * @return return_type
	 */
	public String exportcouponInfo() throws Exception{
		Map pageMap = new HashMap();
		pageMap.put("couponkey", couponkey);
		excouponsList = this.excouponsService.getList(pageMap);
		excouponsList = ToolsFuc.replaceList(excouponsList, "");
		if(this.excouponsService.exprotcouponExcel(excouponsList, getResponse())) {
			   this.addActionMessage("数据导出品牌成功！");	
			}else {
			   this.addActionMessage("数据导出品牌成功！");
			}
	   
	    return couponlist();
	}
	
	
	
	/**
	 * @return the CouponList
	 */
	public List getCouponList() {
		return couponList;
	}
	/**
	 * @param couponList
	 *  the CouponList to set
	 */
	public void setCouponList(List couponList) {
		this.couponList = couponList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(coupon == null){
			coupon = new Coupon();
		}
		String id = this.coupon.getCoupon_id();
		if(!this.validateFactory.isDigital(id)){
			coupon = this.couponService.get(id);
		}
	}
	/**
	 * @return the coupon
	 */
	public Coupon getCoupon() {
		return coupon;
	}
	/**
	 * @param Coupon
	 *            the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}

