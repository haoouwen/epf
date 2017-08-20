/*
  
 
 * Package:com.rbt.function
 * FileName: CreateSpringContext.java 
 */

package com.rbt.function;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @function 功能  加载spring的配置文件至内存
 * @author  创建人 HXK
 * @date  创建日期  Jun 27, 2014
 */
public class CreateSpringContext {
	private static ApplicationContext context;
	
	static{
		if(context ==null){
			context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		}
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
	
	/**
	 * Convenience method to get the request
	 * 
	 * @return current request
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Convenience method to get the session. This will create a session if one
	 * doesn't exist.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public static ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}

}
