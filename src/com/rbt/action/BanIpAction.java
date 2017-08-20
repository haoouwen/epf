/*
 
 * Package:com.rbt.action
 * FileName: BanIpAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Banip;
import com.rbt.service.IBanipService;

/**
 * @function 功能 禁止IP管理action类
 * @author 创建人 HXK
 * @date 创建日期 July 5, 2014
 */
@Controller
public class BanIpAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = -7634169656972328499L;
	
	/*******************实体层****************/
	public Banip banip;

	/*******************业务层接口****************/
	@Autowired
	public IBanipService ban_ipService;

	/*********************集合******************/
	public List banipList;//IP

	/*********************字段******************/
	private String admin_ip;//批量获取选择的IP
	public String admin_ip_id;//批量更新数据时候，获取所有ID
	public String ip_s;//ip搜索
	public String ipname;// 获取添加的IP名称
	public String ipnameend;//获取添加的IP结束
	
	/**
	 * 方法描述：新增ip
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		Map pageMap = new HashMap();
		if (ipname != null && !ipname.equals("")) pageMap.put("ip", ipname+"-"+ipnameend);
		// 根据页面提交的条件找出信息总数
		int count = this.ban_ipService.getCount(pageMap);
		if (count == 0) {
			Banip banIpObj = new Banip();
			banIpObj.setIp(ipname+"-"+ipnameend);
			banIpObj.setUser_id(this.session_user_id);
			this.ban_ipService.insert(banIpObj);
			this.addActionMessage("新增IP成功");
		} else {
			this.addActionMessage("禁用的IP已经存在，无需重复提交");
		}
		//情况添加数据返回列表页
		ipname=null;
		ipnameend=null;
		return list();
	}

	/**
	 * 方法描述：删除ip信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.ban_ipService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除IP成功");
		}else{
			this.addActionMessage("删除IP失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (ip_s != null && !"".equals(ip_s)) pageMap.put("ip", ip_s);
		// 根据页面提交的条件找出信息总数
		int count = this.ban_ipService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		banipList = this.ban_ipService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出禁用IP信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量更新IP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAllIP() throws Exception {
        List banipidList=this.ban_ipService.banipidList(this.admin_ip_id, this.admin_ip, this.session_user_id);
		this.ban_ipService.updateAllIp(banipidList);
		this.addActionMessage("更新IP成功");
		return list();
	}
	
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(banip == null){
			banip = new Banip();
		}
		String id = this.banip.getIp_id();
		if(!ValidateUtil.isDigital(id)){
			banip = this.ban_ipService.get(id);
		}
	}
	
	public String getIpnameend() {
		return ipnameend;
	}

	public void setIpnameend(String ipnameend) {
		this.ipnameend = ipnameend;
	}

	public Banip getBanip() {
		return banip;
	}

	public void setBanip(Banip banip) {
		this.banip = banip;
	}

	public List getBanipList() {
		return banipList;
	}

	public void setBanipList(List banipList) {
		this.banipList = banipList;
	}

	public String getIp_s() {
		return ip_s;
	}

	public void setIp_s(String ip_s) {
		this.ip_s = ip_s;
	}

	public String getAdmin_ip_id() {
		return admin_ip_id;
	}

	public void setAdmin_ip_id(String admin_ip_id) {
		this.admin_ip_id = admin_ip_id;
	}

	public String getAdmin_ip() {
		return admin_ip;
	}

	public void setAdmin_ip(String admin_ip) {
		this.admin_ip = admin_ip;
	}

	public String getIpname() {
		return ipname;
	}

	public void setIpname(String ipname) {
		this.ipname = ipname;
	}

}
