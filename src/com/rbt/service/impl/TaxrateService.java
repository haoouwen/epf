/*
 
 * Package:com.rbt.servie.impl
 * FileName: TaxrateService.java 
 */
package com.rbt.service.impl;

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
import jxl.write.WriteException;

import com.rbt.common.util.RandomStrUtil;
import com.rbt.dao.ITaxrateDao;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Taxrate;
import com.rbt.service.ITaxrateService;
import com.sun.xml.internal.ws.api.pipe.Tube;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 税率信息Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Tue Aug 18 16:12:24 CST 2015
 */
@Service
public class TaxrateService extends GenericService<Taxrate,String> implements ITaxrateService {
	
	ITaxrateDao taxrateDao;

	@Autowired
	public TaxrateService(ITaxrateDao taxrateDao) {
		super(taxrateDao);
		this.taxrateDao = taxrateDao;
	}
	
	/**
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
			chiMap.put("up_tax_id", str_id[i]);
			// 获取所有上级id为当前id的栏目
			List chi_list = this.taxrateDao.getDeleteList(chiMap);
			System.out.println(chi_list);
			if (chi_list != null && chi_list.size() > 0) {
				Map listMap = null;
				for (int j = 0; j < chi_list.size(); j++) {
					listMap = (HashMap) chi_list.get(j);
					if (listMap.get("tax_id_str") != null) {
						chhid_id += listMap.get("tax_id_str") + ",";
					}
				}
			}
		}
		// 判断是否最后一个字符是否为逗号，是则删除
		if (chhid_id.lastIndexOf(",") > 0) {
			chhid_id = chhid_id.substring(0, chhid_id.lastIndexOf(","));
		}
		// 删除ID
		this.taxrateDao.delete(id);
		Recursive(chhid_id);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.IAreaService#getAll()
	 */
	public List getAll() {
		return taxrateDao.getAll();
	}
	
	
	/**
	 * @Method Description :导出税率表格
	 * @author: HXK
	 * @date : Nov 6, 2015 3:05:28 PM
	 * @param 
	 * @return return_type
	 * @throws IOException 
	 * @throws WriteException 
	 */
    public boolean exportTaxExcel() throws Exception {
		boolean fage=true;
		List<Map<String, String>> allcatList=printAlltax();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "goodsTax.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("商品税率", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.LEFT); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "税率名称", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "税率编号", wc);
			sheet.setColumnView(1, 25);
			sheet.addCell(label);
			
			label = new jxl.write.Label(excelCol++, row, "税率值", wc);
			sheet.setColumnView(2, 25);
			sheet.addCell(label);

			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
			for (int i = 0; i < allcatList.size(); i++) {
				Map logMap = (Map) allcatList.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get("tax_name_all").toString(), wc);
				sheet.addCell(label);
				
				label = new jxl.write.Label(excelCol++, row, logMap.get("tax_id_all").toString(), wc);
				sheet.addCell(label);
				
				label = new jxl.write.Label(excelCol++, row, logMap.get("tax_rate_all").toString(), wc);
				sheet.addCell(label);
			}
			fage=true;

		} catch (Exception e) {
			e.printStackTrace();
			fage=false;

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();
		}
		return fage;
	}
	/**
	 * 导入税率
	 */
	public  boolean importTax(String file ) throws IOException{
		     //String file="E://txv3.xls";
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
			    String tax_id,up_tax_id,tax_name,tax_number,tax_en_name,tax_level,tax_unit,tax_price,tax_rate,tax_unit_remark;
			    int sort_no=30;
				//创建一个数组 用来存储第一行的值
				String[] first = new String[sheet.getColumns()];
				//行数(表头的目录不需要，从1开始)
			     //列数
			     for(int i=0; i<sheet.getColumns(); i++){
			      //获取第i行，第j列的值
			      cell = sheet.getCell(i,0);    
			      first[i] = cell.getContents();
			     }
			     int count=0;
			     //储存商品
			     //行数(表头的目录不需要，从1开始)
			     for(int i=1; i<sheet.getRows(); i++){
			    	//判断多余或者空行
			    	 up_tax_id ="1111111111";// 
			    	 tax_id="";
			    	// 生成十位随机数的字符串
			    	 tax_id= RandomStrUtil.getNumberRand(); 
			    	 tax_name = (sheet.getCell(1,i)).getContents().trim();// 
			    	 tax_number = (sheet.getCell(0,i)).getContents().trim();// 
			    	 tax_en_name =ToolsFuc.getPingYin(tax_name);// 
			    	 tax_level = "1";// 
			    	 sort_no++;// 
			    	 tax_unit = "件";// 
			    	 tax_price = "另行确定";// 
			    	 tax_rate = (sheet.getCell(5,i)).getContents().trim().replace("0%", "");// 
			    	 tax_unit_remark = "";// 
					
			    	 Taxrate tx=new Taxrate();
			    	 tx.setTax_id(tax_id);
			    	 tx.setSort_no(String.valueOf(sort_no));
			    	 tx.setTax_en_name(tax_en_name);
			    	 tx.setTax_level(tax_level);
			    	 tx.setTax_name(tax_name);
			    	 tx.setTax_number(tax_number);
			    	 tx.setTax_price(tax_price);
			    	 tx.setTax_rate(tax_rate);
			    	 tx.setTax_unit(tax_unit);
			    	 tx.setTax_unit_remark(tax_unit_remark);
			    	 tx.setUp_tax_id(up_tax_id);
			    	 taxrateDao.insert(tx);
					 System.out.println("成功插入税率名称："+tax_name+"税率值："+tax_rate+"排序："+sort_no);
					 count++;
			   }
			     System.out.println("成功插入："+count+"条");
			    return true;
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return false;
		  }
	
	
