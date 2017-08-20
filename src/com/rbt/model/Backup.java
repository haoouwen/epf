/*
  
 
 * Package:com.rbt.model
 * FileName: Sysuser.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 备份实体类
 * @author 创建人 LJQ
 * @date 创建日期 Jul 7, 2014 10:43:07 AM
 */

public class Backup implements Serializable {

	static final long serialVersionUID = 2083827134077851396L;
	/*
	 * 数据库中的表名
	 */
	String table_name;

	/**
	 * @return the table_name
	 */
	public String getTable_name() {
		return table_name;
	}

	/**
	 * @param table_name
	 *            the table_name to set
	 */
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

}
