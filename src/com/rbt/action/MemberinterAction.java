/*
 
 * Package:com.rbt.action
 * FileName: MemberinterAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Interhistory;
import com.rbt.model.Member;
import com.rbt.model.Memberinter;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IMemberinterService;

/**
 * @function 功能 记录会员积分数action类
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 14 14:30:38 CST 2014
 */
@Controller
public class MemberinterAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/** *****************实体层******************* */

	public Memberinter memberinter;
	private Member member;
	public Interhistory interhistory;
	/** *****************业务层接口*************** */
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	public IInterhistoryService interhistoryService;

	/** *******************集合******************* */
	public List memberinterList;// 会员积分数信息
	public List smemberinterList;

	/** *******************字段******************* */
	public String fund_num_s;// 积分范围
	public String last_fund_num_s;
	public String cust_id;
	public String cust_name_s;
	public String cust_name;// 用户名字段
	public String fund_num;// 积分字段
	public String reason;// 事由字段
	public String radiochecked;// 增减字段
	public String remark;
	public String custId;
	public String enter;// 页面跳转标识符

	/**
	 * 方法描述：返回新增记录会员积分数页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员积分数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.memberinterService.insert(memberinter);
		this.addActionMessage("新增会员积分成功");
		this.memberinter = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员积分数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		if (ValidateUtil.isRequired(fund_num)
				|| ValidateUtil.isDigital(fund_num)) {
			this.addFieldError("fund_num", "不能为空且只能输入数字");
			return view();
		}
		if (ValidateUtil.isRequired(reason)) {
			this.addFieldError("reason", "事由不能为空");
			return view();
		}
		// 实例化fundhistory
		interhistory = new Interhistory();
		memberinter = new Memberinter();
		interhistory.setCust_id(cust_id);
		if (!cust_id.equals("")) {
			memberinter = this.memberinterService.get(cust_id);
		}
		// 判断收入支出"0"表示收入
		if (radiochecked.equals("0")) {
			interhistory.setInter_in(fund_num);
			interhistory.setInter_out("0");
			Double value_fund = Double.valueOf(memberinter.getFund_num())
					+ Double.valueOf(fund_num);
			memberinter.setFund_num(String.valueOf(value_fund));
			interhistory.setThisinter(String.valueOf(value_fund));
		} else {
			interhistory.setInter_in("0");
			interhistory.setInter_out(fund_num);
			Double value_fund = Double.valueOf(memberinter.getFund_num())
					- Double.valueOf(fund_num);
			memberinter.setFund_num(String.valueOf(value_fund));
			interhistory.setThisinter(String.valueOf(value_fund));
		}
		interhistory.setUser_id(this.session_user_id);
		interhistory.setReason(reason);
		interhistory.setRemark(remark);
		// 更新余额表
		this.memberinterService.updateinter(interhistory, memberinter);
		this.addActionMessage("修改会员积分成功");
		return list();
	}

	/**
	 * 方法描述：删除记录会员积分数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memberinterService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除会员积分信息成功");
		} else {
			this.addActionMessage("删除会员积分信息失败");
		}

		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (member != null) {
			custId = member.getCust_id();
		}
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (!ValidateUtil.isRequired(enter)) {
			if (custId != null && !custId.equals("")) {
				pageMap.put("cust_id", custId);
			}
		}
		if (fund_num_s != null && !fund_num_s.equals(""))
			pageMap.put("fund_num", fund_num_s);
		if (last_fund_num_s != null && !last_fund_num_s.equals(""))
			pageMap.put("last_fund_num_s", last_fund_num_s);
		if (cust_name_s != null && !cust_name_s.equals(""))
			pageMap.put("cust_name", cust_name_s);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (cust_id != null && !cust_id.equals("")) {
			pageMap.put("cust_id", cust_id);
		}
		// 去除掉运营商在memberinter表中的cust_id
		pageMap.put("inte_cust_id", "0");
		// 过滤地区
		pageMap = super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberinterService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		memberinterList = this.memberinterService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录会员积分数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if (memberinter.getCust_id() != null) {
			if (accessControl(memberinter.getCust_id())) {
				return list();
			}
		}
		// 判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.memberinter.getCust_id();
		if (!ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			if (!cust_id.equals("")) {
				memberinter = this.memberinterService.get(cust_id);
			}
			Map map = new HashMap();
			map.put("cust_id", cust_id);
			smemberinterList = this.memberinterService.getList(map);
			if (smemberinterList.size() > 0 && smemberinterList != null) {
				Map getmap = (HashMap) smemberinterList.get(0);
				if(getmap!=null&& getmap.get("cust_name")!=null){
					cust_name = getmap.get("cust_name").toString();
				}
			}
		} else {
			if (!this.session_cust_id.equals("")) {
				memberinter = this.memberinterService.get(this.session_cust_id);
			}
		}

		return goUrl(VIEW);
	}

	/**
	 * @return the MemberinterList
	 */
	public List getMemberinterList() {
		return memberinterList;
	}

	/**
	 * @param memberinterList
	 *            the MemberinterList to set
	 */
	public void setMemberinterList(List memberinterList) {
		this.memberinterList = memberinterList;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public List getSmemberinterList() {
		return smemberinterList;
	}

	public void setSmemberinterList(List smemberinterList) {
		this.smemberinterList = smemberinterList;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getFund_num() {
		return fund_num;
	}

	public void setFund_num(String fund_num) {
		this.fund_num = fund_num;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRadiochecked() {
		return radiochecked;
	}

	public void setRadiochecked(String radiochecked) {
		this.radiochecked = radiochecked;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the memberinter
	 */
	public Memberinter getMemberinter() {
		return memberinter;
	}

	/**
	 * @param Memberinter
	 *            the memberinter to set
	 */
	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : LJQ
	 * @date : Nov 8, 2014 2:36:50 PM
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (memberinter == null) {
			memberinter = new Memberinter();
		}
		String id = memberinter.getCust_id();
		if (!ValidateUtil.isDigital(id)) {
			memberinter = this.memberinterService.get(id);
		}
	}
}
