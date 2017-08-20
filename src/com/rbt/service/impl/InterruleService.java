/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: InterruleService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.rbt.dao.IInterruleDao;
import com.rbt.model.Interrule;
import com.rbt.service.IInterruleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 积分规则表Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Thu Nov 10 14:26:30 CST 2014
 */
@Service
public class InterruleService  extends GenericService<Interrule,String> implements IInterruleService {

	/*
	 * 积分规则表Dao层接口
	 */
	IInterruleDao interruleDao;

	@Autowired
	public InterruleService(IInterruleDao interruleDao) {
		super(interruleDao);
		this.interruleDao = interruleDao;
	}

    /**
	 * @Method Description :批量更新规则
	 * @author : LJQ
	 * @date : Nov 28, 2014 12:48:47 PM
	 */
	public void updateInterruleList(List list) {
		this.interruleDao.updateInterruleList(list);
	}
	

	/**
	 * 方法描述：修改积分规则表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List updateScore(String rule_id,String rule_num){
		rule_id=rule_id.replace(" ","");
		String ruleStr[]=rule_id.split(",");
		String ruleNumStr[]=rule_num.split(",");
		List ruleList=new ArrayList();
		if(ruleStr.length>0){
			for(int i=0;i<ruleStr.length;i++){
				HashMap ruleMap = new HashMap();
				ruleMap.put("rule_id", ruleStr[i]);
				ruleMap.put("internum", ruleNumStr[i]);
				ruleList.add(ruleMap);
			}
		}
		return ruleList;
	}
}

