package com.rbt.webappaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Comsumer;
import com.rbt.model.Excoupons;
import com.rbt.service.IComsumerService;
import com.rbt.service.IExcouponsService;

public class WebAppcomsumerAction extends WebAppgoodsModelAction implements Preparable {

	/*******************实体层****************/

	/*******************业务层接口****************/
	@Autowired
	private IComsumerService comsumerService;
	@Autowired
	private IExcouponsService excouponsService;

	/*********************集合******************/
	
	public List comsumerList;//优惠券列表
	


	/*********************字段******************/
    public String coupon_no;//优惠券号码

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("use_state", "0");
        pageMap.put("date", "now");
        pageMap.put("cust_id", this.session_cust_id);
		// 根据页面提交的条件找出信息总数
		int count = this.comsumerService.getCount(pageMap);
		// 分页插件
		pageMap = super.webAppPageTool(count, pageMap);
        comsumerList = this.comsumerService.getList(pageMap);
        comsumerList = ToolsFuc.replaceList(comsumerList, "");
		return goUrl("mbCoupon");
	}
	
	/**
	 * 兑换优惠券
	 * @throws IOException
	 */
	public String exCoupon() throws Exception {
		if(!ValidateUtil.isRequired(coupon_no)){
			Map map = new HashMap();
			map.put("coupon_no", coupon_no);
			List list = this.excouponsService.getList(map);
			
			//判断是否已经兑换过
			if (list != null && list.size() > 0 ) {
				  Map couponMap = (HashMap) list.get(0);
				  if(couponMap.get("ex_state") != null && couponMap.get("ex_state").toString().equals("1")) {
					  //优惠券已经兑换过 
					  this.addFieldError("coupon_no", "优惠券已经兑换过！");
					  return goUrl("mbExCoupon");
				   }else {
					  //兑换优惠券
					  String ex_id = couponMap.get("ex_id").toString();
					  Excoupons excoupons = this.excouponsService.get(ex_id);
					  //插入优惠券
					  Comsumer comsumer = new Comsumer();
					  comsumer.setComsumer_no(coupon_no);
					  comsumer.setCoupon_id(excoupons.getCoupon_id());
					  comsumer.setCust_id(this.session_cust_id);
					  comsumer.setUse_state("0");
					  this.comsumerService.insert(comsumer);
					  //修改兑换状态
					  excoupons.setEx_state("1");
					  this.excouponsService.update(excoupons);
					  return list();
				   }
			}else { 
			   //判断优惠券号码不存在
				this.addFieldError("coupon_no", "优惠券号码错误！");
				return goUrl("mbExCoupon");
			   
			}
		}else{
			this.addFieldError("coupon_no", "请选填写优惠券号码!");
			return goUrl("mbExCoupon");
		}
		
		
	}
	
	
	public void prepare() throws Exception {
	}

}
