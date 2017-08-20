/*
 
 * Package:com.rbt.action
 * FileName: ChannelAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.createHtml.ParseHtml;
import com.rbt.model.Channel;
import com.rbt.model.Commpara;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录网站栏目信息action类
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 15 10:57:10 CST 2014
 */
@Controller
public class ChannelAction extends AdminBaseAction implements Preparable{

	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	public Commpara commpara;
	public ParseHtml parsehtmlService;
	public Channel channel;

	/*******************业务层接口****************/
	@Autowired
	private IChannelService channelService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	public ICommparaService commparaService;

	/*********************集合******************/
	public List channelList;//记录网站栏目信息信息集合
	public List commparaList;//参数列表
	public List myfileList;//文件

	/*********************字段******************/
	public String admin_Sort_id;//排序ID
	public String isort_no;//排序输入的数字
	public String[] files;//文件数组
	public String update_ch_id;// 更新栏目获取的ID
	public String channel_id;//栏目ID
	public String cname;//栏目名称
	public String jsonMenu;//Json数值
	public String strall="all";//表示请求的是为"更新全部栏目
	public String strone="one";//表示请求的动作为"更新当前栏目页
	public String strtwo="two";//表示请求的动作为"更新子栏目页
	public String update_state;//更新状态
	public String update_type;// 更新的类型：如有update_type不为空的话，就是请求的更新详细栏目页的，否则就是为一般的栏目更新请求

	/**
	 * 方法描述：返回新增记录网站栏目信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
//		setCommpara();
//		getfilelist();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：网站栏目的级别增加
	 */
	public void validateChannelLevel() {
		if (channel.getCh_level().equals("1")) {
			channel.setCh_level("1");
		} else {
			Integer ch_level = Integer.parseInt(channel.getCh_level()) - 1;
			channel.setCh_level(ch_level.toString());
		}
	}

	public void getfilelist() throws Exception {
		File file = new File(PropertiesUtil.getRootpath()
				+ "/templets/"+templateFiles+"/");
		files = file.list();
		myfileList = new ArrayList();
		Map map = new HashMap();
		for (int i = 0; i < files.length; i++) {
			map.put("filenames", files[i]);
			myfileList.add(map);
		}
	}

	// 用于判断是否出现相同的栏目名称
	public void getCName() throws Exception {
		PrintWriter out = getResponse().getWriter();
		Map map = new HashMap();
		map.put("ch_name", cname);
		Integer count = 0;
		if (channel_id != null && !channel_id.equals("")) {
			Channel channames = new Channel();
			channames = channelService.get(channel_id);
			if (cname.equals(channames.getCh_name())) {
				count = 0;
			} else {
				count = channelService.getCount(map);
			}
		} else {
			count = channelService.getCount(map);
		}
		out.write(count.toString());

	}


