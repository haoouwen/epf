/*
 
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Md5;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Expressfund;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Memberuser;
import com.rbt.service.IAreaService;
import com.rbt.service.IExpressfundService;
import com.rbt.service.IInfocountService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IMemberuserService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员信息表action类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 09:38:55 CST 2014
 */
@Controller
public class MemberAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	private String CFG_MB_NOTALLOW = "cfg_mb_notallow";
	/*******************实体层********************/
	private Member member;
	public Memberuser memberuser;
	public Malllevelset malllevelset;
	public Memberfund memberfund;
	private Expressfund expressfund;
	public Memberinter memberinter;
	private Membercredit membercredit;
	/*******************业务层接口****************/
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	private IMalllevelsetService malllevelsetservice;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	private IExpressfundService expressfundService;
	@Autowired
	private IMembercreditService membercreditService;
	@Autowired
	private IInfocountService infocountService;
	@Autowired
	private IAreaService areaService;
	
	/*********************集合********************/
	public List memberList;
	public List malllevelsetsellList;
	public List malllevelsetbuyList;
	public List memberinterList;
	public List memberfundList;
	public List expressfundList;
	/*********************字段********************/
	public String oldcellphone;
	public String oldphone;
	public String olduser_name;
	public String is_update;
	public String cust_name_s;//传参变量
	public String store_code_s;//门店码
	public String oldemail;
	public String info_state_s;
	public String mem_type_s;
	public String logo_path;
	public String reason;
    public String buy_level;
    public String confirmpasswd;//确认密码 
    private static final String CFG_MB_ISAUDIT = "cfg_mb_isaudit";//注册是否需要人工审核
    public String is_nal="0";//判断是否内部员工
    
    public Map totalMemberMap = new HashMap();
    
    public Map totalAmountMap = new HashMap();
    
    //中国一级地区集合
    public List areaList;//地区
    
    public List areaMembernumList = new ArrayList();
    
	/**
	 * 方法描述：返回新增会员信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 0为卖家
		malllevelsetsellList = this.malllevelsetservice.getMemLevelList("0");
		// 1为买家
		malllevelsetbuyList = this.malllevelsetservice.getMemLevelList("1");
		return goUrl(ADD);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 5:30:50 PM
	 * @Method Description :添加，修改时的验证字段合法性
	 */
	private void commonCheck(){
		// 验证用户名
		if (ValidateUtil.isRequired(memberuser.getUser_name())) {
			this.addFieldError("memberuser.user_name", "用户名不能为空");
		} else {
			//if (ValidateUtil.isAlphasFirst(memberuser.getUser_name())) {
				//this.addFieldError("memberuser.user_name", "用户名以字母开头，由数字、字母、下划线组成");
			//} else {
				String notallow_name =SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
				// 需要过滤的会员注册名
				if(this.memberService.isAllow(memberuser.getUser_name(),CFG_MB_NOTALLOW)){
					this.addFieldError("memberuser.user_name", "本网站不允许注册此类用户名");
				} else {
					if (existsTitle(memberuser.getUser_name(), olduser_name, "memberuser", "user_name")) {
						this.addFieldError("memberuser.user_name", "该用户名已经存在,请重新输入");
					}
				}
			//}
		}
		if(is_update==null){
			// 验证密码
			if (ValidateUtil.isRequired(memberuser.getPasswd())) {
				this.addFieldError("memberuser.passwd", "密码不能为空");
			}
			if (ValidateUtil.isRequired(confirmpasswd)) {
				this.addFieldError("confirmpasswd", "确认密码不能为空");
			} 
		}
		
		if(memberuser.getPasswd()!=null && !memberuser.getPasswd().equals("")){
			if (ValidateUtil.isAlphasLimtLength(memberuser.getPasswd())) {
				this.addFieldError("memberuser.passwd", "密码只能由6-20个字母、数字、下划线组成");
			}
			if (!memberuser.getPasswd().equals(confirmpasswd)) {
				this.addFieldError("confirmpasswd", "两次输入不一致！");
			} 
		}
		
		// 检查该会员是否已经存在
		if (existsTitle(member.getCust_name(), oldinfotitle, "member", "cust_name")) {
			this.addFieldError("member.cust_name", "该会员已经存在,请重新输入");
		}
		
		// 限制Email只能注册一个帐号
		if (!ValidateUtil.isRequired(memberuser.getEmail()) && existsTitle(memberuser.getEmail(),oldemail, "memberuser", "email")) {
			this.addFieldError("memberuser.email", "该邮箱已被占用，请重新输入");
		}
		// 限制手机只能注册一个帐号
		if (!ValidateUtil.isRequired(memberuser.getCellphone()) && existsTitle(memberuser.getCellphone(),oldcellphone, "memberuser", "cellphone")) {
			this.addFieldError("memberuser.cellphone", "该手机已被被注册，请重新输入");
		}
		// 限制公司电话只能注册一个帐号
		if (!ValidateUtil.isRequired(memberuser.getPhone()) && existsTitle(memberuser.getPhone(),oldphone, "memberuser", "phone")) {
			this.addFieldError("memberuser.phone", "该公司电话已被被注册，请重新输入");
		}
		if (ValidateUtil.isRequired(memberuser.getBirthday())) {
		      memberuser.setBirthday(null);
		}
		memberuser.setIs_check_email("1");
		memberuser.setIs_check_mobile("1");
		super.commonValidateField(member);
		super.commonValidateField(memberuser);
	}
	
	
	/**
	 * 方法描述：新增会员信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 是否需要审核
		String isaduit = SysconfigFuc.getSysValue(CFG_MB_ISAUDIT);
		if ("0".equals(isaduit)) {
			member.setInfo_state("0");// 0:未审核
		} else {
			member.setInfo_state("1");// 1:审核通过
		}
		//验证合法性
		commonCheck();
		if (super.ifvalidatepass) {
			return add();
		}
		member.setLogo_path(SysconfigFuc.getSysValue("cfg_nopic"));
		//获取等级代码
		String cust_id = this.memberService.insertGetPk(member);
		this.memberuser.setCust_id(cust_id);
		String level_code = this.malllevelset.getLevel_code();
		malllevelset.setLevel_name(level_code);
		//等级名称
		String level_name = this.malllevelset.getLevel_name();
		malllevelset.setLevel_name(level_name);
		// 对密码进行加密
		getPassword();
		String user_id=this.memberuserService.insertGetPk(memberuser);
		//插入数据
		this.memberService.insertInit(cust_id);
		//初始化会员信誉对象
		Membercredit membercredit=new Membercredit();
		membercredit.setCust_id(cust_id);
		membercredit.setUser_id(user_id);
		membercredit.setBuy_num("0");
		membercredit.setSell_num("0");
		this.membercreditService.insert(membercredit);
		// 新增审核直通车数据操作流
		addAuditRecord(cust_id, "member", member.getInfo_state(), "新增会员");
		this.addActionMessage("新增会员信息成功！");
		this.member = null;
		return list();
	}

	/**
	 * 方法描述：修改会员信息表信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String cust_id = this.member.getCust_id();
		//验证合法性
		commonCheck();
		// 判断会员的密码是否有被填写,否则对新密码加密
		if (memberuser.getPasswd() == null || memberuser.getPasswd().equals("")) {
			Memberuser mu = this.memberuserService.get(memberuser.getUser_id());
			memberuser.setPasswd(mu.getPasswd());
		} else {
			getPassword();
		}
		if (super.ifvalidatepass) {
			return view();
		}
		// 新增审核会员数据操作流
		if (member.getInfo_state().equals("1")) {
			reason = "审核通过状态";
		}
		if (member.getInfo_state().equals("3") && reason.equals("")) {
			this.addFieldError("member.info_state", "您还未填写禁用理由！请点击禁用选项");
			return view();
		}
		String info_state = member.getInfo_state();
		addAuditRecord(cust_id, "member", info_state, reason);
		this.memberService.update(member);
	    if(!buy_level.equals(member.getBuy_level())){
	    	//获取买家等级对应的最低分
	    	malllevelset=this.malllevelsetservice.get(member.getBuy_level());
	    	//获取会员信誉信息
	    	membercredit=this.membercreditService.getByCustId(member.getCust_id());
	    	//等级改变，信誉随便改变
	    	membercredit.setBuy_num(malllevelset.getInter_lower());
	    	this.membercreditService.update(membercredit);
	    }
		//更新会员表
		if (memberuser != null && !memberuser.equals("")) {
			this.memberuserService.update(memberuser);
		}
		this.addActionMessage("修改会员信息成功！");
		if(is_nal.equals("0")){
		   return list();
		}else{
			return membernalList();
		}
		
	}

	
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 5:23:27 PM
	 * @Method Description :共用memberList
	 */
	public void commonMemList(Map pageMap){
		
		// 会员类型0表示商家，1表示买家
		//pageMap.put("mem_type", "1");
		if (mem_type_s != null && !mem_type_s.equals("")) {
			pageMap.put("mem_type", mem_type_s);
		}
		if (!ValidateUtil.isRequired(cust_name_s)) {
			pageMap.put("cust_name", cust_name_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
			//回选地区
			viewArea(area_attr_s);
		}
		if(!ValidateUtil.isRequired(store_code_s)){
			pageMap.put("membernum", store_code_s);//门店码
		}
		// 根据页面提交的条件找出信息总数
		int count = this.memberService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		memberList = this.memberService.getList(pageMap);
		memberList = ToolsFuc.replaceList(memberList,"");
	}
	

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {

		Map areaMap = new HashMap();
		areaMap.put("area_level", "2");
	    areaList = areaService.getList(areaMap);
		
		Map pageMap = new HashMap();
		// 审核状态1表示审核通过，3表示审核通过
		pageMap.put("info_state", "1,3");
		//过滤运营商信息
		pageMap.put("cust_id_no", "0");
		pageMap.put("buy_level_no","999");
		commonMemList(pageMap);
		//会员总数量
		totalMemberMap = infocountService.getTotalMember(new HashMap());	
		//会员消费总金额
		Map amountMap = new HashMap();
		amountMap.put("order_state", "7,8");
		totalAmountMap = infocountService.getTotalAmount(amountMap);

		
		return goUrl("index");
	}

	/**
	 * @author QJY
	 * @function 获取中国的二级地区
	 * @date 2015-08-09
	 * @throws Exception
	 */
	public String gerArea()throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Map areaMap = new HashMap();
		areaMap.put("up_area_id", areaIdStr);
		areaMap.put("area_level", "2");
		if(request.getParameter("area_attr_s")!=null && !"".equals(request.getParameter("area_attr_s"))){
			areaMap.put("area_id", request.getParameter("area_attr_s").toString());
		}
		areaList = this.areaService.getList(areaMap);
		String outStr = JsonUtil.list2json(areaList);
		out.print(outStr);
		//System.out.println(outStr+"11111=======");
		return null;
	}

	/**
	 * @author QJY
	 * @function 获取各个地区的会员数量
	 * @date 2015-08-07
	 * @return
	 * @throws Exception
	 */
	public String getAreaMember()throws Exception{
		
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Map map = new HashMap();
		map.put("up_area_id", areaIdStr);
		map.put("area_level", "2");
		if(request.getParameter("area_attr_s")!=null && !"".equals(request.getParameter("area_attr_s"))){
			map.put("area_id", request.getParameter("area_attr_s").toString());
		}
		areaList = this.areaService.getList(map);
		
		if(areaList!=null && areaList.size()>0){
			Map memberMap = new HashMap();
			memberMap.put("cust_id", "0");
			Map areaMemberMap = new HashMap();
			for(int i=0;i<areaList.size();i++){
				Map areaMap = (Map) areaList.get(i);
				if(areaMap!=null && areaMap.get("area_id")!=null){
				   memberMap.put("area_attr", areaMap.get("area_id"));
				}
				
				areaMemberMap = this.memberService.getAreaMember(memberMap);
				areaMembernumList.add(areaMemberMap);
			}
		}
		
		String outStr = JsonUtil.list2json(areaMembernumList);
		out.print(outStr);
		
		return null;
	}
	
	/**
	 * @author QJY
	 * @function 获取各个地区的会员消费总额
	 * @date 2015-08-08
	 * @return
	 * @throws Exception
	 */
	public String getAreaTotalAmount() throws Exception{
		
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Map map = new HashMap();
		map.put("order_state", "7,8");
		map.put("cust_id","0");
		if(request.getParameter("area_attr_s")!=null && !"".equals(request.getParameter("area_attr_s"))){
			map.put("area_attr", request.getParameter("area_attr_s").toString());
		}
		
		List areaTotalAmountList = memberService.getAreaTotalAmountList(map);
		
		areaTotalAmountList = ToolsFuc.replaceList(areaTotalAmountList, "");
		
		String outStr = JsonUtil.list2json(areaTotalAmountList);
		out.print(outStr);
		
		System.out.println(outStr+"=======");
		
		return null;
		
	}
	
	
	/**
	 * @author Administrator QJY
	 * @date 2015-08-10
	 * @function 获取饼图所需的数据
	 * @return
	 * @throws Exception
	 */
	public String getPieAreaMember() throws Exception {

		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//根据条件获取中国二级地区
		Map map = new HashMap();
		map.put("up_area_id", areaIdStr);
		map.put("area_level", "2");
		if(request.getParameter("area_attr_s")!=null && !"".equals(request.getParameter("area_attr_s"))){
			map.put("area_id", request.getParameter("area_attr_s").toString());
		}
		areaList = this.areaService.getList(map);
		//构造地区一维数组
		StringBuffer areasb = new StringBuffer();
		String areaProvince="";
		Object areaProvinces[]={};
		StringBuffer membersb = new StringBuffer();
		String areaMember="";
		Integer areaMembers[] = {};
		if(areaList!=null && areaList.size()>0){
		   for(int i=0;i<areaList.size();i++){
				Map areaMap = (Map) areaList.get(i);
				if(areaMap!=null && areaMap.get("area_name")!=null){
					areasb.append(areaMap.get("area_name").toString());
					areasb.append(",");
			    }
		 }
	     areasb.deleteCharAt(areasb.length()-1);
	     areaProvince = areasb.toString();	
	     areaProvinces = areaProvince.split(",");
			
		Map memberMap = new HashMap();
		memberMap.put("cust_id", "0");
		Map areaMemberMap = new HashMap();
		for(int i=0;i<areaList.size();i++){
			Map areaMap = (Map) areaList.get(i);
			if(areaMap!=null && areaMap.get("area_id")!=null){
			   memberMap.put("area_attr", areaMap.get("area_id"));
			}
			
			areaMemberMap = this.memberService.getAreaMember(memberMap);
			areaMembernumList.add(areaMemberMap);
			}
		}
		
		if(areaMembernumList!=null && areaMembernumList.size()>0){
			   for(int i=0;i<areaMembernumList.size();i++){
					Map memberMap = (Map) areaMembernumList.get(i);
					if(memberMap!=null && memberMap.get("area_member_num")!=null){
						membersb.append(memberMap.get("area_member_num").toString());
						membersb.append(",");
				    }
			   }
			   membersb.deleteCharAt(membersb.length()-1);
			   areaMember = membersb.toString();	
			   String  areaMem[] = areaMember.split(",");
			   Integer array[]=new Integer[areaMem.length];
			   for(int j=0;j<array.length;j++){
				   array[j]=Integer.valueOf(areaMem[j]);				   
			   } 
			   areaMembers = array;
		
		}

		if((areaProvinces!=null && areaProvinces.length>0) && (areaMembers!=null && areaMembers.length>0)){
			
			Object karray[][] = new Object[areaProvinces.length][2];
			for (int i = 0; i < areaProvinces.length; i++) {
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						karray[i][j] = areaProvinces[i];
					}
					if (j == 1) {
						karray[i][j] = areaMembers[i];
					}

				}
			}
			String outStr = JsonUtil.array2json(karray);
			out.print(outStr);
			//System.out.println(outStr);
		}

		return null;
	}
	 
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String membernalList() throws Exception {
		Map pageMap = new HashMap();
		// 审核状态1表示审核通过，3表示审核通过
		pageMap.put("info_state", "1,3");
		//过滤运营商信息
		pageMap.put("cust_id_no", "0");
		pageMap.put("buy_level","999");
		commonMemList(pageMap);
		return goUrl("memberindex");
	}

	/**
	 * @author : LSQ
	 * @date : Jan 08, 2014 01:11:01 PM
	 * @Method Description :审核会员列表
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("info_state", "0,2");
		commonMemList(pageMap);
		return goUrl("auditindex");
	}

	/**
	 * @author LSQ
	 * @date : Jan 23, 2014 10:43:25 AM
	 * @Method Description :删除审核会员
	 */
	public String auditdelete() throws Exception {
		commonDelete();
		return auditList();
	}


	/**
	 * 方法描述：删除会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDelete();
		return list();
	}
	
	/**
	 * 方法描述：删除会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delMember() throws Exception {
		commonDelete();
		return membernalList();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 5:26:29 PM
	 * @Method Description :公用删除方法
	 */
	private void commonDelete(){
		boolean flag = this.memberService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除会员信息成功！");
		}else{
			this.addActionMessage("删除会员信息失败！");
		}
	}

	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 5:18:42 PM
	 * @Method Description :查看会员审核信息
	 */
	public String audit() throws Exception {
		String id = this.member.getCust_id();
		if (id == null || id.equals("")) {
			return auditList();
		} else {
			member = this.memberService.get(id);
			memberuser = this.memberuserService.getPKByCustID(id);
			if (memberuser == null || memberuser.equals("")) {
				return auditList();
			}
		}
		// 获取审核列表
		searchAuditList(id, "member");
		// 0为卖家
		malllevelsetsellList =this.malllevelsetservice.getMemLevelList("0");
		// 1为买家
		malllevelsetbuyList =this.malllevelsetservice.getMemLevelList("1");
		return goUrl(AUDIT);
	}

	/**
	 * @author : LSQ
	 * @date : Jan 09, 2014 11:00:01 AM
	 * @Method Description :会员审核状态方法
	 */
	public String auditState() throws Exception {
		String id = this.member.getCust_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		if(!ValidateUtil.isRequired(member.getInfo_state())&&member.getInfo_state().equals("0")){
			this.addFieldError("member.info_state", "请选择审核状态！");
			return audit();
		}
		//获取审核状态
		String info_state = this.member.getInfo_state();
		// 新增审核会员数据操作流 1：审核通过 2：审核不通过
		if(ValidateUtil.isRequired(info_state)){
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
		// 获取数据库对象
		Member member = this.memberService.get(id);
		if (info_state!= null && !info_state.equals("")){
			// 设置状态值
			member.setInfo_state(info_state);
		}
		// 插入审核历史记录表
		addAuditRecord(id, "member", info_state, reason);
		//插入数据
		this.memberService.insertInit(id);
		// 更新数据库会员列表
		this.memberService.update(member);
		this.addActionMessage("审核会员设置成功");
		return auditList();
	}
	

	
	/**
	 * 方法描述：根据主键找出会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.member.getCust_id();
		// 根据cust_id找出memberuser对象
		if (id != null && !id.equals("")) {
			member = this.memberService.get(id);
			memberuser = this.memberuserService.getPKByCustID(id);
		}
		if (memberuser == null) {
			memberuser = new Memberuser();
		}
		if(member==null){
			member = new Member();
		}
		// 0为卖家s
		malllevelsetsellList = this.malllevelsetservice.getMemLevelList("0");
		// 1为买家
		malllevelsetbuyList =this.malllevelsetservice.getMemLevelList("1");
		// 获取审核列表
		searchAuditList(id, "member");
		return goUrl(VIEW);
	}

	/**
	 * @Method Description :跳转到会员更新头像
	 * @author : HZX
	 * @date : Mar 12, 2014 1:37:01 PM
	 */
	public String photoview() throws Exception {
		String id = this.member.getCust_id();
		if (id == null || id.equals("")) {
			member = new Member();
		} else {
			member = this.memberService.get(id);
		}
		return goUrl("updateMemberPhoto");
	}

	/**
	 * @Method Description :更新会员头像
	 * @author : HZX
	 * @date : Mar 12, 2014 1:56:53 PM
	 */
	public String updatePhoto() throws Exception {
		if (logo_path == null || "".equals(logo_path)) {
			this.addFieldError("logo_path", "请上传头像");
			return goUrl("updateMemberPhoto");
		}
		member = memberService.get(this.session_cust_id);
		member.setLogo_path(logo_path);
		memberService.update(member);
		this.addActionMessage("头像修改成功");
		return goUrl("updateMemberPhoto");
	}

	/**
	 * @author LSQ
	 * @date : Jan 30, 2014 12:52:38 PM
	 * @Method Description :对密码进行加密
	 */
	public void getPassword() {
		// 对密码进行加密
		String passwd = this.memberuser.getPasswd();
		passwd = Md5.getMD5(passwd.getBytes());
		memberfund =this.memberfundService.get(this.session_cust_id);
		if(memberfund!=null && !"0".equals(this.session_cust_id)){
			String pay_psw=memberfund.getPay_passwd();
			//支付密码为空就不进行校验
			if(!"".equals(pay_psw)&&pay_psw.equals(passwd)){
				this.addFieldError("memberuser.passwd", "登陆密码不能和支付密码一样！");
			}else {
				this.memberuser.setPasswd(passwd);
			}
		}else {
			this.memberuser.setPasswd(passwd);
		}
		
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (member == null) {
			member = new Member();
		}
		String id = this.member.getCust_id();
		if (!this.validateFactory.isDigital(id)) {
			member = this.memberService.get(id);
		}
	}
	
	/**
	 * @return the MemberList
	 */
	public List getMemberList() {
		return memberList;
	}

	/**
	 * @param memberList
	 *            the MemberList to set
	 */
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	public String getOldemail() {
		return oldemail;
	}

	public void setOldemail(String oldemail) {
		this.oldemail = oldemail;
	}

	public String getMem_type_s() {
		return mem_type_s;
	}

	public void setMem_type_s(String mem_type_s) {
		this.mem_type_s = mem_type_s;
	}

	public Malllevelset getMalllevelset() {
		return malllevelset;
	}

	public void setMalllevelset(Malllevelset malllevelset) {
		this.malllevelset = malllevelset;
	}

	public List getMalllevelsetsellList() {
		return malllevelsetsellList;
	}

	public void setMalllevelsetsellList(List malllevelsetsellList) {
		this.malllevelsetsellList = malllevelsetsellList;
	}

	public List getMalllevelsetbuyList() {
		return malllevelsetbuyList;
	}

	public void setMalllevelsetbuyList(List malllevelsetbuyList) {
		this.malllevelsetbuyList = malllevelsetbuyList;
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getInfo_state_s() {
		return info_state_s;
	}

	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public Expressfund getExpressfund() {
		return expressfund;
	}

	public void setExpressfund(Expressfund expressfund) {
		this.expressfund = expressfund;
	}

	public List getExpressfundList() {
		return expressfundList;
	}

	public void setExpressfundList(List expressfundList) {
		this.expressfundList = expressfundList;
	}

	public List getMemberfundList() {
		return memberfundList;
	}

	public void setMemberfundList(List memberfundList) {
		this.memberfundList = memberfundList;
	}

	public Memberinter getMemberinter() {
		return memberinter;
	}

	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	public List getMemberinterList() {
		return memberinterList;
	}

	public void setMemberinterList(List memberinterList) {
		this.memberinterList = memberinterList;
	}

	public String getLogo_path() {
		return logo_path;
	}

	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}
	
	public Member getMember() {
		return member;
	}

	/**
	 * @param Member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}


}
