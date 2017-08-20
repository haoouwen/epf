/*
  
 
 * Package:com.rbt.filter
 * FileName: KeyWordFilter.java
 */
package com.rbt.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.function.FilterWordFuc;
import com.rbt.function.SysconfigFuc;

/**
 * @function 功能  敏感字过滤-过滤器
 * @author  创建人 HXK
 * @date  创建日期  2014-09-21
 */
public class KeyWordFilter implements Filter {
	
	// 过滤敏感字新增和修改请求
	private final static String FILTER_PAGE = "admin_Filterword_insert.action|admin_Filterword_update.action|admin_Filterword_list.action|admin_Filterword_view.action|admin_Filterword_add.action";

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	//实施过滤
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		//是否开启敏感字过滤功能 0:是 1：否
		String filter_switch = SysconfigFuc.getSysValue("cfg_filterword");
		HttpSession session = req.getSession();
		if(session.getAttribute("is_joinus")!=null&&session.getAttribute("is_joinus").toString().equals("1")){//注册不替换
			filter_switch="1";
		}
		if(filter_switch.equals("0")){
			String reqUrl = req.getRequestURI();
			reqUrl = reqUrl.replace("/", "");
			
			HashMap keyMap = (HashMap)FilterWordFuc.getFilterMap();
			
			if (FILTER_PAGE.indexOf(reqUrl)==-1 && req.getMethod().equals("POST")) {
				chain.doFilter(new KeyWordRequestWrapper(req, keyMap), response);
			} else {
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

}
