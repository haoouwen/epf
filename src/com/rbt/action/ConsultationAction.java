/*
 
 * Package:com.rbt.action
 * FileName: ConsultationAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Consultation;
import com.rbt.model.Consulting;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.service.ICommparaService;
import com.rbt.service.IConsultationService;
import com.rbt.service.IConsultingService;
import com.rbt.service.IGoodsService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品咨询信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:47:46 CST 2014
 */
@Controller
public class ConsultationAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Consultation consultation;
	private Consulting consulting;
	private Goods goods;
	private Member member;

	/*******************业务层接口****************/
	@Autowired
	private IConsultationService consultationService;
	@Autowired
	private IConsultingService consultingService;
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private IGoodsService goodsService;

	/*********************集合******************/
	public List consultationList;//记录商品咨询信息信息集合
	public List consultingList;//记录商品咨询l回复信息信息集合
	public List commparaList;//系统参数

	/*********************字段******************/
	private static String shop_cust_id;//存放商品对应的店铺ID号
	private static String shop_cust_id_;//存放商品对应的店铺会员ID号
	public String goods_name_s;//商品名称
	public String c_type_s;//咨询类型
	public String start_time_s;//留言时间段开始
	public String end_time_s;//留言时间段结束
	public String is_display_s;//是否有效
	public String rstart_time_s;//回复时间段开始
	public String rend_time_s;//回复时间段结束
	public String jinId;//今日咨询
	public String daiId;//待处理咨询
	public String yiId;//待处理咨询
	public String goods_id_s;//分组列表页标记商品编号，用于详细列表页
	public String sell_cust_id;
	
	/**
	 * 方法描述：返回新增记录商品咨询信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取商品咨询类型
		getCommparea();
		return goUrl(ADD);
	}
	

	/**
	 * 方法描述：新增记录商品咨询信息
	 * 
	 */
    public void getCommparea(){
    	Map map=new HashMap();
		map.put("para_code", "c_type");
		commparaList = commparaService.getList(map);
    }
	/**
	 * 方法描述：新增记录商品咨询信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(consultation);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.consultationService.insert(consultation);
		this.addActionMessage("新增记录商品咨询信息成功！");
		this.consultation = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品咨询信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			
		if(ValidateUtil.isRequired(this.consulting.getRe_content())){
			this.addFieldError("consulting.re_content", "回复内容不能为空");
			return view();
		}
		if(consulting.getIs_display()!=null&&!consulting.getIs_display().equals("")){
			String is_display=consulting.getIs_display();
			consultation.setIs_display(is_display);
		}
	    consulting.setTrade_id(consultation.getTrade_id());
		consulting.setUser_id(this.session_user_id);
		this.consultationService.update(consultation);
		if(consulting.getRe_id()!=null&&!consulting.getRe_id().equals("")){
			this.consultingService.update(consulting);
			
		}else{
			consulting.setIs_display("0");
			consulting.setRe_id(null);
			this.consultingService.insert(consulting);
		}
	    
		this.addActionMessage("回复商品咨询信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商品咨询信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if(!ValidateUtil.isRequired(consultation.getTrade_id())){
			chb_id=consultation.getTrade_id();
		}
		boolean flag = this.consultationService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录商品咨询信息成功");
		}else{
			this.addActionMessage("删除记录商品咨询信息失败");
		}
		return auditList();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap(); 
		if(Constants.MEMBER_TYPE.equals(this.session_cust_type)){
			// 卖家咨询
			pageMap.put("cust_id", this.session_cust_id);
		}
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * @author:HXM
	 * @date:May 30, 20149:49:50 AM
	 * @param:
	 * @Description:通过对用户cust_id进行分组查询得到信息
	 */
	public String groupList() throws Exception {
		Map pageMap = new HashMap();
		if (!ValidateUtil.isRequired(goods_name_s)) {
			pageMap.put("goods_name", goods_name_s.trim());
		}
		// 根据页面提交的条件找出信息总数
		int count = this.consultationService.getGroupCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		consultationList = this.consultationService.getListByGroup(pageMap);
		return goUrl("groupindex");
	}
	
	public String audit() throws Exception {	
		//获取商品咨询类型
		getCommparea();
		String id = this.consultation.getTrade_id();
		if(id==null || id.equals("")){
			consultation = new Consultation();
			consulting = new Consulting();
			return auditList();
		}else{
			consultation = this.consultationService.get(id);
			consulting= this.consultingService.getByTradeId(id);
			goods=goodsService.get(consultation.getGoods_id());
			if(goods==null){
				goods = new Goods();
			}
			shop_cust_id=goods.getCust_id();
			if(consulting==null){
				consulting = new Consulting();
			}
		}
	     return goUrl(AUDIT);
	}
	
	public String auditList() throws Exception {
		Map pageMap = new HashMap(); 
		// 买家咨询
        pageMap.put("buy_cust_id", this.session_cust_id);
		commonList(pageMap);
		 return goUrl(AUDITLIST);
	 }
	 
	 public String auditState() throws Exception {
		 return auditList();
	 }
	 /**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:40:49 PM
	 * @Method Description：核心查询
	 */
	 public void commonList(Map pageMap){
		//咨询内容
			getCommparea();
			if (goods_name_s != null && !goods_name_s.equals("")) {
				pageMap.put("goods_name", goods_name_s);
			}
			if (c_type_s != null && !c_type_s.equals("")) {
				pageMap.put("c_type", c_type_s);
			}
			if (start_time_s != null && !start_time_s.equals("")) {
				pageMap.put("start_time", start_time_s);
			}
			if (end_time_s != null && !end_time_s.equals("")) {
				pageMap.put("end_time", end_time_s);
			}
			if (rstart_time_s != null && !rstart_time_s.equals("")) {
				pageMap.put("rstart_time", rstart_time_s);
			}
			if (rend_time_s != null && !rend_time_s.equals("")) {
				pageMap.put("rend_time", rend_time_s);
			}
			if (is_display_s != null && !is_display_s.equals("")) {
				pageMap.put("is_display", is_display_s);
			}
			if (!ValidateUtil.isRequired(goods_id_s)) {
				pageMap.put("goods_id", goods_id_s);
			}
			//搜索今日咨询
			if (jinId != null && !jinId.equals("")) {
				pageMap.put("today", "0");
			}
			//搜索待处理咨询
			if (daiId != null && !daiId.equals("")) {
				if("0".equals(daiId)){
					pageMap.put("re_date","0");
				}else if("1".equals(daiId)){
					pageMap.put("reg","0");
				}
				
			}
			if (!ValidateUtil.isRequired(sell_cust_id)) {
				pageMap.put("cust_id", sell_cust_id);
			}
			//商城地区过滤
			
			//根据页面提交的条件找出信息总数
			int count=this.consultationService.getCount(pageMap);
			
			//分页插件
			pageMap = super.pageTool(count,pageMap);
			consultationList = this.consultationService.getList(pageMap);
			//List替换字段处理
			consultationList = this.consultationService.replaceList(consultationList, member);
	 }
	/**
	 * 方法描述：根据主键找出记录商品咨询信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//获取商品咨询类型
		getCommparea();
		String id = this.consultation.getTrade_id();
		if(id==null || id.equals("")){
			consultation = new Consultation();
			consulting = new Consulting();
			return list();
		}else{
			consultation = this.consultationService.get(id);
			if(this.consultingService.getByTradeId(id)!=null){
				consulting= this.consultingService.getByTradeId(id);
				if(consulting.getIs_display()==null &&consulting.getIs_display().equals("")){
					consulting.setIs_display("0");
				}
			}
			goods=goodsService.get(consultation.getGoods_id());
			if(goods==null){
				goods = new Goods();
			}
			shop_cust_id=goods.getCust_id();
			if(consulting==null){
				consulting = new Consulting();
			}
		}
			
		return goUrl(VIEW);
	}
	
	
	/**
	 * @return the consultation
	 */
	public Consultation getConsultation() {
		return consultation;
	}
	/**
	 * @param Consultation
	 *            the consultation to set
	 */
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	/**
	 * @return the ConsultationList
	 */
	public List getConsultationList() {
		return consultationList;
	}
	/**
	 * @param consultationList
	 *  the ConsultationList to set
	 */
	public void setConsultationList(List consultationList) {
		this.consultationList = consultationList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(consultation == null){
			consultation = new Consultation();
		}
		String id = this.consultation.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			consultation = this.consultationService.get(id);
		}
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public static String getShop_cust_id() {
		return shop_cust_id;
	}
	public static void setShop_cust_id(String shop_cust_id) {
		ConsultationAction.shop_cust_id = shop_cust_id;
	}
	public List getCommparaList() {
		return commparaList;
	}
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}
	public String getGoods_name_s() {
		return goods_name_s;
	}
	public void setGoods_name_s(String goods_name_s) {
		this.goods_name_s = goods_name_s;
	}
	public String getC_type_s() {
		return c_type_s;
	}
	public void setC_type_s(String c_type_s) {
		this.c_type_s = c_type_s;
	}
	public String getStart_time_s() {
		return start_time_s;
	}
	public void setStart_time_s(String start_time_s) {
		this.start_time_s = start_time_s;
	}
	public String getEnd_time_s() {
		return end_time_s;
	}
	public void setEnd_time_s(String end_time_s) {
		this.end_time_s = end_time_s;
	}
	public String getIs_display_s() {
		return is_display_s;
	}
	public void setIs_display_s(String is_display_s) {
		this.is_display_s = is_display_s;
	}
	public String getRstart_time_s() {
		return rstart_time_s;
	}
	public void setRstart_time_s(String rstart_time_s) {
		this.rstart_time_s = rstart_time_s;
	}
	public String getRend_time_s() {
		return rend_time_s;
	}
	public void setRend_time_s(String rend_time_s) {
		this.rend_time_s = rend_time_s;
	}
	public String getJinId() {
		return jinId;
	}
	public void setJinId(String jinId) {
		this.jinId = jinId;
	}
	public String getDaiId() {
		return daiId;
	}
	public void setDaiId(String daiId) {
		this.daiId = daiId;
	}
	public String getYiId() {
		return yiId;
	}
	public void setYiId(String yiId) {
		this.yiId = yiId;
	}
	public Consulting getConsulting() {
		return consulting;
	}
	public void setConsulting(Consulting consulting) {
		this.consulting = consulting;
	}
	public List getConsultingList() {
		return consultingList;
	}
	public void setConsultingList(List consultingList) {
		this.consultingList = consultingList;
	}
	public static String getShop_cust_id_() {
		return shop_cust_id_;
	}
	public static void setShop_cust_id_(String shop_cust_id_) {
		ConsultationAction.shop_cust_id_ = shop_cust_id_;
	}

}

