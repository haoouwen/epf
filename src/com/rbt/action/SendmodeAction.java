/*
 
 * Package:com.rbt.action
 * FileName: SendmodeAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Sendmode;
import com.rbt.service.IPrintstyleService;
import com.rbt.service.ISendmodeService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 配送方式表action类
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2014
 */
@Controller
public class SendmodeAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Sendmode sendmode;
	/*******************业务层接口****************/

	@Autowired
	private ISendmodeService sendmodeService;
	@Autowired
	private IPrintstyleService printstyleService;
	/*********************集合********************/
	public List sendmodeList;//配送方式
	public List printStyleList;//获取快递单打印样式
	/*********************字段********************/
	
	public String smode_name_s;
	public String is_insured_s;//是否保险
	public String rate_s;//运费
	public String mix_insured_s;//最小保险
	public String max_insured_s;
	public String reach_pay_s;//到付
	public String sendmode_sortno_id;
	public String isort_no;//排序
	
	/**
	 * 方法描述：返回新增配送方式表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("print_style", "1");
		printStyleList=printstyleService.getList(map);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增配送方式表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		sendmode.setSmode_id(RandomStrUtil.getNumberRand());
		if(!commonCheck()){
			return add();
		}
		this.sendmodeService.insert(sendmode);
		this.addActionMessage("新增配送方式成功！");
		this.sendmode = null;
		return INPUT;
	}
	/**
	 * 方法描述：修改配送方式表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!commonCheck()){
			return view();
		}
		this.sendmodeService.update(sendmode);
		this.addActionMessage("修改配送方式表成功！");
		return list();
	}
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 9:43:36 AM
	 * @Method Description :通用校验
	 */
	public boolean commonCheck(){
		//验证配送方式名称是否重复
		if (existsTitle(sendmode.getSmode_name(), oldinfotitle, "sendmode","smode_name")) {
			this.addFieldError("sendmode.smode_name", "该配送方式名称已经存在,请重新输入");
		}
		if(ValidateUtil.isRequired(sendmode.getTrade_id())){
			this.addFieldError("sendmode.trade_id", "请选择选择打印方式");
		}
		super.commonValidateField(sendmode);
		if(super.ifvalidatepass){
			return false;
		}
		if(sendmode.getRate()==null||sendmode.getRate().equals(""))
	    {
	    	sendmode.setRate("0.00");
	    }
	    if(sendmode.getMax_insured()==null||sendmode.getMax_insured().equals(""))
	    {
	    	sendmode.setMax_insured(0.00);
	    }
	    if(sendmode.getMix_insured()==null||sendmode.getMix_insured().equals(""))
	    {
	    	sendmode.setMix_insured(0.00);
	    }
	    if(sendmode.getSort_no()==null||sendmode.getSort_no().equals(""))
	    {
	    	sendmode.setSort_no("0");
	    }
	    return true;
	}
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.sendmodeService.updateSort("smode_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("配送方式排序成功");
		}else{
			this.addActionMessage("配送方式排序失败");
		}
		return list();
	}
	
	
	/**
	 * 方法描述：删除配送方式表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.sendmodeService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除配送方式表成功");
		}else{
			this.addActionMessage("删除配送方式表成功失败");
		}
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException 
	 * @date : May 17, 2014 4:48:58 PM
	 * @Method Description : 找出所有的配送方式
	 */
	public void modeList() throws IOException{
		//AJAX获取操作获取运费
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map pageMap = new HashMap();	
		sendmodeList = this.sendmodeService.getList(pageMap);
		JsonUtil json=new JsonUtil();
		String shopStr = json.list2json(sendmodeList);
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
		int count=this.sendmodeService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		sendmodeList = this.sendmodeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出配送方式表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.sendmode.getSmode_id();
		if(id==null || id.equals("")){
			sendmode = new Sendmode();
		}else{
			Map map=new HashMap();
			map.put("cust_id", this.session_cust_id);
			map.put("print_style", "1");
			printStyleList=printstyleService.getList(map);
			sendmode = this.sendmodeService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SendmodeList
	 */
	public List getSendmodeList() {
		return sendmodeList;
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
	 * @return the sendmode
	 */
	public Sendmode getSendmode() {
		return sendmode;
	}
	/**
	 * @param Sendmode
	 *            the sendmode to set
	 */
	public void setSendmode(Sendmode sendmode) {
		this.sendmode = sendmode;
	}
	/**
	 * @param sendmodeList
	 *  the SendmodeList to set
	 */
	public void setSendmodeList(List sendmodeList) {
		this.sendmodeList = sendmodeList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sendmode == null){
			sendmode = new Sendmode();
		}
		String id = this.sendmode.getSmode_id();
		if(!this.validateFactory.isDigital(id)){
			sendmode = this.sendmodeService.get(id);
		}
	}

}

