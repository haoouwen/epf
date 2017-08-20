/*
 * Package:com.rbt.servie.impl
 * FileName: ExcouponsService.java 
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

import com.rbt.dao.IExcouponsDao;
import com.rbt.model.Excoupons;
import com.rbt.service.IExcouponsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 优惠券兑换表Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:10:41 CST 2015
 */
@Service
public class ExcouponsService extends GenericService<Excoupons,String> implements IExcouponsService {
	
	IExcouponsDao excouponsDao;

	@Autowired
	public ExcouponsService(IExcouponsDao excouponsDao) {
		super(excouponsDao);
		this.excouponsDao = excouponsDao;
	}
	
	
	/**
	 * 重新下载
	 * @param goodsList 红包集合
	 */
	public boolean exprotcouponExcel(List excouponsList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(excouponsList != null && excouponsList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"excoupon.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("优惠卷列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "优惠卷名称", wc);
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
	          for(int i = 0; i < excouponsList.size(); i ++){
	            
	            Map goodsMap = (Map)excouponsList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String coupon_name,coupon_no,ex_state;
	            
	            if(goodsMap.get("coupon_name") != null){
	            	coupon_name = goodsMap.get("coupon_name").toString();
	            }else {
	            	coupon_name = "";
				}
	            
	            if(goodsMap.get("coupon_no") != null){
	            	coupon_no = goodsMap.get("coupon_no").toString();
	            }else {
	            	coupon_no = "";
				}
	            if(goodsMap.get("ex_state") != null){
	            	ex_state = goodsMap.get("ex_state").toString();
	            	if(ex_state.equals("0")){
	            		ex_state="未兑换";
	            	}else{
	            		ex_state="已兑换";
	            	}
	            }else {
	            	ex_state = "";
				}
	            
	          //红包名称 
	           label = new jxl.write.Label(excelCol++, row, coupon_name, wc);
	           sheet.addCell(label);
	           
	           //兑换码
	           label = new jxl.write.Label(excelCol++, row, coupon_no, wc);
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

