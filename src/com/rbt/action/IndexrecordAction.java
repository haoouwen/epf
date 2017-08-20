/*
 
 * Package:com.rbt.action
 * FileName: IndexrecordAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Indexrecord;
import com.rbt.service.IIndexrecordService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录更新的索引记录action类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jul 18 15:42:50 CST 2014
 */
@Controller
public class IndexrecordAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Indexrecord indexrecord;

	/*******************业务层接口****************/
	@Autowired
	private IIndexrecordService indexrecordService;

	/*********************集合******************/
	public List indexrecordList;// 记录更新的索引记录信息集合

	/**
	 * 方法描述：返回新增记录更新的索引记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录更新的索引记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        //字段验证
	    if(commonCheck())return add();
		this.indexrecordService.insert(indexrecord);
		this.addActionMessage("新增记录更新的索引记录成功！");
		this.indexrecord = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录更新的索引记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
        //字段验证
	    if(commonCheck())return view();
		this.indexrecordService.update(indexrecord);
		this.addActionMessage("修改记录更新的索引记录成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 4:51:11 PM
	 * @Method Description：公共数据验证
	 */
	 public boolean commonCheck(){
		super.commonValidateField(indexrecord);
		if(super.ifvalidatepass){
			return true;
		}
		 return false;
	 }
	/**
	 * 方法描述：删除记录更新的索引记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.indexrecordService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录更新的索引记录成功");
		}else{
			this.addActionMessage("删除记录更新的索引记录失败");
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
		
		//根据页面提交的条件找出信息总数
		int count=this.indexrecordService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		indexrecordList = this.indexrecordService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录更新的索引记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.indexrecord.getTrade_id();
		if(id==null || id.equals("")){
			indexrecord = new Indexrecord();
		}else{
			indexrecord = this.indexrecordService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @return the indexrecord
	 */
	public Indexrecord getIndexrecord() {
		return indexrecord;
	}
	/**
	 * @param Indexrecord
	 *            the indexrecord to set
	 */
	public void setIndexrecord(Indexrecord indexrecord) {
		this.indexrecord = indexrecord;
	}
	/**
	 * @return the IndexrecordList
	 */
	public List getIndexrecordList() {
		return indexrecordList;
	}
	/**
	 * @param indexrecordList
	 *  the IndexrecordList to set
	 */
	public void setIndexrecordList(List indexrecordList) {
		this.indexrecordList = indexrecordList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(indexrecord == null){
			indexrecord = new Indexrecord();
		}
		String id = this.indexrecord.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			indexrecord = this.indexrecordService.get(id);
		}
	}

}

