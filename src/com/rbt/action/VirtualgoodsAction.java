/*
 
 * Package:com.rbt.action
 * FileName: GoodsAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Selfextendattr;
import com.rbt.model.Selfparagroup;
import com.rbt.model.Selfparavalue;
import com.rbt.model.Selfspecvalue;
import com.rbt.model.Shiptemplate;
import com.rbt.service.IAutoupgoodsService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IParagroupService;
import com.rbt.service.IParavalueService;
import com.rbt.service.ISelfspecnameService;
import com.rbt.service.ISelfspecvalueService;
import com.rbt.service.IShiptemplateService;
import com.rbt.service.ISpecnameService;
import com.rbt.service.impl.MembercatService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录虚拟商品表信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 15 10:28:08 CST 2014
 */
@Controller
public class VirtualgoodsAction extends GoodsBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*******************实体层****************/
	private Goods goods;
	private Goodsattr goodsattr;
	/*******************业务层接口****************/
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsattrService goodsattrService;
	@Autowired
	private ISpecnameService specnameService;
	@Autowired
	private IAutoupgoodsService autoupgoodsService;
	@Autowired
	private IShiptemplateService shiptemplateService;
	@Autowired
	public ISelfspecnameService selfspecnameService;
	@Autowired
	public ISelfspecvalueService selfspecvalueService;
	@Autowired
	private IParagroupService paragroupService;
	@Autowired
	private IParavalueService paravalueService;
	@Autowired
	private IMembercatService membercatService;
	/*********************集合******************/
	public List goodsList;//商品
	public List specnameList;//规格
	public List goodsattrList;//商品属性
	public List specselfNameList;//规格组名称
	public List specselfValueList;//规格组值
	public List paragroupList;//参数组
	public List paravalueList;//参数
	public List updownList;//上下架
	public List ralateList;//相关商品
	public List autoupList;//自动上下架
	private Selfextendattr selfextendattr;
	private Selfparagroup selfparagroup;
	private Selfparavalue selfparavalue;
	/*********************字段******************/
	public String back_sel_cat;//回选分类
	public String back_sel_cat_name;//回选分类名
	public String cat_id;//分类ID
	public String cat_name;//分类名称
	public String brand_name;//品牌名称
	public String gimg;//商品图片
	public String market_price_str;//市场价格
	public String sale_price_str;//销售价格
	public String cost_price_str;//成本价
	public String volume_str;//物流体积
	public String logsweight_str;//物流重量
	public String goods_item_str;//货号
	public String stock_str;//库存
	public String specstr_str;//
	public String sel_spec_name;// 规格名称
	public String sel_spec_count;//
	public String is_more_attr;// 是否多属性
	public String self_goods_size_value;//
	public String self_goods_img_value;//
	public String self_goods_relate_img_value;//
	public String self_goods_sort_value;//
	public String self_size_id;//
	public String gsizeHtml;//
	public String goods_detail;//商品详细
	public String up_goods_str;// 商品属性的上架串
	public String goods_up_str;//
	public String goods_down_str;//商品属性的下架串
	public String para_num;//
	public String is_must_delete_spec;//必须删除的规格
	public String s_goods_name;//商品名称
	public String is_up;//是否上架
	public String sgis;//商品的相关商品串
	public String shipname;//运费名称
	public String goods_no_s;//商品编号
	public String info_state_s;//审核状态
	public String goods_name_s;//商品名称
	public String brand_name_s;//品牌名称
	public String reason;//审核理由
	public String info_state;//审核状态
	public String self_cat_attr;//自定义分类
	public String is_update;//是否为新增页面
	public String cheked;//选择相关商品可以单个
	public String module_s;//用于控制模块是否开启审核主要是用在查找商品
	public int flagAdd=0;
	public String cust_id_flag;
	public String active_state_flag;
	public String slef_para_value;//
	public String ex_attr_value;//
	public String goodslab;
	/**
	 * @author : LJQ
	 * @date : Jan 15, 2014 3:32:19 PM
	 * @Method Description :添加选择页面
	 */
	public String selcat() {
		return goUrl("selcat");
	}

	/**
	 * @author : LJQ
	 * @date : Jan 15, 2014 3:32:19 PM
	 * @Method Description :更新选择页面
	 */
	public String upscat() {
		return goUrl("upscat");
	}
	
	
	/**
	 * @author : LSQ
	 * @date : Mar 26, 2014 2:16:55 PM
	 * @Method Description :虚拟新增商品查看页面
	 */
	@SuppressWarnings("unchecked")
	public String add() throws Exception {
		// cat_id去空格
		if (back_sel_cat != null && !"".equals(back_sel_cat)) {
			goods_cat = back_sel_cat.replace(catIdStr + ",", "").replace(" ",
					"");
			cat_name = CategoryFuc.getCateNameByMap(goods_cat);
		}
		// 获取商品的标签
		getGoodsLabel();
		// 获取商品品牌
		getBrandList(goods_cat);
		// 实例化商品对象
		goods = new Goods();
		// 生成十二位随机的字母与数字组合的字符串
		String charNum = RandomStrUtil.getNumberRand();
		goods.setGoods_no("GN" + charNum);
		return goUrl(ADD);
	}
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:25:16 PM
	 * @Method Description : 赋值
	 */
	private void commonDefault(){
		// 商品品牌
		if (ValidateUtil.isRequired(this.goods.getBrand_id())) {
			this.goods.setBrand_id("0");
		}
		//会员自定义分类
		if(self_cat_attr!=null &&!self_cat_attr.equals("")){
			self_cat_attr=self_cat_attr.replace(" ","");
			goods.setSelf_cat(self_cat_attr);
		}
		//商品分类
		if (ValidateUtil.isRequired(goods_cat)) {
			this.goods.setCat_attr("0");
		} else {
			String goods_cat_attr = goods_cat.replace(" ", "");
			this.goods.setCat_attr(goods_cat_attr);
		}
		//商品详细页
		if (!ValidateUtil.isRequired(goods_detail)) {
			this.goods.setGoods_detail(goods_detail);
		}
		//商品图片
		if (gimg != null && !gimg.equals("")) {
			String img_path = gimg.replace(" ", "");
			goods.setImg_path(img_path);
		}
		//验证状态
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//判断运营商是否让会员商品是否审核
			if(cfg_virtualGoods.equals("1")){
				this.goods.setInfo_state("1");// 审核通过
			}else{
				this.goods.setInfo_state("0");// 未审核
			}
		}else{
			this.goods.setInfo_state("1");// 审核通过
		}
	}
	
	

	/**
	 * @author : LJQ
	 * @date : Feb 4, 2014 4:13:21 PM
	 * @Method Description :保存的公共方法
	 */
	private void commonSave(String goods_id) {
		Map commonMap=commonCheck();
		goods.setSeo_desc(goods.getGoods_name());
		goods.setSeo_keyword(goods.getGoods_name());
		goods.setSeo_title(goods.getGoods_name());
		this.goodsService.insertGoods(goods_id, goods, random_num, goodsattrList, goods_item_str, specstr_str,
				market_price_str, sale_price_str, cost_price_str, stock_str, volume_str, logsweight_str, up_goods_str, self_goods_size_value, self_goods_img_value, 
				self_goods_relate_img_value, self_goods_sort_value, self_size_id, sel_spec_name, is_must_delete_spec, sel_spec_count,
				goods_up_str, goods_down_str, selfextendattr, ex_attr_value, selfparagroup, para_num, selfparavalue, slef_para_value,reason,
				this.session_cust_id,this.session_user_id,this.session_user_name,commonMap);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:12:42 PM
	 * @Method Description :验证数据
	 */
	private Map commonCheck(){
		Map comMap=new HashMap();
		//设置list_img
		String list_img=goods.getList_img();
		if(!ValidateUtil.isRequired(list_img)){
			if(list_img.indexOf("big")>-1){
				list_img=list_img.replace("big","mid");
			}
				this.goods.setList_img(list_img);
		}
		// 是否为新增页面
		if(is_update==null){
			this.goods.setUser_id(this.session_user_name);
			this.goods.setCust_id(this.session_cust_id);
			this.goods.setIs_del("1");// 默认未删除商品
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			// 判断运营商是否让会员商品是否审核
			if(cfg_virtualGoods.equals("1")){
				this.goods.setInfo_state("1");// 审核通过
			}else{
				this.goods.setInfo_state("0");// 未审核
			}
		}else{
			// 是否为新增页面
			if(is_update==null){
				this.goods.setInfo_state("1");// 审核通过
			}
			
		}
		// 商品品牌数据处理
		if (ValidateUtil.isRequired(this.goods.getBrand_id())) {
			this.goods.setBrand_id("0");
		}
		// 会员自定义分类
		if(self_cat_attr!=null &&!self_cat_attr.equals("")){
			self_cat_attr=self_cat_attr.replace(" ","");
			goods.setSelf_cat(self_cat_attr);
		}
		// 商城商品分类
		if (ValidateUtil.isRequired(goods_cat)) {
			this.goods.setCat_attr("0");
		} else {
			String goods_cat_attr = goods_cat.replace(" ", "");
			this.goods.setCat_attr(goods_cat_attr);
		}
		// 商品图片
		if (gimg != null && !gimg.equals("")) {
			String img_path = gimg.replace(" ", "");
			goods.setImg_path(img_path);
		}
		// 商品标签
		if(goods.getLab()!=null && !goods.getLab().equals("")){
			goods.setLab(goods.getLab().replace(" ",""));
		}
		// 商品详情
		if(goods_detail!=null && !goods_detail.equals("")){
			goods.setGoods_detail(goods_detail);
		}
		return comMap;
	}

	/**
	 * 方法描述：新增记录商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String insert() throws Exception {
		this.flagAdd=1;
 		commonSave(null);
 		if(super.ifvalidatepass){
 			return add();
		}
		this.addActionMessage("新增商品信息成功！");
		this.goods = null;
		this.goodsattr = null;
		this.ex_attr_value=null;
		this.selfextendattr=null;
		this.is_more_attr=null;
		return add();
	}
	/**
	 * 方法描述：新增记录商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void savegoods() throws Exception {
		HttpServletResponse response = getResponse();
		commonSave(null);
		PrintWriter out = response.getWriter();
		// 生成十二位随机的字母与数字组合的字符串
		String charNum = RandomStrUtil.getNumberRand();
		out.print("GN" + charNum);
		
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:08:16 PM
	 * @Method Description :更新验证
	 */
	private void updateVirtualCheck(){
		String id = this.goods.getGoods_id();
		this.goods.setUser_id(this.session_user_name);
		if (info_state.equals("1")) {
			reason = "审核通过状态";
		}else if (info_state.equals("3")) {
			if(reason.equals("")){
				this.addFieldError("goods.info_state", "您还未填禁用的理由!");
			}
		}else{
			this.addFieldError("goods.info_state", "请选择状态");
		}
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:36:36 PM
	 * @Method Description :更新方法
	 */
	private void commonUpdate(){
		this.goodsService.updatetVirtualGoods(goods, goods_up_str, goods_down_str, session_cust_id, session_user_id, session_user_name, random_num, reason);
	}
	
	/**
	 * 方法描述：修改记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = this.goods.getGoods_id();
		commonUpdateCheck();
		// 验证数据正确性
		super.commonValidateField(goods);
		if (super.ifvalidatepass) {
			return view();
		}
		commonUpdate(id);
		this.addActionMessage("修改商品信息成功！");
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:14:35 PM
	 * @Method Description :更新的公共方法
	 */
	private void commonUpdate(String goods_id){
		Map<String,String> commonMap=commonCheck();
		this.goodsService.updateGoods(goods_id,goods, random_num, goodsattrList, goods_item_str, specstr_str,
				market_price_str, sale_price_str, cost_price_str, stock_str, volume_str, logsweight_str, up_goods_str, self_goods_size_value, 
				self_goods_img_value, self_goods_relate_img_value, self_goods_sort_value, self_size_id, sel_spec_name, 
				is_must_delete_spec, sel_spec_count, goods_up_str, goods_down_str, selfextendattr, ex_attr_value,
				selfparagroup, para_num, selfparavalue, slef_para_value,reason,this.session_user_id,this.session_user_name,commonMap);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:20:42 PM
	 * @Method Description :更新验证
	 */
	private void commonUpdateCheck(){
		//String info_state = this.goods.getInfo_state();
		this.goods.setUser_id(this.session_user_id);
		// 判断是否是会员
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//if (goods.getInfo_state() != null && goods.getInfo_state().equals("0")) {
				//reason = "修改商品信息";
			//}
			if(cfg_virtualGoods.equals("0")){
				goods.setInfo_state("0");
			}else {
				goods.setInfo_state("1");
			}
			reason = "会员修改商品信息";
		} else {
			// 新增审核会员数据操作流 1：审核通过 2：审核不通过
			if (info_state.equals("1")) {
				reason = "审核通过状态";
			} else if (info_state.equals("3")) {
				if (reason.equals("")) {
					this.addFieldError("goods.info_state", "您还未填禁用的理由");
				}
			} else {
				this.addFieldError("goods.info_state", "请先选择审核状态");
			}
			goods.setInfo_state(info_state);
		}
		//设置标签的值,之所以这样改是因为如果标签只有个的时候,
		//修改成全部不勾选时还是会保存原来的那个
		goods.setLab(goodslab);
	}
	/**
	 * @author :LSQ
	 * @date : Mar 18, 2014 10:18:29 AM
	 * @Method Description :修改仓库中的数据
	 * 
	 */
	public String storeupdate() throws Exception {
		String id = this.goods.getGoods_id();
		commonUpdateCheck();
		// 验证数据正确性
		super.commonValidateField(goods);
		if (super.ifvalidatepass) {
			return view();
		}
		commonUpdate(id);
		this.addActionMessage("修改仓库中虚拟商品成功！");
		return storelist();
	}

	/**
	 * 方法描述：删除记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "0");// 0：逻辑删除商品
		if (flag) {
			this.addActionMessage("删除虚拟商品成功！");
		} else {
			this.addActionMessage("删除虚拟商品失败！");
		}
		return list();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 9:53:06 AM
	 * @Method Description :删除仓库中的虚拟商品
	 */
	public String storedelete() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "0");// 0：逻辑删除商品
		if (flag) {
			this.addActionMessage("删除虚拟商品成功！");
		} else {
			this.addActionMessage("删除虚拟商品失败！");
		}
		return storelist();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 13, 2014 4:51:51 PM
	 * @Method Description :查看仓库商品
	 */
	public String storeview() throws Exception {
		if(!commonView()){
			return storelist();
		}
		return goUrl("storeupdate");
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 11:26:07 AM
	 * @Method Description :审核列表中的删除
	 */
	public String auditelete() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "0");// 0：逻辑删除商品
		if (flag) {
			this.addActionMessage("删除审核虚拟商品成功！");
		} else {
			this.addActionMessage("删除审核虚拟商品失败！");
		}
		return auditlist();
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 6:41:33 PM
	 * @Method Description :查询条件
	 */
	private void commonList(Map pageMap){
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			if(!this.session_cust_id.equals("0")){
				pageMap.put("cust_id", this.session_cust_id);
			}
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (!ValidateUtil.isRequired(goods_no_s)) {
			pageMap.put("goods_no", goods_no_s);
		}
		if (!ValidateUtil.isRequired(info_state_s)) {
			pageMap.put("info_state", info_state_s);
		}
		if (!ValidateUtil.isRequired(goods_name_s)) {
			pageMap.put("goods_name", goods_name_s);
		}
		if (!ValidateUtil.isRequired(cat_attr_s)) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (!ValidateUtil.isRequired(brand_name_s)) {
			pageMap.put("brand_name", brand_name_s);
		}
		//虚拟商品
		pageMap.put("gd_is_virtual", "0");
		//未删除商品
		pageMap.put("is_del", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}
		//上架商品
		pageMap.put("is_up", "0");
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	


	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:29:29 PM
	 * @Method Description :查看详情
	 */
	private boolean commonView() {
		String goods_id = this.goods.getGoods_id();
		if (goods_id == null || goods_id.equals("")) {
			return false;
		} else {
			goods = this.goodsService.get(goods_id);
		}
		// 分类名称
		back_sel_cat = goods.getCat_attr();
		goods_cat = back_sel_cat.replace(catIdStr + ",", "").replace(" ", "");
		cat_name = CategoryFuc.getCateNameByMap(goods_cat);
		if (goods.getSelf_cat() != null && !goods.getSelf_cat().equals("")) {
			viewSelfCat(goods.getSelf_cat());
		}
		// 获取商品分类拥有的规格列表
		Map specMap = new HashMap();
		specMap.put("cat_attr", goods_cat);
		specnameList = this.specnameService.getList(specMap);
		// 获取商品的标签
		getGoodsLabel();
		// 获取扩展属性列表
		getExtendAttr(goods_id);
		// 获得参数组列表
		getParagroup(goods_id);
		// 获取商品品牌
		getBrandList(goods_cat);
		// 查找出商品属性的列表
		Map attrMap = new HashMap();
		attrMap.put("goods_id", goods_id);
		goodsattrList = this.goodsattrService.getList(attrMap);
		if(goodsattrList!=null&&goodsattrList.size()>0){
			Map mspecstr=(HashMap)goodsattrList.get(0);
			//spestr是为了判断是多属性还是单属性
			if (mspecstr.get("specstr")!=null && !"".equals(mspecstr.get("specstr").toString())) {
				//获取商品自定义规格名称列表
				Map ssnMap = new HashMap();
				ssnMap.put("goods_id", goods_id);
				ssnMap.put("id_asc", "id_asc");
				specselfNameList = this.selfspecnameService.getList(ssnMap);
				if (specselfNameList != null && specselfNameList.size() > 0) {
					is_more_attr = "0";
					String flagId="";
					for(int i=0;i<specselfNameList.size();i++){
						Map map=(HashMap)specselfNameList.get(i);
						if (map.get("spec_serial_id") != null) {
							if(!flagId.equals("")){
								flagId = flagId +","+ map.get("spec_serial_id").toString();
							}else{
								flagId = map.get("spec_serial_id").toString();
							}
						}
					}
					if(!ValidateUtil.isRequired(flagId)){
						//获取自定义规格值列表
						Map ssvMap = new HashMap();
						ssvMap.put("id_asc", "id_asc");
						ssvMap.put("spec_serial_id", flagId);
						specselfValueList = this.selfspecvalueService.getList(ssvMap);
					}
				}
			}
		}
		
		// 加载FCK数据
		loadFckData(goods_id, "goods");
		// 加载自动上下架管理数据
		Map updownMap = new HashMap();
		updownMap.put("goods_id", goods_id);
		updownList = this.autoupgoodsService.getList(updownMap);
		// 获取商品的相关商品串
		String sgis = goods.getRelate_goods();
		if (sgis != null && !sgis.equals("")) {
			Map sgisMap = new HashMap();
			sgisMap.put("sgis", sgis);
			ralateList = this.goodsService.getList(sgisMap);
		}
		// 根据商品的模板ID，查询出模板名称
		if (goods.getShip_id() != null && !goods.getShip_id().equals("")) {
			if (this.shiptemplateService.get(goods.getShip_id()) != null) {
				Shiptemplate stp = this.shiptemplateService.get(goods.getShip_id());
				shipname = stp.getShip_name();
			} else {
				shipname = "该模板已删除";
			}
		}
		// 获取审核列表
		searchAuditList(goods_id, "goods");
		// 获取自动上下架商品的列表
		Map autoMap = new HashMap();
		autoMap.put("goods_id", goods_id);
		autoupList = this.autoupgoodsService.getList(autoMap);
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			// 加载卖家等级信息
			getSellLevel("0");
		}
		// 获取审核列表
		searchAuditList(goods.getGoods_id(), "goods");
		return true;
	}
	/**
	 * @author : LJQ
	 * @date : Feb 5, 2014 1:42:36 PM
	 * @Method Description : 获取参数组与参数值表数据
	 */
	public void getParagroup(String goods_id) {
		// 参数组list
		Map pgMap = new HashMap();
		pgMap.put("cat_attr", goods_cat);
		paragroupList = this.paragroupService.getList(pgMap);
		// 参数组值表
		Map pgvMap = new HashMap();
		pgvMap.put("cat_attr", goods_cat);
		if (goods_id != null && !goods_id.equals("")) {
			pgvMap.put("goods_id", goods_id);
			paravalueList = this.paravalueService.getParaValList(pgvMap);
		} else {
			paravalueList = this.paravalueService.getList(pgvMap);
		}
	}
	
	/**
	 * 方法描述：根据主键找出记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(!commonView()){
			return list();
		}
		return goUrl(VIEW);
	}

	/**
	 * @author :LSQ
	 * @date : Mar 14, 2014 1:08:38 PM
	 * @Method Description :商品审核信息
	 */
	public String audit() throws Exception {
		if(!commonView()){
			return auditlist();
		}
		return goUrl("audit");
	}

	/**
	 * @author :LSQ
	 * @date : Mar 15, 2014 10:43:00 AM
	 * @Method Description :商品审核
	 */
	public String auditState() throws Exception {
		String id = this.goods.getGoods_id();
		if (id == null || id.equals("")) {
			return auditlist();
		}
		if (info_state.equals("1")) {
			reason = "审核通过";
		}else if (info_state.equals("2")) {
			if(reason.equals("")){
				this.addFieldError("goods.info_state", "您还未填写审核不通过理由！");
			}
		}else{
			this.addFieldError("goods.info_state", "请选择状态");
		}
		if (super.ifvalidatepass) {
			return audit();
		}
		// 设置审核状态
		Goods goods = this.goodsService.get(id);
		goods.setInfo_state(info_state);
		this.goodsService.updateAuditState(goods, reason, session_cust_id, session_user_id, session_user_name);
		this.addActionMessage("审核虚拟商品成功");
		return auditlist();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 13, 2014 4:39:30 PM
	 * @Method Description :商品审核列表
	 */
	@SuppressWarnings("unchecked")
	public String auditlist() throws Exception {
		Map pageMap = new HashMap();
		//上架商品
		pageMap.put("is_up", "0");
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "0,2");
		}
		commonList(pageMap);
		return goUrl("auditlist");
	}

	/**
	 * @author :LSQ
	 * @date : Feb 28, 2014 4:19:26 PM
	 * @Method Description :产品下架
	 */
	@SuppressWarnings("unchecked")
	public String storelist() throws Exception {
		Map pageMap = new HashMap();
		//下架商品
		pageMap.put("is_up", "1");
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}
		commonList(pageMap);
		return goUrl("storeindex");
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 9:53:06 AM
	 * @Method Description :商品下架功能
	 */
	public String storedown() throws Exception {
		boolean flag = this.goodsService.updateIsup(goods, "1");// 下架
		if (flag) {
			this.addActionMessage("虚拟商品下架成功!");
		} else {
			this.addActionMessage("虚拟商品下架失败!");
		}
		return list();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 9:53:27 AM
	 * @Method Description :商品上架功能
	 */
	public String storeup() throws Exception {
		boolean flag = this.goodsService.updateIsup(goods, "0");// 上架
		if (flag) {
			this.addActionMessage("虚拟商品上架成功!");
		} else {
			this.addActionMessage("虚拟商品上架失败!");
		}
		return storelist();
	}


	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (goods == null) {
			goods = new Goods();
		}
		String id = this.goods.getGoods_id();
		if (!this.validateFactory.isDigital(id)) {
			goods = this.goodsService.get(id);
		}
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Goodsattr getGoodsattr() {
		return goodsattr;
	}

	public void setGoodsattr(Goodsattr goodsattr) {
		this.goodsattr = goodsattr;
	}

	public Selfextendattr getSelfextendattr() {
		return selfextendattr;
	}

	public void setSelfextendattr(Selfextendattr selfextendattr) {
		this.selfextendattr = selfextendattr;
	}

	public Selfparagroup getSelfparagroup() {
		return selfparagroup;
	}

	public void setSelfparagroup(Selfparagroup selfparagroup) {
		this.selfparagroup = selfparagroup;
	}



}
