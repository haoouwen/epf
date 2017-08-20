package com.rbt.webaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Asysuser;
import com.rbt.model.Storeservce;
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
public class WebasysuserAction extends WebbaseAction implements Preparable {

	
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
	public Storeservce storeservce;
	
	//
	public String area_number;
	public String store_id;
	public String area_attr;
	public String storesevice_s;
	
	/**
	 * @author QJY
     * @method 前台门店搜索
     * @date 2015-08-24
	 * @return
	 * @throws Exception
	 */
	public String findShopIndex()throws Exception{
		Map map = new HashMap();
		map.put("up_area_id", areaIdStr);//中国顶级ID，10个1
		map.put("area_level", "2");
	    areaList = this.areaService.getList(map);
	    getstoreintrolistinfo();
		return goUrl("findShopIndex");
	}
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
		shopMap.put("agent_type", "0,2");//门店
		String storestr="";
		if(storesevice_s!=null&&!"".equals(storesevice_s)){
			storesevice_s=storesevice_s.replaceAll(" ", "");
			String[]storesevice_ss=storesevice_s.split(",");
			
			if(storesevice_ss.length>0){
				for(int i=0;i<storesevice_ss.length;i++){
					if(i==0){
						storestr=" INSTR(store_servce,"+storesevice_ss[i].toString()+")>0";
					}else {
						storestr+=" AND INSTR(store_servce,"+storesevice_ss[i].toString()+")>0";
					}
				}
			}
		}
		if(storestr!=null&&!"".equals(storestr)){
			shopMap.put("store_servce_str", storestr);
		}
		int count = this.asysuserService.getWebCount(shopMap);
		shopMap = super.webPageTool(count,shopMap);
		asysuserList = this.asysuserService.getWebList(shopMap);
		Map map = new HashMap();
		map.put("up_area_id", areaIdStr);//中国顶级ID，10个1
		map.put("area_level", "2");
	    areaList = this.areaService.getList(map);
	    getstorelistinfo();
		return goUrl("findShopList");
	}
	/**
	 * @author QJY
	 * @date 2015-08-24
	 * @return
	 * @throws Exception
	 */
	public String findShopDetails()throws Exception{
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
		    String detai_area="";
		    if(!ValidateUtil.isRequired(area_attr)){
		    	 detai_area=area_attr+AreaFuc.getAreaNameByMap(asysuser.getDetai_area_attr())+asysuser.getAddress();
		    }else {
		    	 detai_area=AreaFuc.getAreaNameByMap(asysuser.getDetai_area_attr())+asysuser.getAddress();
			}
		    if(detai_area!=null&&!"".equals(detai_area)){
		    	asysuser.setAddress(detai_area);
		    }
		}
		getstorelistinfo();
		return goUrl("findShopDetails");
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
	public void prepare() throws Exception {
		
	}

}
