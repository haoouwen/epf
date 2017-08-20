/*
 
 * Package:com.rbt.action
 * FileName: PagetipAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Pagetip;
import com.rbt.service.IPagetipService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录页面显示管理信息action类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 29 13:10:46 CST 2014
 */
@Controller
public class PagetipAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Pagetip pagetip;
	/*******************业务层接口****************/
	@Autowired
	private IPagetipService pagetipService;
	
	/*********************集合********************/
	
	public List pagetipList;
	/*********************字段********************/

    private String page_code; //页面编码
    private String page_content;//页面内容
	
	
	/**
	 * 方法描述：返回新增记录页面显示管理信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录页面显示管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(pagetip);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.pagetipService.insert(pagetip);
		this.addActionMessage("新增记录页面显示管理信息成功！");
		this.pagetip = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录页面显示管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			if(pagetip.getPage_code()==null){
				return  notfundview();
			}else {
				this.pagetipService.update(pagetip);
				this.addActionMessage("修改"+pagetip.getRemark()+"信息成功,请点击右上角更新缓存！","修改"+pagetip.getRemark()+"信息成功");
				return goUrl(VIEW);
			}
	}
	/**
	 * 方法描述：删除记录页面显示管理信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.pagetipService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录页面显示管理信息成功");
		}else{
			this.addActionMessage("删除记录页面显示管理信息失败");
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
		int count=this.pagetipService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		pagetipList = this.pagetipService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出错误页面显示管理信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String errorview() throws Exception {
		 String id = "system_error_page";
			pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出无法找到页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String notfundview() throws Exception {
		    String id = "not_found_page";
			pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出无法找到页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String registerview() throws Exception {
		    String id = "register_agreement_page";
			pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出搜索为空页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String nullview() throws Exception {
		 String id = "serach_null_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出店铺不存在页面页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String illegalshopview() throws Exception {
		 String id = "illegal_shop_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出店商品不存在页面页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String illegalgoodsview() throws Exception {
		 String id = "illegal_goods_page ";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出重复提交页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String repeatview() throws Exception {
		 String id = "token_error_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出没有操作权限页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String notoperrightview() throws Exception {
		 String id = "not_oper_right_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HZX
	 * @Method Description : 根据主键找出没有 菜单权限页面显示管理信息信息
	 * @date : Jan 29, 2014 3:10:31 PM
	 */
	public String notmenurightview() throws Exception {
		 String id = "not_menu_right_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	
	/**
	 * @author HXK
	 * @Method Description : 试用条款
	 */
	public String freeuseagreementpageview() throws Exception {
		 String id = "freeuse_agreement_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * @author HXK
	 * @Method Description : 预售规则
	 */
	public String yushouagreementpageeview() throws Exception {
		 String id = "yushou_agreement_page";	
		pagetip = this.pagetipService.get(id);
		return goUrl(VIEW);
	}
	/**
	 * ZMS
	 * @return
	 * @throws Exception:海关总署公告
	 */
	 public String customspubliclyview() throws Exception{
		 String id = "customs_publicly_page";
		 pagetip =this.pagetipService.get(id);
		 return goUrl(VIEW);
	 } 
	 /**
		 * ZMS
		 * @return
		 * @throws Exception:手机联系我们
		 */
		 public String appcontactusview() throws Exception{
			 String id = "app_contact_us";
			 pagetip =this.pagetipService.get(id);
			 return goUrl(VIEW);
		 } 
	
	/**
	 * @return the PagetipList
	 */
	public List getPagetipList() {
		return pagetipList;
	}
	/**
	 * @param pagetipList
	 *  the PagetipList to set
	 */
	public void setPagetipList(List pagetipList) {
		this.pagetipList = pagetipList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(pagetip == null){
			pagetip = new Pagetip();
		}
		String id = this.pagetip.getPage_code();
		if(!this.validateFactory.isDigital(id)){
			pagetip = this.pagetipService.get(id);
		}
	}
	public String getPage_content() {
		return page_content;
	}
	public void setPage_content(String page_content) {
		this.page_content = page_content;
	}
	/**
	 * @return the pagetip
	 */
	public Pagetip getPagetip() {
		return pagetip;
	}
	/**
	 * @param Pagetip
	 *            the pagetip to set
	 */
	public void setPagetip(Pagetip pagetip) {
		this.pagetip = pagetip;
	}
	public String getPage_code() {
		return page_code;
	}
	public void setPage_code(String page_code) {
		this.page_code = page_code;
	}

}


