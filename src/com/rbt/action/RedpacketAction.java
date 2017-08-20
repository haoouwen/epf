/*
 
 * Package:com.rbt.action
 * FileName: RedpacketAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.GoodsDetailToAppDetailFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Exredbag;
import com.rbt.model.Redpacket;
import com.rbt.service.IExredbagService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IRedpacketService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 红包action类
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 20:50:54 CST 2015
 */
@SuppressWarnings("unchecked")
@Controller
public class RedpacketAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 红包对象
	 */
	private Redpacket redpacket;
	/*******************业务层接口****************/
	/*
	 * 红包业务层接口
	 */
	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	/*
	 * 红包兑换表对象
	 */
	private Exredbag exredbag;
	/*******************业务层接口****************/
	/*
	 * 红包兑换表业务层接口
	 */
	@Autowired
	private IExredbagService exredbagService;
	/*********************集合*******************/
	/*
	 * 红包信息集合
	 */
	public List redpacketList;
	/*
	 * 红包兑换表信息集合
	 */
	public List exredbagList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String red_name_s;//名称
	public String red_no_s;//号码
	public String info_state_s;//状态
	public String money_s;//类型
	public String start_time_s;//开始时间
	public String end_time_s;//结束时间
	public String member_level_s;//会员级别
	public String s_goods_name;//搜索名称
	public String sgis;//搜索ID	
	public String down_num_s;//下载数量
	public String red_id_s;//下载红包ID
	public String  redpaceket_id;
	public String dowm_red_id_s;//下载红包ID
	public String redkeyId;
	/**
	 * 方法描述：返回新增红包页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取会员级别
	    commpara();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增红包
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(redpacket);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.redpacketService.insert(redpacket);
		this.addActionMessage("新增红包成功！");
		this.redpacket = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改红包信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(redpacket);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.redpacketService.update(redpacket);
		this.addActionMessage("修改红包成功！");
		return list();
	}
	/**
	 * 方法描述：删除红包信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 获取会员级别
	 */
	public void commpara() {
		Map map = new HashMap();
		map.put("mem_type", "1");
		commparaList = this.malllevelsetService.getList(map);
	}
	
	/**
	 * 方法描述：公用删除红包信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.redpacketService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除红包信息成功!");
		}else{
			this.addActionMessage("删除红包信息失败!");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//名称
		if(!ValidateUtil.isRequired(red_name_s)) {
			pageMap.put("red_name", red_name_s);
		}
		//号码
		if(!ValidateUtil.isRequired(red_no_s)) {
			pageMap.put("red_no", red_no_s);
		}
        //状态
		if(!ValidateUtil.isRequired(info_state_s)) {
			pageMap.put("info_state", info_state_s);
		}
		//金额
		if(!ValidateUtil.isRequired(money_s)) {
			pageMap.put("money", money_s);
		}
		//开始时间
		if(!ValidateUtil.isRequired(start_time_s)) {
			pageMap.put("start_time", start_time_s);
		}
		//结束时间
		if(!ValidateUtil.isRequired(end_time_s)) {
			pageMap.put("end_time", end_time_s);
		}
		//会员级别
		if(!ValidateUtil.isRequired(member_level_s)) {
			pageMap.put("member_level", member_level_s);
		}		
		//根据页面提交的条件找出信息总数
		int count=this.redpacketService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		redpacketList = this.redpacketService.getList(pageMap);
		redpacketList = ToolsFuc.replaceList(redpacketList, "");
		//获取会员级别
	    commpara();
		return goUrl(INDEXLIST);
	}
	public void maxredpacket() throws Exception {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String red_id_max=request.getParameter("red_id");
		String down_max=request.getParameter("down_max");
		if(red_id_max!=null&&!"".equals(red_id_max)){
		    redpacket=this.redpacketService.get(red_id_max);
		    if(redpacket!=null&&redpacket.getRed_num()!=null&&redpacket.getRed_num().equals("不限制")){
		    	out.write("1");//可以下载
		    }else {
		    	if(down_max!=null&&!"".equals(down_max)){
		    		if(Integer.parseInt(down_max)>Integer.parseInt(redpacket.getRed_num())){
						out.write("0");//不能下载
					}else {
						out.write("1");//能下载
					}
		    	}else {
		    		out.write("0");//不能下载
				}
			}
		}else{
			out.write("0");
		}
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String redbaglist() throws Exception {
		Map pageMap = new HashMap();
		//根据页面提交的条件找出信息总数
		pageMap.put("red_id", redpaceket_id);
		//下载码搜索
		if(!ValidateUtil.isRequired(dowm_red_id_s)) {
			pageMap.put("red_no", dowm_red_id_s);
		}
		
		int count=this.exredbagService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		exredbagList = this.exredbagService.getList(pageMap);
		return goUrl("redbagindex");
	}
	
	
	
	
	/**
	 * 方法描述：根据主键找出红包信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.redpacket.getRed_id();
		if(id==null || id.equals("")){
			redpacket = new Redpacket();
		}else{
			redpacket = this.redpacketService.get(id);
		}
		//获取会员级别
	    commpara();
		return goUrl(VIEW);
	}
	
	
   /**
    * 禁用，与启用红包
    * @return
    * @throws Exception
    */ 
	public String updateStateB2C() throws Exception {
		boolean flag = this.redpacketService.updateBatchState(chb_id, state_val, "red_id", "info_state");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("禁用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("启用成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	    return list();	
	}
	
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索优惠券
	 */
	public void searchRedpacket() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 商品id标识
		Map redMap = new HashMap();
		// 搜索名称
		if (s_goods_name != null && !s_goods_name.equals("")) {
			s_goods_name = URLDecoder.decode(s_goods_name, "UTF-8");
			redMap.put("coupon_name", s_goods_name);
		}
		if (sgis != null && !sgis.equals("")) {
			redMap.put("sgis", sgis);
		}
		redMap.put("time", "1");
		redMap.put("info_state", "1");
		redMap = ajaxMap(redMap);
		int totalNum = this.redpacketService.getCount(redMap);
		List list = this.redpacketService.getList(redMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	
	
	/**
	 * 下载红包
	 * @return
	 * @throws Exception
	 */
	public String downRedBag() throws Exception {
		if(this.redpacketService.downRedBag(down_num_s, red_id_s)){
			this.addActionMessage("下载红包成功！");
		}else{
			this.addActionMessage("下载红包失败！");
		}
		return list();
	}
	
	/**
	 * @Method Description : 导出统计信息
	 * @param 
	 * @return return_type
	 */
	public String exportredbagInfo() throws Exception{
		Map pageMap = new HashMap();
		pageMap.put("redkeyId", redkeyId);
		exredbagList = this.exredbagService.getList(pageMap);
		exredbagList = ToolsFuc.replaceList(exredbagList, "");
		if(this.exredbagService.exprotredbagExcel(exredbagList, getResponse())) {
			   this.addActionMessage("数据导出品牌成功！");	
			}else {
			   this.addActionMessage("数据导出品牌成功！");
			}
	   
	    return redbaglist();
	}
	
	/**
	 * @return the RedpacketList
	 */
	public List getRedpacketList() {
		return redpacketList;
	}
	/**
	 * @param redpacketList
	 *  the RedpacketList to set
	 */
	public void setRedpacketList(List redpacketList) {
		this.redpacketList = redpacketList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(redpacket == null){
			redpacket = new Redpacket();
		}
		String id = this.redpacket.getRed_id();
		if(!this.validateFactory.isDigital(id)){
			redpacket = this.redpacketService.get(id);
		}
	}
	/**
	 * @return the redpacket
	 */
	public Redpacket getRedpacket() {
		return redpacket;
	}
	/**
	 * @param Redpacket
	 *            the redpacket to set
	 */
	public void setRedpacket(Redpacket redpacket) {
		this.redpacket = redpacket;
	}

	public String getRedpaceket_id() {
		return redpaceket_id;
	}

	public void setRedpaceket_id(String redpaceket_id) {
		this.redpaceket_id = redpaceket_id;
	}
	
	
	
	
}

