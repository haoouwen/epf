/*
 * Package:com.rbt.action
 * FileName: FloorinfoAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Floorinfo;
import com.rbt.model.Floormark;
import com.rbt.model.Floormodel;
import com.rbt.model.Goodfloormark;
import com.rbt.service.ICategoryService;
import com.rbt.service.IFloorinfoService;
import com.rbt.service.IFloormarkService;
import com.rbt.service.IGoodfloormarkService;
/**
 * @function 功能 楼层管理action类
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:33:52 CST 2015
 */
@Controller
public class FloorinfoAction extends GoodsBaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*******************实体层********************/
	/*
	 * 楼层管理对象
	 */
	public Floorinfo floorinfo;
	public Goodfloormark goodfloormark;
	public Floormodel floormodel;
	private Floormark floormark;
	/*******************业务层接口****************/
	/*
	 * 楼层管理业务层接口
	 */
	@Autowired
	private IFloorinfoService floorinfoService;
	@Autowired
	private IGoodfloormarkService goodfloormarkService;
	@Autowired
	private IFloormarkService floormarkService;
	@Autowired
	private ICategoryService categoryService;
	/*********************集合*******************/
	/*
	 * 楼层管理信息集合
	 */
	public List floorinfoList;
	public List goodfloormarkList;
	public List catList;//分类
	public List floormarkList;
	public List fmList;//
	public List goodsList;
	//二级分类
	public List<Map> senCat_attrList;
	//广告的tab1标签名称
	public String advfoolmark_tab;
	public List cat_attr_list;
	/*********************字段*******************/
	
	public String chb_id; //批量排序ID串
	public String sort_val; //批量排序值串
	public String state_val;//状态值
	public String f_tag_s;//搜索楼层标志字段
	public String f_name_s;//搜索楼层名称字段
	public String fgoodsmark="";//复选框的值
	public String floormark_str;
	public String goods_relate_str;
	public String tab0_00_img="";
		
	/**
	 * 方法描述：返回新增楼层管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//调用公共数据获取方法
		AttainValue("");
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增楼层管理
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(comm()){
			return add();
		}
		//添加的时候返回主键id
		String f_id="";
		f_id=this.floorinfoService.insertGetPk(floorinfo);
		//判断checkbox是否为空！
		if(!ValidateUtil.isRequired(fgoodsmark)){
			Map  fMap=new HashMap();
			fMap.put("f_id", f_id);
			fMap.put("fm_id", fgoodsmark);
			this.floormarkService.updateCheckbox(fMap);
		}
		this.addActionMessage("新增楼层管理成功！");
		this.floorinfo = null;	
	    return list();
	}
	/**
	 * 方法描述：修改楼层管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(comm()){
			return view();
		}
		//修改checkbox的值
		String id = this.floorinfo.getF_id();
			Map flMap=new HashMap();
			flMap.put("f_id","0");
			flMap.put("ff_id",id);
			floormarkService.updateInfo(flMap);
		//修改楼层管理信息
		this.floorinfoService.update(floorinfo);
		//判断checkbox是否为空
		if(!ValidateUtil.isRequired(fgoodsmark)){
			Map  fMap=new HashMap();
			fMap.put("f_id",floorinfo.getF_id());
			fMap.put("fm_id", fgoodsmark);
			this.floormarkService.updateCheckbox(fMap);	
		}
		this.addActionMessage("修改楼层管理成功！");
		return list();
	}
	/**
	 * 方法描述：删除楼层管理信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		    commonDel();
		    return list();
	}
	
	/**
	 * 方法描述：公用删除楼层管理信息
	 * @return
	 * @throws Exception
	 */
	public void commonDel(){
		boolean flag = this.floorinfoService.deltetallinfo(chb_id);
		if(flag){
			this.addActionMessage("删除楼层管理信息成功!");
		}else{
			this.addActionMessage("删除楼层管理信息失败!");
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
        //验证楼层标志字段
		if(f_tag_s!=null &&!f_tag_s.equals("")){
        	pageMap.put("f_tag", f_tag_s);
        }		
        //验证楼层名称字段
        if(f_name_s!=null &&!f_name_s.equals("")){
        	pageMap .put("f_name", f_name_s);
        }
		//根据页面提交的条件找出信息总数
		int count=this.floorinfoService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		floorinfoList = this.floorinfoService.getList(pageMap);
		floorinfoList=ToolsFuc.replaceList(floorinfoList,"");
		return goUrl(INDEXLIST);
	}
	/**
	 * @author :ZMS
	 * @Method Description : 公共数据验证方法
	 */
	public boolean comm(){
		// 验证楼层名称不能为空
		if (ValidateUtil.isRequired(floorinfo.getF_name())) {
			this.addFieldError("floorinfo.f_name", "楼层名字不能为空");
		}
		//验证楼层标志不能为空
		if (ValidateUtil.isRequired(floorinfo.getF_tag())) {
			this.addFieldError("floorinfo.f_tag", "楼层标志不能为空");
		}
		//验证楼层广告标签不鞥为空！
		if(ValidateUtil.isRequired(floorinfo.getF_admark())){
			this.addFieldError("floorinfo.f_admark","广告标签不能为空！");
		}
		if(ValidateUtil.isRequired(fgoodsmark)){
			this.addFieldError("fgoodsmark","tab[1-4]标签不能为空！");
		}
		
		super.commonValidateField(floorinfo);
		if (super.ifvalidatepass) {
			return true;
		}
		return false;
		
	}
	/**
	 * @author :ZMS
	 * @Method Description:公共数据获取的值方法
	 */
	public void AttainValue(String f_id){
		
		// 获取下拉框的值
		Map catmap = new HashMap();
		catmap.put("cat_level", "1");
		catmap.put("module_type", "goods");
		catList = categoryService.getList(catmap);
		//广告标签图片
		Map flooMap=new HashMap();
		flooMap.put("fm_type", "1");
		floormarkList=this.floormarkService.getList(flooMap);
		//商品标签
		Map fMap=new HashMap();
		fMap.put("fm_type", "0");
		fmList=this.floormarkService.getList(fMap);
	}
	
	/**
	 * @author : ZMS
	 * @Method Description :显示
	 */
	public String updateIsshow() throws Exception {
		updateshow();
		return list();
	}

	/**
	 * @author : ZMS
	 * @Method Description :隐藏
	 */
	public String updateUnshow() throws Exception {
		updateshow();
		return list();
	}
	
	/**
	 * @author : ZMS
	 * @Method Description :显示不显示公共方法
	 */
	public void updateshow(){
		boolean flag = this.floorinfoService.updateBatchState(chb_id, state_val, "f_id", "f_state");
		if(flag){
			if (state_val.equals("0")) {
				this.addActionMessage("显示楼层成功");
			} else if (state_val.equals("1")) {
				this.addActionMessage("隐藏楼层成功");
			}
		}else{
			this.addActionMessage("操作楼层失败");
		}
	}
	
	/**
	 * 方法描述：根据主键找出楼层管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.floorinfo.getF_id();
		if(id==null || id.equals("")){
			floorinfo = new Floorinfo();
		}else{
			floorinfo = this.floorinfoService.get(id);
		}
		
		//调用公共数据获取值得方法
		AttainValue(floorinfo.getF_id());
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：根据主键找出楼层管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyview() throws Exception {
		String id = this.floorinfo.getF_id();
		if(id==null || id.equals("")){
			floorinfo = new Floorinfo();
		}else{
			floorinfo = this.floorinfoService.get(id);
		}
		getFloorModeList(floorinfo.getF_id());
		//获取二级分类
		senCat_attrList=new ArrayList();
		HashMap catMap=new HashMap();
		catMap.put("up_cat_id", floorinfo.getCat_attr());
		senCat_attrList=categoryService.getAreaCategoryList(catMap);
		//获取广告tab0标签
		floormark=this.floormarkService.get(floorinfo.getF_admark());
		if(floormark!=null){
			advfoolmark_tab=floormark.getFm_name();
		}
		//获取其他商品tab的标签列表
		floormarkList=new ArrayList();
		HashMap fmarkMap=new HashMap();
		//标签类型 0：商品。1图片
		fmarkMap.put("fm_type", "0");
		fmarkMap.put("f_id", floorinfo.getF_id());
		floormarkList=floormarkService.getList(fmarkMap);
		
		cat_attr_list=new ArrayList();
		if(senCat_attrList!=null&&senCat_attrList.size()>0){
			if(floormodel.getCat_attr_list()!=null){
				String cats[]=floormodel.getCat_attr_list().split("\\|");
				for(String c:cats){
					for(Map cmap :senCat_attrList){
						if(cmap!=null&&cmap.get("cat_id")!=null&&c.trim().equals(cmap.get("cat_id").toString())){
							cat_attr_list.add(cmap);
							break;
						}
					}
				}
			}
		}
		//获取折扣商品
		getSaleGoods();
		// 获取商品的标签
		getGoodsLabel();
		// 获取商品品牌
		getALLBrandList();
		//获取贸易方式
		getTradeWay();
		return goUrl("modifyinfo");
	}
	
	/**
	 * @Method Description :获取编辑对象
	 * @author: HXK
	 * @date : Aug 13, 2015 4:19:28 PM
	 * @param 
	 * @return return_type
	 */
     private void getFloorModeList(String  f_id){
    	  floormodel=new Floormodel();
          goodfloormarkList=new ArrayList();
          goodsList=new ArrayList();
          if(f_id!=null&&!"".equals(f_id)){
        	  HashMap gfMap=new HashMap();
              gfMap.put("f_id", f_id);
              gfMap.put("gm_type", "1,2");
              goodfloormarkList=this.goodfloormarkService.getList(gfMap);
              if(goodfloormarkList!=null&&goodfloormarkList.size()>0){
            	  for(int i=0;i<goodfloormarkList.size();i++){
            		  HashMap getfMap=new HashMap();
            		  getfMap=(HashMap)goodfloormarkList.get(i);
            		  if(getfMap!=null&&getfMap.get("gm_position")!=null){
            			  String gm_position=getfMap.get("gm_position").toString();
            			  String cat_attr="",img_path="",gm_url="",gm_name="";
            			  if(getfMap.get("cat_attr")!=null){
            				  cat_attr=getfMap.get("cat_attr").toString();
        				  }
            			  if(getfMap.get("img_path")!=null){
            				  img_path=getfMap.get("img_path").toString();
        				  }
        				  if(getfMap.get("gm_url")!=null){
        					  gm_url=getfMap.get("gm_url").toString();
        				  }
        				  if(getfMap.get("gm_name")!=null){
        					  gm_name=getfMap.get("gm_name").toString();
        				  }
            			  if(gm_position.equals(Constants.pos_tab0_cat)){
            				  floormodel.setCat_attr_list(cat_attr);
            				  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00)){
        					  floormodel.setTab0_pos1_img(img_path);
        					  floormodel.setTab0_pos1_url(gm_url);
            				  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_01)){
        					  floormodel.setImg_tab0_0_1(img_path);
        					  floormodel.setUrl_tab0_0_1(gm_url);
        					  floormodel.setTitle_tab0_0_1(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_02)){
        					  floormodel.setImg_tab0_0_2(img_path);
        					  floormodel.setUrl_tab0_0_2(gm_url);
        					  floormodel.setTitle_tab0_0_2(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_03)){
        					  floormodel.setImg_tab0_0_3(img_path);
        					  floormodel.setUrl_tab0_0_3(gm_url);
        					  floormodel.setTitle_tab0_0_3(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_04)){
        					  floormodel.setImg_tab0_0_4(img_path);
        					  floormodel.setUrl_tab0_0_4(gm_url);
        					  floormodel.setTitle_tab0_0_4(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_05)){
        					  floormodel.setImg_tab0_0_5(img_path);
        					  floormodel.setUrl_tab0_0_5(gm_url);
        					  floormodel.setTitle_tab0_0_5(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_06)){
        					  floormodel.setImg_tab0_0_6(img_path);
        					  floormodel.setUrl_tab0_0_6(gm_url);
        					  floormodel.setTitle_tab0_0_6(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_07)){
        					  floormodel.setImg_tab0_0_7(img_path);
        					  floormodel.setUrl_tab0_0_7(gm_url);
        					  floormodel.setTitle_tab0_0_7(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_08)){
        					  floormodel.setImg_tab0_0_8(img_path);
        					  floormodel.setUrl_tab0_0_8(gm_url);
        					  floormodel.setTitle_tab0_0_8(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_09)){
        					  floormodel.setImg_tab0_0_9(img_path);
        					  floormodel.setUrl_tab0_0_9(gm_url);
        					  floormodel.setTitle_tab0_0_9(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_00_10)){
        					  floormodel.setImg_tab0_0_10(img_path);
        					  floormodel.setUrl_tab0_0_10(gm_url);
        					  floormodel.setTitle_tab0_0_10(gm_name);
        					  tab0_00_img=tab0_00_img+img_path+",";
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_01)){
        					  floormodel.setImg_tab0_1(img_path);
        					  floormodel.setUrl_tab0_1(gm_url);
        					  floormodel.setTitle_tab0_1(gm_name);
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_02)){
        					  floormodel.setImg_tab0_2(img_path);
        					  floormodel.setUrl_tab0_2(gm_url);
        					  floormodel.setTitle_tab0_2(gm_name);
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_03)){
        					  floormodel.setImg_tab0_3(img_path);
        					  floormodel.setUrl_tab0_3(gm_url);
        					  floormodel.setTitle_tab0_3(gm_name);
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_04)){
        					  floormodel.setImg_tab0_4(img_path);
        					  floormodel.setUrl_tab0_4(gm_url);
        					  floormodel.setTitle_tab0_4(gm_name);
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_05)){
        					  floormodel.setImg_tab0_5(img_path);
        					  floormodel.setUrl_tab0_5(gm_url);
        					  floormodel.setTitle_tab0_5(gm_name);
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_06)){
        					  floormodel.setImg_tab0_6(img_path);
        					  floormodel.setUrl_tab0_6(gm_url);
        					  floormodel.setTitle_tab0_6(gm_name);
        					  continue;
            			  }
            			  if(gm_position.equals(Constants.pos_tab0_07)){
        					  floormodel.setImg_tab0_7(img_path);
        					  floormodel.setUrl_tab0_7(gm_url);
        					  floormodel.setTitle_tab0_7(gm_name);
        					  continue;
            			  }
            		  }
            	  }
            	  if(tab0_00_img!=null&&!"".equals(tab0_00_img)){
            		  tab0_00_img=tab0_00_img.substring(0,tab0_00_img.length()-1);
            	  }
            	  goodsFloorMarkList(f_id);
              }
          }
     }
	//获取标签商品列表
	private void goodsFloorMarkList(String f_id){
	   //获取标签商品
  	  HashMap gMap=new HashMap();
  	  gMap.put("f_id", f_id);
  	  gMap.put("gm_type", "0");
  	  goodsList=this.goodfloormarkService.getGoodsFloorList(gMap);
  	  goodsList=ToolsFuc.replaceList(goodsList, "");
	}
	
	/**
	 * @Method Description :
	 * @author: HXK
	 * @date : Aug 12, 2015 8:43:43 PM
	 * @param 
	 * @return return_type
	 * @throws Exception 
	 */
     public String updatedata() throws Exception{
    	 if(floorinfo!=null&&floormodel!=null){
    		 floorinfoService.updateFloorInfo(floorinfo,floormodel,goods_relate_str,floormark_str);
    	 }
    	 this.addActionMessage("保存楼层数据成功!");
    	 return modifyview();
     }
	
	/**
	 * @return the FloorinfoList
	 */
	public List getFloorinfoList() {
		return floorinfoList;
	}
	/**
	 * @param floorinfoList
	 *  the FloorinfoList to set
	 */
	public void setFloorinfoList(List floorinfoList) {
		this.floorinfoList = floorinfoList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(floorinfo == null){
			floorinfo = new Floorinfo();
		}
		String id = this.floorinfo.getF_id();
		if(!this.validateFactory.isDigital(id)){
			floorinfo = this.floorinfoService.get(id);
		}
		if(floormodel == null){
			floormodel = new Floormodel();
		}
	}
	/**
	 * @return the floorinfo
	 */
	public Floorinfo getFloorinfo() {
		return floorinfo;
	}
	/**
	 * @param Floorinfo
	 *            the floorinfo to set
	 */
	public void setFloorinfo(Floorinfo floorinfo) {
		this.floorinfo = floorinfo;
	}

	public Floormodel getFloormodel() {
		return floormodel;
	}

	public void setFloormodel(Floormodel floormodel) {
		this.floormodel = floormodel;
	}
	
	
	
}

