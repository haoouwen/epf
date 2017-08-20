/*
 
 * Package:com.rbt.action
 * FileName: GoodsevalAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Goodsshare;
import com.rbt.model.Memberuser;
import com.rbt.model.Ordertrans;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IGoodsshareService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IOrdertransService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品评价表信息action类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 08 11:06:50 CST 2014
 */
@Controller
public class GoodsevalAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	private Goodseval goodseval;
	private Goods goods;
	private Ordertrans ordertrans;
	public Goodsorder goodsorder;

	/** *****************业务层接口*************** */
	@Autowired
	private IGoodsevalService goodsevalService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private IOrderdetailService orderdetailService;
	@Autowired
	private IDirectorderdetailService directorderdetailService;
	@Autowired
	private IOrdertransService ordertransService;
	@Autowired
	private IGoodsshareService goodsshareService;

	/** *******************集合***************** */
	public List goodsevalList;// 记录商品评价表信息信息集合
	public List directDetaiList;
	public List detailList;

	/** *******************字段***************** */
	private String type;// 判断是买家还是卖家
	private static String shop_cust_id;// 保存商铺ID
	public String goods_name_s;// 商品名称
	public String g_eval_s;// 商品评级
	public String is_enable_s;// 是否有效
	public String explan_man_s;// 解释人
	public String user_name_s;// 评论人
	public String goods_id_s;// 分组标记商品编号,用于详细列表页分组查询
	public String cfg_evalouttime = SysconfigFuc.getSysValue("cfg_evalouttime");// 获取评价过期时间
	public Integer orderdetaiCount = 0;
	public String order_id;
	public String sell_cust_id; // 卖家标识
	public String order_goods_id_str;// 商品评价的商品ID串
	public String order_goods_feng_str;// 商品评价的商品分数
	public String order_goods_content_str;// 商品评价的内容
	public String order_share_pic_str;// 晒图内容
	public String order_service_attitude;// 卖家服务态度
	public String order_delivery_speed;// 卖家发货速度
	public String order_desc;// 描述相符
	public String trade_id;
	public String share_pic;//晒图

	/**
	 * @return the goodseval
	 */
	public Goodseval getGoodseval() {
		return goodseval;
	}

	/**
	 * @param Goodseval
	 *            the goodseval to set
	 */
	public void setGoodseval(Goodseval goodseval) {
		this.goodseval = goodseval;
	}

	/**
	 * 方法描述：返回新增记录商品评价表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品评价表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		goodseval.setCust_id(this.session_cust_id);
		goodseval.setUser_id(this.session_user_id);
		goodseval.setIs_two("0");// 是否已修改评价默认否
		super.commonValidateField(goodseval);
		if (super.ifvalidatepass) {
			return add();
		}

		this.goodsevalService.insert(goodseval);
		this.addActionMessage("新增记录商品评价表信息成功！");
		this.goodseval = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品评价表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		// this.session_cust_id.equals(shop_cust_id);
		Memberuser memberuser = new Memberuser();
		goodseval.setExplan_cust_id(this.session_cust_id);
		goodseval.setExplan_man(this.session_user_id);

		// 判断是否是买家，买家不需要评论内容验证
		if (this.validateFactory.isRequired(goodseval.getExplan_content())) {
			this.addFieldError("goodseval.explan_content", "回复内容不能为空");
			return view();
		}

		this.goodsevalService.update(goodseval);
		this.addActionMessage("修改记录商品评价表信息成功！");
		return list();
	}

	/**
	 * @Method Description :买家评价
	 * @author : HZX
	 * @date : Mar 18, 2014 3:06:41 PM
	 */
	public String buyupdate() throws Exception {
		if (ValidateUtil.isRequired(goodseval.getG_eval())) {
			this.addFieldError("goodseval.g_eval", "您还没评价呢！");
		}
		// 判断是否是买家，买家不需要评论内容验证
		if (ValidateUtil.isRequired(goodseval.getG_comment())) {
			this.addFieldError("goodseval.g_content", "评价内容不能为空");
			return audit();
		}
		this.goodsevalService.updatebuy(goodseval);
		this.addActionMessage("商品评价成功！");
		return auditList();
	}

	/**
	 * 方法描述：删除记录商品评价表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {

		boolean flag = this.goodsevalService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除记录商品评价表信息成功");
		} else {
			this.addActionMessage("删除记录商品评价表信息失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		commonList("1");
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String alllist() throws Exception {
		commonList("1");
		return goUrl("allindex");
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
		int count = this.goodsevalService.getGroupCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsevalList = this.goodsevalService.getListByGroup(pageMap);
		return goUrl("groupindex");
	}

	/**
	 * 方法描述：根据主键找出记录商品评价表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {

		HttpServletRequest request = getRequest();
		if (request.getParameter("type") != null) {
			type = request.getParameter("type");
		}

		String id = this.goodseval.getTrade_id();
		if (id == null || id.equals("")) {
			goodseval = new Goodseval();
			return list();
		} else {
			goodseval = this.goodsevalService.get(id);
		}
		if (goodseval != null && goodseval.getGoods_id() != null
				&& !goodseval.getGoods_id().equals("")) {
			goods = goodsService.get(goodseval.getGoods_id());
			if (goods != null && goods.getCust_id() != null
					&& !goods.getCust_id().equals("")) {
				shop_cust_id = goods.getCust_id();
			}
		}
		Map map=new HashMap();
		map.put("eval_id", id);
		List list=this.goodsshareService.getList(map);
		if(list!=null&&list.size()>0){
		  Map shareMap=(HashMap)list.get(0);
		  share_pic=shareMap.get("share_pic").toString();
		}
		return goUrl(VIEW);
	}

	public String audit() throws Exception {
		
		if (!ValidateUtil.isRequired(trade_id)) {
			goodseval=this.goodsevalService.get(trade_id);
			goods = goodsService.get(goodseval.getGoods_id());
			Map map=new HashMap();
			map.put("eval_id", trade_id);
			List list=this.goodsshareService.getList(map);
			if(list!=null&&list.size()>0){
			  Map shareMap=(HashMap)list.get(0);
			  share_pic=shareMap.get("share_pic").toString();
			}
			return goUrl(AUDIT);
		}else{
			return auditList();
		}
	}

	/**会员评价列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("order_state", "7,8");
		pageMap.put("cust_id", this.session_cust_id);
		pageMap.put("is_virtual", "1");
		pageMap.put("is_eval", "0");
		// 根据页面提交的条件找出信息总数
		int count = this.orderdetailService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		goodsevalList = this.orderdetailService.getList(pageMap);
		return goUrl(AUDITLIST);

	}

	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 3:28:01 PM
	 * @Method Description：公共查询
	 */
	public void commonList(String type) {
		Map pageMap = new HashMap();
		if (goods_name_s != null && !goods_name_s.equals("")) {
			pageMap.put("goods_name", goods_name_s);
		}
		if (g_eval_s != null && !g_eval_s.equals("")) {
			pageMap.put("g_eval", g_eval_s);
		}
		if (is_enable_s != null && !is_enable_s.equals("")) {
			pageMap.put("is_enable", is_enable_s);
		}
		if (!ValidateUtil.isRequired(goods_id_s)) {
			pageMap.put("goods_id", goods_id_s);
		}

		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if (type.equals("0")) {
				// 加入买家的搜索条件
				pageMap.put("cust_id", this.session_cust_id);
			} 
		}
		// 根据页面提交的条件找出信息总数
		int count = this.goodsevalService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		goodsevalList = this.goodsevalService.getList(pageMap);
	}

	public String auditState() throws Exception {

		if (this.session_cust_id.equals(shop_cust_id)) {
			goodseval.setExplan_cust_id(this.session_cust_id);
			goodseval.setExplan_man(this.session_user_id);
		}
		goodseval.setIs_two("1");
		this.goodsevalService.update(goodseval);
		this.addActionMessage("修改记录商品评价表信息成功！");
		return auditList();
	}

	/**
	 * @author : HXK
	 * @param :
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价-查看
	 */
	public String orderEvaluateView() throws IOException {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				// 获取订单信息
				goodsorder = new Goodsorder();
				goodsorder = this.goodsorderService.get(order_id);
				// 获取商品信息
				Map detailMap = new HashMap();
				detailMap.put("order_id", order_id);
				if (goodsorder.getOrder_type().equals("6")) {
					orderdetaiCount = directorderdetailService.getCount(detailMap);
					detailList = this.directorderdetailService
							.getList(detailMap);
				} else {
					orderdetaiCount = orderdetailService.getCount(detailMap);
					detailList = this.orderdetailService.getList(detailMap);
				}
				orderdetaiCount = detailList.size();
				// 获取店铺信息
				return goUrl("orderevaluate");
			} else {
				return null;
			}
		}
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价
	 */
	public String orderGoodsEvaluate() throws Exception {
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html");
			return goUrl("login");
		} else {
			if (order_id != null) {
				if (orderdetaiCount != null && !orderdetaiCount.equals("0")) {
					String order_goods_id_strs[] = order_goods_id_str
							.split(",");
					String order_goods_feng_strs[] = order_goods_feng_str
							.split(",");
					String order_goods_content_strs[] = order_goods_content_str
							.split("##########");
					String order_share_pic_strs[] = order_share_pic_str
							.split("##########");
					Map evalMap = new HashMap();
					evalMap.put("sell_cust_id", sell_cust_id);
					evalMap.put("order_goods_id_strs", order_goods_id_strs);
					evalMap.put("order_goods_feng_strs", order_goods_feng_strs);
					evalMap.put("order_goods_content_strs",
							order_goods_content_strs);
					evalMap.put("order_share_pic_strs", order_share_pic_strs);
					evalMap.put("orderdetaiCount", orderdetaiCount);
					evalMap.put("session_cust_id", this.session_cust_id);
					evalMap.put("session_user_id", this.session_user_id);
					evalMap.put("order_delivery_speed", order_delivery_speed);
					evalMap.put("order_service_attitude",
							order_service_attitude);
					evalMap.put("order_desc", order_desc);
					evalMap.put("order_id", order_id);
					this.orderdetailService.saveEval(evalMap);// 处理评价
					// 更新订单状态为 已评价
					int detail_ids=0,goods_eval_ids=0;
					Map cMap=new HashMap();
					cMap.put("order_id", order_id);
					detail_ids=orderdetailService.getCount(cMap);
					goods_eval_ids=goodsevalService.getCount(cMap);
					if(detail_ids==goods_eval_ids){
						updateOrderState(order_id, "8");
					}
					this.addActionMessage("商品评价成功！");
					return auditList();
				}
			}
			return null;
		}
	}

	/**
	 * 删除评价
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteEval() throws Exception {
		if(!ValidateUtil.isRequired(goodseval.getTrade_id())){
			this.goodsevalService.delete(goodseval.getTrade_id());
		}
		Map map = new HashMap();
		if (!ValidateUtil.isRequired(trade_id)) {
			map.put("trade_id", trade_id);
		}
		map.put("is_eval", "1");
		this.orderdetailService.updateState(map);
		this.addActionMessage("删除评价成功!");
		return auditList();
	}

	/**
	 * @author : HXK
	 * @throws Exception
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价
	 */
	public void orderEvaluate() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (this.session_cust_id == null || this.session_cust_id.equals("")) {
			out.write("1");
		} else {
			String order_id = request.getParameter("order_id");
			String g_eval = request.getParameter("g_eval");
			String g_comment = request.getParameter("g_comment");
			String goods_id = request.getParameter("goods_id");
			String share_pic = request.getParameter("share_pic");
			goodseval.setCust_id(this.session_cust_id);
			goodseval.setUser_id(this.session_user_id);
			goodseval.setOrder_id(order_id);
			goodseval.setGoods_id(goods_id);
			goodseval.setG_eval(g_eval);
			goodseval.setG_comment(g_comment);
			goodseval.setIs_two("0");
			goodseval.setIs_enable("0");
			String eval_id = goodsevalService.insertGetPk(goodseval);
			// 插入商品晒单图
			if (!ValidateUtil.isRequired(share_pic)) {
				Goodsshare goodsshare = new Goodsshare();
				goodsshare.setEval_id(eval_id);
				goodsshare.setCust_id(this.session_cust_id);
				goodsshare.setUser_id(this.session_user_id);
				goodsshare.setGoods_id(goods_id);
				goodsshare.setShare_pic(share_pic);
				goodsshareService.insert(goodsshare);
			}
			int detail_ids=0,goods_eval_ids=0;
			Map cMap=new HashMap();
			cMap.put("order_id", order_id);
			detail_ids=orderdetailService.getCount(cMap);
			goods_eval_ids=goodsevalService.getCount(cMap);
			if(detail_ids==goods_eval_ids){
				// 更新订单状态为 已评价
				updateOrderState(order_id, "8");
			}
			out.write("2");
		}
	}

	/**
	 * @author : HXK 方法描述：会员修改订单状态
	 * @return
	 * @throws Exception
	 */
	public void updateOrderState(String s_order_id, String s_order_state)
			throws Exception {
		// 订单状态表示：0：订单取消 1：未付款 2：已付款 3：已发货 4：退款中 5：退款成功 6：退款失败：7：交易成功 8：已评价
		Goodsorder goods_order = new Goodsorder();
		goods_order.setOrder_id(s_order_id);
		goods_order.setOrder_state(s_order_state);
		Map stateMap = new HashMap();
		stateMap.put("order_state", s_order_state);
		stateMap.put("order_id", s_order_id);
		if (s_order_state.equals("2")) {
			stateMap.put("pay_time", "pay_time");
		}
		if (s_order_state.equals("3")) {
			stateMap.put("send_time", "send_time");
		}
		if (s_order_state.equals("7")) {
			stateMap.put("sure_time", "sure_time");
		}
		goodsorderService.update(stateMap);
		// 插入订单异动信息表
		String reason = "会员修改订单状态";
		insertOrderTrans(s_order_id, reason, s_order_state);
	}

	/**
	 * 方法描述：修改订单的时候，插入订单异动表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void insertOrderTrans(String order_id, String reason,
			String order_state) {
		ordertrans = new Ordertrans();
		ordertransService.inserOrderTran(order_id, 
				session_cust_id, session_user_id, reason, order_state, session_user_name);
	}

	/**
	 * @return the GoodsevalList
	 */
	public List getGoodsevalList() {
		return goodsevalList;
	}

	/**
	 * @param goodsevalList
	 *            the GoodsevalList to set
	 */
	public void setGoodsevalList(List goodsevalList) {
		this.goodsevalList = goodsevalList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (goodseval == null) {
			goodseval = new Goodseval();
		}
		String id = this.goodseval.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			goodseval = this.goodsevalService.get(id);
		}
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