	//更新前台栏目页面
	//ch_id：栏目标识
	//1：表示更新成功！0：更新失败
	public void updateHtmlPage() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = getResponse().getWriter();
		response.setCharacterEncoding("UTF-8");
		String ch_id="";//更新栏目的ID
		String is_pc_webapp = "";//PC端或触屏版
		String outString="0";//1：表示更新成功！0：更新失败,2:表示需要重新登录
		if(!validateFactory.isRequired(this.session_cust_id)){
			//获取更新栏目的ID
			if(request.getParameter("ch_id")!=null && request.getParameter("is_pc_webapp")!= null){
				ch_id=request.getParameter("ch_id");
				
				is_pc_webapp = request.getParameter("is_pc_webapp").toString();
				//更新全部栏目
		        outString=this.channelService.updateHtmlPage(ch_id,is_pc_webapp);
			}
		}else if(validateFactory.isRequired(this.session_cust_id)){
			outString="2";//没有登录或者登录过期
		}
		out.write(outString);
	}
	
	//更新详细页
	//ch_id all：更新所有
	//type 1：递归更新下级栏目详细页
	public  void updateArticleHtml() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = getResponse().getWriter();
		response.setCharacterEncoding("UTF-8");
		String type="";//更新栏目的类型
		String ch_id="";//更新栏目的ID
		String outString="0";//1：表示更新成功！0：更新失败
		//获取更新栏目的类型
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
		//获取更新栏目的ID
		if(request.getParameter("ch_id")!=null){
			ch_id=request.getParameter("ch_id");
		}
		DoHtml doHtml = new DoHtml();
        outString=this.channelService.updateArticleHtml(type, ch_id, doHtml);
		out.write(outString); ;
		
	}
	
	
	/**
	 * 方法描述：新增记录网站栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if (this.channel.getModule_type().equals("0")) {
			channel.setModule_type("");
		}
		//字段验证
		if(commonCheck())return add();
		this.channelService.insert(channel);
		validateChannelLevel();
		this.addActionMessage("新增网站栏目信息成功");
		channel=null;
		return list();
	}

	/**
	 * 方法描述：修改记录网站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = channel.getCh_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if(ValidateUtil.isDigital(id)){
			return list();
		}
		//字段验证
		if(commonCheck())return view();
		this.channelService.update(channel);
		this.addActionMessage("修改网站栏目信息成功");
		return list();
	}

	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 2:20:35 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck()throws Exception{
	    if (this.channelService.getChanneName(channel.getCh_name(), channel.getCh_id()) != 0) {
			this.addFieldError("channel.ch_name", "已经存在该栏目名称");
		}
		//字段验证
		super.commonValidateField(channel);
		if(super.ifvalidatepass){
			return true;
		}
		return false;
	}
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 1:36:10 PM
	 * @Method Description :批量栏目排序
	 */
	public String updateSort() throws Exception {
		boolean flag = this.channelService.updateSort("ch_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("网站栏排序成功");
		}else{
			this.addActionMessage("网站栏排序失败");
		}
		return list();
	}
	
	
	
	/**
	 * 方法描述：删除记录网站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.channel.getCh_id();
		id = id.replace(" ", "");
		this.channelService.recuDelete(id);
		this.addActionMessage("删除栏目成功");
		return list();
	}
	
	
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		HashMap pageMap=new HashMap();
		channelList = this.channelService.getList(pageMap);
		jsonMenu=GridTreeUtil.getJsonStr(channelList);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录网站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：取参数表所属模块集合信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setCommpara(){
		Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	commparaList = this.sysmoduleService.getList(pageMap);
	}
	
	/**
	 * @MethodDescribe 方法描述    加载需要的索引页面
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 24, 2014 10:06:00 AM
	 */
	public String luceneindex() throws Exception {
		channelList = this.channelService.getList(new HashMap());
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：初始化加载更新栏目页面信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		channelList = this.channelService.getList( new HashMap());
		jsonMenu=GridTreeUtil.getJsonStr(channelList);
		return goUrl(AUDITLIST);
	}
	/**
	 * 方法描述：初始化加载更新详细栏目页面信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("module_type", "0");
		channelList = this.channelService.getList(pageMap);
		return goUrl(AUDIT);
	}
    /**
     *执行更行动作的判断
     * @param mchannel
     * @param type
     */
	public void chanvalue(Channel mchannel,String type)
    {
		 DoHtml pHtml = new DoHtml();
		 if(type.equals("1"))//判断请求的动作是：更新栏目页，还是更新详细栏目页；1为：更新栏目页；0：更新详细栏目
		 {
		   //pHtml.doArticleHtml(mchannel);TODO
		 }
		 else {
			 //pHtml.doIndexHtml(mchannel);TODO
		}
    }
	/**
	 * @MethodDescribe 方法描述    更新首页静态化方法
	 * @author  创建人  LJQ
	 * @throws IOException 
	 * @date  创建日期  Sep 17, 2014 11:18:54 AM
	 */
	public void updateIndexPage() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ParseHtml pHtml = new ParseHtml();
		String dir="",filename="",savedir="";
		//获取模板的路径
		if(request.getParameter("dir")!=null){
			dir=request.getParameter("dir");
		}
		//获取生成后的文件名
		if(request.getParameter("filename")!=null){
			filename=request.getParameter("filename");
		}
		//获取生成后的文件保存路径
		if(request.getParameter("savedir")!=null){
			savedir=request.getParameter("savedir");
		}
		//pHtml.doIndexHtml(dir, filename, savedir, null);
	}
	
	/**
	 * 方法描述：AJAX更新栏目信息的方法
	 * 
	 * @throws Exception
	 */
	public void updateChannelPage() throws Exception {
		String outString="";//返回前台页面提示的字符串；
		String id = this.update_ch_id;//获取更新ID
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//处理信息
	    outString=this.channelService.updateChannelPage(update_state, strall, strtwo, update_type, channel, id, strone);
		out.write(outString);
	}
	/**
	 * @return the ChannelList
	 */
	public List getChannelList() {
		return channelList;
	}

	/**
	 * @param channelList
	 *            the ChannelList to set
	 */
	public void setChannelList(List channelList) {
		this.channelList = channelList;
	}

	public String getAdmin_Sort_id() {
		return admin_Sort_id;
	}

	public void setAdmin_Sort_id(String admin_Sort_id) {
		this.admin_Sort_id = admin_Sort_id;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public List getMyfileList() {
		return myfileList;
	}

	public void setMyfileList(List myfileList) {
		this.myfileList = myfileList;
	}

	public String getUpdate_ch_id() {
		return update_ch_id;
	}

	public void setUpdate_ch_id(String update_ch_id) {
		this.update_ch_id = update_ch_id;
	}

	public ParseHtml getParsehtmlService() {
		return parsehtmlService;
	}

	public void setParsehtmlService(ParseHtml parsehtmlService) {
		this.parsehtmlService = parsehtmlService;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public Commpara getCommpara() {
		return commpara;
	}

	public void setCommpara(Commpara commpara) {
		this.commpara = commpara;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public String getUpdate_state() {
		return update_state;
	}

	public void setUpdate_state(String update_state) {
		this.update_state = update_state;
	}
	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param Channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(channel == null){
			channel = new Channel();
		}
		String id = this.channel.getCh_id();
		if(!ValidateUtil.isDigital(id)){
			channel = this.channelService.get(id);
		}
	}
}
