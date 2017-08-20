/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemberuserService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.dao.IMalllevelsetDao;
import com.rbt.dao.IMemberuserDao;
import com.rbt.dao.IMemroleDao;
import com.rbt.dao.ISysmenuDao;
import com.rbt.model.Malllevelset;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Memrole;
import com.rbt.model.Sysuser;
import com.rbt.service.IMemberuserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员登陆账号信息表Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Fri Jan 04 10:06:41 CST 2014
 */
@Service
public  class MemberuserService extends GenericService<Memberuser,String> implements IMemberuserService {
	
	IMemberuserDao memberuserDao;
	@Autowired
	IMemroleDao memroleDao ;
	@Autowired
	IMalllevelsetDao malllevelsetDao;
	@Autowired
	ISysmenuDao sysmenuDao;

	@Autowired
	public MemberuserService(IMemberuserDao memberuserDao) {
		super(memberuserDao);
		this.memberuserDao = memberuserDao;
	}
	public Memberuser getPKByCustID(String cust_id){
		return  (Memberuser) this.memberuserDao.getPKByCustID(cust_id);
	}
	public void updatePassword(Memberuser memberuser) {
		this.memberuserDao.updatePassword(memberuser);
	}
	public String insertMemberuser(Memberuser memberuser) {
		return this.memberuserDao.insertMemberuser(memberuser);
	}
	public Memberuser getMemberuserByusername(String username) {
		return this.memberuserDao.getMemberuserByusername(username);
	}
	public void deleteMembuser(String user_name){
		this.memberuserDao.deleteMembuser(user_name);
	}
	public String insertSysUser(Memberuser memberuser) {
		return this.memberuserDao.insertSysUser(memberuser);
	}
	public Map get(Map userMap){
		Map mapName=null;
		List memberuserList = memberuserDao.getList(userMap);
		if (memberuserList != null && memberuserList.size() > 0) {
			mapName = (HashMap) memberuserList.get(0);
		}
		return mapName;
	}
	public StringBuffer getMentRight(Member member, String role_code,String mem_type,String user_type) {
		StringBuffer sb = new StringBuffer();
		String level_code = "";
		String role_menu_right = "";
		if (!mem_type.equals("") && mem_type.equals("0")) {
			level_code = member.getSell_level();
			if (user_type.equals("2") && !role_code.equals("")) {
				// 找出子账号角色拥有哪些权限role_code就是memrole对象的(memrole_id)主键
				Memrole memrole = this.memroleDao.get(role_code);
				if (memrole != null && memrole.getMemmenu_right() != null) {
					role_menu_right = memrole.getMemmenu_right();
				}
			}
		} else {
			level_code = member.getBuy_level();
		}
		// 根据等级代码获取对应等级的菜单权限
		Malllevelset malllevelset = this.malllevelsetDao.get(level_code);// 通过主键获取malllevelset的对象
		String level_menu_right = "";
		if (malllevelset != null && malllevelset.getMenu_right() != null) {
			level_menu_right = malllevelset.getMenu_right();
		}

		// 找出系统B2C的菜单
		Map b2cMap = new HashMap();
		b2cMap.put("syscode", "b2c");
		b2cMap.put("enabled", "0");
		// B2C菜单列表
		List list = this.sysmenuDao.getList(b2cMap);
		// user_type.equals("2")是为了阻止user_type.equals("1")的去执行，影响效率
		if (role_menu_right != null && !role_menu_right.equals("")
				&& user_type.equals("2")) {
			for (int i = 0; i < list.size(); i++) {
				HashMap listMap = (HashMap) list.get(i);
				String menu_id = listMap.get("menu_id").toString();
				if (level_menu_right.indexOf(menu_id) > -1
						&& role_menu_right.indexOf(menu_id) > -1) {
					sb.append(menu_id);
					sb.append(",");
				}
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				HashMap listMap = (HashMap) list.get(i);
				String menu_id = listMap.get("menu_id").toString();
				if (level_menu_right.indexOf(menu_id) > -1) {
					sb.append(menu_id);
					sb.append(",");
				}
			}
		}
		return sb;
	}
	
	/**
	 * 根据名字获取cookie（接口方法）
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public  Cookie getCookieByName(HttpServletRequest request, String name) {
		
			Map<String, Cookie> cookieMap = ReadCookieMap(request);
			if (cookieMap.containsKey(name)) {
				Cookie cookie = (Cookie) cookieMap.get(name);
				return cookie;
			} else {
				return null;
			}
	}
	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * @param request
	 * @return
	 */
	private  Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	public String getCookieValue(HttpServletRequest request, String name) {
		String loginName="";
		Cookie cokLoginName=this.getCookieByName(request, name);
		if (cokLoginName != null && cokLoginName.getValue() != null) {
			loginName =cokLoginName.getValue();
		}
		return loginName;
	}
	/**
	 * 设置cookie（接口方法）
	 * @param name  cookie名字
	 * @param value  cookie值
	 * @param maxAge cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	public void savecokes(HttpServletResponse response, String username,String remusername) {
		int loginMaxAge = 30 * 24 * 60 * 60; // 定义账户密码的生命周期，这里是一个月。单位为秒
		if (remusername != null && remusername.equals("on")) {
			addCookie(response, "mallLoginName", username, loginMaxAge);
		}
		
	}
	
	
	
	
	
	
	
}

