/*
 
 * Package:com.rbt.action
 * FileName: ComboAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Combo;
import com.rbt.service.IComboService;
import com.rbt.service.IGoodsService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 套餐表action类
 * @author 创建人 LHY
 * @date 创建日期 Mon Mar 25 15:09:17 CST 2014
 */
@Controller
public class ComboAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public CategoryFuc  categoryFuc;
	private Combo combo;

	/*******************业务层接口****************/
	@Autowired
	private IComboService comboService;
	@Autowired
	private IGoodsService goodsService;

	/*********************集合******************/
	public List comboList;//组合
	public List ralateList;//相关商品

	/*********************字段******************/
	public String combo_name;//组合名称
	public String reason;//理由
	public String cat_attr_s;//分类搜索
	public String cat_String;//转化为具体的分类
	public String combo_sortno_id;//批量排序
	public String isort_no;//排序
	public String hidden_goodsname;//商品名称
	public String info_state_s;//审核状态
	public String cust_name_s;//会员名称
	public String is_update;//是否更新
	public String cheked="1";//选择商品可以单个

	/**
	 * 方法描述：返回新增套餐表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		backRalateGoods();
		return goUrl(ADD);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 4:50:52 PM
	 * @Method Description :数据验证
	 */
	private String commonCheck(){
		//设置list_img
		String list_img;
		String img_paths=combo.getCombo_img();
		if(!ValidateUtil.isRequired(img_paths)){
			list_img=judgeList_img(img_paths);
			if(!ValidateUtil.isRequired(list_img)){
				combo.setList_img(list_img);
			}
		}
		// 保存分类cat_attr
		selectCat();
		this.combo.setCat_attr(cat_attr);
		if(ValidateUtil.isRequired(combo.getCombo_name())){
			this.addFieldError("combo.combo_name","请填写套餐名称！");
		}

		if(ValidateUtil.isRmb(combo.getCombo_price())){
			this.addFieldError("combo.combo_price", "请正确填写套餐价格！");
		}
		
		if(ValidateUtil.isRequired(combo.getCombo_center())){
			this.addFieldError("combo.combo_center","套餐商品描述不能为空！");
		}
		if(ValidateUtil.isRequired(combo.getSort_no())){
			combo.setSort_no("0");
		}
		
		String info_state="1";
		//判断是否新增页面
	    info_state=combo.getInfo_state();
		if(is_update!=null){
				//套餐商品数据操作流
				if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
					if (info_state.equals("1")) {
						reason = "审核通过状态";
					}else if(info_state.equals("3")) {
						if(reason.equals("")){
							this.addFieldError("combo.info_state", "您还未填写禁用理由！");
						}
					}else{
						this.addFieldError("combo.info_state", "请先选择审核状态");
					}
				}
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//判断运营商是否让会员商品是否审核
			if(cfg_combo_audit.equals("1")){
				info_state="1";// 审核通过
			}else{
				info_state="0";// 未审核
			}
		}
		this.combo.setInfo_state(info_state);
		return info_state;
	}

	/**
	 * 方法描述：新增套餐表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.combo.setCust_id(this.session_cust_id);
		String info_state = commonCheck();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			combo.setSort_no("0");
		}
		super.commonValidateField(combo);
		if(super.ifvalidatepass){
			return add();
		}
		String id = this.comboService.insertGetPk(combo);
		addAuditRecord(id,"combo",info_state,"新增套餐商品");
		this.addActionMessage("新增套餐商品成功！");
		this.combo = null;
		return INPUT;
	}
	

	/**
	 * 方法描述：修改套餐表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = this.combo.getTrade_id();
		String info_state = commonCheck();
		this.combo.setCat_attr(cat_attr);
		super.commonValidateField(combo);
		if(super.ifvalidatepass){
			return view();
		}
		this.comboService.update(combo);
		//套餐商品数据操作流
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			addAuditRecord(id,"combo",info_state,"修改套餐商品");
		}else{
			if (info_state.equals("1")) {
				reason = "审核通过状态";
			}
			addAuditRecord(id,"combo",info_state,reason);
		}
		this.addActionMessage("修改套餐商品成功！");
		return list();
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 5:09:32 PM
	 * @Method Description :公共删除
	 */
	private void commonDelete(){
		boolean flag = this.comboService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除套餐商品成功");
		}else{
			this.addActionMessage("删除套餐商品失败");
		}
	}
	
	/**
	 * 方法描述：删除套餐表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @throws Exception 
	 * @date : Apr 17, 2014 5:10:36 PM
	 * @Method Description :审核页面删除
	 */
	public String auditDelete() throws Exception{
		commonDelete();
		return auditList();
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 5:01:01 PM
	 * @Method Description :公共查询
	 */
	private void commonList(Map pageMap){
		// 搜索状态
		if (info_state_s != null && !info_state_s.equals("")){
			pageMap.put("info_state_s", info_state_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")){
			pageMap.put("cust_name",cust_name_s);
		}
		//卖家商品名称搜索
		if(combo_name!=null &&!combo_name.equals("")){
			pageMap.put("combo_name",combo_name);
		}
		//根据页面提交的条件找出信息总数
		int count=this.comboService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		comboList = this.comboService.getList(pageMap);
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state","1,3");
		}else{
			pageMap.put("cust_id",this.session_cust_id);
		}
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 审核列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("info_state","0,2");
		commonList(pageMap);
		return goUrl("auditindex");
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 7:12:25 PM
	 * @Method Description :回选相关商品
	 */
	private void backRalateGoods(){
		// 获取商品的相关商品串
		if(combo!=null && !combo.equals("")){
			String sgis = combo.getGoods_str();
			if(sgis!=null&&!sgis.equals("")){
				Map sgisMap = new HashMap();
				sgisMap.put("sgis", sgis);
				ralateList = this.goodsService.getList(sgisMap);
			}
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 5:12:40 PM
	 * @Method Description :查看详情
	 */
	private boolean commonView(String id){
		if(id==null || id.equals("")){
			return true;
		}else{
			combo = this.comboService.get(id);
			if(combo!=null && combo.getGoods_str()!=null){
				backRalateGoods();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 审核页面
	 * @return
	 * @throws Exception 
	 */
	public String audit() throws Exception{
		String id = this.combo.getTrade_id();
		if(commonView(id)){
			return auditList();
		}
		//得到所属分类字符串
		String cat_id = combo.getCat_attr();
		//转化为具体的分类
		cat_String = categoryFuc.getCateNameByMap(cat_id);
		searchAuditList(id,"combo");
		return goUrl("audit");
	}
	
	
	/**
	 * 审核状态
	 * @return
	 * @throws Exception 
	 */
	public String auditState() throws Exception{
		String info_state = this.combo.getInfo_state() ;
		String id = this.combo.getTrade_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if (info_state.equals("1")) {
			reason = "审核通过";
		}else if (info_state.equals("2")) {
			if(reason.equals("")){
				this.addFieldError("combo.info_state", "您还未填写审核不通过理由！");
			}
		}else{
			this.addFieldError("combo.info_state", "审核状态不能为空！");
		}
		if(super.ifvalidatepass){
			return audit();
		}
		// 获取数据库对象
		Combo cb = this.comboService.get(id);
		if (info_state!= null&& !info_state.equals("")) {
			// 设置状态值
			cb.setInfo_state(info_state);
		}
		// 更新积分商品
		this.comboService.update(cb);
		addAuditRecord(id,"combo", info_state,reason);
		this.comboService.update(combo);
		this.addActionMessage("审核成功！");
		return auditList();
	}
	
	
	/**
	 * 方法描述：根据主键找出套餐表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.combo.getTrade_id();
		if(commonView(id)){
			return list();
		}
		viewCat(combo.getCat_attr());
		//获取审核列表
		searchAuditList(id,"combo");
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */

	public String updateSort() throws Exception {
		commonSort();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 12:57:37 PM
	 * @Method Description :排序
	 */
	private void commonSort(){
		boolean flag = this.comboService.updateSort("trade_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("套餐商品排序成功");
		}else{
			this.addActionMessage("套餐商品排序失败");
		}
	}
	
	/**
	 * @author : CYC
	 * @throws IOException 
	 * @date : Apr 19, 2014 12:57:37 PM
	 * @Method Description :判断组合商品已经添加过了
	 */
	public void getregoods() throws IOException{
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String ralate=request.getParameter("ralate");
		ralate= ralate.substring(0, ralate.length()-1);
		Map map = new HashMap();
		map.put("regoods_str", ralate);
		comboList = comboService.getList(map);
		if(comboList!=null && comboList.size()>0){
			out.write("1");
		}else{
			out.write("0");
		}
	}
	
	
	
	
	/**
	 * 方法描述:审核批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditUpdateSort() throws Exception {
		commonSort();
		return auditList();
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(combo == null){
			combo = new Combo();
		}
		String id = this.combo.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			combo = this.comboService.get(id);
		}
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

}

