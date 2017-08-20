/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: FundhistoryService.java 
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
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IFundhistoryDao;
import com.rbt.model.Fundhistory;
import com.rbt.service.IFundhistoryService;

/**
 * @function 功能 会员资金流Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Wed Jul 13 10:06:11 CST 2014
 */
@Service
public class FundhistoryService extends GenericService<Fundhistory,String> implements IFundhistoryService {

	IFundhistoryDao fundhistoryDao;

	@Autowired
	public FundhistoryService(IFundhistoryDao fundhistoryDao) {
		super(fundhistoryDao);
		this.fundhistoryDao = fundhistoryDao;
	}
	/**
	 * @Method Description :导出余额流
	 * @author : HZX
	 * @throws Exception 
	 * @date : Mar 14, 2014 2:27:27 PM
	 */
	public void exportExcel(List fundhistoryList, HttpServletResponse response) throws Exception {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime =df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"Fundhistory.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("余额流", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setAlignment(Alignment.CENTRE); // 设置居中
	        label = new jxl.write.Label(excelCol++, row, "会员名称", wc);
	        sheet.setColumnView(0,15);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "收入", wc);
	        sheet.setColumnView(1,15);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "支出 ", wc);
	        sheet.setColumnView(2,15);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "余下余额", wc);
	        sheet.setColumnView(3,15);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "操作人", wc);
	        sheet.setColumnView(4,15);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "操作时间", wc);
	        sheet.setColumnView(5,20);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, " 事由", wc);
	        sheet.setColumnView(6,99);
	        sheet.addCell(label);
	       
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i=0;i<fundhistoryList.size();i++){
	        	  	Map logMap=(Map)fundhistoryList.get(i);
		            excelCol = 0;
		            row = i + 1;   
		            String user_name="系统操作";
		            if(logMap.get("user_name")!=null){
		            	user_name=logMap.get("user_name").toString();
		            }
		            label = new jxl.write.Label(excelCol++, row, logMap.get("cust_name").toString(), wc);
		            sheet.addCell(label);      
		          	label = new jxl.write.Label(excelCol++, row, logMap.get("fund_in").toString(), wc);
		            sheet.addCell(label);        
		            label = new jxl.write.Label(excelCol++, row, logMap.get("fund_out").toString(), wc);
		            sheet.addCell(label);             /*字串格式*/
		            label = new jxl.write.Label(excelCol++, row,  logMap.get("balance").toString(), wc);
		            sheet.addCell(label);  
		            label = new jxl.write.Label(excelCol++, row,  user_name, wc);
		            sheet.addCell(label);   
		            label = new jxl.write.Label(excelCol++, row,  logMap.get("in_date").toString(), wc);
		            sheet.addCell(label);   
		            label = new jxl.write.Label(excelCol++, row,  logMap.get("reason").toString(), wc);
		            sheet.addCell(label);   
            }

	    }catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally{
//		      生成excel文件
	        workbook.write();
	        workbook.close();
	        os.close();

	    }


	}
}

