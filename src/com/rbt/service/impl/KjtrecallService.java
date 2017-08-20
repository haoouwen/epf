/*
 * Package:com.rbt.servie.impl
 * FileName: KjtrecallService.java 
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
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.rbt.dao.IKjtrecallDao;
import com.rbt.model.Kjtrecall;
import com.rbt.service.IKjtrecallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 跨境通回调表Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Fri Sep 18 16:21:49 CST 2015
 */
@Service
public class KjtrecallService extends GenericService<Kjtrecall,String> implements IKjtrecallService {
	
	IKjtrecallDao kjtrecallDao;

	@Autowired
	public KjtrecallService(IKjtrecallDao kjtrecallDao) {
		super(kjtrecallDao);
		this.kjtrecallDao = kjtrecallDao;
	}
	
	public void updatekjtpur(Map map){
		 this.kjtrecallDao.updatekjtpur(map);
	 }
	/**
	 * @Method Description :通过订单ID查找海关回传对象值
	 * @author: 胡惜坤
	 * @date : Feb 24, 2016 11:33:23 AM
	 * @param 
	 * @return return_type
	 */
	public Kjtrecall getByOrderId(String order_id){
		return kjtrecallDao.getByOrderId(order_id);
	}
	
	/**
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public boolean exprotExcel(List exList, HttpServletResponse response) throws IOException, WriteException {
		boolean flag = false;
		if(exList != null && exList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"kjtdata.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("跨境通接收值", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "商城订单ID", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商城订单价格", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商城订单税费", wc);
	        sheet.setColumnView(2,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "商城订单运费", wc);
	        sheet.setColumnView(3,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "跨境通订单ID", wc);
	        sheet.setColumnView(4,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "跨境通订单价格", wc);
	        sheet.setColumnView(5,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "跨境通订单税费", wc);
	        sheet.setColumnView(6,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "跨境通订单运费", wc);
	        sheet.setColumnView(7,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "跨境通订单状态", wc);
	        sheet.setColumnView(8,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "购汇帐单号", wc);
	        sheet.setColumnView(9,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "提交时间", wc);
	        sheet.setColumnView(9,35);
	        sheet.addCell(label);
	        
	        
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < exList.size(); i ++){
	            
	            Map goodsMap = (Map)exList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String  order_id,tatal_amount,taxes,ship_free,
	            merchantorderid,productamount,taxamount,shippingamount,sostatus,purchasing,kjtdate;
	            
	            
	            if(goodsMap.get("order_id") != null){
	            	order_id = goodsMap.get("order_id").toString();
	            }else {
	            	order_id = "";
				}
	            
	            if(goodsMap.get("tatal_amount") != null){
	            	tatal_amount = goodsMap.get("tatal_amount").toString();
	            }else {
	            	tatal_amount = "";
				}
	            
	            if(goodsMap.get("taxes") != null){
	            	taxes = goodsMap.get("taxes").toString();
	            }else {
	            	taxes = "";
				}
	            
	            if(goodsMap.get("ship_free") != null){
	            	ship_free = goodsMap.get("ship_free").toString();
	            }else {
	            	ship_free = "";
				}
	            
	            if(goodsMap.get("merchantorderid") != null){
	            	merchantorderid = goodsMap.get("merchantorderid").toString();
	            }else {
	            	merchantorderid = "";
				}
	            
	            if(goodsMap.get("productamount") != null){
	            	productamount = goodsMap.get("productamount").toString();
	            }else {
	            	productamount = "";
				}
	            
	            if(goodsMap.get("taxamount") != null){
	            	taxamount = goodsMap.get("taxamount").toString();
	            }else {
	            	taxamount = "";
				}
	            
	            if(goodsMap.get("shippingamount") != null){
	            	shippingamount = goodsMap.get("shippingamount").toString();
	            }else {
	            	shippingamount = "";
				}
	            
	            if(goodsMap.get("sostatus") != null){
	            	sostatus = goodsMap.get("sostatus").toString();
	            	if("-4".equals(sostatus)){
	            		sostatus="系统作废";
	            	}else if("-1".equals(sostatus)){
	            		sostatus="作废";
	            	}else if("0".equals(sostatus)){
	            		sostatus="待审核";
	            	}else if("1".equals(sostatus)){
	            		sostatus="待出库";
	            	}else if("4".equals(sostatus)){
	            		sostatus="已出库待申报";
	            	}else if("41".equals(sostatus)){
	            		sostatus="已申报待通关";
	            	}else if("45".equals(sostatus)){
	            		sostatus="已通关发往客户";
	            	}else if("5".equals(sostatus)){
	            		sostatus="订单完成";
	            	}else if("6".equals(sostatus)){
	            		sostatus="申报失败订单作废";
	            	}else if("65".equals(sostatus)){
	            		sostatus="通过失败订单作废";
	            	}else if("7".equals(sostatus)){
	            		sostatus="订单拒收";
	            	}
	            }else {
	            	sostatus = "";
				}
	            
	            if(goodsMap.get("purchasing") != null){
	            	purchasing = goodsMap.get("purchasing").toString();
	            }else {
	            	purchasing = "";
				}
	            
	            if(goodsMap.get("kjtdate") != null){
	            	kjtdate = goodsMap.get("kjtdate").toString();
	            }else {
	            	kjtdate = "";
				}
	            

	           
	           //商城订单ID
	           label = new jxl.write.Label(excelCol++, row, order_id, wc);
	           sheet.addCell(label);  
	           
	           //商城订单价格
	           label = new jxl.write.Label(excelCol++, row, tatal_amount, wc);
	           sheet.addCell(label); 
	           
	           //商城订单税费
	           label = new jxl.write.Label(excelCol++, row, taxes, wc);
	           sheet.addCell(label);
	           
	           //商城订单运费
	           label = new jxl.write.Label(excelCol++, row, ship_free, wc);
	           sheet.addCell(label);  
	           
	           //跨境通订单ID
	           label = new jxl.write.Label(excelCol++, row, merchantorderid, wc);
	           sheet.addCell(label); 
	           
	           //跨境通订单价格 
	           label = new jxl.write.Label(excelCol++, row, productamount, wc);
	           sheet.addCell(label);
	           
	           //跨境通订单税费
	           label = new jxl.write.Label(excelCol++, row, taxamount, wc);
	           sheet.addCell(label);  
	           
	           //跨境通订单运费
	           label = new jxl.write.Label(excelCol++, row, shippingamount, wc);
	           sheet.addCell(label); 
	           
	           //跨境通订单状态
	           label = new jxl.write.Label(excelCol++, row, sostatus, wc);
	           sheet.addCell(label); 
	           
	           //汇款订单
	           label = new jxl.write.Label(excelCol++, row, purchasing, wc);
	           sheet.addCell(label); 
	           
	           //日期
	           label = new jxl.write.Label(excelCol++, row, kjtdate, wc);
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

