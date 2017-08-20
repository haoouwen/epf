/*
 * Package:com.rbt.function
 * FileName: MembercreditFuc.java 
 */
package com.rbt.function;
import com.rbt.model.Membercredit;
import com.rbt.service.IMembercreditService;

/**
 * @function 功能 会员信誉
 * @author 创建人 HXK
 * @date 创建日期 2015-09-22
 */
public class MembercreditFuc extends CreateSpringContext {

	/**
	 * 获取会员信息
	 * 
	 * @param cust_id
	 * @param user_id
	 */
	public static void updateSellNum(String cust_id, int num) {
		IMembercreditService membercreditService = (IMembercreditService) getContext()
				.getBean("membercreditService");
		Membercredit membercredit = membercreditService.getByCustId(cust_id);
		int sell_num = Integer.valueOf(membercredit.getSell_num());
		//0 中评 1 好评 -1 差评
		switch (num) {
		case 0:
			
			break;
		case 1:
			sell_num = sell_num + 1;
			break;
		case -1:
			if (sell_num != 0) {
				sell_num = sell_num - 1;
			}
			break;

		default:
			break;
		}
		membercredit.setSell_num(sell_num+"");
		membercreditService.update(membercredit);
	}

	/**
	 * 增加买家
	 * 
	 * @param cust_id
	 * @param user_id
	 */
	public static void updateBuyNum(String cust_id, String user_id) {
//		IMembercreditService membercreditService = (IMembercreditService) getContext()
//				.getBean("membercreditService");
//		Membercredit membercredit = new Membercredit();
//		membercredit.setCust_id(cust_id);
//		membercredit.setUser_id(user_id);
//		membercreditService.updateBuyNum(membercredit);
//	   //获取买家信息
//		membercredit = membercreditService.getByCustId(cust_id);
//	    //卖家等级的改变
//		updateGrade(membercredit.getBuy_num(), membercredit.getCust_id(),"1");
	}
	
   /**
    * 升级等级
    * @param num
    * @param cust_id
    */	
   public static void updateGrade(String num,String cust_id,String mem_type){
//	   IMalllevelsetService malllevelsetService = (IMalllevelsetService) getContext().getBean("malllevelsetService");
//	   IMemberService memberService = (IMemberService) getContext().getBean("memberService");
//	   //获取买家等级
//		Map sellMap=new HashMap();
//		sellMap.put("mem_type",mem_type);
//		List sellList=malllevelsetService.getList(sellMap);
//		double heigth=0;
//		String level_code="";
//		for(int i=0;i<sellList.size();i++){
//			Map maps=(HashMap)sellList.get(i);
//		    if(Double.valueOf(num)>=Double.valueOf((maps.get("inter_lower").toString())) && Double.valueOf(num)<=Double.valueOf((maps.get("inter_height").toString()))){
//		    	level_code=maps.get("level_code").toString();
//		    	break;
//		    }else{
//		    	if(heigth<Double.valueOf((maps.get("inter_height").toString()))){
//		    		heigth=Double.valueOf((maps.get("inter_height").toString()));
//		    		level_code=maps.get("level_code").toString();
//		    	}
//		    }
//		}
//		//查找会员
//		Member member=memberService.get(cust_id);
//		if(member==null){
//			member=new Member();
//		}
//		if(mem_type.equals("0"))
//			member.setSell_level(level_code);
//		else{
//			member.setBuy_level(level_code);
//		}
//		memberService.update(member);
   }

}