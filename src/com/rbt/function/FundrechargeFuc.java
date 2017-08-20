package com.rbt.function;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.rbt.common.util.DateUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Fundhistory;
import com.rbt.model.Fundrecharge;
import com.rbt.model.Memberfund;
import com.rbt.model.Payment;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IPaymentService;
import com.rbt.service.ISysfundService;

/**
 * @function 功能  充值处理
 * @author  创建人  HXK
 * @date  创建日期  Jul 29, 2014 1:10:56 PM
 */
public class FundrechargeFuc extends CreateSpringContext {
	/**
	 * @author HXK
	 * @Method Description : 会员帐号充值
	 * @date : Jan 28, 2014 2:26:22 PM
	 */
	public static void insertChongZhi(String pay_id,String fund_num,String order_id,String bankOrderId,String cust_str) throws Exception {
			
		IFundrechargeService fundrechargeService = (IFundrechargeService)getContext().getBean("fundrechargeService");
		IMemberfundService memberfundService = (IMemberfundService)getContext().getBean("memberfundService");
		ISysfundService sysfundService = (ISysfundService)getContext().getBean("sysfundService");
		IPaymentService paymentService = (IPaymentService)getContext().getBean("paymentService");
		//判断该充值 第三方 支付平台交易流水号 是否存在
		if(if_fundcharge(bankOrderId).equals("0")){
			//cust_str 的格式为：cust_id,user_id
			String custs[]=cust_str.split("\\-");
			String cust_id="",user_id="";
			if(custs!=null&&cust_str.length()>0){
				cust_id=custs[0].toString();
				user_id=custs[1].toString();
				//会员账号充值记录
				//充值是否审核 0:审核 1：不审核
				String is_chongzhi=SysconfigFuc.getSysValue("cfg_IsAuditChonzhi");
				if(!verifyTrxid(bankOrderId)){
					
					Fundrecharge fundrecharge=new Fundrecharge();
					fundrecharge.setCust_id(cust_id);
					fundrecharge.setUser_id(user_id);
					Payment payment = paymentService.get(pay_id);
					fundrecharge.setPayplat(payment.getPay_name());
					//插入第三方支付接口返回的订单ID
					fundrecharge.setBank_order_id(bankOrderId);
					fundrecharge.setFund_num(Double.valueOf(fund_num));
					//插入十位数的随机数订单号
					fundrecharge.setOrder_id(order_id);
					if(is_chongzhi.equals("0")){
						//未审核
						fundrecharge.setOrder_state("0");
					}else {
						//已审核
						fundrecharge.setOrder_state("1");
					}
					fundrechargeService.insert(fundrecharge);
					//修改余额可用资金
					if(is_chongzhi.equals("0")){//开启充值审核 
						//将充值金额 用等值的余额 放入平台冻结资金中，系统审核通过后 解冻到可用资金中
						sysfundService.addNumByOnLion(Double.valueOf(fund_num));
					}else if(is_chongzhi.equals("1")){//关闭充值审核
						memberfundService.outAndInNum(cust_id, Double.valueOf(fund_num), 1);
						sysfundService.outAndInNum(Double.valueOf(fund_num),1);
						//充值流水记录
						fundOnlineManage(order_id,cust_id,user_id,fund_num);
					}
					
				}
				
			}
		}
		
	}
	
	
	/**
	 * @funtion 校验订单是否已经支付了。
	 * @author QJY
	 * @param order_id
	 * @return boolean
	 */
	public static boolean verifyTrxid(String bankOrderId) throws Exception{
		IFundrechargeService fundrechargeService = (IFundrechargeService)getContext().getBean("fundrechargeService");
		Fundrecharge fundrecharge = fundrechargeService.getByTrxid(bankOrderId);
		if(fundrecharge!=null){
			return true;
		}else {
			return false;
		}
	} 
	
