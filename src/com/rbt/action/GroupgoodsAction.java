/*
 
 * Package:com.rbt.action
 * FileName: GroupgoodsAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Goods;
import com.rbt.model.Groupgoods;
import com.rbt.model.Memberfund;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGroupgoodsService;
import com.rbt.service.IGroupladderService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.ISysfundService;
import com.opensymphony.xwork2.Preparable;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 团购商品信息action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Mar 28 14:55:26 CST 2014
 */
@Controller
public class GroupgoodsAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	public CategoryFuc categoryFuc;
	private Groupgoods groupgoods;
	private Goods goods;
	private Memberfund memberfund;

	/** *****************业务层接口*************** */
	@Autowired
	private IGroupladderService groupladderService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGroupgoodsService groupgoodsService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	private IFundhistoryService fundhistoryService;
	@Autowired
	private ISysfundService sysfundService;

	/** *******************集合***************** */
	public List labList;// 阶梯价格列表
	public List groupgoodsList;// 团购商品信息信息集合
	public List groupladderList;// 团购阶梯价格信息集合

	/** *******************字段***************** */
	public String groupgoods_sortno_id;// 批量排序
	public String isort_no;// 排序
	public String reason;// 操作流理由
	public String info_state_s;// 审核状态
	public String goods_name;// 商品名称
	public String cat_name;// 转化为具体的分类
	public String cust_name_s;// 会员名称
	public String shot;// 一口价
	public String lownums;// 阶梯价格之购买数量下限
	public String ladprices;// 阶梯价格之对应价格
	public String st_put_date_s;// 开始时间
	public String en_put_date_s;
	public String st_en_date_s;// 结束时间
	public String en_en_date_s;
	public String group_title_s;// 团购标题
	public String hidden_goodsname;// 商品名称
	public String is_update;// 是否更新
	public String goods_id_flag;// 标记团购的商品编号，用于修改时判断商品是否要去修改商品活动状态
	public String min_sale_price;// 商品价格
    public String total_stock;//商品库存
	/**
	 * 方法描述：返回新增团购商品信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * @Method Description :新增和更新的校验
	 * @author : HZX
	 * @throws Exception
	 * @date : Apr 15, 2014 8:56:00 PM
	 */
	public String commonCheck() throws Exception {
		// 设置list_img
		String list_img;
		String img_paths = groupgoods.getImg_path();
		if (!ValidateUtil.isRequired(img_paths)) {
			list_img = judgeList_img(img_paths);
			if (!ValidateUtil.isRequired(list_img)) {
				this.groupgoods.setList_img(list_img);
			}
		}
		// 保存分类cat_attr
		selectCat();
		this.groupgoods.setCat_attr(cat_attr);
		this.groupgoods.setUser_id(this.session_user_id);
		if (groupgoods.getGoods_id() == null
				|| groupgoods.getGoods_id().equals("")) {
			this.addFieldError("groupgoods.goods_id", "请添加商品！");
		}
		if (groupgoods.getBond() == null || groupgoods.getBond() == 0) {
			this.addFieldError("groupgoods.bond", "请输入0以上金额！");
		}
		if (groupgoods.getCust_maxbuy() != null
				&& !groupgoods.getCust_maxbuy().trim().equals("")) {
			if (Integer.parseInt(groupgoods.getCust_maxbuy()) == 0) {
				this.addFieldError("groupgoods.cust_maxbuy", "请输入0以上同一会员购买上限！");
			}else if(Integer.parseInt(groupgoods.getCust_maxbuy())>Integer.parseInt(total_stock)){
				this.addFieldError("groupgoods.cust_maxbuy", "同一会员购买上限不能大于库存，库存为"+total_stock);
			}
		} else {
			this.addFieldError("groupgoods.cust_maxbuy", "同一会员购买上限不能为空");
		}

		if (!ValidateUtil.isRequired(shot)) {
			double dshot = Double.parseDouble(shot);
			if (!ValidateUtil.isRequired(min_sale_price)
					&& dshot > Double.parseDouble(min_sale_price)) {
				this.addFieldError("shot", "输入团购金额要小于原有的价格，原有的商品价格为"
						+ min_sale_price + "元！");
			} else if (dshot <= 0) {
				this.addFieldError("shot", "请输入0以上团购金额！");
			}
		} else {
			this.addFieldError("shot", "请输入团购金额！");
		}

		if (groupgoods.getImg_path().length() > 600) {
			this.addFieldError("groupgoods.img_path", "您上传的图片太多了，建议不超过9张！");
		}
		// 判断是否新增页面
		String info_state = groupgoods.getInfo_state();
		if (is_update != null) {
			// 团购商品数据操作流
			if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
				if (info_state.equals("1")) {
					reason = "审核通过状态";
				} else if (info_state.equals("3")) {
					if (reason.equals("")) {
						this.addFieldError("groupgoods.info_state",
								"您还未填写禁用理由！");
					}
				} else {
					this.addFieldError("groupgoods.info_state", "请先选择审核状态");
				}
			}
		}
		// 判断会员发布团购商品是否审核
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if ("1".equals(groupgoods.getApply_state())
					|| "2".equals(groupgoods.getApply_state())) {
				this.addActionMessage("团购商品已进入审核流程，不能做修改!");
				super.ifvalidatepass = true;
			}
			if (cfg_IsAuditgroup.equals("0")) {
				info_state = "0";
			} else {
				info_state = "1";
			}
		}
		groupgoods.setInfo_state(info_state);
		groupgoods.setGroup_type("0");
		return info_state;
	}

	/**
	 * 方法描述：新增团购商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String info_state = commonCheck();
		this.groupgoods.setCust_id(this.session_cust_id);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			groupgoods.setSort_no("0");
		}
		// 执行校验
		super.commonValidateField(groupgoods);
		if (super.ifvalidatepass) {
			return add();
		}
		String id = this.groupgoodsService.insertGroupGoods(groupgoods, shot,
				lownums, ladprices);
		// 团购商品数据操作流
		addAuditRecord(id, "groupgoods", info_state, "新增团购商品");
		this.addActionMessage("新增团购商品信息成功！");
		this.groupgoods = null;
		this.hidden_goodsname = null;
		this.shot = null;
		this.catIdStr = "1111111111";// 重置分类选择
		return INPUT;
	}

	/**
	 * 方法描述：修改团购商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = groupgoods.getTrade_id();
		String group_type = groupgoods.getGroup_type();
		String info_state = commonCheck();
		super.commonValidateField(groupgoods);
		if (super.ifvalidatepass) {
			return view();
		}
		// 积分商品数据操作流
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			addAuditRecord(id, "groupgoods", info_state, "修改团购商品");
		} else {
			if (info_state.equals("1")) {
				reason = "审核通过状态";
			}
			addAuditRecord(id, "groupgoods", info_state, reason);
		}
		if (super.ifvalidatepass) {
			return view();
		}
		// 修改商品的活动状态
		if (!groupgoods.getGoods_id().equals(goods_id_flag)
				&& goods_id_flag != null) {
			Map map = new HashMap();
			map.put("goods_id", goods_id_flag);
			map.put("active_state", "0");
			goodsService.updateActiveState(map);
			map.put("goods_id", groupgoods.getGoods_id());
			map.put("active_state", "1");
			goodsService.updateActiveState(map);
		} else {
			Map map = new HashMap();
			map.put("goods_id", groupgoods.getGoods_id());
			map.put("active_state", "1");
			goodsService.updateActiveState(map);
		}
		// 团购商品更新成功往阶梯价格表更新对应数据
		this.groupgoodsService.updateGroupladder(group_type, id, shot, lownums,
				ladprices);
		this.groupgoodsService.update(groupgoods);
		this.addActionMessage("修改团购商品信息成功！");
		catIdStr = "1111111111";
		return list();
	}

	/**
	 * @author:HXM
	 * @date:May 9, 20144:22:49 PM
	 * @param:
	 * @Description:修改冻结状态
	 */
	public String updateApply() throws Exception {
		if (groupgoods.getTrade_id() == null) {
			return applyList();
		}
		Map map = new HashMap();
		map.put("trade_id", groupgoods.getTrade_id());
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			map.put("apply_state", "2");
			map.put("apply_time", "1");
			this.addActionMessage("申请资金解冻成功");
		} else {
			map.put("apply_state", groupgoods.getApply_state());
			this.addActionMessage("资金解冻成功");
		}
		map.put("user_id", this.session_user_id);
		groupgoodsService.updateApply(map);
		return applyList();
	}

	/**
	 * @author:HXM
	 * @date:May 9, 20142:53:06 PM
	 * @param:
	 * @Description:查询团购资金列表
	 */
	public String applyList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("apply_state", "1,2,3");
		commonList(pageMap);
		return goUrl("applyindex");
	}

	/**
	 * @author : HZX
	 * @date : Apr 17, 2014 1:03:12 PM
	 * @Method Description :公用删除
	 */
	private void commonDelete() {
		boolean flag = this.groupgoodsService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除团购商品信息成功");
		} else {
			this.addActionMessage("删除团购商品信息失败");
		}
	}

	/**
	 * 方法描述：删除记录积分商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return list();
	}

	/**
	 * 方法描述：删除团购商品审核息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditDelete() throws Exception {
		commonDelete();
		return auditList();
	}

	/**
	 * @Method Description :搜索条件集合，并找出符合条数、分页及列表
	 * @author : HZX
	 * @date : Apr 16, 2014 10:57:19 AM
	 */
	private void commonList(Map pageMap) throws Exception {
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
		if (group_title_s != null && !group_title_s.equals("")) {
			pageMap.put("group_title", group_title_s);
		}
		pageMap.put("defaultSort", "defaultSort");

		// 根据页面提交的条件找出信息总数
		int count = this.groupgoodsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		groupgoodsList = this.groupgoodsService
				.changList(this.groupgoodsService.getList(pageMap));
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
			pageMap.put("info_state", "1,3");
		}
		// 加入搜索
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * @Method Description : 运营商审核列表
	 * @author : HZX
	 * @date : Mar 29, 2014 10:14:54 AM
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
	 * @Method Description :加载阶梯价格列表
	 * @author : HZX
	 * @date : Apr 16, 2014 3:34:34 PM
	 */
	public void serchlablist(String id) {
		// 加载阶梯价格
		Map labMap = new HashMap();
		labMap.put("group_id", id);
		labList = this.groupladderService.getList(labMap);
	}

	/**
	 * @Method Description : 公用跳转更新或审核页面
	 * @author : HZX
	 * @date : Apr 16, 2014 8:08:45 PM
	 */
	private boolean commonView(String id) {
		if (id == null || id.equals("")) {
			groupgoods = new Groupgoods();
			return true;
		} else {
			// 加载阶梯价格
			serchlablist(id);
			// 回选分类
			viewCat(groupgoods.getCat_attr());
			// 获取审核列表
			searchAuditList(id, "groupgoods");
			groupgoods = this.groupgoodsService.get(id);
			if (groupgoods.getGoods_id() != null
					&& !groupgoods.getGoods_id().equals("")) {
				goods = this.goodsService
						.getByPkNoDel(groupgoods.getGoods_id());
				if (goods != null && goods.getGoods_name() != null) {
					hidden_goodsname = goods.getGoods_name();
					min_sale_price = goods.getMin_sale_price();
					total_stock=goods.getTotal_stock();
					return false;
				} else {
					hidden_goodsname = null;
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 方法描述：根据主键找出团购商品信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.groupgoods.getTrade_id();
		if (commonView(id)) {
			return list();
		}
		return goUrl(VIEW);

	}

	/**
	 * 方法描述：跳转到审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.groupgoods.getTrade_id();
		if (commonView(id)) {
			return list();
		}
		memberfund = memberfundService.get(groupgoods.getCust_id());
		// 转化为具体的分类
		cat_name = categoryFuc.getCateNameByMap(groupgoods.getCat_attr());
		return goUrl("audit");
	}

	/**
	 * @Method Description :审核团购商品
	 * @author : HZX
	 * @date : Mar 29, 2014 10:32:55 AM
	 */
	public String auditState() throws Exception {
		String id = this.groupgoods.getTrade_id();
		String info_state = this.groupgoods.getInfo_state();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if (info_state.equals("1")) {
			reason = "审核通过";
		} else if (info_state.equals("2")) {
			if (reason.equals("")) {
				this.addFieldError("groupgoods.info_state", "您还未填写审核不通过理由！");
			}
		} else {
			this.addFieldError("groupgoods.info_state", "审核状态不能为空！");
		}
		memberfund = memberfundService.get(groupgoods.getCust_id());
		if (memberfund == null) {
			this.addActionMessage("该用户可用余额不足！");
			super.ifvalidatepass = true;
		}
		// 验证数据
		if (super.ifvalidatepass) {
			return audit();
		}
		// 获取数据库对象
		Groupgoods gd = this.groupgoodsService.get(id);
		
		if ("1".equals(info_state)
				&& ("0".equals(gd.getApply_state()) || "3".equals(gd
						.getApply_state()))) {
			double i2 = Double.valueOf(memberfund.getUse_num());// 可用资金
			double i3 = Double.valueOf(memberfund.getFreeze_num());// 冻结资金
			if (i2 < groupgoods.getBond()) {
				this.addActionMessage("该用户可用余额不足！");
				super.ifvalidatepass = true;
			}

			double i1 = memberfundService.freezeNum(groupgoods.getCust_id(),
					groupgoods.getBond(), 0);
			// 插入资金记录表
			Fundhistory fh = new Fundhistory();
			fh.setBalance(i1);
			fh.setCust_id(groupgoods.getCust_id());
			fh.setFund_in(0.0);
			fh.setFund_out(groupgoods.getBond());
			fh.setUser_id(this.session_user_id);
			fh.setAction_type("3");// 1:入金，2:出金，3:冻结，4:解冻'
			fh.setReason("团购【冻结】资金:团购名称为" + groupgoods.getGroup_title()
					+ "冻结金额为" + groupgoods.getBond() + "元");
			this.fundhistoryService.insert(fh);
			// 冻结运营商资金
			sysfundService.freezeNum(groupgoods.getBond(), 0);
			// 表示资金被冻结
			gd.setApply_state("1");
		}
		// 设置状态值
		gd.setInfo_state(info_state);
		// 更新数据库供应列表
		this.groupgoodsService.update(gd);
		// 新增审核团购商品数据操作流
		addAuditRecord(id, "groupgoods", info_state, reason);
		this.addActionMessage("审核团购商品成功");
		return auditList();
	}

	/**
	 * @Method Description :
	 * @author : HZX
	 * @date : Apr 16, 2014 8:19:35 PM
	 */
	private void commonSort() {
		boolean flag = this.groupgoodsService.updateSort("trade_id", "sort_no",
				chb_id, sort_val);
		if (flag) {
			this.addActionMessage("团购商品排序成功");
		} else {
			this.addActionMessage("团购商品排序失败");
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
		this.addActionMessage("团购商品排序成功");
		return auditList();
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (groupgoods == null) {
			groupgoods = new Groupgoods();
		}
		String id = this.groupgoods.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			groupgoods = this.groupgoodsService.get(id);
		}
	}

	public Groupgoods getGroupgoods() {
		return groupgoods;
	}

	public void setGroupgoods(Groupgoods groupgoods) {
		this.groupgoods = groupgoods;
	}

	public Goods getGoods() {
		return goods;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
