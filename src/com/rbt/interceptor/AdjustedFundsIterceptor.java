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
import com.rbt.common.Constants;

/**
 * @author : HZX
 * @date : Aug 26, 2014 1:47:25 PM
 * @Method Description :
 */
public class AdjustedFundsIterceptor extends AbstractInterceptor {

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
		if(session.getAttribute(Constants.PASSWORD_A)==null||session.getAttribute(Constants.PASSWORD_B)==null||session.getAttribute(Constants.PASSWORD_C)==null){
			session.setAttribute("istoAdjus", "1");
			return "adminAdjusLogin";
		}
		session.setAttribute("trueToAdjus", "0");
		return actionInvocation.invoke();
	}

}
