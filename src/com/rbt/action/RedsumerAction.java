/*
 
 * Package:com.rbt.action
 * FileName: RedsumerAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.BaseAction;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Exredbag;
import com.rbt.model.Redsumer;
import com.rbt.service.IExredbagService;
import com.rbt.service.IRedsumerService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 红包消费码action类
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 17:05:07 CST 2015
 */
@Controller
public class RedsumerAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 红包消费码对象
	 */
	private Redsumer redsumer;
	/*******************业务层接口****************/
	/*
	 * 红包消费码业务层接口
	 */
	@Autowired
	private IRedsumerService redsumerService;
	@Autowired
	private IExredbagService exredbagService;
	
	/*********************集合*******************/
	/*
	 * 红包消费码信息集合
	 */
	public List redsumerList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	
		
	/**
	 * 方法描述：返回新增红包消费码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增红包消费码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(redsumer);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.redsumerService.insert(redsumer);
		this.addActionMessage("新增红包消费码成功！");
		this.redsumer = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改红包消费码信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(redsumer);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.redsumerService.update(redsumer);
		this.addActionMessage("修改红包消费码成功！");
		return list();
	}
	
	/**
	 * 方法描述：删除消费码信息
	 * @return
	 * @throws Exception
	 */
	public String outDelete() throws Exception {
		commonDel();
		return outList();
	}
	
	/**
	 * 方法描述：删除消费码信息
	 * @return
	 * @throws Exception
	 */
	public String useDelete() throws Exception {
		commonDel();
		return useList();
	}
	
	/**
	 * 方法描述：删除红包消费码信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除红包消费码信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.redsumerService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除红包消费码信息成功!");
		}else{
			this.addActionMessage("删除红包消费码信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("use_state", "0");
        pageMap.put("date", "now");
		commonList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String useList() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("use_state", "1");
		commonList(pageMap);
		return goUrl("useindex");
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String outList() throws Exception {
        Map pageMap=new HashMap();
        pageMap.put("outdate", "now");
		commonList(pageMap);
		return goUrl("outindex");
	}	
	
	/**
	 * 公共查询方法
	 */
	public void commonList(Map pageMap){
		pageMap.put("cust_id", this.session_cust_id);
		//根据页面提交的条件找出信息总数
		int count=this.redsumerService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		redsumerList = this.redsumerService.getList(pageMap);
		redsumerList = ToolsFuc.replaceList(redsumerList, "");
	}	
	
	/**
	 * 方法描述：根据主键找出红包消费码信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.redsumer.getRedsumer_id();
		if(id==null || id.equals("")){
			redsumer = new Redsumer();
		}else{
			redsumer = this.redsumerService.get(id);
		}
		return goUrl(VIEW);
	}
	
	
	/**
	 * ajax兑换红包
	 * @throws IOException
	 */
	public void exRedpacket() throws IOException {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String red_no = request.getParameter("red_no");
		
		Map map = new HashMap();
		map.put("red_no", red_no);
		List list = this.exredbagService.getList(map);
		
		
		//判断是否已经兑换过
		if (list != null && list.size() > 0 ) {
			  Map couponMap = (HashMap) list.get(0);
			  if(couponMap.get("ex_state") != null && couponMap.get("ex_state").toString().equals("1")) {
				  //红包已经兑换过 
				  out.write("2");
			   }else {
				  //兑换红包
				  String ex_id = couponMap.get("ex_id").toString();
				  Exredbag exredbag = this.exredbagService.get(ex_id);
				  //插入红包
				  Redsumer redsumer = new Redsumer();
				  redsumer.setRedsumer_no(red_no);
				  redsumer.setRed_id(exredbag.getRed_id());
				  redsumer.setCust_id(this.session_cust_id);
				  redsumer.setUse_state("0");
				  this.redsumerService.insert(redsumer);
				  //修改兑换状态
				  exredbag.setEx_state("1");
				  this.exredbagService.update(exredbag);
				  out.write("3");
			   }
		}else { 
		   //判断红包号码不存在
		   out.write("1");
		   
		}
	}
	
	/**
	 * @return the RedsumerList
	 */
	public List getRedsumerList() {
		return redsumerList;
	}
	/**
	 * @param redsumerList
	 *  the RedsumerList to set
	 */
	public void setRedsumerList(List redsumerList) {
		this.redsumerList = redsumerList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(redsumer == null){
			redsumer = new Redsumer();
		}
		String id = this.redsumer.getRedsumer_id();
		if(!this.validateFactory.isDigital(id)){
			redsumer = this.redsumerService.get(id);
		}
	}
	/**
	 * @return the redsumer
	 */
	public Redsumer getRedsumer() {
		return redsumer;
	}
	/**
	 * @param Redsumer
	 *            the redsumer to set
	 */
	public void setRedsumer(Redsumer redsumer) {
		this.redsumer = redsumer;
	}
}

