/*
 
 * Package:com.rbt.action
 * FileName: FundhistoryAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Fundhistory;
import com.rbt.model.Memberfund;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberfundService;

/**
 * @function 功能 会员余额流action类
 * @author 创建人 CYC
 * @date 创建日期 Wed Jul 13 10:06:11 CST 2014
 */
@Controller
public class FundhistoryAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	/** *****************实体层*************** */
	public Fundhistory fundhistory;
	public Memberfund memberfund;

	/** *****************业务层接口*************** */
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public IMemberfundService memberfundService;

	/** *******************集合***************** */
	public List fundhistoryList;// 会员余额流信息集合

	/** *******************字段***************** */
	public String cust_id_s;// 会员ID
	public String cust_name_s;// 会员名称
	public String in_date_s;// 发布时间
	public String enddateString;// 操作时间结束时间
	public String starttime_s;// 时间段开始
	public String endtime_s;// 时间段结束
	public String cust_id;// 会员ID
	public String enter;// 页面跳转标识符

	/**
	 * 方法描述：返回新增会员余额流页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员余额流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.fundhistoryService.insert(fundhistory);
		this.addActionMessage("新增余额流信息成功");
		this.fundhistory = null;
		return add();
	}

	/**
	 * 方法描述：修改会员余额流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.fundhistoryService.update(fundhistory);
		this.addActionMessage("修改余额流信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员余额流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.fundhistoryService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除余额流信息成功");
		} else {
			this.addActionMessage("删除余额流信息失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		commonList(false,true);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：查看运营商对应的余额流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yysList() throws Exception {
		Map pageMap = new HashMap();
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}
		pageMap.put("cust_id", "0");
		//用于是否要分页判断
		int count = this.fundhistoryService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		fundhistoryList = this.fundhistoryService.getList(pageMap);
		return goUrl("yys"+INDEXLIST);
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String frostList() throws Exception {
		commonList(true,true);
		return goUrl("frostindex");
	}

	/**
	 * @Method Description :通用列表
	 * @author : HZX
	 * @date : Mar 14, 2014 2:23:59 PM
	 */
	public void commonList(Boolean flag,boolean flg) {

		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (cust_id_s != null && !cust_id_s.equals("")) {
			pageMap.put("cust_id", cust_id_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}
		if (flag) {
			pageMap.put("action_type", "3,4");
		}

		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}

		
		// 过滤地区
		// pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		//用于是否要分页判断
		if(flg){
		  int count = this.fundhistoryService.getCount(pageMap);
		  // 分页插件
		  pageMap = super.pageTool(count, pageMap);
		}
		fundhistoryList = this.fundhistoryService.getList(pageMap);
	}

	/**
	 * @Method Description :导出余额流
	 * @author : HZX
	 * @date : Mar 14, 2014 2:22:22 PM
	 */
	public String export() throws Exception {
		// 导出余额数据操作
		commonList(false,false);
		this.fundhistoryService.exportExcel(fundhistoryList, getResponse());
		this.addActionMessage("系统日志导出成功!");
		return list();
	}
	
	/**
	 * @Method Description :导出余额流
	 * @author : HZX
	 * @date : Mar 14, 2014 2:22:22 PM
	 */
	public String yysExport() throws Exception {
		// 导出余额数据操作
		Map pageMap = new HashMap();
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}
		pageMap.put("cust_id", "0");
		//用于是否要分页判断
		int count = this.fundhistoryService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		fundhistoryList = this.fundhistoryService.getList(pageMap);
		this.fundhistoryService.exportExcel(fundhistoryList, getResponse());
		this.addActionMessage("系统日志导出成功!");
		return list();
	}
	/**
	 * 方法描述：根据主键找出会员余额流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if (fundhistory.getCust_id() != null) {
			if (accessControl(fundhistory.getCust_id())) {
				return list();
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the FundhistoryList
	 */
	public List getFundhistoryList() {
		return fundhistoryList;
	}

	/**
	 * @param fundhistoryList
	 *            the FundhistoryList to set
	 */
	public void setFundhistoryList(List fundhistoryList) {
		this.fundhistoryList = fundhistoryList;
	}

	public String getCust_id_s() {
		return cust_id_s;
	}

	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public String getIn_date_s() {
		return in_date_s;
	}

	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	public String getEnddateString() {
		return enddateString;
	}

	public void setEnddateString(String enddateString) {
		this.enddateString = enddateString;
	}

	/**
	 * @return the fundhistory
	 */
	public Fundhistory getFundhistory() {
		return fundhistory;
	}

	/**
	 * @param Fundhistory
	 *            the fundhistory to set
	 */
	public void setFundhistory(Fundhistory fundhistory) {
		this.fundhistory = fundhistory;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (fundhistory == null) {
			fundhistory = new Fundhistory();
		}
		String id = fundhistory.getTrade_id();
		if (!ValidateUtil.isDigital(id)) {
			fundhistory = this.fundhistoryService.get(id);
		}
		System.out.println(fundhistory);
	}

}
