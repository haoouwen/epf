/*
 
 * Package:com.rbt.action
 * FileName: WaterfallAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Waterfall;
import com.rbt.service.ITemplateService;
import com.rbt.service.IWaterfallService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 瀑布布局action类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Dec 28 11:10:08 CST 2014
 */
@Controller
public class WaterfallAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层****************/
	private Waterfall waterfall;
	/*******************业务层接口****************/
	@Autowired
	private IWaterfallService waterfallService;
	@Autowired
	private ITemplateService templateService;
	/*********************集合******************/
	public List waterfallList;//瀑布布局
	public List templateList;//模板
	/*********************字段******************/
	public String wf_title;//瀑布流标题
	public String wf_link_url;//链接
	public String wf_imgpath;//图片
	public String wf_width;//宽
	public String wf_height;//高
	public String is_area_proxy = "0";
	public String cfg_imgsize;//图片大小
	public String cfg_imgtype;//图片类型
	
	/**
	 * 方法描述：返回新增瀑布布局页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取模板
		getTemplate();
		cfg_imgsize=SysconfigFuc.getSysValue("cfg_imgsize");
		cfg_imgtype=SysconfigFuc.getSysValue("cfg_imgtype");
		
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增瀑布布局
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		waterfall.setUser_id(this.session_user_name);
		//布局编码
		if (ValidateUtil.isRequired(waterfall.getWf_code())) {
			this.addFieldError("waterfall.wf_code", "布局编码不能为空!");
		}else{
			Waterfall wf = this.waterfallService.get(waterfall.getWf_code());
			if(wf!=null){
				this.addFieldError("waterfall.wf_code", "布局编码已存在!");
			}
		}
		//模板选择
		if(ValidateUtil.isRequired(waterfall.getTemp_type())){
			this.addFieldError("waterfall.temp_type", "请选择模板！");
		}
		super.commonValidateField(waterfall);
		if(super.ifvalidatepass){
			return add();
		}
		this.waterfallService.insert(waterfall);
		this.addActionMessage("新增瀑布布局成功！");
		return INPUT;
	}

	/**
	 * 方法描述：修改瀑布布局信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//布局编码
		if (ValidateUtil.isRequired(waterfall.getWf_code())) {
			this.addFieldError("waterfall.wf_code", "布局编码不能为空!");
		}
		//模板选择
		if(ValidateUtil.isRequired(waterfall.getTemp_type())){
			this.addFieldError("waterfall.temp_type", "请选择模板！");
		}
		waterfall.setUser_id(this.session_user_name);
		super.commonValidateField(waterfall);
		if(super.ifvalidatepass){
			return view();
		}
		this.waterfallService.update(waterfall);
		this.addActionMessage("修改瀑布布局成功！");
		return list();
	}
	/**
	 * 方法描述：删除瀑布布局信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = chb_id;
		id = id.replace(" ", "");
		String[] ids = id.split(",");
		for(int i=0;i<ids.length;i++){
			this.waterfallService.delete(ids[i]);
		}
		this.addActionMessage("删除瀑布布局成功！");
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
		int count=this.waterfallService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		waterfallList = this.waterfallService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出瀑布布局信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.waterfall.getWf_code();
		if(id==null || id.equals("")){
			waterfall = new Waterfall();
		}else{
			waterfall = this.waterfallService.get(id);
		}
		//获取模板
		getTemplate();
		
		return goUrl(VIEW);
	}
	/**
	 * @author：XBY
	 * @date：Jan 7, 2014 9:50:28 AM
	 * @Method Description：获取模板
	 */
	public void getTemplate()throws Exception{
		Map pageMap=new HashMap();
		templateList=this.templateService.getList(pageMap);
	}
	
	/**
	 * @return the WaterfallList
	 */
	public List getWaterfallList() {
		return waterfallList;
	}
	/**
	 * @param waterfallList
	 *  the WaterfallList to set
	 */
	public void setWaterfallList(List waterfallList) {
		this.waterfallList = waterfallList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(waterfall == null){
			waterfall = new Waterfall();
		}
		String id = this.waterfall.getWf_code();
		if(!this.validateFactory.isDigital(id)){
			waterfall = this.waterfallService.get(id);
		}
	}
	/**
	 * @return the waterfall
	 */
	public Waterfall getWaterfall() {
		return waterfall;
	}
	/**
	 * @param Waterfall
	 *            the waterfall to set
	 */
	public void setWaterfall(Waterfall waterfall) {
		this.waterfall = waterfall;
	}
	
}

