/*
 
 * Package:com.rbt.action
 * FileName: PointsgoodsAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Goods;
import com.rbt.model.Pointsgoods;
import com.rbt.service.IGoodsService;
import com.rbt.service.IPointsgoodsService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录积分商品信息action类
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 25 16:00:03 CST 2014
 */
@Controller
public class PointsgoodsAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	public CategoryFuc  categoryFuc;
	/*******************实体层********************/
	private Pointsgoods pointsgoods;
	
	/*******************业务层接口****************/
	@Autowired
	private IPointsgoodsService pointsgoodsService;
	@Autowired
	private IGoodsService goodsService;
	
	/*********************集合********************/
	public List pointsgoodsList;//积分商品
	
	/*********************字段********************/
	
	public String reason;//操作流理由
	public String info_state_s;//审核状态
	public String goods_name_s;//商品名称
	public String cust_name_s;//会员名称
	public Goods goods;//商品对象
	public String hidden_goodsname;//商品名称
	public String is_update;//是否更新
	public String cat_attr_s;//分类搜索
	public String cat_String;//转化为具体的分类
	public String pointsgoods_sortno_id;//批量排序
	public String isort_no;//排序
	
	
	/**
	 * 方法描述：返回新增记录积分商品信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * @author : CYC
	 * @date : Apr 17, 2014 10:59:10 AM
	 * @Method Description :验证数据
	 */
	private String commonCheck(){
		//设置list_img
		String list_img;
		String img_paths=pointsgoods.getInter_image();
		if(!ValidateUtil.isRequired(img_paths)){
			list_img=judgeList_img(img_paths);
			if(!ValidateUtil.isRequired(list_img)){
				pointsgoods.setList_img(list_img);
			}
		}
		// 保存分类cat_attr
		selectCat();
		this.pointsgoods.setCat_attr(cat_attr);
		if(validateFactory.isRequired((pointsgoods.getGoods_id()))){
			this.addFieldError("pointsgoods.goods_id","请添加商品！");
		}
		if(validateFactory.isDouble(pointsgoods.getBuy_points()) || Double.valueOf(pointsgoods.getBuy_points())<=0){
			this.addFieldError("pointsgoods.buy_points","购买积分不能为空且必须大于0！");
		}
		if(validateFactory.isDigital(pointsgoods.getStock()+"")||Integer.parseInt(pointsgoods.getStock().trim())<=0){
				this.addFieldError("pointsgoods.stock","库存不能为空且必须大于0");
		}
		if(validateFactory.isRequired(pointsgoods.getInter_image())){
			this.addFieldError("pointsgoods.inter_image","积分图片不能为空！");
		}
		if(validateFactory.isRequired(pointsgoods.getDescription())){
			this.addFieldError("pointsgoods.description","积分商品描述不能为空！");
		}
		//判断是否新增页面
		String info_state=pointsgoods.getInfo_state();
		if(is_update!=null){
			//积分商品数据操作流
			if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
				if (info_state.equals("1")) {
					reason = "审核通过状态";
				}else if(info_state.equals("3")) {
					if(reason.equals("")){
						this.addFieldError("pointsgoods.info_state", "您还未填写禁用理由！");
					}
				}else{
					this.addFieldError("pointsgoods.info_state", "请先选择审核状态");
				}
			}
		}
		//判断会员发布积分商品是否审核
		if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
			if(cfg_jifen_audit.equals("0")){
				info_state="0";
			}else{
				info_state="1";
			}
		}
		pointsgoods.setInfo_state(info_state);
		return info_state;
	}
	
	
	/**
	 * 方法描述：新增记录积分商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String info_state = commonCheck();
		pointsgoods.setCust_id(this.session_cust_id);
		pointsgoods.setUser_id(this.session_user_id);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pointsgoods.setSort_no("0");
		}
		pointsgoods.setBuy_sum(0.0);
		super.commonValidateField(pointsgoods);
		if(super.ifvalidatepass){
			return add();
		}
		pointsgoods.setLab("0");
		String id = this.pointsgoodsService.insertGetPk(pointsgoods);
		//积分商品数据操作流
		addAuditRecord(id,"pointsgoods",info_state,"新增积分商品");
		//积分商品添加后,商品标记活动商品
		goods = goodsService.get(pointsgoods.getGoods_id());
		goods.setActive_state("3");
		goodsService.update(goods);
		this.addActionMessage("新增积分商品信息成功！");
		this.pointsgoods = null;
		this.hidden_goodsname=null;
		return add();
	}

	/**
	 * 方法描述：修改记录积分商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id=pointsgoods.getTrade_id();
		String info_state = commonCheck();
		this.pointsgoods.setCat_attr(cat_attr);
		if(super.ifvalidatepass){
			return view();
		}
		//更新积分商品
		this.pointsgoodsService.update(pointsgoods);
		//积分商品数据操作流
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			addAuditRecord(id,"pointsgoods",info_state,"修改积分商品");
		}else{
			if (info_state.equals("1")) {
				reason = "审核通过状态";
			}
			addAuditRecord(id,"pointsgoods",info_state,reason);
		}
		this.addActionMessage("修改积分商品信息成功！");
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 1:03:12 PM
	 * @Method Description :公用删除
	 */
	private void commonDelete(){
		boolean flag = this.pointsgoodsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除积分商品信息成功");
		}else{
			this.addActionMessage("删除积分商品信息失败");
		}
	}
	
	/**
	 * 方法描述：删除记录积分商品信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return list();
	}
	
	/**
	 * 方法描述：删除积分商品审核息信息
	 * @return
	 * @throws Exception
	 */
	public String auditDelete() throws Exception {
		commonDelete();
		return auditList();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 12:57:17 PM
	 * @Method Description :公共搜索
	 */
	private void commonList(Map pageMap){
		// 获取搜索的分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (info_state_s != null && !info_state_s.equals("")){
			pageMap.put("info_state_s", info_state_s);
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id",this.session_cust_id);
		}
		if(!validateFactory.isRequired(cust_name_s)){
			pageMap.put("cust_name",cust_name_s);
		}
		if(!validateFactory.isRequired(goods_name_s)){
			pageMap.put("goods_name",goods_name_s);
		}
		if (info_state_s != null && !info_state_s.equals("")){
			pageMap.put("info_state_s", info_state_s);
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
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state","1,3");
		}
		commonList(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.pointsgoodsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		pointsgoodsList = this.pointsgoodsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	
	/**
	 * 方法描述：会员后台根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("info_state","0,2");
		commonList(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.pointsgoodsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		pointsgoodsList = this.pointsgoodsService.getList(pageMap);
		return goUrl("auditindex");
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 12:38:11 PM
	 * @Method Description :查看详情
	 */
	private boolean commonView(String id){
		if(id==null || id.equals("")){
			return true;
		}else{
			if(pointsgoods.getGoods_id()!=null && !pointsgoods.getGoods_id().equals("")){
				goods=this.goodsService.getByPkNoDel(pointsgoods.getGoods_id());
				if(goods!=null && goods.getGoods_name()!=null){
					hidden_goodsname = goods.getGoods_name();
					return false;
				}else{
					hidden_goodsname = null;
					return false;
				}
			}
			
		}
		return true;
	}
	
	/**
	 * 方法描述：根据主键找出记录积分商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.pointsgoods.getTrade_id();
		if(commonView(id)){
			return list();
		}
		if(pointsgoods.getCat_attr()!=null&&!pointsgoods.getCat_attr().equals("")){
			viewCat(pointsgoods.getCat_attr());
		}
		//获取审核列表
		searchAuditList(id,"pointsgoods");
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：跳转到审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.pointsgoods.getTrade_id();
		if(commonView(id)){
			return auditList();
		}
		//得到所属分类字符串
		String cat_id = pointsgoods.getCat_attr();
		//转化为具体的分类
		cat_String = categoryFuc.getCateNameByMap(cat_id);
		//获取审核列表
		searchAuditList(id,"pointsgoods");
		return goUrl("audit");
	}
	
	/**
	 * @author : HZX
	 * @date : Mon Mar 25 16:00:03 CST 2014
	 * @Method Description :审核积分商品设置
	 */
	public String auditState() throws Exception {
		String info_state = this.pointsgoods.getInfo_state() ;
		String id = this.pointsgoods.getTrade_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if (info_state.equals("1")) {
			reason = "审核通过";
		}else if (info_state.equals("2")) {
			if(reason.equals("")){
				this.addFieldError("pointsgoods.info_state", "您还未填写审核不通过理由！");
			}
		}else{
			this.addFieldError("pointsgoods.info_state", "审核状态不能为空！");
		}
		if(super.ifvalidatepass){
			return audit();
		}
		// 获取数据库对象
		Pointsgoods gd = this.pointsgoodsService.get(id);
		if (info_state!= null&& !info_state.equals("")) {
			// 设置状态值
			gd.setInfo_state(info_state);
		}
		// 更新积分商品
		this.pointsgoodsService.update(gd);
		addAuditRecord(id,"pointsgoods", info_state,reason);
		this.addActionMessage("审核积分商品成功");
		return auditList();
	}
	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception {
		boolean flag = this.pointsgoodsService.updateSort("trade_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("积分商品排序成功");
		}else{
			this.addActionMessage("积分商品排序失败");
		}
		return list();
	}
	
	/**
	 * @author : HZX
	 * @date : Apr 17, 2014 3:45:04 PM
	 * @Method Description :公用排序
	 */
	private void commonSort(){
		boolean flag = this.pointsgoodsService.updateSort("trade_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("积分商品排序成功");
		}else{
			this.addActionMessage("积分商品排序失败");
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
		if(pointsgoods == null){
			pointsgoods = new Pointsgoods();
		}
		String id = this.pointsgoods.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			pointsgoods = this.pointsgoodsService.get(id);
		}
	}

	public Pointsgoods getPointsgoods() {
		return pointsgoods;
	}

	public void setPointsgoods(Pointsgoods pointsgoods) {
		this.pointsgoods = pointsgoods;
	}

}

