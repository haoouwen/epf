/*
 
 * Package:com.rbt.action
 * FileName: FundtocashAction.java 
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
import com.rbt.function.ToolsFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Fundtocash;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberuser;
import com.rbt.model.Sysuser;
import com.rbt.service.ICommparaService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IFundtocashService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.ISysfundService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 会员资金提现记录action类
 * @author 创建人 CYC
 * @date 创建日期 Wed Jul 13 09:52:27 CST 2014
 */
@Controller
public class FundtocashAction extends AdminBaseAction implements Preparable{

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Memberuser memberuser;
	public Sysuser sysuser;
	public Fundtocash fundtocash;
	public Memberfund memberfund;
	public Fundhistory fundhistory;

	/*******************业务层接口****************/
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IFundtocashService fundtocashService;
	@Autowired
	public ISysuserService sysuserService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	private ISysfundService sysfundService;

	/*********************集合******************/
	public List fundtocashList;//会员资金提现记录信息集合
	public List commparaList_value;//参照表
	public List commparaList;//参照表

	/*********************字段******************/
	public String order_state_s;//状态
	public String getcash_type_s;//收款方式
	public String cust_name;//会员名称
	public String cuts_name_s;//会员名称
	public String getcash_type;//收款方式
	public String order_state;//状态
	public String starttime_s;//时间段开始
	public String endtime_s;//时间段结束
	public String sgetcash_type;//收款方式参数
	public String scust_name;//申请人
	public String username;//管理员名称
	

/**
 * @author HZX
 * @Method Description :提现申请
 * @date : Jan 28, 2014 1:06:47 PM
 */
	public String add() throws Exception {
		// 绑定收款方式下拉列表
		getcommpara();
		//查看余额余额
		if (!this.session_cust_id.equals("")) {
			memberfund = this.memberfundService.get(this.session_cust_id);
		}
		return goUrl(ADD);
	}
	/**
	 * 方法描述：新增会员资金提现记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		Double funnum=0.00;
        if(fundtocash.getFund_num()!=null){
        	funnum=fundtocash.getFund_num();
        	if(funnum==0.00){
        		 this.addFieldError("fundtocash.fund_num", "请输入有效的金额");
        	}
        }else{
        	 this.addFieldError("fundtocash.fund_num", "提取金额不能为空");
        }
		if ("0".equals(this.fundtocash.getGetcash_type())) {
			this.addFieldError("fundtocash.getcash_type", "付款方式不能为空");
		}
		if (!"2".equals(this.fundtocash.getGetcash_type())){
			if(ValidateUtil.isRequired(this.fundtocash.getAccount()) || ValidateUtil.isRequired(this.fundtocash.getAccount())) {
			this.addFieldError("fundtocash.account", "不能为空且只能输入数字");
			}
			if(ValidateUtil.isRequired(this.fundtocash.getAccount_name())){
			this.addFieldError("fundtocash.account_name", "账号名字不能为空");
			}
		}
		Map pageMap = new HashMap();
		pageMap.put("cust_name", cust_name);
		
		//判断当前登录的账号类型，执行相应的操作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			List custList = this.memberService.getList(pageMap);
			if (custList == null || custList.size() <= 0) {
				this.addFieldError("cust_name", "用户名不存在,请重新输入");
				return add();
			}
			Map cust_value = (HashMap)custList.get(0);
			fundtocash.setCust_id(cust_value.get("cust_id").toString());
		} else {
			fundtocash.setCust_id(this.session_cust_id);
		}
		Memberfund mdf=this.memberfundService.get(this.session_cust_id);
		if(funnum>Double.parseDouble(mdf.getUse_num())){
			this.addFieldError("fundtocash.fund_num","提现的余额不能超过可用余额！");
			fundtocash.setFund_num(null);
			return add();
		}
		fundtocash.setUser_id(this.session_user_id);
		fundtocash.setOrder_state("0");
		fundtocash.setRemark("余额提现");
		//字段验证
		super.commonValidateField(fundtocash);
		if(super.ifvalidatepass){
			return add();
		}
		//当前会员的账号资金
		memberfund=memberfundService.get(this.session_cust_id);
		//平台资金
		//判断两个对象不能为空
		if(memberfund!=null){
			memberfund.setFund_num((Double.parseDouble(memberfund.getFund_num())-fundtocash.getFund_num())+"");
			memberfund.setUse_num(""+(Double.parseDouble(memberfund.getUse_num())-fundtocash.getFund_num()));
			this.memberfundService.update(memberfund);
			this.sysfundService.outAndInNum(fundtocash.getFund_num(),0);
			this.fundtocashService.insert(fundtocash);
			//资金异动表添加数据
			fundhistory=new Fundhistory();
			fundhistory.setCust_id(this.session_cust_id);
			fundhistory.setFund_out(fundtocash.getFund_num());
			fundhistory.setFund_in(0.00);
			fundhistory.setBalance(Double.parseDouble(memberfund.getUse_num()));
			fundhistory.setUser_id(this.session_user_id);
			fundhistory.setReason("申请余额提现，等待审核！");
			this.fundhistoryService.insert(fundhistory);
			this.addActionMessage("提交成功，等待审核！");
		}else{
			this.addActionMessage("资金提现失败！");
		}
		this.fundtocash = null;
		return add();
	}

	/**
	 * 方法描述：修改会员资金提现记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (sgetcash_type != null && !sgetcash_type.equals("")) {
			Map pageMap_vlaue = new HashMap();
			pageMap_vlaue.put("para_key", sgetcash_type);
			commparaList_value = this.commparaService.getList(pageMap_vlaue);
			if (commparaList_value != null && commparaList_value.size() > 0) {
				HashMap valueMap =  (HashMap) commparaList_value.get(0);
				String module_type_v = valueMap.get("para_value").toString();
				fundtocash.setGetcash_type(module_type_v);
			}
		}

		this.fundtocash.setUser_id((String) getSession().getAttribute(
				Constants.USER_ID));
		
		//字段验证
		super.commonValidateField(fundtocash);
		if(super.ifvalidatepass){
			return view();
		}
		this.fundtocashService.update(fundtocash);
		this.addActionMessage("修改会员资金提现信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员资金提现记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.fundtocashService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除资金提现信息成功");
		}else{
			this.addActionMessage("删除资金提现信息失败");
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
         commonList("1");
		//查看余额余额
		if (!this.session_cust_id.equals("")) {
			memberfund = this.memberfundService.get(this.session_cust_id);
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员资金提现记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id=fundtocash.getTrade_id();
        if(id == null || "".equals(id)){
        	return list();
        }
		
		Map map_value = new HashMap();
		map_value.put("para_code", "getcash_type");
		map_value.put("para_value", fundtocash.getGetcash_type());
		commparaList_value = commparaService.getList(map_value);
        if(commparaList_value!=null&&commparaList_value.size()>0){
			Map map_getcash = (HashMap) commparaList_value.get(0);
			sgetcash_type = map_getcash.get("para_key").toString();
        }
		
		// 绑定收款方式下拉列表
		getcommpara();
		
		// 申请人
		Map map_id = new HashMap();
		map_id.put("trade_id", id);
		fundtocashList = this.fundtocashService.getList(map_id);
		Map map_sys=new HashMap();
		if(fundtocashList!=null&&fundtocashList.size()>0){
			// 获取之前操作人
		    map_sys = (HashMap) fundtocashList.get(0);
		}
		
		if(map_sys!=null&&map_sys.get("user_id")!=null){
			sysuser = sysuserService.get(map_sys.get("user_id").toString());
		}
		if (sysuser != null) {
			username = sysuser.getUser_name();
		} else {
			memberuser = memberuserService.get(map_sys.get("user_id").toString());
			username = memberuser.getUser_name();
		}
		Map map_tcustname = new HashMap();
		if(fundtocashList!=null&&fundtocashList.size()>0){
			map_tcustname = (HashMap) fundtocashList.get(0);
			if(map_tcustname!=null&&map_tcustname.get("cust_name")!=null){
				scust_name = map_tcustname.get("cust_name").toString();
			}
		}
		return goUrl(VIEW);
	}
	public void getcommpara(){
		// 收款方式
		Map map = new HashMap();
		map.put("para_code", "getcash_type");
		commparaList = commparaService.getList(map);
	}
	/**
	 * @author : HZX
	 * @date : Jan 05, 2014 04:06:01 PM
	 * @Method Description :审核提现设置列表
	 */
	public String auditList() throws Exception {
		commonList("2");
		return goUrl("auditindex");
	}
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 4:28:31 PM
	 * @Method Description：查询核心方法
	 */
	 public void commonList(String order_type){
		// 绑定收款方式下拉列表
			getcommpara();

			// 页面搜索提交的参数
			Map pageMap = new HashMap();
			
			// 操作者为会员则默认加入搜索条件
			if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				pageMap.put("cust_id", this.session_cust_id);
			}

