/*
 
 * Package:com.rbt.action
 * FileName: NavAction.java 
 */
package com.rbt.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Nav;
import com.rbt.service.ICommparaService;
import com.rbt.service.INavService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能  导航链接action类
 * @author  创建人 CYC
 * @date  创建日期  July 5, 2014
 */
@Controller
public class NavAction extends AdminBaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -852060267211940416L;
	/*******************实体层********************/
	public Nav nav;
	
	/*******************业务层接口****************/
	@Autowired
	public INavService navService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	
	/*********************集合********************/
	 public List navList;//列表页导航链接
	 public List commparalist;//参数列表
	 public List nav_List;//图标参数
	
	/*********************字段********************/
	public String nav_name_s;//导航名称
	public String link_url_s;//链接地址
    public String isshow_s;//是否显示
    public String nav_post_s;//导航位置
    public String admin_nav_id;
    public String isort_no;//排序
    
	/**
	 * 方法描述：根据主键找出导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//获取参数列表
		nav_List = commparaService.getCommparaList("nav_ico");
		getCommpara();
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String navid=nav.getNav_id();
		if(ValidateUtil.isDigital(navid))
		{
			return list();
		}
		if(nav.getNav_post().equals("0")){
			this.addFieldError("nav.nav_post", "请选择导航放置位置");
		}
		if(nav.getIsopen().equals("0")){
			this.addFieldError("nav.isopen", "请选择导航链接类型");
		}
		
		//字段验证
		super.commonValidateField(nav);
		if(super.ifvalidatepass){
			return view();
		}
		this.navService.update(nav);
		this.addActionMessage("修改导航信息成功");
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
		if(nav_name_s!=null && !nav_name_s.equals("")) pageMap.put("nav_name", nav_name_s);
		if(isshow_s!=null && !isshow_s.equals("")) pageMap.put("isshow", isshow_s);
		if(nav_post_s!=null && !nav_post_s.equals("")) pageMap.put("nav_post", nav_post_s);
		if(link_url_s!=null && !link_url_s.equals("")) pageMap.put("link_url", link_url_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.navService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		navList=this.navService.getList(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.navService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除导航链接成功");
		}else{
			this.addActionMessage("删除导航链接成功失败");
		}

		return list();
	}
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取参数列表
		getCommpara();
		Map navmap = new HashMap();
		navmap.put("para_code", "nav_ico");
		nav_List = commparaService.getList(navmap);
		
		return goUrl(ADD);
	}
	
	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {		
		if(this.nav.getNav_post().equals("0")){
			this.addFieldError("nav.nav_pos", "请选择位置");
		}
		
		if(nav.getIsopen().equals("0")){
			this.addFieldError("nav.isopen", "请选择导航链接类型");
		}
		//字段验证
		super.commonValidateField(nav);
		if(super.ifvalidatepass){
			return add();
		}
		
		// 生成十位随机数的字符串
		String charNum = RandomStrUtil.getNumberRand();
		nav.setNav_code(charNum);
		//插入数据库
		this.navService.insert(nav);
		this.addActionMessage("新增导航链接成功");
		this.nav = null;
		return add();
	}
	
	//获取参数列表
	public void getCommpara(){
    	Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	commparalist = this.sysmoduleService.getList(pageMap);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:48:33 PM
	 * @Method Description :显示
	 */
	public String updateIsshow() throws Exception {
		updateshow();
		return list();
	}

	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:48:43 PM
	 * @Method Description :隐藏
	 */
	public String updateUnshow() throws Exception {
		updateshow();
		return list();
	}
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 4:48:54 PM
	 * @Method Description :显示不显示公共方法
	 */
	public void updateshow(){
		boolean flag = this.navService.updateBatchState(chb_id, state_val, "nav_id", "isshow");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("显示导航成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("隐藏导航成功");
			}
		}else{
			this.addActionMessage("操作导航失败");
		}
	}
	
	/**
	 * 方法描述：批量修改导航
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.navService.updateSort("nav_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("导航排序成功");
		}else{
			this.addActionMessage("导航排序失败");
		}
		this.addActionMessage("批量排序修改成功");
		return list();
	}

	public Nav getNav() {
		return nav;
	}

	public void setNav(Nav nav) {
		this.nav = nav;
	}
	
	public List getNavList() {
		return navList;
	}

	public void setNavList(List navList) {
		this.navList = navList;
	}
	
	public String getNav_name_s() {
		return nav_name_s;
	}

	public void setNav_name_s(String nav_name_s) {
		this.nav_name_s = nav_name_s;
	}
	
	public String getIsshow_s() {
		return isshow_s;
	}

	public void setIsshow_s(String isshow_s) {
		this.isshow_s = isshow_s;
	}

	public String getNav_post_s() {
		return nav_post_s;
	}

	public void setNav_post_s(String nav_post_s) {
		this.nav_post_s = nav_post_s;
	}

	public String getLink_url_s() {
		return link_url_s;
	}

	public void setLink_url_s(String link_url_s) {
		this.link_url_s = link_url_s;
	}

	public String getAdmin_nav_id() {
		return admin_nav_id;
	}

	public void setAdmin_nav_id(String admin_nav_id) {
		this.admin_nav_id = admin_nav_id;
	}
	
	public String getIsort_no() {
		return isort_no;
	}

	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}

	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
	if(nav == null){
		nav = new Nav();
	}
	String id = nav.getNav_id();
	if(!ValidateUtil.isDigital(id)){
		nav = this.navService.get(id);
	}
}
}
