package com.rbt.action;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.timerTask.KjtStateJob;
import org.quartz.JobExecutionException;
/**
 * @function 功能 手动执行定时器的类
 * @author 创建人 7be70a6c6b150379（MD5加密……）
 * @date 创建日期 2016年1月12日16:46:08
 */
@Controller
public class TimerTaskRunAction extends AdminBaseAction implements Preparable {

	/**
	 * 方法描述：返回新增商品订单页面
	 * 
	 * @return
	 * @throws JobExecutionException 
	 * @throws Exception
	 */
	public void kjtStateRun() throws JobExecutionException{
		KjtStateJob mKjtStateJob = new KjtStateJob();
		mKjtStateJob.execute(null);
	}
	
	

}
