/*
 
 * Package:com.rbt.action
 * FileName: Index_historyAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Index_history;
import com.rbt.service.IIndex_historyService;
/**
 * @function 功能 记录已经索引过的信息action类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Aug 12 10:12:10 CST 2014
 */
@Controller
public class Index_historyAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Index_history index_history;

	/*******************业务层接口****************/
	@Autowired
	public IIndex_historyService index_historyService;

	/*********************集合******************/
	public List index_historyList;// 记录已经索引过的信息信息集合

	
	/**
	 * 方法描述：返回新增记录已经索引过的信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录已经索引过的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.index_historyService.insert(index_history);
		this.addActionMessage("新增索引过的信息成功");
		this.index_history = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录已经索引过的信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id=index_history.getInfo_id();
		if(id == null || "".equals(id)){
			list();
		}
		this.index_historyService.update(index_history);
		this.addActionMessage("修改索引过的信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录已经索引过的信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.index_history.getInfo_id();
		id = id.replace(" ", "");
		this.addActionMessage("删除索引过的信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		index_historyList = this.index_historyService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	
	/**
	 * 方法描述：根据主键找出记录已经索引过的信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	/**
	 * @return the Index_historyList
	 */
	public List getIndex_historyList() {
		return index_historyList;
	}
	/**
	 * @param index_historyList
	 *  the Index_historyList to set
	 */
	public void setIndex_historyList(List index_historyList) {
		this.index_historyList = index_historyList;
	}
	
	/**
	 * @return the index_history
	 */
	public Index_history getIndex_history() {
		return index_history;
	}
	/**
	 * @param Index_history
	 *            the index_history to set
	 */
	public void setIndex_history(Index_history index_history) {
		this.index_history = index_history;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(index_history == null){
			index_history = new Index_history();
		}
		String id = index_history.getInfo_id();
		if(!ValidateUtil.isDigital(id)){
			index_history = this.index_historyService.get(id);
		}
		System.out.println(index_history);
	}

}

