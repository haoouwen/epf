/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsbrandService.java 
 */
package com.rbt.service.impl;

import java.io.IOException;
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
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.rbt.dao.ICategoryDao;
import com.rbt.dao.IGoodsbrandDao;
import com.rbt.function.CategoryFuc;
import com.rbt.index.ParaModel;
import com.rbt.index.SearchIndex;
import com.rbt.model.Goodsbrand;
import com.rbt.service.IGoodsbrandService;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品品牌表Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Mon Feb 27 12:42:32 CST 2014
 */
@Service
public class GoodsbrandService extends GenericService<Goodsbrand,String> implements IGoodsbrandService {
	
	IGoodsbrandDao goodsbrandDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	public GoodsbrandService(IGoodsbrandDao goodsbrandDao) {
		super(goodsbrandDao);
		this.goodsbrandDao = goodsbrandDao;
	}
	
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	public List cat_attr_list(String cat_ids){
		String ids[]=cat_ids.split("\\|");
		List cat_attr_list=new ArrayList();		
		for(int i=0;i<ids.length;i++){
			Map listMap=new HashMap();
			String id=ids[i].replace(" ","");
			listMap.put("id",id);
			String catName=CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if(!id.equals("")&&!catName.equals("")){
				cat_attr_list.add(i,listMap);
			}
		}
		return cat_attr_list;
	}
	
	/**	
	 * @author : WXP
	 * @param :en_index 品牌索引
	 * @throws IOException 
	 * @throws ParseException 
	 * @date Mar 26, 2014 10:04:22 AM
	 * @Method Description :品牌列表
	 */
	public Map goodsBrandList(String en_index,String cat_id,String brand_name) throws ParseException, IOException{
		//索引条件
		List shList = new ArrayList();
		//状态正常
		shList = normalSearch("info_state", "1", shList);
		//品牌分类
		Map catMap = new HashMap();
		//品牌索引
		if(en_index != null && !en_index.equals("") && !en_index.equals("All")){
			shList = normalSearch("en_index", en_index, shList);
			catMap.put("en_index", en_index);
		}
		//品牌名称
		if(brand_name != null && !brand_name.equals("") && !brand_name.equals("All")){
			shList = normalSearch("brand_name", brand_name, shList);
			catMap.put("brand_name", brand_name);
		}
		//商品分类
		if(cat_id != null && !cat_id.equals("")){
			shList = normalSearch("goods_attr", cat_id, shList);
		}
		catMap.put("module_type", "goods");
		catMap.put("cat_level", "1");
		//根据条件获取品牌
		SearchIndex si = new SearchIndex("goodsbrand");
		Sort so = new  Sort();  
		//搜索到的数据条数
		int count = si.getCount(shList);
		List goodsbrandList = si.search(shList, so , 1, count);
		//根据条件获取品牌分类
		List categoryList = this.categoryDao.getList(catMap);
		Map map=new HashMap();
		map.put("goodsbrandList", goodsbrandList);
		map.put("categoryList", categoryList);
		map.put("en_index", en_index);
		map.put("cat_id", cat_id);
		map.put("brand_name", brand_name);
		return map;
	}
	
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 1:45:23 PM
	 * @Method Description：获取catName
	 */
	public String getCatName(String goodsAttr,String cat_name_str){
		//品牌分类
		String brand_cat[] = goodsAttr.split("\\|");
		for(int i = 0; i < brand_cat.length; i++){
			if(cat_name_str != null && !cat_name_str.equals("")){
				cat_name_str = cat_name_str +"/"+ CategoryFuc.getCateNameByMap(brand_cat[i]);
			}else{
				cat_name_str = CategoryFuc.getCateNameByMap(brand_cat[i]);
			}
		}
		return cat_name_str;
	}
	
	/**
	 * @author : LJQ
	 * @date : Jul 25, 2014 10:20:30 AM
	 * @Method Description : lucene普通搜索条件的赋值------------------------更换sorl索引时删掉
	 */
	public List normalSearch(String fieldName,String fieldValue,List list){
		boolean is_souch=false;
		if(fieldName==null || fieldName.equals("")) return list;
		if(fieldValue!=null&&!fieldValue.equals("")){
			ParaModel pm = new ParaModel(fieldName,fieldValue);
			list.add(pm);
			is_souch = true;
		}
		return list;
	}
	
	/**	
	 * @author : WXP
	 * @param :goods_attr 商品分类串关联 is_recom 是否推荐
	 * @throws IOException 
	 * @throws ParseException 
	 * @date Apr 10, 2014 8:45:04 PM
	 * @Method Description :相关品牌
	 */
	public List getBrandList(String goods_attr, String is_recom) throws ParseException, IOException{
		List shList = new ArrayList();
		//找出相关分类的品牌
		String brand_cat[] = goods_attr.split("\\|");
		for(int i = 0; i < brand_cat.length; i++){
			brand_cat[i] = brand_cat[i].replace(",", "/");
			shList = normalSearch("goods_attr", brand_cat[i], shList);
		}
		shList = normalSearch("is_recom", is_recom, shList);//是否推荐
		Sort so = new  Sort();  
		SortField sf=new  SortField("sort_no", SortField.STRING, false);//升序
		so.setSort(new  SortField[]{sf});
		SearchIndex si=new SearchIndex("goodsbrand");
		List brandList = si.search(shList, so , 1, 8);
		return brandList;
	}
	/**	
	 * @author : HXK
	 * @param :随机读取品牌20条
	 * @throws IOException 
	 * @throws ParseException 
	 * @Method Description :相关品牌
	 */
	public List getBrandRandList(Map map) throws ParseException, IOException{
		return this.goodsbrandDao.getBrandRandList(map);
	}
	
	/**
	 * 导出品牌
	 * @param goodsList 品牌集合
	 */
	public boolean exportbrandExcel(List goodsbrandList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(goodsbrandList != null && goodsbrandList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"Goodsbrand.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("品牌列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "品牌ID", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "品牌名称", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < goodsbrandList.size(); i ++){
	            
	            Map goodsMap = (Map)goodsbrandList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String brand_id,brand_name;
	            
	            if(goodsMap.get("brand_id") != null){
	            	brand_id = goodsMap.get("brand_id").toString();
	            }else {
	            	brand_id = "";
				}
	            
	            if(goodsMap.get("brand_name") != null){
	            	brand_name = goodsMap.get("brand_name").toString();
	            }else {
	            	brand_name = "";
				}
	            
	          //商品品牌ID 
	           label = new jxl.write.Label(excelCol++, row, brand_id, wc);
	           sheet.addCell(label);
	           
	           //商品品牌名称
	           label = new jxl.write.Label(excelCol++, row, brand_name, wc);
	           sheet.addCell(label);  
	           
	           }
	         flag = true; 
	       //生成excel文件
		   workbook.write();
	    }catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	          workbook.close();  
	  	      os.flush();  
		  	  os.close();  
		  	  os=null;  
	  	      response.flushBuffer(); 	        
	    }
	   }
		return flag;
	}
}

