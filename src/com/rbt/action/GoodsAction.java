/*
 
 * Package:com.rbt.action
 * FileName: GoodsAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.GoodsDetailToAppDetailFuc;
import com.rbt.function.SalegoodsFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodsattr;
import com.rbt.model.Salegoods;
import com.rbt.model.Selfextendattr;
import com.rbt.model.Selfparagroup;
import com.rbt.model.Selfparavalue;
import com.rbt.model.Selfspecvalue;
import com.rbt.model.Shiptemplate;
import com.rbt.model.Taxrate;
import com.rbt.service.IAutoupgoodsService;
import com.rbt.service.ICouponService;
import com.rbt.service.IDirectsellService;
import com.rbt.service.IGenericService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.rbt.service.IInternationaltariffService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IParagroupService;
import com.rbt.service.IParavalueService;
import com.rbt.service.ISalegoodsService;
import com.rbt.service.ISelfspecnameService;
import com.rbt.service.ISelfspecvalueService;
import com.rbt.service.IShiptemplateService;
import com.rbt.service.ISpecnameService;
import com.rbt.service.ITaxrateService;

/**
 * @function 功能 记录商品表信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 15 10:28:08 CST 2014
 */
@SuppressWarnings("unchecked")
@Controller
public class GoodsAction extends GoodsBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Goods goods;
	private Goodsattr goodsattr;
	private Selfspecvalue selfspecvalue;
	private Selfextendattr selfextendattr;
	private Selfparagroup selfparagroup;
	private Selfparavalue selfparavalue;
	/*******************业务层接口****************/
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsattrService goodsattrService;
	@Autowired
	private ISpecnameService specnameService;
	@Autowired
	private IParagroupService paragroupService;
	@Autowired
	private IParavalueService paravalueService;
	@Autowired
	private IAutoupgoodsService autoupgoodsService;
	@Autowired
	private IShiptemplateService shiptemplateService;
	@Autowired
	private IMembercatService membercatService;
	@Autowired
	public ISelfspecnameService selfspecnameService;
	@Autowired
	public ISelfspecvalueService selfspecvalueService;
	@Autowired
	public IDirectsellService directsellService;
	@Autowired
	private ISalegoodsService salegoodsService;
	@Autowired
	private IInternationaltariffService internationaltariffService;
	@Autowired
	private ITaxrateService taxrateService;
	@Autowired
	private ICouponService couponService;
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
	public List interList;//国际物流
	/*********************字段******************/
	public String back_sel_cat;//回选分类
	public String back_sel_cat_name;//回选分类名
	public String cat_id;//分类ID
	public String cat_name;//分类名称
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
	public String slef_para_value;//
	public String ex_attr_value;//
	public String is_must_delete_spec;//必须删除的规格
	public String s_goods_name;//商品名称
	public String s_goods_no;//商品名称
	public String s_cust_name;//商品名称
	public String sale_id;//商品促销ID
	public String is_up;//是否上架
	public String sgis;//商品的相关商品串
	public String shipname;//运费名称
	public String info_state_s;//审核状态
	public String reason;//审核理由
	public String info_state;//审核状态
	public String self_cat_attr;//自定义分类
	public String is_update;//是否为新增页面
	public String cheked;//选择相关商品可以单个
	public String module_s;//用于控制模块是否开启审核主要是用在查找商品
	public int flagAdd=0;//标记添加完跳到add()让方法不会提示分类重选
	public String cust_id_flag;//标记查找商品的cust_id,主要是运营商修改卖家团购时让他去找卖家的商品而不是运营商的
	public String active_state_flag;//标记商品活动状态 0：普通商品，1：团购商品
	public String goodslab;//用于修改商品标签时使用
	public String phone_goods_detail;//用于前台处理手机详细 没有包括样式
	public String CFG_CAUTIONSTOCK="cfg_Cautionstock";//判断库存是否小于系统参数
	public String total_stock;
	public String iname;//文件路径
	public String hidden_nowprice;//设置价格
	public String hidden_updatetype;//修改类型
	public String hidden_pricetype;//价格类型成本价 销售价
	public String hidden_pricetype2;//公式价格类型2
	public String hidden_mouth;//设置加减乘除
	public String hidden_pricevalue;//公式操作值
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
	 * @author : LJQ
	 * @date : Feb 5, 2014 1:42:36 PM
	 * @Method Description : 获取参数组与参数值表数据
	 */
	public void getParagroup(String goods_id) {
		// 参数组list
		String[] cat_attrs = goods_cat.split(",");
		paragroupList = commonParagroup(cat_attrs, "", paragroupService);
		// 参数组值表
		Map pgvMap = new HashMap();
		pgvMap.put("cat_attr", goods_cat);
		if (goods_id != null && !goods_id.equals("")) {
			pgvMap.put("goods_id", goods_id);
			paravalueList = commonParagroup(cat_attrs, goods_id, paravalueService);
		} else {
			paravalueList = commonParagroup(cat_attrs, "", paravalueService);
		}
	}

    /**
     * 返回参数组，和参数值
     * @param cat_attr_s分类数组
     * @param goods_id商品ID
     * @return
     */
	private List commonParagroup(String[] cat_attr_s, String goods_id, IGenericService t) {
		List commonList = new ArrayList();
		for(int i =0; i < cat_attr_s.length; i++) {
			List list = new ArrayList();
			Map map = new HashMap();
			map.put("cat_attr", cat_attr_s[i]+"|");
			if(!ValidateUtil.isRequired(goods_id)) {
				map.put("goods_id", goods_id);
				list = this.paravalueService.getParaValList(map);
			}else {
			    list = t.getList(map);
			}
			
			if(list != null && list.size() > 0) {
				for(int j = 0; j < list.size(); j++) {
					Map attrMap = (HashMap) list.get(j);
					commonList.add(attrMap);
				}
			}
		}
		return commonList;
	}
	
	
	/**
	 * 方法描述：返回新增记录商品表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String add() throws Exception {
		//获取产地地区
		getTopArea();
		// cat_id去空格
		//获取第一个分类
		String first_cat = "";
		
		if (back_sel_cat != null && !"".equals(back_sel_cat)) {
			goods_cat = back_sel_cat.replace(catIdStr + ",", "").replace(" ", "");
			//获取第一大分类
			first_cat = goods_cat.split(",")[0];
			cat_name = CategoryFuc.getCateNameByMap(goods_cat);
		}else{
			if(flagAdd!=1){
				this.addFieldError("back_sel_cat", "请选择商品分类!");
				return selcat();
			}
		}
		if(goods != null && goods.getGoods_id() != null && !goods.getGoods_id().equals("")){
			return view();
		}
		// 获取商品分类拥有的规格列表
		Map specMap = new HashMap();
		specMap.put("cat_attr", goods_cat);
		specnameList = this.specnameService.getList(specMap);
		// 获取商品的标签
		getGoodsLabel();
		// 获取扩展属性列表
		getExtendAttr(null);
		// 获得参数组列表
		getParagroup(null);
		// 获取商品品牌
		getBrandList(first_cat);
		//获取贸易方式
		getTradeWay();
		//获取国际物流
		getInerTariff();
		// 实例化商品对象
		if(goods == null) {
		    goods = new Goods();
		}
		// 生成十二位随机的字母与数字组合的字符串
		String charNum = RandomStrUtil.getNumberRand();
		goods.setGoods_no("GN" + charNum);
		// 加载卖家等级信息
		getSellLevel("0");
		return goUrl(ADD);
	}
	/**
	 * 获取国际物流
	 */
	public void getInerTariff() {
		interList = this.internationaltariffService.getList(new HashMap());
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
			if(cfg_goods_audit.equals("1")){
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
		if(ValidateUtil.isRequired(goods.getSort_order())){
			goods.setSort_order("0");
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
	 * 获取税率百分比
	 * @return
	 */
	private String getTaxRate() {
		String tax_rate = "0";
		String[] tax_ids = tax_attr_s.split(",");
		Taxrate taxrate=new Taxrate();
		taxrate = this.taxrateService.get(tax_ids[tax_ids.length-1].trim());
		if(taxrate != null && !ValidateUtil.isRequired(taxrate.getTax_rate())) {
		   tax_rate = taxrate.getTax_rate();	
		}
		return tax_rate;
	}
	
	/**
	 * @author : LJQ
	 * @throws Exception 
	 * @date : Feb 4, 2014 4:13:21 PM
	 * @Method Description :保存的公共方法
	 */
	private String commonSave(String goods_id) throws Exception {
		Map commonMap=commonCheck();
		goods.setSeo_desc(goods.getGoods_name());
		goods.setSeo_keyword(goods.getGoods_name());
		goods.setSeo_title(goods.getGoods_name());
		if(!validateFactory.isRequired(goods.getIs_limit())&&"0".equals(goods.getIs_limit())){
			if(validateFactory.isRequired(goods.getLimit_num())){
				this.addFieldError("goods.limit_num", "请填限购数量！");
				return add();
			}
			
		}
		if("1".equals(goods.getIs_limit())){
			goods.setLimit_num("10000");
		}
		this.goodsService.insertGoods(goods_id, goods, random_num, goodsattrList, goods_item_str, specstr_str,
				market_price_str, sale_price_str, cost_price_str, stock_str, volume_str, logsweight_str, up_goods_str, self_goods_size_value, self_goods_img_value, 
				self_goods_relate_img_value, self_goods_sort_value, self_size_id, sel_spec_name, is_must_delete_spec, sel_spec_count,
				goods_up_str, goods_down_str, selfextendattr, ex_attr_value, selfparagroup, para_num, selfparavalue, slef_para_value,reason,
				this.session_cust_id,this.session_user_id,this.session_user_name,commonMap);
		return goods_id;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:14:35 PM
	 * @Method Description :更新的公共方法
	 */
	private void commonUpdate(String goods_id){
		Map<String,String> commonMap=commonCheck();
		goods.setSeo_desc(goods.getGoods_name());
		goods.setSeo_keyword(goods.getGoods_name());
		goods.setSeo_title(goods.getGoods_name());
		goods.setPhone_goods_detail(phone_goods_detail);
		this.goodsService.updateGoods(goods_id,goods, random_num, goodsattrList, goods_item_str, specstr_str,
				market_price_str, sale_price_str, cost_price_str, stock_str, volume_str, logsweight_str, up_goods_str, self_goods_size_value, 
				self_goods_img_value, self_goods_relate_img_value, self_goods_sort_value, self_size_id, sel_spec_name, 
				is_must_delete_spec, sel_spec_count, goods_up_str, goods_down_str, selfextendattr, ex_attr_value,
				selfparagroup, para_num, selfparavalue, slef_para_value,reason,this.session_user_id,this.session_user_name,commonMap);
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
		//计算税费
		if(tax_attr_s.contains(", 0")){
			this.addFieldError("tax_attr_s", "请选择税费到最后一级");
		}else if(!ValidateUtil.isRequired(tax_attr_s) && !tax_attr_s.equals("0")) {
			tax_attr_s = tax_attr_s.trim().replace(" ", "");
			goods.setTax_attr(tax_attr_s);
			goods.setTax_rate(getTaxRate());
			
		}else {
			this.addFieldError("tax_attr_s", "请选择税费");
		}
 		if(super.ifvalidatepass){
 			return add();
		}
 		
 		commonSave(null);
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
	 * @date : Apr 18, 2014 1:20:42 PM
	 * @Method Description :更新验证
	 */
	private void commonUpdateCheck(){
		//String info_state = this.goods.getInfo_state();
		this.goods.setUser_id(this.session_user_name);
		// 判断是否是会员
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//if (goods.getInfo_state() != null && goods.getInfo_state().equals("0")) {
				//reason = "修改商品信息";
			//}
			if(cfg_goods_audit.equals("0")){
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
		if(!validateFactory.isRequired(goods.getIs_limit())&&"0".equals(goods.getIs_limit())){
			if(validateFactory.isRequired(goods.getLimit_num())){
				this.addFieldError("goods.limit_num", "请填限购数量！");
			}
			
		}
		if(!validateFactory.isRequired(goods.getIs_limit())&&"1".equals(goods.getIs_limit())){
			goods.setLimit_num("10000");
		}
		if(!ValidateUtil.isRequired(tax_attr_s)){
			tax_attr_s=tax_attr_s.replaceAll(" ", "");
			if(tax_attr_s.contains(",0")||tax_attr_s.equals("0")){
				this.addFieldError("tax_attr", "请选择税费到最后一级");
			}else {
				goods.setTax_attr(tax_attr_s);
				goods.setTax_rate(getTaxRate());
			}
			
		}else {
			this.addFieldError("tax_attr_s", "请选择税费");
		}
		
		
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
		tax_attr = "";
		tax_attr_s= "";
		this.addActionMessage("修改商品信息成功！");
		return list();
	}
	/**
	 * @author:HXM
	 * @date:Jun 9, 201410:00:02 AM
	 * @param:
	 * @Description:通过ajax修改商品的活动状态，
	 * 需要参数有商品编号（goods_id）
	 */
	public void updateActiveByAjax() throws Exception {
		boolean flag = true;
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		PrintWriter out = response.getWriter();
		String goods_id=request.getParameter("goods_id");
		if(ValidateUtil.isRequired(goods_id)){
			out.write("0");
		}else{
			HashMap directsellMap = new HashMap();
			directsellMap.put("goods_id", goods_id);
			List directsellList = this.directsellService.getList(directsellMap);
			if(directsellList != null){
				HashMap preMap = new HashMap();
				for(int i=0;i<directsellList.size();i++){
					preMap = (HashMap) directsellList.get(i);
					if(preMap!=null){
						String pretime = preMap.get("pretime").toString();
						String prenotstarttime = preMap.get("prenotstarttime").toString();
						if(pretime.endsWith("0") || prenotstarttime.equals("0")){//判断该商品在预售列表中是否有处于预售中或者预售前被选为预售商品
							flag=false;
							break;
						}
					}

				}
			}
			if(flag==true){//若flag为true，即预售列表中不存在正在预售或者预售前的商品
				Map map=new HashMap();
				map.put("goods_id", goods_id);
				map.put("active_state","0");
				goodsService.updateActiveState(map);
				out.write("1");
			}else{
				out.write("2");
			}
		
		}
	}
	
	
	/**
	 * @Method Description :生成单个手机详情
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public void toSetAppDetailOne() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String content=request.getParameter("content");
		String imgs=request.getParameter("imgs");
		String goods_id=request.getParameter("goods_id");
		if(content!=null&&imgs!=null&&!"".equals(imgs)&&!"".equals(content)){
			content=GoodsDetailToAppDetailFuc.DealSetAppDetail(content,imgs);
			goods =this.goodsService.get(goods_id);
			goods.setPhone_goods_detail(content);
			this.goodsService.update(goods);
		}
		out.write(content);
	}
	
	/**
	 * @Method Description :获取商品内容详情
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public void toSetAppDetailGoodsdContent() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String content="";
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			Goods appGoods=new Goods();
			appGoods=goodsService.get(id);
			if(appGoods!=null&&!ValidateUtil.isRequired(appGoods.getGoods_detail())){
				content=appGoods.getGoods_detail();
			}
		}
		out.write(content);
	}
	
	
	
	/**
	 * @Method Description :生成批量手机详情,且直接更新数据库
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public void toSetAppDetailMore() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String content=request.getParameter("content");
		String imgs=request.getParameter("imgs");
		String goods_id=request.getParameter("goods_id");
		if(content!=null&&!"".equals(content)){
			content=GoodsDetailToAppDetailFuc.DealSetAppDetail(content,imgs);
			goods =this.goodsService.get(goods_id);
			goods.setPhone_goods_detail(content);
			this.goodsService.update(goods);	
			out.write("1");
		}else{
			out.write("0");
		}
	}
	/**
	 * @Method Description :生成批量手机详情,且直接更新数据库
	 * @author : HZX
	 * @date : Apr 13, 2015 10:55:54 AM
	 */
	public void toDetailPic() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String imgs=request.getParameter("imgs");
		if(imgs!=null&&!"".equals(imgs)){
			boolean  flag=false;
			flag=GoodsDetailToAppDetailFuc.DealDetailPic(imgs);
			if(flag==false){
				//不存在图片异常
				out.write("0");
			}else {
				//存在图片异常
				out.write("1");
			}
			
		}else{
			//存在图片异常
			out.write("1");
		}
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
		this.addActionMessage("修改商品信息成功！");
		tax_attr = "";
		return storelist();
	}

	/**
	 * 方法描述：删除记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "0");// 逻辑删除商品
		if (flag) {
			this.addActionMessage("删除商品信息成功！");
		} else {
			this.addActionMessage("删除商品信息失败！");
		}
		return list();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 11:26:07 AM
	 * @Method Description :审核列表中的删除
	 */
	public String auditelete() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "0");// 逻辑删除商品
		if (flag) {
			this.addActionMessage("删除审核信息成功！");
		} else {
			this.addActionMessage("删除审核信息失败！");
		}
		return auditlist();
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
		//搜索条件
		commonMapSearchGoodsList(pageMap);
		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}else if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		pageMap = commonList(pageMap);
		// 上架商品: is_up: 0是 1否
		pageMap.put("is_up", "0");
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		//商品促销活动
		goodsList = SalegoodsFuc.replaceList(goodsList, "0", "");
		gCommparaList("goods_lable");
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		//商品促销活动
		getSaleGoods();
		//获取国家列表
		getTopArea();
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String recyclelist() throws Exception {
		Map pageMap = new HashMap();
		//搜索条件
		commonMapSearchGoodsList(pageMap);
		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}else if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		pageMap = commonList(pageMap);
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "0");
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		return goUrl("recycleindex");
	}
	
	/**
	 * @Method Description :从回收站还原商品
	 */
	public String reductionGoods() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "1");
		if (flag) {
			this.addActionMessage("还原商品成功！");
		} else {
			this.addActionMessage("还原商品失败！");
		}
		return recyclelist();
	}
	
	
	/**
	 * 方法描述：物理删除商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteTrue() throws Exception {
		boolean flag = this.goodsService.physicalDeleteGoods(chb_id);// 物理删除商品
		if (flag) {
			this.addActionMessage("删除商品信息成功！");
		} else {
			this.addActionMessage("删除商品信息失败！");
		}
		return recyclelist();
	}
	
	public void gCommparaList(String para_code) {
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparaList = commparaService.getList(map);
	}
	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:29:29 PM
	 * @Method Description :查看详情
	 */	
	private boolean commonView() {
		String goods_id = this.goods.getGoods_id();
		if(goods_id!=null&&!"".equals(goods_id)&&goods_id.contains(",")){
			goods_id=goods_id.substring(0,goods_id.indexOf(","));
		}
		//获取第一个分类
		String first_cat = "";
		if (goods_id == null || goods_id.equals("")) {
			return false;
		} else {
			goods = this.goodsService.get(goods_id);
		}
		if(goods_cat==null||"".equals(goods_cat)){
			// 分类名称
			back_sel_cat = goods.getCat_attr();
			goods_cat = back_sel_cat.replace(catIdStr + ",", "").replace(" ", "");
			first_cat = goods_cat.split(",")[0];
			cat_name = CategoryFuc.getCateNameByMap(goods_cat);
		}
		if (goods.getSelf_cat() != null && !goods.getSelf_cat().equals("")) {
			viewSelfCat(goods.getSelf_cat());
		}
		if(!ValidateUtil.isRequired(goods.getTax_attr())) {
			viewTax(goods.getTax_attr());
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
		getBrandList(first_cat);
		//获取贸易方式
		getTradeWay();
		//获取国际物流
		getInerTariff();
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
		// 根据商品的模板ID,查询出模板名称
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
	 * @author :LSQ
	 * @date : Mar 13, 2014 4:51:51 PM
	 * @Method Description :查看仓库商品
	 */
	public String storeview() throws Exception {
		if (!commonView()) {
			return storelist();
		}
		getTopArea();
		return goUrl("storeupdate");
	}

	/**
	 * 方法描述：根据主键找出记录商品表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if (!commonView()) {
			return list();
		}
		getTopArea();
		return goUrl(VIEW);
	}

	/**
	 * @author :LSQ
	 * @date : Mar 14, 2014 1:08:38 PM
	 * @Method Description :商品审核信息
	 */
	public String audit() throws Exception {
		if (!commonView()) {
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
		// 新增审核会员数据操作流 1：审核通过 2：审核不通过
		if(info_state==null || info_state.equals("")){
			this.addFieldError("goods.info_state", "请先选择审核状态！");
		}else{
			if (info_state.equals("1")) {
				reason = "审核通过";
			} else if (info_state.equals("2")) {
				if (reason.equals("")) {
					this.addFieldError("goods.info_state", "您还未填写审核不通过理由！");
				}
			}
		}
		if (super.ifvalidatepass) {
			return audit();
		}
		// 设置审核状态
		Goods goods = this.goodsService.get(id);
		goods.setInfo_state(info_state);
		this.goodsService.updateAuditState(goods, reason,this.session_cust_id,this.session_user_id,this.session_user_name);
		this.addActionMessage("审核商品成功");
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
		// 上架商品
		//pageMap.put("is_up", "0");
		//搜索条件
		if(goods_no_s!=null&&!"".equals(goods_no_s)){
			pageMap.put("goods_no", goods_no_s);
		}
		if(goods_name_s!=null&&!"".equals(goods_name_s)){
			pageMap.put("goods_name", goods_name_s);
		}
		if(cat_attr_s!=null&&!"".equals(cat_attr_s)){
			pageMap.put("cat_attr", cat_attr_s);
		}
		if(brand_name_s!=null&&!"".equals(brand_name_s)){
			pageMap.put("brand_name", brand_name_s);
		}
		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
	    if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}else{
		// 审核状态0表示未审核，2表示审核未通过
		pageMap.put("info_state_in", "0,2");
		}
		pageMap = commonList(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		return goUrl("auditlist");
	}

	/**
	 * @author : LJQ
	 * @date : Apr 17, 2014 9:14:42 PM
	 * @Method Description : 公共pageMap
	 */
	private Map commonList(Map pageMap) {
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
			//回选分类
			viewCat(cat_attr_s);
		}
		if (!ValidateUtil.isRequired(brand_name_s)) {
			pageMap.put("brand_name", brand_name_s);
		}
		// 未逻辑删除商品
		pageMap.put("is_del", "1");
		// 实物商品
		pageMap.put("gd_is_virtual", "1");
		return pageMap;
	}

	/**
	 * @author :LSQ
	 * @date : Feb 28, 2014 4:19:26 PM
	 * @Method Description :仓库中商品列表下架
	 */
	@SuppressWarnings("unchecked")
	public String storelist() throws Exception {
		Map pageMap = new HashMap();
		//搜索条件
		commonMapSearchGoodsList(pageMap);
		pageMap = commonList(pageMap);
		// 上架商品: is_up: 0是 1否
		pageMap.put("is_up", "1");
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		//商品促销活动
		goodsList = SalegoodsFuc.replaceList(goodsList, "0", "");
		gCommparaList("goods_lable");
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		//商品促销活动
		getSaleGoods();
		//获取国家列表
		getTopArea();
		return goUrl("storeindex");
	}
	/**
	 * @author :ZMS
	 * @Method Description :商品库存/规格/列表信息
	 */
	@SuppressWarnings("unchecked")
	public String gcountlist() throws Exception {
		Map pageMap = new HashMap();
		//搜索条件
		commonMapSearchGoodsList(pageMap);
        if(stock_str!=null&&!"".equals(stock_str)){
        	
        	if(stock_str.equals("0")){
        		//警告
        		pageMap.put("mixtotal_stock", cfg_Cautionstock);
        	}else {
        		//不警告
        		pageMap.put("maxtotal_stock", cfg_Cautionstock);
			}
        }

		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}else if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		
		pageMap = commonList(pageMap);
		
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		gCommparaList("goods_lable");
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		return goUrl("gcountindex");
	
	}
	
	/**
	 * @author： ZMS
	 * @Method Description : 根据主键查出信息
	 * 
	 */
	public String gcountview() throws Exception {
		if (!commonView()) {
			return gcountlist();
		}
		//获取产地地区
		getTopArea();
		return goUrl("gcountupdate");
	}
	/**
	 * @author :ZMS
	 * @Method Description :修改商品库存中的数据
	 * 
	 */
	public String gcountupdate() throws Exception {
		String id = this.goods.getGoods_id();
		commonUpdateCheck();
		// 验证数据正确性
		super.commonValidateField(goods);
		if (super.ifvalidatepass) {
			return gcountview();
		}
		commonUpdate(id);
		this.addActionMessage("修改商品信息成功！");
		return gcountlist();
	}

	
	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 9:53:06 AM
	 * @Method Description :删除仓库中的商品
	 */
	public String storedelete() throws Exception {
		boolean flag = this.goodsService.updateIsdel(chb_id, "0");// 0：逻辑删除商品
		if (flag) {
			this.addActionMessage("删除仓库中的商品成功！");
		} else {
			this.addActionMessage("删除仓库中的商品失败！");
		}
		return storelist();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 9:53:06 AM
	 * @Method Description :商品下架功能
	 */
	public String storedown() throws Exception {
		boolean flag = this.goodsService.updateIsup(goods, "1");// 下架
		if (flag) {
			this.addActionMessage("商品下架成功!");
		} else {
			this.addActionMessage("商品下架失败!");
		}
		return list();
	}

	/**
	 * @author :LSQ
	 * @date : Mar 1, 2014 9:53:27 AM
	 * @Method Description :商品上架功能
	 */
	public String storeup() throws Exception {
		boolean flag = this.goodsService.updateIsup(goods, "0");// 上架架
		if (flag) {
			this.addActionMessage("商品上架成功!");
		} else {
			this.addActionMessage("商品上架失败!");
		}
		return storelist();
	}

	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索商品
	 */
	public void searchGoods() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 商品id标识
		Map goodsMap = new HashMap();
		// 搜索商品名称
		if (s_goods_name != null && !s_goods_name.equals("")) {
			s_goods_name = URLDecoder.decode(s_goods_name, "UTF-8");
			goodsMap.put("goods_name", s_goods_name);
		}
		commonUTFSearchGoodsList();
		commonMapSearchGoodsList(goodsMap);
		// 搜索商品编号
		if (s_goods_no != null && !s_goods_no.equals("")) {
			goodsMap.put("goods_no", s_goods_no);
		}
		if (sgis != null && !sgis.equals("")) {
			goodsMap.put("sgis", sgis);
		}
		//搜索商品折扣
		if(!ValidateUtil.isRequired(sale_id)){
			Salegoods salegoods = this.salegoodsService.get(sale_id);
			goodsMap.put("sgis", salegoods.getTerm());
		}
		// 商品未删除
		if(is_up!=null&&is_up.equals("1")){
			goodsMap.put("is_up", "1");
		}else{
			goodsMap.put("is_up", "0");
		}
		goodsMap.put("is_del", "1");
		
		goodsMap.put("info_state", "1");
		
		//goodsMap.put("specstr_no","");//规格值为空
		//商品活动状态
		if (!ValidateUtil.isRequired(active_state_flag)) {
			goodsMap.put("active_state", active_state_flag);
		}
		goodsMap = ajaxMap(goodsMap);
		int totalNum = this.goodsService.getCount(goodsMap);
		List list = this.goodsService.getList(goodsMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索获取全部商品
	 */
	public void searchAllGoods() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 商品id标识
		Map goodsMap = new HashMap();
		// 商品未删除
		goodsMap.put("is_up", "0");
		goodsMap.put("is_del", "1");
		goodsMap.put("info_state", "1");
		int totalNum = this.goodsService.getCount(goodsMap);
		List list = this.goodsService.getList(goodsMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	
	/**
	 * @author :CYC
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索商品
	 */
	public void searchTryGoods() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 商品id标识
		Map goodsMap = new HashMap();
		// 搜索商品名称
		if (s_goods_name != null && !s_goods_name.equals("")) {
			s_goods_name = URLDecoder.decode(s_goods_name, "UTF-8");
			goodsMap.put("goods_name", s_goods_name);
		}
		// 搜索商品编号
		if (s_goods_no != null && !s_goods_no.equals("")) {
			goodsMap.put("goods_no", s_goods_no);
		}
		// 搜索商家名称
		if (s_cust_name != null && !s_cust_name.equals("")) {
			s_cust_name = URLDecoder.decode(s_cust_name, "UTF-8");
			goodsMap.put("user_name", s_cust_name);
		}
		if (sgis != null && !sgis.equals("")) {
			goodsMap.put("sgis", sgis);
		}
		// 直通车查询所有商品
		 if(ValidateUtil.isRequired(cust_id_flag)||cust_id_flag.equals("undefined")){
			 goodsMap.put("cust_id", this.session_cust_id);
		 }
		//已上架商品
		goodsMap.put("is_up", "0");

		// 商品未删除
		goodsMap.put("is_del", "1");
		
		goodsMap.put("info_state", "1");

		//商品活动状态
		if (!ValidateUtil.isRequired(active_state_flag)) {
			goodsMap.put("active_state", active_state_flag);
		}
		goodsMap = ajaxMap(goodsMap);
		int totalNum = this.goodsService.getCount(goodsMap);
		List list = this.goodsService.getList(goodsMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}

	/**
	 * @author LHY 获取当前会员的自定义分类
	 * @throws IOException
	 * 
	 */
	public String childList() throws IOException {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		String up_pid = request.getParameter("pId");
		Map map = new HashMap();
		if (up_pid != null && !up_pid.equals("")) {
			map.put("parent_cat_id", up_pid);
		}
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			if (!this.session_cust_id.equals("0")) {
				map.put("cust_id", this.session_cust_id);
			}
		}
		List list = this.membercatService.getList(map);
		// 将list转换为json格式
		String jsonStr = GridTreeUtil.getJsonStr(list);
		// 设置响应格式
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		return null;
	}
	public String tJ() throws Exception{
		String[] chb_ids=chb_id.split(",");
		String lab="";
		for (int i=0;i<chb_ids.length;i++){
			goods= this.goodsService.get(chb_ids[i]);
			lab=goods.getLab();
			if(lab==null){
				lab="";
			}
			if(!(lab.indexOf("4")>-1)){
				goods.setLab(lab+",4");
				this.goodsService.update(goods);
				
			}
		}
		this.addActionMessage("批量推荐成功!");
		return list();
	}
		
	public String nTJ() throws Exception{
			String[] chb_ids=chb_id.split(",");
			String lab="";
			for (int i=0;i<chb_ids.length;i++){
				goods= this.goodsService.get(chb_ids[i]);
				lab=goods.getLab();
				if(lab==null){
					lab="";
				}
				if((lab.indexOf(",4")>-1)||(lab.indexOf("4,")>-1)){
					goods.setLab(lab.replace(",4", "").replace("4,", ""));
				}else if ("4".equals(lab)) {
					goods.setLab("");
				}
				this.goodsService.update(goods);
			}
			this.addActionMessage("批量取消推荐成功!");
			return list();
	}
	
	
	public String updateprice() throws Exception{
		String[] chb_ids=chb_id.split(",");
		String lab="";
		List goodsattrlist;
		//hidden_updatetype修改类型  0表示直接改价 1表示公式改价
		if(hidden_updatetype!=null&&"0".equals(hidden_updatetype)){
			for (int i=0;i<chb_ids.length;i++){
				Map mapattr=new HashMap();
				mapattr.put("goods_id", chb_ids[i]);
				goodsattrlist= this.goodsattrService.getList(mapattr);
				if(goodsattrlist!=null&&goodsattrlist.size()>0){
					for(int j=0;j<goodsattrlist.size();j++){
					Map mapvalue=new HashMap();
					mapvalue = (HashMap)goodsattrlist.get(j);
					goodsattr = goodsattrService.get(mapvalue.get("goods_item").toString());
					//销售价修改
				    if(hidden_pricetype!=null&&"0".equals(hidden_pricetype)){
				    	if(hidden_nowprice!=null&&!"".equals(hidden_nowprice))
				    	goodsattr.setSale_price(Double.parseDouble(hidden_nowprice));
				    //原价修改
				    }else if(hidden_pricetype!=null&&"1".equals(hidden_pricetype)){
				    	if(hidden_nowprice!=null&&!"".equals(hidden_nowprice))
					    	goodsattr.setMarket_price(Double.parseDouble(hidden_nowprice));
				    }
					this.goodsattrService.update(goodsattr);
					}
				}
			}
		}else if(hidden_updatetype!=null&&"1".equals(hidden_updatetype)){
			for (int i=0;i<chb_ids.length;i++){
				    Double price=0.0;
					Map mapattr=new HashMap();
					mapattr.put("goods_id", chb_ids[i]);
					goodsattrlist= this.goodsattrService.getList(mapattr);
					if(goodsattrlist!=null&&goodsattrlist.size()>0){
						for(int j=0;j<goodsattrlist.size();j++){
						Map mapvalue=new HashMap();
						mapvalue = (HashMap)goodsattrlist.get(j);
						goodsattr = goodsattrService.get(mapvalue.get("goods_item").toString());
				    	if(hidden_pricevalue!=null&&!"".equals(hidden_pricevalue)){
				    		//操作销售价
				    		if(hidden_pricetype2!=null&&"0".equals(hidden_pricetype2)){
				    			if(hidden_mouth!=null&&"0".equals(hidden_mouth)){
				    				price = goodsattr.getSale_price() + Double.parseDouble(hidden_pricevalue);
				    			}else if(hidden_mouth!=null&&"1".equals(hidden_mouth)){
				    				price = goodsattr.getSale_price() - Double.parseDouble(hidden_pricevalue);
				    			}else if(hidden_mouth!=null&&"2".equals(hidden_mouth)){
				    				price = goodsattr.getSale_price() * Double.parseDouble(hidden_pricevalue);
				    			}else if(hidden_mouth!=null&&"3".equals(hidden_mouth)){
				    				price = goodsattr.getSale_price() / Double.parseDouble(hidden_pricevalue);
				    			}
				    			//销售价修改
							    if(hidden_pricetype!=null&&"0".equals(hidden_pricetype)){
				    			   goodsattr.setSale_price(price);
							    }else if(hidden_pricetype!=null&&"1".equals(hidden_pricetype)){
							    	goodsattr.setMarket_price(price);
							    }
				    	    //操作原价
				    		}else if(hidden_pricetype2!=null&&"1".equals(hidden_pricetype2)){
					    			if(hidden_mouth!=null&&"0".equals(hidden_mouth)){
					    				price = goodsattr.getMarket_price() + Double.parseDouble(hidden_pricevalue);
					    			}else if(hidden_mouth!=null&&"1".equals(hidden_mouth)){
					    				price = goodsattr.getMarket_price()  - Double.parseDouble(hidden_pricevalue);
					    			}else if(hidden_mouth!=null&&"2".equals(hidden_mouth)){
					    				price = goodsattr.getMarket_price() * Double.parseDouble(hidden_pricevalue);
					    			}else if(hidden_mouth!=null&&"3".equals(hidden_mouth)){
					    				price = goodsattr.getMarket_price() / Double.parseDouble(hidden_pricevalue);
					    			}    
					    			//销售价修改
								    if(hidden_pricetype!=null&&"0".equals(hidden_pricetype)){
					    			   goodsattr.setSale_price(price);
								    }else if(hidden_pricetype!=null&&"1".equals(hidden_pricetype)){
								    	goodsattr.setMarket_price(price);
								    }
				    		   }
				    	}
				    	this.goodsattrService.update(goodsattr);
			    		}
						
			    	}
			}
		}
		//public String hidden_pricetype2;//公式价格类型2
		//public String hidden_mouth;//设置加减乘除
		//public String hidden_pricevalue;//公式操作值
			
		
		this.addActionMessage("统一改价成功!");
		return list();
		
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
	/**
	 * @Method Description : 批量替换商品详细介绍的
	 * @author: HXK
	 * @date : Jan 22, 2015 2:42:34 PM
	 * @param 
	 * @return return_type
	 */
	 public   void replaceGoodsImg(){
	 		List gList=new ArrayList();
	 		Map gMap=new HashMap();
	 		gList=goodsService.getList(gMap);
	 		if(gList!=null&&gList.size()>0){
	 			int kk=0;
	 			String gString="";
	 			for(int i=0;i<gList.size();i++){
	 				HashMap hMap=(HashMap)gList.get(i);
	 				Goods goodss=new Goods();
	 				goodss=goodsService.get(hMap.get("goods_id").toString());
	 				String contentString=hMap.get("goods_detail").toString();
	 				System.out.println("请求商品id="+hMap.get("goods_id").toString()+"更新成功");
	 				//if(contentString.contains("http://www.epff.cc/")){
	 				//	contentString=contentString.replace("http://www.epff.cc/", "http://www.epff.cc/");
	 	 			//	goodss.setGoods_detail(contentString);
	 	 			//	goodsService.update(goodss);
	 	 			//	System.out.println("已经替换商品id="+hMap.get("goods_id").toString()+"更新成功");
	 	 			//	kk++;
	 	 			//	gString+=hMap.get("goods_id").toString()+",";
	 				//}
	 				
	 			}
	 			System.out.println("成功替换商品为"+kk+"个"+"商品变为为："+gString);
	 			
	 		}
	 	}
	
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX获取品牌
	 */
	public void ajaxbrandalllist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 获取商品品牌
		getALLBrandList();
		goodsbrandList = ToolsFuc.replaceList(goodsbrandList, "");
		String jsonStr = JsonUtil.list2json(goodsbrandList);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索商品
	 */
	public void ajaxtoparealist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		getTopArea();
		String jsonStr = JsonUtil.list2json(areaList);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索商品
	 */
	public void ajaxtradewaylist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		//获取贸易方式
		getTradeWay();
		depotList = ToolsFuc.replaceList(depotList, "");
		String jsonStr = JsonUtil.list2json(depotList);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索商品
	 */
	public void ajaxactivelist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		getSaleGoods();
		String jsonStr = JsonUtil.list2json(salegoodsList);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	/**
	 * @Method Description :
	 * @author : XBY
	 * @throws Exception
	 * @date : Sep 16, 2014 1:27:30 PM
	 */
	public String importGoods() throws Exception {
		if (iname == null || "".equals(iname)) {
			this.addActionMessage("请选择要导入的文件!");
		} else {
			String rootPath = PropertiesUtil.getRootpath();// 获取项目的根目录
			iname = rootPath + "" + iname;
			//先验证数据格式,返回值不为空，表示有数据格式有问题
			String checkTip=goodsService.importCheckGoods(iname);
			if(!ValidateUtil.isRequired(checkTip)){
				this.addActionMessage(checkTip);
			}else {
				boolean flag = this.goodsService.importGoods(iname);
				if (flag) {
					this.addActionMessage("导入成功!");
				} else {
					this.addActionMessage("导入失败!请检查文件格式。");
				}
			}
		}
		return list();
	}
	
	/**
	 * @Method Description :跨境通导入修改商品
	 * @author : XBY
	 * @throws Exception
	 * @date : Sep 16, 2014 1:27:30 PM
	 */
	public String importKtjGoods() throws Exception {
		if (iname == null || "".equals(iname)) {
			this.addActionMessage("请选择要导入的文件!");
		} else {
			String rootPath = PropertiesUtil.getRootpath();// 获取项目的根目录
			iname = rootPath + "" + iname;
			boolean flag = this.goodsService.importKtjGoods(iname);
			if (flag) {
				this.addActionMessage("导入成功!");
			} else {
				this.addActionMessage("导入失败!请检查文件格式。");
			}

		}
		return list();
	}
	
	
	/**
	 * KJT导出商品
	 * @return
	 * @throws Exception
	 */
	public String exportKJTGoods() throws Exception {
		Map pageMap = new HashMap();
		//搜索条件
		commonMapSearchGoodsList(pageMap);
		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}else if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		pageMap = commonList(pageMap);
		
		if(!ValidateUtil.isRequired(sgis)) {
			pageMap.put("sgis", sgis);
		}
		// 上架商品: is_up: 0是 1否
		pageMap.put("is_up", "0");
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "1");
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		gCommparaList("goods_lable");
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		if(this.goodsService.exportGoods(goodsList, getResponse())) {
		   this.addActionMessage("导出商品成功！");	
		}else {
		   this.addActionMessage("导出商品失败！");
		}
		
		return goUrl(INDEXLIST);
	}
	
	
	/**
	 * 导出商品
	 * @return
	 * @throws Exception
	 */
	public String exportGoods() throws Exception {
		Map pageMap = new HashMap();
		//搜索条件
		commonMapSearchGoodsList(pageMap);
		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}else if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		pageMap = commonList(pageMap);
		
		if(!ValidateUtil.isRequired(sgis)) {
			pageMap.put("sgis", sgis);
		}
		// 上架商品: is_up: 0是 1否
		pageMap.put("is_up", "0");
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "1");
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		gCommparaList("goods_lable");
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		if(this.goodsService.exprotExcel(goodsList, getResponse())) {
		   this.addActionMessage("导出商品成功！");	
		}else {
		   this.addActionMessage("导出商品失败！");
		}
		
		return goUrl(INDEXLIST);
	}
	
	
	/**
	 * 方法描述：根据搜索参加商品促销的商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saleList() throws Exception {
		//商品促销活动
		getSaleGoods();
		if(salegoodsList != null && salegoodsList.size() > 0) {
		Map pageMap = new HashMap();
		//搜索条件
		commonMapSearchGoodsList(pageMap);
		// 审核状态：1表示审核通过，3表示审核禁用,0表示未审核，2表示审核未通过
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state_in", "1,3");
		}else if(info_state_s!=null&&!"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		pageMap = commonList(pageMap);
		// 上架商品: is_up: 0是 1否
		pageMap.put("is_up", "0");
		//逻辑删除商品：is_del:0是 1否
		pageMap.put("is_del", "1");
		//查找促销活动商品
		pageMap = dealSale(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.goodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsList = this.goodsService.getList(pageMap);
		goodsList = ToolsFuc.replaceList(goodsList, "");
		//商品促销活动
		goodsList = SalegoodsFuc.replaceSalegoodsList(goodsList, "0", "");
		}
		return goUrl("saleindex");
	}
	/**
	 * 方法描述：根据搜索有参加赠送优惠券的商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String couponGoodsList() throws Exception {
		//商品促销活动
		getCouponGoods();
		if(couponList != null && couponList.size() > 0) {
			
			String cgoodsstr="";
			for(int i=0;i<couponList.size();i++){
				Map cMap = new HashMap();
				cMap=(HashMap)couponList.get(i);
				cgoodsstr+=cMap.get("term");
			}
			if(!ValidateUtil.isRequired(cgoodsstr)){
				cgoodsstr=cgoodsstr.trim().replaceAll(" ", "");
			}
			Map pageMap = new HashMap();
			// 上架商品: is_up: 0是 1否
			pageMap.put("is_up", "0");
			//逻辑删除商品：is_del:0是 1否
			pageMap.put("is_del", "1");
			//查找促销活动商品
			pageMap.put("sgis", cgoodsstr);
			if(goods_no_s!=null&&!"".equals(goods_no_s)){
				pageMap.put("goods_no", goods_no_s);
			}
			if(goods_name_s!=null&&!"".equals(goods_name_s)){
				goods_name_s=goods_name_s.trim();
				pageMap.put("goods_name", goods_name_s);
			}
			// 根据页面提交的条件找出信息总数
			int count = this.goodsService.getCount(pageMap);
			// 分页插件
			pageMap = super.pageTool(count, pageMap);
			goodsList = this.goodsService.getList(pageMap);
			goodsList = ToolsFuc.replaceList(goodsList, "");
			goodsList=couponService.getCouponByGoodsList(goodsList);
		}
		return goUrl("coupongoodsindex");
	}
	/**
	 * 
	 * @param pageMap
	 * @return
	 */
	private Map dealSale(Map pageMap){
		//商品促销活动
		getSaleGoods();
		String slae_str = "";
		if(salegoodsList != null && salegoodsList.size() > 0) {
			for(int i = 0; i < salegoodsList.size(); i ++) {
				Map saleMap = (HashMap) salegoodsList.get(i);
				//获取促销ID
				String sale_id = saleMap.get("sale_id").toString();
				if(!ValidateUtil.isRequired(sale_id)) {
					Salegoods salegoods = this.salegoodsService.get(sale_id);
					//指定商品
					if(salegoods.getTerm_state().equals("1")){
						if(ValidateUtil.isRequired(slae_str)) {
							slae_str = " g.goods_id in ("+salegoods.getTerm()+")";
						}else {
							slae_str += " OR g.goods_id in ("+salegoods.getTerm()+")";
						}
					}else if(salegoods.getTerm_state().equals("2")){//商品分类
						if(ValidateUtil.isRequired(slae_str)) {
							slae_str = " INSTR(cat_attr,'"+salegoods.getTerm().toString()+"') > 0";
						}else {
							slae_str += " OR INSTR(cat_attr,'"+salegoods.getTerm().toString()+"') > 0";
						}						
					}else if(salegoods.getTerm_state().equals("4")){//指定的商品总价满X,对商品优惠
						if(ValidateUtil.isRequired(slae_str)) {
							slae_str = " g.goods_id in ("+salegoods.getTerm()+")";
						}else {
							slae_str += " OR g.goods_id in ("+salegoods.getTerm()+")";
						}
					}				
					
				}
			}

		}
		
		if(!ValidateUtil.isRequired(slae_str)) {
			pageMap.put("sale_str", slae_str);
		}
		
		return pageMap;
	}
	/**
	 * @Method Description :根据商品ID 查找商品楼层标签是否存在该商品
	 * @author: 胡惜坤
	 * @date : Mar 15, 2016 9:00:58 AM
	 * @param goods_id：商品ID串
	 * @return 商品编号
	 */
	public void rgGoodsInFloor() throws IOException {
		String jsonStr="";
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String goods_id=request.getParameter("goods_id");
		if(!ValidateUtil.isRequired(goods_id)){
			jsonStr=goodsService.rgGoodsInFloor(goods_id);
		}
		out.write(jsonStr);
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

	public Selfspecvalue getSelfspecvalue() {
		return selfspecvalue;
	}

	public void setSelfspecvalue(Selfspecvalue selfspecvalue) {
		this.selfspecvalue = selfspecvalue;
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

	public Selfparavalue getSelfparavalue() {
		return selfparavalue;
	}

	public void setSelfparavalue(Selfparavalue selfparavalue) {
		this.selfparavalue = selfparavalue;
	}
}
