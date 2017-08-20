/*
 
 * Package:com.rbt.action
 * FileName: BusimesAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.model.Busimes;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.service.IBusimesService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商家留言信息action类
 * @author 创建人 CYC
 * @date 创建日期 Thu Mar 29 11:01:16 CST 2014
 */
@Controller
public class BusimesAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Busimes busimes;
	public Member member;
	public Memberuser memberuser;

	/*******************业务层接口****************/
	@Autowired
	private IBusimesService busimesService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberuserService memberuserService;

	/*********************集合******************/
	public List busimesList;//记录商家留言信息信息集合

	/*********************字段******************/
	public static String shop_cust_id;//保存商铺的ID
	public String cust_name_s;//商店名称
	public String user_name_s;//客户名称
	public String start_time_s;//留言时间段开始
	public String end_time_s;//留言时间段结束
	public String is_enable_s;//是否有效
	public String rstart_time_s;//回复时间段开始
	public String rend_time_s;//回复时间段结束
	public String jinId;//今日留言
	public String weiId;//未处理留言


	
	/**
	 * 方法描述：返回新增记录商家留言信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商家留言信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		busimes.setSelf_cust_id(this.session_cust_id);
		busimes.setSelf_user_id(this.session_user_id);
		busimes.setIs_enable("0");
		super.commonValidateField(busimes);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.busimesService.insert(busimes);
		this.addActionMessage("新增记录商家留言信息成功！");
		this.busimes = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商家留言信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(this.session_cust_id.equals(shop_cust_id)){
			busimes.setGet_user_id(this.session_cust_id);
		}
		
	    if(this.validateFactory.isRequired(busimes.getRe_content())){
	    	this.addFieldError("busimes.re_content", "回复内容不能为空");
	    	return view();
	    }
	    
		this.busimesService.update(busimes);
		this.addActionMessage("修改记录商家留言信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商家留言信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.busimesService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录商家留言信息成功");
		}else{
			this.addActionMessage("删除记录商家留言信息失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
        //查询方法
		commonList();
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录商家留言信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//查看
		commonView();
		return goUrl(VIEW);
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:18:03 PM
	 * @Method Description：审核跳转页面
	 */
	public String audit() throws Exception {
		//查看
		commonView();
		return goUrl(AUDIT);
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:18:59 PM
	 * @Method Description：核心查看方法
	 */
	public void commonView(){
		String id = this.busimes.getTrade_id();
		if(id==null || id.equals("")){
			busimes = new Busimes();
		}else{
			busimes = this.busimesService.get(id);
		}
		if(busimes!=null)
		shop_cust_id=this.busimes.getGet_cust_id();
		member=memberService.get(this.busimes.getGet_cust_id());
		memberuser=memberuserService.get(this.busimes.getSelf_user_id());
	}
	 /**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:18:33 PM
	 * @Method Description：审核查询方法
	 */
	 public String auditList() throws Exception {
	     //查询方法
		 commonList();
		 return goUrl(AUDITLIST);
	 }
	 
	 /**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:14:41 PM
	 * @Method Description：公共核心查询方法
	 */
	 public void commonList(){
			Map pageMap = new HashMap();
			// 操作者为会员则默认加入搜索条件
			if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				pageMap.put("get_cust_id", this.session_cust_id);
			}		
			if (cust_name_s != null && !cust_name_s.equals("")) {
				pageMap.put("cust_name", cust_name_s);
			}
			if (user_name_s != null && !user_name_s.equals("")) {
				pageMap.put("user_name", user_name_s);
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
			if (is_enable_s != null && !is_enable_s.equals("")) {
				pageMap.put("is_enable", is_enable_s);
			}
		
			//搜索今日留言
			if (jinId != null && !jinId.equals("")) {
				pageMap.put("today", "0");
			}
			//搜索未处理留言
			if (weiId != null && !weiId.equals("")) {
				pageMap.put("re_date","0");
			}
			
			//根据页面提交的条件找出信息总数
			int count=this.busimesService.getCount(pageMap);
			
			//分页插件
			pageMap = super.pageTool(count,pageMap);
			
			busimesList = this.busimesService.getList(pageMap);
			//把回复者ID替换回复者商铺名称
            busimesList = this.busimesService.replaceList(busimesList, member);
	 } 
	 /**
	 * @author：XBY
	 * @ date：Feb 25, 2014 5:18:42 PM
	 * @Method Description：审核方法
	 */ 
	 public String auditState() throws Exception {
		 
		 return auditList();
	 }
	 
		/**
		 * @return the busimes
		 */
		public Busimes getBusimes() {
			return busimes;
		}
		/**
		 * @param Busimes
		 *            the busimes to set
		 */
		public void setBusimes(Busimes busimes) {
			this.busimes = busimes;
		}
	/**
	 * @return the BusimesList
	 */
	public List getBusimesList() {
		return busimesList;
	}
	/**
	 * @param busimesList
	 *  the BusimesList to set
	 */
	public void setBusimesList(List busimesList) {
		this.busimesList = busimesList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(busimes == null){
			busimes = new Busimes();
		}
		String id = this.busimes.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			busimes = this.busimesService.get(id);
		}
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Memberuser getMemberuser() {
		return memberuser;
	}
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

}

