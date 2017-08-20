/*
 
 * Package:com.rbt.action
 * FileName: VisitnumAction.java 
 */
package com.rbt.action;
 
import java.util.*;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Visitnum;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IVisitnumService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录日访问数action类
 * @author 创建人 LHY
 * @date 创建日期 Thu Oct 11 09:56:36 CST 2014
 */
@Controller
public class VisitnumAction extends AdminBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层****************/
	private Visitnum visitnum;
	/*******************业务层接口****************/
	@Autowired
	private IVisitnumService visitnumService;
	@Autowired
	private IGoodsorderService goodsorderService;
	/*********************集合******************/
	public List visitnumList;//记录日访问数
	/*********************字段******************/
	public String starttime_s;//开始时间
	public String endtime_s;//结束时间
	public int tradenum;
	public int visitsize;
	public double tradesize;
	
	/**
	 * 方法描述：返回新增记录日访问数页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Oct 14, 2014 1:37:48 PM
	 * @Method Description :更新数据日PV访问数
	 */
	public void updatePvNum(){
		Map pvMap = new HashMap();
		pvMap.put("today", "1");
		Visitnum vn = new Visitnum();
		List list = this.visitnumService.getList(pvMap);
		if(list!=null && list.size()>0){
			HashMap listMap =(HashMap)list.get(0);
			if(listMap.get("day_pv")!=null){
				int num = Integer.parseInt(listMap.get("day_pv").toString());
				num=num+1;
				vn.setDay_pv(String.valueOf(num));
				vn.setId(listMap.get("id").toString());
				vn.setDay_time(listMap.get("day_time").toString());
			}
			this.visitnumService.update(vn);
		}else{
			vn.setDay_pv("1");
			this.visitnumService.insert(vn);
		}
	}
	

	/**
	 * 方法描述：新增记录日访问数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(!commonCheck()){
			return add();
		}
		this.visitnumService.insert(visitnum);
		this.addActionMessage("新增记录日访问数成功！");
		this.visitnum = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录日访问数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(!commonCheck()){
			return view();
		}
		this.visitnumService.update(visitnum);
		this.addActionMessage("修改记录日访问数成功！");
		return list();
	}
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 11:04:08 AM
	 * @Method Description :通用校验
	 */
	public boolean commonCheck(){
		super.commonValidateField(visitnum);
		if(super.ifvalidatepass){
			return false;
		}
		return true;
	}
	

	/**
	 * 方法描述：删除记录日访问数信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		boolean flag = this.visitnumService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除记录日访问数成功");
		}else{
			this.addActionMessage("删除记录日访问数成功失败");
		}
		return list();
	}
	/**
	 * @author LHY
	 * 方法描述：根据时间搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if((starttime_s!=null || starttime_s!="")&&(endtime_s!=null||endtime_s!="")){
			if(starttime_s!=null && !"".equals(starttime_s))
		    {	    	
		    	pageMap.put("starttime_s", starttime_s);
		    }
		    if(endtime_s!=null && !"".equals(endtime_s))
		    {	    	
		    	pageMap.put("endtime_s", endtime_s);
		    }
		    tradenum=goodsorderService.getCount(pageMap);
		    visitsize=this.visitnumService.getSum(pageMap);
		    tradesize=this.visitnumService.calculate(tradenum, visitsize);
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录日访问数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.visitnum.getId();
		if(id==null || id.equals("")){
			visitnum = new Visitnum();
		}else{
			visitnum = this.visitnumService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the VisitnumList
	 */
	public List getVisitnumList() {
		return visitnumList;
	}
	/**
	 * @param visitnumList
	 *  the VisitnumList to set
	 */
	public void setVisitnumList(List visitnumList) {
		this.visitnumList = visitnumList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(visitnum == null){
			visitnum = new Visitnum();
		}
		String id = this.visitnum.getId();
		if(!this.validateFactory.isDigital(id)){
			visitnum = this.visitnumService.get(id);
		}
	}
	/**
	 * @return the visitnum
	 */
	public Visitnum getVisitnum() {
		return visitnum;
	}
	/**
	 * @param Visitnum
	 *            the visitnum to set
	 */
	public void setVisitnum(Visitnum visitnum) {
		this.visitnum = visitnum;
	}

}

