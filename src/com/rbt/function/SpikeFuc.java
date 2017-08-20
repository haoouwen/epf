/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.ISpikegoodsService;

/**
 * @function 功能 前台广告显示
 * @author 创建人 HXK
 * @date 创建日期 2014-09-22
 */
public class SpikeFuc extends CreateSpringContext {

	// 取秒杀信息
	public static int isHava(String spikeday, String time) {
		ISpikegoodsService spikegoodsService = (ISpikegoodsService) getContext()
				.getBean("spikegoodsService");
		// 获取秒杀列表
		Map spikeMap = new HashMap();
		spikeMap.put("in_day", spikeday);
		spikeMap.put("end_date", "end_date");
		spikeMap.put("time", time);
		spikeMap.put("info_state_s", "1");
		List spikegoodsList = spikegoodsService.getWebList(spikeMap);
		// 0:代表没有 1:代表有
		if (spikegoodsList != null && spikegoodsList.size() > 0) {
			return 1;
		} else
			return 0;
	}

}