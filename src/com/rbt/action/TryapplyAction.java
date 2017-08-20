/*
 
 * Package:com.rbt.action
 * FileName: TryapplyAction.java 
 */
package com.rbt.action;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.model.Goods;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Memberuser;
import com.rbt.model.Tryapply;
import com.rbt.model.Trygoods;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IGoodsService;
import com.rbt.service.ITryapplyService;
import com.rbt.service.ITrygoodsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 申请试用action类
 * @author 创建人 CYC
 * @date 创建日期 Sat Jul 12 09:25:36 CST 2014
 */
@Controller
public class TryapplyAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 申请试用对象
	 */
	private Tryapply tryapply;
	public Goods goods;
	public Buyeraddr buyeraddr;
	/*******************业务层接口****************/
	/*
	 * 申请试用业务层接口
	 */
	@Autowired
	private ITryapplyService tryapplyService;
	@Autowired
	private ITrygoodsService trygoodsService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IBuyeraddrService buyeraddrService;

	/*********************集合*******************/
	/*
	 * 申请试用信息集合
	 */
	public List tryapplyList;
	/*********************字段*******************/
	public String chb_id;
	public String user_name_s;
	public String status_s;
	public String report_s;
	public String try_id;
	public Trygoods trygoods;
	public Memberuser memberuser;
	public String goods_name_s;
	public String try_type_s;
		
	/**
	 * 方法描述：返回新增申请试用页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增申请试用
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(tryapply);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.tryapplyService.insert(tryapply);
		this.addActionMessage("新增申请试用成功！");
		this.tryapply = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改申请试用信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(ValidateUtil.isRequired(tryapply.getComment())){
			this.addFieldError("tryapply.comment", "请选择评论！");
			return view();
		}
		
		if(ValidateUtil.isRequired(tryapply.getReport())){
			this.addFieldError("tryapply.comment", "试用报告不能为空！");
			return view();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String comment=tryapply.getComment();
		String report=tryapply.getReport();
		tryapply=this.tryapplyService.get(tryapply.getTryapply_id());
		tryapply.setComment(comment);
		tryapply.setReport(report);
		tryapply.setReport_date(df.format(new Date()));
		super.commonValidateField(tryapply);
		if(super.ifvalidatepass){
			return view();
		}
		this.tryapplyService.update(tryapply);
		this.addActionMessage("试用体验保存成功！");
		return list();
	}
	/**
	 * 方法描述：删除申请试用信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除申请试用信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.tryapplyService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除申请试用信息成功!");
		}else{
			this.addActionMessage("删除申请试用信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		commonList(false);
		return goUrl(INDEXLIST);
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String tryList() throws Exception {
		commonList(true);
		return goUrl("tryindex");
	}
	/**
	 * 公共查询方法
	 * @throws Exception
	 */
	public void commonList(boolean flag)throws Exception{
		Map pageMap = new HashMap();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try_id = request.getParameter("try_id");
		if(try_id!=null && !"".equals(try_id)){
			trygoods = trygoodsService.get(try_id);
			goods = goodsService.get(trygoods.getGoods_id());
		}
		if(user_name_s!=null && !"".equals(user_name_s)){
			pageMap.put("user_name", user_name_s);
		}
		if(flag){
			pageMap.put("cust_id", this.session_cust_id);
		}else{
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
				pageMap.put("user_id", this.session_user_id);
			}
		}
		if(status_s!=null && !"".equals(status_s)){
			pageMap.put("status", status_s);
		}
		
		if(report_s!=null && !"".equals(report_s)){
			pageMap.put("report", report_s);
		}
		pageMap.put("try_id", try_id);
		if(!ValidateUtil.isRequired(goods_name_s)){
			pageMap.put("goods_name", goods_name_s);
		}
		
		if(!ValidateUtil.isRequired(try_type_s)){
			pageMap.put("try_type", try_type_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.tryapplyService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		tryapplyList = this.tryapplyService.getList(pageMap);
	}
	
	/**
	 * @author :CYC
	 * @date : Mar 1, 2014 9:53:06 AM
	 * @Method Description :批量修改审核状态
	 */
	public String updateState() throws Exception {
		boolean flag = this.tryapplyService.updateBatchState(chb_id, state_val, "tryapply_id", "status");// 0：逻辑删除商品
		if(flag){
			if (state_val.equals("0")) {
				//删除订单
				this.tryapplyService.delOrder(chb_id);
				this.addActionMessage("取消试用资格");
			} else if (state_val.equals("1")) {
				//生成订单
				this.tryapplyService.addOrder(chb_id);
				this.addActionMessage("获得试用资格");
			}
		}else{
			this.addActionMessage("操作失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据主键找出申请试用信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		commonView();
		return goUrl(VIEW);
	}
	
	
	/**
	 * 方法描述：根据主键找出申请试用信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tryView() throws Exception {
		commonView();
		return goUrl("updatetry");
	}
	
	/**
	 * 方法描述：根据主键找出申请试用信息
	 */
	public void commonView(){
		String id = this.tryapply.getTryapply_id();
		if(id==null || id.equals("")){
			tryapply = new Tryapply();
		}else{
			tryapply = this.tryapplyService.get(id);
		}
		trygoods = trygoodsService.get(tryapply.getTry_id());
		memberuser = memberuserService.get(tryapply.getUser_id());
		goods = goodsService.get(trygoods.getGoods_id());
		buyeraddr=buyeraddrService.get(tryapply.getAdd_id());
		String area_name=AreaFuc.getAreaNameByMap(buyeraddr.getArea_attr());
		buyeraddr.setArea_attr(area_name);
	}
	/**
	 * @return the TryapplyList
	 */
	public List getTryapplyList() {
		return tryapplyList;
	}
	/**
	 * @param tryapplyList
	 *  the TryapplyList to set
	 */
	public void setTryapplyList(List tryapplyList) {
		this.tryapplyList = tryapplyList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(tryapply == null){
			tryapply = new Tryapply();
		}
		String id = this.tryapply.getTry_id();
		if(!this.validateFactory.isDigital(id)){
			tryapply = this.tryapplyService.get(id);
		}
	}
	/**
	 * @return the tryapply
	 */
	public Tryapply getTryapply() {
		return tryapply;
	}
	/**
	 * @param Tryapply
	 *            the tryapply to set
	 */
	public void setTryapply(Tryapply tryapply) {
		this.tryapply = tryapply;
	}
}

