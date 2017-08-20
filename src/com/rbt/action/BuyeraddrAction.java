/*
 
 * Package:com.rbt.action
 * FileName: BuyeraddrAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.AdminBaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.service.IBuyeraddrService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 收货地址管理action类
 * @author 创建人 HZX
 * @date 创建日期 Thu Dec 27 16:33:56 CST 2014
 */
@Controller
public class BuyeraddrAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*******************实体层****************/
	private Buyeraddr buyeraddr;

	/*******************业务层接口****************/
	@Autowired
	private IBuyeraddrService buyeraddrService;

	/*********************集合******************/
	public List buyeraddrList;//收货地址管理信息集合

	/*********************字段******************/
	public String cust_name_s;//商家名称
	public String consignee_s;//收货人姓名
	public String cell_phone_s;//手机
	public String area_attr_s;//地区
	public String max_num;
	public String cust_id_s;//标记会员编号
	
	/**
	 * 方法描述：返回新增收货地址管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增收货地址管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		if(commonCheck())return add();
	    Map map=new HashMap();
	    map.put("cust_id",this.session_cust_id);
	    List list=this.buyeraddrService.getList(map);
	    if(list.size()>=cfg_maxaddressnumber){
	    	this.addActionMessage("收货地址不能超过"+cfg_maxaddressnumber+"个！");
	    	return add();
	    }else{
	    	buyeraddr.setCust_id(this.session_cust_id);
	    	this.buyeraddrService.insert(buyeraddr);
	    	this.addActionMessage("新增收货地址管理成功！");
	    }
		this.buyeraddr = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改收货地址管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		if(commonCheck())return view();
		this.buyeraddrService.update(buyeraddr);
		this.addActionMessage("修改收货地址管理成功！");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 2:21:59 PM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		//保存地区
		selectArea();
		if(area_attr==""||area_attr==null){
			this.addFieldError("area_attr", "请选择地区");
		}else {
			this.buyeraddr.setArea_attr(area_attr);
		}
		if(validateFactory.isRequired(buyeraddr.getPhone())&&validateFactory.isRequired(buyeraddr.getCell_phone())){
			this.addFieldError("buyeraddr.phone", "电话手机请至少填写一个！");
		}
		super.commonValidateField(buyeraddr);
		if(super.ifvalidatepass){
			return true;
		}
		 //默认一个用户只能有一条默认的地址
		String sel_address =buyeraddr.getSel_address();
	    if(sel_address.equals("0")){
	    	Map hasdfoaddr = new HashMap();
	    		hasdfoaddr.put("cust_id", this.session_cust_id);
	    		hasdfoaddr.put("sel_address", "0");
	    		buyeraddrList = this.buyeraddrService.getList(hasdfoaddr);
		    	if(buyeraddrList!=null&&buyeraddrList.size()>0){
		    		Map isdefo = (Map) buyeraddrList.get(0);
		    		String addr_id=isdefo.get("addr_id").toString();
		    		Buyeraddr	setdefoaddr =new Buyeraddr();
		    		setdefoaddr = this.buyeraddrService.get(addr_id);
		    		setdefoaddr.setSel_address("1");
		    		this.buyeraddrService.update(setdefoaddr);
			}
	    	
	    }
		return false;
	}
	/**
	 * 方法描述：删除收货地址管理信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.buyeraddrService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除收货地址管理成功");
		}else{
			this.addActionMessage("删除收货地址管理失败");
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
		common();
		 max_num=SysconfigFuc.getSysValue("cfg_maxaddressnumber");
		return goUrl(INDEXLIST);
	}
	
	public void common() throws Exception{
		Map pageMap = new HashMap();
	
		pageMap.put("cust_id", this.session_cust_id);
		if(!ValidateUtil.isRequired(cust_id_s)){
				pageMap.put("cust_id", cust_id_s);
		}
		
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s.trim());
		}
		if (consignee_s != null && !consignee_s.equals("")) {
			pageMap.put("consignee", consignee_s.trim());
		}
		if (cell_phone_s != null && !cell_phone_s.equals("")) {
			pageMap.put("cell_phone", cell_phone_s.trim());
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s.trim());
			//回选地区
			viewArea(area_attr_s);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.buyeraddrService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		buyeraddrList = this.buyeraddrService.getList(pageMap);
		buyeraddrList = ToolsFuc.replaceList(buyeraddrList, "");
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:49:50 AM
	 * @param:
	 * @Description:通过对用户cust_id进行分组查询得到信息
	 */
	public String groupList() throws Exception {
		Map pageMap = new HashMap();
		if (!ValidateUtil.isRequired(cust_name_s)) {
			pageMap.put("cust_name", cust_name_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.buyeraddrService.getGroupCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		buyeraddrList = this.buyeraddrService.getListByGroup(pageMap);
		return goUrl("groupindex");
	}
	
	/**
	 * 方法描述：根据主键找出收货地址管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.buyeraddr.getAddr_id();
		if(id==null || id.equals("")){
			buyeraddr = new Buyeraddr();
			return list();
		}else{
			buyeraddr = this.buyeraddrService.get(id);
		}
		area_attr=buyeraddr.getArea_attr();
		selectArea();
		return goUrl(VIEW);
	}
	
	/******************************以下为运营商退/换货地址管理操作**************************************************/
	
	/**退/换货集合信息
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String sysList() throws Exception{
		common();
		return goUrl("sysindex");
	}
	
    /**
     * @author QJY
     * @return
	 * @throws Exception
     */
	public String sysView() throws Exception{
		String id = this.buyeraddr.getAddr_id();
		if(id==null || id.equals("")){
			buyeraddr = new Buyeraddr();
			return list();
		}else{
			buyeraddr = this.buyeraddrService.get(id);
		}
		area_attr=buyeraddr.getArea_attr();
		selectArea();
		return goUrl("sysupdate");
	}
	/**
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String sysAdd() throws Exception{
		return goUrl("sysinsert");
	}
	
	/**
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String sysUpdate() throws Exception{
		//字段验证
		if(commonCheck())return sysView();
		this.buyeraddrService.update(buyeraddr);
		this.addActionMessage("修改退/换货地址成功！");
		return sysList();
	}
	/**
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String sysInsert() throws Exception{
		//字段验证
		if(commonCheck())return sysAdd();
    	buyeraddr.setCust_id(this.session_cust_id);
    	this.buyeraddrService.insert(buyeraddr);
    	this.addActionMessage("新增退/换货地址成功！");
		this.buyeraddr = null;
		return sysList();
	}
	/**
	 * @author QJY
	 * @return
	 * @throws Exception
	 */
	public String sysDelete()throws Exception{
		boolean flag = this.buyeraddrService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除退/换货地址成功");
		}else{
			this.addActionMessage("删除退/换货地址失败");
		}
		return sysList();
	}
	
	
	
	/**
	 * @return the buyeraddr
	 */
	public Buyeraddr getBuyeraddr() {
		return buyeraddr;
	}
	/**
	 * @param Buyeraddr
	 *            the buyeraddr to set
	 */
	public void setBuyeraddr(Buyeraddr buyeraddr) {
		this.buyeraddr = buyeraddr;
	}
	
	/**
	 * @return the BuyeraddrList
	 */
	public List getBuyeraddrList() {
		return buyeraddrList;
	}
	
	
	/**
	 * @param buyeraddrList
	 *  the BuyeraddrList to set
	 */
	public void setBuyeraddrList(List buyeraddrList) {
		this.buyeraddrList = buyeraddrList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(buyeraddr == null){
			buyeraddr = new Buyeraddr();
		}
		String id = this.buyeraddr.getAddr_id();
		if(!this.validateFactory.isDigital(id)){
			buyeraddr = this.buyeraddrService.get(id);
		}
	}
	
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getConsignee_s() {
		return consignee_s;
	}
	public void setConsignee_s(String consignee_s) {
		this.consignee_s = consignee_s;
	}
	public String getCell_phone_s() {
		return cell_phone_s;
	}
	public void setCell_phone_s(String cell_phone_s) {
		this.cell_phone_s = cell_phone_s;
	}
	public String getArea_attr_s() {
		return area_attr_s;
	}
	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}


}

