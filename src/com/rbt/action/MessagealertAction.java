/*
 
 * Package:com.rbt.action
 * FileName: MessagealertAction.java 
 */
package com.rbt.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Messagealert;
import com.rbt.service.IMessagealertService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录信息提醒设置信息action类
 * @author 创建人 HZX
 * @date 创建日期 Sat Feb 02 10:29:52 CST 2014
 */
@Controller
public class MessagealertAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	private Messagealert messagealert;
	
	/*******************业务层接口****************/
	@Autowired
	private IMessagealertService messagealertService;
	
	/*********************集合********************/
	
	public List messagealertList;//信息提醒设置信息
	/*********************字段********************/
	

	/**
	 * 方法描述：返回新增记录信息提醒设置信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录信息提醒设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(messagealert);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.messagealertService.insert(messagealert);
		this.addActionMessage("新增记录信息提醒设置信息成功！");
		this.messagealert = null;
		return INPUT;
	}

	/**
	 * @author HZX
	 * @Method Description : 修改模板信息是否启用
	 * @date : Feb 2, 2014 5:19:27 PM
	 */

	public void upstate(){
		HttpServletRequest request =getRequest();
		String selcheck_val = request.getParameter("selcheck_val");
		String type = request.getParameter("type");
		String msg_code = request.getParameter("msg_code");
		if(msg_code!=null && !msg_code.equals("")){
			Messagealert maMessagealert = this.messagealertService.get(msg_code);	
			if(type.equals("0")){
				maMessagealert.setIs_send_email(selcheck_val);
			}else if(type.equals("1")){
				maMessagealert.setIs_send_mobile(selcheck_val);
			}else{
				maMessagealert.setIs_send_letter(selcheck_val);
			}
			this.messagealertService.update(maMessagealert);
		}
	}
	
	
	
	/**
	 * 方法描述：修改记录信息提醒设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(messagealert);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.messagealertService.update(messagealert);
		this.addActionMessage("修改记录信息提醒设置信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录信息提醒设置信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.messagealertService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录信息提醒设置信息成功");
		}else{
			this.addActionMessage("删除记录信息提醒设置信息失败");
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
		int count=this.messagealertService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		messagealertList = this.messagealertService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录信息提醒设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.messagealert.getMsg_code();
		if(id==null || id.equals("")){
			messagealert = new Messagealert();
		}else{
			messagealert = this.messagealertService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MessagealertList
	 */
	public List getMessagealertList() {
		return messagealertList;
	}
	/**
	 * @param messagealertList
	 *  the MessagealertList to set
	 */
	public void setMessagealertList(List messagealertList) {
		this.messagealertList = messagealertList;
	}
	/**
	 * @return the messagealert
	 */
	public Messagealert getMessagealert() {
		return messagealert;
	}
	/**
	 * @param Messagealert
	 *            the messagealert to set
	 */
	public void setMessagealert(Messagealert messagealert) {
		this.messagealert = messagealert;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(messagealert == null){
			messagealert = new Messagealert();
		}
		String id = this.messagealert.getMsg_code();
		if(!this.validateFactory.isDigital(id)){
			messagealert = this.messagealertService.get(id);
		}
	}

}

