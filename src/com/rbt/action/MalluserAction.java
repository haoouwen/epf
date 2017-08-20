/*
 
 * Package:com.rbt.action
 * FileName: MemberuserAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Memrole;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMemroleService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员信息表action类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 15:56:45 CST 2014
 */
@Controller
public class MalluserAction extends AdminBaseAction implements Preparable {
	private static final String MESSAGE_VALUE = "1";
	private static final long serialVersionUID = 1L;
	private static final String CFG_MB_NOTALLOW = "cfg_mb_notallow";
	/*******************实体层********************/
	private Memberuser memberuser;
	private Memrole memrole;
	
	/*******************业务层接口****************/
	@Autowired
	private IMemberuserService memberuserService;
	@Autowired
	private IMemroleService memroleService;
	@Autowired
	private IMemberService memberService;
	/*********************集合********************/
	public List memberuserList;
	public List rolecodeList;
	
	/*********************字段********************/
	public String message;//提示信息
	public String newpasswd;
	public String passwd;
	public String confirmpasswd;
	public String user_name;
	public String user_id;
	public String oldEmail;
	public String oldUserName;
	public String flag = "1";//标识为修改

	/**
	 * 方法描述：返回新增会员信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map maplist = new HashMap();
		maplist.put("mem_cust_id", this.session_cust_id);
		rolecodeList = this.memroleService.getList(maplist);
		// 判断该会员类型是哪种，member_type为会员类型，而user_type="1"为admin_type是管理员
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			user_name = this.session_user_name;
		}
		getSession().removeAttribute("is_joinus");

		return goUrl(ADD);
	}

	/**
	 * @Method Description :新增、更新校验
	 * @author : HZX
	 * @date : May 7, 2014 9:47:50 AM
	 */
	private void commonCheck() {
		memberuser.setCust_id(this.session_cust_id);
		// 验证用户名
		if (ValidateUtil.isRequired(memberuser.getUser_name())) {
			this.addFieldError("memberuser.user_name", "子账号名称不能为空");
		} else {
			if (ValidateUtil.isAlphasFirst(memberuser.getUser_name())) {
				this.addFieldError("memberuser.user_name",
						"子账号名称以字母开头，由数字、字母、下划线组成");
			} else {
				String notallow_name = "";
				notallow_name = SysconfigFuc.getSysValue(CFG_MB_NOTALLOW);
				if (notallow_name.indexOf(memberuser.getUser_name()) > -1) {
					this.addFieldError("memberuser.user_name", "本网站不允许注册此类用户名");
				} else {
					//if是判断修改else是处理添加
					if ("0".equals(flag)) {
						if (existsTitle(user_name+":"+memberuser.getUser_name(),user_name+":"+oldUserName,
								"memberuser", "user_name")) {
							this.addFieldError("memberuser.user_name",
							"该子账号名称已经存在,");
						}
					}else{
						if (existsTitle(user_name+":"+memberuser.getUser_name(), "",
								"memberuser", "user_name")) {
							this.addFieldError("memberuser.user_name",
							"该子账号名称已经存在,");
						}
					}
				}
			}
		}
		
		//if是判断修改else是处理添加
		if("0".equals(flag)){
			// 限制Email只能注册一个帐号
			if (!ValidateUtil.isRequired(memberuser.getEmail())
					&& existsTitle(memberuser.getEmail(), oldEmail, "memberuser", "email")) {
				this.addFieldError("memberuser.email", "该邮箱已被占用");
			}
		}else{
			// 限制Email只能注册一个帐号
			if (!ValidateUtil.isRequired(memberuser.getEmail())
					&& existsTitle(memberuser.getEmail(), "", "memberuser", "email")) {
				this.addFieldError("memberuser.email", "该邮箱已被占用");
			}
		}
		
		if (flag.equals("0")) {
			if (!"".equals(newpasswd) || !"".equals(confirmpasswd)) {
				// 新密码不能为空
				if (ValidateUtil.isRequired(newpasswd)) {
					this.addFieldError("newpasswd", "新密码不能为空");
				} else if (ValidateUtil.isAlphasLimtLength(newpasswd)) {
					this.addFieldError("newpasswd", "密码只能由6-20个字母、数字、下划线组成");
				}
			}
		} else {
			// 密码不能为空
			if (ValidateUtil.isRequired(passwd)) {
				this.addFieldError("passwd", "密码不能为空");
			} else if (ValidateUtil.isAlphasLimtLength(passwd)) {
				this.addFieldError("passwd", "密码只能由6-20个字母、数字、下划线组成");
			}
		}

		String passwd = "";
		if (flag.equals("0")) {
			if (!"".equals(newpasswd) || !"".equals(confirmpasswd)) {
				// 确认密码不能为空
				if (ValidateUtil.isRequired(confirmpasswd)) {
					this.addFieldError("confirmpasswd", "确认密码不能为空");
				}
				passwd = newpasswd;
				// 确认密码和确认密码比较
				if (!newpasswd.equals(confirmpasswd)) {
					this.addFieldError("confirmpasswd", "确认密码与密码不一致");
				}
				// 对密码加密
				if (passwd != null && !passwd.equals("")) {
					passwd = Md5.getMD5(passwd.getBytes());
				} else {
					passwd = null;
				}
				memberuser.setPasswd(passwd);
			}
		} else {
			// 确认密码不能为空
			if (ValidateUtil.isRequired(confirmpasswd)) {
				this.addFieldError("confirmpasswd", "确认密码不能为空");
			}
			passwd = this.getPasswd();
			// 确认密码和确认密码比较
			if (!this.getPasswd().equals(confirmpasswd)) {
				this.addFieldError("confirmpasswd", "确认密码与密码不一致");
			}
			// 对密码加密
			if (passwd != null && !passwd.equals("")) {
				passwd = Md5.getMD5(passwd.getBytes());
			} else {
				passwd = null;
			}
			memberuser.setPasswd(passwd);
		}
		// 判断角色代码
		if (ValidateUtil.isRequired(memberuser.getRole_code())) {
			this.addFieldError("memberuser.role_code", "请选择角色代码");
		}

		// 验证字段类型为：email为电子邮件
		if (!ValidateUtil.isRequired(memberuser.getEmail().trim())) {
			if (ValidateUtil.isEmail(memberuser.getEmail().trim())) {
				this
						.addFieldError("memberuser.email",
								"格式不正确,格式如:xxxx@xxx.com");
			}
		}

		// 验证字段类型为：tel为固定电话
		if (!ValidateUtil.isRequired(memberuser.getPhone().trim())) {
			if (ValidateUtil.isTelephone(memberuser.getPhone())) {
				this.addFieldError("memberuser.phone", "格式不正确,格式如:xxx-xxxxxxx");
			}
		}

		// 验证字段类型为：mobile为移动电话
		if (!ValidateUtil.isRequired(memberuser.getCellphone().trim())) {
			if (ValidateUtil.isMobile(memberuser.getCellphone().trim())) {
				this.addFieldError("memberuser.cellphone", "格式不正确");
			}
		}
}
	/**
	 * @author : HZX
	 * @date : Nov 22, 2014 4:09:59 PM
	 * @Method Description :设置跳过敏感词过滤器->原因是敏感词会自动替换，且系统已有限制注册词,且会员有审核开关
	 * 
	 */
	public void setIs_joinus(){
		getSession().setAttribute("is_joinus", "1");
	}
	/**
	 * 方法描述：新增会员信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.memberuser.setUser_id(this.session_user_id);
		String id = memberuser.getUser_id();

		if (id == null && "".equals(id)) {
			return insert();
		}
		commonCheck();
		if (super.ifvalidatepass) {
			return add();
		}
		this.memberuser.setUser_name(this.user_name + ":"
				+ memberuser.getUser_name());
		this.memberuserService.insert(memberuser);

		this.addActionMessage("新增会员信息表成功！");
		this.memberuser = null;
		return add();
	}

	/**
	 * 方法描述：修改会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		String id = user_id;
		if (id == null && "".equals(id)) {
			return list();
		}
		commonCheck();
		if (super.ifvalidatepass) {
			return view();
		}
		this.memberuser.setUser_id(user_id);
		// 用replace的""来代替多次出现的user_name
		this.memberuser.setUser_name(this.memberuser.getUser_name().replace(
				this.user_name + ":", ""));
		this.memberuser.setUser_name(this.user_name + ":"
				+ memberuser.getUser_name());
		this.memberuserService.update(memberuser);
		this.addActionMessage("修改会员信息表成功！");
		return list();
	}

	/**
	 * 方法描述：删除会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.memberuserService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除会员信息表成功");
		} else {
			this.addActionMessage("删除会员信息表失败");
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
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
			pageMap.put("user_type", "2");
		}
		// 根据页面提交的条件找出信息总数
		int count = this.memberuserService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		memberuserList = this.memberuserService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */

