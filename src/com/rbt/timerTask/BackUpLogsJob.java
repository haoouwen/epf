/*
  
 
 * Package:com.timerTask
 * FileName: BackUpLogsJob.java 
 */
package com.rbt.timerTask;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.common.util.BackMysql;
import com.rbt.function.CreateSpringContext;
import com.rbt.service.ILogsService;

/**
 * @function 功能 执行备份日记
 * @author 创建人 HXK
 * @date 创建日期 2014-02-08
 */
public class BackUpLogsJob extends CreateSpringContext implements Job {
	
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			backuplogsdate();
			deletelogsdate();
		} catch (Exception e) {
			System.err.println("定时备份日记出现异常情况");
			e.printStackTrace();
		}
	}
	//备份数据日记的方法
	public void backuplogsdate()
	{
		BackMysql backtable=new BackMysql();
		backtable.backupDbTable("logs");
	}
	//删除数据库日记信息方法
	public void deletelogsdate()
	{
		ILogsService logsService = (ILogsService) getContext().getBean("logsService");
		logsService.deleteAlllogs();
	}

}
