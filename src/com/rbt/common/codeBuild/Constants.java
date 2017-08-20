/*
 
 * Package:com.rbt.common.codeBuild
 * FileName: Constants.java
 */
package com.rbt.common.codeBuild;

/**
 * 功能：存放代码生成工具所需要的所有常量
 * date:2014-07-10
 * @author HXK
 *
 */
public class Constants {
	
	/*
	 * 链接数据库信息
	 */
	public static final String TYPE = "mysql";
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://124.72.51.26:3306/b2c?useUnicode=true&characterEncoding=UTF-8";
	
	public static final String USERNAME = "root";
	
	public static final String PASSWD = "111111";
	
	//src根目录
	public static final String SRC_PATH = "src/";
	
	//源码根目录
	public static final String ROOT_SAVEPATH = SRC_PATH+"com/rbt/";
	
	/*
	 * 模板路径
	 */
	public static final String TEMPLATEPATH = ROOT_SAVEPATH+"common/codeBuild/";
	
	/*
	 * 生成后文件存放的位置
	 */
	
	//action类存放的位置
	public static final String ACTION_SAVEPATH = ROOT_SAVEPATH+"action";
	
	//pojo类存放的位置
	public static final String POJO_SAVEPATH = ROOT_SAVEPATH+"model";
	
	//ibatis的sql.xml文件存放的位置
	public static final String SQLMAP_SAVEPATH = POJO_SAVEPATH+"/sql";
	
	//dao接口存放的位置
	public static final String DAO_SAVEPATH = ROOT_SAVEPATH+"dao";
	
	//dao实现类存放的位置
	public static final String DAOIMPL_SAVEPATH = DAO_SAVEPATH+"/impl";
	
	//service接口存放的位置
	public static final String SERVICE_SAVEPATH = ROOT_SAVEPATH+"service";
	
	//service实现类存放的位置
	public static final String SERVICEIMPL_SAVEPATH = SERVICE_SAVEPATH+"/impl";
	
	//spring配置文件里要替换的标签代码
	public static final String XML_TAGBODY = "<!--tagbody-->";
	
	//spring action配置文件名称
	public static final String SPRING_ACTION_XML_NAME = "applicationContext-action.xml";
	
	//spring dao配置文件名称
	public static final String SPRING_DAO_XML_NAME = "applicationContext-dao.xml";
	
	//spring service配置文件名称
	public static final String SPRING_SERVICE_XML_NAME = "applicationContext-service.xml";
	
	//ibatis配置文件名称
	public static final String IBATIS_XML_NAME = "sqlmap.xml";
	
	//sqlMap.xml文件路径
	public static final String SQL_PATH =SRC_PATH+"sqlmap.xml";
	
	//运营商后台页面路径
	public static final String PAGE_PATH ="WebRoot/WEB-INF/template/manager/admin/";
	
	
}
