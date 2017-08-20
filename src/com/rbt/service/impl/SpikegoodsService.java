/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SpikegoodsService.java 
 */
package com.rbt.service.impl;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rbt.common.util.PageUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.dao.IGoodsDao;
import com.rbt.dao.ISpikegoodsDao;
import com.rbt.function.GoodsSpreadFuc;
import com.rbt.model.Spikegoods;
import com.rbt.service.ISpikegoodsService;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 秒杀商品Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Mar 29 15:32:29 CST 2014
 */
@Service
public class SpikegoodsService extends GenericService<Spikegoods, String>
		implements ISpikegoodsService {

	@Autowired
	private IGoodsDao goodsDao;

	ISpikegoodsDao spikegoodsDao;

	@Autowired
	public SpikegoodsService(ISpikegoodsDao spikegoodsDao) {
		super(spikegoodsDao);
		this.spikegoodsDao = spikegoodsDao;
	}

	public int getWebCount(Map map) {
		return this.spikegoodsDao.getWebCount(map);
	}

	public List getWebList(Map map) {
		return this.spikegoodsDao.getWebList(map);
	}

	/**
	 * @author : HZX
	 * @date : Feb 13, 2014 2:36:07 PM
	 * @Method Description :获取前台页面所需值
	 */
	public Map getIndexMap(int cfg_spike_day, String spikeday, String time,
			int pages_s, int pageSize_s) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取搜索条件
		String keyword = "";
		if (request.getParameter("wd") != null
				&& !request.getParameter("wd").equals("")) {
			keyword = URLDecoder.decode(request.getParameter("wd"), "UTF-8");
		}

		// 获取搜索分类条件
		String catEn_name = "";
		if (request.getParameter("catEn_name") != null
				&& !request.getParameter("catEn_name").equals("")) {
			catEn_name = URLDecoder.decode(request.getParameter("catEn_name"),
					"UTF-8");
		}

		// 前台页面所需值
		Map indexMap = new HashMap();
		// 秒杀日期排程列表
		List dateList = new ArrayList();
		// 秒杀列表
		List spikegoodsList;
		// 推广商品列表
		List goodsspreadList;
		// 当前系统小时
		String current_hour = "";
		String current_day;
		// 分页分隔符
		String pageBar = "";
		// 秒杀日期排程列表
		for (int i = 0; i < cfg_spike_day; i++) {
			// 获取当前时间
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.get(Calendar.DAY_OF_MONTH)
					+ i);
			Map dateMap = new HashMap();
			dateMap.put("month", calendar.get(Calendar.MONTH) + 1);
			dateMap.put("day", calendar.get(Calendar.DATE));
			dateList.add(dateMap);
		}
		// 获取当前所在小时（24小时制）
		Calendar calendar = Calendar.getInstance();
		current_hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		current_day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		// 获取秒杀列表
		Map spikeMap = new HashMap();
		spikeMap.put("in_day", spikeday);
		spikeMap.put("end_date", "end_date");

		// 搜索条件
		if(!ValidateUtil.isRequired(keyword)&&!ValidateUtil.isRequired(catEn_name)){
			spikeMap.put("spike_title", keyword);
			spikeMap.put("cat_attr", catEn_name);
		}else if (!ValidateUtil.isRequired(keyword)) {
			spikeMap.put("spike_title", keyword);
		} else if (!ValidateUtil.isRequired(catEn_name)) {
			spikeMap.put("cat_attr", catEn_name);
		} else {
			if (!ValidateUtil.isRequired(time)) {
				spikeMap.put("time", time);
			} else {
				spikeMap.put("time", current_hour);
			}
		}
		spikeMap.put("info_state_s", "1");
		// 分页控件
		int count = this.spikegoodsDao.getWebCount(spikeMap);
		PageUtil page = new PageUtil();
		page.setCurPage(pages_s);
		page.setPageSize(pageSize_s);
		page.setTotalRow(count);
		pageBar = page.getWebToolsMenu();
		spikeMap.put("start", page.getStart());
		spikeMap.put("limit", page.getEnd());
		spikegoodsList = this.spikegoodsDao.getWebList(spikeMap);
		// 获取推广商品
		goodsspreadList = GoodsSpreadFuc.getGoodsSpreadList("3", false);
		indexMap.put("dateList", dateList);
		indexMap.put("current_hour", current_hour);
		indexMap.put("current_day", current_day);
		indexMap.put("spikeday", spikeday);
		indexMap.put("pageBar", pageBar);
		indexMap.put("spikegoodsList", spikegoodsList);
		indexMap.put("goodsspreadList", goodsspreadList);
		return indexMap;
	}

	public String insertGetPk(Spikegoods spikegoods) {
		// 修改商品的活动状态
		Map map = new HashMap();
		map.put("goods_id", spikegoods.getGoods_id());
		map.put("active_state", "2");
		goodsDao.updateActiveState(map);
		return this.spikegoodsDao.insertGetPk(spikegoods);
	}

	/**
	 * @author:HXM
	 * @date:May 9, 20149:30:42 AM
	 * @param:
	 * @Description:为集合添加一个标识来判断，秒杀是在开始前后，或是进行
	 */
	public List changList(List list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (HashMap) list.get(i);
				// 获取倒计时时间秒数
				SimpleDateFormat sd = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				long nowTime = new Date().getTime();
				long flagTime;
				long flagTime1;
				if (map.get("end_date") != null
						&& map.get("start_date") != null) {
					Date date = sd.parse(map.get("end_date").toString());
					Date date1 = sd.parse(map.get("start_date").toString());
					flagTime = date.getTime() - nowTime;
					flagTime1 = date1.getTime() - nowTime;
					if (flagTime < 0) {
						map.put("active_state", "3");// 判断开杀后
					} else if (flagTime1 > 0) {
						map.put("active_state", "1");// 判断开杀前
					} else {
						map.put("active_state", "2");// 判断开杀中
					}
				} else {
					map.put("active_state", "4");
				}
				list.set(i, map);
			}
		}
		return list;
	}

}
