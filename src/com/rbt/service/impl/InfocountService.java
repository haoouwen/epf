/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: InfocountService.java 
 */
package com.rbt.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.rbt.dao.IInfocountDao;
import com.rbt.model.Infocount;
import com.rbt.service.IInfocountService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 数据统计Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Wed Jan 30 10:28:21 CST 2014
 */
@Service
public class InfocountService extends GenericService<Infocount, String>
		implements IInfocountService {

	IInfocountDao infocountDao;

	@Autowired
	public InfocountService(IInfocountDao infocountDao) {
		super(infocountDao);
		this.infocountDao = infocountDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.InfocountService#getRepeatTitle(java.util.Map)
	 */
	public int getRepeatTitle(Map map) {
		return this.infocountDao.getRepeatTitle(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.InfocountService#getSevenDaysList(java.util.Map)
	 */
	public List getSevenDaysList(Map map) {
		return this.infocountDao.getSevenDaysList(map);
	}

	public List getTotalList(Map map) {
		return this.infocountDao.getTotalList(map);
	}

	public List getRankingList(Map map) {
		return this.infocountDao.getRankingList(map);
	}

	public List getBuycountList(Map map) {
		return this.infocountDao.getBuycountList(map);
	}

	public List getOrdernumList(Map map) {
		return this.infocountDao.getOrdernumList(map);
	}

	public List getMembernumList(Map map) {
		return this.infocountDao.getMembernumList(map);
	}

	public List getMoneyList(Map map) {
		return this.infocountDao.getMoneyList(map);
	}

	public int getRankingCount(Map map) {
		return infocountDao.getRankingCount(map);
	}

	public int getMoneyCount(Map map) {
		return infocountDao.getMoneyCount(map);
	}

	public int getBuyCount(Map map) {
		return infocountDao.getBuyCount(map);
	}

	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 12:53:43 PM
	 * @Method Description：
	 */
	public Map sevendays(List infocountList, String sevendatatime,
			String sevengoodsorder) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		if (infocountList != null && infocountList.size() > 0) {
			for (int j = 6; j >= 0; j--) {
				// 获取当前时间
				String cur_date_str = "";
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, -j);// 取当前日期的前n天.
				cur_date_str = format.format(cal.getTime());
				sevendatatime += cur_date_str + ",";
				Integer count = 0;
				for (int i = 0; i < infocountList.size() - 1; i++) {
					HashMap gMap = new HashMap();
					gMap = (HashMap) infocountList.get(i);
					if (gMap != null) {
						if (gMap.get("order_time").toString().substring(0, 10)
								.equals(cur_date_str)) {
							sevengoodsorder += gMap.get("countnum").toString()
									+ ",";
							count = 1;
						}
					}
				}
				if (count != 1) {
					sevengoodsorder += "0" + ",";
				}
			}
		}
		if (sevendatatime != null) {
			sevendatatime = sevendatatime.substring(0,
					sevendatatime.length() - 1);
		}
		if (sevengoodsorder != null) {
			sevengoodsorder = sevengoodsorder.substring(0, sevengoodsorder
					.length() - 1);
		}
		Map map = new HashMap();
		map.put("sevendatatime", sevendatatime);
		map.put("sevengoodsorder", sevengoodsorder);
		return map;
	}

	/**
	 * 方法描述：获取日期数据字符串
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map getinfo(List infototalList, double[] elements, String x_axis,
			double max, double steps) {
		elements = new double[infototalList.size()];
		for (int i = 0; i < infototalList.size(); i++) {
			HashMap mapvalue = (HashMap) infototalList.get(i);
			String timetype = mapvalue.get("timetype").toString();
			x_axis += timetype + ",";
			String totalnum = mapvalue.get("totalamount").toString();
			elements[i] = Double.parseDouble(totalnum);

		}
		if (elements.length > 0) {
			max = elements[0];
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] > max) {
					max = elements[i];
				}
			}
			max = max * 12 / 10;
			steps = max / 10;
		}
		Map map = new HashMap();
		map.put("elements", elements);
		map.put("x_axis", x_axis);
		map.put("max", max);
		map.put("steps", steps);
		return map;

	}

	/**
	 * 导出数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void exportExcel(List list, String count_type) throws Exception {
		if (count_type.equals("totalsales")) {
			// 总销售额
			totalsales(list);
		} else if (count_type.equals("ranking")) {
			// 销售量（额）排行
			ranking(list);
		} else if (count_type.equals("buycount")) {
			// 会员购物量排行
			buycount(list);
		} else if (count_type.equals("ordernum")) {
			// 订单数
			ordernum(list);
		} else if (count_type.equals("membernum")) {
			// 会员数
			membernum(list);
		} else if (count_type.equals("moneycount")) {
			// 财务报表
			moneycount(list);
		}
	}

	public void totalsales(List list) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "totalsales.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("总销售额", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "时间段", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "销售总额", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);


			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat(
					"yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(
					customDateFormat);
			for (int i = 0; i < list.size(); i++) {
				Map logMap = (Map) list.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"timetype").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"totalamount").toString(), wc);
				sheet.addCell(label);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}

	public void ranking(List list) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "ranking.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("销售量（额）排行", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "商品编号", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "商品名称", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "销售总量", wc);
			sheet.setColumnView(2, 13);
			sheet.addCell(label);
			
			label = new jxl.write.Label(excelCol++, row, "销售总额", wc);
			sheet.setColumnView(3, 13);
			sheet.addCell(label);

			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat(
					"yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(
					customDateFormat);
			for (int i = 0; i < list.size(); i++) {
				Map logMap = (Map) list.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"goods_id").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"goods_name").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
				       "num").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"price").toString(), wc);
				sheet.addCell(label);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}

	public void buycount(List list) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "buycount.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("会员购物量排行", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "会员名称", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "购买总量", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "购买总额", wc);
			sheet.setColumnView(2, 13);
			sheet.addCell(label);

			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat(
					"yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(
					customDateFormat);
			for (int i = 0; i < list.size(); i++) {
				Map logMap = (Map) list.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"cust_name").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"buynum").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
				        "totalprice").toString(), wc);
		        sheet.addCell(label);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}

	public void ordernum(List list) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "ordernum.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("订单数", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "时间段", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "销售总量", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "订单状态", wc);
			sheet.setColumnView(2, 13);
			sheet.addCell(label);

			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat(
					"yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(
					customDateFormat);
			for (int i = 0; i < list.size(); i++) {
				Map logMap = (Map) list.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"timetype").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"totalamount").toString(), wc);
				sheet.addCell(label);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}

	public void membernum(List list) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "membernum.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("会员数", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "时间段", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "新增总数", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);

			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat(
					"yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(
					customDateFormat);
			for (int i = 0; i < list.size(); i++) {
				Map logMap = (Map) list.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"timetype").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"totalamount").toString(), wc);
				sheet.addCell(label);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}

	public void moneycount(List list) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime = df.format(new Date());
		response.setHeader("Content-Disposition", "filename=" + nowtime
				+ "moneycount.xls");// attachment //
		// WritableWorkbook是JexcelApi的一个类。
		// 以下可以理解为创建一个excel文件，然后在excel里面创建一个表
		OutputStream os = response.getOutputStream();// 取得输出流
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("财务报表", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "时间段", wc);
			sheet.setColumnView(0, 25);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "手续费费用", wc);
			sheet.setColumnView(1, 13);
			sheet.addCell(label);

			label = new jxl.write.Label(excelCol++, row, "配送费用", wc);
			sheet.setColumnView(2, 13);
			sheet.addCell(label);
			
			label = new jxl.write.Label(excelCol++, row, "保价费用", wc);
			sheet.setColumnView(3, 13);
			sheet.addCell(label);
			
			label = new jxl.write.Label(excelCol++, row, "订单总金额", wc);
			sheet.setColumnView(4, 13);
			sheet.addCell(label);

			jxl.write.DateTime dateTime;
			jxl.write.DateFormat customDateFormat = new jxl.write.DateFormat(
					"yyyy-MM-dd");// 时间格式
			WritableCellFormat dateFormat = new WritableCellFormat(
					customDateFormat);
			for (int i = 0; i < list.size(); i++) {
				Map logMap = (Map) list.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"timetype").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"comm_free").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
				         "ship_free").toString(), wc);
				sheet.addCell(label);
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"insured").toString(), wc);
				sheet.addCell(label);				
				label = new jxl.write.Label(excelCol++, row, logMap.get(
				        "total_amount").toString(), wc);
				sheet.addCell(label);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 生成excel文件
			workbook.write();
			workbook.close();
			os.close();

		}
	}
	
	/**
	 * 会员总数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalMember(Map map)throws Exception{
		return this.infocountDao.getTotalMember(map);
	}
	/**
	 * 新增会员数量
	 */
	public Map getGrowMember(Map map) throws Exception{
		return this.infocountDao.getGrowMember(map);
	}
	
	/**
	 * 区域会员消费总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalAreaAmount(Map map) throws Exception{
		return this.infocountDao.getTotalAreaAmount(map);
	}
	
	/**
	 * 会员消费总金额
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalAmount(Map map) throws Exception{
		return this.infocountDao.getTotalAmount(map);
	}
	
	/**
	 * 被会员消费的商品数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalGoods(Map map) throws Exception{
		return this.infocountDao.getTotalGoods(map);
	}
	
	/**
	 * 被会员消费的同类商品数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getTotalCatGoods(Map map) throws Exception{
		return this.infocountDao.getTotalCatGoods(map);
	}
	
	
	/**
	 * 销售统计的数量
	 * @param map
	 * @return
	 */
	public int getOperationCount(Map map) throws Exception{
	     return this.infocountDao.getOperationCount(map);
	}
	
	/**
	 * 销售统计
	 * @param map
	 * @return
	 */
	public List getSalesAmountList(Map map) throws Exception{
		return this.infocountDao.getSalesAmountList(map);
	}
	
	/**
	 * 按商品种类
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByCat(Map map)throws Exception{
		return this.infocountDao.getCountSalesByCat(map);
	}
	
    public List getSalesByCatList(Map map)throws Exception{
    	return this.infocountDao.getSalesByCatList(map);
    }
	
	public int getSaleByCatCount(Map map)throws Exception{
		return this.infocountDao.getSaleByCatCount(map);
	}
	
	/**
	 * 按地区统计
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByArea(Map map)throws Exception{
		return this.infocountDao.getCountSalesByArea(map);
	}
	
	/**
	 * 按购买量排序统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getCountSalesByPurchases(Map map)throws Exception{
		return this.infocountDao.getCountSalesByPurchases(map);
	}
	public int getCountByPurchases(Map map) throws Exception{
		return this.infocountDao.getCountByPurchases(map);
	}
	
}
