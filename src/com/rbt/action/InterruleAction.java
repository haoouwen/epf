/*
 
 * Package:com.rbt.action
 * FileName: InterruleAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Interrule;
import com.rbt.service.IInterruleService;

/**
 * @function 功能 积分规则表action类
 * @author 创建人 LJQ
 * @date 创建日期 Thu Nov 10 14:26:30 CST 2014
 */
@Controller
public class InterruleAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Interrule interrule;

	/*******************业务层接口****************/
	@Autowired
	public IInterruleService interruleService;

	/*********************集合******************/
	public List interruleList;//积分规则表信息集合

	/*********************字段******************/
	public String scoreid;//积分ID
	public String scoreNum;//积分

	
	
	/**
	 * 方法描述：返回新增积分规则表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增积分规则表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		return null;
	}

	/**
	 * 方法描述：修改积分规则表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateScore() throws Exception {
		if(ValidateUtil.isRequired(this.scoreid)){
			return list();
		}
		List ruleList=this.interruleService.updateScore(this.scoreid, this.scoreNum);
		this.interruleService.updateInterruleList(ruleList);
		this.addActionMessage("修改积分规则信息成功");
		return list();
	}
	/**
	 * 方法描述：删除积分规则表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();	
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.interruleService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		interruleList = this.interruleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出积分规则表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.interrule.getRule_id();
		if(id==null || id.equals("")){
			interrule = new Interrule();
		}else{
			interrule = this.interruleService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the InterruleList
	 */
	public List getInterruleList() {
		return interruleList;
	}
	/**
	 * @param interruleList
	 *  the InterruleList to set
	 */
	public void setInterruleList(List interruleList) {
		this.interruleList = interruleList;
	}
	
	/**
	 * @return the interrule
	 */
	public Interrule getInterrule() {
		return interrule;
	}
	/**
	 * @param Interrule
	 *            the interrule to set
	 */
	public void setInterrule(Interrule interrule) {
		this.interrule = interrule;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(interrule == null){
			interrule = new Interrule();
		}
		String id = this.interrule.getRule_id();
		if(!ValidateUtil.isDigital(id)){
			interrule = this.interruleService.get(id);
		}
	}

}

