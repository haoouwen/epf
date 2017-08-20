/*
 * Package:com.rbt.servie.impl
 * FileName: FloorinfoService.java 
 */
package com.rbt.service.impl;
import java.util.HashMap;
import java.util.Map;

import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IFloorinfoDao;
import com.rbt.dao.IFloormarkDao;
import com.rbt.dao.IGoodfloormarkDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.model.Floorinfo;
import com.rbt.model.Floormodel;
import com.rbt.model.Goodfloormark;
import com.rbt.service.IFloorinfoService;
import com.rbt.service.IGoodfloormarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EscapeBodyTag;

/**
 * @function 功能 楼层管理Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:33:52 CST 2015
 */
@Service
public class FloorinfoService extends GenericService<Floorinfo,String> implements IFloorinfoService {
	
	IFloorinfoDao floorinfoDao;

	@Autowired
	public FloorinfoService(IFloorinfoDao floorinfoDao) {
		super(floorinfoDao);
		this.floorinfoDao = floorinfoDao;
	}
	@Autowired
	private IGoodfloormarkDao goodfloormarkDao;
	@Autowired
	private IFloormarkDao floormarkDao;
	
	
	
	public boolean deltetallinfo(String f_id){
		if(!ValidateUtil.isRequired(f_id)){
			String f_ids[]=f_id.split(",");
			for(String str_f_id:f_ids){
				if(!ValidateUtil.isRequired(str_f_id)){
					Map flMap=new HashMap();
					flMap.put("f_id","0");
					flMap.put("ff_id",str_f_id);
					floormarkDao.updateInfo(flMap);
					goodfloormarkDao.delete(str_f_id);
					floorinfoDao.delete(str_f_id);
				}
			}
			return  true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	/**
	 * @Method Description : 修改楼层信息
	 * @author: HXK
	 * @date : Aug 13, 2015 10:34:50 AM
	 * @param 
	 * @return return_type
	 */
	public void updateFloorInfo(Floorinfo floorinfo,Floormodel floormodel,String goods_relate_str,String floormark_str){
		
		//先执行删除 楼层存在的信息
		 goodfloormarkDao.delete(floorinfo.getF_id());
		/////////////////////////执行新增/////////////////////////////////
		 //goodfloormark 字段  goods_id,fm_id,f_id,gm_name,img_path,gm_url,gm_type,cat_attr,gm_sort,gm_position
		 //	gm_type:类型：0商品，1广告，2分类
		//新增第一条广告位
		 insertGfmAdv(floorinfo.getF_id(),"tab0左上角第一张图片",floormodel.getTab0_pos1_img(),floormodel.getTab0_pos1_url(), Constants.pos_tab0_00);
		 //插入所选的6个分类
		 insertGfmCategory(floorinfo.getF_id(),floormodel.getCat_attr_list());
		 //插入tab0 第一个广告位第1张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_1(),floormodel.getImg_tab0_0_1(),floormodel.getUrl_tab0_0_1(),Constants.pos_tab0_00_01);
		//插入tab0 第一个广告位第2张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_2(),floormodel.getImg_tab0_0_2(),floormodel.getUrl_tab0_0_2(),Constants.pos_tab0_00_02);
		//插入tab0 第一个广告位第3张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_3(),floormodel.getImg_tab0_0_3(),floormodel.getUrl_tab0_0_3(),Constants.pos_tab0_00_03);
		//插入tab0 第一个广告位第4张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_4(),floormodel.getImg_tab0_0_4(),floormodel.getUrl_tab0_0_4(),Constants.pos_tab0_00_04);
		//插入tab0 第一个广告位第5张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_5(),floormodel.getImg_tab0_0_5(),floormodel.getUrl_tab0_0_5(),Constants.pos_tab0_00_05);
		//插入tab0 第一个广告位第6张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_6(),floormodel.getImg_tab0_0_6(),floormodel.getUrl_tab0_0_6(),Constants.pos_tab0_00_06);
		//插入tab0 第一个广告位第7张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_7(),floormodel.getImg_tab0_0_7(),floormodel.getUrl_tab0_0_7(),Constants.pos_tab0_00_07);
		//插入tab0 第一个广告位第8张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_8(),floormodel.getImg_tab0_0_8(),floormodel.getUrl_tab0_0_8(),Constants.pos_tab0_00_08);
		//插入tab0 第一个广告位第9张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_9(),floormodel.getImg_tab0_0_9(),floormodel.getUrl_tab0_0_9(),Constants.pos_tab0_00_09);
		//插入tab0 第一个广告位第10张图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_0_10(),floormodel.getImg_tab0_0_10(),floormodel.getUrl_tab0_0_10(),Constants.pos_tab0_00_10);
		//插入tab0 第二个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_1(),floormodel.getImg_tab0_1(),floormodel.getUrl_tab0_1(),Constants.pos_tab0_01);
		//插入tab0 第3个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_2(),floormodel.getImg_tab0_2(),floormodel.getUrl_tab0_2(),Constants.pos_tab0_02);
		//插入tab0 第4个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_3(),floormodel.getImg_tab0_3(),floormodel.getUrl_tab0_3(),Constants.pos_tab0_03);
		//插入tab0 第5个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_4(),floormodel.getImg_tab0_4(),floormodel.getUrl_tab0_4(),Constants.pos_tab0_04);
		//插入tab0 第6个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_5(),floormodel.getImg_tab0_5(),floormodel.getUrl_tab0_5(),Constants.pos_tab0_05);
		//插入tab0 第7个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_6(),floormodel.getImg_tab0_6(),floormodel.getUrl_tab0_6(),Constants.pos_tab0_06);
		//插入tab0 第8个广告位图片信息
		 insertGfmAdv(floorinfo.getF_id(),floormodel.getTitle_tab0_7(),floormodel.getImg_tab0_7(),floormodel.getUrl_tab0_7(),Constants.pos_tab0_07);
		 //插入商品
		 insertGfmGood(floorinfo.getF_id(),floormark_str,goods_relate_str);
		 
	}
	
	
	
	
	
	
	
	/**
	 * @Method Description : 插入数据广告
	 * @author: HXK
	 * @date : Aug 13, 2015 1:17:31 PM
	 * @param f_id 楼层ID
	 * @param gm_name 名称
	 * @param img_path 图片路径
	 * @param gm_url 链接地址
	 * @param gm_type 类型 0商品，1广告，2分类
	 * @param gm_position 位置
	 */
	@SuppressWarnings("unused")
	private void insertGfmAdv(String f_id,String gm_name,String img_path,String gm_url,String gm_position){
		if(img_path!=null&&!"".equals(img_path)){
			insertGFM("","",f_id,gm_name,img_path,gm_url,"1","","0",gm_position);
		}
	}
	/**
	 * @Method Description : 插入数据分类
	 * @author: HXK
	 * @date : Aug 13, 2015 1:17:31 PM
	 * @param f_id 楼层ID
	 * @param gm_name 名称
	 * @param img_path 图片路径
	 * @param gm_url 链接地址
	 * @param gm_type 类型 0商品，1广告，2分类
	 * @param gm_position 位置
	 */
	@SuppressWarnings("unused")
	private void insertGfmCategory(String f_id,String cat_attr){
		if(!ValidateUtil.isRequired(cat_attr)){
			cat_attr=cat_attr.replaceAll(" ", "");
		}
		insertGFM("","",f_id,"tab0左边推荐6个分类","","","2",cat_attr,"0",Constants.pos_tab0_cat);
	}
	/**
	 * @Method Description : 插入数据商品
	 * @author: HXK
	 * @date : Aug 13, 2015 1:17:31 PM
	 * @param f_id 楼层ID
	 * @param floormark_str 楼层ID
	 * @param goods_relate_str 商品ID
	 * @param gm_type 类型 0商品，1广告，2分类
	 */
	@SuppressWarnings("unused")
	private void insertGfmGood(String f_id,String floormark_str,String goods_relate_str){
		 if(floormark_str!=null&&!"".equals(floormark_str)){
			 floormark_str=floormark_str.substring(0,floormark_str.length()-1);
			 String floormark_str_s[]=floormark_str.split(",");
			 String goods_relate_str_s[]=goods_relate_str.split(",");
			 if(floormark_str_s!=null&&floormark_str_s.length>0&&goods_relate_str_s!=null&&goods_relate_str_s.length>0){
				 for(int i=0;i<floormark_str_s.length;i++){
					 if(goods_relate_str_s[i]!=null&&!"".equals(goods_relate_str_s[i])){
						 String sub_goods_relate_str_s[]=goods_relate_str_s[i].split("_");
						 if(sub_goods_relate_str_s!=null&&sub_goods_relate_str_s.length>0){
							 for(String gid:sub_goods_relate_str_s){
								 if(gid!=null&&!"".equals(gid.trim())){
									 insertGFM(gid.trim(),floormark_str_s[i],f_id,"标签商品","","","0","","0","goods_pos_00");
								 }
							 }
						 }
					 }
				 }
			 }
			 
		 }
	}
	/**
	 * @Method Description : 插入数据通用
	 * @author: HXK
	 * @date : Aug 13, 2015 1:17:31 PM
	 * @param goods_id 商品ID
	 * @param fm_id 楼层标签ID
	 * @param f_id 楼层ID
	 * @param gm_name 名称
	 * @param img_path 图片路径
	 * @param gm_url 链接地址
	 * @param gm_type 类型
	 * @param cat_attr 商品
	 * @param gm_sort 排序
	 * @param gm_position 位置
	 */
	@SuppressWarnings("unused")
	private void insertGFM(String goods_id,String fm_id,String f_id,String gm_name,
			String img_path,String gm_url,String gm_type,String cat_attr,String gm_sort,String gm_position){
			 Goodfloormark goodfloormark=new Goodfloormark();
			 goodfloormark.setCat_attr(cat_attr);
			 goodfloormark.setF_id(f_id);
			 goodfloormark.setFm_id(fm_id);
			 goodfloormark.setGm_name(gm_name);
			 goodfloormark.setGm_position(gm_position);
			 goodfloormark.setGm_sort(gm_sort);
			 goodfloormark.setGm_type(gm_type);
			 goodfloormark.setGm_url(gm_url);
			 goodfloormark.setGoods_id(goods_id);
			 goodfloormark.setImg_path(img_path);
			 goodfloormarkDao.insert(goodfloormark);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

