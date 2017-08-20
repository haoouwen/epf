/*
  
 
 * Package:com.rbt.service.impl
 * FileName: LogsService.java
 */
package com.rbt.service.impl;

import java.io.IOException;
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
import com.rbt.service.ILogsService;
import com.rbt.model.Logs;
import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ILogsDao;

/**
 * @function 功能 添加角色业务层类
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 3:25:23 PM
 */
@Service
public class LogsService extends GenericService<Logs,String>implements ILogsService {

	/*
	 * 系统日志实现层接口
	 */
	@Autowired
	private ILogsDao logsDao;
	
	IAboutusDao aboutusDao;

	@Autowired
	public LogsService(ILogsDao logsDao) {
		super(logsDao);
		this.logsDao = logsDao;
	}

	public void deleteAlllogs() {
		logsDao.deleteAlllogs();
	}
	/**
	 * @Method Description :导出日志
	 * @author : HZX
	 * @date : Feb 27, 2014 1:17:43 PM
	 */
	public void exportExcel(List logslist, HttpServletResponse response) throws Exception {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime =df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"Logs.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("系统日志", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setAlignment(Alignment.CENTRE); // 设置居中
	        label = new jxl.write.Label(excelCol++, row, "用户名", wc);
	        sheet.setColumnView(0,10);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "ip", wc);
	        sheet.setColumnView(1,15);
	        sheet.addCell(label);
	        label = new jxl.write.Label(excelCol++, row, "操作内容 ", wc);
	        sheet.setColumnView(2,49);
	        sheet.addCell(label);
	        sheet.setColumnView(3,23);
	        label = new jxl.write.Label(excelCol++, row, "操作时间", wc);
	        sheet.addCell(label);
	        //jxl.write.Number number = null;
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i=0;i<logslist.size();i++){
	          Map logMap=(Map)logslist.get(i);
	            excelCol = 0;
	            row = i + 1;           
	                  label = new jxl.write.Label(excelCol++, row, logMap.get("user_name").toString(), wc);
	            sheet.addCell(label);      
	                   label = new jxl.write.Label(excelCol++, row, logMap.get("ip").toString(), wc);
	            sheet.addCell(label);        
	                 label = new jxl.write.Label(excelCol++, row, logMap.get("content").toString(), wc);
	            sheet.addCell(label);             /*字串格式*/
	                       label = new jxl.write.Label(excelCol++, row,  logMap.get("in_date").toString(), wc);
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
