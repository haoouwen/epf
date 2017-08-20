/*
 
 * Package:com.rbt.servie.impl
 * FileName: RedpacketService.java 
 */
package com.rbt.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.rbt.common.util.RandomStrUtil;
import com.rbt.dao.IExredbagDao;
import com.rbt.dao.IRedpacketDao;
import com.rbt.model.Exredbag;
import com.rbt.model.Redpacket;
import com.rbt.service.IRedpacketService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 红包Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 20:50:54 CST 2015
 */
@SuppressWarnings("unchecked")
@Service
public class RedpacketService extends GenericService<Redpacket,String> implements IRedpacketService {
	
	@Autowired
	private IExredbagDao exredbagDao;
	
	IRedpacketDao redpacketDao;

	@Autowired
	public RedpacketService(IRedpacketDao redpacketDao) {
		super(redpacketDao);
		this.redpacketDao = redpacketDao;
	}
	
	
	/**
	 * 下载红包
	 * @param num 红包数量
	 * @param red_id 红包ID
	 * @return
	 * @throws Exception
	 */
	public boolean downRedBag(String num, String red_id) throws Exception {
		boolean flag = false;	
	       //获取优惠券对象
		   Redpacket redpacket = this.redpacketDao.get(red_id);
		   
		 //减少数量
			if(redpacket!=null){
				
				if(redpacket.getRed_num()!=null&&!"".equals(redpacket.getRed_num())&&!redpacket.getRed_num().equals("不限制")){
					
				   Integer newcoupon_num=Integer.parseInt(redpacket.getRed_num())-Integer.parseInt(num);
				   redpacket.setRed_num(newcoupon_num.toString());
				   this.redpacketDao.update(redpacket);
				}
				
			}
		   
		   
		   //创建下载优惠券的数量
		   for(int i = 0; i < Integer.valueOf(num); i++) {
			   Exredbag exredbag = new Exredbag();
			   exredbag.setRed_id(red_id);
			   exredbag.setEx_state("0");
			   exredbag.setRed_no(redpacket.getRed_no()+""+RandomStrUtil.getRand("10"));
			   exredbagDao.insert(exredbag);
	       }
		   
		    //获取下载优惠券的集合
	        Map map = new HashMap();
		    map.put("red_id", red_id);
		    map.put("ex_state", "0");
		    map.put("limit", num);
			List excouponsList = exredbagDao.getList(map);
			
		
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String nowtime = df.format(new Date());
			response.setHeader("Content-Disposition", "filename=" + nowtime
					+ "Coupon.xls");// attachment //
			// WritableWorkbook是JexcelApi的一个类。
			// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
			OutputStream os = response.getOutputStream();// 取得输出流
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("红包表", 0); // 组织excel文件的内容
			jxl.write.Label label = null;
			int excelCol = 0;
			int row = 0;
			try {
				WritableCellFormat wc = new WritableCellFormat();
				wc.setAlignment(Alignment.LEFT); // 设置居中

				label = new jxl.write.Label(excelCol++, row, "红包名称", wc);
				sheet.setColumnView(1, 50);
				sheet.addCell(label);
				
				for (int i = 0; i < excouponsList.size(); i++) {
					Map logMap = (Map) excouponsList.get(i);
					excelCol = 0;
					row = i + 1;
					label = new jxl.write.Label(excelCol++, row, logMap.get(
							"red_no").toString(), wc);
					sheet.addCell(label);
				}
				flag = true;

			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				// 生成excel文件
				workbook.write();
				workbook.close();
				os.close();

			}
			
			return flag;
	}




}
