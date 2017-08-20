/*
 
 * Package:com.rbt.action
 * FileName: AboutusAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Aboutus;
import com.rbt.service.IAboutusService;
import com.rbt.service.ICommparaService;

/**
 * @function 功能 关于我们action类
 * @author 创建人 CYC
 * @date 创建日期 Mon Jul 11 12:15:32 CST 2014
 */
@Controller
public class AboutusAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Aboutus aboutus; 
   
	/*******************业务层接口****************/
	@Autowired
	private IAboutusService aboutusService;
	@Autowired
	private ICommparaService commparaService;
	
	/*********************集合******************/
	public List aboutusList;//关于我们
	public List commparaList;//参数
	public List commparaList_value;//参数值
	
	/*********************字段******************/
	public String title_s;//搜索标题
	public String ch_id_s;//搜索栏目ID
	public String starttime_s;//搜索开始的发布时间
	public String endtime_s;//搜索结束的发布时间
	
	/**
	 * 方法描述：返回新增关于我们页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取下拉列表
		getcommparaList("");
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增关于我们
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//验证字段
		if(commonCheck())return add();
		this.aboutusService.insert(aboutus);
        //获取下拉列表
		this.addActionMessage("新增关于我们成功");
		this.aboutus = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改关于我们信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = aboutus.getInfo_id();
		//判断实体ID是否存在
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
		//字段验证
		if(commonCheck())return view();
		this.aboutusService.update(aboutus);
		//获取下拉列表
		this.addActionMessage("修改关于我们信息成功");
		return list();
	}
	
    
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 10:44:23 AM
	 * @Method Description：验证核心方法
	 */
	public boolean commonCheck()throws Exception{
		if ("0".equals(this.aboutus.getCh_id())) {
			this.addFieldError("aboutus.ch_id", "请选择关于我们类型");
		}
		aboutus.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(aboutus);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述：删除关于我们信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.aboutusService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除关于我们信息成功");
		}else{
			this.addActionMessage("删除关于我们信息失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//获取下拉列表
		getcommparaList(mall_type);
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if (title_s != null && !"".equals(title_s)){
			pageMap.put("title", title_s);
		}
		if(ch_id_s!=null && !"".equals(ch_id_s)){
			pageMap.put("ch_id", ch_id_s);
		}
		if (starttime_s != null && !starttime_s.equals("")){
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")){
			pageMap.put("endtime", endtime_s);
		}
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.aboutusService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		aboutusList = this.aboutusService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据主键找出关于我们信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		aboutus=this.aboutusService.get(this.aboutus.getInfo_id());
		//获取下拉列表
		getcommparaList("");
		return goUrl(VIEW);
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 7:31:53 PM
	 * @Method Description :获取参数表字段para_code为ch_id的列表
	 */
	public void getcommparaList(String strmall_type){
		commparaList = commparaService.getCommparaList("ch_id");
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (aboutus == null) {
			aboutus = new Aboutus();
		}
		String id = this.aboutus.getInfo_id();
		if (!ValidateUtil.isDigital(id)) {
			aboutus = this.aboutusService.get(id);
		}
	}
}
