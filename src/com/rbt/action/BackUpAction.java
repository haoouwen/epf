/*
 
 * Package:com.rbt.action
 * FileName: BackUpAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.service.IBackupService;
import com.rbt.service.ISysuserService;
import com.rbt.common.Md5;
import com.rbt.common.util.BackMysql;
import com.rbt.common.util.DbUtil;
import com.rbt.common.util.FileUpDownFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 数据库备份/还原action类
 * @author 创建人 LJQ
 * @date 创建日期 Jul 7, 2014 1:43:46 PM
 */
@Controller
public class BackUpAction extends AdminBaseAction {
	private static final long serialVersionUID = -5707993749015761816L;
	
	/*******************业务层接口****************/
	@Autowired
	public IBackupService backupService;
	@Autowired
	public ISysuserService sysuserService;

	/*********************集合******************/
	public List tableList;	//数据库表信息
	public List structureList;//表的结构信息

	/*********************字段******************/
	public String databaseName;//数据库名
	public String cbtable;//表选择框checkbox
	public String sqlFileName;// 还原表结构传入的sql文件名
	public String initPassword;//超级管理员密码
	public String admin_name;//超级管理员名称

	

	/**
	 * @MethodDescribe 方法描述 调用mysql 命令备份数据库
	 * @author 创建人 LJQ
	 * @param mysql
	 *            执行的mysql cmd 的字符串
	 * @throws Exception
	 * @date 创建日期 Jul 7, 2014 2:04:17 PM
	 */
	public String backup() throws Exception {
		BackMysql bm = new BackMysql();
		String dbSqlName = bm.backupDb();
		this.addActionMessage("数据库备份成功,点击《还原数据库》可选择文件" + dbSqlName + "还原数据库");
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 还原指定的表
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 12, 2014 1:02:06 PM
	 */
	public String loadSql() throws Exception {
		BackMysql bm = new BackMysql();
		bm.load(sqlFileName);
		this.addActionMessage(sqlFileName + "还原成功");
		return list();
	}

	/**
	 * @Method Description :删除指定的文件名称
	 * @author : LJQ
	 * @date : Nov 9, 2014 2:39:58 PM
	 */
	public String deleteTab() throws Exception {
		BackMysql bm = new BackMysql();
		if (bm.deleteTable(sqlFileName)) {
			this.addActionMessage(sqlFileName + "删除成功");
		} else {
			this.addActionMessage(sqlFileName + "删除失败");
		}
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 备份指定的表
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 11, 2014 12:38:05 PM
	 */
	public String butable() throws Exception {
		BackMysql bm = new BackMysql();
		if (cbtable != null) {
			String dbSqlName = bm.backupDbTable(cbtable);
			this.addActionMessage("表备份成功,点击《还原表》可选择文件" + dbSqlName + "还原数据库表");
		} else {
			this.addActionMessage("表备份失败");
		}
		return list();
	}

	/**
	 * @Method Description : 下载表文件
	 * @author : LJQ
	 * @throws Exception
	 * @date : Nov 9, 2014 3:47:44 PM
	 */
	public void downloadTab() throws Exception {        
        FileUpDownFileUtil fudf=new FileUpDownFileUtil();
        String tabPath="/WEB-INF/dbBackup/";
        fudf.downloadFile(tabPath, sqlFileName);
	}	
	

	/**
	 * @MethodDescribe 方法描述 获取指定数据库中所有的表的名称
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 7, 2014 2:07:33 PM
	 */
	public String list() throws Exception {	
		DbUtil dbUtil = new DbUtil();
		// 获取数据库名称
		String dbName = dbUtil.getDbName();
		Map pageMap = new HashMap();
		// 通过对数据库的名称查询数据库中的表数
		pageMap.put("dbName", dbName);
		tableList = this.backupService.getTableList(pageMap);
		// 重构原List数据,将表赋给表对象用于前台的显示
		tableList = this.backupService.newTableList(tableList, dbName, structureList);
		return goUrl(INDEXLIST);
	}
	/**
	 * @Method Description :初始化数据库跳转
	 * @author : HZX
	 * @date : Apr 10, 2014 10:05:34 AM
	 */
	public String dbDelete(){
		Map adminMap=new HashMap();
		adminMap.put("user_type", "3");
		List sysList=this.sysuserService.getList(adminMap);
		if(sysList!=null&&sysList.size()>0){
			Map supperMap=(Map) sysList.get(0);
			admin_name=supperMap.get("nike_name").toString();
		}else {
			this.addFieldError("passmessage", "系统错误！");
		}
		return goUrl("dbinit");
	}
	
	/**
	 * @Method Description :初始化数据库
	 * @author : HZX
	 * @date : Apr 10, 2014 10:05:34 AM
	 */
	public String dbInit(){
		if(validateFactory.isRequired(initPassword)){
			this.addFieldError("passmessage", "请输入密码！");
			return dbDelete();
		}
		String supper_psw=null;
		Map adminMap=new HashMap();
		adminMap.put("user_type", "3");
		List sysList=this.sysuserService.getList(adminMap);
		if(sysList!=null&&sysList.size()>0){
			Map supperMap=(Map) sysList.get(0);
			supper_psw=supperMap.get("passwd").toString();
		}else {
			this.addFieldError("passmessage", "系统错误！");
			return dbDelete();
		}
		initPassword = Md5.getMD5(initPassword.getBytes());
		if(!initPassword.equals(supper_psw)){
			this.addFieldError("passmessage", "密码错误！");
			return dbDelete();
		}
		boolean flag ;
	    flag = this.backupService.dbInit();
		if(flag){
			this.addActionMessage("初始化成功");
		}else{
			this.addActionMessage("初始化失败");
		}
		return dbDelete();
	}
	public List getTableList() {
		return tableList;
	}

	public void setTableList(List tableList) {
		this.tableList = tableList;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the cbtable
	 */
	public String getCbtable() {
		return cbtable;
	}

	/**
	 * @param cbtable
	 *            the cbtable to set
	 */
	public void setCbtable(String cbtable) {
		this.cbtable = cbtable;
	}

	/**
	 * @return the structureList
	 */
	public List getStructureList() {
		return structureList;
	}

	/**
	 * @param structureList
	 *            the structureList to set
	 */
	public void setStructureList(List structureList) {
		this.structureList = structureList;
	}
	public String getSqlFileName() {
		return sqlFileName;
	}

	public void setSqlFileName(String sqlFileName) {
		this.sqlFileName = sqlFileName;
	}

}
