/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.Constants;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberuser;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IReceiveboxService;

/**
 * @function 功能  根据客户ID（cust_id）查找出memberuser表的用户名称user_name显示
 * @author  创建人 CYC
 * @date  创建日期  2014-09-22
 */
public class MemberuserFuc extends CreateSpringContext{
	public static Memberuser memberuser;

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:09:48 PM
	 * @Method Description : 通过cust_id获取会员名称
	 */
	public static Memberuser getuserName(String cust_id){
		String userid="";
		HashMap usermap=new HashMap();
		usermap.put("cust_id", cust_id);
		List memberuserList=getMemberuserObj().getList(usermap);
		if(memberuserList!=null&&memberuserList.size()>0){
		  HashMap usernamemap=new HashMap();
		  usernamemap=(HashMap)memberuserList.get(0);
		  userid=usernamemap.get("user_id").toString();
		  memberuser=getMemberuserObj().get(userid);
		}
		return memberuser;
	}

	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:09:38 PM
	 * @Method Description :根据客户ID（cust_id）查找出memberuser表用户类型为管理员的memberuser实体
	 */
	public static Memberuser getMemberuserById(String cust_id){
		String userid="";
		Map usermap=new HashMap();
		usermap.put("cust_id", cust_id);
		usermap.put("user_type", "1");
		List memberuserList=getMemberuserObj().getList(usermap);
		if(memberuserList!=null&&memberuserList.size()>0){
		  Map usernamemap=new HashMap();
		  usernamemap=(HashMap)memberuserList.get(0);
		  userid=usernamemap.get("user_id").toString();
		  memberuser=getMemberuserObj().get(userid);
		}
		return memberuser;
		
	}

	
	/**
	 * @author : LJQ
	 * @date : Mar 12, 2014 11:00:36 AM
	 * @Method Description : 获取上次系统用户登录系统的数据
	 */
	public static Map get_Last_Sysdata(String user_id,String content){
		//获取上次登录时间或是第一次登录时间
		HashMap map=new HashMap();
		map.put("user_id", user_id);
		map.put("start",0);
		map.put("limit",2);
		map.put("content", content);
		//找出会员近两次的登录记录
		List logsList=getlogsServiceObj().getList(map);
		HashMap logmap=new HashMap();
		if(logsList!=null && logsList.size() >0 ){
			if(logsList.size()==1){
				logmap=(HashMap)logsList.get(0);
			}else{
				logmap=(HashMap)logsList.get(1);
			}
		}
		return logmap;
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 1:20:15 PM
	 * @Method Description : 获取用户登录的次数
	 */
	public static int getLoginTimes(String user_id,String content){
		//获取上次登录时间或是第一次登录时间
		HashMap map=new HashMap();
		map.put("user_id", user_id);
		map.put("content", content);
		//找出会员近两次的登录记录
		int count=getlogsServiceObj().getCount(map);
		return count;
	}
	
	/**
	 * @Method Description :判断会员是否已设置支付密码
	 * @author : HZX
	 * @date : Apr 20, 2014 1:00:18 PM
	 */
	public static boolean getIsSetPay(){
		boolean isSet=false;
		IMemberfundService memberfundService=(IMemberfundService) getContext().getBean("memberfundService");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		HashMap map=new HashMap();
		Memberfund memberfund=memberfundService.get(session.getAttribute("cust_id").toString());
		String pay_pass="";
		if(memberfund!=null){
			pay_pass=memberfund.getPay_passwd();
		}

		if(pay_pass!=null&&!pay_pass.equals("")){
			isSet=true;
		}
		return isSet;
	}
	
	
	
	//从Spring容器中获取招聘业务Bean
	public static IMemberuserService getMemberuserObj(){
		return (IMemberuserService)getContext().getBean("memberuserService");
	}
	
	//从Spring容器中获取招聘业务Bean
	public static ILogsService getlogsServiceObj(){
		return (ILogsService)getContext().getBean("logsService");
	}
	
	public static  String logoinIndex(String menu_right){
		//没有任何菜单权限的显示首页
		String returnIndex="/bmall-nopageIndex.action";
		//根据上一级ID找出属于上一级的列表
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", "b2c");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		map.put("enabled","0");
		//根据系统后台、一级菜单找出菜单信息
		List topMenuList = SysmenuFuc.getSysmenuList(map);
		if(topMenuList!=null&&topMenuList.size()>0&&menu_right!=null&&!"".equals(menu_right)){
			HashMap mMap = new HashMap();
     		for(Iterator it = topMenuList.iterator();it.hasNext();){
     			mMap = (HashMap)it.next();
     			String menu_id = "",url="";
     			if(mMap.get("menu_id")!=null) menu_id = mMap.get("menu_id").toString();
     			if(mMap.get("url")!=null) url = mMap.get("url").toString();
     			if(menu_right.contains(menu_id)&&!"".equals(url)){
     				returnIndex=url;
     				break;
     			}
     		}
     	}
	    return 	returnIndex;
	}
	
	public static  String webappLogoinIndex(String menu_right){
		//没有任何菜单权限的显示首页
		String returnIndex="/webappmembercenter.html";
		//根据上一级ID找出属于上一级的列表
		Map<String,String> map = new HashMap<String,String>();
		map.put("syscode", "b2c");
		map.put("up_menu_id", Constants.UP_MENU_ID);
		map.put("enabled","0");
		//根据系统后台、一级菜单找出菜单信息
		List topMenuList = SysmenuFuc.getSysmenuList(map);
		if(topMenuList!=null&&topMenuList.size()>0&&menu_right!=null&&!"".equals(menu_right)){
			HashMap mMap = new HashMap();
     		for(Iterator it = topMenuList.iterator();it.hasNext();){
     			mMap = (HashMap)it.next();
     			String menu_id = "",url="";
     			if(mMap.get("menu_id")!=null) menu_id = mMap.get("menu_id").toString();
     			if(mMap.get("url")!=null) url = mMap.get("url").toString();
     			if(menu_right.contains(menu_id)&&!"".equals(url)){
     				returnIndex=url;
     				break;
     			}
     		}
     	}
	    return 	returnIndex;
	}
	
}
