/*
 
 * Package:com.rbt.action
 * FileName: InternationaltariffAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Internationaltariff;
import com.rbt.service.IInternationaltariffService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 国际物流action类
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 20 18:55:20 CST 2015
 */
@Controller
public class InternationaltariffAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 国际物流对象
	 */
	private Internationaltariff internationaltariff;
	/*******************业务层接口****************/
	/*
	 * 国际物流业务层接口
	 */
	@Autowired
	private IInternationaltariffService internationaltariffService;
	
	/*********************集合*******************/
	/*
	 * 国际物流信息集合
	 */
	public List internationaltariffList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String title_s;//国际物流名称
		
	/**
	 * 方法描述：返回新增国际物流页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增国际物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if(conn()){
			return add();
		}
		this.internationaltariffService.insert(internationaltariff);
		this.addActionMessage("新增国际物流成功！");
		this.internationaltariff = null;
		return INPUT;
	}

	public boolean conn(){
		//验证国际物流是否为空
		if(ValidateUtil.isRequired(internationaltariff.getIef_name())){
			this.addFieldError("internationaltariff.ief_name", "国际物流不能为空!");
		}
		//验证国际运单单价是否为空
	    if(ValidateUtil.isRequired(internationaltariff.getIef_price())){
	    	this.addFieldError("internationaltariff.ief_price", "国际运单单价不能为空！");
	    }
		super.commonValidateField(internationaltariff);
		if(super.ifvalidatepass){
			return true;
		}
	    return false;
	}
	
	
	/**
	 * 方法描述：修改国际物流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(internationaltariff);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.internationaltariffService.update(internationaltariff);
		this.addActionMessage("修改国际物流成功！");
		return list();
	}
	/**
	 * 方法描述：删除国际物流信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除国际物流信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.internationaltariffService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除国际物流信息成功!");
		}else{
			this.addActionMessage("删除国际物流信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//搜索国际物流名称
		if(title_s!=null && !"".equals(title_s)){
			pageMap.put("ief_name", title_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.internationaltariffService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		internationaltariffList = this.internationaltariffService.getList(pageMap);
		//转字符串
		internationaltariffList =ToolsFuc.replaceList(internationaltariffList, "");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出国际物流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.internationaltariff.getIef_id();
		if(id==null || id.equals("")){
			internationaltariff = new Internationaltariff();
		}else{
			internationaltariff = this.internationaltariffService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the InternationaltariffList
	 */
	public List getInternationaltariffList() {
		return internationaltariffList;
	}
	/**
	 * @param internationaltariffList
	 *  the InternationaltariffList to set
	 */
	public void setInternationaltariffList(List internationaltariffList) {
		this.internationaltariffList = internationaltariffList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(internationaltariff == null){
			internationaltariff = new Internationaltariff();
		}
		String id = this.internationaltariff.getIef_id();
		if(!this.validateFactory.isDigital(id)){
			internationaltariff = this.internationaltariffService.get(id);
		}
	}
	/**
	 * @return the internationaltariff
	 */
	public Internationaltariff getInternationaltariff() {
		return internationaltariff;
	}
	/**
	 * @param Internationaltariff
	 *            the internationaltariff to set
	 */
	public void setInternationaltariff(Internationaltariff internationaltariff) {
		this.internationaltariff = internationaltariff;
	}
}

