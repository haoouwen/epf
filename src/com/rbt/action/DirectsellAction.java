/*
 
 * Package:com.rbt.action
 * FileName: DirectsellAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Directladder;
import com.rbt.model.Directsell;
import com.rbt.model.Goods;
import com.rbt.service.IDirectladderService;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IDirectsellService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsattrService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 预售商品action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:27:41 CST 2014
 */
@Controller
public class DirectsellAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	private Directsell directsell;
	public CategoryFuc categoryFuc;
	private Goods goods;
	public Directladder directladder;

	/** *****************业务层接口*************** */
	@Autowired
	private IDirectsellService directsellService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IDirectladderService directladderService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsattrService goodsattrService;

	/** *******************集合***************** */
	public List directsellList;// 预售商品信息集合
	public List labList;// 阶梯价格列表
	public List directladderList;// 预售阶梯价格信息集合

	/** *******************字段***************** */
	public String groupgoods_sortno_id;// 批量排序
	public String isort_no;
	public String reason;// 操作流理由
	public String info_state_s;// 审核状态
	public String goods_name;// 商品名称
	public String cat_name;// 转化为具体的分类
	public String cust_name_s;// 会员名称
	public String lownums;// 阶梯价格之购买数量下限
	public String st_put_date_s;// 开始时间
	public String ladprices;// 阶梯价格之对应价格
	public String en_put_date_s;
	public String st_en_date_s;// 结束时间
	public String en_en_date_s;
	public String sale_title_s;// 预售标题
	public String hidden_goodsname;// 商品名称
	public String hidden_goodssaleprice;// 商品销售价格
	public String hidden_goodsmarketprice;// 商品市场价格
	public String is_update;// 是否更新
	public String directprice;
	public String directsellStock;// 预设库存
	public String directsellMaxbuy;// 预设预定数量
	public String stock_maxbuy;
	public String deposit_num;// 预定人数
	public String final_num;// 付完尾款的人数
	public String direct_stock;// 预售商品总库存
	public String old_goods_id;// 旧商品标识
	public String pregoodsCount;// 记录预售商品总数
	public String str_pregoods_id;// 预售商品的ID串
	public String str_pregoods_trade_id;// 预售商品主键ID串
	public String preCatAttr;
	public String stock_deposit;
	public String stock_deposit_num;
	public String addorupdate;

	/**
	 * 方法描述：返回新增预售商品页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:06:29 PM
	 * @Method Description :新增和更新的校验
	 */
	public String commonCheck() throws Exception {
		// 保存分类cat_attr
		selectCat();
		this.directsell.setCat_attr(cat_attr);
		this.directsell.setUser_id(this.session_user_id);
		if (directsell.getGoods_id() == null
				|| directsell.getGoods_id().equals("")) {
			this.addFieldError("directsell.goods_id", "请添加商品！");
		}
		if (directsell.getStock() != null
				&& !directsell.getStock().trim().equals("")) {
			if (Double.parseDouble(directsell.getStock()) <= 0) {
				this.addFieldError("directsell.stock", "请输入0以上库存！");
			}
		} else {
			this.addFieldError("directsell.stock", "库存不能为空");
		}

		if (!ValidateUtil.isRequired(directsell.getMax_buy())) {
			if (Double.parseDouble(directsell.getMax_buy()) <= 0) {
				this.addFieldError("directsell.max_buy", "请输入0以上预定数量！");
			}
			if(addorupdate.equals("1")){
				if ((Double.parseDouble(directsell.getStock()) < Double
						.parseDouble(directsell.getMax_buy()))) {
					this.addFieldError("directsell.max_buy", "预定数量不能大于实际库存！");
				}
			}else{
				if ((Double.parseDouble(directsell.getStock_maxbuy()) < Double
						.parseDouble(directsell.getMax_buy()))) {
					this.addFieldError("directsell.max_buy", "预定数量不能大于实际库存！");
				}
			}
			
		}
		if (lownums != null && !lownums.equals("") && ladprices != null
				&& !ladprices.equals("")) {
			String[] lownum = lownums.split(",");
			String[] ladprice = ladprices.split(",");
			for (int i = 0; i < lownum.length; i++) {
				if (lownum[i].trim() != null && !lownum[i].equals("")
						&& ladprice[i].trim() != null
						&& !ladprice[i].equals("")) {
					// 校验输入的金额是否大于0
					double dladprice = Double.parseDouble(ladprice[i]);
					if (ladprice[i].equals("") || ladprice[i] == null
							|| dladprice == 0) {
						this.addFieldError("ladprices", "请输入0以上阶梯价金额！");
					}
				}
			}
		} else {
			this.addFieldError("lownums", "预售价格必填！");
		}
		// 判断是否新增页面
		String info_state = directsell.getInfo_state();
		if (is_update != null) {
			// 预售商品数据操作流
			if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
				if (info_state.equals("1")) {
					reason = "审核通过状态";
				} else if (info_state.equals("3")) {
					if (reason.equals("")) {
						this.addFieldError("directsell.info_state",
								"您还未填写禁用理由！");
					}
				} else {
					this.addFieldError("directsell.info_state", "请先选择审核状态");
				}
			}
		}
		// 判断会员发布预售商品是否审核
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if (cfg_IsAuditYuShou.equals("0")) {
				info_state = "0";
			} else {
				info_state = "1";
			}
		}

		// 判断定金不能大于预售价格 hong注释
		if (directsell.getEarnest() != null
				&& !ValidateUtil.isRmb(directsell.getEarnest().toString())
				&& !ValidateUtil.isRmb(ladprices)) {
			if (Double.valueOf(directsell.getEarnest()) > Double
					.valueOf(ladprices)) {
				this.addFieldError("directsell.earnest", "定金不能大于预售价格！");
			}
		}
		String goods_price = getPrice(directsell.getGoods_id(), "");
		if (!ValidateUtil.isRequired(ladprices)) {
			if (Double.valueOf(ladprices) > Double.valueOf(goods_price)) {
				this.addFieldError("lownums", "预售价格不能大于商品市场价格！");
			}
		}
		if (ValidateUtil.isRequired(directsell.getSort())) {
			directsell.setSort("0");
		}
		if(!validateFactory.isRequired(directsell.getIs_limit())&&"0".equals(directsell.getIs_limit())){
			if(validateFactory.isRequired(directsell.getLimit_num())){
				this.addFieldError("directsell.limit_num", "请填限购数量！");
			}
			
		}
		if(!validateFactory.isRequired(directsell.getIs_limit())&&"1".equals(directsell.getIs_limit())){
			directsell.setLimit_num("10000");
		}
		directsell.setInfo_state(info_state);
		return info_state;
	}

	public String getPrice(String goods_id, String spec_id) {
		String new_price = "0";
		Map priMap = new HashMap();
		priMap.put("goods_id", goods_id.replace(" ", ""));
		priMap.put("spec_id", spec_id.replace(" ", ""));
		List goodsList = this.goodsattrService.getWebList(priMap);
		if (goodsList != null && goodsList.size() > 0) {
			Map pMap = (Map) goodsList.get(0);
			if (pMap.get("market_price") != null) {
				new_price = pMap.get("market_price").toString();
			}
		}
		return new_price;
	}

	/**
	 * 方法描述：新增预售商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String info_state = commonCheck();
		// 执行校验
		super.commonValidateField(directsell);
		if (super.ifvalidatepass) {
			return add();
		}
		directsell.setCust_id(this.session_cust_id);
		directsellStock = directsell.getStock();
		directsell.setStock_maxbuy(directsellStock);
		directsell.setSaleprice(Double.valueOf(ladprices));
		directsell.setIs_del("0");//is_del: 0:正常预售商品列表 1：收回站列表（预售前或者预售结束后可删除进入回收站）
		String id = this.directsellService.insertDirectsell(directsell,
				lownums, ladprices);
		this.addActionMessage("新增预售商品成功！");
		directsell = this.directsellService.get(id);
		goods = goodsService.get(directsell.getGoods_id());
		goods.setActive_state("4");
		goodsService.update(goods);
		// 预售商品数据操作流
		addAuditRecord(id, "directsell", info_state, "新增预售商品");
		this.directsell = null;
		this.hidden_goodsname = null;
		this.hidden_goodssaleprice = null;
		this.hidden_goodsmarketprice = null;
		this.ladprices = null;
		this.catIdStr = "1111111111";
		return INPUT;
	}

	/**
	 * 方法描述：修改预售商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = directsell.getTrade_id();
		String info_state = commonCheck();
		super.commonValidateField(directsell);
		if (super.ifvalidatepass) {
			return view();
		}

		// 积分商品数据操作流
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			addAuditRecord(id, "directsell", info_state, "修改预售商品");
		} else {
			if (info_state.equals("1")) {
				reason = "审核通过状态";
			}
			addAuditRecord(id, "directsell", info_state, reason);
		}
		// 预售商品更新成功往阶梯价格表更新对应数据
		directsell.setSaleprice(Double.valueOf(ladprices));
		this.directsellService.updateDirectladder(id, lownums, ladprices);
		this.directsellService.update(directsell);
		if (!old_goods_id.equals(directsell.getGoods_id())) {
			goods = this.goodsService.get(old_goods_id);
			goods.setActive_state("0");
			this.goodsService.update(goods);
			goods = this.goodsService.get(directsell.getGoods_id());
			goods.setActive_state("4");
			this.goodsService.update(goods);
		}
		this.addActionMessage("修改预售商品信息成功！");
		return list();
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:05:54 PM
	 * @Method Description :公用删除
	 */
	private void commonDelete() {
		boolean flag = this.directsellService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除预售商品信息成功");
		} else {
			this.addActionMessage("删除预售商品信息失败");
		}

	}

	/**
	 * 方法描述：删除预售商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return prerecyclelist();
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:05:37 PM
	 * @Method Description :删除预售商品审核息信息
	 */
	public String auditDelete() throws Exception {
		commonDelete();
		return auditList();
	}

	/**
	 * @author Administrator QJY
	 * @Method Description :批量逻辑删除预售商品信息
	 * @return list()
	 * @throws Exception
	 */
	public String deletetorecycle() throws Exception{
		boolean flag = this.directsellService.deletetorecycle(chb_id, "1");//逻辑删除
		if (flag) {
			this.addActionMessage("预售商品删除成功！");
		} else {
			this.addActionMessage("预售商品删除失败!");
		}
		return list();
	}
	
	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:05:26 PM
	 * @Method Description :搜索条件集合，并找出符合条数、分页及列表
	 */
	private void commonList(Map pageMap) {
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 搜索状态
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state_s", info_state_s);
		}
		// 获取搜索的分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (st_put_date_s != null && !st_put_date_s.equals("")) {
			pageMap.put("st_put_date", st_put_date_s);
		}
		if (en_put_date_s != null && !en_put_date_s.equals("")) {
			pageMap.put("en_put_date", en_put_date_s);
		}
		if (st_en_date_s != null && !st_en_date_s.equals("")) {
			pageMap.put("st_en_date", st_en_date_s);
		}
		if (en_en_date_s != null && !en_en_date_s.equals("")) {
			pageMap.put("en_en_date", en_en_date_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (sale_title_s != null && !sale_title_s.equals("")) {
			pageMap.put("sale_title", sale_title_s);
		}
		pageMap.put("defaultSort", "defaultSort");

		// 根据页面提交的条件找出信息总数
		int count = this.directsellService.getCount(pageMap);
		pregoodsCount = String.valueOf(count);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		directsellList = this.directsellService.getList(pageMap);
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:05:14 PM
	 * @Method Description :根据搜索条件列出信息列表
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state", "1,3");
		}
		// 加入搜索
		pageMap.put("is_del", "0");
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @author ：QJY
	 * @date: 
	 * @return
	 * @throws Exception
	 */
	public String prerecyclelist() throws Exception{
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state", "1,3");
		}
		// 加入搜索
		pageMap.put("is_del", "1");
		commonList(pageMap);
		return goUrl("prerecyclelist");
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:05:01 PM
	 * @Method Description :运营商审核列表
	 */
	@SuppressWarnings("unchecked")
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("info_state", "0,2");
		}
		// 加入搜索
		commonList(pageMap);
		return goUrl("auditindex");
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:04:52 PM
	 * @Method Description :加载阶梯价格列表
	 */
	public void serchlablist(String id) {
		// 加载阶梯价格
		Map labMap = new HashMap();
		labMap.put("direct_id", id);
		labList = this.directladderService.getList(labMap);
	}

	/**
	 * @Method Description :实时获取预定信息数据
	 * @author : QJY
	 * @throws IOException
	 * @date : Nov 26, 2014 9:40:34 AM
	 */
	/*
	 * public void getPresellStatistics() throws IOException {
	 * HttpServletRequest request = getRequest(); HttpServletResponse response =
	 * getResponse();
	 * 
	 * request.setCharacterEncoding("UTF-8");
	 * response.setCharacterEncoding("UTF-8"); PrintWriter out =
	 * response.getWriter(); Integer count = 0,prestock =0;
	 * 
	 * if(!ValidateUtil.isRequired(request.getParameter("str_pregoods_id"))){
	 * str_pregoods_id = request.getParameter("str_pregoods_id"); }
	 * if(!ValidateUtil.isRequired(request.getParameter("pregoodsCount"))){
	 * pregoodsCount = request.getParameter("pregoodsCount"); count =
	 * Integer.valueOf(pregoodsCount); }
	 * if(!ValidateUtil.isRequired(request.getParameter("str_pregoods_trade_id"))){
	 * str_pregoods_trade_id = request.getParameter("str_pregoods_trade_id"); }
	 * 
	 * //对预售商品记录总数循环，得到各个ID值,根据ID值查找统计出该预售商品的预定，库存信息 List<HashMap<String,String>>
	 * countList = new ArrayList<HashMap<String,String>>(); if (count != null &&
	 * count != 0) { String pregoods_id_str_strs[] = str_pregoods_id.split("_");
	 * String pregoods_trade_id_strs[] = str_pregoods_trade_id.split("_"); for
	 * (int i = 0; i < count; i++) { if(pregoods_id_str_strs[i] != null){
	 * 
	 * HashMap countMap = new HashMap(); countMap.put("pregoods_id",
	 * pregoods_id_str_strs[i]);
	 * 
	 * directsell = this.directsellService.get(pregoods_trade_id_strs[i]);
	 * prestock = Integer.valueOf(directsell.getStock()); // 已付定金商品数量 HashMap
	 * depositMap = new HashMap(); depositMap.put("direct_id",
	 * pregoods_trade_id_strs[i]); depositMap.put("pay_state", "1"); deposit_num =
	 * String.valueOf(directorderdetailService.getCount(depositMap));
	 * countMap.put("deposit_num", deposit_num);//将已付定金的商品数量放入 // 付完尾款的商品数量
	 * HashMap finalMap = new HashMap(); finalMap.put("direct_id",
	 * pregoods_trade_id_strs[i]); finalMap.put("pay_state", "2"); final_num =
	 * String.valueOf(directorderdetailService.getCount(finalMap));
	 * countMap.put("final_num", final_num);//将付完尾款的商品数量放入
	 * 
	 * countList.add(countMap);//把统计Map放入到List中 } } }
	 * 
	 * String count_json = JsonUtil.list2json(countList);
	 * 
	 * out.print(count_json); }
	 */
	
	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:04:40 PM
	 * @Method Description :公用跳转更新或审核页面
	 */
	private boolean commonView(String id) {
		if (id == null || id.equals("")) {
			directsell = new Directsell();
			return true;
		} else {
			// 加载阶梯价格
			serchlablist(id);
			// 回选分类
			viewCat(directsell.getCat_attr());
			// 获取分类的中文名称
			preCatAttr = CategoryFuc.getCateNameByMap(directsell.getCat_attr());

			// 获取审核列表
			searchAuditList(id, "directsell");
			directsell = this.directsellService.get(id);
			if (directsell.getGoods_id() != null
					&& !directsell.getGoods_id().equals("")) {
				goods = this.goodsService
						.getByPkNoDel(directsell.getGoods_id());

				// 取得预售商品总库存
				direct_stock = directsell.getStock();

				if (goods != null && goods.getGoods_name() != null) {
					hidden_goodsname = goods.getGoods_name();
					hidden_goodssaleprice = goods.getMin_sale_price();
					hidden_goodsmarketprice = goods.getGoods_market_price();
					return false;
				} else {
					hidden_goodsname = null;
					hidden_goodssaleprice = null;
					hidden_goodsmarketprice = null;
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 方法描述：根据主键找出预售商品信息（预售前，可以修改）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String preview() throws Exception {
		String id = this.directsell.getTrade_id();
		if (commonView(id)) {
			return list();
		}
		return goUrl("preupdate");
	}

	/**
	 * 方法描述：根据主键找出预售商品信息（预售中，部分修改）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.directsell.getTrade_id();
		if (commonView(id)) {
			return list();
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出预售商品信息（预售结束，查看）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String browse() throws Exception {
		String id = this.directsell.getTrade_id();
		if (commonView(id)) {
			return list();
		}
		return goUrl("browse");
	}

	/**
	 * 新增库存和预售数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addnum() throws Exception {
		String id = this.directsell.getTrade_id();
		if (!ValidateUtil.isRequired(id)) {
			directsell = directsellService.get(id);
			if (!ValidateUtil.isRequired(stock_deposit_num)) {
				if ("1".equals(stock_deposit)) {// 增加库存(增加实际库存和预售的库存)
					Integer newStock = Integer.valueOf(directsell.getStock())
							+ Integer.valueOf(stock_deposit_num);
					Integer newStock_maxbuy = Integer.valueOf(directsell
							.getStock_maxbuy())
							+ Integer.valueOf(stock_deposit_num);
					directsell.setStock(newStock.toString());
					directsell.setStock_maxbuy(newStock_maxbuy.toString());
					directsellService.update(directsell);
				} else {// 增加预售数量，同时增加库存
					Integer newMax_buy = Integer.valueOf(directsell
							.getMax_buy())
							+ Integer.valueOf(stock_deposit_num);
					Integer newStock = Integer.valueOf(directsell.getStock())
							+ Integer.valueOf(stock_deposit_num);
					Integer newStock_maxbuy = Integer.valueOf(directsell
							.getStock_maxbuy())
							+ Integer.valueOf(stock_deposit_num);
					directsell.setMax_buy(newMax_buy.toString());
					directsell.setStock(newStock.toString());
					directsell.setStock_maxbuy(newStock_maxbuy.toString());
					directsellService.update(directsell);
				}
				this.addActionMessage("新增预售商品数量成功");
			}
		}
		return view();
	}

	/**
	 * 方法描述：跳转到审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.directsell.getTrade_id();
		if (commonView(id)) {
			return list();
		}
		// 转化为具体的分类
		cat_name = categoryFuc.getCateNameByMap(directsell.getCat_attr());
		// 获取预售价格
		HashMap map = new HashMap();
		map.put("direct_id", id);
		List directladderList = directladderService.getList(map);
		if (directladderList != null && directladderList.size() > 0) {
			HashMap mapvalue = (HashMap) directladderList.get(0);
			directprice = mapvalue.get("price").toString();
		}
		return goUrl("audit");
	}

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:13:57 PM
	 * @Method Description :审核预售商品
	 */
	public String auditState() throws Exception {
		String id = this.directsell.getTrade_id();
		String info_state = this.directsell.getInfo_state();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if (info_state.equals("1")) {
			reason = "审核通过";
		} else if (info_state.equals("2")) {
			if (reason.equals("")) {
				this.addFieldError("directsell.info_state", "您还未填写审核不通过理由！");
			}
		} else {
			this.addFieldError("directsell.info_state", "审核状态不能为空！");
		}
		// 验证数据
		if (super.ifvalidatepass) {
			return audit();
		}
		// 获取数据库对象
		Directsell gd = this.directsellService.get(id);
		// 设置状态值
		gd.setInfo_state(info_state);
		// 更新数据库供应列表
		this.directsellService.update(gd);
		// 新增审核预售商品数据操作流
		addAuditRecord(id, "directsell", info_state, reason);
		this.addActionMessage("审核预售商品成功");
		return auditList();
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : Apr 16, 2014 8:19:35 PM
	 */
	private void commonSort() {
		boolean flag = this.directsellService.updateSort("trade_id", "sort",
				chb_id, sort_val);
		if (flag) {
			this.addActionMessage("预售商品排序成功");
		} else {
			this.addActionMessage("预售商品排序失败");
		}
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
	 * 方法描述：审核批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditUpdateSort() throws Exception {
		commonSort();
		this.addActionMessage("预售商品排序成功");
		return auditList();
	}

	/**
	 * @return the directsell
	 */
	public Directsell getDirectsell() {
		return directsell;
	}

	/**
	 * @param Directsell
	 *            the directsell to set
	 */
	public void setDirectsell(Directsell directsell) {
		this.directsell = directsell;
	}

	/**
	 * @return the DirectsellList
	 */
	public List getDirectsellList() {
		return directsellList;
	}

	/**
	 * @param directsellList
	 *            the DirectsellList to set
	 */
	public void setDirectsellList(List directsellList) {
		this.directsellList = directsellList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (directsell == null) {
			directsell = new Directsell();
		}
		String id = this.directsell.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			directsell = this.directsellService.get(id);
		}
	}

}
