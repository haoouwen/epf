/*
 
 * Package:com.rbt.common
 * FileName: Constants.java
 */
package com.rbt.common;

/**
 * @function 功能  系统常量		
 * @author  创建人 HXK
 * @date  创建日期  Jun 25, 2014
*/
public class Constants {

	/*
	 * struts配置文件新定义的跳转常量
	 * view:跳转至修改页面
	 * add：跳转至新增页面
	 * audit:审核的详细页面
	 * auditlist:审核列表页面
	 */
	public static final String VIEW = "update";
	
	public static final String ADD = "insert";
	
	public static final String AUDIT = "audit";
	
	public static final String INDEX = "index";
	
	public static final String AUDITLIST = "auditindex";
	
	/*
	 * session里存放的user名称
	 */
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String USER_ID_NEW = "login_id";
	public static final String USER_NAME_NEW = "user_name_new";
	public static final String CUST_ID = "cust_id";
	public static final String CUST_LOGO = "cust_logo";
	public static final String CUST_NAME = "cust_name";
	public static final String ORG_ID = "org_id";
	public static final String CUST_TYPE = "cust_type";
	public static final String MEM_TYPE="mem_type";
	public static final String USER_TYPE = "user_type";
	public static final String ROLE_ID = "role_id";
	public static final String AREA_MANAGER="area_manger";
	public static final String LEVEL_CODE="level_code";
	public static final String LAST_LOGIN_TIME="last_login_time";
	public static final String PASSWORD_A="password_a";
	public static final String PASSWORD_B="password_b";
	public static final String PASSWORD_C="password_c";
	public static final String RADOM_NO = "radom_no";
	public static final String NEIBU_LEVEL_CODE = "999";
	
	
	
	
	/*
	 * 管理员登陆的action请求名称
	 */
	public static final String ADMIN_LOGIN = "adminlogin";
	/*
	 * 代理商登陆的action请求名称
	 */
	public static final String AGENT_LOGIN = "agent";
	
	/*
	 * 会员登陆的action请求名称
	 */
	public static final String MEMBER_LOGIN = "memberlogin";

	/*
	 * 商城会员登陆的action请求名称
	 */
	public static final String MEMBER_SIGNIN = "/login.html";
	
	/*
	 * 商城触屏版会员登陆
	 */
	public static final String WEBAPP_MEMBER_SIGNIN = "/webapplogin.html";
	
	/*
	 * 以上两个名称用于session失效控制跳转
	 * com.rbt.interceptor.SessionIterceptor
	 */
	
	/*
	 * 企业会员类型标识值
	 */
	public static final String COMPANY_MEM_TYPE = "0";
	/*
	 * 个人会员类型标识值
	 */
	public static final String PERSONAL_MEM_TYPE = "1";
	public static final String MALL_TYPE_B2B = "b2b";
	public static final String MALL_TYPE_B2C = "b2c";
	/*
	 * 管理员类型名称
	 */
	public static final String ADMIN_TYPE = "admin";
	/*
	 * 代理商类型名称
	 */
	public static final String AGENT_TYPE = "agent";
	/*
	 * 会员的类型名称
	 */
	public static final String MEMBER_TYPE = "member";
	/*
	 * B2C商城会员的类型名称
	 */
	public static final String BMALL_TYPE = "bmall";
	//模板文件存储路径
	public static final String TEMPLATE_PORTAL_FOLDER = "WEB-INF/template/";
	//运营商部门顶级ID
	public static final String UP_ORG_ID = "1111111111";
	//运营商部门顶级菜单ID
	public static final String UP_MENU_ID = "1111111111";
	//中国的地区ID
	public static final String UP_AREA_ID = "1111111111";
	//JDBC属性文件名称
	public static final String JDBC_NAME = "jdbc.properties";
	//获取系统用户所所对于组织部门的地区ID，主要是用于系统后台地区信息过滤
	public static final String ORG_AREA_ID = "org_area_id";
	//国家顶级的地区ID
	public static final String UP_COUNTRY_AREA_ID = "9999999999";
	
	/*
	 * 网站显示页面
	 */
	//系统错误页面
	public static final String SYSTEM_ERROR_PAGE = "/include/pageTip.jsp?page_code=system_error_page";
	//重复提交页面
	public static final String TOKEN_PAGE = "/include/pageTip.jsp?page_code=token_page";
	//搜索为空页面
	public static final String SERRCH_NULL_PAGE = "/include/pageTip.jsp?page_code=search_null_page";
	//店铺内搜索为空页面
	public static final String SEARCH_SHOPGOODS_NULL_PAGE = "/include/pageTip.jsp?page_code=serach_shopGoods_null_page";
	//404页面
	public static final String NOT_FOUND_PAGE = "/include/pageTip.jsp?page_code=not_fount_page";
	//网站注册协议页面
	public static final String REGISTER_AGREEMENT_PAGE = "/include/pageTip.jsp?page_code=register_agreement_page";
	//非法店铺页面
	public static final String ILLEGAL_SHOP_PAGE = "/include/pageTip.jsp?page_code=illegal_shop_page";
	//非法商品页面
	public static final String ILLEGAL_GOODS_PAGE = "/include/pageTip.jsp?page_code=illegal_goods_page";
	//没有操作权限页面
	public static final String NOT_OPER_RIGHT_PAGE = "/include/pageTip.jsp?page_code=not_oper_right_page";
	//店铺留言为空页面
	public static final String SHOPMESSAGE_NULL_PAGE = "/include/pageTip.jsp?page_code=shopMessage_null_page";
	
	//触屏端商品不存在友情提醒页面
	public static final String WEBAPP_NOT_GOODS_PAGE = "/include/webapp_pageTip.jsp?page_code=illegal_goods_page";
	
	
	/*
	 * 日志操作内容
	 * */
	public static final String MEMBER_LOGIN_LOG = "会员登录";
	
	
	
	
	/********楼层标签位置开始**************************/
	
	public static final String pos_tab0_00 = "tab0_00";
	public static final String pos_tab0_cat = "tab0_cat";
	public static final String pos_tab0_00_01 = "tab0_00_01";
	public static final String pos_tab0_00_02 = "tab0_00_02";
	public static final String pos_tab0_00_03 = "tab0_00_03";
	public static final String pos_tab0_00_04 = "tab0_00_04";
	public static final String pos_tab0_00_05 = "tab0_00_05";
	public static final String pos_tab0_00_06 = "tab0_00_06";
	public static final String pos_tab0_00_07 = "tab0_00_07";
	public static final String pos_tab0_00_08 = "tab0_00_08";
	public static final String pos_tab0_00_09 = "tab0_00_09";
	public static final String pos_tab0_00_10 = "tab0_00_10";
	public static final String pos_tab0_01 = "tab0_01";
	public static final String pos_tab0_02 = "tab0_02";
	public static final String pos_tab0_03 = "tab0_03";
	public static final String pos_tab0_04 = "tab0_04";
	public static final String pos_tab0_05 = "tab0_05";
	public static final String pos_tab0_06 = "tab0_06";
	public static final String pos_tab0_07 = "tab0_07";
	
	/********楼层标签位置结束**************************/
	
	
	//微信appid
	public static final String WX_APPID = "wxee8fbbeaa95e7dd3";
	//微信secret
	public static final String WX_SECRET = "00d8dec0f8cc3659d4914cada8be27d9";
	
	
	
	
	
	
}
