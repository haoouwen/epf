/*
  
 
 * Package:com.rbt.filter
 * FileName: BanIpFilter.java
 */
package com.rbt.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.common.Constants;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.IBanipService;
/**
 * @function 功能 禁用IP拦截类
 * @author 创建人 HXK
 * @date 创建日期 July 6, 2014
 */
public class BanIpFilter extends CreateSpringContext implements Filter
{
	/*
	 * 方法描述：提供IP拦截的方法 
	 * (non-Javadoc) 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	
	//跳转到限制IP提示页面
	private final static String IP_LIMIT_PAGE = "/include/banIp.jsp"; 
	
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws ServletException, IOException 
	{
		
		PropertiesUtil pu = new PropertiesUtil(Constants.JDBC_NAME);
		String filterswitch=pu.readValue("filterswitch");
		if(filterswitch.equals("0"))
		{
			chain.doFilter(req, resp);
		}
		else if(filterswitch.equals("1"))
		{
		
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			//得到客户端访问IP
			String requestIp = request.getRemoteAddr();
			//得到客户端访问URL地址
			String requestUrl = request.getRequestURL().toString();
			
			//is_banip：是否开启禁止IP访问功能 0:是 1：否
			String is_banip = SysconfigFuc.getSysValue("cfg_banip");
			
			//判断IP是否禁止访问
			if(is_banip.equals("0") && existIpSection(requestIp) && requestUrl.indexOf(IP_LIMIT_PAGE) == -1)//匹配数据库限制IP
			{
				response.sendRedirect(IP_LIMIT_PAGE);
			}
			else
			{
				chain.doFilter(req, resp);
			}
		}
	}
	/*
	 * IP段的控制
	 * 修改人：HXK
	 * 日期：2014-09-16
	 */
	 public boolean existIpSection(String ip)
	 {
		 boolean val = false;
		 IBanipService ban_ipService = (IBanipService)getContext().getBean("banipService");
		 Map pageMap = new HashMap();
		 List list = ban_ipService.getList(pageMap);
		 if(list!=null && list.size()>0){
			 for(Iterator it = list.iterator(); it.hasNext();){
				HashMap obj = (HashMap)it.next();
				String _ip = "";
				if(obj.get("ip")!=null){
					_ip = obj.get("ip").toString();
				}
				if(ipIsValid(_ip,ip)){
					val = true;
					break;
				}
			}
		}
		return val;
	 }
	 /*
     * 判断IP是否在指定范围； 
     * 修改人：HXK
     * 日期：2014-09-16
     */ 
	public static boolean ipIsValid(String ipSection, String ip) { 
        if (ipSection == null) 
            throw new NullPointerException("IP段不能为空"); 
        if (ip == null) 
            throw new NullPointerException("IP不能为空"); 
        ipSection = ipSection.trim(); 
        ip = ip.trim(); 
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)"; 
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP; 
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP)) 
            return false; 
        int idx = ipSection.indexOf('-'); 
        String[] sips = ipSection.substring(0, idx).split("\\."); 
        String[] sipe = ipSection.substring(idx + 1).split("\\."); 
        String[] sipt = ip.split("\\."); 
        long ips = 0L, ipe = 0L, ipt = 0L; 
        for (int i = 0; i < 4; ++i) { 
            ips = ips << 8 | Integer.parseInt(sips[i]); 
            ipe = ipe << 8 | Integer.parseInt(sipe[i]); 
            ipt = ipt << 8 | Integer.parseInt(sipt[i]); 
        } 
        if (ips > ipe) { 
            long t = ips; 
            ips = ipe; 
            ipe = t; 
        } 
        return ips <= ipt && ipt <= ipe; 
    } 
	
	/*
	 * 功能：判断传入的IP是否存在禁止访问的IP列表中
	 * 为什么用List直接把IP全取出来？
	 * 原因：之前用的是getCount方法判断是否存在IP，这样缓存的话，每个IP都需要缓存一次，耗资源
	 * 现在的方法是一次把IP禁止信息都查询出来，只需要缓存一次即可，节省资源
	 * 修改人：HXK 
	 * 日期：2014-08-31
	 */
	public boolean existIp(String ip){
		boolean val = false;
		IBanipService ban_ipService = (IBanipService)getContext().getBean("banipService");
		Map pageMap = new HashMap();
		List list = ban_ipService.getList(pageMap);
		if(list!=null && list.size()>0){
			for(Iterator it = list.iterator(); it.hasNext();){
				HashMap obj = (HashMap)it.next();
				String _ip = "";
				if(obj.get("ip")!=null){
					_ip = obj.get("ip").toString();
				}
				if(_ip.indexOf(ip) > -1){
					val = true;
					break;
				}
			}
		}
		return val;
	}
	
	
	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