	public String view() throws Exception {
		String id = this.user_id;
		// 获取角色代码的值memrole.level_code
		Map map = new HashMap();
		map.put("mem_cust_id", this.session_cust_id);
		rolecodeList = this.memroleService.getList(map);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			user_name = this.session_user_name;
		}
		if (id == null || id.equals("")) {
			memberuser = new Memberuser();
		} else {
			memberuser = this.memberuserService.get(id);
			// 去掉重名的主帐号
			String child_user_name = memberuser.getUser_name();
			if (child_user_name.indexOf(":") > -1) {
				child_user_name = child_user_name.substring(child_user_name
						.indexOf(":") + 1, child_user_name.length());
				memberuser.setUser_name(child_user_name);
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：商城用户退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception {
		getSession().invalidate();
		return Constants.MEMBER_SIGNIN;
	}

	/**
	 * @return the MemberuserList
	 */
	public List getMemberuserList() {
		return memberuserList;
	}

	/**
	 * @param memberuserList
	 *            the MemberuserList to set
	 */
	public void setMemberuserList(List memberuserList) {
		this.memberuserList = memberuserList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (memberuser == null) {
			memberuser = new Memberuser();
		}
		String id = this.memberuser.getUser_id();
		if (!this.validateFactory.isDigital(id)) {
			memberuser = this.memberuserService.get(id);
		}
	}

	public String getConfirmpasswd() {
		return confirmpasswd;
	}

	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}

	public String getMessage() {
		return message;
	}

	public Memrole getMemrole() {
		return memrole;
	}

	public void setMemrole(Memrole memrole) {
		this.memrole = memrole;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the memberuser
	 */
	public Memberuser getMemberuser() {
		return memberuser;
	}

	/**
	 * @param Memberuser
	 *            the memberuser to set
	 */
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

}
