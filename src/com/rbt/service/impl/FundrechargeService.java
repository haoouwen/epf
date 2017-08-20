/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: FundrechargeService.java 
 */
package com.rbt.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IFundrechargeDao;
import com.rbt.model.Fundrecharge;
import com.rbt.service.IFundrechargeService;

/**
 * @function 功能 会员资金充值记录Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Tue Jul 12 13:10:48 CST 2014
 */
@Service
public class FundrechargeService extends GenericService<Fundrecharge,String> implements IFundrechargeService {

	
	IFundrechargeDao fundrechargeDao;
	@Autowired
	public FundrechargeService(IFundrechargeDao fundrechargeDao) {
		super(fundrechargeDao);
		this.fundrechargeDao = fundrechargeDao;
	}
	
	public Fundrecharge getByTrxid(String trxid)throws Exception{
		return fundrechargeDao.getByTrxid(trxid);
	}

	public Fundrecharge getByOrderId(String order_id) throws Exception {
		return fundrechargeDao.getByOrderId(order_id);
	}
	/**
	 * 充值记录导出
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public boolean fundchargeExport( HttpServletResponse response) throws IOException, WriteException {
		boolean flag = false;
		List exList=new ArrayList();
		Map pageMap = new HashMap();
		pageMap.put("charge_cust_id", "0");
		pageMap.put("order_state", "1,2");
		exList=fundrechargeDao.getList(pageMap);
		if(exList != null && exList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"fundcharge.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("充值记录", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "会员名称", wc);
	        sheet.setColumnView(0,25);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "充值金额", wc);
	        sheet.setColumnView(1,15);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "充值平台", wc);
	        sheet.setColumnView(2,15);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "充值时间", wc);
	        sheet.setColumnView(3,20);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "银行交易单号", wc);
	        sheet.setColumnView(4,30);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "充值订单号", wc);
	        sheet.setColumnView(5,20);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "充值状态", wc);
	        sheet.setColumnView(6,15);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "审核状态", wc);
	        sheet.setColumnView(7,15);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "操作人", wc);
	        sheet.setColumnView(8,20);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "备注", wc);
	        sheet.setColumnView(9,35);
	        sheet.addCell(label);
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < exList.size(); i ++){
	            
	            Map goodsMap = (Map)exList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String  cust_name,fund_num,payplat,pay_date,order_state,remark,bank_order_id,order_id,recharge_state,user_name;
                //会员名称	            
	            if(goodsMap.get("cust_name") != null){
	            	cust_name = goodsMap.get("cust_name").toString();
	            }else {
	            	cust_name = "";
				}
	            //充值金额
	            if(goodsMap.get("fund_num") != null){
	            	fund_num = goodsMap.get("fund_num").toString();
	            }else {
	            	fund_num = "0";
				}
	            //充值平台
	            if(goodsMap.get("payplat") != null){
	            	payplat = goodsMap.get("payplat").toString();
	            	if("goldpay".equals(payplat)){
	            		payplat = "余额充值";
	            	}
	            }else {
	            	payplat = "--";
				}
	            
	            //充值时间
	            if(goodsMap.get("pay_date") != null){
	            	pay_date = goodsMap.get("pay_date").toString();
	            }else {
	            	pay_date = "--";
				}
	            //银行交易单号
	            if(goodsMap.get("bank_order_id") != null){
	            	bank_order_id = goodsMap.get("bank_order_id").toString();
	            }else {
	            	bank_order_id = "--";
				}
	            //充值订单号
	            if(goodsMap.get("order_id") != null){
	            	order_id = goodsMap.get("order_id").toString();
	            }else {
	            	order_id = "--";
				}
	            //充值状态 0待充值，1充值成功，2充值失败
	            if(goodsMap.get("recharge_state") != null){
	            	recharge_state = goodsMap.get("recharge_state").toString();
	            	if("0".equals(recharge_state)){
	            		recharge_state="待充值";
	            	}else if("1".equals(recharge_state)){
	            		recharge_state="充值成功";
	            	}else if("2".equals(recharge_state)){
	            		recharge_state="充值失败";
	            	}
	            }else {
	            	recharge_state = "--";
				}
	            //审核状态 0：未审核 1：已审核 2：作废
	            if(goodsMap.get("order_state") != null){
	            	order_state = goodsMap.get("order_state").toString();
	            	if("0".equals(order_state)){
	            		order_state="未审核";
	            	}else if("1".equals(order_state)){
	            		order_state="已审核";
	            	}else if("2".equals(order_state)){
	            		order_state="作废";
	            	}
	            }else {
	            	order_state = "--";
				}
	           //操作人
	            if(goodsMap.get("user_name") != null){
	            	user_name = goodsMap.get("user_name").toString();
	            }else {
	            	user_name = "--";
				}
	            
	            //备注
	            if(goodsMap.get("remark") != null){
	            	remark = goodsMap.get("remark").toString();
	            }else {
	            	remark = "--";
				}
	           //会员名称      
	           label = new jxl.write.Label(excelCol++, row, cust_name, wc);
	           sheet.addCell(label);  
	           
	           //充值金额
	           label = new jxl.write.Label(excelCol++, row, fund_num, wc);
	           sheet.addCell(label); 
	           
	           //充值平台
	           label = new jxl.write.Label(excelCol++, row, payplat, wc);
	           sheet.addCell(label); 
	           
	           //充值时间
	           label = new jxl.write.Label(excelCol++, row, pay_date, wc);
	           sheet.addCell(label);
	           
	           //银行交易单号
	           label = new jxl.write.Label(excelCol++, row, bank_order_id, wc);
	           sheet.addCell(label);  
	           
	           //充值订单号
	           label = new jxl.write.Label(excelCol++, row, order_id, wc);
	           sheet.addCell(label); 
	           
	           //充值状态
	           label = new jxl.write.Label(excelCol++, row, recharge_state, wc);
	           sheet.addCell(label);
	           
	           //审核状态
	           label = new jxl.write.Label(excelCol++, row, order_state, wc);
	           sheet.addCell(label);  
	           
	           //操作人
	           label = new jxl.write.Label(excelCol++, row, user_name, wc);
	           sheet.addCell(label); 
	           
	           //备注
	           label = new jxl.write.Label(excelCol++, row, remark, wc);
	           sheet.addCell(label); 
	           }
	         flag = true; 
	       //生成excel文件
		   workbook.write();
	    }catch (Exception e) {
	    	flag=false;
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

