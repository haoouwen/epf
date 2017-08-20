/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CapitalmanagementService.java 
 */
package com.rbt.service.impl;
import com.rbt.dao.ICapitalmanagementDao;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.model.Capitalmanagement;
import com.rbt.model.Fundhistory;
import com.rbt.model.Memberfund;
import com.rbt.service.ICapitalmanagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 运营商资金管理Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Aug 23 14:17:23 CST 2014
 */
@Service
public class CapitalmanagementService extends GenericService<Capitalmanagement,String> implements ICapitalmanagementService {
	
	ICapitalmanagementDao capitalmanagementDao;
    @Autowired
	private IMemberfundDao memberfundDao;
    @Autowired
    private IFundhistoryDao fundhistoryDao;
	@Autowired
	public CapitalmanagementService(ICapitalmanagementDao capitalmanagementDao) {
		super(capitalmanagementDao);
		this.capitalmanagementDao = capitalmanagementDao;
	}
	
	
	/**
	 * @author : HZX
	 * @date : Aug 23, 2014 3:10:32 PM
	 * @Method Description :调整运营商资金
	 */
	public String fundUpdate(String radiochecked,String fund_num,String session_user_id,String reason,String remark) throws Exception {
		String actionMessage;
		// 实例化fundhistory
		Fundhistory fundhistory = new Fundhistory();
		Memberfund memberfund = this.memberfundDao.get("0");
		fundhistory.setCust_id("0");
		// 判断收入支出"0"表示收入
		if (radiochecked.equals("0")) {
			fundhistory.setFund_in(Double.parseDouble(fund_num));
			fundhistory.setFund_out(0.00);
			Double value_fund = Double.valueOf(memberfund.getFund_num()) + Double.valueOf(fund_num);
			Double value_usefund = Double.valueOf(memberfund.getUse_num()) + Double.valueOf(fund_num);
			memberfund.setUse_num((value_usefund+"").trim());
			memberfund.setFund_num((""+value_fund).trim());
			fundhistory.setBalance(value_usefund);
			actionMessage="运营商收入"+fund_num+"元，资金调整成功";
		} else {
			fundhistory.setFund_in(0.00);
			fundhistory.setFund_out(Double.parseDouble(fund_num));
			Double value_fund = Double.valueOf(memberfund.getFund_num()) - Double.valueOf(fund_num);
			Double value_usefund = Double.valueOf(memberfund.getUse_num()) - Double.valueOf(fund_num);
			memberfund.setUse_num((value_usefund+"").trim());
			memberfund.setFund_num((""+value_fund).trim());
			fundhistory.setBalance(value_usefund);
			actionMessage="运营商支出"+fund_num+"元，资金调整成功";
		}
		fundhistory.setUser_id(session_user_id);
		fundhistory.setReason(reason);
		fundhistory.setRemark(remark);
		// 更新余额表
		this.memberfundDao.update(memberfund);
		this.fundhistoryDao.insert(fundhistory);
		return actionMessage;
	}
}

