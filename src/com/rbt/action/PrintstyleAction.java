/*
 
 * Package:com.rbt.action
 * FileName: PrintstyleAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.Constants;
import com.rbt.model.Printstyle;
import com.rbt.service.IPrintstyleService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录打印样式信息action类
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 27 15:56:03 CST 2014
 */
@Controller
public class PrintstyleAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/** *****************实体层******************* */
	private Printstyle printstyle;

	/** *****************业务层接口*************** */

	@Autowired
	private IPrintstyleService printstyleService;
	/** *******************集合******************* */
	public List printstyleList;// 打印样式信息
	public List commparaList;//打印项集合
	public String [] printCommparaString;//用户选中的打印项
	/** *******************字段******************* */

	/**
	 * 方法描述：返回新增记录打印样式信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获得打印项
		commparaList=com.rbt.function.CommparaFuc.getEnabledList("pri_param");
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录打印样式信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		printstyle.setCust_id(this.session_cust_id);
		if(printstyle.getPrint_param()!=null){
			String print_param=printstyle.getPrint_param();
			printCommparaString = print_param.split(",");
		}
		super.commonValidateField(printstyle);
		if (super.ifvalidatepass) {
			return add();
		}
		this.printstyle.replacePrintCode();
		printstyle.setPrint_style("1");//设置打印样式为套打
		this.printstyleService.insert(printstyle);
		this.addActionMessage("新增运单样式成功！");
		this.printstyle = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录打印样式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(printstyle);
		if (super.ifvalidatepass) {
			return view();
		}
		this.printstyle.replacePrintCode();
		this.printstyleService.update(printstyle);
		this.addActionMessage("修改样式成功！");
		if(printstyle.getPrint_style().equals("1")){
			return list();
		}else if(printstyle.getPrint_style().equals("2")){
			return qrcodelist();
		}
        return NONE;
	}
	
	/**
	 * @author:HXM
	 * @date:May 21, 201410:30:30 AM
	 * @param:
	 * @Description:
	 */
	public String updateHtml() throws Exception {
		super.commonValidateField(printstyle);
		if (super.ifvalidatepass) {
			return viewHtml();
		}
		this.printstyleService.update(printstyle);
		this.addActionMessage("修改打印样式成功！");
		return goUrl("updatehtml");
	}

	/**
	 * 方法描述：删除记录打印样式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.printstyleService.delete(chb_id);
		if (flag) {
			this.addActionMessage("删除运单样式成功");
		} else {
			this.addActionMessage("删除运单样式失败");
		}
		return list();
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
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE))
			pageMap.put("cust_id", this.session_cust_id);
		//只显示运单样式
		pageMap.put("print_style", "1");
		// 根据页面提交的条件找出信息总数
		int count = this.printstyleService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		printstyleList = this.printstyleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String qrcodelist() throws Exception {
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE))
			pageMap.put("cust_id", this.session_cust_id);
		//只显示运单样式
		pageMap.put("print_style", "2");
		// 根据页面提交的条件找出信息总数
		int count = this.printstyleService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		printstyleList = this.printstyleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录打印样式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.printstyle.getTrade_id();
		if (id == null || id.equals("")) {
			printstyle = new Printstyle();
		} else {
			printstyle = this.printstyleService.get(id);
			if(printstyle.getPrint_style().equals("1")){//订单显示打印项
				commparaList=com.rbt.function.CommparaFuc.getEnabledList("pri_param");
			}else if(printstyle.getPrint_style().equals("2")){//二维码显示打印项
				commparaList=com.rbt.function.CommparaFuc.getEnabledList("print_md_goods_code");
			}
			
			if(printstyle.getPrint_param()!=null){
				String print_param=printstyle.getPrint_param();
				printCommparaString = print_param.split(",");
			}
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @author:HXM
	 * @date:May 21, 201410:25:13 AM
	 * @param:
	 * @Description:静态打印内容修改
	 */
	public String viewHtml() throws Exception {
		String id = this.printstyle.getTrade_id();
		if (id == null || id.equals("")) {
			printstyle = new Printstyle();
		} else {
			printstyle = this.printstyleService.get(id);
		}
		return goUrl("updatehtml");
	}

	/**
	 * @return the PrintstyleList
	 */
	public List getPrintstyleList() {
		return printstyleList;
	}

	/**
	 * @param printstyleList
	 *            the PrintstyleList to set
	 */
	public void setPrintstyleList(List printstyleList) {
		this.printstyleList = printstyleList;
	}

	/**
	 * @return the printstyle
	 */
	public Printstyle getPrintstyle() {
		return printstyle;
	}

	/**
	 * @param Printstyle
	 *            the printstyle to set
	 */
	public void setPrintstyle(Printstyle printstyle) {
		this.printstyle = printstyle;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (printstyle == null) {
			printstyle = new Printstyle();
		}
		String id = this.printstyle.getTrade_id();
		if (!this.validateFactory.isDigital(id)) {
			printstyle = this.printstyleService.get(id);
		}
	}

}
