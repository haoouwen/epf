/*
  
 
 * Package:com.rbt.interceptor
 * FileName: SessionIterceptor.java 
 */

package com.rbt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rbt.common.Constants;

/**
 * @function 功能 当用户登陆后，session超时后则返回到登陆页面重新登陆。
 * @author 创建人 HXK
 * @date 创建日期 Jun 22, 2014
 */
public class SessionIterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6394880315123802107L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		//会员登陆页面地址
		String loginPage = "/login.html";
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		
		//得到访问的url地址，不带域名
		String reqUrl = req.getRequestURI();
		
		String user_id = "",cust_type="";
		if(session.getAttribute(Constants.USER_ID)!=null){
			user_id = session.getAttribute(Constants.USER_ID).toString();
		}
		if(session.getAttribute(Constants.CUST_TYPE)!=null){
			cust_type = session.getAttribute(Constants.CUST_TYPE).toString();
		}
		
		//获取登陆来源地址
		//登陆后重新跳转至来源地址
		String loc = "";
		if(req.getParameter("loc")!=null){
			loc = req.getParameter("loc");
		}
		//处理前台直接进入后台的链接
		//如果会员未登录直接想进入后台，则先把链接保存到loc里，登录后在跳转
		if(loc.equals("")&&!reqUrl.equals("/bmall_Memberuser_exit.action")&&!reqUrl.equals("/admin_Sysuser_logout.action")&&!reqUrl.equals("/admin_Asysuser_logout.action")){
			loc = reqUrl;
		}
		
		//控制会员进入运营商后台
		//运营商不能进入会员后台
		//运营商不能进入商城会员后台
		if(cust_type.equals(Constants.ADMIN_TYPE) && reqUrl.startsWith("/"+Constants.BMALL_TYPE)){
			return Constants.MEMBER_LOGIN;
		}
		
		// * 先注释掉，会员很多地方都用到admin的路径
		if(cust_type.equals(Constants.MEMBER_TYPE) && reqUrl.startsWith("/"+Constants.ADMIN_TYPE)){
			return Constants.ADMIN_LOGIN;
		}
		
		if(cust_type.equals(Constants.ADMIN_TYPE) && reqUrl.startsWith("/"+Constants.BMALL_TYPE)){
			return Constants.MEMBER_SIGNIN;
		}
		
		if(cust_type.equals(Constants.BMALL_TYPE) && reqUrl.startsWith("/"+Constants.ADMIN_TYPE)){
			return Constants.ADMIN_LOGIN;
		}
		
		if (user_id == null || user_id.equals("")) {
			//该操作人为运营商
			if(reqUrl.startsWith("/"+Constants.ADMIN_TYPE)){
				return Constants.ADMIN_LOGIN;
			}else if (reqUrl.startsWith("/"+Constants.AGENT_TYPE)) {
				res.sendRedirect(Constants.AGENT_LOGIN);
				return Constants.AGENT_LOGIN;
			}else{
				//该操作员为商城会员
				if(!loc.equals("")){
					res.sendRedirect(loginPage+"?loc="+loc);
				}
				res.sendRedirect(Constants.MEMBER_SIGNIN);
				return actionInvocation.invoke();
			}
		} else {
			return actionInvocation.invoke();
		}
	}

}
