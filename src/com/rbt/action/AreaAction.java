/*
 
 * Package:com.rbt.action
 * FileName: AreaAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.AdminBaseAction;
import com.rbt.model.Area;
import com.rbt.service.IAreaService;
import com.rbt.service.ICommparaService;
import com.rbt.common.util.GridTreeUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;

/**
 * @function 功能 地区管理action类
 * @author 创建人 HXK
 * @date 创建日期 Jun 28, 2014
 */
@Controller
public class AreaAction extends AdminBaseAction implements Preparable {

	private static final long serialVersionUID = -573203663791615674L;
	
	/*******************实体层****************/
	private Area area;
	
	/*******************业务层接口****************/
	@Autowired
	private IAreaService areaService;
	@Autowired
	private ICommparaService commparaService;

	/*********************集合******************/
	public List areaList;//地区
	public List areahave_List;// 大区域划分
	
	/*********************字段******************/
	public String lel_level;//地区级别
	public String back_sel_area;//去除保存在隐藏字段中已经被删除的地区编号
	public String back_sel_area_name;//
	public String area_id;//地区ID
	public String iname;//文件路径
	
	/**
	 * 方法描述：返回新增地区页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 选择区域
		Map areamap = new HashMap();
		areamap.put("para_code", "area_type");
		areahave_List = commparaService.getList(areamap);
		// 找出上一级地区级别
		String up_area_id = "";
		if (area.getUp_area_id() != null && !area.getUp_area_id().equals("")) {
			up_area_id = area.getUp_area_id();
			Area menuobj = this.areaService.get(up_area_id);
			int level = Integer.parseInt(menuobj.getArea_level());
			String levelStr = String.valueOf((level + 1));
			area.setArea_level(levelStr);
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增地区
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		area.setArea_id(RandomStrUtil.getNumberRand());
		// 字段验证
		if(commonCheck())return add();
		this.areaService.insert(area);
		this.addActionMessage("新增地区成功,请点击右上角更新缓存!","新增地区成功");
		return INPUT;
	}

	/**
	 * 方法描述：修改地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = area.getArea_id();
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}
		// 字段验证
		if (commonCheck()) {
			return view();
		}
		this.areaService.update(area);
		//处理地区区号
		if(area.getArea_level().equals("3")){
			List listarea=new ArrayList();
			HashMap amap=new HashMap();
			amap.put("up_area_id", area.getArea_id());
			listarea=areaService.getList(amap);
			for(int i=0;i<listarea.size();i++){
				HashMap gareaMap=new HashMap();
				gareaMap=(HashMap)listarea.get(i);
				Area areadown=new Area();
				areadown=areaService.get(gareaMap.get("area_id").toString());
				//获取上级地区ID的区号 复制到所属地区一下的区域的区号
				areadown.setArea_number(area.getArea_number());
				areaService.update(areadown);
			}
			
			
		}
		this.addActionMessage("修改地区成功,请点击右上角更新缓存!","修改地区成功");
		return list();
	}
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 11:16:00 AM
	 * @Method Description：公共数据验证
	 */
	public boolean commonCheck()throws Exception{
		// 字段验证
		super.commonValidateField(area);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
	}

	/**
	 * 方法描述：删除地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String areaid = this.area_id;
		areaid = areaid.replace(" ", "");
		this.areaService.Recursive(areaid);
		
		//去除保存在隐藏字段中已经被删除的地区编号 hong注释
		if (back_sel_area != null) {
			int i = back_sel_area.indexOf(areaid);
			if (i > 0) {
				back_sel_area = back_sel_area.substring(0, i - 1);
			}
		}
		this.addActionMessage("删除地区成功,请点击右上角更新缓存!","删除地区成功");
		return list();
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
		map.put("up_area_id", parentId);
		try {
			list = this.areaService.getList(map);
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
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map areaMap = new HashMap();
		areaList = this.areaService.getList(areaMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：地区批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSort() throws Exception {
		boolean flag = this.areaService.updateSort("area_id", "sort_no",
				this.area.getArea_id(), this.area.getSort_no());
		if (flag) {
			this.addActionMessage("地区排序成功");
		} else {
			this.addActionMessage("地区排序失败");
		}
		return list();
	}

	/**
	 * 方法描述：根据主键找出地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		// 地区区域
		areahave_List = commparaService.getCommparaList("area_type");
		// 获取地区ID
		String id = this.area_id;
		if (id == null || id.equals("")) {
			area = new Area();
		} else {
			area = this.areaService.get(id);
		}
		lel_level = area.getArea_level();
		return goUrl(VIEW);
	}

	/**
	 * @Method Description : 导出统计信息
	 * @author: HXK
	 * @date : Apr 25, 2014 2:47:20 PM
	 * @param 
	 * @return return_type
	 */
	public String exportAreaInfo() throws Exception{
		this.areaService.exportAreaExcel();
	    this.addActionMessage("数据导出地区成功!");
	    return list();
	}
	
	/**
	 * @Method Description :
	 * @author : XBY
	 * @throws Exception
	 * @date : Sep 16, 2014 1:27:30 PM
	 */
	public String importAreaInfo() throws Exception {
		if (iname == null || "".equals(iname)) {
			this.addActionMessage("请选择要导入的文件!");
		} else {
			String rootPath = PropertiesUtil.getRootpath();// 获取项目的根目录
			iname = rootPath + "" + iname;
			boolean flag = this.areaService.importArea(iname);
			if (flag) {
				this.addActionMessage("导入成功!");
			} else {
				this.addActionMessage("导入失败!请检查文件格式。");
			}

		}
		return list();
	}
	
	
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (area == null) {
			area = new Area();
		}
		String id = this.area.getArea_id();
		if (!ValidateUtil.isRequired(id)) {
			area = this.areaService.get(id);
		}
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
