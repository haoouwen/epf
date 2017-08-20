/*
 * Package:com.rbt.servie.impl
 * FileName: RechargeablecardsService.java 
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

import com.rbt.dao.IFundrechargeDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IRechargeablecardDao;
import com.rbt.dao.IRechargeablecardsDao;
import com.rbt.model.Fundrecharge;
import com.rbt.model.Memberfund;
import com.rbt.model.Rechargeablecard;
import com.rbt.model.Rechargeablecards;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IRechargeablecardsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 充值卡Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 14:01:50 CST 2015
 */
@Service
public class RechargeablecardsService extends GenericService<Rechargeablecards,String> implements IRechargeablecardsService {
	
	IRechargeablecardsDao rechargeablecardsDao;
	public Rechargeablecards rechargeablecards;
	public Rechargeablecard rechargeablecard;
	public Memberfund memberfund;
	public Fundrecharge fundrecharge;
	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IFundrechargeDao fundrechargeDao;
	@Autowired
	private IRechargeablecardDao rechargeablecardDao;
	
	
	@Autowired
	public RechargeablecardsService(IRechargeablecardsDao rechargeablecardsDao) {
		super(rechargeablecardsDao);
		this.rechargeablecardsDao = rechargeablecardsDao;
	}
	
	/**
	 * @Method Description :通过key找到充值卡
	 * @author : CYC
	 * @date : Apr 19, 2014 11:01:04 AM
	 */
	public Rechargeablecards getCardkey(String cardkey) {
		return this.rechargeablecardsDao.getCardkey(cardkey);
	}
	
	
	public void recharge(Rechargeablecards rechargeablecards,String cust_id,String user_id){
		//设置充值卡为已充值
		rechargeablecards.setCardsstate("1");
		rechargeablecardsDao.update(rechargeablecards);
		//修改充值卡使用多少张
		rechargeablecard = rechargeablecardDao.get(rechargeablecards.getCardid());
		rechargeablecard.setCardused(Integer.toString(Integer.parseInt(rechargeablecard.getCardused())+1));
		rechargeablecardDao.update(rechargeablecard);
		//给帐号充值
		memberfund=this.memberfundDao.get(cust_id);
		if(memberfund!=null){
			Double cardsmoney=Double.parseDouble(rechargeablecards.getCardsmoney());
			//总金额
			Double allfun=Double.parseDouble(memberfund.getFund_num())+cardsmoney;
			//可使用金额
			Double usefun=Double.parseDouble(memberfund.getUse_num())+cardsmoney;
			memberfund.setFund_num(allfun.toString());
			memberfund.setUse_num(usefun.toString());
			//更新用户资金表
			memberfundDao.update(memberfund);
			//写入资金日志表
			fundrecharge = new Fundrecharge();
			fundrecharge.setCust_id(cust_id);
			fundrecharge.setFund_num(cardsmoney);
			fundrecharge.setPayplat("充值卡");
			fundrecharge.setUser_id(user_id);
			fundrecharge.setOrder_state("1");
			fundrecharge.setOrder_id(rechargeablecards.getCardskey());
			this.fundrechargeDao.insert(fundrecharge);
		}
	}
	
	/**
	 * 重新下载
	 * @param goodsList 红包集合
	 */
	public boolean exprotcradExcel(List excardList, HttpServletResponse response) throws Exception {
		boolean flag = false;
		if(excardList != null && excardList.size()> 0) {
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = df.format(new Date());
	    response.setHeader("Content-Disposition", "filename="+nowtime+"excoupon.xls");//attachment // WritableWorkbook是JexcelApi的一个类。
	    // 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
	    OutputStream os = response.getOutputStream();//取得输出流
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    WritableSheet sheet = workbook.createSheet("充值卡列表", 0); // 组织excel文件的内容
	    jxl.write.Label label = null;
	    int excelCol = 0;
	    int row = 0;
	    try {
	    	WritableCellFormat wc = new WritableCellFormat();
	    	wc.setVerticalAlignment(VerticalAlignment.CENTRE); 
	        wc.setAlignment(Alignment.CENTRE); 
	        
	        label = new jxl.write.Label(excelCol++, row, "充值卡号", wc);
	        sheet.setColumnView(0,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "充值卡金额", wc);
	        sheet.setColumnView(1,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "截至日期", wc);
	        sheet.setColumnView(2,35);
	        sheet.addCell(label);
	        
	        label = new jxl.write.Label(excelCol++, row, "使用状态", wc);
	        sheet.setColumnView(3,35);
	        sheet.addCell(label);
	         
	        jxl.write.DateTime dateTime;
	        jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat("yyyy-MM-dd");//时间格式
	        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);       
	          for(int i = 0; i < excardList.size(); i ++){
	            
	            Map goodsMap = (Map)excardList.get(i);
	            excelCol = 0;
	            row = i + 1;      
	            String cardskey,cardsmoney,cardsdate,cardsstate;
	            
	            if(goodsMap.get("cardskey") != null){
	            	cardskey = goodsMap.get("cardskey").toString();
	            }else {
	            	cardskey = "";
				}
	            
	            if(goodsMap.get("cardsmoney") != null){
	            	cardsmoney = goodsMap.get("cardsmoney").toString();
	            }else {
	            	cardsmoney = "";
				}
	            
	            if(goodsMap.get("cardsdate") != null){
	            	cardsdate = goodsMap.get("cardsdate").toString();
	            }else {
	            	cardsdate = "";
				}
	            
	            if(goodsMap.get("cardsstate") != null){
	            	cardsstate = goodsMap.get("cardsstate").toString();
	            	if(cardsstate.equals("0")){
	            		cardsstate="未充值";
	            	}else{
	            		cardsstate="已充值";
	            	}
	            }else {
	            	cardsstate = "";
				}
	            
	          //充值卡号 
	           label = new jxl.write.Label(excelCol++, row, cardskey, wc);
	           sheet.addCell(label);
	           
	           //充值卡金额
	           label = new jxl.write.Label(excelCol++, row, cardsmoney, wc);
	           sheet.addCell(label);  
	           
	           //截至日期
	           label = new jxl.write.Label(excelCol++, row, cardsdate, wc);
	           sheet.addCell(label); 
	           
	           //截至日期
	           label = new jxl.write.Label(excelCol++, row, cardsstate, wc);
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

