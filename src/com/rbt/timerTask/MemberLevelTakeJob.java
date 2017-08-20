package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.rbt.common.util.DateUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.model.Member;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMemberService;


/**
 * @author QJY
 * @function 按一年的时间间隔扣除不同级别会员的成长值，根据剩余的成长值重新计算级别，定时器，时间频率
 * @date 2015-08-19
 *
 */
public class MemberLevelTakeJob extends CreateSpringContext implements Job{
	//注入bean
	IMemberService memberService=(IMemberService) getContext().getBean("memberService");
	IMalllevelsetService malllevelsetService=(IMalllevelsetService) getContext().getBean("malllevelsetService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			getMemberLevelTake();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @author QJY
	 * @function 
	 * 获取所有会员，根据升级时间和当前时间作差，是否超过一年，扣除对应的级别会员的成长值
	 * @date 2015-08-20
	 */
	public void getMemberLevelTake()throws Exception{
		Map levelMap = new HashMap();
	   	List levelList = this.malllevelsetService.getList(levelMap);
		
		Map memMap = new HashMap();
		List memberList = this.memberService.getList(memMap);
		
		if(memberList !=null && memberList.size()>0){
			 Map timeMap = new HashMap();
			 Member member = new Member();
			for(int i=0;i<memberList.size();i++){
				timeMap = (Map) memberList.get(i);
				if(timeMap!=null && timeMap.get("growthtime")!=null && timeMap.get("buy_level")!=null && timeMap.get("cust_id")!=null){
					member = this.memberService.get(timeMap.get("cust_id").toString());
					String growthtime =  timeMap.get("growthtime").toString();
					//级别
					//String memberlevel =  timeMap.get("buy_level").toString();
					//级别有效期
					Integer validity_period = Integer.valueOf(timeMap.get("validity_period").toString());
					//扣除成长值
					Integer dedu_growth_value = (int)Math.round(Double.valueOf(timeMap.get("dedu_growth_value").toString()));
				    Integer growthvalue = (int)Math.round(Double.valueOf(timeMap.get("growthvalue").toString()));
					Integer months = Integer.valueOf(DateUtil.getMonthSpace(DateUtil.getCurrentTime(), growthtime));
					if(months>validity_period){//判断当前时间和最后一次升级时间是否超过规定的时间，这里是月数。
						growthvalue = growthvalue-dedu_growth_value;
					if(growthvalue<0){
						growthvalue=0;
					}
						String level_code="";
						if(levelList !=null && levelList.size()>0){
							for(int j=0;i<levelList.size();j++){
								Map map = (Map) levelList.get(i);
								if(map!=null && map.get("inter_lower")!=null&& map.get("inter_height")!=null){
									Integer inter_lower = Integer.valueOf(map.get("inter_lower").toString());
									Integer inter_upper = Integer.valueOf(map.get("inter_height").toString());
									//注册会员默认成长值上下限为0，故意降级规则如下
									if((growthvalue>= inter_lower) && (growthvalue<= inter_upper)){
										level_code = map.get("level_code").toString();
										break;
									}
								}
							}
							
						}
						member.setGrowthtime(DateUtil.getCurrentTime());
						member.setGrowthvalue(String.valueOf(growthvalue));
						member.setBuy_level(level_code);
						
						this.memberService.update(member);
						
					}
				}
			}
		}
		
	}
	
	/*public static void main(String[]args)throws Exception{
		MemberLevelTakeJob ot=new MemberLevelTakeJob();
		ot.getMemberLevelTake();
	}*/

}
