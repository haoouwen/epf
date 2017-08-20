/*
 * Package:com.rbt.servie.impl
 * FileName: ExredbagService.java 
 */
package com.rbt.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.rbt.dao.IExredbagDao;
import com.rbt.model.Exredbag;
import com.rbt.service.IExredbagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 红包兑换表Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:33:32 CST 2015
 */
@Service
public class ExredbagService extends GenericService<Exredbag,String> implements IExredbagService {
	
	IExredbagDao exredbagDao;

	@Autowired
	public ExredbagService(IExredbagDao exredbagDao) {
		super(exredbagDao);
		this.exredbagDao = exredbagDao;
	}
	
	/**
	 * 重新下载
	 * @param goodsList 红包集合
	 */
	public boolean exprotredbagExcel(List exredbagList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(exredbagList != null && exredbagList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"redpacket.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("红包列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "红包名称", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "兑换码", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "状态", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < exredbagList.size(); i ++){
	            
	            Map exredbagMap = (Map)exredbagList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String red_name,red_no,ex_state;
	            
	            if(exredbagMap.get("red_name") != null){
	            	red_name = exredbagMap.get("red_name").toString();
	            }else {
	            	red_name = "";
				}
	            
	            if(exredbagMap.get("red_no") != null){
	            	red_no = exredbagMap.get("red_no").toString();
	            }else {
	            	red_no = "";
				}
	            if(exredbagMap.get("ex_state") != null){
	            	ex_state = exredbagMap.get("ex_state").toString();
	            	if(ex_state.equals("0")){
	            		ex_state="未兑换";
	            	}else{
	            		ex_state="已兑换";
	            	}
	            }else {
	            	ex_state = "";
				}
	            
	          //红包名称 
	           label = new jxl.write.Label(excelCol++, row, red_name, wc);
	           sheet.addCell(label);
	           
	           //兑换码
	           label = new jxl.write.Label(excelCol++, row, red_no, wc);
	           sheet.addCell(label);  
	           
	           //红包状态
	           label = new jxl.write.Label(excelCol++, row, ex_state, wc);
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