public List<Map<String, String>> printAlltax(){
	List<Map<String, String>> allcatList=new ArrayList<Map<String,String>>();
	List<Map<String, String>> onecatList=new ArrayList<Map<String,String>>();
	List<Map<String, String>> twocatList=new ArrayList<Map<String,String>>();
	List<Map<String, String>> threecatList=new ArrayList<Map<String,String>>();
	List<Map<String, String>> fourcatList=new ArrayList<Map<String,String>>();
	List<Map<String, String>> fivecatList=new ArrayList<Map<String,String>>();
	Map<String, String> mapc=new HashMap<String, String>();
	mapc.put("tax_level", "1");
	onecatList=taxrateDao.getList(mapc);
	for(Map<String, String> onemap:onecatList){
		String onecat_name="",onecat_id="",one_tax_rate="";
		onecat_name=onemap.get("tax_name");
		onecat_id=onemap.get("tax_id");
		one_tax_rate=onemap.get("tax_rate");
		mapc.put("tax_level", "2");
		mapc.put("up_tax_id", onecat_id);
		twocatList=taxrateDao.getList(mapc);
		if(twocatList.size()>0){
			for(Map<String, String> twmap:twocatList){
				//二级
				String twcat_name="",twcat_id="",tw_tax_rate="";
				twcat_name=twmap.get("tax_name");
				twcat_id=twmap.get("tax_id");
				tw_tax_rate=twmap.get("tax_rate");
				mapc.put("tax_level", "3");
				mapc.put("up_tax_id", twcat_id);
				threecatList=taxrateDao.getList(mapc);
				   if(threecatList.size()>0){
					   for(Map<String, String> thrmap:threecatList){
						   
						    //三级
							String thrcat_name="",thrcat_id="",th_tax_rate="";
							thrcat_name=thrmap.get("tax_name");
							thrcat_id=thrmap.get("tax_id");
							th_tax_rate=thrmap.get("tax_rate");
							mapc.put("tax_level", "4");
							mapc.put("up_tax_id", thrcat_id);
							fourcatList=taxrateDao.getList(mapc);
							if(fourcatList.size()>0){
								 for(Map<String, String> fmap:fourcatList){
										//继续4级
										String fcat_name="",fcat_id="",f_tax_rate="";
										fcat_name=fmap.get("tax_name");
										fcat_id=fmap.get("tax_id");
										f_tax_rate=fmap.get("tax_rate");
										mapc.put("tax_level", "5");
										mapc.put("up_tax_id", fcat_id);
										fivecatList=taxrateDao.getList(mapc);
										if(fivecatList.size()>0){
											for(Map<String, String> fvmap:fivecatList){
												//继续5级
												String fvcat_name="",fvcat_id="",fv_tax_rate="";
												fvcat_name=fvmap.get("tax_name");
												fvcat_id=fvmap.get("tax_id");
												fv_tax_rate=fvmap.get("tax_rate"); 
									       		//输入5级
												System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+">"+fvcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+","+fcat_id+","+fvcat_id+"=========="+fv_tax_rate+"%");
												Map<String, String> mapall=new HashMap<String, String>();
												mapall.put("tax_name_all", onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+">"+fvcat_name);
												mapall.put("tax_id_all", onecat_id+"#"+twcat_id+"#"+thrcat_id+"#"+fcat_id+"#"+fvcat_id);
												mapall.put("tax_rate_all", fv_tax_rate+"%");
												allcatList.add(mapall);
												continue;
											}
											
											
										}else{
											//输入4级
											System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+","+fcat_id+"=========="+f_tax_rate+"%");
											Map<String, String> mapall=new HashMap<String, String>();
											mapall.put("tax_name_all", onecat_name+">"+twcat_name+">"+thrcat_name+">"+fcat_name);
											mapall.put("tax_id_all", onecat_id+"#"+twcat_id+"#"+thrcat_id+"#"+fcat_id);
											mapall.put("tax_rate_all", f_tax_rate+"%");
											allcatList.add(mapall);
											continue;
										}
										
								 }
							}else{
								//输入三级
								System.out.println(onecat_name+">"+twcat_name+">"+thrcat_name+"======"+onecat_id+","+twcat_id+","+thrcat_id+"=========="+th_tax_rate+"%");
								Map<String, String> mapall=new HashMap<String, String>();
								mapall.put("tax_name_all", onecat_name+">"+twcat_name+">"+thrcat_name);
								mapall.put("tax_id_all", onecat_id+"#"+twcat_id+"#"+thrcat_id);
								mapall.put("tax_rate_all", th_tax_rate+"%");
								allcatList.add(mapall);
								continue;
							}
						   
					   }
				   }else{
					 //输入2级
					   System.out.println(onecat_name+">"+twcat_name+"======"+onecat_id+","+twcat_id+"=========="+tw_tax_rate+"%");
						Map<String, String> mapall=new HashMap<String, String>();
						mapall.put("tax_name_all", onecat_name+">"+twcat_name);
						mapall.put("tax_id_all", onecat_id+"#"+twcat_id);
						mapall.put("tax_rate_all", tw_tax_rate+"%");
						allcatList.add(mapall);
					   continue;
				   }
			}
		}else{
			//输入1级
			System.out.println(onecat_name+"======"+onecat_id+"=========="+one_tax_rate+"%");
			Map<String, String> mapall=new HashMap<String, String>();
			mapall.put("tax_name_all", onecat_name);
			mapall.put("tax_id_all", onecat_id);
			mapall.put("tax_rate_all", one_tax_rate+"%");
			allcatList.add(mapall);
			continue;
		}
	}
	return allcatList;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

