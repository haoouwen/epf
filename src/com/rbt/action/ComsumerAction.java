/*
 * Package:com.rbt.action
 * FileName: ComsumerAction.java 
 */
package com.rbt.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.BaseAction;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Comsumer;
import com.rbt.model.Excoupons;
import com.rbt.service.IComsumerService;
import com.rbt.service.IExcouponsService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 优惠券消费码action类
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 14:55:34 CST 2015
 */
@SuppressWarnings("unchecked")
@Controller
public class ComsumerAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 优惠券消费码对象
	 */
	private Comsumer comsumer;
	/*******************业务层接口****************/
	/*
	 * 优惠券消费码业务层接口
	 */
	@Autowired
	private IComsumerService comsumerService;
	@Autowired
	private IExcouponsService excouponsService;
	
	/*********************集合*******************/
	/*
	 * 优惠券消费码信息集合
	 */
	public List comsumerList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增优惠券消费码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增优惠券消费码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(comsumer);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.comsumerService.insert(comsumer);
		this.addActionMessage("新增优惠券消费码成功！");
		this.comsumer = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改优惠券消费码信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(comsumer);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.comsumerService.update(comsumer);
		this.addActionMessage("修改优惠券消费码成功！");
		return list();
	}
	/**
	 * 方法描述：删除优惠券消费码信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：删除消费码信息
	 * @return
	 * @throws Exception
	 */
	public String outDelete() throws Exception {
		commonDel();
		return outList();
	}
	
	/**
	 * 方法描述：删除消费码信息
	 * @return
	 * @throws Exception
	 */
	public String useDelete() throws Exception {
		commonDel();
		return useList();
	}
	
	/**
	 * 方法描述：公用删除优惠券消费码信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.comsumerService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除优惠券消费码信息成功!");
		}else{
			this.addActionMessage("删除优惠券消费码信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("use_state", "0");
        pageMap.put("date", "now");
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String useList() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("use_state", "1");
		commonList(pageMap);
		return goUrl("useindex");
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String outList() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("outdate", "now");
		commonList(pageMap);
		return goUrl("outindex");
	}	
	
	/**
	 * 公共查询方法
	 */
	public void commonList(Map pageMap){
		pageMap.put("cust_id", this.session_cust_id);
		//根据页面提交的条件找出信息总数-
		int count=this.comsumerService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		comsumerList = this.comsumerService.getList(pageMap);
		comsumerList = ToolsFuc.replaceList(comsumerList, "");
	}
	
	
	/**
	 * 方法描述：根据主键找出优惠券消费码信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.comsumer.getComsumer_id();
		if(id==null || id.equals("")){
			comsumer = new Comsumer();
		}else{
			comsumer = this.comsumerService.get(id);
		}
		return goUrl(VIEW);
	}
	
	
	/**
	 * ajax兑换优惠券
	 * @throws IOException
	 */
	public void exCoupon() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String coupon_no = request.getParameter("coupon_no");
		
		Map map = new HashMap();
		map.put("coupon_no", coupon_no);
		List list = this.excouponsService.getList(map);
		
		
		//判断是否已经兑换过
		if (list != null && list.size() > 0 ) {
			  Map couponMap = (HashMap) list.get(0);
			  if(couponMap.get("ex_state") != null && couponMap.get("ex_state").toString().equals("1")) {
				  //优惠券已经兑换过 
				  out.write("2");
			   }else {
				  //兑换优惠券
				  String ex_id = couponMap.get("ex_id").toString();
				  Excoupons excoupons = this.excouponsService.get(ex_id);
				  //插入优惠券
				  Comsumer comsumer = new Comsumer();
				  comsumer.setComsumer_no(coupon_no);
				  comsumer.setCoupon_id(excoupons.getCoupon_id());
				  comsumer.setCust_id(this.session_cust_id);
				  comsumer.setUse_state("0");
				  this.comsumerService.insert(comsumer);
				  //修改兑换状态
				  excoupons.setEx_state("1");
				  this.excouponsService.update(excoupons);
				  out.write("3");
			   }
		}else { 
		   //判断优惠券号码不存在
		   out.write("1");
		   
		}
	}
	
	/**
	 * @return the ComsumerList
	 */
	public List getComsumerList() {
		return comsumerList;
	}
	/**
	 * @param comsumerList
	 *  the ComsumerList to set
	 */
	public void setComsumerList(List comsumerList) {
		this.comsumerList = comsumerList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(comsumer == null){
			comsumer = new Comsumer();
		}
		String id = this.comsumer.getComsumer_id();
		if(!this.validateFactory.isDigital(id)){
			comsumer = this.comsumerService.get(id);
		}
	}
	/**
	 * @return the comsumer
	 */
	public Comsumer getComsumer() {
		return comsumer;
	}
	/**
	 * @param Comsumer
	 *            the comsumer to set
	 */
	public void setComsumer(Comsumer comsumer) {
		this.comsumer = comsumer;
	}
}


