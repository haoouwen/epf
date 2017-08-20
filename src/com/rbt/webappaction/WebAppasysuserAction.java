package com.rbt.webappaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Asysuser;
import com.rbt.service.IAreaService;
import com.rbt.service.IAsysuserService;
import com.rbt.service.IStoreintroService;
import com.rbt.service.IStoreservceService;

/**
 * @author QJY
 * @function 前台门店搜索
 * @date 2015-08-24
 */
@Controller
public class WebAppasysuserAction extends WebAppbaseAction implements Preparable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -107141118733249289L;
	/*******************业务层接口****************/
	@Autowired
	public IAsysuserService asysuserService;
	
	@Autowired
	public IAreaService areaService;
	@Autowired
	public IStoreservceService storeservceService;
	@Autowired
	public IStoreintroService storeintroService;
	
	
	
	//集合
	public List asysuserList;
	public List areaList;
	public List storesevrceList;
	public List storeintroList;
	
	//实体
	public Asysuser asysuser;
	
	//
	public String area_number;
	public String store_id;
	public String area_attr;
	
	
	/**
	 * @author QJY
     * @method 前台门店搜索
     * @date 2015-08-24
	 * @return
	 * @throws Exception
	 */
	public String findShopList()throws Exception{
		Map shopMap = new HashMap();
		if(area_number!=null&&!"".equals(area_number)){
			shopMap.put("area_attr", area_number);
		}
		shopMap.put("state", "0");//启用状态
		shopMap.put("is_del", "0");//无逻辑删除
		shopMap.put("agent_type", "0,2");//0门店,2直营店
		int count = this.asysuserService.getWebCount(shopMap);
		// 分页插件
		shopMap = super.webAppPageTool(count,shopMap);
		asysuserList = this.asysuserService.getWebList(shopMap);
		
		Map map = new HashMap();
		map.put("up_area_id", areaIdStr);//中国顶级ID，10个1
		map.put("area_level", "2");
	    areaList = this.areaService.getList(map);
	    getstorelistinfo();
		return goUrl("mbSearShop");
	}
	public void getstorelistinfo(){
		 //获取门店服务
	    Map storemap = new HashMap();
	    storemap.put("state", "0");//0启用，1禁用
	    storesevrceList=storeservceService.getList(storemap);
	}
	public void getstoreintrolistinfo(){
		 //获取门店服务介绍
	    Map storemap = new HashMap();
	    storemap.put("is_show", "0");//0显示，1不显示
	    storeintroList=storeintroService.getWebList(storemap);
	}
	/**
	 * @author QJY
	 * @date 2015-08-24
	 * @return
	 * @throws Exception
	 */
	public String findShopDetail()throws Exception{
		if(store_id!=null && !"".equals(store_id)){
			asysuser =  this.asysuserService.get(store_id);
			Map map = new HashMap();
			map.put("up_area_id", areaIdStr);//中国顶级ID，10个1
			map.put("area_level", "2");
			map.put("area_number", asysuser.getArea_attr());
		    areaList = this.areaService.getList(map);
		    if(areaList!=null&&areaList.size()>0){
		    	Map areaMap = (Map) areaList.get(0);
		    	if(areaMap!=null&& areaMap.get("area_name")!=null){
		    		area_attr = areaMap.get("area_name").toString();
		    	}
		    }
			
		}
		getstorelistinfo();
		return goUrl("mbSearShopDetail");
	}
	
	
	public void prepare() throws Exception {
		
	}

}
