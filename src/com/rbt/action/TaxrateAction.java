/*
 
 * Package:com.rbt.action
 * FileName: TaxrateAction.java 
 */
package com.rbt.action;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.BaseAction;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Taxrate;
import com.rbt.service.ITaxrateService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 税率信息action类
 * @author 创建人 ZMS
 * @date 创建日期 Tue Aug 18 16:12:24 CST 2015
 */
@Controller
public class TaxrateAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 税率信息对象
	 */
	private Taxrate taxrate;
	/*******************业务层接口****************/
	/*
	 * 税率信息业务层接口
	 */
	@Autowired
	private ITaxrateService taxrateService;
	/*********************集合*******************/
	/*
	 * 税率信息信息集合
	 */
	public List taxrateList;
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String back_sel_taxrate;//去除保存在隐藏字段中已经被删除的地区编号
	public String back_sel_taxrate_name;//
	public String tax_id;//税率ID

	/**
	 * 方法描述：返回新增税率信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 找出上一级税率级别
		String up_tax_id = "";
		if (taxrate.getUp_tax_id() != null && !taxrate.getUp_tax_id().equals("")) {
			up_tax_id = taxrate.getUp_tax_id();
			Taxrate taxobj = this.taxrateService.get(up_tax_id);
			int level=0;
			if(taxobj!=null){
				level = Integer.parseInt(taxobj.getTax_level());
			}
			String levelStr = String.valueOf((level+1));
			taxrate.setTax_level(levelStr);
		}
		
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增税率信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 生成十位随机数的字符串
		String charNum = RandomStrUtil.getNumberRand(); 
	        taxrate.setTax_id(charNum);
		if(commonCheck())return add();
		 this.taxrateService.insert(taxrate);
		this.addActionMessage("新增税率成功,请点击右上角更新缓存!","新增税率成功");
		return INPUT;
	}
 
	/**
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck(){
		//验证税率名称是否为空
		if(ValidateUtil.isRequired(taxrate.getTax_name())){
			this.addFieldError("taxrate.tax_name", "税率名称不能为空");
		}
		//验证税率号是否为空
        if(ValidateUtil.isRequired(taxrate.getTax_number())){
        	this.addFieldError("taxrate.tax_number", "税率号不能为空！");
        }		
        //验证英文名称是否为空
        if(ValidateUtil.isRequired(taxrate.getTax_en_name())){
        	this.addFieldError("taxrate.tax_en_name", "税率英文名称不能为空!");
        }
        //验证税率id是否为空
        if(ValidateUtil.isRequired(taxrate.getTax_id())){
        	this.addFieldError("txrate.tax_id", "税率id不能为空！");
        }
		// 字段验证
		super.commonValidateField(taxrate);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}
	
	/**
	 * 方法描述：修改税率信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = taxrate.getTax_id();
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}
		// 字段验证
		if (commonCheck()) {
			return view();
		}
		this.taxrateService.update(taxrate);
		this.addActionMessage("修改税率成功,请点击右上角更新缓存!","修改税率成功");
		return list();
	}
	/**
	 * 方法描述：删除地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String taxid = this.tax_id;
		taxid = taxid.replace(" ", "");
		this.taxrateService.Recursive(taxid);
		//去除保存在隐藏字段中已经被删除的地区编号 hong注释
		if (back_sel_taxrate != null) {
			int i = back_sel_taxrate.indexOf(taxid);
			if (i > 0) {
				back_sel_taxrate = back_sel_taxrate.substring(0, i - 1);
			}
		}
		this.addActionMessage("删除税率成功,请点击右上角更新缓存!","删除地区成功");
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
		pageMap.put("sort_no_asc", "asc");
		taxrateList = this.taxrateService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：通过父ID获取子分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String childList() throws Exception {
		Map map = new HashMap();
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String parentId = request.getParameter("pId");
		List list = new ArrayList();
		map.put("up_tax_id", parentId);
		map.put("sort_no_asc", "asc");
		try {
			list = this.taxrateService.getList(map);
			// 调用工具类的方法得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list);
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("懒加载子串:" + jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方法描述：地区批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception {
		boolean flag = this.taxrateService.updateSort("tax_id", "sort_no",
				this.taxrate.getTax_id(), this.taxrate.getSort_no());
		if (flag) {
			this.addActionMessage("税率排序成功");
		} else {
			this.addActionMessage("税率排序失败");
		}
		return list();
	}
	/**
	 * @Method Description : 导出统计信息
	 * @author: HXK
	 * @date : Apr 25, 2014 2:47:20 PM
	 * @param 
	 * @return return_type
	 * @throws Exception 
	 */
	public String exportTaxInfo() throws Exception {
		if(this.taxrateService.exportTaxExcel()){
			this.addActionMessage("数据导出税率成功!");
		}else{
			this.addActionMessage("数据导出税率失败!");
		}
		return list();
	}
	/**
	 * 方法描述：根据主键找出税率信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.tax_id;
		if(id==null || id.equals("")){
			taxrate = new Taxrate();
		}else{
			taxrate = this.taxrateService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the TaxrateList
	 */
	public List getTaxrateList() {
		return taxrateList;
	}
	/**
	 * @param taxrateList
	 *  the TaxrateList to set
	 */
	public void setTaxrateList(List taxrateList) {
		this.taxrateList = taxrateList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(taxrate == null){
			taxrate = new Taxrate();
		}
		String id = this.taxrate.getTax_id();
		if(!this.validateFactory.isDigital(id)){
			taxrate = this.taxrateService.get(id);
		}
	}
	
	/**
	 * @return the taxrate
	 */
	public Taxrate getTaxrate() {
		return taxrate;
	}
	/**
	 * @param Taxrate
	 *            the taxrate to set
	 */
	public void setTaxrate(Taxrate taxrate) {
		this.taxrate = taxrate;
	}
}

