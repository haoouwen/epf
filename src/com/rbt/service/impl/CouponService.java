/*
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CouponService.java 
 */
package com.rbt.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.ICouponDao;
import com.rbt.dao.IExcouponsDao;
import com.rbt.model.Coupon;
import com.rbt.model.Excoupons;
import com.rbt.service.ICouponService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 优惠券Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Fri Aug 07 14:37:49 CST 2015
 */
@SuppressWarnings("unchecked")
@Service
public class CouponService extends GenericService<Coupon,String> implements ICouponService {
	
	@Autowired
	private IExcouponsDao excouponsDao;
	
	ICouponDao couponDao;

	@Autowired
	public CouponService(ICouponDao couponDao) {
		super(couponDao);
		this.couponDao = couponDao;
	}
	
	
	/**
	 * 下载优惠券
	 * @param num 优惠券数量
	 * @param coupon_id 优惠券ID
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean downCoupon(String num, String coupon_id) throws Exception {
	   
		boolean flag = false;	
		 //获取优惠券对象
		 Coupon coupon = this.couponDao.get(coupon_id);
		//减少数量
		if(coupon!=null){
			
			if(coupon.getCoupon_num()!=null&&!"".equals(coupon.getCoupon_num())&&!coupon.getCoupon_num().equals("不限制")){
				
			   Integer newcoupon_num=Integer.parseInt(coupon.getCoupon_num())-Integer.parseInt(num);
			   coupon.setCoupon_num(newcoupon_num.toString());
			   this.couponDao.update(coupon);
			}
			
		}
	   //创建下载优惠券的数量
	   for(int i = 0; i < Integer.valueOf(num); i++) {
    	   Excoupons excoupons = new Excoupons();
    	   excoupons.setCoupon_id(coupon_id);
    	   excoupons.setEx_state("0");
    	   excoupons.setCoupon_no(coupon.getCoupon_no()+""+RandomStrUtil.getRand("10"));
    	   excouponsDao.insert(excoupons);
       }
	   
	    //获取下载优惠券的集合
        Map map = new HashMap();
	    map.put("coupon_id", coupon_id);
	    map.put("ex_state", "0");
	    map.put("limit", num);
		List excouponsList = excouponsDao.getList(map);
		
	
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
		WritableSheet sheet = workbook.createSheet("优惠券表", 0); // 组织excel文件的内容
		jxl.write.Label label = null;
		int excelCol = 0;
		int row = 0;
		try {
			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.LEFT); // 设置居中

			label = new jxl.write.Label(excelCol++, row, "优惠券名称", wc);
			sheet.setColumnView(1, 50);
			sheet.addCell(label);
			
			for (int i = 0; i < excouponsList.size(); i++) {
				Map logMap = (Map) excouponsList.get(i);
				excelCol = 0;
				row = i + 1;
				label = new jxl.write.Label(excelCol++, row, logMap.get(
						"coupon_no").toString(), wc);
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
	/**
	 * @Method Description :通过商品ID 获得前台支持领取优惠券
	 * @author: 胡惜坤
	 * @date : Nov 5, 2015 5:15:07 PM
	 * @param 
	 * @return List
	 */
	public List getCouponByGoodsID(String  goods_id,String menberlevel){
		List gooosCouponList=new ArrayList();
		if(!ValidateUtil.isRequired(goods_id)){
			//获取优惠券列表
			List couponList=new ArrayList();
			HashMap couponMap=new HashMap();
			//状态 1 启用 0 禁用
			couponMap.put("info_state", "1");
			couponMap.put("time", "time");
			couponMap.put("is_show", "1");
			if(!ValidateUtil.isRequired(menberlevel)){
				couponMap.put("member_level", "1");
			}
			couponList=couponDao.getList(couponMap);
			//存在符合条件的优惠券
			if(couponList.size()>0){
				for(int i=0;i<couponList.size();i++){
					HashMap gMap=new HashMap();
					gMap=(HashMap)couponList.get(i);
					if(gMap!=null&&gMap.get("term")!=null){
						// 判断优惠券里面是否包含该商品
						if(ifcontansInfo(goods_id,gMap.get("term").toString().replaceAll(" ", ""))==true){
							gooosCouponList.add(gMap);
						}
					}
				}
				
			}
		}
		return gooosCouponList;
	}
	 public boolean ifcontansInfo(String gid,String gstr){
			boolean  fage=false;
			if(gid!=null&&!"".equals(gid)&&gstr!=null&&!"".equals(gstr)){
				String gstrs[]=gstr.split(",");
				if(gstrs!=null){
					for(int i=0;i<=gstrs.length-1;i++){
						if(gstrs[i]!=null){
							if(gid.equals(gstrs[i].toString())){
								fage=true;
							}
						}
					}
				}
			}
			return fage;
		}
	
	/**
	 * @Method Description :通过商品ID 获取商品参加几个优惠券
	 * @author: 胡惜坤
	 * @date : Nov 5, 2015 5:15:07 PM
	 * @param 
	 * @return List
	 */
	public List getCouponByGoodsList(List coupongoodsList){
		if(coupongoodsList!=null&&coupongoodsList.size()>0){
			
			for(int i=0;i<coupongoodsList.size();i++){
				String goods_id="";
				Map gcMap = new HashMap();
				gcMap=(HashMap)coupongoodsList.get(i);
				goods_id=gcMap.get("goods_id").toString();
				//获取优惠券列表
				List couponList=new ArrayList();
				HashMap couponMap=new HashMap();
				//状态 1 启用 0 禁用
				couponMap.put("info_state", "1");
				couponMap.put("time", "time");
				couponList=couponDao.getList(couponMap);
				String couponname="";
				//存在符合条件的优惠券
				if(couponList.size()>0){
					for(int j=0;j<couponList.size();j++){
						HashMap gMap=new HashMap();
						gMap=(HashMap)couponList.get(j);
						if(gMap!=null&&gMap.get("term")!=null){
							// 判断优惠券里面是否包含该商品
							if(ifcontansInfo(goods_id,gMap.get("term").toString().replaceAll(" ", ""))==true){
								couponname=couponname+gMap.get("coupon_name").toString()+",";
							}
						}
					}
					
				}
				if(!ValidateUtil.isRequired(couponname)){
					couponname=couponname.trim().substring(0,couponname.length()-1);
				}
				gcMap.put("coupon_name", couponname);
			}
			
			
		}
		return coupongoodsList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