			if (cuts_name_s != null && !cuts_name_s.equals("")) {
				pageMap.put("cust_name", cuts_name_s);
			}

			if (order_state != null && !order_state.equals("")) {
				pageMap.put("order_state", order_state);
			}else if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				pageMap.put("order_state","0,1,2");
			}else if("1".equals(order_type)){
				pageMap.put("order_state","1,2");
			}else if("2".equals(order_type)){
				pageMap.put("order_state","0");
			}

			if (starttime_s != null && !starttime_s.equals("")) {
				pageMap.put("starttime", starttime_s);
			}

			if (endtime_s != null && !endtime_s.equals("")) {
				pageMap.put("endtime", endtime_s);
			}

			if (getcash_type_s != null && !getcash_type_s.equals("")) {
				pageMap.put("getcash_type", getcash_type_s);
			}
			//去除掉运营商在memberinter表中的cust_id
			pageMap.put("audit_cust_id", "0");
			//过滤地区
			pageMap=super.areafilter(pageMap);
			
			// 根据页面提交的条件找出信息总数
			int count = this.fundtocashService.getCount(pageMap);

			//分页插件
			pageMap = super.pageTool(count,pageMap);
			fundtocashList = this.fundtocashService.getList(pageMap);
			fundtocashList = ToolsFuc.replaceList(fundtocashList, "getcash_type");
	 }
	/**
	 * @author : HZX
	 * @date : Jan 05, 2014 04:06:01 PM
	 * @Method Description :审核提现设置
	 */
	public String audit() throws Exception {
		String id=fundtocash.getTrade_id();
        if(id == null || "".equals(id)){
        	return auditList();
        }
		
		Map map_value = new HashMap();
		map_value.put("para_code", "getcash_type");
		map_value.put("para_value", fundtocash.getGetcash_type());
		commparaList_value = commparaService.getList(map_value);
        if(commparaList_value!=null&&commparaList_value.size()>0){
			Map map_getcash = (HashMap) commparaList_value.get(0);
			if(map_getcash!=null&&map_getcash.get("para_key")!=null){
				sgetcash_type = map_getcash.get("para_key").toString();
			}
        }
		
		// 绑定收款方式下拉列表
		getcommpara();
		
		// 申请人
		Map map_id = new HashMap();
		map_id.put("trade_id", id);
		fundtocashList = this.fundtocashService.getList(map_id);
		Map map_sys=new HashMap ();
		if(fundtocashList!=null&&fundtocashList.size()>0){
			// 获取之前操作人
			 map_sys = (HashMap) fundtocashList.get(0);
		}
		if(map_sys!=null&&map_sys.get("user_id")!=null)
		sysuser = sysuserService.get(map_sys.get("user_id").toString());
		if (sysuser != null) {
			username = sysuser.getUser_name();
		} else {
			memberuser = memberuserService.get(map_sys.get("user_id").toString());
			username = memberuser.getUser_name();
		}

		Map map_tcustname = new HashMap();
		if(fundtocashList!=null&&fundtocashList.size()>0){
			map_tcustname = (HashMap) fundtocashList.get(0);
		}
		if(map_tcustname!=null&&map_tcustname.get("cust_name")!=null){
			scust_name = map_tcustname.get("cust_name").toString();
		}
		return goUrl("audit");
	}
	/**
	 * @author : HZX
	 * @date : Jan 05, 2014 04:06:01 PM
	 * @Method Description :审核提现设置
	 */
	public String auditState() throws Exception {
		String order_state = "";
		String id = this.fundtocash.getTrade_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if(ValidateUtil.isRequired(fundtocash.getOrder_state())||fundtocash.getOrder_state().equals("0")){
			this.addFieldError("fundtocash.order_state", "请选择审核状态！");
			return audit();
		}
	
		// 获取数据库对象
		Fundtocash gd = this.fundtocashService.get(id);
		if (this.fundtocash.getOrder_state() != null
				&& !this.fundtocash.getOrder_state().equals("")) {
			order_state = this.fundtocash.getOrder_state();
			// 设置状态值
			gd.setOrder_state(order_state);
		}
		Memberfund md=this.memberfundService.get(gd.getCust_id());
		//资金异动表添加数据
		fundhistory=new Fundhistory();
		fundhistory.setCust_id(fundtocash.getCust_id());
		fundhistory.setIn_date(fundtocash.getIn_date());
		fundhistory.setUser_id(this.session_user_id);
		fundhistory.setReason(fundtocash.getRemark());
		if(order_state.equals("1")){
				fundhistory.setFund_out(fundtocash.getFund_num());
				fundhistory.setFund_in(0.00);
		}else{
			if( md!=null){
				//进行数据操作，总资金
				md.setFund_num((Double.parseDouble(md.getFund_num())+gd.getFund_num())+"");
				//可用资金
				md.setUse_num((Double.parseDouble(md.getUse_num())+gd.getFund_num())+"");
				fundhistory.setFund_out(0.00);
				fundhistory.setFund_in(fundtocash.getFund_num());
				this.sysfundService.outAndInNum(gd.getFund_num(),1);
				this.memberfundService.update(md);
			}
		}
		if(md!=null){
			fundhistory.setBalance(Double.parseDouble(md.getFund_num()));
		}
		// 更新数据库供应列表
		this.fundtocashService.update(gd);
		this.fundhistoryService.insert(fundhistory);
		this.addActionMessage("审核提现设置成功");
		return auditList();
	}
	/*
	 * 方法描述：删除提现审核息信息
	 * @return
	 * @throws Exception
	 */
	public String auditdelete() throws Exception {
		String id = this.fundtocash.getTrade_id();
		id = id.replace(" ", "");
		this.fundtocashService.delete(id);
		this.addActionMessage("删除提现审核信息成功！");
		return auditList();
	}
	/**
	 * @return the FundtocashList
	 */
	public List getFundtocashList() {
		return fundtocashList;
	}

	/**
	 * @param fundtocashList
	 *            the FundtocashList to set
	 */
	public void setFundtocashList(List fundtocashList) {
		this.fundtocashList = fundtocashList;
	}

	public List getCommparaList_value() {
		return commparaList_value;
	}

	public void setCommparaList_value(List commparaList_value) {
		this.commparaList_value = commparaList_value;
	}

	public String getOrder_state_s() {
		return order_state_s;
	}

	public void setOrder_state_s(String order_state_s) {
		this.order_state_s = order_state_s;
	}

	public String getGetcash_type_s() {
		return getcash_type_s;
	}

	public void setGetcash_type_s(String getcash_type_s) {
		this.getcash_type_s = getcash_type_s;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCuts_name_s() {
		return cuts_name_s;
	}

	public void setCuts_name_s(String cuts_name_s) {
		this.cuts_name_s = cuts_name_s;
	}

	public String getGetcash_type() {
		return getcash_type;
	}

	public void setGetcash_type(String getcash_type) {
		this.getcash_type = getcash_type;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public String getSgetcash_type() {
		return sgetcash_type;
	}

	public void setSgetcash_type(String sgetcash_type) {
		this.sgetcash_type = sgetcash_type;
	}

	public String getScust_name() {
		return scust_name;
	}

	public void setScust_name(String scust_name) {
		this.scust_name = scust_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public Sysuser getSysuser() {
		return sysuser;
	}

	public void setSysuser(Sysuser sysuser) {
		this.sysuser = sysuser;
	}
	
	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}
	
	/**
	 * @return the fundtocash
	 */
	public Fundtocash getFundtocash() {
		return fundtocash;
	}

	/**
	 * @param Fundtocash
	 *            the fundtocash to set
	 */
	public void setFundtocash(Fundtocash fundtocash) {
		this.fundtocash = fundtocash;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(fundtocash == null){
			fundtocash = new Fundtocash();
		}
		String id = fundtocash.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			fundtocash = this.fundtocashService.get(id);
		}
		System.out.println(fundtocash);
	}

}
