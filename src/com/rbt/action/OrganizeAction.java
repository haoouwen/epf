/*
 
 * Package:com.rbt.action
 * FileName: OrganizeAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Organize;
import com.rbt.service.IOrganizeService;

/**
 * @function 功能 记录组织部门action类
 * @author 创建人 LJQ
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2014
 */
@Controller
public class OrganizeAction extends AdminBaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	public Organize organize;
	/*******************业务层接口****************/
	@Autowired
	public IOrganizeService organizeService;
	/*********************集合********************/
	public List organizeList;//组织部门
	
	/*********************字段********************/
	
	
	/**
	 * 方法描述：返回新增记录组织部门页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录组织部门
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		if(area_attr!=null&&!area_attr.equals("")){
			area_attr=area_attr.replace(" ","");	
			areaIdStr+=","+area_attr;
		}
		//生成随机数
		String org_id = RandomStrUtil.getNumberRand();
		String up_org_id=organize.getUp_org_id();
		organize.setArea_attr(area_attr);
		organize.setOrg_id(org_id);
		//'0：业务部门 1：系统部门'
		organize.setSys_org("0");		
		//字段验证
		super.commonValidateField(organize);
		if(super.ifvalidatepass){
			return add();
		}	
		this.organizeService.insert(organize);
		this.addActionMessage("新增组织部门信息成功,请点击右上角更新缓存!","新增组织部门信息成功");
		//保留部门等级
		String org_lel=organize.getOrg_level();
		this.organize=null;
		//重新赋值
		organize=new Organize();
		organize.setOrg_level(org_lel);
		organize.setUp_org_id(up_org_id);
		return INPUT;
	}

	/**
	 * 方法描述：修改记录组织部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属地区的回选开始
		selectArea();
		//验证所属地区是否有选择,验证第一级时是否未选择
		organize.setArea_attr(area_attr);
		//字段验证
		super.commonValidateField(organize);
		if(super.ifvalidatepass){
			return view();
		}	
		this.organizeService.update(organize);
		this.addActionMessage("修改组织部门信息成功,请点击右上角更新缓存!","修改组织部门信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录组织部门信息
	 * @return
	 * @throws Exception
	 */
	public String org_delete() throws Exception {
		boolean flag=false;
		String tip="成功";
		//定义request对象
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		//获取前台传过来的ID值
		if(request.getParameter("id")!=null){
			String id = request.getParameter("id");
			//进入循环删除ID
			flag=this.organizeService.dealDelete(id);
		}
		if(!flag){
			tip="失败";
		}
		this.addActionMessage("删除组织部门信息成功,请点击右上角更新缓存!","删除组织部门信息成功");
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
		int count=this.organizeService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		organizeList = this.organizeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @Method Description : 获取组织部门的列表
	 * @author : LJQ
	 * @date : Nov 7, 2014 1:49:27 PM
	 */
	public void getList() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap=new HashMap();
		//判断传进来的上一级ID是否为空,为空时把最上级的ID传进去
		String up_org_id="";
		if(request.getParameter("up_org_id")!=null){
			up_org_id = request.getParameter("up_org_id");
			pageMap.put("up_org_id", up_org_id);
		}else{
			pageMap.put("up_org_id", Constants.UP_ORG_ID);
		}
		List list = this.organizeService.getList(pageMap);	
		StringBuffer sb=new StringBuffer();
		//获取要打印的html字符串
		sb=this.organizeService.getHtmlStr(list,up_org_id);
		if(list!=null&&list.size()>0){
			out.write(sb.toString());
		}else{
			out.write("");
		}		
	}
	
	/**
	 * @MethodDescribe 方法描述    实现异步方式的所属部门的级联加载
	 * @author  创建人  LJQ
	 * @date  创建日期  Jul 26, 2014 9:16:54 AM
	 */
	public String viewlist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap = new HashMap();
		//获取上一级的分类ID,所属模块类型
		String up_org_id = "";
		if(request.getParameter("cid")!=null){
			up_org_id=request.getParameter("cid").toString();
		}
		//判断从页面传过来的ID是否为空，如果为空则把up_cat常量赋给上一级分类ID
		if (up_org_id == null || up_org_id.equals("")) {
			up_org_id = Constants.UP_ORG_ID;
		}
	    //去掉首尾的空格	
		pageMap.put("up_org_id", up_org_id.trim());
		List list = this.organizeService.getList(pageMap);
		//获取要打印的html字符串
		StringBuffer sb = new StringBuffer();
		sb=this.organizeService.getViewHtmlStr(list, up_org_id);
		PrintWriter out = response.getWriter();
		//判断列表的数据是否为空，如果是则输出空，否则则输出字符串
		if (list!=null && list.size() > 0) {
			out.write("" + sb.toString());
		} else {
			out.write("");
		}
		return Action.NONE;
	}
	/**
	 * @author LHY
	 * 删除当前部门，和所属子部门
	 * @return
	 * @throws Exception 
	 */
	public String delete() throws Exception{
		String id=this.getRequest().getParameter("id");
		id.replace(" ","");
		if(id!=null || !id.equals("")){
			this.organizeService.recuDelete(id);
			this.addActionMessage("删除部门成功！");
		}
		return list();
		
	}

	
	/**
	 * 方法描述：根据主键找出记录组织部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(organize!=null&&organize.getArea_attr()!=null){
			String area_Str=organize.getArea_attr();
			// 找出地区字段的存入隐藏值中
			viewArea(area_Str); 
		}		
		return goUrl(VIEW);
	}
	/**
	 * @return the OrganizeList
	 */
	public List getOrganizeList() {
		return organizeList;
	}
	/**
	 * @param organizeList
	 *  the OrganizeList to set
	 */
	public void setOrganizeList(List organizeList) {
		this.organizeList = organizeList;
	}

	/**
	 * @return the organize
	 */
	public Organize getOrganize() {
		return organize;
	}
	/**
	 * @param Organize
	 *            the organize to set
	 */
	public void setOrganize(Organize organize) {
		this.organize = organize;
	}
	
	
	public void prepare() throws Exception { super.saveRequestParameter();
	if(organize == null){
		organize = new Organize();
	}
	String id = organize.getOrg_id();
	if(!ValidateUtil.isRequired(id)){
		organize = this.organizeService.get(id);
	}
}
	
}