	/**
	 * @author QJY
	 * @Method Description : 会员帐号充值
	 * @date : Jan 28, 2015 2:26:22 PM
	 */
	public static void mobileRecharge(String pay_id,String order_id,String bankOrderId) throws Exception {		
		IFundrechargeService fundrechargeService = (IFundrechargeService)getContext().getBean("fundrechargeService");
		IMemberfundService memberfundService = (IMemberfundService)getContext().getBean("memberfundService");
		ISysfundService sysfundService = (ISysfundService)getContext().getBean("sysfundService");
		IPaymentService paymentService = (IPaymentService)getContext().getBean("paymentService");
		//会员账号充值记录
		//充值是否审核 0:审核 1：不审核
		String is_chongzhi=SysconfigFuc.getSysValue("cfg_IsAuditChonzhi");
		Fundrecharge fundrecharge=fundrechargeService.getByOrderId(order_id);
		String fund_num ="0";
		if(fundrecharge.getFund_num()!=null){
			fund_num = String.valueOf(fundrecharge.getFund_num());
		}
		String cust_id = fundrecharge.getCust_id();
		String user_id = fundrecharge.getUser_id();
		Payment payment = paymentService.get(pay_id);
		fundrecharge.setPayplat(payment.getPay_name());
		//插入第三方支付接口返回的订单ID
		fundrecharge.setBank_order_id(bankOrderId);
		fundrecharge.setRecharge_state("1");//充值成功
		fundrecharge.setPay_date(DateUtil.parseDateTime(new Date()));
		fundrechargeService.update(fundrecharge);
		//修改余额可用资金
		if(is_chongzhi.equals("0")){//开启充值审核 
			//将充值金额 用等值的余额 放入平台冻结资金中，系统审核通过后 解冻到可用资金中
			sysfundService.addNumByOnLion(Double.valueOf(fund_num));
		}else if(is_chongzhi.equals("1")){//关闭充值审核
			memberfundService.outAndInNum(cust_id, Double.valueOf(fund_num), 1);
			sysfundService.outAndInNum(Double.valueOf(fund_num),1);
			//充值流水记录
			fundOnlineManage(order_id,cust_id,user_id,fund_num);
		}

	}
	
	/**
	 * 创建新充值订单
	 */
	public static void createFundrecharge(String order_id,String fund_num,String para){
		
		IFundrechargeService fundrechargeService = (IFundrechargeService)getContext().getBean("fundrechargeService");
		Fundrecharge fundrecharge = new Fundrecharge();
		fundrecharge.setOrder_id(order_id);
		fundrecharge.setFund_num(Double.valueOf(fund_num));
		
		String custs[]=para.split("\\-");
		String cust_id="",user_id="";
		if(custs!=null&&para.length()>0){
			cust_id=custs[0].toString();
			user_id=custs[1].toString();
		}
		fundrecharge.setCust_id(cust_id);
		fundrecharge.setUser_id(user_id);
		fundrecharge.setOrder_state("0");
		fundrechargeService.insert(fundrecharge);
		
	}
	
	/**
	 * @author: HXK
	 * @date : Aug 15, 2014 11:21:51 AM
	 * @Method Description : 
	 */
     public static String if_fundcharge(String bankOrderId){
    	 //返回结果，如果存在该充值信息返回1 否则返回0
    	 String retString="0";
    	 IFundrechargeService fundrechargeService = (IFundrechargeService)getContext().getBean("fundrechargeService");
    	//先处理该充值信息是否已经插入数据库
 		List oldfundList=new ArrayList();
 		HashMap oldefunMap=new HashMap();
 		oldefunMap.put("bank_order_id", bankOrderId);
 		oldfundList=fundrechargeService.getList(oldefunMap);
    	if(oldfundList!=null&&oldfundList.size()>0){
    		retString="1";
    	}
    	 return retString;
     }	
	
     /**	
 	 * @author : HXK
 	 * @param :
 	 * @date Mar 12, 2014 1:33:55 PM
 	 * @Method Description :资金处理
 	 */
 	private static void fundOnlineManage(String order_id,String  buy_cust_id,String buy_user_id,String fund_num){
 		IMemberfundService memberfundService = (IMemberfundService)getContext().getBean("memberfundService");
 		IFundhistoryService fundhistoryService = (IFundhistoryService)getContext().getBean("fundhistoryService");
 		Double buy_fund_num = Double.parseDouble(fund_num);
 		//买家资金处理
 		Double buy_use_num = 0.0;//可用金额
 		Memberfund buy_mf = memberfundService.get(buy_cust_id);
 		if(buy_mf != null){
 			buy_use_num = Double.parseDouble(buy_mf.getUse_num());
 		}
 		//买家的资金异动
 		Fundhistory buy_fh = new Fundhistory();
 		buy_fh.setBalance(buy_use_num);
 		buy_fh.setCust_id(buy_cust_id);
 		buy_fh.setFund_in(buy_fund_num);
 		buy_fh.setFund_out(0.00);
 		buy_fh.setUser_id(buy_user_id);
 		buy_fh.setReason("会员在线充值:"+fund_num+"元,充值订单号为"+order_id);
 		fundhistoryService.insert(buy_fh); 
 	}
	
}