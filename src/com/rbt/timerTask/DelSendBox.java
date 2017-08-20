package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.common.util.DbUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.service.ISendboxService;
/**
 * 定时删除发件箱的数据
 * @author LHY
 *
 */
public class DelSendBox extends CreateSpringContext implements Job {
	//将对象注入进来
    ISendboxService sendboxService=(ISendboxService)getContext().getBean("sendboxService");
	public List send_idList;
	private static final String DEL_SQL="SELECT send_id,delcount FROM(SELECT  s.send_id,(SELECT COUNT(receive_id) " +
			"FROM receivebox r WHERE r.send_id=s.send_id) AS delcount FROM sendbox s where s.is_send_del='2') AS senDel where delcount=0";
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		delSendMsg();
	}
	/**
	 * 取出发件箱的信息，并删除
	 */
	private void delSendMsg(){
		//获取所有的发件箱
		send_idList=new DbUtil().queryList(DEL_SQL);
		if(send_idList!=null&&send_idList.size()>0){
			Map map=new HashMap();
			for (int i = 0; i <send_idList.size(); i++) {
				//取出发件箱的id
				map=(Map) send_idList.get(i);
				String send_id=map.get("send_id").toString();
				System.out.println("=============================delete:"+send_id);
				this.sendboxService.delete(send_id);
			}
		}
	} 
	public static void main(String[]args){
		new DelSendBox().delSendMsg();
	}

}
