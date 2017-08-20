/*
 
 * Package:com.rbt.action
 * FileName: PushlogisticsAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Pushlogistics;
import com.rbt.service.IPushlogisticsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 物流推送action类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jun 18 09:31:12 CST 2014
 */
@Controller
public class PushlogisticsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	
	private Pushlogistics pushlogistics;
	/*******************业务层接口****************/

	@Autowired
	private IPushlogisticsService pushlogisticsService;
	/*********************集合********************/
	public List pushlogisticsList;//物流推送信息
	
	/*********************字段********************/

	
	/**
	 * 方法描述：返回新增物流推送页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增物流推送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(pushlogistics);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.pushlogisticsService.insert(pushlogistics);
		this.addActionMessage("新增物流推送成功！");
		this.pushlogistics = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改物流推送信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(pushlogistics);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.pushlogisticsService.update(pushlogistics);
		this.addActionMessage("修改物流推送成功！");
		return list();
	}
	/**
	 * 方法描述：删除物流推送信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.pushlogistics.getTrade_id();
		id = id.replace(" ", "");
		this.pushlogisticsService.delete(id);
		this.addActionMessage("删除物流推送成功！");
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
		
		//根据页面提交的条件找出信息总数
		int count=this.pushlogisticsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		pushlogisticsList = this.pushlogisticsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出物流推送信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.pushlogistics.getTrade_id();
		if(id==null || id.equals("")){
			pushlogistics = new Pushlogistics();
		}else{
			pushlogistics = this.pushlogisticsService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the PushlogisticsList
	 */
	public List getPushlogisticsList() {
		return pushlogisticsList;
	}
	/**
	 * @param pushlogisticsList
	 *  the PushlogisticsList to set
	 */
	public void setPushlogisticsList(List pushlogisticsList) {
		this.pushlogisticsList = pushlogisticsList;
	}
	/**
	 * @return the pushlogistics
	 */
	public Pushlogistics getPushlogistics() {
		return pushlogistics;
	}
	/**
	 * @param Pushlogistics
	 *            the pushlogistics to set
	 */
	public void setPushlogistics(Pushlogistics pushlogistics) {
		this.pushlogistics = pushlogistics;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(pushlogistics == null){
			pushlogistics = new Pushlogistics();
		}
		String id = this.pushlogistics.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			pushlogistics = this.pushlogisticsService.get(id);
		}
	}

}

