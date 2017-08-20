/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CategoryService.java 
 */
package com.rbt.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.common.util.DateUtil;
import com.rbt.dao.ICategoryDao;
import com.rbt.model.Category;
import com.rbt.service.IArticleService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IComboService;
import com.rbt.service.IDirectsellService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGroupgoodsService;
import com.rbt.service.IPointsgoodsService;
import com.rbt.service.ISpikegoodsService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 分类信息表Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jul 12 13:04:58 CST 2014
 */
@Service
public class CategoryService extends GenericService<Category,String> implements ICategoryService {

	/*
	 * 分类信息表Dao层接口
	 */
    ICategoryDao categoryDao;
	@Autowired
	public CategoryService(ICategoryDao categoryDao) {
		super(categoryDao);
		this.categoryDao = categoryDao;
	}
	@Autowired
	ISysmoduleService sysmoduleService;
	@Autowired
	IGoodsService goodsService;//商品
	@Autowired
	IArticleService articleService;//文章
	@Autowired
	IGroupgoodsService groupgoodsService;//团购
	@Autowired
	IPointsgoodsService pointsgoodsService;//积分
	@Autowired
	ISpikegoodsService spikegoodsService;//秒杀、
	@Autowired
	IDirectsellService directsellService;//预售
	@Autowired
	IComboService comboService;//套餐
	
	
	
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getCategoryIndexList(java.util.Map)
	 */
	public List getAreaCategoryList(Map map) {
		return this.categoryDao.getAreaCategoryList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getCategoryIndexList(java.util.Map)
	 */
	public List getTwoAreaCategoryList(Map map) {
		return this.categoryDao.getTwoAreaCategoryList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getCategoryIndexList(java.util.Map)
	 */
	public List getCategoryIndexList(Map map) {
		return this.categoryDao.getCategoryIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getWebCategroyList(java.util.Map)
	 */
	public List getWebCategroyList(Map map) {
		return this.categoryDao.getWebCategroyList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getAll()
	 */
	public List getAll() {
		return this.categoryDao.getAll();
	}

	public void updateDisplay(List list) {
		this.categoryDao.updateDisplay(list);
	}




	public List getDeleteList(Map map) {
		return this.categoryDao.getDeleteList(map);
	}


	/**
	 * @MethodDescribe 方法描述 创建一个递归方法用于批量删除
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 15, 2014 4:33:29 PM
	 */
	public boolean Recursive(String id) {
		String cat_id = "";
		Map map = new HashMap();
		map.put("up_cat_id", id);
		// 删除传进来的ID行
		categoryDao.delete(id);
		// 通过对传进来的ID进行查询
		List categoryList = categoryDao.getList(map);
		// 若存在有子ID
		if (categoryList != null && categoryList.size() > 0) {
			for (int i = 0; i < categoryList.size(); i++) {
				Map upmap = (HashMap) categoryList.get(i);
				// 如果通过上一级获取到本级的ID不为空，则进入递归继续查找并删除
				if (upmap != null && upmap.get("cat_id") != null
						&& !upmap.get("cat_id").equals("")) {
					cat_id = upmap.get("cat_id").toString();
					// 进入递归循环
					Recursive(cat_id);
				}
			}
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @author : LHY
	 * @Method Description : 递归调用删除数据
	 */
	public void recuDelete(String id){
		if(id==null || id.equals(""))
			return ;
		String chhid_id="";
		List list=new ArrayList();
		String[] str_id=id.split(",");
		for (int i = 0; i < str_id.length; i++) {
			if(str_id[i].trim().equals("")){
				return;
			}
			Map chiMap=new HashMap();
			chiMap.put("up_cat_id",str_id[i]);
			//获取所有上级id为当前id的栏目
			List chi_list=this.categoryDao.getDeleteList(chiMap);
			System.out.println(chi_list);
			if(chi_list!=null && chi_list.size()>0){
				Map listMap=null;
				for (int j = 0; j < chi_list.size(); j++) {
					listMap=(HashMap) chi_list.get(j);
					if(listMap.get("cat_id_str")!=null){
						chhid_id+=listMap.get("cat_id_str")+",";
					}
				}
			}
		}
		//判断是否最后一个字符是否为逗号，是则删除
		if(chhid_id.lastIndexOf(",")>0){
			chhid_id=chhid_id.substring(0, chhid_id.lastIndexOf(","));
		}
		//删除ID
		this.categoryDao.delete(id);
		recuDelete(chhid_id);
	}
	
	/**
	 * 方法描述：删除分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean catdelete(String ids){
		// 获取前台传过来的ID值
		if (ids != null) {
			String[] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				if (Recursive(id[i])) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * @Method Description :通过模块名称获取对于的表的名称
	 * @author: HXK
	 * @date : May 29, 2014 6:27:38 PM
	 * @param 
	 */
	@SuppressWarnings("unused")
	private String  moduleNameToTableName(String moudel_type){
		String tabelname="";
		List moduleList=new ArrayList();
		moduleList=sysmoduleService.getList(new HashMap());
		if(moduleList!=null&&moduleList.size()>0){
			for(int i=0;i<moduleList.size();i++){
				HashMap nMap =new HashMap ();
				nMap=(HashMap)moduleList.get(i);
				if(moudel_type.equals(nMap.get("module_type").toString())){
					tabelname=nMap.get("table_name").toString();
					break;
				}
			}
		}
		return tabelname;
	}
	/**
	 * @Method Description :删除分类前，先判断是否已经有商品绑定了，如果没有可以删除，否则不让删除
	 * @author: HXK
	 * @date : May 29, 2014 6:16:07 PM
	 * @param 
	 * @return return_type
	 */
    public boolean categoryGoods(String cat_id,String model_type){
    	boolean is_del=true;
    	if(cat_id!=null&&!cat_id.equals("")){
    		String table_name="";
    		table_name=moduleNameToTableName(model_type);
            if(table_name!=null&&!"".equals(table_name)){
            	List infoList=new ArrayList ();
        		HashMap infoMap =new HashMap ();
        		infoMap.put("cat_attr", cat_id);
        		infoMap.put("is_del", "1");
        		if(table_name.equals("goods")){
            		//商品
        			infoList=goodsService.getList(infoMap);
            	}else if (table_name.equals("article")) {
					//文章
            		infoList=articleService.getList(infoMap);
				}else if (table_name.equals("group")) {
					//团购
					infoList=groupgoodsService.getList(infoMap);
				}else if (table_name.equals("spike")) {
					//秒杀
					infoList=spikegoodsService.getList(infoMap);
				}else if (table_name.equals("inter")) {
					//积分
					infoList=pointsgoodsService.getList(infoMap);
				}else if (table_name.equals("direct")) {
					//预售
					infoList=directsellService.getList(infoMap);
				}else if (table_name.equals("combo")) {
					//套餐
					infoList=comboService.getList(infoMap);
				}
        		if(infoList!=null&&infoList.size()>0){
        			is_del=false;
        		}
            }
    	}
    	
    	
    	
    	
    	return is_del;
    }
	
	public void exportCatExcel() throws Exception {
		
		List<Map<String, String>> allcatList=printAllcategory();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "goodsCategory.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("商品分类", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.LEFT); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "商品分类名称", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "商品分类编号", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);


			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
			for (int i = 0; i < allcatList.size(); i++) {
				Map logMap = (Map) allcatList.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"cat_name_all").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"cat_id_all").toString(), wc);
				sheet.addCell(label);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}
	
	/**
	 * 方法：根据级别输出相对应的分类
	 */
	public List<Map<String, String>> printAllcategory(){
		List<Map<String, String>> allcatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> onecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> twocatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> threecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> fourcatList=new ArrayList<Map<String,String>>();
		Map<String, String> mapc=new HashMap<String, String>();
		mapc.put("cat_level", "1");
		mapc.put("module_type", "goods");
		onecatList=categoryDao.getList(mapc);
		for(Map<String, String> onemap:onecatList){
			String onecat_name="",onecat_id="";
			onecat_name=onemap.get("cat_name");
			onecat_id=onemap.get("cat_id");
			mapc.put("cat_level", "2");
			mapc.put("up_cat_id", onecat_id);
			twocatList=categoryDao.getList(mapc);
			for(Map<String, String> twmap:twocatList){
				//二级
				String twcat_name="",twcat_id="";
				twcat_name=twmap.get("cat_name");
				twcat_id=twmap.get("cat_id");
				mapc.put("cat_level", "3");
				mapc.put("up_cat_id", twcat_id);
				threecatList=categoryDao.getList(mapc);
				   for(Map<String, String> thrmap:threecatList){
					   
					    //三级
						String thrcat_name="",thrcat_id="";
						thrcat_name=thrmap.get("cat_name");
						thrcat_id=thrmap.get("cat_id");
						mapc.put("cat_level", "4");
						mapc.put("up_cat_id", thrcat_id);
						fourcatList=categoryDao.getList(mapc);
						if(fourcatList.size()>0){
							 for(Map<String, String> fmap:fourcatList){
									//继续4级
									String fcat_name="",fcat_id="";
									fcat_name=fmap.get("cat_name");
									fcat_id=fmap.get("cat_id");
									//输入三级
									System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+"======"+onecat_id+"#"+twcat_id+"#"+thrcat_id+"#"+fcat_id);
									Map<String, String> mapall=new HashMap<String, String>();
									mapall.put("cat_name_all", onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name);
									mapall.put("cat_id_all", onecat_id+"#"+twcat_id+"#"+thrcat_id+"#"+fcat_id);
									allcatList.add(mapall);
									continue;
							 }
						}else{
							//输入三级
							System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+"======"+onecat_id+"#"+twcat_id+"#"+thrcat_id);
							Map<String, String> mapall=new HashMap<String, String>();
							mapall.put("cat_name_all", onecat_name+">"+twcat_name+">"+thrcat_name);
							mapall.put("cat_id_all", onecat_id+"#"+twcat_id+"#"+thrcat_id);
							allcatList.add(mapall);
							continue;
						}
						
					   
				   }
			}
		}
		
		return allcatList;
		
	}
	
	
	
	
	
	
	
}

