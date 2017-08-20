/*
 
 * Package:com.rbt.action
 * FileName: SendshipmodeAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.model.Sendshipmode;
import com.rbt.service.ISendshipmodeService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 快递方式action类
 * @author 创建人 HZX
 * @date 创建日期 Tue May 06 11:17:02 CST 2014
 */
@Controller
public class SendshipmodeAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 快递方式对象
	 */
	private Sendshipmode sendshipmode;
	/*******************业务层接口****************/
	/*
	 * 快递方式业务层接口
	 */
	@Autowired
	private ISendshipmodeService sendshipmodeService;
	
	/*********************集合*******************/
	/*
	 * 快递方式信息集合
	 */
	public List sendshipmodeList;
	/*********************字段*******************/
	public String smode_name_s;
	public String is_insured_s;//是否保险
	public String rate_s;//运费
	public String mix_insured_s;//最小保险
	public String max_insured_s;
	public String reach_pay_s;//到付
	public String sendmode_sortno_id;
	public String isort_no;//排序
	
	/**
	 * 方法描述：返回新增发货方式表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增发货方式表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		sendshipmode.setSmode_id(RandomStrUtil.getNumberRand());
		if(!commonCheck()){
			return add();
		}
		this.sendshipmodeService.insert(sendshipmode);
		this.addActionMessage("新增发货方式成功！");
		this.sendshipmode = null;
		return INPUT;
	}
	/**
	 * 方法描述：修改发货方式表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!commonCheck()){
			return view();
		}
		this.sendshipmodeService.update(sendshipmode);
		this.addActionMessage("修改发货方式表成功！");
		return list();
	}
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 9:43:36 AM
	 * @Method Description :通用校验
	 */
	public boolean commonCheck(){
		//验证发货方式名称是否重复
		if (existsTitle(sendshipmode.getSmode_name(), oldinfotitle, "sendshipmode","smode_name")) {
			this.addFieldError("sendshipmode.smode_name", "该发货方式名称已经存在,请重新输入");
		}
		if(sendshipmode.getRate()==null||sendshipmode.getRate().equals(""))
	    {
	    	sendshipmode.setRate("0.00");
	    }
	    if(sendshipmode.getMax_insured()==null||sendshipmode.getMax_insured().equals(""))
	    {
	    	sendshipmode.setMax_insured(0.00);
	    }
	    if(sendshipmode.getMix_insured()==null||sendshipmode.getMix_insured().equals(""))
	    {
	    	sendshipmode.setMix_insured(0.00);
	    }
	    if(sendshipmode.getSort_no()==null||sendshipmode.getSort_no().equals(""))
	    {
	    	sendshipmode.setSort_no("0");
	    }
	    super.commonValidateField(sendshipmode);
		if(super.ifvalidatepass){
			return false;
		}
	    return true;
	}
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.sendshipmodeService.updateSort("smode_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("发货方式排序成功");
		}else{
			this.addActionMessage("发货方式排序失败");
		}
		return list();
	}
	
	
	/**
	 * 方法描述：删除发货方式表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.sendshipmodeService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除发货方式表成功");
		}else{
			this.addActionMessage("删除发货方式表失败");
		}
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @date : May 17, 2014 4:48:58 PM
	 * @Method Description : 找出所有的发货方式
	 */
	public void modeList() throws IOException{
		//AJAX获取操作获取运费
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map pageMap = new HashMap();	
		sendshipmodeList = this.sendshipmodeService.getList(pageMap);
		JsonUtil json=new JsonUtil();
		String shopStr = json.list2json(sendshipmodeList);
		out.print(shopStr);	
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		if(smode_name_s!=null&&!"".equals(smode_name_s))
		{
			pageMap.put("smode_name", smode_name_s);
		}
		if(is_insured_s!=null&&!"".equals(is_insured_s))
		{
			pageMap.put("is_insured", is_insured_s);
		}
		if(rate_s!=null&&!"".equals(rate_s))
		{
			pageMap.put("rate", rate_s);
		}
		if(mix_insured_s!=null&&!"".equals(mix_insured_s))
		{
			pageMap.put("mix_insured", mix_insured_s);
		}
		if(max_insured_s!=null&&!"".equals(max_insured_s))
		{
			pageMap.put("max_insured", max_insured_s);
		}
		if(reach_pay_s!=null&&!"".equals(reach_pay_s))
		{
			pageMap.put("reach_pay", reach_pay_s);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.sendshipmodeService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		sendshipmodeList = this.sendshipmodeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出发货方式表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.sendshipmode.getSmode_id();
		if(id==null || id.equals("")){
			sendshipmode = new Sendshipmode();
		}else{
			sendshipmode = this.sendshipmodeService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the sendshipmodeList
	 */
	public List getSendshipmodeList() {
		return sendshipmodeList;
	}
	
	public String getSmode_name_s() {
		return smode_name_s;
	}
	public void setSmode_name_s(String smode_name_s) {
		this.smode_name_s = smode_name_s;
	}
	public String getIs_insured_s() {
		return is_insured_s;
	}
	public void setIs_insured_s(String is_insured_s) {
		this.is_insured_s = is_insured_s;
	}
	public String getRate_s() {
		return rate_s;
	}
	public void setRate_s(String rate_s) {
		this.rate_s = rate_s;
	}
	public String getMix_insured_s() {
		return mix_insured_s;
	}
	public void setMix_insured_s(String mix_insured_s) {
		this.mix_insured_s = mix_insured_s;
	}
	public String getMax_insured_s() {
		return max_insured_s;
	}
	public void setMax_insured_s(String max_insured_s) {
		this.max_insured_s = max_insured_s;
	}
	public String getReach_pay_s() {
		return reach_pay_s;
	}
	public void setReach_pay_s(String reach_pay_s) {
		this.reach_pay_s = reach_pay_s;
	}
	/**
	 * @param sendshipmodeList
	 *  the sendshipmodeList to set
	 */
	public void setsendshipmodeList(List sendshipmodeList) {
		this.sendshipmodeList = sendshipmodeList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sendshipmode == null){
			sendshipmode = new Sendshipmode();
		}
		String id = this.sendshipmode.getSmode_id();
		if(!this.validateFactory.isDigital(id)){
			sendshipmode = this.sendshipmodeService.get(id);
		}
	}
	/**
	 * @return the sendshipmode
	 */
	public Sendshipmode getSendshipmode() {
		return sendshipmode;
	}
	/**
	 * @param sendshipmode
	 *            the sendshipmode to set
	 */
	public void setSendshipmode(Sendshipmode sendshipmode) {
		this.sendshipmode = sendshipmode;
	}
}

