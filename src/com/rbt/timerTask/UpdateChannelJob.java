/*
  
 
 * Package:com.timerTask
 * FileName: UpdateChannelJobAction.java 
 */
package com.rbt.timerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.CreateSpringContext;
import com.rbt.model.Channel;
import com.rbt.service.IChannelService;
import com.rbt.function.SysconfigFuc;

;

/**
 * @function 功能 执行栏目定时更新
 * @author 创建人 HXK
 * @date 创建日期 2014-09-24
 */
public class UpdateChannelJob extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			String ifflageString = "";// 获取系统配置是否启用更新的值
			ifflageString = SysconfigFuc.getSysValue("cfg_qtzUpdateChannel");
			// 根据系统配置表取值，如果cfg_qtzUpdateChannel的值为0：表示启用定时更新栏目；1：不启用定时更新
			if (ifflageString.equals("0")) {
				//updateChannelPage();// 执行更新栏目的方法
			}
		} catch (Exception e) {
			System.err.println("定时更新全部栏目出现异常情况");
			e.printStackTrace();
		}
	}

	/*
	 * 记录网站栏目信息对象
	 */
	private Channel channel;
	/*
	 * 记录网站栏目信息信息集合
	 */
	public List channelList;

	/**
	 * 执行更行动作的判断
	 * 
	 * @param mchannel
	 * @param type
	 */
	public void chanvalue(Channel mchannel, String type) {
		DoHtml pHtml = new DoHtml();
		if (type.equals("1"))// 判断请求的动作是：更新栏目页，还是更新详细栏目页；1为：更新栏目页；0：更新详细栏目
		{
			//pHtml.doArticleHtml(mchannel);
		} else {
			//pHtml.doIndexHtml(mchannel);
		}
	}

	/*
	 * 将List的值，转换为对象的值
	 */
	public Channel switchChannel(HashMap myMap) {
		Channel myChannel = new Channel();
		myChannel.setArticle_rule(myMap.get("article_rule").toString());
		myChannel.setArticle_temp(myMap.get("article_temp").toString());
		myChannel.setCh_id(myMap.get("ch_id").toString());
		myChannel.setCh_level(myMap.get("ch_level").toString());
		myChannel.setCh_name(myMap.get("ch_name").toString());
		myChannel.setCh_title(myMap.get("ch_title").toString());
		myChannel.setFile_name(myMap.get("file_name").toString());
		myChannel.setMeta_desc(myMap.get("meta_desc").toString());
		myChannel.setMeta_keyword(myMap.get("meta_keyword").toString());
		myChannel.setModule_type(myMap.get("module_type").toString());
		myChannel.setRemark(myMap.get("remark").toString());
		myChannel.setSave_dir(myMap.get("save_dir").toString());
		myChannel.setSort_no(myMap.get("sort_no").toString());
		myChannel.setTemp_path(myMap.get("temp_path").toString());
		myChannel.setUp_ch_id(myMap.get("up_ch_id").toString());
		return myChannel;
	}

	/**
	 * 方法描述：AJAX更新栏目信息的方法
	 * 
	 * @throws Exception
	 */
	public void updateChannelPage() throws Exception {
		List mychannelList = new ArrayList();
		Map pageMap = new HashMap();
		IChannelService channelupService = (IChannelService) getContext()
				.getBean("channelService");
		mychannelList = channelupService.getList(pageMap);
		if (mychannelList != null && mychannelList.size() != 0) {
			for (int i = 0; i < mychannelList.size() - 1; i++) {
				HashMap myMap = new HashMap();
				myMap = (HashMap) mychannelList.get(i);
				chanvalue(switchChannel(myMap), "0");// 执行更新的动作

			}

		}
	}

	/**
	 * @return the ChannelList
	 */
	public List getChannelList() {
		return channelList;
	}

	/**
	 * @param channelList
	 *            the ChannelList to set
	 */
	public void setChannelList(List channelList) {
		this.channelList = channelList;
	}

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param Channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
