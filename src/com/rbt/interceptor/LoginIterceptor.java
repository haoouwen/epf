/*
  
 
 * Package:com.rbt.interceptor
 * FileName: SessionIterceptor.java 
 */

package com.rbt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @function 功能 菜单权限拦截器
 * @author 创建人 HXK
 * @date 创建日期 Jun 22, 2014
 */
public class LoginIterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5436397213271026801L;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		
		return actionInvocation.invoke();
	}

}
