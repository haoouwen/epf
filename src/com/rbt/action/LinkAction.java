/*
 
 * Package:com.rbt.action
 * FileName: LinkAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Link;
import com.rbt.service.ILinkService;
import com.rbt.service.ILink_groupService;

/**
 * @function 功能  友情链接action类
 * @author  创建人 CYC
 * @date  创建日期  Jun 28, 2014
 */
@Controller
public class LinkAction extends AdminBaseAction implements Preparable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5366624086700003990L;
	
	/*******************实体层****************/
	public Link link;

	/*******************业务层接口****************/
	@Autowired
	public ILinkService linkService;
	@Autowired
	public ILink_groupService link_groupService;

	/*********************集合******************/
	public List linkList;//列表页友情链接集合
	public List link_groupList;//列表页友情链接分组集合

	/*********************字段******************/
	public String link_name_s;//友情链接名称
	public String link_name_ss;//友情链接名称
	public String name_s;//
	public String url_s;//链接地址
	public String link_group;//地区
	public String is_display_s;//是否显示
	public String area_attr_s;//


	/**
	 * 方法描述：默认执行方法
	 * @return
	 * @throws Exception
	 */
	public String excute() throws Exception {
		//获取友情链接分组
		getlinkgroup();
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.link.getLink_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		//获取友情链接分组
		getlinkgroup();
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：修改友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 找出地区字段的存入隐藏值中
		if(area_attr!=null&&!"".equals(area_attr)){
		    area_attr=area_attr.replace(" ", "");
			link.setArea_attr(area_attr);
		}
		//字段验证
		super.commonValidateField(link);
		if (super.ifvalidatepass) {
			return view();
		}
		this.linkService.update(link);
		this.addActionMessage("修改友情链接成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if (link_name_s != null && !link_name_s.equals(""))
			pageMap.put("link_name", link_name_s);
		if (link_name_ss != null && !link_name_ss.equals(""))
			pageMap.put("link_name", link_name_ss);
		if (link_group != null && !link_group.equals(""))
			pageMap.put("link_group", link_group);
		if (name_s != null && !name_s.equals(""))
			pageMap.put("name", name_s);
		if (url_s != null && !url_s.equals(""))
			pageMap.put("url", url_s);
		if (is_display_s != null && !is_display_s.equals(""))
			pageMap.put("is_display", is_display_s);
		if (area_attr_s != null && !area_attr_s.equals("")){
			pageMap.put("area_attr", area_attr_s);
			//回选地区
			viewArea(area_attr_s);
		}
			
		//商城地区过滤
		//过滤地区
		pageMap = super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.linkService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		//找出信息列表，放入list
		linkList = this.linkService.getList(pageMap);
		linkList = ToolsFuc.replaceList(linkList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：删除友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.linkService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除友情链接成功");
		}else{
			this.addActionMessage("删除友情链接失败");
		}

		return list();
	}

	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取友情链接分组
		getlinkgroup();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		if ("0".equals(link.getLink_group())) {
			this.addFieldError("link.link_group", "请选择友情链接分组");
		}
		if(area_attr!=null&&!"".equals(area_attr)){
		    area_attr=area_attr.replace(" ", "");
			link.setArea_attr(area_attr);
		}
		//字段验证
		super.commonValidateField(link);
		if (super.ifvalidatepass) {
			return add();
		}
		this.linkService.insert(link);
		this.addActionMessage("新增友情链接成功");
		this.link = null;
		return add();
	}


	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:40:44 PM
	 * @Method Description :显示
	 */
	public String updateIsshow() throws Exception {
		updateShow();
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:40:51 PM
	 * @Method Description :隐藏
	 */
	public String updateUnshow() throws Exception {
		updateShow();
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:41:01 PM
	 * @Method Description :显示、隐藏公共调用方法
	 */
	public void updateShow() {
		boolean flag = this.linkService.updateBatchState(chb_id, state_val, "link_id", "is_display");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("显示友情链接成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("隐藏友情链接成功");
			}
		}else{
			this.addActionMessage("操作友情链接失败");
		}
	}


	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : LJQ
	 * @date : Nov 8, 2014 2:36:50 PM
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (link == null) {
			link = new Link();
		}
		String id = link.getLink_id();
		if (!ValidateUtil.isDigital(id)) {
			link = this.linkService.get(id);
		}
	}
	
	public void getlinkgroup() {
		Map map = new HashMap();
		link_groupList = link_groupService.getList(map);
	}

	/**
	 * 方法描述：get/set方法
	 * @return
	 * @throws Exception
	 */
	public String getLink_name_s() {
		return link_name_s;
	}

	public void setLink_name_s(String link_name_s) {
		this.link_name_s = link_name_s;
	}

	public String getName_s() {
		return name_s;
	}

	public void setName_s(String name_s) {
		this.name_s = name_s;
	}

	public String getLink_name_ss() {
		return link_name_ss;
	}

	public void setLink_name_ss(String link_name_ss) {
		this.link_name_ss = link_name_ss;
	}

	public String getIs_display_s() {
		return is_display_s;
	}

	public void setIs_display_s(String is_display_s) {
		this.is_display_s = is_display_s;
	}

	public String getLink_group() {
		return link_group;
	}

	public String getUrl_s() {
		return url_s;
	}

	public void setUrl_s(String url_s) {
		this.url_s = url_s;
	}

	public ILinkService getLinkService() {
		return linkService;
	}

	public List getLinkList() {
		return linkList;
	}

	public void setLinkList(List linkList) {
		this.linkList = linkList;
	}

	public ILink_groupService getLink_groupService() {
		return link_groupService;
	}

	public void setLink_group(String link_group) {
		this.link_group = link_group;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public List getLink_groupList() {
		return link_groupList;
	}

	public void setLink_groupList(List link_groupList) {
		this.link_groupList = link_groupList;
	}

	public String getArea_attr_s() {
		return area_attr_s;
	}

	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}

	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

}
