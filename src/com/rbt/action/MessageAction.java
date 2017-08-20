/*
 
 * Package:com.rbt.action
 * FileName: MessageAction.java 
 */
package com.rbt.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Message;
import com.rbt.service.IMessageService;

/**
 * @function 功能  网站留言action类
 * @author  创建人 CYC
 * @date  创建日期  July 11, 2014
 */
@Controller
public class MessageAction extends AdminBaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8370522115773269896L;
	/*******************实体层********************/
	public Message message;
	
	/*******************业务层接口****************/
	@Autowired
	public IMessageService messageService;
	
	/*********************集合********************/
	public List messageList;//网站留言
	
	/*********************字段********************/
    public String  mess_title_s;
    public String  mess_name_s;
    public String  mess_phone_s;
    public String  mess_email_s;
    public String  mess_qq_s;
    public String  mess_msn_s;
    public String  mess_skype_s;

	/**
	 * 方法描述：根据主键找出网站留言信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改网站留言信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.messageService.update(message);
		this.addActionMessage("修改网站留言信息成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件网站留言列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if(mess_title_s!=null && !mess_title_s.equals("")) pageMap.put("title", mess_title_s);
		if(mess_name_s!=null && !mess_name_s.equals("")) pageMap.put("name", mess_name_s);
		if(mess_phone_s!=null && !mess_phone_s.equals("")) pageMap.put("phone", mess_phone_s);
		if(mess_email_s!=null && !mess_email_s.equals("")) pageMap.put("email", mess_email_s);
		if(mess_qq_s!=null && !mess_qq_s.equals("")) pageMap.put("qq", mess_qq_s);
		if(mess_msn_s!=null && !mess_msn_s.equals("")) pageMap.put("msn", mess_msn_s);
		if(mess_skype_s!=null && !mess_skype_s.equals("")) pageMap.put("skype", mess_skype_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.messageService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		messageList=this.messageService.getList(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除网站留言信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.messageService.delete(chb_id);
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
		return goUrl(ADD);
	}
	
	/**
	 * 方法描述：新增网站留言信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		if((!this.message.getPhone().equals(""))&&(ValidateUtil.isTelephone(this.message.getPhone())&&ValidateUtil.isMobile(this.message.getPhone()))){
			this.addFieldError("message.phone", "联系电话输入有误");
		}
		//字段验证
		super.commonValidateField(message);
		if(super.ifvalidatepass){
			return add();
		}
		this.messageService.insert(message);
		this.addActionMessage("新增网站留言成功");
		this.message = null;
		return add();
	}

	public Message getmessage() {
		return message;
	}

	public void setmessage(Message message) {
		this.message = message;
	}

	public List getmessageList() {
		return messageList;
	}

	public void setmessageList(List messageList) {
		this.messageList = messageList;
	}

	public String getMess_title_s() {
		return mess_title_s;
	}

	public void setMess_title_s(String mess_title_s) {
		this.mess_title_s = mess_title_s;
	}

	public String getMess_phone_s() {
		return mess_phone_s;
	}

	public void setMess_phone_s(String mess_phone_s) {
		this.mess_phone_s = mess_phone_s;
	}

	public String getMess_email_s() {
		return mess_email_s;
	}

	public void setMess_email_s(String mess_email_s) {
		this.mess_email_s = mess_email_s;
	}

	public String getMess_qq_s() {
		return mess_qq_s;
	}

	public void setMess_qq_s(String mess_qq_s) {
		this.mess_qq_s = mess_qq_s;
	}

	public String getMess_msn_s() {
		return mess_msn_s;
	}

	public void setMess_msn_s(String mess_msn_s) {
		this.mess_msn_s = mess_msn_s;
	}

	public String getMess_skype_s() {
		return mess_skype_s;
	}

	public void setMess_skype_s(String mess_skype_s) {
		this.mess_skype_s = mess_skype_s;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(message == null){
			message = new Message();
		}
		String id = message.getMess_id();
		if(!ValidateUtil.isDigital(id)){
			message = this.messageService.get(id);
		}
	}
	
	
    
}
