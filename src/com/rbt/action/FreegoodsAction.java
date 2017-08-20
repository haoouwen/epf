/*
 * Package:com.rbt.action
 * FileName: FreegoodsAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.BaseAction;

import com.rbt.function.ToolsFuc;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Freegoods;
import com.rbt.service.IFreegoodsService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 赠品action类
 * @author 创建人 ZMS
 * @date 创建日期 Tue Sep 29 17:12:09 CST 2015
 */
@Controller
public class FreegoodsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 赠品对象
	 */
	private Freegoods freegoods;
	/*******************业务层接口****************/
	/*
	 * 赠品业务层接口
	 */
	@Autowired
	private IFreegoodsService freegoodsService;
	
	/*********************集合*******************/
	/*
	 * 赠品信息集合
	 */
	public List freegoodsList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String s_goods_name;//搜索名称
	public String sgis;//搜索ID
	public String title_s;

	
		
	/**
	 * 方法描述：返回新增赠品页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增赠品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(commonCheck()){
			return add();
		}
	     
		// 生成十位随机数的字符串
		String charNum = RandomStrUtil.getNumberRand();
		freegoods.setFg_no("ZP"+charNum);
		//随机生成十位数唯一键
		String key_no = RandomStrUtil.getNumberRand();
		freegoods.setKey_no(key_no);
		
		this.freegoodsService.insert(freegoods);
		this.addActionMessage("新增赠品成功！");
		this.freegoods = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改赠品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(commonCheck()){
			return view();
		}
	
		this.freegoodsService.update(freegoods);
		this.addActionMessage("修改赠品成功！");
		return list();
	}
	
	/**
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck() {
		cat_attr = cat_attr.replace(" ", "");
		this.freegoods.setCat_attr(cat_attr);
		if(ValidateUtil.isRequired(freegoods.getFg_name())){
			this.addFieldError("freegoods.fg_name", "赠品名称不能为空！");
		}
		if(ValidateUtil.isRequired(freegoods.getFg_number())){
			this.addFieldError("freegoods.fg_number", "赠品数量不能为空！");
		}
		if(ValidateUtil.isRequired(freegoods.getFg_price())){
			this.addFieldError("freegoods.fg_price", "赠品价格不能为空！");
		}
		if(ValidateUtil.isRequired(freegoods.getImg_path())){
			this.addFieldError("freegoods.img_path", "赠品图片不能为空！");
		}
		super.commonValidateField(freegoods);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}
	/**
	 * 方法描述：删除赠品信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	/**
	 * 方法描述：公用删除赠品信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.freegoodsService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除赠品信息成功!");
		}else{
			this.addActionMessage("删除赠品信息失败!");
		}
	}
	/**
	 *
	 * @date : May 3, 2014 4:48:33 PM
	 * @Method Description :启用
	 */
	public String updateState() throws Exception {
		updatestate();
		return list();
	}

	/**
	 *
	 * @date : May 3, 2014 4:48:43 PM
	 * @Method Description :禁用
	 */
	public String updateNoState() throws Exception {
		updatestate();
		return list();
	}
	
	/**
	 * @author : 
	 * @date : May 3, 2014 4:48:54 PM
	 * @Method Description :启用/禁用公共方法
	 */
	public void updatestate(){
		boolean flag = this.freegoodsService.updateBatchState(chb_id, state_val, "fg_id", "fg_state");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("启用成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("禁用成功");
			}
		}else{
			this.addActionMessage("操作失败");
		}
	
	}
	
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception { 
		boolean flag = this.freegoodsService.updateSort("fg_id", "fg_sort",chb_id, sort_val);
		if(flag){
			this.addActionMessage("赠品排序成功");
		}else{
			this.addActionMessage("赠品排序失败");
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
		if(title_s!=null &&!title_s.equals("")){
			pageMap.put("fg_name", title_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.freegoodsService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		freegoodsList = this.freegoodsService.getList(pageMap);
		freegoodsList = ToolsFuc.replaceList(freegoodsList, "");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出赠品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.freegoods.getFg_id();
		if(id==null || id.equals("")){
			freegoods = new Freegoods();
		}else{
			freegoods = this.freegoodsService.get(id);
		}
		viewCat(freegoods.getCat_attr());
		return goUrl(VIEW);
	}
	
	/**
	 * @author : XBY
	 * @throws IOException
	 * @date : Feb 27, 2014 10:25:56 AM
	 * @Method Description :AJAX搜索赠品
	 */
	public void searchGoods() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		// 商品id标识
		Map freeMap = new HashMap();
		// 搜索商品名称
		if (s_goods_name != null && !s_goods_name.equals("")) {
			s_goods_name = URLDecoder.decode(s_goods_name, "UTF-8");
			freeMap.put("fg_name", s_goods_name);
		}
		if (sgis != null && !sgis.equals("")) {
			freeMap.put("sgis", sgis);
		}
		freeMap.put("fg_state", "0");
		freeMap = ajaxMap(freeMap);
		int totalNum = this.freegoodsService.getCount(freeMap);
		List list = this.freegoodsService.getList(freeMap);
		list = ToolsFuc.replaceList(list, "");
		String jsonStr = pageList(list, totalNum);
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}
	
	/**
	 * @return the FreegoodsList
	 */
	public List getFreegoodsList() {
		return freegoodsList;
	}
	/**
	 * @param freegoodsList
	 *  the FreegoodsList to set
	 */
	public void setFreegoodsList(List freegoodsList) {
		this.freegoodsList = freegoodsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(freegoods == null){
			freegoods = new Freegoods();
		}
		String id = this.freegoods.getFg_id();
		if(!this.validateFactory.isDigital(id)){
			freegoods = this.freegoodsService.get(id);
		}
	}
	/**
	 * @return the freegoods
	 */
	public Freegoods getFreegoods() {
		return freegoods;
	}
	/**
	 * @param Freegoods
	 *            the freegoods to set
	 */
	public void setFreegoods(Freegoods freegoods) {
		this.freegoods = freegoods;
	}
}

