/*
 
 * Package:com.rbt.action
 * FileName: AdvposAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 广告位Action层实现
 * @author 创建人 QJY  
 * @date 创建日期 Jul 7, 2014 5:50:38 PM
 */         
@Controller
public class AdvposAction extends  AdminBaseAction implements Preparable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3289326239880366863L;
	
	/*******************实体层****************/
	public Advpos advpos;
	
	/*******************业务层接口****************/
	@Autowired
	private IAdvposService advposService;
	@Autowired
	private IAdvinfoService advinfoService;
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	
	/*********************集合******************/
	public List commpara_modelList;//参数信息
	public List advposList;//广告位信息
	private List posnameList;//广告位名称匹配表
	public List commparaList;//参数
	public List templateTypeList;//
	public List adv_posList ;//广告所属模块
	
	/*********************字段******************/
	public String pos_name_s;//搜索广告位名称
	public String pos_type_s;//搜索广告位类型
	public String pos_size_s;//搜索规格
	public String p_width;//搜索宽度
	public String p_height;	//搜索高度
	public String pos_price_s;//搜索广告价格
	public String isshow_s;//搜索是否显示
	public String pos_id_s;//搜索广告位ID
	public String module_type_s;//搜索所属模块
	public String cat_attr_s;//搜索分类
	public String area_attr_s;//搜索地区
	public String adv_pos_s;//广告所属模块
	private String PARA_CODE="adv_pos";//所属模块参数
	public String batch_update_hidden_posid;//广告位批量更新隐藏字段 batch_update_hidden_posid
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		setcom_or_mod();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (pos_name_s != null && !pos_name_s.equals("")) {
			pageMap.put("pos_name", pos_name_s);
		}
		if (pos_type_s != null && !pos_type_s.equals("")) {
			pageMap.put("pos_type", pos_type_s);
		}
		if (p_width != null && !p_width.equals("")) {
			pageMap.put("p_width", p_width);
		}
		if (p_height != null && !p_height.equals("")) {
			pageMap.put("p_height", p_height);
		}
		if (pos_price_s != null && !pos_price_s.equals("")) {
			pageMap.put("pos_price", pos_price_s);
		}
		if (isshow_s != null && !isshow_s.equals("")) {
			pageMap.put("isshow", isshow_s);
		}
		if (module_type_s != null && !module_type_s.equals("")) {
			pageMap.put("module_type", module_type_s);
		}
		//查找广告所属模块
		if (adv_pos_s != null && !adv_pos_s.equals("")) {
			pageMap.put("adv_pos", adv_pos_s);
		}
		pageMap.put("para_code", "adv_type");
		// 获取搜索的所属地区
		if (area_attr_s != null && !"".equals(area_attr_s)) {
			pageMap.put("area_attr", area_attr_s);
			//回选地区
			viewArea(area_attr_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		// 根据页面提交的条件找出信息总数
		int count = this.advposService.getCount(pageMap);

		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		advposList = this.advposService.getList(pageMap);
		advposList = ToolsFuc.replaceList(advposList,"module_type");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：跳转到广告位新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		setcom_or_mod();
		//查找广告所属模块下拉菜单
		findAdv_pos();
		return goUrl(ADD);
	}
	/**
	 * @Method Description :公共验证数据
	 * @author : HZX
	 * @date : May 14, 2014 10:52:39 AM
	 */
	public  boolean commonCheck(){
		// 用于所属地区的回选开始
		selectArea();
		if (this.advpos.getPos_type().equals("0")) {
			this.addFieldError("advpos.pos_type", "请选择广告类型");
		}
		if(this.advpos.getPos_type().equals("g")||this.advpos.getPos_type().equals("f")){
			if(this.advpos.getModule_type().equals("0")){
				this.addFieldError("advpos.module_type", "请选择模块");
			}
		}
		if (this.advpos.getAdv_pos().equals("0")) {
			this.addFieldError("advpos.adv_pos", "广告所属模块不能为空");
		}
		super.commonValidateField(advpos);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	/**
	 * 方法描述：新增广告位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//广告所属模块adv_posList
		Map pagemap =new HashMap();
		pagemap.put("para_code", PARA_CODE);
		adv_posList=this.commparaService.getList(pagemap);
		//字段验证
		if(commonCheck())return add();
		this.advposService.insert(advpos);
		this.addActionMessage("新增广告位成功");
		this.advpos = null;
		return add();
	}

	/**
	 * 方法描述：删除广告位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
	    String pos_id=advpos.getPos_id();
	    if(!ValidateUtil.isRequired(pos_id)){
		boolean flag = this.advposService.delete(pos_id);
		if(flag){
			this.addActionMessage("删除广告成功");
		}else{
			this.addActionMessage("删除广告失败");
		}
	    }
		return list();
	}

	/**
	 * 方法描述：根据主键找出广告位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		setcom_or_mod();
		//查找广告所属模块下拉菜单
		findAdv_pos();
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：参数和所属模块集合信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setcom_or_mod(){
		Map paracodemap = new HashMap();
		paracodemap.put("para_code", "adv_type");
		commparaList = this.commparaService.getList(paracodemap);
		Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	commpara_modelList = this.sysmoduleService.getList(pageMap);
	}
	
	/**
	 * 方法描述：查看JS调用代码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewJS() throws Exception{
		return INPUT;
	}
	
	/**
	 * 方法描述：修改广告位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = advpos.getPos_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if(ValidateUtil.isDigital(id)){
			return list();
		}
		//字段验证
		if(commonCheck()){
			return view();
		}
		this.advposService.update(advpos);
		this.addActionMessage("修改广告位成功");
		return list();
	}

	/**
	 * 方法描述：批量修改系统变量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAdvposBatch() throws Exception {
		String posid = this.batch_update_hidden_posid;
		String sortno = this.advpos.getSort_no();
		if(posid!=null&&sortno!=null){
		posid = posid.replace(" ", "");
		sortno = sortno.replace(" ", "");
		String posidStr[] = posid.split(",");
		String sortnoStr[] = sortno.split(",");
		List advposList = new ArrayList();
		if (posidStr.length > 0) {
			for (int i = 0; i < posidStr.length; i++) {
				HashMap configMap = new HashMap();
				configMap.put("pos_id", posidStr[i]);
				configMap.put("sort_no", sortnoStr[i]);
				advposList.add(configMap);
			}
		}
		this.advposService.updateAdvposBatch(advposList);
		this.addActionMessage("广告位批量排序成功");
		}
		return list();
	}
	
	/**
	 * CXY
	 * 方法描述：	查找广告所属模块adv_posList
	 * @return
	 * @throws Exception
	 */

	public void  findAdv_pos()   throws Exception {		
		Map pagemap =new HashMap();
		pagemap.put("para_code", PARA_CODE);
		adv_posList=this.commparaService.getList(pagemap);
	}	
	/**
	 * CXY
	 * 方法描述：跳转到广告位所属模块
	 * @return
	 * @throws Exception
	 */
	public String viewAdvpos() throws Exception {
		Map pagemap =new HashMap();
		pagemap.put("para_code", PARA_CODE);
		adv_posList=this.commparaService.getList(pagemap);
		return goUrl("viewAdvpos");
	}
	
	

	/**
	 * @param advinfoService
	 *            the advinfoService to set
	 */
	public void setAdvinfoService(IAdvinfoService advinfoService) {
		this.advinfoService = advinfoService;
	}
	/**
	 * @return the pos_name_s
	 */
	public String getPos_name_s() {
		return pos_name_s;
	}

	/**
	 * @param pos_name_s
	 *            the pos_name_s to set
	 */
	public void setPos_name_s(String pos_name_s) {
		this.pos_name_s = pos_name_s;
	}

	/**
	 * @return the pos_type_s
	 */
	public String getPos_type_s() {
		return pos_type_s;
	}

	/**
	 * @param pos_type_s
	 *            the pos_type_s to set
	 */
	public void setPos_type_s(String pos_type_s) {
		this.pos_type_s = pos_type_s;
	}

	/**
	 * @return the pos_size_s
	 */
	public String getPos_size_s() {
		return pos_size_s;
	}

	/**
	 * @param pos_size_s
	 *            the pos_size_s to set
	 */
	public void setPos_size_s(String pos_size_s) {
		this.pos_size_s = pos_size_s;
	}

	/**
	 * @return the advposList
	 */
	public List getAdvposList() {
		return advposList;
	}

	/**
	 * @param advposList
	 *            the advposList to set
	 */
	public void setAdvposList(List advposList) {
		this.advposList = advposList;
	}

	/**
	 * @return the commparaList
	 */
	public List getCommparaList() {
		return commparaList;
	}

	/**
	 * @param commparaList
	 *            the commparaList to set
	 */
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	/**
	 * @return the advpos
	 */
	public Advpos getAdvpos() {
		return advpos;
	}

	/**
	 * @param advpos
	 *            the advpos to set
	 */
	public void setAdvpos(Advpos advpos) {
		this.advpos = advpos;
	}

	/**
	 * @return the isshow_s
	 */
	public String getIsshow_s() {
		return isshow_s;
	}

	/**
	 * @param isshow_s
	 *            the isshow_s to set
	 */
	public void setIsshow_s(String isshow_s) {
		this.isshow_s = isshow_s;
	}

	/**
	 * @return the pos_price_s
	 */
	public String getPos_price_s() {
		return pos_price_s;
	}

	/**
	 * @param pos_price_s the pos_price_s to set
	 */
	public void setPos_price_s(String pos_price_s) {
		this.pos_price_s = pos_price_s;
	}

	/**
	 * @return the posnameList
	 */
	public List getPosnameList() {
		return posnameList;
	}

	/**
	 * @param posnameList
	 *            the posnameList to set
	 */
	public void setPosnameList(List posnameList) {
		this.posnameList = posnameList;
	}

	public List getTemplateTypeList() {
		return templateTypeList;
	}

	public void setTemplateTypeList(List templateTypeList) {
		this.templateTypeList = templateTypeList;
	}
    
	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	public String getModule_type_s() {
		return module_type_s;
	}

	public void setModule_type_s(String module_type_s) {
		this.module_type_s = module_type_s;
	}

	public String getPos_id_s() {
		return pos_id_s;
	}

	public void setPos_id_s(String pos_id_s) {
		this.pos_id_s = pos_id_s;
	}

	public String getBatch_update_hidden_posid() {
		return batch_update_hidden_posid;
	}

	public void setBatch_update_hidden_posid(String batch_update_hidden_posid) {
		this.batch_update_hidden_posid = batch_update_hidden_posid;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getArea_attr_s() {
		return area_attr_s;
	}

	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}
	
	public String getAdv_pos_s() {
		return adv_pos_s;
	}

	public void setAdv_pos_s(String adv_pos_s) {
		this.adv_pos_s = adv_pos_s;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { 
		super.saveRequestParameter();
		if(advpos == null){
			advpos = new Advpos();
		}
		String id = this.advpos.getPos_id();
		if(!ValidateUtil.isDigital(id)){
			advpos = this.advposService.get(id);
		}
	}
	
}
