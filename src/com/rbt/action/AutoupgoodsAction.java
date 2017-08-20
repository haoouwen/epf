/*
 
 * Package:com.rbt.action
 * FileName: AutoupgoodsAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.model.Autoupgoods;
import com.rbt.service.IAutoupgoodsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品上下架管理信息action类
 * @author 创建人 HZX
 * @date 创建日期 Fri Feb 01 10:46:02 CST 2014
 */
@Controller
public class AutoupgoodsAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Autoupgoods autoupgoods;

	/*******************业务层接口****************/
	@Autowired
	private IAutoupgoodsService autoupgoodsService;

	/*********************集合******************/
	public List autoupgoodsList; //记录商品上下架管理信息

	/*********************字段******************/
	private String goods_name_s;//搜索商品名称
	private String cust_name_s;//搜索会员名称
	public String cust_name_str;//搜索会员名称字符串
	public String goods_name_str;//商品名称字符串
	public String rstart_time_s;//搜索下架时间开始
	public String rend_time_s;//搜索下架时间结束
	public String start_time_s;//搜索上架时间开始
	public String end_time_s;//搜索上架时间结束

	/**
	 * 方法描述：返回新增记录商品上下架管理信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品上下架管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        //字段验证
		if(commonCheck())return add();
		this.autoupgoodsService.insert(autoupgoods);
		this.addActionMessage("新增记录商品上下架管理信息成功！");
		this.autoupgoods = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品上下架管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
        //字段验证
		if(commonCheck())return view();
		this.autoupgoodsService.update(autoupgoods);
		this.addActionMessage("修改记录商品上下架管理信息成功！");
		return list();
	}
     /**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:57:04 PM
	 * @Method Description：公告数据验证
	 */
	public boolean commonCheck(){
		super.commonValidateField(autoupgoods);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：删除记录商品上下架管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.autoupgoodsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录商品上下架管理信息成功");
		}else{
			this.addActionMessage("删除记录商品上下架管理信息失败");
		}
		return list();
	}

	/**
	 * @author : LSQ
	 * @date : Mar 27, 2014 1:09:24 PM
	 * @Method Description :删除虚拟商品上架管理列表
	 */
	public String virtualdelete() throws Exception {
		String id = this.autoupgoods.getTrade_id();
		id = id.replace(" ", "");
		this.autoupgoodsService.delete(id);
		this.addActionMessage("删除虚拟商品上下架管理成功！");
		return virtuallist();
	}


	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)||this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
			if (goods_name_s != null && !goods_name_s.equals("")) {
				pageMap.put("goods_name", goods_name_s);
			}
			if (start_time_s != null && !start_time_s.equals("")) {
				pageMap.put("start_time", start_time_s);
			}
			if (end_time_s != null && !end_time_s.equals("")) {
				pageMap.put("end_time", end_time_s);
			}
			if (rstart_time_s != null && !rstart_time_s.equals("")) {
				pageMap.put("rstart_time", rstart_time_s);
			}
			if (rend_time_s != null && !rend_time_s.equals("")) {
				pageMap.put("rend_time", rend_time_s);
			}
		}
		if (goods_name_s != null && !goods_name_s.equals("")) {
			pageMap.put("goods_name", goods_name_s);
		}

		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		pageMap.put("up_cust_id", this.session_cust_id);
		//1:表示真实的商品
		pageMap.put("up_is_virtual", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.autoupgoodsService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		autoupgoodsList = this.autoupgoodsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * @author : LSQ
	 * @date : Mar 27, 2014 1:16:24 PM
	 * @Method Description :虚拟商品上架管理列表
	 */
	@SuppressWarnings("unchecked")
	public String virtuallist() throws Exception {
		Map pageMap = new HashMap();
		if (goods_name_s != null && !goods_name_s.equals("")) {
			pageMap.put("goods_name", goods_name_s);
		}

		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		pageMap.put("up_cust_id", this.session_cust_id);
		//0:表示虚拟的商品
		pageMap.put("up_is_virtual", "0");
		// 根据页面提交的条件找出信息总数
		int count = this.autoupgoodsService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		autoupgoodsList = this.autoupgoodsService.getList(pageMap);
		return goUrl("virtualindex");
	}

	/**
	 * 方法描述：根据主键找出记录商品上下架管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.autoupgoods.getTrade_id();
		if (id == null || id.equals("")) {
			autoupgoods = new Autoupgoods();
		} else {
			autoupgoods = this.autoupgoodsService.get(id);
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the AutoupgoodsList
	 */
	public List getAutoupgoodsList() {
		return autoupgoodsList;
	}

	/**
	 * @param autoupgoodsList
	 *            the AutoupgoodsList to set
	 */
	public void setAutoupgoodsList(List autoupgoodsList) {
		this.autoupgoodsList = autoupgoodsList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (autoupgoods == null) {
			autoupgoods = new Autoupgoods();
		}
		String id = this.autoupgoods.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			autoupgoods = this.autoupgoodsService.get(id);
		}
	}

	public String getGoods_name_s() {
		return goods_name_s;
	}

	public void setGoods_name_s(String goods_name_s) {
		this.goods_name_s = goods_name_s;
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	/**
	 * @return the autoupgoods
	 */
	public Autoupgoods getAutoupgoods() {
		return autoupgoods;
	}

	/**
	 * @param Autoupgoods
	 *            the autoupgoods to set
	 */
	public void setAutoupgoods(Autoupgoods autoupgoods) {
		this.autoupgoods = autoupgoods;
	}

}
