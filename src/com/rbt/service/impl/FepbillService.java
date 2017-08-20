/*
 * Package:com.rbt.servie.impl
 * FileName: FepbillService.java 
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

import com.rbt.dao.IFepbillDao;
import com.rbt.model.Fepbill;
import com.rbt.service.IFepbillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 代购汇账单Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Sep 23 13:22:25 CST 2015
 */
@Service
public class FepbillService extends GenericService<Fepbill,String> implements IFepbillService {
	
	IFepbillDao fepbillDao;

	@Autowired
	public FepbillService(IFepbillDao fepbillDao) {
		super(fepbillDao);
		this.fepbillDao = fepbillDao;
	}
	
	/**
	 * 重新下载
	 * @param goodsList 红包集合
	 */
	public boolean exprotExcel(List exList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(exList != null && exList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"excoupon.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("代购汇帐单", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "订单编号", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "购汇总金额", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "日期", wc);
	        sheet.setColumnView(2,35);
	        sheet.addCell(label);
	        
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < exList.size(); i ++){
	            
	            Map goodsMap = (Map)exList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String fepbill_id,purchasingtotalamount,in_date;
	            
	            if(goodsMap.get("fepbill_id") != null){
	            	fepbill_id = goodsMap.get("fepbill_id").toString();
	            }else {
	            	fepbill_id = "";
				}
	            
	            if(goodsMap.get("purchasingtotalamount") != null){
	            	purchasingtotalamount = goodsMap.get("purchasingtotalamount").toString();
	            }else {
	            	purchasingtotalamount = "";
				}
	            
	            if(goodsMap.get("in_date") != null){
	            	in_date = goodsMap.get("in_date").toString();
	            }else {
	            	in_date = "";
				}
	            

	            
	          //充值卡号 
	           label = new jxl.write.Label(excelCol++, row, fepbill_id, wc);
	           sheet.addCell(label);
	           
	           //充值卡金额
	           label = new jxl.write.Label(excelCol++, row, purchasingtotalamount, wc);
	           sheet.addCell(label);  
	           
	           //截至日期
	           label = new jxl.write.Label(excelCol++, row, in_date, wc);
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

