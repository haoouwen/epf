/*
 
 * Package:com.rbt.action
 * FileName: NavigationAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Goods;
import com.rbt.model.Navigation;
import com.rbt.service.IGoodsService;
import com.rbt.service.INavigationService;
import com.rbt.service.ISalegoodsService;

/**
 * @function 功能 导航列表信息action类
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 13 11:37:53 CST 2015
 */
@Controller
public class NavigationAction extends GoodsBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 导航列表信息对象
	 */
	private Navigation navigation;
	private Goods goods;
	
	/*******************业务层接口****************/
	/*
	 * 导航列表信息业务层接口
	 */
	@Autowired
	private INavigationService navigationService;
	@Autowired
	private IGoodsService goodsService;
	
	/*********************集合*******************/
	/*
	 * 导航列表信息信息集合
	 */
	public List navigationList;
	public List ralateList;//相关商品
	
	/*********************字段*******************/
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String tab_id_s;
	public String nav_id;
	public String sgis;
	public String title_s;//搜索字段
	public String price;//
	public String tab_number_s;
	
		
	/**
	 * 方法描述：返回新增导航列表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增导航列表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(navigation);
		if(super.ifvalidatepass){
			return add();
		}
		this.navigationService.insert(navigation);
		this.addActionMessage("新增导航列表信息成功！");
		this.navigation = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改导航列表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(navigation);
		if(super.ifvalidatepass){
			return view();
		}
		this.navigationService.update(navigation);
		this.addActionMessage("修改导航列表信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除导航列表信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		commonDel();
		return list();
	}
	
	
	
	/**
	 * 方法描述：公用删除导航列表信息信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.navigationService.delete(chb_id);
		if(flag){
			this.addActionMessage("删除导航列表信息成功!");
		}else{
			this.addActionMessage("删除导航列表信息失败!");
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
		pageMap.put("tab_id", tab_id_s);
		if(title_s!=null && !title_s.equals("")){
		   pageMap.put("goods_name", title_s);	
		}
		//搜索价格
		if(price!=null && !price.equals("")){
			pageMap.put("sale_price", price);
		}
		if(cat_attr_s!=null&&!"".equals(cat_attr_s)){
			pageMap.put("cat_attr", cat_attr_s);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.navigationService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		navigationList = this.navigationService.getList(pageMap);
		navigationList =ToolsFuc.replaceList(navigationList, "");
		//获取折扣商品
		getSaleGoods();
		// 获取商品的标签
		getGoodsLabel();
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		return goUrl(INDEXLIST);
	}
	
	
	/**
	 * @author : LJQ
	 * @throws IOException
	 * @date : Feb 27, 2015 10:25:56 AM
	 * @Method Description :AJAX插入导航关联商品
	 */
	public void insertNavGoods() throws IOException {
		
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 兼容火狐文本输出
		response.setContentType("text/html;chartset=UTF-8;");
		int  falsenumber=0;
		PrintWriter out = response.getWriter();
		// 如果列表存在数据则输出，否则则输出空
		// 商品id标识
		if (sgis != null && !"".equals(sgis)&&nav_id!=null&&!"".equals(nav_id)) {
			
			falsenumber=navigationService.insertNavGoods(sgis, nav_id,tab_number_s);
			
		}
		out.write(String.valueOf(falsenumber));
	}
	
	
	/**
	 * @Method Description :批量导航标签排序
	 */

	public String updateSort() throws Exception {
		boolean flag = this.navigationService.updateSort("n_id", "sort_no",chb_id, sort_val);
		if(flag){
			this.addActionMessage("导航列表排序成功");
		}else{
			this.addActionMessage("导航列表排序失败");
		}
		return list();
	}
	
	
	/**
	 * 方法描述：根据主键找出导航列表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.navigation.getN_id();
		if(id==null || id.equals("")){
			navigation = new Navigation();
		}else{
			navigation = this.navigationService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the NavigationList
	 */
	public List getNavigationList() {
		return navigationList;
	}
	/**
	 * @param navigationList
	 *  the NavigationList to set
	 */
	public void setNavigationList(List navigationList) {
		this.navigationList = navigationList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(navigation == null){
			navigation = new Navigation();
		}
		String id = this.navigation.getN_id();
		if(!this.validateFactory.isDigital(id)){
			navigation = this.navigationService.get(id);
		}
	}
	/**
	 * @return the navigation
	 */
	public Navigation getNavigation() {
		return navigation;
	}
	/**
	 * @param Navigation
	 *            the navigation to set
	 */
	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}
}

