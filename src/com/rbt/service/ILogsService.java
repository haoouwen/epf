/*
  
 
 * Package:com.rbt.service
 * FileName: ILogsService.java
 */

package com.rbt.service;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Logs;

/**
 * @function 功能 添加系统日志业务层类的接口
 * @author 创建人 LJQ
 * @date 创建日期 Jul 5, 2014 9:38:38 AM
 */
public interface ILogsService extends IGenericService<Logs,String>{

	/**
	 * @Method Description :清空logs记录表
	 * @author : LJQ
	 * @date : Nov 9, 2014 1:30:03 PM
	 */
	public void deleteAlllogs();
	/**
	 * @Method Description :导出日志
	 * @author : HZX
	 * @throws Exception 
	 * @date : Feb 27, 2014 1:15:50 PM
	 */
	public void exportExcel(List logslist, HttpServletResponse response) throws Exception;

}
