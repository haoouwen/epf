/*
 
 * Package:com.rbt.action
 * FileName: RateAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.model.Rate;
import com.rbt.service.IRateService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.common.util.ValidateUtil;

/**
 * @function 功能 汇率信息action类
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 21 12:46:55 CST 2014
 */
@Controller
public class RateAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Rate rate;
	
	/*******************业务层接口****************/

	@Autowired
	private IRateService rateService;
	/*********************集合********************/
	public List rateList;//汇率信息s
	
	/*********************字段********************/
	
	public String rate_name_s;

	
	/**
	 * 方法描述：返回新增汇率信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增汇率信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//设置参数ID为随机数
		String rate_id=RandomStrUtil.getNumberRand();
		rate.setRate_id(rate_id);
		//将汇率设置为3位小数
		String number=String.valueOf(rate.getExchangerate());
		if(rate.getEndefault().equals("0")){
			this.rateService.updateendefault();
		}
		
		super.commonValidateField(rate);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.rateService.insert(rate);
		this.addActionMessage("新增汇率信息成功！");
		this.rate = null;
		return INPUT;
	}


	/**
	 * 方法描述：修改汇率信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id=rate.getRate_id();
		if(id==null&&"".equals(id)){
			return view();
		}
		if(rate.getEndefault().equals("0")){
			this.rateService.updateendefault();
		}
		super.commonValidateField(rate);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.rateService.update(rate);
		this.addActionMessage("修改汇率信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除汇率信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.rateService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除汇率信息成功");
		}else{
			this.addActionMessage("删除汇率信息失败");
		}
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		
		Map pageMap = new HashMap();
		
		pageMap.put("endefault", "1");
		if (!ValidateUtil.isRequired(rate_name_s)) {
			pageMap.put("rate_name", rate_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.rateService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出当前的汇率是否默认为0
		
		Map enableMap = new HashMap();
		enableMap.put("endefault", "0");
		rateList = this.rateService.getList(enableMap);
		
		rateList = this.rateService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出汇率信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.rate.getRate_id();
		if(id==null || id.equals("")){
			rate = new Rate();
		}else{
			rate = this.rateService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the RateList
	 */
	public List getRateList() {
		return rateList;
	}
	/**
	 * @param rateList
	 *  the RateList to set
	 */
	public void setRateList(List rateList) {
		this.rateList = rateList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(rate == null){
			rate = new Rate();
		}
		String id = this.rate.getRate_id();
		if(!this.validateFactory.isDigital(id)){
			rate = this.rateService.get(id);
		}
	}

	/**
	 * @return the rate
	 */
	public Rate getRate() {
		return rate;
	}
	/**
	 * @param Rate
	 *            the rate to set
	 */
	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
}

