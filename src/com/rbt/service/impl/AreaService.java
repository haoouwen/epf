/*
  
 
 * Package:com.rbt.service.impl
 * FileName: AreaService.java 
 */
package com.rbt.service.impl;

import com.rbt.service.IAreaService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IAreaDao;
import com.rbt.model.Area;
import com.rbt.model.Goods;

/**
 * @function 功能 地区管理业务层接口实现
 * @author  创建人 HXK
 * @date  创建日期  Jun 28, 2014
 */
@SuppressWarnings("unchecked")
@Service
public class AreaService extends GenericService<Area,String> implements IAreaService {
	
	private IAreaDao areaDao;

	@Autowired
	public AreaService(IAreaDao areaDao) {
		super(areaDao);
		this.areaDao = areaDao;
	}

	public void updateOneAreaSortNo(Map map) {
		this.areaDao.updateOneAreaSortNo(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAreaIndexList(java.util.Map)
	 */
	public List getAreaIndexList(Map map) {
		return areaDao.getAreaIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getWebAreaList(java.util.Map)
	 */
	public List getWebAreaList(Map map) {
		return areaDao.getWebAreaList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAll()
	 */
	public List getAll() {
		return areaDao.getAll();
	}

	public List getWebAreaIndexList(Map map) {
		return areaDao.getWebAreaIndexList(map);
	}
	
	public List getAreaCityList(Map map) {
		return areaDao.getAreaCityList(map);
	}
	
	public List getCharacterList(Map map) {
		return areaDao.getCharacterList(map);
	}
	
	public List getCountryList(Map map) {
		return areaDao.getCountryList(map);
	}

	public List getDeleteList(Map map) {
		return this.areaDao.getDeleteList(map);
	}
	/**
	 * @author : LHY
	 * @Method Description : 递归调用删除数据
	 */
	public void Recursive(String id) {
		if (id == null || id.equals(""))
			return;
		String chhid_id = "";
		List list = new ArrayList();
		String[] str_id = id.split(",");
		for (int i = 0; i < str_id.length; i++) {
			if (str_id[i].trim().equals("")) {
				return;
			}
			Map chiMap = new HashMap();
			chiMap.put("up_area_id", str_id[i]);
			// 获取所有上级id为当前id的栏目
			List chi_list = this.areaDao.getDeleteList(chiMap);
			System.out.println(chi_list);
			if (chi_list != null && chi_list.size() > 0) {
				Map listMap = null;
				for (int j = 0; j < chi_list.size(); j++) {
					listMap = (HashMap) chi_list.get(j);
					if (listMap.get("area_id_str") != null) {
						chhid_id += listMap.get("area_id_str") + ",";
					}
				}
			}
		}
		// 判断是否最后一个字符是否为逗号，是则删除
		if (chhid_id.lastIndexOf(",") > 0) {
			chhid_id = chhid_id.substring(0, chhid_id.lastIndexOf(","));
		}
		// 删除ID
		this.areaDao.delete(id);
		Recursive(chhid_id);
	}
	
    
	/**
	 * 导出地区
	 * @throws Exception
	 */
	public void exportAreaExcel() throws Exception {
		
		List areaList =printALLarea();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "Area.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("地区列表", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.LEFT); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "地区ID", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "地区名称", wc);
			sheet.setColumnView(1, 25);
			sheet.addCell(label);
			
			label = new jxl.write.Label(excelCol++, row, "跨境通ID", wc);
			sheet.setColumnView(2, 25);
			sheet.addCell(label);


			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
			for (int i = 0; i < areaList.size(); i++) {
				Map logMap = (Map) areaList.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"area_id").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"area_name").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
				"kjtareaid").toString(), wc);
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
	public List<Map<String, String>> printALLarea(){
		List<Map<String, String>> allcatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> onecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> twocatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> threecatList=new ArrayList<Map<String,String>>();
		List<Map<String, String>> fourcatList=new ArrayList<Map<String,String>>();
		Map<String, String> mapc=new HashMap<String, String>();
		mapc.put("up_area_id", "9999999999");
		onecatList=areaDao.getList(mapc);
		for(Map<String, String> onemap:onecatList){
			String onecat_name="",onecat_id="";
			onecat_name=onemap.get("area_name");
			onecat_id=onemap.get("area_id");
			mapc.put("up_area_id", onecat_id);
			twocatList=areaDao.getList(mapc);
			//加入地区列表
			Map<String, String> mapallone=new HashMap<String, String>();
			mapallone.put("area_id", onecat_id);
			mapallone.put("area_name", onecat_name);
			mapallone.put("kjtareaid", onemap.get("kjtareaid"));
			allcatList.add(mapallone);
			for(Map<String, String> twmap:twocatList){
				//二级
				String twcat_name="",twcat_id="";
				twcat_name=twmap.get("area_name");
				twcat_id=twmap.get("area_id");
				mapc.put("up_area_id", twcat_id);
				threecatList=areaDao.getList(mapc);
				   //加入地区列表
					Map<String, String> mapallt=new HashMap<String, String>();
					mapallt.put("area_id", twcat_id);
					mapallt.put("area_name", twcat_name);
					mapallt.put("kjtareaid", twmap.get("kjtareaid"));
					allcatList.add(mapallt);
				   for(Map<String, String> thrmap:threecatList){
					    //三级
						String thrcat_name="",thrcat_id="";
						thrcat_name=thrmap.get("area_name");
						thrcat_id=thrmap.get("area_id");
						mapc.put("up_area_id", thrcat_id);
						fourcatList=areaDao.getList(mapc);
						Map<String, String> mapall3=new HashMap<String, String>();
						mapall3.put("area_id", thrcat_id);
						mapall3.put("area_name", thrcat_name);
						mapall3.put("kjtareaid", thrmap.get("kjtareaid"));
						allcatList.add(mapall3);
						if(fourcatList.size()>0){
							 for(Map<String, String> fmap:fourcatList){
									//继续4级
									String fcat_name="",fcat_id="";
									fcat_name=fmap.get("area_name");
									fcat_id=fmap.get("area_id");
									Map<String, String> mapall4=new HashMap<String, String>();
									mapall4.put("area_id", fcat_id);
									mapall4.put("area_name", fcat_name);
									mapall4.put("kjtareaid", fmap.get("kjtareaid"));
									allcatList.add(mapall4);
									continue;
							 }
						}
				   }
			}
		}
		return allcatList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    /**
     * 导入地区
     */
	public  boolean importArea(String file) throws Exception{
		  //创建一个list 用来存储读取的内容
		    List list = new ArrayList();
		    WorkbookSettings settings = new WorkbookSettings();
		    Workbook rwb = null;
		    Cell cell = null;
		    
		    
		    //创建输入流
		    InputStream stream = new FileInputStream(file);
		    
		    //获取Excel文件对象
		    try {
		    	settings.setEncoding("utf-8");
				rwb = Workbook.getWorkbook(stream, settings);
				//获取文件的指定工作表 默认的第一个
			    Sheet sheet = rwb.getSheet(0);  
			    String area_id,area_name,kjtareaid = "";
			    
			     
			     //储存商品
			     //行数(表头的目录不需要，从1开始)
			     for(int i=1; i<sheet.getRows(); i++){
			    	 
			    	 area_id = (sheet.getCell(0,i)).getContents().trim(); //地区ID
			    	 area_name = (sheet.getCell(1,i)).getContents().trim(); //地区名称
			    	 kjtareaid = (sheet.getCell(2,i)).getContents().trim(); //跨境通地区ID
			         
			    	//判断地区ID不为空 
					if(!ValidateUtil.isRequired(area_id)) {
						// 创建地区对象
						Area area = this.areaDao.get(area_id);
						//判断地区对象不为空
						if(area != null) {
							//判断地区名称不为空 
							if(!ValidateUtil.isRequired(area_name)) {
								area.setArea_name(area_name);
							}
							//判断跨境通地区ID不为空 
							if(!ValidateUtil.isRequired(kjtareaid)) {
								area.setKjtareaid(kjtareaid);
							}
							//修改商品
							this.areaDao.update(area);
						}
						
					}
					

			     }
			     return true;
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return false;
		  }
	
}
