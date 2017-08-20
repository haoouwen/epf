/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CertificationService.java 
 */
package com.rbt.service.impl;


import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.ICertificationDao;
import com.rbt.model.Certification;
import com.rbt.service.ICertificationService;
import com.rbt.service.ICommparaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录企业身份认证信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Wed Nov 30 13:34:38 CST 2014
 */
@SuppressWarnings("unchecked")
@Service
public class CertificationService extends GenericService<Certification,String> implements ICertificationService {
	
	ICertificationDao certificationDao;
	@Autowired
	public CertificationService(ICertificationDao certificationDao) {
		super(certificationDao);
		this.certificationDao = certificationDao;
	}

	public void auditState(Certification t) {
		this.certificationDao.auditState(t);
	}
	//@Autowired
	//private IMembercreditService membercreditService;
	//@Autowired
	//private ICredithistoryService credithistoryService;
	@Autowired
	private ICommparaService commparaService;
//	@Autowired
	//private IMemberService memberService;
//	@Autowired
//	private ICreditdetailService creditdetailService;
	
	/**
	 * @Method Description : 插入会员信用指数表
	 * @author : LJQ
	 * @date : Dec 2, 2014 3:15:20 PM
	 */
	//第一个参数需要操作的CUST_ID,第二个参数传正负一，第三个参数指数值,第四个参数理由类型，第五个参数为理由内容,第六个标题名称
	@SuppressWarnings("unchecked")
	public void creditChangeNum(String cust_id,int symbol,String fund_value,String reason_type,String reason,String title){
		
		  /*  //从配置表取出信用指数类别名称
		    String opaReason = com.rbt.function.CommparaFuc.get_commparakey_By_value(reason_type, "credit_type");

		 	//判断是否要从配置表中获取信用指数值
		    String fund_num=""; 
		    if(fund_value.indexOf("cfg")>-1){
		    	//获取要加入的信用指数
		    	fund_num= SysconfigFuc.getSysValue(fund_value);
		    }else{
		    	//如果不是配置表来获取信用的值则直接接受参数值
		    	fund_num=fund_value;
		    }
		    		    
		    
			int c_num=symbol*Integer.parseInt(fund_num);
			int now_num=0;
			//插入会员信用指数
			Map numMap=new HashMap();
			numMap.put("cust_id", cust_id);		
			List list =this.membercreditService.getList(numMap);
			Membercredit membercredit=new Membercredit();
			membercredit.setCust_id(cust_id);
			if(list!=null&&list.size()>0){
				Map listMap = (HashMap)list.get(0);
				now_num=Integer.parseInt(listMap.get("c_num").toString());
				now_num+=c_num;//得到获得后的指数
				membercredit.setC_num(String.valueOf(now_num));
				this.membercreditService.update(membercredit);			
			}else{		
				now_num=c_num;
				membercredit.setC_num(String.valueOf(c_num));
				this.membercreditService.insert(membercredit);
			}
			
			
			
			
			//插入会员信用历史指数记录表
			Credithistory credithistory=new Credithistory();
			credithistory.setCust_id(cust_id);
			credithistory.setC_num(String.valueOf(c_num));
			credithistory.setNow_num(String.valueOf(now_num));
			credithistory.setR_type(reason_type);//从配置表中取
			//定义操作描述
			StringBuffer sb=new StringBuffer();
			//找出cust_id对应的会员名称
			Member member=this.memberService.get(cust_id);
			if(member!=null&&member.getCust_name()!=null){
				sb.append("["+member.getCust_name()+"]");
			}			
			sb.append(opaReason);//操作理由从配置表中获取
			sb.append(reason);//成功,删除，更新
			//判断是否是评价
			if(reason_type.equals("c")){
				if(c_num>0){
					sb.append("好评");
				}else if(c_num==0){
					sb.append("中评");
				}else{
					sb.append("差评");
				}
			}
			//语言描述加分
			if(c_num>0){
				sb.append("加"+fund_num+"分");
			}else if(c_num==0){
				sb.append("不加分");
			}else{
				sb.append("减"+c_num*(-1)+"分");//转成正数
			}
			credithistory.setReason(sb.toString());
			this.credithistoryService.insert(credithistory);
			//对信用指数的操作
			creditditail(cust_id,c_num,reason_type,sb.toString(),title);*/
	}
	
	/**
	 * @Method Description : 信用指数明细表的操作
	 * @author : LJQ
	 * @date : Dec 8, 2014 9:11:18 PM
	 */
	@SuppressWarnings("unchecked")
	private void creditditail(String cust_id,int c_num,String reason_type,String reason,String title){
		/*//添加到会员信用指数明细
		Creditdetail cd=new Creditdetail();
		cd.setCust_id(cust_id);
		cd.setR_type(reason_type);
		cd.setR_name(reason);
		cd.setR_num(String.valueOf(c_num));
		//a:表示企业实名认证 b:表示资质证书审核 c:表示评价成功 d:VIP年限
		if(c_num>0){
			this.creditdetailService.insert(cd);
		}else if(reason_type.equals("a")){
			Map map=new HashMap();
			map.put("cust_id", cust_id);
			map.put("r_type", reason_type);
			this.creditdetailService.delcredit(map);
		}else if(reason_type.equals("b")){
			Map map=new HashMap();
			map.put("cust_id", cust_id);
			map.put("r_type", reason_type);
			map.put("r_name", title);
			this.creditdetailService.delcredit(map);
		}else if(reason_type.equals("c")){
			this.creditdetailService.insert(cd);
		}*/
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:15:37 PM
	 * @Method Description：对审核通过的实名认证删除操作减分
	 */
	public void del(String id){
		id = id.replace(" ", "");
		String[] ids=id.split(",");
		for(int i = 0;i<ids.length;i++){
			Certification cfc=new Certification();
			if(!ValidateUtil.isRequired(ids[i])){
				cfc=this.certificationDao.get(ids[i]);
			}		
			String state=cfc.getInfo_state();
			//对审核通过的实名认证删除操作减分
			if(state!=null && state.equals("3")){
				creditChangeNum(ids[i], -1, "cfg_identity","a","删除成功","");
			}
		}		
	}
}

