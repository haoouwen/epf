/*
 
 * Package:com.rbt.action
 * FileName: MembercatAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.model.Membercat;
import com.rbt.service.IMembercatService;
import com.opensymphony.xwork2.Preparable;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品自定义分类信息action类
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 07 14:02:34 CST 2014
 */
@Controller
public class MembercatAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Membercat membercat;
	
	/*******************业务层接口****************/
	@Autowired
	private IMembercatService membercatService;
	
	/*********************集合********************/
	public List membercatList;
	
	/*********************字段********************/

	/**
	 * 方法描述：返回新增记录商品自定义分类信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品自定义分类信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String cat_id = RandomStrUtil.getNumberRand();
		membercat.setCat_id(cat_id);
		membercat.setCust_id(this.session_cust_id);
		// 字段验证
		super.commonValidateField(membercat);
		if (super.ifvalidatepass) {
			return add();
		}
		if (membercat.getParent_cat_id().equals("1111111111")) {
			membercat.setLevel("1");
		} else {
			membercat.setLevel("2");
		}
		// 判断是否已经是二级分类
		if (Integer.parseInt(membercat.getLevel()) > 2) {
			this.addActionMessage("抱歉！商品分类只能添加二级分类！");
			return add();
		} else {
			this.membercatService.insert(membercat);
			this.addActionMessage("新增记录商品自定义分类信息成功！");
			return list();
		}
	}

	/**
	 * 方法描述：修改记录商品自定义分类信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(membercat);
		if (super.ifvalidatepass) {
			return view();
		}

		this.membercatService.update(membercat);
		this.addActionMessage("修改记录商品自定义分类信息成功！");
		return list();
	}

	/**
	 * 方法描述：删除记录商品自定义分类信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean falg=false;
		String tip="成功";
		String id = this.membercat.getCat_id();
		if (id != null && !"".equals(id)) {
			id = id.replace(" ", "");
			falg=this.membercatService.dealDelete(id,this.session_cust_id);
		}
		if(!falg){
			tip="失败";
		}
			this.addActionMessage("删除商品自定义分类信息"+tip+"！");
			
		return list();
	}
	
	
	/**
	 * @Method Description :
	 * @author: HXK
	 * @date : May 29, 2014 7:07:18 PM
	 * @param 
	 * @return return_type
	 */
	public void ajaxCatGoods() throws Exception {
		String outString="";//返回前台页面提示的字符串；
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cat_id=request.getParameter("cat_id");
		//处理信息
		if(membercatService.memCatGoods(cat_id, this.session_cust_id)==true){
			outString="1";//能删除
		}else {
			outString="2";//不能删除
		}
		out.write(outString);
	}
	
	

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		// 过滤会员
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.membercatService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		membercatList = this.membercatService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录商品自定义分类信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.membercat.getCat_id();
		if (id == null || id.equals("")) {
			membercat = new Membercat();
		} else {
			membercat = this.membercatService.get(id);
		}
		return goUrl(VIEW);
	}


	/**
	 * @return the MembercatList
	 */
	public List getMembercatList() {
		return membercatList;
	}

	/**
	 * @param membercatList
	 *            the MembercatList to set
	 */
	public void setMembercatList(List membercatList) {
		this.membercatList = membercatList;
	}
	/**
	 * @return the membercat
	 */
	public Membercat getMembercat() {
		return membercat;
	}

	/**
	 * @param Membercat
	 *            the membercat to set
	 */
	public void setMembercat(Membercat membercat) {
		this.membercat = membercat;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (membercat == null) {
			membercat = new Membercat();
		}
		String id = this.membercat.getCat_id();
		if (!this.validateFactory.isDigital(id)) {
			membercat = this.membercatService.get(id);
		}
	}

}
