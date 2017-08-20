/*
 
 * Package:com.rbt.common.util
 * FileName: DbUtil.java
 */
package com.rbt.common.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.rbt.function.CreateSpringContext;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 * @function 功能  常用数据库操作
 * @author HXK
 * @date  创建日期  Jun 26, 2014
 */
public class DbUtil {
	
	private PropertiesUtil config;
	
	private static DataSource dataSource;
	
	private static QueryRunner run;
	
	private static Statement st;
	
	
	public DbUtil(){
		config = new PropertiesUtil("jdbc.properties");
		if(dataSource == null){
			dataSource = (DataSource)CreateSpringContext.getContext().getBean("dataSource");
		}
		if(run == null){
			run = new QueryRunner(dataSource);
		}
		
	}
	
	/**
	 * 查询返回list对象
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Object[] param, Class<T> clazz) {
		List<T> obj = null;
		try {
			obj = (List<T>) run.query(sql,param,new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * @author : LJQ
	 * @date : Aug 28, 2014 2:20:34 PM
	 * @Method Description :删除记录
	 */
	public void  excuteSql(String sql){
		try {
			 run.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List queryList(String sql){
		return queryForList(sql,null,HashMap.class);
	}

	
	//找出数据库表信息
    public ArrayList getTableDescList(String tableName){
    	return (ArrayList)queryList("desc "+tableName+";");
    }
    
    
    public String getDbName(){
		// 获取系统配置文件中的参数
		String dbUrl = "";
		try {
			dbUrl = config.readValue("datasource.url");
		} catch (IOException e) {
			System.out.println("读取配置文件jdbc.properties报错");
			e.printStackTrace();
		}
		String dbName = "";
		// 获取问号的位置
		int askPos = dbUrl.indexOf("?");
		dbUrl = dbUrl.substring(0, askPos);
		// 获取最后一个斜杠的位置
		int symPos = dbUrl.lastIndexOf("/");
		//获取数据库的名称
		dbName = dbUrl.substring(symPos + 1, dbUrl.length());
		return dbName;
	}
    
    public String getHost(){
    	String dbUrl = "";
		try {
			dbUrl = config.readValue("datasource.url");
		} catch (IOException e) {
			System.out.println("读取配置文件jdbc.properties报错");
			e.printStackTrace();
		}
		String fenPos = "";
		if(dbUrl.indexOf(":") > -1){
			fenPos = dbUrl.substring(0,dbUrl.lastIndexOf(":"));
		}
		int xiePos = 0;
		if(fenPos.indexOf("/") > -1){
			xiePos = fenPos.lastIndexOf("/")+1;
		}
		String host = fenPos.substring(xiePos,fenPos.length());
		return host;
    }
    
    public static void main(String[] args) {

		String sql = "delete from autofuck";
		 new DbUtil().excuteSql(sql);
		System.out.println(new DbUtil().getHost());

	}
	
}
