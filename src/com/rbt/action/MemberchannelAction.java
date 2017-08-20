/*
 
 * Package:com.rbt.action
 * FileName: MemberchannelAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Memberchannel;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberchannelService;

/**
 * @function 功能 记录会员企业站栏目信息action类
 * @author 创建人 CYC
 * @date 创建日期 Fri Aug 26 16:21:41 CST 2014
 */
@Controller
public class MemberchannelAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Memberchannel memberchannel;
	
	/*******************业务层接口****************/
	@Autowired
	public IMemberchannelService memberchannelService;
	@Autowired
	public IMemberService memberService;
	
	/*********************集合********************/
	public List memberchannelList;//会员企业站栏目信息
	
	/*********************字段********************/
	public String member_memberchannel_id;
	public String member_sort;
	public String member_name;
	public String member_num;
	public String ch_name;//栏目名
	public String ch_code;//栏目编码
	public String ch_type;//0：菜单 1：侧栏 2：首页主栏
	public String is_dis;
	public String sort_no;//排序
	public String ch_num;
	public String meta_keyword;
	public String meta_desc;
	public String ch_content;
	public String ch_id;
	public String ch_name_s;
	public String is_dis_s;
	public String ch_type_s;//0：菜单 1：侧栏 2：首页主栏
	public String is_dis_update;
	public static final String activate = "/member_Memberconfig_add.action";// 激活企业站跳转页面

	/**
	 * @author : LJQ
	 * @date : Jul 13, 2014 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum() {
		// 查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.memberchannelService.getCount(tmap);
		return false;
	}

	/**
	 * 方法描述：返回新增记录会员企业站栏目信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员企业站栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if ("3".equals(this.memberchannel.getCh_type())) {
			this.addFieldError("memberchannel.ch_type", "请选择栏目类型");
		}
		// 设置属于B2B 或者B2C
		memberchannel.setCust_id(this.session_cust_id);
		memberchannel.setSys_ch("1");
		// 发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if (controlInfoNum()) {
				return add();
			}
		}
		// 字段验证
		super.commonValidateField(memberchannel);
		if (super.ifvalidatepass) {
			return add();
		}
		this.memberchannelService.insert(memberchannel);
		this.addActionMessage("新增企业站栏目成功");
		this.memberchannel = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员企业站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		if ("3".equals(this.memberchannel.getCh_type())) {
			this.addFieldError("memberchannel.ch_type", "请选择栏目类型");
		}
		// 字段验证
		super.commonValidateField(memberchannel);
		if (super.ifvalidatepass) {
			return view();
		}
		this.memberchannelService.update(memberchannel);
		this.addActionMessage("修改企业站栏目成功");
		return list();
	}

	/**
	 * 方法描述：删除记录会员企业站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memberchannelService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除企业站栏目成功");
		}else{
			this.addActionMessage("删除企业站栏目失败");
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
		String cust_id = this.session_cust_id;
		Map pageMap = new HashMap();
		pageMap.put("cust_id", cust_id);
		if (ch_name_s != null && !ch_name_s.equals(""))
			pageMap.put("ch_name", ch_name_s);
		if (is_dis_s != null && !is_dis_s.equals(""))
			pageMap.put("is_dis", is_dis_s);
		if (ch_type_s != null && !ch_type_s.equals(""))
			pageMap.put("ch_type", ch_type_s);

		// 通过用户名找出用户信息
		memberchannelList = this.memberchannelService.getList(pageMap);
		// 过滤地区
		// 根据页面提交的条件找出信息总数
		int count = this.memberchannelService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		memberchannelList = this.memberchannelService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录会员企业站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if (memberchannel.getCust_id() != null) {
			if (accessControl(memberchannel.getCust_id())) {
				return list();
			}
		}
		return goUrl(VIEW);
	}
	/**
	 * @author : HZX
	 * @date : May 3, 2014 4:40:44 PM
	 * @Method Description :显示
	 */
	public String updateIsshow() throws Exception {
		updateShow();
		return list();
	}

	/**
	 * @author :HZX
	 * @date : May 3, 2014 4:40:51 PM
	 * @Method Description :隐藏
	 */
	public String updateUnshow() throws Exception {
		updateShow();
		return list();
	}

	/**
	 * @author : HZX
	 * @date : May 3, 2014 4:41:01 PM
	 * @Method Description :显示、隐藏公共调用方法
	 */
	public void updateShow() {
		boolean flag = this.memberchannelService.updateBatchState(chb_id, state_val, "ch_id", "is_dis");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("显示栏目成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("隐藏栏目成功");
			}
		}else{
			this.addActionMessage("操作栏目失败");
		}
	}
	/**
	 * 方法描述：批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateChannel() throws Exception {
		if(member_memberchannel_id==null || member_sort==null ||
				member_name==null || member_num==null){
			return list();
		}
		this.memberchannelService.updatechannel(member_memberchannel_id, member_sort, member_name, member_num);
		this.addActionMessage("批量修改成功");
		return list();
	}

	public List getMemberchannelList() {
		return memberchannelList;
	}

	public void setMemberchannelList(List memberchannelList) {
		this.memberchannelList = memberchannelList;
	}

	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public String getCh_code() {
		return ch_code;
	}

	public void setCh_code(String ch_code) {
		this.ch_code = ch_code;
	}

	public String getCh_type() {
		return ch_type;
	}

	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}

	public String getIs_dis() {
		return is_dis;
	}

	public void setIs_dis(String is_dis) {
		this.is_dis = is_dis;
	}

	public String getSort_no() {
		return sort_no;
	}

	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

	public String getCh_num() {
		return ch_num;
	}

	public void setCh_num(String ch_num) {
		this.ch_num = ch_num;
	}

	public String getMeta_keyword() {
		return meta_keyword;
	}

	public void setMeta_keyword(String meta_keyword) {
		this.meta_keyword = meta_keyword;
	}

	public String getMeta_desc() {
		return meta_desc;
	}

	public void setMeta_desc(String meta_desc) {
		this.meta_desc = meta_desc;
	}

	public String getCh_content() {
		return ch_content;
	}

	public void setCh_content(String ch_content) {
		this.ch_content = ch_content;
	}

	public String getCh_id() {
		return ch_id;
	}

	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}

	public String getIs_dis_update() {
		return is_dis_update;
	}

	public void setIs_dis_update(String is_dis_update) {
		this.is_dis_update = is_dis_update;
	}

	public String getMember_sort() {
		return member_sort;
	}

	public void setMember_sort(String member_sort) {
		this.member_sort = member_sort;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_num() {
		return member_num;
	}

	public void setMember_num(String member_num) {
		this.member_num = member_num;
	}

	/**
	 * @return the memberchannel
	 */
	public Memberchannel getMemberchannel() {
		return memberchannel;
	}

	/**
	 * @param Memberchannel
	 *            the memberchannel to set
	 */
	public void setMemberchannel(Memberchannel memberchannel) {
		this.memberchannel = memberchannel;
	}

	public String getMember_memberchannel_id() {
		return member_memberchannel_id;
	}

	public void setMember_memberchannel_id(String member_memberchannel_id) {
		this.member_memberchannel_id = member_memberchannel_id;
	}

	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : LJQ
	 * @date : Nov 8, 2014 2:36:50 PM
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (memberchannel == null) {
			memberchannel = new Memberchannel();
		}
		String id = memberchannel.getCh_id();
		if (!ValidateUtil.isDigital(id)) {
			memberchannel = this.memberchannelService.get(id);
		}
	}
}
